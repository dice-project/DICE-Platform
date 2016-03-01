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
package org.eclipse.epf.authoring.ui.properties;

import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


/**
 * The general tab section for Descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class DescriptorGeneralSection extends BreakdownElementGeneralSection {
	protected Descriptor element;

	private Button synchronizedButton;

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#init()
	 */
	protected void init() {
		super.init();

		// get Descriptor object
		element = (Descriptor) getElement();

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#createGeneralSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGeneralSection(Composite composite) {
		super.createGeneralSection(composite);

		// create composite for checkbox
		checkBoxComposite = FormUI.createComposite(toolkit, generalComposite,
				GridData.FILL_HORIZONTAL, numOfColumns, true);

		synchronizedButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		synchronizedButton.setText(PropertiesResources.BreakdownElement_Option_Synchronized); 
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();

		synchronizedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				actionMgr.doAction(IActionManager.SET, element,
						UmaPackage.eINSTANCE
								.getDescriptor_IsSynchronizedWithSource(),
						Boolean.valueOf(synchronizedButton.getSelection()), -1);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();
		synchronizedButton.setEnabled(editable);
		if (ProcessUtil.isSynFree()) {
			synchronizedButton.setVisible(false);
			synchronizedButton.setEnabled(false);
		}
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof Descriptor) {
				super.refresh();

				element = (Descriptor) getElement();

				synchronizedButton.setSelection(element
						.getIsSynchronizedWithSource().booleanValue());
			}

		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing Descriptor general section: ", ex); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#getNamePrefix()
	 */
	public String getNamePrefix() {
		return LibraryUIText.TEXT_DESCRIPTOR + ": "; //$NON-NLS-1$
	}
}