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
package org.eclipse.epf.authoring.ui.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.actions.LibraryViewDeleteAction;
import org.eclipse.epf.authoring.ui.filters.AllFilter;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MethodElementSetPropertyCommand;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Assign page in the Custom Category editor.
 * 
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CustomCategoryAssignPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "customCategoryAssignPage"; //$NON-NLS-1$

	private CustomCategory category;

	//private ContentElementOrderList allSteps;
	private ContentElementOrderList allSteps;

	private Button includeCheckBox;

	private ComboViewer typeComboViewer;

	/**
	 * Creates a new instance.
	 */
	public CustomCategoryAssignPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.ASSIGN_PAGE_TITLE);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		
		ctrl_add.setText(AuthoringUIResources.assignAction_text);
		ctrl_remove.setText(AuthoringUIResources.unassignAction_text);
		
		createIncludeComposite();
	}

	private void createIncludeComposite() {
		createCompositeForButtons(toolkit, aComposite);
		Composite composite = createComposite(toolkit, aComposite, GridData.FILL_BOTH, 1, 1, 2);
		includeCheckBox = toolkit.createButton(composite, AuthoringUIResources.CustomCategoryAssignPage_includeElementsOfType, SWT.CHECK);
		includeCheckBox.setLayoutData(new GridData(GridData.BEGINNING));
		Combo typeCombo = new Combo(composite, SWT.SINGLE | SWT.FLAT | SWT.READ_ONLY);
		typeCombo.setLayoutData(new GridData(GridData.BEGINNING));
		typeComboViewer = new ComboViewer(typeCombo);
		typeComboViewer.setContentProvider(new ArrayContentProvider());
		typeComboViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if(element instanceof EClass) {
					return TngUtil.getTypeText((EClass) element);
				} else if (element instanceof UserDefinedTypeMeta) {
					return ((UserDefinedTypeMeta)element).getRteNameMap().get(UserDefinedTypeMeta._typeName);
				}
				
				return super.getText(element);
			}
		});
		List<Object> allTypeValues = new ArrayList<Object>();
		allTypeValues.addAll(LibraryUtil.getIncludedElementTypes());
		allTypeValues.addAll(LibraryUtil.getAllUDTMetas());
		typeComboViewer.setInput(allTypeValues.toArray());
		
		// populate data
		//
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(category, MethodElementPropertyHelper.CUSTOM_CATEGORY__INCLUDED_ELEMENTS);
		if(prop != null) {
			includeCheckBox.setSelection(true);
			Object item = null;
			EClassifier cls = UmaPackage.eINSTANCE.getEClassifier(prop.getValue());
			if ((cls != null) && (cls instanceof EClass)) {
				item = cls;
			} else if (cls == null) {
				item = LibraryUtil.getUDTMetaFromId(prop.getValue());
			}
			if (item != null) {
				typeComboViewer.setSelection(new StructuredSelection(item));
			}
		} else {
			typeCombo.setEnabled(false);
		}
		
		// add listeners
		//
		includeCheckBox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				typeComboViewer.getCombo().setEnabled(includeCheckBox.getSelection());
				updateInclude();
			}
		});
		typeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if(includeCheckBox.getSelection()) {
					updateInclude();
				}
			}
			
		});
	}

	private void updateInclude() {
		if(includeCheckBox.getSelection()) {
			String type = null;
			IStructuredSelection selection = (IStructuredSelection) typeComboViewer.getSelection();
			if(selection.isEmpty()) {
				type = StrUtil.EMPTY_STRING;
			} else {
				if (selection.getFirstElement() instanceof EClass) {
					type = ((EClass) selection.getFirstElement()).getName();
				} else if (selection.getFirstElement() instanceof UserDefinedTypeMeta) {
					type = ((UserDefinedTypeMeta)selection.getFirstElement()).getRteNameMap().get(UserDefinedTypeMeta._typeId);
				}
			}
			MethodElementSetPropertyCommand cmd = new MethodElementSetPropertyCommand(category,
					MethodElementPropertyHelper.CUSTOM_CATEGORY__INCLUDED_ELEMENTS, type);
			getActionManager().execute(cmd);
		} else {
			// remove property "include"
			//
			MethodElementProperty prop = MethodElementPropertyHelper.getProperty(category, MethodElementPropertyHelper.CUSTOM_CATEGORY__INCLUDED_ELEMENTS);
			if (prop != null) {
				getActionManager().doAction(IActionManager.REMOVE, category, UmaPackage.eINSTANCE.getMethodElement_MethodElementProperty(), prop, -1);
			}
		}
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		category = (CustomCategory) contentElement;
		setUseCategory2(false);
		setUseCategory3(false);
		setAllowChange1(true);
		setIsUpAndDownButtonsRequired1(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (allSteps == null) {
//					allSteps = new ContentElementOrderList(
//							contentElement,
//							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
//							getOrderFeature());
					allSteps = getProviderExtender().newContentElementOrderList(contentElement, 
							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
							getOrderFeature(), 1);
				}
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				List returnList = CategorySortHelper.sortCategoryElements(contentElement, allSteps.toArray());
				return returnList.toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		addItemsToModel1(addItems, category, usedCategories,
				getActionManager(), getAncestors(category));
		if (!addItems.isEmpty()) {
			setDirty(true);
		}
	}
		
	public static void addItemsToModel1(ArrayList addItems, 
			CustomCategory category,
			ArrayList usedCategories,
			IActionManager actionManager,
			List<Object> ancestors
			) {
		boolean ok = DependencyChecker.checkCircularForMovingVariabilityElement(category, addItems);
		if(! ok) {
			String title = AuthoringUIResources.circular_dependency_error_title;
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(title, LibraryEditResources.circular_dependency_error_msg);						
			return;
		}
		
		//List<Object> ancestors = getAncestors(category);
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				MethodElement customCategory = (MethodElement) it.next();
				if (customCategory instanceof CustomCategory
						&& TngUtil
								.isRootCustomCategory((CustomCategory) customCategory)) {
				} else if (ancestors.contains(customCategory)) {
				} else {
					if (customCategory instanceof ProcessComponent) {
						MethodElement object = ((ProcessComponent) customCategory)
								.getProcess();
						LibraryManager.getInstance().addToCustomCategory(
								actionManager, category, object, usedCategories);
					} else {
						LibraryManager.getInstance().addToCustomCategory(
								actionManager, category, customCategory, usedCategories);
					}
				}
			}
			//setDirty(true);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		removeItemsFromModel1(rmItems, category, usedCategories,
				getActionManager(), getAncestors(category));
		if (!rmItems.isEmpty()) {
			setDirty(true);
		}
	}
	
	public static void removeItemsFromModel1(ArrayList rmItems,
		CustomCategory category,
		ArrayList usedCategories,
		IActionManager actionManager,
		List<Object> ancestors
		) {
		if (!rmItems.isEmpty()) {
			ArrayList<MethodElement> customCategoriesToDelete = new ArrayList<MethodElement>();
			MethodPlugin currentPlugin = UmaUtil.getMethodPlugin(category);
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				MethodElement e = (MethodElement) it.next();
				if (e instanceof CustomCategory) {
//					Adapter adapter = TngAdapterFactory.INSTANCE
//							.getNavigatorView_ComposedAdapterFactory().adapt(
//									category, ITreeItemContentProvider.class);
//					if (adapter != null
//							&& adapter instanceof ItemProviderAdapter) {
//						for (Iterator iter = ((ItemProviderAdapter) adapter)
//								.getChildren(category).iterator(); iter
//								.hasNext();) {
//							Object o = iter.next();
//							if (TngUtil.unwrap(o) == e) {
//								customCategoriesToDelete.add(o);
//							}
//						}
//					}
					
					// will delete e if it is in the current plugin and
					// the last super custom category of e in the current plugin
					//
					if(TngUtil.isInPluginWithLessThanOneSuperCustomCategory((CustomCategory) e, currentPlugin)) {
//						customCategoriesToDelete.add(e);
//						continue;
						
						//Now, don't delete it, but add it to the root instead
						CustomCategory rootCC = TngUtil.getRootCustomCategory(currentPlugin);
						if (rootCC == category) {
							return;
						}
						LibraryManager.getInstance().addToCustomCategory(
								actionManager, rootCC, e, new ArrayList());
					}
				}				
				LibraryManager.getInstance().removeFromCustomCategory(
						actionManager, category, e, usedCategories);
			}

			if (customCategoriesToDelete.size() > 0) {
				int i = 0;
				StringBuffer elementStr = new StringBuffer();
				for (Iterator<MethodElement> it = customCategoriesToDelete.iterator(); it.hasNext();) {
					Object obj = TngUtil.unwrap(it.next());
					if (obj instanceof MethodElement) {
						if (i > 0) {
							elementStr.append(", "); //$NON-NLS-1$
						}
						elementStr.append(((MethodElement) obj).getName());

						i++;
					}
				}
				
				if(!AuthoringUIPlugin.getDefault().getMsgDialog().displayConfirmation(AuthoringUIResources.remove_text,
						NLS.bind(AuthoringUIResources.promptDeleteCustomCategoryBeforeRemoveLastRefToIt, elementStr))) {
					return;
				}
				LibraryViewDeleteAction deleteAction = new LibraryViewDeleteAction() {
					public void run() {
						String title = AuthoringUIResources.actions_LibraryActionBarContributor_deleteErrorTitle; 
						confirm = false;
						try {
							super.run();
						} catch (MessageException e) {
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayError(title, e.getMessage());
						} catch (Exception e) {
							String details = TngUtil.toStackTraceString(e);
							String message = AuthoringUIResources.actions_LibraryActionBarContributor_deleteErrorMessage; 
							String reason = AuthoringUIResources.actions_LibraryActionBarContributor_deleteErrorReason; 
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayError(title, message, reason,
											details, e);
						}
					}

					protected void deleteFailed() {
						// library need to be reloaded
						String libDir = LibraryService.getInstance()
								.getCurrentMethodLibraryLocation();
						LibraryUIManager.getInstance().openLibrary(libDir);
					}
					
				};
				deleteAction.setEditingDomain(LibraryView.getView()
						.getEditingDomain());
				deleteAction.selectionChanged(new StructuredSelection(
						customCategoriesToDelete));
				deleteAction.run();
			}
			//setDirty(true);
		}
	}

	/**
	 * Returns the ancestors for the given Custom Category.
	 */
	public static List<Object> getAncestors(CustomCategory methodObject) {
		List<Object> ancestorList = new ArrayList<Object>();
		List<Object> objList = new ArrayList<Object>();
		objList.add(methodObject);
		getAncestors(ancestorList, objList);
		return ancestorList;
	}

	private static List<Object> getAncestors(List<Object> ancestorList, List<Object> methodObjectList) {
		if (methodObjectList.isEmpty())
			return ancestorList;

		List allParentCustCats = new ArrayList();

		for (Iterator<Object> iter = methodObjectList.iterator(); iter.hasNext();) {
			CustomCategory element = (CustomCategory) iter.next();
			List parentList = AssociationHelper.getCustomCategories(element);
			allParentCustCats.addAll(parentList);
		}

		ancestorList.addAll(methodObjectList);
		List<Object> nextCheckList = new ArrayList<Object>();
		for (Iterator iter = allParentCustCats.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!ancestorList.contains(element)) {
				nextCheckList.add(element);
			}
		}

		return getAncestors(ancestorList, nextCheckList);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return category;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.ALL_ELEMENTS;
	}

	private class CustomCategoryAssignFilter extends AllFilter {
		protected boolean childAccept(Object obj) {
			if (obj instanceof ProcessPackage)
				return true;
			if (obj instanceof Process)
				return true;
			if(obj instanceof Milestone){
				return true;
			}
			if(obj instanceof Activity){
				return true;
			}
			if (obj instanceof CustomCategory) {
				if (TngUtil.isRootCustomCategory((CustomCategory) obj)) {
					return !(((CustomCategory) obj).getCategorizedElements()
							.isEmpty());
				}
			}
			if (obj instanceof TermDefinition)
				return false;
			if (obj instanceof ContentElement) {
				// if (obj instanceof Discipline){
				// return
				// AssociationHelper.getDisciplineGroups((Discipline)obj).isEmpty();
				// }
				// if (obj instanceof RoleSet){
				// return
				// AssociationHelper.getRoleSetGroups((RoleSet)obj).isEmpty();
				// }
				return !(getHelper().isContributor((ContentElement) obj));
			}
			return false;
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new CustomCategoryAssignFilter();
	}
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.customCategoryAssignPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.customCategoryAssignPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.customCategoryAssignPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.customCategoryAssignPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return null;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return null;
	}
	
	@Override
	protected EStructuralFeature getOrderFeature() {
		return UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
	}
	
	@Override
	protected String[] getModelStructurePath() {
		return ModelStructure.DEFAULT.customCategoryPath;
	}
	
	@Override
	public ContentElementOrderList getContentElementOrderList() {
		return allSteps;
	}
}
