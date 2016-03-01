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
package org.eclipse.epf.library.edit.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * Instance of this class can scan text for copy of resource files
 * 
 * @author Weiping Lu - August 30, 2008
 * @since 1.5.1
 */
public interface IResourceScanner {

	void init(MethodPlugin srcPlugin, MethodPlugin tgtPlugin);
	
	void copyFiles();

	String registerFileCopy(String srcUrl);

	String scan(MethodElement srcElement, MethodElement tgtElement,
			String source, EStructuralFeature feature);

}
