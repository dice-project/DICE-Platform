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

import org.eclipse.jface.action.Action;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

/**
 * Create switch cheat sheet action
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SwitchCheatsheetTo extends Action implements ICheatSheetAction {

	
	/**
	 * @see org.eclipse.ui.cheatsheets.ICheatSheetAction#run(java.lang.String[], org.eclipse.ui.cheatsheets.ICheatSheetManager)
	 */
	public void run(String[] params, ICheatSheetManager manager) {

		if (params.length <= 0)
			return;

		String cs_id = params[0];

		// System.out.println("$$$ DEBUG: in SwitchCheatsheetTo.run(...) cs_ID =
		// " + cs_id); //$NON-NLS-1$

		CheatsheetSwitchJob switchJob = new CheatsheetSwitchJob(
				"Switch Cheatsheet", cs_id); //$NON-NLS-1$
		switchJob.schedule(50);

	}
}
