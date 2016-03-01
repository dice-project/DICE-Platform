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
package org.eclipse.epf.library.edit.ui;

import org.eclipse.emf.common.command.Command;
import org.eclipse.epf.library.edit.command.IActionManager;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IActionTypeProvider {

	public static final int COPY = 1;

	public static final int EXTEND = 2;
	
	public static final int DEEP_COPY = 3;

	void setInputData(Object object);

	void setActionManager(IActionManager actionMgr);

	/**
	 * Executes given command based on the choice that user selected from given
	 * <code>choices</code>. Choice can be one of the constant {@link #COPY},
	 * {@link #EXTEND}, or {@link #DEEP_COPY}
	 * 
	 * @param cmd
	 * @param choices
	 */
	void execute(Command cmd, int[] choices);

//	int getActionType();

}
