//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.epf.library.edit.ContextIFilter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.DisciplineCategoriesItemProvider;
import org.eclipse.epf.library.edit.category.RoleSetsItemProvider;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

/**
 * The item provider adapter for a method configuration in the Configuration
 * view.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class MethodConfigurationItemProvider extends
		org.eclipse.epf.uma.provider.MethodConfigurationItemProvider implements
		IGroupContainer, IConfigurable, IStatefulItemProvider {

	private Map groupItemProviderMap;

	// changed to protected, extended class can play with children variable.
	protected ArrayList children;

	private static boolean finalOnGetChildrenCall = false; 
	
	public static boolean isFinalOnGetChildrenCall() {
		return finalOnGetChildrenCall;
	}

	public static void setFinalOnGetChildrenCall(boolean b) {
		finalOnGetChildrenCall = b;
	}

	private IFilter disciplinesFilter = new IFilter() {

		public boolean accept(Object obj) {
			ContentCategory element = (ContentCategory) obj;
			// Browsing: With categories, replace generalization
			// causes both replacement and base to become invisible to browsing
			return DisciplineCategoriesItemProvider.accept(element) /*
																	 * &&
																	 * element.getVariabilityBasedOnElement() ==
																	 * null
																	 */;
		}
	};

	private static final IFilter domainFilter = new IFilter() {

		public boolean accept(Object obj) {
			// Browsing: With categories, replace generalization
			// causes both replacement and base to become invisible to browsing
			return obj instanceof Domain /*
											 * &&
											 * ((Domain)obj).getVariabilityBasedOnElement() ==
											 * null
											 */;
		}

	};

	private static final IFilter workProductTypesFilter = new IFilter() {

		public boolean accept(Object obj) {
			// Browsing: With categories, replace generalization
			// causes both replacement and base to become invisible to browsing
			return obj instanceof WorkProductType /*
													 * &&
													 * ((WorkProductType)obj).getVariabilityBasedOnElement() ==
													 * null
													 */;
		}

	};

	private IFilter roleSetsFilter = new IFilter() {

		public boolean accept(Object obj) {
			// Browsing: With categories, replace generalization
			// causes both replacement and base to become invisible to browsing
			return RoleSetsItemProvider.accept(obj) /*
													 * &&
													 * ((VariabilityElement)obj).getVariabilityBasedOnElement() ==
													 * null
													 */;
		}
	};

	private static final IFilter toolsFilter = new IFilter() {

		public boolean accept(Object obj) {
			// Browsing: With categories, replace generalization
			// causes both replacement and base to become invisible to browsing
			return obj instanceof Tool /*
										 * &&
										 * ((Tool)obj).getVariabilityBasedOnElement() ==
										 * null
										 */;
		}

	};

	private static final ContextIFilter customCategoriesFilter = new ContextIFilter() {

		public boolean accept(Object obj) {
			// Browsing: With categories, replace generalization
			// causes both replacement and base to become invisible to browsing
			return org.eclipse.epf.library.edit.category.CustomCategoriesItemProvider
					.accept(obj, getMethodConfiguration()) /*
									 * &&
									 * ((VariabilityElement)obj).getVariabilityBasedOnElement() ==
									 * null
									 */;
		}

	};
	
	// upcategorized filters can be reset based on configurator.
	private IFilter uncategorizedTaskFilter = new IFilter() {

		public boolean accept(Object obj) {
			return obj instanceof Task
					&& AssociationHelper.getDisciplines((Task) obj).isEmpty();
		}

	};

	private IFilter domainUncategorizedWorkProductFilter = new IFilter() {

		public boolean accept(Object obj) {
			return obj instanceof WorkProduct
					&& !AssociationHelper.getDomains((WorkProduct) obj).isEmpty();
		}

	};

	private IFilter wpTypeUncategorizedWorkProductFilter = new IFilter() {

		public boolean accept(Object obj) {
			return obj instanceof WorkProduct
					&& AssociationHelper.getWorkProductTypes((WorkProduct) obj)
							.isEmpty();
		}

	};

	private IFilter uncategorizedRoleFilter = new IFilter() {

		public boolean accept(Object obj) {
			return obj instanceof Role
					&& AssociationHelper.getRoleSets((Role) obj).isEmpty();
		}

	};

	private IFilter uncategorizedToolMentorFilter = new IFilter() {
		public boolean accept(Object obj) {
			return obj instanceof ToolMentor
					&& AssociationHelper.getTools((ToolMentor) obj).isEmpty();
		}

	};

	private IConfigurator configurator;

	private IFilter filter;

	/**
	 * Creates a new instance.
	 */
	public MethodConfigurationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (groupItemProviderMap != null) {
			for (Iterator iter = groupItemProviderMap.values().iterator(); iter
					.hasNext();) {
				Object adapter = iter.next();
				if (adapter instanceof IDisposable) {
					((IDisposable) adapter).dispose();
				}
			}
			groupItemProviderMap.clear();
			groupItemProviderMap = null;
		}

		if (children != null) {
			children.clear();
			children = null;
		}

		super.dispose();
	}

	public Collection getChildren(Object object) {
		// if(object instanceof MethodConfiguration) {
		// return Collections.singleton(new
		// ObjectLinkItemProvider(adapterFactory, object, (Notifier) object));
		// }
		// MethodConfiguration conf = (MethodConfiguration)
		// ((ObjectLinkItemProvider) object).getLinkedObject();
		MethodConfiguration conf = (MethodConfiguration) object;
		if (children == null) {
			children = new ArrayList();
			groupItemProviderMap = new HashMap();
			String name;
			Object uncategorizedImage;
			CategoriesItemProvider child;

			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Disciplines_group"); //$NON-NLS-1$
			child = new CategoriesItemProvider(
					adapterFactory,
					conf,
					name,
					LibraryEditPlugin.INSTANCE.getImage("full/obj16/Tasks_cfv"), //$NON-NLS-1$
					ModelStructure.DEFAULT.disciplineDefinitionPath);
			child.setParent(conf);
			child.setUncategorizedFilter(uncategorizedTaskFilter);
			child.setCategorizedFilter(disciplinesFilter);
			child.setUncategorizedLabel(LibraryEditPlugin.INSTANCE
					.getString("_UI_Uncategorized_Tasks_text")); //$NON-NLS-1$
			uncategorizedImage = LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/Tasks"); //$NON-NLS-1$
			child.setUncategorizedImage(uncategorizedImage);
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group"); //$NON-NLS-1$
			child = new CategoriesItemProvider(adapterFactory, conf, name,
					LibraryEditPlugin.INSTANCE.getImage("full/obj16/Domains"), //$NON-NLS-1$
					ModelStructure.DEFAULT.domainPath);
			child.setParent(conf);
			child.setUncategorizedFilter(domainUncategorizedWorkProductFilter);
			uncategorizedImage = LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/WorkProducts"); //$NON-NLS-1$
			child.setUncategorizedImage(uncategorizedImage);
			child.setCategorizedFilter(domainFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_WorkProductTypes_group"); //$NON-NLS-1$
			child = new WorkProductTypesItemProvider(adapterFactory, conf,
					name, LibraryEditPlugin.INSTANCE
							.getImage("full/obj16/WorkProducts"), //$NON-NLS-1$
					ModelStructure.DEFAULT.workProductTypePath);
			child.setParent(conf);
			child.setUncategorizedFilter(wpTypeUncategorizedWorkProductFilter);
			uncategorizedImage = LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/WorkProducts"); //$NON-NLS-1$
			child.setUncategorizedImage(uncategorizedImage);
			child.setCategorizedFilter(workProductTypesFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE.getString("_UI_Role_Sets_group"); //$NON-NLS-1$
			child = new CategoriesItemProvider(adapterFactory, conf, name,
					LibraryEditPlugin.INSTANCE.getImage("full/obj16/Roles"), //$NON-NLS-1$
					ModelStructure.DEFAULT.roleSetPath);
			child.setParent(conf);
			child.setUncategorizedFilter(uncategorizedRoleFilter);
			child.setCategorizedFilter(roleSetsFilter);
			child.setUncategorizedLabel(LibraryEditPlugin.INSTANCE
					.getString("_UI_Uncategorized_Roles_text")); //$NON-NLS-1$
			uncategorizedImage = LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/Roles"); //$NON-NLS-1$
			child.setUncategorizedImage(uncategorizedImage);
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group"); //$NON-NLS-1$
			child = new CategoriesItemProvider(adapterFactory, conf, name,
					LibraryEditPlugin.INSTANCE.getImage("full/obj16/Tools"), //$NON-NLS-1$
					ModelStructure.DEFAULT.toolPath);
			child.setParent(conf);
			child.setCategorizedFilter(toolsFilter);
			child.setUncategorizedFilter(uncategorizedToolMentorFilter);
			uncategorizedImage = LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/Tools"); //$NON-NLS-1$
			child.setUncategorizedImage(uncategorizedImage);
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"); //$NON-NLS-1$
			Object otherChild = new ProcessesItemProvider(adapterFactory, conf,
					ModelStructure.DEFAULT);
			children.add(otherChild);
			groupItemProviderMap.put(name, otherChild);

			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Custom_Categories_group"); //$NON-NLS-1$
			child = new CategoriesItemProvider(adapterFactory, conf, name,
					LibraryEditPlugin.INSTANCE
							.getImage("full/obj16/MethodPackages"), //$NON-NLS-1$
					ModelStructure.DEFAULT.customCategoryPath);
			child.setParent(conf);
			customCategoriesFilter.setContext(conf);
			child.setCategorizedFilter(customCategoriesFilter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			Collection<UserDefinedTypeMeta> udtTypes = LibraryEditUtil.getInstance().getUserDefinedTypes();		
			if (udtTypes != null && !udtTypes.isEmpty()) {
				name = LibraryEditPlugin.INSTANCE.getString("_UI_UdtElements_group"); //$NON-NLS-1$
	//			GuidanceItemProvider child2 = new GuidanceItemProvider(
	//					adapterFactory, conf, name, LibraryEditPlugin.INSTANCE.getImage("full/obj16/Practices"));
				Object image = overlayImage(object,  UmaEditPlugin.INSTANCE.getImage(
				"full/obj16/UdtNode")); //$NON-NLS-1$
				GuidanceItemProvider child2 = new GuidanceItemProvider(
				adapterFactory, conf, name, image);
				
				IFilter udtFilter = new IFilter() {
					public boolean accept(Object obj) {
						if (! (obj instanceof Practice)) {
							return false;
						}
						return PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj);
					}
				};
				child2.setGuidanceFilter(udtFilter);
				children.add(child2);
				groupItemProviderMap.put(name, child2);
			}
			
			name = LibraryEditPlugin.INSTANCE.getString("_UI_Guidances_group"); //$NON-NLS-1$
			GuidanceGroupingItemProvider child1 = new GuidanceGroupingItemProvider(
					adapterFactory, conf);
			child1.setFilter(filter);
			children.add(child1);
			groupItemProviderMap.put(name, child1);
						
		}

		return children;
	}

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.edit.provider.ItemProviderAdapter#getElements(java.lang.Object)
	// */
	// public Collection getElements(Object object) {
	// return Collections.singleton(object);
	// }
	//    
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		return super.hasChildren(object);
	}

	public String getText(Object object) {
		return TngUtil.getLabel(object,
				getString("_UI_MethodConfiguration_type")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		
		if ( (filter != configurator) && (filter instanceof IConfigurator) ) {
			configurator = (IConfigurator) filter;
			if ( configurator != null ) {			
				// create the uncategorized filters. If IConfigurator is set, get the filders from there.
				// otherwize, use the default one.
				// This is needed to allow extra uncategorized elements due to realization 
				// to show up in the un-categorized folder.
				// For example, if a disciple is replaced by another, the tasks in the replaced disciplie
				// should show up in the un-categorized tasks folder
				// 156438 - Variability of disciplines is not working correctly
				
				uncategorizedTaskFilter = configurator.getUncategorizedTaskFilter();
				domainUncategorizedWorkProductFilter = configurator.getDomainUncategorizedWorkProductFilter();
				wpTypeUncategorizedWorkProductFilter = configurator.getWpTypeUncategorizedWorkProductFilter();
				uncategorizedRoleFilter = configurator.getUncategorizedRoleFilter();
				uncategorizedToolMentorFilter = configurator.getUncategorizedToolMentorFilter();
				
				// [Bug 162603] New: Replacing disciplines do appear inside their replaced elements discipline groupings as well as outside of the discipline grouping
				// need to customize the filters for disciplines and rolesets
				disciplinesFilter = configurator.getDisciplinesFilter();
				roleSetsFilter = configurator.getRoleSetsFilter();
				
			}
		}
		this.filter = filter;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
	}

	// public Object getParent(Object object) {
	// return plugin;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.MethodConfigurationItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (configurator != null) {
			switch (notification.getFeatureID(MethodConfiguration.class)) {
			case UmaPackage.METHOD_CONFIGURATION__METHOD_PLUGIN_SELECTION:
			case UmaPackage.METHOD_CONFIGURATION__METHOD_PACKAGE_SELECTION:
				configurator.notifyChanged(notification);
				break;
			}
		}
	}

}
