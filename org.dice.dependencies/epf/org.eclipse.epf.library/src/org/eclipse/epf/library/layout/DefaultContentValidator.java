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
package org.eclipse.epf.library.layout;

import java.io.File;
import java.io.PrintStream;

import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.util.ContentResourceScanner;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.osgi.util.NLS;

/**
 * default implementation for IContentValidator. This validator is used for browsing and preview
 * 
 * @author Jinhua Xi
 *
 */
public class DefaultContentValidator implements IContentValidator {

	protected PrintStream info;

	protected PrintStream warning;

	protected PrintStream error;

	protected String pubDir;

	//protected boolean showBrokenLinks = true;
		
	/**
	 * constructor
	 *
	 */
	public DefaultContentValidator() {
		this(null);
	}

	/**
	 * constructor
	 * @param pubDir
	 */
	public DefaultContentValidator(String pubDir) {
		this.pubDir = pubDir;
		info = System.out;
		warning = System.out;
		error = System.err;
	}

	/**
	 * set publish dir
	 * @param pubDir String
	 */
	public void setPublishDir(String pubDir) {
		this.pubDir = pubDir;
	}
	
	public String getPublishDir() {
		return this.pubDir;
	}

//	public void setShowBrokenLinks(boolean flag) {
//		showBrokenLinks = flag;
//	}
	
	public boolean showBrokenLinks() {
		return true; // default
	}
	
	public void scanContent(IElementLayout layout, String content) throws Exception {
		
		MethodElement owner = layout.getElement();
		String contentPath = layout.getFilePath();

		ContentResourceScanner scanner = getScanner(owner);
		if ( scanner != null ) {
			scanner.resolveResources(owner, content, contentPath);
		}
	}
	
	/**
	 * Returns the content resource scanner for the element.
	 */
	private ContentResourceScanner getScanner(MethodElement owner) {
		ILibraryResourceManager resMgr = ResourceHelper.getResourceMgr(owner);
		if ( resMgr == null ) {
			return null;
		}
		
		String rootContentPath = resMgr.getLogicalPluginPath(owner);
		File src_root = new File(resMgr.getPhysicalPluginPath(owner));
		File tgt_root = new File(pubDir, rootContentPath);
		ContentResourceScanner scanner = new ContentResourceScanner(src_root, tgt_root, rootContentPath, this);

		return scanner;
	}
	
	public LinkInfo validateLink(MethodElement owner, String attributes,
			String text, MethodConfiguration config, String tag) {

		LinkInfo info = new LinkInfo(owner, this, pubDir, tag);
		info.validateLink(attributes, text, config);

		return info;
	}

	public void logInfo(MethodElement owner, String message) {
		info
				.println(NLS.bind(LibraryResources.DefaultContentValidator_MSG1, ((owner == null) ? "" : LibraryUtil.getTypeName(owner)) + ": " + message));  //$NON-NLS-1$ //$NON-NLS-2$
		info.flush();
	}

	public void logWarning(MethodElement owner, String message) {
		warning
				.println(NLS.bind(LibraryResources.DefaultContentValidator_MSG4, ((owner == null) ? "" : LibraryUtil.getTypeName(owner)) + ": " + message));  //$NON-NLS-1$ //$NON-NLS-2$
		warning.flush();
	}

	public void logError(MethodElement owner, String message, Throwable th) {
		error
				.println(NLS.bind(LibraryResources.DefaultContentValidator_MSG7, ((owner == null) ? "" : LibraryUtil.getTypeName(owner)) + ": " + message));  //$NON-NLS-1$ //$NON-NLS-2$
		if (th != null) {
			th.printStackTrace(error);
		}
		error.flush();

	}

	public void logInfo(String message) {
		logInfo(null, message);
	}

	public void logWarning(String message) {
		logWarning(null, message);
	}

	public void logError(String message, Throwable th) {
		logError(null, message, th);
	}

	public void logMissingReference(MethodElement owner,
			MethodElement refElement) {
		logWarning(
				owner,
				NLS.bind(LibraryResources.DefaultContentValidator_MSG10, LibraryUtil.getTypeName(refElement))); 
	}

	public void logMissingReference(MethodElement owner, String guid,
			String linkedText) {
		logWarning(owner, NLS.bind(LibraryResources.DefaultContentValidator_MSG11, guid)); 
	}

	public void logMissingResource(MethodElement owner, File resourceFile,
			String url) {
		String msg;
		if (resourceFile != null) {
			msg = NLS.bind(LibraryResources.DefaultContentValidator_MSG12, resourceFile.getPath(), url); 
		} else {
			msg = NLS.bind(LibraryResources.DefaultContentValidator_MSG15, url); 
		}

		logWarning(owner, msg);
	}

	public void logInvalidExternalLink(MethodElement owner, String url,
			String message) {
		String text;
		if (message != null && message.length() > 0) {
			text = NLS.bind(LibraryResources.DefaultContentValidator_MSG19, url, message); 
		} else {
			text = NLS.bind(LibraryResources.DefaultContentValidator_MSG17, url); 
		}
		logWarning(owner, text);
	}

	/**
	 * check if the element is discarded or not
	 * discarded elements will be treated as out side the configursation
	 * 
	 * @param owner MethodElement the owner of the element
	 * @param Object feature EStructuralFeature or OppositeFeature
	 * @param e MethodElement the element to be checked
	 */
	public boolean isDiscarded(MethodElement owner, Object feature, MethodElement e) {
		return false;
	}
	
	public boolean isDiscarded(MethodElement owner, Object feature, MethodElement e, MethodConfiguration config) {
		return false;
	}

	public void dispose() {
	}

	public void addReferencedElement(MethodElement owner, MethodElement e)
	{
	}
	
	
	public void setDiscardedElement(MethodElement e) {
		
	}
	
	public boolean showExtraInfoForDescriptors() {
		return BrowsingLayoutSettings.INSTANCE.isShowExtraInfoForDescriptors();
	}
		
	/**
	 * show descriptors on method element page
	 */
	public boolean showRelatedDescriptors() {
		return true;
	}
	
	/**
	 * get the tab id for the activity layout
	 * 
	 * @return String
	 */
	public String getDefaultActivityTab() {
		return null;  // use the default
	}
	
	public boolean showLinkedPageForDescriptor() {
		return BrowsingLayoutSettings.INSTANCE.isShowLinkedPageForDescriptor();
	}
}

