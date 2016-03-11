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
package org.eclipse.epf.importing.xml.services;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.dataexchange.util.BaseResourceHandler;
import org.eclipse.epf.dataexchange.util.UrlInfo;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodElement;


/**
 * resource handler for content in the imported library
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ImportResourceHandler extends BaseResourceHandler {

		
	/**
	 * The constructor
	 * @param sourceLibRoot
	 * @param targetLibRoot
	 */
	public ImportResourceHandler(File sourceLibRoot, File targetLibRoot) {
		super(sourceLibRoot, targetLibRoot);
	}
	
	/**
	 * resolve the url. put the resources into the location based on the library resource structure definition.
	 */
	public UrlInfo resolveFileUrl(Object owner, String srcUrl) throws Exception {

		UrlInfo info = new UrlInfo();

		info.sourceUrl = srcUrl;
		info.sourceFile = new File(sourceLibRoot, srcUrl).getCanonicalFile();
		info.targetUrl = srcUrl; 
		info.targetFile = null;

		if (info.sourceFile == null || !info.sourceFile.exists() ) {
			return info;
		}
		
		if ( owner instanceof MethodElement ) {
			// fix the target url and 
			info.targetUrl = fixUrl(srcUrl);
			
			//String elementPath = ResourceHelper.getElementResourcePath(owner);
			String elementPath = ResourceHelper.getElementPath( (MethodElement)owner);
			ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr((MethodElement)owner);
			String srcPluginPath = libResMgr.getPhysicalPluginPath((MethodElement)owner);
			File srcRoot = (new File(srcPluginPath)).getParentFile();
			info.targetFile = new File(new File(srcRoot, elementPath), info.targetUrl);	
		} else {
			info.targetUrl = srcUrl; 
			info.sourceFile = new File(sourceLibRoot, srcUrl);
		}

		return info;
	}

	private String fixUrl(String url) {
		url = url.replace(File.separatorChar, '/');  
		int indx = url.lastIndexOf(ResourceHelper.RESOURCE_FOLDER + "/");  //$NON-NLS-1$
		if ( indx >=0 ) {
			return url.substring(indx);
		}
		
		// put the resource into the resources folder
		return new File(ResourceHelper.RESOURCE_FOLDER, url)
			.toString().replace(File.separatorChar, '/'); 
	}
	
	/**
	 * copying the resource file from the source library to the target
	 * 
	 * @param sourceFile the file path relative to the source root
	 */
	public void copyResource(String sourceFile, EObject obj, org.eclipse.epf.uma.MethodPlugin umaPlugin) {
		ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(umaPlugin);
		String tgtPluginPath = libResMgr.getPhysicalPluginPath(umaPlugin);
		File tgtPluginRoot = new File(tgtPluginPath);
		File srcPluginRoot = new File(sourceLibRoot, umaPlugin.getName());
		
		File src = new File(srcPluginRoot, sourceFile);
		if ( ! src.exists() ) {
			sourceFile = NetUtil.decodedFileUrl(sourceFile);
			src = new File(srcPluginRoot, sourceFile);
			if (! src.exists()) {
				try {
					sourceFile = NetUtil.decodeURL(sourceFile);
					src = new File(srcPluginRoot, sourceFile);				
				} catch (Exception e) {					
				}
			}		
		}
		if ( src.exists() ) {
			File tgt = new File(tgtPluginRoot, sourceFile);
			FileUtil.copyFile(src, tgt);
		}
	}
	
//	/**
//	 * the resource path will be relative to the libary root. urls needs to be fixed
//	 * 
//	 * @param owner the owner object in the target library
//	 * @param srcUrl the url referenced in the source content
//	 * @return
//	 */
//	private UrlInfo loadUrlInfoWithFixedPath(Object owner, String srcUrl) {
//		UrlInfo info = new UrlInfo();
//		info.sourceUrl = srcUrl;
//		info.targetUrl = srcUrl;
//		info.sourceFile = new File(sourceLibRoot, srcUrl);
//		info.targetFile = new File(targetLibRoot, srcUrl);
//
//		if ( owner instanceof MethodElement)  {
//			String backPath = ResourceHelper.getBackPath( (MethodElement)owner);
//			info.targetUrl = backPath + srcUrl;
//		}
//		return info;	
//	}
//	
//	/**
//	 * the resource path will be relative to the owner element. 
//	 * urls will not be changed but resource files is relicated.
//	 * @param owner owner the owner object in the target library
//	 * @param srcUrl
//	 * @return
//	 */
//	private UrlInfo loadUrlInfoWithRelativePath(Object owner, String srcUrl) {
//		UrlInfo info = new UrlInfo();
//		info.sourceUrl = srcUrl;
//		info.targetUrl = info.sourceUrl; // no url change
//		info.sourceFile = new File(sourceLibRoot, srcUrl);
//		
//		if ( owner instanceof MethodElement)  {
//			//String elementPath = ResourceHelper.getElementResourcePath(owner);
//			String elementPath = ResourceHelper.getElementResourcePath((MethodElement)owner);
//			info.targetFile = new File(new File(targetLibRoot, elementPath), srcUrl);			
//		}
//
//		return info;
//	}
	
}
