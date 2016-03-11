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

/**
 * Drag tracker to promote GFMarqueeSelectionTool.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFMarqueeDragTracker extends GFMarqueeSelectionTool implements DragTracker {

	/**
	 * Called when the mouse button is released. Overridden to do nothing, since
	 * a drag tracker does not need to unload when finished.
	 */
	@Override
	protected void handleFinished() {
	}
}
