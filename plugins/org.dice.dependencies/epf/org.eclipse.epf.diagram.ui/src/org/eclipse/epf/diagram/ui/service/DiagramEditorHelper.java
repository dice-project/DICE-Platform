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
package org.eclipse.epf.diagram.ui.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.add.part.ADDiagramEditor;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.diagram.wpdd.part.WPDDiagramEditorPlugin;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.DiagramOptions;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Helper class for diagram editor.
 * 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class DiagramEditorHelper {
	private Map<Process, DiagramManager> processToDiagramManagerMap = new HashMap<Process, DiagramManager>();
	
	public DiagramEditorHelper() {
	}
	
	public DiagramManager getDiagramManager(Process proc) {
		DiagramManager mgr = processToDiagramManagerMap.get(proc);
		if(mgr != null && mgr.isDisposed()) {
			processToDiagramManagerMap.remove(proc);
			mgr = null;
		}
		if(mgr == null) {
			synchronized (processToDiagramManagerMap) {
				mgr = processToDiagramManagerMap.get(proc);
				if(mgr == null) {
					mgr = DiagramManager.getInstance(proc, this);
					processToDiagramManagerMap.put(proc, mgr);
				}
			}
		}
		return mgr;
	}	
	
	public void openDiagram(IEditorPart part, int diagramType, Object selectedObject, Suppression suppression) {
		try {
			DiagramEditorInput input = new org.eclipse.epf.diagram.core.part.DiagramEditorInput(
					selectedObject, suppression, diagramType);
			DiagramEditorUtil.openDiagramEditor(part.getSite().getPage(), input, getDiagramPreferencesHint(diagramType), new NullProgressMonitor());					
		} catch (RuntimeException e) {
			DiagramUIPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public org.eclipse.gmf.runtime.notation.Diagram getDiagram(Activity activity, int type) {
		org.eclipse.gmf.runtime.notation.Diagram diagram = null;
		DiagramManager dMgr = getDiagramManager(TngUtil.getOwningProcess(activity));
		if (dMgr != null) {
			try {
				List<Diagram> list = dMgr.getDiagrams(activity, type);
				if (!list.isEmpty()) {
					diagram = (org.eclipse.gmf.runtime.notation.Diagram) list.get(0);
				}
			} catch (Exception e) {
				DiagramUIPlugin.getDefault().getLogger().logError(
						"Core error retrieving a diagram in process editor:", e); //$NON-NLS-1$
			} 
		}
		return diagram;
	}
	
	/**
	 * 
	 * @param act
	 * @return
	 */
	public Collection getDiagrams(Activity act) {

		DiagramManager mgr = getDiagramManager(TngUtil.getOwningProcess(act));
		try{
			ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
			for (int i = 0; i < DiagramHelper.DIAGRAM_TYPES.length; i++) {
				List<Diagram> list = mgr.getDiagrams(act, DiagramHelper.DIAGRAM_TYPES[i]);
				if (!list.isEmpty()) {
					diagrams.addAll(list);
				}
			}
			return diagrams;
		}catch(Exception e){
			return Collections.EMPTY_LIST;
		}
	}
	
	public void dispose(){
		for (DiagramManager mgr : processToDiagramManagerMap.values()) {
			try {
				mgr.removeConsumer(this);
			}
			catch(Exception e) {
				//
			}
		}
	}
	
	public static int getDiagramType(Diagram diagram){
		return DiagramHelper.getDiagramType(diagram);
	}
	
	public static String getDiagramTypeString(Diagram diagram){
		return DiagramHelper.getDiagramTypeString(diagram);
	}
	
	public static PreferencesHint getDiagramPreferencesHint(int diagramType) {
		switch(diagramType) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		}
		return null;
	}
	
	/**
	 * Close diagram editors
	 * 
	 * @param closeElement
	 * @param closeEditorRefs
	 */
	public static void closeDiagramEditors(Object closeElement, 
			java.util.List<IEditorReference> closedEditorRefs) {
		IWorkbenchPage workbenchPage = DiagramUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		if(workbenchPage == null) {
			return;
		}
		IEditorReference[] editorReferences = workbenchPage.getEditorReferences();
		if(closedEditorRefs == null) {
			closedEditorRefs = new ArrayList<IEditorReference>();
		}
		ArrayList<IEditorReference> closeEditorRefs = new ArrayList<IEditorReference>();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				Object element = null;
				if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input).getDiagramEditorInput();
					if (diagramInput.getWrapper() != null) {
						element = diagramInput.getWrapper();
						if(element instanceof BreakdownElementWrapperItemProvider){
							// close the parent editor too, if opened.
							Object parent = ((BreakdownElementWrapperItemProvider)element).getParent(element);
							if(parent instanceof BreakdownElementWrapperItemProvider){
								if(element.equals(closeElement))
									closeDiagramEditors(parent, closedEditorRefs);
							}
						}
					} else {
						element = diagramInput.getMethodElement();
					}
				} 
				if (element != null) {
					if (element.equals(closeElement)) {
						closeEditorRefs.add(reference);
					} else {
						// check whether element is child of closeElement
						// directly/indirectly
						if (closeElement instanceof Activity) {
							Collection collection = new ArrayList();
							ProcessUtil.getChildElements(
									(Activity) closeElement, Activity.class,
									collection);
							if (collection.contains(element)) {
								closeEditorRefs.add(reference);
							}
						}
					}
				}
			}
		}
		int size = closeEditorRefs.size();
		IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			references[i] = (IEditorReference) closeEditorRefs.get(i);
		}
		workbenchPage.closeEditors(references, false);
		references = null;
	}
	
	/**
	 * Diagram editors of object itself will be closed. And Parent diagram
	 * editors will be refreshed.
	 * 
	 */
	public static void refreshParentDiagramEditors(Object refreshElement,
			java.util.List<IEditorReference> openEditorRefs, boolean parentRefresh) {
		openEditorRefs = new ArrayList<IEditorReference>();
		// Now refresh object's parent's diagrams.
		IWorkbenchPage workbenchPage = DiagramUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				Object element = null;
				if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input)
							.getDiagramEditorInput();
					if (diagramInput.getWrapper() != null) {
						element = diagramInput.getWrapper();
						if(parentRefresh){
							if (element instanceof BreakdownElementWrapperItemProvider) {
							Object parent = ((BreakdownElementWrapperItemProvider) element)
									.getParent(null);
							if (parent instanceof BreakdownElementWrapperItemProvider) {
								if (element.equals(refreshElement))
									refreshParentDiagramEditors(parent,
											openEditorRefs, parentRefresh);
							}
							}
						}
					} else {
						element = diagramInput.getMethodElement();
					}
				}
				if (element != null) {
					if (element instanceof Activity) {
						if(!parentRefresh){
							if(element == refreshElement){
								openEditorRefs.add(reference);
							}
						}
						if (((Activity) element).getBreakdownElements()
								.contains(refreshElement))
							openEditorRefs.add(reference);
						else if (refreshElement instanceof BreakdownElementWrapperItemProvider) {
							Object localRefreshElement = refreshElement;
							while ((localRefreshElement instanceof BreakdownElementWrapperItemProvider)
									&& ((BreakdownElementWrapperItemProvider) localRefreshElement)
											.getOwner() != null) {
								if (((BreakdownElementWrapperItemProvider) localRefreshElement)
										.getOwner().equals(element)) {
									openEditorRefs.add(reference);
								}
								localRefreshElement = ((BreakdownElementWrapperItemProvider) localRefreshElement)
										.getOwner();
							}
						}
						if(((Activity) element).getVariabilityBasedOnElement() != null){
							Activity base = (Activity)((Activity) element).getVariabilityBasedOnElement();
							if (((Activity) base).getBreakdownElements()
									.contains(refreshElement))
								openEditorRefs.add(reference);
						}
					} else if (element instanceof BreakdownElementWrapperItemProvider) {
						Collection c = ((BreakdownElementWrapperItemProvider) element)
								.getChildren(element);
						if (c != null && c.contains(refreshElement)) {
							openEditorRefs.add(reference);
						}
					}

				}
			}
		}
		int size = openEditorRefs.size();
		// IEditorReference[] references = new IEditorReference[size];
		for (int i = 0; i < size; i++) {
			IEditorReference reference = (IEditorReference) openEditorRefs
					.get(i);
			IEditorPart editor = reference.getEditor(true);
			if (editor instanceof AbstractDiagramEditor) {
				((AbstractDiagramEditor) editor).refreshDiagram();
				if(editor instanceof ADDiagramEditor){
					((ADDiagramEditor)editor).runResetLayoutAction();
				}
			}
		}
	}
	
	/**
	 * Gets list of command listeners that will handle the diagram related changes of the commands of interesses
	 * 
	 * @return
	 * @see ICommandListener
	 */
//	public static List<ICommandListener> getCommandListeners() {
//		if (commandListeners == null) {
//			commandListeners = new ArrayList<ICommandListener>();
//		}
//
//		commandListeners.add(new ICommandListener() {
//			
//			public void notifyExecuted(Command command) {
//				// get contributor/replacer of the activity
//				//
//				if(true) return;
//				Activity act = (Activity) command.getResult().iterator().next();
//				Activity base = (Activity) act.getVariabilityBasedOnElement();
//				DiagramManager dMgr = null;
//				DiagramManager baseMgr = null;
//				try {
//					if (base != null) {
//						Activity parentAct = act.getSuperActivities();
//						dMgr = DiagramManager.getInstance(TngUtil
//								.getOwningProcess(parentAct));
//						Diagram diagram = null;
//						List<Diagram> diagrams = dMgr.getDiagrams(parentAct,
//								IDiagramManager.ACTIVITY_DIAGRAM);
//						if (!diagrams.isEmpty()) {
//							diagram = diagrams.get(0);
//						}
//						if (diagram != null) {
//							// find existing node for base and link it to the
//							// contributore/replacer of the activity
//							Node node = findNode(diagram, base);
//							if (node != null) {
//								NodeAdapter adapter = BridgeHelper
//										.getNodeAdapter(node.getElement());
//								if (adapter != null
//										&& adapter.getElement() != act) {
//									BridgeHelper.associate((EModelElement) node
//											.getElement(), act);
//									BridgeHelper
//											.unmarkInHerited((EModelElement) node
//													.getElement());
//								}
//							}
//						}
//						if (act.getVariabilityType() == VariabilityType.LOCAL_CONTRIBUTION_LITERAL) {
//							// copy diagrams and their publishing options
//							baseMgr = DiagramManager
//									.getInstance(TngUtil.getOwningProcess(base));
//							for (int i = 0; i < DiagramHelper.DIAGRAM_TYPES.length; i++) {
//								int diagramType = DiagramHelper.DIAGRAM_TYPES[i];
//								List baseDiagrams = baseMgr.getDiagrams(base,
//										diagramType);
//								if (!baseDiagrams.isEmpty() && baseDiagrams.get(0) != null) {
//									Diagram copy = baseMgr.createDiagram(act,
//											diagramType, null);
//									if (isPublishDiagram(base, diagramType)) {
//										setDiagramOptions(act, diagramType);
//									}
//								}
//							}
//						}
//					}
//				}catch(Exception e){
//					
//				}
//				finally{
//					if(dMgr != null){
//						dMgr.release();
//					}
//					if(baseMgr != null){
//						dMgr.release();
//					}
//				}
//			}
//
//			public Class getCommandType() {
//				return VaryActivityCommand.class;
//			}
//
//			public void preUndo(Command command) {
//				// get old contributor/replacer of the activity
//				//
//				if(true) return;
//				Activity act = (Activity) command.getResult().iterator().next();
//				VariabilityElement baseAct = act.getVariabilityBasedOnElement();
//				if (baseAct != null) {
//					Activity parentAct = act.getSuperActivities();
//					DiagramManager dMgr = DiagramManager.getInstance(TngUtil.getOwningProcess(parentAct));
//					Diagram diagram = null; 
//					try {
//						List diagrams = dMgr.getDiagrams(parentAct,
//								GraphicalDataHelper.ACTIVITY_DIAGRAM);
//						diagram = (Diagram) diagrams.get(0);
//						if (diagram != null) {
//							// find existing node for the old
//							// contributore/replacer
//							// old and relink it to the base activity
//							Node node = findNode(diagram, act);
//							if (node != null) {
//								NodeAdapter adapter = BridgeHelper
//										.getNodeAdapter(node.getElement());
//								if (adapter != null
//										&& adapter.getElement() != baseAct) {
//									BridgeHelper.associate((EModelElement) node
//											.getElement(), baseAct);
//									BridgeHelper
//											.markInherited((EModelElement) node
//													.getElement());
//								}
//							}
//						}
//						// remove diagrams 
//						
//						
//					}catch(Exception e){
//						
//					}
//					finally {
//							
//					}
//				}
//			}
//
//			public void preExecute(Command command) {
//				
//			}
//
//		});
//
//		return commandListeners;
//	}
	
	public static void setDiagramOptions(Activity activity , int diagramType){
		switch(diagramType){
		case IDiagramManager.ACTIVITY_DIAGRAM:
			DiagramOptions.setPublishAD(activity, false);
			break;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			DiagramOptions.setPublishADD(activity, false);
			break;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			DiagramOptions.setPublishWPDD(activity, false);
			break;	
		}
	}
	
	public static boolean isPublishDiagram(Activity activity, int diagramType){
		switch(diagramType){
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return DiagramOptions.isPublishAD(activity);
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return DiagramOptions.isPublishADD(activity);
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return DiagramOptions.isPublishWPDD(activity);
		}
		return false;
	}
}
