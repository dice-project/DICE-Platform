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
package org.eclipse.epf.diagram.add.edit.policies;

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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.add.edit.parts.ActivityDetailDiagramEditPart;
import org.eclipse.epf.diagram.add.edit.parts.LinkEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleTaskCompositeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.TaskNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductCompositeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductDescriptorNodeEditPart;
import org.eclipse.epf.diagram.add.part.DiagramVisualIDRegistry;
import org.eclipse.epf.diagram.add.providers.DiagramElementTypes;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
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

/**
 * @generated
 */
public class ActivityDetailDiagramCanonicalEditPolicy extends
		CanonicalConnectionEditPolicy {

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
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		List result = new LinkedList();
		EObject modelObject = ((View) getHost().getModel()).getElement();
		View viewObject = (View) getHost().getModel();
		EObject nextValue;
		int nodeVID;
		for (Iterator values = ((NodeContainer) modelObject).getNodes()
				.iterator(); values.hasNext();) {
			nextValue = (EObject) values.next();
			nodeVID = DiagramVisualIDRegistry.getNodeVisualID(viewObject,
					nextValue);
			switch (nodeVID) {
			case RoleTaskCompositeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case WorkProductCompositeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			}
		}
		return result;
	}

	/**
	 * @modified
	 */
	protected boolean shouldDeleteView(View view) {
		return view.isSetElement()
				&& view.getElement() != null
				&& (view.getElement().eIsProxy() || view.getElement() instanceof Node);
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	private static void getConnections(Collection connections,
			NodeContainer container) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			Node node = (Node) iter.next();
			connections.addAll(node.getOutgoingConnections());
			if (node instanceof NodeContainer) {
				getConnections(connections, (NodeContainer) node);
			}
		}
	}

	/**
	 * @modified
	 */
	protected List getSemanticConnectionsList() {
		ActivityDetailDiagram add = (ActivityDetailDiagram) ((View) getHost()
				.getModel()).getElement();
		List connections = new ArrayList();
		getConnections(connections, add);
		return connections;
	}

	/**
	 * @modified
	 */
	protected EObject getSourceElement(EObject relationship) {
		if (relationship instanceof Link) {
			return ((Link) relationship).getSource();
		}
		return null;
	}

	/**
	 * @modified
	 */
	protected EObject getTargetElement(EObject relationship) {
		if (relationship instanceof Link) {
			return ((Link) relationship).getTarget();
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

	@Override
	protected void refreshSemantic() {
		List createdViews = new LinkedList();
		createdViews.addAll(refreshSemanticChildren());
		List createdConnectionViews = new LinkedList();
		createdConnectionViews.addAll(refreshSemanticConnections());
		createdConnectionViews.addAll(refreshConnections());

		//		if (createdViews.size() > 1) {
		//			// perform a layout of the container
		//			DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host()
		//					.getEditingDomain(), createdViews, host());
		//			executeCommand(new ICommandProxy(layoutCmd));
		//		}

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
				int diagramLinkVisualID = DiagramVisualIDRegistry
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
		int diagramElementVisualID = DiagramVisualIDRegistry.getVisualID(view);
		switch (diagramElementVisualID) {
		case RoleTaskCompositeEditPart.VISUAL_ID:
		case WorkProductCompositeEditPart.VISUAL_ID:
		case RoleNodeEditPart.VISUAL_ID:
		case TaskNodeEditPart.VISUAL_ID:
		case WorkProductDescriptorNodeEditPart.VISUAL_ID:
		case ActivityDetailDiagramEditPart.VISUAL_ID: {
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
		storeTypeModelFacetLinks_Link_3001(container, containerMetaclass);
	}

	/**
	 * @generated
	 */
	private void storeTypeModelFacetLinks_Link_3001(EObject container,
			EClass containerMetaclass) {
		if (ModelPackage.eINSTANCE.getNode().isSuperTypeOf(containerMetaclass)) {
			for (Iterator values = ((Node) container).getOutgoingConnections()
					.iterator(); values.hasNext();) {
				EObject nextValue = ((EObject) values.next());
				int linkVID = DiagramVisualIDRegistry
						.getLinkWithClassVisualID(nextValue);
				if (LinkEditPart.VISUAL_ID == linkVID) {
					Object structuralFeatureResult = ((Link) nextValue)
							.getTarget();
					if (structuralFeatureResult instanceof EObject) {
						EObject dst = (EObject) structuralFeatureResult;
						structuralFeatureResult = ((Link) nextValue)
								.getSource();
						if (structuralFeatureResult instanceof EObject) {
							EObject src = (EObject) structuralFeatureResult;
							myLinkDescriptors.add(new LinkDescriptor(src, dst,
									nextValue, DiagramElementTypes.Link_3001,
									linkVID));
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

}