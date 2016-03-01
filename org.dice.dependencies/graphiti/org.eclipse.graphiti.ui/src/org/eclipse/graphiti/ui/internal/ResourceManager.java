/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.graphics.Resource;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ResourceManager implements IResourceManager {
	private static IResourceManager singleton;

	private HashMap<Object, List<Resource>> map = new HashMap<Object, List<Resource>>();

	/**
	 * Provides the resource manager.
	 * 
	 * @return the resource manager
	 * 
	 * @see org.eclipse.graphiti.ui.internal.IResourceManager
	 */
	public static IResourceManager getResourceManager() {
		if (singleton == null) {
			singleton = new ResourceManager();
		}

		return singleton;
	}

	public void cleanUpResources(Object obj) {

		if (obj == null) {
			return;
		}
		List<Resource> list = map.get(obj);
		if (list != null) {
			for (Resource resource : list) {
				if (resource != null && !resource.isDisposed()) {
					resource.dispose();
				}
			}
			list.clear();
		}
		map.remove(obj);
	}

	public void manageResource(Object obj, Resource resource) {
		if (obj == null || resource == null) {
			return;
		}
		List<Resource> list = map.get(obj);
		if (list == null) {
			list = new ArrayList<Resource>();
			map.put(obj, list);
		}
		list.add(resource);
	}
}
