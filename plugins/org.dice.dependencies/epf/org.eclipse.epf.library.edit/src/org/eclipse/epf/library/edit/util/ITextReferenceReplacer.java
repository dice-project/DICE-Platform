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
package org.eclipse.epf.library.edit.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * Instance of this class can scan text and replaces textual references to the
 * old objects with references to new objects
 * 
 * @author Phong Nguyen Le - Jun 22, 2006
 * @since 1.0
 */
public interface ITextReferenceReplacer {
	/**
	 * Replaces textual references to the old objects with references to new
	 * objects provided in the <code>oldToNewObjectMap</code>
	 * 
	 * @param text
	 * @param owner
	 *            the owner of the text
	 * @param oldToNewObjectMap
	 * @return
	 */
	String replace(String text, EObject owner, Map oldToNewObjectMap);
	
	IResourceScanner getResourceScanner();
	
}
