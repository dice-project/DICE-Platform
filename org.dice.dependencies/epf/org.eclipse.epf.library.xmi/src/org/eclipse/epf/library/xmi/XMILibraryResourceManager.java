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
package org.eclipse.epf.library.xmi;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.MethodLibraryPersister;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * XMI library resource mamager
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class XMILibraryResourceManager implements ILibraryResourceManager {

	public XMILibraryResourceManager() {
	}

	
	/**
	 * get the logical path of the element in the library,
	 * the path should start with the plugin name. 
	 * i.e. <plugin name>/path/to/element
	 * 
	 * The implementation class should encapsulate the physical storage 
	 * of the resource and return the path of the element start with it's plugin name
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public String getLogicalPath(MethodElement element) {
		try {
			String path = MethodLibraryPersister.INSTANCE.getElementVirtualPath(element);
			if (path == null || path.equals("")) //$NON-NLS-1$
			{
				System.out
						.println("Warning! No Path for Element [" + element.getType().getName() + ": " + element.getName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return ""; //$NON-NLS-1$
			}
			return fixPath(path);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return ""; //$NON-NLS-1$
	}
	
	/**
	 * get the element's back path of the element based on the logical path, for example,
	 * "OpenUP\guidance\concept\c1.xml", the back path is "./../../../"
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public String getBackPath(MethodElement element) {
		// Linux: Browsing and preview shows only plain text.
		// There are no images/sections
		// element path should check File.separatorChar instead of "\"
		String backPath = ""; //$NON-NLS-1$
		String path = getLogicalPath(element);
		if (path != null && path.length() > 0) {
			backPath = path.replace(File.separatorChar, '/').replaceAll(
					".*?/", "\\.\\./"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return "./" + backPath; //$NON-NLS-1$
	}
	
	/**
	 * fix the path by appending a File.seperator to the end of it
	 * @param path String
	 * @return String
	 */
	private String fixPath(String path) {
		if (path == null || path.equals("")) //$NON-NLS-1$
		{
			return ""; //$NON-NLS-1$
		} else if (!path.endsWith(File.separator)) {
			return path + File.separator;
		} else {
			return path;
		}
	}

	/**
	 * get the logical resource path of the element in the
	 * library. the path should start with the plugin name. 
	 * i.e. <plugin name>/path/to/element/resources
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public String getLogicalResourcePath(MethodElement element) {
		String path = MethodLibraryPersister.INSTANCE.geResourceVirtualPath(element);
		return fixPath(path);
	}
	
	/**
	 * Gets the physical path of a method element
	 * 
	 * @param element
	 * @return the physical path of the element
	 */
	public String getPhysicalPath(MethodElement element) {
		if ( element == null ) {
			return null;
		}
		
		IFileBasedLibraryPersister persister = getFileBasedLibraryPersister(element);
		return persister != null ? fixPath(persister.getFolderAbsolutePath(element)) : null;
	}
	
	/**
	 * Gets the absolute path of the resource folder of the element in the library.
	 * 
	 * @param e
	 * @return the physical resource path of the element
	 */
	public String getPhysicalResourcePath(MethodElement element) {
		IFileBasedLibraryPersister persister = getFileBasedLibraryPersister(element);
		return persister != null ? fixPath(persister.getResourceFolderPath(element)) : null;
	}
	
	private IFileBasedLibraryPersister getFileBasedLibraryPersister(MethodElement element) {
		Resource resource = element.eResource();
		if(resource != null) {
			ILibraryPersister persister = LibraryServiceUtil.getPersisterFor(resource);
			if(persister instanceof IFileBasedLibraryPersister) {
				return ((IFileBasedLibraryPersister)persister);
			}
		}
		return null;
	}
	
	/**
	 * get the logical path of the plugin 
	 * 
	 * @param element
	 * @return String
	 */
	public String getLogicalPluginPath(MethodElement element) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
		if (plugin != null) {
			return plugin.getName() + File.separator;
		} 
		
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * get the physical path of the plugin associated with the element
	 * 
	 * @param element
	 * @return String
	 */
	public String getPhysicalPluginPath(MethodElement element) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
		if (plugin != null) {
			return getPhysicalPath(plugin);
		} 
		
		return null;
	}

	
	/**
	 * get the physical path associated with the given logical path
	 * 
	 * @param element MethodElement an element in the library to access the library resource
	 * usually this is the owner element that references the logicalPath
	 * @param logicalPath String
	 * @return String the physical path resolved from the logical path
	 *
	 * for example, element task1, logical path is "plugin 1/tasks/task1.html"
	 * the returned physical path is <plugin location>/path/to/element
	 * 
	 * @param element 
	 * @param logicalPath the logical path of the element
	 * @return String
	 */
	public String resolve(MethodElement element, String logicalPath) {

		// use the plugin name in the logical path
		// find the MethodPlugin object
		// find the pluginPath
				
		// strip off the first folder in the relpath since this is the plugin name
		logicalPath = logicalPath.replace('/', File.separatorChar);
		int i = logicalPath.indexOf(File.separatorChar);
		if ( i > 0 ) {
			String pluginName = logicalPath.substring(0, i);
			String pluginPath = null; 

			MethodLibrary lib = UmaUtil.getMethodLibrary(element);
			List plugins = LibraryUtil.getMethodPlugins(lib);
			
			for (Iterator it = plugins.iterator(); it.hasNext(); ) {
				MethodPlugin plugin = (MethodPlugin)it.next();
				if ( plugin.getName().equals(pluginName) ) {
					pluginPath = getPhysicalPluginPath(plugin);
					break;
				}
			}
			
			if ( pluginPath != null ) {
				return pluginPath + logicalPath.substring(i+1);
			}
		}
		
		return null;
		
	}
	
}
