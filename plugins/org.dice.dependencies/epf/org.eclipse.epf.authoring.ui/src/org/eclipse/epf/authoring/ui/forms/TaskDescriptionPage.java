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
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The Description page for the Task editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class TaskDescriptionPage extends DescriptionFormPage {

	private static final String FORM_PAGE_ID = "taskDescriptionPage"; //$NON-NLS-1$		

	private static final String BASE_TASK = AuthoringUIText.BASE_ELEMENT_TEXT;

	private IMethodRichText ctrl_alternatives;

	private Task task;

	/**
	 * Creates a new instance.
	 */
	public TaskDescriptionPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		task = (Task) contentElement;
		purposeOn = true;
		setExternalIDOn(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#createEditorContent(FormToolkit)
	 */
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
		ctrl_alternatives = createRichTextEditWithLinkForSection(toolkit,
				detailComposite, AuthoringUIText.ALTERNATIVES_TEXT, 40, 400,
				DETAIL_SECTION_ID);
		label_base.setText(BASE_TASK);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadData()
	 */
	protected void loadData() {
		super.loadData();
		String purpose = ((org.eclipse.epf.uma.TaskDescription) task.getPresentation())
				.getPurpose();
		String alternatives = ((org.eclipse.epf.uma.TaskDescription) task
				.getPresentation()).getAlternatives();
		ctrl_purpose.setText(purpose == null ? "" : purpose); //$NON-NLS-1$	
		ctrl_alternatives.setText(alternatives == null ? "" : alternatives); //$NON-NLS-1$	
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();
		final MethodElementEditor editor = (MethodElementEditor) getEditor();

		ctrl_purpose.setModalObject(contentElement.getPresentation());
		ctrl_purpose.setModalObjectFeature(UmaPackage.eINSTANCE
				.getTaskDescription_Purpose());
		ctrl_purpose.addModifyListener(contentModifyListener);
		ctrl_purpose.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				IMethodRichText control = descExpandFlag ? ctrl_expanded
						: ctrl_purpose;
				if (!control.getModified()) {
					return;
				}
				String oldContent = ((org.eclipse.epf.uma.TaskDescription) task
						.getPresentation()).getPurpose();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						control, oldContent)) {
					return;
				}
				String newContent = control.getText();
				if (!newContent.equals(oldContent)) {
					boolean success = editor.getActionManager().doAction(
							IActionManager.SET,
							contentElement.getPresentation(),
							UmaPackage.eINSTANCE.getTaskDescription_Purpose(),
							newContent, -1);
					if (success && isVersionSectionOn()) {
						updateChangeDate();
					}
				}
			}
		});

		ctrl_alternatives.setModalObject(contentElement.getPresentation());
		ctrl_alternatives.setModalObjectFeature(UmaPackage.eINSTANCE
				.getTaskDescription_Alternatives());
		ctrl_alternatives.addModifyListener(contentModifyListener);
		ctrl_alternatives.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				IMethodRichText control = descExpandFlag ? ctrl_expanded
						: ctrl_alternatives;
				if (!control.getModified()) {
					return;
				}
				String oldContent = ((org.eclipse.epf.uma.TaskDescription) task
						.getPresentation()).getAlternatives();
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
									.getTaskDescription_Alternatives(),
							newContent, -1);
					if (success && isVersionSectionOn()) {
						updateChangeDate();
					}
				}
			}
		});
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#refresh(boolean)
	 */
	protected void refresh(boolean editable) {
		super.refresh(editable);
		ctrl_alternatives.setEditable(editable);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return task;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.TASKS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof Task);
			}
		};

		// Set additional filter for variability base element checking.
		((ContentFilter) filter)
				.setAdditionalFilters(new IFilter[] { new VariabilityBaseElementFilter(
						task) });
		return filter;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		this.generalSectionDescription = AuthoringUIResources.task_generalInfoSection_desc;
		this.detailSectionDescription = AuthoringUIResources.task_detailSection_desc;
		this.variabilitySectionDescription = AuthoringUIResources.task_variabilitySection_desc;
		this.versionSectionDescription = AuthoringUIResources.task_versionInfoSection_desc;
	}

}
