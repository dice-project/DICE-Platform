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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.authoring.ui.filters.WorkProductFilter;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.VariabilityBaseElementFilter;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ArtifactDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DeliverableDescription;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * The Description page in a Work Product (Artifact, Deliverable and Outcome)
 * editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductDescriptionPage extends DescriptionFormPage {

	private static final String FORM_PAGE_ID = "workProductDescriptionPage"; //$NON-NLS-1$	

	private IMethodRichText ctrl_impact;

	private IMethodRichText ctrl_reason;

	// For Artifact
	private IMethodRichText ctrl_brief_outline;

	private IMethodRichText ctrl_representation_options;

	// For Deliverable
	private IMethodRichText ctrl_external_desc, ctrl_packaging_guidance;

	private IMethodRichText ctrl_representation;

	private IMethodRichText ctrl_notation;

	private WorkProduct workProduct;

	// For slots
	private Button ctrl_slot_button;
	
	private Section slotSection;
	
	private Composite slotComposite;
	
	private String slotSectionDescription;
	
	private Button addSlotButton, removeSlotButton;
	
	private Table ctrl_slot;
	
	private TableViewer slotViewer;

	// slots content provider
	private IStructuredContentProvider slotContentProvider = new AdapterFactoryContentProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public Object[] getElements(Object object) {
			List<FulfillableElement> list = new ArrayList<FulfillableElement>();
			list.addAll(workProduct.getFulfills());
			return list.toArray();
		}
	};
	
	protected ILabelProvider slotLabelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getColumnText(Object object, int columnIndex) {
			return TngUtil.getLabelWithPath(object);
		}
	};
	/**
	 * Creates a new instance.
	 */
	public WorkProductDescriptionPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		workProduct = (WorkProduct) contentElement;
		purposeOn = true;
		setExternalIDOn(true);
		if (!(contentElement instanceof Outcome)) {
			notationSectionOn = true;
		}
		tailoringSectionOn = true;
		iconSectionOn = true;
		slotSectionOn = true;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#createNotationSectionContent()
	 */
	protected void createNotationSectionContent() {
		super.createNotationSectionContent();

		if (workProduct instanceof Artifact) {
			ctrl_brief_outline = createRichTextEditWithLinkForSection(toolkit,
					notationComposite, AuthoringUIText.BRIEF_OUTLINE_TEXT, 40,
					400, NOTATION_SECTION_ID);
			ctrl_representation = createRichTextEditWithLinkForSection(toolkit,
					notationComposite, AuthoringUIText.REPRESENTATION_TEXT, 40,
					400, NOTATION_SECTION_ID);
			ctrl_notation = createRichTextEditWithLinkForSection(toolkit,
					notationComposite, AuthoringUIText.NOTATION_TEXT, 40, 400,
					NOTATION_SECTION_ID);
		} else if (workProduct instanceof Deliverable) {
			ctrl_external_desc = createRichTextEditWithLinkForSection(toolkit,
					notationComposite,
					AuthoringUIText.EXTERNAL_DESCRIPTION_TEXT, 40, 400,
					NOTATION_SECTION_ID);
			ctrl_packaging_guidance = createRichTextEditWithLinkForSection(
					toolkit, notationComposite,
					AuthoringUIText.PACKAGING_GUIDANCE_TEXT, 40, 400,
					NOTATION_SECTION_ID);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#createTailoringSectionContent()
	 */
	protected void createTailoringSectionContent() {
		super.createTailoringSectionContent();
		ctrl_impact = createRichTextEditWithLinkForSection(toolkit,
				tailoringComposite, AuthoringUIText.IMPACT_OF_NOT_HAVING_TEXT,
				40, 400, TAILORING_SECTION_ID);
		ctrl_reason = createRichTextEditWithLinkForSection(toolkit,
				tailoringComposite,
				AuthoringUIText.REASON_FOR_NOT_NEEDING_TEXT, 40, 400,
				TAILORING_SECTION_ID);
		if (workProduct instanceof Artifact) {
			ctrl_representation_options = createRichTextEditWithLinkForSection(
					toolkit, tailoringComposite,
					AuthoringUIText.REPRESENTATION_OPTIONS_TEXT, 40, 400,
					NOTATION_SECTION_ID);
		}
	}

	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
	}

	/**
	 * Create slot section
	 * 
	 * @param toolkit
	 */
	protected void createSlotSection(FormToolkit toolkit) {

		slotSection = createSection(toolkit, sectionComposite,
				AuthoringUIText.SLOT_SECTION_NAME, this.slotSectionDescription);
		slotComposite = createComposite(toolkit, slotSection);
		slotComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		slotComposite.setLayout(new GridLayout(5, false));
		createSlotSectionContent();
		toolkit.paintBordersFor(slotComposite);
	}

	/**
	 * 
	 */
	private void createSlotSectionContent() {
		
		ctrl_slot_button = toolkit.createButton(slotComposite,
				AuthoringUIResources.workproductDescriptionPage_slot_text,
				SWT.CHECK);
		GridData data = new GridData();
		data.horizontalSpan = 5;
		ctrl_slot_button.setLayoutData(data);
		
		createLabel(toolkit, slotComposite,
				AuthoringUIResources.slotsLabel_text, 2);
		ctrl_slot = createTable(toolkit, slotComposite, SWT.MULTI
				| SWT.READ_ONLY, GridData.FILL_HORIZONTAL | GridData.BEGINNING,
				5, 300, 1, 2);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.BEGINNING);
			gridData.horizontalSpan = 2;
			gridData.heightHint = 30;
			gridData.widthHint = 300;
			ctrl_slot.setLayoutData(gridData);
		}

		slotViewer = new TableViewer(ctrl_slot);
		slotViewer.setContentProvider(slotContentProvider);
		slotViewer.setLabelProvider(slotLabelProvider);
		slotViewer.setInput(methodElement);

		Composite buttonPane = createComposite(toolkit, slotComposite,
				GridData.HORIZONTAL_ALIGN_END, 1, 1, 1);
		{
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
			buttonPane.setLayoutData(gridData);
		}

		addSlotButton = toolkit.createButton(buttonPane,
				AuthoringUIText.ADD_BUTTON_TEXT, SWT.SIMPLE);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = BUTTON_WIDTH;
			addSlotButton.setLayoutData(gridData);
		}
		
		removeSlotButton = toolkit.createButton(buttonPane,
				AuthoringUIText.REMOVE_BUTTON_TEXT, SWT.SIMPLE);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = BUTTON_WIDTH;
			removeSlotButton.setLayoutData(gridData);
		}
		removeSlotButton.setEnabled(false);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#addListeners()
	 */
	protected void addListeners() {
		final IActionManager actionMgr = ((MethodElementEditor) getEditor())
				.getActionManager();

		super.addListeners();

		// add all slot related listeners
		
		if (slotSectionOn) {
			addSlotListeners();
		}
		
		
		if (purposeOn) {
			ctrl_purpose.setModalObject(contentElement.getPresentation());
			ctrl_purpose.setModalObjectFeature(UmaPackage.eINSTANCE
					.getWorkProductDescription_Purpose());
			ctrl_purpose.addModifyListener(contentModifyListener);
			ctrl_purpose.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_purpose;
					if (!control.getModified()) {
						return;
					}
					String oldContent = ((org.eclipse.epf.uma.WorkProductDescription) workProduct
							.getPresentation()).getPurpose();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr.doAction(
								IActionManager.SET, contentElement
										.getPresentation(),
								UmaPackage.eINSTANCE
										.getWorkProductDescription_Purpose(),
								newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});
		}


		if (tailoringSectionOn) {
			ctrl_impact.setModalObject(contentElement.getPresentation());
			ctrl_impact.setModalObjectFeature(UmaPackage.eINSTANCE
					.getWorkProductDescription_ImpactOfNotHaving());
			ctrl_impact.addModifyListener(contentModifyListener);
			ctrl_impact.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_impact;
					if (!control.getModified()) {
						return;
					}
					String oldContent = ((org.eclipse.epf.uma.WorkProductDescription) workProduct
							.getPresentation()).getImpactOfNotHaving();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr
								.doAction(
										IActionManager.SET,
										contentElement.getPresentation(),
										UmaPackage.eINSTANCE
												.getWorkProductDescription_ImpactOfNotHaving(),
										newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});

			ctrl_reason.setModalObject(contentElement.getPresentation());
			ctrl_reason.setModalObjectFeature(UmaPackage.eINSTANCE
					.getWorkProductDescription_ReasonsForNotNeeding());
			ctrl_reason.addModifyListener(contentModifyListener);
			ctrl_reason.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_reason;
					if (!control.getModified()) {
						return;
					}
					String oldContent = ((org.eclipse.epf.uma.WorkProductDescription) workProduct
							.getPresentation()).getReasonsForNotNeeding();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr
								.doAction(
										IActionManager.SET,
										contentElement.getPresentation(),
										UmaPackage.eINSTANCE
												.getWorkProductDescription_ReasonsForNotNeeding(),
										newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});

			if (workProduct instanceof Artifact) {
				ctrl_representation_options.setModalObject(contentElement
						.getPresentation());
				ctrl_representation_options
						.setModalObjectFeature(UmaPackage.eINSTANCE
								.getArtifactDescription_RepresentationOptions());
				ctrl_representation_options
						.addModifyListener(contentModifyListener);
				ctrl_representation_options.addListener(SWT.Deactivate,
						new Listener() {
							public void handleEvent(Event e) {
								IMethodRichText control = descExpandFlag ? ctrl_expanded
										: ctrl_representation_options;
								if (!control.getModified()) {
									return;
								}
								String oldContent = ((org.eclipse.epf.uma.ArtifactDescription) workProduct
										.getPresentation())
										.getRepresentationOptions();
								if (((MethodElementEditor) getEditor())
										.mustRestoreValue(control, oldContent)) {
									return;
								}
								String newContent = control.getText();
								if (!newContent.equals(oldContent)) {
									boolean success = actionMgr
											.doAction(
													IActionManager.SET,
													contentElement
															.getPresentation(),
													UmaPackage.eINSTANCE
															.getArtifactDescription_RepresentationOptions(),
													newContent, -1);
									if (success && isVersionSectionOn()) {
										updateChangeDate();
									}
								}
							}
						});
			}
		}

		if (notationSectionOn) {
			if (workProduct instanceof Artifact) {
				ctrl_brief_outline.setModalObject(contentElement
						.getPresentation());
				ctrl_brief_outline.setModalObjectFeature(UmaPackage.eINSTANCE
						.getArtifactDescription_BriefOutline());
				ctrl_brief_outline.addModifyListener(contentModifyListener);
				ctrl_brief_outline.addListener(SWT.Deactivate, new Listener() {
					public void handleEvent(Event e) {
						IMethodRichText control = descExpandFlag ? ctrl_expanded
								: ctrl_brief_outline;
						if (!control.getModified()) {
							return;
						}
						String oldContent = ((org.eclipse.epf.uma.ArtifactDescription) workProduct
								.getPresentation()).getBriefOutline();
						if (((MethodElementEditor) getEditor())
								.mustRestoreValue(control, oldContent)) {
							return;
						}
						String newContent = descExpandFlag ? ctrl_expanded
								.getText() : ctrl_brief_outline.getText();
						if (!newContent.equals(oldContent)) {
							boolean success = actionMgr
									.doAction(
											IActionManager.SET,
											contentElement.getPresentation(),
											UmaPackage.eINSTANCE
													.getArtifactDescription_BriefOutline(),
											newContent, -1);
							if (success && isVersionSectionOn()) {
								updateChangeDate();
							}
						}
					}
				});

				ctrl_representation.setModalObject(contentElement
						.getPresentation());
				ctrl_representation.setModalObjectFeature(UmaPackage.eINSTANCE
						.getArtifactDescription_Representation());
				ctrl_representation.addModifyListener(contentModifyListener);
				ctrl_representation.addListener(SWT.Deactivate, new Listener() {
					public void handleEvent(Event e) {
						IMethodRichText control = descExpandFlag ? ctrl_expanded
								: ctrl_representation;
						if (!control.getModified()) {
							return;
						}
						String oldContent = ((org.eclipse.epf.uma.ArtifactDescription) workProduct
								.getPresentation()).getRepresentation();
						if (((MethodElementEditor) getEditor())
								.mustRestoreValue(control, oldContent)) {
							return;
						}
						String newContent = descExpandFlag ? ctrl_expanded
								.getText() : ctrl_representation.getText();
						if (!newContent.equals(oldContent)) {
							boolean success = actionMgr
									.doAction(
											IActionManager.SET,
											contentElement.getPresentation(),
											UmaPackage.eINSTANCE
													.getArtifactDescription_Representation(),
											newContent, -1);
							if (success && isVersionSectionOn()) {
								updateChangeDate();
							}
						}
					}
				});

				ctrl_notation.setModalObject(contentElement.getPresentation());
				ctrl_notation.setModalObjectFeature(UmaPackage.eINSTANCE
						.getArtifactDescription_Notation());
				ctrl_notation.addModifyListener(contentModifyListener);
				ctrl_notation.addListener(SWT.Deactivate, new Listener() {
					public void handleEvent(Event e) {
						IMethodRichText control = descExpandFlag ? ctrl_expanded
								: ctrl_notation;
						if (!control.getModified()) {
							return;
						}
						String oldContent = ((org.eclipse.epf.uma.ArtifactDescription) workProduct
								.getPresentation()).getNotation();
						if (((MethodElementEditor) getEditor())
								.mustRestoreValue(control, oldContent)) {
							return;
						}
						String newContent = descExpandFlag ? ctrl_expanded
								.getText() : ctrl_notation.getText();
						if (!newContent.equals(oldContent)) {
							boolean success = actionMgr.doAction(
									IActionManager.SET, contentElement
											.getPresentation(),
									UmaPackage.eINSTANCE
											.getArtifactDescription_Notation(),
									newContent, -1);
							if (success && isVersionSectionOn()) {
								updateChangeDate();
							}
						}
					}
				});
			}

			if (workProduct instanceof Deliverable) {
				ctrl_external_desc.setModalObject(contentElement
						.getPresentation());
				ctrl_external_desc.setModalObjectFeature(UmaPackage.eINSTANCE
						.getDeliverableDescription_ExternalDescription());
				ctrl_external_desc.addModifyListener(contentModifyListener);
				ctrl_external_desc.addListener(SWT.Deactivate, new Listener() {
					public void handleEvent(Event e) {
						IMethodRichText control = descExpandFlag ? ctrl_expanded
								: ctrl_external_desc;
						if (!control.getModified()) {
							return;
						}
						String oldContent = ((org.eclipse.epf.uma.DeliverableDescription) workProduct
								.getPresentation()).getExternalDescription();
						if (((MethodElementEditor) getEditor())
								.mustRestoreValue(control, oldContent)) {
							return;
						}
						String newContent = control.getText();
						if (!newContent.equals(oldContent)) {
							boolean success = actionMgr
									.doAction(
											IActionManager.SET,
											contentElement.getPresentation(),
											UmaPackage.eINSTANCE
													.getDeliverableDescription_ExternalDescription(),
											newContent, -1);
							if (success && isVersionSectionOn()) {
								updateChangeDate();
							}
						}
					}
				});

				ctrl_packaging_guidance.setModalObject(contentElement
						.getPresentation());
				ctrl_packaging_guidance
						.setModalObjectFeature(UmaPackage.eINSTANCE
								.getDeliverableDescription_PackagingGuidance());
				ctrl_packaging_guidance
						.addModifyListener(contentModifyListener);
				ctrl_packaging_guidance.addListener(SWT.Deactivate,
						new Listener() {
							public void handleEvent(Event e) {
								IMethodRichText control = descExpandFlag ? ctrl_expanded
										: ctrl_packaging_guidance;
								if (!control.getModified()) {
									return;
								}
								String oldContent = ((org.eclipse.epf.uma.DeliverableDescription) workProduct
										.getPresentation())
										.getPackagingGuidance();
								if (((MethodElementEditor) getEditor())
										.mustRestoreValue(control, oldContent)) {
									return;
								}
								String newContent = control.getText();
								if (!newContent.equals(oldContent)) {
									boolean success = actionMgr
											.doAction(
													IActionManager.SET,
													contentElement
															.getPresentation(),
													UmaPackage.eINSTANCE
															.getDeliverableDescription_PackagingGuidance(),
													newContent, -1);
									if (success && isVersionSectionOn()) {
										updateChangeDate();
									}
								}
							}
						});
			}
		}
	}
	
	
	/**
	 * Slot Listeners
	 */
	private void addSlotListeners()	{
		// work product slot check button
		ctrl_slot_button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Boolean isSlot = new Boolean(ctrl_slot_button.getSelection());
				if (isSlot.booleanValue()) {
					// if this becoming a slot itself
					List<FulfillableElement> fulFills = workProduct
							.getFulfills();
					if (fulFills != null && fulFills.size() != 0) {
						boolean val = AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayConfirmation(
										AuthoringUIResources.slotConfirmDialog_title,
										AuthoringUIResources.wpFulFillsConfirmDialog_message);
						if (!val) {
							ctrl_slot_button.setSelection(workProduct
									.getIsAbstract().booleanValue());
							return;
						} else {
							actionMgr.doAction(IActionManager.REMOVE_MANY,
									workProduct, UmaPackage.eINSTANCE
											.getFulfillableElement_Fulfills(),
									fulFills, -1);
						}
					}
				} else {					
					List fulFills = new ArrayList();
					fulFills.addAll(AssociationHelper.getFullFills(workProduct));
					if (fulFills != null && fulFills.size() != 0) {
						boolean val = AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayConfirmation(
										AuthoringUIResources.slotConfirmDialog_title,
										AuthoringUIResources.wpSlotFulFillsConfirmDialog_message);
						if (!val) {
							ctrl_slot_button.setSelection(workProduct
									.getIsAbstract().booleanValue());
							return;
						} else {
							for (Iterator itor = fulFills.iterator(); itor
									.hasNext();) {
								WorkProduct wp = (WorkProduct) itor.next();
								actionMgr
										.doAction(
												IActionManager.REMOVE,
												wp,
												UmaPackage.eINSTANCE
														.getFulfillableElement_Fulfills(),
												workProduct, -1);
							}
						}
					}				
				}
				actionMgr.doAction(IActionManager.SET, methodElement,
						UmaPackage.eINSTANCE.getClassifier_IsAbstract(), isSlot,
						-1);
				
				if (isSlot) {		
					addSlotButton.setEnabled(false);
				}
				else {
					addSlotButton.setEnabled(true);
				}
			}
		});

		addSlotButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IFilter filter = new WorkProductFilter() {
					protected boolean childAccept(Object obj) {
						if (obj instanceof WorkProduct) {
							return ((WorkProduct) obj).getIsAbstract().booleanValue();
						}
						return false;
					}
				};
				List<FulfillableElement> alreadyExisting = new ArrayList<FulfillableElement>();
				alreadyExisting.addAll(workProduct.getFulfills());
				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, methodElement,
						FilterConstants.WORKPRODUCTS, alreadyExisting);
				fd.setViewerSelectionSingle(false);
				fd.setBlockOnOpen(true);
				fd.setTitle(FilterConstants.WORKPRODUCTS);
				fd.open();
				
				if (fd.getSelectedItems().size() > 0) {
					boolean status = actionMgr.doAction(
							IActionManager.ADD_MANY, workProduct,
							UmaPackage.eINSTANCE
									.getFulfillableElement_Fulfills(), fd
									.getSelectedItems(), -1);
					if (!status) {
						return;
					}
					// refresh slot viewer
					slotViewer.refresh();	
				}		
			}
		});
		
		removeSlotButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				IStructuredSelection selection = (IStructuredSelection) slotViewer
						.getSelection();
				if (selection.size() > 0) {
					boolean status = actionMgr.doAction(IActionManager.REMOVE_MANY, workProduct,
							UmaPackage.eINSTANCE
									.getFulfillableElement_Fulfills(),
							selection.toList(), -1);
					
					if (!status) {
						return;
					}
					// refresh slot viewer
					slotViewer.refresh();
					removeSlotButton.setEnabled(false);
				}
			}	
		});
		
		slotViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent e) {
				IStructuredSelection selection = (IStructuredSelection) slotViewer
						.getSelection();
				if (selection.size() > 0 && !TngUtil.isLocked(workProduct)) {
					removeSlotButton.setEnabled(true);
				}
			}
		});

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#refresh(boolean)
	 */
	protected void refresh(boolean editable) {
		super.refresh(editable);
		
		if (slotSectionOn)  {
			ctrl_slot_button.setEnabled(editable);
			
			Boolean isAbstract = workProduct.getIsAbstract();
			if (isAbstract != null && editable) {
				if (isAbstract.booleanValue())  {
					addSlotButton.setEnabled(false);
				} else {
					addSlotButton.setEnabled(true);
				}
			}
			else
				addSlotButton.setEnabled(editable);
			
			IStructuredSelection selection = (IStructuredSelection) slotViewer.getSelection();
			if (selection.size() > 0 && editable) {
				removeSlotButton.setEnabled(true);
			} else {
				removeSlotButton.setEnabled(false);
			}
		}
		
		if (tailoringSectionOn) {
			ctrl_impact.setEditable(editable);
			ctrl_reason.setEditable(editable);
			if (workProduct instanceof Artifact)
				ctrl_representation_options.setEditable(editable);
		}
		if (notationSectionOn) {
			if (workProduct instanceof Artifact) {
				ctrl_brief_outline.setEditable(editable);
				ctrl_representation.setEditable(editable);
				ctrl_notation.setEditable(editable);
			}
			if (workProduct instanceof Deliverable) {
				ctrl_external_desc.setEditable(editable);
				ctrl_packaging_guidance.setEditable(editable);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadData()
	 */
	protected void loadData() {
		super.loadData();

		// IsAbstract attribute
		if (slotSectionOn) {
			Boolean isAbstract = workProduct.getIsAbstract();
			if (isAbstract != null) {
				ctrl_slot_button.setSelection(isAbstract.booleanValue());
			} else
				ctrl_slot_button.setSelection(false);
		}

		
		org.eclipse.epf.uma.WorkProductDescription content = ((org.eclipse.epf.uma.WorkProductDescription) workProduct
				.getPresentation());

		if (purposeOn) {
			String purpose = content.getPurpose();
			ctrl_purpose.setText(purpose == null ? "" : purpose); //$NON-NLS-1$
		}
		if (tailoringSectionOn) {
			String impact = content.getImpactOfNotHaving();
			String reason = content.getReasonsForNotNeeding();
			ctrl_impact.setText(impact == null ? "" : impact); //$NON-NLS-1$	
			ctrl_reason.setText(reason == null ? "" : reason); //$NON-NLS-1$
			if (workProduct instanceof Artifact) {
				ctrl_representation_options
						.setText(((ArtifactDescription) content)
								.getRepresentationOptions() == null ? "" : ((ArtifactDescription) content).getRepresentationOptions()); //$NON-NLS-1$
			}
		}

		

		if (notationSectionOn) {
			if (workProduct instanceof Artifact) {
				ctrl_brief_outline
						.setText(((ArtifactDescription) content)
								.getBriefOutline() == null ? "" : ((ArtifactDescription) content).getBriefOutline()); //$NON-NLS-1$			
				ctrl_representation
						.setText(((ArtifactDescription) content)
								.getRepresentation() == null ? "" : ((ArtifactDescription) content).getRepresentation()); //$NON-NLS-1$
				ctrl_notation
						.setText(((ArtifactDescription) content).getNotation() == null ? "" : ((ArtifactDescription) content).getNotation()); //$NON-NLS-1$
			} else if (workProduct instanceof Deliverable) {
				ctrl_external_desc
						.setText(((DeliverableDescription) content)
								.getExternalDescription() == null ? "" : ((DeliverableDescription) content).getExternalDescription()); //$NON-NLS-1$
				ctrl_packaging_guidance
						.setText(((DeliverableDescription) content)
								.getPackagingGuidance() == null ? "" : ((DeliverableDescription) content).getPackagingGuidance()); //$NON-NLS-1$
			}
		}

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return workProduct;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new WorkProductFilter() {
			List badlist = new ArrayList();

			protected boolean childAccept(Object obj) {
				if (super.childAccept(obj)) {
					if (obj instanceof ContentElement) {
						if (obj instanceof WorkProduct
								&& ((ContentElement) getHelper()
										.getContentElement()).getType().equals(
										((ContentElement) obj).getType())) {
							// prevent circular dependency from deliverable
							// parts and variability
							if (obj instanceof Deliverable
									&& workProduct instanceof Deliverable) {
								if (!UIHelper.checkCircularDeliverables(
										(Deliverable) obj,
										(Deliverable) workProduct)) {
									badlist.add(obj);
									return false;
								}
								if (((Deliverable) obj)
										.getVariabilityBasedOnElement() != null) {
									if (!UIHelper
											.checkCircularDeliverables(
													(Deliverable) ((Deliverable) obj)
															.getVariabilityBasedOnElement(),
													(Deliverable) workProduct)) {
										badlist.add(obj);
										return false;
									}
								}
								Iterator it1 = TngUtil
										.getGeneralizers((Deliverable) obj);
								while (it1.hasNext()) {
									VariabilityElement ve = (VariabilityElement) it1
											.next();
									if (ve != null) {
										if (ve.equals(obj))
											return false;
										if (!UIHelper
												.checkCircularDeliverables(
														(Deliverable) ve,
														(Deliverable) workProduct)) {
											badlist.add(obj);
											return false;
										}
									}

								}
								// browse through badlist and its deliverable
								// parts.
								List templist = new ArrayList();
								for (Iterator iterator = badlist.iterator(); iterator
										.hasNext();) {
									Object bad = iterator.next();
									UIHelper.deliverablePartsChain(
											(Deliverable) bad, templist);
								}
								if (templist.contains(obj))
									return false;
							}

							return true;
						}
					}
				}
				return false;
			}
		};

		// set additional filter for variability base element checking
		((ContentFilter) filter)
				.setAdditionalFilters(new IFilter[] { new VariabilityBaseElementFilter(
						workProduct) });
		return filter;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		if (contentElement instanceof Artifact) {
			this.generalSectionDescription = AuthoringUIResources.artifact_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.artifact_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.artifact_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.artifact_versionInfoSection_desc;
			this.notationSectionDescription = AuthoringUIResources.artifact_notationSection_desc;
			this.tailoringSectionDescription = AuthoringUIResources.artifact_tailoringSection_desc;
			this.iconSectionDescription = AuthoringUIResources.artifact_iconSection_desc;
			this.slotSectionDescription = AuthoringUIResources.artifact_slotSection_desc;
		}
		if (contentElement instanceof Outcome) {
			this.generalSectionDescription = AuthoringUIResources.outcome_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.outcome_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.outcome_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.outcome_versionInfoSection_desc;
			this.notationSectionDescription = AuthoringUIResources.outcome_notationSection_desc;
			this.tailoringSectionDescription = AuthoringUIResources.outcome_tailoringSection_desc;
			this.iconSectionDescription = AuthoringUIResources.outcome_iconSection_desc;
			this.slotSectionDescription = AuthoringUIResources.outcome_slotSection_desc;
		}
		if (contentElement instanceof Deliverable) {
			this.generalSectionDescription = AuthoringUIResources.deliverable_generalInfoSection_desc;
			this.detailSectionDescription = AuthoringUIResources.deliverable_detailSection_desc;
			this.variabilitySectionDescription = AuthoringUIResources.deliverable_variabilitySection_desc;
			this.versionSectionDescription = AuthoringUIResources.deliverable_versionInfoSection_desc;
			this.notationSectionDescription = AuthoringUIResources.deliverable_notationSection_desc;
			this.tailoringSectionDescription = AuthoringUIResources.deliverable_tailoringSection_desc;
			this.iconSectionDescription = AuthoringUIResources.deliverable_iconSection_desc;
			this.slotSectionDescription = AuthoringUIResources.deliverable_slotSection_desc;
		}
	}

}
