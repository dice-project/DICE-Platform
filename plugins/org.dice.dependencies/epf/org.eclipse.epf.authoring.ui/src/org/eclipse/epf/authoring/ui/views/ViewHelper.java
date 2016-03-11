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
package org.eclipse.epf.authoring.ui.views;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.preferences.AuthoringUIPreferences;
import org.eclipse.epf.common.service.utils.CommandLineRunUtil;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * A helper class for managing the views and editors in the Authoring
 * perspective.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public final class ViewHelper {

	/**
	 * Prompts the user to save the open library if it has been modified.
	 * 
	 * @return <code>true<code> if the user saved or discarded the change,
	 * 		   <code>false</code> if the user cancelled the action that
	 *         triggered this call.
	 */
	public static boolean promptSave() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryModified()) {
			int ret = MsgBox.prompt(AuthoringUIResources.saveLibraryDialog_title, 
					AuthoringUIResources.saveLibraryDialog_text, 
					SWT.YES | SWT.NO | SWT.CANCEL);
			switch (ret) {
			case SWT.YES:
				try {
					LibraryService.getInstance().saveCurrentMethodLibrary();
				} catch (Exception e) {
					MsgDialog dialog = AuthoringUIPlugin.getDefault()
							.getMsgDialog();
					dialog
							.displayError(
									AuthoringUIResources.saveLibraryDialog_title, 
									AuthoringUIResources.saveLibraryError_msg, 
									AuthoringUIResources.error_reason, e); 

					return dialog
							.displayPrompt(
									AuthoringUIResources.openLibraryDialog_title, 
									AuthoringUIResources.openLibraryDialog_text); 
				}
				break;
			case SWT.NO:
				// Discard all changes by resetting all resources as unchanged.
				manager.discardMethodLibraryChanges();
				break;
			case SWT.CANCEL:
				return false;
			}
		}
		return true;
	}

	/**
	 * Prompts the user to save the open library if it has been modified.
	 * 
	 * @return the choice the user made when prompted to save - one of SWT.YES, SWT.NO, SWT.CANCEL
	 */
	public static int promptSaveInt() {
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryModified()) {
			int ret = MsgBox.prompt(AuthoringUIResources.saveLibraryDialog_title, 
					AuthoringUIResources.saveLibraryDialog_text, 
					SWT.YES | SWT.NO | SWT.CANCEL);
			switch (ret) {
			case SWT.YES:
				try {
					LibraryService.getInstance().saveCurrentMethodLibrary();
				} catch (Exception e) {
					MsgDialog dialog = AuthoringUIPlugin.getDefault()
							.getMsgDialog();
					dialog
							.displayError(
									AuthoringUIResources.saveLibraryDialog_title, 
									AuthoringUIResources.saveLibraryError_msg, 
									AuthoringUIResources.error_reason, e); 
				}
				return SWT.YES;
			case SWT.NO:
				// Discard all changes by resetting all resources as unchanged.
				manager.discardMethodLibraryChanges();
				return SWT.NO;
			case SWT.CANCEL:
				return SWT.CANCEL;
			}
		}

		return SWT.CANCEL;
	}

	/**
	 * closes all editors on the active page
	 *
	 */
	public static void closeAllEditors() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.closeAllEditors(true);
	}

//	/**
//	 * closes the MessageView (error view)
//	 *
//	 */
//	public static void closeMessageView() {
//		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
//				.hideView(MessageView.getView());
//	}

	/**
	 * handles a dangling object
	 * @param object
	 * @return
	 * 			Object
	 */
	public static Object handleDangling(Object object) {
		if (object instanceof MethodElement
				&& ((EObject) object).eResource() == null) {
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.errorDialog_title, 
					AuthoringUIResources.bind(AuthoringUIResources.elementAlreadyDeletedError_msg, ((MethodElement) object).getName()));
			return null;
		} else if (object instanceof FeatureValueWrapperItemProvider) {
			FeatureValueWrapperItemProvider adapter = (FeatureValueWrapperItemProvider) object;
			Object value = adapter.getValue();
			if (value instanceof MethodElement
					&& ((EObject) value).eResource() == null) {
				Object owner = TngUtil.unwrap(adapter.getParent(value));
				if (owner instanceof ItemProviderAdapter) {
					// This is a UI item provider.
					owner = ((ItemProviderAdapter) owner).getTarget();
				}
				String ownerName = ((MethodElement) owner).getName();
				EStructuralFeature feature = adapter.getFeature();
				if (feature != null) {
					if (AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayPrompt(
									AuthoringUIResources.deleteDialog_title, 
									AuthoringUIResources.bind(AuthoringUIResources.ViewHelper_alreadydeletedconfirm_text, ((MethodElement) value)
									.getName(), ownerName))) { 
						// Remove the association.
						if (feature.isMany()) {
							((Collection) ((EObject) owner).eGet(feature))
									.remove(value);
						}
					}
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayError(
									AuthoringUIResources.errorDialog_title, 
									AuthoringUIResources.bind(AuthoringUIResources.elementAlreadyDeletedError_msg, ((MethodElement) value)
									.getName()));
				}
				return null;
			}
		}
		return object;
	}

	/* TODO: Is this still needed?
	public static void loadAllAndSaveAll() {
		final MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (lib == null)
			return;

		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the operation runs.
			public void execute(IProgressMonitor monitor) {
				monitor.beginTask(AuthoringUIResources
						.getString("AuthoringUI.upgradingLibraryTask.name"), 3); //$NON-NLS-1$
				try {
					try {
						monitor.worked(1);
						monitor
								.setTaskName(AuthoringUIResources
										.getString("AuthoringUI.loadingLibraryElementsTask.name")); //$NON-NLS-1$
						ModelStorage.loadAllProxies(lib);
					} catch (Exception e) {
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										AuthoringUIResources
												.getString("AuthoringUI.upgradeLibraryDialog.title"), //$NON-NLS-1$
										AuthoringUIResources
												.getString("AuthoringUI.upgradeLibraryError.msg"), //$NON-NLS-1$
										AuthoringUIResources
												.getString("AuthoringUI.upgradeLibraryError.reason"), //$NON-NLS-1$
										e);
						return;
					}
					try {
						monitor.worked(1);
						monitor
								.setTaskName(AuthoringUIResources
										.getString("AuthoringUI.savingUpgradedElementsTask.name")); //$NON-NLS-1$
						MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) lib
								.eResource().getResourceSet();
						resourceSet.save(LibraryProcessor.getInstance()
								.getSaveOptions(), true);
					} catch (Exception e) {
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										AuthoringUIResources
												.getString("AuthoringUI.upgradeLibraryDialog.title"), //$NON-NLS-1$
										AuthoringUIResources
												.getString("AuthoringUI.upgradeLibraryError.msg"), //$NON-NLS-1$
										AuthoringUIResources
												.getString("AuthoringUI.saveUpgradedLibraryError.reason"), //$NON-NLS-1$
										e);
					}
				} finally {
					monitor.done();
				}
			}
		};

		try {
			// Run the operation and display the progress.
			new ProgressMonitorDialog(Display.getDefault().getActiveShell())
					.run(true, false, operation);
		} catch (Exception e) {
			AuthoringUIPlugin
					.getDefault()
					.getMsgDialog()
					.displayError(
							AuthoringUIResources
									.getString("AuthoringUI.upgradeLibraryDialog.title"), //$NON-NLS-1$
							AuthoringUIResources
									.getString("AuthoringUI.upgradeLibraryError.msg"), //$NON-NLS-1$
							AuthoringUIResources
									.getString("AuthoringUI.internalError.reason"), //$NON-NLS-1$
							e);
		}
	}
	*/

	/**
	 * Checks if the current selection is locked.
	 */
	public static boolean isLocked(IStructuredSelection selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			while (element instanceof ITreeItemContentProvider) {
				element = ((ITreeItemContentProvider) element).getParent(null);
			}
			element = TngUtil.unwrap(element);
			if (element instanceof EObject
					&& TngUtil.isLocked((EObject) element)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Judge if there is a defined customized locker
	 * @return true
	 */
	public static boolean hasCustomizedLocker(IStructuredSelection selection){
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			while (element instanceof ITreeItemContentProvider) {
				element = ((ITreeItemContentProvider) element).getParent(null);
			}			
			element = TngUtil.unwrap(element);			
			if (element instanceof EObject ) {	
				AbstractLocker locker = null;
				if(element instanceof MethodConfiguration ){
					locker = LockerFactory.getInstance().getLocker(element);	
				}else{
					MethodPlugin plugin = UmaUtil.getMethodPlugin((EObject)element);
					if(plugin != null  ){
						locker = LockerFactory.getInstance().getLocker(plugin);						
					}
				}				
				if(locker != null ){
					return true;
				}				
			}
		}
		return false;
	}

	/**
	 * Checks if the current selection is locked.
	 * If there is a customized locker, use it, otherwise check it with the locked flag of 
	 * Method plugin
	 * @param selection
	 * @return
	 */
	public static boolean isLockedWithCustomizedLocker(IStructuredSelection selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {			
			Object selectedItem = iter.next();
			Object element = selectedItem;
			while (element instanceof ITreeItemContentProvider) {
				element = ((ITreeItemContentProvider) element).getParent(null);
			}			
			Object unwrapElement = TngUtil.unwrap(element);
			
			if (unwrapElement instanceof EObject ) {	
				
				if(element instanceof MethodConfiguration ){
					AbstractLocker locker = LockerFactory.getInstance().getLocker(element);					
					if(locker != null ){
						if( locker.isLocked(element)){
							return true;
						}
					}
				}
				
				MethodPlugin plugin = UmaUtil.getMethodPlugin((EObject)unwrapElement);
				if(plugin != null ){
					AbstractLocker locker = LockerFactory.getInstance().getLocker(plugin);					
					if(locker != null ){
						if( locker.isLocked(selectedItem)){
							return true;
						}
					}else if( TngUtil.isLocked((EObject) unwrapElement)){
						return true;
					}
				}				
			}
		}

		return false;
	}
	
	/**
	 * Check whether the destination is locked by a customized locker.
	 * Note: Some item could be locked by a extented locker.
	 * @return
	 */
	public static boolean isExtendedLocked(Object destination) {
		try {
			if (destination == null) {
				return false;
			}
			Object element = destination;
			while (element instanceof ITreeItemContentProvider) {
				element = ((ITreeItemContentProvider) element).getParent(null);
			}
			element = TngUtil.unwrap(element);
			if (element instanceof EObject) {
				MethodPlugin plugin = UmaUtil
				.getMethodPlugin((EObject) element);
				if (plugin != null) {
					AbstractLocker locker = LockerFactory.getInstance()
					.getLocker(plugin);
					if (locker != null && locker.isLocked(destination)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return false;
	}

	/**
	 * Fixes the content description GUIDs.
	 */
	public static void fixContentDescriptionGUIDs() {
		final MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (lib == null)
			return;

		org.eclipse.epf.library.edit.util.IRunnableWithProgress runnable = new org.eclipse.epf.library.edit.util.IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				HashSet modifiedResources = new HashSet();
				for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
					InternalEObject element = (InternalEObject) iter.next();
					if (element.eProxyURI() == null) {
						if (element instanceof ContentDescription) {
							ContentDescription content = (ContentDescription) element;
							DescribableElement container = (DescribableElement) element
									.eContainer();
							if (container != null) {
								String guid = UmaUtil.generateGUID(container
										.getGuid());
								if (!guid.equals(content.getGuid())) {
									content.setGuid(guid);
									modifiedResources.add(content.eResource());
									modifiedResources
											.add(container.eResource());
								}
							}
						}
					} else {
						AuthoringUIPlugin
								.getDefault()
								.getLogger()
								.logError(
										"Unresolved proxy in '" + element.eResource().getURI().toFileString() + "': " + element); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}

				monitor.subTask(AuthoringUIResources.savingFilesTask_name);
				ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
				try {
					for (Iterator iter = modifiedResources.iterator(); iter
							.hasNext();) {
						Resource resource = (Resource) iter.next();
						monitor.subTask(AuthoringUIResources.bind(AuthoringUIResources.savingTask_name, resource.getURI().toFileString()));
						persister.save(resource);
					}
					persister.commit();
				} catch (Exception e) {
					persister.rollback();
					throw new WrappedException(e);
				}
			}

		};
		UserInteractionHelper
				.runWithProgress(
						runnable,
						AuthoringUIResources.fixingContentDescriptionGUIDsTask_name); 
	}

	private static String checkProxy(InternalEObject element, EReference ref,
			Object value) {
		if (value instanceof InternalEObject) {
			InternalEObject eObj = (InternalEObject) value;
			if (eObj.eIsProxy()) {
				EObject resolved = element.eResolveProxy(eObj);
				String errMsg = null;
				if (resolved == eObj) {
					errMsg = "Unresolved proxy"; //$NON-NLS-1$
				} else if (!ref.getEType().isInstance(resolved)) {
					errMsg = "Invalid data"; //$NON-NLS-1$
				}
				if (errMsg != null) {
					String path;
					if (element instanceof NamedElement) {
						path = ref.getEType().getName()
								+ "(" + Misc.getPathRelativeToLibrary((NamedElement) element) + ")." + ref.getName() + " = "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					} else {
						path = ""; //$NON-NLS-1$
					}
					return errMsg
							+ " in '" + element.eResource().getURI().toFileString() + "': " + path + resolved; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		return null;
	}

	/**
	 * Library health check
	 *
	 */
	public static void checkLibraryHealth(Object context) {
		if (! AuthoringUIPreferences.getEnableLibraryValidation()) {
			return;
		}
		
		final MethodLibrary lib = context instanceof MethodLibrary ? (MethodLibrary) context : null;
		
		final List<MethodPlugin> pluginList = new ArrayList<MethodPlugin>();
		if (context instanceof  List) {
			for (Object obj : (List) context) {
				if (obj instanceof MethodPlugin) {
					pluginList.add((MethodPlugin) obj);
				}
			}
		}
		
		if (lib == null && pluginList.isEmpty()) {
			return;
		}

		org.eclipse.epf.library.edit.util.IRunnableWithProgress runnable = new org.eclipse.epf.library.edit.util.IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				AuthoringUIPlugin.getDefault().getLogger().logInfo(
						"++++ LIBRARY HEALTH CHECK REPORT - START +++"); //$NON-NLS-1$
				StringWriter strWriter = new StringWriter();
				PrintWriter printWriter = new PrintWriter(strWriter);
				printWriter.println();
				printWriter
						.println("UNRESOLVED/INVALID PROXIES IN X-REFERENCES"); //$NON-NLS-1$
				printWriter
						.println("------------------------------------------"); //$NON-NLS-1$
				
				Iterator iter = null;
				if (! pluginList.isEmpty()) {
					List list = new ArrayList();					
					for (MethodPlugin plugin :  pluginList) {
						for (Iterator it = plugin.eAllContents(); it.hasNext();) {
							list.add(it.next());
						}
					}
					iter = list.iterator();
				} else {
					iter = lib.eAllContents();
				}
				for ( ; iter.hasNext();) {
					InternalEObject element = (InternalEObject) iter.next();
					if (element.eProxyURI() == null) {
						if (element instanceof ContentDescription) {
							ContentDescription content = (ContentDescription) element;
							DescribableElement container = (DescribableElement) element
									.eContainer();
							if (container != null) {
								String guid = UmaUtil.generateGUID(container
										.getGuid());
								if (!guid.equals(content.getGuid())) {
									AuthoringUIPlugin
											.getDefault()
											.getLogger()
											.logError(
													"ContentDescription with invalid GUID: " + content.getGuid() + " in '" + content.eResource().getURI().toFileString() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								}
							} else {
								AuthoringUIPlugin
										.getDefault()
										.getLogger()
										.logError(
												"ContentDescription without a container: " + content); //$NON-NLS-1$
							}

							if (content instanceof BreakdownElementDescription) {
								// check if the content.xmi of the process is in
								// the right place
								ProcessComponent procComp = UmaUtil
										.getProcessComponent(content);
								if (procComp != null) {
									String modelPath = procComp.eResource()
											.getURI().toFileString();
									File dir = new File(modelPath)
											.getParentFile();
									String contentPath = content.eResource()
											.getURI().toFileString();

									// System.out.println("model path: " +
									// modelPath);
									// System.out.println("content path: " +
									// contentPath);
									// System.out.println();

									File contentDir = new File(contentPath)
											.getParentFile();
									if (!dir.equals(contentDir)) {
										AuthoringUIPlugin
												.getDefault()
												.getLogger()
												.logError(
														"Content file of " + container.eClass().getName() + " '" + container.getName() + "' in '" + modelPath + " is misplaced: " + contentPath); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
									}
								}
							}
						}

						// check for unresolved proxies in cross references
						ArrayList xReferences = new ArrayList(element.eClass()
								.getEAllReferences());
						xReferences.removeAll(element.eClass()
								.getEAllContainments());
						for (Iterator iterator = xReferences.iterator(); iterator
								.hasNext();) {
							EReference ref = (EReference) iterator.next();
							Object value = element.eGet(ref, false);
							if (ref.isMany()) {
								if (value instanceof InternalEList) {
									InternalEList list = (InternalEList) value;
									for (Iterator iter1 = list.basicIterator(); iter1
											.hasNext();) {
										String msg = checkProxy(element, ref,
												iter1.next());
										if (msg != null) {
											printWriter.println(msg);
										}
									}
								}
							} else {
								String msg = checkProxy(element, ref, value);
								if (msg != null) {
									printWriter.println(msg);
								}
							}
						}
					} else {
						AuthoringUIPlugin
								.getDefault()
								.getLogger()
								.logError(
										"Unresolved proxy in '" + element.eResource().getURI().toFileString() + "': " + element); //$NON-NLS-1$ //$NON-NLS-2$
					}

				}
				AuthoringUIPlugin.getDefault().getLogger().logError(
						strWriter.toString());
				AuthoringUIPlugin.getDefault().getLogger().logInfo(
						"++++ LIBRARY HEALTH CHECK REPORT - END +++"); //$NON-NLS-1$
			}

		};
		if (UserInteractionHelper
				.runWithProgress(runnable, AuthoringUIResources.viewHelper_performHealthCheck)) { 
			String title = AuthoringUIResources.viewHelperHealthCheckDialog_title; 
			String message = AuthoringUIResources.viewHelperHealthCheckDialog_message; 
			AuthoringUIPlugin.getDefault().getMsgDialog().displayInfo(title,
					message);
		}
	}

	/**
	 * removes invalid reference in the open library
	 *
	 */
	public static void removeInvalidReferences() {
		final MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (lib == null)
			return;

		org.eclipse.epf.library.edit.util.IRunnableWithProgress runnable = new org.eclipse.epf.library.edit.util.IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				HashSet modifiedResources = new HashSet();
				for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
					InternalEObject element = (InternalEObject) iter.next();
					if (element.eProxyURI() == null) {
						ArrayList xReferences = new ArrayList(element.eClass()
								.getEAllReferences());
						xReferences.removeAll(element.eClass()
								.getEAllContainments());
						for (Iterator iterator = xReferences.iterator(); iterator
								.hasNext();) {
							EReference ref = (EReference) iterator.next();
							Object value = element.eGet(ref, false);
							if (ref.isMany()) {
								if (value instanceof InternalEList) {
									InternalEList list = (InternalEList) value;
									ArrayList invalidProxies = new ArrayList();
									for (Iterator iter1 = list.basicIterator(); iter1
											.hasNext();) {
										Object v = iter1.next();
										if (isInvalidReference(element, ref, v)) {
											invalidProxies.add(v);
										}
									}
									if (!invalidProxies.isEmpty()) {
										removeInvalidReferences(element, list,
												invalidProxies);
										modifiedResources.add(element
												.eResource());
									}
								}
							} else {
								if (isInvalidReference(element, ref, value)) {
									element.eSet(ref, null);
									modifiedResources.add(element.eResource());
								}
							}
						}
					} else {
						AuthoringUIPlugin
								.getDefault()
								.getLogger()
								.logError(
										"Unresolved proxy in '" + element.eResource().getURI().toFileString() + "': " + element); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}

				monitor.subTask(AuthoringUIResources.savingFilesTask_name); 
				ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
				try {
					for (Iterator iter = modifiedResources.iterator(); iter
							.hasNext();) {
						Resource resource = (Resource) iter.next();
						monitor.subTask(AuthoringUIResources.bind(AuthoringUIResources.savingTask_name, resource.getURI().toFileString()));
						persister.save(resource);
					}
					persister.commit();
				} catch (Exception e) {
					persister.rollback();
					throw new WrappedException(e);
				}
			}

			private boolean isInvalidReference(InternalEObject element,
					EReference ref, Object value) {
				if (value instanceof InternalEObject) {
					InternalEObject eObj = (InternalEObject) value;
					if (eObj.eIsProxy()) {
						EObject resolved = element.eResolveProxy(eObj);
						if (!ref.getEType().isInstance(resolved)) {
							return true;
						}
					}
				}
				return false;
			}

			private void removeInvalidReferences(InternalEObject element,
					InternalEList values, Collection invalidProxies) {
				ArrayList list = new ArrayList(values.basicList());
				list.removeAll(invalidProxies);
				values.clear();
				int max = list.size() - 1;
				for (int i = max; i > -1; i--) {
					list.set(i, element.eResolveProxy((InternalEObject) list
							.get(i)));
				}
				values.addAll(list);
			}

		};
		UserInteractionHelper.runWithProgress(runnable, AuthoringUIResources.deletingInvalidReferencesTask_name); 

	}

	/**
	 * reloads the current library.  Used on a rollback error
	 * @param shell
	 */
	public static void reloadCurrentLibaryOnRollbackError(Shell shell) {
		reloadCurrentLibrary(shell, AuthoringUIResources.ViewHelper_reloadLibOnRollbackError);
	}

	/**
	 * displays the message and reloads the current library 
	 * @param shell
	 * @param message
	 */
	public static void reloadCurrentLibrary(final Shell shell, final String message) {
		Display display = MsgBox.getDisplay();
		Runnable runnable = new Runnable() {

			public void run() {
				doReloadCurrentLibrary(shell, message); 
			}
			
		};
		if(display != null) {
			display.asyncExec(runnable);
		}
		else {
			runnable.run();
		}		

	}
	
	private static void doReloadCurrentLibrary(Shell shell, String message) {
		if (shell == null) {
			MsgBox.getDefaultShell();
		}
		String title = AuthoringUIResources.reloadDialog_title; 
		if (message == null) {
			message = AuthoringUIResources.reloadDialog_message; 
		}
		AuthoringUIPlugin.getDefault().getMsgDialog().displayInfo(title,
				message);

		// The library needs to be reloaded.
		String libDir = LibraryService.getInstance()
				.getCurrentMethodLibraryLocation();
		LibraryUIManager.getInstance().openLibrary(libDir);
	}

	/**
	 * opens the given viewId
	 * @param viewId
	 * @return
	 * 			View
	 */
	public static IViewPart openView(String viewId) {
		return findView(viewId, true);
	}
	
	
	/**
	 * opens the given viewId
	 * @param viewId
	 * @return
	 * 			View
	 */
	public static IViewPart findView(String viewId, boolean show) {
		try {			
			IWorkbenchPage activePage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			if (activePage != null) {
				IViewPart view = activePage.findView(viewId);
				if (view == null) {
					
					// always create the view
					view = activePage.showView(viewId);					
				}
				
				if ( !show ) {
					activePage.hideView(view);
				}
				
				return view;
			}
		} catch (Exception e) {
			if (CommandLineRunUtil.getInstance().isNeedToRun()) {
				return null;
			}
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.errorDialog_title, 
					AuthoringUIResources.internalError_msg, 
					e);
		}
		return null;

	}
	
	public static IViewPart findView(String viewId) {
		try {			
			IWorkbenchPage activePage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			if (activePage != null) {
				return activePage.findView(viewId);
			}
		} catch (Exception e) {
			if (CommandLineRunUtil.getInstance().isNeedToRun()) {
				return null;
			}
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.errorDialog_title, 
					AuthoringUIResources.internalError_msg, 
					e);
		}
		return null;

	}
	
	public static boolean isViewInCurrentPerspective(String viewId) {		
		String perspectiveId = PerspectiveUtil.getActivePerspectiveId();
		return isViewInPerspective(viewId, perspectiveId);
	}
	
	/**
	 * Deciede whether the view definded in the perspective or not
	 * 
	 * @author david zhongwei
	 * @param viewId
	 * @param perpectiveId
	 * @return
	 */
	public static boolean isViewInPerspective(String viewId, String perspectiveId) {
		if (viewId == null || perspectiveId == null) {
			return false;
		}

		final String NAMESPACE = "org.eclipse.ui";//$NON-NLS-1$
		final String EXTENSIONPOINT_ID = "perspectiveExtensions";//$NON-NLS-1$
		final String TARGET_ID = "targetID";//$NON-NLS-1$
		final String VIEW = "view";//$NON-NLS-1$
		final String ID = "id";//$NON-NLS-1$
		final String VIEWSHORTCUT = "viewShortcut";//$NON-NLS-1$

		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = null;
		if (extensionRegistry != null) {
			extensionPoint = extensionRegistry.getExtensionPoint(NAMESPACE, EXTENSIONPOINT_ID);
		}

		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				IConfigurationElement[] configElements = extension.getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];
					try {
						String id = configElement.getAttribute(TARGET_ID);
						if (perspectiveId.equals(id)) {

							// decide whether is defined in "view"
							IConfigurationElement[] configElementsForView = configElement.getChildren(VIEW);
							for (int k = 0; k < configElementsForView.length; k++) {
								IConfigurationElement configurationElement = configElementsForView[k];
								if (viewId.equals(configurationElement.getAttribute(ID))) {
									return true;
								}
							}

							// decide whether is defined in "viewShortcut"
							IConfigurationElement[] configElementsForViewShortcut = configElement
							        .getChildren(VIEWSHORTCUT);
							for (int l = 0; l < configElementsForViewShortcut.length; l++) {
								IConfigurationElement configurationElement = configElementsForViewShortcut[l];
								if (viewId.equals(configurationElement.getAttribute(ID))) {
									return true;
								}
							}

						}
					}
					catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
		return false;
	}
	
	public static void refreshView(AbstractBaseView configView) {
		Control ctrl = configView.getViewer().getControl();
		if (ctrl != null && !ctrl.isDisposed()) {
			Object input =  configView.getViewer().getInput();
			if (input instanceof EObject) {
				EObject eObject = (EObject) input;
				if (eObject.eIsProxy()) {
					configView.setInputForViewer(RefreshJob.getInstance()
							.resolve(eObject));
				} else {
					ISelection selection = configView.getViewer()
							.getSelection();
					configView.getViewer().refresh();
					restoreSelection(configView.getViewer(), selection);
				}
			}
		}

	}

	public static void restoreSelection(Viewer viewer, ISelection selection) {
		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			boolean restore = false;
			ArrayList resolvedList = new ArrayList();
			for (Iterator iter = structuredSelection.iterator(); iter.hasNext();) {
				Object object = iter.next();
				if (object instanceof EObject) {
					EObject eObj = (EObject) object;
					if (eObj.eIsProxy()) {
						eObj = RefreshJob.getInstance().resolve(eObj);
						restore = true;
					}
					if (!eObj.eIsProxy()) {
						resolvedList.add(eObj);
					}
				}
			}
			if (restore) {
				viewer
						.setSelection(new StructuredSelection(resolvedList),
								true);
			}
		}
	}
}
