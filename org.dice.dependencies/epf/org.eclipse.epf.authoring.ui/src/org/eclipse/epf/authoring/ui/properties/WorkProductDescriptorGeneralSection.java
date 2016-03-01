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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor.ModifyListener;
import org.eclipse.epf.authoring.ui.filters.DescriptorProcessFilter;
import org.eclipse.epf.authoring.ui.filters.ProcessSpecificWorkProductFilter;
import org.eclipse.epf.authoring.ui.filters.ProcessWorkProductFilter;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.command.AssignWPToDeliverable;
import org.eclipse.epf.library.edit.process.command.LinkMethodElementCommand;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The general tab section for Work Product Descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class WorkProductDescriptorGeneralSection extends
		DescriptorGeneralSection {
	protected WorkProductDescriptor element;

	private Text ctrl_method_element;

	private Text ctrl_workProduct_type;

	private Text activityEntryState, activityExitState;

	private Button linkButton;

	private Button clearButton;
	
	private Table ctrl_table_1;

	private TableViewer viewer_1;

	private Button ctrl_add_1, ctrl_add_proc_1, ctrl_remove_1;

	private Section deliverableSection;

	private ModifyListener wpModelModifyListener;
	
	protected DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#init()
	 */
	protected void init() {
		super.init();
		// get WorkProductDescriptor object
		element = (WorkProductDescriptor) getElement();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

	}

	private void toggleSection() {
		if (!(element.getWorkProduct() instanceof Deliverable)) {
			if (deliverableSection.isExpanded())
				deliverableSection.setExpanded(false);
			if (deliverableSection.isVisible())
				deliverableSection.setVisible(false);
		} else {
			if (!deliverableSection.isExpanded())
				deliverableSection.setExpanded(true);
			if (!deliverableSection.isVisible())
				deliverableSection.setVisible(true);
		}
	}

	private void createDeliverableSection(Composite composite) {
		String sectionTitle = PropertiesResources.WPDescriptor_DeliverablePart_SectionTitle; 
		String sectionDesc = PropertiesResources.WPDescriptor_DeliverablePart_SectionDescription; 
		String tableTitle = PropertiesResources.WPDescriptor_DeliverablePart_Table1; 

		if(isSyncFree()) {
			sectionDesc = sectionDesc + " " + PropertiesResources.Process_SyncFree_FontStyle; //$NON-NLS-1$
		}
		Section section = FormUI.createSection(toolkit, composite,
				sectionTitle, sectionDesc);

		// create composite
		Composite sectionComposite = FormUI.createComposite(toolkit, section,
				2, false);

		Composite pane1 = FormUI.createComposite(toolkit, sectionComposite,
				GridData.FILL_BOTH);
		FormUI.createLabel(toolkit, pane1, tableTitle);

		int tableHeight = 80;
		ctrl_table_1 = FormUI.createTable(toolkit, pane1, tableHeight);
		viewer_1 = new TableViewer(ctrl_table_1);
		initContentProvider();
		initLabelProvider();
		viewer_1.setInput(element);

		// create buttons for table2
		Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
				GridData.VERTICAL_ALIGN_CENTER
						| GridData.HORIZONTAL_ALIGN_CENTER);

		ctrl_add_1 = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Add); 
		ctrl_add_proc_1 = FormUI.createButton(toolkit, pane2,
				PropertiesResources.Process_AddFromProcess); 
		ctrl_remove_1 = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Remove); 

		toolkit.paintBordersFor(pane1);

		deliverableSection = section;
	}
	
	protected void initContentProvider() {
		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				getAdapterFactory()) {
			public Object[] getElements(Object object) {
				List newList = new ArrayList();
				List deliverableParts = element.getDeliverableParts();
				for (Iterator itor = deliverableParts.iterator(); itor.hasNext();) {
					WorkProductDescriptor wpDesc = (WorkProductDescriptor) itor.next();
					if (wpDesc.getSuperActivities() == null || ProcessUtil.isSynFree())
						newList.add(wpDesc);
				}
				
				if (isSyncFreeForDeliverable()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(element)) {
					newList.addAll(element.getDeliverablePartsExclude());
				}
				
				return getFilteredList(newList).toArray();
			}
		};
		
		viewer_1.setContentProvider(contentProvider);
	}
	
	protected void initLabelProvider() {
		ILabelProvider labelProvider = null;		
		if (isSyncFreeForDeliverable()) {
			labelProvider = new SyncFreeLabelProvider(
					TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory(),
					(Descriptor)element,
					UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts(), getConfiguration());  
		} else {
			labelProvider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory());
		}	
		
		viewer_1.setLabelProvider(labelProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#dispose()
	 */
	public void dispose() {
		super.dispose();

		// if (labelProvider != null)
		// {
		// labelProvider.dispose();
		// }
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#createGeneralSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGeneralSection(Composite composite) {
		super.createGeneralSection(composite);

		// method element
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.Process_Type_WorkProduct); 
		ctrl_method_element = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, 1);
		ctrl_method_element.setText(getMethodElementName(element));
		ctrl_method_element.setEnabled(false);

		Composite buttonComposite = FormUI.createComposite(toolkit,
				generalComposite, SWT.NONE, 2, true);
		linkButton = FormUI.createButton(toolkit, buttonComposite, SWT.PUSH, 1);
		linkButton
				.setText(PropertiesResources.Process_Button_LinkMethodElement); 

		clearButton = FormUI.createButton(toolkit, buttonComposite, SWT.PUSH, 1);
		clearButton
				.setText(PropertiesResources.Process_Button_ClearMethodElement); 

		// type
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.WorkProduct_Type); 
		ctrl_workProduct_type = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, horizontalSpan);
		ctrl_workProduct_type.setText(getMethodElementType(element));
		ctrl_workProduct_type.setEnabled(false);

		// activityEntrystate
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.WorkProductDescriptor_ActivityEntryState); 
		activityEntryState = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, horizontalSpan);

		// activityExitstate
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.WorkProductDescriptor_ActivityExitState); 
		activityExitState = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, horizontalSpan);

		// CREATE DELIVERABLE SECTION
		createDeliverableSection(composite);
		toggleSection();
	
	}

	private String getMethodElementName(WorkProductDescriptor element) {
		String str = PropertiesResources.Process_None; 
		if (element.getWorkProduct() != null) {
//			str = element.getWorkProduct().getName();
			str = TngUtil.getLabelWithPath(element.getWorkProduct());
		}

		return str;
	}

	private String getMethodElementType(WorkProductDescriptor element) {
		String str = PropertiesResources.Process_None; 
		if (element.getWorkProduct() != null) {
			str = PropertiesUtil.getType(element.getWorkProduct());
		}

		return str;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#addListeners()
	 */
	protected void addListeners() {
		super.addListeners();

		activityEntryState.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getWorkProductDescriptor_ActivityEntryState());
			}
		
			public void focusLost(FocusEvent e) {
				WorkProductDescriptor element = (WorkProductDescriptor) getElement();
				String oldContent = element.getActivityEntryState();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						activityEntryState, oldContent)) {
					return;
				}
				String newContent = StrUtil.getPlainText(activityEntryState
						.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr
							.doAction(
									IActionManager.SET,
									element,
									UmaPackage.eINSTANCE
											.getWorkProductDescriptor_ActivityEntryState(),
									newContent, -1);
					if (status) {
						activityEntryState.setText(newContent);
					}
				}
			}
		});

		activityExitState.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getWorkProductDescriptor_ActivityExitState());
			}
			
			public void focusLost(FocusEvent e) {
				WorkProductDescriptor element = (WorkProductDescriptor) getElement();
				String oldContent = element.getActivityExitState();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						activityExitState, oldContent)) {
					return;
				}
				String newContent = StrUtil.getPlainText(activityExitState
						.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr
							.doAction(
									IActionManager.SET,
									element,
									UmaPackage.eINSTANCE
											.getWorkProductDescriptor_ActivityExitState(),
									newContent, -1);
					if (status) {
						activityExitState.setText(newContent);
					}
				}
			}
		});

		ctrl_table_1.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewer_1
						.getSelection();
				if (selection.size() > 0)
					ctrl_remove_1.setEnabled(true);
			}
		});		
		
		viewer_1.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)viewer_1.getSelection();
				if ((selection.size() > 0) & editable) {
					if (isSyncFreeForDeliverable()) {
						syncFreeUpdateBtnStatus(selection);
					} else {
						ctrl_remove_1.setEnabled(true);
					}
				}
			}
		});
		
		ctrl_add_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (isSyncFreeForDeliverable()) {
					IStructuredSelection selection = (IStructuredSelection) viewer_1.getSelection();
					if (syncFreeAdd(selection)) { 
						viewer_1.refresh();
						return;
					}
				}
				
				IFilter filter = new ProcessWorkProductFilter(
						getConfiguration(), null, FilterConstants.WORKPRODUCTS); 
				// block it's deliverable parts
				List existingElements = new ArrayList();
				existingElements.addAll(ProcessUtil.getAssociatedElementList(
						((WorkProductDescriptor) element).getDeliverableParts()));
				// block itself
				existingElements.add(ProcessUtil.getAssociatedElement((WorkProductDescriptor) element));
				// also block it's parent work products, if any
				existingElements.addAll((Collection) getParentWorkProducts((WorkProductDescriptor) element));
				
				if (isSyncFreeForDeliverable()
						&& ! DescriptorPropUtil.getDesciptorPropUtil().isNoAutoSyn(element)) {
						existingElements.addAll(element.getDeliverablePartsExclude());
				}				

				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, element, FilterConstants.WORKPRODUCTS,
						existingElements);
				fd.setBlockOnOpen(true);
				fd.setTitle(FilterConstants.WORKPRODUCTS);
				fd.setEnableProcessScope(true);
				fd.setSection(getSection());
				fd.open();
				addItems(fd.getSelectedItems());
				viewer_1.refresh();

			}
		});

		ctrl_add_proc_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String tabName = FilterConstants.WORK_PRODUCT_DESCRIPTORS;
				List existingElements = new ArrayList();
				existingElements.addAll(((WorkProductDescriptor) element)
						.getDeliverableParts());
				existingElements.add(element);

				existingElements
						.addAll(getParentDeliverables((WorkProductDescriptor) element));
				Process process = TngUtil.getOwningProcess(element);

				IFilter descriptorFilter = getDescriptorFilter();
				if (descriptorFilter != null && process != null) {
					ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), descriptorFilter, process, tabName,
							existingElements);
					fd.setBlockOnOpen(true);
					fd.setTitle(tabName);
					fd.open();
					addFromProcessItems(fd.getSelectedItems());
					viewer_1.refresh();
				}
			}
		});

		ctrl_remove_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (isSyncFreeForDeliverable()) {
					IStructuredSelection selection = (IStructuredSelection) viewer_1.getSelection();
					if (syncFreeRemove(selection)) {
						viewer_1.refresh();
						ctrl_remove_1.setEnabled(false);
						return;
					}							
				} 
				
				IStructuredSelection selection = (IStructuredSelection) viewer_1
						.getSelection();
				if (selection.size() > 0) {
					// update the model
					ArrayList rmItems = new ArrayList();
					rmItems.addAll(selection.toList());
					removeItems(rmItems);
					viewer_1.refresh();

					// clear the selection
					viewer_1.setSelection(null, true);
				}
				ctrl_remove_1.setEnabled(false);
			}
		});

		linkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String tabName = FilterConstants.WORKPRODUCTS; 
				Class workProductType = null;
				if (element.getWorkProduct() != null) {
					workProductType = element.getWorkProduct().getClass();
				}
				List existingElements = new ArrayList();
				if (element.getWorkProduct() != null) {
					WorkProduct wp = element.getWorkProduct();
					existingElements.add(wp);
					if (wp instanceof Artifact) {
						List subArtifacts = new ArrayList();
						getAllSubArtifacts((Artifact) wp, subArtifacts,
								((Artifact) wp).getContainedArtifacts());
						existingElements.addAll(subArtifacts);
					}
				}

				List list = ProcessUtil
						.getSiblings(TngAdapterFactory.INSTANCE
								.getPBS_ComposedAdapterFactory(), getAdapter(),
								element);

				for (Iterator itor = list.iterator(); itor.hasNext();) {
					Object obj = itor.next();
					if (obj instanceof WorkProductDescriptor) {
						WorkProductDescriptor wpDesc = (WorkProductDescriptor) obj;
						if ((!wpDesc.equals(element))
								&& (!wpDesc.getSuppressed().booleanValue())) {
							WorkProduct wp = wpDesc.getWorkProduct();

							if (wp != null) {
								existingElements.add(wp);

								if (wp instanceof Artifact) {
									List subArtifacts = new ArrayList();
									getAllSubArtifacts((Artifact) wp,
											subArtifacts, ((Artifact) wp)
													.getContainedArtifacts());
									existingElements.addAll(subArtifacts);
								}
							}
						}
					}
				}
				IFilter filter = new ProcessSpecificWorkProductFilter(
						getConfiguration(), null, tabName, workProductType);

				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, element, tabName, existingElements);

				fd.setBlockOnOpen(true);
				fd.setViewerSelectionSingle(true);
				fd.setTitle(tabName);
				fd.setEnableProcessScope(true);
				fd.setSection(getSection());
				fd.open();
				setMethodElement(fd.getSelectedItems());

				// update method element control
				ctrl_method_element.setText(getMethodElementName(element));
				if (isSyncFree()) {
					getEditor().updateOnLinkedElementChange(element);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
		
		clearButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				actionMgr.doAction(IActionManager.SET, element,
						UmaPackage.eINSTANCE
								.getWorkProductDescriptor_WorkProduct(),
						null, -1);
				// update method element control
				ctrl_method_element.setText(getMethodElementName(element));
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
	}

	private void getAllSubArtifacts(Artifact artifact, List subArtifacts,
			List containedArtifacts) {
		for (Iterator itor = containedArtifacts.iterator(); itor.hasNext();) {
			Object obj = (Object) itor.next();
			if (obj instanceof Artifact) {
				if (!subArtifacts.contains(obj))
					subArtifacts.add(obj);
				getAllSubArtifacts((Artifact) obj, subArtifacts,
						((Artifact) obj).getContainedArtifacts());
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#updateControls()
	 */
	protected void updateControls() {
		super.updateControls();
		activityEntryState.setEditable(editable);
		activityExitState.setEditable(editable);
		linkButton.setEnabled(editable);
		clearButton.setEnabled(editable);
		activityEntryState.setEnabled(editable);
		activityExitState.setEnabled(editable);

		if (ctrl_add_1 != null)
			ctrl_add_1.setEnabled(editable);
		if (ctrl_add_proc_1 != null)
			ctrl_add_proc_1.setEnabled(editable);
		if (ctrl_remove_1 != null)
			ctrl_remove_1.setEnabled(editable);
		
		if (isSyncFree()) {
			if (element.getWorkProduct() != null) {
				linkButton.setEnabled(false);
			}
			clearButton.setEnabled(false);
		}	
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof WorkProductDescriptor) {
				element = (WorkProductDescriptor) getElement();

				super.refresh();

				// Model Modify listener for activity entry state and activity
				// exit state
				if (wpModelModifyListener != null) {
					activityEntryState
							.removeModifyListener(wpModelModifyListener);
					activityExitState
							.removeModifyListener(wpModelModifyListener);

				}

				wpModelModifyListener = getEditor().createModifyListener(
						(WorkProductDescriptor) element);

				if (wpModelModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) wpModelModifyListener)
							.setElement(element);
					((MethodElementEditor.ModifyListener) wpModelModifyListener)
							.setDisable(true);
				}

				ctrl_method_element.setText(getMethodElementName(element));
				ctrl_workProduct_type.setText(getMethodElementType(element));
				activityEntryState.setText(element.getActivityEntryState());
				activityExitState.setText(element.getActivityExitState());

				if (wpModelModifyListener instanceof MethodElementEditor.ModifyListener) {
					((MethodElementEditor.ModifyListener) wpModelModifyListener)
							.setDisable(false);
				}

				activityEntryState.addModifyListener(wpModelModifyListener);
				activityExitState.addModifyListener(wpModelModifyListener);
				
				initContentProvider();
				initLabelProvider();

				if (viewer_1 != null) {
					viewer_1.refresh();
				}

				// hide/show certain sections.
				toggleSection();

			}

		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing WorkProductDescriptor general section : " + element, ex); //$NON-NLS-1$
		}
	}
	
	private void addItems(List items) {
		addItems(items, false);
	}

	private void addItems(List items, boolean calledForExculded) {
		if (items != null) {
			List wps = new ArrayList();

			for (Iterator itor = items.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if ((obj instanceof WorkProduct)
						&& (!isPartOfDeliverable((WorkProduct) obj,
								(WorkProductDescriptor) element))) {
					wps.add((WorkProduct) obj);

				}
			}
			if (!wps.isEmpty()) {
				AssignWPToDeliverable cmd = new AssignWPToDeliverable(
						(WorkProductDescriptor) element, wps, calledForExculded);
				actionMgr.execute(cmd);
			}
		}
		viewer_1.refresh();
	}

	protected IFilter getDescriptorFilter() {

		return new DescriptorProcessFilter(getConfiguration()) {
			protected boolean childAccept(Object obj) {
				if (obj instanceof Activity) {
					List list = new ArrayList();
					getActivitiesInScope(TngAdapterFactory.INSTANCE
							.getPBS_ComposedAdapterFactory(), element, list);
					if (list.contains(obj))
						return true;
					else
						return false;
				}
				if (obj instanceof WorkProductDescriptor)
					return true;
				return false;
			}
		};
	}

	private void addFromProcessItems(List items) {
		if (items != null) {
			for (Iterator itor = items.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof WorkProductDescriptor) {
					WorkProduct wp = ((WorkProductDescriptor) obj)
							.getWorkProduct();
					if (wp != null) {
						if (!isPartOfDeliverable(wp,
								(WorkProductDescriptor) element)) {
							WorkProductDescriptor wpDesc = ProcessUtil
									.createWorkProductDescriptor(wp);
							actionMgr
									.doAction(
											IActionManager.ADD,
											element,
											UmaPackage.eINSTANCE
													.getWorkProductDescriptor_DeliverableParts(),
											wpDesc, -1);
						}
						actionMgr
								.doAction(
										IActionManager.ADD,
										element,
										UmaPackage.eINSTANCE
												.getWorkProductDescriptor_DeliverableParts(),
										obj, -1);
						if (isSyncFreeForDeliverable()) {
							propUtil.addLocalUse((Descriptor)obj, element,
									UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());								
						}						
					} else {
						MessageFormat mf = new MessageFormat(
								PropertiesResources.Process_DeliverableAssignError); 
						Object[] args = {
								((WorkProductDescriptor) obj).getName(),
								((WorkProductDescriptor) element).getName() };
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayInfo(
										PropertiesResources.Process_AssignmentInfoTitle, mf.format(args)); 
					}
				}
			}
		}

	}

	private void removeItems(List items) {
		if (!items.isEmpty()) {
			for (Iterator itor = items.iterator(); itor.hasNext();) {
				Object obj = (Object) itor.next();
				if (obj instanceof WorkProductDescriptor) {
					actionMgr
							.doAction(
									IActionManager.REMOVE,
									element,
									UmaPackage.eINSTANCE
											.getWorkProductDescriptor_DeliverableParts(),
									(WorkProductDescriptor) obj, -1);
					
					if (isSyncFreeForDeliverable()) {
						propUtil.removeLocalUse((Descriptor)obj, element,
								UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
					}
				}

				// find matching deliverable parts
				Object wpDesc = findDeliverableParts((Object) obj);
				actionMgr.doAction(IActionManager.REMOVE, element,
						UmaPackage.eINSTANCE
								.getWorkProductDescriptor_DeliverableParts(),
						wpDesc, -1);
			}
		}
	}

	private Object findDeliverableParts(Object obj) {
		List parts = ((WorkProductDescriptor) element).getDeliverableParts();

		for (Iterator itor = parts.iterator(); itor.hasNext();) {
			Object itorObject = itor.next();
			if (obj instanceof WorkProductDescriptor) {
				if (itorObject instanceof WorkProductDescriptor) {
					Object objWP = ((WorkProductDescriptor) obj)
							.getWorkProduct();
					Object itorWP = ((WorkProductDescriptor) itorObject)
							.getWorkProduct();
					if (objWP.equals(itorWP)) {
						return itorObject;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Find parent process
	 * 
	 * @param element
	 * @return
	 */
	protected Object getProcess(BreakdownElement brElement) {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Process)) {
			brElement = (BreakdownElement) parent;
			adapter = (ItemProviderAdapter) aFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}
		return parent;
	}

	/**
	 * Get parent deliverables
	 * 
	 * @param brElement
	 * @return
	 */
	private List getParentDeliverables(BreakdownElement brElement) {
		List parentDeliverables = new ArrayList();
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Activity)) {
			brElement = (BreakdownElement) parent;
			if (parent instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpdesc = (WorkProductDescriptor) parent;
				if (wpdesc.getWorkProduct() instanceof Deliverable) {
					parentDeliverables.add(wpdesc);
				}
			}
			adapter = (ItemProviderAdapter) aFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}
		return parentDeliverables;
	}
	/**
	 * Find all sibling work products
	 * 
	 * @param element
	 * @return
	 */
	private Object getParentWorkProducts(BreakdownElement brElement) {
		HashSet workProducts = new HashSet();

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Activity)) {
			brElement = (BreakdownElement) parent;
			if (parent instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpdesc = (WorkProductDescriptor) parent;
				if (wpdesc.getWorkProduct() instanceof Deliverable) {
					workProducts.add(wpdesc.getWorkProduct());
				}
			}
			adapter = (ItemProviderAdapter) aFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}

		if (parent instanceof Activity) {
			List brElements = ((Activity) parent).getBreakdownElements();
			for (Iterator itor = brElements.iterator(); itor.hasNext();) {
				Object obj = (Object) itor.next();
				if (obj instanceof WorkProductDescriptor) {
					WorkProduct wp = ((WorkProductDescriptor) obj)
							.getWorkProduct();
					if (wp != null)
						workProducts.add(wp);
				}
			}
		}
		return workProducts;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.DescriptorGeneralSection#getNamePrefix()
	 */
	public String getNamePrefix() {
		return LibraryUIText.TEXT_WORK_PRODUCT_DESCRIPTOR + ": "; //$NON-NLS-1$
	}

	private void setMethodElement(List items) {
		if ((items != null) && (items.size() >= 1)) {
			if (items.get(0) instanceof WorkProduct) {
				WorkProduct wp = (WorkProduct) items.get(0);

//				List list = ProcessUtil
//						.getSiblings(TngAdapterFactory.INSTANCE
//								.getPBS_ComposedAdapterFactory(), getAdapter(),
//								element);
				boolean canAssign = true;
//				for (Iterator itor = list.iterator(); itor.hasNext();) {
//					Object obj = itor.next();
//					if (obj instanceof WorkProductDescriptor) {
//						WorkProductDescriptor wpDesc = (WorkProductDescriptor) obj;
//						if ((!wpDesc.equals(element))
//								&& (!wpDesc.getSuppressed().booleanValue())) {
//							WorkProduct exisingWP = wpDesc.getWorkProduct();
//							if (wp.equals(exisingWP)) {
//								canAssign = false;
//								break;
//							}
//						}
//					}
//				}
				if (canAssign) {
					LinkMethodElementCommand cmd = new LinkMethodElementCommand(
							element, wp,
							UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT);
					actionMgr.execute(cmd);

					// set selection to same element to enable correct actions
					getEditor().setSelection(getSelection());
				} else {
					MessageFormat mf = new MessageFormat(PropertiesResources.Process_InvalidLinkMethodElement); 
					Object[] args = { wp.getName() };
					AuthoringUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayInfo(
									PropertiesResources.Process_LinkMethodElementTitle, mf.format(args)); 
				}
			}
		}
	}

	private boolean isPartOfDeliverable(WorkProduct wp,
			WorkProductDescriptor deliverable) {

		List deliverableParts = ProcessUtil
				.getAssociatedElementList(deliverable.getDeliverableParts());
		if (deliverableParts.contains(wp))
			return true;

		return false;
	}
	
	//Use this method to handle Deliverable under sync-free condition
	private boolean isSyncFreeForDeliverable() {
		return (element.getWorkProduct() instanceof Deliverable) && isSyncFree();
	}
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}
	
	protected boolean syncFreeAdd(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());	
		
		if (! result) {
			return true;
		}
		
		Object testObj = selection.getFirstElement();
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {				
			addItems(selection.toList(), true);
			return true;
		} 
		
		return false;
	}
	
	protected boolean syncFreeRemove(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());
		if (! result) {
			return true;
		}

		Object testObj = selection.getFirstElement();
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {
			return true;
		} 
		
		if (propUtil.isDynamic(testObj, (Descriptor)element, ref)) {
			MoveDescriptorCommand cmd = new MoveDescriptorCommand((Descriptor)element, selection.toList(),
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS,
					UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS_EXCLUDE);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected void syncFreeUpdateBtnStatus(IStructuredSelection selection) {
		EReference ref = UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());
		
		if (!result) {
			ctrl_add_1.setEnabled(false);
			ctrl_remove_1.setEnabled(false);
		} else {
			Object testObj = selection.getFirstElement();
			if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {
				ctrl_add_1.setEnabled(true);
				ctrl_remove_1.setEnabled(false);
			} else {
				ctrl_add_1.setEnabled(true);
				ctrl_remove_1.setEnabled(true);
			}
		}		
	}
	
}