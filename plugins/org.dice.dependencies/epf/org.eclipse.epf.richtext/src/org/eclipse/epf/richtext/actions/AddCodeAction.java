/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2011. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.richtext.actions;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.dialogs.AddCodeDialog;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

public class AddCodeAction extends RichTextAction {
	
	public AddCodeAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_ADD_CODE);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_ADD_CODE);
		setToolTipText(RichTextResources.addCodeAction_toolTipText); 
	}

	public void execute(IRichText richText) {
		if (richText != null ) {
			AddCodeDialog dialog = new AddCodeDialog(Display.getCurrent().getActiveShell());
			dialog.open();
			if (dialog.getReturnCode() == Window.OK) {
				String html = RichText.workaroundForObjectParamNode(dialog.getCode());
				richText.executeCommand(RichTextCommand.ADD_HTML, html);
			}			
		}
	}
	
}
