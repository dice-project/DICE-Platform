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
package org.eclipse.epf.diagram.add.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.add.ADDImages;
import org.eclipse.epf.diagram.add.edit.parts.ActivityDetailDiagramEditPart;
import org.eclipse.epf.diagram.add.service.DiagramResetService;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.actions.DiagramActionsService;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Image;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class ADDiagramEditor extends AbstractDiagramEditor {

	DiagramActionsService actionService = null;
	/**
	 * @generated
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramRootEditPart root = (DiagramRootEditPart) getDiagramGraphicalViewer()
				.getRootEditPart();
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
	}

	@Override
	protected void setGraphicalViewer(GraphicalViewer viewer) {
		super.setGraphicalViewer(viewer);
		if(actionService != null){
			actionService.setGraphicalViewer(viewer);
		}
	}
	/**
	 * @author skannoor
	 */
	public void cleanUpDiagram() {
		DiagramResetService service = new DiagramResetService(getDiagramManager().getEditingDomain(),
				getGraphicalViewer(), getEditDomain(), getActionRegistry());
		service.cleanUpDiagram();
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#contributeToContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void contributeToContextMenu(IMenuManager menu) {
		super.contributeToContextMenu(menu);

		
		boolean canModify = isEditable();

		IAction action = getActionRegistry().getAction(DiagramConstants.RESET_DIAGRAM_LAYOUT);
		if (action.isEnabled() && canModify) {
			menu.insertBefore(ActionIds.ACTION_SHOW_PROPERTIES_VIEW, action);
		}
		IContributionItem item = menu.find(ActionIds.ACTION_TOOLBAR_ARRANGE_ALL);
		if(item != null){
			item.setVisible(false);
		}
		IContributionItem item1 = menu.find(ActionIds.MENU_FORMAT);
		if(item1 != null){
			if(item1 instanceof IMenuManager){
				((IMenuManager)item1).setVisible(false);
			}
		}
		
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();
		Action resetLayoutAction = new Action(
				DiagramCoreResources.ActivityDetailDiagramEditor_ResetDiagramLayout_text) { //$NON-NLS-1$

			public void run() {
				//getActionRegistry().getAction(DiagramConstants.REFRESH).run();
				DiagramEditorUtil.refresh(getDiagramEditPart(), new NullProgressMonitor());
				if (getGraphicalViewer().getContents() instanceof ActivityDetailDiagramEditPart) {
					ActivityDetailDiagramEditPart dep = (ActivityDetailDiagramEditPart) getGraphicalViewer()
							.getContents();
					List<EditPart> local = new ArrayList<EditPart>();
					if(dep.getChildren() != null && !dep.getChildren().isEmpty()){
						for(Iterator iterator = dep.getChildren().iterator(); iterator.hasNext();){
							Object next = iterator.next();
							if(!(next instanceof NoteEditPart)){
								local.add((EditPart)next);
							}
						}
					}
					dep.getRecentlyAddedParts().addAll(local);
					cleanUpDiagram();
				}
			}

			public boolean isEnabled() {
				return getGraphicalViewer().getSelectedEditParts().size() == 0;
			}

			public String getId() {
				return DiagramConstants.RESET_DIAGRAM_LAYOUT;
			}

		};
		resetLayoutAction.setDisabledImageDescriptor(ADDImages.DISABLED_IMG_DESC_RESET_DIAGRAM_LAYOUT);
		resetLayoutAction.setImageDescriptor(ADDImages.IMG_DESC_RESET_DIAGRAM_LAYOUT);
		getActionRegistry().registerAction(resetLayoutAction);
	}
	
	public void resetLayout() {
		if(((ActivityDetailDiagram)getDiagram().getElement()).isAutoLayout()){
			IAction action = getActionRegistry().getAction(DiagramConstants.RESET_DIAGRAM_LAYOUT);
			action.run();
		}
		else {
			layoutRecentlyAdded();
		}
	}
	
	private void layoutRecentlyAdded() {
		getGraphicalViewer().getContents().refresh();
		getDiagramEditPart().getViewport().validate();
		cleanUpDiagram();
		EObject diagramElement = getDiagram().getElement();
		if(diagramElement instanceof Diagram){
			Activity activity = (Activity)((Diagram)diagramElement).getLinkedElement();
			if(TngUtil.isLocked(activity)){
				// Flush all commands, means will not save.
				getCommandStack().flush();
				// Fire dirty flag property change, no dirty flag on editor.
				firePropertyChange(PROP_DIRTY);
			}
		}
	}
	
	protected String getPartNamePrefix() {
		return DiagramCoreResources.ActivityDetailDiagram_prefix; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	protected Image getPartImage() {
		return ActivityDetailDiagramEditorPlugin.getInstance().getSharedImage(
				"full/etool16/ADDEditor.gif"); //$NON-NLS-1$
	}
		
	/**
	 * Clients call this meethod if needed to refresh and do reset layout for 
	 * activity detail diagram whose attribute is autolayout = true.
	 */
	public void runResetLayoutAction(){
		layoutRecentlyAdded();
		if(((ActivityDetailDiagram)getDiagram().getElement()).isAutoLayout()){
			IAction action = getActionRegistry().getAction(DiagramConstants.RESET_DIAGRAM_LAYOUT);
			action.run();
		}
	}
	
	@Override
	protected boolean isOrphan(EObject modelElement) {
		return modelElement instanceof Node && BridgeHelper.getMethodElement(modelElement) == null;
	}
}
