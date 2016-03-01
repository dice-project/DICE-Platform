//------------------------------------------------------------------------------
// Copyright (c) 2005, 20087 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.configuration;

import org.eclipse.epf.library.configuration.closure.IConfigurationError;
import org.eclipse.epf.library.edit.util.MethodElementProperties;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 *  Class managing cached configuration properties
 * 
 * @author Weiping Lu - Mar 19, 2008
 * @since 1.5
 */
public class ConfigurationProperties extends MethodElementProperties {
	
	public ConfigurationProperties(MethodConfiguration config) {
		super(config, getPropStrings());		
	}
			
	private static String[] getPropStrings() {
		String[] props = { MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_ERRORS,
				MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_WARNINGS,
				MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_INFOS,
				MethodElementPropertyHelper.CONFIG_UPDATE_ON_CLICK,
				MethodElementPropertyHelper.CONFIG_NO_UPDATE_ON_CLICK};
		return props;
	}
	
	public boolean toHide(IConfigurationError error) {
		if (error.isError()) {
			return isHideErrors();
		} else if (error.isWarning()) {
			return isHideWarnings();
		} else {
			return isHideInfos();
		}
	}

	public boolean isHideWarnings() {
		return getBooleanValue(MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_WARNINGS);
	}
	
	public boolean isHideErrors() {
		return getBooleanValue(MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_ERRORS);
	}

	public boolean isHideInfos() {
		return getBooleanValue(MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_INFOS);
	}
	
	public boolean isUpdateOnClick() {
		return getBooleanValue(MethodElementPropertyHelper.CONFIG_UPDATE_ON_CLICK);
	}
	
	public boolean isNoUpdateOnClick() {
		boolean b = getBooleanValue(MethodElementPropertyHelper.CONFIG_NO_UPDATE_ON_CLICK);
		if (!b && !isUpdateOnClick()) {
			return true;
		}
		return b;
	}
		
}
