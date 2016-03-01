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

import java.util.Arrays;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIExtensionManager;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The general tab section for breakdownElement
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class BreakdownElementGeneralSection extends AbstractSection {
	// controls
	protected FormToolkit toolkit;

	protected Section generalSection;

	protected Composite generalComposite, checkBoxComposite;

	protected Text nameText;

	protected Text presentationNameText;
	
	protected Button nameRestoreBtn, presentationNameRestoreBtn;
	
	protected Composite buttonComposite_1, buttonComposite_2;
	
	private DescriptorPropUtil descriptorPropUtil;
	
	private ModifyListener syncFreeNameModifyListener, syncFreePresentationNameModifyListener;
	
	private boolean isNameReplace = false; 
	
	private boolean isPresentationNameReplace = false;

	private Button multipleButton, optionalButton;

	private Button plannedButton, supressedButton;

	protected int numOfColumns = 4;

	protected int horizontalSpan = 3;

	// private TabbedPropertySheetPage tabbedPropertySheetPage;

	// element
	protected BreakdownElement element;

	// action manager
	protected IActionManager actionMgr;

	// modify listener
	protected ModifyListener modelModifyListener;

	protected ModifyListener nameModifyListener;

	private ILabelProvider labelProvider;

	final boolean ignoreSuppressed = true;
	
	private BeGeneralSectionExtender extender;

	protected Listener nameDeactivateListener = new Listener() {
		public void handleEvent(Event e) {
			AdapterFactory adapterFactory = EPFPropertySheetPage
					.getAdapterFactory();

			element = (BreakdownElement) getElement();
			String oldContent = element.getName();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					nameText, oldContent)) {
				return;
			}

			String message = null;
			if (!StrUtil.isBlank(element.getName())) {
				Suppression suppression = EPFPropertySheetPage.getEditor()
						.getSuppression();
				message = ProcessUtil.checkBreakdownElementName(
						adapterFactory, element, nameText.getText(),
						suppression, ignoreSuppressed);
			}
			if (message == null) {
				if (!nameText.getText().equals(oldContent)) {
					if ((element instanceof Activity)
							&& (element.eContainer() instanceof ProcessComponent)) {
						ProcessComponent procComp = (ProcessComponent) element
								.eContainer();
						IValidator validator = IValidatorFactory.INSTANCE
								.createNameValidator(
										procComp,
										TngAdapterFactory.INSTANCE
												.getNavigatorView_ComposedAdapterFactory());
						String msg = validator.isValid(nameText.getText());

						if (msg == null) {
							msg = PropertiesResources.Process_Rename_Message; 

							if (AuthoringUIPlugin
									.getDefault()
									.getMsgDialog()
									.displayConfirmation(
											PropertiesResources.Process_Rename_Title, msg)) { 
								e.doit = true;
								boolean status = actionMgr.doAction(
										IActionManager.SET, element,
										UmaPackage.eINSTANCE
												.getNamedElement_Name(),
										nameText.getText(), -1);
								if (!status) {
									return;
									// nameText.setText(element.getName());

								}
								actionMgr.doAction(IActionManager.SET,
										procComp, UmaPackage.eINSTANCE
												.getNamedElement_Name(),
										nameText.getText(), -1);
								getEditor().setPartName();

								// adjust plugin location and save the
								// editor
								//
								BusyIndicator.showWhile(Display
										.getDefault(), new Runnable() {

									public void run() {
										MethodElementEditor editor = (MethodElementEditor) getEditor();
										editor
												.doSave(new NullProgressMonitor());
										ILibraryPersister.FailSafeMethodLibraryPersister persister = editor
												.getPersister();
										try {
											persister
													.adjustLocation(element
															.eResource());
											persister.commit();
										} catch (RuntimeException e) {
											persister.rollback();
											throw e;
										}
										//	 adjust diagram resource as well
										//
										if (element instanceof Process) {
											DiagramManager mgr = DiagramManager.getInstance((Process) element, this);
											if(mgr != null) {
												try {
													mgr.updateResourceURI();
												}
												catch(Exception e) {
													AuthoringUIPlugin.getDefault().getLogger().logError(e);
												}
												finally {
													mgr.removeConsumer(this);
												}
											}
										}
									}

								});
							} else {
								// replace with existing name
								nameText.setText(oldContent);
								return;
							}
						} else {
							// restore the old name first, then present the
							// error message box
							nameText.setText(oldContent);
							AuthoringUIPlugin
									.getDefault()
									.getMsgDialog()
									.displayError(
											PropertiesResources.Process_InvalidNameTitle, msg); 
							e.doit = false;
							nameText.getDisplay().asyncExec(new Runnable() {
								public void run() {
									nameText.setFocus();
									nameText.selectAll();
								}
							});
						}
					} else {
						e.doit = true;
						boolean status = actionMgr
								.doAction(IActionManager.SET, element,
										UmaPackage.eINSTANCE
												.getNamedElement_Name(),
										nameText.getText(), -1);
						if (!status) {
							return;
						}
					}
				}
			} else {
				// restore the old name first, then present the error
				// message box
				nameText.setText(oldContent);
				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								PropertiesResources.Process_InvalidNameTitle, message); 
				e.doit = false;
				nameText.getDisplay().asyncExec(new Runnable() {
					public void run() {
						nameText.setFocus();
						nameText.selectAll();
					}
				});

			}
		}
	};

	protected Listener presentationNameDeactivateListener = new Listener() {
		public void handleEvent(Event e) {
			AdapterFactory adapterFactory = EPFPropertySheetPage
					.getAdapterFactory();

			element = (BreakdownElement) getElement();
			String oldContent = element.getPresentationName();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					presentationNameText, oldContent)) {
				return;
			}

			String message = null;
			
			// you can have blank presentation name for activity that extends 
			if (StrUtil.isBlank(presentationNameText.getText())
					&& element instanceof VariabilityElement) {
				VariabilityElement ve = (VariabilityElement) element;
				if (ve.getVariabilityBasedOnElement() != null
						&& ve.getVariabilityType().equals(
								VariabilityType.EXTENDS)) {
					
					e.doit = true;
					if (!presentationNameText.getText().equals(oldContent)) {
						boolean status = actionMgr
								.doAction(
										IActionManager.SET,
										element,
										UmaPackage.eINSTANCE
												.getMethodElement_PresentationName(),
										presentationNameText.getText(), -1);
						if (!status) {
							return;
						}
					}
				}
			}
			
			if (!StrUtil.isBlank(element.getPresentationName())) {
				Suppression suppression = EPFPropertySheetPage.getEditor()
						.getSuppression();
				message = ProcessUtil
						.checkBreakdownElementPresentationName(
								adapterFactory, element,
								presentationNameText.getText(), suppression, ignoreSuppressed);
			}

			if (message == null) {
				e.doit = true;
				if (!presentationNameText.getText().equals(oldContent)) {
					boolean status = actionMgr
							.doAction(
									IActionManager.SET,
									element,
									UmaPackage.eINSTANCE
											.getMethodElement_PresentationName(),
									presentationNameText.getText(), -1);
					if (!status) {
						return;
					}
				}
			} else {
				// restore the old name first, then present the error
				// message box
				presentationNameText.setText(element.getPresentationName());

				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								PropertiesResources.Process_InvalidNameTitle, message); 
				e.doit = false;
				presentationNameText.getDisplay().asyncExec(new Runnable() {
					public void run() {
						presentationNameText.setFocus();
					}
				});
			}
		}
	};
	
	protected void init() {
		extender = AuthoringUIExtensionManager.getInstance().createBeGeneralSectionExtender(this);
			
		// get BreakdownElement object
		element = (BreakdownElement) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();
		
		// get descriptor util
		descriptorPropUtil = DescriptorPropUtil.getDesciptorPropUtil(actionMgr);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		init();
		// this.tabbedPropertySheetPage = tabbedPropertySheetPage;

		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		// create general section
		createGeneralSection(parent);
		extender.modifyGeneralSectionContent(toolkit, actionMgr);

		// addListeners
		addListeners();

		// update controls
		updateControls();

	}

	/**
	 *  Add listeners
	 */
	protected void addListeners() {
		nameText.addListener(SWT.Activate, new Listener() {

			public void handleEvent(Event event) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(event.widget,
						UmaPackage.eINSTANCE.getNamedElement_Name());
			}
			
		});
		nameText.addListener(SWT.Deactivate, nameDeactivateListener);

		presentationNameText.addListener(SWT.Activate, new Listener() {

			public void handleEvent(Event event) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(event.widget,
						UmaPackage.eINSTANCE.getMethodElement_PresentationName());
			}
			
		});		
		presentationNameText.addListener(SWT.Deactivate, presentationNameDeactivateListener);
		presentationNameText.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				// when user tab to this field, select all text
				presentationNameText.selectAll();
				  
			}
			public void focusLost(FocusEvent e) {
				// clear the selection when the focus of the component is lost 
				if(presentationNameText.getSelectionCount() > 0){
					presentationNameText.clearSelection();
	            } 
			}
		});
		
		multipleButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (BreakdownElement) getElement();

				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getBreakdownElement_HasMultipleOccurrences(),
						Boolean.valueOf(multipleButton.getSelection()), -1);
				if (!status)
					multipleButton.setSelection(element
							.getHasMultipleOccurrences().booleanValue());

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		optionalButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (BreakdownElement) getElement();
				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getBreakdownElement_IsOptional(), Boolean
								.valueOf(optionalButton.getSelection()), -1);
				if (!status)
					optionalButton.setSelection(element.getIsOptional()
							.booleanValue());

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		plannedButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (BreakdownElement) getElement();
				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getBreakdownElement_IsPlanned(), Boolean
								.valueOf(plannedButton.getSelection()), -1);
				if (!status)
					plannedButton.setSelection(element.getIsPlanned()
							.booleanValue());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		supressedButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (BreakdownElement) getElement();
				if (element.getSuppressed().booleanValue()) {
					// revealing
					ProcessEditor editor = getEditor();
					Suppression suppression = editor.getSuppression();
					boolean canReveal = ProcessEditor.canReveal(
							Arrays.asList(new Object[] { element }),
							EPFPropertySheetPage.getAdapterFactory(),
							suppression);
				
					if (!canReveal)					
						supressedButton.setSelection(true);	
					else
						supressedButton.setSelection(false);
				}
				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getMethodElement_Suppressed(), Boolean
								.valueOf(supressedButton.getSelection()), -1);
				if (!status)
					supressedButton.setSelection(element.getSuppressed()
							.booleanValue());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		if (nameRestoreBtn != null) {
			nameRestoreBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					descriptorPropUtil.setNameRepalce((Descriptor)element, false);
					isNameReplace = false;
					updateNameRestoreBtn();
					
					Descriptor greenParent = descriptorPropUtil.getGreenParentDescriptor((Descriptor)element);
					if (greenParent != null) {
						element.setName(greenParent.getName());
					} else {					
						MethodElement reference = getReferenceMethodElement(element);
						element.setName(reference.getName());
					}
				}
			});
		}
		
		if (presentationNameRestoreBtn != null) {
			presentationNameRestoreBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					descriptorPropUtil.setPresentationNameRepalce((Descriptor)element, false);
					isPresentationNameReplace = false;
					updatePresentationNameRestoreBtn();
					
					Descriptor greenParent = descriptorPropUtil.getGreenParentDescriptor((Descriptor)element);
					if (greenParent != null) {
						element.setPresentationName(greenParent.getPresentationName());
					} else {
						MethodElement reference = getReferenceMethodElement(element);
						element.setPresentationName(reference.getPresentationName());
					}
				}
			});
		}		
	}
	
	private MethodElement getReferenceMethodElement(BreakdownElement element) {
		MethodElement e = null;
		
		if (element instanceof TaskDescriptor) {
			e = ((TaskDescriptor)element).getTask();
		} else if (element instanceof RoleDescriptor) {
			e = ((RoleDescriptor)element).getRole();
		} else if (element instanceof WorkProductDescriptor) {
			e = ((WorkProductDescriptor)element).getWorkProduct();
		}
		
		if (e != null) {
			e = ConfigurationHelper.getCalculatedElement(e, getConfiguration());
		}
		
		return e;
	}

	/**
	 * Create general section on the given composite
	 * @param composite
	 */
	protected void createGeneralSection(Composite composite) {
		// create general section
		generalSection = FormUI
				.createSection(
						toolkit,
						composite,
						PropertiesResources.Process_generalInformationTitle, 
						PropertiesResources.bind(PropertiesResources.Process_generalInformationDescription, PropertiesUtil.getTypeLower(element))); 

		// create general composite
		generalComposite = FormUI.createComposite(toolkit, generalSection,
				numOfColumns, false);

		// name
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.Process_name);
		if (isSyncFree()) {
			nameText = FormUI.createText(toolkit, generalComposite, SWT.DEFAULT, 1);
			buttonComposite_1 = FormUI.createComposite(toolkit, generalComposite, SWT.NONE, 2, true);
			nameRestoreBtn = FormUI.createButton(toolkit, buttonComposite_1, PropertiesResources.Process_name_restore);
			nameRestoreBtn.setEnabled(false);
			updateNameRestoreBtn();
		} else {		 
			nameText = FormUI.createText(toolkit, generalComposite, SWT.DEFAULT, horizontalSpan);
		}

		// presentation name
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.Activity_presentationName);
		if (isSyncFree()) {
			presentationNameText = FormUI.createText(toolkit, generalComposite, SWT.DEFAULT, 1);
			buttonComposite_2 = FormUI.createComposite(toolkit, generalComposite, SWT.NONE, 2, true);
			presentationNameRestoreBtn = FormUI.createButton(toolkit, buttonComposite_2,
					PropertiesResources.Process_PresentationName_restore);
			presentationNameRestoreBtn.setEnabled(false);
			updatePresentationNameRestoreBtn();
		} else {		 
			presentationNameText = FormUI.createText(toolkit, generalComposite, SWT.DEFAULT, horizontalSpan);
		}

		// create composite for checkbox
		checkBoxComposite = FormUI.createComposite(toolkit, generalComposite,
				GridData.FILL_HORIZONTAL, numOfColumns, true);

		// optional
		optionalButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		optionalButton.setText(PropertiesResources.BreakdownElement_Option_Optional); 

		// Multiple Ocurrances
		multipleButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		multipleButton.setText(PropertiesResources.BreakdownElement_Option_MultipleOcurrance); 

		// planned
		plannedButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		plannedButton.setText(PropertiesResources.BreakdownElement_Option_Planned); 

		// supressed
		supressedButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		supressedButton.setText(PropertiesResources.BreakdownElement_Option_Supressed); 
		supressedButton.setEnabled(false);

		toolkit.paintBordersFor(generalComposite);
		toolkit.paintBordersFor(checkBoxComposite);
		
		if (isSyncFree()) {
			toolkit.paintBordersFor(buttonComposite_1);
			toolkit.paintBordersFor(buttonComposite_2);
		}
	}
	
	private void updateNameRestoreBtn() {
		if (getReferenceMethodElement(element) != null) {
			nameRestoreBtn.setEnabled(
					descriptorPropUtil.isNameRepalce((Descriptor)element) && editable);
		}
	}
	
	private void updatePresentationNameRestoreBtn() {
		if (getReferenceMethodElement(element) != null) {
			presentationNameRestoreBtn.setEnabled(
					descriptorPropUtil.isPresentationNameRepalce((Descriptor)element) && editable);
		}
	}
	
	/**
	 * Modify listner for name and presentation name
	 *
	 */
	public class NameModifyListener extends MethodElementEditor.ModifyListener {
		/**
		 * Creates a new instance.
		 */

		public NameModifyListener(EObject element, boolean checkContainer) {

			getEditor().super(element, checkContainer);
		}

		public void modifyText(ModifyEvent e) {
			super.modifyText(e);

			updateTitle(nameText.getText());
		}
	}

	/**
	 * Create name modify listener for the given object
	 * @param eObj
	 * @return
	 * 			listener
	 */
	public ModifyListener createNameModifyListener(EObject eObj) {
		return createNameModifyListener(eObj, false);
	}

	public ModifyListener createNameModifyListener(EObject eObj,
			boolean checkContainer) {
		return new NameModifyListener(eObj, checkContainer);
	}

	/**
	 * Update title of the properties page
	 * @param txt
	 * 			new title
	 */
	public void updateTitle(String txt) {
		String name = getNamePrefix() + txt;
		TabbedPropertyComposite comp = (TabbedPropertyComposite) page
				.getControl();
		TabbedPropertyTitle title = comp.getTitle();

		title.setTitle(name, getImage(element));
	}

	private Image getImage(Object obj) {
		if (labelProvider == null) {
			labelProvider = new AdapterFactoryLabelProvider(
					TngUtil.umaItemProviderAdapterFactory);
		}
		Image titleImage = labelProvider.getImage(obj);
		return titleImage;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#dispose()
	 */
	public void dispose() {
		if (labelProvider != null) {
			labelProvider.dispose();
		}
	}

	/*
	 * @see org.eclipse.wst.common.ui.properties.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof BreakdownElement) {
				// get BreakdownElement object
				element = (BreakdownElement) getElement();

				// update section name
				generalSection
						.setDescription(PropertiesResources.bind(PropertiesResources.Process_generalInformationDescription, PropertiesUtil.getTypeLower(element))); 

				// Model Modify listener for presentation name
				if (modelModifyListener != null) {
					presentationNameText
							.removeModifyListener(modelModifyListener);
				}
				modelModifyListener = getEditor().createModifyListener(element);
				if (syncFreePresentationNameModifyListener != null) {
					presentationNameText.removeModifyListener(syncFreePresentationNameModifyListener);
				}
				syncFreePresentationNameModifyListener = createSyncFreePresentationNameModifyListener();				

				// Modify listener for name
				if (nameModifyListener != null)
					nameText.removeModifyListener(nameModifyListener);
				if (element instanceof Process) {
					nameModifyListener = createNameModifyListener(
							((Process) element).eContainer(), true);
				} else {
					nameModifyListener = createNameModifyListener(element);
				}
				if (syncFreeNameModifyListener != null) {
					nameText.removeModifyListener(syncFreeNameModifyListener);
				}
				syncFreeNameModifyListener = createSyncFreeNameModifyListener();				

				if (modelModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) modelModifyListener)
							.setElement(element);
					((MethodElementEditor.ModifyListener) modelModifyListener)
							.setDisable(true);
				}
				if (nameModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) nameModifyListener)
							.setDisable(true);
				}

				if (isNameReplace && descriptorPropUtil.isNameRepalce((Descriptor)element)) {
					//
				} else {
					nameText.setText(element.getName());
					if (isSyncFree()) {
						updateNameRestoreBtn();
					}
				}				
				if (isPresentationNameReplace && descriptorPropUtil.isPresentationNameRepalce((Descriptor)element)) {
					//
				} else {
					presentationNameText.setText(element.getPresentationName());
					if (isSyncFree()) {
						updatePresentationNameRestoreBtn();
					}
				}

				if (modelModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) modelModifyListener)
							.setDisable(false);
				}
				if (nameModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) nameModifyListener)
							.setDisable(false);
				}

				nameText.addModifyListener(nameModifyListener);				
				presentationNameText.addModifyListener(modelModifyListener);
				
				if (syncFreeNameModifyListener != null) {
					nameText.addModifyListener(syncFreeNameModifyListener);
				}
				if (syncFreePresentationNameModifyListener != null) {
					presentationNameText.addModifyListener(syncFreePresentationNameModifyListener);
				}				

				multipleButton.setSelection(element.getHasMultipleOccurrences()
						.booleanValue());
				optionalButton.setSelection(element.getIsOptional()
						.booleanValue());
				plannedButton.setSelection(element.getIsPlanned()
						.booleanValue());
				// get selected original input object
				Object input = getInput();
				if (input instanceof BreakdownElementWrapperItemProvider) {
					if (getEditor().getSuppression().isInSuppressedList(
							(BreakdownElementWrapperItemProvider) input)) {
						supressedButton.setSelection(true);
					} else {
						supressedButton.setSelection(false);
					}
				} else
				supressedButton.setSelection(element.getSuppressed()
						.booleanValue());
				extender.refresh();
				updateControls();
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing breakdown element general section" + element, ex); //$NON-NLS-1$
		}
	}

	protected void updateControls() {
		nameText.setEditable(editable);
		presentationNameText.setEditable(editable);

		multipleButton.setEnabled(editable);
		optionalButton.setEnabled(editable);
		plannedButton.setEnabled(editable);
//		supressedButton.setEnabled(editable);
		
		extender.updateControls(editable);
	}
	
	private class SyncFreeNameModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (getReferenceMethodElement(element) != null) {
				descriptorPropUtil.setNameRepalce((Descriptor)element, true);
				isNameReplace = true;
				updateNameRestoreBtn();
			}
		}		
	}
	
	private ModifyListener createSyncFreeNameModifyListener() {
		if (isSyncFree()) {
			return new SyncFreeNameModifyListener();
		}
		
		return null;
	}
		
	private class SyncFreePresentationNameModifyListener implements ModifyListener {
		public void modifyText(ModifyEvent e) {
			if (getReferenceMethodElement(element) != null) {
				descriptorPropUtil.setPresentationNameRepalce((Descriptor)element, true);
				isPresentationNameReplace = true;
				updatePresentationNameRestoreBtn();			
			}
		}		
	}
	
	private ModifyListener createSyncFreePresentationNameModifyListener() {
		if (isSyncFree()) {
			return new SyncFreePresentationNameModifyListener();
		}
		
		return null;
	}
	
	public static class BeGeneralSectionExtender {

		protected BreakdownElementGeneralSection section;
		public BeGeneralSectionExtender(BreakdownElementGeneralSection section) {
			this.section = section;
		}
		
		public void modifyGeneralSectionContent(FormToolkit toolkit, IActionManager actionMgr) {
		}
		
		public void dispose() {
			
		}
		
		public void refresh() {
			
		}
		
		public void updateControls(boolean editable) {
			
		}
		
		protected Composite getComposite() {
			return section.generalComposite;
		}
		
	}

}
