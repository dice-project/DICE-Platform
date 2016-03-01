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
package org.eclipse.epf.diagram.wpdd.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.wpdd.providers.DiagramElementTypes;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class DiagramPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createModel1Group());
	}

	/**
	 * Creates "model" palette tool group
	 * @generated
	 */
	private PaletteContainer createModel1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Model1Group_title);
		paletteContainer.add(createControlFlow1CreationTool());
		paletteContainer.add(createWorkProductDescriptor2CreationTool());
		return paletteContainer;
	}

	/**
	 * @modified
	 */
	private ToolEntry createControlFlow1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.Link_3001);
		LinkToolEntry entry = new LinkToolEntry(
				DiagramCoreResources.WPDD_Palette_control_flow_text,
				DiagramCoreResources.WPDD_Palette_control_flow_create_text, types);
		entry.setSmallIcon(DiagramElementTypes.getImageDescriptor(ModelPackage.eINSTANCE.getLink()));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @modified
	 */
	private ToolEntry createWorkProductDescriptor2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(DiagramElementTypes.WorkProductNode_1001);
		NodeToolEntry entry = new NodeToolEntry(
				DiagramCoreResources.AbstractDiagram_WorkProductDescriptor_text,
				 DiagramCoreResources.AbstractDiagram_WorkProductDescriptor_tooltip, types);
		entry.setSmallIcon(DiagramElementTypes.getImageDescriptor(ModelPackage.eINSTANCE.getWorkProductDescriptorNode()));
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
