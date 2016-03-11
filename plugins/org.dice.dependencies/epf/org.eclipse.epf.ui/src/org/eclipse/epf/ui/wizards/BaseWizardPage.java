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
package org.eclipse.epf.ui.wizards;

import org.eclipse.epf.ui.util.SWTUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The abstract base class for all EPF wizard pages.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @since 1.0
 */
public abstract class BaseWizardPage extends WizardPage {

	/**
	 * Creates a new instance.
	 * 
	 * @param pageName
	 *            the wizard page name
	 */
	protected BaseWizardPage(String pageName) {
		super(pageName);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            the wizard page name
	 * @param title
	 *            the wizard page title
	 * @param description
	 *            the wizard page description
	 * @param titleImage
	 *            the wizard page title image
	 */
	public BaseWizardPage(String pageName, String title, String description,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setDescription(description);
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		onEnterPage(null);
	}
	
	/**
	 * Called when entering this wizard page.
	 * 
	 * @param obj
	 *            a data initialization object
	 */
	public void onEnterPage(Object obj) {
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		IWizardPage nextPage = super.getNextPage();
		if (nextPage != null && nextPage instanceof BaseWizardPage) {
			try {
				((BaseWizardPage) nextPage).onEnterPage(getNextPageData());
			} catch (Exception e) {
			}
		}
		return nextPage;
	}

	/**
	 * Gets the data initialization object for the next wizard page.
	 * 
	 * @return an <code>Object</code>
	 */
	public Object getNextPageData() {
		return null;
	}

	/**
	 * Creates a composite with a grid layout.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the composite will take up
	 * @return a <code>Composite</code> object
	 */
	protected static Composite createGridLayoutComposite(Composite parent,
			int column) {
		return SWTUtil.createGridLayoutComposite(parent, column);
	}

	/**
	 * Creates a child composite with a grid layout.
	 * <p>
	 * The child composite will be indented from the parent composite.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the composite will take up
	 * @return a <code>Composite</code> object
	 */
	protected static Composite createChildGridLayoutComposite(Composite parent,
			int column) {
		return SWTUtil.createChildGridLayoutComposite(parent, column);
	}

	/**
	 * Creates a group with a grid layout.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the group
	 * @param column
	 *            the number of column cells that the group will take up
	 * @return a <code>Group</code> object
	 */
	protected static Group createGridLayoutGroup(Composite parent, String text,
			int column) {
		return SWTUtil.createGridLayoutGroup(parent, text, column);
	}

	/**
	 * Creates a group with a grid layout.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the group
	 * @param column
	 *            the number of column cells that the group will take up
	 * @param equalColumnWidth
	 *            flag to indicate whether column cells should be same width or
	 *            not
	 * @return a <code>Group</code> object
	 */
	protected static Group createGridLayoutGroup(Composite parent, String text,
			int column, boolean equalColumnWidth) {
		return SWTUtil.createGridLayoutGroup(parent, text, column,
				equalColumnWidth);
	}

	/**
	 * Creates a label.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the label
	 * @param column
	 *            the number of column cells that the label will take up
	 * @return a <code>Label</code> object
	 */
	protected static Label createLabel(Composite parent, String text, int column) {
		return SWTUtil.createLabel(parent, text, column);
	}

	/**
	 * Creates a label that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the label
	 * @return a <code>Label</code> object
	 */
	protected static Label createLabel(Composite parent, String text) {
		return SWTUtil.createLabel(parent, text);
	}

	/**
	 * Creates a vertically aligned label that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the label
	 * @return a <code>Label</code> object
	 */
	protected static Label createVerticallyAlignedLabel(Composite parent,
			String text) {
		return SWTUtil.createVerticallyAlignedLabel(parent, text);
	}

	/**
	 * Creates an editable text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	protected static Text createEditableText(Composite parent,
			String defaultText, int widthHint, int heightHint, int column) {
		return SWTUtil.createEditableText(parent, defaultText, widthHint,
				heightHint, column);
	}

	/**
	 * Creates an editable text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	public static Text createEditableText(Composite parent, int widthHint,
			int heightHint, int column) {
		return SWTUtil
				.createEditableText(parent, widthHint, heightHint, column);
	}

	/**
	 * Creates a readonly text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	protected static Text createText(Composite parent, String defaultText,
			int widthHint, int column) {
		return SWTUtil.createText(parent, defaultText, widthHint, column);
	}

	/**
	 * Creates an readonly text control that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @return a <code>Text</code> object
	 */
	protected static Text createText(Composite parent, String defaultText) {
		return SWTUtil.createText(parent, defaultText);
	}

	/**
	 * Creates an editable text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	protected static Text createEditableText(Composite parent,
			String defaultText, int widthHint, int column) {
		return SWTUtil.createEditableText(parent, defaultText, widthHint,
				column);
	}

	/**
	 * Creates an editable text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	protected static Text createEditableText(Composite parent,
			String defaultText, int widthHint) {
		return SWTUtil.createEditableText(parent, defaultText, widthHint);
	}

	/**
	 * Creates an editable text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	protected static Text createEditableText(Composite parent, int column) {
		return SWTUtil.createEditableText(parent, column);
	}

	/**
	 * Creates an editable text control that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @return a <code>Text</code> object
	 */
	protected static Text createEditableText(Composite parent,
			String defaultText) {
		return SWTUtil.createEditableText(parent, defaultText);
	}

	/**
	 * Creates an empty editable text control that only takes up one column
	 * cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @return a <code>Text</code> object
	 */
	protected static Text createEditableText(Composite parent) {
		return SWTUtil.createEditableText(parent);
	}

	/**
	 * Creates a readonly multi-line text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Text</code> object
	 */
	protected static Text createMultiLineText(Composite parent,
			String defaultText, int widthHint, int heightHint, int column) {
		return SWTUtil.createMultiLineText(parent, defaultText, widthHint,
				heightHint, column);
	}

	/**
	 * Creates an combobox.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Combo</code> object
	 */
	protected static Combo createCombobox(Composite parent, int column) {
		return SWTUtil.createCombobox(parent, column);
	}
	
	/**
	 * Creates an combobox with readonly style.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Combo</code> object
	 */
	protected static Combo createReadOnlyCombobox(Composite parent, int column) {
		return SWTUtil.createReadOnlyCombobox(parent, column);
	}

	/**
	 * Creates an combobox that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return a <code>Combo</code> object
	 */
	protected static Combo createCombobox(Composite parent) {
		return SWTUtil.createCombobox(parent);
	}

	/**
	 * Creates a button.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the control
	 * @return a <code>Button</code> object
	 */
	protected static Button createButton(Composite parent, String text) {
		return SWTUtil.createButton(parent, text);
	}

	/**
	 * Creates a checkbox.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>Button</code> object
	 */
	protected static Button createCheckbox(Composite parent, String text,
			int column) {
		return SWTUtil.createCheckbox(parent, text, column);
	}

	/**
	 * Creates a checkbox that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the control
	 * @return a <code>Button</code> object
	 */
	protected static Button createCheckbox(Composite parent, String text) {
		return SWTUtil.createCheckbox(parent, text);
	}

	/**
	 * Creates an radio button.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @param selected
	 *            if <code>true</code>, select the radio button by default
	 * @return a <code>Button</code> object
	 */
	protected static Button createRadioButton(Composite parent, String text,
			int column, boolean selected) {
		return SWTUtil.createRadioButton(parent, text, column, selected);
	}

	/**
	 * Creates an radio button that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param text
	 *            the text for the control
	 * @return a <code>Button</code> object
	 */
	protected static Button createRadioButton(Composite parent, String text) {
		return SWTUtil.createRadioButton(parent, text);
	}

	/**
	 * Creates a readonly text viewer.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>TextViewer</code> object
	 */
	protected static TextViewer createTextViewer(Composite parent,
			int widthHint, int heightHint, int column) {
		return SWTUtil.createTextViewer(parent, widthHint, heightHint, column);
	}

	/**
	 * Creates a readonly text viewer that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return a <code>TextViewer</code> object
	 */
	protected static TextViewer createTextViewer(Composite parent) {
		return SWTUtil.createTextViewer(parent);
	}

	/**
	 * Creates a table viewer.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>TableViewer</code> object
	 */
	protected static TableViewer createTableViewer(Composite parent,
			int widthHint, int heightHint, int column) {
		return SWTUtil.createTableViewer(parent, widthHint, heightHint, column);
	}

	/**
	 * Creates a checkbox table viewer.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>CheckboxTableViewer</code> object
	 */
	protected static CheckboxTableViewer createCheckboxTableViewer(
			Composite parent, int widthHint, int heightHint, int column) {
		return SWTUtil.createCheckboxTableViewer(parent, widthHint, heightHint,
				column);
	}

	/**
	 * Creates a checkbox table viewer.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>CheckboxTableViewer</code> object
	 */
	protected static CheckboxTableViewer createCheckboxTableViewer(
			Composite parent, int column) {
		return SWTUtil.createCheckboxTableViewer(parent, 360, 175, column);
	}

	/**
	 * Creates a line.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param column
	 *            the number of column cells that the line will take up
	 * @return a <code>Label</code> object
	 */
	protected static Label createLine(Composite parent, int column) {
		return SWTUtil.createLine(parent, column);
	}

	/**
	 * Checks whether a text control has some text.
	 * 
	 * @param control
	 *            the text control
	 * @return <code>true</code> if the text control has some text,
	 *         <code>false</code> otherwise
	 */
	protected static boolean isNonEmptyText(Text control) {
		return SWTUtil.isNonEmptyText(control);
	}

}
