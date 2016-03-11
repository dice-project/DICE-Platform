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
import org.eclipse.epf.authoring.ui.filters.ProcessWorkProductFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.command.AssignWPToRoleDescriptor;
import org.eclipse.epf.library.edit.process.command.IActionTypeConstants;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * Role Descriptor - work product section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class RoleDescriptorWorkProductSection extends RelationSection {
	private IFilter filter = null;

	/**
	 * Get process work product filter
	 */
	public IFilter getFilter() {
		if (filter == null) {
			filter = new ProcessWorkProductFilter(getConfiguration(),
					null, FilterConstants.WORKPRODUCTS);
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
				RoleDescriptor td = (RoleDescriptor) element;
				List<MethodElement> elements = new ArrayList<MethodElement>();
				elements.addAll(td.getResponsibleFor());
				
				if (isSyncFree()
						&& ! DescriptorPropUtil.getDesciptorPropUtil()
								.isNoAutoSyn(td)) {
					elements.addAll(td.getResponsibleForExclude());
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
				return getFilteredList(((RoleDescriptor) element).getModifies())
						.toArray();
			}
		};
		tableViewer2.setContentProvider(contentProvider);
	}
	
	protected void initLabelProvider1() {
		ILabelProvider provider = null; 
		
		if (isSyncFree()) {
			provider = new SyncFreeLabelProvider(
				TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(),
				(Descriptor)element,
				UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor(), getConfiguration());
		} else {
			provider = new AdapterFactoryLabelProvider(
					TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory());
		}
		
		tableViewer1.setLabelProvider(provider);
	}
	
	protected void initLabelProvider2() {
		ILabelProvider provider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory());
		
		tableViewer2.setLabelProvider(provider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#init()
	 */
	protected void init() {
		super.init();

//		labelProvider = new AdapterFactoryLabelProvider(
//				TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory());

		int numOfTables = 2;
		
		setTabData(PropertiesResources.RoleDescriptor_WorkProducts_SectionTitle,
				PropertiesResources.RoleDescriptor_WorkProducts_SectionDescription,
				PropertiesResources.RoleDescriptor_WorkProducts_Table1,
				PropertiesResources.RoleDescriptor_WorkProducts_Table2,
				null,
				null,
				FilterConstants.WORKPRODUCTS); 

		boolean[] changesAllowed = { true, false };
		setTableData(numOfTables, changesAllowed);
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#refresh()
	 */
	public void refresh() {
		if (getElement() instanceof RoleDescriptor) {
			element = (RoleDescriptor) getElement();
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

	protected void addItems1(List items) {
		addItems1(items, false);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems1(java.util.List)
	 */
	protected void addItems1(List items, boolean calledForExculded) {
		if (!items.isEmpty()) {
			AssignWPToRoleDescriptor cmd = new AssignWPToRoleDescriptor(
					(RoleDescriptor) element, items,
					IActionTypeConstants.ADD_RESPONSIBLE_FOR, getConfiguration(), calledForExculded);
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems1(java.util.List)
	 */
	protected void removeItems1(List items) {
		if (!items.isEmpty()) {
			RemoveDescriptorCommand cmd = new RemoveDescriptorCommand((Descriptor) element,
					items, UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR);
			actionMgr.execute(cmd);
		}
	};

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

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getExistingElements1()
	 */
	protected List getExistingElements1() {
//		return getWorkProducts(((RoleDescriptor) element).getResponsibleFor());
		return ((RoleDescriptor) element).getResponsibleFor();
	};
	
	protected List getExistingContentElements1() {		
		List<MethodElement> list = ProcessUtil.getAssociatedElementList(getExistingElements1());
		
		RoleDescriptor rd = (RoleDescriptor) element;
		if (isSyncFree()
				&& ! DescriptorPropUtil.getDesciptorPropUtil()
						.isNoAutoSyn(rd)) {
			list.addAll(rd.getResponsibleForExclude());
		}
		return list;
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getProcess()
	 */
	protected Process getProcess() {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
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
					List list = new ArrayList();
					getActivitiesInScope(TngAdapterFactory.INSTANCE
							.getOBS_ComposedAdapterFactory(), element, list);
					if (list.contains(obj))
						return true;
					else
						return false;
				}
				// Uncomment to show extended activity's workproducts
				// if(obj instanceof WorkProductDescriptorWrapperItemProvider){
				// Object object =
				// ((BreakdownElementWrapperItemProvider)obj).getParent(obj);
				// List list = new ArrayList();
				// getActivitiesInScope(
				// TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory(),
				// element, list);
				// if(list.contains(object)) return true;
				// }
				if (obj instanceof WorkProductDescriptor)
					return true;
				return false;
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getDescriptorTabName()
	 */
	protected String getDescriptorTabName() {
		return FilterConstants.WORK_PRODUCT_DESCRIPTORS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addFromProcessItems1(java.util.List)
	 */
	protected void addFromProcessItems1(List items) {
		if (!items.isEmpty()) {
			actionMgr.doAction(IActionManager.ADD_MANY, element,
					UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor(),
					items, -1);
			if (isSyncFree()) {
				for (Object item : items) {
					if (item instanceof Descriptor) {
						Descriptor des = (Descriptor) item;
						propUtil.addLocalUse(des, (Descriptor)element,
								UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor());
					}
				}
			}
		}
	}
	
	protected boolean syncFreeAdd1(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return false;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor();
		
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
	
	protected boolean syncFreeRemove1(IStructuredSelection selection) {
		if (selection.size() == 0) {
			return true;			
		} 
		
		EReference ref = UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor();
		
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
					UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR,
					UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR_EXCLUDE);
			actionMgr.execute(cmd);
			return true;
		} 
				
		return false;
	}
	
	protected void syncFreeUpdateBtnStatus1(IStructuredSelection selection) {
		EReference ref = UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor();
		
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
	
	protected boolean isSyncFree() {
		return ProcessUtil.isSynFree();
	}
	
}