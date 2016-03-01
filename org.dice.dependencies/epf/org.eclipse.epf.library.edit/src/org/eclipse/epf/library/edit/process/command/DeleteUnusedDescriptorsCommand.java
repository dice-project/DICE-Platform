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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IInteractive;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.util.AdapterFactoryItemLabelProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command for clean-up unused descriptors
 * 
 * @author Phong Nguyen Le
 */
public abstract class DeleteUnusedDescriptorsCommand extends CompoundCommand 
implements IInteractive
{
	private Collection elements;
	private List descriptorsToDelete;
	private boolean includeChildren;
	private Collection deletedDescriptors;
	private IUserInteractionHandler userInteractionHandler;

	/**
	 * @param elements list of elements to check for unused descriptors to delete 
	 * @param includeChildren if <code>true</code> will check the offstring of activities in the given elements
	 */
	public DeleteUnusedDescriptorsCommand(Collection elements, boolean includeChildren, Collection deletedDescriptors) {
		super();
		this.elements = elements;
		this.includeChildren = includeChildren;
		this.deletedDescriptors = deletedDescriptors;
	}
	
	/**
	 * Deletes the specified elements. Subclasss must implement this method
	 * 
	 * @param elements
	 * @return the executed command that deleted the given elements
	 */
	protected abstract Command delete(List elements);
	
	public void execute() {
		// find all role and work product descriptors that are not used
		// in any relationship
		// and delete them
		//
		if (descriptorsToDelete == null) {
			descriptorsToDelete = new ArrayList();
			Iterator iter;
			if(includeChildren) {
				iter = new AbstractTreeIterator(elements, false) {
					
					
					/**
					 * Comment for <code>serialVersionUID</code>
					 */
					private static final long serialVersionUID = 4718889409403141707L;
					
					protected Iterator getChildren(Object object) {
						if (object == elements) {
							return elements.iterator();
						} else if (object instanceof Activity) {
							return ((Activity) object)
							.getBreakdownElements().iterator();
						} else {
							return Collections.EMPTY_LIST.iterator();
						}
					}
					
				};
			}
			else {
				iter = elements.iterator();
			}
			ArrayList currentDescriptorsToDelete = new ArrayList();
			if(deletedDescriptors != null) {
				// add deleted descriptors to the list before checking for unused descriptors
				// those deleted descriptors will be removed from the list after the check
				//
				currentDescriptorsToDelete.addAll(deletedDescriptors);
			}
			for (; iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof RoleDescriptor || element instanceof WorkProductDescriptor) {
						if (!ProcessUtil.checkDescriptorReferences(
							currentDescriptorsToDelete, (Descriptor) element)) {
						currentDescriptorsToDelete.add(element);
					}
				}
			}
			currentDescriptorsToDelete.removeAll(deletedDescriptors);
			if (!currentDescriptorsToDelete.isEmpty()) {
				selectDescriptorsToDelete(currentDescriptorsToDelete);
				if(!currentDescriptorsToDelete.isEmpty()) {
					Command cmd = delete(currentDescriptorsToDelete);
					if (cmd != null) {
						commandList.add(cmd);
						descriptorsToDelete.addAll(currentDescriptorsToDelete);
					}
				}
			}
		}
		else {
			if(!descriptorsToDelete.isEmpty()) {
				commandList.clear();
				append(delete(descriptorsToDelete));
			}
		}
	}
	
	private void selectDescriptorsToDelete(List descriptorsToDelete) {
		if(userInteractionHandler != null) {
			IItemLabelProvider labelProvider = new AdapterFactoryItemLabelProvider(
					TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory());
			String title = LibraryEditResources.ui_references;
			String msg = LibraryEditResources.selectDescriptorsToDelete_msg;			
			List list = new ArrayList(descriptorsToDelete);
			Collection selected = userInteractionHandler.select(list, labelProvider, true, list, title, msg);
			if(selected == null) {
				throw new OperationCanceledException();
			}
			descriptorsToDelete.retainAll(selected);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.IInteractive#setUserInteractionHandler(org.eclipse.epf.library.edit.command.IUserInteractionHandler)
	 */
	public void setUserInteractionHandler(IUserInteractionHandler handler) {
		userInteractionHandler = handler;
	}
}
