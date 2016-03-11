//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.gef.edit;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.gef.editpolicies.DirectEditPolicy;

/**
 * Provides support for creating figure for {@link TypedNode.START}
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class StartNodeEditPart extends NodeEditPart {

	public StartNodeEditPart(TypedNode model) {
		super(model);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createDirectEditPolicy()
	 */
	protected DirectEditPolicy createDirectEditPolicy() {
		return null;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		Ellipse f = new Ellipse();
		f.setBackgroundColor(ColorConstants.black);
		f.setForegroundColor(ColorConstants.red);
		f.setSize(20, 20);
		return f;
	}

}
