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
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.core.services.DiagramService;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.CopyHelper;
import org.eclipse.epf.library.edit.util.ConfigurationSetter;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.StructuredActivityNode;

import com.ibm.icu.util.StringTokenizer;

/**
 * 
 * Updates diagrams of the activity's deep copies.
 * 
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class CopyDiagramForDeepCopyCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private Collection<?> elements;

	private Map<?,?> copyToOriginalMap;

	private Collection<Diagram> copiedElements;

	private static final boolean DEBUG = DiagramCorePlugin.getDefault()
			.isDebugging();

	private Process targetProcess;

	private DiagramManager mgr;
	private InternalTransactionalEditingDomain domain;

	private CopyHelper copyHelper;

	private MethodConfiguration config;

	private Process srcProcess;

	private HashMap<BreakdownElement, String> copyToWrapperPathMap;
	
	public CopyDiagramForDeepCopyCommand(Collection<?> elements, Map<?, ?> copyToOriginalMap,
			Process srcProcess, Process targetProcess, CopyHelper copyHelper,
			MethodConfiguration config) {
		this.copyHelper = copyHelper;
		this.config = config;
		this.srcProcess = srcProcess;
		this.elements = elements;
		this.copyToOriginalMap = copyToOriginalMap;
		this.targetProcess = targetProcess;

		// get diagram manager and domain
		mgr = DiagramManager.getInstance(targetProcess, this);
		domain = mgr.getEditingDomain();
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
	
	private void doExecute() {		
		if (copiedElements == null) {
			copiedElements = new ArrayList<Diagram>();
		} else {
			copiedElements.clear();
		}
		copyToWrapperPathMap = new HashMap<BreakdownElement, String>();
		if (copyHelper != null) {
			for (Map.Entry<String, BreakdownElement> entry : copyHelper
					.getWrapperPathToCopyMap().entrySet()) {
				copyToWrapperPathMap.put(entry.getValue(), entry.getKey());
			}
		}
		DiagramService diagramSvc = new DiagramService();
		try {			
			for (Object e : elements) {
				Object orig = copyToOriginalMap.get(e);
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
								updateReferences(diagramCopy, copyActivity, origActivity);
								
								// remove "inherted" mark, if there is any, on all edges of diagram copy
								//
								for (Object edge : diagramCopy.getEdges()) {
									BridgeHelper.unmarkInHerited((Edge) edge);
								}

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
	
	private void clearReadOnly(org.eclipse.epf.diagram.model.Node node) {
		node.setReadOnly(false);
		if (node instanceof NodeContainer) {
			for (Iterator iter = ((NodeContainer)node).getNodes().iterator(); iter.hasNext();) {
				org.eclipse.epf.diagram.model.Node child = (org.eclipse.epf.diagram.model.Node) iter.next();
				child.setReadOnly(false);
			}
		}
	}	
	
	private BreakdownElementWrapperItemProvider getOriginalWrapper(Activity copy) {
		String wrapperPath = copyToWrapperPathMap.get(copy);
		if(wrapperPath == null) {
			return null;
		}
		if(srcProcess != null) {
			Suppression suppression = Suppression.getSuppression(srcProcess);
			AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
			ConfigurationSetter configSetter = new ConfigurationSetter(adapterFactory);
			try {
				configSetter.set(config);
				StringTokenizer tokens = new StringTokenizer(wrapperPath, "/"); //$NON-NLS-1$
				ArrayList<String> guidList = new ArrayList<String>();
				while(tokens.hasMoreTokens()) {
					guidList.add(tokens.nextToken());
				}
				String[] guidPath = new String[guidList.size()];
				guidList.toArray(guidPath);
				Object o = suppression.getObjectByPath(guidPath, adapterFactory);
				if(o instanceof BreakdownElementWrapperItemProvider) {
					return (BreakdownElementWrapperItemProvider) o;
				}
				else {
					System.out
							.println("CopyDiagramCommand.getOriginalWrapper(): invalid wrapper for copy activity: " + o); //$NON-NLS-1$
				}
			}
			finally {
				configSetter.restore();
			}
		}
		return null;
	}
	
	private Collection<BreakdownElement> getBreakdownElements(Activity origActivity, Activity copyActivity) {
		BreakdownElementWrapperItemProvider wrapper = getOriginalWrapper(copyActivity);
		Object object = wrapper != null ? wrapper : origActivity;
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
		ConfigurationSetter configSetter = new ConfigurationSetter(adapterFactory);
		try {
			configSetter.set(config);
			ITreeItemContentProvider ip = (ITreeItemContentProvider) adapterFactory.adapt(object, ITreeItemContentProvider.class);					
			Collection<?> origChildren = ip.getChildren(origActivity);
			Collection<BreakdownElement> elements = new ArrayList<BreakdownElement>();
			for(Object origChild : origChildren) {
				Object e = TngUtil.unwrap(origChild);
				if(e instanceof BreakdownElement) {
					elements.add((BreakdownElement) e);
				}
			}
			return elements;
		}
		finally {
			configSetter.restore();
		}
	}
	
	private static BreakdownElement getBreakdownElement(Collection<BreakdownElement> elements, Activity base) {
		for (BreakdownElement e : elements) {
			if(e instanceof VariabilityElement) {
				boolean found = false;
				find_base:
				for(VariabilityElement ve = (VariabilityElement) e; ve.getVariabilityBasedOnElement() != null; ve = ve.getVariabilityBasedOnElement()) {
					if(ve.getVariabilityBasedOnElement() == base) {
						found = true;
						break find_base;
					}
				}
				if(found) {
					return (BreakdownElement) e;
				}
			}
		}
		return null;
	}
	
	private class CopyFinder {
		private Activity origActivity;
		private Activity copyActivity;
		private Map<BreakdownElement, BreakdownElement> originalToCopyMap;
		private Collection<BreakdownElement> elements;
		
		private CopyFinder(Activity origActivity, Activity copyActivity) {
			this.origActivity = origActivity;
			this.copyActivity = copyActivity;
		}
		
		public Map<BreakdownElement, BreakdownElement> getOriginalToCopyMap() {
			if(originalToCopyMap == null) {
				originalToCopyMap = new HashMap<BreakdownElement, BreakdownElement>();
				for (Iterator iterator = copyActivity.getBreakdownElements().iterator(); iterator.hasNext();) {
					BreakdownElement e = (BreakdownElement) iterator.next();
					BreakdownElement orig = (BreakdownElement) copyHelper.getOriginal(e);
					originalToCopyMap.put(orig, e);
				}
			}
			return originalToCopyMap;
		}
		
		BreakdownElement findCopy(BreakdownElement original) {
			Object copy = getOriginalToCopyMap().get(original);
			if(copy == null) {
				if(original instanceof Activity) {
					// me must be a base element of a local contributor or replacer
					// 
					if(elements == null) {
						elements = getBreakdownElements(origActivity, copyActivity);
					}
					copy = getBreakdownElement(elements, (Activity) original);
				}
			}
			return (BreakdownElement) copy;
		}
	}

	/**
	 * Update eAnnotations references
	 * 
	 * @param copy
	 * @param origActivity 
	 * @param copyActivity 
	 */
	public void updateReferences(Diagram copy, Activity copyActivity, Activity origActivity) {
		int diagramType = DiagramHelper.getDiagramType(copy);
		CopyFinder copyFinder = new CopyFinder(origActivity, copyActivity);
		List<?> copyChildren = copy.getChildren();
		if(!copyChildren.isEmpty()) {
			List<Object> children = new ArrayList<Object>();
			// collect all children first e.g. for ActivityPartition
			// we don't have partition into partition so just getting one level sub-children
			// are enough for now.
			for (Iterator<?> itor = copyChildren.iterator(); itor.hasNext();) {
				Node node = (Node) itor.next();
				EObject obj = node.getElement();
				children.add(node);
				if (obj instanceof ActivityPartition){
					children.addAll(node.getChildren());
				}
			}
			for (Iterator<?> itor = children.iterator(); itor.hasNext();) {
				Node node = (Node) itor.next();
				BridgeHelper.unmarkInHerited(node);
				EObject obj = node.getElement();

				if (diagramType == IDiagramManager.ACTIVITY_DIAGRAM) {
					if (obj instanceof StructuredActivityNode
							|| obj instanceof ActivityParameterNode) {
						EModelElement modelElement = (EModelElement) obj;
						MethodElement me = BridgeHelper
						.getMethodElementFromAnnotation(modelElement,
								targetProcess.eResource().getResourceSet());
						if(me instanceof BreakdownElement) {						
							BreakdownElement mappedMethodElement = copyFinder.findCopy((BreakdownElement) me);

							if (mappedMethodElement != null) {
								BridgeHelper.addEAnnotation(modelElement, mappedMethodElement);
							}
						}
					}
				} else if (diagramType == IDiagramManager.ACTIVITY_DETAIL_DIAGRAM) {
					if(obj instanceof org.eclipse.epf.diagram.model.Node) {
						clearReadOnly((org.eclipse.epf.diagram.model.Node)obj);
					}
					if (obj instanceof NodeContainer) {
						NodeContainer nodeContainer = (NodeContainer) obj;
						// do mapping for nodecontainer
						MethodElement me = nodeContainer.getLinkedElement();
						Object mappedMethodElement = null;
						if(me instanceof BreakdownElement) {
							mappedMethodElement = copyFinder.findCopy((BreakdownElement) me);
							if (mappedMethodElement != null) {
								nodeContainer
								.setLinkedElement((MethodElement) mappedMethodElement);
							}
						}

						// do mapping for its children
						List<?> nodes = nodeContainer.getNodes();

						for (int i = 0; i < nodes.size(); i++) {
							NamedNode namedNode = (NamedNode) nodes.get(i);
							me = namedNode.getLinkedElement();
							if (me instanceof BreakdownElement) {
								mappedMethodElement = copyFinder
								.findCopy((BreakdownElement) me);
								if (mappedMethodElement != null) {
									namedNode
									.setLinkedElement((MethodElement) mappedMethodElement);
								}
							}
						}
					}
				} else if (diagramType == IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM) {
					if(obj instanceof org.eclipse.epf.diagram.model.Node) {
						clearReadOnly((org.eclipse.epf.diagram.model.Node)obj);
					}
					if (obj instanceof NamedNode) {
						NamedNode namedNode = (NamedNode) obj;
						MethodElement me = namedNode.getLinkedElement();
						if (me instanceof BreakdownElement) {
							Object mappedMethodElement = copyFinder.findCopy((BreakdownElement) me);
							if (mappedMethodElement != null) {
								namedNode
								.setLinkedElement((MethodElement) mappedMethodElement);
							}
						}
					}
				}
			}
		} else {
			// there is situation where diagram notation does not have any children but its model does
			// URIs to UMA element in the model elements still need to be updated
			//
			switch(diagramType) {
			case IDiagramManager.ACTIVITY_DIAGRAM:
				EObject diagramModel = copy.getElement();
				if(diagramModel instanceof org.eclipse.uml2.uml.Activity) {
					org.eclipse.uml2.uml.Activity umlActivity = (org.eclipse.uml2.uml.Activity) diagramModel;
					for (ActivityNode node : umlActivity.getNodes()) {
						if(node instanceof StructuredActivityNode || node instanceof ActivityParameterNode) {
							EModelElement modelElement = (EModelElement) node;
							MethodElement me = BridgeHelper
							.getMethodElementFromAnnotation(modelElement,
									targetProcess.eResource().getResourceSet());
							if(me instanceof BreakdownElement) {						
								BreakdownElement mappedMethodElement = copyFinder.findCopy((BreakdownElement) me);

								if (mappedMethodElement != null) {
									BridgeHelper.addEAnnotation(modelElement, mappedMethodElement);
								}
							}
						}
					}
					// remove any association to work order from edges
					//
					for (ActivityEdge edge : umlActivity.getEdges()) {
						EAnnotation annotation = edge.getEAnnotation(BridgeHelper.UMA_ELEMENT);
						if(annotation != null) {
							EcoreUtil.remove(annotation);
						}
					}
				}
			}
		}
		switch(diagramType) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			EObject diagramModel = copy.getElement();
			if(diagramModel instanceof org.eclipse.uml2.uml.Activity) {
				org.eclipse.uml2.uml.Activity umlActivity = (org.eclipse.uml2.uml.Activity) diagramModel;
				// remove any association to work order from edges
				//
				for (ActivityEdge edge : umlActivity.getEdges()) {
					EAnnotation annotation = edge.getEAnnotation(BridgeHelper.UMA_ELEMENT);
					if(annotation != null) {
						EcoreUtil.remove(annotation);
					}
				}
			}
		}

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
							for (Iterator iter = copiedElements.iterator(); iter.hasNext();) {
								Diagram diagram = (Diagram) iter.next();

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
	public Collection getModifiedResources() {
		if (copiedElements == null || copiedElements.isEmpty()) {
			// command is not executed yet
			return Collections.EMPTY_LIST;
		}
		HashSet<Object> modifiedResources = new HashSet<Object>();

		for (Iterator iter = copiedElements.iterator(); iter.hasNext();) {
			Diagram diagram = (Diagram) iter.next();
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
