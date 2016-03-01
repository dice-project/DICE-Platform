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
package org.eclipse.epf.diagram.ad.edit.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartition2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ForkNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.InitialNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.JoinNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeEditPart;
import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;
import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class ActivityCanonicalEditPolicy extends CanonicalConnectionEditPolicy {
	
	@Override
	public EObject getSemanticHost() {
		return host().getModel() != null ? super.getSemanticHost() : null;
	}
	
	@Override
	protected void refreshOnActivate() {		
		super.refreshOnActivate();
		
		EditPart ep = getHost();
		if(ep instanceof DiagramEditPart) {
			DiagramEditorUtil.initializeOnActivate((DiagramEditPart) ep);
		}
	}

	/**
	 * @modified
	 */
	protected List getSemanticChildrenList() {
		List result = new LinkedList();
		EObject modelObject = ((View) getHost().getModel()).getElement();
		View viewObject = (View) getHost().getModel();
		EObject nextValue;
		int nodeVID;
		for (Iterator values = ((Activity) modelObject).getNodes().iterator(); values
				.hasNext();) {
			nextValue = (EObject) values.next();
			// modified START
			// To avoid the ActivityNode show in duplicate places
			if (nextValue instanceof ActivityNode) {
				if (!((ActivityNode) nextValue).getInPartitions().isEmpty())
					continue;
			}
			// modified END
			nodeVID = UMLVisualIDRegistry
					.getNodeVisualID(viewObject, nextValue);
			switch (nodeVID) {
			case ActivityFinalNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case MergeNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case ForkNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case InitialNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case DecisionNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case JoinNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case StructuredActivityNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case ActivityParameterNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case StructuredActivityNode2EditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case StructuredActivityNode3EditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case ActivityParameterNode2EditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			}
		}
		for (Iterator values = ((Activity) modelObject).getGroups().iterator(); values
				.hasNext();) {
			nextValue = (EObject) values.next();
			nodeVID = UMLVisualIDRegistry
					.getNodeVisualID(viewObject, nextValue);
			if (ActivityPartitionEditPart.VISUAL_ID == nodeVID) {
				result.add(nextValue);
			}
		}
		return result;
	}

	/**
	 * @modified
	 */
	protected boolean shouldDeleteView(View view) {
		if (view instanceof Edge) {
			Object element = view.getElement();
			if (element != null && element instanceof ActivityEdge) {
				if (((ActivityEdge) element).getSource() == null
						|| ((ActivityEdge) element).getTarget() == null) {
					return true;
				} else {
					return false;
				}
			}
		}
		return view.isSetElement()
				&& view.getElement() != null
				&& (view.getElement().eIsProxy() || (view.getElement() instanceof EModelElement && BridgeHelper
						.getMethodElement((EModelElement) view.getElement()) != null));
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @modified
	 */
	protected List getSemanticConnectionsList() {
		//		return Collections.EMPTY_LIST;

		Activity act = (Activity) ((View) getHost().getModel()).getElement();
		return new ArrayList(act.getEdges());
	}

	/**
	 * @modified
	 */
	protected EObject getSourceElement(EObject relationship) {
		if (relationship instanceof ActivityEdge) {
			return ((ActivityEdge) relationship).getSource();
		}
		return null;
	}

	/**
	 * @modified
	 */
	protected EObject getTargetElement(EObject relationship) {
		if (relationship instanceof ActivityEdge) {
			return ((ActivityEdge) relationship).getTarget();
		}
		return null;
	}

	/**
	 * @modified
	 */
	protected boolean shouldIncludeConnection(Edge connector,
			Collection children) {
		return super.shouldIncludeConnection(connector, children);
	}

	/**
	 * @generated
	 */
	protected void refreshSemantic() {
		List createdViews = new LinkedList();
		createdViews.addAll(refreshSemanticChildren());
		List createdConnectionViews = new LinkedList();
		createdConnectionViews.addAll(refreshSemanticConnections());
		createdConnectionViews.addAll(refreshConnections());

		if (createdViews.size() > 1) {
			// perform a layout of the container
			DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host()
					.getEditingDomain(), createdViews, host());
			executeCommand(new ICommandProxy(layoutCmd));
		}

		createdViews.addAll(createdConnectionViews);
		makeViewsImmutable(createdViews);
	}

	/**
	 * @generated
	 */
	private Collection myLinkDescriptors = new LinkedList();

	/**
	 * @generated
	 */
	private Map myEObject2ViewMap = new HashMap();

	/**
	 * @generated
	 */
	private Collection refreshConnections() {
		try {
			collectAllLinks(getDiagram());
			Collection existingLinks = new LinkedList(getDiagram().getEdges());
			for (Iterator diagramLinks = existingLinks.iterator(); diagramLinks
					.hasNext();) {
				Edge nextDiagramLink = (Edge) diagramLinks.next();
				EObject diagramLinkObject = nextDiagramLink.getElement();
				EObject diagramLinkSrc = nextDiagramLink.getSource()
						.getElement();
				EObject diagramLinkDst = nextDiagramLink.getTarget()
						.getElement();
				int diagramLinkVisualID = UMLVisualIDRegistry
						.getVisualID(nextDiagramLink);
				for (Iterator modelLinkDescriptors = myLinkDescriptors
						.iterator(); modelLinkDescriptors.hasNext();) {
					LinkDescriptor nextLinkDescriptor = (LinkDescriptor) modelLinkDescriptors
							.next();
					if (diagramLinkObject == nextLinkDescriptor
							.getLinkElement()
							&& diagramLinkSrc == nextLinkDescriptor.getSource()
							&& diagramLinkDst == nextLinkDescriptor
									.getDestination()
							&& diagramLinkVisualID == nextLinkDescriptor
									.getVisualID()) {
						diagramLinks.remove();
						modelLinkDescriptors.remove();
					}
				}
			}
			deleteViews(existingLinks.iterator());
			return createConnections(myLinkDescriptors);
		} finally {
			myLinkDescriptors.clear();
			myEObject2ViewMap.clear();
		}
	}

	/**
	 * @generated
	 */
	private void collectAllLinks(View view) {
		EObject modelElement = view.getElement();
		int diagramElementVisualID = UMLVisualIDRegistry.getVisualID(view);
		switch (diagramElementVisualID) {
		case ActivityFinalNodeEditPart.VISUAL_ID:
		case MergeNodeEditPart.VISUAL_ID:
		case ForkNodeEditPart.VISUAL_ID:
		case InitialNodeEditPart.VISUAL_ID:
		case DecisionNodeEditPart.VISUAL_ID:
		case JoinNodeEditPart.VISUAL_ID:
		case StructuredActivityNodeEditPart.VISUAL_ID:
		case ActivityPartitionEditPart.VISUAL_ID:
		case ActivityParameterNodeEditPart.VISUAL_ID:
		case StructuredActivityNode2EditPart.VISUAL_ID:
		case StructuredActivityNode3EditPart.VISUAL_ID:
		case ActivityParameterNode2EditPart.VISUAL_ID:
		case ActivityPartition2EditPart.VISUAL_ID:
		case ActivityEditPart.VISUAL_ID: {
			myEObject2ViewMap.put(modelElement, view);
			storeLinks(modelElement, getDiagram());
		}
		default: {
		}
			for (Iterator children = view.getChildren().iterator(); children
					.hasNext();) {
				View childView = (View) children.next();
				collectAllLinks(childView);
			}
		}
	}

	/**
	 * @generated
	 */
	private Collection createConnections(Collection linkDescriptors) {
		if (linkDescriptors.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		List adapters = new LinkedList();
		for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
				.hasNext();) {
			final LinkDescriptor nextLinkDescriptor = (LinkDescriptor) linkDescriptorsIterator
					.next();
			EditPart sourceEditPart = getEditPartFor(nextLinkDescriptor
					.getSource());
			EditPart targetEditPart = getEditPartFor(nextLinkDescriptor
					.getDestination());
			if (sourceEditPart == null || targetEditPart == null) {
				continue;
			}
			CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
					nextLinkDescriptor.getSemanticAdapter(), null,
					ViewUtil.APPEND, false, ((IGraphicalEditPart) getHost())
							.getDiagramPreferencesHint());
			CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(
					descriptor);
			ccr.setType(RequestConstants.REQ_CONNECTION_START);
			ccr.setSourceEditPart(sourceEditPart);
			sourceEditPart.getCommand(ccr);
			ccr.setTargetEditPart(targetEditPart);
			ccr.setType(RequestConstants.REQ_CONNECTION_END);
			Command cmd = targetEditPart.getCommand(ccr);
			if (cmd != null && cmd.canExecute()) {
				executeCommand(cmd);
				IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
				if (viewAdapter != null) {
					adapters.add(viewAdapter);
				}
			}
		}
		return adapters;
	}

	/**
	 * @generated
	 */
	private EditPart getEditPartFor(EObject modelElement) {
		View view = (View) myEObject2ViewMap.get(modelElement);
		if (view != null) {
			return (EditPart) getHost().getViewer().getEditPartRegistry().get(
					view);
		}
		return null;
	}

	/**
	 *@generated
	 */
	private void storeLinks(EObject container, Diagram diagram) {
		EClass containerMetaclass = container.eClass();
		storeFeatureModelFacetLinks(container, containerMetaclass, diagram);
		storeTypeModelFacetLinks(container, containerMetaclass);
	}

	/**
	 * @generated
	 */
	private void storeTypeModelFacetLinks(EObject container,
			EClass containerMetaclass) {
		storeTypeModelFacetLinks_ControlFlow_3001(container, containerMetaclass);
	}

	/**
	 * @generated
	 */
	private void storeTypeModelFacetLinks_ControlFlow_3001(EObject container,
			EClass containerMetaclass) {
		if (UMLPackage.eINSTANCE.getStructuredActivityNode().isSuperTypeOf(
				containerMetaclass)) {
			for (Iterator values = ((StructuredActivityNode) container)
					.getEdges().iterator(); values.hasNext();) {
				EObject nextValue = ((EObject) values.next());
				int linkVID = UMLVisualIDRegistry
						.getLinkWithClassVisualID(nextValue);
				if (ControlFlowEditPart.VISUAL_ID == linkVID) {
					Object structuralFeatureResult = ((ActivityEdge) nextValue)
							.getTarget();
					if (structuralFeatureResult instanceof EObject) {
						EObject dst = (EObject) structuralFeatureResult;
						structuralFeatureResult = ((ActivityEdge) nextValue)
								.getSource();
						if (structuralFeatureResult instanceof EObject) {
							EObject src = (EObject) structuralFeatureResult;
							myLinkDescriptors.add(new LinkDescriptor(src, dst,
									nextValue,
									UMLElementTypes.ControlFlow_3001, linkVID));
						}
					}
				}
			}
		}
	}

	/**
	 *@generated
	 */
	private void storeFeatureModelFacetLinks(EObject container,
			EClass containerMetaclass, Diagram diagram) {

	}

	/**
	 * @generated
	 */
	private Diagram getDiagram() {
		return ((View) getHost().getModel()).getDiagram();
	}

	/**
	 * @generated
	 */
	private class LinkDescriptor {

		/**
		 * @generated
		 */
		private EObject mySource;

		/**
		 * @generated
		 */
		private EObject myDestination;

		/**
		 * @generated
		 */
		private EObject myLinkElement;

		/**
		 * @generated
		 */
		private int myVisualID;

		/**
		 * @generated
		 */
		private IAdaptable mySemanticAdapter;

		/**
		 * @generated
		 */
		protected LinkDescriptor(EObject source, EObject destination,
				EObject linkElement, IElementType elementType, int linkVID) {
			this(source, destination, linkVID);
			myLinkElement = linkElement;
			final IElementType elementTypeCopy = elementType;
			mySemanticAdapter = new EObjectAdapter(linkElement) {
				public Object getAdapter(Class adapter) {
					if (IElementType.class.equals(adapter)) {
						return elementTypeCopy;
					}
					return super.getAdapter(adapter);
				}
			};
		}

		/**
		 * @generated
		 */
		protected LinkDescriptor(EObject source, EObject destination,
				IElementType elementType, int linkVID) {
			this(source, destination, linkVID);
			myLinkElement = null;
			final IElementType elementTypeCopy = elementType;
			mySemanticAdapter = new IAdaptable() {
				public Object getAdapter(Class adapter) {
					if (IElementType.class.equals(adapter)) {
						return elementTypeCopy;
					}
					return null;
				}
			};
		}

		/**
		 * @generated
		 */
		private LinkDescriptor(EObject source, EObject destination, int linkVID) {
			mySource = source;
			myDestination = destination;
			myVisualID = linkVID;
		}

		/**
		 * @generated
		 */
		protected EObject getSource() {
			return mySource;
		}

		/**
		 * @generated
		 */
		protected EObject getDestination() {
			return myDestination;
		}

		/**
		 * @generated
		 */
		protected EObject getLinkElement() {
			return myLinkElement;
		}

		/**
		 * @generated
		 */
		protected int getVisualID() {
			return myVisualID;
		}

		/**
		 * @generated
		 */
		protected IAdaptable getSemanticAdapter() {
			return mySemanticAdapter;
		}
	}

	@Override
    protected boolean isOrphaned(Collection<EObject> semanticChildren, View view) {
		try {
			return super.isOrphaned(semanticChildren, view);
			
		} catch (NullPointerException e) {	
	        EObject element = view.getElement();
	        if (semanticChildren.contains(element)) {
	            if (view instanceof Edge) {
	                Edge edge = (Edge) view;
	                if (edge.getSource() == null || edge.getSource().getElement() != getSourceElement(element)
	                    || edge.getTarget().getElement() != getTargetElement(element))
	                    return true;
	            }
	        } else {
	            return true;
	        }
		}
        return false;
    }
	
}
