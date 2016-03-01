//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006, 2007 IBM Corporation and others.
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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Roles page in the Role Set editor.
 * 
 * @author Lokanath Jagga
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class RoleSetRolesPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "roleSetRolesPage"; //$NON-NLS-1$

	private RoleSet roleSet;
	
	private ContentElementOrderList allSteps;

	/**
	 * Creates a new instance.
	 */
	public RoleSetRolesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.ROLES_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		roleSet = (RoleSet) contentElement;
		setUseCategory2(false);
		setUseCategory3(false);
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
				//return ((RoleSet) object).getRoles().toArray();
				if (allSteps == null) {
//					allSteps = new ContentElementOrderList(
//							contentElement,
//							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
//							getOrderFeature());
					allSteps = getProviderExtender().newContentElementOrderList(contentElement, 
							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
							getOrderFeature(), 1);					
				}
				List returnList = CategorySortHelper.sortCategoryElements(contentElement, 
						allSteps.toArray());
				return returnList.toArray();
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
			editor.getActionManager().doAction(IActionManager.ADD_MANY,
					roleSet, UmaPackage.eINSTANCE.getRoleSet_Roles(), addItems,
					-1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			editor.getActionManager().doAction(IActionManager.REMOVE_MANY,
					roleSet, UmaPackage.eINSTANCE.getRoleSet_Roles(), rmItems,
					-1);
		}
	}
		
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return roleSet;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.ROLES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				if (obj instanceof ContentElement) {
					if (getHelper().isContributor((ContentElement) obj))
						return false;
				}
				return (obj instanceof Role);
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.roleSetRolesPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.roleSetRolesPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.roleSetRolesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.roleSetRolesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.roleSetRolesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.roleSetRolesPage_selectedLabel;
	}
	
	@Override
	protected EStructuralFeature getOrderFeature() {
		return UmaPackage.eINSTANCE.getRoleSet_Roles();
	}

	@Override
	protected String[] getModelStructurePath() {
		return ModelStructure.DEFAULT.roleSetPath;
	}
	
	@Override
	public ContentElementOrderList getContentElementOrderList() {
		return allSteps;
	}

}
