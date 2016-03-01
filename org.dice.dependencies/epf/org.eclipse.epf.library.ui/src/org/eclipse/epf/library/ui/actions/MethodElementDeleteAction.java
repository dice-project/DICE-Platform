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
package org.eclipse.epf.library.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.DeleteAction;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.operation.IRunnableWithProgress;


/**
 * Action to delete method element
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodElementDeleteAction extends DeleteAction implements ILibraryAction {
	protected Collection<?> selection = null;

	protected boolean confirm = true;

	private IProgressMonitor progressMonitor;

	/**
	 * Deletes method element
	 *
	 */
	public MethodElementDeleteAction() {
		super();
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param domain
	 */
	public MethodElementDeleteAction(EditingDomain domain) {
		super(domain);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param domain
	 * @param confirm
	 *            flag indicates whether user confirmation is required. If
	 *            <code>false</code>, executing this command will not
	 *            interact with user and the default behavior will apply.
	 */
	public MethodElementDeleteAction(EditingDomain domain, boolean confirm) {
		this(domain);
		this.confirm = confirm;
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.DeleteAction#createCommand(java.util.Collection)
	 */
	public Command createCommand(Collection<?> selection) {
		this.selection = selection;

		DeleteMethodElementCommand cmd = createMethodElementDeleteCommand();
		cmd
				.addCommandListener(new DeleteMethodElementCommand.CommandListener() {

					public void notifyExecuted(EventObject eventObject) {
						DeleteMethodElementCommand cmd = (DeleteMethodElementCommand) eventObject
								.getSource();
						didDelete(cmd.getResult());
					}

					public void notifyFailure(EventObject eventObject) {
						deleteFailed();
					}

				});
		return cmd;
	}

	protected void deleteFailed() {
	}

	/**
	 * Create a command to delete method element
	 * @return command
	 */
	public DeleteMethodElementCommand createMethodElementDeleteCommand() {
		return new DeleteMethodElementCommand(super.createCommand(selection),
				selection, confirm) {
			protected void loadOppositeFeatures(List oppositeFeatures,
					Set deletedGUIDs) {
				ILibraryResourceSet resourceSet = (ILibraryResourceSet) LibraryService
						.getInstance().getCurrentMethodLibrary().eResource()
						.getResourceSet();
				resourceSet
						.loadOppositeFeatures(oppositeFeatures, deletedGUIDs);
			}
		};
	}

	protected void didDelete(Collection<?> deletedElements) {

	}

	/**
	 * Get confirmation message to delete
	 * @return
	 */
	protected String getDeleteConfirmationMessage() {
		return LibraryUIResources.MethodElementDeleteAction_deleteconfirm_text; 
	}

	protected boolean confirmDelete() {
		if (!confirm) {
			return true;
		}

		if (selection == null || selection.isEmpty())
			return false;

		int i = 0;
		StringBuffer elementStr = new StringBuffer();

		for (Iterator<?> it = selection.iterator(); it.hasNext();) {
			Object obj = TngUtil.unwrap(it.next());
			if (obj instanceof MethodElement) {
				if (i > 0) {
					elementStr.append(", "); //$NON-NLS-1$
				}
				elementStr.append(getDisplayString((MethodElement)obj));

				i++;
			}
		}

		Object[] args = { elementStr.toString() };
		String msg = LibraryUIResources.bind(LibraryUIResources.confirmDeleteDialog_msg, args); // NON-NLS-1$

		String title = LibraryUIResources.confirmDeleteDialog_title; 
		boolean deleteOk = LibraryUIPlugin.getDefault().getMsgDialog()
				.displayConfirmation(title, msg);
		return deleteOk;
	}

	protected String getDisplayString(MethodElement element){
		return element.getName();
	}
	/**
	 * @see org.eclipse.epf.authoring.ui.actions.ILibraryAction#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		progressMonitor = monitor;
	}

	/**
	 * @return the progressMonitor
	 */
	protected IProgressMonitor getProgressMonitor() {
		if(progressMonitor == null) {
			progressMonitor = new NullProgressMonitor();
		}
		return progressMonitor;
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.setProgressMonitor(getProgressMonitor());
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				performDelete();
			}
			
		});
	}
	
	/**
	 * Performs delete
	 *
	 */
	protected void performDelete() {
		super.run();		
	}
}