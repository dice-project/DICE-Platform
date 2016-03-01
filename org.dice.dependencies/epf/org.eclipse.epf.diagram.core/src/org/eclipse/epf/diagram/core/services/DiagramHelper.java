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
package org.eclipse.epf.diagram.core.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.services.ILibraryPersister.FailSafeMethodLibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class DiagramHelper {
	
	public static final int[] DIAGRAM_TYPES = { IDiagramManager.ACTIVITY_DIAGRAM,
		IDiagramManager.ACTIVITY_DETAIL_DIAGRAM, IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM };

	
	public static int getDiagramType(Diagram diagram){
		String typeStr = diagram.getType();
		if(typeStr.equals(DiagramManager.AD_kind)){
			return IDiagramManager.ACTIVITY_DIAGRAM;
		}
		if(typeStr.equals(DiagramManager.ADD_kind)){
			return IDiagramManager.ACTIVITY_DETAIL_DIAGRAM;
		}
		if(typeStr.equals(DiagramManager.WPD_kind)){
			return IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM;
		}
		return -1;
	}
	
	public static Node findNode(Diagram diagram, Object object) {
		if (object == null)
			return null;
		for (Iterator iter = diagram.getChildren().iterator(); iter.hasNext();) {
			View view = (View) iter.next();			
			if (view instanceof Node && view.getElement() != null) {
				Object e = null;
				if (object instanceof MethodElement && view.getElement() instanceof EModelElement) {
					EModelElement modelElement = (EModelElement) view.getElement();
					e = BridgeHelper.getMethodElement(modelElement);
					if(e == null) {
						// diagram is not open yet, try to resolve the element from URI stored in the node's annotation
						//
						Resource resource = ((MethodElement)object).eResource();
						if(resource.getResourceSet() != null) {
							e = BridgeHelper.getMethodElementFromAnnotation(modelElement, resource.getResourceSet());
						}
					}
				} else {
					e = view.getElement();
				}
				if (object.equals(e)) {
					return (Node) view;
				}
			}
		}
		return null;
	}
	
	public static Node findNodeInModelDiagram(Diagram diagram, MethodElement e){
		for (Iterator iter = diagram.getChildren().iterator(); iter.hasNext();) {
			View view = (View) iter.next();
			if(view instanceof Node){
				Object ex = null;
				EObject model = view.getElement();
				if(model instanceof org.eclipse.epf.diagram.model.Node){
					ex = ((org.eclipse.epf.diagram.model.Node)model).getLinkedElement();
					if(ex != null && ex == e){
						return (Node)view;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Deletes the diagram and saves immediately.
	 * @param diagram
	 * @throws Exception
	 */
	public static void deleteDiagram(final Diagram diagram, boolean save)
			throws Exception {
		if (diagram == null)
			return;

		boolean fileExist = true;
		File file = null;
		Object linkedObject = null;
		Process process = null;
		if (diagram.getElement() instanceof org.eclipse.uml2.uml.Activity) {
			DiagramAdapter adapter = BridgeHelper.getDiagramAdapter(diagram
					.getElement());
			if (adapter != null) {
				linkedObject = adapter.getWrapper();
				if (linkedObject == null) {
					linkedObject = adapter.getElement();
				}
			}
		} else {
			org.eclipse.epf.diagram.model.Diagram diagramModel = (org.eclipse.epf.diagram.model.Diagram) diagram
					.getElement();
			linkedObject = diagramModel.getLinkedElement();
		}

		if (linkedObject != null) {
			process = TngUtil.getOwningProcess(linkedObject);
			String filePath = DiagramManager.getDiagramFilePath(process);
			file = new File(filePath);
			if (!file.exists())
				fileExist = false;
		}
		final Resource resource = diagram.eResource();
		if (file == null && resource != null) {
			file = new File(FileManager.toFileString(resource
					.getURI()));
			if (!file.exists())
				fileExist = false;
		}

		if (resource != null) {
			TxUtil.runInTransaction(diagram, new Runnable() {

				public void run() {
					resource.getContents().remove(diagram.getElement());
					resource.getContents().remove(diagram);
					UmaUtil.removeAllAdapters(diagram.getElement());
					UmaUtil.removeAllAdapters(diagram);
				}
				
			});
			
			if (!fileExist)
				return;

			if (save) {
//				if (resource.getContents().isEmpty()) {
//					if (file != null) {
//						if (process != null
//								&& DiagramManager.hasDiagramManager(process)) {
//							Object consumer = new Object();
//							DiagramManager mgr = DiagramManager.getInstance(
//									process, consumer);
//							mgr.dispose();
//						}
//						Services.getFileManager()
//								.delete(file.getAbsolutePath());
//					}
//				}
//				else {
					FailSafeMethodLibraryPersister persister = Services
							.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE)
							.getFailSafePersister();
					try {
						persister.save(resource);
						persister.commit();
					} catch (Exception e) {
						CommonPlugin.getDefault().getLogger().logError(e);
						persister.rollback();
						throw new CoreException(new Status(IStatus.ERROR,
								DiagramCorePlugin.PLUGIN_ID, 1, e
										.getLocalizedMessage(), null));
					}
//				}
			}
		}
	}
	
	/**
	 * Convenient method to get editing domain
	 * @return
	 */
	public static InternalTransactionalEditingDomain getEditingDomain(){
		return (InternalTransactionalEditingDomain) DiagramEditingDomainFactory.getInstance()
		.createEditingDomain();
	}
	
	public static InternalTransactionalEditingDomain getEditingDomain(Diagram diagram){
		if(diagram == null) return getEditingDomain();
		Resource resource = diagram.eResource();
		if(resource == null) return getEditingDomain();
		return (InternalTransactionalEditingDomain)DiagramEditingDomainFactory.getInstance().getEditingDomain(resource.getResourceSet());
	}
	
	/**
	 * Gets all available diagrams of the specified activity.
	 * 
	 * @param act
	 * @return
	 */
	public static Collection<Diagram> getDiagrams(Activity act, DiagramManager mgr) {
		try {
			ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
			for (int i = 0; i < DIAGRAM_TYPES.length; i++) {
				List<Diagram> list = mgr.getDiagrams(act, DIAGRAM_TYPES[i]);
				if (!list.isEmpty()) {
					diagrams.addAll(list);
				}
			}
			return diagrams;
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
			return Collections.emptyList();
		}
	}
	
	public static String getDiagramTypeString(Diagram diagram){
		String typeStr = diagram.getType();
		if(typeStr.equals(DiagramManager.AD_kind)){
			return DiagramCoreResources.ActivityDiagram_kind;
		}
		if(typeStr.equals(DiagramManager.ADD_kind)){
			return DiagramCoreResources.ActivityDetail_Diagram_kind;
		}
		if(typeStr.equals(DiagramManager.WPD_kind)){
			return DiagramCoreResources.WorkProductDependency_Diagram_kind;
		}
		return "";  //$NON-NLS-1$
	}
	
	public static Diagram copyDiagram(EditingDomain domain, Diagram diagram) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(diagram.getElement());
		list.add(diagram);
		Command cmd = CopyCommand.create(domain, list);
		cmd.execute();
		for (Iterator<?> iter = cmd.getResult().iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if(element instanceof Diagram) {
				Diagram copy = (Diagram) element;
				return copy;
			}
		}
		return null;
	}
	
	/**
	 * Reassociates activity nodes in the base diagram copy with corresponding
	 * locally contributors or replacers if there is any.
	 * 
	 * @param activity
	 * @param copyOfBaseDiagram
	 */
	public static void reassociate(Activity activity, Diagram copyOfBaseDiagram) {
		for (Iterator iter = activity.getBreakdownElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof Activity) {
				VariabilityElement baseElement = ((Activity) element)
						.getVariabilityBasedOnElement();
				if (baseElement != null) {
					Node node = DiagramHelper.findNode(copyOfBaseDiagram,
							baseElement);
					if (node != null) {
						EObject umlObj = node.getElement();
						BridgeHelper.addEAnnotation((EModelElement) umlObj,
								(MethodElement) element);
					}
				}
			}
		}
	}
}
