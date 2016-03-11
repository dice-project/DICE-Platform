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
package org.eclipse.epf.authoring.ui.providers;

import java.util.Map;

import org.eclipse.ui.forms.editor.FormEditor;

/**
 * Interface to be used for all pages added through extension points
 * 
 * @author Shilpa Toraskar
 * @author Jeff Hardy
 * @since 1.0
 */
public interface IMethodElementEditorPageProviderExtension {


	/**
	 * Returns a map of pages to names.
	 * Pages should be Control, IFormPage, or IEditorPart
	 * Names are the partName (for the IEditorPart or Control -- IFormPages do not need to provide a name in the map).
	 * 
	 * @param pageMap map of pages to names
	 * @param editor editor that pages are added to
	 * @param input object being edited
	 * @return a map
	 *  
	 */
	public Map<Object,String> getPages(Map<Object,String> pageMap, FormEditor editor, Object input);

	
	

}
