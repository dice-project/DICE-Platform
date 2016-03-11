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
package org.eclipse.epf.importing.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * utility class to scan and copy resources from the source library to the target library
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ResourceScanner {

	public static final Pattern p_src_ref = Pattern.compile("src\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
//	protected static final Pattern p_link_ref = Pattern.compile("<a\\s+?([^>]*)>(.*?)</a>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
	public static final Pattern p_href_ref = Pattern.compile("href\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	File srcLibRoot;
	File targetLibRoot;
	
	private Map newFileMap = new HashMap();
	private Map existingFileMap = new HashMap();
	Map<String, String> renamePluginMap;
	
	/**
	 * Creates a new instance.
	 */	
	public ResourceScanner(File srcLibRoot, File targetLibRoot, Map<String, String> renamePluginMap) {
		this.srcLibRoot = srcLibRoot;
		this.targetLibRoot = targetLibRoot;
		this.renamePluginMap = renamePluginMap;
	}
	
	/**
	 * scan th etext and copy the resources
	 * @param owner MethodElement
	 * @param source String
	 */
	public void scan(MethodElement owner, String source) {
		
		try
		{
			// process images and other src resources
			Matcher m = p_src_ref.matcher(source);
			while ( m.find() )
			{
				String url = m.group(1);
				processUrl(owner, url);
			}

			// process hrefs
			m = p_href_ref.matcher(source);
			
			while ( m.find() )
			{
				String url = m.group(1);
				processUrl(owner, url);
			}			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * process the url and copy the resource
	 * if the owner element is specified, the url is relative to the owner element, 
	 *otherwise it's relative to the library
	 *
	 * @param owner
	 * @param url
	 */
	private void processUrl(MethodElement owner, String url) {
		processUrl(owner, url, null);
	}
	
	private void processUrl(MethodElement owner, String url, MethodPlugin plugin) {
		
		if ( url == null ) {
			return;
		}
		
		int index = url.indexOf("#"); //$NON-NLS-1$
		if ( index >= 0 )
		{
			url = url.substring(0, index);
		}
		
		index = url.indexOf("?"); //$NON-NLS-1$
		if ( index >= 0 )
		{
			url = url.substring(0, index);
		}
		
		if (url.trim().length() == 0 ) {
			return;
		}
		
		// the url is relative to the owner element
		// need to convert to the path relative to the library root
		File srcFile = null;
		File targetFile = null;
		try {
			if ( owner != null ) {
				ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(owner);
				String elementPath = ResourceHelper.getElementPath(owner);
				//srcFile = new File(new File(srcLibRoot, elementPath), url);
				//if (!srcFile.isFile() || !srcFile.exists()) {
					elementPath = getElementPathWithRenamedPlugin(elementPath);
					srcFile = new File(new File(srcLibRoot, elementPath), url);
				//}
				if (!srcFile.isFile() || !srcFile.exists()) {
					url = NetUtil.decodeURL(url);
					srcFile = new File(new File(srcLibRoot, elementPath), url);
				}
				//targetFile = new File(new File(targetLibRoot, elementPath), url);
				String pluginPath = libResMgr.getPhysicalPluginPath(owner);
				File targetRoot = (new File(pluginPath)).getParentFile();
				targetFile = new File(new File(targetRoot, elementPath), url);
			} else {
				if (plugin == null) {
					throw new UnsupportedOperationException();
				}			
				File srcPluginRoot = new File(srcLibRoot, plugin.getName());
				srcFile = new File(srcPluginRoot, url);
				if (!srcFile.isFile() || !srcFile.exists()) {
					url = NetUtil.decodeURL(url);
					srcFile = new File(srcPluginRoot, url);
				}
				ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(plugin);				
				String srcPluginRootPath = libResMgr.getPhysicalPluginPath(plugin);	
				targetFile = new File(srcPluginRootPath, url);				
			}
			
			if ( srcFile.isFile() && srcFile.exists() ) {

				srcFile = srcFile.getCanonicalFile();
				targetFile = targetFile.getCanonicalFile();
				
				if ( targetFile.exists() ) {
					if ( !existingFileMap.containsKey(srcFile) ) {
						if ( srcFile.length() != targetFile.length() 
								|| srcFile.lastModified() != targetFile.lastModified() )
						{
							existingFileMap.put(srcFile, targetFile);
						}
					}
				} else {
					if ( !newFileMap.containsKey(srcFile) ) {
						newFileMap.put(srcFile, targetFile);
					}
				}
			}
		} catch (IOException e) {
			// Log the error and proceed. TODO
			e.printStackTrace();
		}

	}

	private String getElementPathWithRenamedPlugin(String elementPath) {
		if (renamePluginMap != null && ! renamePluginMap.isEmpty()) {
			int ix = elementPath.indexOf("\\");//$NON-NLS-1$
			if (ix > 0) {
				String basePluginName =  elementPath.substring(0, ix);
				String importPluginName = renamePluginMap.get(basePluginName);
				if (importPluginName != null) {
					return importPluginName + elementPath.substring(ix);
				}
			}
		}
		return elementPath;
	}
	
	/**
	 * Copies resource.
	 */
	public void copyResource(String url, MethodPlugin plugin) {
		processUrl(null, url, plugin);
	}
	
	/**
	 * Copies resource.
	 */
	public void copyResource(MethodElement owner, String url) {
		processUrl(owner, url);
	}
	
	/**
	 * return a list of all the files to be replaced
	 * @return List a list of file path string
	 */
	public List getFilesTobeReplaced() {
		List files = new ArrayList();
		if ( existingFileMap.size() > 0 ) {
			for (Iterator it = existingFileMap.values().iterator(); it.hasNext(); ) {
				File f = (File)it.next();
				if (f != null ) {
					String path = f.getAbsolutePath();
					if ( !files.contains(path) ) {
						files.add(path);
					}
				}
			}
		}
		
		return files;
	}
	
	/**
	 * copy all the files to the destination
	 *
	 */
	public void execute() {
		
		for (Iterator it = newFileMap.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			File srcFile = (File) entry.getKey();
			File targetFile = (File) entry.getValue();
			FileUtil.copyFile(srcFile, targetFile);
		}	
		
		for (Iterator it = existingFileMap.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			File srcFile = (File) entry.getKey();
			File targetFile = (File) entry.getValue();
			FileUtil.copyFile(srcFile, targetFile);
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
