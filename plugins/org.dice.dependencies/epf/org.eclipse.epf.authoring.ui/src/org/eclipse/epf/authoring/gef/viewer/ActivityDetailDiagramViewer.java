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
package org.eclipse.epf.authoring.gef.viewer;

import org.eclipse.epf.authoring.gef.edit.ActivityDetailDiagramEditPart;
import org.eclipse.epf.authoring.gef.edit.ActivityDetailDiagramEditPartFactory;
import org.eclipse.epf.authoring.gef.edit.DiagramActionService;
import org.eclipse.epf.authoring.gef.edit.DiagramUpdateService;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.swt.widgets.Composite;

/**
 * The graphical viewer for the ActivityDetail diagram.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ActivityDetailDiagramViewer extends AbstractDiagramGraphicalViewer {

	public ActivityDetailDiagramViewer(Composite parent) {
		super(parent);
	}

	protected EditPartFactory createEditPartFactory() {
		return new ActivityDetailDiagramEditPartFactory();
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#createEditPart(java.lang.Object, org.eclipse.epf.library.edit.IFilter, org.eclipse.epf.library.edit.util.Suppression)
	 */

	protected EditPart createEditPart(Object e, IFilter filter, Suppression sup) {
		ActivityDetailDiagram diagram = ModelFactory.eINSTANCE
				.createActivityDetailDiagram();//
		diagram.setFilter(filter);
		if (sup != null) {
			diagram.setSuppression(sup);
		}

		diagram.setObject(e);
		EditPart part = new ActivityDetailDiagramEditPart(
				(ActivityDetailDiagram) diagram);
		part.setModel(diagram);
		return part;
	}

	protected String getDiagramType() {
		return ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#cleanUpDiagram()
	 */
	protected void cleanUpDiagram() {
		ActivityDetailDiagramEditPart dep = (ActivityDetailDiagramEditPart) getGraphicalViewer()
				.getContents();
		dep.getRecentlyAddedParts().addAll(dep.getChildren());

		// clean up the diagram
		DefaultEditDomain editingDomain = new DefaultEditDomain(null);
		ActionRegistry actionRegistry = new ActionRegistry();

		DiagramActionService actionService = new DiagramActionService(
				getGraphicalViewer(), editingDomain, actionRegistry);
		actionService.registerVerticalAlignFirstSelectedAction();

		DiagramUpdateService service = new DiagramUpdateService(
				getGraphicalViewer(), editingDomain, actionRegistry);
		service.cleanUpDiagram();
	}

}
