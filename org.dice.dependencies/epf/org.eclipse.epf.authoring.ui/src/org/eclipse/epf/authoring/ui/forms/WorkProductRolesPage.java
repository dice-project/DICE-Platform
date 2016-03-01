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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Roles page in the Work Product editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 * 
 * TODO: Rename this class to WorkProductRolesFormPage
 */
public class WorkProductRolesPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "workProductRolesPage"; //$NON-NLS-1$

	private WorkProduct workProduct;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public WorkProductRolesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.ROLES_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		workProduct = (WorkProduct) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
		setUseCategory3(false);
		setCategoryIsSingleSelection1(true);
		setAllowChange1(false);
		setAllowChange2(false);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return AssociationHelper.getResponsibleRoles((WorkProduct) object).toArray();
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
			Role role = (Role) addItems.get(0);
			if (UserInteractionHelper.checkModifyOpposite(role,
					UmaPackage.eINSTANCE.getRole_ResponsibleFor(), workProduct)) {
				actionMgr.doAction(IActionManager.ADD, role,
						UmaPackage.eINSTANCE.getRole_ResponsibleFor(),
						workProduct, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
//			LibraryManager.getInstance().setResponsibleRole(getActionManager(),
//					workProduct, null);
			
			for (Iterator iter = rmItems.iterator(); iter.hasNext();) {
				Role role = (Role) iter.next();
				getActionManager().doAction(IActionManager.REMOVE, role, UmaPackage.eINSTANCE.getRole_ResponsibleFor(), workProduct, -1);
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
				return AssociationHelper.getModifiedBy((WorkProduct) object)
						.toArray();
			}
		};
		viewer_selected2.setContentProvider(contentProviderSelected2);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.workProductRolesPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.workProductRolesPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.workProductRolesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.workProductRolesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.workProductRolesPage_selectedLabel2;
	}
	
	
}
