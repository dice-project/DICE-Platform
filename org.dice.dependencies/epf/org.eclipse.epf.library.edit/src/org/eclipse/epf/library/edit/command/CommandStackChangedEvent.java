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
package org.eclipse.epf.library.edit.command;

import java.util.EventObject;

/**
 * Listens to changes to a commend stack.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CommandStackChangedEvent extends EventObject {
	
	public static final int SAVED = 1;

	public static final int EXECUTED = 2;

	public static final int UNDO = 4;

	public static final int UNDO_ALL = 8;

	private int type;

	public CommandStackChangedEvent(Object source, int type) {
		super(source);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	private static final long serialVersionUID = 3256727294604292916L;

}