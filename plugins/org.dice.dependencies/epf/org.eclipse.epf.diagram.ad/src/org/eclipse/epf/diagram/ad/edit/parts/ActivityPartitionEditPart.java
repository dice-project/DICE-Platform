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
package org.eclipse.epf.diagram.ad.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.custom.policies.ActivityPartitionCreationEditPolicy;
import org.eclipse.epf.diagram.ad.custom.policies.ActivityPartitionDelegationEditPolicy;
import org.eclipse.epf.diagram.ad.custom.policies.ActivityPartitionLayoutEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.ActivityPartitionCanonicalEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.ActivityPartitionGraphicalNodeEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.ActivityPartitionItemSemanticEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.ActivityPartitionPartitionCampartmentItemSemanticEditPolicy;
import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class ActivityPartitionEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1008;

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
	public ActivityPartitionEditPart(View view) {
		super(view);
	}

	/**
	 * @modified
	 */
	protected void createDefaultEditPolicies() {
		//		super.createDefaultEditPolicies();
		//		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
		//				new ActivityPartitionItemSemanticEditPolicy());
		//		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
		//				new ActivityPartitionGraphicalNodeEditPolicy());
		//		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
		//				new ActivityPartitionCanonicalEditPolicy());
		//		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new ActivityPartitionCreationEditPolicy());
		super.createDefaultEditPolicies();
		removeEditPolicy(EditPolicy.LAYOUT_ROLE);
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new ActivityPartitionPartitionCampartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ActivityPartitionGraphicalNodeEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ActivityPartitionCanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new ActivityPartitionLayoutEditPolicy());
		installEditPolicy(
				EditPolicy.CONTAINER_ROLE,
				new ActivityPartitionDelegationEditPolicy(
						UMLVisualIDRegistry
								.getType(ActivityPartitionPartitionCampartmentEditPart.VISUAL_ID),
						EditPolicy.CONTAINER_ROLE));
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		XYLayoutEditPolicy lep = new XYLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = super.createChildEditPolicy(child);
				if (result == null) {
					return new ResizableShapeEditPolicy();
				}
				return result;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		ActivityPartitionFigure figure = new ActivityPartitionFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ActivityPartitionFigure getPrimaryShape() {
		return (ActivityPartitionFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ActivityPartitionName2EditPart) {
			((ActivityPartitionName2EditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigurePartitionLabelFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {

		return false;
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
			nodeShape.setLayoutManager(new FreeformLayout() {

				public Object getConstraint(IFigure figure) {
					Object result = constraints.get(figure);
					if (result == null) {
						result = new Rectangle(0, 0, -1, -1);
					}
					return result;
				}
			});
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
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(UMLVisualIDRegistry
				.getType(ActivityPartitionName2EditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {

		return super.getContentPaneFor(editPart);
	}

	/**
	 * @generated
	 */
	public class ActivityPartitionFigure extends RectangleFigure {

		/**
		 * @modified
		 */
		public ActivityPartitionFigure() {

			org.eclipse.draw2d.XYLayout myGenLayoutManager = new org.eclipse.draw2d.XYLayout();

			this.setLayoutManager(myGenLayoutManager);

			LineStyle linestyle = (LineStyle) getPrimaryView().getStyle(
					NotationPackage.Literals.LINE_STYLE);
			if (linestyle != null) {
				this.setForegroundColor(DiagramColorRegistry.getInstance()
						.getColor(new Integer(linestyle.getLineColor())));
			} else {
				this
						.setForegroundColor(org.eclipse.draw2d.ColorConstants.black);
			}

			FillStyle style = (FillStyle) getPrimaryView().getStyle(
					NotationPackage.Literals.FILL_STYLE);
			if (style != null) {
				this.setBackgroundColor(DiagramColorRegistry.getInstance()
						.getColor(new Integer(style.getFillColor())));
			} else {
				this.setBackgroundColor(ACTIVITYPARTITIONFIGURE_BACK);
			}
			this.setPreferredSize(getMapMode().DPtoLP(300), getMapMode()
					.DPtoLP(600));
			this.setSize(getMapMode().DPtoLP(300), getMapMode().DPtoLP(600));
			this.setBorder(new org.eclipse.draw2d.LineBorder());
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
			WrapLabel fig_0 = new WrapLabel();

			setFigurePartitionLabelFigure(fig_0);

			Object layData0 = null;

			this.add(fig_0, layData0);
		}

		/**
		 * @generated
		 */
		private WrapLabel fPartitionLabelFigure;

		/**
		 * @generated
		 */
		public WrapLabel getFigurePartitionLabelFigure() {
			return fPartitionLabelFigure;
		}

		/**
		 * @generated
		 */
		private void setFigurePartitionLabelFigure(WrapLabel fig) {
			fPartitionLabelFigure = fig;
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

		@Override
		public void paintFigure(Graphics graphics) {
// this was overridden to resolve label overlap issue for all the nodes but this breaks activity partition fill color and 
// introduced "01483134: Activity Partition Fill Color doesn't do anything."
// most likely we don't have any issue with label overlap for activity partition
// hence commenting it to fix fill color--- shilpat
//			if (isOpaque())
				super.paintFigure(graphics);
			return;
		}

	}

	/**
	 * @generated
	 */
	public static final Color ACTIVITYPARTITIONFIGURE_BACK = new Color(null,
			255, 255, 255);

	@Override
	protected void handleNotificationEvent(Notification notification) {
		// TODO Auto-generated method stub
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getSize_Width().equals(feature)
				|| NotationPackage.eINSTANCE.getSize_Height().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_X().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_Y().equals(feature)) {
			refreshBounds();
		} else if (NotationPackage.eINSTANCE.getFillStyle_FillColor().equals(
				feature)) {
			Integer c = (Integer) notification.getNewValue();
			setBackgroundColor(DiagramColorRegistry.getInstance().getColor(c));
			getPrimaryShape().setBackgroundColor(
					DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getLineStyle_LineColor().equals(
				feature)) {
			Integer c = (Integer) notification.getNewValue();
			setForegroundColor(DiagramColorRegistry.getInstance().getColor(c));
			getPrimaryShape().setForegroundColor(
					DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle().isInstance(
				notification.getNotifier()))
			refreshFont();
		else if (notification.getFeature() == NotationPackage.eINSTANCE
				.getView_Element()
				&& ((EObject) notification.getNotifier()) == getNotationView())
			handleMajorSemanticChange();
		else
			super.handleNotificationEvent(notification);
	}
}
