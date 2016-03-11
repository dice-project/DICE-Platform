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
package org.eclipse.epf.authoring.ui.dialogs;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.AddLinkFilter;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.dialogs.AddLinkDialog;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Prompts the user to select a link type that will be used to create a HTML anchor
 * tag in the Rich Text Editor.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodAddLinkDialog extends AddLinkDialog {

	private static final int LINK_URL = 0;

	private static final int LINK_FILE = LINK_URL + 1;

	// private static final int LINK_ELEMENT_LINK = LINK_URL + 2;

	private static final int LINK_ELEMENT_LINK_WITH_TYPE = LINK_URL + 3;

	private static final int LINK_ELEMENT_LINK_WITH_USER_TEXT = LINK_URL + 4;

	private static final String[] ELEMENT_TYPE_LABELS = {
			LibraryUIResources.linkToURL_name,
			LibraryUIResources.linkToFile_name,
			LibraryUIResources.elementLink_name,
			LibraryUIResources.elementLinkWithType_name,
			LibraryUIResources.elementLinkWithUserText_name };

	public static final String OPEN_LINK_IN_NEW_WINDOW_ATTRIBUTE = "target=\"_blank\""; //$NON-NLS-1$

	private IMethodRichText richText;

	private MethodElement methodElement;
	
	private String elementLoc;

	private Combo linkTypeCombo;

	private Button browseButton;

	private Button openLinkCheckbox;

	private int linkType;

	private boolean openLinkInNewWindow = true;

	private File fileToCopy;
	
	private ModifyListener modifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent event) {
			if (okButton != null) {
				try {
					if (linkType == LINK_FILE && event.widget == urlText) {
						File urlFile = new File(urlText.getText().trim());
						if (urlFile.isAbsolute()) {
							fileToCopy = urlFile;
						} else {
							fileToCopy = new File(elementLoc, urlText.getText().trim());
						}
						okButton.setEnabled(fileToCopy.isFile() && fileToCopy.canRead());
					} else {
						okButton.setEnabled(urlText.getText().trim().length() > 0);
					}
				} catch (Exception e) {
					okButton.setEnabled(false);
				}
			}
		}
	};


	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            The parent shell.
	 * @param richText
	 *            A rich text control.
	 */
	public MethodAddLinkDialog(Shell parent, IMethodRichText richText) {
		super(parent, richText.getBasePath());
		this.richText = richText;
		this.methodElement = richText.getMethodElement();
		elementLoc = ResourceHelper.getFolderAbsolutePath(methodElement);
	}

	/**
	 * @see org.eclipse.epf.richtext.dialogs.BaseDialog#createDialogArea(Composite
	 *      parent)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.numColumns = 3;

		Label linkTypeLabel = new Label(composite, SWT.NONE);
		linkTypeLabel.setText(AuthoringUIResources.addLinkDialog_linkType);
		linkTypeLabel.moveAbove(urlLabel);
		
		urlText.removeModifyListener(urlTextModifyListener);
		urlText.addModifyListener(modifyListener);


		linkTypeCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		GridData comboGridData = new GridData(GridData.FILL_HORIZONTAL);
		comboGridData.horizontalSpan = 2;
		linkTypeCombo.setLayoutData(comboGridData);
		linkTypeCombo.setItems(ELEMENT_TYPE_LABELS);
		linkTypeCombo.setText(ELEMENT_TYPE_LABELS[0]);
		linkTypeCombo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				linkType = linkTypeCombo.getSelectionIndex();
				browseButton.setEnabled(linkType != LINK_URL);
				urlDisplayNameLabel.setEnabled(linkType == LINK_URL);
				urlDisplayNameText.setEnabled(linkType == LINK_URL);
				if (linkType == LINK_URL) {
					urlDisplayNameText
							.setText(richText.getSelected().getText());
				} else {
					urlDisplayNameText.setText(""); //$NON-NLS-1$
				}
				openLinkCheckbox.setEnabled(linkType == LINK_URL
						|| linkType == LINK_FILE);
				urlText.setText(""); //$NON-NLS-1$
				if (linkType != LINK_FILE) {
					fileToCopy = null;
				}
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
		linkTypeCombo.moveAbove(urlLabel);

		browseButton = new Button(composite, SWT.PUSH);
		browseButton.setLayoutData(new GridData());
		browseButton.setText(AuthoringUIText.BROWSE_BUTTON_TEXT);
		browseButton.setEnabled(false);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (linkType == LINK_FILE) {
					FileDialog dialog = new FileDialog(Display.getCurrent()
							.getActiveShell(), SWT.OPEN);
					dialog.setFilterPath(basePath);
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
				// MethodElement link
				else {
					IFilter filter = new AddLinkFilter() {
						protected boolean childAccept(Object obj) {
							if (obj instanceof MethodPlugin)
								return true;
							if (obj instanceof MethodConfiguration)
								return false;
							if(obj instanceof Milestone) return false;
							return true;
						}
					};
					ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), filter, methodElement,
							FilterConstants.ALL_ELEMENTS);
					fd.setBlockOnOpen(true);
					fd.setTitle(FilterConstants.ALL_ELEMENTS);
					fd.open();
					ArrayList sel = fd.getSelectedItems();
					if (sel.size() > 0) {
						MethodElement selectedElement = (MethodElement) sel
								.get(0);
						String url = null;
						String href = ResourceHelper.getUrl(selectedElement,
								methodElement, "html"); //$NON-NLS-1$
						if (linkType == LINK_ELEMENT_LINK_WITH_USER_TEXT) {
							String linkName = ((RichTextEditor) richText)
									.getSelected().getText();
							if (linkName.trim().length() == 0)
								linkName = selectedElement.getName();
							url = ResourceHelper
									.getElementLink(selectedElement, linkName,
											"file://" + href); //$NON-NLS-1$
						} else {
							boolean withType = (linkType == LINK_ELEMENT_LINK_WITH_TYPE) ? true
									: false;
							url = ResourceHelper
									.getElementLink(selectedElement, withType,
											"file://" + href); //$NON-NLS-1$
						}
						urlText.setText(url);
					}
				}
			}
		});
		browseButton.moveAbove(urlDisplayNameLabel);

		openLinkCheckbox = new Button(composite, SWT.CHECK);
		GridData checkBoxGridData = new GridData();
		checkBoxGridData.horizontalSpan = 2;
		openLinkCheckbox.setLayoutData(checkBoxGridData);
		openLinkCheckbox.setText(AuthoringUIResources.openLinkCheckbox_text);
		openLinkCheckbox.setSelection(true);
		openLinkCheckbox.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				openLinkInNewWindow = openLinkCheckbox.getSelection();
			}
		});

		urlDisplayNameText.setText(richText.getSelected().getText());

		return composite;
	}

	/**
	 * Returns the file to be copied to a resource folder.
	 */
	public File getFileToCopy() {
		return fileToCopy;
	}

	/**
	 * Returns <code>true</code> if the link should be opened in a new browser
	 * window.
	 */
	public boolean getOpenLinkInNewWindow() {
		return openLinkInNewWindow;
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		String url = urlText.getText();
		if (url != null && url.length() > 0) {
			if (linkType == LINK_URL) {
				String urlDisplayName = urlDisplayNameText.getText();
				if (urlDisplayName.trim().length() == 0) {
					urlDisplayName = url;
				}
				String fullURL = "<a href=\"" + //$NON-NLS-1$
						url
						+ "\"" + //$NON-NLS-1$
						(openLinkInNewWindow ? " " + OPEN_LINK_IN_NEW_WINDOW_ATTRIBUTE : "") + //$NON-NLS-1$ //$NON-NLS-2$
						">" + urlDisplayName + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$
				link.setURL(fullURL);
			} else {
				link.setURL(url);
			}
		}

		// Set widget text to blank so super.okPressed doesn't overwrite the
		// above.
		urlText.removeModifyListener(modifyListener);
		urlText.setText(""); //$NON-NLS-1$
		super.okPressed();
	}

}
