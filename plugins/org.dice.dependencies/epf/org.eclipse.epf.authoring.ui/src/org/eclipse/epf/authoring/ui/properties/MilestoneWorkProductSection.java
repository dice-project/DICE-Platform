//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
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
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.WorkProductDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.AssignWPToMilestone;
import org.eclipse.epf.library.edit.process.command.IActionTypeConstants;
import org.eclipse.epf.library.edit.util.MilestonePropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.util.WorkProductPropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * Milestone - work product section
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 * 
 */
public class MilestoneWorkProductSection extends RelationSection {
	private IFilter filter = null;
	private Milestone milestone;
	private Button ctrl_state_1;
	
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
				
				return getFilteredList(
						((Milestone) milestone).getRequiredResults())
						.toArray();
			}
		};
		tableViewer1.setContentProvider(contentProvider);
	}
	
	protected void initLabelProvider1() {
		ILabelProvider provider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory()) {
			public String getColumnText(Object obj, int columnIndex) {
				String label = super.getColumnText(obj, columnIndex);
				if (obj instanceof WorkProductDescriptor) {					
					label = getLabelForWpd((WorkProductDescriptor)obj,
							label, UmaPackage.eINSTANCE.getMilestone_RequiredResults());
				}
				
				return label;
			}
		};
		
		tableViewer1.setLabelProvider(provider);		
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#init()
	 */
	protected void init() {
		if (getElement() instanceof Milestone) {
			milestone = (Milestone) getElement();
		}
		super.init();

//		labelProvider = new AdapterFactoryLabelProvider(
//				TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory());

		int numOfTables = 1;
		
		setTabData(PropertiesResources.RoleDescriptor_WorkProducts_SectionTitle,
				PropertiesResources.Milestone_WorkProducts_SectionDescription,
				PropertiesResources.Milestone_WorkProducts_Table1,
				null,
				null,
				null,
				FilterConstants.WORKPRODUCTS); 

		boolean[] changesAllowed = { true };
		setTableData(numOfTables, changesAllowed);
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#refresh()
	 */
	public void refresh() {
		if (getElement() instanceof Milestone) {
			milestone = (Milestone) getElement();
		}
		super.refresh();
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getDescriptorsFromProcess()
	 */
	protected List getDescriptorsFromProcess() {
		List items = new ArrayList();
		return ProcessUtil.getElementsInScope(getAdapterFactory(), milestone,
				WorkProductDescriptor.class, items);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#addItems1(java.util.List)
	 */
	protected void addItems1(List items) {
		if (!items.isEmpty()) {
			AssignWPToMilestone cmd = new AssignWPToMilestone(
					(Milestone) element, items,
					IActionTypeConstants.ADD_REQUIRED_RESULT, getConfiguration());
			actionMgr.execute(cmd);
		}
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#removeItems1(java.util.List)
	 */
	protected void removeItems1(List items) {
		if (!items.isEmpty()) {
			actionMgr.doAction(IActionManager.REMOVE_MANY, milestone,
					UmaPackage.eINSTANCE.getMilestone_RequiredResults(),
					items, -1);
			
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
		return getWorkProducts(((Milestone) milestone).getRequiredResults());
	};

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.RelationSection#getProcess()
	 */
	protected Process getProcess() {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				milestone, ITreeItemContentProvider.class);
		Object obj = ProcessUtil.getRootProcess(aFactory, adapter, milestone);
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
							.getWBS_ComposedAdapterFactory(), milestone, list);
					if (list.contains(obj))
						return true;
					else
						return false;
				}
				
				//  show extended activity's workproducts			
				 if (obj instanceof ActivityWrapperItemProvider || obj instanceof WorkProductDescriptorWrapperItemProvider){
					 Object object = ((BreakdownElementWrapperItemProvider)obj).getParent(obj);
					 List list = new ArrayList();
				 		getActivitiesInScope(TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(),element, list);
				 		if(list.contains(object)) return true;
				 }
				 
				if (obj instanceof WorkProductDescriptor)
					return true;
				return false;
			}
			
			protected void getActivitiesInScope(AdapterFactory adapterFactory,
					BreakdownElement element, List activityList) {				
				// get all parents in scope
				getParentActivitiesInScope(adapterFactory, element, activityList);
				
				// find parent of the element
				ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory.adapt(element, ITreeItemContentProvider.class);
				Object parent = adapter.getParent(element);
				
				// get all children of the parent in other words siblings of the element
				getChildrenActivitiesInScope(adapterFactory, (BreakdownElement) parent, activityList);
			}
			
			private void getParentActivitiesInScope(AdapterFactory adapterFactory,
					BreakdownElement element, List activityList) {
				ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
						.adapt(element, ITreeItemContentProvider.class);
				Object parent = adapter.getParent(element);
				if (parent instanceof Activity && !activityList.contains(parent)) {   
					activityList.add(parent);
					getParentActivitiesInScope(adapterFactory, (BreakdownElement) parent,
							activityList);
				}
			}
			
			private void getChildrenActivitiesInScope(AdapterFactory adapterFactory,
					BreakdownElement element, List activityList) {
				ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
						.adapt(element, ITreeItemContentProvider.class);
				List children = (List) adapter.getChildren(element);
				if (!children.isEmpty() && children.size() > 0) {
					for (int i=0; i < children.size(); i++) {
						Object child = children.get(i);
						if (child instanceof Activity && !activityList.contains(child))  {
							activityList.add(child);
							getChildrenActivitiesInScope(adapterFactory, (BreakdownElement) child,
									activityList);
						}
						if (child instanceof ActivityWrapperItemProvider) {
							activityList.add(TngUtil.unwrap(child));
							getChildrenActivitiesInScope(adapterFactory, (ActivityWrapperItemProvider) child, activityList);
						}
					}	
				}
			}
			
			private void getChildrenActivitiesInScope(AdapterFactory adapterFactory,
					ActivityWrapperItemProvider provider, List activityList) {
				
				List children = (List) provider.getChildren(element);
				if (!children.isEmpty() && children.size() > 0) {
					for (int i=0; i < children.size(); i++) {
						Object child = children.get(i);
						if (child instanceof Activity && !activityList.contains(child)) {
							activityList.add(child);
							getChildrenActivitiesInScope(adapterFactory, (BreakdownElement) child,
									activityList);
						}
						if (child instanceof ActivityWrapperItemProvider) {
							activityList.add(TngUtil.unwrap(child));
							getChildrenActivitiesInScope(adapterFactory, (ActivityWrapperItemProvider) child, activityList);
						}
					}	
				}
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
			actionMgr.doAction(IActionManager.ADD_MANY, milestone,
					UmaPackage.eINSTANCE.getMilestone_RequiredResults(),
					items, -1);
		}
	}
	
	private String getLabelForWpd(WorkProductDescriptor wpd, String orginalLabel, EReference ref) {
		List<Constraint> states = MilestonePropUtil.getMilestonePropUtil(actionMgr).getWpStates(
				(Milestone)element, wpd, ref);
		if (states.size() > 0) {
			String stateName = states.get(0).getBody();
			return orginalLabel + " [" + stateName + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		return orginalLabel;
	}
	
	protected void createAddtionalButton1(Composite parent) {		
		ctrl_state_1 = FormUI.createButton(toolkit, parent, PropertiesResources.Process_AssignState);
		ctrl_state_1.setEnabled(false);
		
		final EReference ref = UmaPackage.eINSTANCE.getMilestone_RequiredResults();
		
		ctrl_state_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assignState(tableViewer1, ref);
			}
		});		
		
		tableViewer1.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer1.getSelection();
				if ((selection.size() == 1) && (editable)) {
					ctrl_state_1.setEnabled(true);
				} else {
					ctrl_state_1.setEnabled(false);
				}
			}
		});
	}
	
	private void assignState(TableViewer viewer, EReference ref) {
		IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
		
		if (selection.size() == 1) {
			if (selection.getFirstElement() instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = (WorkProductDescriptor)selection.getFirstElement();
				
				ElementListSelectionDialog dialog = new TaskDescriptorWorkProductSection.StateSelectionDialog(
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
				List<Constraint> oldStates = MilestonePropUtil.getMilestonePropUtil(actionMgr).getWpStates(
						(Milestone)element, wpd, ref);
				for(Constraint oldState : oldStates) {
					MilestonePropUtil.getMilestonePropUtil(actionMgr).removeWpState(
							(Milestone)element, wpd, oldState, ref);
				}
				
				//add the new state
				if (!newState.getName().equals(TaskDescriptorWorkProductSection.UNASSIGN_STATE_NAME)) {
					MilestonePropUtil.getMilestonePropUtil(actionMgr).addWpState(
							(Milestone)element, wpd, newState, ref);
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
		
		List<Constraint> oldStates = MilestonePropUtil.getMilestonePropUtil(actionMgr).getWpStates(
				(Milestone)element, wpd, ref);
		WorkProduct wp = (WorkProduct) propUtil.getLinkedElement(wpd);
		if (wp != null) {
			Set<Constraint> states = WorkProductPropUtil.getWorkProductPropUtil(actionMgr).getAllStates(wp);
			states.removeAll(oldStates);
			elements.addAll(states);
		}
		
		// A special state for unassign state from wpd
		elements.add(TaskDescriptorWorkProductSection.createUnassignState());
		
		return elements;
	}
	
}
