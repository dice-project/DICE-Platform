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
package org.eclipse.epf.export.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.uma.MethodElementProperty;

/**
 * Models a library configuration specification.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigurationSpec {

	public String name;

	public String guid;

	public String version;

	public String brief_desc;

	public List pluginIds = new ArrayList();

	public List packageIds = new ArrayList();

	public List viewIds = new ArrayList();
	
	public List addedCCIds = new ArrayList();
	
	public List substractCCIds = new ArrayList();
	
	public String defaultView;

	public List<MethodElementProperty> mepList = new ArrayList<MethodElementProperty>();

	/**
	 * Creates a new instance.
	 */
	public ConfigurationSpec() {
	}

}
