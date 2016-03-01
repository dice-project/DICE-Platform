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

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.WorkProductNode;

import org.eclipse.epf.diagram.wpdd.edit.parts.LinkEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.LinkNameEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductDependencyDiagramEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeNameEditPart;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented 
 * by a domain model object.
 *
 * @generated
 */
public class DiagramVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = WPDDiagramEditorPlugin
			.getInstance().getBundle().getSymbolicName()
			+ "/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (WorkProductDependencyDiagramEditPart.MODEL_ID.equals(view
					.getType())) {
				return WorkProductDependencyDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				WPDDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "	//$NON-NLS-1$
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		EClass domainElementMetaclass = domainElement.eClass();
		return getDiagramVisualID(domainElement, domainElementMetaclass);
	}

	/**
	 * @generated
	 */
	private static int getDiagramVisualID(EObject domainElement,
			EClass domainElementMetaclass) {
		if (ModelPackage.eINSTANCE.getWorkProductDependencyDiagram()
				.isSuperTypeOf(domainElementMetaclass)
				&& isDiagramWorkProductDependencyDiagram_79((WorkProductDependencyDiagram) domainElement)) {
			return WorkProductDependencyDiagramEditPart.VISUAL_ID;
		}
		return getUnrecognizedDiagramID(domainElement);
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		EClass domainElementMetaclass = domainElement.eClass();
		return getNodeVisualID(containerView, domainElement,
				domainElementMetaclass, null);
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView,
			EObject domainElement, EClass domainElementMetaclass,
			String semanticHint) {
		String containerModelID = getModelID(containerView);
		if (!WorkProductDependencyDiagramEditPart.MODEL_ID
				.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (WorkProductDependencyDiagramEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = WorkProductDependencyDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		int nodeVisualID = semanticHint != null ? getVisualID(semanticHint)
				: -1;
		switch (containerVisualID) {
		case WorkProductNodeEditPart.VISUAL_ID:
			if (WorkProductNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return WorkProductNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedWorkProductNode_1001ChildNodeID(
					domainElement, semanticHint);
		case WorkProductDependencyDiagramEditPart.VISUAL_ID:
			if ((semanticHint == null || WorkProductNodeEditPart.VISUAL_ID == nodeVisualID)
					&& ModelPackage.eINSTANCE.getWorkProductNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeWorkProductNode_1001((WorkProductNode) domainElement))) {
				return WorkProductNodeEditPart.VISUAL_ID;
			}
			return getUnrecognizedWorkProductDependencyDiagram_79ChildNodeID(
					domainElement, semanticHint);
		case LinkEditPart.VISUAL_ID:
			if (LinkNameEditPart.VISUAL_ID == nodeVisualID) {
				return LinkNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedLink_3001LinkLabelID(semanticHint);
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		EClass domainElementMetaclass = domainElement.eClass();
		return getLinkWithClassVisualID(domainElement, domainElementMetaclass);
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement,
			EClass domainElementMetaclass) {
		if (ModelPackage.eINSTANCE.getLink().isSuperTypeOf(
				domainElementMetaclass)
				&& (domainElement == null || isLinkWithClassLink_3001((Link) domainElement))) {
			return LinkEditPart.VISUAL_ID;
		} else {
			return getUnrecognizedLinkWithClassID(domainElement);
		}
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isDiagramWorkProductDependencyDiagram_79(
			WorkProductDependencyDiagram element) {
		return true;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedDiagramID(EObject domainElement) {
		return -1;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeWorkProductNode_1001(WorkProductNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedWorkProductNode_1001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedWorkProductDependencyDiagram_79ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedLink_3001LinkLabelID(String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedLinkWithClassID(EObject domainElement) {
		return -1;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isLinkWithClassLink_3001(Link element) {
		return true;
	}
}
