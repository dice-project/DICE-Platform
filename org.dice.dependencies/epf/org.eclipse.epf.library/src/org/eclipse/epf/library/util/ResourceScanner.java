//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.util.IResourceScanner;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * Utility class to scan and copy resources from a plugin to another plugin.
 * @author Weiping Lu
 * @since 1.5
 *
 */
public class ResourceScanner implements IResourceScanner {
	
	private static boolean localDebug = false;

	public static final Pattern p_src_ref = Pattern.compile(
			"src\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
	public static final Pattern p_href_ref = Pattern
			.compile(
					"href\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	private MethodPlugin srcPlugin;
	private MethodPlugin tgtPlugin;
	
	private File srcPluginRoot;
	private File tgtPluginRoot;
	
	private File srcPluginRootParent;
	private File tgtPluginRootParent;
	
	private Map<File, File> fileMap;
	private Set<File> tgtFileSet;

	/**
	 * Creates a new instance.
	 */
	public ResourceScanner(MethodPlugin srcPlugin, MethodPlugin tgtPlugin) {
		init(srcPlugin, tgtPlugin);
	}
	
	public void init(MethodPlugin srcPlugin, MethodPlugin tgtPlugin) {
		this.srcPlugin = srcPlugin;
		this.tgtPlugin = tgtPlugin;
		if (srcPlugin == null || tgtPlugin == null) {
			srcPluginRoot = null;
			tgtPluginRoot = null;
			srcPluginRootParent = null;
			tgtPluginRootParent = null;
			fileMap = null;
			tgtFileSet = null;
			return;
		}
				
		File srcFile = new File(srcPlugin.eResource().getURI().toFileString());
		File tgtFile = new File(tgtPlugin.eResource().getURI().toFileString());
		srcPluginRoot = srcFile.getParentFile();
		tgtPluginRoot = tgtFile.getParentFile();
		srcPluginRootParent = srcPluginRoot.getParentFile();
		tgtPluginRootParent = tgtPluginRoot.getParentFile();
	}

	public String scan(MethodElement srcElement, MethodElement tgtElement, String source, EStructuralFeature feature) {
		/*if (feature.getName().equals("mainDescription")) {
			System.out.println("LD> srcElement: " + srcElement.getName() + ", feature: " + feature.getName());
		}*/
		String srcPath = ResourceHelper.getElementPath(srcElement);
		if (srcPath == null) {
			return source;
		}
		String tgtPath = ResourceHelper.getElementPath(tgtElement);
		if (tgtPath == null) {
			if (srcPath.indexOf(srcPlugin.getName()) == 0) {
				tgtPath = tgtPlugin.getName() + srcPath.substring(srcPlugin.getName().length());
			} else {
				return source;
			}
		}

		File srcFolder = new File(srcPluginRootParent, srcPath);
		File tgtFolder = new File(tgtPluginRootParent, tgtPath);
		
		StringBuffer sb = new StringBuffer();
		try {
			// process images and other src resources
			Matcher m = p_src_ref.matcher(source);
			while (m.find()) {
				String text = m.group();
				String url = m.group(1);
				String tgtUrl = registerFileCopy(srcFolder, tgtFolder, url, srcElement, tgtElement);
				String replaceText = text.replace(url, tgtUrl);
				m.appendReplacement(sb, Matcher.quoteReplacement(replaceText));	
			}
			m.appendTail(sb);

			// process hrefs
			m = p_href_ref.matcher(sb.toString());
			sb = new StringBuffer();
			while (m.find()) {
				String text = m.group();
				String url = m.group(1);
				String tgtUrl = registerFileCopy(srcFolder, tgtFolder, url, srcElement, tgtElement);
				String replaceText = text.replace(url, tgtUrl);
				m.appendReplacement(sb, Matcher.quoteReplacement(replaceText));	
			}
			m.appendTail(sb);
			
		} catch (Exception ex) {
			LibraryPlugin.getDefault().getLogger().logError(ex);
		}
		
		return sb.toString();
	}
	
	public String registerFileCopy(String srcUrl) {
		return registerFileCopy(srcPluginRoot, tgtPluginRoot, srcUrl, null, null);
	}
	
	public String registerFileCopy(String srcUrl, MethodElement srcElement, MethodElement tgtElement) {
		String srcPath = ResourceHelper.getElementPath(srcElement);
		if (srcPath == null) {
			return srcUrl;
		}
		String tgtPath = ResourceHelper.getElementPath(tgtElement);
		if (tgtPath == null) {
			if (srcPath.indexOf(srcPlugin.getName()) == 0) {
				tgtPath = tgtPlugin.getName() + srcPath.substring(srcPlugin.getName().length());
			} else {
				return srcUrl;
			}
		}
		File srcFolder = new File(srcPluginRootParent, srcPath);
		File tgtFolder = new File(tgtPluginRootParent, tgtPath);
		
		return registerFileCopy(srcFolder, tgtFolder, srcUrl, srcElement, tgtElement);
	}
	
	//Can be overridden by subclass
	protected String registerFileCopy(File srcFolder, File tgtFolder, String srcUrl, MethodElement srcElement, MethodElement tgtElement) {
		return registerFileCopy(srcFolder, tgtFolder, srcUrl);
	}
	
	/**
	 * @param srcFolder
	 * @param tgtFolder
	 * @param url
	 * @return tgtUrl
	 */
	private String registerFileCopy(File srcFolder, File tgtFolder, String srcUrl) {
		if (srcUrl == null) {
			return srcUrl;
		}

		String tgtUrl = srcUrl;
		int index = tgtUrl.indexOf("#"); //$NON-NLS-1$
		if (index >= 0) {
			tgtUrl = tgtUrl.substring(0, index);
		}

		index = tgtUrl.indexOf("?"); //$NON-NLS-1$
		if (index >= 0) {
			tgtUrl = tgtUrl.substring(0, index);
		}

		if (tgtUrl.trim().length() == 0) {
			return srcUrl;
		}

		try {
	    	String decodedChild = null;
	    	try {
	    		decodedChild = NetUtil.decodeURL(srcUrl);
	    	} catch (Exception e) {
	    		decodedChild = srcUrl;
	    	}
	    	File srcFile0 = new File(srcFolder, decodedChild);
	    	if (!srcFile0.isFile() || !srcFile0.exists()) {
	    		return srcUrl;
	    	}
	    	
			File srcFile = newFile(srcFolder, srcUrl);
			File tgtFile = newFile(tgtFolder, tgtUrl);
			
			if (srcFile.isFile() && srcFile.exists()) {
				if (tgtFile.exists()) {
					if (tgtFile.lastModified() == srcFile.lastModified()
							&& tgtFile.length() == srcFile.length()) {
						return tgtUrl;
					}
				}
				tgtUrl = this.getTargetUrl(srcFile, tgtFolder, tgtUrl);
				tgtFile = newFile(tgtFolder, tgtUrl);;
				
//				srcFile = srcFile.getCanonicalFile();
//				tgtFile = tgtFile.getCanonicalFile();				
//				fileMap.put(srcFile, tgtFile);
				tgtUrl = registerFileCopyToMap(srcFile, tgtFile, tgtUrl);
				
			}
		} catch (Throwable e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
		
		return tgtUrl;
	}

	protected String registerFileCopyToMap(File srcFile0, File tgtFile0, String tgtUrl) {
		try {
			File srcFile = srcFile0.getCanonicalFile();
			File tgtFile = tgtFile0.getCanonicalFile();
			getFileMap().put(srcFile, tgtFile);
		} catch (IOException e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}		
		return tgtUrl;
	}
	
    private File newFile(File parent, String child) {
    	String decodedChild;
    	try {
    		decodedChild = NetUtil.decodeURL(child);
    	} catch (Exception e) {
    		decodedChild = child;
    	}
    	File file = new File(parent, decodedChild);
		try {
			return file.getCanonicalFile();
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
		
		return file;
    }

	/**
	 * copy all the files to the destination
	 */
	public void copyFiles() {
		if (localDebug) {
			System.out.println("LD> copyFiles: ");	//$NON-NLS-1$
		}
				
		for (Iterator it = getFileMap().entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			File srcFile = (File) entry.getKey();
			File tgtFile = (File) entry.getValue();
			FileManager.copyFile(srcFile, tgtFile);			
			tgtFile.setLastModified(srcFile.lastModified());			
			
			if (localDebug) {
				System.out.println("LD> srcFile: " + srcFile);	//$NON-NLS-1$
				System.out.println("LD> tgtFile: " + tgtFile);	//$NON-NLS-1$
				System.out.println("");							//$NON-NLS-1$
			}
		}		
	}
	
	private String getTargetUrl(File srcFile, File tgtFolder, String tgtUrl0) {
		String dot = ".";	//$NON-NLS-1$
		String url1 = tgtUrl0;
		String url2 = "";	//$NON-NLS-1$
		
		int ix = tgtUrl0.lastIndexOf(dot);
		int len = tgtUrl0.length();

		boolean addDot = false;
		if (ix > 0 && ix < len) {
			url1 = tgtUrl0.substring(0, ix);
			url2 = tgtUrl0.substring(ix + 1, len);
			addDot = true;
		}
		
		String tgtUrl = tgtUrl0;
		File tgtFile = newFile(tgtFolder, tgtUrl);
		String u = "_";	//$NON-NLS-1$
		int i = 1;
		while (true) {
			boolean exists = tgtFile.exists();
			if (exists) {
				if (tgtFile.lastModified() == srcFile.lastModified()
						&& tgtFile.length() == srcFile.length()) {
					break;
				}
			}			
			
			boolean inTgtSet = getTgtFileSet().contains(tgtFile);
			if (! exists && !inTgtSet) {
				break;
			}
						
			if (inTgtSet) {
				try {
					File file = getFileMap().get(srcFile.getCanonicalFile());
					if (file == null) {	//Check file binary equals
						boolean findEqual = false;
						for (Map.Entry<File, File> entry : getFileMap().entrySet()) {
							if (entry.getValue().equals(tgtFile)) {
								if (FileUtil.binaryEqual(srcFile.getCanonicalFile(), entry.getKey())) {
									findEqual = true;
									break;
								}
							}								
						}
						if (findEqual) {
							break;
						}
					} else if (file.equals(tgtFile.getCanonicalFile())) {
						break;
					}
					
				} catch (Exception e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			}
								
			tgtUrl = url1 + u + i;
			if (addDot) {
				tgtUrl += dot + url2;
			}
			tgtFile = newFile(tgtFolder, tgtUrl);			
			i++;
		}
		
		try {
			tgtFile = tgtFile.getCanonicalFile();
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
		
		getTgtFileSet().add(tgtFile);		
		return tgtUrl;
	}

	public MethodPlugin getSrcPlugin() {
		return srcPlugin;
	}
	
	protected Map<File, File> getFileMap() {
		if (fileMap == null) {
			fileMap = new HashMap<File, File>();
		}
		return fileMap;
	}
	
	protected Set<File> getTgtFileSet() {
		if (tgtFileSet == null) {
			tgtFileSet = new HashSet<File>();
		}
		return tgtFileSet;
	}
	
}
