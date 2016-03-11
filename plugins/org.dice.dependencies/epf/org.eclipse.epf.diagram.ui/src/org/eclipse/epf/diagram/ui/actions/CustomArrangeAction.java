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
package org.eclipse.epf.diagram.ui.actions;

import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.ui.IWorkbenchPage;

/**
 * @author skannoor
 *
 */
public class CustomArrangeAction extends ArrangeAction {

	
	private DiagramEditPart diagramEditPart;
	private GraphicalViewer viewer;

	/**
	 * @param workbenchPage
	 * @param selectionOnly
	 */
	public CustomArrangeAction(IWorkbenchPage workbenchPage,
			boolean selectionOnly, DiagramEditPart diagramEditPart, GraphicalViewer viewer) {
		super(workbenchPage, selectionOnly);
		this.diagramEditPart = diagramEditPart;
		this.viewer = viewer;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List getSelectedObjects() {
		return diagramEditPart.getChildren();
	}
	
	@Override
	protected DiagramEditPart getDiagramEditPart() {
		return diagramEditPart;
	}
	@Override
	protected DiagramCommandStack getDiagramCommandStack() {
		return new DiagramCommandStack((IDiagramEditDomain)viewer.getEditDomain());
	}
}
