/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.gef.ui.actions;

import org.eclipse.ui.actions.RetargetAction;

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.internal.InternalImages;

/**
 * @author danlee
 */
public class ZoomOutRetargetAction extends RetargetAction {

	/**
	 * Constructor for ZoomInRetargetAction
	 */
	public ZoomOutRetargetAction() {
		super(null, null);
		setText(GEFMessages.ZoomOut_Label);
		setId(GEFActionConstants.ZOOM_OUT);
		setToolTipText(GEFMessages.ZoomOut_Tooltip);
		setImageDescriptor(InternalImages.DESC_ZOOM_OUT);
		setActionDefinitionId(GEFActionConstants.ZOOM_OUT);
	}

}
