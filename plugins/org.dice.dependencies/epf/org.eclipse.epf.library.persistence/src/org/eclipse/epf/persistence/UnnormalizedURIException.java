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
package org.eclipse.epf.persistence;

import org.eclipse.emf.common.util.URI;

/**
 * Exception for URI that cannot be normalized.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UnnormalizedURIException extends MultiFileIOException {

	private URI uri;

	/**
	 * @param msg
	 */
	public UnnormalizedURIException(URI uri, String msg) {
		super(msg);
		this.uri = uri;
	}
	
	public URI getURI() {
		return uri;
	}

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8129316210755337816L;

}
