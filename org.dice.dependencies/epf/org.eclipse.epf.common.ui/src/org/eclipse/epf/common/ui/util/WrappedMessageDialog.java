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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Displays a message dialog that displays wrapped text.
 * 
 * @author Bingxue Xu
 * @since 1.0
 */
public class WrappedMessageDialog extends MessageDialog {

	public WrappedMessageDialog(Shell parentShell, String dialogTitle,
			Image dialogTitleImage, String dialogMessage, int dialogImageType,
			String[] dialogButtonLabels, int defaultIndex) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
				dialogImageType, dialogButtonLabels, defaultIndex);
	}

	protected Control createMessageArea(Composite composite) {

		Control rComposite = super.createMessageArea(composite);

		int minWidth = IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH;// + 300;
		// TO-DO: need to revist the # of dialog display units and margin
		// when in other languages
		int charsPerLine = minWidth / 4 - 15;
		GridData gd = (GridData) messageLabel.getLayoutData();
		gd.minimumWidth = minWidth;
		messageLabel.setText(addLineBreaks(message, charsPerLine));
		
		return rComposite;
	}

	String addLineBreaks(String msg, int maxChars) {
		// System.out.println("$$$ DEBUG: original message = " + msg);

		if (msg == null)
			return null;

		StringBuffer strBuf = new StringBuffer();
		int count = 0;
		for (int i = 0; i < msg.length(); i++) {
			strBuf.append(msg.charAt(i));
			if (Character.isWhitespace(msg.charAt(i)))
				count = 0;
			else
				count++;

			if (count >= maxChars) {
				if (!Character.isWhitespace(msg.charAt(i))) {
					strBuf.append("\n"); //$NON-NLS-1$
				}
				count = 0;
			}
		}

		// System.out.println("$$$ DEBUG: broken message = " +
		// strBuf.toString());
		return strBuf.toString();
	}

	public static boolean openConfirm(Shell parent, String title, String message) {
		WrappedMessageDialog dialog = new WrappedMessageDialog(parent, title,
				null, // accept
				// the
				// default
				// window
				// icon
				message, QUESTION, new String[] { IDialogConstants.OK_LABEL,
						IDialogConstants.CANCEL_LABEL }, 0); // OK is the
		// default
		return dialog.open() == 0;
	}
	
	static class ConfirmWithCheckBoxDialog extends WrappedMessageDialog {
		private String checkBoxLabel;
		private Button checkBox;
		private boolean checkBoxChecked = false;

		public ConfirmWithCheckBoxDialog(Shell parentShell, String dialogTitle,
				Image dialogTitleImage, String dialogMessage, int dialogImageType,
				String[] dialogButtonLabels, int defaultIndex, String checkBoxLabel) {
			super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
					dialogImageType, dialogButtonLabels, defaultIndex);
			this.checkBoxLabel = checkBoxLabel;
		}
		
		@Override
		protected Control createMessageArea(Composite composite) {
			Control rComposite = super.createMessageArea(composite);

			checkBox = new Button(composite, SWT.CHECK);
			checkBox.setText(checkBoxLabel);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			checkBox.setLayoutData(gd);
			
			return rComposite;
		}
		
	    protected void buttonPressed(int buttonId) {
	    	checkBoxChecked = checkBox.getSelection();
	    	super.buttonPressed(buttonId);
	    }
		
		public boolean isCheckBoxChecked() {
			return checkBoxChecked;
		}
	    
	}
	
	//return: 0 = cancel, 1 = ok with check-box checked, 2 = ok with check box unchecked 
	public static int openConfirmWithCheckBox(Shell parent, String title, String message, String checkBoxLabel) {
		ConfirmWithCheckBoxDialog dialog = new ConfirmWithCheckBoxDialog(parent, title,
				null, // accept
				// the
				// default
				// window
				// icon
				message, QUESTION, new String[] { IDialogConstants.OK_LABEL,
						IDialogConstants.CANCEL_LABEL }, 0, checkBoxLabel); // OK is the default
		
		int ix = dialog.open() ;
		if (ix != 0) {
			return 0;
		}
		
		return dialog.isCheckBoxChecked() ? 1 : 2;
	}

	public static void openError(Shell parent, String title, String message) {
		WrappedMessageDialog dialog = new WrappedMessageDialog(parent, title,
				null, // accept
				// the
				// default
				// window
				// icon
				message, ERROR, new String[] { IDialogConstants.OK_LABEL }, 0); // ok
		// is
		// the
		// default
		dialog.open();
		return;
	}

	public static void openInformation(Shell parent, String title,
			String message) {
		WrappedMessageDialog dialog = new WrappedMessageDialog(parent, title,
				null, // accept
				// the
				// default
				// window
				// icon
				message, INFORMATION,
				new String[] { IDialogConstants.OK_LABEL }, 0);
		// ok is the default
		dialog.open();
		return;
	}

	public static boolean openQuestion(Shell parent, String title,
			String message) {
		WrappedMessageDialog dialog = new WrappedMessageDialog(parent, title,
				null, // accept
				// the
				// default
				// window
				// icon
				message, QUESTION, new String[] { IDialogConstants.YES_LABEL,
						IDialogConstants.NO_LABEL }, 0); // yes is the
		// default
		return dialog.open() == 0;
	}

	public static void openWarning(Shell parent, String title, String message) {
		WrappedMessageDialog dialog = new WrappedMessageDialog(parent, title,
				null, // accept
				// the
				// default
				// window
				// icon
				message, WARNING, new String[] { IDialogConstants.OK_LABEL }, 0); // ok
		// is
		// the
		// default
		dialog.open();
		return;
	}
}
