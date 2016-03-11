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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.OpenCheatSheetAction;

/**
 * Switchs cheat sheet and opens it.
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class CheatsheetSwitchJob extends Job {
	private String cheatsheetId;

	/**
	 * Create an instance with cheat sheet name and Id
	 * @param name
	 * @param cheatsheetId
	 */
	public CheatsheetSwitchJob(String name, String cheatsheetId) {
		super(name);
		this.cheatsheetId = cheatsheetId;
	}

	/**
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IStatus run(IProgressMonitor monitor) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				new OpenCheatSheetAction(cheatsheetId).run();
			}
		});
		return Status.OK_STATUS;
	}
}
