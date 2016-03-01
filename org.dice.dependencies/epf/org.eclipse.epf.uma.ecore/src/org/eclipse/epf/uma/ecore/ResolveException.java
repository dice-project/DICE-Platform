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
package org.eclipse.epf.uma.ecore;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * Signals that an error has occurred while resolving a proxy object.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ResolveException extends WrappedException {

	private static final long serialVersionUID = 3258412815814636081L;

	private EObject proxy;

	private EObject resolver;

	/**
	 * Creates a new instance.
	 * 
	 * @param proxy
	 *            a proxy object
	 * @param msg
	 *            an error message
	 * @param resolver
	 *            the proxy resolver
	 */
	public ResolveException(EObject proxy, String msg, EObject resolver) {
		super(msg, null);
		this.proxy = proxy;
		this.resolver = resolver;
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param proxy
	 *            a proxy object
	 * @param exception
	 *            the exception that was thrown
	 */
	public ResolveException(EObject proxy, Exception exception, EObject resolver) {
		super(exception);
		this.proxy = proxy;
		this.resolver = resolver;
	}

	/**
	 * Returns the proxy object.
	 * 
	 * @return the proxy object
	 */
	public EObject getProxy() {
		return proxy;
	}

	/**
	 * Returns the proxy resolver.
	 * 
	 * @return the proxy resolver
	 */
	public EObject getResolver() {
		return resolver;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof ResolveException) {
			ResolveException re = (ResolveException) obj;
			if ((resolver == re.resolver) || (resolver == null && re.resolver == null)) {
				if (proxy == re.proxy) {
					return true;
				}
				if (proxy instanceof InternalEObject) {
					URI uri = ((InternalEObject) proxy).eProxyURI();
					if (uri != null && re.proxy instanceof InternalEObject) {
						return uri.equals(((InternalEObject) re.proxy)
								.eProxyURI());
					}
				}
			}
		}
		return super.equals(obj);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (proxy instanceof InternalEObject) {
			int hc = resolver == null ? 0 : resolver.hashCode();
			URI uri = ((InternalEObject) proxy).eProxyURI();
			if (uri != null) {
				return (Integer.toString(hc) + uri).hashCode();
			}
			return hc;
		}
		return super.hashCode();
	}

}
