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
package org.eclipse.epf.library;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * A default library service listener.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryServiceListener implements ILibraryServiceListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.ILibraryServiceListener#configurationSet(org.eclipse.epf.uma.MethodConfiguration)
	 */
	public void configurationSet(MethodConfiguration config) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryClosed(org.eclipse.epf.uma.MethodLibrary)
	 */
	public void libraryClosed(MethodLibrary library) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryCreated(org.eclipse.epf.uma.MethodLibrary)
	 */
	public void libraryCreated(MethodLibrary library) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryOpened(org.eclipse.epf.uma.MethodLibrary)
	 */
	public void libraryOpened(MethodLibrary library) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.ILibraryServiceListener#libraryReopened(org.eclipse.epf.uma.MethodLibrary)
	 */
	public void libraryReopened(MethodLibrary library) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.ILibraryServiceListener#librarySet(org.eclipse.epf.uma.MethodLibrary)
	 */
	public void librarySet(MethodLibrary library) {
	}

}
