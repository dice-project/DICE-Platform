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
package org.eclipse.epf.library.events;

import java.util.Collection;

/**
 * @author Jinhua Xi
 * @since 1.0
 */
public interface ILibraryChangeListener {

//	public static final int OPTION_LOADED = 0x1;

//	public static final int OPTION_SAVED = 0x2;

//	public static final int OPTION_CREATED = 0x4;

	public static final int OPTION_CHANGED = 0x8;

	public static final int OPTION_DELETED = 0x10;

	public static final int OPTION_NEWCHILD = 0x20;

//	public static final int OPTION_CONFIGURATION_SELECTED = 0x40;

//	public static final int OPTION_LIBRARY_CLOSED = 0x80;

	/**
	 * notify the listener of the library changes.
	 * 
	 * @param option
	 *            int one of the change options
	 * @param collection
	 *            the changed elements in the library, if the option is
	 *            OPTION_CHANGED, null otherwise
	 */
	public void libraryChanged(int option, Collection<Object> collection);

}
