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
/*
 * Created on 27.09.2005
 *


 */
package org.eclipse.graphiti.ui.internal.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * The Class ManhattanConnectionEditPart.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ManhattanConnectionEditPart extends ConnectionEditPart {

	/**
	 * The Constructor.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param connection
	 *            the connection
	 */
	public ManhattanConnectionEditPart(IConfigurationProvider configurationProvider, Connection connection) {
		super(configurationProvider, connection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	public IFigure createFigure() {
		IFigure ret = super.createFigure();
		if (ret instanceof org.eclipse.draw2d.Connection) {
			org.eclipse.draw2d.Connection draw2dConnection = (org.eclipse.draw2d.Connection) ret;
			draw2dConnection.setConnectionRouter(new ManhattanConnectionRouter());
		}
		return ret;
	}

}