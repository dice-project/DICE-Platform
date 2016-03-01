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
package org.eclipse.epf.diagram.ad.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
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

import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;

import org.eclipse.epf.diagram.ad.view.factories.ActivityFinalNodeNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityFinalNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityParameterNode2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityParameterNodeName2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityParameterNodeNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityParameterNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityPartition2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityPartitionName2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityPartitionNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityPartitionPartitionCampartment2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityPartitionPartitionCampartmentViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityPartitionViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ActivityViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ControlFlowNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ControlFlowViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.DecisionNodeNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.DecisionNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.ForkNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.InitialNodeNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.InitialNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.JoinNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.MergeNodeNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.MergeNodeViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.StructuredActivityNode2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.StructuredActivityNode3ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.StructuredActivityNodeName2ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.StructuredActivityNodeName3ViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.StructuredActivityNodeNameViewFactory;
import org.eclipse.epf.diagram.ad.view.factories.StructuredActivityNodeViewFactory;

/**
 * @generated
 */
public class UMLViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (ActivityEditPart.MODEL_ID.equals(diagramKind)
				&& UMLVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return ActivityViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (elementType != null
				&& !UMLElementTypes.isKnownElementType(elementType)) {
			return null;
		}
		EClass semanticType = getSemanticEClass(semanticAdapter);
		EObject semanticElement = getSemanticElement(semanticAdapter);
		int nodeVID = UMLVisualIDRegistry.getNodeVisualID(containerView,
				semanticElement, semanticType, semanticHint);
		switch (nodeVID) {
		case ActivityFinalNodeEditPart.VISUAL_ID:
			return ActivityFinalNodeViewFactory.class;
		case ActivityFinalNodeNameEditPart.VISUAL_ID:
			return ActivityFinalNodeNameViewFactory.class;
		case MergeNodeEditPart.VISUAL_ID:
			return MergeNodeViewFactory.class;
		case MergeNodeNameEditPart.VISUAL_ID:
			return MergeNodeNameViewFactory.class;
		case ForkNodeEditPart.VISUAL_ID:
			return ForkNodeViewFactory.class;
		case InitialNodeEditPart.VISUAL_ID:
			return InitialNodeViewFactory.class;
		case InitialNodeNameEditPart.VISUAL_ID:
			return InitialNodeNameViewFactory.class;
		case DecisionNodeEditPart.VISUAL_ID:
			return DecisionNodeViewFactory.class;
		case DecisionNodeNameEditPart.VISUAL_ID:
			return DecisionNodeNameViewFactory.class;
		case JoinNodeEditPart.VISUAL_ID:
			return JoinNodeViewFactory.class;
		case StructuredActivityNodeEditPart.VISUAL_ID:
			return StructuredActivityNodeViewFactory.class;
		case StructuredActivityNodeNameEditPart.VISUAL_ID:
			return StructuredActivityNodeNameViewFactory.class;
		case ActivityPartitionEditPart.VISUAL_ID:
			return ActivityPartitionViewFactory.class;
		case ActivityPartitionName2EditPart.VISUAL_ID:
			return ActivityPartitionName2ViewFactory.class;
		case ActivityParameterNodeEditPart.VISUAL_ID:
			return ActivityParameterNodeViewFactory.class;
		case ActivityParameterNodeNameEditPart.VISUAL_ID:
			return ActivityParameterNodeNameViewFactory.class;
		case StructuredActivityNode2EditPart.VISUAL_ID:
			return StructuredActivityNode2ViewFactory.class;
		case StructuredActivityNodeName2EditPart.VISUAL_ID:
			return StructuredActivityNodeName2ViewFactory.class;
		case StructuredActivityNode3EditPart.VISUAL_ID:
			return StructuredActivityNode3ViewFactory.class;
		case StructuredActivityNodeName3EditPart.VISUAL_ID:
			return StructuredActivityNodeName3ViewFactory.class;
		case ActivityParameterNode2EditPart.VISUAL_ID:
			return ActivityParameterNode2ViewFactory.class;
		case ActivityParameterNodeName2EditPart.VISUAL_ID:
			return ActivityParameterNodeName2ViewFactory.class;
		case ActivityPartition2EditPart.VISUAL_ID:
			return ActivityPartition2ViewFactory.class;
		case ActivityPartitionNameEditPart.VISUAL_ID:
			return ActivityPartitionNameViewFactory.class;
		case ActivityPartitionPartitionCampartmentEditPart.VISUAL_ID:
			return ActivityPartitionPartitionCampartmentViewFactory.class;
		case ActivityPartitionPartitionCampartment2EditPart.VISUAL_ID:
			return ActivityPartitionPartitionCampartment2ViewFactory.class;
		case ControlFlowNameEditPart.VISUAL_ID:
			return ControlFlowNameViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (elementType != null
				&& !UMLElementTypes.isKnownElementType(elementType)) {
			return null;
		}
		EClass semanticType = getSemanticEClass(semanticAdapter);
		if (semanticType == null) {
			return null;
		}
		EObject semanticElement = getSemanticElement(semanticAdapter);
		int linkVID = UMLVisualIDRegistry.getLinkWithClassVisualID(
				semanticElement, semanticType);
		switch (linkVID) {
		case ControlFlowEditPart.VISUAL_ID:
			return ControlFlowViewFactory.class;
		}
		return getUnrecognizedConnectorViewClass(semanticAdapter,
				containerView, semanticHint);
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}

	/**
	 * @generated
	 */
	private Class getUnrecognizedConnectorViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		// Handle unrecognized child node classes here
		return null;
	}

}
