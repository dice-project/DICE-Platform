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
package org.eclipse.epf.authoring.ui.forms;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.richtext.dialogs.AddLinkDialog;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The Description page in a Guidance (with attachments) editor.
 * 
 * @author Lokanath Jagga
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @since 1.0
 */
public class GuidanceWithAttachmentsDescriptionPage extends GuidanceDescriptionPage {

	private Button ctrl_attach, ctrl_detach, ctrl_attach_url ; //ctrl_remove ;

	private List ctrl_attached_files;

	public static final String GUIDANCE_ATTACHMENT_LAST_DIRECTORY_BROWSED_PREF = "guidance_attachment_last_dir_browsed"; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public GuidanceWithAttachmentsDescriptionPage(FormEditor editor) {
		super(editor);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.GuidanceDescriptionPage#createEditorContent(org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);

		createLabel(toolkit, generalComposite,
				AuthoringUIText.ATTACHED_FILE_TEXT, 2);
		ctrl_attached_files = new List(generalComposite, SWT.SINGLE
				| SWT.V_SCROLL | SWT.H_SCROLL);
		GridData listGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING,
				true, true);
		listGridData.heightHint = 30;
		listGridData.widthHint = 300;
		ctrl_attached_files.setLayoutData(listGridData);

		ctrl_attached_files.setData(FormToolkit.KEY_DRAW_BORDER,
				FormToolkit.TEXT_BORDER);

		Composite buttonComposite = createComposite(toolkit, generalComposite,
				1);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.verticalSpan = 1;
		gridData.horizontalSpan = 3;
		buttonComposite.setLayoutData(gridData);
		buttonComposite.setLayout(new GridLayout(3, false));

		ctrl_attach = createButton(toolkit, buttonComposite, ATTACH_BUTTON);
		ctrl_attach_url = createButton(toolkit, buttonComposite, ATTACH_URL_BUTTON);
		ctrl_detach = createButton(toolkit, buttonComposite, DETACH_BUTTON);
		
		label_base.setText(AuthoringUIText.BASE_ELEMENT_TEXT);

	}

	/**
	 * Add listeners
	 * 
	 * @see org.eclipse.epf.authoring.ui.forms.GuidanceDescriptionPage#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();

		final IActionManager actionMgr = ((MethodElementEditor) getEditor())
				.getActionManager();

		ctrl_attach.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell());
				dialog
						.setText(AuthoringUIResources.attachGuidanceFileDialog_title); 
				String lastDir = AuthoringUIPlugin
						.getDefault()
						.getPreferenceStore()
						.getString(
								GUIDANCE_ATTACHMENT_LAST_DIRECTORY_BROWSED_PREF);
				if (lastDir != null && lastDir.trim().length() > 0) {
					dialog.setFilterPath(lastDir);
				} else {
					dialog.setFilterPath(ResourceHelper.getFolderAbsolutePath(guidance));
				}
				dialog.open();
				if (dialog.getFileName() != null && dialog.getFileName() != "") { //$NON-NLS-1$
					try {
						File fileToAttach = new File(dialog.getFilterPath(),
								dialog.getFileName());
						String fileUrl = LibraryUIUtil.getURLForAttachment(ctrl_attach.getShell(),
								fileToAttach, guidance, true);
						if (fileUrl == null) {
							// user hit cancel on RenameFileConflictDialog
							return;
						}
						if (ctrl_attached_files.indexOf(fileUrl) != -1) {
							// file is already an attachment
							AuthoringUIPlugin
									.getDefault()
									.getMsgDialog()
									.displayInfo(
											AuthoringUIResources.AttachmentDialogattachFile_text, 
											AuthoringUIResources.forms_GuidanceWithAttachmentsDescriptionPage_alreadyAttached_msg); 
							return;
						}
						ctrl_attached_files.add(fileUrl);
						boolean status = actionMgr.doAction(IActionManager.SET,
								guidance.getPresentation(),
								UmaPackage.eINSTANCE
										.getGuidanceDescription_Attachments(),
								getFilesFromListCtrl(), -1);
						if (!status) {
							return;
						} else {
							AuthoringUIPlugin
									.getDefault()
									.getPreferenceStore()
									.setValue(
											GUIDANCE_ATTACHMENT_LAST_DIRECTORY_BROWSED_PREF,
											dialog.getFilterPath());
						}
					} catch (IOException ex) {
						// TODO: Display an error dialog.
						ex.printStackTrace();
					}
				}
			}
		});

		ctrl_attach_url.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddLinkDialog dialog = new AddLinkDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), ""); //$NON-NLS-1$
				dialog.open();
				if (dialog.getReturnCode() == Window.OK) {
					
					if( dialog.getLink() != null) { 
						
						try {
							String linkUrl = dialog.getLink().getURL();
							String linkName = dialog.getLink().getName();
							if (linkName == null || linkName.trim().length() == 0) {
								linkName = FileUtil.getFileName(linkUrl);
							}
							
							try {
								URL url = new URL(linkUrl);
							} catch (MalformedURLException mue) {
								if (mue.getMessage().startsWith("no protocol")) { //$NON-NLS-1$
									linkUrl = NetUtil.HTTP_URI_PREFIX + linkUrl;
								}
							}
							
							String urlString = "<a href=\""; //$NON-NLS-1$
							if (Platform.getOS().equals(Platform.WS_WIN32)) {
								urlString += NetUtil
										.encodeFileURL(linkUrl);
							} else {
								urlString += NetUtil.encodeFileURL(linkUrl);
							}
							urlString += "\">" + linkName + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$
							linkUrl = urlString;
							//String linkDisplayName = dialog.getLink().getName();							
							
							if (linkUrl == null) {
								// user hit cancel on AddLinkDialog
								return;
							}
							if (ctrl_attached_files.indexOf(linkUrl) != -1) {
								// If link already exists
								AuthoringUIPlugin
										.getDefault()
										.getMsgDialog()
										.displayInfo(
												AuthoringUIResources.AttachmentDialogattachUrl_text, 
												AuthoringUIResources.forms_GuidanceWithUrlAttachmentsDescriptionPage_alreadyAttached_msg); 
								return;
							}
							ctrl_attached_files.add(linkUrl);
							boolean status = actionMgr.doAction(IActionManager.SET,
									guidance.getPresentation(),
									UmaPackage.eINSTANCE
											.getGuidanceDescription_Attachments(),
									getFilesFromListCtrl(), -1);
							if (!status) {
								return;
							}
						} catch (Exception ex) {
							// TODO: Display an error dialog.
							ex.printStackTrace();
						}
					}
				}
			}
		});
		
		ctrl_detach.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_attached_files.remove(ctrl_attached_files
						.getSelectionIndex());
				boolean status = actionMgr.doAction(IActionManager.SET,
						guidance.getPresentation(), UmaPackage.eINSTANCE
								.getGuidanceDescription_Attachments(),
						getFilesFromListCtrl(), -1);

				if (!status) {
					// ctrl_attached_files.setText(oldContent);
					return;
				}
			}
		});

		// ctrl_remove.addSelectionListener(new SelectionAdapter(){
		// public void widgetSelected(SelectionEvent e) {
		// //ctrl_attached_files.setText(""); //$NON-NLS-1$
		// String oldContent =
		// ((GuidanceDescription)guidance.getPresentation()).getAttachments();
		// ctrl_attached_files.setText(""); //$NON-NLS-1$
		// boolean status = actionMgr.doAction(IActionManager.SET,
		// guidance.getPresentation(),
		// UmaPackage.eINSTANCE.getGuidanceDescription_Attachments(),
		// ctrl_attached_files.getText(), -1);
		// if(!status){
		// //ctrl_attached_files.setText(oldContent);
		// return;
		// }
		// }
		// });
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.GuidanceDescriptionPage#refresh(boolean)
	 */
	protected void refresh(boolean editable) {
		super.refresh(editable);
		ctrl_attach.setEnabled(editable);
		ctrl_attach_url.setEnabled(editable);
		ctrl_detach.setEnabled(editable);
		
		// ctrl_remove.setEnabled(editable);
	}

	/**
	 * Load initial data from model
	 *
	 * @see org.eclipse.epf.authoring.ui.forms.GuidanceDescriptionPage#loadData()
	 */
	protected void loadData() {
		super.loadData();
		if (guidance != null) {
			ContentDescription content = (ContentDescription) guidance
					.getPresentation();
			String thirdpartyformat = ((org.eclipse.epf.uma.GuidanceDescription) content)
					.getAttachments();
			setFilesToListCtrl(thirdpartyformat);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.GuidanceDescriptionPage#getContentElement()
	 */
	protected Object getContentElement() {
		return contentElement;
	}

	/**
	 * Given a string of files, add the files to the List control
	 * 
	 * @param files
	 */
	private void setFilesToListCtrl(String fileString) {
		java.util.List attachmentList = TngUtil
				.convertGuidanceAttachmentsToList(fileString);
		ctrl_attached_files.removeAll();

		for (Iterator iter = attachmentList.iterator(); iter.hasNext();) {
			String attachment = (String) iter.next();
			ctrl_attached_files.add(attachment);

		}
	}

	/**
	 * returns the list of files as a string
	 * 
	 * @return
	 */
	private String getFilesFromListCtrl() {
		String files[] = ctrl_attached_files.getItems();
		return TngUtil.convertGuidanceAttachmentsToString(Arrays.asList(files));
	}

}
