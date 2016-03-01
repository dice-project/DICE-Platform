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
package org.eclipse.epf.diagram.wpdd.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.epf.diagram.wpdd.edit.parts.LinkEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.LinkNameEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductDependencyDiagramEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeNameEditPart;

import org.eclipse.epf.diagram.wpdd.part.DiagramVisualIDRegistry;

import org.eclipse.epf.diagram.wpdd.view.factories.LinkNameViewFactory;
import org.eclipse.epf.diagram.wpdd.view.factories.LinkViewFactory;
import org.eclipse.epf.diagram.wpdd.view.factories.WorkProductDependencyDiagramViewFactory;
import org.eclipse.epf.diagram.wpdd.view.factories.WorkProductNodeNameViewFactory;
import org.eclipse.epf.diagram.wpdd.view.factories.WorkProductNodeViewFactory;

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
		if (WorkProductDependencyDiagramEditPart.MODEL_ID.equals(diagramKind)
				&& DiagramVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return WorkProductDependencyDiagramViewFactory.class;
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
		case WorkProductNodeEditPart.VISUAL_ID:
			return WorkProductNodeViewFactory.class;
		case WorkProductNodeNameEditPart.VISUAL_ID:
			return WorkProductNodeNameViewFactory.class;
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
