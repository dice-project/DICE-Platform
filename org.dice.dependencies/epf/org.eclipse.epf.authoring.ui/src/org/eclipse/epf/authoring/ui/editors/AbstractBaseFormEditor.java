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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.ui.editors.IMethodEditor;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * The abstract base class for all method editors.
 * 
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class AbstractBaseFormEditor extends FormEditor implements IMethodEditor {

	// Global map used to record which editor modifies the shared resource
	// first.
	// This enables the appropriate editors to be flagged as dirty.
	protected static final Map SHARED_RESOURCE_TO_EDITORS_MAP = new HashMap();

	// If true, debug tracing is enabled.
	protected static final boolean DEBUG = AuthoringUIPlugin.getDefault()
			.isDebugging();

	// Collection of resources associated with the model data being edited by
	// this editor.
	protected Collection resources;

	/**
	 * Creates a new instance.
	 */
	public AbstractBaseFormEditor() {
		super();
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	protected abstract void addPages();

	/**
	 * @see org.eclipse.ui.ISaveablePart#doSave(IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		try {
			LibraryService.getInstance().saveCurrentMethodLibrary();
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(
					"Error saving library:", e); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#doSaveAs()
	 */
	public void doSaveAs() {
	}

	/**
	 * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * @see org.eclipse.ui.part.MultiPageEditorPart#isDirty()
	 */
	public boolean isDirty() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		return manager != null ? manager.isMethodLibraryModified() : false;
	}

	/**
	 * Disposes the operating system resources allocated by this editor.
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * Updates the editor's dirty state by adding a "*" to the editor tab and
	 * enabling the Save button.
	 */
	public void updateDirtyStatus() {
		super.editorDirtyStateChanged();
	}

}
