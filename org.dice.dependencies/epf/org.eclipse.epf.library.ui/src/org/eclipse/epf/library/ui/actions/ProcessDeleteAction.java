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
package org.eclipse.epf.library.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.DeleteRoleDescriptor;
import org.eclipse.epf.library.edit.process.command.DeleteTaskDescriptor;
import org.eclipse.epf.library.edit.process.command.DeleteTeamProfile;
import org.eclipse.epf.library.edit.process.command.DeleteUnusedDescriptorsCommand;
import org.eclipse.epf.library.edit.process.command.DeleteWorkProductDescriptor;
import org.eclipse.epf.library.edit.process.command.ProcessElementDeleteCommand;
import org.eclipse.epf.library.edit.process.command.RemoveUnusedDescriptorsCommand;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Implements the delete action for the process elements.
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ProcessDeleteAction extends MethodElementDeleteAction {

	private boolean deletionConfirmed = false;

	private HashSet<Descriptor> removedDescriptors;

	/**
	 * Creates a new instance
	 */
	public ProcessDeleteAction() {
		super();
	}

	/**
	 * Creates a new instance
	 * 
	 * @param domain
	 *            the editing domain
	 */
	public ProcessDeleteAction(EditingDomain domain) {
		super(domain);
	}

	/**
	 * Creates a new instance
	 * 
	 * @param domain
	 *            the editing domain
	 * @param confirm
	 *            if <code>true</code>, displays a prompt to ask for user
	 *            confirmation
	 */
	public ProcessDeleteAction(EditingDomain domain, boolean confirm) {
		super(domain, confirm);
	}

	/**
	 * @see MethodElementDeleteAction#createMethodElementDeleteCommand()
	 */
	public DeleteMethodElementCommand createMethodElementDeleteCommand() {
		return new ProcessElementDeleteCommand(RemoveCommand.create(domain,
				selection), selection, confirm);
	}

	/**
	 * Runs the delete action given the action manager
	 * 
	 * @param actionMgr
	 *            the action manager
	 */
	public void run(IActionManager actionMgr) {
		if (confirmDelete()) {
			for (Iterator itor = selection.iterator(); itor.hasNext();) {
				// remove references
				removeReferences(actionMgr, itor.next());
			}

			// delete element
			domain.getCommandStack().execute(command);
		}
	}

	protected void saveCurrentEditor() {
	}

	/**
	 * @see MethodElementDeleteAction#getDeleteConfirmationMessage()
	 */
	protected String getDeleteConfirmationMessage() {
		return LibraryUIResources.ProcessDeleteAction_deletecofirm_text; 
	}

	protected Command createDeleteUnusedDescriptorsCommand(Collection<Descriptor> descriptors, Collection<?> deletedElements) {
		return new DeleteUnusedDescriptorsCommand(
				descriptors, false, deletedElements) {

			protected Command delete(List elements) {
				return ProcessDeleteAction.delete(elements);
			}

			// protected void selectDescriptorsToDelete(List
			// descriptorsToDelete) {
			// String msg =
			// AuthoringUIResources.ProcessDeleteAction_selectDescriptorsToDelete;
			// Object[] descriptors =
			// ReferenceSelection.getReferences(descriptorsToDelete,
			// msg);
			// descriptorsToDelete.retainAll(Arrays.asList(descriptors));
			// }
		};
	}
	
	/**
	 * @see MethodElementDeleteAction#run()
	 */
	public void run() {
		if (confirmDelete()) {
			// collection the related descriptors before the elements got
			// deleted
			//
			Collection<Descriptor> descriptors = new HashSet<Descriptor>();
			for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				addRelatedDescriptors(descriptors, element);
			}

			Command cmd = command;

			// delete elements
			//
			cmd.execute();

			// Check reference confirmation and if confirmed set
			// deletionconfirmed to true
			// If any deletion happens in sequence, will check for
			// deletionconfirmed variable
			// and proceeds remaining deletion sequence. (eg:
			// AbstractDiagramEditor - DeleteAction()).
			if (((DeleteMethodElementCommand) cmd).executed) {
				deletionConfirmed = true;

				Collection<?> deletedElements = cmd.getResult();
				Command deleteUnusedDescriptorsCommand = createDeleteUnusedDescriptorsCommand(descriptors, deletedElements);
				try {
					deleteUnusedDescriptorsCommand.execute();
				} catch (OperationCanceledException e) {
					//
				} finally {
					deleteUnusedDescriptorsCommand.dispose();
				}
			}

			// save the current editor
			//
			saveCurrentEditor();
		}
	}

	private static void addRelatedDescriptors(
			Collection<Descriptor> descriptors, Object element) {
		if (element instanceof TaskDescriptor) {
			TaskDescriptor taskDesc = (TaskDescriptor) element;
			descriptors.addAll(taskDesc.getAdditionallyPerformedBy());
			descriptors.addAll(taskDesc.getAssistedBy());
			descriptors.addAll(taskDesc.getPerformedPrimarilyBy());
			descriptors.addAll(taskDesc.getMandatoryInput());
			descriptors.addAll(taskDesc.getExternalInput());
			descriptors.addAll(taskDesc.getOptionalInput());
			descriptors.addAll(taskDesc.getOutput());
		} else if (element instanceof RoleDescriptor) {
			RoleDescriptor roleDesc = (RoleDescriptor) element;
			descriptors.addAll(AssociationHelper
					.getAssistedTaskDescriptors(roleDesc));
			descriptors.addAll(AssociationHelper
					.getAdditionalTaskDescriptors(roleDesc));
			descriptors.addAll(AssociationHelper
					.getPrimaryTaskDescriptors(roleDesc));
			descriptors.addAll(roleDesc.getResponsibleFor());
		}
	}

	private void removeReferences(IActionManager actionMgr, Object obj) {
		IResourceAwareCommand cmd = null;
		boolean force = !confirm;
		if (obj instanceof TaskDescriptor) {
			cmd = new DeleteTaskDescriptor((TaskDescriptor) obj, force);
		} else if (obj instanceof RoleDescriptor) {
			cmd = new DeleteRoleDescriptor((RoleDescriptor) obj, force);
		} else if (obj instanceof WorkProductDescriptor) {
			cmd = new DeleteWorkProductDescriptor((WorkProductDescriptor) obj,
					force);
		} else if (obj instanceof TeamProfile) {
			cmd = new DeleteTeamProfile((TeamProfile) obj);
		}
		if (cmd != null) {
			if (actionMgr != null) {
				actionMgr.execute(cmd);

			} else {
				cmd.execute();
			}
			if (cmd instanceof RemoveUnusedDescriptorsCommand) {
				removedDescriptors
						.addAll(((RemoveUnusedDescriptorsCommand) cmd)
								.getRemovedDescriptors());
			}
		}
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		boolean ret = super.updateSelection(filterSelection(selection));
		return ret;
	}

	/**
	 * Filters selection.
	 * 
	 * @param selection
	 * @return New Selection
	 */
	public static IStructuredSelection filterSelection(
			IStructuredSelection selection) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof BreakdownElementWrapperItemProvider
					&& ((BreakdownElementWrapperItemProvider) element)
							.isReadOnly()) {
				continue;
			} else {
				list.add(element);
			}
		}

		return new StructuredSelection(list);
	}

	/**
	 * If deletion confirmed, set confirmation and child process can use the
	 * confirmation to proceed for the deletion of remaining.
	 * 
	 * @return boolean
	 */
	public boolean isDeletionConfirmed() {
		return deletionConfirmed;
	}

	/**
	 * Return command
	 * 
	 * @return cmd
	 */
	protected Command getCommand() {
		return command;
	}

	/**
	 * Convenience method to delete process elements without UI action
	 * 
	 * @param elements
	 * @return the executed delete command if some of the given elements have
	 *         been successfully deleted, <code>null</code> otherwise
	 * @exception OperationCanceledException
	 *                if user canceled this deletion operation
	 */
	public static Command delete(Collection elements) {
		ProcessDeleteAction deleteAction = new ProcessDeleteAction(null, false) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.epf.authoring.ui.actions.MethodElementDeleteAction#createCommand(java.util.Collection)
			 */
			public Command createCommand(Collection selection) {
				domain = new AdapterFactoryEditingDomain(
						TngAdapterFactory.INSTANCE
								.getProcessComposedAdapterFactory(),
						new BasicCommandStack());

				return super.createCommand(selection);
			}

		};
		if (deleteAction.updateSelection(new StructuredSelection(new ArrayList(
				elements)))) {
			deleteAction.run();
			if (!deleteAction.isDeletionConfirmed()) {
				throw new OperationCanceledException();
			}
			return deleteAction.getCommand();
		}
		return null;
	}

}