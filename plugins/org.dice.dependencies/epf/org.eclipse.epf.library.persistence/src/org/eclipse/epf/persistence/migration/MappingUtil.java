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
package org.eclipse.epf.persistence.migration;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.osgi.framework.Bundle;

/**
 * Utility class with static convenience methods to perform library upgrade from
 * older version.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class MappingUtil {

	private static class Migrator {
		Bundle bundle;

		String className;

		String libNsURI;

		IMigrator instance;

		/**
		 * @param bundle
		 * @param className
		 * @param libNsURI
		 */
		public Migrator(Bundle bundle, String className, String libNsURI) {
			super();
			this.bundle = bundle;
			this.className = className;
			this.libNsURI = libNsURI;
		}
	}

	private final static boolean localDebug = false;
	private static Map migrators;
	private static Set oldNsUris = new HashSet();
	private static Map<String, String> adjustedLibNsURIMap = new HashMap<String,String>();

	public static final IMigrator getMigrator(String libNsURI) {
		if (migrators == null) {
			migrators = new HashMap();
			// Process the "org.eclipse.epf.library.persistence.migrators"
			// extension point
			// contributors.
			IExtensionRegistry extensionRegistry = Platform
					.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry
					.getExtensionPoint(
							"org.eclipse.epf.library.persistence", "migrators"); //$NON-NLS-1$ //$NON-NLS-2$
			if (extensionPoint != null) {
				IExtension[] extensions = extensionPoint.getExtensions();
				for (int i = 0; i < extensions.length; i++) {
					IExtension extension = extensions[i];
					String pluginId = extension.getNamespaceIdentifier();
					Bundle bundle = Platform.getBundle(pluginId);
					IConfigurationElement[] configElements = extension
							.getConfigurationElements();
					for (int j = 0; j < configElements.length; j++) {
						IConfigurationElement configElement = configElements[j];
						try {
							String className = configElement
									.getAttribute("class"); //$NON-NLS-1$
							String nsURI = configElement
									.getAttribute("libNsURI"); //$NON-NLS-1$
							if (className != null
									&& className.trim().length() > 0
									&& nsURI != null
									&& nsURI.trim().length() > 0) {
								migrators.put(nsURI, new Migrator(bundle,
										className, nsURI));
							}
						} catch (Exception e) {
							CommonPlugin.INSTANCE.log(e);
						}
					}
				}
			}
		}
		Migrator migrator = (Migrator) migrators.get(libNsURI);
		if (migrator != null) {
			if (migrator.instance == null) {
				try {
					migrator.instance = (IMigrator) migrator.bundle.loadClass(
							migrator.className).newInstance();
				} catch (Exception e) {
					CommonPlugin.INSTANCE.log(e);
				}
			}
			return migrator.instance;
		}
		return null;
	}

	public synchronized static final boolean conversionRequired(String libPath,
			VersionUtil.VersionCheckInfo info) {					
		String currentNsURI = PersistenceUtil.getUMANsURI();
		String libNsURI = PersistenceUtil.getUMANsURI(libPath);
		libNsURI = getAdjustedLibNsURI(libNsURI, info);

		if (oldNsUris.contains(libNsURI)) {
			return true;
		}
		if (currentNsURI.equals(libNsURI)) {
			return false;
		}
		boolean ret = getMigrator(libNsURI) != null;
		if (ret) {
			oldNsUris.add(libNsURI);
		}
		return ret;
	}
	
	//This is an ugly piece, but we need it for migrating EPF 1.1.0
	private static String getAdjustedLibNsURI(String libNsURI, VersionUtil.VersionCheckInfo info) {
		String ret = getAdjustedLibNsURI_(libNsURI, info);
		if (ret != libNsURI) {
			if (localDebug) {
				System.out.println("LD> libNsURI: " + libNsURI);	//$NON-NLS-1$ 
				System.out.println("LD> adjustedLibNsURI: " + ret);	//$NON-NLS-1$
			}
			adjustedLibNsURIMap.put(libNsURI, ret);
		}
		return ret;
	}
	private static String getAdjustedLibNsURI_(String libNsURI, VersionUtil.VersionCheckInfo info) {
		String currentNsURI = PersistenceUtil.getUMANsURI();
		if (! currentNsURI.equals(libNsURI)) {
			return libNsURI;
		} else if (! libNsURI.equals("http://www.eclipse.org/epf/uma/1.0.4/uma.ecore")) { //$NON-NLS-1$ 
			return libNsURI;
		}
		if (info.toolVersion.equals(info.currentMinToolVersion)) {
			return libNsURI;
		}
		return "http://www.eclipse.org/epf/uma/1.0.3/uma.ecore";	//$NON-NLS-1$ 
	}

	public static void migrate(String libPath, IProgressMonitor monitor, UpgradeCallerInfo info)
			throws Exception {
		IMigrator migrator = getMigratorByLibPath(libPath);
		if (migrator == null && info.getUpgradableFiles() != null && !
				info.getUpgradableFiles().isEmpty()) {
			File upgradableFile = info.getUpgradableFiles().get(0);
			migrator = getMigratorByLibPath(upgradableFile.getAbsolutePath());
		}
		if (migrator != null) {
			migrator.migrate(libPath, monitor, info);
		}
	}
	
	public static IMigrator getMigratorByLibPath(String libPath) {
		String nsURI = PersistenceUtil.getUMANsURI(libPath);
		String adjustedLibNsURI = adjustedLibNsURIMap.get(nsURI);
		if (adjustedLibNsURI != null) {
			nsURI = adjustedLibNsURI;
		}
		return getMigrator(nsURI);
	}

	public static void main(String[] args) {
		String libPath = args[0];
		System.out
				.println("Start migrating method library at '" + libPath + "'..."); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			migrate(libPath, null, null);
			System.out.println();
			System.out.println("Migration successfull."); //$NON-NLS-1$
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println();
			System.err.println("Migration failed."); //$NON-NLS-1$
		}
	}

}
