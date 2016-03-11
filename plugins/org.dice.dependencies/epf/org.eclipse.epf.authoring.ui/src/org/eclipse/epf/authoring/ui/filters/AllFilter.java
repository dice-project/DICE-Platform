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
package org.eclipse.epf.authoring.ui.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.itemsfilter.CategorizedProcessesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.ContentCategoriesGroupItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.IAllFilter;
import org.eclipse.epf.library.edit.itemsfilter.ProcessesItemProvider;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * Filter for all elements eg: used in {@link CustomCategoryAssignPage} 
 * 
 * @author Lokanath Jagga
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class AllFilter extends AbstractBaseFilter implements IAllFilter {
	
	private List<String> selectedTypeStrings; 
	
	public List<String> getSelectedTypeStrings() {
		return selectedTypeStrings;
	}

	public void addToSelectedTypeStrings(String typeString) {
		if (selectedTypeStrings == null) {
			selectedTypeStrings = new ArrayList<String>();
		}
		selectedTypeStrings.add(typeString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.IAllFilter#getObject()
	 */
	public Object getObject() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IFilter#accept(java.lang.Object)
	 */
	public boolean accept(Object obj) {
		if(obj == null)
			return false;
		// if can't be accdepted by super, return.
		if (!super.accept(obj)) {
			return false;
		}

		String filterTypeString = helper.getFilterTypeStr();
		Object contentElement = helper.getContentElement();

		if (helper.getAlreadySelectedList() != null) {
			if (obj instanceof ProcessComponent) {
				if (helper.getAlreadySelectedList().contains(
						((ProcessComponent) obj).getProcess()))
					return false;
			} else {
				if (helper.getAlreadySelectedList().contains(obj))
					return false;
			}
		}

		if (contentElement != null
				&& !(obj instanceof ProcessComponent || obj instanceof Process)) {
			if(obj == null){
				System.out.println("null"); //$NON-NLS-1$
			}
			if (obj.equals(helper.getContentElement()))
				return false;
		}

		if (filterTypeString == null
				|| filterTypeString.equals(FilterConstants.ALL_ELEMENTS)
				|| filterTypeString.equals(FilterConstants.METHO_PLUGINS)
				|| filterTypeString
						.equals(FilterConstants.ONLY_CONTENT_ELEMENTS)) {

			if (obj instanceof CustomCategory) {
				if (TngUtil.isRootCustomCategory((CustomCategory) obj)) {
					if (((CustomCategory) obj).getCategorizedElements()
							.isEmpty())
						return false;
				}
			}
			if (!helper.matchPattern(obj))
				return false;

			// if(obj instanceof TermDefinition) return false;

			if (obj instanceof MethodPlugin) {
				if (obj instanceof MethodPlugin) {
					if (contentElement != null) {
						if (MethodElementUtil.getAllModels(contentElement)
								.contains(obj))
							return true;
						else
							return false;
					} else {
						return true;
					}
				}
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				Collection list = ((ContentCategoriesGroupItemProvider) obj)
						.getChildren(obj);
				if (list.isEmpty())
					return false;
				else
					return true;
			}
			if (obj instanceof ProcessesItemProvider) {
				Collection list = ((ProcessesItemProvider) obj)
						.getChildren(obj);
				for (Iterator ior = list.iterator(); ior.hasNext();) {
					Object object = ior.next();
					if (((CategorizedProcessesItemProvider) object)
							.getChildren(object).isEmpty())
						ior.remove();
				}
				if (list.isEmpty())
					return false;
				else
					return true;
			}

			if (childAccept(obj))
				return true;
			//		
		} else if (filterTypeString.equals(FilterConstants.DISCIPLINES)) {

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				return acceptCategoriesGroup(obj, LibraryEditPlugin.INSTANCE
						.getString("_UI_Disciplines_group")); //$NON-NLS-1$
			}
			if (obj instanceof DisciplineGrouping) {
				return !((DisciplineGrouping) obj).getDisciplines().isEmpty();
			}
			if (obj instanceof Discipline) {
				return true;// AssociationHelper.getDisciplineGroups((Discipline)obj).isEmpty();
			}
		} else if (filterTypeString.equals(FilterConstants.ROLESETS)) {

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				return acceptCategoriesGroup(obj, LibraryEditPlugin.INSTANCE
						.getString("_UI_Role_Sets_group")); //$NON-NLS-1$
			}
			if (obj instanceof RoleSetGrouping) {
				return !((RoleSetGrouping) obj).getRoleSets().isEmpty();
			}
			if (obj instanceof RoleSet) {
				return true;// AssociationHelper.getRoleSetGroups((RoleSet)obj).isEmpty();
			}
		} else if (filterTypeString.equals(FilterConstants.WORKPRODUCTTYPES)) {

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				return acceptCategoriesGroup(obj, LibraryEditPlugin.INSTANCE
						.getString("_UI_WorkProductTypes_group")); //$NON-NLS-1$
			}
			if (obj instanceof WorkProductType)
				return true;
		} else if (filterTypeString.equals(FilterConstants.DOMAINS)) {

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				return acceptCategoriesGroup(obj, LibraryEditPlugin.INSTANCE
						.getString("_UI_Domains_group")); //$NON-NLS-1$
			}
			if (obj instanceof Domain)
				return true;
		} else if (filterTypeString.equals(FilterConstants.TOOLS)) {

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				return acceptCategoriesGroup(obj, LibraryEditPlugin.INSTANCE
						.getString("_UI_Tools_group")); //$NON-NLS-1$
			}
			if (obj instanceof Tool)
				return true;
		} else if (filterTypeString.equals(FilterConstants.ROLES)) { 

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Role)
				return true;
		} else if (filterTypeString.equals(FilterConstants.TASKS)) { 

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Task)
				return true;
		} else if (filterTypeString.equals(FilterConstants.WORKPRODUCTS)) { 

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof WorkProduct)
				return true;
		} else if (filterTypeString.equals(FilterConstants.UDTs)) { 

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Practice)
				return PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj);
			
		} else if (filterTypeString.equals(FilterConstants.GUIDANCE)) { 

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Guidance)
				return true;
		} else if (filterTypeString.equals(FilterConstants.CHECKLISTS)) { //172956
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Checklist)
				return true;
		}else if (filterTypeString.equals(FilterConstants.CONCEPTS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Concept)
				return true;
		}
		else if (filterTypeString.equals(FilterConstants.EXAMPLES)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Example)
				return true;
		}else if (filterTypeString.equals(FilterConstants.GUIDELINES)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Guideline)
				return true;
		}else if (filterTypeString.equals(FilterConstants.CONCEPTS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Concept)
				return true;
		}else if (filterTypeString.equals(FilterConstants.ESTIMATE_CONSIDERATIONS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof EstimationConsiderations)
				return true;
		}else if (filterTypeString.equals(FilterConstants.PRACTICES)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Practice)
				return true;
		}else if (filterTypeString.equals(FilterConstants.REPORTS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Report)
				return true;
		} else if (filterTypeString.equals(FilterConstants.REUSABLE_ASSETS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof ReusableAsset)
				return true;
		} else if (filterTypeString.equals(FilterConstants.ROADMAP)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Roadmap)
				return true;
		} else if (filterTypeString.equals(FilterConstants.SUPPORTING_MATERIALS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof SupportingMaterial)
				return true;
		} else if (filterTypeString.equals(FilterConstants.TEMPLATES)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Template)
				return true;
		} else if (filterTypeString.equals(FilterConstants.TERM_DEFINITIONS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof TermDefinition)
				return true;
		} else if (filterTypeString.equals(FilterConstants.TOOL_MENTORS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof ToolMentor)
				return true;
		} else if (filterTypeString.equals(FilterConstants.WHITE_PAPERS)) {
			if (!helper.matchPatternBasedOnType(obj))
				return false;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof Whitepaper)
				return true;
		} else if (filterTypeString.equals(FilterConstants.CONTENT_PACKAGES)) {

			if (!helper.matchPattern(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof CustomCategory)
				return false;
			if (obj instanceof Role || obj instanceof Task
					|| obj instanceof WorkProduct || obj instanceof Guidance)
				return true;
			// if(obj instanceof Guidance) return true;
		} else if (filterTypeString.equals(FilterConstants.CUSTOM_CATEGORIES)) {

			if (!helper.matchPattern(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof CustomCategory)
				return true;
		} else if (filterTypeString.equals(FilterConstants.PROCESSES)) {

			if (!helper.matchPatternBasedOnType(obj))
				return false;
			// if(obj instanceof Discipline) return true;
			if (obj instanceof MethodPlugin) {
				return acceptMethodPlugin(obj, contentElement);
			}
			if (obj instanceof CustomCategory)
				return false;
			if (obj instanceof ProcessesItemProvider) {
				Collection list = ((ProcessesItemProvider) obj)
						.getChildren(obj);
				for (Iterator ior = list.iterator(); ior.hasNext();) {
					Object object = ior.next();
					if (((CategorizedProcessesItemProvider) object)
							.getChildren(object).isEmpty())
						ior.remove();
				}
				if (list.isEmpty())
					return false;
				else
					return true;
			}
			if (obj instanceof ProcessPackage)
				return true;
			if (obj instanceof BreakdownElement)
				//return true;
				return childAccept(obj);
		}
		return false;
	}

	protected boolean childAccept(Object obj) {
		if (obj instanceof MethodConfiguration)
			return false;
		if(obj instanceof Milestone) return false;
		return true;
	}

	// public FilterHelper getHelper() {
	// return helper;
	// }
	//
	//	
	// public void setHelper(FilterHelper helper) {
	// this.helper = helper;
	// }
	protected List getAncestors(CustomCategory methodObject) {
		List ancestorList = new ArrayList();

		List objList = new ArrayList();
		objList.add(methodObject);

		getAncestors(ancestorList, objList);

		return ancestorList;
	}

	protected List getAncestors(List ancestorList, List methodObjectList) {
		if (methodObjectList.isEmpty())
			return ancestorList;

		List allParentCustCats = new ArrayList();

		for (Iterator iter = methodObjectList.iterator(); iter.hasNext();) {
			CustomCategory element = (CustomCategory) iter.next();
			List parentList = AssociationHelper.getCustomCategories(element);
			allParentCustCats.addAll(parentList);

		}

		ancestorList.addAll(methodObjectList);
		List nextCheckList = new ArrayList();
		for (Iterator iter = allParentCustCats.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!ancestorList.contains(element))
				nextCheckList.add(element);
		}

		return getAncestors(ancestorList, nextCheckList);
	}

	public boolean acceptMethodPlugin(Object obj, Object contentElement) {
		if (contentElement != null) {
			if (MethodElementUtil.getAllModels(contentElement).contains(obj))
				return true;
			else
				return false;
		} else {
			return true;
		}
	}

	public boolean acceptCategoriesGroup(Object obj, String type) {
		if (obj instanceof ContentCategoriesGroupItemProvider) {
			Collection list = ((ContentCategoriesGroupItemProvider) obj)
					.getChildren(obj);
			if (list.isEmpty())
				return false;
			if (((ContentCategoriesGroupItemProvider) obj).getText(obj).equals(
					type))
				return true;
		}
		return false;
	}

}
