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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epf.uma.MethodPlugin;

/**
 * Holds a map of Plugin Package names to the list of plugins contained by them
 * @author Jeff Hardy
 *
 */
public class PluginUIPackagesMap extends HashMap<String, Set<MethodPlugin>> {

	private static final long serialVersionUID = -8321379015870090256L;

	public PluginUIPackagesMap() {
		super();
	}
	
	public void add(String packageName, MethodPlugin plugin) {
		Set<MethodPlugin> plugins = get(packageName);
		if (plugins == null) {
			plugins = new HashSet<MethodPlugin>();
		}
		plugins.add(plugin);
		put(packageName, plugins);
	}
}
