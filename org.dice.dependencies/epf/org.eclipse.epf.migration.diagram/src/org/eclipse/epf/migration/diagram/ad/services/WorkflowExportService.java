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
package org.eclipse.epf.migration.diagram.ad.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.diagram.wpdd.part.WPDDiagramEditorPlugin;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.util.DiagramOptions;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.migration.diagram.DiagramMigrationPlugin;
import org.eclipse.epf.migration.diagram.MigrationExportException;
import org.eclipse.epf.migration.diagram.ad.map.IMapConstants;
import org.eclipse.epf.migration.diagram.ad.map.MapFactory;
import org.eclipse.epf.migration.diagram.ad.map.MapNode;
import org.eclipse.epf.migration.diagram.ad.map.MapUtil;
import org.eclipse.epf.migration.diagram.addwpd.map.ConnectionFactory;
import org.eclipse.epf.migration.diagram.util.MigrationUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @author Weiping Lu
 * @since 1.2
 */

public class WorkflowExportService {

	public static final String DEFAULT_XMI_EXTENSION = ".xmi"; //$NON-NLS-1$

	protected boolean debug;
	
	public static WorkflowExportService singleton = null;

	public static WorkflowExportService getInstance() {
		if (singleton == null) {
			singleton = new WorkflowExportService();
		}
		return singleton;
	}

	/**
	 * Export activity diagram data
	 * @param proc
	 * @param activity
	 * @param fileName
	 * @param targetDir
	 * @return
	 * @throws MigrationExportException
	 */
	public boolean export(Process proc, Activity activity, String fileName,
			File targetDir) throws MigrationExportException {

		debug = DiagramMigrationPlugin.getDefault().isDebugging();
		
		// construct the export target xml file path
		Path exportPath = new Path(targetDir.getAbsolutePath());
		boolean endWithXmiExt = fileName.toLowerCase().endsWith(
				DEFAULT_XMI_EXTENSION);

		String exportXMIPath = (exportPath.append(endWithXmiExt ? fileName
				: (fileName + DEFAULT_XMI_EXTENSION))).toOSString();


		URI uri = URI.createFileURI(exportXMIPath);
		
/*		// Create resource set
		ResourceSet resourceSet = new ResourceSetImpl();

		//XMLResourceImpl xmires = new Workflow2XMIResourceImpl(uri);
		//Resource xmires =  new GMFResourceFactory().createResource(uri);
		Resource xmires =  DiagramManager.getInstance(proc)
							.getEditingDomain().getResourceSet().createResource(uri);

		resourceSet.getResources().add(xmires);*/
		DiagramManager mgr = DiagramManager.getInstance(proc, this);
		
		try {
			Transaction tx =  mgr.getEditingDomain().startTransaction(false,
					Collections.EMPTY_MAP);
			Resource xmires = mgr.getResource();
			tx.commit();
			
			// Create temp map
			IMapConstants.graphUMLNodeMap.clear();
			
			if (proc == activity) {
				Map activities = MigrationUtil.getActivities(proc, true);
				for (Iterator it = activities.values().iterator(); it.hasNext();) {
					Activity act = (Activity) it.next();
					addAD(xmires, proc, act, mgr);
					addADD(xmires, proc, act, mgr);
					addWPD(xmires, proc, act, mgr);
				}
			} else {
				addAD(xmires, proc, activity, mgr);
				addADD(xmires, proc, activity, mgr);
				addWPD(xmires, proc, activity, mgr);
			}

/*			tx.commit();*/
			
			List contents = xmires.getContents();
			if (contents != null && ! contents.isEmpty()) {
				xmires.save(null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new MigrationExportException(e);
		} finally {
			if(mgr != null) {
				mgr.removeConsumer(this);
			}
		}

		return true;
	}
	
	private void addDataToResource_WPD(Resource xmiResource, Process proc,
			Activity umaActivity) {	
		
		org.eclipse.epf.uma.Diagram d  = GraphicalDataManager.getInstance().getUMADiagram(umaActivity, IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM, false);
		if (d != null) {
			if (debug) {
				System.out.println("WPDD->"+proc.getName()+"::"+umaActivity.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			WorkProductDependencyDiagram wpdDiagram = ModelFactory.eINSTANCE
					.createWorkProductDependencyDiagram();
			addDataToResource_ADDorWPD(proc, umaActivity, xmiResource,
					wpdDiagram);
		}
	}
	

	private void addDataToResource_ADD(Resource xmiResource,
			Process proc, Activity umaActivity) {
		org.eclipse.epf.uma.Diagram d = GraphicalDataManager.getInstance()
				.getUMADiagram(umaActivity,
						IDiagramManager.ACTIVITY_DETAIL_DIAGRAM, false);
		if (d != null) {
			if (debug) {
				System.out.println("ADD->"+proc.getName()+":"+umaActivity.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			ActivityDetailDiagram addDiagram = ModelFactory.eINSTANCE
					.createActivityDetailDiagram();
			addDataToResource_ADDorWPD(proc, umaActivity, xmiResource,
					addDiagram);
		}
	}

	private void addDataToResource_ADDorWPD(Process proc, Activity umaActivity, 
			Resource xmiResource, org.eclipse.epf.diagram.model.Diagram addOrWpdDiagram) {
		Suppression sup = new Suppression(proc);
		addOrWpdDiagram.setSuppression(sup);
		String kind = null;
		PreferencesHint prefHint = null;
		if (addOrWpdDiagram instanceof ActivityDetailDiagram) {
			addOrWpdDiagram.setFilter(ProcessAuthoringConfigurator.INSTANCE);
			kind = DiagramManager.ADD_kind;
			prefHint = ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		} else if (addOrWpdDiagram instanceof WorkProductDependencyDiagram) {
			kind = DiagramManager.WPD_kind;
			prefHint = WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		} else {
			throw new UnsupportedOperationException();
		}
		addOrWpdDiagram.setObject(umaActivity);
		addOrWpdDiagram.setLinkedElement(umaActivity);
		
		//	set suppression/published info for ADD/ WPDD
		org.eclipse.epf.uma.Diagram umaDiagram = addOrWpdDiagram.getUMADiagram();
		if (umaDiagram != null) {
			if (umaDiagram.getSuppressed().booleanValue()) {	
				if (addOrWpdDiagram instanceof ActivityDetailDiagram){
					DiagramOptions.setPublishADD(umaActivity, false);
				}
				else if (addOrWpdDiagram instanceof WorkProductDependencyDiagram) {
					DiagramOptions.setPublishWPDD(umaActivity, false);
				}
			}
		}
		// get autoLayout flag if diagram is ADD
		if (addOrWpdDiagram instanceof ActivityDetailDiagram) {
			boolean value = GraphicalDataHelper
					.isAutoLayout((ActivityDetailDiagram) addOrWpdDiagram);
			((ActivityDetailDiagram) addOrWpdDiagram).setAutoLayout(value);
		}
		
		// Create diagram
		Diagram diagram = ViewService.createDiagram(addOrWpdDiagram, kind,
				prefHint);
		diagram.setElement(addOrWpdDiagram);
		diagram.setName(umaActivity.getName());
		
		xmiResource.getContents().add(addOrWpdDiagram);
		xmiResource.getContents().add(diagram);
		
		List nodes = new ArrayList();
		HashMap map = new HashMap();
		
		
		List diagramNodes = addOrWpdDiagram.getNodes();
		for (Iterator iterator = diagramNodes.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof Node ) {
				Node node = (Node) obj;
				if ((obj instanceof TypedNode) &&
						(((TypedNode)obj).getType() == TypedNode.FREE_TEXT))	{	
					// do special handing for free text node					
					org.eclipse.gmf.runtime.notation.Node text = MapUtil.createText(diagram, (Node) obj);
					diagram.insertChild(text);
				}
				else {
					// Create Notation nnode
					org.eclipse.gmf.runtime.notation.Node notationNode = MapUtil.createNotationNode_ADDorWPD(diagram, node);
					if (notationNode == null) {
						continue;
					}
					diagram.insertChild(notationNode);

					Bounds bounds = NotationFactory.eINSTANCE.createBounds();
					bounds.setHeight(node.getHeight());
					bounds.setWidth(node.getWidth());
					bounds.setX(node.getLocation().x);
					bounds.setY(node.getLocation().y);
					notationNode.setLayoutConstraint(bounds);

					notationNode.setElement(node);
				
					nodes.add(node);
					map.put(node, notationNode);
				}
			}
		}
		
		
		ConnectionFactory factory = new ConnectionFactory(diagram, map, kind);
		// Create Outgoing connections
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof Node) {
				factory.outgoingConnections((Node) obj);
			}
		}

		// Create Outgoing connections
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof Node) {
				factory.incomingConnections((Node) obj);
			}
		}
		
	}
	

	/**
	 * 
	 * @param resource
	 * @param list
	 * @param process
	 */
	private void addDataToResource_AD( Resource xmiResource, Process proc,
			Activity umaActivity) {

		org.eclipse.epf.uma.Diagram d = GraphicalDataManager.getInstance()
				.getUMADiagram(umaActivity, IDiagramManager.ACTIVITY_DIAGRAM,
						false);
		if (d == null) {
			return;
		}
		if (debug) {
			System.out.println("AD->"+proc.getName()+":"+umaActivity.getName()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		// Create top level UML activity
		EObject activity = UMLFactory.eINSTANCE.create(UMLPackage.eINSTANCE
				.getActivity());
		if (activity instanceof org.eclipse.uml2.uml.Activity) {
			((org.eclipse.uml2.uml.Activity)activity).setName(umaActivity.getName());
		}
		if (activity instanceof EModelElement) {
			//BridgeHelper.addEAnnotation((EModelElement) activity, ((MethodElement) umaActivity));
//			UmaUmlUtil.createEAnnotationForUml((NamedElement) activity,
//					umaActivity.getGuid());
			
			BridgeHelper.associate((EModelElement) activity, umaActivity);
		}
		
		// Create diagram
		Diagram diagram = ViewService.createDiagram(activity, DiagramManager.AD_kind,
				ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		diagram.setElement(activity);
		if (activity instanceof org.eclipse.uml2.uml.Activity) {
			diagram.setName(umaActivity.getName());
		}
	
		Suppression suppression = new Suppression(proc);
		
		ActivityDiagram ad = ModelFactory.eINSTANCE.createActivityDiagram();
		ad.setSuppression(suppression);
		ad.setObject(umaActivity);
		if (ad.isNew()) {
			return;
		}
		
		// set suppression/published info for AD 
		org.eclipse.epf.uma.Diagram umaDiagram = ad.getUMADiagram();
		if (umaDiagram != null) {
			if (umaDiagram.getSuppressed().booleanValue()) {	
				DiagramOptions.setPublishAD(umaActivity, false);
			}
		}
		
//		return (List) ad.getNodes();
		
//		List diagramNodes = getActivityDiagramData(suppression, umaActivity);
		List diagramNodes = ad.getNodes();
		if (diagramNodes == null) {
			return;
		}
		


		xmiResource.getContents().add(activity);
		xmiResource.getContents().add(diagram);
			
		List mapNodes = new ArrayList();
		HashMap mapNodesMap = new HashMap();
		
		// Create UML and notation nodes
		for (Iterator iterator = diagramNodes.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof Node ) {
				if ((obj instanceof TypedNode) &&
						(((TypedNode)obj).getType() == TypedNode.FREE_TEXT))	{	
					// do special handing for free text node
					org.eclipse.gmf.runtime.notation.Node text = MapUtil.createText(diagram, (Node) obj);
				}
				else {
					MapNode mapNode = MapFactory.getInstance().create((Node) obj);
					mapNode.setActivity((org.eclipse.uml2.uml.Activity)activity);
					mapNode.setDiagram(diagram);
					
					mapNode.updateResource();
					mapNodes.add(mapNode);
					mapNodesMap.put(mapNode, (Node) obj);
				}
			}
		}
		
		// Create Outgoing connections
		for (Iterator iterator = mapNodes.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof MapNode) {
				((MapNode) obj).outgoingConnections((Node) mapNodesMap.get((MapNode) obj));
			}
		}
		
		// Create incoming connections
		for (Iterator iterator = mapNodes.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof MapNode) {
				((MapNode) obj).incomingConnections((Node) mapNodesMap.get((MapNode) obj));
			}
		}
	}
	
	
	/**
	 * Return activity diagram data
	 * @param sup
	 * @param activity
	 * @return
	 */
	private List getActivityDiagramData(Suppression sup, Activity activity) {
		ActivityDiagram ad = ModelFactory.eINSTANCE.createActivityDiagram();
		ad.setSuppression(sup);
		ad.setObject(activity);
		if (ad.isNew()) {
			return null;
		}
		return (List) ad.getNodes();

	}
	
	private void addAD(final Resource resource, final Process proc,
			final Activity umaActivity, final DiagramManager mgr) {
		addToResource(resource, proc, umaActivity, mgr, 0);
	}
	
	private void addADD(final Resource resource, final Process proc,
			final Activity umaActivity, final DiagramManager mgr) {
		addToResource(resource, proc, umaActivity, mgr, 1);
	}
	
	private void addWPD(final Resource resource, final Process proc,
			final Activity umaActivity, final DiagramManager mgr) {
		addToResource(resource, proc, umaActivity, mgr, 2);
	}
	
	//type: 0=AD, 1=ADD, 2=WPD
	private void addToResource(final Resource resource,
			final Process proc, final Activity umaActivity,
			final DiagramManager mgr, final int type) {
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				try {
					Transaction tx = mgr.getEditingDomain().startTransaction(
							false, Collections.EMPTY_MAP);

					if (type == 0) {
						addDataToResource_AD(resource, proc, umaActivity);
					} else if (type == 1) {
						addDataToResource_ADD(resource, proc, umaActivity);
					} else if (type == 2) {
						addDataToResource_WPD(resource, proc, umaActivity);
					} else {
						assert (false);
					}
					
					tx.commit();
				} catch (Exception e) {

				}
			}
		});
	}
}
