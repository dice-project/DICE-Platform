//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.configuration;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * The item provider adapter for a practice.
 * 
 * @author Weiping Lu
 * @since 1.5
 */
public class PracticeItemProvider extends
		org.eclipse.epf.uma.provider.PracticeItemProvider implements
		IConfigurable, ILibraryItemProvider {

	private IFilter filter;

	private Object parent;

	private String label;
	
	private static String ROADMAP = "roadmap"; //$NON-NLS-1$
	private static String CATEGORIES = "categories"; //$NON-NLS-1$
	private static String KEY_CONCEPTS = "Key Concepts"; //$NON-NLS-1$
	private static String WORKPRODUCTS_GROUP = "Work Products"; //$NON-NLS-1$
	private static String TASKS_GROUP = "Tasks"; //$NON-NLS-1$
	private static String ROLES_GROUP = "Roles"; //$NON-NLS-1$
	private static String ACTIVITIES_GROUP = "Activities"; //$NON-NLS-1$
	private static String GUIDANCES_GROUP = "Guidance"; //$NON-NLS-1$
	private static String UNKNOWN = "unknown"; //$NON-NLS-1$

	private static String GUIDANCES_CHECKLISTS = "Checklists"; //$NON-NLS-1$
	private static String GUIDANCES_Example = "Examples"; //$NON-NLS-1$
	private static String GUIDANCES_PRACTICES = "Practices"; //$NON-NLS-1$
	private static String GUIDANCES_REPORTS = "Reports"; //$NON-NLS-1$
	private static String GUIDANCES_REUSABLEASSETS = "Reusable Assets"; //$NON-NLS-1$
	private static String GUIDANCES_SUPPORTINGMATERIALS = "Supporting Materials"; //$NON-NLS-1$
	private static String GUIDANCES_TEMPLATES = "Templates"; //$NON-NLS-1$
	private static String Guidances_TermDefinitions = "Term Definitions"; //$NON-NLS-1$	
	private static String GUIDANCES_TOOLMENTORS = "Tool Mentors"; //$NON-NLS-1$
	private static String GUIDANCES_WORKPRODUCTGUIDELINES = "Guidelines"; //$NON-NLS-1$
	private static String GUIDANCES_ESTIMATIONCONSIDERATIONS = "Estimation Considerations"; //$NON-NLS-1$
	
	public PracticeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection<?> getChildren(Object object) {
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * @param children
	 * @return
	 */
	public Collection<?> getModifiedChildren(Object parentObject, Collection children) {
		List ret = new ArrayList();

		GroupingHelper groupingHelper = new GroupingHelper(this);
		grouping(parentObject, ret, children, groupingHelper);
		
		return ret;
	}
	
	private void grouping(Object parentObject, List ret, Collection children, 
			GroupingHelper groupingHelper) {
		Map<String, List> map = getSubGroupMap(children, groupingHelper);
		
		boolean toSort = true;
		if (parentObject instanceof MethodElement) {
			MethodElement elem = (MethodElement) parentObject;
			if (elem != null) {
				toSort = ! CategorySortHelper.isManualCategorySort(elem);
			}
		}
		String[] keys = groupingHelper.getKeysInOrder();
		String[] prefixes = groupingHelper.getPrefixInOrder();
		
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			
			String prefix = prefixes[i];
			List subgroupChildren = map.get(key);
			if (subgroupChildren == null || subgroupChildren.isEmpty()) {
				continue;
			}
			if (groupingHelper.toGroup(key, subgroupChildren)) {
				if (toSort) {
					sort(subgroupChildren);
				}
				subgroupChildren = groupingHelper.nestedGrouping(parentObject, key, subgroupChildren);
				PracticeSubgroupItemProvider sub = new PracticeSubgroupItemProvider(
						getAdapterFactory(), key, prefix, getImageObject(key), subgroupChildren, parentObject);
				ret.add(sub);
			} else {
				if (toSort) {
					sort(subgroupChildren);
				}
				ret.addAll(subgroupChildren);
			}
		}
	}

	public static void sort(List subgroupChildren) {
		if (subgroupChildren.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE.getPresNameComparator();
			Collections.<FulfillableElement>sort(subgroupChildren, comparator);
		}
	}	
	
	public static Map<String, List> getSubGroupMap(Collection children, GroupingHelper groupingHelper) {
		 Map<String, List> map = new LinkedHashMap<String, List>(); 
		
		for (Object child: children) {
			String key = groupingHelper.getSubGroupName(child);
			add(map, key, child);
		}				
		
		return map;
	}
	
	private Object getImageObject(String subGroupName) {
		String imageStr = getImageStr(subGroupName); 
		return imageStr == null ? null : LibraryEditPlugin.INSTANCE.getImage(imageStr);
	}
	
	public static String getImageStr(String subGroupName) {
		
		String imageStr = "full/obj16/Folder"; //$NON-NLS-1$
		
		if (true) {		
			if (subGroupName.equals(getUIString("_UI_Key_Concepts"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Concepts"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_WorkProducts_group"))) { //$NON-NLS-1$
				imageStr = "full/obj16/WorkProducts"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Tasks_group"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Tasks"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Roles_group"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Roles"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Activities_group"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Processes"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_group"))) { //$NON-NLS-1$
				imageStr = "full/obj16/GuidanceFolder"; //$NON-NLS-1$
				
				//Guidance sub groups
			} else if (subGroupName.equals(getUIString("_UI_Guidances_Checklists"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Checklists"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_Examples"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Examples"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_Practices"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Practices"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_Reports"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Reports"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_ReusableAssets"))) { //$NON-NLS-1$
				imageStr = "full/obj16/ReusableAssets"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_SupportingMaterials"))) { //$NON-NLS-1$
				imageStr = "full/obj16/SupportingMaterials"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_Templates"))) { //$NON-NLS-1$
				imageStr = "full/obj16/Templates"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_TermDefinitions"))) { //$NON-NLS-1$
				imageStr = "full/obj16/TermDefinitions"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_ToolMentors"))) { //$NON-NLS-1$
				imageStr = "full/obj16/ToolMentors"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_WorkProductGuidelines"))) { //$NON-NLS-1$
				imageStr = "full/obj16/WorkProductGuidelines"; //$NON-NLS-1$
			} else if (subGroupName.equals(getUIString("_UI_Guidances_EstimationConsiderations"))) { //$NON-NLS-1$
				imageStr = "full/obj16/EstimationConsiderations"; //$NON-NLS-1$
			}

		}
	
		return imageStr;
	}

	
	private static String getUIString(String key) {
		return LibraryEditPlugin.INSTANCE.getString(key);
	}

	private static void add(Map<String, List> map, String key, Object value) {
		List list = map.get(key);
		if (list == null) {
			list = new ArrayList();
			map.put(key, list);
		}
		list.add(value);
	}
	
	public boolean hasChildren(Object object) {
		return true;
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.PracticeItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures
					.add(UmaPackage.Literals.PRACTICE__CONTENT_REFERENCES);
			childrenFeatures
					.add(UmaPackage.Literals.PRACTICE__ACTIVITY_REFERENCES);
			childrenFeatures.add(UmaPackage.Literals.PRACTICE__SUB_PRACTICES);
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		Object parent = TngUtil
				.getNavigatorParentItemProvider((Guidance) object);
		if (parent == null) {
			return super.getParent(object);
		}
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_Practice_type")); //$NON-NLS-1$
	}

	public Object getImage(Object object) {
		if (object instanceof DescribableElement) {
			if (((DescribableElement) object).getNodeicon() != null) {
				URI imgUri = TngUtil.getFullPathofNodeorShapeIconURI(
						(DescribableElement) object,
						((DescribableElement) object).getNodeicon());
				Object image = LibraryEditPlugin.INSTANCE
						.getSharedImage(imgUri);
				if (image != null)
					return image;
			}
		}
		
		if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)object)) {	
			ImageDescriptor img = TngUtil.getImageForUdt((Practice)object);
			if (img != null) {
				return img;
			}
			return overlayImage(object, getResourceLocator().getImage(
			"full/obj16/UdtNode")); //$NON-NLS-1$
		}
		
		return super.getImage(object);
	}
	
	public static class GroupingHelper {
		
		private Object grouper;
		private List<String> udtTypeNameList = new ArrayList<String>();
		private Set<String> udtTypeNameSet = new HashSet<String>();
		
		public GroupingHelper(Object grouper) {
			this.grouper = grouper;
		}
		
		public GroupingHelper(Object grouper, boolean alwayGroup) {
			this.grouper = grouper;
		}
		
		protected Object getGrouper() {
			return grouper;
		}		
		
		protected String getSubGroupName(Object obj) {
			if (obj instanceof Roadmap) {
				return ROADMAP;
			}
			if (obj instanceof Concept) {
				return getUIString("_UI_Key_Concepts"); //$NON-NLS-1$
			}
			if (obj instanceof WorkProduct) {
				return getUIString("_UI_WorkProducts_group"); //$NON-NLS-1$
			}
			if (obj instanceof Task) {
				return getUIString("_UI_Tasks_group"); //$NON-NLS-1$
			}
			if (obj instanceof Role) {
				return getUIString("_UI_Roles_group"); //$NON-NLS-1$
			}
			if (obj instanceof Activity) {
				return getUIString("_UI_Activities_group"); //$NON-NLS-1$
			}
			if (obj instanceof Guidance) {
				if (obj instanceof Practice) {
					UserDefinedTypeMeta meta = PracticePropUtil.getPracticePropUtil().getUdtMeta((Practice) obj);
					if (meta != null) {						
						String name = meta.getRteNameMap().get(UserDefinedTypeMeta._typeName);
						if (udtTypeNameSet.add(name)) {
							udtTypeNameList.add(name);
						}
						return name;
					}
				}
				return getUIString("_UI_Guidances_group"); //$NON-NLS-1$
			}
			if (obj instanceof ContentCategory) {			
				return CATEGORIES;
			}
			
			return UNKNOWN;
		}
		
		public String[] getPrefixInOrder() {
			String[] prefixes = {
					ROADMAP,
					KEY_CONCEPTS,
					WORKPRODUCTS_GROUP,
					TASKS_GROUP,	
					ROLES_GROUP,	
					ACTIVITIES_GROUP,
					GUIDANCES_GROUP,
					CATEGORIES,
					UNKNOWN
			};
			
			if (! udtTypeNameList.isEmpty()) {
				Collections.sort(udtTypeNameList);
				int sz = prefixes.length + udtTypeNameList.size();
				String[] modified = new String[sz];
				for (int i = 0; i < prefixes.length; i++) {
					modified[i] = prefixes[i];
				}
				for (int i = prefixes.length; i < sz; i++) {
					modified[i] = udtTypeNameList.get(i - prefixes.length);
				}
				prefixes = modified;
			}
			
			return prefixes;
		}
		
		public String[] getKeysInOrder() {
			String[] keys = {
					ROADMAP,
					getUIString("_UI_Key_Concepts"),	//$NON-NLS-1$
					getUIString("_UI_WorkProducts_group"),	//$NON-NLS-1$
					getUIString("_UI_Tasks_group"),			//$NON-NLS-1$
					getUIString("_UI_Activities_group"),	//$NON-NLS-1$
					getUIString("_UI_Roles_group"),			//$NON-NLS-1$
					getUIString("_UI_Guidances_group"),		//$NON-NLS-1$
					CATEGORIES,
					UNKNOWN
			};
			
			if (! udtTypeNameList.isEmpty()) {
				Collections.sort(udtTypeNameList);
				int sz = keys.length + udtTypeNameList.size();
				String[] modifiedKesy = new String[sz];
				for (int i = 0; i < keys.length; i++) {
					modifiedKesy[i] = keys[i];
				}
				for (int i = keys.length; i < sz; i++) {
					modifiedKesy[i] = udtTypeNameList.get(i - keys.length);
				}
				keys = modifiedKesy;
			}			
			return keys;
		}
		
		public boolean toGroup(String key, List subgroupChildren) {
			if (udtTypeNameSet.contains(key)) {
				return true;
			}
			if (key.equals(ROADMAP) || 
				key.equals(CATEGORIES) ||
				key.equals(UNKNOWN) ||
				subgroupChildren.size() < 3) {
				return false;
			}
			return true;
		}
		
		public List<?> nestedGrouping(Object parentObject, String key, List<?> subgroupChildren) {
			if (! key.equals(PracticeItemProvider.getUIString("_UI_Guidances_group"))) { //$NON-NLS-1$
				return subgroupChildren;
			}
			
			List ret = new ArrayList<Object>();
			
			GroupingHelper groupingHelper = new GuidanceGroupingHelper(grouper);
			grouping(parentObject, ret, subgroupChildren, groupingHelper);
			
			return ret;
		}
		
		protected void grouping(Object parentObject, List ret,
				Collection children, GroupingHelper groupingHelper) {
			if (grouper instanceof PracticeItemProvider) {
				((PracticeItemProvider) grouper).grouping(parentObject, ret,
						children, groupingHelper);
			}
		}		
	}
	
	public static class GuidanceGroupingHelper extends GroupingHelper {
		public GuidanceGroupingHelper(Object grouper) {
			super(grouper);
		}
		
		protected String getSubGroupName(Object obj) {
			
			if (obj instanceof Checklist) {
				return getUIString("_UI_Guidances_Checklists"); //$NON-NLS-1$
			}
			if (obj instanceof Example) {
				return getUIString("_UI_Guidances_Examples"); //$NON-NLS-1$
			}
			if (obj instanceof Practice) {
				return getUIString("_UI_Guidances_Practices"); //$NON-NLS-1$
			}
			if (obj instanceof Report) {
				return getUIString("_UI_Guidances_Reports"); //$NON-NLS-1$
			}
			
			if (obj instanceof ReusableAsset) {
				return getUIString("_UI_Guidances_ReusableAssets"); //$NON-NLS-1$
			}
			if (obj instanceof SupportingMaterial) {
				return getUIString("_UI_Guidances_SupportingMaterials"); //$NON-NLS-1$
			}
			if (obj instanceof Template) {
				return getUIString("_UI_Guidances_Templates"); //$NON-NLS-1$
			}
			if (obj instanceof TermDefinition) {
				return getUIString("_UI_Guidances_TermDefinitions"); //$NON-NLS-1$
			}
			
			if (obj instanceof ToolMentor) {
				return getUIString("_UI_Guidances_ToolMentors"); //$NON-NLS-1$
			}
			
			if (obj instanceof Guideline) {
				return getUIString("_UI_Guidances_WorkProductGuidelines"); //$NON-NLS-1$
			}
					
			if (obj instanceof EstimationConsiderations) {
				return getUIString("_UI_Guidances_EstimationConsiderations"); //$NON-NLS-1$
			}
			
			return UNKNOWN;
			
		}
		
		public String[] getPrefixInOrder() {
			String[] prefixes = {
					GUIDANCES_CHECKLISTS,	
					GUIDANCES_Example,	
					GUIDANCES_PRACTICES,
					GUIDANCES_REPORTS,
					GUIDANCES_REUSABLEASSETS,
					GUIDANCES_SUPPORTINGMATERIALS,	
					GUIDANCES_TEMPLATES,
					Guidances_TermDefinitions,
					GUIDANCES_TOOLMENTORS,
					GUIDANCES_WORKPRODUCTGUIDELINES,
					GUIDANCES_ESTIMATIONCONSIDERATIONS,	
					UNKNOWN
			};
			
			return prefixes;
		}
		
		public String[] getKeysInOrder() {
			String[] keys = {
					getUIString("_UI_Guidances_Checklists"),	//$NON-NLS-1$
					getUIString("_UI_Guidances_Examples"),	//$NON-NLS-1$
					getUIString("_UI_Guidances_Practices"),			//$NON-NLS-1$
					getUIString("_UI_Guidances_Reports"),	//$NON-NLS-1$
					getUIString("_UI_Guidances_ReusableAssets"),			//$NON-NLS-1$
					getUIString("_UI_Guidances_SupportingMaterials"),		//$NON-NLS-1$
					getUIString("_UI_Guidances_Templates"),		//$NON-NLS-1$
					getUIString("_UI_Guidances_TermDefinitions"), //$NON-NLS-1$
					getUIString("_UI_Guidances_ToolMentors"),		//$NON-NLS-1$
					getUIString("_UI_Guidances_WorkProductGuidelines"),		//$NON-NLS-1$
					getUIString("_UI_Guidances_EstimationConsiderations"),		//$NON-NLS-1$
					UNKNOWN				
			};
			
			return keys;
		}
		
		@Override
		public boolean toGroup(String key, List subgroupChildren) {
			if (key.equals(UNKNOWN) || 
				subgroupChildren.size() < 3) {
				return false;
			}
			return true;
		}
		
		public List<?> nestedGrouping(Object parentObject, String key, List<?> subgroupChildren) {
			return subgroupChildren;
		}

	}
	
}