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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Tasks page in the Work Product editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductTasksPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "workProductTasksPage"; //$NON-NLS-1$

	private WorkProduct workProduct;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public WorkProductTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		workProduct = (WorkProduct) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
		setAllowChange1(false);
		setAllowChange2(false);
		setAllowChange3(false);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return AssociationHelper.getMandatoryInputToTasks(
						(WorkProduct) object).toArray();
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
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				if (!elementList.contains(task)) {
					actionMgr.doAction(IActionManager.ADD, task,
							UmaPackage.eINSTANCE.getTask_MandatoryInput(),
							workProduct, -1);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				actionMgr.doAction(IActionManager.REMOVE, task,
						UmaPackage.eINSTANCE.getTask_MandatoryInput(),
						workProduct, -1);
			}
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
				List selectedTasks = new ArrayList();
				List availableTasksList = LibraryManager.getInstance()
						.getAvailableTasks(workProduct);
				if (availableTasksList != null && availableTasksList.size() > 0) {
					for (java.util.Iterator it = availableTasksList.iterator(); it
							.hasNext();) {
						Task task = (Task) it.next();
						if (task.getOptionalInput().contains(workProduct))
							selectedTasks.add(task);
					}
				}
				return selectedTasks.toArray();
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
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				if (!elementList.contains(task)) {
					actionMgr.doAction(IActionManager.ADD, task,
							UmaPackage.eINSTANCE.getTask_OptionalInput(),
							workProduct, -1);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel2(ArrayList)
	 */
	protected void removeItemsFromModel2(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				actionMgr.doAction(IActionManager.REMOVE, task,
						UmaPackage.eINSTANCE.getTask_OptionalInput(),
						workProduct, -1);
			}
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
				return AssociationHelper.getOutputtingTasks(
						(WorkProduct) object).toArray();
			}
		};
		viewer_selected3.setContentProvider(contentProviderSelected3);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel3(ArrayList)
	 */
	protected void addItemsToModel3(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				actionMgr.doAction(IActionManager.ADD, task,
						UmaPackage.eINSTANCE.getTask_Output(), workProduct, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel3(ArrayList)
	 */
	protected void removeItemsFromModel3(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				actionMgr.doAction(IActionManager.REMOVE, task,
						UmaPackage.eINSTANCE.getTask_Output(), workProduct, -1);
			}
		}
	}
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.WorkProduct_Tasks_MultipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.WorkProduct_Tasks_SectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.WorkProduct_Tasks_SectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.WorkProduct_Tasks_SelectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.WorkProduct_Tasks_SelectedLabel2;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.WorkProduct_Tasks_SelectedLabel3;
	}
}
