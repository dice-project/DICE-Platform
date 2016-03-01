//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.add.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.draw2d.Connection;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.epf.diagram.add.edit.policies.LinkItemSemanticEditPolicy;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.epf.diagram.core.util.DiagramCoreValidation;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class LinkEditPart extends ConnectionNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3001;

	/**
	 * @generated
	 */
	public LinkEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new LinkItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected Connection createConnectionFigure() {

		return new LinkFigure();
	}

	/**
	 * @generated
	 */
	public class LinkFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public LinkFigure() {
			this.setForegroundColor(ColorConstants.black

			);
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private PolylineDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			// dispatchNext?

			PointList pl = new PointList();
			pl.addPoint(-1, 1);
			pl.addPoint(0, 0);
			pl.addPoint(-1, -1);
			df.setTemplate(pl);
			df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));

			return df;
		}

	}

	@Override
	protected void setForegroundColor(Color color) {
		Color overrideColor = DiagramCoreUtil.getLinkOverrideColor((Edge) getModel());
		super.setForegroundColor(overrideColor != null ? overrideColor : color);
	}

	@Override
	public void deactivate() {
		super.deactivate();
	}

	@Override
	protected void setVisibility(boolean vis) {
		super.setVisibility(vis);
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
	}
}
