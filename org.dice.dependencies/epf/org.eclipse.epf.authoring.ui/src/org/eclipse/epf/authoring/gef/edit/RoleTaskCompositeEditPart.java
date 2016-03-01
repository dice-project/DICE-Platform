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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.authoring.gef.edit.policies.RoleTaskFlowLayoutEditPolicy;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.ibm.icu.util.StringTokenizer;

/**
 * RoleTaskCompositeEditPart is container with {@link FlowLayout}, and does not support adding/removing child editpart. 
 *  
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RoleTaskCompositeEditPart extends NodeContainerEditPart {

//	private static final Color BG_COLOR = new Color(Display.getCurrent(), 255,
//			255, 156);
	
	private static  Color BG_COLOR;
	public RoleTaskCompositeEditPart(RoleTaskComposite model) {
		super(model);
	}

	protected IFigure createFigure() {
		Figure figure = new Figure();
		FlowLayout layout = new FlowLayout();
		layout.setMinorSpacing(20);
		figure.setLayoutManager(layout);
		figure.setBorder(new RaisedMarginBorder());
		figure.setBackgroundColor(getBackgroundColor());
		figure.setOpaque(true);
		return figure;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NodeContainerEditPart#handlePropertyChanged(Notification)
	 */
	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(NodeContainer.class)) {
		case ModelPackage.NODE_CONTAINER__NODES:
			refreshChildren();

			// refresh diagram's children
			EditPart diagramEditPart = getParent();
//			if (diagramEditPart instanceof ActivityDetailDiagramEditPart) {
//				ActivityDetailDiagram diagram = (ActivityDetailDiagram) diagramEditPart
//						.getModel();
//
//				if (!GraphicalDataHelper.isAutoLayout(diagram)) {
//					((ActivityDetailDiagramEditPart) diagramEditPart)
//							.getRecentlyAddedParts().addAll(
//									diagramEditPart.getChildren());
//					DefaultEditDomain ed = (DefaultEditDomain) diagramEditPart
//							.getViewer().getEditDomain();
//					IEditorPart editor = ed.getEditorPart();
//					if (editor != null && editor instanceof ActivityDetailDiagramEditor) {
//						((ActivityDetailDiagramEditor) editor).cleanUpDiagram();
//					}
//				} else {
//					diagramEditPart.refresh();
//				}
//			}
			diagramEditPart.refresh();
			return;
		}
		super.handlePropertyChanged(msg);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NodeContainerEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new RoleTaskFlowLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
	}
	protected Color getBackgroundColor(){
		if(BG_COLOR != null) return BG_COLOR;
		
		if(BG_COLOR == null){
			String colorString = AuthoringUIPlugin.getDefault().getPreferenceStore().getString(
					TemplateConstants.ADD_ROLE_TASKS_BOX_BG_COLOR_RGB);
			if(colorString != null && colorString.length() > 0){
				StringTokenizer tokenizer = new StringTokenizer(colorString, ","); //$NON-NLS-1$
				int r = Integer.parseInt(tokenizer.nextToken());
				int g = Integer.parseInt(tokenizer.nextToken());
				int b = Integer.parseInt(tokenizer.nextToken());
				BG_COLOR = new Color(null, r,g,b);
				return BG_COLOR;
			}
		}
		if(BG_COLOR == null){
			BG_COLOR = new Color(Display.getCurrent(), 255,
							255, 156);
		}
		return BG_COLOR;
	}
}
