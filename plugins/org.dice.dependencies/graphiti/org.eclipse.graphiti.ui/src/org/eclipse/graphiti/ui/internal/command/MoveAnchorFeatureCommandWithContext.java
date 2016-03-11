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
package org.eclipse.graphiti.ui.internal.command;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.internal.command.FeatureCommandWithContext;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MoveAnchorFeatureCommandWithContext extends FeatureCommandWithContext {

	public MoveAnchorFeatureCommandWithContext(IMoveAnchorFeature feature, IMoveAnchorContext context, GraphicalEditPart ep) {
		super(feature, context);
	}

	public boolean canExecute() {
		IMoveAnchorContext layoutAnchorContext = getLayoutAnchorContext();
		IMoveAnchorFeature layoutAnchorFeature = getLayoutAnchorFeature();
		return layoutAnchorFeature.canMoveAnchor(layoutAnchorContext);
	}

	public boolean execute() {
		IMoveAnchorFeature layoutAnchorFeature = getLayoutAnchorFeature();
		IMoveAnchorContext layoutAnchorContext = getLayoutAnchorContext();

		layoutAnchorFeature.preMoveAnchor(layoutAnchorContext);

		layoutAnchorFeature.moveAnchor(layoutAnchorContext);

		layoutAnchorFeature.postMoveAnchor(layoutAnchorContext);

		return true;
	}

	private IMoveAnchorFeature getLayoutAnchorFeature() {
		return (IMoveAnchorFeature) getFeature();
	}

	protected IMoveAnchorContext getLayoutAnchorContext() {
		IMoveAnchorContext ret = null;
		if (getContext() instanceof IMoveAnchorContext) {
			ret = (IMoveAnchorContext) getContext();
		}
		return ret;
	}
}