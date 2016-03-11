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
package org.eclipse.epf.library;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeEventWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeListenerWrapper;
import org.eclipse.epf.common.serviceability.DebugTrace;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.preferences.LibraryPreferences;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.library.services.LibraryModificationHelper;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.MultiFileXMISaveImpl;
import org.eclipse.epf.persistence.refresh.IRefreshEvent;
import org.eclipse.epf.persistence.refresh.IRefreshListener;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.LibrarySchedulingRule;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.ui.IPropertyListener;

/**
 * The abstract base class for a Library Manager. Concrete implementation of
 * ILibraryManager must be a subclass of this class.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @author Jinhua Xi
 * 
 * @since 1.0
 */
public abstract class AbstractLibraryManager implements ILibraryManager {

	public static final int PROP_DIRTY = 1;

	/**
	 * The library name.
	 */
	public static final String ARG_LIBRARY_NAME = "library.name"; //$NON-NLS-1$

	public static final String ARG_LIBRARY_REGISTER_TYPE = "libraryRegisterType"; 	//$NON-NLS-1$
	
	// If true, generate debug traces.
	protected static boolean debug = LibraryPlugin.getDefault().isDebugging();

	// The managed library.
	protected MethodLibrary library;	

	// The default editing domain for the managed library.
	protected AdapterFactoryEditingDomain editingDomain;

	// A list of listeners that monitor changes to the managed library.
	private List libraryChangedListeners = new ArrayList();

	// remove this one since this may cause potential memory leak
	// A list of listeners that have been detached from the managed library.
	//private List detachedLibraryChangedListeners = new ArrayList();

	// A list of listeners that monitor resource changes in the managed library.
	private ListenerList resourceChangeListeners = new ListenerList();

	// The save library options.
	private Map saveOptions;

	// If true, skip all event processing.
	protected boolean skipEventProcessing = false;

	// TODO: find a better way to notify the change in library instead of
	// relying on the command stack listener
	private CommandStackListener commandStackListener = new CommandStackListener() {
		public void commandStackChanged(final EventObject event) {
			if (debug) {
				DebugTrace.print(this, "commandStackChanged", "event=" + event); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (!skipEventProcessing) {
				SafeUpdateController.asyncExec(new Runnable() {
					public void run() {
						// Try to select the affected objects.
						Command mostRecentCommand = LibraryUtil
								.unwrap(((CommandStack) event.getSource())
										.getMostRecentCommand());
						if (mostRecentCommand != null) {
							if (mostRecentCommand instanceof AddCommand) {
								AddCommand cmd = (AddCommand) mostRecentCommand;
								EObject owner = cmd.getOwner();

								// need to send owner changed notification for
								// all element types
								// 
								// 156028 - Reference from WP and Guidence was
								// not detected
								// when deselect the related element from
								// configuration

								Collection objs = new ArrayList();
								objs.add(owner);
								notifyListeners(
										ILibraryChangeListener.OPTION_CHANGED,
										objs);

								if (!(owner instanceof MethodConfiguration)) {

									objs = mostRecentCommand.getResult();

									// Update the configuration selection if the
									// object is a newly added method package.
									if (owner instanceof MethodPackage) {
										objs = LibraryUtil
												.getContainedElements(owner,
														objs);
										if (!objs.isEmpty()) {
											addNewPackagesToConfiguration(objs);
										}
									}
									notifyListeners(
											ILibraryChangeListener.OPTION_NEWCHILD,
											objs);
								}
							} else if (mostRecentCommand instanceof PasteFromClipboardCommand) {
								Collection objs = mostRecentCommand.getResult();
								notifyListeners(
										ILibraryChangeListener.OPTION_NEWCHILD,
										objs);
								PasteFromClipboardCommand cmd = ((PasteFromClipboardCommand) mostRecentCommand);

								// Update the configuration selection if the
								// object is a newly added method package.
								if (cmd.getOwner() instanceof MethodPackage) {
									objs = LibraryUtil.getContainedElements(cmd
											.getOwner(), objs);
									if (!objs.isEmpty()) {
										addNewPackagesToConfiguration(objs);
									}
								}
							} else if (mostRecentCommand instanceof CreateChildCommand) {
								notifyListeners(
										ILibraryChangeListener.OPTION_NEWCHILD,
										mostRecentCommand.getAffectedObjects());
							} else if (mostRecentCommand != null) {
								notifyListeners(
										ILibraryChangeListener.OPTION_CHANGED,
										mostRecentCommand.getAffectedObjects());
							}
						}
					}
				});
			}
		}
	};

	// Listen to changes to the managed method library.
	private INotifyChangedListener notifyChangedListener = new INotifyChangedListener() {
		public void notifyChanged(Notification notification) {
			if (debug) {
				DebugTrace.print(this,
						"notifyChanged", "notification=" + notification); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (!skipEventProcessing) {
				int eventType = notification.getEventType();
				switch (eventType) {
				case Notification.ADD: {
					// A method element, typically a method plug-in, has been
					// added to the managed library without using an editing
					// command.
					Object notifier = notification.getNotifier();
					Object value = notification.getNewValue();
					if ((notifier instanceof MethodLibrary)
							&& (value instanceof MethodPlugin)) {
						Collection affectedObjects = new ArrayList();
						affectedObjects.add(value);
						notifyListeners(ILibraryChangeListener.OPTION_NEWCHILD,
								affectedObjects);
					}
					break;
				}

				case Notification.SET: {
					Object notifier = notification.getNotifier();
					if (notifier != null) {
						Collection affectedObjects = new ArrayList();
						affectedObjects.add(notifier);
						notifyListeners(ILibraryChangeListener.OPTION_CHANGED,
								affectedObjects);
					}
					break;
				}

				case Notification.REMOVE: {
					// Either a method element has been removed from the
					// containing element, or a method element reference has
					// been deleted.
					Object notifier = notification.getNotifier();
					Object oldValue = notification.getOldValue();
					Collection affectedObjects = new ArrayList();
					if (oldValue instanceof EObject
							&& ((EObject) oldValue).eContainer() == null) {
						// A method element has been deleted.
						affectedObjects.add(oldValue);
						notifyListeners(ILibraryChangeListener.OPTION_DELETED,
								affectedObjects);
					} else {
						// A method element reference has been deleted, notify
						// the listeners that the containing method element has
						// changed.
						affectedObjects.add(notifier);
						notifyListeners(ILibraryChangeListener.OPTION_CHANGED,
								affectedObjects);
					}
					break;
				}

				case Notification.REMOVE_MANY: {
					// Two or more method elements have been removed from
					// the containing element, or tw or more method element
					// reference have been deleted.
					List oldValue = new ArrayList((Collection) notification
							.getOldValue());
					ArrayList deletedElements = new ArrayList();
					ArrayList removedReferences = new ArrayList();
					if (!oldValue.isEmpty()) {
						for (Iterator iter = oldValue.iterator(); iter
								.hasNext();) {
							Object element = iter.next();
							if (element instanceof EObject) {
								if (((EObject) element).eContainer() == null) {
									deletedElements.add(element);
								} else {
									removedReferences.add(element);
								}
							}
						}
					}
					if (!deletedElements.isEmpty()) {
						// Two or more method elements have been deleted.
						notifyListeners(ILibraryChangeListener.OPTION_DELETED,
								deletedElements);
					}
					if (!removedReferences.isEmpty()) {
						// Two or more method element reference has been
						// deleted.
						notifyListeners(ILibraryChangeListener.OPTION_CHANGED,
								removedReferences);
					}
					break;
				}
				}
			}
		}
	};

	// Listen to managed method library resource changes.
	private Adapter resourceChangedListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			if (debug) {
				DebugTrace.print(this, "notifyChanged", "msg=" + msg); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (msg.getFeatureID(null) == Resource.RESOURCE__IS_MODIFIED
					&& msg.getEventType() == org.eclipse.emf.common.notify.Notification.SET) {
				firePropertyChange(msg.getNotifier(), PROP_DIRTY);
			}
		}
	};

	// Listen to persistence refresh events.
	private IRefreshListener refreshListener = new IRefreshListener() {
		public void notifyRefreshed(IRefreshEvent event) {
			if (debug) {
				DebugTrace.print(this, "notifyRefreshed", "event=" + event); //$NON-NLS-1$ //$NON-NLS-2$
			}
			handleRefreshEvent(event);
		}
	};

	// Listen to preference store changes.
	private IPropertyChangeListenerWrapper preferenceStoreChangeListener = new IPropertyChangeListenerWrapper() {
		public void propertyChange(IPropertyChangeEventWrapper event) {
			if (event.getProperty().equals(
					LibraryPreferences.DISCARD_UNRESOLVED_REFERENCES)) {
				saveOptions.put(
						MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES,
						event.getNewValue());
			}
		}
	};

	/**
	 * Creates a new instance.
	 */
	public AbstractLibraryManager() {
		init();
	}

	/**
	 * Performs the necessary initialization.
	 */
	protected void init() {
		if (debug) {
			DebugTrace.print(this, "init"); //$NON-NLS-1$
		}

		LibraryPlugin.getDefault().getPreferenceStore()
				.addPropertyChangeListener(preferenceStoreChangeListener);

		// Create the adapter factory.
		List factories = new ArrayList();
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				factories);

		// Create the command stack.
		BasicCommandStack commandStack = new BasicCommandStack();

		// Create the resource set.
		ILibraryResourceSet resourceSet = createResourceSet();
		resourceSet.addRefreshListener(refreshListener);
		RefreshJob.getInstance().setResourceSet(resourceSet);

		// Initialize the library save options.
		saveOptions = resourceSet.getDefaultSaveOptions();
		saveOptions.put(MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES,
				LibraryPreferences.getDiscardUnresolvedReferences());

		// Create the editing domain.
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				commandStack, resourceSet);

		// Register the editing domain.
		registerEditingDomain(editingDomain);

	}

	/**
	 * Saves the managed method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void saveMethodLibrary() throws LibraryServiceException {
		if (debug) {
			DebugTrace.print(this, "saveMethodLibrary"); //$NON-NLS-1$
		}

		try {
			if (library != null) {
				skipEventProcessing = true;

				ILibraryResourceSet resourceSet = ((ILibraryResourceSet) editingDomain
						.getResourceSet());
				resourceSet.save(saveOptions);

				((BasicCommandStack) editingDomain.getCommandStack())
						.saveIsDone();

				skipEventProcessing = false;

				firePropertyChange(library, PROP_DIRTY);

			}
		} catch (Exception e) {
			throw new LibraryServiceException(e);
		} finally {
			skipEventProcessing = false;
		}
	}

	/**
	 * Discards all changes made to the managed method library.
	 */
	public void discardMethodLibraryChanges() {
		if (debug) {
			DebugTrace.print(this, "discardMethodLibraryChanges"); //$NON-NLS-1$
		}

		for (Iterator it = getEditingDomain().getResourceSet().getResources()
				.iterator(); it.hasNext();) {
			Resource resource = (Resource) it.next();
			resource.setModified(false);
		}
	}

	/**
	 * Closes the managed method library.
	 * 
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void closeMethodLibrary() throws LibraryServiceException {
		if (debug) {
			String msg = "library=" + library + ", memory on entry=" //$NON-NLS-1$ //$NON-NLS-2$
					+ (Runtime.getRuntime().totalMemory() - Runtime
							.getRuntime().freeMemory());
			DebugTrace.print(this, "closeMethodLibrary", msg); //$NON-NLS-1$
		}

		// String libPath = LibraryService.getInstance()
		// .getCurrentMethodLibraryPath();
		File libFile = new File(library.eResource().getURI().toFileString());
		String libPath = libFile.getParentFile().getAbsolutePath();

		// remove the configuration managers associated with this library
		LibraryService.getInstance().removeConfigurationManagers(library);

		removeResourceChangedListeners();

		// Clear the temp layout resources.
		LayoutResources.clear();
		
		Suppression.clearCachedSuppressions();

		TngUtil.umaItemProviderAdapterFactory.dispose();

		ILibraryResourceSet resourceSet = (ILibraryResourceSet) editingDomain
				.getResourceSet();
		resourceSet.unload();

		try {
			// Close the method library project file.
			MethodLibraryProject.closeProject(libPath, null);
		} catch (Exception e) {
			throw new LibraryServiceException(e);
		} 

		RefreshJob.getInstance().reset();

		// Activates the garbage collector.
		Runtime.getRuntime().gc();

		if (debug) {
			String msg = "library=" + library + ", memory on exit=" //$NON-NLS-1$ //$NON-NLS-2$
					+ (Runtime.getRuntime().totalMemory() - Runtime
							.getRuntime().freeMemory());
			DebugTrace.print(this, "closeMethodLibrary", msg); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the managed method library.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getMethodLibrary() {
		if (debug) {
			DebugTrace.print(this, "getMethodLibrary", "library=" + library); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return library;
	}

	/**
	 * Sets the managed method library.
	 * 
	 * @param library
	 *            a method library
	 */
	public void setMethodLibrary(MethodLibrary library) {
		if (debug) {
			DebugTrace.print(this, "setMethodLibrary", "library=" + library); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (this.library != null) {
			Resource resource = (Resource) this.library.eResource();
			if (resource != null) {
				resource.getContents().clear();
				resource.getContents().add(library);
			}
		}

		this.library = library;
	}

	/**
	 * Gets the adapter factory for the managed method library.
	 * 
	 * @return an adapter factory
	 */
	public ComposedAdapterFactory getAdapterFactory() {
		if (debug) {
			DebugTrace.print(this, "getAdapterFactory"); //$NON-NLS-1$
		}

		return (ComposedAdapterFactory) getEditingDomain().getAdapterFactory();
	}

	/**
	 * Gets the editing domain for the managed method library.
	 * 
	 * @return an editing domain
	 */
	public AdapterFactoryEditingDomain getEditingDomain() {
		if (debug) {
			DebugTrace.print(this,
					"getEditingDomain", "editingDomain=" + editingDomain); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return editingDomain;
	}

	/**
	 * Registers an editing domain with the managed method library.
	 * 
	 * @param domain
	 *            an editing domain
	 */
	public void registerEditingDomain(AdapterFactoryEditingDomain domain) {
		if (debug) {
			DebugTrace.print(this, "registerEditingDomain", "domain=" + domain); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// Add a listener to monitor library changes made in the given editing
		// domain.
		AdapterFactory adapterFactory = domain.getAdapterFactory();
		if(adapterFactory instanceof ComposedAdapterFactory) {
			ComposedAdapterFactory composedAdapterFactory = (ComposedAdapterFactory) adapterFactory;
			// remove the listener before adding it to make sure that the same listener is not added more than one
			composedAdapterFactory.removeListener(notifyChangedListener);
			composedAdapterFactory.addListener(notifyChangedListener);
		}

		// Add a listener to monitor changes made to the command stack.
		// This is used to select the most recently affected objects in the
		// viewer.
		domain.getCommandStack().removeCommandStackListener(commandStackListener);
		domain.getCommandStack().addCommandStackListener(commandStackListener);
	}
	
	public void unregisterEditingDomain(AdapterFactoryEditingDomain domain) {
		AdapterFactory adapterFactory = domain.getAdapterFactory();
		if(adapterFactory instanceof ComposedAdapterFactory) {
			ComposedAdapterFactory composedAdapterFactory = (ComposedAdapterFactory) adapterFactory;
			composedAdapterFactory.removeListener(notifyChangedListener);
		}

		domain.getCommandStack().removeCommandStackListener(commandStackListener);
	}

	/**
	 * Adds a listener to monitor changes to the managed method library.
	 * 
	 * @param listener
	 *            a library change listener
	 */
	public void addListener(ILibraryChangeListener listener) {
		synchronized (libraryChangedListeners) {
			if (debug) {
				DebugTrace.print(this, "addListener", "listener=" + listener); //$NON-NLS-1$ //$NON-NLS-2$
			}

			if (!libraryChangedListeners.contains(listener)) {
				libraryChangedListeners.add(listener);
			}
		}
	}

	/**
	 * Removes a listener that was added to monitor changes to the managed
	 * method library.
	 * 
	 * @param listener
	 *            a library change listener
	 */
	public synchronized void removeListener(ILibraryChangeListener listener) {
		if (debug) {
			DebugTrace
					.print(this, "removeListener", "listener=" + listener); //$NON-NLS-1$ //$NON-NLS-2$
		}

//			// Cache the listener and remove it just before dispatching the
//			// library changed events.
//			if (!detachedLibraryChangedListeners.contains(listener)) {
//				detachedLibraryChangedListeners.add(listener);
//			}
		
		if(listener != null) {
			synchronized (libraryChangedListeners) {
				libraryChangedListeners.remove(listener);
			}
		}
	}

	/**
	 * Adds a listener to monitor resource changes in the managed method
	 * library.
	 * 
	 * @param listener
	 *            a property change listener
	 */
	public void addPropertyListener(IPropertyListener listener) {
		if (debug) {
			DebugTrace.print(this,
					"addPropertyListener", "listener=" + listener); //$NON-NLS-1$ //$NON-NLS-2$
		}

		resourceChangeListeners.add(listener);
	}

	/**
	 * Removes a listener that was added to monitor resource changes in the
	 * managed method library.
	 * 
	 * @param listener
	 *            a property change listener
	 */
	public void removePropertyListener(IPropertyListener listener) {
		if (debug) {
			DebugTrace.print(this,
					"removePropertyListener", "listener=" + listener); //$NON-NLS-1$ //$NON-NLS-2$
		}

		resourceChangeListeners.remove(listener);
	}

	/**
	 * Starts listening to command processing on a command stack.
	 * 
	 * @param commandStack
	 *            a command stack
	 */
	public void startListeningTo(CommandStack commandStack) {
		if (debug) {
			DebugTrace.print(this,
					"startListeningTo", "commandStack=" + commandStack); //$NON-NLS-1$ //$NON-NLS-2$
		}

		commandStack.addCommandStackListener(commandStackListener);
	}

	/**
	 * Stops listening to command processing on a command stack.
	 * 
	 * @param commandStack
	 *            a command stack
	 */
	public void stopListeningTo(CommandStack commandStack) {
		if (debug) {
			DebugTrace.print(this,
					"stopListeningTo", "commandStack=" + commandStack); //$NON-NLS-1$ //$NON-NLS-2$
		}

		commandStack.removeCommandStackListener(commandStackListener);
	}

	/**
	 * Starts listening to change notifications sent from an adapter factory.
	 * 
	 * @param adapterFactory
	 *            an adapter factory
	 */
	public void startListeningTo(ComposedAdapterFactory adapterFactory) {
		if (debug) {
			DebugTrace.print(this,
					"startListeningTo", "adapterFactory=" + adapterFactory); //$NON-NLS-1$ //$NON-NLS-2$
		}

		adapterFactory.addListener(notifyChangedListener);
	}

	/**
	 * Stops listening to change notifications sent from an adapter factory.
	 * 
	 * @param adapterFactory
	 *            an adapter factory
	 */
	public void stopListeningTo(ComposedAdapterFactory adapterFactory) {
		if (debug) {
			DebugTrace.print(this,
					"stopListeningTo", "adapterFactory=" + adapterFactory); //$NON-NLS-1$ //$NON-NLS-2$
		}

		adapterFactory.removeListener(notifyChangedListener);
	}

	/**
	 * Gets a method element from the managed method library.
	 * 
	 * @param guid
	 *            the method element's GUID.
	 * 
	 * @return a method element of <code>null</code>
	 */
	public MethodElement getMethodElement(String guid) {
// this printed out too much useless information, comment out
//		if (debug) {
//			DebugTrace.print(this, "getMethodElement", "guid=" + guid); //$NON-NLS-1$ //$NON-NLS-2$
//		}
		if (guid == null) {
			return null;
		}
		try {
			ILibraryResourceSet resourceSet = (ILibraryResourceSet) library
					.eResource().getResourceSet();
			if (resourceSet != null) {
				return (MethodElement) resourceSet.getEObject(guid);
			}
		} catch (Throwable th) {
			// Log error here
			th.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the relative URI of a method element in the managed method library.
	 * 
	 * @param element
	 *            a method element
	 * @return a relative URI
	 */
	public URI getElementRelativeURI(MethodElement element) {
		if (debug) {
			DebugTrace.print(this,
					"getElementRelativeURI", "element=" + element); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (element != null) {
			Resource resource = library.eResource();
			if (resource != null) {
				URI libraryURI = resource.getURI();
				URI elementURI = element.eResource().getURI();
				return elementURI.deresolve(libraryURI);
			}
		}
		return null;
	}

	/**
	 * Checks whether the managed method library is read only.
	 * 
	 * @return <code>true</code> if the method library is read only
	 */
	public boolean isMethodLibraryReadOnly() {
		if (debug) {
			DebugTrace.print(this, "isMethodLibraryReadOnly"); //$NON-NLS-1$
		}

		URI libraryURI = library.eResource().getURI();
		if (libraryURI.isFile()) {
			File libraryXMIFile = new File(libraryURI.toFileString());
			return libraryXMIFile.exists() && !libraryXMIFile.canWrite();
		}
		return false;
	}

	/**
	 * Checks whether the managed method library content has been modified.
	 * 
	 * @return <code>true</code> if the managed method library content has
	 *         been modified
	 */
	public boolean isMethodLibraryModified() {
		if (debug) {
			DebugTrace.print(this, "isMethodLibraryModified"); //$NON-NLS-1$
		}

		for (Iterator it = getEditingDomain().getResourceSet().getResources()
				.iterator(); it.hasNext();) {
			Resource resource = (Resource) it.next();
			if (resource.isModified()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether the managed method library has any unresolved proxy.
	 * 
	 * @return <code>true</code> if the managed method library has an
	 *         unresolved proxy.
	 */
	public boolean hasUnresolvedProxy() {
		if (debug) {
			DebugTrace.print(this, "hasUnresolvedProxy"); //$NON-NLS-1$
		}

		ILibraryResourceSet resourceSet = ((ILibraryResourceSet) editingDomain
				.getResourceSet());
		return resourceSet.hasUnresolvedProxy();
	}

	/**
	 * Reloads the given resources.
	 * 
	 * @param resources
	 *            a collection of resources
	 * @return a collection of resources that have reloaded
	 */
	public Collection reloadResources(final Collection resources) {
		if (debug) {
			System.out
					.println("AbstractLibraryManager.reloadResources(): START"); //$NON-NLS-1$
		}
		try {
			final ArrayList reloadedResources = new ArrayList();
			IWorkspaceRunnable runnable = new IWorkspaceRunnable() {

				public void run(IProgressMonitor monitor) throws CoreException {
					reloadedResources.addAll(doReloadResources(resources));

				}

			};
			try {
				ResourcesPlugin.getWorkspace().run(runnable,
						new LibrarySchedulingRule(library),
						IWorkspace.AVOID_UPDATE, new NullProgressMonitor());
			} catch (Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
			return reloadedResources;
		} finally {
			if (debug) {
				System.out
						.println("AbstractLibraryManager.doReloadResources(): END"); //$NON-NLS-1$
			}
		}
	}

	private Collection doReloadResources(Collection resources) {
		if (debug) {
			DebugTrace.print(this, "reloadResources"); //$NON-NLS-1$
		}
		if (library == null) {
			return Collections.EMPTY_LIST;
		}

		// check if resources to reload contains any elements cached in
		// LibraryService
		// to update them
		//
		LibraryService libSvc = (LibraryService) LibraryService.getInstance();
		Resource currentLibResource = null;
		ILibraryManager currentLibMgr = null;
		Resource currentConfigResource = null;
		MethodConfiguration currentConfig = null;
		List configResources = new ArrayList();
		List configs = new ArrayList();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			MethodElement e = PersistenceUtil.getMethodElement(resource);
			if (e == libSvc.getCurrentMethodLibrary()) {
				currentLibMgr = libSvc.getCurrentLibraryManager();
				currentLibResource = resource;
			} else if (e == libSvc.getCurrentMethodConfiguration()) {
				currentConfigResource = resource;
				currentConfig = libSvc.getCurrentMethodConfiguration();
			} else if (e instanceof MethodConfiguration) {
				configResources.add(resource);
				configs.add(e);
			}
		}

		ILibraryResourceSet resourceSet = (ILibraryResourceSet) library
				.eResource().getResourceSet();
		Collection reloadedResources = resourceSet.reloadResources(resources);
		if (!reloadedResources.isEmpty()) {
			if (currentLibResource != null || currentConfigResource != null) {
				// update cached elements in LibraryService and this library
				// manager
				//
				for (Iterator iter = reloadedResources.iterator(); iter
						.hasNext();) {
					Resource resource = (Resource) iter.next();
					if (resource == currentLibResource) {
						MethodElement e = PersistenceUtil
								.getMethodElement(resource);
						if (e instanceof MethodLibrary) {
							MethodLibrary newLib = (MethodLibrary) e;
							libSvc.setCurrentMethodLibrary(newLib);
							if (currentLibMgr instanceof AbstractLibraryManager) {
								libSvc.removeLibraryManager(currentLibMgr);
								((AbstractLibraryManager) currentLibMgr)
										.updateMethodLibrary(newLib);
								libSvc.setLibraryManager(currentLibMgr);
							}
						}
					}
					if (resource == currentConfigResource) {
						MethodElement e = PersistenceUtil
								.getMethodElement(resource);
						if (e instanceof MethodConfiguration) {
							// remove config manager of old current config
							//
							libSvc.removeConfigurationManager(currentConfig);
							MethodConfiguration config = (MethodConfiguration) e;
							libSvc.setCurrentMethodConfiguration(config);
						}
					} else if (!configResources.isEmpty()) {
						int id = configResources.indexOf(resource);
						if (id != -1) {
							// remove config manager of old config
							//
							libSvc
									.removeConfigurationManager((MethodConfiguration) configs
											.get(id));
						}
					}
				}
			}

			// TODO: Review implementation.
			Suppression.cleanUp();
		}
		return reloadedResources;
	}

	/**
	 * @param newLib
	 */
	private void updateMethodLibrary(MethodLibrary newLib) {
		library = newLib;
	}

	/**
	 * Gets the options used for saving the managed method library.
	 * 
	 * @return a map of method library specific save options
	 */
	public Map getSaveOptions() {
		if (debug) {
			DebugTrace.print(this, "getSaveOptions"); //$NON-NLS-1$
		}

		return saveOptions;
	}

	/**
	 * Adds a new method plug-in to the managed method library.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void addMethodPlugin(final MethodPlugin plugin)
			throws LibraryServiceException {
		if (debug) {
			DebugTrace.print(this, "addMethodPlugin", "plugin=" + plugin); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// This operation will cause an UI update. It must be executed in
		// the main UI to aoid an Invalid Thread Access exception.
		final Exception[] exceptions = new Exception[1];

		try {
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					library.getMethodPlugins().add(plugin);

					ILibraryPersister.FailSafeMethodLibraryPersister persister = Services
							.getLibraryPersister(getLibraryPersisterType())
							.getFailSafePersister();
					try {
						persister.save(library.eResource());
						persister.commit();
					} catch (Exception e) {
						persister.rollback();
						exceptions[0] = e;
						return;
					}

					plugin.eResource().eAdapters().add(resourceChangedListener);
				}
			});
		} catch (Exception e) {
			throw new LibraryServiceException(e);
		}

		if (exceptions[0] != null) {
			throw new LibraryServiceException(exceptions[0]);
		}
	}

	/**
	 * Disposes all resources allocated by this library manager.
	 */
	public void dispose() {
		if (preferenceStoreChangeListener != null) {
			IPreferenceStoreWrapper prefStoreWrapper = LibraryPlugin
					.getDefault()
					.getPreferenceStore();
			
			if(prefStoreWrapper != null) {
				prefStoreWrapper.removePropertyChangeListener(preferenceStoreChangeListener);
			}
		}

		if (libraryChangedListeners.size() > 0) {
			libraryChangedListeners.clear();
		}

//		if (detachedLibraryChangedListeners.size() > 0) {
//			detachedLibraryChangedListeners.clear();
//		}

		if (resourceChangeListeners.size() > 0) {
			resourceChangeListeners.clear();
		}
		if(editingDomain != null) {
			unregisterEditingDomain(editingDomain);
			editingDomain = null;
		}
		library = null;

	}

	/**
	 * Checks the arguments used for creating a new method element.
	 * 
	 * @param containingElement
	 *            the parent/containing method element
	 * @param name
	 *            a name for the new method element
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation.
	 */
	protected void checkElementCreationArguments(
			MethodElement containingElement, String name)
			throws LibraryServiceException {
		if (containingElement == null) {
			throw new IllegalArgumentException();
		}
		if (name == null || name.length() == 0) {
			throw new InvalidMethodElementNameException();
		}
		// TODO: Check for illegal characters.
	}

	/**
	 * Handles a persistence refresh event.
	 * 
	 * @param event
	 *            a refresh event
	 */
	protected void handleRefreshEvent(IRefreshEvent event) {
		if (debug) {
			DebugTrace.print(this, "handleRefreshEvent", "refreshedResources=" //$NON-NLS-1$ //$NON-NLS-2$
					+ event.getRefreshedResources());
		}

		if (!event.getUnloadedObjects().isEmpty()) {
			TngAdapterFactory.INSTANCE.cleanUp();
		}
	}

	/**
	 * Notifies all library changed listeners attached to the managed library.
	 * 
	 * @param type
	 *            the type of change that has occurred
	 * @param changedElements
	 *            a collection of method elements that have changed
	 */
	protected synchronized void notifyListeners(final int option,
			final Collection collection) {

		// send notification might be causing listeners added or removed
		// to avoid concurrent update of the listener list, 
		// we keep the list of listeners processed
		// and check the listener list repeatedly
		HashSet processed = new HashSet();	
		while (_doNotifyListeners(option, collection, processed) ) {
			;
		}
	}
	
	/**
	 * Notifies all library changed listeners attached to the managed library.
	 * 
	 * @param type
	 *            the type of change that has occurred
	 * @param changedElements
	 *            a collection of method elements that have changed
	 * @return boolean true if any listener is processed
	 */
	private boolean _doNotifyListeners(final int option,
			final Collection collection, final Collection processed) {
		if (debug) {
			DebugTrace.print(this, "notifyListeners", "option=" + option); //$NON-NLS-1$ //$NON-NLS-2$
		}

		boolean changed = false;
		
		try {
//			// Remove the changed listeners that have been dettached.
//			if (detachedLibraryChangedListeners.size() > 0) {
//				for (Iterator it = detachedLibraryChangedListeners.iterator(); it
//						.hasNext();) {
//					Object l = it.next();
//					if (libraryChangedListeners.contains(l)) {
//						libraryChangedListeners.remove(l);
//					}
//				}
//				detachedLibraryChangedListeners.clear();
//			}

			// Notify the changed listeners.
			// Note: more changed listeners may be added while each listener is
			// being notified. However,
			// they will be added to the end of the list which does no harm.
			
			
			for ( Iterator it = new ArrayList(libraryChangedListeners).iterator(); it.hasNext(); ) {
				final ILibraryChangeListener listener = (ILibraryChangeListener) it.next();

				if ( (listener != null) && !processed.contains(listener) ) {
	
					// keep the processed ones
					processed.add(listener);
					changed = true;
					
					// Since this may trigger an update to the UI, the
					// notification must be executed in the UI thread to avoid
					// getting an Invalid Thread Access exception. The
					// notification must also be executed in sync mode to
					// gurantee delivery of the event before a listener is
					// disposed.
					SafeUpdateController.syncExec(new Runnable() {
						public void run() {
							if (debug) {
								DebugTrace
										.print(
												this,
												"notifyListeners", "listener=" + listener); //$NON-NLS-1$ //$NON-NLS-2$
							}
							listener.libraryChanged(option, collection);
						}
					});
				}
			
			}
		} catch (Exception e) {
			if (debug) {
				DebugTrace.print(this, "notifyListeners", e); //$NON-NLS-1$
			}
		}
		
		return changed;
		
	}

	/**
	 * Fires a property changed event.
	 * 
	 * @param propertyId
	 *            the id of the changed property
	 */
	protected void firePropertyChange(final Object source, final int propertyId) {
		if (debug) {
			DebugTrace.print(this, "firePropertyChange", "source=" + source); //$NON-NLS-1$ //$NON-NLS-2$
		}

		Object[] array = resourceChangeListeners.getListeners();
		for (int i = 0; i < array.length; i++) {
			final IPropertyListener listener = (IPropertyListener) array[i];

			// This operation will cause an UI update. It must be executed in
			// the main UI to aoid an Invalid Thread Access exception.
			SafeUpdateController.asyncExec(new Runnable() {
				public void run() {
					if (debug) {
						DebugTrace.print(this,
								"firePropertyChange", "listener=" + listener); //$NON-NLS-1$ //$NON-NLS-2$
					}
					listener.propertyChanged(source, propertyId);
				}
			});
		}
	}

	/**
	 * Adds a resource changed listener to the managed method library resources.
	 */
	protected void addResourceChangedListeners() {
		if (library == null || library.eResource() == null) {
			return;
		}

		if (!library.eResource().eAdapters().contains(resourceChangedListener)) {
			library.eResource().eAdapters().add(resourceChangedListener);
		}

		for (Iterator it = library.getMethodPlugins().iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (!plugin.eResource().eAdapters().contains(
					resourceChangedListener)) {
				plugin.eResource().eAdapters().add(resourceChangedListener);
			}
		}

		for (Iterator it = library.getPredefinedConfigurations().iterator(); it
				.hasNext();) {
			MethodConfiguration config = (MethodConfiguration) it.next();
			if (!config.eResource().eAdapters().contains(
					resourceChangedListener)) {
				config.eResource().eAdapters().add(resourceChangedListener);
			}
		}
	}

	/**
	 * Removes the resource changed listener to the managed method library
	 * resource and method plug-ins.
	 */
	protected void removeResourceChangedListeners() {
		if (library == null || library.eResource() == null) {
			return;
		}

		library.eResource().eAdapters().remove(resourceChangedListener);

		for (Iterator iter = library.getMethodPlugins().iterator(); iter
				.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			plugin.eResource().eAdapters().remove(resourceChangedListener);
		}

		for (Iterator it = library.getPredefinedConfigurations().iterator(); it
				.hasNext();) {
			MethodConfiguration config = (MethodConfiguration) it.next();
			config.eResource().eAdapters().remove(resourceChangedListener);
		}
	}

	/**
	 * Gets the managed method library resource.
	 * 
	 * @return a method library resource.
	 */
	protected Resource getMethodLibraryResource() {
		return library != null ? library.eResource() : null;
	}

	/**
	 * Gets the URI of the managed method library.
	 * 
	 * @return a <code>java.net.URI</code>
	 */
	public java.net.URI getMethodLibraryURI() {
		Resource savedResource = getMethodLibraryResource();
		if (savedResource != null) {
			URI resourceURI = savedResource.getURI();
			try {
				File file = new File(resourceURI.toFileString());
				return file.getParentFile().toURI();
			} catch (Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
		return null;
	}

	/**
	 * Adds the new packages into the configurations if the parent is in the
	 * configuration.
	 */
	private void addNewPackagesToConfiguration(Collection newobjs) {
		if (newobjs == null || newobjs.size() == 0) {
			return;
		}

		LibraryModificationHelper helper = new LibraryModificationHelper();

		try {
			EObject e, parent;
			for (Iterator it = newobjs.iterator(); it.hasNext();) {
				e = (EObject) it.next();
				if ((e instanceof MethodPackage)
						&& ((parent = e.eContainer()) != null)
						&& (parent instanceof MethodPackage)) {
					Object configs = ((MultiResourceEObject) parent)
							.getOppositeFeatureValue(AssociationHelper.MethodPackage_MethodConfigurations);
					if (configs instanceof List) {
						for (Iterator itconfig = ((List) configs).iterator(); itconfig
								.hasNext();) {
							MethodConfiguration config = (MethodConfiguration) itconfig
									.next();
							if (! ConfigurationHelper.getDelegate().needFixupLoadCheckPackages(config)) {
								continue;
							}
							List pkgs = config.getMethodPackageSelection();
							if (!pkgs.contains(e)) {
								// pkgs.add(e);
								helper
										.getActionManager()
										.doAction(
												IActionManager.ADD,
												config,
												UmaPackage.eINSTANCE
														.getMethodConfiguration_MethodPackageSelection(),
												e, -1);
							}
						}
					}
				}
			}
			
//wlu0 9-12-2012: we are now using "loadCheckPkgs" property mechanism to handle adding to configuration. No need to save here.
//			helper.save();

		} catch (RuntimeException e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		} finally {
			helper.dispose();
		}
	}

	/**
	 * Gets the type of library persister to be used in this library manager
	 * 
	 * @return the library persister type
	 * @see Services#XMI_PERSISTENCE_TYPE
	 */
	protected abstract String getLibraryPersisterType();

	protected abstract ILibraryResourceSet createResourceSet();

}