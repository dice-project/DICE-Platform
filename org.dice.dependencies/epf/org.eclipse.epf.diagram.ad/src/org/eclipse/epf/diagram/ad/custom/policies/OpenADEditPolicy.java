/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.ad.custom.policies;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Mouse double click on StructuredActivityEditPart should open diagram. 
 * @author Shashidhar Kannoori
 *
 */
public class OpenADEditPolicy extends OpenEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy#getOpenCommand(org.eclipse.gef.Request)
	 */
	@Override
	protected Command getOpenCommand(Request request) {
		EditPart targetEditPart = getTargetEditPart(request);
		if (targetEditPart instanceof IGraphicalEditPart) {
			Node view = (Node) targetEditPart.getModel();

			DiagramEditPart diagramEditPart = DiagramCoreUtil.getDiagramEditPart(targetEditPart);
			Diagram diagram = (Diagram) diagramEditPart.getModel();
			DiagramAdapter diagramAdapter = BridgeHelper
					.getDiagramAdapter(diagram.getElement());
			EObject element = view.getElement();
			NodeAdapter adapter = BridgeHelper
					.getNodeAdapter(element);
			if (adapter != null && adapter.getElement() instanceof Activity) {
				Object selectedObject = adapter.getWrapper();
				if (selectedObject == null) {
					selectedObject = adapter.getElement();
				}
				GraphicalViewer viewer = (GraphicalViewer) diagramEditPart
						.getViewer();
				if (viewer != null) {
					DiagramEditDomain domain = (DiagramEditDomain) viewer
							.getEditDomain();
					IWorkbenchPart part = domain.getDiagramEditorPart();
					if (part != null) {
						DiagramEditorInput input = new org.eclipse.epf.diagram.core.part.DiagramEditorInput(
								selectedObject,
								diagramAdapter.getSuppression(),
								IDiagramManager.ACTIVITY_DIAGRAM);
						try{
						DiagramEditorUtil
								.openDiagramEditor(
										part.getSite().getPage(),
										input,
										ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
										new NullProgressMonitor());
						}catch(Exception e){
							
						}
					}
				}
			}
		}
		return null;
	}

}
