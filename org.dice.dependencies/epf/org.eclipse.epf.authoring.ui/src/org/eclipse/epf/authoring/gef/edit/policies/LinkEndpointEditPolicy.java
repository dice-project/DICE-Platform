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
package org.eclipse.epf.authoring.gef.edit.policies;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

/**
 * A EditPolicy to handle the selection handles for connection. 
 *  
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LinkEndpointEditPolicy extends ConnectionEndpointEditPolicy {

	private static Font BOLD_FONT;

	protected void addSelectionHandles() {
		super.addSelectionHandles();
		getConnectionFigure().setLineWidth(2);
	}

	protected PolylineConnection getConnectionFigure() {
		return (PolylineConnection) getHostFigure();
	}

	protected void hideSelection() {
		super.hideSelection();
		getHostFigure().setFont(null);
		getHostFigure().invalidateTree();
	}

	protected void removeSelectionHandles() {
		super.removeSelectionHandles();
		getConnectionFigure().setLineWidth(0);
	}

	protected void showSelection() {
		super.showSelection();
		if (BOLD_FONT == null) {
			FontData[] data = getConnectionFigure().getFont().getFontData();
			for (int i = 0; i < data.length; i++)
				if ((data[i].getStyle() & SWT.BOLD) == 0)
					data[i].setStyle(data[i].getStyle() | SWT.BOLD);
			BOLD_FONT = new Font(null, data);
		}
		getHostFigure().setFont(BOLD_FONT);
		getHostFigure().invalidateTree();
	}

}