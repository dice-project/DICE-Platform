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
package org.eclipse.epf.authoring.ui;

import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.gef.util.DiagramEditorUtil;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.providers.DescriptionPageColumnProvider;
import org.eclipse.epf.common.ui.PreferenceStoreWrapper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationApplicator;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IConfiguratorFactory;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.util.CopyAttachmentsToNewLocation;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * The Authoring UI service.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AuthoringUIService {

	// The shared instance.
	private static AuthoringUIService instance = new AuthoringUIService();

	// The status flag.
	private boolean started = false;

	/**
	 * Creates a new instance.
	 */
	private AuthoringUIService() {
		LibraryService.getInstance().addListener(new ILibraryServiceListener() {
			public void libraryCreated(MethodLibrary library) {
			}

			public void libraryOpened(MethodLibrary library) {
			}

			public void libraryReopened(MethodLibrary library) {
			}
			
			public void libraryClosed(MethodLibrary library) {
			}			

			public void librarySet(MethodLibrary library) {
				if (library != null) {
					start();
				}
				LibraryUIUtil.updateShellTitle();
			}
			
			public void configurationSet(MethodConfiguration config) {
			}			
		});		
	}

	/**
	 * Returns the shared instance.
	 */
	public static AuthoringUIService getInstance() {
		return instance;
	}

	/**
	 * Starts the Authoring UI service.
	 */
	public synchronized void start() {
		if (!started) {	
			LibraryUIPreferences.applyDefaultValuesForBreakdownElementAttributes();

			// Initialize ProcessAuthoringConfigurator
			ProcessAuthoringConfigurator.INSTANCE.getClass();
			
			// Set configurator for WBS adapter factory if it's not set yet
			if(TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory() instanceof ConfigurableComposedAdapterFactory) {
				ConfigurableComposedAdapterFactory adapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
				if(adapterFactory.getFilter() == null) {
					adapterFactory.setFilter(ProcessAuthoringConfigurator.INSTANCE);
				}
			}

			// Initialize the move/paste command.
			MethodElementAddCommand
					.setResourceManager(new CopyAttachmentsToNewLocation());

			// Initialize the EditorChooser.
			EditorChooser.getInstance();

			// Load EditorPage Providers
			DescriptionPageColumnProvider.getInstance().loadProviders();

			// Set providers for library edit.
			Providers.setConfiguratorFactory(new IConfiguratorFactory() {
				public IConfigurator createConfigurator(
						MethodConfiguration config) {
					return new ProcessConfigurator(config, true);
				}
			});

			Providers
					.setConfigurationApplicator(new ConfigurationApplicator());

			Providers.setPreferenceStore(LibraryPlugin.getDefault()
					.getPreferenceStore());

			PreferenceStoreWrapper store = new PreferenceStoreWrapper(AuthoringUIPlugin.getDefault().getPreferenceStore());
			Providers.setAuthoringPluginPreferenceStore(store);

			List<ICommandListener> cmdListeners = GraphicalDataManager.getInstance()
					.getCommandListeners();
			cmdListeners.addAll(DiagramEditorUtil.getInstance()
					.getVaryCommandListeners());
			for (Iterator<ICommandListener> iter = cmdListeners.iterator(); iter.hasNext();) {
				ICommandListener listener = iter.next();
				Providers.registerCommandListener(listener);
			}

			RefreshJob.getInstance().start();
			
			started = true;
		}
	}

	/**
	 * Stops the Authoring UI service.
	 */
	public synchronized void stop() {
		if (started) {
			RefreshJob.getInstance().stop();			
			started = false;
		}
	}

}
