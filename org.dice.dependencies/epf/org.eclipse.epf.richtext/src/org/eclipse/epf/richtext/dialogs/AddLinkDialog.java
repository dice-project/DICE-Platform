/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.richtext.dialogs;

import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.html.Link;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Prompts the user to specify the file that will be used to create a HTML
 * &lt;a&gt; tag in the rich text editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class AddLinkDialog extends BaseDialog {

	protected Link link = new Link();

	protected Text urlText;

	protected String basePath;

	protected Composite composite;

	protected Label urlLabel;

	protected Text urlDisplayNameText;

	protected Label urlDisplayNameLabel;

	protected ModifyListener urlTextModifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			if (okButton != null) {
				okButton.setEnabled(urlText.getText().trim().length() > 0);
			}
		}
	};

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent shell
	 */
	public AddLinkDialog(Shell parent, String basePath) {
		super(parent);
		this.basePath = basePath;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		composite = (Composite) super.createDialogArea(parent);

		urlLabel = new Label(composite, SWT.NONE);
		urlLabel.setText(RichTextResources.urlLabel_text);
		urlText = new Text(composite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 300;
		urlText.setLayoutData(gridData);
		urlText.addModifyListener(urlTextModifyListener);

		urlDisplayNameLabel = new Label(composite, SWT.NONE);
		urlDisplayNameLabel.setText(RichTextResources.urlDisplayNameLabel_text);
		urlDisplayNameText = new Text(composite, SWT.BORDER);
		GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);
		gridData2.widthHint = 300;
		urlDisplayNameText.setLayoutData(gridData2);

		super.getShell().setText(RichTextResources.addLinkDialog_title);

		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		okButton.setEnabled(false);
	}

	@Override
	protected void okPressed() {
		String url = urlText.getText();
		if (url != null && url.length() > 0) {
			link.setURL(url);
			link.setName(urlDisplayNameText.getText());
		}
		super.okPressed();
	}

	/**
	 * Gets the user specified link.
	 * 
	 * @return an <code>Link</code> object
	 */
	public Link getLink() {
		return link;
	}

}