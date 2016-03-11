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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.add.edit.policies.DiagramTextSelectionEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.WorkProductCompositeCanonicalEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.WorkProductCompositeGraphicalNodeEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.WorkProductCompositeItemSemanticEditPolicy;
import org.eclipse.epf.diagram.core.figures.RaisedMarginBorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class WorkProductCompositeEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1002;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public WorkProductCompositeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new WorkProductCompositeItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new WorkProductCompositeCanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		ConstrainedToolbarLayoutEditPolicy lep = new ConstrainedToolbarLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				if (child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) == null) {
					if (child instanceof ITextAwareEditPart) {
						return new DiagramTextSelectionEditPolicy();
					}
				}
				return super.createChildEditPolicy(child);
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		WorkProductCompositeFigure figure = new WorkProductCompositeFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public WorkProductCompositeFigure getPrimaryShape() {
		return (WorkProductCompositeFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(40), getMapMode().DPtoLP(40));
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public class WorkProductCompositeFigure extends RectangleFigure {

		/**
		 * @modified
		 */
		public WorkProductCompositeFigure() {

			org.eclipse.draw2d.ToolbarLayout myGenLayoutManager = new org.eclipse.draw2d.ToolbarLayout();
			myGenLayoutManager.setStretchMinorAxis(false);
			myGenLayoutManager
					.setMinorAlignment(org.eclipse.draw2d.ToolbarLayout.ALIGN_CENTER);
			myGenLayoutManager.setSpacing(5);
			myGenLayoutManager.setVertical(true);

			this.setLayoutManager(myGenLayoutManager);

			this.setBackgroundColor(WORKPRODUCTCOMPOSITEFIGURE_BACK

			);
			//			this.setBorder(new org.eclipse.draw2d.MarginBorder(getMapMode()
			//					.DPtoLP(0), getMapMode().DPtoLP(0), getMapMode().DPtoLP(0),
			//					getMapMode().DPtoLP(0)));
			this.setBorder(new RaisedMarginBorder());
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

	}

	/**
	 * @generated
	 */
	public static final Color WORKPRODUCTCOMPOSITEFIGURE_BACK = new Color(null,
			255, 255, 205);

	/**
	 * @custom
	 */
	public void updatebounds() {
		Rectangle constraint = new Rectangle(0, 0, -1, -1);
		Node node = (Node) this.getModel();
		Location location = (Location) node.getLayoutConstraint();
		if (location != null) {
			constraint.setLocation(new Point(location.getX(), location.getY()));
		}
		//		constraint.width = getNode().getWidth();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);
	}
	
	@Override
	public void activate() {
		super.activate();
		Object model = getModel();
		if (model instanceof View) {
			EObject e = ((View) model).getElement();
			if (e instanceof org.eclipse.epf.diagram.model.Node) {
				((org.eclipse.epf.diagram.model.Node) e).addConsumer(this);
			}
		}
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		Object model = getModel();
		if (model instanceof View) {
			EObject e = ((View) model).getElement();
			if (e instanceof org.eclipse.epf.diagram.model.Node) {
				((org.eclipse.epf.diagram.model.Node) e).removeConsumer(this);
			}
		}
	}

}
