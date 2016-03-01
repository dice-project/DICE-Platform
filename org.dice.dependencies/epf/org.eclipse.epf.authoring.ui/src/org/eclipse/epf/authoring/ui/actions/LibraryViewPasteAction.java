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
package org.eclipse.epf.authoring.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.ui.action.PasteAction;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ActivityHandler;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.ui.actions.ILibraryAction;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;


/**
 * Pastes an element from the clipboard to the Library view.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=181559
 */
public class LibraryViewPasteAction extends PasteAction implements
		ILibraryAction {

	private IProgressMonitor progressMonitor;

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.setProgressMonitor(getProgressMonitor());
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				TraceableAdapterFactoryEditingDomain tDomain = null;
				if (domain instanceof TraceableAdapterFactoryEditingDomain) {
					tDomain = (TraceableAdapterFactoryEditingDomain) domain;
					tDomain.setExtenalMaintainedCopyMap(new HashMap<Object, Object>());
				}
				performPaste();
				if (tDomain != null) {					
					Set<Resource> resouresToSave = new HashSet<Resource>();			
					Map map = tDomain.getExtenalMaintainedCopyMap();
					
					LibraryEditUtil.getInstance().fixWpStates(map.keySet(), resouresToSave);					
					ActivityHandler.fixGuidReferences(map, false, true, resouresToSave);
					LibraryEditUtil.getInstance().fixProcssWpStates(map.keySet(), resouresToSave);
					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
							.getCurrentPersister().getFailSafePersister();

					try {
						for (Iterator<Resource> it = resouresToSave.iterator(); it
								.hasNext();) {
							MultiFileXMIResourceImpl res = (MultiFileXMIResourceImpl) it
									.next();
							res.setModified(true);
							persister.save(res);
						}
						persister.commit();

					} catch (Exception e) {
						persister.rollback();
						e.printStackTrace();

					} finally {
						if (tDomain != null) {
							tDomain.setExtenalMaintainedCopyMap(null);
						}
					}
				}
				if (tDomain != null) {
					tDomain.setExtenalMaintainedCopyMap(null);
				}
				
			}
			
		});
	}
	
	/**
	 * Perform paste operation
	 */
	protected void performPaste() {
		if(command instanceof PasteFromClipboardCommand) {
			PasteFromClipboardCommand cmd = ((PasteFromClipboardCommand)command);
			Object owner = cmd.getOwner();
			if(owner instanceof Adapter) {
				owner = ((Adapter)owner).getTarget();
			}
			
			// check target for modifiable
			//
			if(owner instanceof EObject) {
				IStatus status = UserInteractionHelper.checkModify((EObject) owner, MsgBox.getDefaultShell());
				if (!status.isOK()) {
					AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
							getText(), null, status);
					return;
				}
			}			
		}
		
		if (command == null)
			return;
		else
			super.run();
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		if(selection == null || selection.isEmpty() || selection.size() != 1)
			return false;
		else{
			Object object = selection.getFirstElement();
			if(object instanceof MethodPlugin)
				return false;
			if(object instanceof StandardCategoriesItemProvider)
				return false;
		}
		
		return super.updateSelection(selection);
	}
	
	/**
	 * Notify propery change with old and new value
	 * 
	 * @param propertyName
	 * 			property name for which value is going to change
	 * @param oldValue
	 * 			Old value of the property
	 * @param newValue
	 * 			New value of the property
	 */
	public void notifyPropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		super.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * @see org.eclipse.epf.library.ui.actions.ILibraryAction#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		progressMonitor = monitor;
	}

	/**
	 * Returns progress monitor
	 * @return the progressMonitor
	 */
	protected IProgressMonitor getProgressMonitor() {
		if(progressMonitor == null) {
			progressMonitor = new NullProgressMonitor();
		}
		return progressMonitor;
	}
}
