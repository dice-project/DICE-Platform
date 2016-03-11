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
package org.eclipse.epf.authoring.ui.forms;

import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Interface to be used add columns on description tab of all editors
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 */
public interface IColumnProvider   {


	/**
	 * Set column composite for the given method element editor
	 * @param toolkit
	 * @param parent
	 * @param element
	 * @return
	 */
	public Composite setColumn(MethodElementEditor editor, FormToolkit toolkit, Composite parent);
	
	/**
	 * Refresh state of all controls
	 * @param value
	 */
	public void refresh(boolean value);
	
	
	/**
	 * Dispose the control
	 */
	public void dispose();
}
