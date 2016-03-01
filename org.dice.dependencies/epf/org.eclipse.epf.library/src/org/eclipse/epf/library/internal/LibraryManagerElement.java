//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.internal;

import org.osgi.framework.Bundle;

/**
 * Models a "libraryManager" configuration element in the
 * "org.eclipse.epf.library.libraryManagers" extension point.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryManagerElement {

	// The contributing plug-in.
	private Bundle bundle;

	// The library manager class name.
	private String className;

	// The library type identifier.
	private String typeId;

	// The library type display name.
	private String typeName;
	
	private int version;

	/**
	 * Creates a new instance.
	 * 
	 * @param bundle
	 *            the contributing plug-in
	 * @param className
	 *            the library manager class name
	 * @param typeId
	 *            the library type identifier
	 * @param typeName
	 *            the library type display name
	 */
	public LibraryManagerElement(Bundle bundle, String className,
			String typeId, String typeName, int version) {
		this.bundle = bundle;
		this.className = className;
		this.typeId = typeId;
		this.typeName = typeName;
		this.version = version;
	}

	/**
	 * Gets the plug-in that contributed the extension point
	 * 
	 * @return a plug-in bundle
	 */
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * Gets the "class" attribute value.
	 * 
	 * @return the name of the library manager class
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Gets the "type" attribute value.
	 * 
	 * @return a unique string ID for the library type
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Gets the "typeName" attribute value.
	 * 
	 * @return a display name for the library type
	 */
	public String getTypeName() {
		return typeName;
	}

	public int getVersion() {
		return version;
	}
}
