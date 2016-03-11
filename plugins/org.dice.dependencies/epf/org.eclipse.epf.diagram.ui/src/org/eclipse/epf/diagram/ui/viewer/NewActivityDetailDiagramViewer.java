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
package org.eclipse.epf.diagram.ui.viewer;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.epf.diagram.add.edit.parts.ActivityDetailDiagramEditPart;
import org.eclipse.epf.diagram.add.service.DiagramResetService;
import org.eclipse.epf.diagram.core.actions.DiagramActionsService;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.swt.widgets.Composite;

/**
 * The graphical viewer for the ActivityDetail diagram.
 * 
 * @author Jinhua Xi
 * @author Shashidhar Kannoori
 */
public class NewActivityDetailDiagramViewer extends
		AbstractDiagramGraphicalViewerEx {

	/**
	 * @param parent
	 * @param wrapper
	 */
	public NewActivityDetailDiagramViewer(Composite parent, Object wrapper) {
		super(parent, wrapper);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#createEditPart(java.lang.Object, org.eclipse.epf.library.edit.IFilter, org.eclipse.epf.library.edit.util.Suppression)
	 */
	@Override
	protected EditPart createEditPart(Object wrapper, IFilter filter, Suppression sup) {
		Activity e = null;
		if (wrapper instanceof ActivityWrapperItemProvider) {
			e = (Activity) TngUtil.unwrap(wrapper);
		} else if (wrapper instanceof Activity) {
			e = (Activity) wrapper;
		}
		if(e instanceof Activity){
			updateDiagramElement(diagram, (MethodElement)e, sup, getMethodConfiguration(),
					filter,
					new NullProgressMonitor());
			DiagramEditDomain domain = new DiagramEditDomain(null);
			((IDiagramGraphicalViewer)graphicalViewer).setEditDomain(domain);
			part = EditPartService.getInstance().createGraphicEditPart(diagram);
			if(part == null){
				DiagramUIPlugin.getDefault().getLogger().
				logError("Publishing: Activity Detail Diagram EditPart creation failed for : "+e); //$NON-NLS-1$
			}
			return part;
		}
		return part;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#getDiagramType()
	 */
	@Override
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
		if (dep != null) {
			dep.getRecentlyAddedParts().addAll(dep.getChildren());

			// clean up the diagram
			DefaultEditDomain editingDomain = new DefaultEditDomain(null);
			ActionRegistry actionRegistry = new ActionRegistry();

			DiagramActionsService actionService = new DiagramActionsService(
					dmgr.getEditingDomain(), getGraphicalViewer(),
					editingDomain, actionRegistry);
			actionService.registerVerticalAlignFirstSelectedAction();

			DiagramResetService service = new DiagramResetService(dmgr
					.getEditingDomain(), getGraphicalViewer(), editingDomain,
					actionRegistry);
			service.cleanUpDiagram();
			// TODO: need to improve this function. Have to call it twice right now
			// so it can layout the diagram correctly.
			service.cleanUpDiagram();			
		}
	}
}
