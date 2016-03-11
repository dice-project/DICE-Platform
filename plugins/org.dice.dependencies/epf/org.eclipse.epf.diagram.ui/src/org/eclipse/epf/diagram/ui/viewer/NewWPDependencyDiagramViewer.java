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
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.diagram.wpdd.edit.parts.DiagramEditPartFactory;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.swt.widgets.Composite;

/**
 *  The graphical viewer for the WorkProduct Dependency diagram.
 *  
 * @author Jinhua Xi
 * @author Shashidhar Kannoori
 */
public class NewWPDependencyDiagramViewer extends
		AbstractDiagramGraphicalViewerEx {

	/**
	 * @param parent
	 * @param wrapper
	 */
	public NewWPDependencyDiagramViewer(Composite parent, Object wrapper) {
		super(parent, wrapper);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#createEditPart(java.lang.Object, org.eclipse.epf.library.edit.IFilter, org.eclipse.epf.library.edit.util.Suppression)
	 */
	@Override
	protected EditPart createEditPart(Object wrapper, IFilter filter, Suppression sup) {
		Activity e = null;
		if(wrapper instanceof ActivityWrapperItemProvider){
			e = (Activity)TngUtil.unwrap(wrapper);
		}else if(wrapper instanceof Activity){
			e = (Activity)wrapper;
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
				logError("Publishing: WorkProduct Diagram EditPart creation failed for : "+e); //$NON-NLS-1$
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
		return ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
	}
	@Override
	protected void configureGraphicalViewer() {
		DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) getGraphicalViewer();
		DiagramRootEditPart root = (DiagramRootEditPart) EditPartService
		.getInstance().createRootEditPart(getDiagram());
		LayeredPane printableLayers = (LayeredPane) root
				.getLayer(LayerConstants.PRINTABLE_LAYERS);
		FreeformLayer extLabelsLayer = new FreeformLayer();
		extLabelsLayer.setLayoutManager(new DelegatingLayout());
//		printableLayers.addLayerAfter(extLabelsLayer,
//				DiagramEditPartFactory.EXTERNAL_NODE_LABELS_LAYER,
//				LayerConstants.PRIMARY_LAYER);
		LayeredPane scalableLayers = (LayeredPane) root
				.getLayer(LayerConstants.SCALABLE_LAYERS);
		FreeformLayer scaledFeedbackLayer = new FreeformLayer();
		scaledFeedbackLayer.setEnabled(false);
		scalableLayers.addLayerAfter(scaledFeedbackLayer,
				LayerConstants.SCALED_FEEDBACK_LAYER,
				DiagramRootEditPart.DECORATION_UNPRINTABLE_LAYER);
		viewer.setRootEditPart(root);
		viewer.setEditPartFactory(createEditPartFactory());
	}
}
