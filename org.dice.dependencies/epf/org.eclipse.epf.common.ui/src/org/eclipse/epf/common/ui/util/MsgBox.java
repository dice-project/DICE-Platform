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
package org.eclipse.epf.common.ui.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Helper class to display a message box.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public final class MsgBox {
	/**
	 * Use this method to show error message if you don't want long path to be
	 * cut off.
	 * 
	 * @param msg
	 */
	public static final void nativeShowError(Shell shell, String msg) {
		if (shell == null) {
			shell = getDefaultShell();
			if (shell == null) {
				return;
			}
		}
		MessageBox msgBox = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
		msgBox.setMessage(msg);
		msgBox.setText(shell.getText());
		msgBox.open();
	}

	public static final void showError(String msg) {
		showError(getDefaultShell(), msg);
	}

	public static final void showError(Shell shell, String msg) {
		WrappedMessageDialog.openError(shell, shell == null ? null : shell
				.getText(), msg);
	}

	public static final void showWarning(String msg) {
		showWarning(getDefaultShell(), msg);
	}

	public static final void showWarning(Shell shell, String msg) {
		WrappedMessageDialog.openWarning(shell, shell == null ? null : shell
				.getText(), msg);
	}

	public static final Shell getDefaultShell() {
		try {
			Display d = Display.getCurrent();
			if (d == null) {
				d = Display.getDefault();
			}

			Shell s = null;
			if (d != null) {
				s = d.getActiveShell();
			}

			return s;
		} catch (RuntimeException e) {
			return null;
		}
	}

	public static final Display getDisplay() {
		try {
			Display d = Display.getCurrent();
			if (d == null) {
				d = Display.getDefault();
			}

			return d;
		} catch (RuntimeException e) {
			return null;
		}
	}

	public static final int prompt(String msg) {
		return prompt(getDefaultShell(), msg);
	}

	public static final int prompt(Shell shell, String msg) {
		return prompt(shell, null, msg, SWT.YES | SWT.NO | SWT.CANCEL);
	}

	public static final int prompt(String msg, int buttons) {
		return prompt(getDefaultShell(), null, msg, buttons);
	}

	public static final int prompt(String title, String msg, int buttons) {
		return prompt(getDefaultShell(), title, msg, buttons);
	}

	public static final int prompt(Shell shell, String msg, int buttons) {
		return prompt(shell, null, msg, buttons);
	}

	public static final int prompt(Shell shell, String title, String msg,
			int buttons) {
		MessageBox msgBox = new MessageBox(shell, buttons | SWT.ICON_QUESTION);
		msgBox.setText(title != null && title.length() > 0 ? title : shell
				.getText());
		msgBox.setMessage(msg);
		return msgBox.open();
	}

}
