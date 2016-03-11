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
package org.eclipse.epf.authoring.ui.util;

import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleListener;

/**
 * Accessible listener while authoring the content
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 *
 */
public class AuthoringAccessibleListener implements AccessibleListener {
	
	private String description;
	private String name;
	
	/**
	 * Creates an instance
	 * @param name
	 */
	public AuthoringAccessibleListener(String name){
		this.name = name;
	}
	
	/**
	 * Create an instance
	 * @param name
	 * @param description
	 */
	public AuthoringAccessibleListener(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	/**
	 * @see org.eclipse.swt.accessibility.AccessibleListener#getDescription(org.eclipse.swt.accessibility.AccessibleEvent)
	 */
	public void getDescription(AccessibleEvent e) {
		e.result = this.description;
	}

	/**
	 * @see org.eclipse.swt.accessibility.AccessibleListener#getHelp(org.eclipse.swt.accessibility.AccessibleEvent)
	 */
	public void getHelp(AccessibleEvent e) {

	}

	/**
	 * @see org.eclipse.swt.accessibility.AccessibleListener#getKeyboardShortcut(org.eclipse.swt.accessibility.AccessibleEvent)
	 */
	public void getKeyboardShortcut(AccessibleEvent e) {
	
	}

	/**
	 * @see org.eclipse.swt.accessibility.AccessibleListener#getName(org.eclipse.swt.accessibility.AccessibleEvent)
	 */
	public void getName(AccessibleEvent e) {		
		e.result = this.name;
	}
	
	
	/**
	 * Set name for the listener
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
}
