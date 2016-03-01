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

import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.PluginExportData;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * A wizard page that displays a summary of the selected method plug-ins that
 * will be exported.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportPluginSummaryPage extends BaseWizardPage {

	public static final String PAGE_NAME = ExportPluginSummaryPage.class
			.getName();

	private TextViewer textViewer;

	private TextPresentation style;

	private PluginExportData data;

	/**
	 * Creates a new instance.
	 */
	public ExportPluginSummaryPage(PluginExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.exportPluginSummaryPage_title);
		setDescription(ExportResources.exportPluginSummaryPage_desc);
		setImageDescriptor(ExportPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_meth_plugin_wizban.gif")); //$NON-NLS-1$		
		this.data = data;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		textViewer = createTextViewer(container, 360, 160, 1);
		Document doc = new Document(""); //$NON-NLS-1$		
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
	 * Returns a document of the summary text.
	 */
	public Document getSummaryText() {
		StringBuffer textBuf = new StringBuffer();

		if (data.selectedPlugins == null || data.selectedPlugins.isEmpty())
			return new Document(
					ExportResources.exportPluginSummaryPage_noPlugin_text);

		int start = 0;
		int offset = 0;
		for (MethodPlugin plugin : data.selectedPlugins) {
			start = textBuf.length();
			offset = plugin.getName().length();
			style.addStyleRange(new StyleRange(start, offset, null, null,
					SWT.BOLD));
			textBuf.append(plugin.getName()).append("\n"); //$NON-NLS-1$

			start = textBuf.length();
			offset = AuthoringUIText.AUTHORS_TEXT.length();
			style.addStyleRange(new StyleRange(start, offset, null, null,
					SWT.BOLD | SWT.ITALIC));
			textBuf
					.append("\t").append(AuthoringUIText.AUTHORS_TEXT).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
			textBuf.append("\t\t").append(plugin.getAuthors()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

			start = textBuf.length();
			offset = AuthoringUIText.VERSION_TEXT.length();
			style.addStyleRange(new StyleRange(start, offset, null, null,
					SWT.BOLD | SWT.ITALIC));
			textBuf
					.append("\t").append(AuthoringUIText.VERSION_TEXT).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
			textBuf.append("\t\t").append(plugin.getVersion()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

			start = textBuf.length();
			offset = AuthoringUIText.BRIEF_DESCRIPTION_TEXT.length();
			style.addStyleRange(new StyleRange(start, offset, null, null,
					SWT.BOLD | SWT.ITALIC));
			textBuf
					.append("\t").append(AuthoringUIText.BRIEF_DESCRIPTION_TEXT).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
			textBuf
					.append("\t\t").append(plugin.getBriefDescription()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$

			start = textBuf.length();
			offset = ExportResources.reviewPluginsPage_dependentPluginsLabel_text
					.length(); 
			style.addStyleRange(new StyleRange(start, offset, null, null,
					SWT.BOLD | SWT.ITALIC));
			textBuf
					.append("\t") //$NON-NLS-1$
					.append(
							ExportResources.reviewPluginsPage_dependentPluginsLabel_text)
					.append("\n"); //$NON-NLS-1$
			List dependentPluginList = plugin.getBases();
			boolean addLinefeed = true;
			for (Iterator iter = dependentPluginList.iterator(); iter.hasNext();) {
				MethodPlugin element = (MethodPlugin) iter.next();
				textBuf.append("\t\t").append(element.getName()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
				addLinefeed = false;
			}
			if (addLinefeed)
				textBuf.append("\n"); //$NON-NLS-1$

			start = textBuf.length();
			offset = ExportResources.reviewPluginsPage_associatedConfigsLabel_text
					.length();
			style.addStyleRange(new StyleRange(start, offset, null, null,
					SWT.BOLD | SWT.ITALIC));
			textBuf
					.append("\t") //$NON-NLS-1$
					.append(
							ExportResources.reviewPluginsPage_associatedConfigsLabel_text)
					.append("\n"); //$NON-NLS-1$
			List associatedConfigList = (List) data.associatedConfigMap
					.get(plugin);
			for (Iterator iter = associatedConfigList.iterator(); iter
					.hasNext();) {
				MethodConfiguration element = (MethodConfiguration) iter.next();
				textBuf.append("\t\t").append(element.getName()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			textBuf.append("\n"); //$NON-NLS-1$
		}

		Document doc = new Document(textBuf.toString());
		return doc;
	}

}
