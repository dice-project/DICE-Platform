//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Base for simple library action classes
 * @author Weiping Lu
 * @since  1.5
 */
public abstract class LibraryViewSimpleAction extends Action {

	private LibraryView libraryView;
	
	/**
	 * @param libView
	 * @param text
	 */
	public LibraryViewSimpleAction(LibraryView libView, String text) {
		super(text);
		libraryView = libView;
	}
	
	protected abstract void doRun();

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException,
					InterruptedException {
				doRun();
			}

		});
	}
	
	/**
	 * @param selection
	 * @return
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		return true;
	}

	protected LibraryView getLibraryView() {
		return libraryView;
	}
	
	protected Object getSelectionParentObject() {
		TreeItem[] selectedItems = getLibraryView().getTreeViewer().getTree().getSelection();
		if (selectedItems == null || selectedItems.length == 0) {
			return null;
		}
		TreeItem item = selectedItems[0].getParentItem();
		return TngUtil.unwrap(item.getData());
	}
	
	public static boolean save(Collection<Resource> resouresToSave) {
		ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
				.getCurrentPersister().getFailSafePersister();
		try {
			HashSet<Resource> seens = new HashSet<Resource>();
			for (Iterator<Resource> it = resouresToSave.iterator(); it.hasNext();) {
				MultiFileXMIResourceImpl res = (MultiFileXMIResourceImpl) it.next();
				if (! seens.contains(res)) {
					persister.save(res);
					seens.add(res);
				}
			}
			persister.commit();
		} catch (Exception e) {
			persister.rollback();
			return false;
		} finally {
		}
		
		return true;
	}
	
	public static abstract class CustomeCategoryAction extends LibraryViewSimpleAction {
		public CustomeCategoryAction(LibraryView libView, String text) {
			super(libView, text);
		}
		
		protected boolean checkModify() {
			IStructuredSelection selection = (IStructuredSelection) getLibraryView().getSelection();
			for (Iterator iter = selection.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof MethodElement
						|| (element = TngUtil.unwrap(element)) instanceof CustomCategory) {
					if (! checkModify(element)) {
						return false;
					}
				}
			}
			
			return true;
		}
				
		protected boolean checkModify(Object element) {
			if (element instanceof MethodElement
					|| (element = TngUtil.unwrap(element)) instanceof CustomCategory) {
				// Handle CustomCategory specially.
				EObject container = ((EObject) element).eContainer();
				IStatus status = UserInteractionHelper.checkModify(
						container, getLibraryView().getSite().getShell());
				if (container != null && !status.isOK()) {
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayError(
									AuthoringUIResources.errorDialog_title,
									AuthoringUIResources.errorDialog_moveError,
									status);
					return false;
				}
			}
			
			return true;
		}
		
	}
	
}
