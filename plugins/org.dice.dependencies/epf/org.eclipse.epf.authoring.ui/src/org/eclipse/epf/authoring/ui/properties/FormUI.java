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

import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.forms.BaseFormPage;
import org.eclipse.epf.authoring.ui.forms.MethodFormToolkit;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.util.RichTextImageLinkContainer;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;


/**
 * The UI util class tailored for properties view for process authoring
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class FormUI {

	private static final int DEFAULT_WIDTH = 300;

	private static Label _createLabel(FormToolkit toolkit, Composite parent,
			String str, int gridDataStyle, int horizontalSpan) {
		Label label = toolkit.createLabel(parent, str);
		GridData gridData = new GridData(gridDataStyle);
		gridData.horizontalSpan = horizontalSpan;
		label.setLayoutData(gridData);
		return label;
	}

	public static Label createLabel(FormToolkit toolkit, Composite parent,
			String str) {
		// make horizonatalSpan 1
		return _createLabel(toolkit, parent, str, GridData.BEGINNING, 1);
	}

	public static Label createLabel(FormToolkit toolkit, Composite parent,
			String str, int horizontalSpan) {
		// make horizonatalSpan 2
		return _createLabel(toolkit, parent, str, GridData.BEGINNING,
				horizontalSpan);
	}

	private static Text _createText(FormToolkit toolkit, Composite parent,
			int textStyle, int gridDataStyle, int height, int width,
			int horizontalSpan) {
		Text control = toolkit.createText(parent, "", textStyle); //$NON-NLS-1$
		GridData gridData = new GridData(gridDataStyle);
		gridData.heightHint = height;
		gridData.widthHint = width;
		gridData.horizontalSpan = horizontalSpan;
		control.setLayoutData(gridData);
		return control;
	}

	public static Text createText(FormToolkit toolkit, Composite parent) {
		return _createText(toolkit, parent, SWT.SINGLE | SWT.WRAP,
				GridData.FILL_HORIZONTAL, SWT.DEFAULT, DEFAULT_WIDTH,
				SWT.DEFAULT);
	}

	public static Text createText(FormToolkit toolkit, Composite parent,
			int height) {
		return _createText(toolkit, parent, SWT.MULTI | SWT.WRAP,
				GridData.FILL_HORIZONTAL, height, DEFAULT_WIDTH, SWT.DEFAULT);
	}

	public static Text createText(FormToolkit toolkit, Composite parent,
			int height, int horizontalSpan) {
		return _createText(toolkit, parent, SWT.SINGLE | SWT.WRAP,
				GridData.FILL_HORIZONTAL, height, DEFAULT_WIDTH, horizontalSpan);
	}

	private static IMethodRichText _createRichText(FormToolkit toolkit,
			Composite parent, int height, int width, String path,
			MethodElement methodElement, Label label) {
		IMethodRichText control = MethodFormToolkit.createRichText(toolkit,
				parent,
				"", SWT.MULTI | SWT.WRAP | SWT.V_SCROLL, path, methodElement, label); //$NON-NLS-1$
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		gridData.heightHint = height;
		gridData.widthHint = width;
		control.setLayoutData(gridData);
		return control;
	}

	public static RichTextImageLinkContainer createRichTextWithLink(FormToolkit toolkit,
			Composite parent, int height, String path,
			MethodElement methodElement, String labelText) {
		ImageHyperlink link = createImageHyperLink(toolkit, parent, AuthoringUIImages.IMG_COLLAPSED,
				AuthoringUIResources.openRTE);
		link.setData("Title", labelText); //$NON-NLS-1$
		Label decoratedLabel = BaseFormPage.createDecoratedLabel(toolkit, parent, labelText);
		((GridData)decoratedLabel.getLayoutData()).verticalAlignment = SWT.CENTER;

		IMethodRichText control = _createRichText(toolkit, parent, height, DEFAULT_WIDTH, path,
				methodElement, decoratedLabel);
		link.setHref(control);

		return new RichTextImageLinkContainer(control, link, decoratedLabel);
	}

	public static ImageHyperlink createImageHyperLink(FormToolkit toolkit,
			Composite parent, Image image, String toolTip) {
		ImageHyperlink link = toolkit.createImageHyperlink(parent, SWT.LEFT
				| SWT.TOP);
		GridData gridData = new GridData(GridData.BEGINNING);
		link.setLayoutData(gridData);
		link.setImage(image);
		link.setToolTipText(toolTip);
		return link;
	}

	public static Section createSection(FormToolkit toolkit, Composite parent,
			String title, String description) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TWISTIE | Section.EXPANDED);
		GridData gd = new GridData(GridData.FILL_BOTH);
		section.setLayoutData(gd);
		section.setText(title);
		section.setDescription(description);
		toolkit.createCompositeSeparator(section);
		section.setLayout(new GridLayout());
		return section;
	}
	
	public static Section createSection(FormToolkit toolkit, Composite parent,
			String title, String description, int gdStyle) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TWISTIE | Section.EXPANDED);
		GridData gd = new GridData(gdStyle);
		section.setLayoutData(gd);
		section.setText(title);
		section.setDescription(description);
		toolkit.createCompositeSeparator(section);
		section.setLayout(new GridLayout());
		return section;
	}

	public static Composite createComposite(FormToolkit toolkit,
			Section section, int numOfColumns, boolean expanded) {
		Composite composite = toolkit.createComposite(section);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout(numOfColumns, false));
		if (!expanded)
			section.setClient(composite);
		else
			composite.setVisible(false);
		return composite;
	}
	
	public static Composite createComposite(FormToolkit toolkit,
			Composite parent, int gridStyle) {
		Composite pane = toolkit.createComposite(parent, SWT.NONE);
		GridData gridData = new GridData(gridStyle);
		pane.setLayoutData(gridData);
		pane.setLayout(new GridLayout());
		return pane;
	}

	public static Composite createComposite(FormToolkit toolkit,
			Composite parent, int gridStyle, int numOfColumns,
			boolean equalSizeColumn) {
		Composite pane = toolkit.createComposite(parent, SWT.NONE);
		GridData gridData = new GridData(gridStyle);
		gridData.horizontalSpan = numOfColumns;
		pane.setLayoutData(gridData);
		GridLayout layout = new GridLayout(numOfColumns, equalSizeColumn);
		layout.marginBottom = 0;
		layout.marginHeight = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginWidth = 0;
		pane.setLayout(layout);
		return pane;
	}

	private static Button _createButton(FormToolkit toolkit, Composite parent,
			String buttonStr, int style, int horizontalSpan) {
		Button control = toolkit.createButton(parent, buttonStr, style);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = horizontalSpan;
		control.setLayoutData(gridData);
		return control;
	}

	private static Button _createButton(FormToolkit toolkit, Composite parent,
			String buttonStr, int style) {
		Button control = toolkit.createButton(parent, buttonStr, style);
		control.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return control;
	}
	
	

	private static Combo _createCombo(FormToolkit toolkit, Composite parent,
			int style, int horizontalSpan) {
		Combo combo = new Combo(parent, style);
		GridData gridData = new GridData(GridData.BEGINNING);
		gridData.horizontalSpan = horizontalSpan;
		combo.setLayoutData(gridData);
		return combo;
	}

	public static Combo createCombo(FormToolkit toolkit, Composite parent,
			int horizontalSpan) {
		return _createCombo(toolkit, parent, SWT.SINGLE | SWT.FLAT
				| SWT.READ_ONLY, horizontalSpan);
	}
	
	public static Button createButton(FormToolkit toolkit, Composite parent,
			String buttonStr) {
		return _createButton(toolkit, parent, buttonStr, SWT.NONE);
	}


	public static Button createButton(FormToolkit toolkit, Composite parent,
			int style, int horizontalSpan) {
		return _createButton(toolkit, parent, "", style, horizontalSpan); //$NON-NLS-1$
	}
	
	public static Button createCheckButton(FormToolkit toolkit, Composite parent, int horizontalSpan)
	{
		Button control = toolkit.createButton(parent, "", SWT.CHECK);		//$NON-NLS-1$
		GridData gridData = new GridData(GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = horizontalSpan;
		control.setLayoutData(gridData);
		return control;
	}

	private static Table _createTable(FormToolkit toolkit, Composite parent,
			int tableStyle, int gridStyle, int height, int width,
			int verticalSpan, int horizontalSpan) {
		Table table = toolkit.createTable(parent, tableStyle);
		GridData gridData = new GridData(gridStyle);
		gridData.heightHint = height;
		gridData.widthHint = width;
		gridData.verticalSpan = verticalSpan;
		gridData.horizontalSpan = horizontalSpan;
		table.setLayoutData(gridData);
		return table;
	}

	public static Table createTable(FormToolkit toolkit, Composite parent) {
		return _createTable(toolkit, parent, SWT.MULTI, GridData.FILL_BOTH,
				200, 200, 1, 1);
	}

	public static Table createTable(FormToolkit toolkit, Composite parent,
			int height) {
		return _createTable(toolkit, parent, SWT.MULTI, GridData.FILL_BOTH,
				height, 200, 1, 1);
	}
}
