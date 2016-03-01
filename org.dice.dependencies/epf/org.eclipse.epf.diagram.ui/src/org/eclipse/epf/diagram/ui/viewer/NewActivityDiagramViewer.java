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

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionEditPart;
import org.eclipse.epf.diagram.core.bridge.ActivityDiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Composite;

/**
 * The graphical viewer for the Activity diagram.
 * 
 * @author Jinhua Xi
 * @author Shashidhar Kannoori
 * @since 1.2
 */
public class NewActivityDiagramViewer extends AbstractDiagramGraphicalViewerEx {



	private ActivityDiagramAdapter adapter;

	/**
	 * @param parent
	 */
	public NewActivityDiagramViewer(Composite parent, Object act) {
		super(parent,act);
	}

	@Override
	public void dispose() {
		if(adapter != null && diagram != null && diagram.getElement() != null) {
			diagram.getElement().eAdapters().remove(adapter);
		}
		
		super.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#createEditPart(java.lang.Object, org.eclipse.epf.library.edit.IFilter, org.eclipse.epf.library.edit.util.Suppression)
	 */
	@Override
	protected EditPart createEditPart(Object wrapper, IFilter filter, Suppression sup) {
		Activity e = null;
		if (wrapper instanceof ActivityWrapperItemProvider) {
			e = (Activity) TngUtil.unwrap(wrapper);
			adapter = new ActivityDiagramAdapter(
					(ActivityWrapperItemProvider) wrapper);
		} else {
			if (wrapper instanceof Activity) {
				e = (Activity) wrapper;
				adapter = new ActivityDiagramAdapter(e);
			}
		}
		if (adapter != null) {
			try {
				adapter.setView(diagram);
				adapter.setFilter(filter);
				adapter.setSuppression(sup);
				adapter.setEditingDomain((InternalTransactionalEditingDomain) TransactionUtil
								.getEditingDomain(diagram.getElement()));
				diagram.getElement().eAdapters().add(adapter);
				DiagramEditDomain domain = new DiagramEditDomain(null);
				((IDiagramGraphicalViewer) graphicalViewer)
						.setEditDomain(domain);
				part = EditPartService.getInstance().createGraphicEditPart(
						diagram);
				if (part == null) {
					DiagramUIPlugin.getDefault().getLogger().logError(
							"Publishing: Activity Diagram EditPart creation failed for : "	//$NON-NLS-1$
									+ e);
				}
				return part;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#getDiagramType()
	 */
	@Override
	protected String getDiagramType() {
		return ResourceHelper.DIAGRAM_TYPE_WORKFLOW;
	}

	/**
	 * 
	 */
	protected void loadDiagramInfo(GraphicalEditPart part, DiagramInfo diagramInfo) {
		List children = part.getChildren();
		for (Iterator it = children.iterator(); it.hasNext();) {
			Object o = it.next();
			if (o instanceof ActivityPartitionEditPart)
			{
				ActivityPartitionEditPart partition = (ActivityPartitionEditPart) o;
				List partitionChildren = partition.getChildren();
				for (Iterator i = partitionChildren.iterator(); i.hasNext();)
				{
					Object o1 = i.next();
					if (o1 instanceof GraphicalEditPart){
						loadDiagramInfo((GraphicalEditPart) o1, diagramInfo);
					}
				}
				
			} else if (o instanceof GraphicalEditPart) {
				part = (GraphicalEditPart) o;
				Object model = part.getModel();
				Object e = model;
				
				if(e instanceof View){
					EModelElement ex = (EModelElement)((View)e).getElement();
					if(ex != null){
						e = BridgeHelper.getMethodElement(ex);
					}
				}
				
				if (e instanceof MethodElement) {
					boolean suppressed;
					if (model instanceof Node) {
						suppressed = ((MethodElement)BridgeHelper.getMethodElement((EModelElement)((Node)model).getElement())).getSuppressed().booleanValue();
					} else {
						suppressed = ((MethodElement) e).getSuppressed()
								.booleanValue();
					}

					IFigure f = part.getFigure();
					Rectangle bounds = f.getBounds();

					String altTag = null;
					if (f instanceof WrapLabel) {
						altTag = ((WrapLabel) f).getText();
					}
					
					e = getElementForAddArea((MethodElement) e);
					diagramInfo.addArea((MethodElement) e, bounds.x,
						bounds.y, bounds.width, bounds.height, altTag, suppressed);

				}
				else
					diagramInfo.addGraphicalNodes();
			} 
		}
	}
	
	@Override
	protected void createGraphicalViewer() {
		this.graphicalViewer = new DiagramGraphicalViewer();
		this.graphicalViewer.createControl(parent);
		configureGraphicalViewer();
	}
	
	/**
	 * 
	 */
	protected void configureGraphicalViewer() {
		getGraphicalViewer().getControl().setBackground(
				ColorConstants.listBackground);
		DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) getGraphicalViewer();

		DiagramRootEditPart root = (DiagramRootEditPart) EditPartService
				.getInstance().createRootEditPart(getDiagram());
		LayeredPane printableLayers = (LayeredPane) root
				.getLayer(LayerConstants.PRINTABLE_LAYERS);
		FreeformLayer extLabelsLayer = new FreeformLayer();
		extLabelsLayer.setLayoutManager(new DelegatingLayout());
//		printableLayers.addLayerAfter(extLabelsLayer,
//				UMLEditPartFactory.EXTERNAL_NODE_LABELS_LAYER,
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
