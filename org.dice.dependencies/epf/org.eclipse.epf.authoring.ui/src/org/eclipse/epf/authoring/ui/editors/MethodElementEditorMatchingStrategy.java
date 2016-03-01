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
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

/**
 * implements IEditorMatchingStrategy for MethodElementEditor
 * @author Jeff Hardy
 *
 */
public class MethodElementEditorMatchingStrategy implements IEditorMatchingStrategy {

	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		// inspect input
		MethodElement inputElement = getMethodElementFromInput(input);
		if (inputElement == null) {
			// no MethodElement in incoming input
			return false;
		}
		
        IEditorInput existingEditorInput;
        IEditorPart editor = editorRef.getEditor(false);
        
        if (!(editor instanceof MethodElementEditor)) {
        	// existing editor is not a MethodElementEditor
        	return false;
        }

        try {
            existingEditorInput = editorRef.getEditorInput();
        } catch (PartInitException e) {
            return false;
        }
        
        MethodElement existingEditorInputElement = getMethodElementFromInput(existingEditorInput);
		if (existingEditorInputElement == null) {
			// no MethodElement in existing editor
			return false;
		}
        
        if (existingEditorInputElement.equals(inputElement)) {
        	// editor is editing the MethodElement represented by the input
        	return true;
        }
        
        return false;
	}
	
	private MethodElement getMethodElementFromInput(IEditorInput input) {
		MethodElement inputElement = null;
		if (input instanceof FileEditorInput) {
			try {
				FileEditorInput finput = (FileEditorInput) input;
				if (finput.getFile().getName().endsWith(".xmldef")) {//$NON-NLS-1$
					return null;
				}
			} catch (Exception e) {
				return null;
			}
			// probably opened from Problems View
			inputElement = PersistenceUtil.getMethodElement(((FileEditorInput)input).getFile(),
					LibraryService.getInstance().getCurrentLibraryManager().
					getEditingDomain().getResourceSet());
		}
		if (input instanceof MethodElementEditorInput) {
			inputElement = ((MethodElementEditorInput)input).getMethodElement();
		}
		return inputElement;
	}
}
