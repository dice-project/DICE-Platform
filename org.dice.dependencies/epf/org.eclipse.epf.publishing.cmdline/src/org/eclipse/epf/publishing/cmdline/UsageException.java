/*******************************************************************************
 * Copyright (c) 2008 IBM, TietoEnator, corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Brian Schlosser - initial implementation
 *  Roman Smirak  - update for EPFC 1.2 and 1.5
 *******************************************************************************/ 
package org.eclipse.epf.publishing.cmdline;

public class UsageException
        extends Exception {

	private static final long serialVersionUID = 2410647848153957620L;

	public UsageException(String message) {
        super(message);
    }
}
