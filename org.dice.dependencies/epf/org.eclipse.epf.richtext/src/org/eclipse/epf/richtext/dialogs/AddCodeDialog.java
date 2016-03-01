/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2011. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.richtext.dialogs;

import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddCodeDialog extends BaseDialog {
	private Text html;
	private String htmlStr;
	
	public AddCodeDialog(Shell parent) {
		super(parent);
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText(RichTextResources.addCodeDialog_Msg);
		{
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			label.setLayoutData(gd);
		}
		
		html = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		{
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.horizontalSpan = 2;
			gd.widthHint = 300;
			gd.heightHint = 200;
			html.setLayoutData(gd);
		}
		html.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {				
				okButton.setEnabled(html.getText().trim().length() > 0);		
			}			
		});
		
		super.getShell().setText(RichTextResources.addCodeDialog_title);
		
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		okButton.setEnabled(false);
	}
	
	protected void okPressed() {
		//add the extra "<br/>" to avoid Jtidy problem
		String br = "<br/>"; //$NON-NLS-1$
		String customHtml = html.getText().trim();		
		htmlStr = br + customHtml;
		
		super.okPressed();
	}
	
	public String getCode() {
		return htmlStr;
	}

}
