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
package org.eclipse.epf.ui.util;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Helper class to create SWT widgets for the EPF UI.
 * <p>
 * This class helps to provide a common look and feel for all EPF forms,
 * dialogs, wizards and preference pages.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class SWTUtil {

	/**
	 * Prevents external instantiation of this class. All methods in this class
	 * should be declared static.
	 */
	private SWTUtil() {
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
	public static Composite createGridLayoutComposite(Composite parent,
			int column) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(column, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return composite;
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
	public static Composite createChildGridLayoutComposite(Composite parent,
			int column) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(column, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		((GridLayout) composite.getLayout()).marginTop = -3;
		((GridLayout) composite.getLayout()).marginLeft = 13;
		return composite;
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
	public static Group createGridLayoutGroup(Composite parent, String text,
			int column) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(column, false));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setText(text);
		return group;
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
	public static Group createGridLayoutGroup(Composite parent, String text,
			int column, boolean equalColumnWidth) {
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(column, equalColumnWidth));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setText(text);
		return group;
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
	public static Label createLabel(Composite parent, String text, int column) {
		Label label = new Label(parent, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		label.setLayoutData(gd);
		label.setText(text);
		return label;
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
	public static Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData());
		label.setText(text);
		return label;
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
	public static Label createVerticallyAlignedLabel(Composite parent,
			String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		label.setText(text);
		return label;
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
	public static Text createText(Composite parent, String defaultText,
			int widthHint, int column) {
		Text text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		text.setLayoutData(gd);
		return text;
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
	public static Text createText(Composite parent, String defaultText) {
		Text text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setText(defaultText);
		return text;
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
	public static Text createEditableText(Composite parent, String defaultText,
			int widthHint, int heightHint, int column) {
		Text text = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		text.setLayoutData(gd);
		return text;
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
		return createEditableText(parent, "", widthHint, heightHint, column); //$NON-NLS-1$
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
	public static Text createEditableText(Composite parent, String defaultText,
			int widthHint, int column) {
		Text text = new Text(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		text.setLayoutData(gd);
		return text;
	}

	/**
	 * Creates an editable text control that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param defaultText
	 *            the default text for the control
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @return a <code>Text</code> object
	 */
	public static Text createEditableText(Composite parent, String defaultText,
			int widthHint) {
		Text text = new Text(parent, SWT.BORDER);
		GridData gd = new GridData();
		gd.widthHint = widthHint;
		text.setLayoutData(gd);
		return text;
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
	public static Text createEditableText(Composite parent, String defaultText) {
		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setText(defaultText);
		return text;
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
	public static Text createEditableText(Composite parent, int column) {
		return createEditableText(parent, "", SWT.DEFAULT, column); //$NON-NLS-1$
	}

	/**
	 * Creates an empty editable text control that only takes up one column
	 * cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return a <code>Text</code> object
	 */
	public static Text createEditableText(Composite parent) {
		return createEditableText(parent, ""); //$NON-NLS-1$
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
	public static Text createMultiLineText(Composite parent,
			String defaultText, int widthHint, int heightHint, int column) {
		Text text = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		text.setLayoutData(gd);
		return text;
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
	public static Combo createCombobox(Composite parent, int column) {
		Combo combo = new Combo(parent, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		combo.setLayoutData(gd);
		return combo;
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
	public static Combo createReadOnlyCombobox(Composite parent, int column) {
		Combo combo = new Combo(parent, SWT.BORDER|SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		combo.setLayoutData(gd);
		return combo;
	}

	/**
	 * Creates an combobox that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return a <code>Combo</code> object
	 */
	public static Combo createCombobox(Composite parent) {
		return createCombobox(parent, 1);
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
	public static Button createButton(Composite parent, String text) {
		Button button = new Button(parent, SWT.NONE);
		button.setText(text);
		return button;
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
	public static Button createCheckbox(Composite parent, String text,
			int column) {
		Button button = new Button(parent, SWT.CHECK);
		button.setText(text);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		button.setLayoutData(gd);
		return button;
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
	public static Button createCheckbox(Composite parent, String text) {
		return createCheckbox(parent, text, 1);
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
	public static Button createRadioButton(Composite parent, String text,
			int column, boolean selected) {
		Button radioButton = new Button(parent, SWT.RADIO);
		radioButton.setText(text);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		radioButton.setLayoutData(gd);
		radioButton.setSelection(selected);
		return radioButton;
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
	public static Button createRadioButton(Composite parent, String text) {
		return createRadioButton(parent, text, 1, false);
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
	public static TextViewer createTextViewer(Composite parent, int widthHint,
			int heightHint, int column) {
		TextViewer textViewer = new TextViewer(parent, SWT.BORDER | SWT.MULTI
				| SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
		StyledText textWidget = textViewer.getTextWidget();
		GridData gd = new GridData(GridData.BEGINNING | GridData.FILL_BOTH);
		gd.horizontalSpan = column;
		gd.heightHint = heightHint;
		gd.widthHint = widthHint;
		textWidget.setLayoutData(gd);
		return textViewer;
	}

	/**
	 * Creates a readonly text viewer that only takes up one column cell.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return a <code>TextViewer</code> object
	 */
	public static TextViewer createTextViewer(Composite parent) {
		return createTextViewer(parent, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT);
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
	public static TableViewer createTableViewer(Composite parent,
			int widthHint, int heightHint, int column) {
		Table table = new Table(parent, SWT.V_SCROLL | SWT.BORDER);
		GridData gd = new GridData(GridData.BEGINNING | GridData.FILL_BOTH);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		table.setLayoutData(gd);
		TableViewer tableViewer = new TableViewer(table);
		return tableViewer;
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
	public static CheckboxTableViewer createCheckboxTableViewer(
			Composite parent, int widthHint, int heightHint, int column) {
		CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer
				.newCheckList(parent, SWT.BORDER | SWT.FILL | SWT.HORIZONTAL);
		Table table = checkboxTableViewer.getTable();
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		table.setLayoutData(gd);
		return checkboxTableViewer;
	}
	
	/**
	 * Creates a list viewer.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param widthHint
	 *            the preferred width (in pixels) for the control
	 * @param heightHint
	 *            the preferred height (in pixels) for the control
	 * @param column
	 *            the number of column cells that the control will take up
	 * @return a <code>ListViewer</code> object
	 */
	public static ListViewer createListViewer(Composite parent,
			int widthHint, int heightHint, int column) {
		ListViewer listViewer = new ListViewer(parent, SWT.V_SCROLL | SWT.BORDER);
		GridData gd = new GridData(GridData.BEGINNING | GridData.FILL_BOTH);
		gd.horizontalSpan = column;
		gd.widthHint = widthHint;
		gd.heightHint = heightHint;
		listViewer.getControl().setLayoutData(gd);
		return listViewer;
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
	public static Label createLine(Composite parent, int column) {
		Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BOLD);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = column;
		line.setLayoutData(gd);
		return line;
	}

	/**
	 * Checks whether a text control has some text.
	 * 
	 * @param control
	 *            the text control
	 * @return <code>true</code> if the text control has some text,
	 *         <code>false</code> otherwise
	 */
	public static boolean isNonEmptyText(Text control) {
		String text = control.getText();
		return text != null && text.trim().length() > 0;
	}

}
