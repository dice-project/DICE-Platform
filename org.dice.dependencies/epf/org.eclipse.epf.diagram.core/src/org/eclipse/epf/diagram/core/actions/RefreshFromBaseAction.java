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
/**
 * 
 */
package org.eclipse.epf.diagram.core.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramService;
import org.eclipse.epf.diagram.model.impl.NodeImpl;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.StructuredActivityNode;

/**
 * Action to refresh the latest changes from base activity diagram into 
 * extend's Activity diagram. Action will keep the existing immediate children's 
 * information of extend diagram, only refresh the base diagram's related information.
 *  
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 */
public class RefreshFromBaseAction implements IObjectActionDelegate {

	private EditPart editPart;
	private IWorkbenchPart targetPart;
	/**
	 * 
	 */
	public RefreshFromBaseAction() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		refreshFromBaseCommand.execute();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		editPart = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof EditPart) {
				editPart = (EditPart) structuredSelection
						.getFirstElement();
			}
		}
		if(editPart != null){
			action.setChecked(false);
			// Check if diagram readonly.
			if(editPart instanceof DiagramEditPart){
				if(!((DiagramEditPart)editPart).isEditModeEnabled()){
					action.setEnabled(false);
					return;
				}
			}

			// Checked variability 
			Diagram diagram = (Diagram)editPart.getModel();
			EObject model = diagram.getElement();
			Activity e = null;
			if(model instanceof org.eclipse.uml2.uml.Activity){
				e = (Activity)BridgeHelper.getMethodElement((EModelElement)model);
			}else if(model instanceof org.eclipse.epf.diagram.model.Diagram){
				e = (Activity)((org.eclipse.epf.diagram.model.Diagram)model).getLinkedElement();
			}
			if(e != null){
				if(e instanceof Activity){
					if(((Activity)e).getVariabilityBasedOnElement() == null
							|| (((Activity)e).getVariabilityBasedOnElement() != null
							&& ((Activity)e).getVariabilityType() != VariabilityType.EXTENDS)){
						action.setEnabled(false);
						return;
					}
				}

			}
		}else{
			action.setEnabled(false);
		}
	}
	
	/**
	 * Refresh base from previous graphical editor code.
	 * TODO: handle consumer (set)
	 * 
	 */
	private Command refreshFromBaseCommand = new Command(DiagramCoreResources.AbstractDiagramEditorrefreshfrombase) { //$NON-NLS-1$
		private ArrayList oldContent = new ArrayList();

		public void execute() {
			Diagram diagram = (Diagram) editPart.getModel();
			
			EditPart parent = editPart.getParent();
			// back up old diagram content
			//
			oldContent.clear();
			oldContent.addAll(diagram.getPersistedChildren());

			boolean refreshed = //DiagramHelper.refreshFromBase(diagram);
				refreshFromBase(diagram);
			if(!refreshed) return;
			
			if (editPart.isActive()) {
				editPart.deactivate();
			}
			//diagram.removeConsumer(this);
			//createEditPart();
			editPart = (EditPart)EditPartService.getInstance().createGraphicEditPart(diagram);
			editPart.setParent(parent);
			parent.getViewer().setContents(editPart);
		}

		public void undo() {
			Diagram diagram = (Diagram) editPart.getModel();
			diagram.getChildren().clear();
			diagram.getChildren().addAll(oldContent);
			if (editPart.isActive()) {
				editPart.deactivate();
			}
			//diagram.removeConsumer(this);
			editPart = (EditPart)EditPartService.getInstance().createGraphicEditPart(diagram);
			getGraphicalViewer().setContents(editPart);
			editPart.activate();
		}
	};
	
	private GraphicalViewer getGraphicalViewer(){
		return (GraphicalViewer)editPart.getRoot().getViewer();
	}
	
	public boolean refreshFromBase(final Diagram diagram) {
		final boolean[] resultHolder = new boolean[1];
		try {
			TxUtil.runInTransaction(diagram, new Runnable() {

				public void run() {
					resultHolder[0] = doRefreshFromBase(diagram);
				}
				
			});			
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
		return resultHolder[0];
	}
	
	public boolean refreshFromBase(final Diagram diagram, final Activity activity) {
		final boolean[] resultHolder = new boolean[1];
		try {
			TxUtil.runInTransaction(diagram, new Runnable() {

				public void run() {
					resultHolder[0] = doRefreshFromBase(diagram, activity);
				}
				
			});			
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
		return resultHolder[0];
	}

	private static interface IDiagramAssociationHelper {
		/**
		 * Gets the {@link MethodElement} that the given {@link Node} represents.
		 * 
		 * @param node
		 * @param resourceSet the resource set of the library
		 * @return
		 */
		MethodElement getMethodElement(Node node, ResourceSet resourceSet);
		
		/**
		 * Finds in the given diagram the node that represents the given {@link MethodElement}.
		 *  
		 * @param diagram
		 * @param e
		 * @return
		 */
		Node findNode(Diagram diagram, MethodElement e);
				
		/**
		 * Re-associates activity nodes in the base diagram copy with corresponding
		 * locally contributors or replacers if there is any.
		 * 
		 * @param activity
		 * @param copyOfBaseDiagram
		 */
		void reassociate(Activity activity, Diagram copyOfBaseDiagram);
		
		void addNodeModels(EObject diagramModel, Collection<?> nodeModels);
		
		void addEdgeModels(EObject diagramModel, Collection<?> edgeModels);
		
		void removeNodeModel(EObject diagramModel, EObject nodeModel);
		
		void removeEdgeModel(EObject diagramModel, EObject edgeModel);

		Collection<?> getNodeModels(EObject element);

		Collection<?> getEdgeModels(EObject element);
	}
	
	private static final IDiagramAssociationHelper ADAssociationHelper = new IDiagramAssociationHelper() {

		public Node findNode(Diagram diagram, MethodElement e) {
			return DiagramHelper.findNode(diagram, e);
		}

		public MethodElement getMethodElement(Node node, ResourceSet resourceSet) {
			EObject umlObject = node.getElement();
			if (umlObject instanceof StructuredActivityNode
					|| umlObject instanceof ObjectNode) {
				MethodElement e = BridgeHelper
						.getMethodElement((ActivityNode) umlObject);
				if (e == null && resourceSet != null) {
					// base diagram is not open, try to get from
					// annotation
					//
					e = BridgeHelper
							.getMethodElementFromAnnotation(
									(EModelElement) umlObject, resourceSet);
				}
				return e;
			}
			return null;
		}

		public void reassociate(Activity activity, Diagram copyOfBaseDiagram) {
			DiagramHelper.reassociate(activity, copyOfBaseDiagram);
		}

		public void addEdgeModels(EObject diagramModel, Collection<?> edgeModels) {
			if(!(diagramModel instanceof org.eclipse.uml2.uml.Activity)) {
				return;
			}
			DiagramAdapter diagramAdapter = BridgeHelper.getDiagramAdapter(diagramModel);
			boolean oldNotify = false;
			if(diagramAdapter != null) {
				oldNotify = diagramAdapter.isNotificationEnabled();
				diagramAdapter.setNotificationEnabled(false);
			}
			try {
				((org.eclipse.uml2.uml.Activity) diagramModel).getEdges().addAll((Collection<? extends ActivityEdge>) edgeModels);
			}
			finally {
				if(diagramAdapter != null) {
					diagramAdapter.setNotificationEnabled(oldNotify);
				}
			}
		}

		public void addNodeModels(EObject diagramModel, Collection<?> nodeModels) {
			if(!(diagramModel instanceof org.eclipse.uml2.uml.Activity)) {
				return;
			}
			DiagramAdapter diagramAdapter = BridgeHelper.getDiagramAdapter(diagramModel);
			boolean oldNotify = false;
			if(diagramAdapter != null) {
				oldNotify = diagramAdapter.isNotificationEnabled();
				diagramAdapter.setNotificationEnabled(false);
			}
			try {
				((org.eclipse.uml2.uml.Activity) diagramModel).getNodes().addAll((Collection<? extends ActivityNode>) nodeModels);
			}
			finally {
				if(diagramAdapter != null) {
					diagramAdapter.setNotificationEnabled(oldNotify);
				}
			}
		}

		public void removeEdgeModel(EObject diagramModel, EObject edgeModel) {
			if(!(diagramModel instanceof org.eclipse.uml2.uml.Activity)) {
				return;
			}
			DiagramAdapter diagramAdapter = BridgeHelper.getDiagramAdapter(diagramModel);
			boolean oldNotify = false;
			if(diagramAdapter != null) {
				oldNotify = diagramAdapter.isNotificationEnabled();
				diagramAdapter.setNotificationEnabled(false);
			}
			try {
				((org.eclipse.uml2.uml.Activity) diagramModel).getEdges().remove(edgeModel);
			}
			finally {
				if(diagramAdapter != null) {
					diagramAdapter.setNotificationEnabled(oldNotify);
				}
			}
		}

		public void removeNodeModel(EObject diagramModel, EObject nodeModel) {
			if(!(diagramModel instanceof org.eclipse.uml2.uml.Activity)) {
				return;
			}
			DiagramAdapter diagramAdapter = BridgeHelper.getDiagramAdapter(diagramModel);
			boolean oldNotify = false;
			if(diagramAdapter != null) {
				oldNotify = diagramAdapter.isNotificationEnabled();
				diagramAdapter.setNotificationEnabled(false);
			}
			try {
				((org.eclipse.uml2.uml.Activity) diagramModel).getNodes().remove(nodeModel);
			}
			finally {
				if(diagramAdapter != null) {
					diagramAdapter.setNotificationEnabled(oldNotify);
				}
			}
		}

		public Collection<?> getEdgeModels(EObject element) {
			if(element instanceof org.eclipse.uml2.uml.Activity) {
				return ((org.eclipse.uml2.uml.Activity) element).getEdges();
			}
			return Collections.EMPTY_LIST;
		}

		public Collection<?> getNodeModels(EObject element) {
			if(element instanceof org.eclipse.uml2.uml.Activity) {
				return ((org.eclipse.uml2.uml.Activity) element).getNodes();
			}
			return Collections.EMPTY_LIST;
		}

	};
	
	private static final IDiagramAssociationHelper diagramAssociationHelper = new IDiagramAssociationHelper() {

		public Node findNode(Diagram diagram, MethodElement e) {
			return DiagramHelper.findNodeInModelDiagram(diagram, e);
		}

		public MethodElement getMethodElement(Node node, ResourceSet resourceSet) {
			EObject element = node.getElement();
			return element instanceof org.eclipse.epf.diagram.model.Node? 
					((org.eclipse.epf.diagram.model.Node) element).getLinkedElement() : null;
		}

		public void reassociate(Activity activity, Diagram copyOfBaseDiagram) {
			// TODO Auto-generated method stub
			
		}
		
		public void addNodeModels(EObject diagramModel, Collection<?> nodeModels) {
			if(!(diagramModel instanceof org.eclipse.epf.diagram.model.Diagram)) {
				return;
			}
			org.eclipse.epf.diagram.model.Diagram diagram = (org.eclipse.epf.diagram.model.Diagram) diagramModel;
			boolean oldNotify = ((NodeImpl)diagram).isNotificationEnabled();
			try {
				((NodeImpl)diagram).setNotificationEnabled(false);
				diagram.getNodes().addAll((Collection<? extends org.eclipse.epf.diagram.model.Node>) nodeModels);
			}
			finally {
				((NodeImpl)diagram).setNotificationEnabled(oldNotify);
			}
		}

		public void addEdgeModels(EObject diagramModel, Collection<?> edgeModels) {
			//
		}

		public void removeEdgeModel(EObject diagramModel, EObject edgeModel) {
		}

		public void removeNodeModel(EObject diagramModel, EObject nodeModel) {
			if(!(diagramModel instanceof org.eclipse.epf.diagram.model.Diagram)) {
				return;
			}
			org.eclipse.epf.diagram.model.Diagram diagram = (org.eclipse.epf.diagram.model.Diagram) diagramModel;
			boolean oldNotify = ((NodeImpl)diagram).isNotificationEnabled();
			try {
				((NodeImpl)diagram).setNotificationEnabled(false);
				diagram.getNodes().remove(nodeModel);
			}
			finally {
				((NodeImpl)diagram).setNotificationEnabled(oldNotify);
			}
		}

		public Collection<?> getEdgeModels(EObject element) {
			return Collections.EMPTY_LIST;
		}

		public Collection<?> getNodeModels(EObject element) {
			if(element instanceof org.eclipse.epf.diagram.model.Diagram) {
				return ((org.eclipse.epf.diagram.model.Diagram) element).getNodes();
			}
			return Collections.EMPTY_LIST;
		}
		
	};
	
	/**
	 * Checks if the given node is one of the specified nodes or their children.
	 * 
	 * @param nodes
	 * @param node
	 */
	private static boolean contains(Collection<?> nodes, View node) {
		if(nodes.contains(node)) {
			return true;
		}
		for (Object child : nodes) {
			if(child instanceof Node && contains(((Node) child).getChildren(), node)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean doRefreshFromBase(Diagram diagram) {
		Activity act = null;
		EObject model = diagram.getElement();
		if (model instanceof org.eclipse.uml2.uml.Activity) {
			act = (Activity) BridgeHelper
					.getMethodElement((EModelElement) model);
		} else if (model instanceof org.eclipse.epf.diagram.model.Diagram) {
			act = (Activity) ((org.eclipse.epf.diagram.model.Diagram) model)
					.getLinkedElement();
		}
		if (act == null)
			return false;
		
		return doRefreshFromBase(diagram, act);
		
	}
	
	private boolean doRefreshFromBase(Diagram diagram, Activity act) {
		EObject model = diagram.getElement();
		Activity base = (Activity) act.getVariabilityBasedOnElement();
		if (base == null
				|| act.getVariabilityType() == VariabilityType.LOCAL_REPLACEMENT) {
			return false;
		}
		int type = DiagramHelper.getDiagramType(diagram);
		DiagramService diagramSvc = new DiagramService();
		try {
			Diagram baseDiagram = diagramSvc.getBaseDiagram(act, type);
			if (baseDiagram == null)
				return false;

			Collection<Node> oldNodes = new HashSet<Node>();
			Collection<Edge> oldEdges = new HashSet<Edge>();
			Diagram baseCopy = DiagramHelper.copyDiagram(TransactionUtil
					.getEditingDomain(diagram), baseDiagram);
			
			IDiagramAssociationHelper helper = type == IDiagramManager.ACTIVITY_DIAGRAM ? ADAssociationHelper
					: diagramAssociationHelper; 
			
			boolean notification = model.eDeliver();
			try {
//				model.eSetDeliver(false);
				ResourceSet resourceSet = base.eResource().getResourceSet();
				// Collect the base nodes that exist in extend diagram
				for (Iterator<?> iter = baseDiagram.getChildren().iterator(); iter
						.hasNext();) {
					Node baseNode = (Node) iter.next();
					MethodElement e = helper.getMethodElement(baseNode, resourceSet);
					if (e != null) {
						Node node = helper.findNode(diagram, e);
						if (node != null) {
							oldNodes.add(node);
						}
					}
				}

				// Collect old inherited UI nodes && nodes of
				// contributor/replacer
				List<?> children = diagram.getChildren();
				for (Iterator<?> iter = children.iterator(); iter.hasNext();) {
					Node node = (Node) iter.next();
					if (BridgeHelper.isInherited(node)) {
						oldNodes.add(node);
					} else {
						MethodElement e = helper.getMethodElement(node, resourceSet);
						if (e instanceof Activity
								&& ((Activity) e).getVariabilityBasedOnElement() != null) {
							// node is of contributor or replacer
							//
							oldNodes.add(node);
						}
					}
				}
				
				Map<MethodElement, Edge> targetToCustomLinkMap = new HashMap<MethodElement, Edge>();
				// Collect the edges thats exists in extend diagram
				for (Iterator<?> iter = diagram.getEdges().iterator(); iter
						.hasNext();) {
					Edge edge = (Edge) iter.next();
					if (contains(oldNodes, edge.getTarget())) {
						if(contains(oldNodes, edge.getSource())) {
							oldEdges.add(edge);
						} else { // must be custom connection
							MethodElement e = helper.getMethodElement((Node) edge.getTarget(), resourceSet);
							targetToCustomLinkMap.put(e, edge);
						}
					}
				}

				// remove the old base nodes and edges that exists in the
				// extend diagram.
				for (Node node : oldNodes) {
					diagram.removeChild(node);
					helper.removeNodeModel(model, node.getElement());
				}
				for (Edge edge : oldEdges) {
					diagram.removeEdge(edge);
					helper.removeEdgeModel(model, edge.getElement());
					edge.setSource(null);
					edge.setTarget(null);
				}

				// replace associated base element with
				// contributing/replacing
				// element
				//
				helper.reassociate(act, baseCopy);

				// Mark inherited
				for (Object obj : baseCopy.getChildren()) {
					View child = (View) obj;
					BridgeHelper.markInherited(child);
					if(child instanceof Node) {
						MethodElement e = helper.getMethodElement((Node) child, resourceSet);
						if(e != null) {
							Edge edge = targetToCustomLinkMap.get(e);
							if(edge != null) {
								// update custom edge with new copy of target from base
								edge.setTarget(child);
								Object edgeModel = edge.getElement();
								if(edgeModel instanceof ActivityEdge && child.getElement() instanceof ActivityNode) {
									ActivityEdge actEdge = (ActivityEdge) edgeModel;
									// disable notification for node adapter of
									// the old target so associated work order
									// will not be removed
									ActivityNode oldTarget = actEdge.getTarget();
									NodeAdapter nodeAdapter = BridgeHelper.getNodeAdapter(oldTarget);
									boolean notify = false;
									if(nodeAdapter != null) {
										notify = nodeAdapter.isNotificationEnabled();
										nodeAdapter.setNotificationEnabled(false);
									}
									try {
										((ActivityEdge) edgeModel).setTarget((ActivityNode) child.getElement());
									} finally {
										if(nodeAdapter != null) {
											nodeAdapter.setNotificationEnabled(notify);
										}
									}
								}
							}
						}
					}
				}
				for (Object obj : baseCopy.getEdges()) {
					BridgeHelper.markInherited((View) obj);
				}
				
				// Add all new nodes and edges from base copy to extend diagram.
				//
				diagram.getPersistedChildren().addAll(
						baseCopy.getChildren());
				diagram.getPersistedEdges().addAll(
						baseCopy.getEdges());
				Collection<?> baseNodeModels = helper.getNodeModels(baseCopy.getElement());
				if(baseNodeModels != null && !baseNodeModels.isEmpty()) {
					helper.addNodeModels(model, baseNodeModels);
				}					
				Collection<?> baseEdgeModels = helper.getEdgeModels(baseCopy
						.getElement());
				if (baseEdgeModels != null && !baseEdgeModels.isEmpty()) {
					helper.addEdgeModels(model, baseEdgeModels);
				}
				
				// TODO: handle the missed links.

				// ActivityDiagramAdapter adapter =
				// (ActivityDiagramAdapter)BridgeHelper.getNodeAdapter(umlActivity);
				// adapter.populateDiagram();
				// if(targetPart instanceof AbstractDiagramEditor){
				// DiagramEditorUtil.resetEditor((AbstractDiagramEditor)targetPart);
				// }
			} finally {
				// reset the notification.
				model.eSetDeliver(notification);
			}

			try {
				if (targetPart instanceof AbstractDiagramEditor) {
					DiagramEditorUtil
							.resetEditor((AbstractDiagramEditor) targetPart);
				}
				// diagram.eResource().save(null);
			} catch (Exception e) {

			}
		} finally {
			diagramSvc.dispose();
		}
		return true;
	}
	
	public void initialize(){
		if(targetPart instanceof AbstractDiagramEditor){
			AbstractDiagramEditor editor = (AbstractDiagramEditor)targetPart;
			DiagramEditPart part = editor.getDiagramEditPart();
			
		}
		
	}
}
