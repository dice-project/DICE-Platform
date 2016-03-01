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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Properties;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

/**
 * Open cheat sheet view
 * 
 * @author Bingxue Xu
 * @since 1.0
 * 
 */
public class OpenCheatsheetView extends Action implements IIntroAction {

	/**
	 * Create an instance
	 *
	 */
	public OpenCheatsheetView() {
		super(AuthoringUIResources.OpenCheatsheetView_title); 
	}

	/* 
	 * @see org.eclipse.ui.intro.config.IIntroAction#run(org.eclipse.ui.intro.IIntroSite, java.util.Properties)
	 */
	public void run(IIntroSite site, Properties params) {
		String cheatsheet_id = params.getProperty("input"); //$NON-NLS-1$
		String toggle_str = params.getProperty("toggle"); //$NON-NLS-1$
		// System.out.println("$$$ INFO: cheatsheet id = " + cheatsheet_id);

		IWorkbenchWindow activeWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		// de-maximaize the welcome, i.e. the intro view window
		if (toggle_str != null && toggle_str.equalsIgnoreCase("true")) { //$NON-NLS-1$
			toggleActiveView();
		}

		try {
			// CheatSheetView obj = new CheatSheetView();
			activeWindow.getActivePage().showView(
					"org.eclipse.ui.cheatsheets.views.CheatSheetView"); //$NON-NLS-1$
			CheatSheetView csView = (CheatSheetView) activeWindow
					.getActivePage().getActivePart();
			csView.setInput(cheatsheet_id);

		} catch (PartInitException exp) {
			// $$$ error handling here.
		}

	}

	/**
	 * Toggle active view
	 *
	 */
	private void toggleActiveView() {
		IWorkbenchWindow activeWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		// de-maximaize the welcome, i.e. the intro view window or any active
		// view
		IWorkbenchPage page = activeWindow.getActivePage();
		if (page != null) {
			if (page instanceof WorkbenchPage) {
				//WorkbenchPage realPage = (WorkbenchPage) page;

				IWorkbenchPartReference partRef = page.getActivePartReference();

				if (partRef != null) {
					((WorkbenchPage) page).toggleZoom(partRef);
				}
			}
		}
	}

}
