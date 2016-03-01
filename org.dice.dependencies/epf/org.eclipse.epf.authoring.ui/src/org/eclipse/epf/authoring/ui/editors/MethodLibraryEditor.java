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
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.forms.MethodLibraryDescriptionFormPage;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.persistence.MultiFileIOException;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

/**
 * The method library editor.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodLibraryEditor extends MethodElementEditor {
	
	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#setPartName()
	 */
	public void setPartName() {
		String partName = AuthoringUIResources.MethodLibraryEditor_title; 
		if (elementObj.getName() != null) {
			partName += elementObj.getName();
			;
		}
		setPartName(partName);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#addPages()
	 */
	protected void addPages() {
		try {
			addPage(new MethodLibraryDescriptionFormPage(this));
			createPreviewPage();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#dispose()
	 */
	public void dispose() {
		// Close all the editors.
		getSite().getPage().closeAllEditors(false);

		// Clear the Library View.
		LibraryView.getView().setInputForViewer(null);

		try {
			// Close the current method library.
			LibraryService.getInstance().closeCurrentMethodLibrary();
		} catch (Exception e) {
		}

		super.dispose();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		if (monitor != null) {
			monitor
					.beginTask(
							AuthoringUIResources.MethodLibraryEditor_savemodellibrary, IProgressMonitor.UNKNOWN); 

			try {
				LibraryService.getInstance().saveCurrentMethodLibrary();
				firePropertyChange(IEditorPart.PROP_DIRTY);
			} catch (Exception ex) {
				ex.printStackTrace();
				String errMsg = AuthoringUIResources.MethodLibraryEditor_err_save; 
				String errDetails = ""; //$NON-NLS-1$
				if (ex.getMessage() != null) {
					errDetails = ex.getMessage(); 
				}
				if (ex instanceof MultiFileIOException) {
					Object troubleObj = ((MultiFileIOException) ex)
							.getTroubleObject();
					if (troubleObj instanceof MethodElement) {
						errDetails = AuthoringUIResources.MethodLibraryEditor_troubleobject + MultiFileSaveUtil.getPath((MethodElement) troubleObj); 
					}
				}

				String title = AuthoringUIResources.MethodLibraryEditor_error_dialog_title; 
				String problem = AuthoringUIResources.MethodLibraryEditor_save_library_problem_msg; 
				MsgDialog dialog = AuthoringUIPlugin.getDefault()
						.getMsgDialog();
				dialog.displayError(title, problem, errMsg, errDetails); 
			} finally {
				monitor.done();
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#isDirty()
	 */
	public boolean isDirty() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		return manager != null ? manager.isMethodLibraryModified() : false;
	}

}
