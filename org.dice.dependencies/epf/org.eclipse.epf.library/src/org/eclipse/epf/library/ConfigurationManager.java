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
package org.eclipse.epf.library;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationProperties;
import org.eclipse.epf.library.configuration.SupportingElementData;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.configuration.closure.DependencyManager;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.util.MethodElementPropertyMgr;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.realization.RealizationManagerFactory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.util.Scope;

/**
 * Manages a method configuration.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationManager implements IConfigurationManager {

	// If true, generate debug traces.
	protected static boolean debug = LibraryPlugin.getDefault().isDebugging();

	// The managed method configuraiton.
	protected MethodConfiguration config;
	
	// The managed configuraiton data.
	private ConfigurationData configData;
	
	private SupportingElementData supportingElementData;
	
	private ConfigurationProperties configProps;

	// The containing method library for the managed method configuration.
	protected MethodLibrary library;

	// The library manager for the containing method library.
	protected ILibraryManager libraryManager;

	// The dependency manager.
	protected DependencyManager dependencyManager;

	// The layout manager.
	protected ElementLayoutManager layoutManager;

	// The configuration closure.
	protected ConfigurationClosure closure;

	protected AdapterFactoryContentProvider afcp;
	
	private IRealizationManager realizationManager;

	/**
	 * Creates a new instance.
	 * 
	 * @param config
	 *            a method configuration
	 */
	public ConfigurationManager(MethodConfiguration config) {
		this.config = config;
		
		configData = ConfigurationData.newConfigurationData(config);
		if (! ConfigurationData.ignoreSupportingPlugin) {
			supportingElementData = configData.newSupportingElementData();
		}
				
		configProps = new ConfigurationProperties(config);
		MethodElementPropertyMgr.getInstance().register(config, configProps);

		if (config instanceof Scope) {
			library = LibraryService.getInstance().getCurrentMethodLibrary();
		} else {
			library = LibraryServiceUtil.getMethodLibrary(config);
			if (library == null) {
				library = LibraryService.getInstance().getCurrentMethodLibrary();
			}
		}

		libraryManager = LibraryService.getInstance()
				.getLibraryManager(library);
		afcp = new AdapterFactoryContentProvider(libraryManager
				.getAdapterFactory());

		dependencyManager = new DependencyManager(library, config);

		if (config == null) {
			layoutManager = new ElementLayoutManager();
		} else {
			layoutManager = new ElementLayoutManager(config);
			// closure = new ConfigurationClosure(this, config);
			realizationManager = RealizationManagerFactory.getInstance()
					.newRealizationManager(config);

		}
	}

	/**
	 * Gets the method configuration managed by this configuration manager.
	 * 
	 * @return a method configuration
	 */
	public MethodConfiguration getMethodConfiguration() {
		return config;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.IConfigurationManager#getConfigurationData()
	 */
	public ConfigurationData getConfigurationData() {
		return configData;
	}
	
	public SupportingElementData getSupportingElementData() {
		return supportingElementData;
	}
	
	/**
	 * Gets the containing method library for the managed method configuration.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getMethodLibrary() {
		return library;
	}

	/**
	 * Gets the adapter factory.
	 * 
	 * @return an adapter factory
	 */
	public AdapterFactory getAdapterFactory() {
		return libraryManager.getAdapterFactory();
	}

	/**
	 * Gets the adapter factory content provider.
	 * 
	 * @return an adapter factory content provider
	 */
	public AdapterFactoryContentProvider getContentProvider() {
		return afcp;
	}

	/**
	 * Gets the dependency manager.
	 * 
	 * @return a dependency manager
	 */
	public DependencyManager getDependencyManager() {
		return dependencyManager;
	}

	/**
	 * Returns the element layout manager.
	 * 
	 * @return an element layout manager
	 */
	public ElementLayoutManager getElementLayoutManager() {
		if (layoutManager != null) {
			layoutManager.buildPublishDir(null);
		}
		return layoutManager;
	}

	/**
	 * Gets the configuration closure.
	 * 
	 * @return a configuration closure
	 */
	public ConfigurationClosure getConfigurationClosure() {
		return closure;
	}

	/**
	 * Makes a configuration closure.
	 */
	public void makeConfigurationClosure() {
		if (closure != null) {
			closure.fixErrors();
		}
	}

	/**
	 * Returns a list of method plug-ins in the containing method library.
	 * 
	 * @return a list of method plug-ins
	 */
	public List getMethodPlugins() {
		return library.getMethodPlugins();
	}

	/**
	 * Disposes all resources allocated by this configuration manager.
	 */
	public void dispose() {
		configData.dispose();
		configData = null;
		config = null;
		library = null;
		libraryManager = null;
		dependencyManager = null;
		if (layoutManager != null) {
			layoutManager.clear();
		}
		layoutManager = null;
		closure = null;
		MethodElementPropertyMgr.getInstance().unregister(config);
		configProps = null;
		
		if (realizationManager != null) {
			realizationManager.dispose();
			realizationManager = null;
		}
	}

	public ConfigurationProperties getConfigurationProperties() {
		return configProps;
	}
	
	/**
	 * @return an IRealizationManager instance
	 */
	public IRealizationManager getRealizationManager() {
		return realizationManager;
	}

}
