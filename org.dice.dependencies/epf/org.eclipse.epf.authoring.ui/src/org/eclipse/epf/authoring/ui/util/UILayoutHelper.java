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
package org.eclipse.epf.authoring.ui.util;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * Layout Helper for UI. 
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class UILayoutHelper {

	private static Composite editorComposite, previewComposite;

	private static CTabFolder tabFolder;

	/**
	 * Create tabs in form 
	 * @param form
	 * @param toolkit
	 */
	public static void createTabs(ScrolledForm form, FormToolkit toolkit) {
		// create tabfolder
		tabFolder = new CTabFolder(form.getBody(), SWT.FLAT | SWT.TOP);
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		tabFolder.setLayoutData(td);

		// set tabfolder background color
		Color selectedColor = toolkit.getColors()
				.getColor(FormColors.SEPARATOR);
		tabFolder.setSelectionBackground(new Color[] { selectedColor,
				toolkit.getColors().getBackground() }, new int[] { 50 });

		// create editor tab item and the corresponding composite
		CTabItem editorItem = new CTabItem(tabFolder, SWT.NULL);
		editorItem.setText(AuthoringUIText.EDITOR_TEXT);
		editorComposite = createComposite(toolkit);
		editorItem.setControl(editorComposite);

		// create preview tab item and the corresponding composite
		CTabItem previewItem = new CTabItem(tabFolder, SWT.NULL);
		previewItem.setText(AuthoringUIText.PREVIEW_PAGE_TITLE);
		previewComposite = createComposite(toolkit);
		previewItem.setControl(previewComposite);

		toolkit.paintBordersFor(tabFolder);

		toolkit.adapt(tabFolder, true, true);

		tabFolder.setSelection(0);
	}

	/**
	 * Create composite on the form
	 * 
	 * @param toolkit
	 * @return
	 * 			UI composite
	 */
	private static Composite createComposite(FormToolkit toolkit) {
		Composite composite = toolkit.createComposite(tabFolder);
		composite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		composite.setLayout(new TableWrapLayout());

		return composite;
	}

	/**
	 * Return editor composite
	 * 
	 * @return
	 * 		UI Composite
	 */
	public static Composite getEditorComposite() {
		return editorComposite;
	}

	/**
	 * Return preview composite
	 * 
	 * @return
	 * 		Preview composite
	 */
	public static Composite getPreviewComposite() {
		return previewComposite;
	}

}
