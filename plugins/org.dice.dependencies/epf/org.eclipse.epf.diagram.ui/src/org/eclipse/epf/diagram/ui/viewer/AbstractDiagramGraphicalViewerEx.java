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
package org.eclipse.epf.diagram.ui.viewer;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityEditPart;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.IDiagramEditorInputProvider;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.diagram.ui.service.DiagramImageService;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

/**
 * @author Shashidhar Kannoori
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.2
 */
public abstract class AbstractDiagramGraphicalViewerEx extends
		AbstractDiagramGraphicalViewer {
	
	protected class DiagramGraphicalViewerEx extends DiagramGraphicalViewer implements IDiagramEditorInputProvider {
		private DiagramEditorInput input;
		public DiagramEditorInput getDiagramEditorInput() {
			if(input == null) {
				input = new DiagramEditorInput(wrapper, null, DiagramImageService.getIntType(getDiagramType()));
			}
			return input;
		}

		public IEditorPart getEditor() {
			return null;
		}
		
	};

	protected GraphicalEditPart part = null;
	protected Diagram diagram;
	protected DiagramManager dmgr;
	protected boolean debug = DiagramUIPlugin.getDefault().isDebugging(); 
	protected MethodConfiguration config = null;

	public AbstractDiagramGraphicalViewerEx(Composite parent, Object wrapper) {
		super(parent, wrapper);
	}

	/**
	 * Creates diagram for given object (only for {@link Activity} and packs the bounds.
	 * 
	 * @param wrapper 	{@link Object} 	
	 * @param needReset boolean
	 * @param filter 	{@link IFilter}
	 * @param sup		{@link Suppression}
	 */
	public EditPart loadDiagram(Object wrapper, boolean needReset,
			IFilter filter, Suppression sup) {
		// initialize the viewer with the edit part
		EditPart editPart = createEditPart(wrapper, filter, sup);
		if(editPart == null){
			if (debug) {
				System.err.println("Editpart is null: "+ wrapper); //$NON-NLS-1$
			}
			return editPart;
		}
		this.graphicalViewer.setContents(editPart);
		DiagramEditPart diagramEditPart = (DiagramEditPart)editPart;		
		org.eclipse.emf.ecore.resource.Resource resource = diagramEditPart.getDiagramView().eResource();
		boolean oldDeliver = resource.eDeliver();
		try {
			// disable notification on the resource change during refresh to
			// avoid making the diagram editor dirty
			//
			if(resource != null) {
				resource.eSetDeliver(false);
			}
			refresh(diagramEditPart);
		}
		finally {
			if(resource != null) {
				resource.eSetDeliver(oldDeliver);
			}
		}
		
		// ask for immediate update of the control so that the diagram figure
		// will be computed
		parent.pack(true);
		if(editPart != null){
			if(debug){
				for (Iterator<?> iterator = editPart.getChildren().iterator(); iterator.hasNext();) {
					EditPart name = (EditPart) iterator.next();
					View view = (View)name.getModel();
					if (debug) {
						System.out.println(view.getType() +":" + ViewUtil.getStructuralFeatureValue(view,		//$NON-NLS-1$
								NotationPackage.eINSTANCE.getLocation_X()) + "," + ViewUtil.getStructuralFeatureValue(view,		//$NON-NLS-1$
								NotationPackage.eINSTANCE.getLocation_Y()));
					}
				}
			}
		}

		// This check is needed for browsing, in order to render activity contributing's
		// children.  In processAuthoring we don't do realization, in order to displayed 
		// realized elements, we have to cleanup ADD diagram and recreate. needtoReset or cleanupdiagram.
		// Only for activity detail diagram.
		Object o = TngUtil.unwrap(wrapper);
		if(o instanceof VariabilityElement && getDiagramType() != null && 
				getDiagramType().equalsIgnoreCase(ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL)){
			if(TngUtil.hasContributor((VariabilityElement)o)){
				needReset = true;
			}
		}
		
		if (needReset) {
			cleanUpDiagram();
			parent.pack(true);
		}
		IFigure figure = getFigure();
		if (figure != null) {
			Rectangle bounds = figure.getBounds();
			if (bounds.x < 0 || bounds.y < 0) {
				if(editPart instanceof ActivityEditPart){
					((ActivityEditPart) editPart).moveFigure(-bounds.x, -bounds.y);
				}
				parent.pack(true);

				bounds = figure.getBounds();
			}
		}

		return editPart;
	}

	
	/**
	 * returns {@link DiagramInfo}
	 * 
	 */
	public DiagramInfo getDiagramInfo() {
		DiagramInfo diagramInfo = null;

		Object element = part.getModel();
		if (element instanceof LinkedObject) {
			element = ((LinkedObject) element).getObject();
		}
		if(element instanceof View){
			 element = ((View)element).getElement();
			if(element != null && element instanceof EModelElement){
				element = BridgeHelper.getMethodElement((EModelElement)element);
			}
			if(element instanceof Node){
				element = ((Node)element).getLinkedElement();
			}
		}

		if (element instanceof MethodElement) {
			diagramInfo = new DiagramInfo(getDiagramType(),
					(MethodElement) element);
			loadDiagramInfo(part, diagramInfo);
		}

		return diagramInfo;
	}
	
	public void createDiagramImage(ImageFileFormat imageFileFormat){
//		CopyToImageUtil im = new CopyToImageUtil();
		//im.copyToImage(part, destination, format, monitor)
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.viewer.AbstractDiagramGraphicalViewer#createEditPartFactory()
	 */
	@Override
	protected EditPartFactory createEditPartFactory() {
		return EditPartService.getInstance();
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
//		LayeredPane printableLayers = (LayeredPane) root
//				.getLayer(LayerConstants.PRINTABLE_LAYERS);
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
		
		// Set editing domain
//		DiagramEditDomain editDomain = new DiagramEditDomain(null);
//        editDomain.setCommandStack(
//            new DiagramCommandStack(editDomain));
//
//        viewer.setEditDomain(editDomain);
        
//        PreferencesHint preferencesHint = DiagramImageService.getPreferenceHint(
//        		getDiagramType());
//        if (root instanceof IDiagramPreferenceSupport) {
//            if (preferencesHint == null) {
//                preferencesHint = ((IDiagramPreferenceSupport) root)
//                    .getPreferencesHint();
//            } else {
//                ((IDiagramPreferenceSupport) root)
//                    .setPreferencesHint(preferencesHint);
//            }
//            viewer
//                .hookWorkspacePreferenceStore((IPreferenceStore) preferencesHint
//                    .getPreferenceStore());
//        }
//        
//        DiagramEventBroker.startListening(TransactionUtil.getEditingDomain(diagram));
//        viewer.setContents(diagram);
	}
	
	/*
	 * 
	 */
	protected Diagram getDiagram(){
		if (diagram == null) {
			try {
				if (diagram == null) {
					Object o = TngUtil.unwrap(wrapper);
					if (o instanceof Activity) {
						Activity act = (Activity) o;
						dmgr = DiagramManager.getInstance(TngUtil
								.getOwningProcess(act), this);
						List<Diagram> list = dmgr.getDiagrams(act, DiagramImageService
								.getIntType(getDiagramType()));
						if (!list.isEmpty()) {
							diagram = (Diagram) list.get(0);
						} else {
							if (debug) {
								System.out
										.println("Diagram is empty for " + act); //$NON-NLS-1$
							}
						}
					}
				}

			} catch (Exception e) {
				if (debug) {
					e.printStackTrace();
				}
			}
			if (diagram == null) {
				if (debug) {
					System.out
							.println("Creating diagram of  " + getDiagramType()); //$NON-NLS-1$
				}
				try {
					diagram = dmgr.createDiagram((Activity) TngUtil
							.unwrap(wrapper), DiagramImageService
							.getIntType(getDiagramType()), DiagramImageService
							.getPreferenceHint(getDiagramType()));
				} catch (Exception e) {

				}
			}
			
			if(diagram != null) {
				EObject e = diagram.getElement();
				if(e instanceof Node) {
					((Node)e).addConsumer(this);
				}
			}
		}
		return diagram;
	}
	
	@Override
	protected void createGraphicalViewer() {
		this.graphicalViewer = new DiagramGraphicalViewerEx();
		this.graphicalViewer.createControl(parent);
		configureGraphicalViewer();
	}
	
	protected void loadDiagramInfo(GraphicalEditPart part, DiagramInfo diagramInfo) {
		List<?> children = part.getChildren();
		for (Iterator<?> it = children.iterator(); it.hasNext();) {
			Object o = it.next();

			if (o instanceof GraphicalEditPart) {
				part = (GraphicalEditPart) o;
				Object model = part.getModel();
				Object e = model;
				if (e instanceof LinkedObject) {
					e = ((LinkedObject) e).getObject();
				}
				if (e instanceof View) {
					e = ((View) e).getElement();
					if (e instanceof Node) {
						if (e instanceof NodeContainer) {
							loadDiagramInfo((GraphicalEditPart) o, diagramInfo);
						}
						e = ((Node) e).getLinkedElement();
					}
					if (e != null && e instanceof EModelElement) {
						e = BridgeHelper.getMethodElement((EModelElement) e);
					}
				}

				if (e instanceof DiagramElement) {
					continue;
				}

				if (e instanceof MethodElement) {
					boolean suppressed;
					if (model instanceof NamedNode) {
						suppressed = ((NamedNode) model).isSuppressed();
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
					diagramInfo.addArea((MethodElement) e, bounds.x, bounds.y,
							bounds.width, bounds.height, altTag, suppressed);

				}
			} else {
			}
		}
	}
	
	
	protected MethodElement getElementForAddArea(MethodElement e) {
		if (DescriptorPropUtil.useLinkedElementInDiagram && e instanceof Descriptor) {
			MethodElement e1 = ProcessUtil.getAssociatedElement((Descriptor) e);
			if (e1 != null) {
				e1 = LibraryEditUtil.getInstance().getCalcualtedElement(e1, getMethodConfiguration());
				if (e1 != null) {
					return e1;
				}
			}
		}
		
		return e;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		if(dmgr != null){
			dmgr.removeConsumer(this);
		}
		if(diagram != null) {
			EObject e = diagram.getElement();
			if(e instanceof Node) {
				((Node)e).removeConsumer(this);
			}
		}
	}
	
	protected void refresh(DiagramEditPart editPart){
		try {
			DiagramEditorUtil.refresh(editPart, new NullProgressMonitor(), true);
			DiagramEditorUtil.refreshLabels(editPart);
		} catch(Exception e) {
			if(debug){
				System.out.println("Error diagram refresh while publishing : " + e);	 //$NON-NLS-1$
			}
		}
	}
	
	public void updateDiagramElement(final org.eclipse.gmf.runtime.notation.Diagram model, final MethodElement element,  
			final Suppression sup, final MethodConfiguration configuration, final IFilter filter, IProgressMonitor monitor){
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(model);
		AbstractEMFOperation op = new AbstractEMFOperation(domain, "") {	//$NON-NLS-1$
		
			@Override
			protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				org.eclipse.epf.diagram.model.Diagram d = 
					(org.eclipse.epf.diagram.model.Diagram) model.getElement();
				d.setSuppression(sup);
				if(filter == null && configuration != null){
					ProcessConfigurator p = new ProcessConfigurator(config);
					d.setFilter(p);
				}else{
					d.setFilter(filter);
				}
				d.setLinkedElement(element);
				
				return Status.OK_STATUS;
			}
		
		};
		try {
			op.execute(monitor, null);
		} catch (ExecutionException e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		}
		
	}
	
	public void setMethodConfiguration(MethodConfiguration config) {
		this.config = config;
	}
	
	public MethodConfiguration getMethodConfiguration(){
		return config;
	}
}
