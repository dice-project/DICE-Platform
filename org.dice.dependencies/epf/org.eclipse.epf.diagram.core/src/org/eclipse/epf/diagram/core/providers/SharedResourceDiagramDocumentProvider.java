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
package org.eclipse.epf.diagram.core.providers;

import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.part.IDiagramFileEditorInputProxy;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.core.services.FileSynchronizer;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.services.ILibraryPersister.FailSafeMethodLibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramModificationListener;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.FileDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.StorageDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.WorkspaceOperationRunner;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.EditorIDEPlugin;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.EditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.EditorStatusCodes;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class SharedResourceDiagramDocumentProvider extends StorageDiagramDocumentProvider 
{
	private static class FileDiagramDocumentProviderEx extends FileDiagramDocumentProvider {
		@Override
		protected ISchedulingRule getSaveRule(Object element) {
			if (element instanceof IFileEditorInput) {
				IFileEditorInput input= (IFileEditorInput) element;
				return computeSaveSchedulingRule(input.getFile());
			}
			return null;
		}
		
		/**
		 * Computes the scheduling rule needed to create or modify a resource. If
		 * the resource exists, its modify rule is returned. If it does not, the
		 * resource hierarchy is iterated towards the workspace root to find the
		 * first parent of <code>toCreateOrModify</code> that exists. Then the
		 * 'create' rule for the last non-existing resource is returned.
		 *
		 * @param toCreateOrModify the resource to create or modify
		 * @return the minimal scheduling rule needed to modify or create a resource
		 */
		private ISchedulingRule computeSaveSchedulingRule(IResource toCreateOrModify) {
			if (toCreateOrModify.exists() && toCreateOrModify.isSynchronized(IResource.DEPTH_ZERO))
				return fResourceRuleFactory.refreshRule(toCreateOrModify);

			IResource parent= toCreateOrModify;
			do {
				 /*
				 * XXX This is a workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=67601
				 * IResourceRuleFactory.createRule should iterate the hierarchy itself.
				 */
				toCreateOrModify= parent;
				parent= toCreateOrModify.getParent();
			} while (parent != null && !parent.exists() && !parent.isSynchronized(IResource.DEPTH_ZERO));

			return fResourceRuleFactory.createRule(toCreateOrModify);
		}
		
		@Override
		protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
			return super.getOperationRunner(monitor);
		}
	}
	
	private static final FileDiagramDocumentProviderEx fileDiagramDocumentProvider = new FileDiagramDocumentProviderEx();
	
	//a StorageInfo with a DiagramModificationListener 
	private class DiagramStorageInfo extends StorageInfo {

		DiagramModificationListener fListener;
		public DiagramStorageInfo(IDocument document, DiagramModificationListener listener) {
			super(document);
			fListener = listener;
		}
		
	}

	private WorkspaceOperationRunner fOperationRunner;
	private DiagramManager fDiagramMgr;
	private boolean locked = false;

	public SharedResourceDiagramDocumentProvider(DiagramManager diagramMgr) {
		super();
		fDiagramMgr = diagramMgr;
	}
	
	@Override
	protected boolean setDocumentContent(IDocument document, IEditorInput editorInput) throws CoreException {
		if(document instanceof IDiagramDocument) {
			((IDiagramDocument)document).setEditingDomain(fDiagramMgr.getEditingDomain());
		}
		return super.setDocumentContent(document, editorInput);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.StorageDiagramDocumentProvider#setDocumentContentFromStorage(org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument, org.eclipse.core.resources.IStorage)
	 */
	protected void setDocumentContentFromStorage(IDocument document, IStorage storage) throws CoreException {
		Diagram diagram = (Diagram)document.getContent();
		if(diagram != null) {
			Resource resource = diagram.eResource();
			IFile resourceFile = WorkspaceSynchronizer.getFile(resource);
			// unload if the resourceFile and storage is same.
			// if not same throw exception.
			if(resourceFile != null) {
				if(resourceFile.equals(storage)) {
					document.setContent(null);
				} else {
					throw new CoreException(new Status(IStatus.ERROR, EditorIDEPlugin.getPluginId(), EditorStatusCodes.ERROR, EditorMessages.FileDocumentProvider_handleElementContentChanged, null));
				}
			}
		}
		document.setContent(((org.eclipse.epf.diagram.core.resources.IDiagramStorage)storage).getDiagram());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.StorageDiagramDocumentProvider#disposeElementInfo(java.lang.Object, org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider.ElementInfo)
	 */
	@Override
	protected void disposeElementInfo(Object element, ElementInfo info) {
		((DiagramStorageInfo)info).fListener.stopListening();
	}

	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {
		final Diagram diagram = (Diagram) document.getContent();
		//TODO: handle case where user did not refresh diagram after diagram's resource has been reloaded
		// diagram does not have a resource in this case
		// if overwrite allowed, this diagram should replace the diagram in the reloaded resource.
		//
		Resource resource = diagram.eResource();
		if (resource != null) {
			if (!overwrite) {
				DiagramManager.checkSynchronizationState(resource);
			}
			IStatus status = Services.getAccessController().checkModify(
					new Resource[] { resource }, MsgBox.getDefaultShell());
			if (!status.isOK()) {
				throw new CoreException(status);
			}
		}
		
		// inform about the upcoming content change
		fireElementStateChanging(element);
		
		if(diagram.getElement() instanceof org.eclipse.epf.diagram.model.Diagram) {
			try {
				TxUtil.runInTransaction(diagram, new Runnable() {

					public void run() {
						diagram.persistChildren();
						for (Object child : diagram.getChildren()) {
							((View) child).persistChildren();
						}
					}
					
				});
			} catch (ExecutionException e) {
				DiagramCorePlugin.getDefault().getLogger().logError(e);
			}
		}
		
		FailSafeMethodLibraryPersister persister = Services.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE).getFailSafePersister();
		try {
//			resource.save(Collections.EMPTY_MAP);
			persister.save(resource);
			persister.commit();
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
			
			persister.rollback();
			
			// inform about failure
			fireElementStateChangeFailed(element);
			throw new CoreException(new Status(IStatus.ERROR, DiagramCorePlugin.PLUGIN_ID
					, EditorStatusCodes.RESOURCE_FAILURE, e
					.getLocalizedMessage(), null));
		}
		
		try {
			fDiagramMgr.removeDiagramBackup(getActivity((IEditorInput) element), diagram.getType());
			if(DiagramManager.ADD_kind.equals(diagram.getType())
					|| DiagramManager.WPD_kind.equals(diagram.getType())) {
				org.eclipse.epf.diagram.model.Diagram d = (org.eclipse.epf.diagram.model.Diagram) diagram.getElement();
				d.setNew(false);
			}
		} catch (RuntimeException x) {
			// inform about failure
			fireElementStateChangeFailed(element);
			throw x;
		}
		
		if(monitor != null) {
			monitor.done();
		}
		
		logResourceErrorsAndWarnings(resource);
	}
	
	private static void logResourceErrorsAndWarnings(Resource resource) {
		if (resource != null)  {
			for (Iterator iter = resource.getErrors().iterator(); iter.hasNext();) {
				Resource.Diagnostic diagnostic = (Resource.Diagnostic) iter.next();
				Log.error(EditorPlugin.getInstance(), EditorStatusCodes.ERROR, diagnostic.getMessage());				
			}
	
			for (Iterator iter = resource.getWarnings().iterator(); iter.hasNext();) {
				Resource.Diagnostic diagnostic = (Resource.Diagnostic) iter.next();
				Log.warning(EditorPlugin.getInstance(), EditorStatusCodes.WARNING, diagnostic.getMessage());				
			}
		}
	}

	@Override
	public long getModificationStamp(Object element) {
		if (element instanceof IFileEditorInput) {
			IFileEditorInput input= (IFileEditorInput) element;
			return FileSynchronizer.computeModificationStamp(input.getFile());
		}

		return super.getModificationStamp(element);
	}
	
	public void setContent(IEditorInput input) {
		ElementInfo info = getElementInfo(input);
		if (info == null)
			return;

		IDocument document = createEmptyDocument();
		IStatus status = null;

		try {
			setDocumentContent(document, input);
		} catch (CoreException x) {
			status = x.getStatus();
		}
		
		Object newContent= document.getContent();
		
		// set the new content and fire content related events
		fireElementContentAboutToBeReplaced(input);

		removeUnchangedElementListeners(input, info);

		info.fDocument.removeDocumentListener(info);
		info.fDocument.setContent(newContent);
		info.fCanBeSaved = false;
		info.fStatus = status;

		addUnchangedElementListeners(input, info);

		fireElementContentReplaced(input);
	}
		
	/**
	 * Updates the element info to a change of the file content and sends out
	 * appropriate notifications.
	 *
	 * @param input the input of a document editor
	 */
	public void handleElementContentChanged(IEditorInput input) {
		ElementInfo info = getElementInfo(input);
		if (info == null)
			return;

		IDocument document= createEmptyDocument();
		IStatus status= null;

		try {
			setDocumentContent(document, input);
		} catch (CoreException x) {
			status= x.getStatus();
		}

		Object newContent= document.getContent();

		if ( !newContent.equals(info.fDocument.getContent())) {

			// set the new content and fire content related events
			fireElementContentAboutToBeReplaced(input);

			removeUnchangedElementListeners(input, info);

			info.fDocument.removeDocumentListener(info);
			info.fDocument.setContent(newContent);
			info.fCanBeSaved= false;
			info.fStatus= status;

			addUnchangedElementListeners(input, info);

			fireElementContentReplaced(input);

		} else {
			
			handleExistingDocumentSaved(input, info, status);

		}
	}

	private void handleExistingDocumentSaved(IEditorInput input, ElementInfo info, IStatus status) {
		removeUnchangedElementListeners(input, info);

		// fires only the dirty state related event
		info.fCanBeSaved= false;
		info.fStatus= status;

		addUnchangedElementListeners(input, info);

		fireElementDirtyStateChanged(input, false);		
	}

	public void markDocumentAsSaved(IFileEditorInput input) {
		ElementInfo info = getElementInfo(input);
		if (info == null)
			return;
		handleExistingDocumentSaved(input, info, null);
	}
	
	@Override
	protected void doSynchronize(Object element, IProgressMonitor monitor) throws CoreException {
		if(element instanceof IEditorInput) {
			handleElementContentChanged((IEditorInput) element);
			return;
		}
		super.doSynchronize(element, monitor);
	}
	
	@Override
	public boolean isSynchronized(Object element) {
		ElementInfo info = getElementInfo(element);
		if(info.fDocument instanceof IDiagramDocument) {
			Diagram diagram = ((IDiagramDocument)info.fDocument).getDiagram();
			if(diagram != null) {
				Resource resource = diagram.eResource();
				// a diagram that does not have a resource is considered as out-of-synch
				// because its resource must have been reloaded.
				//
				return resource != null && DiagramManager.isSynchronized(resource);
			}
		}
		return super.isSynchronized(element);
	}

	@Override
	public ElementInfo createNewElementInfo(IDocument document) {
		DiagramModificationListener listener = new AccessibleDiagramModificationListener(this, (DiagramDocument)document);
		DiagramStorageInfo info = new DiagramStorageInfo(document, listener);
		listener.startListening();
		return info;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editor.AbstractDocumentProvider#getOperationRunner(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
		return fileDiagramDocumentProvider.getOperationRunner(monitor);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editor.AbstractDocumentProvider#getSaveRule(java.lang.Object)
	 */
	protected ISchedulingRule getSaveRule(Object element) {
		return fileDiagramDocumentProvider.getSaveRule(element);
	}

	/**
	 * Allows editing even the document is out of synch.
	 * 
	 * @param input
	 */
	public void allowEditing(IEditorInput input) {
		ElementInfo info = getElementInfo(input);
		if(info.fDocument instanceof IDiagramDocument) {
			Diagram diagram = ((IDiagramDocument)info.fDocument).getDiagram();
			if(diagram != null && diagram.eResource() == null 
					&& input instanceof IDiagramFileEditorInputProxy) {
				Activity act = getActivity(input);
				if(act != null) {
					fDiagramMgr.replaceTemporarily(act, diagram);
				}
			}
		}
	}
	
	private static Activity getActivity(IEditorInput input) {
		return (Activity) ((IDiagramFileEditorInputProxy)input).getDiagramEditorInput().getMethodElement();
	}

	public void reverseToSaved(IEditorInput input) {
		ElementInfo info = getElementInfo(input);
		if(info.fDocument instanceof IDiagramDocument) {
			Diagram diagram = ((IDiagramDocument)info.fDocument).getDiagram();
			if(diagram != null && diagram.eResource() != null 
					&& input instanceof DiagramEditorInputProxy) {
				Activity act = getActivity(input);
				if(act != null) {
					fDiagramMgr.reverseToSaved(act, diagram, ((DiagramEditorInputProxy)input).getPreferenceHint());
				}
			}
		}
	}
	
	/**
	 * If a plugin is locked, diagram editor and document provider
	 * should be locked, this is done after loading the diagram editor
	 * using this method.
	 * @param lock
	 */
	public void lock(boolean lock){
		locked =lock; 
	}
	
	@Override
	protected void updateCache(IStorageEditorInput input) throws CoreException {
		super.updateCache(input);
		StorageInfo info= (StorageInfo) getElementInfo(input);
		if (info != null) {
			if(locked && info.fIsModifiable)
				info.fIsModifiable= !locked;
		}
	}
	
	/**
	 * 
	 * returns the locked state of plugin. 
	 * locked state is  set using lock(boolean lock) method. 
	 * @return
	 */
	public boolean getLockedState(){
		return locked;
	}
	
	@Override
	public boolean isModifiable(Object element) {
		if(!DiagramEditorUtil.isModifiable(element)) {
			return false;
		}
		return super.isModifiable(element);
	}
}
