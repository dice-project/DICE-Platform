//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.diagram.model.util.IWPDDiagramChangeListener;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Work Product Dependency Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class WorkProductDependencyDiagramImpl extends DiagramImpl implements
		WorkProductDependencyDiagram {
	private class WPDDiagramChangeListener extends ActivityAdapter implements
			IWPDDiagramChangeListener {

	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	protected WorkProductDependencyDiagramImpl() {
		super();

		diagramChangeListener = new WPDDiagramChangeListener();
	}

	protected Class getDiagramChangeListenerType() {
		return IWPDDiagramChangeListener.class;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.WORK_PRODUCT_DEPENDENCY_DIAGRAM;
	}

	private static final AdapterFactory[] DEFAULT_ADAPTER_FACTORIES = {			
		TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory() 
	}; 

	//
	// Start custom code
	//
	public Collection getChildren() {
		ITreeItemContentProvider adapter = null;
		AdapterFactory adapterFactory = null;
		if (wrapper != null) {
			//https://bugs.eclipse.org/bugs/show_bug.cgi?id=147552
			List wrappers = ProcessUtil.getWrappers(wrapper, DEFAULT_ADAPTER_FACTORIES);
			if(wrappers != null && wrappers.size() > 0){
				adapter = (ITreeItemContentProvider)wrappers.get(0);
			}
		} else {
			if (filter == null) {
				adapter = (ITreeItemContentProvider) TngAdapterFactory.INSTANCE
						.getPBS_ComposedAdapterFactory().adapt(getObject(),
								ITreeItemContentProvider.class);
			} else if (filter instanceof IAdapterFactoryFilter) {
				adapterFactory = ((IAdapterFactoryFilter) filter)
						.getWPBSAdapterFactory();
				adapter = (ITreeItemContentProvider) adapterFactory.adapt(
						getObject(), ITreeItemContentProvider.class);
			}
		}
		if (adapter != null) {
			// return adapter.getChildren(getObject());
			// commented above line, For diagrams - rollup should be false, for
			// handling rollup state below code.
			List children = new ArrayList();
			extractChildren(adapter, getObject(), children, true);
			return children;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	protected void populateNodes() {
		// Activity act = (Activity) getObject();

		// get all WorkProductDescriptors under the activity
		//
		super.populateNodes();
		// ITreeItemContentProvider adapter = (ITreeItemContentProvider)
		// TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory().adapt(act,
		// ITreeItemContentProvider.class);

		// ArrayList wpDescriptors = new ArrayList();
		ArrayList nodes = new ArrayList();
		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			Object e = iter.next();
			Object element = TngUtil.unwrap(e);
			if (element instanceof WorkProductDescriptor) {
				WorkProductNode node = (WorkProductNode) GraphicalDataHelper.findNode(this, element);
				if(node == null) {
					node = (WorkProductNode) toNode((MethodElement) element);
				}
				if (e instanceof BreakdownElementWrapperItemProvider) {
					BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) e;
					if (wrapper.isReadOnly()) {
						((NamedNodeImpl) node).itemProvider = wrapper;
						node.setReadOnly(true);
					}
				}
				if (((WorkProductDescriptor) element).getWorkProduct() instanceof Artifact) {
					node.setType(1);
				}
				if (((WorkProductDescriptor) element).getWorkProduct() instanceof Deliverable) {
					node.setType(2);
				}
				if (((WorkProductDescriptor) element).getWorkProduct() instanceof Outcome) {
					node.setType(3);
				}
				nodes.add(node);
			}
		}
		selectNodes(nodes);
	}
	protected Node toNode(MethodElement e) {
		if(e instanceof WorkProductDescriptor){
			return super.toNode(e);
		}
		return null;
	}

	protected Node newNode() {
		return ModelFactory.eINSTANCE.createWorkProductNode();
	}

	protected int getType() {
		return GraphicalDataHelper.WORK_PRODUCT_DEPENDENCY_DIAGRAM;
	}

	protected Class getBreakdownElementType() {
		return WorkProductDescriptor.class;
	}

	protected void addToUmaModel(int position, Node addedNode) {		
		if (addedNode.getObject() instanceof BreakdownElement) {
			((Activity) getObject()).getBreakdownElements().add(
					(BreakdownElement) addedNode.getObject());
		}
		super.addToUmaModel(position, addedNode);
	}
	
	@Override
	protected void nodeAdded(int index, Node node) {
		if(node.getObject() == null) {
			if(node instanceof WorkProductNode) {
				node.setObject(UmaFactory.eINSTANCE.createWorkProductDescriptor());
			}
		}
		super.nodeAdded(index, node);
	}
	@Override
	protected void populateLinks() {
		super.populateLinks();
		
		// Clean up un-used the links.
		for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
			Node node = ((Node) iter.next());
			ArrayList<Link> linksToRemove = new ArrayList<Link>();
			for (Iterator iterator = node
					.getIncomingConnections().iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();
				if (!isValid(link)) {
					linksToRemove.add(link);
				}
			}
			for (Iterator iterator = linksToRemove.iterator(); iterator
					.hasNext();) {
				Link link = (Link) iterator.next();
				GraphicalDataHelper.removeLink(link);
			}
			linksToRemove.clear();
			linksToRemove = null;
		}
		// Refresh links based on model info.
		for(Iterator iter = getNodes().iterator(); iter.hasNext();){
			Node node = ((Node)iter.next());
			Object wbe = null;
			//wbe = (WorkProductDescriptor) node.getObject();
			//if(wbe != null){
			wbe =  node.getObject();
			if(wbe instanceof WorkProductDescriptor){
				List impactedBy = ((WorkProductDescriptor)wbe).getImpactedBy();
				if(!impactedBy.isEmpty()){
					for(Iterator iter1 = impactedBy.iterator(); iter1.hasNext();){
						WorkProductDescriptor source = (WorkProductDescriptor)iter1.next();
						Link link = findLink(node, source);
						if(link == null){
							NamedNodeImpl nodeImpl = ((NamedNodeImpl) node);
							boolean oldNotify = nodeImpl.notificationEnabled;
							try {
								nodeImpl.notificationEnabled = false;
								nodeImpl.addIncomingConnection(source); 
							} finally {
								nodeImpl.notificationEnabled = oldNotify;
							}
						}
					}
				}
			}
		}
	}

	private boolean isValid(Link link) {
		if (link.getSource() == null || link.getTarget() == null
				|| !getNodes().contains(link.getSource())
				|| !getNodes().contains(link.getTarget())) {
			return false;
		}
		return true;
	}
	
	public Link findLink(Node target, Object sourceLinkedElement) {
		for (Iterator iter = target.getIncomingConnections().iterator(); iter.hasNext();) {
			Link link = (Link) iter.next();
			Node source = link.getSource();
			if(source.getLinkedElement() == sourceLinkedElement) {
				return link;
			}
		}
		return null;
	}
} // WorkProductDependencyDiagramImpl