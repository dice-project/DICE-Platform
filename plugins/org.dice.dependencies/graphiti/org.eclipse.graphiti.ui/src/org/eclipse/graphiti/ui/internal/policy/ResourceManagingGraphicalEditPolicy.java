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
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.graphiti.ui.internal.IDisposable;
import org.eclipse.graphiti.ui.internal.IResourceRegistry;
import org.eclipse.graphiti.ui.internal.ResourceRegistry;
import org.eclipse.swt.graphics.Color;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class ResourceManagingGraphicalEditPolicy extends GraphicalEditPolicy {

	private IResourceRegistry resourceRegistry = new ResourceRegistry();

	public ResourceManagingGraphicalEditPolicy() {
		super();
	}

	protected void disposeFigure(IFigure figure) {
		if (figure instanceof IDisposable) {
			IDisposable d = (IDisposable) figure;
			d.dispose();
		}
	}

	protected Color manageColor(Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		Color ret = getResourceRegistry().getSwtColor(red, green, blue);
		color.dispose();
		return ret;
	}

	protected IResourceRegistry getResourceRegistry() {
		return resourceRegistry;
	}
}