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
package org.eclipse.epf.importing;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.common.ui.AbstractPlugin;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.FileUtil.FileChecker;
import org.eclipse.epf.importing.services.FileModifyChecker;
import org.osgi.framework.BundleContext;


/**
 * The Import plug-in class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ImportPlugin extends AbstractPlugin {

	// The shared plug-in instance.
	private static ImportPlugin plugin;

	/**
	 * Default constructor.
	 */
	public ImportPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see AbstractPlugin#start(BundleContext context)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		FileChecker fileChecker = new FileChecker() {
			public IStatus syncExecCheckModify(List modifiedFiles) {
				return FileModifyChecker.syncExecCheckModify(modifiedFiles);
			}
		};		
		FileUtil.setFileChecker(fileChecker);		
	}

	/**
	 * @see AbstractPlugin#start(BundleContext context)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static ImportPlugin getDefault() {
		return plugin;
	}

}
