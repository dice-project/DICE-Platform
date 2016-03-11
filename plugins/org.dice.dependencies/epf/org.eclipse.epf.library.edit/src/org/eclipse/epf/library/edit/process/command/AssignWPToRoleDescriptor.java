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
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command to assign work products to role descriptors. It will set reponsible
 * role feature.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AssignWPToRoleDescriptor extends AddMethodElementCommand {

	private List workProducts;

	private Activity activity;

	private RoleDescriptor roleDesc;

	private Collection modifiedResources;
	
	private Collection affectedObjects;

	private HashMap map = new HashMap();

	private int action;

	List existingWPDescList = new ArrayList();

	List newWPDescList = new ArrayList();

	private MethodConfiguration config;
	
	private boolean calledForExculded = false;
	
	private DescriptorPropUtil propUtil;

	public AssignWPToRoleDescriptor(RoleDescriptor roleDesc, List workProducts,
			int action, MethodConfiguration config) {
		this(roleDesc, workProducts, action, config, false);
	}
	
	/**
	 * 
	 */
	public AssignWPToRoleDescriptor(RoleDescriptor roleDesc, List workProducts,
			int action, MethodConfiguration config, boolean calledForExculded) {

		super(TngUtil.getOwningProcess(roleDesc));

		this.workProducts = workProducts;
		this.roleDesc = roleDesc;
		this.action = action;
		this.config = config;
		this.calledForExculded = calledForExculded;
		this.propUtil = DescriptorPropUtil.getDesciptorPropUtil();

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				roleDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(roleDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}
		this.modifiedResources = new HashSet();
		this.affectedObjects = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		// add to default configuration if not there already
		if (!super.addToDefaultConfiguration(workProducts))
			return;

		for (Iterator it = workProducts.iterator(); it.hasNext();) {
			WorkProduct wp = (WorkProduct) it.next();
			WorkProductDescriptor newWpDesc = null;

			boolean isNewDescriptor = false;
			// check for local descriptor
			newWpDesc = (WorkProductDescriptor) ProcessCommandUtil
					.getDescriptor(wp, activity, config);
			if (newWpDesc == null) {
				// check for inherited descriptor
				newWpDesc = (WorkProductDescriptor) ProcessCommandUtil
						.getInheritedDescriptor(wp, activity, config);
				if (newWpDesc == null) {
					newWpDesc = ProcessUtil.createWorkProductDescriptor(wp);
					isNewDescriptor = true;

				}
			}
			if (isNewDescriptor)
				newWPDescList.add(newWpDesc);
			else
				existingWPDescList.add(newWpDesc);

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

		if (action == IActionTypeConstants.ADD_RESPONSIBLE_FOR) {
			roleDesc.getResponsibleFor().addAll(existingWPDescList);
			roleDesc.getResponsibleFor().addAll(newWPDescList);
		}
		
		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = null;
				if (action == IActionTypeConstants.ADD_RESPONSIBLE_FOR) {
					excludedList = roleDesc.getResponsibleForExclude();
				}
				if (excludedList != null) {
					excludedList.removeAll(workProducts);
				}

			} else {
				propUtil.addLocalUsingInfo(existingWPDescList, roleDesc, getFeature(action));
				propUtil.addLocalUsingInfo(newWPDescList, roleDesc, getFeature(action));
			}
			
			for (WorkProductDescriptor rd : (List<WorkProductDescriptor>) newWPDescList) {
				propUtil.setCreatedByReference(rd, true);
			}
		}

		activity.getBreakdownElements().addAll(newWPDescList);

		if (map != null) {
			Set keyset = map.keySet();
			for (Iterator itor = keyset.iterator(); itor.hasNext();) {
				Object key = itor.next();
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) map
						.get(key);

				// add to deliverable
				wpDesc.getDeliverableParts().add((WorkProductDescriptor) key);
			}
		}		
	}
	
	private EReference getFeature(int action) {
		UmaPackage up = UmaPackage.eINSTANCE;
		
		if (action == IActionTypeConstants.ADD_RESPONSIBLE_FOR) {
			return up.getRoleDescriptor_ResponsibleFor();		
		}
		
		return null;
	}

	public void undo() {

		// remove from configuration if anything was added
		super.undo();

		if (action == IActionTypeConstants.ADD_RESPONSIBLE_FOR) {
			roleDesc.getResponsibleFor().removeAll(existingWPDescList);
			roleDesc.getResponsibleFor().removeAll(newWPDescList);
		}
		
		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = null;
				if (action == IActionTypeConstants.ADD_RESPONSIBLE_FOR) {
					excludedList = roleDesc.getResponsibleForExclude();
				}
				if (excludedList != null) {
					excludedList.addAll(workProducts);
				}
			} else {
				propUtil.removeLocalUsingInfo(existingWPDescList, roleDesc, getFeature(action));
				propUtil.removeLocalUsingInfo(newWPDescList, roleDesc, getFeature(action));
			}
		}

		activity.getBreakdownElements().removeAll(newWPDescList);

		if (map != null) {
			Set keyset = map.keySet();
			for (Iterator itor = keyset.iterator(); itor.hasNext();) {
				Object key = itor.next();
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) map
						.get(key);

				// remove it from deliverable
				wpDesc.getDeliverableParts()
						.remove((WorkProductDescriptor) key);
			}
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		if (workProducts != null &&  !workProducts.isEmpty()) {
			if (activity.eResource() != null) {
				modifiedResources.add(activity.eResource());
			}
			if (roleDesc.eResource() != null) {
				modifiedResources.add(roleDesc.eResource());
			}
		}
		return modifiedResources;
	}
	
	public Collection getAffectedObjects() {
		if (workProducts != null &&  !workProducts.isEmpty()) {
			affectedObjects.add(activity);
			affectedObjects.add(roleDesc);
			return affectedObjects;
		}
		return super.getAffectedObjects();
	}
}
