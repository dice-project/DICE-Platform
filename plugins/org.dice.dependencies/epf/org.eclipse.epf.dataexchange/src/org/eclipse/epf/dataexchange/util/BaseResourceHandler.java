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
package org.eclipse.epf.dataexchange.util;

import java.io.File;

import org.eclipse.epf.common.utils.FileUtil;

/**
 * content resource handler to process the respources in the content
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public abstract class BaseResourceHandler implements IResourceHandler {

	protected File sourceLibRoot;
	protected File targetLibRoot;

	public BaseResourceHandler(File sourceLibRoot, File targetLibRoot) {
		this.sourceLibRoot = sourceLibRoot;
		this.targetLibRoot = targetLibRoot;
	}
	
	public abstract UrlInfo resolveFileUrl(Object owner, String srcUrl) throws Exception;
	
	
	/**
	 * copying the resource file from the source library to the target
	 * 
	 * @param sourceFile the file path relative to the source root
	 */
	public void copyResource(String sourceFile) {
		File src = new File(sourceLibRoot, sourceFile);
		if ( src.exists() ) {
			File tgt = new File(targetLibRoot, sourceFile);
			FileUtil.copyFile(src, tgt);
		}
	}
}
