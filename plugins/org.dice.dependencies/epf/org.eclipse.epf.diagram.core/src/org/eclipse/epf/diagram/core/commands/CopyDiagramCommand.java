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

package org.eclipse.epf.diagram.core.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.core.services.DiagramService;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.StructuredActivityNode;

/**
 * 
 * It does copy the diagram for the activity
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class CopyDiagramCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private Collection<?> elements;

	private Map<EObject, EObject> copyToOriginalMap;

	private Collection<Diagram> copiedElements;

	private static final boolean DEBUG = DiagramCorePlugin.getDefault()
			.isDebugging();

	private Process targetProcess;

	private DiagramManager mgr;
	private InternalTransactionalEditingDomain domain;

	private Map<EObject, EObject> originalToCopyMap;
	
	private CopyDiagramCommand(Collection<?> elements, Process targetProcess) {
		this.elements = elements;
		this.targetProcess = targetProcess;

		// get diagram manager and domain
		mgr = DiagramManager.getInstance(targetProcess, this);
		domain = mgr.getEditingDomain();				
	}
	
	public CopyDiagramCommand(Collection<?> elements, Helper copyHelper,
			Process targetProcess) {
		this(elements, targetProcess);
		this.originalToCopyMap = copyHelper;
	}
	
	public CopyDiagramCommand(Collection<?> elements, Map<EObject, EObject> copyToOriginalMap,
			Process targetProcess) {
		this(elements, targetProcess);
		this.copyToOriginalMap = copyToOriginalMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		try {
			TxUtil.runInTransaction(domain, new Runnable() {

				public void run() {
					doExecute();
				}
				
			});
		} catch (ExecutionException e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}
	
	protected Map<?, ?> getCopyToOriginalMap() {
		if(copyToOriginalMap == null && originalToCopyMap != null) {
			copyToOriginalMap = new HashMap<EObject, EObject>();
			for (Map.Entry<EObject, EObject> entry : originalToCopyMap.entrySet()) {
				copyToOriginalMap.put(entry.getValue(), entry.getKey());
			}
		}
		return copyToOriginalMap;
	}
	
	private void doExecute() {		
		if (copiedElements == null) {
			copiedElements = new ArrayList<Diagram>();
		} else {
			copiedElements.clear();
		}

		DiagramService diagramSvc = new DiagramService();
		try {
			for (Object e : elements) {
				Object orig = getCopyToOriginalMap().get(e);
				if (orig != null && orig instanceof Activity && e != null
						&& e instanceof Activity) {
					Activity copyActivity = (Activity) e;
					Activity origActivity = (Activity) orig;

					Collection<Diagram> diagrams = diagramSvc.getDiagrams(origActivity);
					if (diagrams != null && !diagrams.isEmpty()) {
						for (Diagram diagram : diagrams) {
							if (diagram != null) {
								// copy the diagram
								Diagram diagramCopy = (Diagram) DiagramHelper
										.copyDiagram(domain, diagram);

								// update children references
								updateReferences(diagramCopy);

								// associate with the activity
								int diagramType = DiagramHelper
										.getDiagramType(diagramCopy);
								mgr.associate(diagramCopy, diagramType,
										(Activity) copyActivity);

								copiedElements.add(diagramCopy);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			DiagramCorePlugin.getDefault().getLogger().logError(ex);
			if (DEBUG) {
				ex.printStackTrace();
			}
		} finally {
			diagramSvc.dispose();
		}
	}
	
	/**
	 * Update eAnnotations references
	 * 
	 * @param copy
	 */
	public void updateReferences(Diagram copy) {
		int diagramType = DiagramHelper.getDiagramType(copy);
		List<Object> children = new ArrayList<Object>();
		// collect all children first e.g. for ActivityPartition
		// we don't have partition into partition so just getting one level sub-children
		// are enough for now.
		for (Iterator<?> itor = copy.getChildren().iterator(); itor.hasNext();) {
			Node node = (Node) itor.next();
			EObject obj = node.getElement();
			children.add(node);
			if (obj instanceof ActivityPartition){
				children.addAll(node.getChildren());
			}
		}
		for (Iterator<?> itor = children.iterator(); itor.hasNext();) {

			Node node = (Node) itor.next();
			EObject obj = node.getElement();

			if (diagramType == IDiagramManager.ACTIVITY_DIAGRAM) {
				if (obj instanceof StructuredActivityNode
						|| obj instanceof ActivityParameterNode) {
					EModelElement modelElement = (EModelElement) obj;
					MethodElement me = BridgeHelper
							.getMethodElementFromAnnotation(modelElement,
									targetProcess.eResource().getResourceSet());

					Object mappedMethodElement = getCopiedElement(me);
					if (mappedMethodElement != null) {
						BridgeHelper.addEAnnotation(modelElement,
								(MethodElement) mappedMethodElement);
					}

				}
			} else if (diagramType == IDiagramManager.ACTIVITY_DETAIL_DIAGRAM) {
				if (obj instanceof NodeContainer) {
					NodeContainer nodeContainer = (NodeContainer) obj;
					// do mapping for nodecontainer
					MethodElement me = nodeContainer.getLinkedElement();
					Object mappedMethodElement = getCopiedElement(me);
					if (mappedMethodElement != null) {
						nodeContainer
								.setLinkedElement((MethodElement) mappedMethodElement);
					}

					// do mapping for its children
					List<?> nodes = nodeContainer.getNodes();

					for (int i = 0; i < nodes.size(); i++) {
						NamedNode namedNode = (NamedNode) nodes.get(i);
						me = namedNode.getLinkedElement();

						mappedMethodElement = getCopiedElement(me);
						if (mappedMethodElement != null) {
							namedNode
									.setLinkedElement((MethodElement) mappedMethodElement);
						}
					}
				}
			} else if (diagramType == IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM) {
				if (obj instanceof NamedNode) {
					NamedNode namedNode = (NamedNode) obj;
					MethodElement me = namedNode.getLinkedElement();

					Object mappedMethodElement = getCopiedElement(me);
					if (mappedMethodElement != null) {
						namedNode
								.setLinkedElement((MethodElement) mappedMethodElement);
					}
				}
			}
		}
	}

	/**
	 * Get copied element from the map
	 * 
	 * @param element
	 * @return
	 */
	private Object getCopiedElement(Object element) {
		if(originalToCopyMap == null && copyToOriginalMap != null) {
			originalToCopyMap = new HashMap<EObject, EObject>();
			for (Map.Entry<EObject, EObject> entry : copyToOriginalMap.entrySet()) {
				originalToCopyMap.put(entry.getValue(), entry.getKey());
			}
		}
		return originalToCopyMap != null ? originalToCopyMap.get(element) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		if (!(copiedElements == null || copiedElements.isEmpty())) {
			try {
				TxUtil.runInTransaction(domain, new Runnable() {

					public void run() {
						try {
							for (Diagram diagram : copiedElements) {
								mgr.getResource().getContents()
										.remove(diagram.getElement());
								mgr.getResource().getContents().remove(diagram);
							}
						} catch (CoreException e) {
							throw new WrappedException(e);
						}
					}
					
				});
				copiedElements.clear();
			} catch (Exception ex) {
				DiagramCorePlugin.getDefault().getLogger().logError(ex);
				if (DEBUG) {
					ex.printStackTrace();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection<Resource> getModifiedResources() {
		if (copiedElements == null || copiedElements.isEmpty()) {
			// command is not executed yet
			return Collections.emptyList();
		}
		HashSet<Resource> modifiedResources = new HashSet<Resource>();

		for (Diagram diagram : copiedElements) {
			if(diagram != null && diagram.eResource() != null)
				modifiedResources.add(diagram.eResource());
		}

		return modifiedResources;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose() {
		if (mgr != null)
			mgr.removeConsumer(this);

		super.dispose();
	}
}
