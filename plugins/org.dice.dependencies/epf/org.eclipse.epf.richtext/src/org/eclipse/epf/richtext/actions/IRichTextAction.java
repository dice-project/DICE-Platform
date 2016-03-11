/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext.actions;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolItem;

/**
 * The interface for a rich text button action.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public interface IRichTextAction extends IBaseRichTextAction {

	/**
	 * Returns the image for the action.
	 * 
	 * @return the image for the action
	 */
	public Image getImage();

	/**
	 * Sets the image for the action.
	 * 
	 * @param image
	 *            the image for the action
	 */
	public void setImage(Image image);

	/**
	 * Returns the disabled image for the action.
	 * 
	 * @return the disabled image for the action
	 */
	public Image getDisabledImage();

	/**
	 * Sets the disabled image for the action.
	 * 
	 * @param image
	 *            the disabled image for the action
	 */
	public void setDisabledImage(Image image);

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in readonly mode.
	 */
	public boolean disableInReadOnlyMode();

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in source edit mode.
	 */
	public boolean disableInSourceMode();

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public void execute(IRichText richText);
	
	/**
	 * Sets the action's ToolItem
	 * @param toolItem
	 */
	public void setToolItem(ToolItem toolItem);
	
	/**
	 * Gets the SWT ToolItem Style to be used for this action
	 */
	public int getStyle();

}
