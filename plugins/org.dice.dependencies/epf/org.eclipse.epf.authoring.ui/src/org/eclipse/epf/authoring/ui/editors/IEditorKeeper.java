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

/**
 * An interface which manages opening/closing of an editor
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IEditorKeeper {

	/**
	 * Reference class for editor keeper
	 *
	 */
	public static class Reference {
		private IEditorKeeper editorKeeper;

		private Reference() {

		}

		public void setEditorKeeper(IEditorKeeper editorKeeper) {
			this.editorKeeper = editorKeeper;
		}

		public IEditorKeeper getEditorKeeper() {
			return editorKeeper;
		}
	}

	public static final Reference REFERENCE = new Reference();

	/**
	 * Open an editor for the given element
	 * @param object
	 */
	void openEditor(Object object);

	/**
	 * Closes all open editors of the specified (deleted) element and
	 * all of its children and grand children.
	 * 
	 * @param element
	 */
	void closeEditorsOnDeletion(Object element);
	
	/**
	 * Closes all open editors of the specified element and all of its children
	 * and grand children.
	 * 
	 * @param element
	 * @param promptSave
	 *            if true and the editor is dirty, will prompt user to save
	 */
	void closeEditors(Object element, boolean promptSave);
}
