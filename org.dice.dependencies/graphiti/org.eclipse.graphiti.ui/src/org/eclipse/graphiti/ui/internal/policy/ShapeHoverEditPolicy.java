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
package org.eclipse.graphiti.ui.internal.policy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.graphiti.platform.ga.IVisualState;
import org.eclipse.graphiti.platform.ga.IVisualStateHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * Gives feedback on hovering. It does not create any commands.
 * 
 * @see org.eclipse.graphiti.ui.internal.policy.IEditPolicyFactory#createShapeHighlightEditPolicy()
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ShapeHoverEditPolicy extends GraphicalEditPolicy {

	private IConfigurationProvider _configurationProvider;

	/**
	 * Creates a new ShapeHighlightEditPolicy.
	 * 
	 * @param configurationProvider
	 *            The IConfigurationProvider.
	 */
	protected ShapeHoverEditPolicy(IConfigurationProvider configurationProvider) {
		_configurationProvider = configurationProvider;
	}

	protected final IConfigurationProvider getConfigurationProvider() {
		return _configurationProvider;
	}

	/**
	 * Returns a _target EditPart only, if the request is REQ_SELECTION_HOVER.
	 * By this it filters all other requests out (note, that this EditPolicy
	 * does not overwrite getCommand(), which is usually used to filter the
	 * requests).
	 * 
	 * @see org.eclipse.gef.EditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	@Override
	public EditPart getTargetEditPart(Request request) {
		return request.getType().equals(RequestConstants.REQ_SELECTION_HOVER) ? getHost() : null;
	}

	/**
	 * Is called, when the previously 'selected' EditPart is 'deselected'. It
	 * then disables the highlight of the selected EditPart.
	 * 
	 * @see org.eclipse.gef.EditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	@Override
	public void eraseTargetFeedback(Request request) {
		IFigure containerFigure = ((GraphicalEditPart) getHost()).getFigure();
		if (containerFigure instanceof IVisualStateHolder) {
			IVisualStateHolder feedbackRenderer = (IVisualStateHolder) containerFigure;
			IVisualState vs = feedbackRenderer.getVisualState();
			vs.setHoverFeedback(IVisualState.HOVER_OFF);
		}
	}

	/**
	 * Is called when the EditPart is 'selected'. It then checks, if the request
	 * is one of those, which can be handled by the selected EditPart (which
	 * means that EditPart.getCommand(request) returns an executeable command.
	 * In this case it highlights the selected EditPart.
	 * 
	 * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	@Override
	public void showTargetFeedback(Request request) {
		if (request.getType().equals(RequestConstants.REQ_SELECTION_HOVER)) {
			IFigure containerFigure = ((GraphicalEditPart) getHost()).getFigure();
			if (containerFigure instanceof IVisualStateHolder) {
				IVisualStateHolder feedbackRenderer = (IVisualStateHolder) containerFigure;
				IVisualState vs = feedbackRenderer.getVisualState();
				vs.setHoverFeedback(IVisualState.HOVER_ON);
			}
		}
	}

}