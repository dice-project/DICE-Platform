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
package org.eclipse.epf.export.wizards;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.ConfigurationExportData;
import org.eclipse.epf.library.IConfigurationClosure;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.configuration.closure.ElementError;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * A wizard page that displays the result of a configuration integrity check.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportConfigCheckingPage extends BaseWizardPage {

	public static final String PAGE_NAME = ExportConfigCheckingPage.class
			.getName();

	private TextViewer textViewer;

	private TextPresentation style;

	private ConfigurationExportData data;

	private IConfigurationClosure closure = null;

	/**
	 * Creates a new instance.
	 */
	public ExportConfigCheckingPage(ConfigurationExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.checkConfigPage_title);
		setDescription(ExportResources.checkConfigPage_desc);
		setImageDescriptor(ExportPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_lib_conf_wizban.gif")); //$NON-NLS-1$
		this.data = data;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		textViewer = createTextViewer(container, 360, 160, 1);
		Document doc = new Document(" "); //$NON-NLS-1$
		textViewer.setDocument(doc);

		setControl(container);
		setPageComplete(true);
	}

	private void displaySummary() {
		style = new TextPresentation();
		Document doc = getSummaryText();
		textViewer.setDocument(doc);
		textViewer.changeTextPresentation(style, true);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		displaySummary();
	}

	/**
	 * Returns a document of summary text.
	 */
	public Document getSummaryText() {
		final StringBuffer textBuf = new StringBuffer();

		if (data.selectedConfigs.size() == 0) {
			textBuf.append(ExportResources.ExportConfigCheckingPage_summary_2);
		} else {
			final MethodConfiguration config = (MethodConfiguration) data.selectedConfigs
					.get(0);
			if ((closure == null) || (closure.getConfiguration() != config)) {
				Runnable runnable = new Runnable() {
					public void run() {
						// Check the configuration closure.
						closure = new ConfigurationClosure(null, config);
					}
				};

				UserInteractionHelper.runWithProgress(runnable,
						ExportResources.ExportConfigCheckingPage_checking);
			}
		}

		List<ElementError> errors = closure.getAllErrors();
		if (errors.size() == 0) {
			textBuf.append(ExportResources.ExportConfigCheckingPage_summary_3);
		} else {
			int eSz = 0;
			int wSz = 0;
			int iSz = 0;
			for (ElementError error: errors) {
				if (error.getSeverity() == IMarker.SEVERITY_ERROR) {
					eSz++;
				} else if (error.getSeverity() == IMarker.SEVERITY_WARNING) {
					wSz++;
				} else {
					iSz++;
				}
			}
			textBuf.append(ExportResources.bind(
					ExportResources.ExportConfigCheckingPage_summary_4,
					(new Object[] { 
							Integer.toString(errors.size()),
							Integer.toString(eSz), 
							Integer.toString(wSz), 
							Integer.toString(iSz), 
							})));
		}

		Document doc = new Document(textBuf.toString());
		return doc;
	}

}
