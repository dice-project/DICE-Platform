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
package org.eclipse.epf.library.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.library.edit.command.AddToCategoryCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.command.RemoveFromCategoryCommand;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * This class contains all the routines used by the tool editor to manipulate
 * the associations of a method element in the library with other categories or
 * method elements
 * 
 * Use getAvailableXXX to retrieve a pick list of categories, method element for
 * a given method element. Use addToXXX to establish a new association. Use
 * removeFromXXX to remove an exisiting association.
 * 
 * Call releaseCategories() before the editor closes, passing in all the
 * categories that you have retrieved with getAvailableXXX() for the editor.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryManager {

	private static Comparator nameComparator = new Comparator() {

		public int compare(Object o1, Object o2) {
			if (o1 instanceof MethodElement && o2 instanceof MethodElement) {
				return ((MethodElement) o1).getName().compareTo(
						((MethodElement) o2).getName());
			}

			return 0;
		}

	};

	private static LibraryManager instance = null;

	public static final LibraryManager getInstance() {
		if (instance == null) {
			synchronized (LibraryManager.class) {
				if (instance == null) {
					instance = new LibraryManager();
				}
			}
		}
		return instance;
	}

	protected LibraryManager() {
	}

	// //////
	// Begin of code moved from MethodElementUtil and made private
	// //////
	/**
	 * Return categories in the model, given particular object and class
	 * 
	 * @param object
	 * @param class
	 * @param String[]
	 * @return
	 */
	public List getAvailableCategories(MethodElement object, Class classType,
			String[] path) {
		List itemList = new ArrayList();
		MethodPlugin model = UmaUtil.getMethodPlugin((EObject) object);

		if (model != null) {
			// add all categories from the current plugin
			//
			ContentPackage disciplinePkg = UmaUtil.findContentPackage(model,
					path);
			if (disciplinePkg != null) {
				EList contentCats = (EList) disciplinePkg.getContentElements();
				for (int i = 0; i < contentCats.size(); i++) {
					Object element = contentCats.get(i);

					if (classType.isInstance(element) && element != object) {
						itemList.add(element);
					}
				}
			}

			// add all categories from base plugins that are not extended
			//
			List list = Misc.getAllBase(model);
			// list.add(0, model);
			for (int j = 0; j < list.size(); j++) {
				ContentPackage parentDisciplines = UmaUtil.findContentPackage(
						((MethodPlugin) list.get(j)), path);
				if (parentDisciplines != null) {
					EList contentCats = (EList) parentDisciplines
							.getContentElements();
					for (int i = 0; i < contentCats.size(); i++) {
						Object element = contentCats.get(i);

						if (classType.isInstance(element)) {
							VariabilityElement item = (VariabilityElement) element;
							if (item.getVariabilityBasedOnElement() == null) {
								// make sure the itemList doesn't have a
								// category that extends this
								int k;
								for (k = 0; k < itemList.size(); k++) {
									if (((VariabilityElement) itemList.get(k))
											.getVariabilityBasedOnElement() == item)
										break;
								}
								if (k == itemList.size())
									itemList.add(item);

								// itemList.add(item);
							}
						}
					}
				}
			}
		}

		Collections.sort(itemList, nameComparator);

		return itemList;
	}

	// ////////
	// End of code moved from MethodElementUtil
	// ////////

	/**
	 * Gets all disciplines that are available to the given MethodElement
	 * object.
	 * 
	 * @param e
	 * @return list of Discipline objects
	 */
	public List getAvailableDisciplines(MethodElement e) {
		return getAvailableCategories(e, Discipline.class,
				ModelStructure.DEFAULT_DISCIPLINE_DEF_PATH);
	}

	/**
	 * Gets all domains that are available to the given MethodElement object.
	 * 
	 * @param e
	 * @return list of Domain objects
	 */
	public List getAvailableDomains(MethodElement e) {
		List domains = getAvailableCategories(e, Domain.class,
				ModelStructure.DEFAULT_DOMAIN_PATH);

		// get all the domains including subdomains
		//
		List allDomains = new ArrayList();
		for (Iterator iter = domains.iterator(); iter.hasNext();) {
			Domain domain = (Domain) iter.next();
			Iterator domainTree = new AbstractTreeIterator(domain, true) {

				protected Iterator getChildren(Object object) {
					return ((Domain) object).getSubdomains().iterator();
				}

			};
			while (domainTree.hasNext()) {
				allDomains.add(domainTree.next());
			}
		}

		Collections.sort(allDomains, nameComparator);

		return allDomains;
	}

	/**
	 * Gets all work product types that are available to the given MethodElement
	 * object.
	 * 
	 * @param e
	 * @return list of WorkProductType objects
	 */
	public List getAvailableWorkProductTypes(MethodElement e) {
		return getAvailableCategories(e, WorkProductType.class,
				ModelStructure.DEFAULT_WORK_PRODUCT_TYPE_PATH);
	}

	/**
	 * Gets all role sets that are available to the given MethodElement object.
	 * 
	 * @param e
	 * @return list of RoleSet objects
	 */
	public List getAvailableRoleSets(MethodElement e) {
		return getAvailableCategories(e, RoleSet.class,
				ModelStructure.DEFAULT.roleSetPath);
	}

	/**
	 * Gets all custom categories that are available to the given MethodElement
	 * object.
	 * 
	 * @param e
	 * @return list of RoleSet objects
	 */
	public List getAvailableCustomCategories(MethodElement e) {
		List categories = getAvailableCategories(e, CustomCategory.class,
				ModelStructure.DEFAULT_CUSTOM_CATEGORY_PATH);
		// filter out the root custom categories
		//
		for (Iterator iter = categories.iterator(); iter.hasNext();) {
			if (TngUtil.isRootCustomCategory((CustomCategory) iter.next())) {
				iter.remove();
			}
		}
		return categories;
	}

	/**
	 * Gets all tools that are available to the given MethodElement object.
	 * 
	 * @param e
	 * @return list of Tool objects
	 */
	public List getAvailableTools(MethodElement e) {
		return getAvailableCategories(e, Tool.class,
				ModelStructure.DEFAULT.toolPath);
	}

	/**
	 * Gets all discipline groupings that are available to the given
	 * MethodElement object.
	 * 
	 * @param e
	 * @return list of RoleSet objects
	 */
	public List getAvailableDisciplineGroupings(MethodElement e) {
		return getAvailableCategories(e, DisciplineGrouping.class,
				ModelStructure.DEFAULT.disciplineDefinitionPath);
	}

	/**
	 * Gets all role set groupings that are available to the given MethodElement
	 * object.
	 * 
	 * @param e
	 * @return list of RoleSet objects
	 */
	public List getAvailableRoleSetGroupings(MethodElement e) {
		return getAvailableCategories(e, RoleSetGrouping.class,
				ModelStructure.DEFAULT.roleSetPath);
	}

	/**
	 * add a task to a discipline
	 * 
	 * @param actionMgr
	 * @param discipline
	 * @param task
	 * @return Discipline
	 */
	public Discipline addToDiscipline(IActionManager actionMgr,
			Discipline discipline, Task task, List usedCategories) {
		return (Discipline) addToCategory(actionMgr, discipline, task,
				UmaPackage.eINSTANCE.getDiscipline_Tasks(),
				ModelStructure.DEFAULT.disciplineDefinitionPath, true, usedCategories);
	}

	/**
	 * add a workproduct to a domain
	 * 
	 * @param actionMgr
	 * @param domain
	 * @param wp
	 * @return Domain
	 */
	public Domain addToDomain(IActionManager actionMgr, Domain domain,
			WorkProduct wp, List usedCategories) {
		return (Domain) addToCategory(actionMgr, domain, wp,
				UmaPackage.eINSTANCE.getDomain_WorkProducts(),
				ModelStructure.DEFAULT.domainPath, true, usedCategories);
	}


	/**
	 * add a work product to a WorkProductType
	 * 
	 * @param actionMgr
	 * @param wpType
	 * @param wp
	 * @return WorkProductType
	 */
	public WorkProductType addToWorkProductType(IActionManager actionMgr,
			WorkProductType wpType, WorkProduct wp, List usedCategories) {
		return (WorkProductType) addToCategory(actionMgr, wpType, wp,
				UmaPackage.eINSTANCE.getWorkProductType_WorkProducts(),
				ModelStructure.DEFAULT.workProductTypePath, true, usedCategories);
	}



	/**
	 * add an element to a CustomCategory
	 * 
	 * @param actionMgr
	 * @param userDefinedCategory
	 * @param element
	 * @return CustomCategory
	 */
	public CustomCategory addToCustomCategory(IActionManager actionMgr,
			CustomCategory userDefinedCategory, MethodElement element, List usedCategories) {
		if (element instanceof DescribableElement) {
			return (CustomCategory) addToCategory(actionMgr,
					userDefinedCategory, element, UmaPackage.eINSTANCE
							.getCustomCategory_CategorizedElements(),
					ModelStructure.DEFAULT.customCategoryPath, true, usedCategories);
		}

		// if(element instanceof ContentElement) {
		// return (CustomCategory) addToCategory(actionMgr, userDefinedCategory,
		// element,
		// UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(),
		// ModelStructure.DEFAULT.customCategoryPath, true);
		// }
		// else if(element instanceof ContentCategory) {
		// return (CustomCategory) addToCategory(actionMgr, userDefinedCategory,
		// element, UmaPackage.eINSTANCE.getCustomCategory_SubCategories(),
		// ModelStructure.DEFAULT.customCategoryPath, true);
		// }
		// else if(element instanceof Process){
		// return (CustomCategory) addToCategory(actionMgr, userDefinedCategory,
		// element, UmaPackage.eINSTANCE.getCustomCategory_ProcessElements(),
		// ModelStructure.DEFAULT.customCategoryPath, true);
		// }

		return null;
	}


	/**
	 * add a toolmentor to a Tool
	 * 
	 * @param actionMgr
	 * @param tool
	 * @param toolMentor
	 * @return Tool
	 */
	public Tool addToTool(IActionManager actionMgr, Tool tool,
			ToolMentor toolMentor, List usedCategories) {
		return (Tool) addToCategory(actionMgr, tool, toolMentor,
				UmaPackage.eINSTANCE.getTool_ToolMentors(),
				ModelStructure.DEFAULT.toolPath, true, usedCategories);
	}


	/**
	 * add a role to a RoleSet
	 * @param actionMgr
	 * @param roleSet
	 * @param role
	 * @return RoleSet
	 */
	public RoleSet addToRoleSet(IActionManager actionMgr, RoleSet roleSet,
			Role role, List usedCategories) {
		return (RoleSet) addToCategory(actionMgr, roleSet, role,
				UmaPackage.eINSTANCE.getRoleSet_Roles(),
				ModelStructure.DEFAULT.roleSetPath, true, usedCategories);
	}

	/**
	 * Adds a method element to the category. This method might create a new
	 * contributor to the given category in the method element's plugin if the
	 * given category belongs to the base plugin of the method element's plugin.
	 * 
	 * @param actionMgr
	 * @param category
	 * @param element
	 * @param feature
	 * @param categoryPkgPath
	 * @param checkForExistingContributor
	 * @return the actual category that the given method element is added to
	 */
	public ContentCategory addToCategory(IActionManager actionMgr,
			ContentCategory category, MethodElement element,
			EStructuralFeature feature, String[] categoryPkgPath,
			boolean checkForExistingContributor, List usedCategories) {
		AddToCategoryCommand cmd = new AddToCategoryCommand(category, element,
				feature, categoryPkgPath, checkForExistingContributor, usedCategories);
		actionMgr.execute(cmd);
		return (ContentCategory) ((List) cmd.getAffectedObjects()).get(0);
	}

//	public void setResponsibleRole(IActionManager actionMgr, WorkProduct wp,
//			Role role) {
//		IResourceAwareCommand cmd = new SetOppositeFeatureCommand(wp,
//				AssociationHelper.WorkProduct_ResponsibleRole, role);
//		actionMgr.execute(cmd);
//	}

	/**
	 * Removes the method element from the category. This method might delete
	 * the category if it is a contributor and became empty after the removal of
	 * the method element.
	 * 
	 * @param category
	 * @param element
	 * @param feature
	 *            feature that contains the given method element
	 * @param contentPkgPath
	 * @return true if the method element is successfully removed, false
	 *         otherwise.
	 */
	public boolean removeFromCategory(IActionManager actionMgr,
			ContentCategory category, MethodElement element,
			EStructuralFeature feature, String[] contentPkgPath, List usedCategories) {
		IResourceAwareCommand cmd = new RemoveFromCategoryCommand(category,
				element, feature, contentPkgPath, usedCategories);
		actionMgr.execute(cmd);
		return !cmd.getResult().isEmpty();
	}

	/**
	 * Removes the method element from the category. This method might delete
	 * the category if it is a contributor and became empty after the removal of
	 * the method element.
	 * 
	 * @param category
	 * @param element
	 * @param feature
	 *            feature that contains the given method element
	 * @param elementFeatures
	 *            features to check for emptiness before deleting the category
	 * @param contentPkgPath
	 * @return true if the method element is successfully removed, false
	 *         otherwise.
	 */
	// public boolean removeFromCategory(ContentCategory category, MethodElement
	// element, EStructuralFeature feature, EStructuralFeature[]
	// elementFeatures, String[] contentPkgPath) {
	// boolean ret;
	// boolean empty = false;
	// MethodPlugin categoryPlugin = UmaUtil.getMethodPlugin(category);
	// MethodPlugin elementPlugin = UmaUtil.getMethodPlugin(element);
	// ContentCategory usedCategory;
	// if(categoryPlugin != elementPlugin) {
	// usedCategory =
	// TngUtil.findContributor(UmaUtil.findContentPackage(elementPlugin,
	// contentPkgPath), category);
	// if(usedCategory == null) return false;
	// }
	// else {
	// usedCategory = category;
	// }
	// if(feature.isMany()) {
	// Collection collection = (Collection) usedCategory.eGet(feature);
	// ret = collection.remove(element);
	// empty = true;
	// //TODO: need revisit to find better way to remove the empty contributors
	// without adding to much work on in the UI code
	// //
	// // for (int i = 0; i < elementFeatures.length; i++) {
	// // collection = (Collection) category.eGet(elementFeatures[i]);
	// // if(!collection.isEmpty()) {
	// // empty = false;
	// // break;
	// // }
	// // }
	// }
	// else {
	// usedCategory.eSet(feature, null);
	// ret = true;
	// }
	// // if(category.getVariabilityBasedOnElement() != null && empty) {
	// // EcoreUtil.remove(category);
	// // }
	// return ret;
	// }
	// public boolean removeFromDiscipline(Discipline discipline, Task task) {
	// EStructuralFeature feature = UmaPackage.eINSTANCE.getDiscipline_Tasks();
	// return removeFromCategory(discipline, task, feature, new
	// EStructuralFeature[] { feature },
	// ModelStructure.DEFAULT.disciplineDefinitionPath);
	// }
	public boolean removeFromDiscipline(IActionManager actionMgr,
			Discipline discipline, Task task, List usedCategories) {
		EStructuralFeature feature = UmaPackage.eINSTANCE.getDiscipline_Tasks();
		return removeFromCategory(actionMgr, discipline, task, feature,
				ModelStructure.DEFAULT.disciplineDefinitionPath, usedCategories);
	}

	// public boolean removeFromDomain(Domain domain, WorkProduct wp) {
	// EStructuralFeature feature =
	// UmaPackage.eINSTANCE.getDomain_WorkProducts();
	// return removeFromCategory(domain, wp, feature, new EStructuralFeature[] {
	// feature }, ModelStructure.DEFAULT.domainPath);
	// }

	public boolean removeFromDomain(IActionManager actionMgr, Domain domain,
			WorkProduct wp, List usedCategories) {
		EStructuralFeature feature = UmaPackage.eINSTANCE
				.getDomain_WorkProducts();
		return removeFromCategory(actionMgr, domain, wp, feature,
				ModelStructure.DEFAULT.domainPath, usedCategories);
	}

	// public boolean removeFromWorkProductType(WorkProductType wpType,
	// WorkProduct wp) {
	// EStructuralFeature feature =
	// UmaPackage.eINSTANCE.getWorkProductType_WorkProducts();
	// return removeFromCategory(wpType, wp, feature, new EStructuralFeature[] {
	// feature }, ModelStructure.DEFAULT.workProductTypePath);
	// }

	public boolean removeFromWorkProductType(IActionManager actionMgr,
			WorkProductType wpType, WorkProduct wp, List usedCategories) {
		EStructuralFeature feature = UmaPackage.eINSTANCE
				.getWorkProductType_WorkProducts();
		return removeFromCategory(actionMgr, wpType, wp, feature,
				ModelStructure.DEFAULT.workProductTypePath, usedCategories);
	}

	// public boolean removeFromRoleSet(RoleSet roleSet, Role role) {
	// EStructuralFeature feature = UmaPackage.eINSTANCE.getRoleSet_Roles();
	// return removeFromCategory(roleSet, role, feature, new
	// EStructuralFeature[] { feature }, ModelStructure.DEFAULT.roleSetPath);
	// }

	public boolean removeFromRoleSet(IActionManager actionMgr, RoleSet roleSet,
			Role role, List usedCategories) {
		EStructuralFeature feature = UmaPackage.eINSTANCE.getRoleSet_Roles();
		return removeFromCategory(actionMgr, roleSet, role, feature,
				ModelStructure.DEFAULT.roleSetPath, usedCategories);
	}

	// public boolean removeFromTool(Tool tool, ToolMentor toolMentor) {
	// EStructuralFeature feature = UmaPackage.eINSTANCE.getTool_ToolMentors();
	// return removeFromCategory(tool, toolMentor, feature, new
	// EStructuralFeature[] { feature }, ModelStructure.DEFAULT.toolPath);
	// }

	public boolean removeFromTool(IActionManager actionMgr, Tool tool,
			ToolMentor toolMentor, List usedCategories) {
		EStructuralFeature feature = UmaPackage.eINSTANCE.getTool_ToolMentors();
		return removeFromCategory(actionMgr, tool, toolMentor, feature,
				ModelStructure.DEFAULT.toolPath, usedCategories);
	}

	// public boolean removeFromCustomCategory(CustomCategory
	// userDefinedCategory, MethodElement e) {
	// EStructuralFeature contentElementFeature =
	// UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
	// EStructuralFeature contentCategoryFeature =
	// UmaPackage.eINSTANCE.getCustomCategory_SubCategories();
	// EStructuralFeature[] features = { contentElementFeature,
	// contentCategoryFeature };
	// if(e instanceof ContentElement) {
	// return removeFromCategory(userDefinedCategory, e, contentElementFeature,
	// features, ModelStructure.DEFAULT.customCategoryPath);
	// }
	// else if(e instanceof ContentCategory) {
	// return removeFromCategory(userDefinedCategory, e, contentCategoryFeature,
	// features, ModelStructure.DEFAULT.customCategoryPath);
	// }
	// return false;
	// }

	public boolean removeFromCustomCategory(IActionManager actionMgr,
			CustomCategory userDefinedCategory, MethodElement e, List usedCategories) {
		if (e instanceof DescribableElement) {
			EStructuralFeature feature = UmaPackage.eINSTANCE
					.getCustomCategory_CategorizedElements();
			return removeFromCategory(actionMgr, userDefinedCategory, e,
					feature, ModelStructure.DEFAULT.customCategoryPath, usedCategories);
		}
		return false;
	}

	/**
	 * Call this method before the editor closes, passing in all the categories
	 * that you have retrieved with getAvailableXXX() for the editor
	 * 
	 * @param consumer
	 * @param categories
	 */
	public void releaseCategories(MethodElement consumer, Collection categories) {
		MethodPlugin consumerPlugin = UmaUtil.getMethodPlugin(consumer);
		for (Iterator iter = categories.iterator(); iter.hasNext();) {
			ContentCategory category = (ContentCategory) iter.next();
			MethodPlugin categoryPlugin = UmaUtil.getMethodPlugin(category);
			if (category.getVariabilityBasedOnElement() != null
					&& categoryPlugin == consumerPlugin
					&& TngUtil.isEmpty(category)) {
				EcoreUtil.remove(category);
				if (category instanceof CustomCategory) {
					TngUtil.getRootCustomCategory(categoryPlugin)
							.getCategorizedElements().remove(category);
				}
			} else {
				String[] path = getCategoryPackagePath(category);
				// path can be null if the category is deleted
				//
				if (path != null) {
					ContentPackage categoryPkg = UmaUtil.findContentPackage(
							consumerPlugin, path);
					ContentCategory contributor = TngUtil.findContributor(
							categoryPkg, category);
					if (contributor != null && TngUtil.isEmpty(contributor)) {
						EcoreUtil.remove(contributor);
						if (contributor instanceof CustomCategory) {
							TngUtil.getRootCustomCategory(consumerPlugin)
									.getCategorizedElements().remove(contributor);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param category
	 * @return List
	 */
	public List getAvailableTasks(ContentCategory category) {
		return getAvailableContentElements(Task.class, category, false);
	}

	/**
	 * 
	 * @param category
	 * @return List
	 */
	public List getAvailableWorkProducts(ContentCategory category) {
		// // special handling to disallow assigment of work products to domain
		// with subdomains
		// //
		// if(category instanceof Domain &&
		// !((Domain)category).getSubdomains().isEmpty()) {
		// return Collections.EMPTY_LIST;
		// }

		return getAvailableContentElements(WorkProduct.class, category, false);
	}

	/**
	 * 
	 * @param category
	 * @return List
	 */
	public List getAvailableRoles(ContentCategory category) {
		return getAvailableContentElements(Role.class, category, false);
	}

	/**
	 * Gets all content elements that are available to the given content element
	 * to reference to
	 * 
	 * @param methodElementType
	 *            concrect class of the the given content element.
	 * @param element
	 * @param localOnly
	 *            if true, only the elements in the given element's plugin are
	 *            included in the result.
	 * @return
	 */
	public List getAvailableContentElements(Class methodElementType,
			ContentElement element, boolean localOnly) {
		List elementList = new ArrayList();
		List modelList;
		if (localOnly) {
			modelList = Collections.singletonList(UmaUtil
					.getMethodPlugin(element));
		} else {
			modelList = MethodElementUtil.getAllModels(element);
		}
		int size = modelList.size();
		for (int i = 0; i < size; i++) {
			MethodPlugin model = (MethodPlugin) modelList.get(i);
			ContentPackage coreContentPkg = UmaUtil.findContentPackage(model,
					ModelStructure.DEFAULT.coreContentPath);
			if (coreContentPkg == null)
				continue;
			TreeIterator iterator = coreContentPkg.eAllContents();

			while (iterator.hasNext()) {
				Object obj = (Object) iterator.next();
				if (methodElementType.isInstance(obj) && obj != element) {
					elementList.add(obj);
				}
			}
		}

		Collections.sort(elementList, nameComparator);

		return elementList;
	}

	/**
	 * 
	 * @param role
	 * @return List
	 */
	public List getAvailableTasks(Role role) {
		// While editing a role, you only can assign this role to a task in the
		// same plugin.
		// Othewise you will modify the base plugin.
		//
		return getAvailableContentElements(Task.class, role, true);
	}

	/**
	 * 
	 * @param wp
	 * @return List
	 */
	public List getAvailableTasks(WorkProduct wp) {
		// While editing a work product, you only can assign this work product
		// to a task in the same plugin.
		// Othewise you will modify the base plugin.
		//
		return getAvailableContentElements(Task.class, wp, true);
	}

	/**
	 * 
	 * @param wp
	 * @return List
	 */
	public List getAvailableRoles(WorkProduct wp) {
		// While editing a work product, you only can assign this work product
		// to a role in the same plugin.
		// Othewise you will modify the base plugin.
		//
		return getAvailableContentElements(Role.class, wp, true);
	}

	/**
	 * @param role
	 * @return List
	 */
	public List getAvailableWorkProducts(Role role) {
		return getAvailableContentElements(WorkProduct.class, role, false);
	}

	/**
	 * @param task
	 * @return List
	 */
	public List getAvailableWorkProducts(Task task) {
		return getAvailableContentElements(WorkProduct.class, task, false);
	}

	/**
	 * 
	 * @param task
	 * @return List
	 */
	public List getAvailableRoles(Task task) {
		return getAvailableContentElements(Role.class, task, false);
	}

	/**
	 * 
	 * @param task
	 * @return List
	 */
	public List getStepList(Task task) {
		if (task.getVariabilityType() == VariabilityType.EXTENDS
				|| task.getVariabilityType() == VariabilityType.CONTRIBUTES) {
			VariabilityElement base = TngUtil.getBase(task);
			final List types = new ArrayList();
			if (types.isEmpty()) {
				synchronized (types) {
					if (types.isEmpty()) {
						types.add(VariabilityType.CONTRIBUTES);
						types.add(VariabilityType.EXTENDS);
					}
				}
			}
			Iterator extended = new AbstractTreeIterator(base) {
				protected Iterator getChildren(Object object) {
					List list = AssociationHelper
							.getImmediateVarieties((VariabilityElement) object);
					if (list == null || list.isEmpty())
						Collections.EMPTY_LIST.iterator();
					int size = list.size();
					List outputList = new ArrayList();
					for (int i = 0; i < size; i++) {
						VariabilityElement e = (VariabilityElement) list.get(i);
						if (types.contains(e.getVariabilityType())) {
							outputList.add(e);
						}
					}
					return outputList.iterator();
				}
			};
			for (; extended.hasNext();) {
				Task t = (Task) extended.next();
				t.getPresentation().getSections();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param base
	 * @param variabilityTypes
	 * @return List
	 */
	public List getExtended(VariabilityElement base, Collection variabilityTypes) {
		List list = new ArrayList();
		getExtended(list, base, variabilityTypes);
		return list;
	}

	private static void getExtended(List outputList, VariabilityElement base,
			Collection variabilityTypes) {
		List list = AssociationHelper.getImmediateVarieties(base);
		if (list == null || list.isEmpty())
			return;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			VariabilityElement e = (VariabilityElement) list.get(i);
			if (variabilityTypes.contains(e.getVariabilityType())) {
				outputList.add(e);
			}
		}
		for (int i = 0; i < size; i++) {
			VariabilityElement e = (VariabilityElement) list.get(i);
			if (variabilityTypes.contains(e.getVariabilityType())) {
				getExtended(outputList, base, variabilityTypes);
			}
		}

	}

	private static String[] getCategoryPackagePath(ContentCategory category) {
		EObject parent = null;
		for (parent = category.eContainer(); parent != null
				&& !(parent instanceof ContentPackage); parent = parent
				.eContainer())
			;
		if (parent == null)
			return null;
		ContentPackage categoryPkg = (ContentPackage) parent;
		return Misc.getPathRelativeToPlugin(categoryPkg);
	}

}
