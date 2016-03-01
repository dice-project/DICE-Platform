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

import java.util.List;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import java.util.ArrayList;

import org.eclipse.epf.diagram.add.providers.DiagramElementTypes;

import org.eclipse.gef.palette.PaletteDrawer;

import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class DiagramPaletteFactory {

	/**
	 * @modified
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		//		paletteRoot.add(createNodes1Group());
		//		paletteRoot.add(createLinks2Group());
	}

	/**
	 * @generated
	 */
	private PaletteContainer createNodes1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Nodes1Group_title);
		paletteContainer.add(createRoleNode1CreationTool());
		paletteContainer.add(createRoleTaskComposite2CreationTool());
		paletteContainer.add(createTaskNode3CreationTool());
		paletteContainer.add(createWorkProductDescriptorNode4CreationTool());
		paletteContainer.add(createWorkProductComposite5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private PaletteContainer createLinks2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Links2Group_title);
		paletteContainer.add(createLink1CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleNode1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.RoleNode_2001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.RoleNode1CreationTool_title,
				Messages.RoleNode1CreationTool_desc, types);
		entry.setSmallIcon(DiagramElementTypes
				.getImageDescriptor(DiagramElementTypes.RoleNode_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleTaskComposite2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.RoleTaskComposite_1001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.RoleTaskComposite2CreationTool_title,
				Messages.RoleTaskComposite2CreationTool_desc, types);
		entry
				.setSmallIcon(DiagramElementTypes
						.getImageDescriptor(DiagramElementTypes.RoleTaskComposite_1001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTaskNode3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.TaskNode_2002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.TaskNode3CreationTool_title,
				Messages.TaskNode3CreationTool_desc, types);
		entry.setSmallIcon(DiagramElementTypes
				.getImageDescriptor(DiagramElementTypes.TaskNode_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createWorkProductDescriptorNode4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.WorkProductDescriptorNode_2003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.WorkProductDescriptorNode4CreationTool_title,
				Messages.WorkProductDescriptorNode4CreationTool_desc, types);
		entry
				.setSmallIcon(DiagramElementTypes
						.getImageDescriptor(DiagramElementTypes.WorkProductDescriptorNode_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createWorkProductComposite5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.WorkProductComposite_1002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.WorkProductComposite5CreationTool_title,
				Messages.WorkProductComposite5CreationTool_desc, types);
		entry
				.setSmallIcon(DiagramElementTypes
						.getImageDescriptor(DiagramElementTypes.WorkProductComposite_1002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.Link_3001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Link1CreationTool_title,
				Messages.Link1CreationTool_desc, types);
		entry.setSmallIcon(DiagramElementTypes
				.getImageDescriptor(DiagramElementTypes.Link_3001));
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
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
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
