/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createFlows1Group());
		paletteRoot.add(createNodes2Group());
	}

	/**
	 * @modified
	 */
	private PaletteContainer createFlows1Group() {
		PaletteContainer paletteContainer = new PaletteDrawer(
				DiagramCoreResources.ActivityDiagram_Palette_flows_title);
		paletteContainer
				.setDescription(DiagramCoreResources.ActivityDiagram_Palette_flows_create_text);
		paletteContainer.add(createControlFlow1CreationTool());
		return paletteContainer;
	}

	/**
	 * @modified
	 */
	private PaletteContainer createNodes2Group() {
		PaletteContainer paletteContainer = new PaletteDrawer(
				DiagramCoreResources.ActivityDiagram_Palette_nodes_titles);
		paletteContainer
				.setDescription(DiagramCoreResources.ActivityDiagram_Palette_nodes_group_text);
		paletteContainer.add(createActivityPartition1CreationTool());
		paletteContainer.add(createStartNode2CreationTool());
		paletteContainer.add(createEndNode6CreationTool());
		paletteContainer.add(createForkNode3CreationTool());
		paletteContainer.add(createJoinNode7CreationTool());
		paletteContainer.add(createDecisionNode5CreationTool());
		paletteContainer.add(createMergeNode4CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createActivity9CreationTool());
		paletteContainer.add(createIteration11CreationTool());
		paletteContainer.add(createPhase10CreationTool());
		paletteContainer.add(createMilestone13CreationTool());
		paletteContainer.add(createTaskDescriptor12CreationTool());
		return paletteContainer;
	}

	/**
	 * @modified
	 */
	private ToolEntry createControlFlow1CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.ControlFlow_3001);
		LinkToolEntry entry = new LinkToolEntry(
				DiagramCoreResources.ActivityDiagram_Palette_control_flow_text,
				DiagramCoreResources.ActivityDiagram_Palette_control_flow_create_text, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getControlFlow(),
				UMLElementTypes.ControlFlow_3001)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createActivityPartition1CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(2);
		types.add(UMLElementTypes.ActivityPartition_1008);
		types.add(UMLElementTypes.ActivityPartition_2001);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Partition_Node_text,
				DiagramCoreResources.ActivityDiagram_Partition_Node_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getActivityPartition(),
				UMLElementTypes.ActivityPartition_1008)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createStartNode2CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.InitialNode_1004);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_StartNode_text,
				DiagramCoreResources.ActivityDiagram_StartNode_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getInitialNode(),
				UMLElementTypes.InitialNode_1004)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createForkNode3CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.ForkNode_1003);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Fork_Node_text,
				DiagramCoreResources.ActivityDiagram_Fork_Node_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getForkNode(),
				UMLElementTypes.ForkNode_1003)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createMergeNode4CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.MergeNode_1002);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Merge_Node_text,
				DiagramCoreResources.ActivityDiagram_Merge_Node_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getMergeNode(),
				UMLElementTypes.MergeNode_1002)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createDecisionNode5CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.DecisionNode_1005);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_DecisionNode_text,
				DiagramCoreResources.ActivityDiagram_DecisionNode_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getDecisionNode(),
				UMLElementTypes.DecisionNode_1005)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createEndNode6CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.ActivityFinalNode_1001);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_EndNode_text, 
				DiagramCoreResources.ActivityDiagram_EndNode_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getActivityFinalNode(),
				UMLElementTypes.ActivityFinalNode_1001)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createJoinNode7CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.JoinNode_1006);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Join_Node_text,
				DiagramCoreResources.ActivityDiagram_Join_Node_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(
				UMLPackage.eINSTANCE.getJoinNode(),
				UMLElementTypes.JoinNode_1006)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createActivity9CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.StructuredActivityNode_1007);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Activity_text, 
				DiagramCoreResources.ActivityDiagram_Activity_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(null,
				UMLElementTypes.StructuredActivityNode_1007)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createPhase10CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.StructuredActivityNode_1010);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Phase_text, 
				DiagramCoreResources.ActivityDiagram_Phase_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(null,
				UMLElementTypes.StructuredActivityNode_1010)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createIteration11CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.StructuredActivityNode_1011);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Iteration_text,
				DiagramCoreResources.ActivityDiagram_Iteration_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(null,
				UMLElementTypes.StructuredActivityNode_1011)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createTaskDescriptor12CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.ActivityParameterNode_1009);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.AbstractDiagram_TaskDescriptor_text,
				DiagramCoreResources.AbstractDiagram_TaskDescriptor_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(null,
				UMLElementTypes.ActivityParameterNode_1009)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createMilestone13CreationTool() {
		List/* <IElementType> */types = new ArrayList/* <IElementType> */(1);
		types.add(UMLElementTypes.ActivityParameterNode_1012);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.ActivityDiagram_Milestone_text,
				DiagramCoreResources.ActivityDiagram_Milestone_tooltip, types);
		entry.setSmallIcon(UMLElementTypes.getImageDescriptor(null,
				UMLElementTypes.ActivityParameterNode_1012)); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @modified
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes) {
				@Override
				protected void updateTargetRequest() {
					CreateRequest req = getCreateRequest();
					if (isInState(STATE_DRAG_IN_PROGRESS)) {
						req.setSize(null);
						req.setLocation(getLocation());
					} else {
						req.setSize(null);
						req.setLocation(getLocation());
					}
				}
			};
			tool.setProperties(getToolProperties());
			BridgeHelper.elementTypes.add((IElementType) elementTypes
					.get(elementTypes.size() - 1));
			return tool;
		}

	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
