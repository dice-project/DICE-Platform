/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util.clipboard;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;

/**
 * Data class used by {@link UriTransfer} to store a list of {@link URI} strings
 * and additional lookup metadata.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class UriTransferData {

	private final List<String> uriStrings;

	static final String LINE_SEP = System.getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Creates {@link UriTransferData}.
	 * 
	 * @param uriStrings
	 */
	public UriTransferData(List<String> uriStrings) {
		this.uriStrings = uriStrings;
	}

	/**
	 * @return the uriStrings
	 */
	public List<String> getUriStrings() {
		return this.uriStrings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.uriStrings == null ? 0 : this.uriStrings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UriTransferData other = (UriTransferData) obj;
		if (this.uriStrings == null) {
			if (other.uriStrings != null) {
				return false;
			}
		} else if (!this.uriStrings.equals(other.uriStrings)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder s = new StringBuilder();
		if (this.uriStrings != null && !this.uriStrings.isEmpty()) {
			s.append("\n"); //$NON-NLS-1$
			for (final Iterator<String> iter = this.uriStrings.iterator(); iter.hasNext();) {
				final String uri = iter.next();
				s.append(uri);
				if (iter.hasNext()) {
					s.append("\n"); //$NON-NLS-1$
				}
			}
		}
		return s.toString();
	}
}
