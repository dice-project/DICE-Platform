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
package org.eclipse.epf.library.services;

import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.swt.widgets.Display;

/**
 * a utility class to allow UI update with the valid UI thread.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public abstract class SafeUpdateController {

	/**
	 * sync execute the runnable with the valid UI thread.
	 * @param runnable Runnable
	 */
	public static void syncExec(Runnable runnable) {
		try {
			// since this will cause UI update, which must be executed in the UI
			// thread
			// otherwise, will cause Invalid Thread Access exception
			Display display = Display.getCurrent();
			if (display == null) {
				display = Display.getDefault();
			}
			if (display == null) {
				runnable.run();
			} else {
				display.syncExec(runnable);
			}
		} catch (Exception ex) {
			LibraryPlugin.getDefault().getLogger().logError(ex);
		}
	}

	/**
	 * async execute the runnable with the valid UI thread.
	 * @param runnable Runnable
	 */
	public static void asyncExec(Runnable runnable) {
		try {
			// since this will cause UI update, which must be executed in the UI
			// thread
			// otherwise, will cause Invalid Thread Access exception
			Display display = Display.getCurrent();
			if (display == null) {
				display = Display.getDefault();
			}
			if (display == null) {
				runnable.run();
			} else {
				display.asyncExec(runnable);
			}
		} catch (Exception ex) {
			LibraryPlugin.getDefault().getLogger().logError(ex);
		}
	}

}
