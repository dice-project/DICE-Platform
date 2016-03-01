//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider;
import org.eclipse.epf.library.edit.IConfigurationApplicator;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The task descriptor - step section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class TaskDescriptorStepSection extends AbstractSection {
	private TaskDescriptor element;

	private IActionManager actionMgr;

	private FormToolkit toolkit;

	private Button ctrl_add, ctrl_remove, ctrl_up, ctrl_down;

	private Table ctrl_selected;

	private TableViewer viewer;

	// private Text ctrl_brief_desc;
	private Image titleImage;


	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {

		super.createControls(parent, tabbedPropertySheetPage);
		init();

		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		// create steps section
		createStepSection(parent);

		addListeners();

	}

	/**
	 * Initialize
	 *
	 */
	private void init() {
		// get activity object
		element = (TaskDescriptor) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof TaskDescriptor) {
				element = (TaskDescriptor) getElement();
			
				// hide/refresh controls
				updateControls();
				
				if (isSyncFree()) {
					syncFreeUpdateControls();
				}
				
				initContentProvider();
				initLabelProvider();
				
				viewer.refresh();
			}
		} catch (Exception ex) {
			logger.logError("Error refreshing TaskDescriptor step section" + element, ex);  //$NON-NLS-1$
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * Update controls based on editable flag. Controls will be either editable 
	 * or read-only after updating this.
	 */
	public void updateControls() {
		ctrl_add.setEnabled(editable);
		if (element.getTask() == null)
			ctrl_add.setEnabled(false);
		else {
			List steps = getSteps();
			if (steps != null && steps.size() > 0) 
				ctrl_add.setEnabled(true);
			else 
				ctrl_add.setEnabled(false);
		}
		
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove.setEnabled(editable);
			ctrl_up.setEnabled(editable);
			ctrl_down.setEnabled(editable);
		} else {
			ctrl_remove.setEnabled(false);
			ctrl_up.setEnabled(false);
			ctrl_down.setEnabled(false);

		}
	}
		
	/**
	 * Create Step section on the given composite
	 * @param composite
	 */
	private void createStepSection(Composite composite) {
		// create step section
		String sectionDesc = PropertiesResources.TaskDescriptor_stepInformationDescription; 
		Section stepSection = FormUI
				.createSection(
						toolkit,
						composite,
						PropertiesResources.TaskDescriptor_stepInformationTitle, 
						sectionDesc); 

		// create step composite
		Composite stepComposite = FormUI.createComposite(toolkit, stepSection,
				2, false);

		// create table composite
		Composite pane1 = FormUI.createComposite(toolkit, stepComposite,
				GridData.FILL_BOTH);
		FormUI.createLabel(toolkit, pane1, PropertiesResources.TaskDescriptor_Selected_Steps); 

		ctrl_selected = FormUI.createTable(toolkit, pane1);
		viewer = new TableViewer(ctrl_selected);
		initContentProvider();
		initLabelProvider();
		viewer.setInput(element);

		// create button composite
		Composite pane2 = FormUI.createComposite(toolkit, stepComposite,
				GridData.VERTICAL_ALIGN_CENTER
						| GridData.HORIZONTAL_ALIGN_CENTER);

		// create buttons
		ctrl_add = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Add);
		ctrl_add.setEnabled(false);
		ctrl_remove = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Remove); 
		ctrl_remove.setEnabled(false);
		ctrl_up = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Up); 
		ctrl_down = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Down); 

		if (element.getTask() != null) {
			List steps = getSteps();
			if (steps != null && steps.size() > 0) {
				ctrl_add.setEnabled(true);
			}
		}
		// create brief description
		// int heightHint = 40;
		// int horizontalSpan = 2;
		// Label label = FormUI.createLabel(toolkit, stepComposite,
		// PropertiesResources.getString("Process.briefDescription"),
		// heightHint);
		// ctrl_brief_desc = FormUI.createText(toolkit, stepComposite,
		// heightHint, heightHint);
		// ctrl_brief_desc.setEditable(false);
		//		
		toolkit.paintBordersFor(stepComposite);
		toolkit.paintBordersFor(pane1);
	}

	/**
	 * Add listeners
	 */
	private void addListeners() {
		// Add focus listener on primary tasks list
		ctrl_selected.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if ((selection.size() > 0) && editable) {
					ctrl_remove.setEnabled(editable);
					ctrl_up.setEnabled(editable);
					ctrl_down.setEnabled(editable);
				}
				
				if (isSyncFree()) {
					syncFreeUpdateControls();
				}
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if ((selection.size() > 0) && editable) {
					ctrl_remove.setEnabled(true);

					if (viewer.getTable().getSelectionIndex() > 0
							&& selection.size() == 1) {
						ctrl_up.setEnabled(true);
					} else
						ctrl_up.setEnabled(false);

					if (viewer.getTable().getSelectionIndex() < viewer
							.getTable().getItemCount() - 1
							&& selection.size() == 1) {
						ctrl_down.setEnabled(true);
					} else
						ctrl_down.setEnabled(false);

				}

				if (selection.size() == 1) {
					String desc = ((MethodElement) selection.getFirstElement())
							.getBriefDescription();
					if (desc == null) {
						desc = ""; //$NON-NLS-1$
					}
					// ctrl_brief_desc.setText(desc);
				} else if (selection.size() > 1) {
					// ctrl_brief_desc.setText(PropertiesResources.getString("Process.MultipleSelection")
					// + " - " + selection.size());
				}
				
				if (isSyncFree()) {
					syncFreeUpdateControls();
				}
			}
		});

		ctrl_add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (isSyncFree()) {
					IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();					
					addSteps(selection.toArray());			
					viewer.refresh();
					syncFreeUpdateControls();
					
					return;
				}
				
				IStructuredContentProvider stepContentProvider = new AdapterFactoryContentProvider(
						TngAdapterFactory.INSTANCE
								.getNavigatorView_ComposedAdapterFactory()) {
					public Object[] getElements(Object object) {

						return getSteps().toArray();
//						// get all steps
//						IConfigurationApplicator configApplicator = Providers
//								.getConfigurationApplicator();
//						MethodConfiguration config = TngUtil.getOwningProcess(
//								element).getDefaultContext();
//						List steps = (List) configApplicator.getReference(
//								element.getTask(), UmaPackage.eINSTANCE
//										.getTask_Steps(), config);
//
//						// remove already existing steps
//						steps.removeAll(element.getSelectedSteps());
//						return steps.toArray();
					}
				};

				ILabelProvider stepLabelProvider = new VariabilityElementLabelProvider(
						TngAdapterFactory.INSTANCE
								.getNavigatorView_ComposedAdapterFactory()) {

					public boolean isExternal(Object obj) {

						boolean flag = !element.getTask().getPresentation()
								.getSections().contains(obj);
						return flag;
					}
				};

				ListSelectionDialog dlg = new ListSelectionDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						element.getTask(), stepContentProvider,
						stepLabelProvider, PropertiesResources.TaskDescriptor_StepDialogMessage); 
				titleImage = Display.getCurrent().getActiveShell().getImage();
				ListSelectionDialog.setDefaultImage(titleImage);
				dlg.setTitle(PropertiesResources.TaskDescriptor_StepDialogTitle);
				dlg.setBlockOnOpen(true);
				dlg.open();

				addSteps(dlg.getResult());
				
				// enable/disable add button
				List steps = getSteps();
				if (steps != null && steps.size() > 0) 
					ctrl_add.setEnabled(true);
				else 
					ctrl_add.setEnabled(false);
				
				viewer.refresh();
			}
		});

		ctrl_remove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() > 0) {
					// update the model
					ArrayList rmItems = new ArrayList();
					rmItems.addAll(selection.toList());
					removeSteps(rmItems);
					viewer.refresh();

					// clear the selection
					viewer.setSelection(null, true);
					// ctrl_brief_desc.setText("");
				}
				// ctrl_remove.setEnabled(false);
				
				// enable/disable add button
				List steps = getSteps();
				if (steps != null && steps.size() > 0) 
					ctrl_add.setEnabled(true);
				else 
					ctrl_add.setEnabled(false);
				
				if (isSyncFree()) {
					syncFreeUpdateControls();
				}
			}
		});

		ctrl_up.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ArrayList steps = new ArrayList();
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() > 0) {
					ArrayList items = new ArrayList();
					items.addAll(selection.toList());
					BasicEList stepList = (BasicEList) element
							.getSelectedSteps();

					for (Iterator it = items.iterator(); it.hasNext();) {
						Object object = it.next();
						int index = stepList.indexOf(object);
						if (index > 0)
							stepList.move(index - 1, object);

					}
					steps.addAll(stepList);
					// TODO - write a command. 
					// activate dirty flag
					actionMgr.doAction(IActionManager.REMOVE_MANY, element,
							UmaPackage.eINSTANCE
									.getTaskDescriptor_SelectedSteps(),
							stepList, -1);
					actionMgr.doAction(IActionManager.ADD_MANY, element,
							UmaPackage.eINSTANCE
									.getTaskDescriptor_SelectedSteps(), steps,
							-1);

					viewer.refresh();
				}

				if (viewer.getTable().getSelectionIndex() > 0 && editable)
					ctrl_up.setEnabled(true);
				else
					ctrl_up.setEnabled(false);

				if (viewer.getTable().getSelectionIndex() < viewer.getTable()
						.getItemCount() - 1
						&& editable)
					ctrl_down.setEnabled(true);
				else
					ctrl_down.setEnabled(false);
			}

		});

		ctrl_down.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ArrayList steps = new ArrayList();
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() > 0) {
					ArrayList items = new ArrayList();
					items.addAll(selection.toList());
					BasicEList stepList = (BasicEList) element
							.getSelectedSteps();

					for (Iterator it = items.iterator(); it.hasNext();) {
						Object object = it.next();
						int index = stepList.indexOf(object);

						if (index < stepList.size() - 1)
							stepList.move(index + items.size(), object);

					}
					steps.addAll(stepList);

					// TODO - write a cmmand. Hacking it for now to activate
					// dirty flag
					actionMgr.doAction(IActionManager.REMOVE_MANY, element,
							UmaPackage.eINSTANCE
									.getTaskDescriptor_SelectedSteps(),
							stepList, -1);
					actionMgr.doAction(IActionManager.ADD_MANY, element,
							UmaPackage.eINSTANCE
									.getTaskDescriptor_SelectedSteps(), steps,
							-1);

					viewer.refresh();
				}

				if (viewer.getTable().getSelectionIndex() > 0 && editable)
					ctrl_up.setEnabled(true);
				else
					ctrl_up.setEnabled(false);

				if (viewer.getTable().getSelectionIndex() < viewer.getTable()
						.getItemCount() - 1
						&& editable)
					ctrl_down.setEnabled(true);
				else
					ctrl_down.setEnabled(false);

			}

		});
	}

	/**
	 * Assign Steps to task descriptor
	 * @param addItems
	 * 			List of steps
	 */
	private void addSteps(Object[] addItems) {
		// update the model
		if (addItems != null) {
			for (int i = 0; i < addItems.length; i++) {
				org.eclipse.epf.uma.Section item = (org.eclipse.epf.uma.Section) addItems[i];

				actionMgr.doAction(IActionManager.ADD, element,
						UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps(),
						item, -1);
				if (isSyncFree()) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getTaskDescriptor_SelectedStepsExclude(),
							item, -1);
					
					DescriptorPropUtil propUtil = DescriptorPropUtil
							.getDesciptorPropUtil(actionMgr);
					TaskDescriptor greenParent = (TaskDescriptor) propUtil
							.getGreenParentDescriptor(element);
					if (greenParent != null) {
						EReference eRef = UmaPackage.eINSTANCE
								.getTaskDescriptor_SelectedStepsExclude();
						List<org.eclipse.epf.uma.Section> parentExecludeList = greenParent
								.getSelectedStepsExclude();
						propUtil.removeGreenRefDelta(element, item, eRef, true);
						if (parentExecludeList != null
								&& parentExecludeList.contains(item)) {
							propUtil.addGreenRefDelta(element, item, eRef,
									false);
						}

					}
				}
			}
		}
	}

	/**
	 * Remove assigned steps for the task descriptor
	 * @param rmItems
	 * 			List of steps 
	 */
	private void removeSteps(List rmItems) {
		// update the model
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				org.eclipse.epf.uma.Section item = (org.eclipse.epf.uma.Section) it.next();

				actionMgr.doAction(IActionManager.REMOVE, element,
						UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps(),
						item, -1);
				if (isSyncFree()) {
					actionMgr.doAction(IActionManager.ADD, element,
							UmaPackage.eINSTANCE.getTaskDescriptor_SelectedStepsExclude(),
							item, -1);
					
					DescriptorPropUtil propUtil = DescriptorPropUtil
							.getDesciptorPropUtil(actionMgr);
					TaskDescriptor greenParent = (TaskDescriptor) propUtil
							.getGreenParentDescriptor(element);
					if (greenParent != null) {
						EReference eRef = UmaPackage.eINSTANCE
								.getTaskDescriptor_SelectedStepsExclude();
						List<org.eclipse.epf.uma.Section> parentExecludeList = greenParent
								.getSelectedStepsExclude();
						propUtil.removeGreenRefDelta(element, item, eRef, false);
						if (parentExecludeList == null
								|| ! parentExecludeList.contains(item)) {
							propUtil.addGreenRefDelta(element, item, eRef,
									true);
						}

					}
				}
			}
		}
	}
	
	/**
	 * Get steps that could be assigned to this task descriptor
	 * @return
	 */
	private List getSteps(){
		//	get all steps
		IConfigurationApplicator configApplicator = Providers
				.getConfigurationApplicator();
		MethodConfiguration config = TngUtil.getOwningProcess(
				element).getDefaultContext();
		List steps = (List) configApplicator.getReference(
				element.getTask(), UmaPackage.eINSTANCE
						.getTask_Steps(), config);

		// remove already existing steps
		steps.removeAll(element.getSelectedSteps());
		return steps;
	}
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}
	
	private void initContentProvider() {
		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				List elements = new ArrayList();				
				elements.addAll(element.getSelectedSteps());
				
				if (isSyncFree() && !DescriptorPropUtil.getDesciptorPropUtil().isNoAutoSyn(element)) {
					
					if (element.getTask() != null) {
						Set validValueSet = new HashSet();
						validValueSet.addAll(element.getTask().getSteps());
						removeOutdatedReferences(element, UmaPackage.eINSTANCE.getTaskDescriptor_SelectedStepsExclude(), validValueSet);
					}
					
					//This is a work around on UI for data layer under sync free
					elements = syncFreeReOrder(elements);
					
					elements.addAll(element.getSelectedStepsExclude());
				}
				
				return elements.toArray();
			}
		};
		
		viewer.setContentProvider(contentProvider);
	}
	
	private List syncFreeReOrder(List steps) {
		List result = new ArrayList();
		
		if (element.getTask() != null) {
			List originalSteps = element.getTask().getSteps();
			
			for (Object obj : originalSteps) {
				if (steps.contains(obj)) {
					result.add(obj);
				}
			}
		} else {
			result = steps;
		}
		
		return result;
	}
	
	private void initLabelProvider() {
		ILabelProvider labelProvider = null;
		
		if (isSyncFree()) {
			labelProvider = new StepsSyncFreeLabelProvider(
					TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory(),
					element,
					UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps(),
					getConfiguration()
					);			
		} else {
			labelProvider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory());
		}
		
		viewer.setLabelProvider(labelProvider);
	}
	
	private void syncFreeUpdateControls() {
		ctrl_up.setEnabled(false);
		ctrl_down.setEnabled(false);
		
		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
		if ((selection.size() > 0) && (editable)) {
			if (isAllDynamic(selection)) {
				ctrl_add.setEnabled(false);
				ctrl_remove.setEnabled(true);
			} else if (isAllExcluded(selection)) {
				ctrl_add.setEnabled(true);
				ctrl_remove.setEnabled(false);
			} else {
				ctrl_add.setEnabled(false);
				ctrl_remove.setEnabled(false);
			}			
		} else {
			ctrl_add.setEnabled(false);
			ctrl_remove.setEnabled(false);
		}
	}
	
	private boolean isAllDynamic(IStructuredSelection selection) {
		List<org.eclipse.epf.uma.Section> allDynamic = element.getSelectedSteps();
		
		for (Object obj : selection.toArray()) {
			if (obj instanceof org.eclipse.epf.uma.Section) {
				if (!allDynamic.contains((org.eclipse.epf.uma.Section)obj)) {
					return false;
				}				
			}
		}
		
		return true;	
	}
	
	private boolean isAllExcluded(IStructuredSelection selection) {
		List<org.eclipse.epf.uma.Section> allExcluded = element.getSelectedStepsExclude();
		
		for (Object obj : selection.toArray()) {
			if (obj instanceof org.eclipse.epf.uma.Section) {
				if (!allExcluded.contains((org.eclipse.epf.uma.Section)obj)) {
					return false;
				}				
			}
		}
		
		return true;
	}
	
	public class StepsSyncFreeLabelProvider extends AdapterFactoryLabelProvider implements ITableFontProvider {
		private DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();		
		private Font systemFont = Display.getCurrent().getSystemFont();;
		
		private Descriptor desc;
		private EReference ref;
		private MethodConfiguration config;

		public StepsSyncFreeLabelProvider(AdapterFactory adapterFactory, Descriptor desc, EReference ref, MethodConfiguration config) {
			super(adapterFactory);
			this.desc = desc;
			this.ref = ref;		
			this.config = config;
		}
	    
	    public Font getFont(Object obj, int columnIndex) {
	    	return systemFont;
	    }	    	
	    
	    public String getColumnText(Object obj, int columnIndex) {
	    	String original = super.getColumnText(obj, columnIndex);
	    	
	    	if (propUtil.isDynamicAndExclude(obj, desc, ref, config)) {
	    		return "--<" + original + ">";	    		 //$NON-NLS-1$ //$NON-NLS-2$
	    	}
	    	
	    	return original;	    	
	    }
	}
	
}
