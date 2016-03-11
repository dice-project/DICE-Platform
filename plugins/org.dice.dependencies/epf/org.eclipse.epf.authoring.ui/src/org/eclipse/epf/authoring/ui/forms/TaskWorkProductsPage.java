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
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.WorkProductFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Work Products page in the Task editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class TaskWorkProductsPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "taskWorkProductsPage"; //$NON-NLS-1$	

	private Task task;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public TaskWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		task = (Task) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
		setAllowChange1(true);
		setAllowChange2(true);
		setAllowChange3(true);
	}

	/**
	 * @see org.eclipse.ui.forms.editor.createFormContent(IManagedForm)
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				return ((Task) object).getMandatoryInput().toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {
			List elementList = retrieveTableViewerContents(viewer_selected2);
			List addItemsFiltered = new ArrayList();
			for (int i = 0; i < addItems.size(); i++) {
				if (!elementList.contains(((WorkProduct) addItems.get(i)))) {
					addItemsFiltered.add(addItems.get(i));
				}
			}
			if (addItems.size() > 0) {
				actionMgr.doAction(IActionManager.ADD_MANY, task,
						UmaPackage.eINSTANCE.getTask_MandatoryInput(),
						addItemsFiltered, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			actionMgr.doAction(IActionManager.REMOVE_MANY, task,
					UmaPackage.eINSTANCE.getTask_MandatoryInput(), rmItems, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected2()
	 */
	protected void initContentProviderSelected2() {
		contentProviderSelected2 = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 2);
				}
				return ((Task) object).getOptionalInput().toArray();
			}
		};
		viewer_selected2.setContentProvider(contentProviderSelected2);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel2(ArrayList)
	 */
	protected void addItemsToModel2(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {
			List elementList = retrieveTableViewerContents(viewer_selected);
			List addItemsFiltered = new ArrayList();
			for (int i = 0; i < addItems.size(); i++) {
				if (!elementList.contains(((WorkProduct) addItems.get(i)))) {
					addItemsFiltered.add(addItems.get(i));
				}
			}
			if (addItems.size() > 0) {
				actionMgr.doAction(IActionManager.ADD_MANY, task,
						UmaPackage.eINSTANCE.getTask_OptionalInput(),
						addItemsFiltered, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel2(ArrayList)
	 */
	protected void removeItemsFromModel2(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			actionMgr.doAction(IActionManager.REMOVE_MANY, task,
					UmaPackage.eINSTANCE.getTask_OptionalInput(), rmItems, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected3()
	 */
	protected void initContentProviderSelected3() {
		contentProviderSelected3 = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 3);
				}
				return ((Task) object).getOutput().toArray();
			}
		};
		viewer_selected3.setContentProvider(contentProviderSelected3);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel2(ArrayList)
	 */
	protected void addItemsToModel3(ArrayList addItems) {
		actionMgr.doAction(IActionManager.ADD_MANY, task, UmaPackage.eINSTANCE
				.getTask_Output(), addItems, -1);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel2(ArrayList)
	 */
	protected void removeItemsFromModel3(ArrayList rmItems) {
		actionMgr.doAction(IActionManager.REMOVE_MANY, task,
				UmaPackage.eINSTANCE.getTask_Output(), rmItems, -1);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return task;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new WorkProductFilter();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter2()
	 */
	protected IFilter getFilter2() {
		return filter = new WorkProductFilter();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter3()
	 */
	protected IFilter getFilter3() {
		return filter = new WorkProductFilter();
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.taskWorkProductsPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.taskWorkProductsPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.taskWorkProductsPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.taskWorkProductsPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.taskWorkProductsPage_selectedLabel2;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.taskWorkProductsPage_selectedLabel3;
	}
	
	@Override
	public EReference getReference(int ix) {
		if (ix == 1) {
			return UmaPackage.eINSTANCE.getTask_MandatoryInput();
		}
		if (ix == 2) {
			return UmaPackage.eINSTANCE.getTask_OptionalInput();
		}
		if (ix == 3) {
			return UmaPackage.eINSTANCE.getTask_Output();
		}		
		return super.getReference(ix);
	}

}
