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
package org.eclipse.epf.authoring.ui.wizards;

import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;


/**
 * A wizard page that lists the currently open editors and prompts the user to
 * save them before performing an import or export operation.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SaveAllEditorsPage extends BaseWizardPage {

	private static final String SAVE_AND_CLOSE_PAGE_TITLE = AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_saveAndClosePageTitle;

	private static final String SAVE_AND_CLOSE_PAGE_DESC = AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_saveAndClosePageDescription;

	private static final String SAVE_PAGE_TITLE = AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_saveTitle;

	private static final String SAVE_PAGE_DESC = AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_saveDescription;

	private boolean doClose;

	private CheckboxTableViewer ctrl_chkboxTableViewer;

	private Button selectAll;

	private Button deselectAll;

	/**
	 * Adds this wizard page to a wizard only when necessary.
	 * This page will be added iff there are dirty method editors.
	 * if doClose is true and there are no dirty editors, this method will
	 * close all method editors.
	 * 
	 * @param wizard
	 *            The wizard to which this page might be added.
	 * @param doClose
	 *            If <code>true</code>, this page will close editors when
	 *            next is clicked. 
	 * @return <code>true</code> if the page will be added to the wizard.
	 */
	public static boolean addPageIfNeeded(Wizard wizard, boolean doClose) {
		if (isUnsavedEditor()) {
			wizard.addPage(new SaveAllEditorsPage(doClose));
			return true;
		} else {
			performClose(doClose);
			return false;
		}
	}

	/**
	 * Adds this wizard page to a wizard only when necessary.
	 * This page will be added iff there are dirty method editors.
	 * if doClose is true and there are no dirty editors, this method will
	 * close all method editors.
	 * 
	 * @param wizard
	 *            The wizard which this page might be added.
	 * @param doClose
	 *            If <code>true</code>, this page will close editors when
	 *            next is clicked. 
	 * @return <code>true</code> if the page will be added to the wizard.
	 */
	public static boolean addPageIfNeeded(Wizard wizard, boolean doClose,
			String title, String description, ImageDescriptor image) {
		if (isUnsavedEditor()) {
			SaveAllEditorsPage page = new SaveAllEditorsPage(doClose);
			if (title != null) {
				page.setTitle(title);
			}
			if (description != null) {
				page.setDescription(description);
			}
			if (image != null) {
				page.setImageDescriptor(image);
			}
			wizard.addPage(page);
			return true;
		} else {
			performClose(doClose);
			return false;
		}
	}

	/**
	 * Creates a new intance.
	 */
	public SaveAllEditorsPage(boolean close) {
		super(SAVE_PAGE_TITLE);
		this.doClose = close;
		if (doClose) {
			setTitle(SAVE_AND_CLOSE_PAGE_TITLE);
			setDescription(SAVE_AND_CLOSE_PAGE_DESC);
		} else {
			setTitle(SAVE_PAGE_TITLE);
			setDescription(SAVE_PAGE_DESC);
		}
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		ctrl_chkboxTableViewer = CheckboxTableViewer.newCheckList(container,
				SWT.BORDER | SWT.FILL | SWT.HORIZONTAL);
		ctrl_chkboxTableViewer.getTable().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		ctrl_chkboxTableViewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				IEditorReference ref = (IEditorReference) element;
				return ref.getEditor(false).getTitle();
			}

			public Image getImage(Object element) {
				IEditorReference ref = (IEditorReference) element;
				return ref.getEditor(false).getTitleImage();
			}
		});
		ctrl_chkboxTableViewer.setContentProvider(new ArrayContentProvider());
		ctrl_chkboxTableViewer.setInput(EditorChooser.getInstance().getOpenMethodEditors().toArray());
		ctrl_chkboxTableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof IEditorReference) {
					if (((IEditorReference)element).isDirty())
						return true;
				}
				return false;
			}
		});
		ctrl_chkboxTableViewer.setAllChecked(true);

		Composite buttonContainer = new Composite(container, SWT.NONE);
		buttonContainer.setLayout(new GridLayout(1, true));
		buttonContainer.setLayoutData(new GridData(SWT.DEFAULT, SWT.BEGINNING,
				false, true));

		selectAll = new Button(buttonContainer, SWT.PUSH);
		selectAll
				.setText(AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_SelectAllButtonLabel);
		selectAll.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false,
				true));

		deselectAll = new Button(buttonContainer, SWT.PUSH);
		deselectAll
				.setText(AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_DeselectAllButtonLabel);
		deselectAll.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false,
				true));

		addListeners();

		setControl(container);
		setPageComplete(true);
	}

	private void refresh () {
		if (ctrl_chkboxTableViewer != null) {
			ctrl_chkboxTableViewer.refresh();
			if (ctrl_chkboxTableViewer.getTable() != null) {
				if (ctrl_chkboxTableViewer.getTable().getItems() != null) {
					if (ctrl_chkboxTableViewer.getTable().getItems().length == 0) {
						if (ctrl_chkboxTableViewer.getControl() != null)
							ctrl_chkboxTableViewer.getControl().setEnabled(false);
						selectAll.setEnabled(false);
						deselectAll.setEnabled(false);
					}
				}
			}
		}
	}
	
	private void addListeners() {
		if (getWizard() != null) {
			IWizardContainer container = getWizard().getContainer();
			if (container instanceof WizardDialog) {
				((WizardDialog)container).addPageChangedListener(new IPageChangedListener() {
					public void pageChanged(PageChangedEvent event) {
						refresh();
					}
				});
			}
		}
		selectAll.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		deselectAll.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(false);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});
	}
	
	public boolean canFlipToNextPage() {
		return true;
	}

	/**
	 * Perform our save (and possibly close) operation
	 */
	public IWizardPage getNextPage() {
		performSaveAndClose();
		return super.getNextPage();
	}
	
	protected void performSaveAndClose() {
		Object[] checkedArray = ctrl_chkboxTableViewer.getCheckedElements();
		for (int i = 0; i < checkedArray.length; i++) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().saveEditor(
							((IEditorReference) checkedArray[i])
									.getEditor(false), false);
		}
		performClose(doClose);
	}
	
	protected static void performClose(boolean doClose) {
		if (doClose) {
			EditorChooser.getInstance().closeAllMethodEditors();
		}
	}

	/**
	 * Checked whether there is any unsaved editor open
	 * 
	 * @param doClose
	 * @return true if there is an unsaved editor open
	 * 	true if doClose is true and there is an open editor
	 *  false otherwise
	 */
	private static boolean isUnsavedEditor() {
		boolean anEditorIsDirty = false;
		List<IEditorReference> methodEditors = EditorChooser.getInstance().getOpenMethodEditors();
		for (Iterator<IEditorReference> iterator = methodEditors.iterator(); iterator.hasNext();) {
			IEditorReference editor = iterator.next();
			if (editor.isDirty()) {
				anEditorIsDirty = true;
				break;
			}
			
		}
		return anEditorIsDirty;
	}
	
	private static boolean isOpenEditor(boolean doClose) {
		List<IEditorReference> methodEditors = EditorChooser.getInstance().getOpenMethodEditors();
		return doClose && methodEditors.size() > 0;
	}
	
	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return true;
	}
}
