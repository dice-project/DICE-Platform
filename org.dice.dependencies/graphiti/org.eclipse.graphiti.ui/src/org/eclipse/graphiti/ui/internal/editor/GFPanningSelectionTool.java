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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.gef.tools.PanningSelectionTool;

/**
 * A subclass of the PanningSelectionTool comprising a marquee selection of
 * shapes ignoring ghost shapes.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPanningSelectionTool extends PanningSelectionTool {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gef.tools.SelectionTool#setDragTracker(org.eclipse.gef.
	 * DragTracker)
	 */
	@Override
	public void setDragTracker(DragTracker newDragTracker) {

		if (newDragTracker != null & newDragTracker instanceof MarqueeDragTracker) {
			// pass in drag tracker which ignores ghost shapes
			super.setDragTracker(new GFMarqueeDragTracker());

		} else {
			//pass thru null or (DragEditPartsTracker) newDragTracker for selection and dragging of shapes
			super.setDragTracker(newDragTracker);
		}
	}

}
