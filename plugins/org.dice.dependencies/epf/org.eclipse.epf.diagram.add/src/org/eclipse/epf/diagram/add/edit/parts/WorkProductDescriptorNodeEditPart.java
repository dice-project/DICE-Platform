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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.add.edit.policies.WorkProductDescriptorNodeCanonicalEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.WorkProductDescriptorNodeGraphicalNodeEditPolicy;
import org.eclipse.epf.diagram.add.edit.policies.WorkProductDescriptorNodeItemSemanticEditPolicy;

import org.eclipse.epf.diagram.add.part.DiagramVisualIDRegistry;
import org.eclipse.epf.diagram.core.figures.WidenedWrapLabel;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import org.eclipse.gef.commands.Command;

import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import org.eclipse.gef.requests.CreateRequest;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;

import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * @generated
 */
public class WorkProductDescriptorNodeEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2003;

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
	public WorkProductDescriptorNodeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new WorkProductDescriptorNodeItemSemanticEditPolicy());
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
		WorkProductDescriptorNodeFigure figure = new WorkProductDescriptorNodeFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public WorkProductDescriptorNodeFigure getPrimaryShape() {
		return (WorkProductDescriptorNodeFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof WorkProductDescriptorNodeNameEditPart) {
			((WorkProductDescriptorNodeNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureWorkProductNodeNameFigure());
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
		return getChildBySemanticHint(DiagramVisualIDRegistry
				.getType(WorkProductDescriptorNodeNameEditPart.VISUAL_ID));
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
	public class WorkProductDescriptorNodeFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		public WorkProductDescriptorNodeFigure() {

			this.setOutline(false);
			this.setBackgroundColor(WORKPRODUCTDESCRIPTORNODEFIGURE_BACK

			);
			createContents();
		}

		/**
		 * @modified
		 */
		private void createContents() {
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_0 = new WidenedWrapLabel();
			fig_0.setText(""); //$NON-NLS-1$
			fig_0.setFont(WORKPRODUCTNODENAMEFIGURE_FONT);

			DiagramCoreUtil.setLabelProperties(fig_0); // modified
			setFigureWorkProductNodeNameFigure(fig_0);
			fig_0.setTextWrapAlignment(PositionConstants.LEFT);
			Object layData0 = null;

			this.add(fig_0, layData0);
		}

		/**
		 * @generated
		 */
		private WrapLabel fWorkProductNodeNameFigure;

		/**
		 * @generated
		 */
		public WrapLabel getFigureWorkProductNodeNameFigure() {
			return fWorkProductNodeNameFigure;
		}

		/**
		 * @generated
		 */
		private void setFigureWorkProductNodeNameFigure(WrapLabel fig) {
			fWorkProductNodeNameFigure = fig;
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

	/**
	 * @generated
	 */
	public static final Font WORKPRODUCTNODENAMEFIGURE_FONT = new Font(Display
			.getCurrent(), "Arial", 8, SWT.NORMAL);	//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final Color WORKPRODUCTDESCRIPTORNODEFIGURE_BACK = new Color(
			null, 255, 255, 205);

}
