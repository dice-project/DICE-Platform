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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.dialogs.SectionsOrderDialog;
import org.eclipse.epf.authoring.ui.editors.AttributeTextBox;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor.ModifyListener;
import org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichTextEditor;
import org.eclipse.epf.authoring.ui.util.EditorsContextHelper;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.AddToSectionListCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MoveInSectionListCommand;
import org.eclipse.epf.library.edit.command.RemoveFromSectionList;
import org.eclipse.epf.library.edit.util.SectionList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.richtext.RichText;
import org.eclipse.epf.richtext.RichTextListener;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * The Steps page in the Task editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class TaskStepsPage extends BaseFormPage {

	private static final String FORM_PAGE_ID = "taskStepsPage"; //$NON-NLS-1$

	private Text ctrl_name;
	private AttributeTextBox nameTextBox;

	private Button ctrl_add, ctrl_delete, ctrl_up, ctrl_down, ctrl_ordering;

	private Table ctrl_steps;

	private TableViewer stepsTableViewer;

	private SectionList allSteps;

	protected IMethodRichText activeControl;

	protected boolean descExpandFlag = false;

	protected IMethodRichTextEditor ctrl_expanded;

	protected ImageHyperlink expandLink;

	protected Label expandLabel;

	protected Section formSection;

	protected Section generalSection;

	protected Composite sectionComposite;
	
	protected Composite generalComposite;

	protected Composite expandedComposite;

	protected IMethodRichText ctrl_maindesc;

	private IStructuredContentProvider stepsViewerContentProvider;

	private ILabelProvider stepsViewerLabelProvider;

	private Task task;

	private org.eclipse.epf.uma.Section currentStep;

	private ModifyListener contentModifyListener;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public TaskStepsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.STEPS_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.BaseFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		MethodElementEditorInput methodElementInput = (MethodElementEditorInput) input;
		task = (Task) methodElementInput.getMethodElement();
	}

	/**
	 * @see org.eclipse.ui.forms.editor.createFormContent(IManagedForm
	 *      managedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);

		form.setText(LibraryUIText.getUIText(task) + ": " + task.getName()); //$NON-NLS-1$

		formSection = toolkit.createSection(form.getBody(), Section.NO_TITLE);
		{
			TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
			formSection.setLayoutData(td);
			formSection.setLayout(new TableWrapLayout());
		}
		// Create the composite for the sections.
		sectionComposite = toolkit.createComposite(formSection, SWT.NONE);
		sectionComposite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		sectionComposite.setLayout(new TableWrapLayout());
		formSection.setClient(sectionComposite);

		// Create the General Information section.
		generalSection = toolkit.createSection(sectionComposite,
				Section.TITLE_BAR | Section.DESCRIPTION | Section.TWISTIE
						| Section.EXPANDED);
		generalSection
				.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		generalSection.setText(AuthoringUIText.STEPS_SECTION_NAME);
		generalSection.setDescription(AuthoringUIText.STEPS_SECTION_DESC);
		generalSection.setLayout(new GridLayout());

		generalComposite = toolkit.createComposite(generalSection);
		generalComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		generalComposite.setLayout(new GridLayout(4, false));
		generalSection.setClient(generalComposite);

		EditorsContextHelper.setHelp(generalComposite.getParent(), task);

		Composite pane1 = toolkit.createComposite(generalComposite);
		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.verticalSpan = 1;
			gridData.horizontalSpan = 3;
			pane1.setLayoutData(gridData);
			pane1.setLayout(new GridLayout());
		}

		Label l_name = toolkit.createLabel(pane1, AuthoringUIText.STEPS_TEXT);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 3;
			l_name.setLayoutData(gridData);
		}

		ctrl_steps = toolkit.createTable(pane1, SWT.MULTI);
		{
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 3;
			gridData.heightHint = 150;
			ctrl_steps.setLayoutData(gridData);
		}

		initProviders();

		stepsTableViewer = new TableViewer(ctrl_steps);
		stepsTableViewer.setContentProvider(stepsViewerContentProvider);
		stepsTableViewer.setLabelProvider(stepsViewerLabelProvider);

		Composite pane2 = toolkit.createComposite(generalComposite);
		pane2.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER
				| GridData.HORIZONTAL_ALIGN_CENTER));
		pane2.setLayout(new GridLayout());

		ctrl_add = toolkit.createButton(pane2, AuthoringUIText.ADD_BUTTON_TEXT,
				SWT.NONE);
		ctrl_add.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ctrl_delete = toolkit.createButton(pane2,
				AuthoringUIText.DELETE_BUTTON_TEXT, SWT.NONE);
		ctrl_delete.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ctrl_delete.setEnabled(false);

		ctrl_up = toolkit.createButton(pane2, AuthoringUIText.UP_BUTTON_TEXT,
				SWT.NONE);
		ctrl_up.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ctrl_up.setEnabled(false);

		ctrl_down = toolkit.createButton(pane2,
				AuthoringUIText.DOWN_BUTTON_TEXT, SWT.NONE);
		ctrl_down.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		ctrl_down.setEnabled(false);

		ctrl_ordering = toolkit.createButton(pane2,
				AuthoringUIText.ORDER_BUTTON_TEXT, SWT.NONE);
		ctrl_ordering.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();

		// name
		Label nameLabel = toolkit.createLabel(generalComposite,
				AuthoringUIText.STEP_NAME_TEXT);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 4;
			nameLabel.setLayoutData(gridData);
		}
		ctrl_name = toolkit.createText(generalComposite, "", SWT.SINGLE); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 4;
			ctrl_name.setLayoutData(gridData);
		}
		nameTextBox = AttributeTextBox.createAttributeTextBox(ctrl_name, null, UmaPackage.eINSTANCE.getNamedElement_Name(), false, actionMgr);

		createBlankLabel(toolkit, generalComposite, 4);

		ctrl_maindesc = createRichTextEditWithLink(
				toolkit, generalComposite, AuthoringUIText.DESCRIPTION_TEXT,
				250, 300, 4);

		createRichTextEditorSection();

		toolkit.paintBordersFor(pane1);
		toolkit.paintBordersFor(pane2);
		toolkit.paintBordersFor(generalComposite);
		toolkit.paintBordersFor(expandedComposite);


		addListeners();
		loadData();
		enableControls();
	}

	/**
	 * Add listeners to monitor focus and modification changes in the edit
	 * controls.
	 */
	public void addListeners() {
		final MethodElementEditor editor = (MethodElementEditor) getEditor();
		contentModifyListener = editor.createModifyListener(currentStep);
//		ctrl_name.addModifyListener(contentModifyListener);
		((MethodElementEditor.ModifyListener) contentModifyListener)
				.setDisable(true);

		form.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
//				IStatus status = TngUtil.checkEdit((EObject) task, getSite()
//						.getShell());
//				if (status.isOK()) {
//					loadData();
//				}
				if (form != null && !form.isDisposed()) {
					UIHelper.setFormText(form, contentElement);
				}
				loadData();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					ctrl_add.setEnabled(true);
					checkSelection();
				}
			}
		});

		form.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				deactivate();
			}
		});

		form.addControlListener(new ControlListener() {
			public void controlResized(ControlEvent e) {
				if (ctrl_expanded != null) {
					((GridData) ctrl_expanded.getLayoutData()).heightHint = getRichTextEditorHeight();
					((GridData) ctrl_expanded.getLayoutData()).widthHint = getRichTextEditorWidth();
				}
				formSection.layout(true, true);
			}

			public void controlMoved(ControlEvent e) {
			}
		});

//		ctrl_name.addModifyListener(contentModifyListener);
//		ctrl_name.addFocusListener(new FocusAdapter() {
//			public void focusLost(FocusEvent e) {
//				if (currentStep != null) {
//					String oldContent = currentStep.getName();
//					String newContent = ctrl_name.getText();
//					if (ctrl_name.getText() == null
//							|| ctrl_name.getText().length() == 0) {
//						String title = AuthoringUIResources.bind(AuthoringUIResources.renameDialog_title, LibraryUIText.TEXT_STEP); 
//						String msg = AuthoringUIResources.bind(AuthoringUIResources.emptyElementNameError_msg, StrUtil.toLower(LibraryUIText.TEXT_STEP)); 
//						AuthoringUIPlugin.getDefault().getMsgDialog()
//								.displayError(title, msg);
//						ctrl_name.setSelection(0, oldContent.length());
//					} else if (!newContent.equals(oldContent)) {
//						actionMgr.doAction(IActionManager.SET, currentStep,
//								UmaPackage.eINSTANCE.getNamedElement_Name(),
//								newContent, -1);
//					}
//				}
//			}
//		});

		ctrl_maindesc.addModifyListener(contentModifyListener);
		ctrl_maindesc.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				saveMainDescription();
			}
		});

		stepsTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						checkSelection();
					}
				});

		ctrl_add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.epf.uma.Section child = UmaFactory.eINSTANCE
						.createSection();
				String newName = MessageFormat.format(
						AuthoringUIText.NEW_ELEMENT_TEXT,
						new String[] { LibraryUIText.TEXT_STEP });
				child.setName(newName);
				AddToSectionListCommand cmd = new AddToSectionListCommand(task,
						child, allSteps);
				getActionManager().execute(cmd);
				stepsTableViewer.refresh();
				stepsTableViewer.setSelection(new StructuredSelection(child));
				ctrl_name.setFocus();
				ctrl_name.setSelection(0, newName.length());
			}
		});

		ctrl_delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();
				for (Iterator iter = selection.toList().iterator(); iter
						.hasNext();) {
					Object o = iter.next();
					if (o instanceof org.eclipse.epf.uma.Section) {
						RemoveFromSectionList cmd = new RemoveFromSectionList(
								task, (org.eclipse.epf.uma.Section) o, allSteps);
						getActionManager().execute(cmd);
					}
				}
				stepsTableViewer.refresh();
				editStep(null);
			}
		});

		ctrl_up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();

				ArrayList moveUpItems = new ArrayList();
				moveUpItems.addAll(selection.toList());
				MoveInSectionListCommand cmd = new MoveInSectionListCommand(
						task, moveUpItems, allSteps, 1);
				getActionManager().execute(cmd);

				stepsTableViewer.refresh();

				if (stepsTableViewer.getTable().getSelectionIndex() > 0
						&& !TngUtil.isLocked(task)) {
					ctrl_up.setEnabled(true);
				} else {
					ctrl_up.setEnabled(false);
				}
				if (stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
						.getTable().getItemCount() - 1
						&& !TngUtil.isLocked(task)) {
					ctrl_down.setEnabled(true);
				} else {
					ctrl_down.setEnabled(false);
				}
			}
		});

		ctrl_down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
						.getSelection();
				ArrayList moveDownItems = new ArrayList();
				moveDownItems.addAll(selection.toList());
				MoveInSectionListCommand cmd = new MoveInSectionListCommand(
						task, moveDownItems, allSteps, 0);
				getActionManager().execute(cmd);

				stepsTableViewer.refresh();

				if (stepsTableViewer.getTable().getSelectionIndex() > 0
						&& !TngUtil.isLocked(task)) {
					ctrl_up.setEnabled(true);
				} else {
					ctrl_up.setEnabled(false);
				}
				if (stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
						.getTable().getItemCount() - 1
						&& !TngUtil.isLocked(task)) {
					ctrl_down.setEnabled(true);
				} else {
					ctrl_down.setEnabled(false);
				}
			}
		});

		ctrl_ordering.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				SectionsOrderDialog dlg = new SectionsOrderDialog(
						Display.getCurrent().getActiveShell(),
						task,
						getActionManager(),
						AuthoringUIResources.StepsOrderDialog_title, 
						AuthoringUIResources.StepsOrderDialog_description, 
						AuthoringUIResources.steps_text); 
				boolean isDirty = getEditor().isDirty();
				int rtnValue = dlg.open();
				if(!isDirty && rtnValue == Dialog.CANCEL && getEditor().isDirty()){
					getEditor().doSave(null);
				}
			}
		});
	}

	protected void enableControls(boolean editable) {
		ctrl_name.setEditable(editable);
		ctrl_maindesc.setEditable(editable);
		ctrl_add.setEnabled(editable);
		ctrl_delete.setEnabled(editable);
		ctrl_up.setEnabled(editable);
		ctrl_down.setEnabled(editable);
		ctrl_ordering.setEnabled(true);
		if (ctrl_expanded != null) {
			ctrl_expanded.setEditable(editable);
		}
	}

	protected void deactivate() {
		// Save the step order.
		if (allSteps != null) {
			allSteps.apply();
		}
	}

	/**
	 * Initializes the controls with data from the model.
	 */
	private void loadData() {
		allSteps = null;
		stepsTableViewer.setInput(task);
		stepsTableViewer.refresh();
		editStep(currentStep);
	}

	/**
	 * Edit current step.
	 */
	private void editStep(org.eclipse.epf.uma.Section step) {
		((MethodElementEditor.ModifyListener) contentModifyListener)
				.setDisable(true);

		if (ctrl_maindesc.getModified()) {
			saveMainDescription();
		}

		currentStep = step;
		String name = ""; //$NON-NLS-1$
		String desc = ""; //$NON-NLS-1$
		if (currentStep != null) {
			name = currentStep.getName();
			desc = currentStep.getSectionDescription();
			ctrl_name.setFocus();
		}
//		ctrl_name.setText(name == null ? "" : name); //$NON-NLS-1$
//		System.out.println("TaskStepsPage.editStep(): name=" + name);
		nameTextBox.setElement(currentStep);
		
		ctrl_maindesc.setText(desc == null ? "" : desc); //$NON-NLS-1$
		ctrl_maindesc.setModalObject(currentStep);
		ctrl_maindesc.setModalObjectFeature(UmaPackage.eINSTANCE
				.getSection_SectionDescription());
		if (step == null) {
			// close any Find/Replace dialog open in the RTE
			ctrl_maindesc.getFindReplaceAction().dispose();
		}

		((MethodElementEditor.ModifyListener) contentModifyListener)
				.setElement(currentStep);
		((MethodElementEditor.ModifyListener) contentModifyListener)
				.setDisable(currentStep == null);

		enableControls();
	}

	/**
	 * Enable name and description controls
	 */
	private void enableControls() {
		if (task.getPresentation().getSections().contains(currentStep)) {
			if (!TngUtil.isLocked(contentElement)) {
				ctrl_name.setEditable(true);
				ctrl_maindesc.setEditable(true);
			}
		} else {
			ctrl_name.setEditable(false);
			ctrl_maindesc.setEditable(false);
		}
	}

	/**
	 * Dispose listener
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	public void dispose() {
	}

	private void createRichTextEditorSection() {
		expandedComposite = toolkit.createComposite(formSection, SWT.NONE);
		expandedComposite.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		expandedComposite.setLayout(new GridLayout(2, false));
		expandedComposite.setVisible(false);


		// Hyperlink desc
		expandLink = toolkit.createImageHyperlink(expandedComposite, SWT.NONE);
		expandLink.setImage(AuthoringUIImages.IMG_EXPANDED);
		expandLink.setToolTipText(AuthoringUIResources.closeRTE); 
		expandLink.setUnderlined(false);
		expandLink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				toggle(e);
			}
		});

		expandLabel = createDecoratedLabel(toolkit, expandedComposite, ""); //$NON-NLS-1$
	}

	/**
	 * Toggle Description control to expand and control state.
	 */
	protected void toggle(HyperlinkEvent e) {
		if (currentStep == null)
			return;
		if (ctrl_expanded == null) {
			ctrl_expanded = createRichTextEditor(toolkit, expandedComposite,
					SWT.MULTI | SWT.WRAP | SWT.V_SCROLL, GridData.FILL_BOTH,
					getRichTextEditorHeight(), getRichTextEditorWidth(), 2, expandLabel);
			ctrl_expanded.addModifyListener(contentModifyListener);
		}

		if (descExpandFlag) {
			ctrl_expanded.collapse();
			sectionComposite.setVisible(true);
			expandedComposite.setVisible(false);
			formSection.setClient(sectionComposite);
			IMethodRichText richText = activeControl;
			richText.setText(ctrl_expanded.getText());
			for (Iterator i = richText.getListeners(); i.hasNext();) {
				RichTextListener listener = (RichTextListener) i.next();
				ctrl_expanded.removeListener(listener.getEventType(), listener
						.getListener());
			}
			if (ctrl_expanded.getModified()) {
				((MethodElementEditor) getEditor())
						.saveModifiedRichText(ctrl_expanded);
			}
			richText.setFocus();
		} else {
			sectionComposite.setVisible(false);
			expandedComposite.setVisible(true);
			formSection.setClient(expandedComposite);
			expandLabel.setText(currentStep.getName() +
					" - " + (String) ((ImageHyperlink) e.getSource()).getData("Title")); //$NON-NLS-1$ //$NON-NLS-2$
			IMethodRichText richText = (IMethodRichText) e.getHref();
			ctrl_expanded.setInitialText(richText.getText());
			ctrl_expanded.setModalObject(richText.getModalObject());
			ctrl_expanded.setModalObjectFeature(richText
					.getModalObjectFeature());
			ctrl_expanded.setFindReplaceAction(richText.getFindReplaceAction());
			for (Iterator i = richText.getListeners(); i.hasNext();) {
				RichTextListener listener = (RichTextListener) i.next();
				ctrl_expanded.addListener(listener.getEventType(), listener
						.getListener());
			}
			boolean editable = !TngUtil.isLocked(contentElement);
			ctrl_expanded.setEditable(editable);
			if (editable) {
				ctrl_expanded.setFocus();
			}
			activeControl = richText;
		}

		form.getBody().layout(true, true);
		descExpandFlag = !descExpandFlag;
	}

	private void initProviders() {
		stepsViewerContentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (allSteps == null) {
					allSteps = new SectionList(task,
							SectionList.STEPS_FOR_ELEMENT_ONLY);
				}
				return allSteps.toArray();
			}
		};

		stepsViewerLabelProvider = new VariabilityElementLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public boolean isExternal(Object element) {
				return !task.getPresentation().getSections().contains(element);
			}
		};
	}

	private void saveMainDescription() {
		if (currentStep != null) {
			IMethodRichText control = descExpandFlag ? ctrl_expanded
					: ctrl_maindesc;
			if (!control.getModified()) {
				return;
			}
			String oldContent = currentStep.getSectionDescription();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(control,
					oldContent)) {
				return;
			}
			String newContent = control.getText();
			if (control instanceof RichText) {
//				newContent = ((RichText) control).getCurrentRawText();
				RichText rt = (RichText) control;
				oldContent = rt.tidyText(oldContent);
//				oldContent = rt.formatText(oldContent);
				
				newContent = rt.tidyText(newContent);
//				newContent = rt.formatText(newContent);
			}
			if (!newContent.equals(oldContent)) {
				actionMgr.doAction(IActionManager.SET, currentStep,
						UmaPackage.eINSTANCE.getSection_SectionDescription(),
						newContent, -1);
			}
		}
	}

	protected IActionManager getActionManager() {
		FormEditor editor = getEditor();
		if (editor instanceof MethodElementEditor) {
			return ((MethodElementEditor) editor).getActionManager();
		}
		return null;
	}

	private void checkSelection() {
		IStructuredSelection selection = (IStructuredSelection) stepsTableViewer
				.getSelection();
		if (selection.size() > 0 && !TngUtil.isLocked(task)) {
			ctrl_delete.setEnabled(true);

			if (stepsTableViewer.getTable().getSelectionIndex() > 0
					&& !TngUtil.isLocked(task) && selection.size() == 1) {
				ctrl_up.setEnabled(true);
			} else {
				ctrl_up.setEnabled(false);
			}

			if (stepsTableViewer.getTable().getSelectionIndex() < stepsTableViewer
					.getTable().getItemCount() - 1
					&& !TngUtil.isLocked(task) && selection.size() == 1) {
				ctrl_down.setEnabled(true);
			} else {
				ctrl_down.setEnabled(false);
			}
		}
		if (selection.size() == 1) {
			editStep((org.eclipse.epf.uma.Section) selection.getFirstElement());
		}
	}
}