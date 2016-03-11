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
package org.eclipse.epf.library.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.VariabilityInfo;
import org.eclipse.epf.library.edit.configuration.CategoriesItemProvider;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.Log;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.Scope;

/**
 * A method configuration filter to allow filtering element 
 * and realize the element feature values within a configuration.
 * 
 * @author Phong Nguyen Le
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigurationFilter extends AdapterImpl implements IConfigurator {

	protected MethodConfiguration methodConfig;	
	//private Viewer viewer;
	
	// set the default behavior for configuration view to true, i.e. contributors are discarded.
	private boolean discardContributors = true;
	

	/**
	 * constructor
	 * @param methodConfig a <code>MethodConfiguration</code>
	 * @param viewer a <code>Viewer</code>
	 */
	public ConfigurationFilter(MethodConfiguration methodConfig) {
		this.methodConfig = methodConfig;
		//this.viewer = viewer;
	}

	public ConfigurationFilter(MethodConfiguration methodConfig, boolean discardContributors) {
		this(methodConfig);
		this.discardContributors = discardContributors;
	}
	
	/**
	 * @see org.eclipse.epf.library.edit.IFilter#accept(java.lang.Object)
	 */
	public boolean accept(Object obj) {
		if (methodConfig == null) {
			if (obj instanceof MethodPlugin) {
				MethodPlugin plugin = (MethodPlugin) obj;
				if (null != MethodPluginPropUtil.getMethodPluginPropUtil().getCustomizedParent(plugin)) {
					return false;
				}
			}
			return true;
		}
		
		obj = LibraryUtil.unwrap(obj);

		if (methodConfig instanceof Scope && obj instanceof MethodElement) {
			return ((Scope) methodConfig).inScope((MethodElement) obj);
		}
		
		if ( (ElementRealizer.isExtendReplaceEnabled() ||  
				(obj instanceof VariabilityElement) && ConfigurationHelper.isExtendReplacer((VariabilityElement)obj) )
				&& FeatureValue.isBlankIndicator(obj) ) {
			return false;
		}
		
		if (obj instanceof MethodPackage) {
			return methodConfig.getMethodPackageSelection().contains(obj);
		} else if (obj instanceof MethodElement) {
			return ConfigurationHelper.canShow((MethodElement) obj,
					methodConfig);
		} else if (obj instanceof ItemProviderAdapter) {
			return true;
		} else {
			if (Log.DEBUG) {
				System.out
						.println("Object filtered: " + (obj == null ? null : obj.toString())); //$NON-NLS-1$
			}
		}
		return false;
	}
	

	/**
	 * get the realized children for the given object and feature
	 * 
	 * @param obj Object
	 * @param childFeature EStructuralFeature
	 * 
	 * @return Collection
	 */
	public Collection getChildren(Object obj, EStructuralFeature childFeature) {
		if (methodConfig == null)
			return null;

		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(methodConfig);
		// discard the contributors
		realizer.setDiscardContributor(this.discardContributors);

		if (childFeature != null && childFeature.isMany()) {
			if (obj instanceof MethodElement) {
				List value = ConfigurationHelper.calc0nFeatureValue(
						(MethodElement) obj, childFeature, realizer);
				return value;
			}
		}

		return null;
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
//	public void notifyChanged(final Notification msg) {
//		if (viewer == null) {
//			return;
//		}
//
//		SafeUpdateController.syncExec(new Runnable() {
//			public void run() {
//				switch (msg.getEventType()) {
//				case Notification.ADD:
//				case Notification.ADD_MANY:
//				case Notification.REMOVE:
//				case Notification.REMOVE_MANY:
//					viewer.refresh();
//				}
//			}
//		});
//
//	}

	/**
	 * @see org.eclipse.epf.library.edit.IConfigurator#getMethodConfiguration()
	 */
	public MethodConfiguration getMethodConfiguration() {
		return methodConfig;
	}

	/**
	 * @see org.eclipse.epf.library.edit.IConfigurator#setMethodConfiguration(org.eclipse.epf.uma.MethodConfiguration)
	 */
	public void setMethodConfiguration(MethodConfiguration config) {
		methodConfig = config;
	}

	protected void resolveElementVariabilityList(VariabilityElement element,
			VariabilityInfo info, boolean includeBase, ElementRealizer realizer) {

		if (methodConfig == null) {
			return;
		}
		
		//resolve to include contributors
		resolveElementContributors(element,info,realizer);

		// if the element is an extended element, get the base element's
		// contributors
		// NOTE: the base element itself should not be included since it's
		// already handled
		// in the activity realiztion in ItemProviders.
		if (ConfigurationHelper.isExtender(element)) {
			MethodElement base = element.getVariabilityBasedOnElement();
			VariabilityElement e = (VariabilityElement) ConfigurationHelper
					.getCalculatedElement(base, realizer);

			// if includebase is true, add the element to the inheritance list
			if (includeBase) {
				List values = info.getInheritanceList();
				if (!values.contains(e)) {
					values.add(e);
				}
			}

			// resolve the base to include additional contributors and/or base
			resolveElementVariabilityList(e, info, includeBase, realizer);

		}

	}
	
	protected void resolveElementContributors(VariabilityElement element,
			VariabilityInfo info, ElementRealizer realizer){
		// if the element has contributors in the configuration, get the
		// reference properties
		// if a contributor has replacer, it's replacer is used
		List items = ConfigurationHelper.getContributors(element, methodConfig);
		if (items != null && items.size() > 0) {
			for (Iterator it = items.iterator(); it.hasNext();) {
				VariabilityElement e = (VariabilityElement) it.next();
				List values = info.getContributors();
				if (!values.contains(e)) {
					values.add(e);
				}
				resolveElementVariabilityList(e, info, false, realizer);
			}
		}
	}

	/*
	 * resolve the variability of the element and get a list of contributors.
	 * 
	 * This method is used for realizing actitivy breakdown elements in the
	 * itemProviders.
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurator#getVariabilityInfo(org.eclipse.epf.uma.VariabilityElement)
	 */
	public VariabilityInfo getVariabilityInfo(VariabilityElement ve) {

		// calculate the element first
		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(methodConfig, true, true);
		return getVariabilityInfo(ve, realizer);
	}

	protected VariabilityInfo getVariabilityInfo(VariabilityElement ve, ElementRealizer realizer) {

		// calculate the element first
		VariabilityElement e = (VariabilityElement) ConfigurationHelper
				.getCalculatedElement(ve, realizer);

		if (e == null) {
			return null;
		}

		VariabilityInfo info = new VariabilityInfo(ve);
		info.getInheritanceList().add(e);

		resolveElementVariabilityList(e, info, true, realizer);

		return info;
	}
	
	public Object resolve(Object element) {
		if (element instanceof VariabilityElement) {
			ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(methodConfig, true, true);
			return ConfigurationHelper.getCalculatedElement(
					(MethodElement) element, realizer);
		}
		return element;
	}
	
	
	/**
	 * get filter for uncategorized tasks
	 * 
	 * @return IFilter
	 */
	public IFilter getUncategorizedTaskFilter() {
		return new IFilter() {
			public boolean accept(Object obj) {
				// 158924 - wrong categories in configuration view
				// should use the default realizer
				// we should not discard the discipline contributors 
				// instead should realize to the base discipline
				return ( obj instanceof Task ) && 
					ConfigurationHelper.calc0nFeatureValue(
							(Task)obj, 
							AssociationHelper.Task_Disciplines, 
							DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}
	
	/**
	 * get filter for workproducts without a domain
	 * 
	 * @return IFilter
	 */
	public IFilter getDomainUncategorizedWorkProductFilter(){
		return new IFilter() {
			public boolean accept(Object obj) {
				// 158924 - wrong categories in configuration view
				// should use the default realizer
				// we should not discard the domain contributors 
				// instead should realize to the base domain
				return ( obj instanceof WorkProduct ) && 
					ConfigurationHelper.calc0nFeatureValue(
							(WorkProduct)obj, 
							AssociationHelper.WorkProduct_Domains, 
							DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}
	
	/**
	 * get filter for workproducts without a WP Type
	 * 
	 * @return IFilter
	 */
	public IFilter getWpTypeUncategorizedWorkProductFilter(){
		return new IFilter() {
			public boolean accept(Object obj) {
				// 158924 - wrong categories in configuration view
				// should use the default realizer
				// we should not discard the WPType contributors 
				// instead should realize to the base 
				return ( obj instanceof WorkProduct ) && 
					ConfigurationHelper.calc0nFeatureValue(
							(WorkProduct)obj, 
							AssociationHelper.WorkProduct_WorkProductTypes, 
							DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}
	
	/**
	 * get filter for uncategorized roles
	 * 
	 * @return IFilter
	 */
	public IFilter getUncategorizedRoleFilter(){
		return new IFilter() {
			public boolean accept(Object obj) {
				// 158924 - wrong categories in configuration view
				// should use the default realizer
				// we should not discard the roleset contributors 
				// instead should realize to the base 
				return ( obj instanceof Role ) && 
					ConfigurationHelper.calc0nFeatureValue(
							(Role)obj, 
							AssociationHelper.Role_RoleSets, 
							DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}

	/**
	 * get filter for uncategorized tool mentors
	 * 
	 * @return IFilter
	 */
	public IFilter getUncategorizedToolMentorFilter() {
		return new IFilter() {
			public boolean accept(Object obj) {
				// 158924 - wrong categories in configuration view
				// should use the default realizer
				// we should not discard the tool contributors 
				// instead should realize to the base 
				return ( obj instanceof ToolMentor ) && 
					ConfigurationHelper.calc0nFeatureValue(
							(ToolMentor)obj, 
							AssociationHelper.ToolMentor_Tools, 
							DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}
	
	/**
	 * get filter for disciplines and displine groupings
	 * 
	 * @return IFilter
	 */
	public IFilter getDisciplinesFilter() {
		return new IFilter() {
			public boolean accept(Object obj) {
				// [Bug 162603] New: Replacing disciplines do appear inside their replaced elements discipline groupings 
				// as well as outside of the discipline grouping
				if ( obj instanceof DisciplineGrouping ) {
					return true;
				}
				
				return ( obj instanceof Discipline ) && 
				ConfigurationHelper.calc0nFeatureValue(
						(MethodElement)obj, 
						AssociationHelper.Discipline_DisciplineGroupings, 
						DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}
	
	/**
	 * get filter for rolesets and roleset groupings
	 * 
	 * @return IFilter
	 */
	public IFilter getRoleSetsFilter() {
		return new IFilter() {
			public boolean accept(Object obj) {
				// [Bug 162603] New: Replacing disciplines do appear inside their replaced elements discipline groupings 
				// as well as outside of the discipline grouping
				// same for roleset grouping
				if ( obj instanceof RoleSetGrouping ) {
					return true;
				}
				
				return ( obj instanceof RoleSet ) && 
					ConfigurationHelper.calc0nFeatureValue(
							(MethodElement)obj, 
							AssociationHelper.RoleSet_RoleSetGrouppings, 
							DefaultElementRealizer.newElementRealizer(methodConfig)).isEmpty();					
			}
		};
	}
	
	/**
	 * @return an IRealizationManager instance
	 */
	public IRealizationManager getRealizationManager() {
		MethodConfiguration c = getMethodConfiguration();
		return ConfigurationHelper.getDelegate().getRealizationManager(c);
	}
	
	public Collection<?> getModifiedChildren(Object parentObject, Collection children) {
//		if (ConfigurationHelper.getDelegate().isAuthoringMode()) {
//			return children;
//		}		
//		if (ConfigurationHelper.getDelegate().isPublishingMode()) {
//			return children;
//		}
		if (! ConfigurationHelper.getDelegate().filterOutEmptyCategories()) {
				return children;
		}	
		if (children == null || children.isEmpty()) {
			return children;			
		}
		
		boolean modified = false;
		List modifiedChildren = new ArrayList();
		if (parentObject instanceof CategoriesItemProvider || TngUtil.unwrap(parentObject) instanceof ContentCategory) {
			for (Object child : children) {
				if (child instanceof ContentCategory) {
					if (! isEmpty((ContentCategory) child)) {
						modifiedChildren.add(child);
					} else {
						modified = true;
					}
				} else {
					modifiedChildren.add(child);
				}
			}
		}		
		return modified ? modifiedChildren : children;
	}
	
	public boolean isEmpty(ContentCategory cc) {
		return isEmpty(cc, new HashMap<ContentCategory, Boolean>());
	}
	
	public static Object isEmptyCheckLock = new Object();
	
	private boolean isEmpty(ContentCategory cc, Map<ContentCategory, Boolean> processedMap) {
		Boolean bvalue = processedMap.get(cc);
		if (bvalue == null) {
			Map map = MethodElementPropUtil.getMethodElementPropUtil().getExtendedPropertyMap(cc, true);
			try {
				map.put(isEmptyCheckLock, true);
				boolean b = isEmpty_(cc, processedMap);
				bvalue = b ? Boolean.TRUE : Boolean.FALSE;
				processedMap.put(cc, bvalue);
			} finally {
				map.remove(isEmptyCheckLock);
			}
		}		
		return bvalue.booleanValue();
	}
	
	private boolean isEmpty_(ContentCategory cc, Map<ContentCategory, Boolean> processedMap) {
		LibraryEditUtil util = LibraryEditUtil.getInstance();
		
		EReference ref = getCategorizedRef(cc);
		Object value = ref == null ? null : util.calc0nFeatureValue(cc, ref, getMethodConfiguration());
		if (value instanceof List) {
			for (Object item : (List) value) {
				if (! (item instanceof ContentCategory)) {
					return false;
				}
			}
		}

		ref = getSubCategoryRef(cc);
		value = ref == null ? null : util.calc0nFeatureValue(cc, ref, getMethodConfiguration());
		if (value instanceof List) {
			for (Object item : (List) value) {
				if (item instanceof ContentCategory) {
					if (! isEmpty((ContentCategory) item, processedMap)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	private EReference getSubCategoryRef(ContentCategory cc) {
		UmaPackage up = UmaPackage.eINSTANCE;
		if (cc instanceof CustomCategory) {
			return up.getCustomCategory_CategorizedElements();
		}
		if (cc instanceof Discipline) {
			return up.getDiscipline_Subdiscipline();
		}
		if (cc instanceof DisciplineGrouping) {
			return up.getDisciplineGrouping_Disciplines();
		}
		if (cc instanceof Domain) {
			return up.getDomain_Subdomains();
		}
		if (cc instanceof RoleSet) {
			return null;
		}
		if (cc instanceof RoleSetGrouping) {
			return up.getRoleSetGrouping_RoleSets();
		}
		if (cc instanceof Tool) {
			return null;
		}
		if (cc instanceof WorkProductType) {
			return null;
		}		
		return null;
	}
	
	private EReference getCategorizedRef(ContentCategory cc) {
		UmaPackage up = UmaPackage.eINSTANCE;
		if (cc instanceof CustomCategory) {
			return up.getCustomCategory_CategorizedElements();
		}
		if (cc instanceof Discipline) {
			return up.getDiscipline_Tasks();
		}
		if (cc instanceof DisciplineGrouping) {
			return null;
		}
		if (cc instanceof Domain) {
			return up.getDomain_WorkProducts();
		}
		if (cc instanceof RoleSet) {
			return up.getRoleSet_Roles();
		}
		if (cc instanceof RoleSetGrouping) {
			return null;
		}
		if (cc instanceof Tool) {
			return up.getTool_ToolMentors();
		}
		if (cc instanceof WorkProductType) {
			return up.getWorkProductType_WorkProducts();
		}		
		return null;
	}
	
	public ElementRealizer getRealizer() {
		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(methodConfig);
		return realizer;
	}
	
}