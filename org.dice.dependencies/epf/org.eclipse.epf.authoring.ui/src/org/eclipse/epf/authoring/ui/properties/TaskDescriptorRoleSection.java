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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter;
import org.eclipse.epf.authoring.ui.filters.DescriptorProcessFilter;
import org.eclipse.epf.authoring.ui.filters.ProcessRoleFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.command.AssignRoleToTaskDescriptor;
import org.eclipse.epf.library.edit.process.command.IActionTypeConstants;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The Task descriptor - role tab section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class TaskDescriptorRoleSection extends RelationSection {
	private IFilter roleFilter = null;

	/**
	 * Get process roledescriptor filter
	 */
	public IFilter getFilter() {
		if (roleFilter == null) {
			roleFilter = new ProcessRoleFilter(getConfiguration(), null,
					FilterConstants.ROLES);
		} else if (roleFilter instanceof DescriptorConfigurationFilter) {
			((DescriptorConfigurationFilter) roleFilter).setMethodConfiguration(getConfiguration());
		}
		return roleFilter;
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
				elements.addAll(td.getPerformedPrimarilyBy());
				
				if (isSyncFree()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(td)) {
//					elements.addAll(td.getPerformedPrimarilyByExcluded());
					
					mixWithExcluded(td, elements, UmaPackage.eINSTANCE.getTask_PerformedBy(),
							UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyByExcluded(), UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy());
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
				elements.addAll(td.getAdditionallyPerformedBy());
				
				if (isSyncFree()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(td)) {
//					elements.addAll(td.getAdditionallyPerformedByExclude());
					
					mixWithExcluded(td, elements, UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(),
							UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedByExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy());
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
						((TaskDescriptor) element).getAssistedBy()).toArray();
			}
		};
		tableViewer3.setContentProvider(contentProvider);
	}
	
	protected void initLabelProvider1() {
		ILabelProvider provider = null; 
			
		if (isSyncFree()) {
			provider = new SyncFreeLabelProvider(
				TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory(),
				(Descriptor)element,
				UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(), getConfiguration());
		} else {
			provider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());
		}
		
		tableViewer1.setLabelProvider(provider);
	}
	
	protected void initLabelProvider2() {
		ILabelProvider provider = null;
		
		if (isSyncFree()) {
			provider = new SyncFreeLabelProvider(
				TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory(),
				(Descriptor)element,
				UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy(), getConfiguration());
		} else {
			provider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());
		}
		
		tableViewer2.setLabelProvider(provider);
	}
	
	protected void initLabelProvider3() {
		ILabelProvider provider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());
		
		tableViewer3.setLabelProvider(provider);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#init()
	 */
	protected void init() {
		super.init();

//		labelProvider = new AdapterFactoryLabelProvider(
//				TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());

		int numOfTables = 3;
		
		setTabData(PropertiesResources.TaskDescriptor_Roles_SectionTitle,
				PropertiesResources.TaskDescriptor_Roles_SectionDescription,
				PropertiesResources.TaskDescriptor_Roles_Table1,
				PropertiesResources.TaskDescriptor_Roles_Table2,
				PropertiesResources.TaskDescriptor_Roles_Table3,
				null,
				FilterConstants.ROLES);
		
		boolean[] changesAllowed = { true, true, true };
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
				RoleDescriptor.class, items);

	}

	private List getRoles(List items) {
		List roleList = new ArrayList();
		if (!items.isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				roleList.add(((RoleDescriptor) items.get(i)).getRole());
			}
		}

		return roleList;
	}

	protected void addItems1(List items) {
		addItems1(items, false);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems1(java.util.List)
	 */
	protected void addItems1(List items, boolean calledForExculded) {
		if (!items.isEmpty()) {
			List elementList = getRoles(((TaskDescriptor) element)
					.getAdditionallyPerformedBy());
			elementList.addAll(getRoles(((TaskDescriptor) element)
					.getAssistedBy()));

			List newList = new ArrayList();
			for (int i = 0; i < items.size(); i++) {
				Object obj = items.get(i);
				if (obj instanceof Role) {
					Role role = (Role) obj;
					if (!elementList.contains(role))
						newList.add(role);
				}
			}

			if (newList.size() > 0) {
				AssignRoleToTaskDescriptor cmd = new AssignRoleToTaskDescriptor(
						(TaskDescriptor) element, newList,
						IActionTypeConstants.ADD_PRIMARY_PERFORMER, getConfiguration(), calledForExculded);
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
			List elementList = getRoles(((TaskDescriptor) element)
					.getPerformedPrimarilyBy());
			elementList.addAll(getRoles(((TaskDescriptor) element)
					.getAssistedBy()));
		

			List newList = new ArrayList();
			for (int i = 0; i < items.size(); i++) {
				Object obj = items.get(i);
				if (obj instanceof Role) {
					Role role = (Role) obj;
					if (!elementList.contains(role))
						newList.add(role);
				}
			}
			if (newList.size() > 0) {
				AssignRoleToTaskDescriptor cmd = new AssignRoleToTaskDescriptor(
						(TaskDescriptor) element, newList,
						IActionTypeConstants.ADD_ADDITIONAL_PERFORMER, getConfiguration(), calledForExculded);
				actionMgr.execute(cmd);
			}
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems3(java.util.List)
	 */
	protected void addItems3(List items) {
		if (!items.isEmpty()) {
			List elementList = getRoles(((TaskDescriptor) element)
					.getPerformedPrimarilyBy());
			elementList.addAll(getRoles(((TaskDescriptor) element)
					.getAdditionallyPerformedBy()));

			List newList = new ArrayList();
			for (int i = 0; i < items.size(); i++) {
				Object obj = items.get(i);
				if (obj instanceof Role) {
					Role role = (Role) obj;
					if (!elementList.contains(role))
						newList.add(role);
				}
			}
			if (newList.size() > 0) {
				AssignRoleToTaskDescriptor cmd = new AssignRoleToTaskDescriptor(
						(TaskDescriptor) element, newList,
						IActionTypeConstants.ADD_ASSISTED_BY, getConfiguration());
				actionMgr.execute(cmd);
			}
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addFromProcessItems1(java.util.List)
	 */
	protected void addFromProcessItems1(List items) {
		if (!items.isEmpty()) {
			items.removeAll(((TaskDescriptor) element)
					.getAdditionallyPerformedBy());
			items.removeAll(((TaskDescriptor) element).getAssistedBy());
			
			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE
							.getTaskDescriptor_PerformedPrimarilyBy(),
					items, -1);
			
			if (isSyncFree()) {
				for (Object item : items) {
					if (item instanceof Descriptor) {
						Descriptor des = (Descriptor) item;
						propUtil.addLocalUse(des, (Descriptor)element,
								UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy());
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
			items.removeAll(((TaskDescriptor) element).getAssistedBy());
			items.removeAll(((TaskDescriptor) element).getPerformedPrimarilyBy());

			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE
							.getTaskDescriptor_AdditionallyPerformedBy(),
					items, -1);
			
			if (isSyncFree()) {
				for (Object item : items) {
					if (item instanceof Descriptor) {
						Descriptor des = (Descriptor) item;
						propUtil.addLocalUse(des, (Descriptor)element,
								UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy());
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
			items.removeAll(((TaskDescriptor) element)
					.getAdditionallyPerformedBy());
			items.removeAll(((TaskDescriptor) element).getPerformedPrimarilyBy());

			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE.getTaskDescriptor_AssistedBy(), items,
					-1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems1(java.util.List)
	 */
	protected void removeItems1(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items, UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems2(java.util.List)
	 */
	protected void removeItems2(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items,
					UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems3(java.util.List)
	 */
	protected void removeItems3(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor)element,
					items, UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements1()
	 */
	protected List getExistingElements1() {
		return ((TaskDescriptor) element).getPerformedPrimarilyBy();
	};
	
	protected List getExistingContentElements1() {		
		List<MethodElement> list = ProcessUtil.getAssociatedElementList(getExistingElements1());
		
		TaskDescriptor td = (TaskDescriptor) element;
		if (isSyncFree()
				&& ! DescriptorPropUtil.getDesciptorPropUtil()
						.isNoAutoSyn(td)) {
//			list.addAll(td.getPerformedPrimarilyByExcluded());
			
			mixWithExcluded(td, list, UmaPackage.eINSTANCE.getTask_PerformedBy(),
					UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyByExcluded(), UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy());
		}
		return list;
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements2()
	 */
	protected List getExistingElements2() {
		return ((TaskDescriptor) element).getAdditionallyPerformedBy();
	};
	
	protected List getExistingContentElements2() {		
		List<MethodElement> list = ProcessUtil.getAssociatedElementList(getExistingElements2());
		
		TaskDescriptor td = (TaskDescriptor) element;
		if (isSyncFree()
				&& ! DescriptorPropUtil.getDesciptorPropUtil()
						.isNoAutoSyn(td)) {
//			list.addAll(td.getAdditionallyPerformedByExclude());
			
			mixWithExcluded(td, list, UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(),
					UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedByExclude(), UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy());
		}
		return list;
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements3()
	 */
	protected List getExistingElements3() {
		return ((TaskDescriptor) element).getAssistedBy();
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getFilterForDescriptors()
	 */
	protected IFilter getFilterForDescriptors() {
		return new DescriptorProcessFilter(getConfiguration()) {
			protected boolean childAccept(Object obj) {
				if (obj instanceof Activity) {
					List list = new ArrayList();
					getActivitiesInScope(TngAdapterFactory.INSTANCE
							.getWBS_ComposedAdapterFactory(), element, list);
					if (list.contains(obj))
						return true;
					else
						return false;
				}
				if (obj instanceof RoleDescriptor)
					return true;
				return false;
			}
		};
	}

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
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getDescriptorTabName()
	 */
	protected String getDescriptorTabName() {
		return FilterConstants.ROLE_DESCRIPTORS;
	}
	
	//Return true if handled in this method
	protected boolean syncFreeAdd1(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy();
		
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
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy();
		
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
	
	//Return true if handled in this method
	protected boolean syncFreeRemove1(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy();
		
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
					UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY,
					UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected boolean syncFreeRemove2(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy();
		
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
					UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY,
					UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected void syncFreeUpdateBtnStatus1(IStructuredSelection selection) {
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy();
		
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
		EReference ref = UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy();
		
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
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}

}
