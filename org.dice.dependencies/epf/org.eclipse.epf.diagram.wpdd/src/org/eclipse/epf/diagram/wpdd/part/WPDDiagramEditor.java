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
/**
 * 
 */
package org.eclipse.epf.diagram.wpdd.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.wpdd.providers.DiagramElementTypes;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Image;

/**
 * Diagram Editor for Work Product Dependency Diagram.
 * @author Shashidhar Kannoori
 * @custom
 */
public class WPDDiagramEditor extends AbstractDiagramEditor {

	/**
	 * @modified
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
	protected PreferencesHint getPreferencesHint() {
		return new PreferencesHint(WPDDiagramEditorPlugin.ID);
	}
	
	@Override
	protected void createActions() {
		super.createActions();
		List<IElementType> elementTypes = new ArrayList<IElementType>();
		elementTypes.add(DiagramElementTypes.WorkProductNode_1001);
		IAction action = createAnAction(
				DiagramCoreResources.AbstractDiagram_WorkProductDescriptor_text, elementTypes, //$NON-NLS-1$
				DiagramConstants.CREATE_WORK_PRODUCT_DESCRIPTOR_NODE, 
				DiagramCoreResources.AbstractDiagram_WorkProductDescriptor_tooltip
				, WPDDiagramEditorPlugin.getInstance().getImageDescriptor("full/obj16/WorkProductDescriptor.gif")); //$NON-NLS-1$
		getActionRegistry().registerAction(action);
	}
	
	@Override
	public void contributeToContextMenu(IMenuManager menu) {
		super.contributeToContextMenu(menu);
		IContributionItem item = menu.find(ActionIds.MENU_DIAGRAM_ADD);
		if(item != null && item instanceof IMenuManager){
			IMenuManager addMenu = (IMenuManager)item;
			if(getGraphicalViewer().getSelectedEditParts().size() == 0){
				addToMenu(addMenu, null, DiagramConstants.DIAGRAM_ADD_MENU_GENERAL_GROUP, 
						true, true);	
				addToMenu(addMenu,DiagramConstants.CREATE_WORK_PRODUCT_DESCRIPTOR_NODE, 
						DiagramConstants.DIAGRAM_ADD_MENU_GENERAL_GROUP, true, true);
			}
		}
	}
	protected String getPartNamePrefix() {
		return DiagramCoreResources.WorkProductDependencyDiagram_prefix; 
	}
	
	/**
	 * @return
	 */
	protected Image getPartImage() {
		return WPDDiagramEditorPlugin.getInstance().getSharedImage(
				"full/etool16/WPDDEditor.gif"); //$NON-NLS-1$
	}
	
	protected CreateUnspecifiedTypeConnectionRequest getCreateControlFlowRequest(){
		List<IElementType> list = new ArrayList<IElementType>();
		list.add(DiagramElementTypes.Link_3001);
		CreateUnspecifiedTypeConnectionRequest connectionRequest = 
			new CreateUnspecifiedTypeConnectionRequest(list, false,
					WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		return connectionRequest;
	}
	
	@Override
	protected boolean isOrphan(EObject modelElement) {
		return modelElement instanceof Node && BridgeHelper.getMethodElement(modelElement) == null;
	}

}
