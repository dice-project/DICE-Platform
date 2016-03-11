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
package org.eclipse.epf.diagram.add.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
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

import org.eclipse.epf.diagram.add.part.DiagramVisualIDRegistry;

import org.eclipse.epf.diagram.add.view.factories.ActivityDetailDiagramViewFactory;
import org.eclipse.epf.diagram.add.view.factories.LinkNameViewFactory;
import org.eclipse.epf.diagram.add.view.factories.LinkViewFactory;
import org.eclipse.epf.diagram.add.view.factories.RoleNodeNameViewFactory;
import org.eclipse.epf.diagram.add.view.factories.RoleNodeViewFactory;
import org.eclipse.epf.diagram.add.view.factories.RoleTaskCompositeRoleTaskCompartmentViewFactory;
import org.eclipse.epf.diagram.add.view.factories.RoleTaskCompositeViewFactory;
import org.eclipse.epf.diagram.add.view.factories.TaskNodeNameViewFactory;
import org.eclipse.epf.diagram.add.view.factories.TaskNodeViewFactory;
import org.eclipse.epf.diagram.add.view.factories.WorkProductCompositeViewFactory;
import org.eclipse.epf.diagram.add.view.factories.WorkProductCompositeWorkProductCompartmentViewFactory;
import org.eclipse.epf.diagram.add.view.factories.WorkProductDescriptorNodeNameViewFactory;
import org.eclipse.epf.diagram.add.view.factories.WorkProductDescriptorNodeViewFactory;

/**
 * @generated
 */
public class DiagramViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (ActivityDetailDiagramEditPart.MODEL_ID.equals(diagramKind)
				&& DiagramVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return ActivityDetailDiagramViewFactory.class;
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
				&& !DiagramElementTypes.isKnownElementType(elementType)) {
			return null;
		}
		EClass semanticType = getSemanticEClass(semanticAdapter);
		EObject semanticElement = getSemanticElement(semanticAdapter);
		int nodeVID = DiagramVisualIDRegistry.getNodeVisualID(containerView,
				semanticElement, semanticType, semanticHint);
		switch (nodeVID) {
		case RoleTaskCompositeEditPart.VISUAL_ID:
			return RoleTaskCompositeViewFactory.class;
		case WorkProductCompositeEditPart.VISUAL_ID:
			return WorkProductCompositeViewFactory.class;
		case RoleNodeEditPart.VISUAL_ID:
			return RoleNodeViewFactory.class;
		case RoleNodeNameEditPart.VISUAL_ID:
			return RoleNodeNameViewFactory.class;
		case TaskNodeEditPart.VISUAL_ID:
			return TaskNodeViewFactory.class;
		case TaskNodeNameEditPart.VISUAL_ID:
			return TaskNodeNameViewFactory.class;
		case WorkProductDescriptorNodeEditPart.VISUAL_ID:
			return WorkProductDescriptorNodeViewFactory.class;
		case WorkProductDescriptorNodeNameEditPart.VISUAL_ID:
			return WorkProductDescriptorNodeNameViewFactory.class;
		case RoleTaskCompositeRoleTaskCompartmentEditPart.VISUAL_ID:
			return RoleTaskCompositeRoleTaskCompartmentViewFactory.class;
		case WorkProductCompositeWorkProductCompartmentEditPart.VISUAL_ID:
			return WorkProductCompositeWorkProductCompartmentViewFactory.class;
		case LinkNameEditPart.VISUAL_ID:
			return LinkNameViewFactory.class;
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
				&& !DiagramElementTypes.isKnownElementType(elementType)) {
			return null;
		}
		EClass semanticType = getSemanticEClass(semanticAdapter);
		if (semanticType == null) {
			return null;
		}
		EObject semanticElement = getSemanticElement(semanticAdapter);
		int linkVID = DiagramVisualIDRegistry.getLinkWithClassVisualID(
				semanticElement, semanticType);
		switch (linkVID) {
		case LinkEditPart.VISUAL_ID:
			return LinkViewFactory.class;
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
