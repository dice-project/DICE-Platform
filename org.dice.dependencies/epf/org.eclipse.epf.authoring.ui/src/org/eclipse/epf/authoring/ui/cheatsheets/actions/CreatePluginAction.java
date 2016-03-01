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
package org.eclipse.epf.authoring.ui.cheatsheets.actions;

import org.eclipse.epf.authoring.ui.actions.NewPluginAction;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

/**
 * Create Method Plug-in cheat sheet action.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class CreatePluginAction extends Action implements ICheatSheetAction {

	/**
	 * @see ICheatSheetAction#run(String[], ICheatSheetManager)
	 */
	public void run(String[] params, ICheatSheetManager manager) {
		NewPluginAction.execute(PlatformUI.getWorkbench(), null);
	}

}
