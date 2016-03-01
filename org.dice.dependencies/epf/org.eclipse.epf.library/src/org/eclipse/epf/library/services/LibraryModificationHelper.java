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
package org.eclipse.epf.library.services;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.command.ActionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.ILibraryPersister.FailSafeMethodLibraryPersister;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.osgi.util.NLS;

/**
 * This helper is using an instance of IActionManager to keep track of changes.
 * Clien must {@link #dispose() <em>dispose</em>} this helper after use.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LibraryModificationHelper {

	ActionManager actionMgr = null;

	/**
	 * constructor
	 *
	 */
	public LibraryModificationHelper() {
	}

	/**
	 * get the action manager
	 * @return ActionManager
	 */
	public ActionManager getActionManager() {
		if (actionMgr != null) {
			return actionMgr;
		}

		actionMgr = new ActionManager() {
			public boolean doAction(int actionType, EObject object,
					EStructuralFeature feature, Object value, int index) {
				if (canUpdate(object)) {
					return super.doAction(actionType, object, feature, value,
							index);
				}
				return false;
			}

			protected void save(Resource resource) {
			}
		};

		return actionMgr;
	}

	/**
	 * dispose
	 *
	 */
	public void dispose() {
		if (actionMgr != null) {
			actionMgr.dispose();
			actionMgr = null;
		}
	}
	
	/**
	 * check if update is allowed
	 * @param object
	 * @return boolean
	 */
	public static boolean canUpdate(EObject object) {
		return canUpdate(object, null);
	}
	
	public static boolean canUpdate(EObject object, Object context) {
		IStatus status = TngUtil.checkEdit(object, context);
		if (status.isOK()) {
			return true;
		} else {
			String msg = NLS.bind(LibraryResources.LibraryModificationHelper_cannotUpdate, TngUtil.getTypeText(object), object
			.eGet(UmaPackage.eINSTANCE.getNamedElement_Name()));
			LibraryPlugin.getDefault().getMsgCallback().displayWarning(LibraryPlugin.getDefault(),
					LibraryResources.warningDlg_title
					, msg, TngUtil.getMessage(status));

			return false;
		}
	}

	

	/**
	 * check if save is needed
	 * @return boolean
	 */
	public boolean isSaveNeeded() {
		return (actionMgr != null) && actionMgr.isSaveNeeded();
	}

	/**
	 * do save
	 *
	 */
	public void save() {
		if (actionMgr == null) {
			return;
		}

		try {			
			Collection modifiedResources = actionMgr.getModifiedResources();
			FailSafeMethodLibraryPersister persister = getPersister(modifiedResources);
			for (Iterator iter = modifiedResources.iterator(); iter.hasNext();) {
				Resource resource = (Resource) iter.next();
				try {
					persister.save(resource);
				} catch (Exception e) {
					String msg = NLS.bind(LibraryResources.errorDlg_saveError
							, resource.getURI().isFile() ? resource.getURI().toFileString() : resource.getURI().toString()); 
					LibraryPlugin.getDefault().getMsgCallback().displayError(LibraryPlugin.getDefault(),
							LibraryResources.errorDlg_title
							, msg, e);
				}
			}

			try {
				persister.commit();
				actionMgr.saveIsDone();
			} catch (Exception e) {
				try {
					persister.rollback();
				} catch (Throwable th) {
					try {
						// if rollback failed, reload the library
						LibraryService.getInstance()
								.reopenCurrentMethodLibrary();
					} catch (RuntimeException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FailSafeMethodLibraryPersister getPersister(Collection resources) {
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			ResourceSet resourceSet = resource.getResourceSet();
			if(resourceSet instanceof ILibraryResourceSet) {
				ILibraryPersister persister = ((ILibraryResourceSet)resourceSet).getPersister();
				if(persister != null) {
					return persister.getFailSafePersister();
				}
			}
		}
		return null;
	}

	public static class CheckActionManager extends ActionManager {
		
		private Object context;

		public CheckActionManager(Object context) {
			this.context = context;
		}
		
		public boolean doAction(int actionType, EObject object,
				EStructuralFeature feature, Object value, int index) {
			if (canUpdate(object, context)) {
				return super.doAction(actionType, object, feature, value,
						index);
			}
			return false;
		}

	}
}
