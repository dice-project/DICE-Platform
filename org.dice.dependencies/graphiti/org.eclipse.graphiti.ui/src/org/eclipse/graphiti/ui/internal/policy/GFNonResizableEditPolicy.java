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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.util.draw2d.GFHandleHelper;
import org.eclipse.graphiti.ui.internal.util.draw2d.TransparentGhostFigure;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFNonResizableEditPolicy extends NonResizableEditPolicy {
	private GFEditPolicyDelegate delegate;

	public GFNonResizableEditPolicy(IConfigurationProvider cfgProvider) {
		setDelegate(new GFEditPolicyDelegate(cfgProvider));
	}

	private IConfigurationProvider getConfigurationProvider() {
		return getDelegate().getConfigurationProvider();
	}

	private GFEditPolicyDelegate getDelegate() {
		return delegate;
	}

	private void setDelegate(GFEditPolicyDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	protected IFigure createDragSourceFeedbackFigure() {
		IFigure feedback = new TransparentGhostFigure(getHostFigure(), 70, getConfigurationProvider().getDiagramEditor().getZoomLevel());
		addFeedback(feedback);
		return feedback;
	}

	@Override
	protected List<?> createSelectionHandles() {
		GraphicalEditPart owner = (GraphicalEditPart) getHost();
		List<?> list = GFHandleHelper.createShapeHandles(owner, getConfigurationProvider(), 0, isDragAllowed());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#hideSelection()
	 */
	@Override
	protected void hideSelection() {
		getDelegate().hideSelection(getHostFigure());
		removeSelectionHandles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.SelectionEditPolicy#showPrimarySelection()
	 */
	@Override
	protected void showPrimarySelection() {
		getDelegate().showPrimarySelection(getHostFigure());
		addSelectionHandles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#showSelection()
	 */
	@Override
	protected void showSelection() {
		getDelegate().showSelection(getHostFigure());
		addSelectionHandles();
	}

	@Override
	public void eraseSourceFeedback(Request request) {
		/* dispose ghosts */
		@SuppressWarnings("unchecked")
		List<IFigure> children = getFeedbackLayer().getChildren();
		for (IFigure child : children) {
			if (child instanceof TransparentGhostFigure) {
				((TransparentGhostFigure) child).dispose();
			}
		}
		super.eraseSourceFeedback(request);
	}
}
