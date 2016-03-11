//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.forms;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.CategoryFilter;
import org.eclipse.epf.authoring.ui.filters.CustomCategoryFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Categories page in the Work Product editor.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 * remove the to-one UI restriction on wp-domian (R395390) 2007-08-30
 */
public class WorkProductCategoriesPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "workProductCategoriesPage"; //$NON-NLS-1$

	private WorkProduct workProduct;

	/**
	 * Creates a new instance.
	 */
	public WorkProductCategoriesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.CATEGORIES_PAGE_TITLE);
	}
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		workProduct = (WorkProduct) contentElement;
		setUseCategory3(true);
		setCategoryIsSingleSelection1(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return AssociationHelper.getDomains((WorkProduct) object).toArray();
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
			Object domain = addItems.get(0);
			List domains = AssociationHelper.getDomains(workProduct);
			if (domains != null && domains.size() > 0)	{
				Object associatedDomain = domains.get(0);
//				String msg = MessageFormat
//				.format(
//						LibraryEditResources.UserInteractionHelper_errRelationshipExists,
//						new Object[] {
//								workProduct.getName(),
//								TngUtil
//										.getLabelWithPath(associatedDomain),
//								((Domain) domain).getName() });
//				Messenger.INSTANCE.showWarning(
//						LibraryEditResources.errorDialog_title, msg);
//				return;
			}
			
			LibraryManager.getInstance().addToDomain(getActionManager(),
					(Domain) domain, workProduct, usedCategories);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Domain domain = (Domain) it.next();
				LibraryManager.getInstance().removeFromDomain(
						getActionManager(), domain, workProduct, usedCategories);
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
				return AssociationHelper.getWorkProductTypes(
						(WorkProduct) object).toArray();
			}
		};
		viewer_selected2.setContentProvider(contentProviderSelected2);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel2()
	 */
	protected void addItemsToModel2(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {
			for (java.util.Iterator it = addItems.iterator(); it.hasNext();) {
				WorkProductType wpType = (WorkProductType) it.next();
				LibraryManager.getInstance().addToWorkProductType(
						getActionManager(), wpType, workProduct, usedCategories);
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
				WorkProductType wpType = (WorkProductType) it.next();
				LibraryManager.getInstance().removeFromWorkProductType(
						getActionManager(), wpType, workProduct, usedCategories);
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
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				return AssociationHelper.getCustomCategories(
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
			for (java.util.Iterator it = addItems.iterator(); it.hasNext();) {
				CustomCategory customCategory = (CustomCategory) it.next();
				LibraryManager.getInstance().addToCustomCategory(
						getActionManager(), customCategory, workProduct, usedCategories);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel3(ArrayList)
	 */
	protected void removeItemsFromModel3(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (java.util.Iterator it = rmItems.iterator(); it.hasNext();) {
				CustomCategory customCategory = (CustomCategory) it.next();
				LibraryManager.getInstance().removeFromCustomCategory(
						getActionManager(), customCategory, workProduct, usedCategories);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return workProduct;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.DOMAINS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString2()
	 */
	protected String getTabString2() {
		return FilterConstants.WORKPRODUCTTYPES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString3()
	 */
	protected String getTabString3() {
		return FilterConstants.CUSTOM_CATEGORIES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new CategoryFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof Domain);
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter2()
	 */
	protected IFilter getFilter2() {
		return filter2 = new CategoryFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof WorkProductType);
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter3()
	 */
	protected IFilter getFilter3() {
		return filter3 = new CustomCategoryFilter();
	}
	
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.workProductCategoriesPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.workProductCategoriesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.workProductCategoriesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.workProductCategoriesPage_selectedLabel2;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.workProductCategoriesPage_selectedLabel3;
	}
	
	protected String getMultipleSelectDescriptionString() {
		return AuthoringUIResources.workProductCategoriesPage_multipleSelectDescription;
	}
	
	@Override
	public OppositeFeature getOppositeFeature(int ix) {
		return ix == 1 ? AssociationHelper.WorkProduct_Domains : super.getOppositeFeature(ix);
	}

}
