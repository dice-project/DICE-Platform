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
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * 
 * Command to assign deliverable parts to work product descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AssignWPToDeliverable extends AddMethodElementCommand {

	private List workProducts;

	private Activity activity;

	private WorkProductDescriptor wpDesc;

	private Collection modifiedResources;
	
	private Collection affectedObjects;

	private HashMap map = new HashMap();

	List existingWPDescList = new ArrayList();

	List newWPDescList = new ArrayList();
	
	private boolean calledForExculded = false;
	
	private DescriptorPropUtil propUtil;

	public AssignWPToDeliverable(WorkProductDescriptor wpDesc, List workProducts) {
		this(wpDesc, workProducts, false);
	}
	
	/**
	 * 
	 */
	public AssignWPToDeliverable(WorkProductDescriptor wpDesc, List workProducts, boolean calledForExculded) {
		super(TngUtil.getOwningProcess(wpDesc));

		this.workProducts = workProducts;
		this.wpDesc = wpDesc;
		this.calledForExculded = calledForExculded;
		this.propUtil = DescriptorPropUtil.getDesciptorPropUtil();

		Object parent = getParentActivity(wpDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet();
		this.affectedObjects = new HashSet();

	}

	/**
	 * Get parent activity for breakdownelement
	 * 
	 * @param brElement
	 * @return
	 */
	private Object getParentActivity(BreakdownElement brElement) {
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Activity)) {
			brElement = (BreakdownElement) parent;
			adapter = (ItemProviderAdapter) adapterFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}

		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {

		List breakdownElements = activity.getBreakdownElements();

		// add to default configuration if not there already
		if (!super.addToDefaultConfiguration(workProducts))
			return;

		for (Iterator it = workProducts.iterator(); it.hasNext();) {
			boolean descExists = false;
			WorkProduct wp = (WorkProduct) it.next();
			WorkProductDescriptor newWpDesc = null;
			for (int i = 0; i < breakdownElements.size(); i++) {
				BreakdownElement element = (BreakdownElement) breakdownElements
						.get(i);
				if (element instanceof WorkProductDescriptor) {
					newWpDesc = (WorkProductDescriptor) element;
					WorkProduct elementWP = newWpDesc.getWorkProduct();
					if (wp.equals(elementWP)) {
						// if found under the parent activity, return that
						// wpdescriptor
						descExists = true;

						existingWPDescList.add(element);
						break;
					}
				}

			}

			if (!descExists) {
				newWPDescList.add(ProcessUtil.createWorkProductDescriptor(wp));
			}

			// get deliverable
			WorkProductDescriptor deliverable = UserInteractionHelper
					.getDeliverable(activity, wp);
			if (deliverable != null) {
				map.put(newWpDesc, deliverable);
			}
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		wpDesc.getDeliverableParts().addAll(existingWPDescList);
		wpDesc.getDeliverableParts().addAll(newWPDescList);
		
		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = wpDesc.getDeliverablePartsExclude();
				if (excludedList != null) {
					excludedList.removeAll(workProducts);
				}

			} else {
				propUtil.addLocalUsingInfo(existingWPDescList, wpDesc,
						UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
				propUtil.addLocalUsingInfo(newWPDescList, wpDesc,
						UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
			}
			for (WorkProductDescriptor rd : (List<WorkProductDescriptor>) newWPDescList) {
				propUtil.setCreatedByReference(rd, true);
			}
		}

		// activity.getBreakdownElements().addAll(newWPDescList);
	}

	public void undo() {

		// basically remove from configuration if anything was added
		super.undo();

		wpDesc.getDeliverableParts().removeAll(existingWPDescList);
		wpDesc.getDeliverableParts().removeAll(newWPDescList);
		
		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = wpDesc.getDeliverablePartsExclude();			
				if (excludedList != null) {
					excludedList.addAll(workProducts);
				}
			} else {
				propUtil.removeLocalUsingInfo(existingWPDescList, wpDesc,
						UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
				propUtil.removeLocalUsingInfo(newWPDescList, wpDesc,
						UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
			}
		}

		// activity.getBreakdownElements().removeAll(newWPDescList);
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		if (workProducts != null &&  !workProducts.isEmpty()) {
			if (activity.eResource() != null) {
				modifiedResources.add(activity.eResource());
			}
			if (wpDesc.eResource() != null) {
				modifiedResources.add(wpDesc.eResource());
			}
		}
		return modifiedResources;
	}
	
	public Collection getAffectedObjects() {
		if (workProducts != null &&  !workProducts.isEmpty()) {
			affectedObjects.add(activity);
			affectedObjects.add(wpDesc);
			return affectedObjects;
		}
		return super.getAffectedObjects();
	}
}
