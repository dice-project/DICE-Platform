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
import org.eclipse.epf.authoring.ui.filters.CategoryFilter;
import org.eclipse.epf.authoring.ui.filters.CustomCategoryFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Categories page in the Role editor.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class RoleCategoriesPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "roleCategoriesPage"; //$NON-NLS-1$

	private Role role;

	/**
	 * Creates a new instance.
	 */
	public RoleCategoriesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.CATEGORIES_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		role = (Role) contentElement;
		setAllowChange1(true);
		setAllowChange2(true);
		setUseCategory3(false);
		setCategoryIsSingleSelection1(false);
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
				return AssociationHelper.getRoleSets((Role) object).toArray();
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
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				RoleSet roleSet = (RoleSet) it.next();
				// TODO: Test on-the-fly creation of category contributor
				LibraryManager.getInstance().addToRoleSet(getActionManager(),
						roleSet, role, usedCategories);
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
				RoleSet roleSet = (RoleSet) it.next();
				// TODO: Test on-the-fly creation of category contributor
				LibraryManager.getInstance().removeFromRoleSet(
						getActionManager(), roleSet, role, usedCategories);
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
				return AssociationHelper.getCustomCategories((Role) object)
						.toArray();
			}
		};
		viewer_selected2.setContentProvider(contentProviderSelected2);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel2()
	 */
	protected void addItemsToModel2(ArrayList addItems) {
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				CustomCategory customCategory = (CustomCategory) it.next();
				LibraryManager.getInstance().addToCustomCategory(
						getActionManager(), customCategory, role, usedCategories);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel2()
	 */
	protected void removeItemsFromModel2(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				CustomCategory customCategory = (CustomCategory) it.next();
				LibraryManager.getInstance().removeFromCustomCategory(
						getActionManager(), customCategory, role, usedCategories);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return role;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.ROLESETS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString2()
	 */
	protected String getTabString2() {
		return FilterConstants.CUSTOM_CATEGORIES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new CategoryFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof RoleSet);
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter2()
	 */
	protected IFilter getFilter2() {
		return filter = new CustomCategoryFilter();
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.roleCategoriesPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.roleCategoriesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.roleCategoriesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.roleCategoriesPage_selectedLabel2;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return null;
	}
	
	protected String getMultipleSelectDescriptionString() {
		return AuthoringUIResources.roleCategoriesPage_multipleSelectDescription;
	}
	
	@Override
	public OppositeFeature getOppositeFeature(int ix) {
		return ix == 1 ? AssociationHelper.Role_RoleSets : super.getOppositeFeature(ix);
	}
	
}
