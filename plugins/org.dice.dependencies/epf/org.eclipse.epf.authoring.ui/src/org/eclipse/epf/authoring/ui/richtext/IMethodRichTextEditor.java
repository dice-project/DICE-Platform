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
package org.eclipse.epf.authoring.ui.richtext;

import org.eclipse.epf.richtext.IRichTextEditor;
import org.eclipse.epf.richtext.IRichTextToolBar;

/**
 * The interface for a Rich Text editor used in the Method editors.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IMethodRichTextEditor extends IMethodRichText, IRichTextEditor {

	/**
	 * Fills the Rich Text editor tool bar with action items.
	 * 
	 * @param toolBar
	 *            The Rich text editor tool bar.
	 */
	public void fillToolBar(IRichTextToolBar toolBar);
	
	/**
	 * Selects the Rich Text or HTML tab.
	 * 
	 * @param index
	 *            0 for the Rich Text tab, 1 for the HTML tab.
	 */
	public void setSelection(int index);	
	
	
	/**
	 * Method rich text editor collapsed.
	 */
	public void collapse();

}
