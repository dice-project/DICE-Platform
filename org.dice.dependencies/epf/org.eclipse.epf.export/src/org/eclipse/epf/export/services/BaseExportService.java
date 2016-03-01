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
package org.eclipse.epf.export.services;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The abstract base class for the Export Service.
 * 
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public abstract class BaseExportService {

	protected static final String exportFile = "export.xmi"; //$NON-NLS-1$

	protected static final String libraryFile = "library.xmi"; //$NON-NLS-1$

	protected List selectedIds = new ArrayList();

	protected List selectedPluginNames = new ArrayList();

	protected boolean isIdSelected(String uri) {
		for (Iterator it = selectedIds.iterator(); it.hasNext();) {
			String id = (String) it.next();
			if (uri.indexOf(id) >= 0) {
				return true;
			}
		}
		return false;
	}

	protected boolean isSelectedPluginResource(String uri) {
		for (Iterator it = selectedPluginNames.iterator(); it.hasNext();) {
			String plugin_name = (String) it.next();
			if (uri.startsWith(plugin_name + "/")) //$NON-NLS-1$
			{
				return true;
			}
		}

		return false;
	}

	protected boolean isResourceSelected(String uri) {
		if (isIdSelected(uri)) {
			return true;
		}
		return isSelectedPluginResource(uri);
	}

	protected LibraryDocument processLibraryFile(File source, File target) {
		try {
			LibraryDocument document = new LibraryDocument(source);

			// Reload the element mapping.
			NodeList nodes = document.getPlugins();
			int i = 0;
			while (i < nodes.getLength()) {
				Element node = (Element) nodes.item(i);
				String uri = node.getAttribute(LibraryDocument.ATTR_href);
				if (!isIdSelected(uri)) {
					document.removePlugin(node);
				} else {
					i++;
				}
			}

			// Remove the unneeded configurations.
			nodes = document.getConfigurations();
			i = 0;
			while (i < nodes.getLength()) {
				Element node = (Element) nodes.item(i);
				
				// 142379 - update plugin importing and exporting to match with the new file format
				// configuration is saved as seperate resource since 1.0m3
				String uri = node.getAttribute(LibraryDocument.ATTR_href);
				if (!isIdSelected(uri)) {
					document.removeConfiguration(node);
				} else {
					i++;
				}
			}

			// Reload the element mapping.
			List plugins = LibraryService.getInstance().getCurrentMethodLibrary()
								.getMethodPlugins();
			Map pluginMap = MethodElementUtil.buildMap(plugins);

			nodes = document.getResourceDescriptors();
			i = 0;
			while (i < nodes.getLength()) {
				Element node = (Element) nodes.item(i);
				String id = node.getAttribute(LibraryDocument.ATTR_id);
				String uri = getPluginResMgrUri(id, node, pluginMap);
				if (!isIdSelected(id)
						&& !isResourceSelected(URLDecoder.decode(uri, "UTF-8"))) { //$NON-NLS-1$
					document.removeResourceDescriptor(node);
				} else {
					i++;
				}
			}

			nodes = document.getResourceSubManagers();
			i = 0;
			while (i < nodes.getLength()) {
				Element node = (Element) nodes.item(i);
				String uri = node.getAttribute(LibraryDocument.ATTR_href);
				if (!isResourceSelected(uri)) {
					document.removeResourceDescriptor(node);
				} else {
					i++;
				}
			}

			List pluginList =  LibraryService.getInstance().getCurrentMethodLibrary()
			.getMethodPlugins();
			document.storeExtraInfo(pluginList);
			
			document.saveAs(target.getAbsolutePath());
			
			return document;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	private String getPluginResMgrUri(String id, Element node, Map<String, MethodPlugin> pluginMap) {
		String uri = node.getAttribute(LibraryDocument.ATTR_uri);
		MethodPlugin plugin = pluginMap.get(id);
		if (plugin != null) {
			String newUri = plugin.getName() + "/plugin.xmi";  //$NON-NLS-1$
			if (! newUri.equals(uri)) {
				node.setAttribute(LibraryDocument.ATTR_uri, newUri);
				return newUri;
			}
		}
		return uri;
	}

}
