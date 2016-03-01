//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.xmi.internal.migration;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epf.library.layout.LinkInfo;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Process;

/**
 * @author Weiping Lu - July 18, 2008
 * @since 1.5
 */
public class Migrator104 extends MigratorBase {

	public void migrate(String libPath, IProgressMonitor monitor)
			throws Exception {
		migrate(libPath, monitor, null);
	}

	public void migrate(String libPath, IProgressMonitor monitor,
			UpgradeCallerInfo info) throws Exception {
		setCallerInfo(info);
		
		File libFile = new File(libPath);
		boolean toVerify = true;
		if (info != null && info.getIsExportedPluginLib()) {
			toVerify = false;
		}

		ResourceUtil.open(libFile.getParent(), monitor);

		MultiFileResourceSetImpl resourceSet = null;
		if (info != null) {
			resourceSet = info.getResourceSet();
		}
		try {
			// load the library
			//
			updateStatus(monitor, PersistenceResources.loadLibraryTask_name);

			if (resourceSet == null) {
				if (toVerify) {
					resourceSet = new MultiFileResourceSetImpl(false);
				} else {
					resourceSet = PersistenceUtil.getImportPluginResourceSet();
				}
			}

			resourceSet.getLoadOptions().put(
					XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

			MethodLibrary lib = info == null || info.getResourceSet() == null ? resourceSet
					.loadLibrary(libPath)
					: info.loadLibrary(libFile);

			LinkInfo.setLibrary(lib);
			// load all elements in memory
			//
			updateStatus(monitor, PersistenceResources.loadResourcesTask_name);

			updateAllContents(monitor, lib);

			// save all files
			//
			updateStatus(monitor, PersistenceResources.saveLibraryTask_name);
			Map saveOptions = resourceSet.getDefaultSaveOptions();
			resourceSet.save(saveOptions, true);

			updateStatus(monitor,
					PersistenceResources.refreshLibraryFilesTask_name);

			ResourceUtil.refreshResources(lib, monitor);

			ResourceUtil.refreshResources(lib, monitor);

		} finally {
			LinkInfo.setLibrary(null);

			if (resourceSet != null) {
				resourceSet.reset();
				resourceSet = null;
			}
		}

	}

	protected void updateElement(MethodElement element, IProgressMonitor monitor)
			throws Exception {

	}

}