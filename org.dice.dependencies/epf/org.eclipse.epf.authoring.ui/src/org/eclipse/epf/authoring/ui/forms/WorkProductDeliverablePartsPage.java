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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.WorkProductFilter;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Deliverable Parts page in the Work Product editor.
 * 
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductDeliverablePartsPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "workProductDeliverablePartsPage"; //$NON-NLS-1$

	private WorkProduct workProduct;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public WorkProductDeliverablePartsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID,
				AuthoringUIText.DELIVERABLE_PARTS_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		workProduct = (WorkProduct) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
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
				Deliverable wp = (Deliverable) workProduct;
				return wp.getDeliveredWorkProducts().toArray();
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
				WorkProduct item = (WorkProduct) it.next();
				actionMgr.doAction(IActionManager.ADD, workProduct,
						UmaPackage.eINSTANCE
								.getDeliverable_DeliveredWorkProducts(), item,
						-1);
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
				WorkProduct item = (WorkProduct) it.next();
				actionMgr.doAction(IActionManager.REMOVE, workProduct,
						UmaPackage.eINSTANCE
								.getDeliverable_DeliveredWorkProducts(), item,
						-1);
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
		return FilterConstants.WORKPRODUCTS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new WorkProductFilter() {
			/*
			 * ArrayList(badlist) is used to store all
			 * generalizer/basevariability for subparts of
			 * generalizer/basevariability can be rejected. Deliverable cannot
			 * hold information to who itself is acting as deliverable part.
			 */
			List badlist = new ArrayList();

			protected boolean childAccept(Object obj) {
				// TODO Auto-generated method stub
				if (obj instanceof WorkProduct) {
					/* 
					 * Check Deliverable and DeliverableParts association
					 * circular dependency.
					*/
					if (obj instanceof Deliverable
							&& workProduct instanceof Deliverable) {
						/*
						 * Check passed object's chained deliverable parts
						 * (tree) contain workProduct (editor object).
						 */
						if (!UIHelper.checkCircularDeliverables(
								(Deliverable) obj, (Deliverable) workProduct)) {
							badlist.add(obj);
							return false;
						}
						if (!checkVariability((Deliverable) obj, badlist)) {
							badlist.add(obj);
							return false;
						}
						// browse through badlist and its deliverable parts.
						List templist = new ArrayList();
						for (Iterator iterator = badlist.iterator(); iterator
								.hasNext();) {
							Object bad = iterator.next();
							UIHelper.deliverablePartsChain((Deliverable) bad, templist);
						}
						if (templist.contains(obj))
							return false;
					}
					/*
					 * return true if above condition are successful
					 */
					return true;
				}
				return false;
			}
		};
		return filter;
	}

	public boolean checkVariability(Deliverable e, List badlist) {

		/*
		 * Check editor object's variability base object is child in
		 * Deliverable(e) tree. then Deliverable(e)shouldnot be accepted. D1 ->
		 * D2 -> D3 ( -> indicates deliverable part relationship) D6 contributes
		 * D3. D6 cannot select D1,D2 as deliverable part.
		 */
		if (workProduct.getVariabilityBasedOnElement() != null) {
			Object obj = workProduct.getVariabilityBasedOnElement();
			if (!UIHelper.checkCircularDeliverables(e, (Deliverable) obj))
				return false;
		}

		/*
		 * Check editor Deliverable's generalizer, if generalizer's subparts
		 * (tree) contains passed deliverable. Return false.
		 */

		Iterator it = TngUtil.getGeneralizers(workProduct);
		while (it.hasNext()) {
			VariabilityElement ve = (VariabilityElement) it.next();
			if (ve != null) {
				/*
				 * generalizer is added to badlist, because if variability base
				 * is filtered before coming to childAccept(object) method, this
				 * will prevents any subparts of generalizer to be added as a
				 * deliverable part of Deliverable (Editor object or local
				 * defined variable"workProduct").
				 * 
				 * eg: D1 -> D2 -> D3. (-> indicates deliverable part). D1
				 * contributes D5. and D5's deliverable parts assign filter
				 * shouldnot show D2 and D3.
				 * 
				 */
				badlist.add(ve);
				if (ve.equals(e))
					return false;
				if (!UIHelper.checkCircularDeliverables(e, (Deliverable) ve))
					return false;
			}

		}
		/*
		 * 
		 */
		if (e.getVariabilityBasedOnElement() != null) {
			Object obj = e.getVariabilityBasedOnElement();
			if (!UIHelper.checkCircularDeliverables((Deliverable) obj,
					(Deliverable) workProduct))
				return false;
		}
		/*
		 * Check passed object's generalizer's deliverable parts list donot have
		 * workProduct.
		 */
		Iterator it1 = TngUtil.getGeneralizers(e);
		while (it1.hasNext()) {
			VariabilityElement ve = (VariabilityElement) it1.next();
			if (ve != null) {
				if (ve.equals(e))
					return false;
				if (!UIHelper.checkCircularDeliverables((Deliverable) ve,
						(Deliverable) workProduct))
					return false;
			}

		}

		/*
		 * Check workProduct object (editor object)'s variability and its
		 * deliverables should not contain passed object.
		 */
		List list = new ArrayList();
		deliverablePartsVariability((Deliverable) workProduct, list);
		if (list.contains(e))
			return false;

		/*
		 * Check passed object variability and its deliverables should not
		 * contain workproduct(deliverable or editor object). If it contains,
		 * passed object shouldnot be accepted.
		 */
		deliverablePartsVariability((Deliverable) e, list);
		if (list.contains(workProduct))
			return false;
		return true;
	}

	private void deliverablePartsVariability(Deliverable e, List roots) {
		List list = ((Deliverable) e).getDeliveredWorkProducts();
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				if (obj instanceof Deliverable) {
					if (((Deliverable) obj).getVariabilityBasedOnElement() != null) {
						roots.add(((Deliverable) obj)
								.getVariabilityBasedOnElement());
					} else {
						Iterator it1 = TngUtil.getGeneralizers(e);
						while (it1.hasNext()) {
							VariabilityElement ve = (VariabilityElement) it1
									.next();
							UIHelper.deliverablePartsChain((Deliverable) ve, roots);
						}
					}
					deliverablePartsVariability((Deliverable) obj, roots);
				}
			}
		}
		if (e instanceof Deliverable) {
			Iterator it1 = TngUtil.getGeneralizers(e);
			while (it1.hasNext()) {
				VariabilityElement ve = (VariabilityElement) it1.next();
				UIHelper.deliverablePartsChain((Deliverable) ve, roots);
			}
		}
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.workProductDeliverablePartsPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.workProductDeliverablePartsPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.workProductDeliverablePartsPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.workProductDeliverablePartsPage_selectedLabel;
	}
	
	@Override
	public EReference getReference(int ix) {
		if (ix == 1) {
			return UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts();
		}	
		return super.getReference(ix);
	}


}
