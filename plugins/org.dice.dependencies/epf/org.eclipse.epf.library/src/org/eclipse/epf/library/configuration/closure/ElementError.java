/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.configuration.closure;

import org.eclipse.core.resources.IMarker;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;

/**
 * This class identifies a configuration error between two elements
 * 
 * @author jxi
 *
 */
public class ElementError  extends ErrorInfo implements IConfigurationError {

	// need the config id to uniquely identify an error
	String configId;
	
	public ElementError(MethodConfiguration config,  
			int errorLevel, String message, 
			Object ownerElement, Object causeElement, 
			int relation, String messageId) {
		super(errorLevel, message, ownerElement, causeElement, relation, messageId);
		this.configId = config.getGuid();
	}

	
	// methods for the IConfigurationError
	//
	public MethodElement getCauseMethodElement() {
		if ( this.causeElement instanceof MethodElement) {
			return (MethodElement)this.causeElement;
		}
		
		return null;
	}

	public MethodElement getErrorMethodElement() {
		if ( this.ownerElement instanceof MethodElement ) {
			return (MethodElement)this.ownerElement;
		}
		return null;
	}

	public String getId() {
		MethodElement m1 = getErrorMethodElement();
		MethodElement m2 = getCauseMethodElement();
		if ( m1 != null && m2 != null ) {
			return configId + "," + m1.getGuid() + "," + m2.getGuid(); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		return Integer.toHexString(this.hashCode());
	}

	public int getSeverity() {
		switch (getErrorLevel() ) {
		case ErrorInfo.ERROR:
			return IMarker.SEVERITY_ERROR;
		case ErrorInfo.WARNING: 
			return IMarker.SEVERITY_WARNING;
		}
		
		return IMarker.SEVERITY_INFO;

	}
	
}
