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

import java.io.File;

import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.html.Image;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Prompts the user to specify the image that will be used to create a HTML
 * &lt;image&gt; tag in the rich text editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class AddImageDialog extends BaseDialog {

	protected Image image = new Image();

	protected Text urlText;

	protected Button browseButton;

	protected SelectionAdapter browseSelectionAdapter = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			FileDialog dialog = new FileDialog(Display.getCurrent()
					.getActiveShell(), SWT.OPEN);
			dialog
					.setFilterExtensions(new String[] {
							"*.gif", "*.jpg", "*.bmp" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			String imageFile = dialog.open();
			if (imageFile != null && imageFile.length() > 0) {
				File file = new File(imageFile);
				try {
					String url = file.toURL().toExternalForm();
					urlText.setText(url);
				} catch (Exception e) {
					logger.logError(e);
				}
			}
		}
	};

	protected ModifyListener urlTextListener = new ModifyListener() {
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
	public AddImageDialog(Shell parent) {
		super(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.numColumns = 3;

		Label urlLabel = new Label(composite, SWT.NONE);
		urlLabel.setText(RichTextResources.urlLabel_text);
		urlText = new Text(composite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 300;
		urlText.setLayoutData(gridData);
		urlText.addModifyListener(urlTextListener);

		browseButton = new Button(composite, SWT.NONE);
		browseButton.setText(RichTextResources.browseButton_text);
		browseButton.addSelectionListener(browseSelectionAdapter);

		super.getShell().setText(RichTextResources.addImageDialog_title);

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
			image.setURL(url);
		}
		super.okPressed();
	}

	/**
	 * Gets the user specified image.
	 * 
	 * @return an <code>Image</code> object
	 */
	public Image getImage() {
		return image;
	}

}