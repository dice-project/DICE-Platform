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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichTextEditor;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * The base class for all Method editor form pages.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @author Shashidhar Kannoori
 * @since 1.0
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=176382
 */
public class BaseFormPage extends FormPage {

	/**
	 * Button types.
	 */
	public static final int ADD_BUTTON = 0;

	public static final int REMOVE_BUTTON = 1;

	public static final int UP_BUTTON = 2;

	public static final int DOWN_BUTTON = 3;

	protected static final int ORDER_BUTTON = 4;

	protected static final int SELECT_BUTTON = 5;

	protected static final int CLEAR_BUTTON = 6;

	public static final int ATTACH_BUTTON = 7;

	public static final int DETACH_BUTTON = 8;
	
	public static final int ATTACH_URL_BUTTON = 9;
	
	/**
	 * Table types.
	 */
	public static final int SMALL_SIZE = 0;

	public static final int MEDIUM_SIZE = 1;

	public static final int LARGE_SIZE = 2;

	public static final int SINGLE_ROW = 3;

	protected boolean debug;

	protected ScrolledForm form;

	protected FormToolkit toolkit;

	protected String editorTabName;

	protected String editorName;

	protected MethodUnit methodUnit;
	
	protected MethodElement methodElement;

	protected ContentElement contentElement = null;

	protected String contentElementPath;

	private int SECTION_ID;

	public static String LABEL_DECORATOR_KEY = "labelControlDecoration"; //$NON-NLS-1$
	
	/**
	 * Creates a new instance.
	 * 
	 * @param editor
	 *            The parent form editor.
	 * @param id
	 *            The unique ID for the form page.
	 * @param title
	 *            The title for the form page.
	 */
	public BaseFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		debug = AuthoringUIPlugin.getDefault().isDebugging();
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		setSite(site);
		setInput(input);

		// Retrieve the ContentElement object from the editor input.
		MethodElementEditorInput methodElementInput = (MethodElementEditorInput) input;
		methodElement = methodElementInput.getMethodElement();
		MethodElement elementOfPath =  methodElement instanceof ProcessComponent ?
				((ProcessComponent) methodElement).getProcess() : methodElement;
		contentElementPath = ResourceHelper.getFolderAbsolutePath(elementOfPath);
		if (methodElement instanceof MethodUnit) {
			methodUnit = (MethodUnit)methodElement;
		}
		if (methodElement instanceof ContentElement) {
			contentElement = (ContentElement) methodElement;
			ContentDescription contentDescription = contentElement
					.getPresentation();
			methodUnit = contentDescription;
		}
	}

	/**
	 * @see org.eclipse.ui.forms.editor.createFormContent(IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		form = managedForm.getForm();
		toolkit = managedForm.getToolkit();
		form.getBody().setLayout(new TableWrapLayout());
	}

	/**
	 * Called when the expand/collapse button is selected.
	 * 
	 * @param event
	 *            The associated event.
	 */
	protected void toggle(HyperlinkEvent event) {
	}

	/**
	 * Called when the expand/collapse button is selected.
	 * 
	 * @param event
	 *            The associated event.
	 * @param o
	 *            ???
	 */
	protected void toggle(HyperlinkEvent event, int o) {
	}

	/**
	 * Sets the name for the editor.
	 * 
	 * @param editorName
	 *            The name of the editor.
	 */
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	/**
	 * Returns a list of Method elements.
	 * 
	 * @param viewer
	 *            A TableViewer.
	 * @return A list of Method elements.
	 */
	public List<?> retrieveTableViewerContents(TableViewer viewer) {
		Object[] elements = ((IStructuredContentProvider) viewer
				.getContentProvider()).getElements(viewer.getInput());
		List<Object> elementList = new ArrayList<Object>();
		for (int i = 0; i < elements.length; i++)
			elementList.add(elements[i]);
		return elementList;
	}

	/**
	 * Creates a section on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param name
	 *            The section name.
	 * @param description
	 *            The section description.
	 * @return A new <code>Section</code>.
	 */
	public Section createSection(FormToolkit toolkit, Composite parent,
			String title, String description) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TWISTIE | Section.EXPANDED | Section.TITLE_BAR);
		GridData td = new GridData(SWT.FILL, SWT.FILL, true, false);
		section.setLayoutData(td);
		section.setText(title);
//		String text = MessageFormat.format(description,
//				new String[] { LibraryUIText.getUITextLower(methodElement) });
//		section.setDescription(text);
		section.setDescription(description);
		section.setLayout(new GridLayout());
		return section;
	}

	/**
	 * Creates a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param text
	 *            The text for the label.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Label.
	 * @return A new <code>Label</code>.
	 */
	protected static Label createLabel(FormToolkit toolkit, Composite parent,
			String text, int gridDataStyle, int horizontalSpan) {
		Label label = toolkit.createLabel(parent, text, SWT.WRAP);
		GridData gridData = new GridData(gridDataStyle);
		gridData.horizontalSpan = horizontalSpan;
		gridData.verticalAlignment = SWT.TOP;
		gridData.widthHint = (horizontalSpan == 2) ? 115 : 100;
		label.setLayoutData(gridData);
		return label;
	}
	
	/**
	 * Creates a label on a form page without wrap and no widthHint.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param text
	 *            The text for the label.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Label.
	 * @return A new <code>Label</code>.
	 */
	public static Label createLabelWithNoWrap(FormToolkit toolkit, Composite parent,
			String text, int gridDataStyle, int horizontalSpan) {
		Label label = toolkit.createLabel(parent, text);
		GridData gridData = new GridData(gridDataStyle);
		gridData.horizontalSpan = horizontalSpan;
		gridData.verticalAlignment = SWT.TOP;
		//gridData.widthHint = (horizontalSpan == 2) ? 115 : 100;
		label.setLayoutData(gridData);
		return label;
	}

	/**
	 * Creates a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param text
	 *            The text for the label.
	 * @return A new <code>Label</code>.
	 */
	public static Label createLabel(FormToolkit toolkit, Composite parent,
			String text) {
		return createLabel(toolkit, parent, text, GridData.BEGINNING, 1);
	}

	/**
	 * Creates a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param text
	 *            The text for the label.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Label.
	 * @return A new <code>Label</code>.
	 */
	public static Label createLabel(FormToolkit toolkit, Composite parent,
			String text, int horizontalSpan) {
		return createLabel(toolkit, parent, text, GridData.BEGINNING,
				horizontalSpan);
	}

	/**
	 * Creates a blank label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Label.
	 * @return A new <code>Label</code>.
	 */
	protected static Label createBlankLabel(FormToolkit toolkit,
			Composite parent, int horizontalSpan) {
		return createLabel(toolkit, parent,
				"", GridData.FILL_HORIZONTAL, horizontalSpan); //$NON-NLS-1$
	}

	/**
	 * Creates a text control on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The Text style.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @param heigth
	 *            The height of the control.
	 * @param width
	 *            The width of the text control.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Text control.
	 * @return A new <code>Text</code> control.
	 */
	protected static Text createTextEdit(FormToolkit toolkit, Composite parent,
			int style, int gridDataStyle, int height, int width,
			int horizontalSpan) {
		Text control = toolkit.createText(parent, "", style); //$NON-NLS-1$
		GridData gridData = new GridData(gridDataStyle);
		gridData.heightHint = height;
		gridData.widthHint = width;
		gridData.horizontalSpan = horizontalSpan;
		control.setLayoutData(gridData);
		return control;
	}

	/**
	 * Creates a single-line text control on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @return A new <code>Text</code> control.
	 */
	protected static Text createTextEdit(FormToolkit toolkit, Composite parent) {
		return createTextEdit(toolkit, parent, SWT.SINGLE | SWT.WRAP,
				GridData.FILL_HORIZONTAL, SWT.DEFAULT, 100, 1);
	}

	/**
	 * Creates a text control with a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param textStyle
	 *            The Text style.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @param height
	 *            The height of the control.
	 * @param width
	 *            The width of the text control.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Text control.
	 * @param labelText
	 *            the text for the label.
	 * @return A new <code>Text</code> control.
	 */
	protected static Text createTextEditWithLabel(FormToolkit toolkit,
			Composite parent, int textStyle, int gridDataStyle, int height,
			int width, int horizontalSpan, String textLabel) {
		createLabel(toolkit, parent, textLabel);
		return createTextEdit(toolkit, parent, textStyle, gridDataStyle,
				height, width, horizontalSpan);
	}

	/**
	 * Creates a single-line text control with a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            the text for the label.
	 * @return A new <code>Text</code> control.
	 */
	protected static Text createTextEditWithLabel(FormToolkit toolkit,
			Composite parent, String labelText) {
		createLabel(toolkit, parent, labelText, 2);
		return createTextEdit(toolkit, parent);
	}
	
	/**
	 * Creates a double-line text control with a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            the text for the label.
	 * @return A new <code>Text</code> control.
	 */
	public static Text createTextEditWithLabel2(FormToolkit toolkit,
			Composite parent, String labelText) {
		createLabel(toolkit, parent, labelText, 2);
		return createTextEdit(toolkit, parent, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL, GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL, 40, 300, 1);
	}

	public static Text createTextEditWithLabel2a(FormToolkit toolkit,
			Composite parent, String labelText) {
		Label label = createLabel(toolkit, parent, labelText);
		((GridData) label.getLayoutData()).widthHint = SWT.DEFAULT;
		return createTextEdit(toolkit, parent, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL, GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL, 40, 300, 1);
	}
	
	/**
	 * Creates a single or multi-line text control with a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            the text for the label.
	 * @return A new <code>Text</code> control.
	 */
	public static Text createTextEditWithLabel3(FormToolkit toolkit,
			Composite parent, String labelText, int height, int singleOrMulti) {
		createLabel(toolkit, parent, labelText, 2);
		return createTextEdit(toolkit, parent, singleOrMulti | SWT.WRAP
				| SWT.V_SCROLL | SWT.TRAVERSE_TAB_NEXT,
				GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL, height,
				300, 2);
	}

	/**
	 * Creates a single or multi-line text control with a label in a composite.
	 * 
	 * @author hopeshared
	 */
	protected static Text createTextEditWithLabel4(FormToolkit toolkit,
			Composite parent, String labelText, int height, int singleOrMulti, String fillText) {
		createLabel(toolkit, parent, labelText, 2);
		
		int horizontalSpan = calculateSpan(fillText);
		
		if(horizontalSpan==1){
			Text text = createTextEdit(toolkit, parent, singleOrMulti | SWT.WRAP
					| SWT.V_SCROLL | SWT.TRAVERSE_TAB_NEXT, GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL, height, 200, 1);
			Text text2 = createTextEdit(toolkit, parent, singleOrMulti | SWT.WRAP
					| SWT.V_SCROLL, GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL, height, 100, 1);
			text2.setVisible(false);
			createLabel(toolkit, parent,
					"", 1); //$NON-NLS-1$
			return text;
		}else{
			return createTextEdit(toolkit, parent, singleOrMulti | SWT.WRAP
					| SWT.V_SCROLL | SWT.TRAVERSE_TAB_NEXT,
					GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL, height,
					300, 3);
		}
	}
	
	
	/**
	 * Creates a double-line text control with a label on a form page.
	 * @author hopeshared
	 */
	protected static Text createTextEditWithLabel5(FormToolkit toolkit,
			Composite parent, String labelText) {
		createLabel(toolkit, parent, labelText, 2);
		return createTextEdit(toolkit, parent, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL, GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL, 40, 300, 2);
	}
	
	/**
	 * Creates a single or multi-line text control with a label on a form page.
	 * @author hopeshared
	 */
	protected static Text createTextEditWithLabel5(FormToolkit toolkit,
			Composite parent, String labelText, int height, int singleOrMulti) {
		createLabel(toolkit, parent, labelText, 2);
		return createTextEdit(toolkit, parent, singleOrMulti | SWT.WRAP
				| SWT.V_SCROLL | SWT.TRAVERSE_TAB_NEXT,
				GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL, height,
				300, 3);
	}
	
	/**
	 * Creates a combobox control with a label on a form page.
	 * 
	 * @author hopeshared
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            The text for the Label.
	 * @return A new <code>Combobox</code>.
	 */
	public static Combo createComboWithLabel3(FormToolkit toolkit, Composite parent,
			String labelText) {
		createLabel(toolkit, parent, labelText, 2);
		return createCombo(parent, SWT.SINGLE | SWT.FLAT | SWT.READ_ONLY |SWT.TRAVERSE_TAB_NEXT,
				GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
	}
	
	/**
	 * Calculate the text's horizontal span.
	 * 
	 * @author hopeshared
	 */
	protected static int calculateSpan(String fillText){
		int length = fillText.length();

		if(length < 60)
			return 1;
		return 2;
	}


	/**
	 * Creates a large text control with a label on a form page.
	 * <p>
	 * Used for the Brief Description box.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            the text for the label.
	 * @return A new <code>Text</code> control.
	 */
	public static Text createTextEditWithLabelLarge(FormToolkit toolkit,
			Composite parent, String labelText) {
		Label label = createLabel(toolkit, parent, labelText);
		((GridData) label.getLayoutData()).widthHint = SWT.DEFAULT;
		return createTextEdit(toolkit, parent, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL, GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL, 80, SWT.DEFAULT, 3);
	}

	/**
	 * Creates an image hyperlink on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param text
	 *            The text for the hyperlink.
	 * @param image
	 *            The hyperlink image.
	 * @return A new <code>ImageHyperlink</code>.
	 */
	protected ImageHyperlink createImageHyperlink(FormToolkit toolkit,
			Composite parent, String text, Image image) {
		ImageHyperlink link = toolkit.createImageHyperlink(parent, SWT.LEFT
				| SWT.TOP);
		GridData gridData = new GridData(GridData.BEGINNING);
		gridData.verticalAlignment = SWT.TOP;
		link.setLayoutData(gridData);
		link.setImage(image);
		if (text != null)
			link.setText(text);
		return link;
	}

	/**
	 * Creates an image hyperlink on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param sectionId
	 *            The sction ID.
	 * @return A new <code>ImageHyperlink</code>.
	 */
	protected ImageHyperlink createHyperLink(FormToolkit toolkit,
			Composite expandedComposite, int sectionID) {
		SECTION_ID = sectionID;
		ImageHyperlink expandLink = toolkit.createImageHyperlink(
				expandedComposite, SWT.NONE);
		expandLink.setImage(AuthoringUIImages.IMG_EXPANDED);
		expandLink.setUnderlined(false);
		expandLink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				toggle(e, SECTION_ID);
			}
		});
		return expandLink;
	}

	/**
	 * Creates a rich text control on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The rich text style.
	 * @param height
	 *            The height of the control.
	 * @param width
	 *            The width of the text control.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Text control.
	 * @return A new <code>IMethodRichText</code>.
	 */
	protected IMethodRichText createRichTextEdit(FormToolkit toolkit,
			Composite parent, int style, int gridDataStyle, int height,
			int width, int horizontalSpan, Label label) {
		IMethodRichText richText = MethodFormToolkit.createRichText(toolkit,
				parent, "", style, contentElementPath, methodElement, label); //$NON-NLS-1$
		GridData gridData = new GridData(gridDataStyle);
		gridData.heightHint = height;
		gridData.widthHint = width;
		gridData.horizontalSpan = horizontalSpan;
		richText.setLayoutData(gridData);
		return richText;
	}

	/**
	 * Creates a rich text control on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @return A new <code>IMethodRichText</code>.
	 */
	protected IMethodRichText createRichTextEdit(FormToolkit toolkit,
			Composite parent) {
		return createRichTextEdit(toolkit, parent, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL, GridData.FILL_HORIZONTAL, SWT.DEFAULT,
				SWT.DEFAULT, 1, null);
	}
	
	
	public static Label createDecoratedLabel(FormToolkit toolkit, Composite parent,
			String text) {
		Label decoratedLabel = createLabel(toolkit, parent, text);
		int margin = FieldDecorationRegistry.getDefault().getMaximumDecorationWidth(); 
		((GridData)decoratedLabel.getLayoutData()).horizontalIndent = margin;
		ControlDecoration labelControlDecoration = new ControlDecoration(decoratedLabel, SWT.LEFT | SWT.BOTTOM);
		decoratedLabel.setData(LABEL_DECORATOR_KEY, labelControlDecoration);
		return decoratedLabel;
	}
	
	/**
	 * Creates a rich text control with a image hyperLink and label on a form
	 * page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            The text for the label.
	 * @param sectionId
	 *            The section ID.
	 * @return A new <code>IRichMethodText</code>.
	 */
	protected IMethodRichText createRichTextEditWithLink(
			FormToolkit toolkit, Composite parent, String labelText,
			int height, int width, int horizontalSpan) {
		ImageHyperlink link = createImageHyperlink(toolkit, parent, null,
				AuthoringUIImages.IMG_COLLAPSED);
		link.setToolTipText(AuthoringUIResources.openRTE);
		// create label with ControlDecoration here for markers
		Label decoratedLabel = createDecoratedLabel(toolkit, parent, labelText);

		IMethodRichText control = createRichTextEdit(toolkit, parent, SWT.MULTI
				| SWT.WRAP | SWT.V_SCROLL, GridData.FILL_HORIZONTAL, height,
				width, horizontalSpan, decoratedLabel);

		link.setHref(control);
		link.setData("Title", labelText); //$NON-NLS-1$
		link.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				toggle(e);
			}
		});

		return control;
	}


	/**
	 * Creates a rich text control with a image hyperLink and label on a form
	 * page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            The text for the label.
	 * @param sectionId
	 *            The section ID.
	 * @return A new <code>IRichMethodText</code>.
	 */
	public IMethodRichText createRichTextEditWithLinkForSection(
			FormToolkit toolkit, Composite parent, String labelText,
			int height, int width, int sectionID) {
		final int SECTION_ID = sectionID;
		ImageHyperlink link = createImageHyperlink(toolkit, parent, null,
				AuthoringUIImages.IMG_COLLAPSED);
		link.setToolTipText(AuthoringUIResources.openRTE);

		// create label with ControlDecoration here for markers
		Label decoratedLabel = createDecoratedLabel(toolkit, parent, labelText);

		IMethodRichText control = createRichTextEdit(toolkit, parent, SWT.MULTI
				| SWT.WRAP | SWT.V_SCROLL, GridData.FILL_HORIZONTAL, height,
				width, 1, decoratedLabel);

		link.setHref(control);
		link.setData("Title", labelText); //$NON-NLS-1$
		link.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				toggle(e, SECTION_ID);
			}
		});

		return control;
	}

	/**
	 * Creates a rich text editor on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The rich text style.
	 * @param height
	 *            The height of the control.
	 * @param width
	 *            The width of the text control.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Text control.
	 * @return The new <code>IMethodRichTextEditor</code>.
	 */
	protected IMethodRichTextEditor createRichTextEditor(FormToolkit toolkit,
			Composite parent, int style, int gridDataStyle, int height,
			int width, int horizontalSpan, Label label) {
		IMethodRichTextEditor editor = MethodFormToolkit.createRichTextEditor(
				toolkit, parent, "", style, contentElementPath, methodElement, label, getEditor().getEditorSite()); //$NON-NLS-1$
		GridData gridData = new GridData(gridDataStyle);
		gridData.heightHint = height;
		gridData.widthHint = width;
		gridData.horizontalSpan = horizontalSpan;
		editor.setLayoutData(gridData);
		return editor;
	}

	/**
	 * Creates a combobox control on a form page.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The Combobox style.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @return A new <code>Combobox</code>.
	 */
	protected static Combo createCombo(Composite parent, int style, int gridDataStyle) {
		Combo control = new Combo(parent, style);
		return control;
	}

	/**
	 * Creates a combobox control on a form page.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @return A new <code>Combobox</code>.
	 */
	protected Combo createCombo(Composite parent) {
		return createCombo(parent, SWT.SINGLE | SWT.FLAT | SWT.READ_ONLY,
				GridData.FILL_HORIZONTAL);
	}

	/**
	 * Creates a combobox control with a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            The text for the Label.
	 * @param style
	 *            The Combobox style.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @return A new <code>Combobox</code>.
	 */
	protected Combo createCComboWithLabel(FormToolkit toolkit,
			Composite parent, String textLabel, int style, int gridDataStyle) {
		createLabel(toolkit, parent, textLabel);
		return createCombo(parent, style, gridDataStyle);
	}

	/**
	 * Creates a combobox control with a label on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param labelText
	 *            The text for the Label.
	 * @return A new <code>Combobox</code>.
	 */
	protected Combo createComboWithLabel(FormToolkit toolkit, Composite parent,
			String labelText) {
		createLabel(toolkit, parent, labelText);
		return createCombo(parent, SWT.SINGLE | SWT.FLAT | SWT.READ_ONLY,
				GridData.FILL_HORIZONTAL);
	}

	/**
	 * Creates a composite on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param section
	 *            The parent section.
	 * @return A new <code>Composite</code>.
	 */

	public Composite createComposite(FormToolkit toolkit, Section section) {
		Composite composite = toolkit.createComposite(section);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite.setLayout(new GridLayout(3, false));
		section.setClient(composite);
		return composite;
	}

	/**
	 * Creates a composite on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @param verticalSpan
	 *            The number of row cells taken up by the Composite.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Composite.
	 * @param numColumns
	 *            The number of columns in the grid.
	 * @return A new <code>Composite</code>.
	 */
	public static Composite createComposite(FormToolkit toolkit,
			Composite parent, int gridDataStyle, int verticalSpan,
			int horizontalSpan, int numColumns) {
		Composite composite = toolkit.createComposite(parent, SWT.NONE);
		GridData gridData = new GridData(gridDataStyle);
		gridData.verticalSpan = verticalSpan;
		gridData.horizontalSpan = horizontalSpan;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(numColumns, false));
		return composite;
	}

	/**
	 * Creates a composite on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param verticalSpan
	 *            The number of row cells taken up by the Composite.
	 * @return A new <code>Composite</code>.
	 */
	public static Composite createComposite(FormToolkit toolkit,
			Composite parent, int verticalSpan) {
		return createComposite(toolkit, parent, GridData.FILL_BOTH,
				verticalSpan, 1, 1);
	}

	/**
	 * Creates a composite for displaying buttons on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param type
	 *            The Composite type.
	 * @return A new <code>Composite</code>.
	 */
	public static Composite createCompositeForButtons(FormToolkit toolkit,
			Composite parent) {
		return createComposite(toolkit, parent, GridData.VERTICAL_ALIGN_CENTER
				| GridData.HORIZONTAL_ALIGN_CENTER, SWT.DEFAULT, SWT.DEFAULT, 1);
	}

	/**
	 * Creates a expanded composite in the form
	 * <p>
	 * Used to hold an expanded rich text editor.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @return A new <code>Composite</code>.
	 */
	protected Composite createExpandedComposite(FormToolkit toolkit,
			Composite parent) {
		Composite expandedComposite = toolkit.createComposite(parent, SWT.NONE);
		expandedComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expandedComposite.setLayout(new GridLayout(2, false));
		expandedComposite.setVisible(false);
		return expandedComposite;
	}

	/**
	 * Creates a table on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The Table style.
	 * @param gridDataStyle
	 *            The GridData style.
	 * @param height
	 *            The height of the Table.
	 * @param width
	 *            The width of the Table.
	 * @param verticalSpan
	 *            The number of row cells taken up by the Table.
	 * @param horizontalSpan
	 *            The number of column cells taken up by the Table.
	 * @return A new <code>Table</code>.
	 */
	protected static Table createTable(FormToolkit toolkit, Composite parent,
			int style, int gridDataStyle, int height, int width,
			int verticalSpan, int horizontalSpan) {
		Table table = toolkit.createTable(parent, style);
		GridData gridData = new GridData(gridDataStyle);
		gridData.heightHint = height;
		gridData.widthHint = width;
		gridData.verticalSpan = verticalSpan;
		gridData.horizontalSpan = horizontalSpan;
		table.setLayoutData(gridData);
		return table;
	}

	/**
	 * Creates a table on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param type
	 *            The table table.
	 * @return A new <code>Table</code>.
	 */
	public static Table createTable(FormToolkit toolkit, Composite parent,
			int type) {
		switch (type) {
		case SMALL_SIZE:
			return createTable(toolkit, parent, SWT.MULTI, GridData.FILL_BOTH,
					56, 200, 1, 1);
		case MEDIUM_SIZE:
			return createTable(toolkit, parent, SWT.MULTI, GridData.FILL_BOTH,
					112, 200, 1, 1);
		case LARGE_SIZE:
			return createTable(toolkit, parent, SWT.MULTI, GridData.FILL_BOTH,
					200, 200, 1, 1);
		case SINGLE_ROW:
			return createTable(toolkit, parent, SWT.MULTI,
					GridData.FILL_HORIZONTAL, 20, 200, 1, 1);
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates a button on a form page.
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 * @param parent
	 *            The parent composite.
	 * @param type
	 *            The button type.
	 * @return A new <code>Button</code>.
	 */
	public static Button createButton(FormToolkit toolkit, Composite parent,
			int type) {
		Button button;
		switch (type) {
		case ADD_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.ADD_BUTTON_TEXT, SWT.NONE);
			break;
		case REMOVE_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.REMOVE_BUTTON_TEXT, SWT.NONE);
			break;
		case UP_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.UP_BUTTON_TEXT, SWT.NONE);
			break;
		case DOWN_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.DOWN_BUTTON_TEXT, SWT.NONE);
			break;
		case ORDER_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.ORDER_BUTTON_TEXT, SWT.NONE);
			break;
		case SELECT_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.SELECT_BUTTON_TEXT, SWT.NONE);
			break;
		case CLEAR_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.CLEAR_BUTTON_TEXT, SWT.NONE);
			break;
		case ATTACH_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.ATTACH_BUTTON_TEXT, SWT.NONE);
			break;
		case ATTACH_URL_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.ATTACH_URL_BUTTON_TEXT, SWT.NONE);
			break;		
		case DETACH_BUTTON:
			button = toolkit.createButton(parent,
					AuthoringUIText.DETACH_BUTTON_TEXT, SWT.NONE);
			break;
		default:
			throw new IllegalArgumentException();
		}
		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button.setEnabled(false);
		return button;
	}
	
	/**
	 * Get height of the rich text control
	 * @return
	 * 		Height of the rich text control
	 */
	public int getRichTextEditorHeight() {
		return form.getBounds().height - 3 * 32;
	}

	/**
	 * Get width of the rich text control
	 * @return
	 * 		Width of the rich text control
	 */
	public int getRichTextEditorWidth() {
		return form.getBounds().width - 2 * 32;
	}

	public MethodElement getMethodElement() {
		return methodElement;
	}


}
