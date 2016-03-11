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
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * The interface for a Method Configuration Manager.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IConfigurationManager {

	/**
	 * Gets the method configuration managed by this configuration manager.
	 * 
	 * @return a method configuration
	 */
	public MethodConfiguration getMethodConfiguration();
	
	/**
	 * Gets ConfigurationData object managed by this configuration manager.
	 * 
	 * @return a ConfigurationData object
	 */
	public ConfigurationData getConfigurationData();
	
	/**
	 * Gets SupportingElementData object managed by this configuration manager.
	 * 
	 * @return a SupportingElementData object
	 */
	public SupportingElementData getSupportingElementData();
	
	/**
	 * Gets ConfigurationProperties object managed by this configuration manager.
	 * 
	 * @return a ConfigurationProperties object
	 */
	public ConfigurationProperties getConfigurationProperties();

	/**
	 * Gets the containing method library for the managed method configuration.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getMethodLibrary();

	/**
	 * Gets the adapter factory.
	 * 
	 * @return an adapter factory
	 */
	public AdapterFactory getAdapterFactory();

	/**
	 * Gets the adapter factory content provider.
	 * 
	 * @return an adapter factory content provider
	 */
	public AdapterFactoryContentProvider getContentProvider();

	/**
	 * Gets the dependency manager.
	 * 
	 * @return a dependency manager
	 */
	public DependencyManager getDependencyManager();

	/**
	 * Returns the element layout manager.
	 * 
	 * @return an element layout manager
	 */
	public ElementLayoutManager getElementLayoutManager();

	/**
	 * Gets the configuration closure.
	 * 
	 * @return a configuration closure
	 */
	public ConfigurationClosure getConfigurationClosure();

	/**
	 * Makes a configuration closure.
	 */
	public void makeConfigurationClosure();

	/**
	 * Returns a list of method plug-ins in the containing method library.
	 * 
	 * @return a list of method plug-ins
	 */
	public List getMethodPlugins();
	
	/**
	 * @return an IRealizationManager instance
	 */
	IRealizationManager getRealizationManager();

	
	/**
	 * Disposes all resources allocated by this configuration manager.
	 */
	public void dispose();

}
