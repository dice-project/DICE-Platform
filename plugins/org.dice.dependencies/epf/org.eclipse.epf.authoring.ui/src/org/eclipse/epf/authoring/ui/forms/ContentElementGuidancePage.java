/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.GuidanceFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.ChangeUdtCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * Class for Guidance tab page in the content element editor.
 * Class ContentElementGuidancePage replaces CategoryGuidancePage and handles
 * all contentelement's guidance tab feature.  
 * 
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ContentElementGuidancePage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "categoryGuidancesPage"; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public ContentElementGuidancePage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.GUIDANCE_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		setAllowChange1(true);
		setAllowChange2(true);
		setUseCategory2(false);
		setUseCategory3(false);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				List list = MethodElementUtil
						.getSelectedGuidances(contentElement);
				List local = new ArrayList();
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object obj = it.next();
					if (!(obj instanceof Practice
							|| (obj instanceof ToolMentor && contentElement instanceof Tool) || obj
							.equals(contentElement
									.getVariabilityBasedOnElement()))) {
						local.add(obj);
					} else if (obj instanceof Practice) {
						if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj)) {
							local.add(obj);
						}
					}
				}
				return local.toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		// Update the model.
		IActionManager actionMgr = editor.getActionManager();
		List<Practice> utdItems = new ArrayList<Practice>();
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// Consolidating the separate form page code
				// to accomodate better filtering.
				if (contentElement instanceof Task) {
//					if (item instanceof Estimate) {
//						editor.getActionManager().doAction(IActionManager.SET,
//								contentElement,
//								UmaPackage.eINSTANCE.getTask_Estimate(),
//								(Estimate) item, -1);
//					} else 
					if (item instanceof ToolMentor) {
						actionMgr.doAction(IActionManager.ADD,
								contentElement,
								UmaPackage.eINSTANCE.getTask_ToolMentors(),
								(ToolMentor) item, -1);
						continue;
					} else if (item instanceof EstimationConsiderations) {
						actionMgr.doAction(IActionManager.ADD, contentElement,
								UmaPackage.eINSTANCE.getTask_EstimationConsiderations(),
								item, -1);
						continue;
					}
				}
				// In case of WorkProduct, WorkProduct is associated to ToolMentor,
				// Estimate, Report and Template guidance
				if (contentElement instanceof WorkProduct) {
					if (item instanceof Template) {
						actionMgr
								.doAction(IActionManager.ADD, contentElement,
										UmaPackage.eINSTANCE
												.getWorkProduct_Templates(),
										item, -1);
						continue;
					} else if (item instanceof ToolMentor) {
						actionMgr
								.doAction(IActionManager.ADD, contentElement,
										UmaPackage.eINSTANCE
												.getWorkProduct_ToolMentors(),
										item, -1);
						continue;
					} else if (item instanceof Report) {
						actionMgr.doAction(IActionManager.ADD, contentElement,
								UmaPackage.eINSTANCE.getWorkProduct_Reports(),
								item, -1);
						continue;
					} 
//					else if (item instanceof Estimate) {
//						actionMgr.doAction(IActionManager.SET, contentElement,
//								UmaPackage.eINSTANCE.getWorkProduct_Estimate(),
//								item, -1);
//					}
					else if (item instanceof EstimationConsiderations) {
						actionMgr.doAction(IActionManager.ADD, contentElement,
								UmaPackage.eINSTANCE.getWorkProduct_EstimationConsiderations(),
								item, -1);
						continue;
					}
				}

				// Guidance for content element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
					continue;
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
					continue;
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
					continue;
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
					continue;
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
					continue;
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
					continue;
				} else if (item instanceof Practice) {
					if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) item)) {
						utdItems.add((Practice) item);
					}
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to add guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"to " //$NON-NLS-1$
											+ contentElement.getType()
													.getName()
											+ ":" + contentElement.getName()); //$NON-NLS-1$ 
				}
			}
			setDirty(true);
		}
		if (! utdItems.isEmpty()) {
			actionMgr.execute(new ChangeUdtCommand(contentElement, utdItems, false));
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		IActionManager actionMgr = editor.getActionManager();
		List<Practice> utdItems = new ArrayList<Practice>();
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// In case of Task, Task is associated to ToolMentor and
				// Estimate guidance
				if (contentElement instanceof Task) {
					if (item instanceof ToolMentor) {
						editor.getActionManager().doAction(
								IActionManager.REMOVE, contentElement,
								UmaPackage.eINSTANCE.getTask_ToolMentors(),
								item, -1);
						continue;
					} 
//					else if (item instanceof Estimate) {
//						editor.getActionManager().doAction(IActionManager.SET,
//								contentElement,
//								UmaPackage.eINSTANCE.getTask_Estimate(), null,
//								-1);
//					}
					else if (item instanceof EstimationConsiderations) {
						editor.getActionManager().doAction(IActionManager.REMOVE,
								contentElement,
								UmaPackage.eINSTANCE.getTask_EstimationConsiderations(), item,
								-1);
						continue;
					}
				}
				// In case of WorkProduct, WorkProduct is associated to ToolMentor,
				// Estimate, Report and Template guidance
				if (contentElement instanceof WorkProduct) {
					if (item instanceof Report) {
						actionMgr.doAction(IActionManager.REMOVE,
								contentElement, UmaPackage.eINSTANCE
										.getWorkProduct_Reports(), item, -1);
						continue;
					} else if (item instanceof Template) {
						actionMgr.doAction(IActionManager.REMOVE,
								contentElement, UmaPackage.eINSTANCE
										.getWorkProduct_Templates(), item, -1);
						continue;
					} else if (item instanceof ToolMentor) {
						actionMgr
								.doAction(IActionManager.REMOVE,
										contentElement, UmaPackage.eINSTANCE
												.getWorkProduct_ToolMentors(),
										item, -1);
						continue;
					} 
//					else if (item instanceof Estimate) {
//						actionMgr.doAction(IActionManager.SET, contentElement,
//								UmaPackage.eINSTANCE.getWorkProduct_Estimate(),
//								null, -1);
//					}
					else if (item instanceof EstimationConsiderations) {
						editor.getActionManager().doAction(IActionManager.REMOVE,
								contentElement,
								UmaPackage.eINSTANCE.getWorkProduct_EstimationConsiderations(), item,
								-1);
						continue;
					}
				}
				// Guidance for the method element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									item, -1);
					continue;
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
					continue;
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
					continue;
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
					continue;
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
					continue;
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
					continue;
				} else if (item instanceof Practice) {
					if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) item)) {
						utdItems.add((Practice) item);
					}
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to remove guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"from " //$NON-NLS-1$
											+ contentElement.getType()
													.getName()
											+ ":" + contentElement.getName()); //$NON-NLS-1$ 
				}
			}
			setDirty(true);
		}
		if (! utdItems.isEmpty()) {
			actionMgr.execute(new ChangeUdtCommand(contentElement, utdItems, true));
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return contentElement;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.GUIDANCE;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new GuidanceFilter();
	}

	/**
	 * 
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getFilterTypes()
	 *      Types are returned in order to display in the filter dialog for
	 *      search based on type.
	 */
	protected String[] getFilterTypes() {
		
		String[] str;
		if (contentElement instanceof Task) {
			str = new String[11];
		} else if (contentElement instanceof WorkProduct) {
			str = new String[13];
		} else
			str = new String[9];
		int i = 0;
		str[i++] = FilterConstants.GUIDANCE;
		str[i++] = FilterConstants.space + FilterConstants.CHECKLISTS;
		str[i++] = FilterConstants.space + FilterConstants.CONCEPTS;
		str[i++] = FilterConstants.space + FilterConstants.EXAMPLES;
		str[i++] = FilterConstants.space + FilterConstants.GUIDELINES;
		str[i++] = FilterConstants.space + FilterConstants.REUSABLE_ASSETS;
		str[i++] = FilterConstants.space + FilterConstants.SUPPORTING_MATERIALS;
		if (contentElement instanceof Task) {
			str[i++] = FilterConstants.space + FilterConstants.TOOL_MENTORS;
			str[i++] = FilterConstants.space + FilterConstants.ESTIMATE_CONSIDERATIONS;
			// TODO Estimate contentelement
		} else if (contentElement instanceof WorkProduct) {
			str[i++] = FilterConstants.space + FilterConstants.REPORTS;
			str[i++] = FilterConstants.space + FilterConstants.TEMPLATES;
			str[i++] = FilterConstants.space + FilterConstants.TOOL_MENTORS;
			str[i++] = FilterConstants.space + FilterConstants.ESTIMATE_CONSIDERATIONS;
			// TODO Estimate contentelement
		}
		str[i++] = FilterConstants.CONTENT_PACKAGES;
		str[i++] = FilterConstants.METHO_PLUGINS;
		return str;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.categoryGuidancesPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		
		if(contentElement instanceof RoleSet){
			return AuthoringUIResources.rolesets_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Discipline){
			return AuthoringUIResources.disciplines_guidancepage_sectiondescription;
		}
		if(contentElement instanceof WorkProductType){
			return AuthoringUIResources.workproducttypes_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Domain){
			return AuthoringUIResources.domains_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Tool){
			return AuthoringUIResources.tools_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Role){
			return AuthoringUIResources.roleGuidancePage_sectionDescription;
		}
		if(contentElement instanceof Task){
			return AuthoringUIResources.taskGuidancePage_sectionDescription;
		}
		if(contentElement instanceof WorkProduct){
			return AuthoringUIResources.workProductGuidancePage_sectionDescription;
		}
		if(contentElement instanceof Tool){
			return AuthoringUIResources.tools_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Whitepaper){
			return AuthoringUIResources.whitepaper_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Concept){
			return AuthoringUIResources.concept_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Checklist){
			return AuthoringUIResources.checklist_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Example){
			return AuthoringUIResources.example_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Guideline){
			return AuthoringUIResources.guideline_guidancepage_sectiondescription;
		}
		if(contentElement instanceof SupportingMaterial){
			return AuthoringUIResources.supportingmaterial_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Roadmap){
			return AuthoringUIResources.roadmap_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Practice){
			return AuthoringUIResources.practice_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Report){
			return AuthoringUIResources.report_guidancepage_sectiondescription;
		}
		if(contentElement instanceof ReusableAsset){
			return AuthoringUIResources.reusableasset_guidancepage_sectiondescription;
		}
		if(contentElement instanceof Template){
			return AuthoringUIResources.template_guidancepage_sectiondescription;
		}
		if(contentElement instanceof TermDefinition){
			return AuthoringUIResources.termdefinition_guidancepage_sectiondescription;
		}
		if(contentElement instanceof EstimationConsiderations){
			return AuthoringUIResources.estimationconsideration_guidancepage_sectiondescription;
		}
		if(contentElement instanceof ToolMentor){
			return AuthoringUIResources.toolmentor_guidancepage_sectiondescription;
		}
		if(contentElement instanceof CustomCategory){
			return AuthoringUIResources.customcategory_guidancepage_sectiondescription;
		}
		
		if(contentElement != null)
			return AuthoringUIResources.bind(AuthoringUIResources.categoryGuidancesPage_sectionDescription, contentElement.getType().getName().toLowerCase());
		
		return null;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.categoryGuidancesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.categoryGuidancesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.categoryGuidancesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.categoryGuidancesPage_selectedLabel;
	}


}
