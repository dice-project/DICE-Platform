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
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.VariabilityBaseElementFilter;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The Description page for the Role editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class RoleDescriptionPage extends DescriptionFormPage {

	private static final String FORM_PAGE_ID = "roleDescriptionPage"; //$NON-NLS-1$

	private static final String BASE_ROLE = AuthoringUIText.BASE_ELEMENT_TEXT;

	private IMethodRichText ctrl_skills;

	private IMethodRichText ctrl_assign;

	private IMethodRichText ctrl_synonyms;

	private Role role;

	/**
	 * Creates a new instance.
	 */
	public RoleDescriptionPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		role = (Role) contentElement;
		notationSectionOn = true;
		setExternalIDOn(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#createEditorContent(FormToolkit)
	 */
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
		label_base.setText(BASE_ROLE);
	}

	/**
	 * Creates the Staffing section.
	 */
	protected void createNotationSectionContent() {
		super.createNotationSectionContent();
		notationSection.setText(AuthoringUIText.STAFFING_SECTION_NAME);
		notationSection.setDescription(AuthoringUIText.STAFFING_SECTION_DESC);
		ctrl_skills = createRichTextEditWithLinkForSection(toolkit,
				notationComposite, AuthoringUIText.SKILLS_TEXT, 40, 400,
				NOTATION_SECTION_ID);
		ctrl_assign = createRichTextEditWithLinkForSection(toolkit,
				notationComposite, AuthoringUIText.ASSIGNMENT_APPROACHES_TEXT,
				40, 400, NOTATION_SECTION_ID);
		ctrl_synonyms = createRichTextEditWithLinkForSection(toolkit,
				notationComposite, AuthoringUIText.SYNONYMS_TEXT, 40, 400,
				NOTATION_SECTION_ID);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();

		final MethodElementEditor editor = (MethodElementEditor) getEditor();

		if (notationSectionOn) {
			ctrl_skills.setModalObject(contentElement.getPresentation());
			ctrl_skills.setModalObjectFeature(UmaPackage.eINSTANCE
					.getRoleDescription_Skills());
			ctrl_skills.addModifyListener(contentModifyListener);
			ctrl_skills.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_skills;
					if (!control.getModified()) {
						return;
					}
					String oldContent = ((org.eclipse.epf.uma.RoleDescription) role
							.getPresentation()).getSkills();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = editor.getActionManager().doAction(
								IActionManager.SET,
								contentElement.getPresentation(),
								UmaPackage.eINSTANCE.getRoleDescription_Skills(),
								newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});

			ctrl_assign.setModalObject(contentElement.getPresentation());
			ctrl_assign.setModalObjectFeature(UmaPackage.eINSTANCE
					.getRoleDescription_AssignmentApproaches());
			ctrl_assign.addModifyListener(contentModifyListener);
			ctrl_assign.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_assign;
					if (!control.getModified()) {
						return;
					}
					String oldContent = ((org.eclipse.epf.uma.RoleDescription) role
							.getPresentation()).getAssignmentApproaches();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = editor.getActionManager().doAction(
								IActionManager.SET,
								contentElement.getPresentation(),
								UmaPackage.eINSTANCE
										.getRoleDescription_AssignmentApproaches(),
								newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});

			ctrl_synonyms.setModalObject(contentElement.getPresentation());
			ctrl_synonyms.setModalObjectFeature(UmaPackage.eINSTANCE
					.getRoleDescription_Synonyms());
			ctrl_synonyms.addModifyListener(contentModifyListener);
			ctrl_synonyms.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_synonyms;
					if (!control.getModified()) {
						return;
					}
					String oldContent = ((org.eclipse.epf.uma.RoleDescription) role
							.getPresentation()).getSynonyms();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = editor.getActionManager().doAction(
								IActionManager.SET,
								contentElement.getPresentation(),
								UmaPackage.eINSTANCE.getRoleDescription_Synonyms(),
								newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#refresh(boolean)
	 */
	protected void refresh(boolean editable) {
		super.refresh(editable);
		
		if (notationSectionOn) {
			ctrl_skills.setEditable(editable);
			ctrl_assign.setEditable(editable);
			ctrl_synonyms.setEditable(editable);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadData()
	 */
	protected void loadData() {
		super.loadData();

		if (notationSectionOn) {
			String skills = ((org.eclipse.epf.uma.RoleDescription) role.getPresentation())
					.getSkills();
			String assign = ((org.eclipse.epf.uma.RoleDescription) role.getPresentation())
					.getAssignmentApproaches();
			String synonyms = ((org.eclipse.epf.uma.RoleDescription) role.getPresentation())
					.getSynonyms();
	
			ctrl_skills.setText(skills == null ? "" : skills); //$NON-NLS-1$
			ctrl_assign.setText(assign == null ? "" : assign); //$NON-NLS-1$
			ctrl_synonyms.setText(synonyms == null ? "" : synonyms); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return role;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.ROLES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof Role);
			}
		};

		// Set additional filter for variability base element checking.
		((ContentFilter) filter)
				.setAdditionalFilters(new IFilter[] { new VariabilityBaseElementFilter(
						role) });
		return filter;
	}
	
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		this.generalSectionDescription = AuthoringUIResources.role_generalInfoSection_desc;
		this.detailSectionDescription = AuthoringUIResources.role_detailSection_desc;
		this.variabilitySectionDescription = AuthoringUIResources.role_variabilitySection_desc;
		this.versionSectionDescription = AuthoringUIResources.role_versionInfoSection_desc;
	}

}