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
package org.eclipse.epf.library.edit.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.library.edit.IReferencer;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.services.ILibraryPersister.FailSafeMethodLibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * This command is used to delete a method element permanently. This involves
 * deleting the storage content and removing all references to this element.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DeleteMethodElementCommand extends CommandWrapper {

	protected Collection elements;

	private boolean refRemoved;

	public boolean executed = false;

	private Collection commandListeners;

	private FailSafeMethodLibraryPersister persister;

	protected ArrayList elementsToDeleteContent;

	protected Set<Resource> modifiedResources;

	// Map of element to Map of its referencer to features list
	//
	protected Map<EObject, Map<EObject, Collection<EStructuralFeature>>> elementToRemovedRefsMap;

	private BatchCommand batchCommand = new BatchCommand(true);

	/**
	 * List of Reference objects
	 * 
	 * @see Reference
	 */
	protected ArrayList removedReferences;

	public boolean failed;

	private boolean confirmRemoveReferences;
	
	private ArrayList<Command> nestedCommands;

	private HashSet<Descriptor> descriptors;
	
	private Set<MethodPlugin> plugins;

	/**
	 * @param command
	 * @param elements
	 *            MethodElement objects to be permanently deleted.
	 */
	public DeleteMethodElementCommand(Command command, Collection elements) {
		this(command, elements, true);
	}

	public DeleteMethodElementCommand(Command command, Collection elements,
			boolean confirmRemoveRefs) {
		super(command);
		this.elements = elements == null ? null : new ArrayList(elements);
		commandListeners = new ArrayList();
		confirmRemoveReferences = confirmRemoveRefs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CommandWrapper#dispose()
	 */
	public void dispose() {
		batchCommand.dispose();
		if (commandListeners != null) {
			commandListeners.clear();
		}
		if (elements != null) {
			elements.clear();
		}
		if (elementsToDeleteContent != null) {
			elementsToDeleteContent.clear();
		}
		if (elementToRemovedRefsMap != null) {
			elementToRemovedRefsMap.clear();
		}
		if (modifiedResources != null) {
			modifiedResources.clear();
		}
		if (removedReferences != null) {
			removedReferences.clear();
		}
		// Dispose nested commands.
		if(nestedCommands != null){
			if(!nestedCommands.isEmpty()){
				for (Command command : nestedCommands) {
					command.dispose();
				}
			}
		}
		if(descriptors != null) {
			descriptors.clear();
		}
		if (plugins != null) {
			plugins.clear();
		}
		
		super.dispose();
	}

	protected void superExecute() {
		super.execute();
	}

	private void notifyPreExecute() {
		List commandListeners = Providers
				.getCommandListeners(DeleteMethodElementCommand.class);
		if (commandListeners != null && !commandListeners.isEmpty()) {
			for (Iterator iter = commandListeners.iterator(); iter.hasNext();) {
				ICommandListener cmdListener = (ICommandListener) iter.next();
				try {
					cmdListener.preExecute(this);
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}

	private void notifyPreUndo() {
		List commandListeners = Providers
				.getCommandListeners(DeleteMethodElementCommand.class);
		if (commandListeners != null && !commandListeners.isEmpty()) {
			for (Iterator iter = commandListeners.iterator(); iter.hasNext();) {
				ICommandListener cmdListener = (ICommandListener) iter.next();
				try {
					cmdListener.preUndo(this);
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}

	protected void prepareElements() {
		plugins = new HashSet<MethodPlugin>();
		ArrayList newElements = new ArrayList();
		Collection<CustomCategory> customCategoriesToDelete = new HashSet<CustomCategory>();
		RemoveCommand cmd = null;
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof CustomCategory) {
				cmd = getRemoveCommand(element);
				if (cmd.getFeature() instanceof EReference
						&& ((EReference) cmd.getFeature()).isContainment()
						&& cmd.getOwnerList().contains(element)) {
					customCategoriesToDelete.add((CustomCategory) element);
				}
				MethodPlugin plugin = UmaUtil.getMethodPlugin((CustomCategory) element);
				if (plugin != null) {
					plugins.add(plugin);
				}
			}
		}
		
		boolean newDeleteRule = true;
		if (newDeleteRule) {
			return;
		}
		
		//The following code is no longer in used, keep it for future reference
		if (!customCategoriesToDelete.isEmpty()) {
			ArrayList<CustomCategory> topCustomCategoriesToDelete = new ArrayList<CustomCategory>(
					customCategoriesToDelete);
			// find all subcategories in the same plug-in that are not
			// referenced by
			// any other custom category to delete them as well
			//
			Iterator<CustomCategory> iter = new AbstractTreeIterator<CustomCategory>(
					new ArrayList<CustomCategory>(
							topCustomCategoriesToDelete), false) {

				private static final long serialVersionUID = 1L;

				@Override
				protected Iterator<? extends CustomCategory> getChildren(
						Object object) {
					if (object instanceof Collection) {
						return ((Collection) object).iterator();
					}
					ArrayList<CustomCategory> children = new ArrayList<CustomCategory>();
					CustomCategory cc = ((CustomCategory) object);
					MethodPlugin plugin = UmaUtil.getMethodPlugin(cc);
					for (DescribableElement element : cc
							.getCategorizedElements()) {
						if (element instanceof CustomCategory
								&& UmaUtil.getMethodPlugin(element) == plugin) {
							children.add((CustomCategory) element);
						}
					}
					return children.iterator();
				}

			};
			HashSet<CustomCategory> allCustomCategories = new HashSet<CustomCategory>();
			while(iter.hasNext()) {
				allCustomCategories.add(iter.next());
			}
			int size;
			do {
				size = customCategoriesToDelete.size();
				check_cc:
				for (CustomCategory cc : allCustomCategories) {
					if (!customCategoriesToDelete.contains(cc)) {
						List parents = AssociationHelper
								.getCustomCategories(cc);
						for (Object parent : parents) {
							if (!customCategoriesToDelete.contains(parent)) {
								// parent is not in the collection of custom
								// categories to be deleted
								// cannot delete the sub custom category for now
								//
								continue check_cc;
							}
						}
						customCategoriesToDelete.add(cc);
					}
				}
			} while (size != customCategoriesToDelete.size());
			customCategoriesToDelete.removeAll(topCustomCategoriesToDelete);
			elements.addAll(customCategoriesToDelete);
			Collection collection = cmd.getCollection();
			collection.addAll(customCategoriesToDelete);
		}
	}

	/**
	 * Gets the RemoveCommand for the given element
	 * 
	 * @param e
	 * @return
	 */
	protected RemoveCommand getRemoveCommand(Object e) {
		if (command instanceof RemoveCommand) {
			if (((RemoveCommand) command).getCollection().contains(e)) {
				return (RemoveCommand) command;
			}
		} else if (command instanceof CompoundCommand) {
			for (Iterator iter = ((CompoundCommand) command).getCommandList()
					.iterator(); iter.hasNext();) {
				Object cmd = (Object) iter.next();
				if (cmd instanceof RemoveCommand) {
					RemoveCommand removeCommand = (RemoveCommand) cmd;
					if (removeCommand.getCollection().contains(e)) {
						return removeCommand;
					}
				}
			}
		}
		return null;
	}

	public void execute() {
		prepareElements();

		notifyPreExecute();

		elementsToDeleteContent = new ArrayList();
		descriptors = new HashSet<Descriptor>();

		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof MethodElement) {
				collectObjectsToDeleteContent(elementsToDeleteContent,
						(MethodElement) element);
			}
		}

		IRunnableWithProgress runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				prepareRemovingReferences();
			}

		};
		UserInteractionHelper.runWithProgress(runnable,
				LibraryEditResources.processingReferencesTask_name); 


		if(!confirmRemovingReferences()) {
			descriptors.clear();
			return;
		}
		
		modifiedResources = new HashSet<Resource>();
		Set unmodifiedResources = new HashSet();

		// get the owner resources before the elements got removed from
		// container in superExecute()
		//
		collectOwnerResources(modifiedResources, unmodifiedResources);

		superExecute();
		
		executeNestedCommands();
		
		// Collect the nested command modified resources for saving. 
		collectNestedCommandResources(modifiedResources);

		// get resources of the objects that have been affected by this command
		// until now
		//
		collectResources(modifiedResources, super.getAffectedObjects());

		modifiedResources.removeAll(unmodifiedResources);

		final Exception[] exceptions = new Exception[1];
		UserInteractionHelper.getUIHelper().runWithBusyIndicator(new Runnable() {

			public void run() {
				try {
					removeReferences();
				} catch (Exception e) {
					exceptions[0] = e;
				}
			}

		});

		if (exceptions[0] != null) {
			Messenger.INSTANCE.showError(
					LibraryEditResources.deleteDialog_title,
					LibraryEditResources.deleteElementError_msg,
					LibraryEditResources.deleteReferencesError_reason,
					exceptions[0]);
				
			undo();
			return;
		}

		modifiedResources.addAll(getReferencingResources());

		for (Iterator iter = elementsToDeleteContent.iterator(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (element.eContainer() != null) {
				iter.remove();
			}
		}

		elementsToDeleteContent.addAll(elements);
		
		HashSet<Resource> deletedResources = new HashSet<Resource>();
		for (Iterator iter = elementsToDeleteContent.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (! (obj instanceof EObject)) {
				obj = TngUtil.unwrap(obj);
			}
			if (obj instanceof EObject) {
				EObject e = (EObject) obj;
				UmaUtil.getResources(e, deletedResources);
			}
		}
		
		// exclude deleted resources from the set of modified resources
		//
		modifiedResources.removeAll(deletedResources);
		
		// exclude any resource that is contained by the deleted resources
		//
		for (Iterator iter = modifiedResources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			for (Iterator iterator = deletedResources.iterator(); iterator
					.hasNext();) {
				Resource deletedResource = (Resource) iterator.next();
				if(getPersister().isContainedBy(resource, deletedResource)) {
					iter.remove();
				}
			}
		}		
		
		// check affected resources for unmodifiable
		//
		IStatus status = UserInteractionHelper.checkModify(modifiedResources,
				LibraryEditPlugin.getDefault().getContext());
		if (!status.isOK()) {
			Messenger.INSTANCE.showError(
					LibraryEditResources.deleteDialog_title,
					LibraryEditResources.deleteElementError_msg, status);

			undo();
			return;
		}
		
		runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask("", 3); //$NON-NLS-1$
				getPersister();

				// save resources that had been changed after references to the
				// deleted elements
				// had been removed
				//
				try {
					monitor
							.subTask(LibraryEditResources.deletingElementsTask_name);
					monitor.worked(1);
					deleteContent();

					// save modified resources
					//
					monitor.subTask(LibraryEditResources.savingResources_msg);
					monitor.worked(1);
					for (Iterator iter = modifiedResources.iterator(); iter
							.hasNext();) {
						Resource resource = (Resource) iter.next();
						if (resource.isLoaded()) {
							persister.save(resource);
						}
					}

					persister.commit();

					executed = true;

					removeAdapters();
				} catch (Exception e) {
					LibraryEditPlugin.INSTANCE.log(e);
					try {
						persister.rollback();
					} catch (Exception ex) {
						failed = true;
					}
					if (e instanceof RuntimeException) {
						throw (RuntimeException) e;
					} else {
						throw new WrappedException(e);
					}
				}
			}

		};

		// if (!UserInteractionHelper.runWithProgress(runnable,
		// LibraryEditResources.deletingElementsTask_name)) { //$NON-NLS-1$
		// if (failed) {
		// notifyFailure();
		// } else {
		// undo();
		// }
		// return;
		// }

//		UserInteractionHelper.runInUI(runnable, (Shell) null);
		
		UserInteractionHelper.runWithProgress(runnable, LibraryEditResources.deletingElementsTask_name);

		if (executed) {
			try {
				List warnings = persister.getWarnings();
				if (!warnings.isEmpty()) {
					String title = LibraryEditResources.deleteDialog_title;
					String msg = LibraryEditResources.DeleteMethodElementCommand_warningMsg;
					StringBuffer reason = new StringBuffer();
					for (Iterator iter = warnings.iterator(); iter.hasNext();) {
						Exception e = (Exception) iter.next();
						String str = e.getMessage();
						if (!StrUtil.isBlank(str)) {
							reason.append(str).append('\n');
						}
					}

					Messenger.INSTANCE.showWarning(title, msg, reason
							.toString());
				}
			} catch (Exception e) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);
			}
			
			notifyExecuted();
			if (plugins != null && !plugins.isEmpty()) {
				for (MethodPlugin p : plugins) {
					LibraryEditUtil.getInstance().fixUpDanglingCustomCategories(p);
				}
			}
			
		} else {
			if (failed) {
				notifyFailure();
			} else {
				undo();
			}
		}
	}

	private boolean confirmRemovingReferences() {
		if (!elementToRemovedRefsMap.isEmpty()) {
			// collect set of referencers
			HashSet<EObject> referencers = new HashSet<EObject>();
			for (Map<EObject, Collection<EStructuralFeature>> referencerToFeaturesMap : elementToRemovedRefsMap.values()) {
				// collect only referencer with an unidirectional or containment
				// relationship
				//
				for (Map.Entry<EObject, Collection<EStructuralFeature>> entry : referencerToFeaturesMap.entrySet()) {
					Collection<EStructuralFeature> features = entry.getValue();
					boolean canCollect = false;
					boolean hasDescriptor = false;
					check_ref: for (EStructuralFeature f : features) {
						if (!canCollect && f instanceof EReference) {
							EReference ref = (EReference) f;
							if (ref.isContainment()
									|| ref.getEOpposite() == null) {
								canCollect = true;
							}
						}
						if(!hasDescriptor && (f == UmaPackage.eINSTANCE.getTaskDescriptor_Task()
								|| f == UmaPackage.eINSTANCE.getRoleDescriptor_Role()
								|| f == UmaPackage.eINSTANCE.getWorkProductDescriptor_WorkProduct())) {
							hasDescriptor = true;
						}
						if(canCollect && hasDescriptor) {
							break check_ref;							
						}
					}
					if (canCollect) {
						referencers.add(entry.getKey());
					}
					if(hasDescriptor && entry.getKey() instanceof Descriptor) {
						descriptors.add((Descriptor) entry.getKey());
					}
				}
			}

			// confirm with user before removing illegal references
			//
			if (confirmRemoveReferences) {				
				MultiStatus multiStatus = new MultiStatus(
						LibraryEditPlugin.INSTANCE.getSymbolicName(), 0,
						"", null); //$NON-NLS-1$
				
				if(!descriptors.isEmpty()) {
					// ask user to delete the descriptors that are associated
					// with the content element to be deleted
					//
					for (EObject e : descriptors) {
						String msg = NLS.bind(
								LibraryEditResources.elementType_text, TngUtil.getTypeText(e), TngUtil
										.getLabelWithPath(e));
						IStatus status = new Status(IStatus.INFO,
								LibraryEditPlugin.INSTANCE.getSymbolicName(),
								0, msg, null);
						multiStatus.add(status);
					}
					IUserInteractionHandler uiHandler = ExtensionManager
							.getDefaultUserInteractionHandler();
					if (uiHandler != null) {
						switch (uiHandler
								.selectOne(
										new int[] {
												IUserInteractionHandler.ACTION_YES,
												IUserInteractionHandler.ACTION_NO,
												IUserInteractionHandler.ACTION_CANCEL },
										LibraryEditResources.confirmDescriptorsDeletion_title,
										LibraryEditResources.confirmDescriptorsDeletion_msg,
										multiStatus)) {
						case IUserInteractionHandler.ACTION_CANCEL:
							return false;
						case IUserInteractionHandler.ACTION_NO:
							descriptors.clear();
						}
					}
				}
				
				if(!descriptors.isEmpty()) {
					// remove the descriptors that user wanted to delete from the referencers
					//
					referencers.removeAll(descriptors);
				}
				multiStatus = new MultiStatus(
						LibraryEditPlugin.INSTANCE.getSymbolicName(), 0,
						"", null); //$NON-NLS-1$
				for (EObject e : referencers) {
					// don't show predefined element
					//
					if (!(e instanceof MethodElement && TngUtil.isPredefined((MethodElement) e))) {
						String msg = NLS.bind(
								LibraryEditResources.elementType_text, TngUtil.getTypeText(e), TngUtil
										.getLabelWithPath(e));
						IStatus status = new Status(IStatus.INFO,
								LibraryEditPlugin.INSTANCE.getSymbolicName(),
								0, msg, null);
						multiStatus.add(status);
					}
				}
				if (multiStatus.getChildren().length > 0) {
					IUserInteractionHandler uiHandler = ExtensionManager
							.getDefaultUserInteractionHandler();
					if (uiHandler != null) {
						if (uiHandler
								.selectOne(
										new int[] {
												IUserInteractionHandler.ACTION_OK,
												IUserInteractionHandler.ACTION_CANCEL },
										LibraryEditResources.deleteReferencesDialog_title,
										LibraryEditResources.deleteReferencesDialog_text, multiStatus) == IUserInteractionHandler.ACTION_CANCEL) {
							return false;
						}
					}
				}
			}

			// check if the referencers can be changed
			//
			for (EObject e : referencers) {
				IStatus status = UserInteractionHelper.checkModify(e, LibraryEditPlugin.getDefault().getContext());
				if (!status.isOK()) {
					Messenger.INSTANCE
							.showError(
									LibraryEditResources.deleteDialog_title,
									LibraryEditResources.deleteElementError_msg,
									status);
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * Disposes all stateful adapters, then removes all adapters that are
	 * attached to the given element
	 */
	private static void removeAdapters(EObject element) {
		for (Iterator iterator = new ArrayList(element.eAdapters()).iterator(); iterator
				.hasNext();) {
			Object adapter = iterator.next();
			if (adapter instanceof IStatefulItemProvider) {
				((IStatefulItemProvider) adapter).dispose();
				if (adapter instanceof ItemProviderAdapter) {
					AdapterFactory adapterFactory = ((ItemProviderAdapter) adapter)
							.getAdapterFactory();
					if (adapterFactory instanceof IReferencer) {
						((IReferencer) adapterFactory).remove(adapter);
					}
				}
			}
		}
		element.eAdapters().clear();
	}

	/**
	 * Disposes all stateful adapters, then removes all adapters that are
	 * attached to the deleted elements
	 */
	protected void removeAdapters() {
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object obj = TngUtil.unwrap(iter.next());
			if (obj instanceof EObject) {
				EObject element = (EObject) obj;
				if (element.eContainer() == null) {
					for (Iterator iterator = element.eAllContents(); iterator
							.hasNext();) {
						EObject e = (EObject) iterator.next();
						removeAdapters(e);
					}
					removeAdapters(element);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void notifyFailure() {
		EventObject eventObject = new EventObject(this);

		for (Iterator iter = commandListeners.iterator(); iter.hasNext();) {
			CommandListener listener = (CommandListener) iter.next();
			listener.notifyFailure(eventObject);
		}
	}

	/**
	 * @return
	 * 
	 */
	protected FailSafeMethodLibraryPersister getPersister() {
		if (persister == null) {
			persister = Services.getDefaultLibraryPersister()
					.getFailSafePersister();
		}
		return persister;
	}

	private void notifyExecuted() {
		EventObject eventObject = new EventObject(this);

		for (Iterator iter = this.commandListeners.iterator(); iter.hasNext();) {
			CommandListener listener = (CommandListener) iter.next();
			listener.notifyExecuted(eventObject);
		}

		List commandListeners = Providers
				.getCommandListeners(DeleteMethodElementCommand.class);
		if (commandListeners != null && !commandListeners.isEmpty()) {
			for (Iterator iter = commandListeners.iterator(); iter.hasNext();) {
				ICommandListener cmdListener = (ICommandListener) iter.next();
				try {
					cmdListener.notifyExecuted(this);
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}

	private Collection getReferencingResources() {
		HashSet referrers = new HashSet();

		// for (Iterator iter = removedReferencesMap.values().iterator();
		// iter.hasNext();) {
		// Map referrerToFeaturesMap = (Map) iter.next();
		// referrers.addAll(referrerToFeaturesMap.keySet());
		// }

		for (Iterator iter = removedReferences.iterator(); iter.hasNext();) {
			Reference ref = (Reference) iter.next();
			referrers.add(ref.owner);
		}

		HashSet resources = new HashSet();
		for (Iterator iter = referrers.iterator(); iter.hasNext();) {
			MethodElement element = (MethodElement) iter.next();
			if (element.eResource() != null) {
				resources.add(element.eResource());
			}
		}
		return resources;
	}

	/**
	 * Collects owner resources of the elements to be deleted to save later.
	 * 
	 * @param resources
	 */
	private void collectOwnerResources(Set resources, Set unmodifiedResources) {
		ILibraryPersister persister = Services.getDefaultLibraryPersister();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof MethodElement) {
				EObject container = ((MethodElement) element).eContainer();
				Resource resource = container.eResource();
				if (resource != null) {
					if (persister.hasOwnResourceWithoutReferrer(element)) {
						unmodifiedResources.add(resource);
					} else {
						resources.add(resource);
					}
				}
			}
		}
	}
	
	private static void collectResources(Set resources, Collection objects) {
		for (Iterator iter = objects.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof EObject) {
				EObject eObj = (MethodElement) element;
				if (eObj.eResource() != null) {
					resources.add(eObj.eResource());
				}
			}
		}
	}

	public void redo() {

		super.redo();
		removeReferences();

	}

	protected void deleteContent() throws Exception {
		List<MethodElement> toDeleteList = new ArrayList<MethodElement>();
		for (Iterator iter = elementsToDeleteContent.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof MethodElement) {
				MethodElement e = (MethodElement) element;
				if (e.eContainer() == null) {
					toDeleteList.add(e);
				}
			}
		}
		persister.delete(toDeleteList);
	}

	public Collection getElementsToRemoveReferences() {
		Collection list = new ArrayList();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof EObject
					&& willRemoveElementFromContainer((EObject) element)) {
				list.add(element);
			}
		}
		return list;
	}

	protected boolean canRemoveReferences(MethodElement e) {
		// if e is one of the deleted elements, make sure that it actually got
		// deleted by checking its container
		//
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object obj = TngUtil.unwrap(iter.next());
			if (e == obj) {
				return e.eContainer() == null;
			}
		}
		return true;
	}

	/**
	 * Loads all opposite features of the elements to be deleted
	 * 
	 * @return list of elements whose opposite features are loaded
	 */
	public List loadOppositeFeatures() {
		HashSet oppositeFeatures = new HashSet();
		HashSet deletedGUIDs = new HashSet();
		ArrayList elements = new ArrayList();

		for (Iterator iter = getElementsToRemoveReferences().iterator(); iter
				.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof MethodElement) {
				MethodElement e = (MethodElement) obj;
				for (Iterator iterator = e.eAllContents(); iterator.hasNext();) {
					Object element = iterator.next();
					if (element instanceof MethodElement) {
						elements.add(element);
						Map oppositeFeatureMap = ((MultiResourceEObject) element)
								.getOppositeFeatureMap();
						if (oppositeFeatureMap != null
								&& !oppositeFeatureMap.isEmpty()) {
							oppositeFeatures
									.addAll(oppositeFeatureMap.keySet());
							deletedGUIDs.add(((MethodElement) element)
									.getGuid());
						}
					}
				}
				elements.add(e);
				Map oppositeFeatureMap = ((MultiResourceEObject) e)
						.getOppositeFeatureMap();
				if (oppositeFeatureMap != null && !oppositeFeatureMap.isEmpty()) {
					oppositeFeatures.addAll(oppositeFeatureMap.keySet());
					deletedGUIDs.add(((MethodElement) e).getGuid());
				}
			}
		}

		loadOppositeFeatures(new ArrayList(oppositeFeatures), deletedGUIDs);

		return elements;
	}

	private boolean isContainedByDeletedElement(EObject e) {
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object deleted = TngUtil.unwrap(iter.next());
			if (deleted instanceof EObject && UmaUtil.isContainedBy(e, deleted)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Collects objects that should be removed if <code>elementToDelete</code>
	 * will be removed from <code>references</code> of <code>referencer</code>.
	 * 
	 * @param objectsToRemove
	 *            output
	 * @param referencer
	 *            element that references to elementToDelete
	 * @param references
	 *            collection of {@link EReference} that contains elementToDelete
	 * @return true if one of the collected objects is the
	 *         <code>referencer</code> or the container of
	 *         <code>referencer</code>
	 */
	protected boolean collectObjectsToRemove(Collection objectsToRemove,
			EObject elementToDelete, EObject referencer, Collection references) {
		boolean ret = false;
		List commandListeners = Providers
				.getCommandListeners(DeleteMethodElementCommand.class);
		if (commandListeners != null && !commandListeners.isEmpty()) {
			for (Iterator iter = commandListeners.iterator(); iter.hasNext();) {
				Object cmdListener = iter.next();
				if (cmdListener instanceof IDeleteMethodElementCommandListener) {
					try {
						boolean b = ((IDeleteMethodElementCommandListener) cmdListener)
								.collectObjectsToRemove(objectsToRemove,
										elementToDelete, referencer, references);
						if (b) {
							ret = true;
						}
					} catch (Exception e) {
						LibraryEditPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}

		return ret;
	}

	protected boolean willRemoveElementFromContainer(EObject element) {
		if (willRemoveElementFromContainer(command, element)) {
			return true;
		} else if (command instanceof CompoundCommand) {
			for (Iterator iter = ((CompoundCommand) command).getCommandList()
					.iterator(); iter.hasNext();) {
				Command cmd = (Command) iter.next();
				if (willRemoveElementFromContainer(cmd, element)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean willRemoveElementFromContainer(Command cmd,
			EObject element) {
		if (cmd instanceof RemoveCommand) {
			RemoveCommand removeCommand = ((RemoveCommand) cmd);
			return removeCommand.getCollection().contains(element)
					&& removeCommand.getOwner() == element.eContainer();
		}
		return false;
	}

	protected void prepareRemovingReferences() {
		List elements = loadOppositeFeatures();

		elementToRemovedRefsMap = new HashMap<EObject, Map<EObject, Collection<EStructuralFeature>>>();
		HashSet objectsToRemove = new HashSet();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			MethodElement element = (MethodElement) iter.next();
			Map<EObject, Collection<EStructuralFeature>> refMap = AssociationHelper.getReferenceMap(element);
			if (!refMap.isEmpty()) {
				for (Iterator iterator = refMap.entrySet().iterator(); iterator
						.hasNext();) {
					Map.Entry entry = (Entry) iterator.next();
					EObject referencer = (EObject) entry.getKey();
					Collection references = (Collection) entry.getValue();
					boolean b = collectObjectsToRemove(objectsToRemove,
							element, referencer, references);
					if (b || elements.contains(referencer)
							|| isContainedByDeletedElement(referencer)) {
						iterator.remove();
					}
				}
				if (!refMap.isEmpty())
					elementToRemovedRefsMap.put(element, refMap);
			}
		}

		// add entries for objectsToRemove to elementToRemovedRefsMap
		//
		for (Iterator iter = objectsToRemove.iterator(); iter.hasNext();) {
			EObject obj = (EObject) iter.next();
			Map<EObject, Collection<EStructuralFeature>> map = elementToRemovedRefsMap.get(obj);
			if (map == null) {
				map = new HashMap();
				elementToRemovedRefsMap.put(obj, map);
			}
			EObject container = obj.eContainer();
			EReference containmentFeature = obj.eContainmentFeature();
			Collection refs = (Collection) map.get(container);
			if (refs == null) {
				refs = new ArrayList();
				refs.add(containmentFeature);
				map.put(container, refs);
			} else {
				if (!refs.contains(containmentFeature)) {
					refs.add(containmentFeature);
				}
			}
		}

		// remove all bi-directional relationships
		//
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				Map objToRefsMap = new HashMap();
				for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) eObject
						.eCrossReferences().iterator(); featureIterator
						.hasNext();) {
					EObject eObj = (EObject) featureIterator.next();
					EReference eReference = (EReference) featureIterator
							.feature();
					if (eReference.getEOpposite() != null) {
						List refs = (List) objToRefsMap.get(eObj);
						if (refs == null) {
							refs = new ArrayList();
							objToRefsMap.put(eObj, refs);
						}
						refs.add(eReference.getEOpposite());
					}
				}
				Map map = (Map) elementToRemovedRefsMap.get(eObject);
				if (map == null) {
					elementToRemovedRefsMap.put(eObject, objToRefsMap);
				} else {
					// merge objToRefsMap to map
					//
					for (Iterator iterator = objToRefsMap.entrySet().iterator(); iterator
							.hasNext();) {
						Map.Entry entry = (Map.Entry) iterator.next();
						Object obj = entry.getKey();
						Collection refs = (Collection) entry.getValue();
						Collection existingRefs = (Collection) map.get(obj);
						if (existingRefs != null) {
							for (Iterator itor = refs.iterator(); itor
									.hasNext();) {
								Object ref = (Object) itor.next();
								if (!existingRefs.contains(ref)) {
									existingRefs.add(ref);
								}
							}
						} else {
							map.put(obj, refs);
						}
					}
				}
			}
		}

	}

	/**
	 * @param collectedObjects
	 * @param element
	 *            the element that will be deleted by this command
	 */
	protected void collectObjectsToDeleteContent(Collection collectedObjects,
			MethodElement element) {
		// this special handling is no longer needed since affected
		// subcategories are added
		// to the list of elements to be deleted
		//
		// if (element instanceof CustomCategory) {
		// // have to handle CustomCategory specially since deleting a
		// // CustomCategory might triger deleting
		// // its subcategories even the relationship between a CustomCategory
		// and
		// // its subcategories is
		// // non-containment reference
		// //
		// Iterator iter1 = new AbstractTreeIterator(element, false) {
		//
		// private static final long serialVersionUID = -6285969923138781437L;
		// protected Iterator getChildren(Object object) {
		// ArrayList children = new ArrayList();
		// Collection catElements = ((CustomCategory) object)
		// .getCategorizedElements();
		// for (Iterator iterator = catElements.iterator(); iterator
		// .hasNext();) {
		// Object e = iterator.next();
		// if (e instanceof CustomCategory) {
		// children.add(e);
		// }
		// }
		// return children.iterator();
		// }
		//
		// };
		// while (iter1.hasNext()) {
		// collectedObjects.add(iter1.next());
		// }
		// }

		List commandListeners = Providers
				.getCommandListeners(DeleteMethodElementCommand.class);
		if (commandListeners != null && !commandListeners.isEmpty()) {
			for (Iterator iter = commandListeners.iterator(); iter.hasNext();) {
				Object cmdListener = iter.next();
				if (cmdListener instanceof IDeleteMethodElementCommandListener) {
					try {
						((IDeleteMethodElementCommandListener) cmdListener)
								.collectObjectsToDeleteContent(
										collectedObjects, element);
					} catch (Exception e) {
						LibraryEditPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}

	protected void removeReferences() {
		if (refRemoved)
			return;

		if (removedReferences == null) {
			removedReferences = new ArrayList();
		} else {
			removedReferences.clear();
		}
		for (Iterator iter = elementToRemovedRefsMap.entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			MethodElement referenced = (MethodElement) entry.getKey();
			if (canRemoveReferences(referenced)) {
				Map removedRefMap = (Map) entry.getValue();
				for (Iterator iterator = removedRefMap.entrySet().iterator(); iterator
						.hasNext();) {
					Map.Entry ent = (Map.Entry) iterator.next();
					EObject referencer = (EObject) ent.getKey();
					Collection features = (Collection) ent.getValue();
					for (Iterator iter1 = features.iterator(); iter1.hasNext();) {
						EStructuralFeature feature = (EStructuralFeature) iter1
								.next();
						if (feature.isMany()) {
							List list = ((List) referencer.eGet(feature));

							int index = list.indexOf(referenced);
							// list.remove(index);
							if (index != -1) {
								list.remove(index);
								removedReferences.add(new Reference(referencer,
										feature, referenced, index));
								removeReferenceFollowUp(referencer, referenced, feature);
							} else {
								if (TngUtil.DEBUG) {
									System.out
											.println("DeleteMethodElementCommand.removeReferences(): index=" + index + ", size=" + list.size() + ", referencer=" + referencer + ", referenced=" + referenced + ", feature=" + feature); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
								}
								// work-around: try to find the proxy and remove
								// it.
								// TODO: kind of hack, needs revisit
								//
								String guid = ((MethodElement) referenced)
										.getGuid();
								find_proxy: for (int i = 0; i < list.size(); i++) {
									InternalEObject ref = (InternalEObject) list
											.get(i);
									URI uri = ref.eProxyURI();
									if (uri != null
											&& guid.equals(uri.fragment())) {
										list.remove(i);
										removedReferences.add(new Reference(
												referencer, feature,
												referenced, i));
										break find_proxy;
									}
								}
							}
						} else {
							referencer.eSet(feature, null);
							removedReferences.add(new Reference(referencer,
									feature, referenced, -1));
						}
					}
				}
			}
		}

		if (TngUtil.DEBUG) {
			System.out
					.println("removedReferences: size=" + removedReferences.size()); //$NON-NLS-1$
		}

		batchCommand.getObjectToNewFeatureValuesMap().clear();
		for (Iterator iter = removedReferences.iterator(); iter.hasNext();) {
			Reference ref = (Reference) iter.next();
			if (ref.getFeature() == UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT) {
				batchCommand
						.addFeatureValue(
								ref.getOwner(),
								UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_TYPE,
								UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_TYPE
										.getDefaultValue());
				if (ref.getOwner() instanceof Activity) {
					// Fill blank presentation name of extended/locally
					// contributing activity with presentation name
					// from base activity when base is deleted
					//
					Activity act = (Activity) ref.getOwner();
					VariabilityType vType = act.getVariabilityType();
					if ((vType == VariabilityType.EXTENDS || vType == VariabilityType.LOCAL_CONTRIBUTION)
							&& StrUtil.isNull(act.getPresentationName())) {
						Activity base = (Activity) ref.getValue();
						batchCommand
								.addFeatureValue(
										act,
										UmaPackage.Literals.METHOD_ELEMENT__PRESENTATION_NAME,
										ProcessUtil.getPresentationName(base));
					}
				}
			}
		}
		if (batchCommand.canExecute()) {
			batchCommand.execute();
		}

		refRemoved = true;
	}

	/**
	 * Subclass should override this method to resolve all target features so
	 * opposite features of the given MethodElement are fully loaded.
	 * 
	 * @param deletedGUIDs
	 * 
	 * @param e
	 */
	protected void loadOppositeFeatures(List oppositeFeatures, Set deletedGUIDs) {
		//
	}

	protected static void restoreReferences(List removedReferences) {
		for (int i = removedReferences.size() - 1; i > -1; i--) {
			Reference ref = (Reference) removedReferences.get(i);
			if (ref.feature.isMany()) {
				List list = (List) ref.owner.eGet(ref.feature);
				if (ref.index != -1) {
					// TODO: need revisits
					//
					if (!list.contains(ref.value)) {
						if (ref.index < list.size()) {
							list.add(ref.index, ref.value);
						} else {
							if (TngUtil.DEBUG) {
								System.out
										.println("DeleteMethodElementCommand.removeReferences(): index=" + ref.index + ", size=" + list.size() + ", referencer=" + ref.owner + ", referenced=" + ref.value + ", feature=" + ref.feature); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
							}
							list.add(ref.value);
						}
					} else {
						if (TngUtil.DEBUG) {
							System.out
									.println("DeleteMethodElementCommand.removeReferences(): reference already exists: referencer=" + ref.owner + ", referenced=" + ref.value + ", feature=" + ref.feature); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						}

					}
				} else {
					list.add(ref.value);
				}
			} else {
				ref.owner.eSet(ref.feature, ref.value);
			}
		}
	}

	protected void restoreReferences() {
		if (!refRemoved)
			return;

		batchCommand.undo();
		restoreReferences(removedReferences);

		refRemoved = false;
	}

	public void undo() {
		notifyPreUndo();
		try {
			super.undo();
			restoreReferences();
		} catch (Exception e) {
			LibraryEditPlugin.INSTANCE.log(e);
			notifyFailure();
		}
	}

	public Collection getAffectedObjects() {
		if (executed) {
			return super.getAffectedObjects();
		}
		return elements;
	}

	public void addCommandListener(CommandListener listener) {
		if (!commandListeners.contains(listener)) {
			commandListeners.add(listener);
		}
	}

	public void removeCommandListener(CommandListener listener) {
		commandListeners.remove(listener);
	}

	public static interface CommandListener {
		void notifyExecuted(EventObject eventObject);

		void notifyFailure(EventObject eventObject);
	}

	/**
	 * 
	 */
	protected void executeNestedCommands() {
		List nestedCommandProviders = ExtensionManager
				.getNestedCommandProviders();
		if (!nestedCommandProviders.isEmpty()) {
			if (!elements.isEmpty()) {
				nestedCommands = new ArrayList();
				for (Iterator iter = nestedCommandProviders.iterator(); iter
						.hasNext();) {
					INestedCommandProvider cmdProvider = (INestedCommandProvider) iter
							.next();
					try {
						Command cmd = cmdProvider.removeRelatedObjects(elements,
								this);
						if (cmd != null && cmd.canExecute()) {
							cmd.execute();
							nestedCommands.add(cmd);
						}
					} catch (Exception e) {
						LibraryEditPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param modifiedResources2
	 */
	private void collectNestedCommandResources(Set<Resource> modifiedResources) {
		
		if (nestedCommands != null && !nestedCommands.isEmpty()) {
			for (int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				try {
					if (cmd instanceof IResourceAwareCommand) {
						Collection resources = ((IResourceAwareCommand) cmd)
								.getModifiedResources();
						if (resources != null) {
							modifiedResources.addAll(resources);
						}
					}
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				} finally {
				}
			}
		}
	}
	
	public Collection<Descriptor> getDescriptorsToDelete() {
		return descriptors;
	}
	
	public boolean isExecuted() {
		return executed;
	}
	
	protected void removeReferenceFollowUp(EObject referencer,
			EObject referenced, EStructuralFeature feature) {
	}
	
}
