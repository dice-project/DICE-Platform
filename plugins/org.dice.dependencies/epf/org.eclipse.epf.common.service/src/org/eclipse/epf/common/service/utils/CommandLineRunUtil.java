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
package org.eclipse.epf.common.service.utils;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Utility class for excuting a ICommandLineRunner object's execute method
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class CommandLineRunUtil {
	
	private static CommandLineRunUtil instance = new CommandLineRunUtil();
	private boolean needToRun = false;
	private boolean neverExcuted = true;
	
	public static CommandLineRunUtil getInstance() {
		return instance;
	}
	
	public CommandLineRunUtil() {		
	}	
	
	public boolean execute(String[] args) {
		setNeverExcuted(false);
		//For general case, args should be parsed here to call on right runnerId.
		//for now, just hard code it with "commandLinePluginImporter"
		executeCommandRunner(args, "commandLinePluginImporter");	//$NON-NLS-1$ 
		return false;
	}
	
	protected boolean executeCommandRunner(String[] args, String runnerId) {
		ICommandLineRunner runner = getRunner(runnerId);
		if (runner != null) {
			return runner.execute(args);
		}
		return false;
	}
	
	private static final ICommandLineRunner getRunner(String id) {
		IExtensionRegistry extensionRegistry = Platform
				.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry
				.getExtensionPoint(
						"org.eclipse.epf.common.service", "commandLineRunners"); //$NON-NLS-1$ //$NON-NLS-2$			
		if (extensionPoint == null) {
			return null;
		}
		
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			String pluginId = extension.getNamespaceIdentifier();
			Bundle bundle = Platform.getBundle(pluginId);
			IConfigurationElement[] configElements = extension
					.getConfigurationElements();
			for (int j = 0; j < configElements.length; j++) {
				IConfigurationElement configElement = configElements[j];
				String runnerId = configElement.getAttribute("runnerId"); //$NON-NLS-1$
				if (runnerId == null) {
					continue;
				}
				runnerId = runnerId.trim();
				if (! runnerId.equals(id)) {
					continue;
				}
				String className = configElement.getAttribute("class"); //$NON-NLS-1$
				if (className == null) {
					continue;
				}
				className = className.trim();
				try {
					ICommandLineRunner runner = (ICommandLineRunner) bundle.loadClass(className).newInstance();
					return runner;
				} catch (Exception e) {					
				}
			}
		}	

		return null;
	}

	public boolean isNeedToRun() {
		return needToRun;
	}

	public void setNeedToRun(boolean needToRun) {
		this.needToRun = needToRun;
	}

	public static void setInstance(CommandLineRunUtil instance) {
		CommandLineRunUtil.instance = instance;
	}

	public boolean isNeverExcuted() {
		return neverExcuted;
	}

	protected void setNeverExcuted(boolean neverExcuted) {
		this.neverExcuted = neverExcuted;
	}

	
}
