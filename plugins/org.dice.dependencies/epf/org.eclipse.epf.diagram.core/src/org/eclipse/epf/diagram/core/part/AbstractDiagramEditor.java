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
/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.core.part;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.actions.AccessibilityMoveAction;
import org.eclipse.epf.diagram.core.actions.CreateElementAction;
import org.eclipse.epf.diagram.core.actions.DelegateAction;
import org.eclipse.epf.diagram.core.actions.DiagramActionsService;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.providers.DiagramContextMenuProvider;
import org.eclipse.epf.diagram.core.providers.SharedResourceDiagramDocumentProvider;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.core.services.DiagramService;
import org.eclipse.epf.diagram.core.util.DiagramConstants;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.ui.IMethodElementProvider;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.ui.editors.IMethodEditor;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.SelectAllAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.editor.FileDiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.StructuredActivityNode;

/**
 * @author Phong Nguyen Le
 * @author skannoor
 * @since 1.2
 */
public abstract class AbstractDiagramEditor extends FileDiagramEditor implements IGotoMarker, IMethodEditor {

	private DiagramManager diagramMgr;
	private DiagramActionsService actionService = null;
	
	private long fModificationStamp;
	private Activity activity;
	private AdapterImpl titleAdapter;

	private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {

		public void resourceChanged(IResourceChangeEvent event) {
			if(event.getSource() == diagramMgr) {
				IDocumentProvider docProvider = getDocumentProvider();
				if(docProvider instanceof SharedResourceDiagramDocumentProvider) {
					if(!isDirty()) {
						((SharedResourceDiagramDocumentProvider)docProvider).handleElementContentChanged(getEditorInput());
					}
				}				
			}
		}
		
	};
	
	// Listeners to refresh diagram if configuration got changed
	//
	private ILibraryChangeListener libraryListener = new ILibraryChangeListener() {
		public void libraryChanged(int option, Collection collection) {
			switch (option) {
			case ILibraryChangeListener.OPTION_CHANGED: {
				if (collection != null && collection.contains(currentConfig)) {
					MethodConfiguration config = ProcessAuthoringConfigurator.INSTANCE.getMethodConfiguration();
					try {
						ProcessAuthoringConfigurator.INSTANCE.setMethodConfiguration(currentConfig);					
						refresh();
					}
					finally {
						ProcessAuthoringConfigurator.INSTANCE.setMethodConfiguration(config);
					}
				}		
				break;
			}
			// handled by libSvcListener
			//
//			case ILibraryChangeListener.OPTION_CONFIGURATION_SELECTED: {
//				configChanged();
//				break;
//			}
			}
		}
	};
	
	private ILibraryServiceListener libSvcListener = new LibraryServiceListener() {

		public void configurationSet(MethodConfiguration config) {
			configChanged();
		}

	};

	private MethodConfiguration currentConfig;

	//TODO: if this flag is set, refresh diagram if needed when the editor is activated 
	private boolean needRefresh;
	private DiagramService diagramSvc;

	public AbstractDiagramEditor() {
		super(true);
	}
	
	public AbstractDiagramEditor(boolean hasFlyoutPalette) {
		super(hasFlyoutPalette);
	}

	protected String getEditingDomainID() {
		return "org.eclipse.epf.diagram.EditingDomain"; //$NON-NLS-1$
	}

	protected DiagramManager getDiagramManager() {
		if (diagramMgr == null) {
			org.eclipse.epf.uma.Process proc = TngUtil.getOwningProcess(activity);
			diagramMgr = DiagramManager.getInstance(proc, this);
			diagramMgr.addResourceChangeListener(resourceChangeListener);
		}
		return diagramMgr;
	}
	
	protected DiagramService getDiagramService() {
		if(diagramSvc == null) {
			diagramSvc = new DiagramService();
		}
		return diagramSvc;
	}

	protected TransactionalEditingDomain createEditingDomain() {
		TransactionalEditingDomain domain = getDiagramManager().getEditingDomain();
		domain.setID(getEditingDomainID());
		return domain;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#setDocumentProvider(org.eclipse.ui.IEditorInput)
	 */
	protected void setDocumentProvider(IEditorInput input) {
		setDocumentProvider(new SharedResourceDiagramDocumentProvider(getDiagramManager()) {
			@Override
			protected void doSynchronize(Object element, IProgressMonitor monitor) throws CoreException {
				// reload diagram's resource
				//
				DiagramManager mgr = getDiagramManager();
				try {
					mgr.removeResourceChangeListener(resourceChangeListener);
					mgr.reload();
				} catch (IOException e) {
					throw new CoreException(new Status(IStatus.ERROR, DiagramCorePlugin.getPluginId(),
							0, "Error reloading diagram", e)); //$NON-NLS-1$
				}
				finally {
					mgr.addResourceChangeListener(resourceChangeListener);
				}
				super.doSynchronize(element, monitor);
			}
		});		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#doSetInput(org.eclipse.ui.IEditorInput, boolean)
	 * @custom
	 */
	public void doSetInput(IEditorInput input, boolean releaseEditorContents)
			throws CoreException {
		activity = (Activity) getMethodElement(input);
		DiagramEditorInputProxy inputProxy = null;
		if (input instanceof DiagramEditorInputProxy) {
			inputProxy = (DiagramEditorInputProxy) input;
			inputProxy.setTransactionalEditingDomain(createEditingDomain());
		}
		super.doSetInput(input, releaseEditorContents);
		if(isEditable() && inputProxy != null && !inputProxy.isNewDiagram()) {
			getDiagramManager().backupDiagram(activity, getDiagram());
		}
		initializeTitle(input);
	}

	/**
	 * Initializes the editor's title based on the given editor input.
	 *
	 * @param input the editor input to be used
	 * @custom
	 */
	private void initializeTitle(IEditorInput input) {

		String title = ""; //$NON-NLS-1$
		if (input != null) {
			DiagramFileEditorInputProxy fileInput = (DiagramFileEditorInputProxy) input;
			DiagramEditorInput diagramInput = fileInput.getDiagramEditorInput();
			MethodElement inputElement = diagramInput.getMethodElement();
			if (inputElement != null) {
				//title = inputElement.getName();
				title = getPartNamePrefix() + 
							inputElement.getName()+
							StringStatics.COMMA + StringStatics.SPACE + 
							diagramInput.getSuppression().getProcess().getName(); 
//				ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
//						TngAdapterFactory.INSTANCE
//								.getNavigatorView_ComposedAdapterFactory());
				Image titleImage = getPartImage();				
				setTitleImage(titleImage);
			}
		}
		setPartName(title);
		firePropertyChange(PROP_DIRTY);
	}

	/**
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#sanityCheckState(org.eclipse.ui.IEditorInput)
	 * @custom
	 */
	protected void sanityCheckState(IEditorInput input) {
		IDocumentProvider p = getDocumentProvider();
		if (p == null)
			return;

		long stamp = p.getModificationStamp(input);
		if (stamp != fModificationStamp) {
			fModificationStamp = stamp;
			if (!p.isSynchronized(input)) {
				handleEditorInputChanged();
				if(!p.isSynchronized(input) && p instanceof SharedResourceDiagramDocumentProvider) {
					((SharedResourceDiagramDocumentProvider)p).allowEditing(input);
				}
			}
		}
		updateState(getEditorInput());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#dispose()
	 */
	public void dispose() {
		Diagram diagram = getDiagram();
		
		// if changes discarded, reverse to saved
		//		
		if(isDirty()) {
			reverseToSaved();
		}		
		
		if(titleAdapter != null){
			MethodElement e = getMethodElement(getEditorInput());
			if(e != null){
				e.eAdapters().remove(titleAdapter);
			}
			titleAdapter = null;
		}
		
		if(diagramMgr != null) {
			if(activity != null && diagram != null) {
				diagramMgr.removeDiagramBackup(activity, diagram.getType());
			}
			diagramMgr.removeResourceChangeListener(resourceChangeListener);
			diagramMgr.removeConsumer(this);
		}		
		
		if(diagramSvc != null) {
			diagramSvc.dispose();
		}
				
		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.removeListener(libraryListener);
		}
		LibraryService.getInstance().removeListener(libSvcListener);
		
		// Remove the adapter from the uml activity if editor is closed.
		if(getDiagram().getElement() instanceof org.eclipse.uml2.uml.Activity){
			org.eclipse.uml2.uml.Activity act = (org.eclipse.uml2.uml.Activity)getDiagram().getElement();
			if(act != null && getDiagramAdapter() != null){
				act.eAdapters().remove(getDiagramAdapter());
			}
		}
		
		// dispose the input
		//
		IEditorInput input = getEditorInput();
		if(input instanceof IDisposable) {
			((IDisposable)input).dispose();
		}
		
		DiagramEditPart editPart = getDiagramEditPart();
		if(editPart != null) {
			editPart.setModel(null);
		}

		super.dispose();
	
	}

	private DiagramAdapter getDiagramAdapter() {
		View view = ((View) getDiagramEditPart().getModel());
		return view != null ? (DiagramAdapter) BridgeHelper
				.getNodeAdapter(view.getElement()) : null;
	}

	private void reverseToSaved() {
		try {
			IDocumentProvider p = getDocumentProvider();
			if(p instanceof SharedResourceDiagramDocumentProvider) {
				((SharedResourceDiagramDocumentProvider)p).reverseToSaved(getEditorInput());
			}
			
			DiagramAdapter adapter = getDiagramAdapter();
			if(adapter == null) {
				//TODO: reverse to saved for ADD and WPDD
				return;
			}
			IActionManager actionMgr = adapter.getActionManager();
			boolean dirty = actionMgr.isSaveNeeded();
			if (dirty) {
				actionMgr.undoAll();
//				getOperationHistory().undo(getUndoContext(), new NullProgressMonitor(), null);
//				getOperationHistory().dispose(getUndoContext(), true, true, true);
			}
			getActionManager().clear();

			// save the undone changes only if the resource has been changed outside this editor
			//
			boolean saveNeeded = dirty
			&& getLastModified() > adapter.getUmaLastModified();
			MethodElement me;
			if (saveNeeded && (me = getMethodElementFromInput()) != null) {
				ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
				.getCurrentPersister().getFailSafePersister();
				try {
					persister.save(me.eResource());
					persister.commit();
				} catch (Exception e) {
					CommonPlugin.getDefault().getLogger().logError(e);
					try {
						persister.rollback();
					} catch (Exception ex) {
						//TODO: reload library needed
						//					ViewHelper.reloadCurrentLibrary(getSite().getShell(), null);
						ex.printStackTrace();
					}
				}
			}
		}
		catch(Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
			e.printStackTrace();
		}
	}

	private Process getProcess() {
		Object input = getEditorInput();
		if (input instanceof IDiagramFileEditorInputProxy) {
			return ((IDiagramFileEditorInputProxy) input)
					.getDiagramEditorInput().getSuppression().getProcess();
		}
		return null;
	}
	
	protected MethodElement getMethodElementFromInput() {
		return getMethodElement(getEditorInput());
	}
	
	private static MethodElement getMethodElement(IEditorInput input) {
		if (input instanceof DiagramFileEditorInputProxy) {
			return ((DiagramFileEditorInputProxy) input)
					.getDiagramEditorInput().getMethodElement();
		}
		return null;
	}

	private long getLastModified() {
		Resource resource = getMethodElementFromInput().eResource();
		if (resource != null && resource.getURI().isFile()) {
			File file = new File(resource.getURI().toFileString());
			return file.lastModified();
		}
		return -1;
	}

	public void doSave(IProgressMonitor progressMonitor) {
		DiagramAdapter diagramAdapter = getDiagramAdapter();
		if(diagramAdapter != null) {
			IActionManager actionMgr = getDiagramAdapter().getActionManager();
			if (actionMgr != null && actionMgr.isSaveNeeded()) {
				try {
					progressMonitor.beginTask(NLS.bind(DiagramCoreResources.Progress_Saving_message, StrUtil.EMPTY_STRING), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
					.getCurrentPersister().getFailSafePersister();
					Collection<?> modifiedResources = actionMgr.getModifiedResources();					
					// check for modifiable
					//
					IStatus status = Services.getAccessController().checkModify(modifiedResources.toArray(new Resource[0]), getSite().getShell());
					if(!status.isOK()) {
						return;
					}
					try {
						for (Iterator<?> iter = modifiedResources.iterator(); iter.hasNext();) {
							Resource resource = (Resource) iter.next();
							progressMonitor.setTaskName(NLS.bind(DiagramCoreResources.Progress_Saving_message,
									resource.getURI().isFile() ? resource.getURI()
											.toFileString() : "")); //$NON-NLS-1$
							persister.save(resource);
						}
						persister.commit();
						actionMgr.saveIsDone();
					} catch (Exception e) {
						CommonPlugin.getDefault().getLogger().logError(e);
						try {
							persister.rollback();
						} catch (Exception ex) {
							CommonPlugin.getDefault().getLogger().logError(ex);
							//						ViewHelper
							//						.reloadCurrentLibaryOnRollbackError(getSite()
							//						.getShell());
							return;
						}
						
						/*
						 * NO UI module should not involve with UI classes
						 * use callback to handle such kind of situation
						 * Jinhua Xi, 01/13/08
						*/
//						CommonPlugin.getDefault().getMsgDialog().displayWarning(
//								getSite().getShell().getText(),
//								DiagramCoreResources.Warning_Saving_Diagram, e.getMessage(),
//								e);
						CommonPlugin.getDefault().getMsgCallback().displayWarning(
								CommonPlugin.getDefault(), 
								DiagramCoreResources.Warning_Saving_Diagram, e.getMessage(),
								e);
					
						return;
					}

				} finally {
					progressMonitor.done();
				}

			}
		}
		
		cleanUp();
		super.doSave(progressMonitor);		

		//Alex: to fix RATLC00430370 
		getDiagramManager().backupDiagram(activity, getDiagram());
	}
	
	protected void cleanUp() {
		// remove any orphan UML elements
		//
		Diagram diagram = getDiagram();
		EObject model = diagram.getElement();
		if(model != null) {
			final HashSet<EObject> orphans = new HashSet<EObject>();
			for (Iterator<EObject> iter = model.eAllContents(); iter.hasNext();) {
				EObject object = iter.next();
				if(isOrphan(object)) {
					orphans.add(object);
				}
			}
			if(!orphans.isEmpty()) {
				try {
					TxUtil.runInTransaction(diagram, new Runnable() {

						public void run() {
							for (EObject orphan : orphans) {
								EcoreUtil.remove(orphan);
							}
						}
						
					});
				} catch (ExecutionException e) {
					DiagramCorePlugin.getDefault().getLogger().logError(e);
				}
			}
		}

	}

	protected boolean isOrphan(EObject modelElement) {
		return false;
	}

	private class DiagramGraphicalViewerEx extends DiagramGraphicalViewer implements IDiagramEditorInputProvider {

		public DiagramEditorInput getDiagramEditorInput() {
			return ((IDiagramFileEditorInputProxy)getEditorInput()).getDiagramEditorInput();
		}

		public IEditorPart getEditor() {
			return AbstractDiagramEditor.this;
		}				
	}
	
	@Override
	protected ScrollingGraphicalViewer createScrollingGraphicalViewer() {
		return new DiagramGraphicalViewerEx();
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		currentConfig = LibraryService.getInstance().getCurrentMethodConfiguration();
		
		// listen to change for current selection of MethodConfiguration or changes in 
		// current configuration.
		//
		LibraryService.getInstance().addListener(libSvcListener);
		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.addListener(libraryListener);
		}
		getGraphicalControl().addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				handleDoubleClick(event);
			}
		});
		IDiagramGraphicalViewer viewer =  getDiagramGraphicalViewer();
        ContextMenuProvider provider = new DiagramContextMenuProvider(this,
                getDiagramGraphicalViewer());
            viewer.setContextMenu(provider);
            getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,
                provider, viewer);
		if(actionService != null){
			actionService.setGraphicalViewer(viewer);
		}
		
		titleAdapter = new AdapterImpl(){
			@Override
			public void notifyChanged(Notification msg) {
				switch (msg.getFeatureID(MethodElement.class)) {
				case UmaPackage.METHOD_ELEMENT__NAME:
//					setName(msg.getNewStringValue());
					initializeTitle(getEditorInput());
					break;
				}
			}
		};
		MethodElement e = getMethodElement(getEditorInput());
		if(e != null && titleAdapter != null){
			e.eAdapters().add(titleAdapter);
		}
	}
		
	private void configChanged() {
		MethodConfiguration config = LibraryService.getInstance().getCurrentMethodConfiguration();
		if(currentConfig != config) {
			// refresh only if the active part is this diagram editor or process editor of the process
			// that owns this diagram
			//
			IWorkbenchPart activePart = getSite().getWorkbenchWindow().getPartService().getActivePart();
			boolean refresh = activePart == this;
			if(!refresh) {
				if(activePart instanceof IEditorPart) {
					IEditorInput input = ((IEditorPart)activePart).getEditorInput();
					if(input instanceof IMethodElementProvider) {
						Object e = ((IMethodElementProvider)input).getMethodElement();
						if(e instanceof ProcessComponent) {
							refresh = e instanceof ProcessComponent && 
							getProcess() == ((ProcessComponent)e).getProcess();
						}
					}
				}
			}
			if(refresh) {
				currentConfig = config;
				refresh();
				needRefresh = false;
			}
			else {
				needRefresh = true;
			}
		}
	}
	
	public void refresh() {
		if(getDocumentProvider() instanceof SharedResourceDiagramDocumentProvider){
			SharedResourceDiagramDocumentProvider docProvider = ((SharedResourceDiagramDocumentProvider)getDocumentProvider());
			DiagramEditPart editPart = getDiagramEditPart();
			boolean wasDirty = isDirty();
			boolean wasBaseDirty = false;
			IEditorPart baseEditor = null;
			final boolean isInherited = DiagramEditorUtil.isInherited(editPart);
			if(isInherited) {
				MethodElement element = BridgeHelper.getMethodElement(editPart.getDiagramView());
				String type = editPart.getDiagramView().getType();
				
				// try to find the dirty flag of base diagram editor
				//
				IEditorReference[] editorRefs = getSite().getPage().getEditorReferences();
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

			docProvider.setContent(getEditorInput());
			if(wasDirty) {
				docProvider.setCanSaveDocument(getEditorInput());
			}
			// editors can become dirty after refresh
			// clear dirty flag
			//
			else if(isDirty()) {
				((SharedResourceDiagramDocumentProvider) getDocumentProvider())
						.markDocumentAsSaved((IFileEditorInput)getEditorInput());
			}
			if(baseEditor != null && !wasBaseDirty) {
				((SharedResourceDiagramDocumentProvider)((DiagramDocumentEditor)baseEditor).getDocumentProvider())
					.markDocumentAsSaved((IFileEditorInput) baseEditor.getEditorInput());
			}
			updateState(getEditorInput());
		}
	}
	
	// Does nothing but for override. 
	protected void handleDoubleClick(Event event) {
	}
	
	@Override
	public boolean isDirty() {
		if(!DiagramEditorUtil.isModifiable(getEditorInput())){
			return false;
		}
		return super.isDirty();
	}
	
	@Override
	public boolean isEditable() {
		if(getDocumentProvider() instanceof SharedResourceDiagramDocumentProvider){
			return !((SharedResourceDiagramDocumentProvider)getDocumentProvider()).getLockedState();
		}
		return true;
	}
	
	@Override
	protected KeyHandler getKeyHandler() {
		KeyHandler keyHandler =  super.getKeyHandler();
		if(keyHandler != null){
			ActionRegistry registry = getActionRegistry();
			IAction action = registry.getAction(ActionFactory.DELETE.getId());
			
			if(action != null && !(action instanceof DelegateAction)){
				getActionRegistry().removeAction(action);
				getSelectionActions().remove(action);
				action = new DelegateAction((IWorkbenchPart)this,ActionFactory.DELETE.getId());
				action.setId(ActionFactory.DELETE.getId());
				getActionRegistry().registerAction(action);
				getSelectionActions().add(action.getId());
			}
            keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
            	getActionRegistry().getAction(ActionFactory.DELETE.getId()));
            keyHandler.put(KeyStroke.getPressed(SWT.BS, 8, 0),
                    getActionRegistry().getAction(ActionFactory.DELETE.getId()));
            keyHandler.put(/* CTRL + D */
                        KeyStroke.getPressed((char) 0x04, 100, SWT.CTRL),
                            getActionRegistry().getAction(
                               ActionFactory.DELETE.getId()));
            getActionRegistry().registerAction(new AccessibilityMoveAction(this,PositionConstants.SOUTH));
            AccessibilityMoveAction moveAction = ((AccessibilityMoveAction)getActionRegistry().getAction(AccessibilityMoveAction.MOVE_DOWN_ACTION));
            keyHandler.put(KeyStroke.getPressed(SWT.ARROW_DOWN, SWT.CTRL),
            		moveAction);
            
            getActionRegistry().registerAction(new AccessibilityMoveAction(this,PositionConstants.NORTH));
            moveAction = ((AccessibilityMoveAction)getActionRegistry().getAction(AccessibilityMoveAction.MOVE_UP_ACTION));
            keyHandler.put(KeyStroke.getPressed(SWT.ARROW_UP, SWT.CTRL),
            		moveAction);
            
            getActionRegistry().registerAction(new AccessibilityMoveAction(this,PositionConstants.WEST));
            moveAction = ((AccessibilityMoveAction)getActionRegistry().getAction(AccessibilityMoveAction.MOVE_LEFT_ACTION));
            keyHandler.put(KeyStroke.getPressed(SWT.ARROW_LEFT, SWT.CTRL),
            		moveAction);
            
            getActionRegistry().registerAction(new AccessibilityMoveAction(this,PositionConstants.EAST));
            moveAction = ((AccessibilityMoveAction)getActionRegistry().getAction(AccessibilityMoveAction.MOVE_RIGHT_ACTION));
            keyHandler.put(KeyStroke.getPressed(SWT.ARROW_RIGHT, SWT.CTRL),
            		moveAction);
            
            /* CTRL + A */
            action = SelectAllAction.createSelectAllAction(getEditorSite()
					.getWorkbenchWindow().getActivePage());
            getActionRegistry().registerAction(action);
            getSelectionActions().add(action.getId());
			keyHandler.put(
            		KeyStroke.getReleased((char) 0x1, 97, SWT.CTRL),
            		action);
			// Key pressed event for CTRL + A is not triggered, use key released event for now
			//
			keyHandler.put(
            		KeyStroke.getReleased((char) 0x1, 97, SWT.CTRL),
            		action);
 		}
		
		return keyHandler;
	}
	
	
	@Override
	protected void createActions() {
		super.createActions();
		createControlFlowAction();
		
		// Register alignment actions:
		actionService = new DiagramActionsService(
				getDiagramManager().getEditingDomain(),
				getGraphicalViewer(),
				getEditDomain(), getActionRegistry());
		actionService.registerHorizontalAlignAverageAction();
		actionService.registerHorizontalAlignFirstSelectedAction();
		actionService.registerVerticalAlignAverageAction();
		actionService.registerVerticalAlignFirstSelectedAction();
		
	}
	
	protected IAction createAnAction(String actionName,
			List<IElementType> elementType, String actionId,
			String tooltipString, ImageDescriptor imagedesc) {
		CreateElementAction action = new CreateElementAction(this, actionName);
		action.setElementTypes(elementType);
		action.setPreferenceHint(getPreferencesHint());
		action.setImageDescriptor(imagedesc);
		action.setToolTipText(tooltipString);
		action.setId(actionId);
		return action;
	}
	
	public void contributeToContextMenu(IMenuManager menu) {
		if(actionService != null){
			actionService.createAlignMenu(menu, isEditable());
		}
		addToMenu(menu, DiagramConstants.CREATE_LINK,
				GEFActionConstants.MB_ADDITIONS, true, true);
	}
	
	protected void addToMenu(IMenuManager menu, String id, String groupName, 
			boolean separator, boolean append) {
		boolean canModify = isEditable();
		if (id != null) {
			IAction action = getActionRegistry().getAction(id);
			if (action != null && action.isEnabled() && canModify) {
				if (append) {
					menu.appendToGroup(groupName, action);
				} else {
					menu.prependToGroup(groupName, action);
				}
				if (separator) {
					menu.insertAfter(id, new Separator());
				}
			}
		}else{
			if(separator && canModify){
				if(append){
					menu.appendToGroup(groupName, new Separator());
				}else{
					menu.prependToGroup(groupName, new Separator());
				}
			}
		}
	}
	
	/**
	 * To reset the layout of the diagram.
	 */
	public void resetLayout(){
		// does nothing. client should implement.
	}
	
	
	/**
	 * Refresh diagram
	 */
	public void refreshDiagram(){
		refresh();
	}
	
	protected abstract String getPartNamePrefix();
	
	protected abstract Image getPartImage();
	
	private void createControlFlowAction(){
		IAction linkAction = new Action(DiagramCoreResources.ActivityDiagram_Palette_control_flow_create_text) { //$NON-NLS-1$
			public void run() {
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 2) {
					EditPart sourceEditPart = (EditPart) editParts.get(0);
					EditPart targetEditPart = (EditPart) editParts.get(1);
					
					View sourceModel = (View)sourceEditPart.getModel();
					View targetModel = (View)targetEditPart.getModel();
					
					if(BridgeHelper.isReadOnly(sourceModel) 
							|| BridgeHelper.isReadOnly(targetModel))
						return;
					
					CreateUnspecifiedTypeConnectionRequest connectionRequest = 
						getCreateControlFlowRequest();
					if (connectionRequest != null) {
						connectionRequest.setSourceEditPart(null);
						connectionRequest.setTargetEditPart(targetEditPart);
						connectionRequest
								.setType(RequestConstants.REQ_CONNECTION_START);
						connectionRequest.setLocation(new Point(0, 0));
						sourceEditPart.getCommand(connectionRequest);

						connectionRequest.setSourceEditPart(sourceEditPart);
						connectionRequest.setTargetEditPart(targetEditPart);
						connectionRequest
								.setType(RequestConstants.REQ_CONNECTION_END);
						connectionRequest.setLocation(new Point(0, 0));

						DiagramEditPart diagramEditPart = getDiagramEditPart();
						if (diagramEditPart.isEditModeEnabled()) {
							Command cmd = targetEditPart
									.getCommand(connectionRequest);
							if (cmd != null && cmd.canExecute()) {
								cmd.execute();
							}
						}
					}
				}
			}

			public boolean isEnabled() {
				if(getCreateControlFlowRequest()== null) return false;
				List editParts = getGraphicalViewer().getSelectedEditParts();
				if (editParts.size() == 2) {
					EditPart sourceEditPart = (EditPart) editParts.get(0);
					EditPart targetEditPart = (EditPart) editParts.get(1);

					View sourceModel = (View) sourceEditPart.getModel();
					View targetModel = (View) targetEditPart.getModel();
					if(BridgeHelper.isReadOnly(sourceModel) 
							|| BridgeHelper.isReadOnly(targetModel))
						return false;
					
					for (Iterator iter = getDiagram().getEdges().iterator(); iter.hasNext();) {
						Edge edge = (Edge) iter.next();
						if(edge.getSource()== sourceModel && edge.getTarget() == targetModel){
							return false;
						}
					}
					
					if (sourceModel.getElement() instanceof ActivityNode
							| sourceModel.getElement() instanceof NamedNode
							|| targetModel.getElement() instanceof ActivityNode
							|| targetModel.getElement() instanceof NamedNode) {
						return true;
					}
					
				}
				return false;
			}

			public String getId() {
				return DiagramConstants.CREATE_LINK;
			}
			@Override
			public ImageDescriptor getImageDescriptor() {
				return DiagramConstants.CONTROL_FLOW_IMAGE_DESCRIPTOR;
			}
		};
		getActionRegistry().registerAction(linkAction);
	}

	/**
	 * Does nothing client should implement.
	 * @return
	 */
	protected CreateUnspecifiedTypeConnectionRequest getCreateControlFlowRequest() {
		return null;
	}
	
	protected Diagram getDiagram(Activity activity, int type) {
		return getDiagramService().getDiagram(activity, type);
	}

}
