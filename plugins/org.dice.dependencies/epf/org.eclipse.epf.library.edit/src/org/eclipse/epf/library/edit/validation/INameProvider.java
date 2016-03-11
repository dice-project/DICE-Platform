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
package org.eclipse.epf.library.edit.validation;

/**
 * Interface to provide name for a specified object
 * 
 * @author Phong Nguyen Le - Jan 31, 2006
 * @since  1.0
 */
public interface INameProvider {
	String getName(Object object);
}
