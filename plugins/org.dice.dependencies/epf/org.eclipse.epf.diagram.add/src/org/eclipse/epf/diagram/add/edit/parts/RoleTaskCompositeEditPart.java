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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.add.edit.policies.DiagramTextSelectionEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.RoleTaskCompositeCanonicalEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.RoleTaskCompositeGraphicalNodeEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.RoleTaskCompositeItemSemanticEditPolicy;
import org.eclipse.epf.diagram.core.figures.RaisedMarginBorder;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
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
public class RoleTaskCompositeEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1001;

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
	public RoleTaskCompositeEditPart(View view) {
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
				new RoleTaskCompositeItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new RoleTaskCompositeCanonicalEditPolicy());
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
		RoleTaskCompositeFigure figure = new RoleTaskCompositeFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public RoleTaskCompositeFigure getPrimaryShape() {
		return (RoleTaskCompositeFigure) primaryShape;
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
	public class RoleTaskCompositeFigure extends RectangleFigure {

		/**
		 * @@modified
		 */
		public RoleTaskCompositeFigure() {

			org.eclipse.draw2d.ToolbarLayout myGenLayoutManager = new org.eclipse.draw2d.ToolbarLayout();
			myGenLayoutManager.setStretchMinorAxis(false);
			myGenLayoutManager
					.setMinorAlignment(org.eclipse.draw2d.ToolbarLayout.ALIGN_CENTER);
			myGenLayoutManager.setSpacing(20);
			myGenLayoutManager.setVertical(false);

			this.setLayoutManager(myGenLayoutManager);

			this.setBackgroundColor(ROLETASKCOMPOSITEFIGURE_BACK

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
	public static final Color ROLETASKCOMPOSITEFIGURE_BACK = new Color(null,
			255, 255, 156);

	/**
	 * @modified
	 */
	public void updatebounds() {
		Rectangle constraint = new Rectangle(0, 0, -1, -1);
		Node node = (Node) this.getModel();
		Location location = (Location) node.getLayoutConstraint();
		if (location != null) {
			constraint.setLocation(new Point(location.getX(), location.getY()));
		}

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);
	}

	@Override
	protected void handleNotificationEvent(Notification notification) {
		//		if(notification.getEventType() == Notification.SET){
		//			ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL); List l = getChildren();
		//			arrangeRequest.setPartsToArrange(l); 
		//			Command arrangeCmd = getCommand(arrangeRequest); 
		//			arrangeCmd.execute();
		//			
		//			Request request = new Request(RequestConstants.REQ_AUTOSIZE);
		//			Command autoSizeCmd = getCommand(request);
		//			if(autoSizeCmd != null){
		//				autoSizeCmd.execute();
		//			}
		//		}
		super.handleNotificationEvent(notification);
	}

	@Override
	protected List getModelChildren() {
		// TODO Auto-generated method stub
		List<View> list = super.getModelChildren();

		Comparator<View> c = new Comparator<View>() {
			public int compare(View o1, View o2) {
				Object model1 = ((View) o1).getElement();
				Object model2 = ((View) o2).getElement();
				if (model1 instanceof TaskNode && model2 instanceof TaskNode) {
					if (((TaskNode) model1).getIndex() >= ((TaskNode) model2)
							.getIndex())
						return 1;
					else
						return -1;
				}
				return 0;
			}
		};
		Collections.sort(list, c);
		return list;
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
