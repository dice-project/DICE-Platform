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

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.add.edit.parts.ActivityDetailDiagramEditPart;
import org.eclipse.epf.diagram.add.edit.parts.LinkEditPart;
import org.eclipse.epf.diagram.add.edit.parts.LinkNameEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleNodeNameEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleTaskCompositeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleTaskCompositeRoleTaskCompartmentEditPart;
import org.eclipse.epf.diagram.add.edit.parts.TaskNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.TaskNodeNameEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductCompositeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductCompositeWorkProductCompartmentEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductDescriptorNodeEditPart;

import org.eclipse.epf.diagram.add.edit.parts.WorkProductDescriptorNodeNameEditPart;

import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;

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
	private static final String DEBUG_KEY = ActivityDetailDiagramEditorPlugin
			.getInstance().getBundle().getSymbolicName()
			+ "/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ActivityDetailDiagramEditPart.MODEL_ID.equals(view.getType())) {
				return ActivityDetailDiagramEditPart.VISUAL_ID;
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
				ActivityDetailDiagramEditorPlugin.getInstance().logError(
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
		if (ModelPackage.eINSTANCE.getActivityDetailDiagram().isSuperTypeOf(
				domainElementMetaclass)
				&& isDiagramActivityDetailDiagram_79((ActivityDetailDiagram) domainElement)) {
			return ActivityDetailDiagramEditPart.VISUAL_ID;
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
		if (!ActivityDetailDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (ActivityDetailDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ActivityDetailDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		int nodeVisualID = semanticHint != null ? getVisualID(semanticHint)
				: -1;
		switch (containerVisualID) {
		case RoleTaskCompositeEditPart.VISUAL_ID:
			if (RoleTaskCompositeRoleTaskCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return RoleTaskCompositeRoleTaskCompartmentEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || RoleNodeEditPart.VISUAL_ID == nodeVisualID)
					&& ModelPackage.eINSTANCE.getRoleNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeRoleNode_2001((RoleNode) domainElement))) {
				return RoleNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || TaskNodeEditPart.VISUAL_ID == nodeVisualID)
					&& ModelPackage.eINSTANCE.getTaskNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeTaskNode_2002((TaskNode) domainElement))) {
				return TaskNodeEditPart.VISUAL_ID;
			}
			return getUnrecognizedRoleTaskComposite_1001ChildNodeID(
					domainElement, semanticHint);
		case WorkProductCompositeEditPart.VISUAL_ID:
			if (WorkProductCompositeWorkProductCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return WorkProductCompositeWorkProductCompartmentEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || WorkProductDescriptorNodeEditPart.VISUAL_ID == nodeVisualID)
					&& ModelPackage.eINSTANCE.getWorkProductDescriptorNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeWorkProductDescriptorNode_2003((WorkProductDescriptorNode) domainElement))) {
				return WorkProductDescriptorNodeEditPart.VISUAL_ID;
			}
			return getUnrecognizedWorkProductComposite_1002ChildNodeID(
					domainElement, semanticHint);
		case RoleNodeEditPart.VISUAL_ID:
			if (RoleNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return RoleNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedRoleNode_2001ChildNodeID(domainElement,
					semanticHint);
		case TaskNodeEditPart.VISUAL_ID:
			if (TaskNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return TaskNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedTaskNode_2002ChildNodeID(domainElement,
					semanticHint);
		case WorkProductDescriptorNodeEditPart.VISUAL_ID:
			if (WorkProductDescriptorNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return WorkProductDescriptorNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedWorkProductDescriptorNode_2003ChildNodeID(
					domainElement, semanticHint);
		case RoleTaskCompositeRoleTaskCompartmentEditPart.VISUAL_ID:
			return getUnrecognizedRoleTaskCompositeRoleTaskCompartment_5001ChildNodeID(
					domainElement, semanticHint);
		case WorkProductCompositeWorkProductCompartmentEditPart.VISUAL_ID:
			return getUnrecognizedWorkProductCompositeWorkProductCompartment_5002ChildNodeID(
					domainElement, semanticHint);
		case ActivityDetailDiagramEditPart.VISUAL_ID:
			if ((semanticHint == null || RoleTaskCompositeEditPart.VISUAL_ID == nodeVisualID)
					&& ModelPackage.eINSTANCE.getRoleTaskComposite()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeRoleTaskComposite_1001((RoleTaskComposite) domainElement))) {
				return RoleTaskCompositeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || WorkProductCompositeEditPart.VISUAL_ID == nodeVisualID)
					&& ModelPackage.eINSTANCE.getWorkProductComposite()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeWorkProductComposite_1002((WorkProductComposite) domainElement))) {
				return WorkProductCompositeEditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityDetailDiagram_79ChildNodeID(
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
	private static boolean isDiagramActivityDetailDiagram_79(
			ActivityDetailDiagram element) {
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
	private static boolean isNodeRoleTaskComposite_1001(
			RoleTaskComposite element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeWorkProductComposite_1002(
			WorkProductComposite element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeRoleNode_2001(RoleNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeTaskNode_2002(TaskNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeWorkProductDescriptorNode_2003(
			WorkProductDescriptorNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedRoleTaskComposite_1001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedWorkProductComposite_1002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedRoleNode_2001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedTaskNode_2002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedWorkProductDescriptorNode_2003ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedRoleTaskCompositeRoleTaskCompartment_5001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedWorkProductCompositeWorkProductCompartment_5002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityDetailDiagram_79ChildNodeID(
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
