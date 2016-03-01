/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2009. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.authoring.ui.dialogs;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author achen
 * @since 7.5.1
 *
 */
public class StateEditDialog extends Dialog {
	private Text name, des;
	
	private boolean create;
	private String stateName, stateDes;
	
	public StateEditDialog(Shell shell, boolean create, String stateName, String stateDes) {
		super(shell);
		
		this.create = create;
		this.stateName = stateName;
		this.stateDes = stateDes;
	}
	
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(AuthoringUIResources.StateEditDialog_title);
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.numColumns = 2;
		
		Label nameLabel = new Label(composite, SWT.NULL);
		nameLabel.setText(AuthoringUIResources.StateEditDialog_label_name);
		name = new Text(composite, SWT.BORDER | SWT.SINGLE);
		name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label desLabel = new Label(composite, SWT.NULL);
		desLabel.setText(AuthoringUIResources.StateEditDialog_label_des);
		desLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		des = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		{
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = 200;
			gd.heightHint = 100;
			des.setLayoutData(gd);
		}
		
		loadData();
		addListeners();
		
		return composite;
	}
	
	protected Control createContents(Composite parent) {
		Control con = super.createContents(parent);
		updateControls();
		
		return con;
	}
	
	protected void okPressed() {
		stateName = name.getText();
		stateDes = des.getText();
		
		super.okPressed();
	}
	
	private void loadData() {
		if (!create) {
			name.setText(stateName);
			des.setText(stateDes);
		}
	}
	
	private void updateControls() {
		String nameValue = name.getText();
		Button okBtn = getButton(IDialogConstants.OK_ID);

		if (okBtn != null) {
			if ((nameValue == null) || (nameValue.length() == 0)) {
				okBtn.setEnabled(false);
			} else {
				okBtn.setEnabled(true);
			}
		}		
	}
	
	private void addListeners() {
		name.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateControls();				
			}			
		});
		
		//Won't allow comma in state name
		name.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				if (e.character == ',') {
					e.doit = false;
				}				
			}			
		});		
	}
	
	public String getStateName() {
		return stateName;
	}
	
	public String getStateDes() {
		return stateDes;
	}
	
}
