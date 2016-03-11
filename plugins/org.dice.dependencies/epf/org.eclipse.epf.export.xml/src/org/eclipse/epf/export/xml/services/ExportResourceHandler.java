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
package org.eclipse.epf.export.xml.services;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.dataexchange.util.BaseResourceHandler;
import org.eclipse.epf.dataexchange.util.UrlInfo;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The resource handler for content in the exported library.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ExportResourceHandler extends BaseResourceHandler {
	
	private static boolean localDebug = false;
	/**
	 * Creates a new instance.
	 */
	public ExportResourceHandler(File sourceLibRoot, File targetLibRoot) {
		super(sourceLibRoot, targetLibRoot);
	}

	/**
	 * resolve the url. For XML export, we should reserve the resource locations.
	 */
	public UrlInfo resolveFileUrl(Object owner, String srcUrl) throws Exception {
		return loadUrlInfoWithFixedPath(owner, srcUrl);
	}

	
	/**
	 * the resource path will be relative to the libary root. urls needs to be
	 * fixed
	 * 
	 * @param owner
	 *            the owner object in the target library
	 * @param srcUrl
	 * @return
	 */
	private UrlInfo loadUrlInfoWithFixedPath(Object owner, String srcUrl) {
		UrlInfo info = new UrlInfo();
		info.sourceUrl = srcUrl;
		info.targetUrl = srcUrl;
		String srcFile = getFileRelPath(owner, srcUrl);
		org.eclipse.epf.xml.uma.MethodElement ownerElem = null;
		if (owner instanceof org.eclipse.epf.xml.uma.MethodElement) {
			ownerElem = (org.eclipse.epf.xml.uma.MethodElement) owner;
		}
		if (srcFile != null && ownerElem != null) {			
			MethodPlugin srcPlugin = getSourcePlugin(ownerElem);
			ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(srcPlugin);
			String srcPluginPath = libResMgr.getPhysicalPluginPath(srcPlugin);
			File srcRoot = (new File(srcPluginPath)).getParentFile();
			
			info.sourceFile = new File(srcRoot, srcFile);
			if (!info.sourceFile.isFile() || !info.sourceFile.exists()) {
				String newSrcFile = null;
				try {
					newSrcFile = NetUtil.decodeURL(srcFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (newSrcFile != null) {
					srcFile = newSrcFile;
					info.sourceFile = new File(srcRoot, srcFile);
				}
			}
			info.targetFile = new File(targetLibRoot, srcFile);
			info.targetUrl = srcFile.replace(File.separatorChar, '/');
		}

		return info;
	}

	protected static MethodPlugin getSourcePlugin(org.eclipse.epf.xml.uma.MethodElement ownerElem) {
		ILibraryManager manager = LibraryService.getInstance()
		.getCurrentLibraryManager();
		if (manager == null) {
			throw new UnsupportedOperationException();
		}
		MethodElement element = manager.getMethodElement(ownerElem.getId());
		if (element == null) {
			throw new UnsupportedOperationException();
		}
		MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
		if (plugin == null) {
			throw new UnsupportedOperationException();
		}
		return plugin;
	}

	private String getFileRelPath(Object owner, String srcUrl) {
		try {
			if (owner instanceof org.eclipse.epf.xml.uma.MethodElement) {
				String id = ((org.eclipse.epf.xml.uma.MethodElement) owner)
						.getId();
				ILibraryManager manager = LibraryService.getInstance()
						.getCurrentLibraryManager();
				if (manager != null) {
					MethodElement element = manager.getMethodElement(id);
					if (element != null) {
						ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(element);
						if (libResMgr != null) { 
							String elementPath = ResourceHelper.getFolderAbsolutePath(element);							
							String pluginPath = libResMgr.getPhysicalPluginPath(element);
							File pluginParent = (new File(pluginPath)).getParentFile();
							File file = new File(elementPath, srcUrl);
							String filePath = file.getCanonicalPath();
							String retPath = filePath.substring(pluginParent.getCanonicalPath().length() + 1);
							if (localDebug) {
								File f = new File(new File(sourceLibRoot,
										ResourceHelper.getElementPath(element)), srcUrl);
								String path = f.getCanonicalPath();
								String oldPath = path.substring(sourceLibRoot.getCanonicalPath()
										.length() + 1);
								if (! oldPath.equals(retPath)) {
									System.out.println("LD> oldPath: " + oldPath);	//$NON-NLS-1
									System.out.println("LD> retPath: " + retPath);	//$NON-NLS-1
									System.out.println("");							//$NON-NLS-1
								}
							}
							return retPath;
						}
						
						File f = new File(new File(sourceLibRoot,
								ResourceHelper.getElementPath(element)), srcUrl);
						String path = f.getCanonicalPath();
						return path.substring(sourceLibRoot.getCanonicalPath()
								.length() + 1);
					}
				}
			}
		} catch (IOException e) {
			// Log error here
		}

		return null;
	}

	/**
	 * copying the resource file from the source library to the target
	 * 
	 * @param sourceFile the file path relative to the source root
	 */
	public void copyResource(String sourceFile, EObject obj, org.eclipse.epf.uma.MethodPlugin umaPlugin) {
		ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(umaPlugin);
		String srcPluginPath = libResMgr.getPhysicalPluginPath(umaPlugin);
		File srcPluginRoot = new File(srcPluginPath);
		
		File src = new File(srcPluginRoot.getParentFile(), sourceFile);
		
		if ( ! src.exists() ) {
			sourceFile = NetUtil.decodedFileUrl(sourceFile);
			src = new File(srcPluginRoot.getParentFile(), sourceFile);
			if (! src.exists()) {
				try {
					sourceFile = NetUtil.decodeURL(sourceFile);
					src = new File(srcPluginRoot.getParentFile(), sourceFile);					
				} catch (Exception e) {					
				}
			}		
		}
		
		if ( src.exists() ) {
			File tgt = new File(targetLibRoot, sourceFile);
			FileUtil.copyFile(src, tgt);
		}
	}
	
//	/**
//	 * the resource path will be relative to the owner element. urls will not be
//	 * changed but resource files is relicated.
//	 * 
//	 * @param owner
//	 * @param srcUrl
//	 * @return
//	 */
//	private UrlInfo loadUrlInfoWithRelativePath(Object owner, String srcUrl) {
//		UrlInfo info = new UrlInfo();
//
//		try {
//			info.sourceUrl = srcUrl;
//			info.targetUrl = srcUrl; // no url change
//
//			// get the rmc object
//			if (owner instanceof org.eclipse.epf.xml.uma.MethodElement) {
//				String id = ((org.eclipse.epf.xml.uma.MethodElement) owner)
//						.getId();
//				ILibraryManager manager = LibraryService.getInstance()
//						.getCurrentLibraryManager();
//				if (manager != null) {
//					MethodElement element = manager.getMethodElement(id);
//					if (element != null) {
//						File f = new File(new File(sourceLibRoot,
//								ResourceHelper.getElementPath(element)), srcUrl);
//						f = f.getCanonicalFile();
//
//						info.sourceFile = f;
//					}
//				}
//			}
//
//			info.targetFile = new File(targetLibRoot, srcUrl)
//					.getCanonicalFile();
//
//		} catch (IOException e) {
//			// log error TODO
//		}
//		return info;
//	}

}
