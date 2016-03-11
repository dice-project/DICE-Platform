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
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter;
import org.eclipse.epf.authoring.ui.filters.DescriptorProcessFilter;
import org.eclipse.epf.authoring.ui.filters.ProcessWorkProductFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.command.AssignWPToTaskDescriptor;
import org.eclipse.epf.library.edit.process.command.IActionTypeConstants;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TaskDescriptorPropUtil;
import org.eclipse.epf.library.edit.util.WorkProductPropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The task descriptor - work product section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class TaskDescriptorWorkProductSection extends RelationSection {
	private IFilter filter = null;
	private Button ctrl_state_1, ctrl_state_2, ctrl_state_3, ctrl_state_4;
	public static final String UNASSIGN_STATE_NAME = "UnassignState"; //$NON-NLS-1$
	public static final String UNASSIGN_STATE_BODY = PropertiesResources.Process_UnassignState_Body_Text;
	public static final String UNASSIGN_STATE_DESCRIPTION = PropertiesResources.Process_UnassignState_Description;

	/**
	 * Get process work product filter
	 */
	public IFilter getFilter() {
		if (filter == null) {
			filter = new ProcessWorkProductFilter(getConfiguration(), null,
					FilterConstants.WORKPRODUCTS);
		} else if (filter instanceof DescriptorConfigurationFilter) {
			((DescriptorConfigurationFilter) filter).setMethodConfiguration(getConfiguration());
		}
		return filter;
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {

		super.createControls(parent, tabbedPropertySheetPage);

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#initContentProvider1()
	 */
	protected void initContentProvider1() {
		contentProvider = new AdapterFactoryContentProvider(getAdapterFactory()) {
			public Object[] getElements(Object object) {
				TaskDescriptor td = (TaskDescriptor) element;
				List<MethodElement> elements = new ArrayList<MethodElement>();
				elements.addAll(td.getMandatoryInput());
				
				if (isSyncFree()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(td)) {
//					elements.addAll(td.getMandatoryInputExclude());
					
					mixWithExcluded(td, elements, UmaPackage.eINSTANCE.getTask_MandatoryInput(),
							UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInputExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput());
				}
				
				return getFilteredList(elements).toArray();
			}
		};
		tableViewer1.setContentProvider(contentProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#initContentProvider2()
	 */
	protected void initContentProvider2() {
		contentProvider = new AdapterFactoryContentProvider(getAdapterFactory()) {
			public Object[] getElements(Object object) {
				TaskDescriptor td = (TaskDescriptor) element;
				List<MethodElement> elements = new ArrayList<MethodElement>();
				elements.addAll(td.getOptionalInput());
				
				if (isSyncFree()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(td)) {
//					elements.addAll(td.getOptionalInputExclude());
					
					mixWithExcluded(td, elements, UmaPackage.eINSTANCE.getTask_OptionalInput(),
							UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInputExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput());
				}
				
				return getFilteredList(elements).toArray();
			}
		};
		tableViewer2.setContentProvider(contentProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#initContentProvider3()
	 */
	protected void initContentProvider3() {
		contentProvider = new AdapterFactoryContentProvider(getAdapterFactory()) {
			public Object[] getElements(Object object) {
				return getFilteredList(
						((TaskDescriptor) element).getExternalInput())
						.toArray();
			}
		};
		tableViewer3.setContentProvider(contentProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#initContentProvider4()
	 */
	protected void initContentProvider4() {
		contentProvider = new AdapterFactoryContentProvider(getAdapterFactory()) {
			public Object[] getElements(Object object) {
				TaskDescriptor td = (TaskDescriptor) element;
				List<MethodElement> elements = new ArrayList<MethodElement>();
				elements.addAll(td.getOutput());
				
				if (isSyncFree()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(td)) {
//					elements.addAll(td.getOutputExclude());
					
					mixWithExcluded(td, elements, UmaPackage.eINSTANCE.getTask_Output(),
							UmaPackage.eINSTANCE.getTaskDescriptor_OutputExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_Output());
				}
				
				return getFilteredList(elements).toArray();
			}
		};
		tableViewer4.setContentProvider(contentProvider);
	}
	
	protected void initLabelProvider1() {
		ILabelProvider provider = getLabelProvider(
				UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput(), true);
		
		tableViewer1.setLabelProvider(provider);
	}
	
	protected void initLabelProvider2() {
		ILabelProvider provider = getLabelProvider(
				UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput(), true); 
		
		tableViewer2.setLabelProvider(provider);		
	}

	protected void initLabelProvider3() {
		ILabelProvider provider = getLabelProvider(
				UmaPackage.eINSTANCE.getTaskDescriptor_ExternalInput(), false);
		
		tableViewer3.setLabelProvider(provider);
	}
	
	protected void initLabelProvider4() {
		ILabelProvider provider = getLabelProvider(
				UmaPackage.eINSTANCE.getTaskDescriptor_Output(), true); 
		
		tableViewer4.setLabelProvider(provider);
	}
	
	private ILabelProvider getLabelProvider(final EReference ref, boolean enableSyncFree) {
		ILabelProvider provider = null;
		
		if (isSyncFree() && enableSyncFree) {
			provider = new SyncFreeLabelProvider(
				TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory(),
				(Descriptor)element, ref, getConfiguration()) {
				public String getColumnText(Object obj, int columnIndex) {
					String label = super.getColumnText(obj, columnIndex);
					if (obj instanceof WorkProductDescriptor) {					
						label = getLabelForWpd((WorkProductDescriptor)obj, label, ref);
					}
					
					return label;
				}
			};
		} else {
			provider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory()) {
				public String getColumnText(Object obj, int columnIndex) {
					String label = super.getColumnText(obj, columnIndex);
					if (obj instanceof WorkProductDescriptor) {					
						label = getLabelForWpd((WorkProductDescriptor)obj, label, ref);
					}
					
					return label;
				}
			};
		}
		
		return provider;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#init()
	 */
	protected void init() {
		super.init();

//		labelProvider = new AdapterFactoryLabelProvider(
//				TngAdapterFactory.INSTANCE.getPBS_ComposedAdapterFactory());

		int numOfTables = 4;
		setTabData(PropertiesResources.TaskDescriptor_WorkProducts_SectionTitle,
				PropertiesResources.TaskDescriptor_WorkProducts_SectionDescription,
				PropertiesResources.TaskDescriptor_WorkProducts_Table1,
				PropertiesResources.TaskDescriptor_WorkProducts_Table2,
				PropertiesResources.TaskDescriptor_WorkProducts_Table3,
				PropertiesResources.TaskDescriptor_WorkProducts_Table4,
				FilterConstants.WORKPRODUCTS);
		
		boolean[] changesAllowed = { true, true, true, true };
		setTableData(numOfTables, changesAllowed);

	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#refresh()
	 */
	public void refresh() {
		if (getElement() instanceof TaskDescriptor) {
			element = (TaskDescriptor) getElement();
		}
		super.refresh();
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getDescriptorsFromProcess()
	 */
	protected List getDescriptorsFromProcess() {
		List items = new ArrayList();
		return ProcessUtil.getElementsInScope(getAdapterFactory(), element,
				WorkProductDescriptor.class, items);
	}

	private List getWorkProducts(List items) {
		List wpList = new ArrayList();
		if (!items.isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				wpList.add(((WorkProductDescriptor) items.get(i))
						.getWorkProduct());
			}
		}

		return wpList;
	}

	protected void addItems1(List list) {
		addItems1(list, false);
	};
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems1(java.util.List)
	 */
	protected void addItems1(List items, boolean calledForExculded) {
		if (!items.isEmpty()) {
			List elementList = getWorkProducts(((TaskDescriptor) element)
					.getOptionalInput());
			elementList.addAll(getWorkProducts(((TaskDescriptor) element)
					.getExternalInput()));
			List newList = new ArrayList();
			for (int i = 0; i < items.size(); i++) {
				Object obj = items.get(i);
				if (obj instanceof WorkProduct) {
					WorkProduct wp = (WorkProduct) obj;
					if (!elementList.contains(wp))
						newList.add(wp);
				}
			}
			if (newList.size() > 0) {
				AssignWPToTaskDescriptor cmd = new AssignWPToTaskDescriptor(
						(TaskDescriptor) element, newList,
						IActionTypeConstants.ADD_MANDATORY_INPUT,
						getConfiguration(), calledForExculded);
				actionMgr.execute(cmd);
			}
		}
	};

	protected void addItems2(List items) {
		addItems2(items, false);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems2(java.util.List)
	 */
	protected void addItems2(List items, boolean calledForExculded) {
		if (!items.isEmpty()) {
			List elementList = getWorkProducts(((TaskDescriptor) element)
					.getMandatoryInput());
			elementList.addAll(getWorkProducts(((TaskDescriptor) element)
					.getExternalInput()));

			List newList = new ArrayList();
			for (int i = 0; i < items.size(); i++) {
				Object obj = items.get(i);
				if (obj instanceof WorkProduct) {
					WorkProduct wp = (WorkProduct) obj;
					if (!elementList.contains(wp))
						newList.add(wp);
				}
			}

			if (newList.size() > 0) {
				AssignWPToTaskDescriptor cmd = new AssignWPToTaskDescriptor(
						(TaskDescriptor) element, newList,
						IActionTypeConstants.ADD_OPTIONAL_INPUT,
						getConfiguration(), calledForExculded);
				actionMgr.execute(cmd);
			}
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems3(java.util.List)
	 */
	protected void addItems3(List items) {
		if (!items.isEmpty()) {
			List elementList = getWorkProducts(((TaskDescriptor) element)
					.getMandatoryInput());
			elementList.addAll(getWorkProducts(((TaskDescriptor) element)
					.getOptionalInput()));

			List newList = new ArrayList();
			for (int i = 0; i < items.size(); i++) {
				Object obj = items.get(i);
				if (obj instanceof WorkProduct) {
					WorkProduct wp = (WorkProduct) obj;
					if (!elementList.contains(wp))
						newList.add(wp);
				}
			}
			if (newList.size() > 0) {
				AssignWPToTaskDescriptor cmd = new AssignWPToTaskDescriptor(
						(TaskDescriptor) element, newList,
						IActionTypeConstants.ADD_EXTERNAL_INPUT,
						getConfiguration());
				actionMgr.execute(cmd);
			}
		}
	};

	protected void addItems4(List items) {
		addItems4(items, false);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems4(java.util.List)
	 */
	protected void addItems4(List items, boolean calledForExculded) {
		if (!items.isEmpty()) {
			AssignWPToTaskDescriptor cmd = new AssignWPToTaskDescriptor(
					(TaskDescriptor) element, items,
					IActionTypeConstants.ADD_OUTPUT, getConfiguration(), calledForExculded);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems1(java.util.List)
	 */
	protected void removeItems1(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items, UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems2(java.util.List)
	 */
	protected void removeItems2(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items, UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems3(java.util.List)
	 */
	protected void removeItems3(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items, UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems4(java.util.List)
	 */
	protected void removeItems4(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items, UmaPackage.TASK_DESCRIPTOR__OUTPUT);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements1()
	 */
	protected List getExistingElements1() {
		return ((TaskDescriptor) element).getMandatoryInput();
	};
	
	protected List getExistingContentElements1() {		
		List<MethodElement> list = ProcessUtil.getAssociatedElementList(getExistingElements1());
		
		TaskDescriptor td = (TaskDescriptor) element;
		if (isSyncFree()
				&& ! DescriptorPropUtil.getDesciptorPropUtil()
						.isNoAutoSyn(td)) {
//			list.addAll(td.getMandatoryInputExclude());
			
			mixWithExcluded(td, list, UmaPackage.eINSTANCE.getTask_MandatoryInput(),
					UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInputExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput());
		}
		return list;
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements2()
	 */
	protected List getExistingElements2() {
		return ((TaskDescriptor) element).getOptionalInput();
	};
	
	protected List getExistingContentElements2() {		
		List<MethodElement> list = ProcessUtil.getAssociatedElementList(getExistingElements2());
		
		TaskDescriptor td = (TaskDescriptor) element;
		if (isSyncFree()
				&& ! DescriptorPropUtil.getDesciptorPropUtil()
						.isNoAutoSyn(td)) {
//			list.addAll(td.getOptionalInputExclude());
			
			mixWithExcluded(td, list, UmaPackage.eINSTANCE.getTask_OptionalInput(),
					UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInputExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput());
		}
		return list;
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements3()
	 */
	protected List getExistingElements3() {
		return ((TaskDescriptor) element).getExternalInput();
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements4()
	 */
	protected List getExistingElements4() {
		return ((TaskDescriptor) element).getOutput();
	};
	
	protected List getExistingContentElements4() {		
		List<MethodElement> list = ProcessUtil.getAssociatedElementList(getExistingElements4());
		
		TaskDescriptor td = (TaskDescriptor) element;
		if (isSyncFree()
				&& ! DescriptorPropUtil.getDesciptorPropUtil()
						.isNoAutoSyn(td)) {
//			list.addAll(td.getOutputExclude());			
			
			mixWithExcluded(td, list, UmaPackage.eINSTANCE.getTask_Output(),
					UmaPackage.eINSTANCE.getTaskDescriptor_OutputExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_Output());
		}
		return list;
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getProcess()
	 */
	protected Process getProcess() {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				element, ITreeItemContentProvider.class);
		Object obj = ProcessUtil.getRootProcess(aFactory, adapter, element);
		return (Process) obj;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getFilterForDescriptors()
	 */
	protected IFilter getFilterForDescriptors() {
		return new DescriptorProcessFilter(getConfiguration()) {
			protected boolean childAccept(Object obj) {
				if (obj instanceof Activity) {
					List list1 = new ArrayList();
					getActivitiesInScope(TngAdapterFactory.INSTANCE
							.getWBS_ComposedAdapterFactory(), element, list1);
					if (list1.contains(obj))
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

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addFromProcessItems1(java.util.List)
	 */
	protected void addFromProcessItems1(List items) {
		if (!items.isEmpty()) {
			items.removeAll(((TaskDescriptor) element).getOptionalInput());
			items.removeAll(((TaskDescriptor) element).getExternalInput());
			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput(),
					items, -1);
			if (isSyncFree()) {
				for (Object item : items) {
					if (item instanceof Descriptor) {
						Descriptor des = (Descriptor) item;
						propUtil.addLocalUse(des, (Descriptor)element,
								UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput());
					}
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addFromProcessItems2(java.util.List)
	 */
	protected void addFromProcessItems2(List items) {
		if (!items.isEmpty()) {
			items.removeAll(((TaskDescriptor) element).getMandatoryInput());
			items.removeAll(((TaskDescriptor) element).getExternalInput());
			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput(),
					items, -1);
			if (isSyncFree()) {
				for (Object item : items) {
					if (item instanceof Descriptor) {
						Descriptor des = (Descriptor) item;
						propUtil.addLocalUse(des, (Descriptor)element,
								UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput());
					}
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addFromProcessItems3(java.util.List)
	 */
	protected void addFromProcessItems3(List items) {
		if (!items.isEmpty()) {
			items.removeAll(((TaskDescriptor) element).getMandatoryInput());
			items.removeAll(((TaskDescriptor) element).getOptionalInput());
			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE.getTaskDescriptor_ExternalInput(),
					items, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addFromProcessItems4(java.util.List)
	 */
	protected void addFromProcessItems4(List items) {
		if (!items.isEmpty()) {
			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE.getTaskDescriptor_Output(), items, -1);
			if (isSyncFree()) {
				for (Object item : items) {
					if (item instanceof Descriptor) {
						Descriptor des = (Descriptor) item;
						propUtil.addLocalUse(des, (Descriptor)element,
								UmaPackage.eINSTANCE.getTaskDescriptor_Output());
					}
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getDescriptorTabName()
	 */
	protected String getDescriptorTabName() {
		return FilterConstants.WORK_PRODUCT_DESCRIPTORS;
	}
	
	protected boolean syncFreeAdd1(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());	
		
		if (! result) {
			return true;
		}
		
		Object testObj = selection.getFirstElement();
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {				
			addItems1(selection.toList(), true);
			return true;
		} 
		
		return false;
	}
	
	protected boolean syncFreeAdd2(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());	
		
		if (! result) {
			return true;
		}
		
		Object testObj = selection.getFirstElement();
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {				
			addItems2(selection.toList(), true);
			return true;
		} 
		
		return false;
	}
	
	protected boolean syncFreeAdd4(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());	
		
		if (! result) {
			return true;
		}
		
		Object testObj = selection.getFirstElement();
		if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {				
			addItems4(selection.toList(), true);
			return true;
		} 
		
		return false;
	}
	
	protected boolean syncFreeRemove1(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
		
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
					UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT,
					UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected boolean syncFreeRemove2(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput();
		
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
					UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT,
					UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected boolean syncFreeRemove4(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
		
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
					UmaPackage.TASK_DESCRIPTOR__OUTPUT,
					UmaPackage.TASK_DESCRIPTOR__OUTPUT_EXCLUDE);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected void syncFreeUpdateBtnStatus1(IStructuredSelection selection) {
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
		
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

	protected void syncFreeUpdateBtnStatus2(IStructuredSelection selection) {
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());
		
		if (!result) {
			ctrl_add_2.setEnabled(false);
			ctrl_remove_2.setEnabled(false);
		} else {
			Object testObj = selection.getFirstElement();
			if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {
				ctrl_add_2.setEnabled(true);
				ctrl_remove_2.setEnabled(false);
			} else {
				ctrl_add_2.setEnabled(true);
				ctrl_remove_2.setEnabled(true);
			}
		}		
	}
	
	protected void syncFreeUpdateBtnStatus4(IStructuredSelection selection) {
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
		
		boolean result = propUtil.checkSelection(selection.toList(), (Descriptor)element, ref, getConfiguration());
		
		if (!result) {
			ctrl_add_4.setEnabled(false);
			ctrl_remove_4.setEnabled(false);
		} else {
			Object testObj = selection.getFirstElement();
			if (propUtil.isDynamicAndExclude(testObj, (Descriptor)element, ref, getConfiguration())) {
				ctrl_add_4.setEnabled(true);
				ctrl_remove_4.setEnabled(false);
			} else {
				ctrl_add_4.setEnabled(true);
				ctrl_remove_4.setEnabled(true);
			}
		}		
	}
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}
	
	public String getLabelForWpd(WorkProductDescriptor wpd, String orginalLabel, EReference ref) {
		List<Constraint> states = TaskDescriptorPropUtil.getTaskDescriptorPropUtil(actionMgr).getWpStates(
				(TaskDescriptor)element, wpd, ref);
		if (states.size() > 0) {
			String stateName = states.get(0).getBody();
			return orginalLabel + " [" + stateName + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		return orginalLabel;
	}
	
	protected void createAddtionalButton1(Composite parent) {		
		ctrl_state_1 = FormUI.createButton(toolkit, parent, PropertiesResources.Process_AssignState);
		ctrl_state_1.setEnabled(false);
		
		final EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput();
		
		ctrl_state_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assignState(tableViewer1, ref);
			}
		});		
		
		tableViewer1.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer1.getSelection();
				//need check the editable variable to support green parent scenario
				if ((selection.size() == 1) && (editable)) {
					if (isSyncFree()) {
						Object obj = selection.getFirstElement();
						if (propUtil.isDynamicAndExclude(obj, (Descriptor)element, ref, getConfiguration())) {
							ctrl_state_1.setEnabled(false);
						} else {
							ctrl_state_1.setEnabled(true);
						}
					} else {
						ctrl_state_1.setEnabled(true);
					}
				} else {
					ctrl_state_1.setEnabled(false);
				}
			}
		});
	}
	
	protected void createAddtionalButton2(Composite parent) {		
		ctrl_state_2 = FormUI.createButton(toolkit, parent, PropertiesResources.Process_AssignState);
		ctrl_state_2.setEnabled(false);
		
		final EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput();
		
		ctrl_state_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assignState(tableViewer2, ref);
			}
		});		
		
		tableViewer2.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer2.getSelection();
				if ((selection.size() == 1) && (editable)) {
					if (isSyncFree()) {
						Object obj = selection.getFirstElement();
						if (propUtil.isDynamicAndExclude(obj, (Descriptor)element, ref, getConfiguration())) {
							ctrl_state_2.setEnabled(false);
						} else {
							ctrl_state_2.setEnabled(true);
						}
					} else {
						ctrl_state_2.setEnabled(true);
					}
				} else {
					ctrl_state_2.setEnabled(false);
				}
			}
		});
	}
	
	protected void createAddtionalButton3(Composite parent) {		
		ctrl_state_3 = FormUI.createButton(toolkit, parent, PropertiesResources.Process_AssignState);
		ctrl_state_3.setEnabled(false);
		
		final EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_ExternalInput();
		
		ctrl_state_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assignState(tableViewer3, ref);
			}
		});		
		
		tableViewer3.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer3.getSelection();
				if ((selection.size() == 1) && (editable)) {
					if (isSyncFree()) {
						Object obj = selection.getFirstElement();
						if (propUtil.isDynamicAndExclude(obj, (Descriptor)element, ref, getConfiguration())) {
							ctrl_state_3.setEnabled(false);
						} else {
							ctrl_state_3.setEnabled(true);
						}
					} else {
						ctrl_state_3.setEnabled(true);
					}
				} else {
					ctrl_state_3.setEnabled(false);
				}
			}
		});
	}
	
	protected void createAddtionalButton4(Composite parent) {		
		ctrl_state_4 = FormUI.createButton(toolkit, parent, PropertiesResources.Process_AssignState);
		ctrl_state_4.setEnabled(false);
		
		final EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
		
		ctrl_state_4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assignState(tableViewer4, ref);
			}
		});		
		
		tableViewer4.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer4.getSelection();
				if ((selection.size() == 1) && (editable)) {
					if (isSyncFree()) {
						Object obj = selection.getFirstElement();
						if (propUtil.isDynamicAndExclude(obj, (Descriptor)element, ref, getConfiguration())) {
							ctrl_state_4.setEnabled(false);
						} else {
							ctrl_state_4.setEnabled(true);
						}
					} else {
						ctrl_state_4.setEnabled(true);
					}
				} else {
					ctrl_state_4.setEnabled(false);
				}
			}
		});
	}
	
	private void assignState(TableViewer viewer, EReference ref) {
		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
		
		if (selection.size() == 1) {
			if (selection.getFirstElement() instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = (WorkProductDescriptor)selection.getFirstElement();
				
				ElementListSelectionDialog dialog = new StateSelectionDialog(
						viewer.getTable().getShell(),
						getLabelProviderForStateSelectionDialog());
				dialog.setElements(getInputForStateSelectionDialog(wpd, ref).toArray());
				dialog.setMultipleSelection(false);
				dialog.setMessage(PropertiesResources.Process_SelectStateDialog_Message);
				dialog.setTitle(PropertiesResources.Process_SelectStateDialog_Title);
				dialog.setImage(null);
				if (dialog.open() == Dialog.CANCEL) {
					return;
				}
				
				Object[] objs = dialog.getResult();
				Constraint newState = (Constraint)objs[0];
				
				//clean up old state first to guarantee only one state assign to wpd
				List<Constraint> oldStates = TaskDescriptorPropUtil.getTaskDescriptorPropUtil(actionMgr).getWpStates(
						(TaskDescriptor)element, wpd, ref);
				for(Constraint oldState : oldStates) {
					TaskDescriptorPropUtil.getTaskDescriptorPropUtil(actionMgr).removeWpState(
						(TaskDescriptor)element, wpd, oldState, ref);
				}
				
				//add the new state
				if (!newState.getName().equals(UNASSIGN_STATE_NAME)) {
					TaskDescriptorPropUtil.getTaskDescriptorPropUtil(actionMgr).addWpState(
							(TaskDescriptor)element, wpd, newState, ref);
				}
				
				viewer.refresh();
			}
		}
	}
	
	private ILabelProvider getLabelProviderForStateSelectionDialog() {
		ILabelProvider provider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE.createLibraryComposedAdapterFactory()) {
			public String getText(Object element) {
				if (element instanceof Constraint) {
					return ((Constraint)element).getBody();
				}				
				return super.getText(element);
			}
		};
		
		return provider;
	}
	
	private List getInputForStateSelectionDialog(WorkProductDescriptor wpd, EReference ref) {
		List elements = new ArrayList();				
		
		List<Constraint> oldStates = TaskDescriptorPropUtil.getTaskDescriptorPropUtil(actionMgr).getWpStates(
				(TaskDescriptor)element, wpd, ref);
		WorkProduct wp = (WorkProduct) propUtil.getLinkedElement(wpd);
		if (wp != null) {
			Set<Constraint> states = WorkProductPropUtil.getWorkProductPropUtil(actionMgr).getAllStates(wp);
			states.removeAll(oldStates);
			elements.addAll(states);
		}
		
		// A special state for unassign state from wpd
		elements.add(createUnassignState());
		
		return elements;
	}
		
	public static Constraint createUnassignState() {
		Constraint constraint = UmaFactory.eINSTANCE.createConstraint();	
		constraint.setName(UNASSIGN_STATE_NAME);
		constraint.setBody(UNASSIGN_STATE_BODY);
		constraint.setBriefDescription(UNASSIGN_STATE_DESCRIPTION);
	
		return constraint;
	}
	
	public static class StateSelectionDialog extends ElementListSelectionDialog {
		private Text des;
		
		public StateSelectionDialog(Shell shell, ILabelProvider provider) {
			super(shell, provider);
		}
		
		protected Control createDialogArea(Composite parent) {
			Control con = super.createDialogArea(parent);
			
			Label desLabel = new Label((Composite)con, SWT.NULL);
			desLabel.setText(PropertiesResources.Process_SelectStateDialog_Label_Description);
			des = new Text((Composite)con, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
			{
				GridData gd = new GridData(GridData.FILL_BOTH);
				gd.heightHint = 100;
				des.setLayoutData(gd);
			}
			des.setEditable(false);
			des.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			
			addStateListeners();
			
			return con;
		}
		
		private void addStateListeners() {
			fFilteredList.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Object[] objs = getSelectedElements();
					if ((objs.length > 0) && (objs[0] instanceof Constraint)) {
						des.setText(((Constraint)objs[0]).getBriefDescription());
					}
				}				
			});
		}
	}
	
}
