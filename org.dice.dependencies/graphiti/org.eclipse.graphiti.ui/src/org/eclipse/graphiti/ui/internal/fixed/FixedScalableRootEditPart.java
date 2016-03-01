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
package org.eclipse.graphiti.ui.internal.fixed;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.ScalableLayeredPane;
import org.eclipse.gef.editparts.ScalableRootEditPart;

/**
 * The Class FixedScalableRootEditPart.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FixedScalableRootEditPart extends ScalableRootEditPart {

	/**
	 * Creates a scalable layered pane and the layers that should be scaled.
	 * 
	 * @return a new <code>ScalableLayeredPane</code> containing the scalable
	 *         layers
	 */
	@Override
	protected ScalableLayeredPane createScaledLayers() {
		ScalableLayeredPane layers = new ScalableLayeredPane();
		layers.add(createGridLayer(), GRID_LAYER);
		layers.add(getPrintableLayers(), PRINTABLE_LAYERS);
		layers.add(new FeedbackLayer(), SCALED_FEEDBACK_LAYER);
		return layers;
	}

	/**
	 * The Class FeedbackLayer.
	 */
	class FeedbackLayer extends FreeformLayer {

		/**
		 * Instantiates a new feedback layer.
		 */
		FeedbackLayer() {
			setEnabled(false);
		}
	}
}
