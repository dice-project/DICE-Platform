/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epf.library.ui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * Support for showing a Busy Cursor during a long running process.
 */
public class BusyIndicatorHelper {

	static int nextBusyId = 1;
	static final String BUSYID_NAME = "SWT BusyIndicator"; //$NON-NLS-1$
	static final String BUSY_CURSOR = "SWT BusyIndicator Cursor"; //$NON-NLS-1$

	public static Integer showWhile(Display display) {
		if (display == null) {
			return null;
		}

		Integer busyId = new Integer(nextBusyId);
		nextBusyId++;
		Cursor cursor = display.getSystemCursor(SWT.CURSOR_WAIT);
		Shell[] shells = display.getShells();
		for (int i = 0; i < shells.length; i++) {
			Integer id = (Integer) shells[i].getData(BUSYID_NAME);
			if (id == null) {
				shells[i].setCursor(cursor);
				shells[i].setData(BUSYID_NAME, busyId);
			}
		}
		return busyId;
	}
	
	public static void hideWhile(Display display, Integer busyId) {
		if (display == null) {
			return;
		}

		Shell[] shells = display.getShells();
		for (int i = 0; i < shells.length; i++) {
			Integer id = (Integer) shells[i].getData(BUSYID_NAME);
			if (id == busyId) {
				shells[i].setCursor(null);
				shells[i].setData(BUSYID_NAME, null);
			}
		}
	}
	
	/**
	 * Iterate through all of the windows and set them to be disabled or enabled
	 * as appropriate.'
	 * 
	 * @param active
	 *            The set the windows will be set to.
	 */
	public static void setUserInterfaceActive(boolean active) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Shell[] shells = workbench.getDisplay().getShells();
		if (active) {
			for (int i = 0; i < shells.length; i++) {
				shells[i].setEnabled(active);
			}
		} else {
			// Deactive shells in reverse order
			for (int i = shells.length - 1; i >= 0; i--) {
				shells[i].setEnabled(active);
			}
		}
	}
}
