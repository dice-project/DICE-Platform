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
package org.eclipse.epf.library.edit;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.epf.library.edit.internal.TngAdapterFactoryImpl;

/**
 * The factory for all the adapter factories.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface TngAdapterFactory {

	public static final TngAdapterFactory INSTANCE = TngAdapterFactoryImpl.createAdapterFactoryProvider();
	
	public static final AdapterFactory[] processAdapterFactories = {
		INSTANCE.getWBS_ComposedAdapterFactory(),
		INSTANCE.getOBS_ComposedAdapterFactory(),
		INSTANCE.getPBS_ComposedAdapterFactory(),
		INSTANCE.getProcessComposedAdapterFactory()
	};

	/**
	 * Gets the composed adapter factory for the Library view.
	 * 
	 * @return the composed adapter factory for the Library view
	 */
	public ComposedAdapterFactory getNavigatorView_ComposedAdapterFactory();

	/**
	 * Gets the adapter factory for the Configuration view.
	 * 
	 * @param filter
	 *            a method element filter
	 * @return the adapter factory for the Configuration view
	 */
	public AdapterFactory getConfigurationView_AdapterFactory(IFilter filter);

	/**
	 * Creates a new adapter factory for the Work Breakdown Structure. Client is
	 * responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the Work Breakdown Structure
	 */
	public ComposedAdapterFactory createWBSComposedAdapterFactory();

	/**
	 * Creates a new adapter factory for the Team Breakdown Structure. Client is
	 * responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the Team Breakdown Structure
	 */
	public ComposedAdapterFactory createTBSComposedAdapterFactory();

	/**
	 * Creates a new adapter factory for Work Product Breakdown Structure.
	 * Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the Work Product Breakdown Structure
	 */
	public ComposedAdapterFactory createWPBSComposedAdapterFactory();

	/**
	 * Creates a new adapter factory for the Consolidated Breakdown Structure.
	 * Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the Consolidated Breakdown Structure
	 */
	public ComposedAdapterFactory createProcessComposedAdapterFactory();

	/**
	 * Creates a new adapter factory for the published Work Breakdown Structure.
	 * Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the published Work Breakdown Structure
	 */
	public ComposedAdapterFactory createPublishingWBSAdapterFactory();

	/**
	 * Creates a new adapter factory for published Team Breakdown Structure.
	 * Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the published Team Breakdown Structure
	 */
	public ComposedAdapterFactory createPublishingTBSAdapterFactory();

	/**
	 * Creates a new adapter factory for published Work Product Breakdown
	 * Structure. Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the published Work Product Breakdown
	 *         Structure
	 */
	public ComposedAdapterFactory createPublishingWPBSAdapterFactory();

	/**
	 * Creates a new adapter factory for the published Consolidated Breakdown
	 * Structure. Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the published Consolidated Breakdown
	 *         Structure
	 */
	public ComposedAdapterFactory createPublishingCBSAdapterFactory();

	/**
	 * Creates a new adapter factory for the library.
	 * Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the library
	 */
	public ComposedAdapterFactory createLibraryComposedAdapterFactory();
	
	/**
	 * Creates a new adapter factory for the library to be used in configuration page.
	 * Client is responsible for disposing it after use by calling
	 * {@linkplain ComposedAdapterFactory#dispose()}
	 * 
	 * @return an adapter factory for the library
	 */
	public ComposedAdapterFactory createConfigPage_LibraryComposedAdapterFactory();
	
	/**
	 * Gets the singleton composed adapter factory for the Work Product
	 * Breakdown Structure editor.
	 * 
	 * @return the adapter factory for the Work Product Breakdown Structure
	 *         editor
	 */
	public ComposedAdapterFactory getWBS_ComposedAdapterFactory();

	/**
	 * Gets the singleton composed adapter factory for the Team Breakdown
	 * Structure editor.
	 * 
	 * @return the adapter factory for the Team Breakdown Structure editor
	 */
	public ComposedAdapterFactory getOBS_ComposedAdapterFactory();

	/**
	 * Gets the singleton composed adapter factory for Work Product Breakdown
	 * Structure editor.
	 * 
	 * @return the adapter factory for the Work Product Breakdown Structure
	 *         editor
	 */
	public ComposedAdapterFactory getPBS_ComposedAdapterFactory();

	/**
	 * Gets the singleton composed adapter factory for the Consolidated
	 * Breakdown Structure editor.
	 * 
	 * @return the adapter factory for the Consolidated Breakdown Structure
	 *         editor
	 */
	public ComposedAdapterFactory getProcessComposedAdapterFactory();

	/**
	 * Gets the singleton composed adapter factory for the Configuration view.
	 * 
	 * @return the adapter factory for the Configuration view
	 */
	public ComposedAdapterFactory getConfigurationView_ComposedAdapterFactory();

	/**
	 * Gets adapter factory for ItemsFilter package. Either
	 * getFilterView_AdapterFactory(IFilter filter) or
	 * getItemsFilter_AdapterFactory(IFilter filter) should be used. Both can be
	 * used at the same time depends on purpose.
	 */
	public AdapterFactory getItemsFilter_AdapterFactory(IFilter filter);

	/**
	 * Gets an adapter factory for the Team Breakdown Structure filter.
	 * 
	 * @param filter
	 *            a method element filter
	 * @return an adapter factory for the Team Breakdown Structure filter
	 */
	public AdapterFactory getOBSFilter_AdapterFactory(IFilter filter);

	/**
	 * Gets an adapter factory for the Work Product Breakdown Structure filter.
	 * 
	 * @param filter
	 *            a method element filter
	 * @return an adapter factory for the Work Product Breakdown Structure
	 *         filter
	 */
	public AdapterFactory getPBSFilter_AdapterFactory(IFilter filter);

	/**
	 * Gets the singleton composed adapter factory for the Team Breakdown
	 * Structure filter.
	 * 
	 * @return the adapter factory for the Team Breakdown Structure filter
	 */
	public ComposedAdapterFactory getOBSFilter_ComposedAdapterFactory();

	/**
	 * Gets the singleton composed adapter factory for the Work Product
	 * Breakdown Structure filter.
	 * 
	 * @return the adapter factory for the Work Product Breakdown Structure
	 *         filter
	 */
	public ComposedAdapterFactory getPBSFilter_ComposedAdapterFactory();

	/**
	 * Gets the singleton composed adapter factory for the item filter.
	 * 
	 * @return the adapter factory for the item filter
	 */
	public ComposedAdapterFactory getItemsFilter_ComposedAdapterFactory();

	/**
	 * Resets the adapter factories.
	 */
	public void reset();

	/**
	 * Releases unused objects.
	 */
	public void cleanUp();

}
