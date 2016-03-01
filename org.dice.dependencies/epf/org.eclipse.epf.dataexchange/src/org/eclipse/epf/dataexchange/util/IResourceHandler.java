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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * Resource handler interface for xml export and import
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public interface IResourceHandler {

	public UrlInfo resolveFileUrl(Object owner, String srcUrl) throws Exception;

	/**
	 * copying the resource file from the source library to the target
	 * 
	 * @param sourceFile the file path relative to the source root
	 * @deprecated
	 */
	public void copyResource(String sourceFile);
	
	public void copyResource(String sourceFile, EObject obj, org.eclipse.epf.uma.MethodPlugin umaPlugin);
}
