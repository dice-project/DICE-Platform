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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.ReferenceSelection;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * Delete work product descriptor
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 * 
 */
public class DeleteWorkProductDescriptor extends RemoveUnusedDescriptorsCommand {
	private Activity activity;

	private WorkProductDescriptor wpDesc;

	private Collection modifiedResources;

	private List notUsedReferences = new ArrayList();

	private boolean forceRemoveUnusedReferences;

	/**
	 * 
	 */
	public DeleteWorkProductDescriptor(WorkProductDescriptor wpDesc) {
		super();

		this.wpDesc = wpDesc;

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				wpDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(wpDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet();
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}
		if (wpDesc.eResource() != null) {
			modifiedResources.add(wpDesc.eResource());
		}
	}

	public DeleteWorkProductDescriptor(WorkProductDescriptor wpDesc,
			boolean forceRemoveUnusedReferences) {
		this(wpDesc);
		this.forceRemoveUnusedReferences = forceRemoveUnusedReferences;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		Set refList = new HashSet();

		List mandatoryInputTo = AssociationHelper.getMandatoryInputTo(wpDesc);
		List externalInputTo = AssociationHelper.getExternalInputTo(wpDesc);
		List optionalInputTo = AssociationHelper.getOptionalInputTo(wpDesc);
		List outputFrom = AssociationHelper.getOutputFrom(wpDesc);
		List responsibleRoleDescList = AssociationHelper.getResponsibleRoleDescriptors(wpDesc);
		// get WorkProductDescriptor relationships references
		refList.addAll(wpDesc.getImpactedBy());
		refList.addAll(wpDesc.getImpacts());
		refList.addAll(mandatoryInputTo);
		refList.addAll(externalInputTo);
		refList.addAll(optionalInputTo);
		refList.addAll(outputFrom);
		refList.add(responsibleRoleDescList);

		for (Iterator itor = refList.iterator(); itor.hasNext();) {
			Object object = itor.next();

			if (object instanceof Descriptor) {
				if (!ProcessUtil.checkDescriptorReferences(wpDesc,
						(Descriptor) object)) {
					if (activity.getBreakdownElements().contains(object)) // check
																			// for
																			// local
																			// descriptor
						notUsedReferences.add(object);
				}
			}
		}

		if (!(notUsedReferences.isEmpty())) {
			try {
				Object[] refToBeDeleted = forceRemoveUnusedReferences ? notUsedReferences
						.toArray()
						: ReferenceSelection.getReferences(notUsedReferences,
								wpDesc);
						delete(refToBeDeleted);
			}
			catch(OperationCanceledException e) {
				aborted = true;
			}
		}

		// check whether wpdesc artifact has any sub-artifacts
		List children = new ArrayList();
		if (wpDesc.getWorkProduct() instanceof Artifact) {
			Artifact artifact = (Artifact) wpDesc.getWorkProduct();
			List list = artifact.getContainedArtifacts();
			for (int i = 0; i < list.size(); i++) {
				Object descriptor = getDescriptor(wpDesc, (Artifact) list
						.get(i));
				if (descriptor != null) {
					children.add(descriptor);
				}
			}
		}

		List objectsToCleared = new ArrayList();
		objectsToCleared.add(wpDesc);
		objectsToCleared.addAll(children);

		for (Iterator itor = objectsToCleared.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpDescObj = (WorkProductDescriptor) obj;

				// wpDescObj.getImpactedBy().clear();
				// wpDescObj.getImpacts().clear();
				// wpDescObj.getMandatoryInputTo().clear();
				// wpDescObj.getExternalInputTo().clear();
				// wpDescObj.getOptionalInputTo().clear();
				// wpDescObj.getOutputFrom().clear();
				// wpDescObj.getWorkedOnBy().clear();
				clear(wpDescObj.getImpactedBy());
				clear(wpDescObj.getImpacts());
//				clear(wpDescObj.getExternalInputTo());
				for (Iterator iter = externalInputTo.iterator(); iter
						.hasNext();) {
					TaskDescriptor td = (TaskDescriptor) iter.next();
					td.getExternalInput().remove(wpDescObj);
				}
//				clear(wpDescObj.getMandatoryInputTo());
				for (Iterator iter = mandatoryInputTo.iterator(); iter
						.hasNext();) {
					TaskDescriptor td = (TaskDescriptor) iter.next();
					td.getMandatoryInput().remove(wpDescObj);
				}
//				clear(wpDescObj.getOptionalInputTo());
				for (Iterator iter = optionalInputTo.iterator(); iter
						.hasNext();) {
					TaskDescriptor td = (TaskDescriptor) iter.next();
					td.getOptionalInput().remove(wpDescObj);
				}
//				clear(wpDescObj.getOutputFrom());
				for (Iterator iter = outputFrom.iterator(); iter
						.hasNext();) {
					TaskDescriptor td = (TaskDescriptor) iter.next();
					td.getOutput().remove(wpDescObj);
				}
				for (Iterator iter = responsibleRoleDescList.iterator(); iter
						.hasNext();) {
					RoleDescriptor roleDesc = (RoleDescriptor) iter.next();
					roleDesc.getResponsibleFor().remove(wpDescObj);
				}
			}
		}

		// this.activity.getBreakdownElements().remove(wpDesc);

		redo();
	}

	private void clear(List list) {
		try {
			if ((list != null) && (list.size() > 0))
				list.clear();
		} catch (Exception ex) {
			LibraryEditPlugin.INSTANCE.log(ex);
		}
	}

	private Object getDescriptor(Object parentDescriptor, Artifact artifact) {
		// Activity activity = getActivity(parentDescriptor);
		if (activity == null)
			return null;
		List list = activity.getBreakdownElements();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object obj = list.get(i);
			if ((obj instanceof WorkProductDescriptor)
					&& (artifact == ((WorkProductDescriptor) obj)
							.getWorkProduct())) {
				return obj;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

	}

	public void undo() {

	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {

		return modifiedResources;
	}
}
