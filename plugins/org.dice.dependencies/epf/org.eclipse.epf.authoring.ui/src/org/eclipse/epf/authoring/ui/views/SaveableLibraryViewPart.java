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
package org.eclipse.epf.authoring.ui.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.part.ViewPart;

/**
 * The abstract class for all views that requires the support of saving a Method
 * Library via the Save action.
 * 
 * @author Phong Le
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class SaveableLibraryViewPart extends ViewPart implements
		ISaveablePart {

	/**
	 * @see org.eclipse.ui.ISaveablePart#doSave(IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		if (monitor != null) {
			monitor
					.beginTask(
							AuthoringUIResources.savingLibraryTask_name, IProgressMonitor.UNKNOWN); 
			String libPath = LibraryService.getInstance()
					.getCurrentMethodLibraryLocation();
			try {
				LibraryService.getInstance().saveCurrentMethodLibrary();
				firePropertyChange(IEditorPart.PROP_DIRTY);
			} catch (Exception e) {
				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								AuthoringUIResources.saveLibraryDialog_title, 
								AuthoringUIResources.bind(AuthoringUIResources.saveLibraryToError_msg, libPath), 
								AuthoringUIResources.error_reason, 
								e);
			} finally {
				monitor.done();
			}
		}
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isDirty()
	 */
	public boolean isDirty() {
		return false;
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isSaveOnCloseNeeded()
	 */
	public boolean isSaveOnCloseNeeded() {
		return true;
	}

}
