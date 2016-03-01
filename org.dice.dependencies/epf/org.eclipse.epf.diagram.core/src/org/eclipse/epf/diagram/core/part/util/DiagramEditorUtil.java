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
package org.eclipse.epf.diagram.core.part.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.ActivityDiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.editparts.InternalNodeEditPart;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.part.DiagramFileEditorInputProxy;
import org.eclipse.epf.diagram.core.part.IDiagramEditorInputProvider;
import org.eclipse.epf.diagram.core.part.IDiagramFileEditorInputProxy;
import org.eclipse.epf.diagram.core.providers.SharedResourceDiagramDocumentProvider;
import org.eclipse.epf.diagram.core.resources.IDiagramStorage;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.impl.DiagramImpl;
import org.eclipse.epf.diagram.model.impl.NodeImpl;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

/**
 * @author Phong Nguyen Le
 * @author skannoor
 * @since 1.2
 */
public class DiagramEditorUtil {
	private static final String AD_EDITOR_ID = "org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorID"; //$NON-NLS-1$
	private static final String ADD_EDITOR_ID = "org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorID"; //$NON-NLS-1$
	private static final String WPDD_EDITOR_ID = "org.eclipse.epf.diagram.wpdd.part.WorkProductDependencyDiagramEditorID"; //$NON-NLS-1$

	public static String getEditorId(int diagramType) {
		switch(diagramType) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return AD_EDITOR_ID;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return ADD_EDITOR_ID;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return WPDD_EDITOR_ID;
		}
		return null;
	}

	public static IEditorPart openDiagramEditor(IWorkbenchPage page, DiagramEditorInput input, PreferencesHint hint, IProgressMonitor progressMonitor) {
		DiagramEditorInputProxy inputProxy = new DiagramEditorInputProxy(input, hint);

		IEditorPart editorPart = null;
		try {
			editorPart = page.openEditor(inputProxy, getEditorId(input.getDiagramType()), true);
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		}

		if(editorPart instanceof AbstractDiagramEditor){
			if(input.getDiagramType() == IDiagramManager.ACTIVITY_DETAIL_DIAGRAM) {
				AbstractDiagramEditor editor = ((AbstractDiagramEditor)editorPart);
				editor.resetLayout();
				// TODO: has to call resetLayout twice here so it can layout properly
				// need to improve this method
				//
				editor.resetLayout();
			}
			
			IDocumentProvider docProvider = ((DiagramDocumentEditor)editorPart).getDocumentProvider();

			// set the document as savable if the diagram is new
			//
			org.eclipse.epf.uma.Process proc = TngUtil.getOwningProcess(input.getMethodElement());
			if(proc != null) {
				DiagramManager mgr = null;
				Object consumer = new Object();
				try {
					mgr = DiagramManager.getInstance(proc, consumer);
					if(mgr != null && inputProxy.isNewDiagram()) {
						docProvider.setCanSaveDocument(inputProxy);
					}
				}
				finally {
					if(mgr != null) {
						mgr.removeConsumer(consumer);
					}
				}
			}

			// initialization might have made the document dirty
			// this will clear dirty flag of the editor
			//
			if (docProvider instanceof SharedResourceDiagramDocumentProvider
					&& editorPart.isDirty()
					&& !inputProxy.isNewDiagram()) {
				((SharedResourceDiagramDocumentProvider) docProvider)
				.markDocumentAsSaved(inputProxy);
			}			

			// Check whether diagram is read-only and plug-in is locked. 
			//
			if(!isModifiable(inputProxy)){
				((SharedResourceDiagramDocumentProvider)((AbstractDiagramEditor)editorPart).getDocumentProvider()).lock(true);
				DiagramEditPart editPart = ((IDiagramWorkbenchPart)editorPart).getDiagramEditPart();
				editPart.disableEditMode();
			}
		}
		return editorPart;
	}
	
	public static void refreshVisual(DiagramEditPart editPart) {
		// refresh labels
		//
		Iterator<Object> iter = new AbstractTreeIterator<Object>(editPart, false) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<?> getChildren(Object object) {
				if(object instanceof EditPart) {
					return ((EditPart)object).getChildren().iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}
			
		};
		while(iter.hasNext()) {
			Object child = iter.next();
			if(child instanceof ITextAwareEditPart || child instanceof ConnectionNodeEditPart) {
				((EditPart)child).refresh();
			}
		}

		// refresh connections
		//
		for (Iterator iterator = editPart.getConnections().iterator(); iterator.hasNext();) {
			ConnectionEditPart conn = (ConnectionEditPart) iterator.next();
			conn.refresh();
		}
	}
	
	public static void refreshLabels(DiagramEditPart editPart) {
//		// make sure that the edit part figure is large enough to show the icon and all the text
//		//
//		for (Iterator iterator = editPart.getChildren().iterator(); iterator.hasNext();) {
//			Object child = (Object) iterator.next();
//			if(child instanceof GraphicalEditPart) {
//				IFigure f = ((GraphicalEditPart)child).getFigure();
//				if(f instanceof DefaultSizeNodeFigure) {
//					Object f2 = ((DefaultSizeNodeFigure)f).getChildren().get(0);
//				if(f2 instanceof RectangleFigure) {
//					RectangleFigure figure = (RectangleFigure) f2;
//					List children = ((RectangleFigure)figure).getChildren();
//					if(!children.isEmpty()) {
//						Object l = children.get(0);
//						if(l instanceof WrapLabel) {
//							WrapLabel label = ((WrapLabel)l);
//							Rectangle r = figure.getBounds();
//							Dimension d = figure.getPreferredSize();
//							Rectangle textR = label.getTextBounds();							
//////							textR.width += (textR.width * WidenedWrapLabel.DELTA_FACTOR);
////							textR.width += 20;
////							if(r.width < d.width || r.height < d.height) {
////								WrapLabel label = ((WrapLabel)l);
////								label.invalidate();
////								figure.setBounds(r.union(textR));	
//////								System.out.println(label.getSubStringText());
////							}
////							if("Design the Solution".equals(label.getText())) {		
//								System.out.println("Text: '" + label.getText() + "'");
//								System.out.println("Label font: " + label.getFont());
//								System.out.println("Default font: " + DiagramConstants.DEFAULT_FONT);
//								System.out.println("Calculated text size: " + DiagramCoreUtil.getTextSizeInWrapLabel(label.getText(), null, r.width, r.height, null));
//								System.out.println("Current text width: " + textR.width);
//								System.out.println("Node width: " + r.width);
////							}
//						}
//					}
//				}
//				}
//			}			
//		}
		
		Iterator iter = new AbstractTreeIterator(editPart, false) {

			@Override
			protected Iterator getChildren(Object object) {
				if(object instanceof EditPart) {
					return ((EditPart)object).getChildren().iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}
			
		};
		while(iter.hasNext()) {
			Object child = iter.next();
			if(child instanceof ITextAwareEditPart) {
				((ITextAwareEditPart)child).refresh();
			}
		}
	}
	
	private static Diagram getDiagram(DiagramEditorInputProxy inputProxy) throws CoreException {
		IStorage storage = inputProxy.getStorage();
		return storage instanceof IDiagramStorage ? ((IDiagramStorage)storage).getDiagram() : null;
	}
	
	private static boolean doInitialize(
			final DiagramEditorInputProxy inputProxy,
			IProgressMonitor monitor) throws CoreException {
		final Diagram model = getDiagram(inputProxy);
		if(model == null) {
			return false;
		}
		if(DiagramManager.AD_kind.equals(model.getType())) {
			ActivityDiagramAdapter adapter = (ActivityDiagramAdapter) BridgeHelper.getNodeAdapter(model.getElement());
			if(adapter == null) {
				adapter = new ActivityDiagramAdapter(
								(Activity) inputProxy
										.getDiagramEditorInput().getMethodElement());
				adapter
						.setEditingDomain((InternalTransactionalEditingDomain) TransactionUtil
								.getEditingDomain(model));
				adapter.setSuppression(inputProxy
						.getDiagramEditorInput().getSuppression());
				adapter.setView(model);
				model.getElement().eAdapters().add(adapter);
			}
			adapter.populateDiagram();
			return true;
		}
		else if(DiagramManager.ADD_kind.equals(model.getType())
				|| DiagramManager.WPD_kind.equals(model.getType())) {
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(model);
			AbstractEMFOperation op = new AbstractEMFOperation(domain, StringStatics.BLANK) {
			
				@Override
				protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					org.eclipse.epf.diagram.model.Diagram d = 
						(org.eclipse.epf.diagram.model.Diagram) model.getElement();
					DiagramEditorInput input = inputProxy.getDiagramEditorInput();
					d.setSuppression(input.getSuppression());
					d.setLinkedElement(input.getMethodElement());
					d.setObject(d.getLinkedElement());

					return Status.OK_STATUS;
				}
			
			};
			try {
				op.execute(monitor, null);
				return true;
			} catch (ExecutionException e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			}
		} 
		return false;
	}
	
	public static void initializeOnActivate(DiagramEditPart editPart) {
		if(editPart.getViewer() instanceof IDiagramEditorInputProvider) {
			IEditorPart editor = ((IDiagramEditorInputProvider)editPart.getViewer()).getEditor();
			if(editor != null && editor.getEditorInput() instanceof DiagramEditorInputProxy) {
				DiagramEditorInputProxy inputProxy = (DiagramEditorInputProxy) editor.getEditorInput();
				IProgressMonitor monitor = new NullProgressMonitor();
				boolean dirty = editor.isDirty();
				try {
					if(doInitialize(inputProxy, monitor)) {
						doRefresh(editPart, monitor, false);
						refreshVisual(editPart);
					}
				} catch (CoreException e) {
					DiagramCorePlugin.getDefault().getLogger().logError(e);
				} finally {
					if(editor.isDirty() && !dirty) {
						// initialization made the document dirty
						// this will clear dirty flag of the editor
						//
						IDocumentProvider docProvider = ((DiagramDocumentEditor)editor).getDocumentProvider();
						if (docProvider instanceof SharedResourceDiagramDocumentProvider) {
							((SharedResourceDiagramDocumentProvider) docProvider)
							.markDocumentAsSaved(inputProxy);
						}			
					}
				}
				
			}
		}
	}

	public static void initialize(final DiagramEditPart editPart, final DiagramEditorInputProxy inputProxy, IProgressMonitor monitor) {		
		try {
			if(doInitialize(inputProxy, monitor)) {
				refresh(editPart, monitor);
				refreshLabels(editPart);
			}
		} catch (CoreException e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
	}
	private static void updateEdges(View view){
		boolean allVisible = false;
		for (Iterator iter = view.getChildren().iterator(); iter.hasNext();) {
			View child = (View) iter.next();
			if(child.getElement() instanceof NodeImpl) {
				NodeImpl node = (NodeImpl)child.getElement();
				if(!node.isVisible()){
					setEdgeVisibility(view, node.isVisible());
				}else{
					child.setVisible(node.isVisible());
					setEdgeVisibility(child, true);
				}
				if(node instanceof NamedNode) {
					FontStyle style = (FontStyle) child.getStyle(NotationPackage.Literals.FONT_STYLE);
					if(style == null) {
						style = (FontStyle) child.createStyle(NotationPackage.Literals.FONT_STYLE);
					}
				}
				if(child.isVisible() && child.getElement() instanceof NodeContainer) {
					updateEdges(child);
				}
				if(node.isVisible()){
					allVisible = true;
				}
			}
		}
		if(view.getElement() instanceof NodeContainer 
				&& !(view.getElement() instanceof ActivityDetailDiagram)
				&& !(view.getElement() instanceof WorkProductDependencyDiagram)){
			setEdgeVisibility(view, allVisible);
		}
		
	}
	
	private static void setEdgeVisibility(View view, boolean visibility){
		Diagram diagram = view.getDiagram();
		for (Iterator iterator = diagram.getEdges().iterator(); iterator
				.hasNext();) {
			Edge edge = (Edge) iterator.next();
			if(edge.getSource() == view || edge.getTarget() == view){
				if(visibility){
					view.setVisible(true);
				}
				edge.setVisible(visibility);
			}
		}
	}
	
	private static void updateView(View view) {
		boolean allVisible = false;
		for (Iterator iter = view.getChildren().iterator(); iter.hasNext();) {
			View child = (View) iter.next();
			if(child.getElement() instanceof NodeImpl) {
				NodeImpl node = (NodeImpl)child.getElement();
				child.setVisible(node.isVisible());
				if(node instanceof NamedNode) {
					FontStyle style = (FontStyle) child.getStyle(NotationPackage.Literals.FONT_STYLE);
					if(style == null) {
						style = (FontStyle) child.createStyle(NotationPackage.Literals.FONT_STYLE);
					}
				}
				if(child.isVisible() && child.getElement() instanceof NodeContainer) {
					updateView(child);
				}
				if(node.isVisible()){
					allVisible = true;
				}
			}
		}
		
		if(view.getElement() instanceof NodeContainer 
				&& !(view.getElement() instanceof ActivityDetailDiagram)
				&& !(view.getElement() instanceof WorkProductDependencyDiagram)) {
//			if(!allVisible) {
//				System.out.println();
//			}
			view.setVisible(allVisible);
		}
	}
	
	public static boolean isInherited(EditPart editPart) {
		EditPartViewer viewer = editPart.getViewer();
		if(viewer instanceof IDiagramEditorInputProvider) {
			DiagramEditorInput input = ((IDiagramEditorInputProvider)viewer).getDiagramEditorInput();
			return isInherited(input);
		}
		return false;
	}
	
	private static boolean isInherited(DiagramEditorInput input) {
		return input != null && input.getWrapper() != null
				&& input.getWrapper().isInherited();
	}
	
	private static Collection getSemanticChildren(DiagramEditPart editPart) {
		EditPartViewer viewer = editPart.getViewer();
		if(viewer instanceof IDiagramEditorInputProvider) {
			DiagramEditorInput input = ((IDiagramEditorInputProvider)viewer).getDiagramEditorInput();
			if(input != null && input.getWrapper() != null &&
					input.getWrapper().isInherited()) {
				Object model = editPart.getModel();
				if(model instanceof View) {
					Collection children = null;
					Diagram diagram = editPart.getDiagramView();
					String type = diagram.getType();
					if(DiagramManager.AD_kind.equals(type)) {
						DiagramAdapter da = ((DiagramAdapter)BridgeHelper.getNodeAdapter(diagram.getElement()));
						if(da != null) {
							BreakdownElementWrapperItemProvider wrapper = input.getWrapper();
							if(TngUtil.getBestAdapterFactory(wrapper.getAdapterFactory()) != TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory()) {
								List wrappers = ProcessUtil.getWrappers(wrapper, new AdapterFactory[] {
										TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory()});
								wrapper = (BreakdownElementWrapperItemProvider) wrappers.get(0);
							}
							ActivityDiagramAdapter adapter = null;
							try {
								adapter = new ActivityDiagramAdapter(wrapper);
								adapter.setFilter(da.getFilter());
								adapter.setSuppression(Suppression.getSuppression(TngUtil.getOwningProcess(wrapper)));
								children = adapter.getChildren();
							}
							finally {
								if(adapter != null) {
									adapter.dispose();
								}
							}
						}
					}
					else if(DiagramManager.ADD_kind.equals(type) || DiagramManager.WPD_kind.equals(type)) {
						DiagramImpl d;
						if(DiagramManager.ADD_kind.equals(type)) {
							d = (DiagramImpl) ModelFactory.eINSTANCE.createActivityDetailDiagram();
						}
						else {
							d = (DiagramImpl) ModelFactory.eINSTANCE.createWorkProductDependencyDiagram();
						}						
						DiagramImpl diagramInUse = ((DiagramImpl)diagram.getElement());
						Suppression suppression = diagramInUse.getSuppression();
						if(suppression != null) {
							d.setWrapper(input.getWrapper());
							d.setFilter(diagramInUse.getFilter());
							d.setSuppression(diagramInUse.getSuppression());
							children = d.getChildren();
						}
					}
					return children;
				}
			}
		}
		return null;
	}
	
	public static void refreshConnections(DiagramEditPart editPart) {
		Diagram diagram = editPart.getDiagramView();
		for (Iterator iter = diagram.getEdges().iterator(); iter.hasNext();) {
			Edge edge = (Edge) iter.next();
			boolean visible = edge.getSource() != null && edge.getSource().isVisible()
				&& edge.getTarget() != null && edge.getTarget().isVisible();
			if(edge.isVisible() != visible) {
				edge.setVisible(visible);
			}
		};
		
	}
	
	private static void refreshChildren(DiagramEditPart editPart) {
		Collection semanticChildren = getSemanticChildren(editPart);
		if(semanticChildren != null) {
			refreshChildren(editPart, semanticChildren);
			refreshConnections(editPart);
		}
	}
	
	
	public static void filterChildren(EditPart editPart, Collection children) {
		DiagramEditPart diagramEditPart = DiagramCoreUtil.getDiagramEditPart(editPart);
		Collection semanticChildren = getSemanticChildren(diagramEditPart);
		if(semanticChildren == null) {
			children.clear();
		}
		else {
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				 EObject child = (EObject) iter.next();
				 MethodElement e = BridgeHelper.getMethodElement(child);
				 if(getWrapper(e, semanticChildren) == null) {
					 iter.remove();
				 }
			}
		}
	}
	
	private static Object getWrapper(MethodElement e, Collection semanticChildren) {
		for (Iterator iterator = semanticChildren.iterator(); iterator.hasNext();) {
			Object c = (Object) iterator.next();
			if(TngUtil.unwrap(c) == e) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Refreshes edit part of an inherited element.
	 * 
	 * @param editPart
	 */
	private static void refreshChildren(EditPart editPart, Collection semanticChildren) {		
		// special handling for edit part of RoleTaskComposite that should not be shown if
		// none of its tasks are visible
		//
		boolean isRoleTaskComposite = false;
		boolean hasTask = false;
		Object model = editPart.getModel();
		if(model instanceof View && ((View)model).getElement() instanceof RoleTaskComposite) {
			isRoleTaskComposite = true;
		}
		
		for (Iterator<?> iter = editPart.getChildren().iterator(); iter
		.hasNext();) {
			Object child = (Object) iter.next();
			if(child instanceof EditPart) {
				boolean refreshChildren = true;
				EditPart childEditPart = ((EditPart)child);
				Object m = childEditPart.getModel();
				if(m instanceof View) {
					View v = (View) m;
					MethodElement e = BridgeHelper.getMethodElement(v);
					if(e != null) {
						Object object = getWrapper(e, semanticChildren);
						boolean visible = object != null;
						if(v.isVisible() != visible) {
							v.setVisible(visible);
						}
						if(!visible) {
							refreshChildren = false;
						}
						else if(isRoleTaskComposite && !hasTask && e instanceof TaskDescriptor) {
							hasTask = true;
						}
					}
					else {
						// this is a non-UMA node like control node, text node, figure...
						// 
					}
				}
				if(refreshChildren) {
					refreshChildren(childEditPart, semanticChildren);
				}
			}
		}
		
		if(isRoleTaskComposite && !hasTask) {
			((View)editPart.getModel()).setVisible(false);
		}
	}
	
	public static void doRefresh(final DiagramEditPart editPart, IProgressMonitor monitor, final boolean refreshData) {
		final boolean isInherited = isInherited(editPart);
		final Diagram diagram = (Diagram) editPart.getModel();
		if(diagram.getElement() instanceof org.eclipse.epf.diagram.model.Diagram) {
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
			AbstractEMFOperation op = new AbstractEMFOperation(domain, StringStatics.BLANK) {
				@Override
				protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
				throws ExecutionException {
					if(refreshData) {
						// refresh the data
						//
						org.eclipse.epf.diagram.model.Diagram d = (org.eclipse.epf.diagram.model.Diagram) diagram.getElement();
						d.setObject(d.getLinkedElement());
					}
					
					// show/hide nodes according updated data
					//
					if(isInherited) {
						refreshChildren(editPart);
					}
					else {
						updateEdges(diagram);
						updateView(diagram);
					}
					
					return Status.OK_STATUS;
				}	

			};
			try {
				op.execute(monitor, null);				
			} catch (ExecutionException e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			}		
		}
		else {
			NodeAdapter adapter = BridgeHelper.getNodeAdapter(diagram.getElement());
			if(adapter instanceof DiagramAdapter) {
				if(refreshData) {
					((DiagramAdapter)adapter).populateDiagram();
				}
				if(isInherited) {
					try {
						new AbstractEMFOperation(TransactionUtil.getEditingDomain(diagram), StringStatics.BLANK) {

							@Override
							protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
								refreshChildren(editPart);
								return Status.OK_STATUS;
							}
							
						}.execute(monitor, null);
					} catch (ExecutionException e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}
	
	public static void refresh(final DiagramEditPart editPart, IProgressMonitor monitor) {
		IDiagramEditorInputProvider p = ((IDiagramEditorInputProvider)editPart.getViewer()); 
		boolean wasDirty = p.getEditor() != null ? p.getEditor().isDirty() : false;
		boolean wasBaseDirty = false;
		IEditorPart baseEditor = null;
		final boolean isInherited = isInherited(editPart);
		if(isInherited) {
			MethodElement element = BridgeHelper.getMethodElement(editPart.getDiagramView());
			String type = editPart.getDiagramView().getType();
			
			if(p.getEditor() != null) {
				// try to find the dirty flag of base diagram editor
				//
				IEditorReference[] editorRefs = p.getEditor().getSite().getPage().getEditorReferences();
				for (int i = 0; i < editorRefs.length; i++) {
					IEditorReference ref = editorRefs[i];
					try {
						if(ref.getEditorInput() instanceof IDiagramFileEditorInputProxy) {
							DiagramEditorInput input = ((IDiagramFileEditorInputProxy)ref.getEditorInput()).getDiagramEditorInput();
							if(input.getWrapper() == null && input.getMethodElement() == element &&
									DiagramManager.getDiagramType(type) == input.getDiagramType()) {
								IEditorPart editor = ref.getEditor(false);
								if(editor != null) {
									wasBaseDirty = editor.isDirty();
									baseEditor = editor;
									break;
								}
							}
						}
					} catch (PartInitException e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
		
		doRefresh(editPart, monitor, true);
		
		// editors can become dirty after refresh
		// clear dirty flag
		//
		if(!wasDirty && p.getEditor() != null && p.getEditor().isDirty()) {
			((SharedResourceDiagramDocumentProvider)((DiagramDocumentEditor)p.getEditor()).getDocumentProvider())
				.markDocumentAsSaved((IFileEditorInput) p.getEditor().getEditorInput());
		}
		if(baseEditor != null && !wasBaseDirty) {
			((SharedResourceDiagramDocumentProvider)((DiagramDocumentEditor)baseEditor).getDocumentProvider())
				.markDocumentAsSaved((IFileEditorInput) baseEditor.getEditorInput());
		}
	}
	
	public static void refresh(final DiagramEditPart editPart, IProgressMonitor monitor, boolean isinherited){
		
		final boolean isInherited = isInherited(editPart);
		final Diagram diagram = (Diagram) editPart.getModel();
		if(diagram.getElement() instanceof org.eclipse.epf.diagram.model.Diagram) {
			try {
				TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
				TxUtil.runInTransaction(diagram.getElement(), new Runnable() {

					public void run() {
						// refresh the data
						//
						org.eclipse.epf.diagram.model.Diagram d = (org.eclipse.epf.diagram.model.Diagram) diagram.getElement();
						d.setObject(d.getLinkedElement());
					}
					
				});
				
				TxUtil.runInTransaction(domain, new Runnable() {

					public void run() {
						// show/hide nodes according updated data
						//
						if(isInherited) {
							refreshChildren(editPart);
						}
						else {
							updateEdges(diagram);
							updateView(diagram);
						}
					}
					
				});
			} catch (Exception e) {
				DiagramCorePlugin.getDefault().getLogger().logError(e);
			}
		}
		else {
			NodeAdapter adapter = BridgeHelper.getNodeAdapter(diagram.getElement());
			if(adapter instanceof DiagramAdapter) {
				((DiagramAdapter)adapter).populateDiagram();
				if(isInherited) {
					try {
						new AbstractEMFOperation(TransactionUtil.getEditingDomain(diagram), StringStatics.BLANK) {

							@Override
							protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
								refreshChildren(editPart);
								return Status.OK_STATUS;
							}
							
						}.execute(monitor, null);
					} catch (ExecutionException e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}
	
	public static void resetEditor(AbstractDiagramEditor editor){
		if(editor.isEditable()){
			DiagramEditPart editPart =editor.getDiagramEditPart();
			IEditorInput input = editor.getEditorInput();
			if(input instanceof DiagramEditorInputProxy ){
				initialize(editPart, (DiagramEditorInputProxy)input, 
						new NullProgressMonitor());
			}
		}
	}
	
	public static boolean isModifiable(Object element) {
		if(element instanceof DiagramFileEditorInputProxy){
				DiagramFileEditorInputProxy input = (DiagramFileEditorInputProxy)element;
				if(input != null){
					DiagramEditorInput dInput = input.getDiagramEditorInput();
					if(dInput != null){
						MethodElement e = dInput.getMethodElement();
						// Check for plugin lock.
						if(e != null){
							if(TngUtil.isLocked(e)) 
								return false;
						}
						// Check wrapper is readonly.
						Object wrapper = dInput.getWrapper();
						if(wrapper != null && ((BreakdownElementWrapperItemProvider)wrapper).isReadOnly()){
							return false;
						}
					}
				}
			}
		return true;
	}
	
	/**
	 * GMF and GEF is not handling connections visibility properly. 
	 * This is work-around solution to handle connections visibility.  
	 * Visibility of edges need to set first and then nodes. 
	 * defect opened for this: 
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=184081
	 */
	public static void refreshConnectionEditParts(DiagramEditPart diagramEditPart) {
		GraphicalViewer viewer = (GraphicalViewer)diagramEditPart.getViewer();
		Diagram act = (Diagram)diagramEditPart.getModel();
		List<?> edges = act.getEdges();
		for (Iterator<?> iter = edges.iterator(); iter.hasNext();) {
			Edge edge = (Edge) iter.next();
			if(edge.isVisible()){
				ConnectionEditPart part = (ConnectionEditPart)viewer.getEditPartRegistry().get(edge); 
					
				if(part != null){
					if(part.getSource() == null){
						for (Iterator<?> iter1 = diagramEditPart.getChildren().iterator(); iter1.hasNext();) {
							EditPart element = (EditPart) iter1.next();
							Object model = element.getModel();
							if(edge.getSource() == model){
								if(element instanceof GraphicalEditPart){
									GraphicalEditPart sourceEditPart = ((GraphicalEditPart)element);
									if(sourceEditPart.getFigure().isVisible()){
										part.setSource(element);
										if(sourceEditPart instanceof InternalNodeEditPart &&
												!sourceEditPart.getSourceConnections().contains(part)) {
											((InternalNodeEditPart) sourceEditPart).primAddSourceConnection(part, 0);
										}
									}else{
										part.setSource(null);
									}
								}
							}
						}
					}
					if(part.getTarget() == null){
						for (Iterator<?> iter1 = diagramEditPart.getChildren().iterator(); iter1.hasNext();) {
							EditPart element = (EditPart) iter1.next();
							Object model = element.getModel();
							if(edge.getTarget() == model){
								if(element instanceof GraphicalEditPart){
									GraphicalEditPart targetEditPart = ((GraphicalEditPart)element);
									if(targetEditPart.getFigure().isVisible()){
										part.setTarget(element);										
										if(targetEditPart instanceof InternalNodeEditPart &&
												!targetEditPart.getTargetConnections().contains(part)) {
											((InternalNodeEditPart) targetEditPart).primAddTargetConnection(part, 0);
										}
									} else {
										part.setTarget(null);
									}
								}
							}
						}
					}
				}
			}
		}
		
		try {
			for (Iterator<?> iter = edges.iterator(); iter.hasNext();) {
				Edge edge = (Edge) iter.next();
				org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart part = 
					(org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart)viewer.getEditPartRegistry().get(edge); 
				if(part != null && (part.getSource() == null || part.getTarget() == null)
						&& part.getFigure() != null){
					part.getFigure().setVisible(false);
					part.getFigure().revalidate();
					part.deactivate();
				}
			}
		} catch(Exception e){
			if(DiagramCorePlugin.getDefault().isDebugging()){
				DiagramCorePlugin.getDefault().getLogger().logError("Error occured while refresh the connection: "+ e); //$NON-NLS-1$
				e.printStackTrace();
			}
		}
	}
}
