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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.common.IActivator;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A message dialog used for displaying error, warning, confirmation or
 * informational messages.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MsgDialog {

	// The plug-in ID.
	private String pluginId;

	// The plug-in logger.
	private Logger logger;
	
	/**
	 * Create a new instance given the plug-in instance.
	 * 
	 * @param plugin
	 *            The plugin instance.
	 */
	public MsgDialog(IActivator plugin) {
		this.pluginId = plugin.getId();
		this.logger = plugin.getLogger();
	}
		
	protected Shell getShell() {
		return PlatformUI.getWorkbench().getDisplay().getActiveShell();
	}

	/**
	 * Displays the given error message in an error dialog without the error
	 * reason and Details button.
	 * <p>
	 * Note: The error message will be written to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 */
	public void displayError(String title, String msg) {
		display(IStatus.ERROR, title, msg, null, null, null);
	}

	/**
	 * Displays the given error message in an error dialog without the error
	 * reason and Details button.
	 * <p>
	 * Note: The error message and the exception stack trace will be written to
	 * the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param ex
	 *            The exception associated with the error.
	 */
	public void displayError(String title, String msg, Throwable ex) {
		display(IStatus.ERROR, title, msg, null, null, ex);
	}

	/**
	 * Displays the given error message in an error dialog without the Details
	 * button.
	 * <p>
	 * Note: The error message will be written to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param reason
	 *            The reason for the error.
	 */
	public void displayError(String title, String msg, String reason) {
		display(IStatus.ERROR, title, msg, reason, null, null);
	}

	/**
	 * Displays the given error message in an error dialog without the Details
	 * button.
	 * <p>
	 * Note: The error message and the exception stack trace will be written to
	 * the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param reason
	 *            The reason for the error.
	 * @param ex
	 *            The exception associated with the error.
	 */
	public void displayError(String title, String msg, String reason,
			Throwable ex) {
		display(IStatus.ERROR, title, msg, reason, null, ex);
	}

	/**
	 * Displays the given error message in an error dialog. The Details button
	 * will be displayed if the <code>details</code> parameter it not null or
	 * empty.
	 * <p>
	 * Note: The error message will be written to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param reason
	 *            The reason for the error.
	 * @param details
	 *            The error details.
	 */
	public void displayError(String title, String msg, String reason,
			String details) {
		display(IStatus.ERROR, title, msg, reason, details, null);
	}

	/**
	 * Displays the given error message in an error dialog. The Details button
	 * will be displayed if the <code>details</code> parameter it not null or
	 * empty.
	 * <p>
	 * Note: The error message and the exception stack trace will be written to
	 * the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param reason
	 *            The reason for the error.
	 * @param details
	 *            The error details.
	 * @param ex
	 *            The exception associated with the error.
	 */
	public void displayError(String title, String msg, String reason,
			String details, Throwable ex) {
		display(IStatus.ERROR, title, msg, reason, details, ex);
	}

	/**
	 * Displays the given error status in an error dialog.
	 * <p>
	 * Note: The error message and the exception stack trace will be written to
	 * the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param status
	 *            The error status.
	 */
	public int displayError(String title, IStatus status) {
		return display(title, null, status);
	}

	/**
	 * Displays the given error status in an error dialog.
	 * <p>
	 * Note: The error message and the exception stack trace will be written to
	 * the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param status
	 *            The error status.
	 */
	public int displayError(String title, String msg, IStatus status) {
		return display(title, msg, status);
	}

	/**
	 * Displays the given warning message in a warning dialog without the
	 * warning reason and Details button.
	 * <p>
	 * Note: The waning message will be written to the log file.
	 * 
	 * @param title
	 *            The title for the warning dialog.
	 * @param msg
	 *            The warning message.
	 */
	public void displayWarning(String title, String msg) {
		display(IStatus.WARNING, title, msg, null, null, null);
	}
	
	/**
	 * Displays the given warning message in a warning dialog without the
	 * warning reason and Details button.
	 * <p>
	 * Note: The warning message and the exception stack trace will be written
	 * to the log file.
	 * 
	 * @param title
	 *            The title for the warning dialog.
	 * @param msg
	 *            The warning message.
	 * @param ex
	 *            The exception associated with the warning.
	 */
	public void displayWarning(String title, String msg, Throwable ex) {
		display(IStatus.WARNING, title, msg, null, null, ex);
	}

	/**
	 * Displays the given warning message in a warning dialog without the
	 * warning reason and Details button.
	 * <p>
	 * Note: The waning message will be written to the log file.
	 * 
	 * @param title
	 *            The title for the warning dialog.
	 * @param msg
	 *            The warning message.
	 * @param reason
	 *            The reason for the warning.
	 */
	public void displayWarning(String title, String msg, String reason) {
		display(IStatus.WARNING, title, msg, reason, null, null);
	}

	/**
	 * Displays the given warning message in a warning dialog without the
	 * warning reason and Details button.
	 * <p>
	 * Note: The warning message and the exception stack trace will be written
	 * to the log file.
	 * 
	 * @param title
	 *            The title for the warning dialog.
	 * @param msg
	 *            The warning message.
	 * @param reason
	 *            The reason for the warning.
	 * @param ex
	 *            The exception associated with the warning.
	 */
	public void displayWarning(String title, String msg, String reason,
			Throwable ex) {
		display(IStatus.WARNING, title, msg, reason, null, ex);
	}

	/**
	 * Displays the given warning message in a warning dialog. The Details
	 * button will be displayed if the <code>details</code> parameter it not
	 * null or empty.
	 * <p>
	 * Note: The waning message will be written to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The warning message.
	 * @param reason
	 *            The reason for the warning.
	 * @param details
	 *            The warning details.
	 */
	public void displayWarning(String title, String msg, String reason,
			String details) {
		display(IStatus.WARNING, title, msg, reason, details, null);
	}

	/**
	 * Displays the given warning message in a warning dialog. The Details
	 * button will be displayed if the <code>details</code> parameter it not
	 * null or empty.
	 * <p>
	 * Note: The warning message and the exception stack trace will be written
	 * to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The warning message.
	 * @param reason
	 *            The reason for the warning.
	 * @param details
	 *            The warning details.
	 * @param ex
	 *            The exception associated with the warning.
	 */
	public void displayWarning(String title, String msg, String reason,
			String details, Throwable ex) {
		display(IStatus.WARNING, title, msg, reason, details, ex);
	}

	/**
	 * Displays the given warning status in an error dialog.
	 * <p>
	 * Note: The warning message and the exception stack trace will be written
	 * to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param status
	 *            The error status.
	 */
	public void displayWarning(String title, IStatus status) {
		display(title, null, status);
	}

	/**
	 * Displays the given warning status in an error dialog.
	 * <p>
	 * Note: The warning message and the exception stack trace will be written
	 * to the log file.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param status
	 *            The error status.
	 */
	public void displayWarning(String title, String msg, IStatus status) {
		display(title, msg, status);
	}

	/**
	 * Displays the given error or warning message in an error or warning
	 * dialog. The Details button will be displayed if the <code>details</code>
	 * parameter it not null or empty.
	 * 
	 * @param severity
	 *            The severity, either IStatus.ERROR or IStatus.WARNING.
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param reason
	 *            The reason for the error.
	 * @param details
	 *            The error details.
	 * @param ex
	 *            The exception associated with the error.
	 */
	public synchronized void display(int severity, String title, String msg,
			String reason, String details, Throwable ex) {
		if (msg == null && ex != null) {
			msg = ex.getMessage();
		}

		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		if (severity == IStatus.ERROR || severity == IStatus.WARNING) {
			logger.logMsg(severity, msg, ex);
		} else {
			throw new IllegalArgumentException(
					"severity argument must be IStatus.ERROR or IStatus.WARNING"); //$NON-NLS-1$
		}

		Image oldImage = org.eclipse.jface.dialogs.ErrorDialog
				.getDefaultImage();
		Image shellImage = getShellImage();
		if (shellImage != null) {
			ErrorDialog.setDefaultImage(shellImage);
		}

		Shell shell = getShell();

		if (details != null && details.length() > 0) {
			MultiStatus mStatus = new MultiStatus(pluginId, IStatus.OK, reason,
					ex);
			Status status = new Status(severity, pluginId, IStatus.OK, details,
					ex);
			mStatus.add(status);
			org.eclipse.jface.dialogs.ErrorDialog.openError(shell, title, msg,
					mStatus);
		} else if (reason != null && reason.length() > 0) {
			Status status = new Status(severity, pluginId, IStatus.OK, reason,
					ex);
			org.eclipse.jface.dialogs.ErrorDialog.openError(shell, title, msg,
					status);
		} else {
			if (severity == IStatus.ERROR) {
				WrappedMessageDialog.openError(shell, title, msg);
			} else if (severity == IStatus.WARNING) {
				WrappedMessageDialog.openWarning(shell, title, msg);
			}
		}

		if (shellImage != null) {
			ErrorDialog.setDefaultImage(oldImage);
		}
	}

	/**
	 * Displays the given error or warning message in an error or warning
	 * dialog. The Details button will be displayed if the <code>details</code>
	 * parameter it not null or empty.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param status
	 *            The error status.
	 */
	public synchronized int display(String title, IStatus status) {
		return display(title, null, status);
	}

	/**
	 * Displays the given error or warning message in an error or warning
	 * dialog. The Details button will be displayed if the <code>details</code>
	 * parameter it not null or empty.
	 * 
	 * @param title
	 *            The title for the error dialog.
	 * @param msg
	 *            The error message.
	 * @param status
	 *            The error status.
	 */
	public synchronized int display(String title, String msg, IStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("status argument cannot be null"); //$NON-NLS-1$
		}

		if (msg == null) {
			msg = status.getMessage();
		}
		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		int severity = status.getSeverity();
		if (severity == IStatus.ERROR || severity == IStatus.WARNING) {
			logger.logMsg(severity, msg, status.getException());
		}

		Image oldImage = org.eclipse.jface.dialogs.ErrorDialog
				.getDefaultImage();
		Image shellImage = getShellImage();
		if (shellImage != null) {
			ErrorDialog.setDefaultImage(shellImage);
		}

		Shell shell = getShell();

		int rc = org.eclipse.jface.dialogs.ErrorDialog.openError(shell, title,
				msg, status);

		if (shellImage != null) {
			ErrorDialog.setDefaultImage(oldImage);
		}

		return rc;
	}

	/**
	 * Displays the given message in a informational dialog with an "OK" button.
	 * 
	 * @param title
	 *            The title for the information dialog.
	 * @param msg
	 *            The message to display.
	 */
	public void displayInfo(String title, String msg) {
		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		Image oldImage = WrappedMessageDialog.getDefaultImage();
		Image shellImage = getShellImage();
		if (shellImage != null) {
			WrappedMessageDialog.setDefaultImage(shellImage);
		}

		Shell shell = getShell();
		WrappedMessageDialog.openInformation(shell, title, msg);

		if (shellImage != null) {
			WrappedMessageDialog.setDefaultImage(oldImage);
		}
	}

	/**
	 * Displays the given message in a question dialog with a "Yes" and "No"
	 * button.
	 * 
	 * @param title
	 *            The title for the information dialog.
	 * @param msg
	 *            The message to display.
	 */
	public boolean displayPrompt(String title, String msg) {
		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		Image oldImage = WrappedMessageDialog.getDefaultImage();
		Image shellImage = getShellImage();
		if (shellImage != null) {
			WrappedMessageDialog.setDefaultImage(shellImage);
		}

		Shell shell = getShell();
		boolean result = WrappedMessageDialog.openQuestion(shell, title, msg);

		if (shellImage != null) {
			WrappedMessageDialog.setDefaultImage(oldImage);
		}

		return result;
	}

	/**
	 * Displays the given message in a confirmation dialog with a "Yes" and
	 * "Cancel" button.
	 * 
	 * @param title
	 *            The title for the information dialog.
	 * @param msg
	 *            The message to display.
	 */
	public boolean displayConfirmation(String title, String msg) {
		return displayConfirmationWithCheckBox(title, msg, null) > 0;
	}
	
	//return: 0 = cancel, 1 = ok with check-box checked, 2 = ok with check box unchecked 
	public int displayConfirmationWithCheckBox(String title, String msg, String checkBoxLabel) {
		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		Image oldImage = WrappedMessageDialog.getDefaultImage();
		Image shellImage = getShellImage();
		if (shellImage != null) {
			WrappedMessageDialog.setDefaultImage(shellImage);
		}

		Shell shell = getShell();

		if (shellImage != null) {
			WrappedMessageDialog.setDefaultImage(oldImage);
		}

		if (checkBoxLabel == null) {
			boolean b = WrappedMessageDialog.openConfirm(shell, title, msg);
			return b ? 1 : 0;
		}

		return WrappedMessageDialog.openConfirmWithCheckBox(shell, title, msg, checkBoxLabel);
	}

	public int displayConfirmation(String title, String msg, IStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("status argument cannot be null"); //$NON-NLS-1$
		}

		if (msg == null) {
			msg = status.getMessage();
		}
		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		Image shellImage = getShellImage();
		if (shellImage != null) {
			ErrorDialogNoReason.setDefaultImage(shellImage);
		}

		Shell shell = getShell();

		int rc = ErrorDialogNoReason.openError(shell, title, msg, status);

		return rc;

	}

	/**
	 * Returns the image for the current shell.
	 * 
	 * @return The current shell image.
	 */
	private Image getShellImage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			IWorkbenchWindow workbenchWindow = workbench
					.getActiveWorkbenchWindow();
			if (workbenchWindow != null) {
				Shell shell = workbenchWindow.getShell();
				if (shell != null) {
					return shell.getImage();
				}
			}
		}
		return null;
	}

}