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
package org.eclipse.epf.migration.diagram.ad.map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ForkNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.InitialNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.InitialNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.JoinNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeNameEditPart;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.add.edit.parts.RoleTaskCompositeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductCompositeEditPart;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeNameEditPart;
import org.eclipse.epf.diagram.wpdd.part.WPDDiagramEditorPlugin;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.Property;
import org.eclipse.gmf.runtime.common.ui.services.parser.CommonParserHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.DescriptionStyle;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.StructuredActivityNode;

/**
 * @author Shilpa Toraskar
 * @since 1.2
 */

public class MapUtil {

	/**
	 * Create notation node in diagram corresponding to activity node
	 * 
	 * @param diagram
	 * @param umlNode
	 * @return
	 */
	public static Node createNotationNode_AD(Diagram diagram, Object obj) {
		Node notationNode = null;

		ActivityNode umlNode = obj instanceof ActivityNode ? (ActivityNode) obj
				: null;
		if (umlNode instanceof StructuredActivityNode) {
			String type = BridgeHelper.getEAnnotationType(umlNode,
					BridgeHelper.UMA_TYPE);
			if (type.equalsIgnoreCase(BridgeHelper.UMA_PHASE)) {
				// Phase
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(StructuredActivityNode2EditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						StructuredActivityNodeName2EditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				
			} else if (type.equalsIgnoreCase(BridgeHelper.UMA_ITERATION)) {
				// Iteration
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(StructuredActivityNode3EditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						StructuredActivityNodeName3EditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else {
				// Activity
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(StructuredActivityNodeEditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						StructuredActivityNodeNameEditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			}
			
			// set default font style
			setDefaultFontStyle(notationNode);
			
		} else if (umlNode instanceof InitialNode) {
			notationNode = ViewService.createNode(diagram, umlNode,
					new Integer(InitialNodeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(notationNode, umlNode, new Integer(
					InitialNodeNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		} else if (umlNode instanceof DecisionNode) {
			notationNode = ViewService.createNode(diagram, umlNode,
					new Integer(DecisionNodeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(notationNode, umlNode, new Integer(
					DecisionNodeNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		} else if (umlNode instanceof MergeNode) {
			notationNode = ViewService.createNode(diagram, umlNode,
					new Integer(MergeNodeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(notationNode, umlNode, new Integer(
					MergeNodeNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		} else if (umlNode instanceof ForkNode) {
			notationNode = ViewService.createNode(diagram, umlNode,
					new Integer(ForkNodeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		} else if (umlNode instanceof JoinNode) {
			notationNode = ViewService.createNode(diagram, umlNode,
					new Integer(JoinNodeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		} else if (umlNode instanceof FinalNode) {
			notationNode = ViewService
					.createNode(diagram, umlNode, new Integer(
							ActivityFinalNodeEditPart.VISUAL_ID).toString(),
							ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(notationNode, umlNode, new Integer(
					ActivityFinalNodeNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		} else if (umlNode instanceof ActivityParameterNode) {
			String type = BridgeHelper.getEAnnotationType(umlNode,
					BridgeHelper.UMA_TYPE);
			if (type.equalsIgnoreCase(BridgeHelper.UMA_MILESTONE)) {
				// Milestone
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(ActivityParameterNode2EditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService.createNode(notationNode, umlNode, new Integer(
						ActivityParameterNodeName2EditPart.VISUAL_ID)
						.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			} else if (type.equalsIgnoreCase(BridgeHelper.UMA_TASK_DESCRIPTOR)) {
				// TaskDescriptor
				notationNode = ViewService.createNode(diagram, umlNode,
						new Integer(ActivityParameterNodeEditPart.VISUAL_ID)
								.toString(),
						ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				ViewService
						.createNode(notationNode, umlNode, new Integer(
								ActivityParameterNodeNameEditPart.VISUAL_ID)
								.toString(),
								ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			}

			//	set default font style
			setDefaultFontStyle(notationNode);
		}

		return notationNode;
	}

	/**
	 * Create notation node for model node in ADD and WPD
	 * @param diagram
	 * @param obj
	 * @return
	 */
	public static Node createNotationNode_ADDorWPD(Diagram diagram, org.eclipse.epf.diagram.model.Node obj) {
		Node notationNode = null;
		org.eclipse.epf.diagram.model.Node node = obj instanceof org.eclipse.epf.diagram.model.Node ? (org.eclipse.epf.diagram.model.Node) obj
				: null;
		if (node instanceof RoleTaskComposite) {
			notationNode = ViewService.createNode(diagram, node, new Integer(
					RoleTaskCompositeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			/*
			 * ViewService.createNode(notationNode, node, new Integer(
			 * RoleTaskCompositeRoleTaskCompartmentEditPart.VISUAL_ID).toString(),
			 * ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			 */
		} else if (node instanceof WorkProductComposite) {
			notationNode = ViewService.createNode(diagram, node, new Integer(
					WorkProductCompositeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			/*
			 * ViewService.createNode(notationNode, node, new Integer(
			 * WorkProductCompositeWorkProductCompartmentEditPart.VISUAL_ID).toString(),
			 * ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			 */
		} else if (node instanceof WorkProductNode) {
			notationNode = ViewService.createNode(diagram, node, new Integer(
					WorkProductNodeEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(notationNode, node, new Integer(
					WorkProductNodeNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		}
		
		// set default font style for notation node
		setDefaultFontStyle(notationNode);
		return notationNode;
	}

	/**
	 * 
	 * Create text for free form text
	 * @param diagram
	 * @param node
	 * @return
	 */
	public static Node createText(Diagram diagram,
			org.eclipse.epf.diagram.model.Node node) {

		// create text
		Node text = ViewService.createNode(diagram, ViewType.TEXT,
				ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		
		ViewService.createNode(text, ViewType.DIAGRAM_NAME,
				ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		ViewService.createNode(text, CommonParserHint.DESCRIPTION,
				ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

		// create description
		GraphNode graphNode = (GraphNode) node.getObject();
		String description = ""; //$NON-NLS-1$
		Property prop = GraphicalDataHelper.getPropertyByKey(graphNode
				.getProperty(), GraphicalDataHelper.GRAPH_NODE_FREE_TEXT);

		if (prop != null)
			description = prop.getValue();
		DescriptionStyle descStyle = (DescriptionStyle) text
				.getStyle(NotationPackage.Literals.DESCRIPTION_STYLE);
		descStyle.setDescription(description);
		
		// 
		IPreferenceStore store = (IPreferenceStore) ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT
				.getPreferenceStore();

		// create fill style
		FillStyle fillStyle = (FillStyle) text
				.getStyle(NotationPackage.Literals.FILL_STYLE);
		if (fillStyle != null) {
			// fill color
			RGB fillRGB = PreferenceConverter.getColor(store,
					IPreferenceConstants.PREF_FILL_COLOR);
			fillStyle.setFillColor(FigureUtilities.RGBToInteger(fillRGB)
					.intValue());
		}

		// create line style
		LineStyle lineStyle = (LineStyle) text
				.getStyle(NotationPackage.Literals.LINE_STYLE);
		if (lineStyle != null) {
			// line color
			RGB lineRGB = PreferenceConverter.getColor(store,
					IPreferenceConstants.PREF_LINE_COLOR);
			lineStyle.setLineColor(FigureUtilities.RGBToInteger(lineRGB)
					.intValue());
		}

		// create font style
		FontStyle fontStyle = (FontStyle) text
				.getStyle(NotationPackage.Literals.FONT_STYLE);
		Font oldFont = null;
		if (fontStyle != null) {
			// get font properties
			String fontName = getPropertyValue(graphNode,
					TemplateConstants.PROPERTY_FONT_NAME);
			if (fontName == null) fontName = TemplateConstants.DEFAULT_FONT_NAME;
			String oldFontStyleStr = getPropertyValue(graphNode,
					TemplateConstants.PROPERTY_FONT_STYLE);	
			String fontHeightStr = getPropertyValue(graphNode,
					TemplateConstants.PROPERTY_FONT_HEIGHT);			
			String fontRed = getPropertyValue(graphNode,
					TemplateConstants.PROPERTY_FONT_RED);
			String fontGreen = getPropertyValue(graphNode,
					TemplateConstants.PROPERTY_FONT_GREEN);
			String fontBlue = getPropertyValue(graphNode,
					TemplateConstants.PROPERTY_FONT_BLUE);
			
			int red = 0;
			int green = 0;
			int blue = 0;

			if (fontName != null)
				fontStyle.setFontName(fontName);
			
			int oldFontStyle = 0;
			if (oldFontStyleStr != null) {
				oldFontStyle = new Integer(oldFontStyleStr).intValue();
				if (oldFontStyle == 1)
					fontStyle.setBold(true);
				if (oldFontStyle == 2)
					fontStyle.setItalic(true);
				if (oldFontStyle == 3) {
					fontStyle.setBold(true);
					fontStyle.setItalic(true);
				}
			}
			
			// set font height
			int fontHeight = TemplateConstants.DEFAULT_FONT_SIZE;
			if (fontHeightStr != null)
				fontHeight = new Integer(fontHeightStr).intValue();
			fontStyle.setFontHeight(fontHeight);
			
			// set font RGB color
			if (fontRed != null)
				red = new Integer(fontRed).intValue();
			if (fontGreen != null)
				green = new Integer(fontGreen).intValue();
			if (fontBlue != null)
				blue = new Integer(fontBlue).intValue();

			RGB fontRGB = new RGB(red, green, blue);
			fontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
					.intValue());			
			
			// make old font as well
			oldFont = new Font(null, fontName, fontHeight, oldFontStyle);
		}
	
		Dimension d = FigureUtilities.getTextExtents(description, oldFont);
		if (oldFont != null) {
			oldFont.dispose();
		}
		int noOfLines = 1;
		if (d != null && d.width > 0) {
			if (node.getWidth() == -1)
				noOfLines = 1;
			else {
				int div = d.width / node.getWidth();		
				int mod = d.width % node.getWidth();
//				if (mod != 0)
					div++;
				noOfLines = div;
			}
		}

		int textHeight = d.height;		

		// create layout contraint
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
//		bounds.setHeight(noOfLines * textHeight);
		if (node.getWidth() == -1) {
			int textWidth = d.width;
			bounds.setWidth(textWidth);
		}
		else
			bounds.setWidth(node.getWidth());
		bounds.setX(node.getLocation().x);
		bounds.setY(node.getLocation().y);

		text.setLayoutConstraint(bounds);
		return text;
	}

	/**
	 * Create edge for AD diagram
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @param actEdge
	 * @return
	 */
	public static Edge createEdge_AD(Node sourceNode, Node targetNode,
			ActivityEdge actEdge) {
		if (actEdge instanceof ControlFlow) {
			Edge edge = ViewService.createEdge(sourceNode, targetNode, actEdge,
					new Integer(ControlFlowEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(edge, actEdge, new Integer(
					ControlFlowNameEditPart.VISUAL_ID).toString(),
					ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			return edge;
		}
		

		return null;
	}

	
	/**
	 * Create edge for WPD diagram
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @param actEdge
	 * @return
	 */
	public static Edge createEdge_WPD(Node sourceNode, Node targetNode,
			EObject actEdge) {
		if (actEdge instanceof Link) {
			Edge edge = ViewService.createEdge(sourceNode, targetNode, actEdge,
					new Integer(org.eclipse.epf.diagram.wpdd.edit.parts.LinkEditPart.VISUAL_ID).toString(),
					WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(edge, actEdge, new Integer(
					org.eclipse.epf.diagram.wpdd.edit.parts.LinkEditPart.VISUAL_ID).toString(),
					WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			return edge;
		}
		return null;
	}
	
	
	/**
	 * Create edge for ADD diagram
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @param actEdge
	 * @return
	 */
	public static Edge createEdge_ADD(Node sourceNode, Node targetNode,
			EObject actEdge) {
		if (actEdge instanceof Link) {
			Edge edge = ViewService.createEdge(sourceNode, targetNode, actEdge,
					new Integer(org.eclipse.epf.diagram.add.edit.parts.LinkEditPart.VISUAL_ID).toString(),
					ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			ViewService.createNode(edge, actEdge, new Integer(
					org.eclipse.epf.diagram.add.edit.parts.LinkEditPart.VISUAL_ID).toString(),
					ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			return edge;
		}
		return null;
	}
	
	/**
	 * 
	 */
	private static String getPropertyValue(GraphNode graphNode, String key) {
		Property prop = GraphicalDataHelper.getPropertyByKey(graphNode
				.getProperty(), key);

		if (prop != null) {
			return prop.getValue();
		}

		return null;
	}

	/**
	 * Calculates no of lines in the node text based on text width and width of
	 * figure
	 * 
	 * @param node
	 * @return
	 */
	public static int getNoOfLines(Node node) {
		int noOfLines = 1;
		if (node.getElement() != null
				&& node.getElement() instanceof NamedElement) {
			String text = ((NamedElement) node.getElement()).getName();
			Font f = TemplateConstants.DEFAULT_FONT;
			Dimension d = FigureUtilities.getTextExtents(text, f);

			Bounds bounds = (Bounds) node.getLayoutConstraint();
			if (d != null && d.width > 0) {
				int div = d.width / bounds.getWidth();
				int mod = d.width % bounds.getWidth();
				if (mod != 0)
					div++;
				noOfLines = div;
			}
		}
		return noOfLines;
	}

	/**
	 * Return height of text of th enode
	 * 
	 * @param node
	 * @return
	 */
	public static int getTextHeight(Node node) {
		if (node.getElement() != null
				&& node.getElement() instanceof NamedElement) {
			String text = ((NamedElement) node.getElement()).getName();
			Font f = TemplateConstants.DEFAULT_FONT;
			Dimension d = FigureUtilities.getTextExtents(text, f);

			if (d != null)
				return d.height;
		}
		return 0;
	}

	/**
	 * Convert point to terminal string
	 * 
	 * @param p
	 * @return
	 */
	public static String composeTerminalString(PrecisionPoint p) {
		final char TERMINAL_START_CHAR = '(';
		final char TERMINAL_DELIMITER_CHAR = ',';
		final char TERMINAL_END_CHAR = ')';

		StringBuffer s = new StringBuffer(24);
		s.append(TERMINAL_START_CHAR); // 1 char
		s.append((float) p.preciseX); // 10 chars
		s.append(TERMINAL_DELIMITER_CHAR); // 1 char
		s.append((float) p.preciseY); // 10 chars
		s.append(TERMINAL_END_CHAR); // 1 char
		return s.toString(); // 24 chars max (+1 for safety, i.e. for string
								// termination)
	}

	/**
	 * Get relative anchor location
	 * 
	 * @param p
	 * @param bounds
	 * @return
	 */
	public static PrecisionPoint getAnchorRelativeLocation(Point p,
			Bounds bounds) {
		PrecisionPoint relLocation;
		Point temp = new Point(p);
		if (p.x < bounds.getX() || p.x > bounds.getX() + bounds.getWidth()
				|| p.y < bounds.getY()
				|| p.y > bounds.getY() + bounds.getHeight()) {
			if (p.x < bounds.getX() || p.x > bounds.getX() + bounds.getWidth()) {
				temp.x = p.x < bounds.getX() ? bounds.getX() : bounds.getX()
						+ bounds.getWidth();
			}
			if (p.y < bounds.getY() || p.y > bounds.getY() + bounds.getHeight()) {
				temp.y = p.y < bounds.getY() ? bounds.getY() : bounds.getY()
						+ bounds.getHeight();
			}
			relLocation = new PrecisionPoint((double) (temp.x - bounds.getX())
					/ bounds.getWidth(), (double) (temp.y - bounds.getY())
					/ bounds.getHeight());
		} else {

			relLocation = new PrecisionPoint((double) (temp.x - bounds.getX())
					/ bounds.getWidth(), (double) (temp.y - bounds.getY())
					/ bounds.getHeight());
		}
		return relLocation;
	}

	
	/**
	 * Set default font style for notation node
	 * @param notationNode
	 */
	private static void setDefaultFontStyle(Node notationNode) {
		//	create font style
		FontStyle fontStyle = (FontStyle) notationNode
				.getStyle(NotationPackage.Literals.FONT_STYLE);

		if (fontStyle != null) {

			// set font name
			String fontName = TemplateConstants.DEFAULT_FONT_NAME;
			if (fontName != null)
				fontStyle.setFontName(fontName);

			// set font height
			int fontHeight = TemplateConstants.DEFAULT_FONT_SIZE;
			fontStyle.setFontHeight(fontHeight);
		}
	}
}
