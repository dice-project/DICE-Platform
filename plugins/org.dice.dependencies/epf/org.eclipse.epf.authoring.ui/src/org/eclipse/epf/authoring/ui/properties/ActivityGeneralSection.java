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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.filters.ActivityVariabilityFilter;
import org.eclipse.epf.authoring.ui.forms.ProcessBreakdownStructureFormPage;
import org.eclipse.epf.authoring.ui.views.ProcessViewer;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.providers.SharedResourceDiagramDocumentProvider;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ProcessVariabilityConfigurator;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.ActivityVariabilityCommand;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.util.WbePropUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.library.ui.dialogs.ConvertActivityDialog;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * The general tab section for Activity
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 * 
 */
public class ActivityGeneralSection extends WorkBreakdownElementGeneralSection {
	protected Activity element;

	private static final String NOT_APPLICABLE_TEXT = AuthoringUIResources.notApplicable_text; 

	private static final String CONTRIBUTES_TEXT = AuthoringUIResources.contributes_text; 

	private static final String EXTENDS_TEXT = AuthoringUIResources.extends_text; 

	private static final String REPLACES_TEXT = AuthoringUIResources.replaces_text; 

	private Text modelInfoText;

	private Label activityTypeLabel;

	private Text ctrl_type_text;

	private Button ctrl_type_button;

	private Combo ctrl_variability;

	private ComboViewer viewer_variability;

	private Text baseText;

	private Button selectButton;
	
	private Text orderText;

	protected ILabelProvider variabilityLabelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getText(Object object) {
			VariabilityType varObject = (VariabilityType) object;
			if (varObject == VariabilityType.NA)
				return NOT_APPLICABLE_TEXT;
			if (varObject == VariabilityType.CONTRIBUTES)
				return CONTRIBUTES_TEXT;
			if (varObject == VariabilityType.EXTENDS)
				return EXTENDS_TEXT;
			if (varObject == VariabilityType.REPLACES)
				return REPLACES_TEXT;
			return null;
		}
	};

	private IContentProvider variabilityContentProvider = new AdapterFactoryContentProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public Object[] getElements(Object object) {
			List variabilityTypesList = new ArrayList();
			variabilityTypesList.add(VariabilityType.NA);
			variabilityTypesList.add(VariabilityType.CONTRIBUTES);
			variabilityTypesList.add(VariabilityType.EXTENDS);
			variabilityTypesList.add(VariabilityType.REPLACES);
			return variabilityTypesList.toArray();
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#init()
	 */
	protected void init() {
		super.init();
		// get activity object
		element = (Activity) getElement();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#createGeneralSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGeneralSection(Composite composite) {
		super.createGeneralSection(composite);

		// Model Info
		FormUI.createLabel(toolkit, generalComposite,
				PropertiesResources.Activity_ModelInfo); 

		modelInfoText = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, horizontalSpan);
//		modelInfoText.setEnabled(false);
		modelInfoText.setEditable(false);
		modelInfoText.setText(getModelInfo());
		modelInfoText.setForeground(ColorConstants.gray);

		// Type of an Activity
		activityTypeLabel = FormUI.createLabel(toolkit, generalComposite,
				PropertiesResources.Activity_Type);
		
		ctrl_type_text = toolkit.createText(generalComposite, "", SWT.READ_ONLY); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.BEGINNING | GridData.FILL_HORIZONTAL);
			gridData.horizontalSpan = 1;
			ctrl_type_text.setLayoutData(gridData);
		}
		ctrl_type_button = toolkit.createButton(generalComposite,
				AuthoringUIText.CHANGE_TYPE_BUTTON_TEXT, SWT.PUSH);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 1;
			ctrl_type_button.setLayoutData(gridData);
		}
		if (ConvertActivityDialog.getValidNewActivityTypes(element) == null) {
			ctrl_type_button.setVisible(false);
		}

		FormUI.createLabel(toolkit, generalComposite, ""); //$NON-NLS-1$

		// Variability Type
		FormUI.createLabel(toolkit, generalComposite,
				AuthoringUIText.VARIABILITY_TYPE_TEXT);

		ctrl_variability = FormUI.createCombo(toolkit, generalComposite, 2);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 1;
			ctrl_variability.setLayoutData(gridData);
		}

		viewer_variability = new ComboViewer(ctrl_variability);
		viewer_variability.getCombo().setLayoutData(new GridData(1));
		viewer_variability.setContentProvider(variabilityContentProvider);
		viewer_variability.setLabelProvider(variabilityLabelProvider);
		viewer_variability.setInput(element);

		Label blankLabel = FormUI.createLabel(toolkit, generalComposite, ""); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 2;
			blankLabel.setLayoutData(gridData);
		}

		// Base elements
		FormUI.createLabel(toolkit, generalComposite,
				AuthoringUIText.BASE_ELEMENT_TEXT);

		baseText = FormUI.createText(toolkit, generalComposite);
		{
			GridData gd = new GridData(GridData.BEGINNING
					| GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			baseText.setLayoutData(gd);
		}
		baseText.setEnabled(false);

		// Button to select
		selectButton = FormUI.createButton(toolkit, generalComposite,
				AuthoringUIText.SELECT_BUTTON_TEXT);
		
		// Variability Type
		FormUI.createLabel(toolkit, generalComposite,
				AuthoringUIResources.contribution_order_text);


		orderText = FormUI.createText(toolkit, generalComposite);
		if (getElement() instanceof Activity) {
			String text = PropUtil.getPropUtil().getContributionOrder((Activity) getElement());
			if (text == null) {
				text = "";  //$NON-NLS-1$
			}
			orderText.setText(text);
		}		
		orderText.addModifyListener(getOrderTextModifyListener());			
		Label blankLabel1 = FormUI.createLabel(toolkit, generalComposite, ""); //$NON-NLS-1$
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 1;
			blankLabel1.setLayoutData(gridData);
		}
		
	}
	
	private ModifyListener getOrderTextModifyListener() {
		final PropUtil propUtil = PropUtil.getPropUtil(actionMgr);
		ModifyListener orderTextModifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String newText = orderText.getText().trim();
				String oldText = propUtil
						.getContributionOrder(getElement());
				if (oldText == null) {
					oldText = "";//$NON-NLS-1$
				}
				if (!newText.equals(oldText)) {
					propUtil.setContributionOrder((Activity) getElement(),
							newText);
				}
			}
		};
		return orderTextModifyListener;
	}
	
	private List<AbstractDiagramEditor> getDirtyDiagramEditors() {
		IWorkbenchPage workbenchPage = AuthoringUIPlugin.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editorReferences = workbenchPage
				.getEditorReferences();
		org.eclipse.epf.uma.Process process = TngUtil.getOwningProcess(element); 
		ArrayList<AbstractDiagramEditor> dirtyEditors = new ArrayList<AbstractDiagramEditor>();
		for (int i = 0; i < editorReferences.length; i++) {
			IEditorReference reference = editorReferences[i];
			IEditorPart editor = reference.getEditor(true);
			if (editor instanceof AbstractDiagramEditor && editor.isDirty()) {
				IEditorInput input = editor.getEditorInput();
				if (input instanceof DiagramEditorInputProxy) {
					DiagramEditorInput diagramInput = ((DiagramEditorInputProxy) input).getDiagramEditorInput();
					if (diagramInput != null) {
						MethodElement element = diagramInput.getMethodElement();
						if(element instanceof BreakdownElement && process == TngUtil.getOwningProcess((BreakdownElement)element)) {
							dirtyEditors.add((AbstractDiagramEditor) editor);
						}
					}
				}
			}
		}
		return dirtyEditors;
	}

	/**
	 * add listeners
	 */
	protected void addListeners() {
		super.addListeners();
		ctrl_type_button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				final ProcessEditor editor = (ProcessEditor) getEditor();
				// allow changing type only if the process editor and all of its diagram editors are not dirty
				//
				if (editor.isDirty() || !getDirtyDiagramEditors().isEmpty()) {
					String title = AuthoringUIResources.changeActivityTypeWarningDialog_title; 
					String message = AuthoringUIResources.descriptionTabGuidanceWarningDialog_message1; 
					AuthoringUIPlugin.getDefault().getMsgDialog()
							.displayWarning(title, message);
				} else  {
					nameText.removeListener(SWT.Deactivate,
							nameDeactivateListener);
					presentationNameText.removeListener(SWT.Deactivate,
							presentationNameDeactivateListener);

					Activity newActivity = ConvertActivityDialog
					.queryUserAndConvert((Activity) element, null, null);
					
					if (newActivity != null) {
						IStructuredSelection sel = new StructuredSelection(
								newActivity);
						// deal with property sheet directly.
						getPropertySheetPage().selectionChanged(getPart(),
								sel);
						// getEditor().setSelection(sel);
						refresh();
						((ProcessViewer) ((ProcessBreakdownStructureFormPage) getEditor()
								.getActivePageInstance()).getViewer())
								.expandToLevel(newActivity, 3);
					}
					nameText.addListener(SWT.Deactivate,
							nameDeactivateListener);
					presentationNameText.addListener(SWT.Deactivate,
							presentationNameDeactivateListener);

					Display.getCurrent().asyncExec(new Runnable() {

						public void run() {
							// refresh any dirty diagram editor and clear the dirty flag
							//
							List<AbstractDiagramEditor> dirtyDiagramEditors = getDirtyDiagramEditors();
							for (AbstractDiagramEditor diagramEditor : dirtyDiagramEditors) {
								diagramEditor.refresh();
								IDocumentProvider docProvider = diagramEditor.getDocumentProvider();
								if(docProvider instanceof SharedResourceDiagramDocumentProvider) {
									((SharedResourceDiagramDocumentProvider)docProvider).markDocumentAsSaved((IFileEditorInput) diagramEditor.getEditorInput());
								}			
							}							
						}
						
					});
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		ctrl_variability.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer_variability
						.getSelection();

				VariabilityElement ve = element.getVariabilityBasedOnElement();
				VariabilityType type = (VariabilityType) selection
						.getFirstElement();

				if (ve != null & ve instanceof Activity) {
//					if(type.equals(VariabilityType.REPLACES_LITERAL)
//							&& ve instanceof Process){
//						Object[] args = { element.getName() };
//						String message = AuthoringUIResources
//						.bind(
//								AuthoringUIResources.activity_variability_error_msg1,
//								args);
//
//						String title = AuthoringUIResources.activity_variability_error_title;
//						AuthoringUIPlugin.getDefault().getMsgDialog()
//							.displayError(title, message, ""); //$NON-NLS-1$
//						VariabilityType vtype = element.getVariabilityType();
//						if(vtype != null){
//							IStructuredSelection sel = new StructuredSelection(vtype);
//							viewer_variability.setSelection(sel);
//						}
//						return;
//					}
//					if (ProcessUtil.hasContributorOrReplacer((Activity) ve)
//							&& type.equals(VariabilityType.EXTENDS_LITERAL)) {
//						Object[] args = { element.getName(),
//								((Activity) ve).getName() };
//						String message = AuthoringUIResources
//								.bind(
//										AuthoringUIResources.activity_variability_error_msg,
//										args);
//
//						String title = AuthoringUIResources.activity_variability_error_title;
//						AuthoringUIPlugin.getDefault().getMsgDialog()
//								.displayError(title, message, ""); //$NON-NLS-1$
//
//						VariabilityType variabilityType = element
//								.getVariabilityType();
//						IStructuredSelection newVariabilitySelection = new StructuredSelection(
//								variabilityType);
//						viewer_variability.setSelection(
//								newVariabilitySelection, true);
//
//						return;
//					}
					
					if(type != VariabilityType.NA) {
						IStatus state = DependencyChecker.checkCircularDependencyAfterFilterSelection(element, (Activity) ve, type);
						if(!state.isOK()) {
							String title = AuthoringUIResources.activity_variability_error_title;
							AuthoringUIPlugin.getDefault().getMsgDialog().displayError(title, state.getMessage());
							
							// set variability to back whatever it was for UI
							VariabilityType variabilityType = element.getVariabilityType();
							IStructuredSelection newVariabilitySelection = new StructuredSelection(
									variabilityType);
							viewer_variability.setSelection(newVariabilitySelection, true);
							return;
						}
					}
					boolean status = actionMgr.doAction(IActionManager.SET,
							element, UmaPackage.eINSTANCE
									.getVariabilityElement_VariabilityType(),
							type, -1);
					if (!status) {
						if (element.getPresentationName() == null
								|| element.getPresentationName().equals("")) { //$NON-NLS-1$
							if (type.equals(VariabilityType.NA))
								element.setPresentationName(element.getName());
						}
						return;
					}
					if (element.getPresentationName() == null
							|| element.getPresentationName().equals("")) { //$NON-NLS-1$
						if (type.equals(VariabilityType.CONTRIBUTES)
								|| type.equals(VariabilityType.REPLACES)
								|| type.equals(VariabilityType.NA))
							element.setPresentationName(element.getName());
					}
				}

				if (type == VariabilityType.NA) {
					if (ve != null) {
						boolean status = actionMgr
								.doAction(
										IActionManager.SET,
										element,
										UmaPackage.eINSTANCE
												.getVariabilityElement_VariabilityBasedOnElement(),
										null, -1);

						if (!status) {
							return;
						}
					}
					selectButton.setEnabled(false);
					baseText.setText(""); //$NON-NLS-1$
				} else {
					List selectionBaseList = new ArrayList();
					VariabilityElement base = element
							.getVariabilityBasedOnElement();
					selectionBaseList.add(base);
					selectButton.setEnabled(true);

				}
				// setFormTextWithVariableInfo();
			}
		});

		selectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MethodConfiguration config = getConfiguration();
				if (config instanceof Scope) {
					config = LibraryService.getInstance().getCurrentMethodConfiguration();
				}
				String tabName = FilterConstants.ACTIVITIES;

				VariabilityType type = (VariabilityType) ((IStructuredSelection) viewer_variability
						.getSelection()).getFirstElement();

				IFilter filter = new ActivityVariabilityFilter(config, null,
						tabName, element);

				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, tabName, null, config);
				fd.setBlockOnOpen(true);
				fd.setViewerSelectionSingle(true);
				fd.setTitle(tabName);
				fd.open();

				List selectedItems = fd.getSelectedItems();

				if (selectedItems != null && !selectedItems.isEmpty()) {
					Object o = selectedItems.get(0);
					if (o instanceof Activity) {
//						Activity act = (Activity) o;
//						if (isDirectParent(act)
//								|| ((act instanceof Process) && type.equals(VariabilityType.REPLACES_LITERAL)
//										&& !(element instanceof Process))) 
//						{
//							Object[] args = { element.getName() };
//							String message = AuthoringUIResources
//									.bind(
//											AuthoringUIResources.activity_variability_error_msg1,
//											args);
//
//							String title = AuthoringUIResources.activity_variability_error_title;
//							AuthoringUIPlugin.getDefault().getMsgDialog()
//									.displayError(title, message, ""); //$NON-NLS-1$
//
//							return;
//						}
//						if (isCircularDependency(act)) {
//							Object[] args = { element.getName(), act.getName() };
//							String message = AuthoringUIResources
//									.bind(
//											AuthoringUIResources.activity_variability_error_msg2,
//											args);
//
//							String title = AuthoringUIResources.activity_variability_error_title;
//							AuthoringUIPlugin.getDefault().getMsgDialog()
//									.displayError(title, message, ""); //$NON-NLS-1$
//
//							return;
//						}
//						// block for children
//						if (ProcessUtil.hasContributorOrReplacer(act)
//								&& type.equals(VariabilityType.EXTENDS_LITERAL)) {
//							Object[] args = { element.getName(), act.getName() };
//							String message = AuthoringUIResources
//									.bind(
//											AuthoringUIResources.activity_variability_error_msg,
//											args);
//
//							String title = AuthoringUIResources.activity_variability_error_title;
//							AuthoringUIPlugin.getDefault().getMsgDialog()
//									.displayError(title, message, ""); //$NON-NLS-1$
//
//							return;
//						}
						
						IStatus status = DependencyChecker.checkCircularDependencyAfterFilterSelection(element, (Activity) o, type);
						if(!status.isOK()) {
							String title = AuthoringUIResources.activity_variability_error_title;
							AuthoringUIPlugin.getDefault().getMsgDialog().displayError(title, status.getMessage());						
							return;
						}
					}
					if (o instanceof VariabilityElement) {
						VariabilityElement ve = (VariabilityElement) o;

						IConfigurator configurator = new ProcessVariabilityConfigurator(
								getDefaultConfiguration());
						ActivityVariabilityCommand cmd = new ActivityVariabilityCommand(
								element, ve, type, configurator);
						boolean status = actionMgr.execute(cmd);

						if (!status) {
							return;
						}

						baseText.setText(TngUtil.getLabelWithPath(ve));
					}
				}
			}
		});

	}

	/**
	 * Get model information for the activity
	 * @return
	 * 			model info string
	 */
	private String getModelInfo() {
		String info = null;

		try {
			Object o = getAdapter();
			if (o instanceof IBSItemProvider) {
				IBSItemProvider adapter = (IBSItemProvider) o;
				info = adapter.getAttribute(element,
						IBSItemProvider.COL_MODEL_INFO);
			}
		} catch (Exception e) {
			// print exception
		}
		if (!(StrUtil.isBlank(info)))
			return info;
		else
			return PropertiesResources.Process_None;
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();

		ctrl_type_button.setEnabled(editable);
		ctrl_variability.setEnabled(editable);
		selectButton.setEnabled(editable);

		ctrl_variability.setEnabled(editable);
		if (((IStructuredSelection) viewer_variability.getSelection())
				.getFirstElement() == VariabilityType.NA) {
			selectButton.setEnabled(false);
		}
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof Activity) {
				super.refresh();

				element = (Activity) getElement();

				String text = PropUtil.getPropUtil()
						.getContributionOrder(element);
				if (text == null) {
					text = ""; //$NON-NLS-1$
				}
				if (! text.equals(orderText.getText())) {
					orderText.setText(text);
				}
				
				modelInfoText.setText(getModelInfo());

				ctrl_type_text.setText(PropertiesUtil.getType(element));

				// hide/show combo
				if ((element instanceof org.eclipse.epf.uma.Process)
						&& (element.getSuperActivities() == null || element
								.getSuperActivities() == null)) {
					activityTypeLabel.setVisible(false);
					ctrl_type_text.setVisible(false);
					ctrl_type_button.setVisible(false);
				} else {
					activityTypeLabel.setVisible(true);
					ctrl_type_text.setVisible(true);
					ctrl_type_button.setVisible(true);
				}

				VariabilityType variabilityType = element.getVariabilityType();
				IStructuredSelection newVariabilitySelection = new StructuredSelection(
						variabilityType);
				viewer_variability.setSelection(newVariabilitySelection, true);

				if (element.getVariabilityBasedOnElement() != null)
					baseText.setText(TngUtil.getLabelWithPath(element
							.getVariabilityBasedOnElement()));
				else
					baseText.setText(""); //$NON-NLS-1$
				
				// make variability type combo disabled incase type is either local contributes or local replaces.
				if (variabilityType
						.equals(VariabilityType.LOCAL_CONTRIBUTION)
						|| (variabilityType
								.equals(VariabilityType.LOCAL_REPLACEMENT))) {
					IStructuredSelection selection = new StructuredSelection(
							VariabilityType.NA);
					viewer_variability.setSelection(selection);
					ctrl_variability.setEnabled(false);
				} else
					ctrl_variability.setEnabled(editable);
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing Activity general section : " + element, ex); //$NON-NLS-1$
		}
	}

	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.WorkBreakdownElementGeneralSection#getNamePrefix()
	 */
	public String getNamePrefix() {
		if (element instanceof CapabilityPattern) {
			return LibraryUIText.TEXT_CAPABILITY_PATTERN + ": "; //$NON-NLS-1$
		} else if (element instanceof DeliveryProcess) {
			return LibraryUIText.TEXT_DELIVERY_PROCESS + ": "; //$NON-NLS-1$
		} else if (element instanceof Phase) {
			return LibraryUIText.TEXT_PHASE + ": "; //$NON-NLS-1$
		} else if (element instanceof Iteration) {
			return LibraryUIText.TEXT_ITERATION + ": "; //$NON-NLS-1$
		} else
			return LibraryUIText.TEXT_ACTIVITY + ": "; //$NON-NLS-1$
	}

}
