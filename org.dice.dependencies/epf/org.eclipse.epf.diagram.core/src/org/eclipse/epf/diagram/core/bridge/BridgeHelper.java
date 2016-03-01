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
package org.eclipse.epf.diagram.core.bridge;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.model.DiagramResources;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResource;
import org.eclipse.epf.persistence.UnnormalizedURIException;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.JoinNode;

/**
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * 
 * @since 1.2
 */
public final class BridgeHelper {

	public static final String UMA_ELEMENT = "uma_element"; //$NON-NLS-1$

	public static final String UMA_URI = "uri"; //$NON-NLS-1$
	
	public static final String UMA_TYPE = "type"; //$NON-NLS-1$
	
	public static final String UMA_PHASE = "Phase"; //$NON-NLS-1$
	
	public static final String UMA_ITERATION = "Iteration"; //$NON-NLS-1$
	
	public static final String UMA_ACTIVITY = "Activity"; //$NON-NLS-1$
	
	public static final String UMA_TASK_DESCRIPTOR = "Task"; //$NON-NLS-1$
	
	public static final String UMA_MILESTONE = "Milestone"; //$NON-NLS-1$
	
	public static final String ANNOTATION_INHERIRED = "inherited"; //$NON-NLS-1$
	
	public static List<IElementType> elementTypes = new ArrayList<IElementType>();
	
	public static Map<String, EClass> typeStringToEClass = new HashMap<String, EClass>();
	
	private static final boolean DEBUG = DiagramCorePlugin.getDefault().isDebugging();
	
	static {
		typeStringToEClass.put(UMA_PHASE, UmaPackage.Literals.PHASE);
		typeStringToEClass.put(UMA_ITERATION, UmaPackage.Literals.ITERATION);
		typeStringToEClass.put(UMA_ACTIVITY, UmaPackage.Literals.ACTIVITY);
		typeStringToEClass.put(UMA_TASK_DESCRIPTOR, UmaPackage.Literals.TASK_DESCRIPTOR);
		typeStringToEClass.put(UMA_MILESTONE, UmaPackage.Literals.MILESTONE);
	}
	
	public static void setSemanticModel(ActivityEdge link, WorkOrder workOrder) {
		// TODO Auto-generated method stub
	}

	public static boolean isReadOnly(View view) {
		NodeAdapter nodeAdapter = BridgeHelper
				.getNodeAdapter(view.getElement());
		if(nodeAdapter != null) {
			return nodeAdapter.isTargetReadOnly();
		}
		if(view.getElement() instanceof Node) {
			return ((Node)view.getElement()).isReadOnly();
		}
		
		return false;
	}
	
	/**
	 * Gets the method element that the given view represents.
	 * 
	 * @param node
	 * @return method element that the view is representing or <code>null</code>
	 *         if the view does not represent any method element or the diagram
	 *         of the view currently is not opened in a diagram editor.
	 */
	public static MethodElement getMethodElement(View view) {
		return getMethodElement(view.getElement());
	}	
	
	/**
	 * Gets the method element that the given diagram model object represents.
	 * 
	 * @param modelObject
	 * @return method element that the model object is representing or <code>null</code>
	 *         if the model object does not represent any method element or the diagram
	 *         of the model object currently is not opened in a diagram editor.
	 */
	public static MethodElement getMethodElement(EObject modelObject) {
		if(modelObject instanceof LinkedObject) {
			return ((LinkedObject)modelObject).getLinkedElement();
		}
		else if(modelObject instanceof EModelElement) {
			return getMethodElement((EModelElement) modelObject);
		}
		return null;
	}
	
	public static MethodElement getMethodElement(View view, org.eclipse.epf.uma.Activity owner) {
		EObject modelObject = view.getElement();
		if(modelObject instanceof LinkedObject) {
			return ((LinkedObject)modelObject).getLinkedElement();
		}
		else if(modelObject instanceof EModelElement) {
			Resource resource = owner.eResource();
			if(resource != null && resource.getResourceSet() != null) {
				return getMethodElementFromAnnotation((EModelElement) modelObject, resource.getResourceSet());
			}
		}
		return null;
	}
	
	public static boolean isSuppressed(View view) {
		EObject element = view.getElement();
		if(element instanceof NamedNode) {
			return ((NamedNode)element).isSuppressed();
		}
		else if(element instanceof EModelElement) {
			NodeAdapter nodeAdapter = getNodeAdapter(element);
			if(nodeAdapter != null) {
				Object o = nodeAdapter.getWrapper();
				if(o == null) {
					o = nodeAdapter.getElement();
				}
				org.eclipse.epf.uma.Activity activity = (org.eclipse.epf.uma.Activity) getMethodElement(view.getDiagram());
				Process proc = TngUtil.getOwningProcess(activity);
				Suppression suppression = Suppression.getSuppression(proc);
				return suppression.isSuppressed(o);
			}
		}
		return false;
	}
	
//	public static Object getSemanticModel(View view) {
//		EObject element = view.getElement();
//		if(element instanceof EModelElement) {
//			return getSemanticModelFromAnnotation(element, process, adapterFactory);
//		}
//		else if(element instanceof )
//			
//		}
//	}
	
	public static void removeLink(ActivityEdge link) {
		// TODO: still need to disable notification before removing link???
		Activity activity = link.getActivity();
		ActivityNode sourceNode = link.getSource();
		ActivityNode targetNode = link.getTarget();
		boolean srcNotify = sourceNode != null ? sourceNode.eDeliver() : false;
		boolean targetNotify = targetNode != null ? targetNode.eDeliver()
				: false;
		try {
			if (sourceNode != null) {
				sourceNode.eSetDeliver(false);
			}
			if (targetNode != null) {
				targetNode.eSetDeliver(false);
			}
			link.setSource(null);
			link.setTarget(null);
			if(activity != null){
				activity.getEdges().remove(link);
			}
		} finally {
			if (sourceNode != null) {
				sourceNode.eSetDeliver(srcNotify);
			}
			if (targetNode != null) {
				targetNode.eSetDeliver(targetNotify);
			}
		}
	}

	/**
	 * Finds node whose linked object or one of its base is the given object
	 * 
	 * @param container
	 * @param object
	 * @return
	 */
	public static ActivityNode findNode(Activity container, Object object,
			boolean checkBase) {
		if (container != null) {
			for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
				ActivityNode node = (ActivityNode) iter.next();
				MethodElement e = BridgeHelper.getMethodElement(node);
				if (object == e) {
					return node;
				} else if (checkBase && e instanceof VariabilityElement) {
					for (VariabilityElement ve = ((VariabilityElement) e)
							.getVariabilityBasedOnElement(); ve != null; ve = ve
							.getVariabilityBasedOnElement()) {
						if (ve == object) {
							return node;
						}
					}
				}
			}	
		}
		return null;
	}

	public static boolean isSynchBar(ActivityNode node) {
		return node instanceof ForkNode || node instanceof JoinNode;
	}

	/**
	 * Finds node whose linked object is the given object
	 * 
	 * @return
	 */
	public static ActivityNode findNode(Activity container, Object object) {
		for (Iterator iter = container.getNodes().iterator(); iter.hasNext();) {
			ActivityNode node = (ActivityNode) iter.next();
			MethodElement element = BridgeHelper.getMethodElement(node);
			if (object == element) {
				return node;
			}
			if (object instanceof TaskDescriptor) {
				DescriptorPropUtil propUtil = DescriptorPropUtil
						.getDesciptorPropUtil();
				TaskDescriptor greenParent = (TaskDescriptor) propUtil
						.getGreenParentDescriptor((TaskDescriptor) object);
				
				while(greenParent != null) {
					if (greenParent == element) {
						return node;
					}
					greenParent = (TaskDescriptor) propUtil.getGreenParentDescriptor(greenParent);
				}

			}
		}
		return null;
	}

	public static URI getProxyURI(MethodElement e) {
		Resource resource = e.eResource();
		if (resource instanceof ILibraryResource) {
			return ((ILibraryResource) resource).getProxyURI(e);
		} else if (resource != null) {
			return resource.getURI().appendFragment(e.getGuid());
		}
		return null;
	}
	
	/**
	 * Associates a {@link EModelElement} with a {@link MethodElement} by
	 * creating or updating the UMA_ELEMENT annotation of the given
	 * {@link EModelElement}.
	 * 
	 * @param element
	 * @param me
	 * @return true if successful, false otherwise
	 * @see #addEAnnotation(EModelElement, MethodElement)
	 */
	public static boolean associate(EModelElement element, MethodElement me) {
		EAnnotation annotation = addEAnnotation(element, me);
		if(annotation != null) {
			String type = getType(me);
			if(type != null) {
				annotation.getDetails().put(UMA_TYPE, type);
			}
			return true;
		}
		return false;
	}
	
	public static EClass getType(String type) {
		return typeStringToEClass.get(type);
	}
	
	public static String getType(MethodElement me) {
		if(me instanceof Phase) {
			return UMA_PHASE;
		}
		else if(me instanceof Iteration) {
			return UMA_ITERATION;
		}
		else if(me instanceof Milestone) {
			return UMA_MILESTONE;
		}
		else if(me instanceof org.eclipse.epf.uma.Activity) {
			return UMA_ACTIVITY;
		}
		else if(me instanceof TaskDescriptor) {
			return UMA_TASK_DESCRIPTOR;
		}
		return null;
	}
	
//	/**
//	 * Creates or updates the UMA_ELEMENT annotation for the given
//	 * {@link EModelElement} with the given method element or its wrapper
//	 * 
//	 * @param element
//	 * @param me
//	 * @return
//	 */
//	public static EAnnotation addEAnnotation(EModelElement element, Object breakdownElementOrWrapper) {
//		String uriStr = null;
//		if(breakdownElementOrWrapper instanceof MethodElement) {
//			URI uri = getProxyURI((MethodElement) breakdownElementOrWrapper);
//			if(uri != null) {
//				uriStr = uri.toString();
//			}
//		}
//		else if(breakdownElementOrWrapper instanceof BreakdownElementWrapperItemProvider){
//			uriStr = Suppression.getPath((BreakdownElementWrapperItemProvider) breakdownElementOrWrapper);
//		}
//		if(uriStr != null) {
//			EAnnotation eAnnotation = element.getEAnnotation(UMA_ELEMENT);
//			if (eAnnotation == null) {
//				eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
//				eAnnotation.setSource(UMA_ELEMENT);
//				element.getEAnnotations().add(eAnnotation);
//			}
//			eAnnotation.getDetails().put(UMA_URI, uriStr);
//			return eAnnotation;
//		}
//		return null;
//	}
	
	/**
	 * Creates or updates the UMA_ELEMENT annotation for the given
	 * {@link EModelElement} with the given method element
	 * 
	 * @param element
	 * @param me
	 * @return
	 */
	public static EAnnotation addEAnnotation(EModelElement element,
			MethodElement me) {
		URI uri = getProxyURI(me);
		if (uri != null) {
			EAnnotation eAnnotation = element.getEAnnotation(UMA_ELEMENT);
			if (eAnnotation == null) {
				eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(UMA_ELEMENT);
				element.getEAnnotations().add(eAnnotation);
			}
			eAnnotation.getDetails().put(UMA_URI, uri.toString());
			return eAnnotation;
		}
		return null;
	}

	private static Activity getActivity(EModelElement e) {
		EObject o = e;
		for(; o != null && !(o instanceof Activity); o = o.eContainer());
		return (Activity) o;
	}
	
//	public static Object getSemanticModelFromAnnotation(EModelElement model, Process process, AdapterFactory adapterFactory) {
//		EAnnotation eAnnotation = model.getEAnnotation(UMA_ELEMENT);
//		if (eAnnotation != null) {
//			String uriStr = (String) eAnnotation.getDetails().get(UMA_URI);
//			if (uriStr != null) {
//				URI uri = URI.createURI(uriStr);
//				if(MultiFileURIConverter.SCHEME.equals(uri.scheme())) {
//					EObject o = process.eResource().getResourceSet().getEObject(uri, false);
//					if (o instanceof MethodElement) {
//						return (MethodElement) o;
//					} else {
//						// TODO: log error
//						System.err.println("Not a method element: " + o); //$NON-NLS-1$
//					}
//				}
//				else {
//					// must be a wrapper path
//					//
//					String[] guidPath = uri.segments();
//					return Suppression.getSuppression(process).getObjectByPath(guidPath, adapterFactory);
//				}
//			}
//		}
//		return null;
//	}
	
	public static MethodElement getMethodElementFromAnnotation(EModelElement node, ResourceSet resourceSet) { 
        EAnnotation eAnnotation = node.getEAnnotation(UMA_ELEMENT); 
        try { 
            if (eAnnotation != null) { 
                String uri = (String) eAnnotation.getDetails().get(UMA_URI); 
                if (uri != null) { 
                    EObject o = resourceSet.getEObject( 
                                    URI.createURI(uri), false); 
                    if (o instanceof MethodElement) { 
                            return (MethodElement) o; 
                    } else { 
                            if(DEBUG) { 
                                    System.err.println("Not a method element: " + o); //$NON-NLS-1$ 
                            } 
                    } 
                } 
            } 
        } catch (Exception e) { 
        	if (!(e instanceof UnnormalizedURIException)) {
                DiagramCorePlugin.getDefault().getLogger().logError(e);
        	}
        	
        	return null; 
        } 
        
        return null; 
	} 
	
	
	/**
	 * Gets the method element that the given diagram model object represents
	 * and stores the linkage info in its annotation.
	 * 
	 * @param node
	 *            the diagram model object
	 * @return method element that the model object is representing or
	 *         <code>null</code> if the model object does not represent any
	 *         method element or the diagram of the model object currently is
	 *         not opened in a diagram editor.
	 */
	public static MethodElement getMethodElementFromAnnotation(EModelElement node) {		
		Activity diagram = getActivity(node);
		if(diagram == null) {
			return null;
		}
		NodeAdapter nodeAdapter = getNodeAdapter(diagram);
		if(nodeAdapter == null || nodeAdapter.getElement() == null) {
			return null;
		}
		Resource resource = nodeAdapter.getElement().eResource();
		if (resource != null && resource.getResourceSet() != null) {
			return getMethodElementFromAnnotation(node, resource.getResourceSet());
		}
		return null;
	}
	
	public static MethodElement getMethodElement(EModelElement node) {
		NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
		return nodeAdapter != null ? nodeAdapter.getElement()
				: getMethodElementFromAnnotation(node);
	}

	public static NodeAdapter getNodeAdapter(EObject node) {
		if (node != null) {
			for (Iterator iter = node.eAdapters().iterator(); iter.hasNext();) {
				Object adapter = (Object) iter.next();
				if (adapter instanceof NodeAdapter) {
					return (NodeAdapter) adapter;
				}
			}
		}
		return null;
	}
	
	public static DiagramAdapter getDiagramAdapter(EObject node) {
		for (Iterator iter = node.eAdapters().iterator(); iter.hasNext();) {
			Object adapter = (Object) iter.next();
			if (adapter instanceof DiagramAdapter) {
				return (DiagramAdapter) adapter;
			}
		}
		return null;
	}

	/**
	 * Method to get the sources of SyncBar (ForkNode or JoinNode) inComming
	 * connections and if syncbar have incoming connection from decision point,
	 * and decision point have incomming connections (workbreaddown elemtns)
	 * collections will ignore all the incoming connection from decision point.
	 * @return
	 */
	public static void getSyncBarSourceNodes(ActivityNode node,
			Collection actNodes) {
		for (Iterator iter = node.getIncomings().iterator(); iter.hasNext();) {
			ActivityEdge link = (ActivityEdge) iter.next();
			ActivityNode source = link.getSource();
			if (getMethodElement(source) instanceof WorkBreakdownElement) {
				actNodes.add(source);
			} else if (isSynchBar(source)) {
				getSyncBarSourceNodes(source, actNodes);
			}
		}
	}

	/**
	 * Gets all nodes with the given type that are direct or indirect targets of
	 * the given node.
	 * 
	 * @param actNodes
	 * @param node
	 * @param type
	 *            the type of method element associated with the view
	 */
	public static void getTargetNodes(Collection actNodes, ActivityNode node,
			Class type) {
		if (node != null) {
			for (Iterator iter = node.getOutgoings().iterator(); iter.hasNext();) {
				ActivityEdge link = (ActivityEdge) iter.next();
				ActivityNode target = link.getTarget();
				if (type.isInstance(getMethodElement(target))) {
					actNodes.add(target);
				} else if (target instanceof ControlNode) {
					getTargetNodes(actNodes, target, type);
				}
			}
		}
	}
	
	public static void getSuccessorNodes(Collection<ActivityNode> actNodes, ActivityNode node) {
		if (node != null) {
			for (ActivityEdge link : node.getOutgoings()) {
				ActivityNode target = link.getTarget();
				if (getMethodElement(target) instanceof WorkBreakdownElement) {
					actNodes.add(target);
				} else if (isSynchBar(target)) {
					getSuccessorNodes(actNodes, target);
				}
			}
		}		
	}
	
	public static void getPredecessorNodes(Collection<ActivityNode> actNodes, ActivityNode node) {
		if (node != null) {
			for (ActivityEdge link : node.getIncomings()) {
				ActivityNode source = link.getSource();
				if (getMethodElement(source) instanceof WorkBreakdownElement) {
					actNodes.add(source);
				} else if (isSynchBar(source)) {
					getPredecessorNodes(actNodes, source);
				}
			}
		}		
	}
	
	/**
	 * Gets all nodes with the given type that are direct or indirect sources of
	 * the given node.
	 * 
	 * @param actNodes
	 * @param node
	 * @param type
	 *            the type of method element associated with the view
	 */

	public static void getSourceNodes(Collection actNodes, ActivityNode node,
			Class type) {
		if (node != null) {
			for (Iterator iter = node.getIncomings().iterator(); iter.hasNext();) {
				ActivityEdge link = (ActivityEdge) iter.next();
				ActivityNode source = link.getSource();
				if (type.isInstance(getMethodElement(source))) {
					actNodes.add(source);
				} else if (source instanceof ControlNode) {
					getSourceNodes(actNodes, source, type);
				}
			}
		}
	}
	
	/*
	 * Method to collect synchronization bar outgoing connection
	 * except any connection going to decision points.  
	 */
	public static void getSyncBarTargetNodes(ActivityNode typedNode, Collection actNodes){
		for (Iterator iter = typedNode.getOutgoings().iterator(); iter.hasNext();) {
				ActivityEdge link = (ActivityEdge) iter.next();
				ActivityNode target = link.getTarget();
				if(BridgeHelper.getMethodElement(target) instanceof WorkBreakdownElement){
					actNodes.add(target);
				} else if (isSynchBar(target)) {
					getSyncBarTargetNodes(target, actNodes);
				}
		}
	}
	
	/**
	 * Method to check before deleting a link. If duplicate predecessor exists
	 * in the legacy data, check if deleting link should remove all the
	 * predecessors or not by verifying if target or indirect target have direct
	 * or indirect links.
	 */
	public static boolean canRemoveAllPreds(ActivityEdge link, ActivityNode oldSource,
			ActivityNode oldTarget) {
		MethodElement targetElement = getMethodElement(oldTarget);
		if (targetElement instanceof WorkBreakdownElement) {
			List inlist = oldTarget.getIncomings();
			for (Iterator itor = inlist.iterator(); itor.hasNext();) {
				ActivityEdge incominglink = (ActivityEdge) itor.next();
				// RATLC00384245 : Predecessor changes should be done only in
				// case of Synchronization Bar.
				if (isSynchBar(incominglink.getSource())) {
					Collection col = new ArrayList(); 
					getSourceNodes(col, incominglink.getSource(),
							WorkBreakdownElement.class);
					if (col.contains(oldSource)) {
						return false;
					}
				} else if (incominglink.getSource() != null &&
						getMethodElement(incominglink.getSource()) instanceof WorkBreakdownElement) {
					if (incominglink.getSource().equals(oldSource))
						return false;
				}
			}
		}
		return true;
	}
	
	public static void markInherited(EModelElement element) {
		if(isInherited(element)) {
			return;
		}
		EAnnotation eAnnotation = element.getEAnnotation(ANNOTATION_INHERIRED);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(ANNOTATION_INHERIRED);
			element.getEAnnotations().add(eAnnotation);
		}
	}
	public static void unmarkInHerited(EModelElement element) {
		EAnnotation eAnnotation = element.getEAnnotation(ANNOTATION_INHERIRED);
		if (eAnnotation != null) {
			element.getEAnnotations().remove(eAnnotation);
		}
	}
	

	public static Diagram copyDiagram(Diagram baseDiagram) {
		Diagram copy = (Diagram) EcoreUtil.copy(baseDiagram);

		// HACK:
		// go thru the nodes of the diagram copy, if any node is a UI-only node
		// (see TypedNode)
		// save the GUID of the original one in its briefDescription to remember
		// who is base.
		//
//		int size = copy.getContained().size();
//		for (int i = 0; i < size; i++) {
//			GraphNode gNode = (GraphNode) copy.getContained().get(i);
//			if (GraphicalDataHelper.isUIGraphNode(gNode)) {
//				gNode.setBriefDescription(((DiagramElement) baseDiagram
//						.getContained().get(i)).getGuid());
//			}
//		}
		List children = copy.getChildren();
		int size = children.size();		
		for (int i = 0; i < size; i++) {
			View view = (View) children.get(i);
			markInherited(view);
		}

		return copy;
	}

	public static EList addEAnnotationDetail(EModelElement element, String detailName, String detailValue ) {
		EAnnotation eAnnotation = element.getEAnnotation(UMA_ELEMENT);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(UMA_ELEMENT);
			// detail must be put before the new annotation can be added to the annotation list
			// so any adapter that listen to change in the annotation list can get the details
			// when it gets notified.
			//
			eAnnotation.getDetails().put(UMA_TYPE, detailValue);
			element.getEAnnotations().add(eAnnotation);
		}
		else {
			eAnnotation.getDetails().put(UMA_TYPE, detailValue);
		}		
		return element.getEAnnotations();
	}
	
	/**
	 * Creates and/or adds the eAnnotation detail for type. {@link BridgeHelper.UMA_TYPE}
	 * @param element
	 * @param type
	 */
	public static EList addEAnnotationType(EModelElement element, String type) {
		EList result = addEAnnotationDetail(element, UMA_TYPE, type);
		
		if(element instanceof ActivityNode) {
			ActivityNode node = (ActivityNode) element;
//			ActivityParameterNode node = (ActivityParameterNode) element;
			MethodElement e = BridgeHelper.getMethodElement(node);				
			if(e instanceof BreakdownElement && 
					type != null && !type.equals(BridgeHelper.getType(e))) {
				// replace the currently linked method element with the right one
				//
				EClass rightType = getType(type);
				if(rightType != null) {
					BreakdownElement rightElement = (BreakdownElement) UmaFactory.eINSTANCE.create(rightType);
					org.eclipse.epf.uma.Activity activity = ((BreakdownElement)e).getSuperActivities();
					if(activity != null) {
						List list = activity.getBreakdownElements();
						list.set(list.indexOf(e), rightElement);
						ProcessPackage pkg = (ProcessPackage) activity.eContainer();
						if (pkg != null) {
							pkg.getProcessElements().add(rightElement);
							// Also set the rightElement to its own process package.
							List childPkgs = pkg.getChildPackages();
							if (!childPkgs.isEmpty()) {
								for (Iterator iter = pkg.getChildPackages()
										.iterator(); iter.hasNext();) {
									ProcessPackage child = (ProcessPackage) iter
									.next();
									List<ProcessElement> pList = child
									.getProcessElements();
									if (!pList.isEmpty()) {
										if (pList.contains(e)) {
											pList.set(pList.indexOf(e),
													rightElement);
										}
									}
								}
							}
						}

						// associate the new method element with the node
						//
						associate(node, rightElement);
						// remove old node adapter and add new one
						//
						NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(node);
						if(nodeAdapter != null) {
							nodeAdapter.dispose();
						}					
						DiagramAdapter diagramAdapter = (DiagramAdapter) getNodeAdapter(node.getActivity());
						diagramAdapter.addNodeAdapterTo(node);
					}
				}

			}
		}

		return result;
	}
	
	/**
	 * To get the eAnnotation detail based on name. (eg: type)
	 * @deprecated replaced with {@link #getEAnnotationDetail(EModelElement, String)}
	 */
	public static String getEAnnotationType(EModelElement element, String detailName) {
		return getEAnnotationDetail(element, detailName);
	}
	
	public static String getEAnnotationDetail(EModelElement element, String detailName) {
		EAnnotation eAnnotation = element.getEAnnotation(UMA_ELEMENT);
		if(eAnnotation != null){
			return (String)eAnnotation.getDetails().get(detailName);
		}
		return null;
	}
	
	public static String getType(ActivityNode node) {
		return getEAnnotationDetail(node, UMA_TYPE);
	}
	
	/**
	 * 
	 * @param e
	 * @return one of the type constants: {@link #UMA_ACTIVITY},
	 *         {@link #UMA_ITERATION}, {@link #UMA_MILESTONE},
	 *         {@link #UMA_PHASE}, {@link #UMA_TASK_DESCRIPTOR},
	 */
	public static String getType(BreakdownElement e) {
		if(e instanceof Iteration) {
			return UMA_ITERATION;
		}
		else if(e instanceof Phase) {
			return UMA_PHASE;
		}
		else if(e instanceof org.eclipse.epf.uma.Activity) {
			return UMA_ACTIVITY;
		}
		else if(e instanceof Milestone) {
			return UMA_MILESTONE;
		}
		else if(e instanceof TaskDescriptor) {
			return UMA_TASK_DESCRIPTOR;
		}
		else {
			return null;
		}
	}
	
	public static boolean isInherited(EModelElement e){
		EAnnotation eAnnotation = e.getEAnnotation(ANNOTATION_INHERIRED);
		if (eAnnotation != null) {
			String source = eAnnotation.getSource();
			if(source != null && source.length() > 0){
				return true;
			}
		}
		return false;
	}
	
	private static EClass getEClassFromType(String annotationType) {
		if(UMA_ACTIVITY.equals(annotationType)) {
			return UmaPackage.eINSTANCE.getActivity();
		}
		else if(UMA_ITERATION.equals(annotationType)) {
			return UmaPackage.eINSTANCE.getIteration();
		} else if(UMA_MILESTONE.equals(annotationType)) {
			return UmaPackage.eINSTANCE.getMilestone();
		} else if(UMA_PHASE.equals(annotationType)) {
			return UmaPackage.eINSTANCE.getPhase();
		} else if(UMA_TASK_DESCRIPTOR.equals(annotationType)) {
			return UmaPackage.eINSTANCE.getTaskDescriptor();
		}
		return null;
	}
	
	public static void setDefaultName(ActivityNode node) {
		Activity parentAct = node.getActivity();
		MethodElement element = BridgeHelper.getMethodElement(parentAct);
		MethodElement obj = BridgeHelper.getMethodElement(node);
		int classID = obj.eClass().getClassifierID();
		org.eclipse.epf.uma.Activity act = (org.eclipse.epf.uma.Activity) element;
		ArrayList siblings = new ArrayList();
		for (Iterator iter = act.getBreakdownElements().iterator(); iter
				.hasNext();) {
			BreakdownElement e = (BreakdownElement) iter.next();
			if (e.eClass().getClassifierID() == classID) {
				siblings.add(e);
			}
		}
		String baseName = MessageFormat
				.format(
						DiagramResources.defaultBaseName, new Object[] { TngUtil.getTypeText(obj.eClass().getName()) }); 
		TngUtil.setDefaultName(siblings, obj, baseName);
		node.setName(obj.getName());
	}
	
	public static View getView(View diagram, Object node) {
		for (Iterator iter = diagram.getChildren().iterator(); iter.hasNext();) {
			View child = (View) iter.next();
			if(child.getElement() == node) {
				return child;
			}
			if(child.getElement() instanceof ActivityPartition){
				for (Iterator iterator = child.getChildren().iterator(); iterator
						.hasNext();) {
					View element = (View) iterator.next();
					if(element.getElement() == node) {
						return child;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Gets node name for the given method element.
	 * 
	 * @param e
	 * @return
	 */
	public static String getNodeName(MethodElement e) {
		if(e instanceof BreakdownElement) {
			return ProcessUtil.getPresentationName((BreakdownElement) e);
		}
		else if(e instanceof DescribableElement) {
			return ((DescribableElement)e).getPresentationName();
		}
		else {
			return e.getName();
		}
	}
	
}
