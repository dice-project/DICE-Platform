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
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.ReferenceSelection;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.TaskDescriptor;


/**
 * Delete Task descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class DeleteTaskDescriptor extends RemoveUnusedDescriptorsCommand {
	private Activity activity;

	private TaskDescriptor taskDesc;

	private Collection modifiedResources;

	private List notUsedReferences = new ArrayList();

	private boolean forceRemoveUnusedReferences;

	/**
	 * 
	 */
	public DeleteTaskDescriptor(TaskDescriptor taskDesc) {
		super();

		this.taskDesc = taskDesc;

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				taskDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(taskDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet();
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}
		if (taskDesc.eResource() != null) {
			modifiedResources.add(taskDesc.eResource());
		}
	}

	public DeleteTaskDescriptor(TaskDescriptor taskDesc,
			boolean forceRemoveUnusedReferences) {
		this(taskDesc);
		this.forceRemoveUnusedReferences = forceRemoveUnusedReferences;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		Set refList = new HashSet();

		// get TaskDescriptor relationships references
		refList.addAll(taskDesc.getAdditionallyPerformedBy());
		refList.addAll(taskDesc.getAssistedBy());
		refList.addAll(taskDesc.getPerformedPrimarilyBy());
		refList.addAll(taskDesc.getMandatoryInput());
		refList.addAll(taskDesc.getExternalInput());
		refList.addAll(taskDesc.getOptionalInput());
		refList.addAll(taskDesc.getOutput());

		for (Iterator itor = refList.iterator(); itor.hasNext();) {
			Object object = itor.next();

			// check all it's references
			if (object instanceof Descriptor) {
				if (!ProcessUtil.checkDescriptorReferences(taskDesc,
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
								taskDesc);
						
						delete(refToBeDeleted);
			}
			catch(OperationCanceledException e) {
				aborted = true;
			}
		}

		// clear out all taskdescriptor relationship
		clear(taskDesc.getAdditionallyPerformedBy());
		clear(taskDesc.getAssistedBy());
		clear(taskDesc.getMandatoryInput());
		clear(taskDesc.getExternalInput());
		clear(taskDesc.getOptionalInput());
		clear(taskDesc.getOutput());
		clear(taskDesc.getPerformedPrimarilyBy());

		redo();
	}

	private void clear(List list) {
		try {
			if ((list != null) && (list.size() > 0)) {
				list.clear();
			}
		} catch (Exception ex) {
		}
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
