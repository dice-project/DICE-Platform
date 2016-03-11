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
package org.eclipse.epf.authoring.ui.dialogs;

import java.io.File;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.richtext.dialogs.AddImageDialog;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * Prompts the user to specify the image that will be used to create a HTML
 * <img> tag in the Rich Text Editor.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodAddImageDialog extends AddImageDialog {

	private static final int MAX_HEIGHT = 600;

	private static final int MAX_WIDTH = 600;

	private static final int DEFAULT_HEIGHT = 200;

	private static final int DEFAULT_WIDTH = 200;

	private MethodElement methodElement;

	private File fileToCopy;

	private String elementLoc;

	private int height = DEFAULT_HEIGHT;

	private int width = DEFAULT_WIDTH;

	private String altTag = ""; //$NON-NLS-1$

	private Text heightText;

	private Text widthText;

	private Text altTagText;

	private ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent event) {
			if (okButton != null) {
				try {
					if (event.widget == urlText) {
						File urlFile = new File(urlText.getText().trim());
						if (urlFile.isAbsolute()) {
							fileToCopy = urlFile;
						} else {
							fileToCopy = new File(elementLoc, urlText.getText().trim());
						}
						if (fileToCopy.isFile() && fileToCopy.canRead()) {
							// Try to get the image info.
							setImageInfo(fileToCopy.getAbsolutePath());
						}
					}
					height = Integer.parseInt(heightText.getText().trim());
					width = Integer.parseInt(widthText.getText().trim());
					okButton.setEnabled(fileToCopy.isFile() && fileToCopy.canRead() && height > 0
							&& width > 0);
				} catch (Exception e) {
					// if the above fails, let the user click ok if the file is readable
					okButton.setEnabled(fileToCopy.isFile() && fileToCopy.canRead());
				}
			}
		}
	};

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            The parent shell.
	 * @param methodElement
	 *            A method element.
	 */
	public MethodAddImageDialog(Shell parent, MethodElement methodElement) {
		super(parent);
		this.methodElement = methodElement;
		elementLoc = ResourceHelper.getFolderAbsolutePath(methodElement);
	}

	/**
	 * @see org.eclipse.epf.richtext.dialogs.BaseDialog#createDialogArea(Composite
	 *      parent)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		((GridLayout) composite.getLayout()).numColumns = 4;
		((GridData) urlText.getLayoutData()).horizontalSpan = 2;

		urlText.removeModifyListener(urlTextListener);
		urlText.addModifyListener(modifyListener);

		browseButton.removeSelectionListener(browseSelectionAdapter);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dialog = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.OPEN);
				dialog.setFilterPath(ResourceHelper.getFolderAbsolutePath(methodElement));
				String attachFile = dialog.open();
				if (attachFile != null && attachFile.length() > 0) {
					try {
						File file = new File(dialog.getFilterPath(), dialog
								.getFileName());
						urlText.setText(file.getAbsolutePath());
					} catch (Exception e) {
						logger.logError(e);
					}
				}
			}
		});

		Label heightLabel = new Label(composite, SWT.NONE);
		heightLabel.setText(AuthoringUIResources.heightLabel_text); 

		heightText = new Text(composite, SWT.BORDER);
		heightText.setLayoutData(new GridData());
		heightText.setText("" + DEFAULT_HEIGHT); //$NON-NLS-1$
		heightText.addModifyListener(modifyListener);

		Label imageSizeLabel = new Label(composite, SWT.WRAP);
		{
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = 300;
			gd.horizontalSpan = 2;
			gd.verticalSpan = 2;
			gd.horizontalIndent = 10;
			imageSizeLabel.setLayoutData(gd);
		}
		imageSizeLabel.setText(AuthoringUIResources.imageSizeLabel_text); 

		Label widthLabel = new Label(composite, SWT.NONE);
		widthLabel.setText(AuthoringUIResources.widthLabel_text); 

		widthText = new Text(composite, SWT.BORDER);
		widthText.setLayoutData(new GridData());
		widthText.setText("" + DEFAULT_WIDTH); //$NON-NLS-1$
		widthText.addModifyListener(modifyListener);

		Label altLabel = new Label(composite, SWT.NONE);
		altLabel.setText(AuthoringUIResources.altTagLabel_text); 
		altTagText = new Text(composite, SWT.BORDER);
		{
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			altTagText.setLayoutData(gd);
		}
		altTagText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				altTag = altTagText.getText().trim();
			}
		});

		return composite;
	}

	/**
	 * Returns the file to be copied to a resource folder.
	 */
	public File getFileToCopy() {
		return fileToCopy;
	}

	private void setImageInfo(String imageName) {
		File imageFile = new File(imageName);
		if (imageFile.exists()) {
			try {
				ImageData imageData = new ImageData(imageName);
				heightText.setText(String.valueOf(imageData.height));
				widthText.setText(String.valueOf(imageData.width));
			} catch (Exception e) {
				// set default height/width if we can't obtain ImageData
				heightText.setText(String.valueOf(DEFAULT_HEIGHT));
				widthText.setText(String.valueOf(DEFAULT_WIDTH));
			}
		} else {
			heightText.setText(String.valueOf(DEFAULT_HEIGHT));
			widthText.setText(String.valueOf(DEFAULT_WIDTH));
		}
	}

	/**
	 * Returns the image height.
	 */
	public int getHeight() {
		return Math.min(MAX_HEIGHT, Math.max(10, height));
	}

	/**
	 * Returns the image width.
	 */
	public int getWidth() {
		return Math.min(MAX_WIDTH, Math.max(10, width));
	}

	/**
	 * Returns the image ALT tag.
	 */
	public String getAltTag() {
		return altTag;
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		super.okPressed();
	}

}
