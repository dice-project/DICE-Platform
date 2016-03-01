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
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.views.ElementHTMLViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * The Preview page in the Method Editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class PreviewPage {

	private ElementHTMLViewer previewer = null;

	/**
	 * Create an instance
	 * 
	 * @param composite
	 */
	public PreviewPage(Composite composite) {
		// Use the ViewForm control to give it the Eclipse Workbench window
		// look & feel.
		ViewForm viewForm = new ViewForm(composite, SWT.DEFAULT);
		viewForm.marginHeight = 0;
		viewForm.marginWidth = 0;
		viewForm.setLayout(new GridLayout());
		viewForm.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Set toolbar
		Control toolbar = createToolbar(viewForm);
		viewForm.setTopRight(toolbar);

		// Set content
		Control content = createContent(viewForm);
		viewForm.setContent(content);
	}

	/**
	 * Create preview content
	 * 
	 * @param parent
	 * @return
	 */
	private Control createContent(ViewForm parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		content.setLayout(layout);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		content.setLayoutData(gridData);

		previewer = new ElementHTMLViewer(content);
		return (Control) content;
	}

	/**
	 * Return ElementViewer
	 * 
	 * @return
	 * 			Viewer
	 */
	public ElementHTMLViewer getPreviewViewer() {
		return previewer;
	}

	/**
	 * Create Toolbar It has Go back, Go forward, Stop and refresh buttons
	 * 
	 * @param ViewForm
	 *            parent
	 * @return Toolbar
	 */
	private ToolBar createToolbar(ViewForm parent) {
		// Create the browser navigation toolbar.
		ToolBar toolbar = new ToolBar(parent, SWT.FLAT);

		// Add the Back button.
		ToolItem backButton = new ToolItem(toolbar, SWT.NONE);
		backButton.setImage(AuthoringUIImages.IMG_BACK);
		backButton.setToolTipText(AuthoringUIResources.backButton_text); 
		backButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Browser browser = previewer.getBrowser();
				if (browser != null) {
					browser.back();
				}
			};
		});

		// Add the Foward button.
		ToolItem forwardButton = new ToolItem(toolbar, SWT.NONE);
		forwardButton.setImage(AuthoringUIImages.IMG_FORWARD);
		forwardButton.setToolTipText(AuthoringUIResources.forwardButton_text); 
		forwardButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Browser browser = previewer.getBrowser();
				if (browser != null) {
					browser.forward();
				}
			};
		});

		// Add the Stop button.
		ToolItem stopButton = new ToolItem(toolbar, SWT.NONE);
		stopButton.setImage(AuthoringUIImages.IMG_STOP);
		stopButton.setToolTipText(AuthoringUIResources.stopButton_text); 
		stopButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Browser browser = previewer.getBrowser();
				if (browser != null) {
					browser.stop();
				}
			};
		});

		// Add the Refresh button.
		ToolItem refreshButton = new ToolItem(toolbar, SWT.NONE);
		refreshButton.setImage(AuthoringUIImages.IMG_REFRESH);
		refreshButton.setToolTipText(AuthoringUIResources.refreshButton_text); 
		refreshButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// Browser browser = previewer.getBrowser();
				// if (browser != null) {
				// browser.refresh();
				// }

				previewer.refresh();
			};
		});

		// Add the Print button.
		ToolItem printButton = new ToolItem(toolbar, SWT.NONE);
		printButton.setImage(AuthoringUIImages.IMG_PRINT);
		printButton.setToolTipText(AuthoringUIResources.printButton_text); 
		printButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				previewer.print();
			};
		});

		return toolbar;
	}

}
