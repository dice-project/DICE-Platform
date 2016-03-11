//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.xmi.internal.migration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EContentsEList.FeatureIterator;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.LinkInfo;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.library.xmi.XMILibraryPlugin;
import org.eclipse.epf.library.xmi.XMILibraryResources;
import org.eclipse.epf.persistence.MethodLibraryPersister;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileXMISaveImpl;
import org.eclipse.epf.persistence.migration.MigrationResourceHandler;
import org.eclipse.epf.persistence.migration.MigratorImpl;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Property;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * @author Phong Nguyen Le - Jun 12, 2006
 * @since 1.0
 */
public class Migrator102 extends MigratorImpl {
	private static final boolean DEBUG = XMILibraryPlugin.getDefault()
			.isDebugging();

	private static void updateStatus(IProgressMonitor monitor, String msg) {
		if (monitor != null) {
			monitor.subTask(msg);
			monitor.worked(1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				//
			}
		} else {
			System.out.println(msg);
		}
	}

	private Collection proxiesToRemove = new ArrayList();

	private Map proxyToFileMap = new HashMap();

	private HashMap proxyToFileWithLoadErrorMap = new HashMap();

	private ArrayList notFoundProxies = new ArrayList();

	private ArrayList proxiesWithUnnormalizedURI = new ArrayList();

	private MethodLibrary lib;

	private MigrationResourceHandler resourceHandler = new MigrationResourceHandler() {

		protected boolean handleUnknownFeature(EObject owner,
				EStructuralFeature feature, Object value) {
			// Order graph nodes of task descriptors in ADD based on their order
			// in the task descriptor list of the role descriptor
			// old feature: RoleDescriptor.performsAsOwner
			//
			if(owner instanceof RoleDescriptor
					&& "performsAsOwner".equals(feature.getName()) //$NON-NLS-1$
					&& value != null)
			{
				ArrayList GUIDs = new ArrayList();
				StringTokenizer tokens = new StringTokenizer((String) value);
				while(tokens.hasMoreTokens()) {
					GUIDs.add(tokens.nextToken());
				}
				if(GUIDs.size() > 1) {
					Activity act = ((RoleDescriptor)owner).getSuperActivities();
					Diagram add = GraphicalDataManager.getInstance().getUMADiagram(act, GraphicalDataHelper.ACTIVITY_DETAIL_DIAGRAM, false);
					if(add != null) {
						Map tdGuidToGraphNodeMap = new HashMap();
						int size = add.getContained().size();
						for (int i = 0; i < size; i++) {
							Object element = add.getContained().get(i);
							if(element instanceof GraphNode) {
								GraphNode graphNode = ((GraphNode)element);
								SemanticModelBridge bridge = graphNode.getSemanticModel();
								if (bridge instanceof UMASemanticModelBridge) {
									MethodElement me = ((UMASemanticModelBridge) bridge).getElement();								
									if(me instanceof TaskDescriptor) {
										List list = graphNode.getList(UmaPackage.GRAPH_NODE__PROPERTY);
										Property property = GraphicalDataHelper.getPropertyByKey(list,
												GraphicalDataHelper.PROP_WORK_PRODUCT_COMPOSITE_TYPE);
										if (property == null) {
											// this is not a GraphNode for WorkProductComposite
											// it must be a GraphNode for a TaskDescriptor
											//
											tdGuidToGraphNodeMap.put(me.getGuid(), graphNode);
										}
									}
								}
							}
						}
						
						// reorder the graph nodes to match order of their linked task descriptors
						//
						ArrayList graphNodes = new ArrayList();
						for(int i = 0; i < GUIDs.size(); i++) {
							Object graphNode = tdGuidToGraphNodeMap.get(GUIDs.get(i));
							if(graphNode != null) {
								graphNodes.add(graphNode);
							}
						}
						add.getContained().removeAll(graphNodes);
						add.getContained().addAll(graphNodes);
					}					
				}
			}
			return true;
		}

	};

	private static final String FILE_PATH = XMILibraryResources.filePath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.persistence.migration.IMigrator#migrate(java.lang.String,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void migrate(String libPath, IProgressMonitor monitor)
		throws Exception {
		migrate(libPath, monitor, null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.migration.IMigrator#migrate(java.lang.String, org.eclipse.core.runtime.IProgressMonitor, org.eclipse.epf.persistence.migration.UpgradeCallerInfo)
	 */
	public void migrate(String libPath, IProgressMonitor monitor, UpgradeCallerInfo info)
			throws Exception {
		initMigrate();
		
		File libFile = new File(libPath);

		boolean toVerify = true;
		if (info != null && info.getIsExportedPluginLib()) {
			toVerify = false;
		}
		
		ResourceUtil.open(libFile.getParent(), monitor);

		MultiFileResourceSetImpl resourceSet = null;
		try {
			// set 1.0.2 default values so data can be correctly loaded
			//
			setOldDefaultValues();

			// load the library
			//
			updateStatus(monitor, PersistenceResources.loadLibraryTask_name);

			if (toVerify) {
				resourceSet = new MultiFileResourceSetImpl(false);
			} else {
				resourceSet = PersistenceUtil.getImportPluginResourceSet();
			}
						
			resourceSet.getLoadOptions().put(
					XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			resourceSet.getLoadOptions().put(
					XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
			lib = resourceSet.loadLibrary(libPath);
			
			removeProcessContributions(monitor);

			// verify the library
			//
			// TODO: uncomment after externalize the text
			// updateStatus(monitor, "Verifying...");

			if (toVerify) {
				Display dis = Display.getDefault();
				if (dis == null || dis.getThread() == Thread.currentThread()) {
					verify();
				} else {
					dis.syncExec(new Runnable() {
						public void run() {
							verify();
						}
					});
				}
				removeUnresolvedReferences(monitor);
			}
						
			if (this instanceof Migrator102_103) {
				LinkInfo.setLibrary(lib);
			}
			// load all elements in memory
			//
			updateStatus(monitor, PersistenceResources.loadResourcesTask_name);
			for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
				EObject element = (EObject) iter.next();
				if (element instanceof MethodElement) {
					try {
						for (Iterator iterator = element.eCrossReferences()
								.iterator(); iterator.hasNext();) {
							iterator.next();
						}
					} catch (Exception e) {
						CommonPlugin.INSTANCE.log(e);
						if (DEBUG) {
							System.err
									.println("Error iterate thru cross references of element: " + element); //$NON-NLS-1$
						}
					}
					update((MethodElement) element, monitor);
				}
			}

			
			handleConverToSynFree(monitor, info, lib);
			
			removeOldDefaultValues();

			// check modified resources for writable before saving them
			//
			Display dis = Display.getDefault();
			if (dis == null || dis.getThread() == Thread.currentThread()) {
				checkModifiedResources();
			} else {
				dis.syncExec(new Runnable() {
					public void run() {
						checkModifiedResources();
					}
				});
			}

			// save all files
			//
			updateStatus(monitor, PersistenceResources.saveLibraryTask_name);
			Map saveOptions = resourceSet.getDefaultSaveOptions();
			if (toVerify) {
				saveOptions.put(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES,
					Boolean.TRUE);
			}
			resourceSet.save(saveOptions, true);

			updateStatus(monitor,
					PersistenceResources.refreshLibraryFilesTask_name);
			ResourceUtil.refreshResources(lib, monitor);
			migrateDiagram(monitor);
		} finally {
			if (this instanceof Migrator102_103) {
				LinkInfo.setLibrary(null);
			}
			if (resourceSet != null) {
				resourceSet.reset();
				resourceSet = null;
			}
		}
	}

	/**
	 * Removes process contributions from all plugins
	 */
	private void removeProcessContributions(IProgressMonitor monitor) {
		for (Iterator iter = lib.getMethodPlugins().iterator(); iter.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			MethodPackage pkg = UmaUtil.findMethodPackage(plugin,
					ModelStructure.DEFAULT.processContributionPath);
			if (pkg != null) {
				for (Iterator iterator = new ArrayList(pkg.getChildPackages())
						.iterator(); iterator.hasNext();) {
					MethodPackage childPkg = (MethodPackage) iterator.next();
					Resource resource = ((InternalEObject) childPkg)
							.eDirectResource();
					if (resource != null) {
						ResourceSet resourceSet = resource.getResourceSet();
						try {
							MethodLibraryPersister.INSTANCE.delete(pkg);
						} catch (Exception e) {
							if (DEBUG) {
								e.printStackTrace();
							}
						}
						if (resourceSet != null) {
							resourceSet.getResources().remove(resource);
						}
					}
				}
				pkg.getChildPackages().clear();
			}
		}
	}

	/**
	 * 
	 */
	private void checkModifiedResources() {
		do {
			ResourceSet resourceSet = lib.eResource().getResourceSet();
			ArrayList readOnlyResources = new ArrayList();
			String pluginId = XMILibraryPlugin.getDefault().getId();
			MultiStatus status = new MultiStatus(pluginId, 0,
					XMILibraryResources.cannotWriteToFiles, null);
			for (Iterator iter = resourceSet.getResources().iterator(); iter
					.hasNext();) {
				Resource resource = (Resource) iter.next();
				File file = new File(resource.getURI().toFileString());
				if (file.exists() && !file.canWrite()) {
					readOnlyResources.add(resource);
					status.add(new Status(IStatus.ERROR, pluginId, 0, file
							.toString(), null));
				}
			}
			if (!status.isOK()) {
				String title = XMILibraryResources.readOnlyFiles_title;
				String msg = XMILibraryResources.readOnlyFiles_msg;
				ErrorDialog errDlg = new ErrorDialog(MsgBox.getDefaultShell(),
						title, msg, status, IStatus.OK | IStatus.INFO
								| IStatus.WARNING | IStatus.ERROR) {
					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.jface.dialogs.ErrorDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
					 */
					protected void createButtonsForButtonBar(Composite parent) {
						// create Retry, Cancel and Details buttons
						createButton(parent, IDialogConstants.OK_ID,
								IDialogConstants.RETRY_LABEL, true);

						createButton(parent, IDialogConstants.CANCEL_ID,
								IDialogConstants.CANCEL_LABEL, false);

						createDetailsButton(parent);
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.jface.dialogs.ErrorDialog#open()
					 */
					public int open() {
						showDetailsArea();
						return super.open();
					}

				};
				if (errDlg.open() == IDialogConstants.CANCEL_ID) {
					throw new OperationCanceledException();
				}
			} else {
				return;
			}
		} while (true);
	}

	/**
	 * @param monitor
	 */
	private void removeUnresolvedReferences(IProgressMonitor monitor) {
		if (proxiesToRemove.isEmpty())
			return;
		// TODO: uncomment after externalize the text
		// updateStatus(monitor, "Removing unresolved references");
		HashSet GUIDs = new HashSet();
		for (Iterator iter = proxiesToRemove.iterator(); iter.hasNext();) {
			InternalEObject proxy = (InternalEObject) iter.next();
			GUIDs.add(proxy.eProxyURI().fragment());
			EcoreUtil.remove(proxy);
		}
		for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			for (EContentsEList.FeatureIterator iterator = (FeatureIterator) element
					.eCrossReferences().iterator(); iterator.hasNext();) {
				InternalEObject obj = (InternalEObject) iterator.next();
				if (obj.eIsProxy()
						&& GUIDs.contains(obj.eProxyURI().fragment())) {
					EStructuralFeature feature = iterator.feature();
					if (feature.isChangeable() && !feature.isDerived()) {
						if (feature.isMany()) {
							((List) element.eGet(feature)).remove(obj);
						} else {
							element.eSet(feature, null);
						}
					}
				}
			}
		}
	}

	/**
	 * @param lib
	 */
	private void verify() {
		notFoundProxies.clear();
		proxiesToRemove.clear();
		proxyToFileMap.clear();
		proxyToFileWithLoadErrorMap.clear();
		proxiesWithUnnormalizedURI.clear();

		Collection<EObject> proxies = PersistenceUtil.getProxies(lib);
		if (!proxies.isEmpty()) {
			ResourceSet resourceSet = lib.eResource().getResourceSet();
			URIConverter uriConverter = resourceSet.getURIConverter();
			for (Iterator iter = proxies.iterator(); iter.hasNext();) {
				InternalEObject proxy = (InternalEObject) iter.next();
				URI uri = proxy.eProxyURI();
				URI normalizedURI = uriConverter.normalize(uri);
				if (normalizedURI == null) {
					proxiesWithUnnormalizedURI.add(proxy);
				} else {
					File file = new File(normalizedURI.toFileString());
					if (!file.exists()) {
						proxyToFileMap.put(proxy, file);
					} else {
						try {
							Resource resource = resourceSet.getResource(
									normalizedURI.trimFragment(), true);
							if (resource.getEObject(normalizedURI.fragment()) == null) {
								notFoundProxies.add(proxy);
							}
						} catch (Exception e) {
							String errMsg = e.getMessage() != null ? e
									.getMessage() : e.toString();
							proxyToFileWithLoadErrorMap.put(proxy,
									new Object[] { file, errMsg });
						}
					}
				}
			}
		}

		if (!proxyToFileMap.isEmpty()) {
			// promp user to resolve missing files
			//
			List list = new ArrayList(proxyToFileMap.keySet());
			final String ELEMENT_PATH = XMILibraryResources.elementPath;
			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE
							.getNavigatorView_ComposedAdapterFactory()) {

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
				 */
				public String getText(Object object) {
					File file = (File) proxyToFileMap.get(object);
					return file.getAbsolutePath()
							+ " (" + TngUtil.getLabelWithPath(object) + ')'; //$NON-NLS-1$
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnText(java.lang.Object,
				 *      int)
				 */
				public String getColumnText(Object object, int columnIndex) {
					switch (columnIndex) {
					case 0:
						return proxyToFileMap.get(object).toString();
					case 1:
						return TngUtil.getLabelWithPath(object);
					}
					return null;
				}

			};

			try {
				String msg = XMILibraryResources.promptRemoveReferencesToMissingFiles_msg;
				SelectionDialog dlg = new SelectionDialog(MsgBox
						.getDefaultShell(), list, labelProvider, msg);

				dlg.setTitle(XMILibraryResources.missingFiles_title);
				dlg.setBlockOnOpen(true);
				dlg.setInitialElementSelections(list);
				dlg
						.setColumnProperties(new String[] { FILE_PATH,
								ELEMENT_PATH });
				if (dlg.open() == Dialog.CANCEL) {
					throw new OperationCanceledException();
				}
				Object objs[] = dlg.getResult();
				if (objs == null) {
					throw new OperationCanceledException();
				} else {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						proxiesToRemove.add(iter.next());

					}
				}
			} finally {
				labelProvider.dispose();
			}
		}

		// prompt user to resolve files that can not be loaded
		//
		if (!proxyToFileWithLoadErrorMap.isEmpty()) {
			List list = new ArrayList(proxyToFileWithLoadErrorMap.keySet());
			final String LOAD_ERROR = XMILibraryResources.loadError;
			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE
							.getNavigatorView_ComposedAdapterFactory()) {

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnText(java.lang.Object,
				 *      int)
				 */
				public String getColumnText(Object object, int columnIndex) {
					Object[] arr = (Object[]) proxyToFileMap.get(object);
					if (columnIndex < 2) {
						return arr[columnIndex].toString();
					}
					return null;
				}

			};

			try {
				String msg = XMILibraryResources.promptRemoveReferencesToFilesWithLoadErrors_msg;
				SelectionDialog dlg = new SelectionDialog(MsgBox
						.getDefaultShell(), list, labelProvider, msg);

				dlg.setTitle(XMILibraryResources.filesWithLoadErrors_title);
				dlg.setBlockOnOpen(true);
				dlg.setInitialElementSelections(list);
				dlg.setColumnProperties(new String[] { FILE_PATH, LOAD_ERROR });
				if (dlg.open() == Dialog.CANCEL) {
					throw new OperationCanceledException();
				}
				Object objs[] = dlg.getResult();
				if (objs == null) {
					throw new OperationCanceledException();
				} else {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						proxiesToRemove.add(iter.next());

					}
				}
			} finally {
				labelProvider.dispose();
			}
		}

		ArrayList proxiesToRetain = new ArrayList();
		proxies.addAll(proxyToFileMap.keySet());
		proxies.addAll(proxyToFileWithLoadErrorMap.keySet());
		proxies.removeAll(proxiesToRemove);

		if (proxiesToRetain.isEmpty()) {
			proxiesToRemove.addAll(notFoundProxies);
			proxiesToRemove.addAll(proxiesWithUnnormalizedURI);
		}

		String msg = "Summary of unresolved proxies:"; //$NON-NLS-1$
		msg += "\n  Not found proxies: " + notFoundProxies; //$NON-NLS-1$
		msg += "\n  Proxies with unnormalized URI: " + proxiesWithUnnormalizedURI; //$NON-NLS-1$
		XMILibraryPlugin.getDefault().getLogger().logInfo(msg);
	}

	/**
	 * 
	 */
	private void removeOldDefaultValues() {
		MultiResourceEObject.removeDefaultValue(UmaPackage.eINSTANCE
				.getMethodPlugin_UserChangeable());
	}

	/**
	 * @param e
	 * 
	 */
	private void adjustToNewDefaultValues(MethodElement e) {
		if (e instanceof MethodPlugin) {
			((MultiResourceEObject) e)
					.removeFeatureWithOverridenDefaultValue(UmaPackage.eINSTANCE
							.getMethodPlugin_UserChangeable());
		}
	}

	/**
	 * 
	 */
	private void setOldDefaultValues() {
		MultiResourceEObject.setDefaultValue(UmaPackage.eINSTANCE
				.getMethodPlugin_UserChangeable(), Boolean.FALSE);
	}

	protected void update(MethodElement e, IProgressMonitor monitor)
			throws Exception {
		adjustToNewDefaultValues(e);

		if (e instanceof Activity) {
			Activity act = (Activity) e;
			VariabilityType type = act.getVariabilityType();
			if (type == VariabilityType.CONTRIBUTES) {
				act
						.setVariabilityType(VariabilityType.LOCAL_CONTRIBUTION);
			} else if (type == VariabilityType.REPLACES) {
				act
						.setVariabilityType(VariabilityType.LOCAL_REPLACEMENT);
			}
		}
		if (! (this instanceof Migrator102_103)) {
			MigrationUtil.formatValue(e);
		}
	}
	
	protected void initMigrate() {		
	}
	
	protected void migrateDiagram(IProgressMonitor monitor) throws Exception {	
	}
	
	protected void handleConverToSynFree(IProgressMonitor monitor, UpgradeCallerInfo info, MethodLibrary lib) {
		throw new UnsupportedOperationException();
	}

}
