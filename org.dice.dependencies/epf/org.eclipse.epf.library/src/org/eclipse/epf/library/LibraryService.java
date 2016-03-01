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

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.common.service.utils.CommandLineRunUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.preferences.LibraryPreferences;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryProblemMonitor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.UmaFactory;

/**
 * The default Library Service implementation.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class LibraryService implements ILibraryService {

	protected static final int EVENT_CREATE_LIBRARY = 1;

	protected static final int EVENT_OPEN_LIBRARY = 2;

	protected static final int EVENT_REOPEN_LIBRARY = 3;

	protected static final int EVENT_CLOSE_LIBRARY = 4;

	protected static final int EVENT_SET_CURRENT_LIBRARY = 5;

	protected static final int EVENT_SET_CURRENT_CONFIGURATION = 6;

	// The shared instance.
	protected static ILibraryService instance;

	// A map of method libraries to library managers.
	protected Map<MethodLibrary, ILibraryManager> libraryManagers = new HashMap<MethodLibrary, ILibraryManager>();

	// A map of method configurations to configuration managers.
	protected Map<MethodConfiguration, IConfigurationManager> configManagers = new HashMap<MethodConfiguration, IConfigurationManager>();

	// The library service listeners.
	protected List<ILibraryServiceListener> listeners = new ArrayList<ILibraryServiceListener>();

	// The current method library.
	protected MethodLibrary currentLibrary;

	// The current method configuration.
	protected MethodConfiguration currentConfig;

	// If true, the current method library is being closed.
	private boolean closingCurrentLibrary;

	private LibraryProblemMonitor libraryProblemMonitor;
	/**
	 * Returns the shared instance.
	 */
	public static ILibraryService getInstance() {
		if (instance == null) {
			synchronized (LibraryService.class) {
				if (instance == null) {
					instance = new LibraryService();
				}
			}
		}
		return instance;
	}

	/**
	 * Creates a new instance.
	 */
	private LibraryService() {
		init();
	}

	/**
	 * Performs the necessary initialization.
	 */
	protected void init() {
		// Initialize the library manager factory to pre-process the
		// "org.eclipse.epf.library.libraryManagers" extension point.
		LibraryManagerFactory.getInstance();

	}

	/**
	 * Creates a new method library.
	 * 
	 * @param name
	 *            a name for the new method library
	 * @param type
	 *            the method library type
	 * @param args
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary createMethodLibrary(String name, String type,
			Map<String, Object> params) throws LibraryServiceException {
		if (name == null || type == null || params == null) {
			throw new IllegalArgumentException();
		}

		boolean reopenLibrary = LibraryService.getInstance()
				.getCurrentMethodLibrary() != null;

		try {
			LibraryService.getInstance().closeCurrentMethodLibrary();

			ILibraryManager manager = LibraryManagerFactory.getInstance()
					.createLibraryManager(type);
			MethodLibrary library = manager.createMethodLibrary(name, params);
			if (library != null) {
				// open newly created method library without sending out notification
				//
				library = manager.openMethodLibrary(manager.getMethodLibraryURI());

				setLibraryManager(manager);

				// Set the current library, do this before notifying the library
				// listeners.
				setCurrentMethodLibrary(library);

				// Save the library URI and type to preference store.
				saveMethodLibraryPreferences(manager.getMethodLibraryURI(),
						type);

				notifyListeners(library, EVENT_CREATE_LIBRARY);								
			}

			reopenLibrary = false;

			return library;
		} catch (LibraryServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new LibraryServiceException(e);
		} finally {
			if (reopenLibrary) {
				openLastOpenedMethodLibrary();
			}
		}
	}

	/**
	 * Opens an existing method library.
	 * 
	 * @param type
	 *            the method library type
	 * @param uri
	 *            the method library URI
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(String type, URI uri)
			throws LibraryServiceException {
		if (uri == null) {
			throw new IllegalArgumentException();
		}

		try {
			ILibraryManager manager = LibraryManagerFactory.getInstance()
					.createLibraryManager(type);
			MethodLibrary library = manager.openMethodLibrary(uri);
			if (library != null) {
				setLibraryManager(manager);

				// Set the current library, do this before notifying the library
				// listeners.
				setCurrentMethodLibrary(library);

				// Save the library URI and type to preference store.
				saveMethodLibraryPreferences(manager.getMethodLibraryURI(),
						type);

				notifyListeners(library, EVENT_OPEN_LIBRARY);
			}
			return library;
		} catch (CreateLibraryManagerException e) {
			throw e;
		} catch (Exception e) {
			throw new LibraryServiceException(e);
		}
	}

	/**
	 * Opens an existing method library.
	 * 
	 * @param type
	 *            the method library type
	 * @param params
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(String type,
			Map<String, Object> params) throws LibraryServiceException {
		if (params == null) {
			throw new IllegalArgumentException();
		}

		try {
			ILibraryManager manager = LibraryManagerFactory.getInstance()
					.createLibraryManager(type);
			MethodLibrary library = manager.openMethodLibrary(params);
			if (library != null) {
				setLibraryManager(manager);

				// Set the current library, do this before notifying the library
				// listeners.
				setCurrentMethodLibrary(library);

				// Save the library URI and type to preference store.
				saveMethodLibraryPreferences(manager.getMethodLibraryURI(),
						type);

				notifyListeners(library, EVENT_OPEN_LIBRARY);
			}
			return library;
		} catch (CreateLibraryManagerException e) {
			throw e;
		} catch (Exception e) {
			throw new LibraryServiceException(e);
		}
	}

	/**
	 * Opens the last opened method library.
	 * 
	 * @return a method library or <code>null</code>
	 */
	public MethodLibrary openLastOpenedMethodLibrary() {
		if (CommandLineRunUtil.getInstance().isNeedToRun()) {
			return null;
		}
		
		String savedMethodLibraryURI = LibraryPreferences
				.getSavedMethodLibraryURI();
		try {
			URI uri = new URI(savedMethodLibraryURI);
			if (uri.getPath().length() == 0) {
				return null;
			}

			String type = LibraryPreferences.getSavedMethodLibraryType();
			if (type == null || type.length() == 0) {
				return null;
			}

			return openMethodLibrary(type, uri);
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logWarning(
					"Unable to open the last opened method library '" //$NON-NLS-1$
							+ savedMethodLibraryURI + "'."); //$NON-NLS-1$
			LibraryPreferences.setSavedMethodLibraryURI(""); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Reopens a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary reopenMethodLibrary(MethodLibrary library)
			throws LibraryServiceException {
		ILibraryManager manager = getLibraryManager(library);
		if (manager != null) {
			try {
				removeLibraryManager(manager);
				library = manager.reopenMethodLibrary();

				// Re-register the library manager since the library instance
				// will be changed.
				setLibraryManager(manager);

				// Set the current library, do this before notifying the library
				// listeners.
				setCurrentMethodLibrary(library);

				notifyListeners(library, EVENT_REOPEN_LIBRARY);
				FileUtil.getValidateEdit().sychnConneciton();
			} catch (Exception e) {
				throw new LibraryServiceException(e);
			}
		}
		return null;
	}

	/**
	 * Reopens the current method library.
	 * 
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary reopenCurrentMethodLibrary()
			throws LibraryServiceException {
		return reopenMethodLibrary(currentLibrary);
	}

	/**
	 * Saves a method library.
	 * 
	 * @param library
	 *            a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void saveMethodLibrary(MethodLibrary library)
			throws LibraryServiceException {
		ILibraryManager manager = getLibraryManager(library);
		if (manager != null) {
			manager.saveMethodLibrary();
		}
	}

	/**
	 * Saves the current method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void saveCurrentMethodLibrary() throws LibraryServiceException {
		saveMethodLibrary(currentLibrary);
	}

	/**
	 * Closes a method library.
	 * <p>
	 * This automatically disposes its library manager and the configuration
	 * managers that manage the method configurations in the method library.
	 * 
	 * @param library
	 *            a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void closeMethodLibrary(MethodLibrary library)
			throws LibraryServiceException {
		ILibraryManager manager = getLibraryManager(library);
		if (manager != null) {
			notifyListeners(library, EVENT_CLOSE_LIBRARY);
			manager.closeMethodLibrary();
			if (currentLibrary == library) {
				setCurrentMethodLibrary(null);
				setCurrentMethodConfiguration(null);
			}
			removeLibraryManager(manager);
			manager.dispose();
		}
	}

	/**
	 * Closes the current method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public synchronized void closeCurrentMethodLibrary()
			throws LibraryServiceException {
		if (closingCurrentLibrary)
			return;
		try {
			closingCurrentLibrary = true;
			if (currentLibrary != null) {
				closeMethodLibrary(currentLibrary);
			}
		} finally {
			closingCurrentLibrary = false;
		}
	}

	/**
	 * Replaces a the method library.
	 * 
	 * @param oldLibrary
	 *            the old method library
	 * @param newLibrary
	 *            the new method library
	 */
	public void replaceMethodLibrary(MethodLibrary oldLibrary,
			MethodLibrary newLibrary) {
		ILibraryManager manager = getLibraryManager(oldLibrary);
		if (manager != null) {
			removeLibraryManager(manager);

			// Set the new library instance to prevent the library manager from
			// referencing the old library instance.
			manager.setMethodLibrary(newLibrary);

			setLibraryManager(manager);
		}
	}

	/**
	 * Adds a listener to monitor Library Service events.
	 * 
	 * @param listener
	 *            a library service listener
	 */
	public void addListener(ILibraryServiceListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes a listener that was added to monitor Library Service events.
	 * 
	 * @param listener
	 *            a library service listener
	 */
	public void removeListener(ILibraryServiceListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Gets the current method library.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getCurrentMethodLibrary() {
		return currentLibrary;
	}

	/**
	 * Sets the current method library.
	 * 
	 * @param library
	 *            a method library
	 */
	public void setCurrentMethodLibrary(MethodLibrary library) {
		if (currentLibrary == library) {
			return;
		}
		LibraryEditUtil.getInstance().fixUpDanglingCustomCategories(library);
		currentLibrary = library;
		if (library != null) {
//			long t = System.currentTimeMillis();
			ConfigurationHelper.getDelegate().fixupLoadCheckPackages(library);
//			System.out.println("LD> time: " +  (System.currentTimeMillis() - t));
			ConfigurationHelper.getDelegate().loadUserDefinedType();
		}
		notifyListeners(library, EVENT_SET_CURRENT_LIBRARY);
	}
	
	/**
	 * Gets the current method library location path.
	 * <p>
	 * Note: A file-based method library may return <code>null</code>.
	 * 
	 * @return an absolute path to the current method library
	 */
	public String getCurrentMethodLibraryLocation() {
		ILibraryManager manager = getLibraryManager(currentLibrary);
		if (manager != null) {
			return manager.getMethodLibraryLocation();
		} else {
			return null;
		}
	}

	/**
	 * Gets the library manager for a method library.
	 * 
	 * @param library
	 *            a method library
	 * @return a library manager
	 */
	public ILibraryManager getLibraryManager(MethodLibrary library) {
		return (ILibraryManager) libraryManagers.get(library);
	}

	public void removeLibraryManager(ILibraryManager libMgr) {
		if (libMgr != null) {
			MethodLibrary lib = libMgr.getMethodLibrary();
			if (lib != null) {
				removeConfigurationManagers(lib);
				libraryManagers.remove(lib);
			}
		}
	}

	public void setLibraryManager(ILibraryManager libMgr) {
		MethodLibrary lib = libMgr.getMethodLibrary();
		if (lib != null) {
			libraryManagers.put(lib, libMgr);
		}

	}

	/**
	 * Gets the library manager for the current method library.
	 * 
	 * @return a library manager
	 */
	public ILibraryManager getCurrentLibraryManager() {
		return getLibraryManager(currentLibrary);
	}

	/**
	 * Creates a new method configuration.
	 * 
	 * @param name
	 *            a name for the new method configuration
	 * @param library
	 *            the containing method library
	 * @return a method configuration
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodConfiguration createMethodConfiguration(String name,
			MethodLibrary library) throws LibraryServiceException {
		if (name == null || library == null) {
			throw new IllegalArgumentException();
		}

		MethodConfiguration config;
		List configs = library.getPredefinedConfigurations();
		for (Iterator it = configs.iterator(); it.hasNext();) {
			config = (MethodConfiguration) it.next();
			if (name.equals(config.getName())) {
				throw new ConfigurationAlreadyExistsException();
			}
		}

		config = UmaFactory.eINSTANCE.createMethodConfiguration();
		config.setName(name);
		configs.add(config);
		return config;
	}
	
	
	/**
	 * Gets the current method configuration.
	 * 
	 * @return a method configuration
	 */
	public MethodConfiguration getCurrentMethodConfiguration() {
		return currentConfig;
	}

	/**
	 * Sets the current method configuration.
	 * 
	 * @param config
	 *            a method configuration
	 */
	public void setCurrentMethodConfiguration(MethodConfiguration config) {
		currentConfig = config;
		notifyListeners(config, EVENT_SET_CURRENT_CONFIGURATION);
	}

	/**
	 * Gets the configuration manager for a method configuration.
	 * 
	 * @param config
	 *            a method configuration
	 * @return a configuration manager
	 */
	public synchronized IConfigurationManager getConfigurationManager(
			MethodConfiguration config) {
		if (config == null) {
			throw new IllegalArgumentException();
		}
//		if (config instanceof Scope || UmaUtil.getMethodLibrary(config) == null) {	//Don't cache it
//			return new ConfigurationManager(config);
//		}
		
		IConfigurationManager manager = (IConfigurationManager) configManagers
				.get(config);
		if (manager == null) {
			manager = new ConfigurationManager(config);
			configManagers.put(config, manager);
		}
		return manager;
	}

	public void removeConfigurationManager(MethodConfiguration config) {
		if (config == null) {
			throw new IllegalArgumentException();
		}

		IConfigurationManager mgr = (IConfigurationManager) configManagers
				.remove(config);
		if (mgr != null) {
			mgr.dispose();
		}
	}

	public void removeConfigurationManagers(MethodLibrary library) {
		if (library == null) {
			throw new IllegalArgumentException();
		}

		MethodConfiguration[] configs = LibraryServiceUtil
				.getMethodConfigurations(library);
		for (int i = 0; i < configs.length; i++) {
			removeConfigurationManager(configs[i]);
		}
		
		//Remove the rest
		if (configManagers != null && !configManagers.isEmpty()) {
			for (IConfigurationManager mgr : configManagers.values()) {
				if (mgr != null) {
					mgr.dispose();
				}
			}
			configManagers.clear();
		}
		
	}

	/**
	 * Gets the configuration manager for the current method configuration.
	 * 
	 * @return a configuration manager
	 */
	public IConfigurationManager getCurrentConfigurationManager() {
		if (currentConfig != null) {
			return getConfigurationManager(currentConfig);
		}
		return null;
	}

	/**
	 * Sends a method library related event to all library service listeners.
	 */
	protected void notifyListeners(final MethodLibrary library, int eventId) {
		for (Iterator<ILibraryServiceListener> it = new ArrayList<ILibraryServiceListener>(
				listeners).iterator(); it.hasNext();) {
			final ILibraryServiceListener listener = it.next();
			switch (eventId) {
			case EVENT_CREATE_LIBRARY:
				SafeUpdateController.asyncExec(new Runnable() {
					public void run() {
						listener.libraryCreated(library);
					}
				});
				break;
			case EVENT_OPEN_LIBRARY:
				SafeUpdateController.asyncExec(new Runnable() {
					public void run() {
						listener.libraryOpened(library);
					}
				});
				break;
			case EVENT_REOPEN_LIBRARY:
				SafeUpdateController.asyncExec(new Runnable() {
					public void run() {
						listener.libraryReopened(library);
					}
				});
				break;
			case EVENT_CLOSE_LIBRARY:
				SafeUpdateController.asyncExec(new Runnable() {
					public void run() {
						listener.libraryClosed(library);
					}
				});
				break;
			case EVENT_SET_CURRENT_LIBRARY:
				SafeUpdateController.asyncExec(new Runnable() {
					public void run() {
						listener.librarySet(library);
					}
				});
				break;
			}
		}
	}

	/**
	 * Sends a method configuration related event to all library service
	 * listeners.
	 */
	protected void notifyListeners(final MethodConfiguration config, int eventId) {
		for (Iterator<ILibraryServiceListener> it = new ArrayList<ILibraryServiceListener>(
				listeners).iterator(); it.hasNext();) {
			final ILibraryServiceListener listener = it.next();
			switch (eventId) {
			case EVENT_SET_CURRENT_CONFIGURATION:
				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						listener.configurationSet(config);
					}
				});
				break;
			}
		}
	}

	/**
	 * Saves the method library URI and type to preference store.
	 * 
	 * @param uri
	 *            the method library URI
	 * @param type
	 *            the menthod library type
	 */
	protected void saveMethodLibraryPreferences(URI uri, String type) {
		LibraryPreferences.setSavedMethodLibraryURI(uri.toString());
		LibraryPreferences.setSavedMethodLibraryType(type);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ILibraryService#registerMethodLibrary(org.eclipse.epf.uma.MethodLibrary, java.lang.String, java.util.Map)
	 */
	public void  registerMethodLibrary(MethodLibrary lib, String type,
			Map<String, Object> params) throws LibraryServiceException {
		//For now, still simply wraping up old implementation (copied from ConfigurationExportService) for smoother change; will definetly change later
		//so that creating a new lib would not be needed		
		ILibraryManager libMgr = LibraryManagerFactory.getInstance()
			.createLibraryManager(type);
		libMgr.registerMethodLibrary(lib, params);
		//libMgr.createMethodLibrary(name, params);
		//libMgr.setMethodLibrary(lib);
		LibraryService.getInstance().setLibraryManager(libMgr);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ILibraryService#unRegisterMethodLibrary(org.eclipse.epf.uma.MethodLibrary)
	 */
	public void unRegisterMethodLibrary(MethodLibrary lib) throws LibraryServiceException {
		//For now, still simply wraping up old implementation (copied from ConfigurationExportService) for smoother change
		ILibraryManager libMgr = getLibraryManager(lib);
		if (libMgr == null) {
			return;
		}
		removeLibraryManager(libMgr);
		//libMgr.closeMethodLibrary();
		libMgr.unRegisterMethodLibrary();
		libMgr.dispose();
	}
	
	public LibraryProblemMonitor getLibraryProblemMonitor() {
		if (libraryProblemMonitor == null) {
			libraryProblemMonitor = new LibraryProblemMonitor();
		}
		return 	libraryProblemMonitor;
	}

}
