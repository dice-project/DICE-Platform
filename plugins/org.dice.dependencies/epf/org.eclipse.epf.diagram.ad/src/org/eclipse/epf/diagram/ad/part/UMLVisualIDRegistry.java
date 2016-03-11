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

import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartition2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionPartitionCampartment2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionPartitionCampartmentEditPart;
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
import org.eclipse.epf.diagram.ad.expressions.UMLAbstractExpression;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented 
 * by a domain model object.
 *
 * @generated
 */
public class UMLVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = ActivityDiagramEditorPlugin
			.getInstance().getBundle().getSymbolicName()
			+ "/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ActivityEditPart.MODEL_ID.equals(view.getType())) {
				return ActivityEditPart.VISUAL_ID;
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
				ActivityDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
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
		if (UMLPackage.eINSTANCE.getActivity().isSuperTypeOf(
				domainElementMetaclass)
				&& isDiagramActivity_79((Activity) domainElement)) {
			return ActivityEditPart.VISUAL_ID;
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
	 * @modified
	 */
	public static int getNodeVisualID(View containerView,
			EObject domainElement, EClass domainElementMetaclass,
			String semanticHint) {
		String containerModelID = getModelID(containerView);
		if (!ActivityEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (ActivityEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ActivityEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		int nodeVisualID = semanticHint != null ? getVisualID(semanticHint)
				: -1;
		switch (containerVisualID) {
		case ActivityFinalNodeEditPart.VISUAL_ID:
			if (ActivityFinalNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return ActivityFinalNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityFinalNode_1001ChildNodeID(
					domainElement, semanticHint);
		case MergeNodeEditPart.VISUAL_ID:
			if (MergeNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return MergeNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedMergeNode_1002ChildNodeID(domainElement,
					semanticHint);
		case ForkNodeEditPart.VISUAL_ID:
			return getUnrecognizedForkNode_1003ChildNodeID(domainElement,
					semanticHint);
		case InitialNodeEditPart.VISUAL_ID:
			if (InitialNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return InitialNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedInitialNode_1004ChildNodeID(domainElement,
					semanticHint);
		case DecisionNodeEditPart.VISUAL_ID:
			if (DecisionNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return DecisionNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedDecisionNode_1005ChildNodeID(domainElement,
					semanticHint);
		case JoinNodeEditPart.VISUAL_ID:
			return getUnrecognizedJoinNode_1006ChildNodeID(domainElement,
					semanticHint);
		case StructuredActivityNodeEditPart.VISUAL_ID:
			if (StructuredActivityNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return StructuredActivityNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedStructuredActivityNode_1007ChildNodeID(
					domainElement, semanticHint);
		case ActivityPartitionEditPart.VISUAL_ID:
			if (ActivityPartitionName2EditPart.VISUAL_ID == nodeVisualID) {
				return ActivityPartitionName2EditPart.VISUAL_ID;
			}
			if (ActivityPartitionPartitionCampartmentEditPart.VISUAL_ID == nodeVisualID) {
				return ActivityPartitionPartitionCampartmentEditPart.VISUAL_ID;
			}
			if (StructuredActivityNodeEditPart.VISUAL_ID == nodeVisualID) {
				return StructuredActivityNodeEditPart.VISUAL_ID;
			}
			if (StructuredActivityNode2EditPart.VISUAL_ID == nodeVisualID) {
				return StructuredActivityNode2EditPart.VISUAL_ID;
			}
			if (StructuredActivityNode3EditPart.VISUAL_ID == nodeVisualID) {
				return StructuredActivityNode3EditPart.VISUAL_ID;
			}
			if (InitialNodeEditPart.VISUAL_ID == nodeVisualID) {
				return InitialNodeEditPart.VISUAL_ID;
			}
			if (ActivityFinalNodeEditPart.VISUAL_ID == nodeVisualID) {
				return ActivityFinalNodeEditPart.VISUAL_ID;
			}
			if (JoinNodeEditPart.VISUAL_ID == nodeVisualID) {
				return JoinNodeEditPart.VISUAL_ID;
			}
			if (ForkNodeEditPart.VISUAL_ID == nodeVisualID) {
				return ForkNodeEditPart.VISUAL_ID;
			}
			if (MergeNodeEditPart.VISUAL_ID == nodeVisualID) {
				return MergeNodeEditPart.VISUAL_ID;
			}
			if (DecisionNodeEditPart.VISUAL_ID == nodeVisualID) {
				return DecisionNodeEditPart.VISUAL_ID;
			}
			if (ActivityParameterNodeEditPart.VISUAL_ID == nodeVisualID) {
				return ActivityParameterNodeEditPart.VISUAL_ID;
			}
			if (ActivityParameterNode2EditPart.VISUAL_ID == nodeVisualID) {
				return ActivityParameterNode2EditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityPartition_1008ChildNodeID(
					domainElement, semanticHint);
		case ActivityParameterNodeEditPart.VISUAL_ID:
			if (ActivityParameterNodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return ActivityParameterNodeNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityParameterNode_1009ChildNodeID(
					domainElement, semanticHint);
		case StructuredActivityNode2EditPart.VISUAL_ID:
			if (StructuredActivityNodeName2EditPart.VISUAL_ID == nodeVisualID) {
				return StructuredActivityNodeName2EditPart.VISUAL_ID;
			}
			return getUnrecognizedStructuredActivityNode_1010ChildNodeID(
					domainElement, semanticHint);
		case StructuredActivityNode3EditPart.VISUAL_ID:
			if (StructuredActivityNodeName3EditPart.VISUAL_ID == nodeVisualID) {
				return StructuredActivityNodeName3EditPart.VISUAL_ID;
			}
			return getUnrecognizedStructuredActivityNode_1011ChildNodeID(
					domainElement, semanticHint);
		case ActivityParameterNode2EditPart.VISUAL_ID:
			if (ActivityParameterNodeName2EditPart.VISUAL_ID == nodeVisualID) {
				return ActivityParameterNodeName2EditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityParameterNode_1012ChildNodeID(
					domainElement, semanticHint);
		case ActivityPartition2EditPart.VISUAL_ID:
			if (ActivityPartitionNameEditPart.VISUAL_ID == nodeVisualID) {
				return ActivityPartitionNameEditPart.VISUAL_ID;
			}
			if (ActivityPartitionPartitionCampartment2EditPart.VISUAL_ID == nodeVisualID) {
				return ActivityPartitionPartitionCampartment2EditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityPartition_2001ChildNodeID(
					domainElement, semanticHint);
		case ActivityPartitionPartitionCampartmentEditPart.VISUAL_ID:
			if ((semanticHint == null || ActivityPartition2EditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getActivityPartition()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeActivityPartition_2001((ActivityPartition) domainElement))) {
				return ActivityPartition2EditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityPartitionPartitionCampartment_5001ChildNodeID(
					domainElement, semanticHint);
		case ActivityPartitionPartitionCampartment2EditPart.VISUAL_ID:
			if ((semanticHint == null || ActivityPartition2EditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getActivityPartition()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeActivityPartition_2001((ActivityPartition) domainElement))) {
				return ActivityPartition2EditPart.VISUAL_ID;
			}
			return getUnrecognizedActivityPartitionPartitionCampartment_5002ChildNodeID(
					domainElement, semanticHint);
		case ActivityEditPart.VISUAL_ID:
			if ((semanticHint == null || ActivityFinalNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getActivityFinalNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeActivityFinalNode_1001((ActivityFinalNode) domainElement))) {
				return ActivityFinalNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || MergeNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getMergeNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeMergeNode_1002((MergeNode) domainElement))) {
				return MergeNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ForkNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getForkNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeForkNode_1003((ForkNode) domainElement))) {
				return ForkNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || InitialNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getInitialNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeInitialNode_1004((InitialNode) domainElement))) {
				return InitialNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || DecisionNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getDecisionNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeDecisionNode_1005((DecisionNode) domainElement))) {
				return DecisionNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || JoinNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getJoinNode().isSuperTypeOf(
							domainElementMetaclass)
					&& (domainElement == null || isNodeJoinNode_1006((JoinNode) domainElement))) {
				return JoinNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || StructuredActivityNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getStructuredActivityNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeStructuredActivityNode_1007((StructuredActivityNode) domainElement))) {
				return StructuredActivityNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ActivityPartitionEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getActivityPartition()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeActivityPartition_1008((ActivityPartition) domainElement))) {
				return ActivityPartitionEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ActivityParameterNodeEditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getActivityParameterNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeActivityParameterNode_1009((ActivityParameterNode) domainElement))) {
				return ActivityParameterNodeEditPart.VISUAL_ID;
			}
			if ((semanticHint == null || StructuredActivityNode2EditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getStructuredActivityNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeStructuredActivityNode_1010((StructuredActivityNode) domainElement))) {
				return StructuredActivityNode2EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || StructuredActivityNode3EditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getStructuredActivityNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeStructuredActivityNode_1011((StructuredActivityNode) domainElement))) {
				return StructuredActivityNode3EditPart.VISUAL_ID;
			}
			if ((semanticHint == null || ActivityParameterNode2EditPart.VISUAL_ID == nodeVisualID)
					&& UMLPackage.eINSTANCE.getActivityParameterNode()
							.isSuperTypeOf(domainElementMetaclass)
					&& (domainElement == null || isNodeActivityParameterNode_1012((ActivityParameterNode) domainElement))) {
				return ActivityParameterNode2EditPart.VISUAL_ID;
			}
			return getUnrecognizedActivity_79ChildNodeID(domainElement,
					semanticHint);
		case ControlFlowEditPart.VISUAL_ID:
			if (ControlFlowNameEditPart.VISUAL_ID == nodeVisualID) {
				return ControlFlowNameEditPart.VISUAL_ID;
			}
			return getUnrecognizedControlFlow_3001LinkLabelID(semanticHint);
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
		if (UMLPackage.eINSTANCE.getControlFlow().isSuperTypeOf(
				domainElementMetaclass)
				&& (domainElement == null || isLinkWithClassControlFlow_3001((ControlFlow) domainElement))) {
			return ControlFlowEditPart.VISUAL_ID;
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
	private static boolean isDiagramActivity_79(Activity element) {
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
	private static boolean isNodeActivityFinalNode_1001(
			ActivityFinalNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeMergeNode_1002(MergeNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeForkNode_1003(ForkNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeInitialNode_1004(InitialNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeDecisionNode_1005(DecisionNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeJoinNode_1006(JoinNode element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeStructuredActivityNode_1007(
			StructuredActivityNode element) {
		return StructuredActivityNode_1007.matches(element);
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeActivityPartition_1008(
			ActivityPartition element) {
		return true;
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeActivityParameterNode_1009(
			ActivityParameterNode element) {
		return ActivityParameterNode_1009.matches(element);
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeStructuredActivityNode_1010(
			StructuredActivityNode element) {
		return StructuredActivityNode_1010.matches(element);
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeStructuredActivityNode_1011(
			StructuredActivityNode element) {
		return StructuredActivityNode_1011.matches(element);
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeActivityParameterNode_1012(
			ActivityParameterNode element) {
		return ActivityParameterNode_1012.matches(element);
	}

	/**
	 * User can change implementation of this method to check some additional 
	 * conditions here.
	 *
	 * @generated
	 */
	private static boolean isNodeActivityPartition_2001(
			ActivityPartition element) {
		return true;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityFinalNode_1001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedMergeNode_1002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedForkNode_1003ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedInitialNode_1004ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedDecisionNode_1005ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedJoinNode_1006ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedStructuredActivityNode_1007ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityPartition_1008ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityParameterNode_1009ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedStructuredActivityNode_1010ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedStructuredActivityNode_1011ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityParameterNode_1012ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityPartition_2001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityPartitionPartitionCampartment_5001ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivityPartitionPartitionCampartment_5002ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedActivity_79ChildNodeID(
			EObject domainElement, String semanticHint) {
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 *
	 * @generated
	 */
	private static int getUnrecognizedControlFlow_3001LinkLabelID(
			String semanticHint) {
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
	private static boolean isLinkWithClassControlFlow_3001(ControlFlow element) {
		return true;
	}

	/**
	 * @generated
	 */
	private static final Matcher StructuredActivityNode_1007 = new Matcher(
			new UMLAbstractExpression(UMLPackage.eINSTANCE
					.getStructuredActivityNode()) {
				protected Object doEvaluate(Object context, Map env) {
					StructuredActivityNode self = (StructuredActivityNode) context;
					return JavaMatchers.getUmaType_Activity(self);
				}
			});

	/**
	 * @generated
	 */
	private static final Matcher ActivityParameterNode_1009 = new Matcher(
			new UMLAbstractExpression(UMLPackage.eINSTANCE
					.getActivityParameterNode()) {
				protected Object doEvaluate(Object context, Map env) {
					ActivityParameterNode self = (ActivityParameterNode) context;
					return JavaMatchers.getUmaType_TaskDescriptor(self);
				}
			});

	/**
	 * @generated
	 */
	private static final Matcher StructuredActivityNode_1010 = new Matcher(
			new UMLAbstractExpression(UMLPackage.eINSTANCE
					.getStructuredActivityNode()) {
				protected Object doEvaluate(Object context, Map env) {
					StructuredActivityNode self = (StructuredActivityNode) context;
					return JavaMatchers.getUmaType_Phase(self);
				}
			});

	/**
	 * @generated
	 */
	private static final Matcher StructuredActivityNode_1011 = new Matcher(
			new UMLAbstractExpression(UMLPackage.eINSTANCE
					.getStructuredActivityNode()) {
				protected Object doEvaluate(Object context, Map env) {
					StructuredActivityNode self = (StructuredActivityNode) context;
					return JavaMatchers.getUmaType_Iteration(self);
				}
			});

	/**
	 * @generated
	 */
	private static final Matcher ActivityParameterNode_1012 = new Matcher(
			new UMLAbstractExpression(UMLPackage.eINSTANCE
					.getActivityParameterNode()) {
				protected Object doEvaluate(Object context, Map env) {
					ActivityParameterNode self = (ActivityParameterNode) context;
					return JavaMatchers.getUmaType_Milestone(self);
				}
			});

	/**
	 * @generated	
	 */
	static class Matcher {

		/**
		 * @generated	
		 */
		private UMLAbstractExpression condition;

		/**
		 * @generated	
		 */
		Matcher(UMLAbstractExpression conditionExpression) {
			this.condition = conditionExpression;
		}

		/**
		 * @generated	
		 */
		boolean matches(Object object) {
			Object result = condition.evaluate(object);
			return result instanceof Boolean
					&& ((Boolean) result).booleanValue();
		}
	}// Matcher

	/**
	 * @generated
	 */
	private static class JavaMatchers {

		/**
		 * @modified
		 */
		private static Boolean getUmaType_Activity(StructuredActivityNode self) {
			// TODO: implement this method
			// Ensure that you remove @generated or mark it @generated NOT

			Boolean bl = new Boolean(false);
			String type = BridgeHelper.getEAnnotationType(self,
					BridgeHelper.UMA_TYPE);
			if (BridgeHelper.UMA_ACTIVITY.equalsIgnoreCase(type)) {
				return bl.TRUE;
			}
			return bl;
			//			throw new UMLAbstractExpression.NoImplException(
			//					"No user java implementation provided in 'getUmaType_Activity' operation"); //$NON-NLS-1$
		}

		/**
		 * @modified
		 */
		private static Boolean getUmaType_TaskDescriptor(
				ActivityParameterNode self) {
			// TODO: implement this method
			// Ensure that you remove @generated or mark it @generated NOT

			Boolean bl = new Boolean(false);
			String type = BridgeHelper.getEAnnotationType(self,
					BridgeHelper.UMA_TYPE);
			if (BridgeHelper.UMA_TASK_DESCRIPTOR.equalsIgnoreCase(type)) {
				return bl.TRUE;
			}
			return bl;
			//			throw new UMLAbstractExpression.NoImplException(
			//					"No user java implementation provided in 'getUmaType_TaskDescriptor' operation"); //$NON-NLS-1$
		}

		/**
		 * @modified
		 */
		private static Boolean getUmaType_Phase(StructuredActivityNode self) {
			// TODO: implement this method
			// Ensure that you remove @generated or mark it @generated NOT

			Boolean bl = new Boolean(false);
			String type = BridgeHelper.getEAnnotationType(self,
					BridgeHelper.UMA_TYPE);
			if (BridgeHelper.UMA_PHASE.equalsIgnoreCase(type)) {
				return bl.TRUE;
			}
			return bl;
			//			throw new UMLAbstractExpression.NoImplException(
			//					"No user java implementation provided in 'getUmaType_Phase' operation"); //$NON-NLS-1$
		}

		/**
		 * @modified
		 */
		private static Boolean getUmaType_Iteration(StructuredActivityNode self) {
			// TODO: implement this method
			// Ensure that you remove @generated or mark it @generated NOT
			Boolean bl = new Boolean(false);
			String type = BridgeHelper.getEAnnotationType(self,
					BridgeHelper.UMA_TYPE);
			if (BridgeHelper.UMA_ITERATION.equalsIgnoreCase(type)) {
				return bl.TRUE;
			}
			return bl;
			//			throw new UMLAbstractExpression.NoImplException(
			//					"No user java implementation provided in 'getUmaType_Iteration' operation"); //$NON-NLS-1$
		}

		/**
		 * @modified
		 */
		private static Boolean getUmaType_Milestone(ActivityParameterNode self) {
			// TODO: implement this method
			// Ensure that you remove @generated or mark it @generated NOT

			Boolean bl = new Boolean(false);
			String type = BridgeHelper.getEAnnotationType(self,
					BridgeHelper.UMA_TYPE);
			if (BridgeHelper.UMA_MILESTONE.equalsIgnoreCase(type)) {
				return bl.TRUE;
			}
			return bl;
			//			throw new UMLAbstractExpression.NoImplException(
			//					"No user java implementation provided in 'getUmaType_Milestone' operation"); //$NON-NLS-1$
		}
	}// JavaMatchers
}
