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

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * Description page for method library
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodLibraryDescriptionFormPage extends FormPage {
	private static final int DEFAULT_VERTICAL_INDENT = 2;

	private Text ctrl_name;

	private Text ctrl_brief_desc;

	private Section generalSection;

	private Composite generalComposite;

	private MethodLibrary library;

	private Text textAuthors;

	private Text ctrl_change_date;

	private Text ctrl_change_desc;

	private Text ctrl_version;

	private Text ctrl_copyright;

	/**
	 * Creates an instance
	 * @param editor
	 */
	public MethodLibraryDescriptionFormPage(FormEditor editor) {
		super(
				editor,
				AuthoringUIResources.descriptionPage_title, AuthoringUIResources.descriptionPage_title); 
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);

		// get MethodLibrary object from Editor input
		MethodElementEditorInput methodElementInput = (MethodElementEditorInput) input;

		Object obj = methodElementInput.getMethodElement();
		if (obj instanceof MethodLibrary) {
			library = (MethodLibrary) obj;
		}
	}

	/**
	 * Creates the form content.
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		// create form toolkit
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText(getEditor().getPartName());

		TableWrapLayout layout = new TableWrapLayout();
		form.getBody().setLayout(layout);

		// Create editor content
		createEditorContent(toolkit, form.getBody());
	}

	/**
	 * Create editor tab content
	 * 
	 * @param toolkit
	 * @param editorComposite
	 */
	private void createEditorContent(FormToolkit toolkit,
			Composite editorComposite) {
		// Create General Information section
		generalSection = toolkit.createSection(editorComposite,
				Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED
						| Section.TITLE_BAR);
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		generalSection.setLayoutData(td);
		generalSection
				.setText(AuthoringUIResources.MethodLibraryDescriptionFormPage_section_title); 
		generalSection
				.setDescription(AuthoringUIResources.MethodLibraryDescriptionFormPage_section_description); 
		generalSection.setLayout(new GridLayout());

		generalComposite = toolkit.createComposite(generalSection);
		generalComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		generalComposite.setLayout(new GridLayout(2, false));
		generalSection.setClient(generalComposite);

		// name
		Label l_name = toolkit.createLabel(generalComposite,
				AuthoringUIResources.name_text); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			l_name.setLayoutData(gridData);
		}

		ctrl_name = toolkit.createText(generalComposite, "", SWT.SINGLE); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.widthHint = 400;
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			ctrl_name.setLayoutData(gridData);
		}

		// brief desc
		Label l_brief_desc = toolkit.createLabel(generalComposite,
				AuthoringUIResources.description_text); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			l_brief_desc.setLayoutData(gridData);
		}

		ctrl_brief_desc = toolkit.createText(generalComposite,
				"", SWT.MULTI | SWT.WRAP); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.heightHint = 40;
			gridData.widthHint = 400;
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			ctrl_brief_desc.setLayoutData(gridData);
		}

		// Authors
		Label label = toolkit.createLabel(generalComposite,
				AuthoringUIResources.authors_text); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			label.setLayoutData(gridData);
		}

		textAuthors = toolkit.createText(generalComposite,
				"", SWT.MULTI | SWT.WRAP); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.heightHint = 40;
			gridData.widthHint = 400;
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			textAuthors.setLayoutData(gridData);
		}

		// Change Date
		Label l_changedate = toolkit.createLabel(generalComposite, ""); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			l_changedate.setLayoutData(gridData);
		}

		ctrl_change_date = toolkit.createText(generalComposite,
				"", SWT.READ_ONLY); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			ctrl_change_date.setLayoutData(gridData);
		}

		// Authors
		Label l_change_desc = toolkit.createLabel(generalComposite,
				AuthoringUIText.CHANGE_DESCRIPTION_TEXT);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			l_change_desc.setLayoutData(gridData);
		}

		ctrl_change_desc = toolkit.createText(generalComposite, "", SWT.MULTI); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.heightHint = 40;
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			ctrl_change_desc.setLayoutData(gridData);
		}

		// Version
		Label l_version = toolkit.createLabel(generalComposite,
				AuthoringUIText.VERSION_TEXT);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			l_version.setLayoutData(gridData);
		}

		ctrl_version = toolkit.createText(generalComposite, "", SWT.MULTI); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			ctrl_version.setLayoutData(gridData);
		}
		Label l_copyright = toolkit.createLabel(generalComposite,
				AuthoringUIResources.copyright_text); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			l_copyright.setLayoutData(gridData);
		}
		ctrl_copyright = toolkit.createText(generalComposite, ""); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.GRAB_HORIZONTAL);
			gridData.heightHint = 40;
			gridData.widthHint = 400;
			gridData.horizontalSpan = 1;
			gridData.verticalIndent = DEFAULT_VERTICAL_INDENT;
			ctrl_copyright.setLayoutData(gridData);
		}

		toolkit.paintBordersFor(generalComposite);

		addListeners();
		loadData();

		// Set focus on the name attribute
		Display display = editorComposite.getDisplay();
		if (!(display == null || display.isDisposed())) {
			display.asyncExec(new Runnable() {
				public void run() {
					ctrl_name.setFocus();
				}
			});
		}
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * Loads initial data from model
	 */
	private void loadData() {
		String name = library.getName();
		String brief_desc = library.getBriefDescription();

		ctrl_name.setText(TngUtil.checkNull(name));
		ctrl_brief_desc.setText(TngUtil.checkNull(brief_desc));
		textAuthors.setText(TngUtil.checkNull(library.getAuthors()));
		if (library.getChangeDate() != null) {
			ctrl_change_date.setText(TngUtil.checkNull(library.getChangeDate()
					.toLocaleString()));
		}
		ctrl_change_desc.setText(TngUtil.checkNull(library
				.getChangeDescription()));
		ctrl_version.setText(TngUtil.checkNull(library.getVersion()));
	}

	/**
	 * Add focus listeners
	 * 
	 */
	private void addListeners() {
		ctrl_name.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				library.setName(ctrl_name.getText());
			}
		});

		ctrl_brief_desc.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				library.setBriefDescription(ctrl_brief_desc.getText());
			}
		});

		textAuthors.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				library.setAuthors(textAuthors.getText());
			}
		});

		ctrl_change_desc.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				library.setChangeDescription(ctrl_change_desc.getText());
			}
		});

		ctrl_version.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				library.setVersion(ctrl_version.getText());
			}
		});

		ctrl_copyright.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
			}
		});

	}

}
