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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.custom.figures.DecisionNodeFigure;
import org.eclipse.epf.diagram.ad.edit.policies.MergeNodeCanonicalEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.MergeNodeGraphicalNodeEditPolicy;
import org.eclipse.epf.diagram.ad.edit.policies.MergeNodeItemSemanticEditPolicy;

import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;
import org.eclipse.epf.diagram.core.editparts.InternalNodeEditPart;
import org.eclipse.epf.diagram.core.util.DiagramConstants;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;

import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

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
public class MergeNodeEditPart extends ShapeNodeEditPart implements InternalNodeEditPart {

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
	public MergeNodeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new MergeNodeItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		DecisionDiamondFigure figure = new DecisionDiamondFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public DecisionDiamondFigure getPrimaryShape() {
		return (DecisionDiamondFigure) primaryShape;
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
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(UMLVisualIDRegistry
				.getType(MergeNodeNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class DecisionDiamondFigure extends DecisionNodeFigure {

		/**
		 * @modified
		 */
		public DecisionDiamondFigure() {

			super(getMapMode().DPtoLP(50), getMapMode().DPtoLP(50),
					getMapMode().DPtoLP(50)); //modified
			LineStyle linestyle = (LineStyle) getPrimaryView().getStyle(
					NotationPackage.Literals.LINE_STYLE);
			if (linestyle != null
					&& linestyle.getLineColor() != DiagramConstants.LINE_COLOR_EDEFAULT) {
				this.setForegroundColor(DiagramColorRegistry.getInstance()
						.getColor(new Integer(linestyle.getLineColor())));
			} else {
				this
						.setForegroundColor(org.eclipse.draw2d.ColorConstants.darkBlue);
			}

			FillStyle style = (FillStyle) getPrimaryView().getStyle(
					NotationPackage.Literals.FILL_STYLE);
			if (style != null
					&& style.getFillColor() != DiagramConstants.FILL_COLOR_EDEFAULT) {
				this.setBackgroundColor(DiagramColorRegistry.getInstance()
						.getColor(new Integer(style.getFillColor())));
			} else {
				this.setBackgroundColor(DECISIONDIAMONDFIGURE_BACK);
			}
			this.setPreferredSize(getMapMode().DPtoLP(50), getMapMode().DPtoLP(
					50));
			this.setSize(getMapMode().DPtoLP(50), getMapMode().DPtoLP(50));

			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
			WrapLabel fig_0 = new WrapLabel();

			setFigureDiamondLabel(fig_0);

			Object layData0 = null;

			this.add(fig_0, layData0);
		}

		/**
		 * @generated
		 */
		private WrapLabel fDiamondLabel;

		/**
		 * @generated
		 */
		public WrapLabel getFigureDiamondLabel() {
			return fDiamondLabel;
		}

		/**
		 * @generated
		 */
		private void setFigureDiamondLabel(WrapLabel fig) {
			fDiamondLabel = fig;
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
	public static final Color DECISIONDIAMONDFIGURE_BACK = new Color(null, 185,
			205, 230);

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
	
	@Override
	public void primAddSourceConnection(ConnectionEditPart connection,
			int index) {
		super.primAddSourceConnection(connection, index);
	}
	
	@Override
	public void primAddTargetConnection(ConnectionEditPart connection,
			int index) {
		super.primAddTargetConnection(connection, index);
	}

}
