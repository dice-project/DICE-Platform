/*******************************************************************************
 * Copyright (c) 2008 IBM, TietoEnator, corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Brian Schlosser - initial implementation
 *  Roman Smirak  - update for EPFC 1.2 and 1.5
 *******************************************************************************/ 
package org.eclipse.epf.publishing.cmdline;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.epf.authoring.ui.AuthoringUIService;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.ui.PreferenceStoreWrapper;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

public class CmdlinePlugin extends Plugin {

	//The shared instance.
	private static CmdlinePlugin plugin;
	private ScopedPreferenceStore preferenceStore;
	
	public void start(BundleContext context) throws Exception {
		super.start(context);
        plugin = this;

        // FIXME: EPFC1.5 requires following initialisation
        CommonPlugin.getDefault();
        PreferenceStoreWrapper storeWrapper = new PreferenceStoreWrapper(getPreferenceStore());
		CommonPlugin.getDefault().setCommonPreferenceStore(storeWrapper);
		// Initialise the Authoring UI service.
		AuthoringUIService.getInstance().start();
	}

    public IPreferenceStore getPreferenceStore() {
        // Create the preference store lazily.
        if (preferenceStore == null) {
            preferenceStore = new ScopedPreferenceStore(new InstanceScope(),getBundle().getSymbolicName());

        }
        return preferenceStore;
    }

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	public static CmdlinePlugin getDefault() {
		return plugin;
	}

    public static void log(Throwable t) {
        if (plugin == null) {
            t.printStackTrace();
        } else {
            ILog log = plugin.getLog();
            String message = t.getMessage();
            if(message == null) {
                message = t.getClass().toString();
            }
            log.log(new Status(IStatus.ERROR, plugin.getBundle()
                    .getSymbolicName(), 1, message, t));
        }
    }

    public static void log(String message) {
        if (plugin == null) {
            System.out.println(message);
        } else {
            ILog log = plugin.getLog();
            log.log(new Status(IStatus.INFO, plugin.getBundle()
                    .getSymbolicName(), 1, message, null));
        }
    }
}
