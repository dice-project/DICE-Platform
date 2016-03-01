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
package org.eclipse.epf.importing.wizards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.osgi.util.NLS;

/**
 * Helper class to construct a message that lists all the locked or read-only
 * method plug-ins in the current method library.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PluginModifyInfo {

	public List<MethodPlugin> lockedPlugins = new ArrayList<MethodPlugin>();

	public List<MethodPlugin> readonlyPlugins = new ArrayList<MethodPlugin>();

	/**
	 * Creates a new instance.
	 */
	public PluginModifyInfo() {
	}

	/**
	 * Gets a message that lists all the locked method plug-ins.
	 * 
	 * @return a <code>StringBuffer<code> containing the message
	 */
	public StringBuffer getLockedMessage() {
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		for (Iterator it = lockedPlugins.iterator(); it.hasNext();) {
			i++;
			MethodPlugin plugin = (MethodPlugin) it.next();
			if (i > 20) {
				String msg = "..."; //$NON-NLS-1$
				buffer.append(msg);
				break;
			}
			String msg = NLS
					.bind(
							ImportResources.SelectImportConfigurationSource_plugin_locked,
							plugin.getName());
			buffer.append(msg);
		}

		return buffer;
	}

	/**
	 * Gets a message that lists all the read-only method plug-ins.
	 * 
	 * @return a <code>StringBuffer<code> containing the message
	 */
	public StringBuffer getReadonlyMessage() {
		StringBuffer buffer = new StringBuffer();
		for (Iterator it = readonlyPlugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			String msg = NLS
					.bind(
							ImportResources.SelectImportConfigurationSource_plugin_readonly,
							plugin.getName());
			buffer.append(msg);
		}

		return buffer;
	}

}
