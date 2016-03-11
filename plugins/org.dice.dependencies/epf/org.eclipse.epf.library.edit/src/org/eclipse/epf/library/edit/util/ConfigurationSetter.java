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
package org.eclipse.epf.library.edit.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class ConfigurationSetter {
	private AdapterFactory adapterFactory;
	private IConfigurator configurator;
	private MethodConfiguration oldConfig;

	public ConfigurationSetter(AdapterFactory adapterFactory) {
		this.adapterFactory = adapterFactory;
	}
	
	public void set(MethodConfiguration config) {
		IFilter filter = ProcessUtil.getFilter(adapterFactory);
		configurator = filter instanceof IConfigurator ? (IConfigurator) filter : null;
		if(configurator != null) {
			oldConfig = configurator.getMethodConfiguration();
			configurator.setMethodConfiguration(config);
		}
	}
	
	public void restore() {
		if(configurator != null) {
			configurator.setMethodConfiguration(oldConfig);
		}
	}
	
	public IConfigurator getConfigurator() {
		return configurator;
	}
	
	public MethodConfiguration getOldConfiguration() {
		return oldConfig;
	}
}
