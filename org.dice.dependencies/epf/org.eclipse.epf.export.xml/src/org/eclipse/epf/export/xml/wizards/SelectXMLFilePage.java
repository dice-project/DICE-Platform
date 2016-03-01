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
package org.eclipse.epf.export.xml.wizards;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.epf.export.xml.ExportXMLPlugin;
import org.eclipse.epf.export.xml.ExportXMLResources;
import org.eclipse.epf.export.xml.preferences.ExportXMLPreferences;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to specify a XML file to store the
 * exported method library content.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectXMLFilePage extends WizardPage {

	public static final String PAGE_NAME = SelectXMLFilePage.class.getName();

	private Text pathText;

	private String path;

	/**
	 * Creates a new instance.
	 */
	public SelectXMLFilePage() {
		super(PAGE_NAME);
		setTitle(ExportXMLResources.selectXMLFilePage_title);
		setDescription(ExportXMLResources.selectXMLFilePage_desc);
		setImageDescriptor(ExportXMLPlugin.getDefault().getImageDescriptor(
				"full/wizban/ExportXML.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label fileLabel = new Label(composite, SWT.NONE);
		fileLabel.setText(ExportXMLResources.fileLabel_text);

		pathText = new Text(composite, SWT.BORDER);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pathText.setText(ExportXMLPreferences.getXMLFile());
		pathText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (isValidPath(pathText.getText().trim())) {
					setPageComplete(true);
					setErrorMessage(null);
				} else {
					setPageComplete(false);
					setErrorMessage(ExportXMLResources.invalidXMLFile_error);
				}
			}
		});

		Button browseButton = new Button(composite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(GridData.END));
		browseButton.setText(ExportXMLResources.browseButton_text);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.SAVE);
				fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String path = fd.open();
				boolean ok = false;
				if (path != null) {
					pathText.setText(path);
					ok = isValidPath(path);
				}
				setPageComplete(ok);
				getWizard().getContainer().updateButtons();
			}
		});

		setControl(composite);

		setPageComplete(isValidPath(pathText.getText().trim()));
	}

	/**
	 * Checks whether the user specific path is valid.
	 * 
	 * @param path
	 *            the user specific path
	 * @return <code>true</code> if the user specified path is valid.
	 */
	private boolean isValidPath(String path) {
		IPath ecPath = Path.fromOSString(path);
		if (ecPath.isValidPath(path) && path.endsWith(".xml")) { //$NON-NLS-1$
			this.path = path;
			return true;
		}
		return false;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public IWizardPage getNextPage() {
		return null; // no more page
	}

	/**
	 * Gets the user specified XML file.
	 * 
	 * @return an absolute path to the XML file
	 */
	public String getPath() {
		return this.path;
	}

}
