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
package org.eclipse.epf.diagram.core.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.events.ResourceChangeEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.emf.transaction.util.ValidateEditSupport;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.providers.AccessibleDiagramModificationListener;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.WorkProductDependencyDiagram;
import org.eclipse.epf.diagram.model.impl.DiagramImpl;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.persistence.ILibraryResource;
import org.eclipse.epf.library.persistence.internal.IFailSafeSavable;
import org.eclipse.epf.library.persistence.util.ExtendedResourceSet;
import org.eclipse.epf.library.persistence.util.FileSynchronizer.FileInfo;
import org.eclipse.epf.persistence.FailSafePersistenceHelper;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.MultiFileURIConverter;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.emf.core.internal.util.EMFCoreConstants;
import org.eclipse.gmf.runtime.emf.core.resources.GMFHelper;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * @author Phong Nguyen Le
 * 
 * @since 1.2
 */
public class DiagramManager {	
	private static final boolean DEBUG = DiagramCorePlugin.getDefault().isDebugging();
	
	private static final String DIAGRAM_FILENAME_WITHOUT_EXTENSION = "diagram"; //$NON-NLS-1$
	
	private static final String DIAGRAM_FILENAME = DIAGRAM_FILENAME_WITHOUT_EXTENSION + MultiFileSaveUtil.DEFAULT_FILE_EXTENSION;

	private static final Map<Process, DiagramManager> processToDiagramManagerMap = new HashMap<Process, DiagramManager>();
	
	private static ILibraryServiceListener libSvcListener;
	
	private static final FileSynchronizer fileSynchronizer = new FileSynchronizer() {
		@Override
		protected Collection<IFile> handleChangedFiles(Collection<IFile> changedFiles) {
			try {
				Collection<DiagramManager> mgrs = getDiagramManagers();
				if(!mgrs.isEmpty()) {
					Collection<IFile> handledFiles = new ArrayList<IFile>();
					for (DiagramManager mgr : mgrs) {
						Resource resource = mgr.getResource(false);
						if(resource != null && resource.isLoaded()) {
							IFile file = WorkspaceSynchronizer.getFile(mgr.getResource());
							if(changedFiles.contains(file)) {
								try {									
									mgr.reload();
									handledFiles.add(file);
								} catch (IOException e) {
									CommonPlugin.getDefault().getLogger().logError(e);
									if(DEBUG) {
										e.printStackTrace();
									}
								}
							}
						}
					}
					return handledFiles;
				}
			}
			catch(CoreException e) {
				handleCoreException(e, e.getMessage());
			}
			return Collections.emptyList();
		}
		
		@Override
		protected Collection<IFile> handleMovedFiles(Map<IFile, IPath> movedFileToNewPathMap) {
			for (Map.Entry<IFile, IPath> entry : movedFileToNewPathMap.entrySet()) {
				IFile iFile = entry.getKey();
				if(DIAGRAM_FILENAME.equals(iFile.getName())) {
					synchronized (processToDiagramManagerMap) {
						DiagramManager mgr = getDiagramManager(iFile);
						if(mgr != null) {
							mgr.updateResourceURI();
						}
					}
				}
			}
			return movedFileToNewPathMap.keySet();
		}
		
		@Override
		protected Collection<IFile> handleDeletedFiles(Collection<IFile> deletedFiles) {
			return super.handleDeletedFiles(deletedFiles);
		}
	};

	public static final String ADD_kind = "ADD"; //$NON-NLS-1$

	public static final String AD_kind = "AD"; //$NON-NLS-1$

	public static final String WPD_kind = "WPDD"; //$NON-NLS-1$	
	
	private static DiagramManager getDiagramManager(IFile iFile) {
		for (Iterator iterator = processToDiagramManagerMap.values().iterator(); iterator
		.hasNext();) {
			DiagramManager mgr = (DiagramManager) iterator.next();
			if(mgr.resource != null && iFile.equals(WorkspaceSynchronizer.getFile(mgr.resource))) {
				return mgr;
			}
		}
		return null;
	}
	
	public static void disposeDiagramManagers(MethodLibrary library) {
		synchronized(processToDiagramManagerMap) {
			for (Iterator<Map.Entry<Process, DiagramManager>> iterator = processToDiagramManagerMap.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<Process, DiagramManager> entry = iterator.next();
				if(UmaUtil.getMethodLibrary(entry.getKey()) == library) {
					entry.getValue().doDispose();
					iterator.remove();
				}
			}
		}
	}
	
	private static synchronized void setupLibraryServiceListener() {
		if(libSvcListener == null) {
			libSvcListener = new LibraryServiceListener() {
				
				@Override
				public void libraryClosed(MethodLibrary library) {
					disposeDiagramManagers(library);
				}
				
				@Override
				public void libraryReopened(MethodLibrary library) {
					disposeDiagramManagers(library);
				}
			};
			LibraryService.getInstance().addListener(libSvcListener);
		}
	}
	
	/**
	 * Gets a instance of DiagramManager for the specified process. Caller must
	 * release the returned DiagramManager instance when it's no longer needed
	 * using {@link #removeConsumer(Object)}.
	 * 
	 * @param process
	 * @return
	 */
	public static final DiagramManager getInstance(Process process, Object consumer) {
		if(DEBUG) {
			if(process.eIsProxy()) {
				System.err.println("Process is a proxy: " + process); //$NON-NLS-1$
			}
		}
		assert process != null && consumer != null && !process.eIsProxy();
		setupLibraryServiceListener();
		DiagramManager mgr = (DiagramManager) processToDiagramManagerMap.get(process);
		if(mgr != null && mgr.isDisposed()) {
			processToDiagramManagerMap.remove(process);
			mgr = null;
		}
		if(mgr == null) {
			synchronized(processToDiagramManagerMap) {
				mgr = (DiagramManager) processToDiagramManagerMap.get(process);
				if(mgr == null) {
					mgr = new DiagramManager(process);
					processToDiagramManagerMap.put(process, mgr);
					if(!fileSynchronizer.isInstalled()) {
						fileSynchronizer.install();
					}
				}
			}			
		}
		mgr.addConsumer(consumer);
		return mgr;
	}
	
	/**
	 * Checks if an diagram manager for the given process already exists.
	 * 
	 * @param process
	 * @return
	 */
	public static final boolean hasDiagramManager(Process process) {
		return processToDiagramManagerMap.containsKey(process);
	}
	
	public static boolean disposeDiagramManager(Process process) {
		synchronized (processToDiagramManagerMap) {
			DiagramManager mgr = processToDiagramManagerMap.get(process);
			if(mgr != null) {
				mgr.doDispose();
				processToDiagramManagerMap.remove(process);
				return true;
			}
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<DiagramManager> getDiagramManagers() {
		ArrayList<DiagramManager> mgrs;
		synchronized(processToDiagramManagerMap) {
			mgrs = new ArrayList<DiagramManager>(processToDiagramManagerMap.values());
		}
		return mgrs.isEmpty() ? Collections.EMPTY_LIST : mgrs;
	}
	
	private class GMFResourceEx extends GMFResource implements IFailSafeSavable {

		private FailSafePersistenceHelper failSafePersistenceHelper;

		public GMFResourceEx(URI uri) {
			super(uri);
		}
		
		@Override
		protected XMLHelper createXMLHelper() {					
			return new GMFHelper(this) {
				
				protected URI getHREF(Resource otherResource, EObject obj) {
					if(otherResource instanceof ILibraryResource) {
						return ((ILibraryResource)otherResource).getProxyURI(obj);
					}
					return super.getHREF(otherResource, obj);
				}
				
			};
		}
		
		public void updateInfo() {
			IFile file = WorkspaceSynchronizer.getFile(this);
			if(DiagramManager.this.resourceIsNew) {
				DiagramManager.this.resourceIsNew = false;
				fileSynchronizer.monitor(file);
			}
			fileSynchronizer.updateModificationStamp(file);
			try {
				file.refreshLocal(IResource.DEPTH_ZERO, null);
			} catch (CoreException e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			}
		}
		
		@Override
		public void save(Map options) throws IOException {
			super.save(options);
			if(!hasTempURI()) {
				updateInfo();
			}
		}
		
		private Adapter superCreateModificationAdapter() {
			return super.createModificationTrackingAdapter();
		}
		
		@Override
		protected Adapter createModificationTrackingAdapter() {
			return new AdapterImpl() {
				private Adapter delegate = superCreateModificationAdapter();
				
				@Override
				public void notifyChanged(Notification msg) {
					delegate.notifyChanged(msg);
					notifyDiagramChangeListeners(msg);
				}
			};
		}
		
		/**
		 * Don't notify DiagramModificationListener about resource modification event but rather
		 * let {@link #notifyDiagramChangeListeners(Notification) handle it.
		 */
		@Override
		public void eNotify(Notification notification) {
			if(isLoaded() && notification.getNotifier() instanceof Resource 
					&& notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_MODIFIED
					&& notification.getEventType() == Notification.SET) {
				if (eAdapters != null && eDeliver())
				{
					int size = eAdapters.size();
					if (size > 0)
					{
						Adapter [] adapters = (Adapter [])eAdapters.data();
						for (int i = 0; i < size; ++i)
						{
							Adapter adapter = adapters[i];
							if(!(adapter instanceof AccessibleDiagramModificationListener)) {
								TransactionChangeRecorder recorder = null;
								ValidateEditSupport support = null;
								if (adapter instanceof TransactionChangeRecorder) {
									recorder = (TransactionChangeRecorder) adapter;
									support = recorder.getValidateEditSupport();
									if (support != null) {
										recorder.setValidateEditSupport(null);
									}
								}
								adapter.notifyChanged(notification);
								if (support != null) {
									recorder.setValidateEditSupport(support);
								}
							}
						}
					}
				}
			}
			else {
				super.eNotify(notification);
			}
		}

		protected void notifyDiagramChangeListeners(Notification msg) {
			BasicEList<Adapter> eAdapters = eBasicAdapters();
			if (eAdapters != null && eDeliver())
			{
				int size = eAdapters.size();
				if (size > 0)
				{
					Adapter [] adapters = (Adapter [])eAdapters.data();
					for (int i = 0; i < size; ++i)
					{
						Adapter adapter = adapters[i];
						if(adapter instanceof AccessibleDiagramModificationListener) {
							adapter.notifyChanged(msg);
						}
					}
				}
			}
		}
		
		private FailSafePersistenceHelper getFailSafePersistenceHelper() {
			if(failSafePersistenceHelper == null) {
				// id format: <process_guid>diagram
				String id = DiagramManager.this.process.getGuid() + DIAGRAM_FILENAME_WITHOUT_EXTENSION;
				failSafePersistenceHelper = new FailSafePersistenceHelper(this, id);
				failSafePersistenceHelper.setCommitEmptyResource(true);
			}
			return failSafePersistenceHelper;
		}

		public void commit() {
			getFailSafePersistenceHelper().commit();
		}

		public void deleteBackup() {
			getFailSafePersistenceHelper().deleteBackup();
		}

		public boolean hasTempURI() {
			return getFailSafePersistenceHelper().hasTempURI();
		}

		public boolean restore() {
			return getFailSafePersistenceHelper().restore();
		}

		public void setTxID(String txID) {
			getFailSafePersistenceHelper().setTempURI(txID);
		}

		public void txFinished(boolean successful) {
			getFailSafePersistenceHelper().txFinished(successful);
			if(successful) {
				updateInfo();
			}
		}
		
		@Override
		protected void doUnload() {
			super.doUnload();
			
		    Iterator<EObject> allContents = getAllProperContents(unloadingContents);
		    List<EObject> list = new ArrayList<EObject>();
			while (allContents.hasNext()) {
				list.add(allContents.next());
			}
			for (EObject object : list) {
				EcoreUtil.remove(object);
			}
		}
		
	}
	
	private class GMFExtendedResourceSet extends ExtendedResourceSet {
		@Override
		protected int getURIType(URI uri) {
			if(MultiFileURIConverter.SCHEME.equals(uri.scheme())) {
				return URI_TYPE_EXTERNAL;
			}
			if(DIAGRAM_FILENAME.equals(uri.lastSegment())) {
				return URI_TYPE_LOCAL;
			}
			return URI_TYPE_EXTERNAL;
 		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Resource createResource(URI uri) {
			XMIResource resource = new GMFResourceEx(uri);

			resource.getDefaultLoadOptions().putAll(GMFResourceFactory.getDefaultLoadOptions());
			resource.getDefaultSaveOptions().putAll(GMFResourceFactory.getDefaultSaveOptions());

			if (!resource.getEncoding().equals(EMFCoreConstants.XMI_ENCODING))
				resource.setEncoding(EMFCoreConstants.XMI_ENCODING);

			getResources().add(resource);
			
			return resource;
		}
		
	}

	/**
	 * Reference count to this diagram manager
	 */
	private Process process;
	
	private Resource resource;
	
	private InternalTransactionalEditingDomain editingDomain;

	private IProgressMonitor monitor;

	private boolean resourceIsNew;
	
	private List<IResourceChangeListener> resourceChangeListeners = new UniqueEList<IResourceChangeListener>();

	private ExtendedResourceSet resourceSet;

	private HashMap<Activity, Map<String, Diagram>> activityToSavedDiagramMap; 
	
	protected UniqueEList<Object> consumers = new UniqueEList<Object>();
	
	private DiagramManager(Process process) {
		this.process = process;
	}
	
	public Process getProcess() {
		return process;
	}
	
	public void addResourceChangeListener(IResourceChangeListener listener) {
		if(!resourceChangeListeners.contains(listener)) {
			resourceChangeListeners.add(listener);
		}
	}
	
	public void removeResourceChangeListener(IResourceChangeListener listener) {
		resourceChangeListeners.remove(listener);
	}
	
	public synchronized boolean addConsumer(Object consumer) {
		return consumers.add(consumer);
	}
	
	/**
	 * Removes the given consumer from the consumer list of this diagram manager. Unload
	 * this diagram manager if it does not have any more consumer after this call. 
	 */
	public synchronized boolean removeConsumer(Object consumer) {
		boolean ret = consumers.remove(consumer);
		if(ret && consumers.isEmpty()) {
			synchronized(processToDiagramManagerMap) {
				processToDiagramManagerMap.remove(process);
				if(processToDiagramManagerMap.isEmpty()) {
					fileSynchronizer.uninstall();
				}
			}
			doDispose();
		}
		return ret;
	}
	
	public boolean isNewResource() {
		return resourceIsNew;
	}
	
	public void setResourceIsNew(boolean resourceIsNew) {
		this.resourceIsNew = resourceIsNew;
	}
	
	public InternalTransactionalEditingDomain getEditingDomain() {
		if (editingDomain == null && !isDisposed()) {
			editingDomain = createEditingDomain();
			resourceSet = (ExtendedResourceSet) editingDomain.getResourceSet();
		}
		return editingDomain;
	}
	
	private InternalTransactionalEditingDomain createEditingDomain() {
		GMFExtendedResourceSet resourceSet = new GMFExtendedResourceSet();
		resourceSet.add(process.eResource().getResourceSet());
		return (InternalTransactionalEditingDomain) DiagramEditingDomainFactory.getInstance()
				.createEditingDomain(resourceSet);
	}
	
	private void doDispose() {
		if(resource != null) {
			IFile file = WorkspaceSynchronizer.getFile(resource);
			if(file != null) {
				fileSynchronizer.unmonitor(file);				
			}
			resource = null;
		}

		resourceChangeListeners.clear();
		
		if(editingDomain != null) {
			resourceSet.dispose();
			resourceSet = null;

			editingDomain.dispose();
			editingDomain = null;			
		}	
		
		consumers.clear();
		
		if(activityToSavedDiagramMap != null) {
			activityToSavedDiagramMap.clear();
		}
		
		process = null;
	}
	
	public void dispose() {
		synchronized (processToDiagramManagerMap) {
			processToDiagramManagerMap.remove(process);
		}
		doDispose();
	}
	
	public boolean isDisposed() {
		return process == null;
	}
	
	/**
	 * Gets diagrams of the given type for the specified activity
	 * <code>act</code>
	 * 
	 * @param act
	 * @param type
	 *            must be one of the following constants:
	 *            {@link IDiagramManager#ACTIVITY_DIAGRAM},
	 *            {@link IDiagramManager#ACTIVITY_DETAIL_DIAGRAM},
	 *            {@link IDiagramManager#WORK_PRODUCT_DEPENDENCY_DIAGRAM}
	 * @return
	 * @throws CoreException
	 */
	public List<Diagram> getDiagrams(Activity act, int type) throws CoreException {
		checkActivity(act);
		Resource resource = getResource(false);
		if(resource != null) {
			ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
			for (EObject o : resource.getContents()) {
				if(o instanceof Diagram) {
					Diagram diagram = (Diagram) o;
					String typeStr = getDiagramTypeString(type);
					if(typeStr.equals(diagram.getType()) && isDiagramOf(diagram, act)) {
						EObject model = diagram.getElement();
						if(model instanceof DiagramImpl) {
							// disable creation of UMA graphical data
							//
							((DiagramImpl)model).setGraphicalDataRequired(false);
						}
						diagrams.add(diagram);
					}
				}
			}
			if(!diagrams.isEmpty()) {
				return diagrams;
			}
		}
		return Collections.emptyList();
	}
	
	private static void markInherited(Diagram diagram) {
		List<?> children = diagram.getChildren();
		int size = children.size();		
		for (int i = 0; i < size; i++) {
			View view = (View) children.get(i);
			BridgeHelper.markInherited(view);
		}
		for (Object edge : diagram.getEdges()) {
			BridgeHelper.markInherited((View) edge);
		}
	}
	
	private static String getDiagramTypeString(int diagramType) {
		switch(diagramType) {
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return ADD_kind;
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return AD_kind;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return WPD_kind;
		}
		return null;
	}
	
	public static int getDiagramType(String typeStr) {
		if(AD_kind.equals(typeStr)) {
			return IDiagramManager.ACTIVITY_DIAGRAM;			
		}
		else if(ADD_kind.equals(typeStr)) {
			return IDiagramManager.ACTIVITY_DETAIL_DIAGRAM;			
		}
		else if(WPD_kind.equals(typeStr)) {
			return IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM;
		}
		return -1;
	}
	
	/**
	 * Associates a new diagram with the given activity
	 * 
	 * @param diagram
	 * @param type
	 * @param activity
	 * @throws CoreException 
	 */
	public void associate(Diagram newDiagram, int type, Activity activity) throws CoreException {
		switch(type) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			associateAD(newDiagram, activity);
			return;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			associateADD(newDiagram, activity);
			return;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			associateWPD(newDiagram, activity);	
		}
	}
	
	private void associateWPD(Diagram copy, Activity activity) throws CoreException {
		// TODO - consolidate one method with ADD.
		if(copy.getElement() instanceof WorkProductDependencyDiagram) {
			((WorkProductDependencyDiagram)copy.getElement()).setLinkedElement(activity);
			((DiagramImpl)copy.getElement()).setGraphicalDataRequired(false);
		}
		getResource().getContents().add(copy.getElement());
		getResource().getContents().add(copy);	
	}

	private void associateAD(Diagram copy, Activity act) throws CoreException {
		if(copy.getElement() instanceof EModelElement) {
			BridgeHelper.associate((EModelElement) copy.getElement(), act);
		}
		getResource().getContents().add(copy.getElement());
		getResource().getContents().add(copy);
	}
	
	private void associateADD(Diagram copy, Activity activity) throws CoreException {
		if(copy.getElement() instanceof ActivityDetailDiagram) {
			((ActivityDetailDiagram)copy.getElement()).setLinkedElement(activity);
			((DiagramImpl)copy.getElement()).setGraphicalDataRequired(false);
		}
		getResource().getContents().add(copy.getElement());
		getResource().getContents().add(copy);		
	}
	
	private Diagram copyDiagram(Diagram diagram, Activity targetActivity) {
		Diagram copy = DiagramHelper.copyDiagram(getEditingDomain(), diagram);
		DiagramHelper.reassociate(targetActivity, copy);
		return copy;
	}

	public Diagram createDiagram(final Activity act, final int type, final PreferencesHint hint) throws CoreException {
		checkActivity(act);
		// check if this activity contributes/extends other activity and try
		// copy the existing diagram from the base
		//
		if (ProcessUtil.isExtendingOrLocallyContributing(act)) {
			Activity baseAct = (Activity) act;
			DiagramManager mgr = null;
			List<?> baseDiagrams = null;
			try {
				do {
					baseAct = (Activity) baseAct.getVariabilityBasedOnElement();
					if(mgr != null) {
						mgr.removeConsumer(this);
					}
					mgr = DiagramManager.getInstance(TngUtil.getOwningProcess(baseAct), this);
					baseDiagrams = mgr.getDiagrams(baseAct, type);
				} while(baseDiagrams.isEmpty() && ProcessUtil.isExtendingOrLocallyContributing(baseAct));
				if(!baseDiagrams.isEmpty()) {
					Diagram baseDiagram = (Diagram) baseDiagrams.get(0);
					if (baseDiagram != null) {
						final Diagram copy = copyDiagram(baseDiagram, act);
						TxUtil.runInTransaction(getEditingDomain(), new Runnable() {

							public void run() {
								markInherited(copy);
								try {
									associate(copy, type, act);
								} catch (CoreException e) {
									throw new WrappedException(e);
								}
							}
							
						});
						return copy;
					}
				}
			} catch(Exception e){
				CommonPlugin.getDefault().getLogger().logError("Error in retrieving base diagram: ", e); //$NON-NLS-1$
			}
			finally {
				if(mgr != null) {
					mgr.removeConsumer(this);
				}
			}
		}
				
		
		final Diagram[] resultHolder = new Diagram[1];
		try {
			TxUtil.runInTransaction(getEditingDomain(), new Runnable() {

				public void run() {
					try {
						resultHolder[0] = doCreateDiagram(act, type, hint);
					} catch (CoreException e) {
						throw new WrappedException(e);
					}
				}
				
			});
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
			if(DEBUG) {
				e.printStackTrace();
			}
		}
		return resultHolder[0];
	}
	
	private Diagram doCreateDiagram(Activity act, int type, PreferencesHint hint) throws CoreException {
		switch(type) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return createAD(act, hint);
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return createADD(act, hint);
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return createWPDD(act, hint);
		}
		return null;
	}

	private Diagram createWPDD(Activity act, PreferencesHint hint)
			throws CoreException {
		// ProcessPackage model = (ProcessPackage) act.eContainer();
		WorkProductDependencyDiagram model = ModelFactory.eINSTANCE
				.createWorkProductDependencyDiagram();
		if (model instanceof DiagramImpl) {
			// disable creation of UMA graphical data
			//
			((DiagramImpl) model).setGraphicalDataRequired(false);
		}
		model.setNew(true);
		Diagram diagram = ViewService
				.createDiagram(
						model,
						getDiagramTypeString(IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM),
						hint);
		if (diagram != null) {
			diagram.setName(act.getName());
			model.setLinkedElement(act);
			getResource().getContents().add(model);
			getResource().getContents().add(diagram);

			// add ResourceDomainLink to the method library resource set
			//
			// EcoreUtil.getAdapter(getResource().getResourceSet().eAdapters(),
			// ResourceDo)
		}
		return diagram;
	}

	private Diagram createADD(Activity act, PreferencesHint hint)
			throws CoreException {
		ActivityDetailDiagram model = ModelFactory.eINSTANCE
				.createActivityDetailDiagram();
		if (model instanceof DiagramImpl) {
			// disable creation of UMA graphical data
			//
			((DiagramImpl) model).setGraphicalDataRequired(false);
		}
		model.setNew(true);
		Diagram diagram = ViewService.createDiagram(model,
				getDiagramTypeString(IDiagramManager.ACTIVITY_DETAIL_DIAGRAM),
				hint);
		if (diagram != null) {
			model.setLinkedElement(act);
			diagram.setName(act.getName());
			getResource().getContents().add(model);
			getResource().getContents().add(diagram);
		}
		return diagram;
	}

	private Diagram createAD(Activity act, PreferencesHint hint) throws CoreException {
		org.eclipse.uml2.uml.Activity model = UMLFactory.eINSTANCE
				.createActivity();
		Diagram diagram = ViewService.createDiagram(model,
				getDiagramTypeString(IDiagramManager.ACTIVITY_DIAGRAM), hint);
		if (diagram != null) {
			BridgeHelper.associate(model, act);
			diagram.setName(act.getName());
			model.setName(act.getName());
			getResource().getContents().add(model);
			getResource().getContents().add(diagram);
		}
		return diagram;
	}

	private IProgressMonitor getMonitor() {
		if(monitor == null) {
			monitor = new NullProgressMonitor();
		}
		return monitor;
	}
	
	public static String getDiagramFilePath(Process process) {
		try {
			ProcessComponent procComp = (ProcessComponent) process.eContainer();
			if(procComp == null) {
				return null;
			}
			if(UmaUtil.hasDirectResource(procComp)) {
				// existing process
				//
				return new File(new File(FileManager.toFileString(process.eResource().getURI())).getParentFile(), DIAGRAM_FILENAME).getCanonicalPath();
			}
			else {
				// new process that has not been saved yet
				//
				URI uri = MultiFileSaveUtil.createURI(procComp, procComp.eResource().getResourceSet());
				return new File(new File(uri.toFileString()).getParentFile(),
								DIAGRAM_FILENAME).getCanonicalPath();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public void reload() throws IOException {
		if(resource == null) {
			return;
		}
		resource.unload();
		resource.load(Collections.EMPTY_MAP);
		IFile file = WorkspaceSynchronizer.getFile(resource);
		fileSynchronizer.updateModificationStamp(file);
		notifyReloaded();
	}
	
	public Resource getResource() throws CoreException {
		if(resource == null) {
			resource = getResource(true);
		}
		return resource;
	}	
	
	private static URI createDiagramResourceURI(String path) {
		IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
		return URI.createPlatformResourceURI(diagramFile
				.getFullPath().toString());
	}
	
	private static Resource loadDiagramResource(IFile fFile,
			EditingDomain domain, Map loadOptions, IProgressMonitor monitor)
			throws CoreException, IOException {
		fFile.refreshLocal(IResource.DEPTH_ZERO, monitor);
		URI uri = URI.createPlatformResourceURI(fFile.getFullPath()
            .toString(), true);
		
		Resource resource = domain.getResourceSet().getResource(uri, false);
		
		if (resource == null) {
			resource = domain.getResourceSet().createResource(uri);
		}
		
		if (!resource.isLoaded()) {
			Map loadingOptions = new HashMap(GMFResourceFactory.getDefaultLoadOptions());
			
            // propogate passed in options to the defaults
            Iterator iter = loadOptions.keySet().iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                loadingOptions.put(key, loadOptions.get(key));
            }
            
            try {
            	resource.load(loadingOptions);
            } catch (IOException e) {
            	resource.unload();
            	throw e;
            }
		}
					
		return resource;

	}
	
	private Resource getResource(boolean create) throws CoreException {
		if(resource == null) {
			String path = getDiagramFilePath(process);	
			if(path != null) {
				IResource wsRes = FileManager.getResourceForLocation(path);
				if(wsRes instanceof IFile) {
					IFile file = (IFile) wsRes;
					try {						
						Diagram diagram = DiagramIOUtil.load(getEditingDomain(), file, true, getMonitor());					
						resource = diagram.eResource();
					}
					catch(CoreException e) {
						// handled exception that is thrown if resource does not have any diagram
						//
						if(org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.l10n.EditorMessages.Diagram_NO_DIAGRAM_IN_RESOURCE
								.equals(e.getMessage())) {
							try {
								resource = loadDiagramResource(file, getEditingDomain(), Collections.EMPTY_MAP, getMonitor());
							} catch (IOException e1) {
								if(DEBUG) {
									DiagramCorePlugin.getDefault().getLogger().logError(e);
								}
								throw new CoreException(new Status(IStatus.ERROR, DiagramCorePlugin.PLUGIN_ID, e1.getMessage(), e1));
							}
						}
						else {
							throw e;
						}
					}
					fileSynchronizer.monitor(file);
				}
				else if(create) {
					resource = getEditingDomain().getResourceSet().createResource(
							createDiagramResourceURI(path));
					resourceIsNew = true;
					
					try {
						TxUtil.runInTransaction(getEditingDomain(), new Runnable() {

							public void run() {
								resource.getContents().clear();
							}
							
						});
					} catch (Exception e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
		else if(!resource.isLoaded()) {
			try {
				resource.load(Collections.EMPTY_MAP);
				notifyReloaded();
			} catch (IOException e) {
				CommonPlugin.getDefault().getLogger().logError(e);
				if(DEBUG) {
					e.printStackTrace();
				}
			}
		}
		return resource;
	}

	private void notifyReloaded() {
		for (Iterator iter = resourceChangeListeners.iterator(); iter.hasNext();) {
			IResourceChangeListener listener = (IResourceChangeListener) iter.next();
			try {
				listener.resourceChanged(new ResourceChangeEvent(this, IResourceChangeEvent.POST_CHANGE, 0, null));
			}
			catch(Exception e) {
				CommonPlugin.getDefault().getLogger().logError(e);
				if(DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}

	private void checkActivity(Activity act) {		
		Process proc = TngUtil.getOwningProcess(act);
		if(proc != process) {
			String msg = "The specified activity does not belong to the process of this diagram manager.";
			String str = process == null ? "null" : TngUtil.getLabelWithPath(process) + ", " + process.getGuid();
			msg += "\nProcess of this diagram manager: " + str;
			str = act == null ? "null" : TngUtil.getLabelWithPath(act) + ", " + act.getGuid();
			msg += "\nActivity of the diagram        : " + str;
			str = proc == null ? "null" : TngUtil.getLabelWithPath(proc) + ", " + proc.getGuid();
			msg += "\nActivity's process             : " + str;
			throw new IllegalArgumentException(msg); //$NON-NLS-1$
		}
	}	
	
	private static boolean isDiagramOf(Diagram diagram, Activity act) {
		if(AD_kind.equals(diagram.getType())) {
			return BridgeHelper.getMethodElementFromAnnotation((EModelElement) diagram.getElement(), act.eResource().getResourceSet()) == act;
		}
		else if(ADD_kind.equals(diagram.getType())) {
			EObject model = diagram.getElement();
			return model instanceof ActivityDetailDiagram && ((ActivityDetailDiagram)model).getLinkedElement() == act;
		}
		else if(WPD_kind.equals(diagram.getType())) {
			EObject model = diagram.getElement();
			return model instanceof WorkProductDependencyDiagram && ((WorkProductDependencyDiagram)model).getLinkedElement() == act;
		}
		return false;
	}
	
	/**
	 * Checks whether the given resource has been changed on the
	 * local file system by comparing the actual time stamp with the
	 * cached one. If the resource has been changed, a <code>CoreException</code>
	 * is thrown.
	 *
	 * @param cachedModificationStamp the cached modification stamp
	 * @param resource the resource to check
	 * @throws org.eclipse.core.runtime.CoreException if resource has been changed on the file system
	 */
	public static void checkSynchronizationState(Resource resource) throws CoreException {
		if(!isSynchronized(resource)) {
			Status status= new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, IResourceStatus.OUT_OF_SYNC_LOCAL, EditorMessages.FileDocumentProvider_error_out_of_sync, null);
			throw new CoreException(status);
		}
	}
	
	public static boolean isSynchronized(Resource resource) {
		if(resource == null) {
			return false;
		}
		IFile file = WorkspaceSynchronizer.getFile(resource);
		if(file != null) {
			// don't refresh to prevent resource from automatically reloaded
			//
//			file.refreshLocal(IResource.DEPTH_ZERO, monitor);
			FileInfo info = fileSynchronizer.getFileInfo(file);
			if(info != null) {
				return info.fModificationStamp == FileSynchronizer.computeModificationStamp(file);
			}
		}
		return true;
	}
	
	private Map<Activity, Map<String, Diagram>> getActivityToSavedDiagramsMap() {
		if(activityToSavedDiagramMap == null) {
			activityToSavedDiagramMap = new HashMap<Activity, Map<String, Diagram>>();
		}
		return activityToSavedDiagramMap;
	}
	
	private Diagram getDiagramBackup(Activity act, String type) {
		if(activityToSavedDiagramMap != null) {
			Map<String, Diagram> typeToDiagamMap = activityToSavedDiagramMap.get(act);
			if(typeToDiagamMap != null) {
				return (Diagram) typeToDiagamMap.get(type);
			}
		}
		return null;
	}
	
	public void replaceTemporarily(final Activity act, final Diagram diagram) {
		try {
			new AbstractEMFOperation(getEditingDomain(), StringStatics.BLANK) {

				@Override
				protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
					try {
						List diagrams = getDiagrams(act, getDiagramType(diagram.getType()));
						if(!diagrams.isEmpty()) {
							Diagram savedDiagram = (Diagram) diagrams.get(0);
							Map<String, Diagram> typeToDiagamMap = getActivityToSavedDiagramsMap().get(act);	
							if(typeToDiagamMap == null) {
								typeToDiagamMap = new HashMap<String, Diagram>();
								getActivityToSavedDiagramsMap().put(act, typeToDiagamMap);
							}
							if(typeToDiagamMap.get(diagram.getType()) == null) {
								typeToDiagamMap.put(diagram.getType(), savedDiagram);
							}
							((InternalEObject) diagram).eSetProxyURI(null);
							for (Iterator iter = diagram.eAllContents(); iter
							.hasNext(); ((InternalEObject) iter.next()).eSetProxyURI(null));
							((InternalEObject) diagram.getElement()).eSetProxyURI(null);
							for (Iterator iter = diagram.getElement().eAllContents(); iter
							.hasNext(); ((InternalEObject) iter.next()).eSetProxyURI(null));							
							resource.getContents().set(resource.getContents().indexOf(savedDiagram), diagram);
							resource.getContents().set(resource.getContents().indexOf(savedDiagram.getElement()), diagram.getElement());				
						}
						else {
							resource.getContents().add(diagram.getElement());
							resource.getContents().add(diagram);
						}
					} catch (CoreException e) {
						CommonPlugin.getDefault().getLogger().logError(e);			
					}
					return Status.OK_STATUS;
				}
				
			}.execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e1) {
			CommonPlugin.getDefault().getLogger().logError(e1);
		}
	}
	
	private Diagram getSavedDiagram(Activity act, Diagram diagram) {
		Diagram savedDiagram = getDiagramBackup(act, diagram.getType());
//		if(savedDiagram == null) {
//			// try to load diagram from file
//			//
//			IFile file = WorkspaceSynchronizer.getFile(resource);
//			if(file != null) {
//				TransactionalEditingDomain domain = createEditingDomain();
//				try {
//					Diagram d = DiagramIOUtil.load(domain, file, true, getMonitor());
//					if(d != null) {
//						int index = resource.getContents().indexOf(diagram);
//						savedDiagram = (Diagram) d.eResource().getContents().get(index);
//					}
//				} catch (CoreException e) {
//					DiagramCorePlugin.getDefault().getLogger().logError(e);
//				} finally {
//					if(domain != null) {
//						domain.dispose();
//					}
//				}
//			}
//		}
		return savedDiagram;
	}

	public synchronized boolean reverseToSaved(final Activity act, final Diagram diagram, PreferencesHint hint) {
		final Diagram savedDiagram = getSavedDiagram(act, diagram);
		try {
			new AbstractEMFOperation(getEditingDomain(), StringStatics.BLANK) {

				@Override
				protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
					if(savedDiagram != null) {
						// restore diagram to its backup
						//
						resource.getContents().set(resource.getContents().indexOf(diagram), savedDiagram);
						resource.getContents().set(resource.getContents().indexOf(diagram.getElement()), savedDiagram.getElement());
						removeDiagramBackup(act, diagram.getType());
					}
					else {
						// diagram is newly created, remove it from resource
						//
						resource.getContents().remove(diagram.getElement());
						resource.getContents().remove(diagram);
					}
					
					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
							.getPersisterFor(act.eResource())
							.getFailSafePersister();
					try {
						persister.save(resource);
						persister.commit();
					} catch (Exception e) {
						DiagramCorePlugin.getDefault().getLogger().logError(e);
						try {
							persister.rollback();
						}
						catch(Exception ex) {}
						throw new ExecutionException(e.getMessage(), e);
					}
					return Status.OK_STATUS;
				}
				
			}.execute(new NullProgressMonitor(), null);
			return true;
		} catch (ExecutionException e1) {
			CommonPlugin.getDefault().getLogger().logError(e1);
		}
		return false;		
	}

	/**
	 * Updates URI of diagram resource after the URI of process resource has been changed.
	 */
	public void updateResourceURI() {
		if(resource == null) {
			return;
		}
		File folder = new File(FileManager.toFileString(resource.getURI())).getParentFile();
		File procFolder = new File(process.eResource().getURI().toFileString()).getParentFile();
		if(!folder.equals(procFolder)) {
			String path = getDiagramFilePath(process);
			if(path != null && new File(path).exists()) {
				resource.setURI(createDiagramResourceURI(path));
			}
		}
	}

	public void backupDiagram(Activity activity, Diagram diagram) {
		Map<String, Diagram> typeToDiagamMap = getActivityToSavedDiagramsMap().get(activity);	
		if(typeToDiagamMap == null) {
			typeToDiagamMap = new HashMap<String, Diagram>();
			getActivityToSavedDiagramsMap().put(activity, typeToDiagamMap);
		}
		typeToDiagamMap.put(diagram.getType(), DiagramHelper.copyDiagram(getEditingDomain(), diagram));
	}

	public Diagram removeDiagramBackup(Activity act, String type) {
		if(activityToSavedDiagramMap != null) {
			Map<String, Diagram> typeToDiagamMap = activityToSavedDiagramMap.get(act);
			if(typeToDiagamMap != null) {
				return (Diagram) typeToDiagamMap.remove(type);
			}
		}
		return null;
	}
}