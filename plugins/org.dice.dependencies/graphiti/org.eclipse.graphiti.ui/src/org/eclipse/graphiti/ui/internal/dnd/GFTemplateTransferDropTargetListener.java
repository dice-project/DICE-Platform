/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;

/**
 * This subclass of TemplateTransferDropTargetListener
 * {@link org.eclipse.gef.dnd.TemplateTransferDropTargetListener} was introduced
 * to change the default behavior. For details on this see csn-message
 * 0120031469 0001576030 2008
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	public GFTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
		// it is important to set this value to true
		setEnablementDeterminedByCommand(true);
	}

}
