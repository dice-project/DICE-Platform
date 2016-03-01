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
package org.eclipse.epf.library.configuration;

import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ProcessVariabilityConfigurator extends ProcessConfigurator {

	/**
	 * @param methodConfig
	 * @param viewer
	 */
	public ProcessVariabilityConfigurator(MethodConfiguration methodConfig) {
		super(methodConfig);
	}

	public void setMethodConfiguration(MethodConfiguration newConfig) {
		methodConfig = newConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.configuration.ProcessConfigurator#accept(org.eclipse.epf.uma.Descriptor)
	 */
	protected boolean accept(BreakdownElement e) {
		if (e instanceof Activity) {
			Activity base = (Activity) ((Activity) e)
					.getVariabilityBasedOnElement();
			if (base == null) {
				return ConfigurationHelper.inConfig(
						TngUtil.getOwningProcess(e), methodConfig);
			} else {
				return ConfigurationHelper.inConfig(TngUtil
						.getOwningProcess(base), methodConfig);
			}
		}

		return super.accept(e);
	}
}
