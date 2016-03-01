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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Roles page in the Task editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class TaskRolesPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "taskRolesPage"; //$NON-NLS-1$

	private Task task;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public TaskRolesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.ROLES_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		task = (Task) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
		setUseCategory3(false);
		setAllowChange1(true);
		setAllowChange2(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				return ((Task) object).getPerformedBy().toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {		
			List elementList = retrieveTableViewerContents(viewer_selected);
			List addItemsFiltered = new ArrayList();
			for (int i = 0; i < addItems.size(); i++) {
				if (!elementList.contains(((Role) addItems.get(i)))) {
					addItemsFiltered.add(addItems.get(i));
				}
			}
			if (addItems.size() > 0) {
				actionMgr.doAction(IActionManager.ADD_MANY, task,
						UmaPackage.eINSTANCE.getTask_PerformedBy(),
						addItemsFiltered, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			actionMgr.doAction(IActionManager.REMOVE_MANY, task,
					UmaPackage.eINSTANCE.getTask_PerformedBy(),
					rmItems, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected2()
	 */
	protected void initContentProviderSelected2() {
		contentProviderSelected2 = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 2);
				}
				return ((Task) object).getAdditionallyPerformedBy().toArray();
			}
		};
		viewer_selected2.setContentProvider(contentProviderSelected2);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel2(ArrayList)
	 */
	protected void addItemsToModel2(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {
			List elementList = retrieveTableViewerContents(viewer_selected);
			List addItemsFiltered = new ArrayList();
			for (int i = 0; i < addItems.size(); i++) {
				if (!elementList.contains(((Role) addItems.get(i)))) {
					addItemsFiltered.add(addItems.get(i));
				}
			}
			if (addItems.size() > 0) {
				actionMgr.doAction(IActionManager.ADD_MANY, task,
						UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(),
						addItemsFiltered, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel2(ArrayList)
	 */
	protected void removeItemsFromModel2(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			actionMgr.doAction(IActionManager.REMOVE_MANY, task,
					UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(),
					rmItems, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return task;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.ROLES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter =	new ContentFilter (){
			protected boolean childAccept(Object obj) {
				if(task.getAdditionallyPerformedBy() != null){
					List list = task.getAdditionallyPerformedBy();
					for(Iterator it = list.iterator(); it.hasNext();){
						Object next = it.next();
						if(next instanceof Role){
							if(obj == next) return false;
							if(!checkContribution((VariabilityElement)next, obj))
								return false;
						}
					}
				}
				return (obj instanceof Role);
			}
		};
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter2()
	 */
	protected IFilter getFilter2() {
		return filter = new ContentFilter (){
			protected boolean childAccept(Object obj) {
				if(task.getPerformedBy() != null){
					
					List list = task.getPerformedBy();
					for(Iterator it = list.iterator(); it.hasNext();){
						Object next = it.next();
						if(next instanceof Role){
							if(obj == next) return false;
							if(!checkContribution((Role)next, obj))
								return false;
						}
					}
				}
				return (obj instanceof Role);
			}
		};
	}

	/**
	 * Role filter
	 *
	 */
	public class RoleFilter extends ContentFilter {
		protected boolean childAccept(Object obj) {
			if(task.getPerformedBy() != null){
				List list = task.getPerformedBy();
				for(Iterator it = list.iterator(); it.hasNext();){
					Object next = it.next();
					if(next instanceof Role){
						if(obj == next) return false;
						if(!checkContribution((Role)next, obj))
							return false;
					}
				}
			}
			return (obj instanceof Role);
		}
	};

	/**
	 * Check contribution
	 * @param contributor
	 * @param obj
	 */
	public boolean checkContribution(VariabilityElement contributor, Object obj){
		if(TngUtil.isContributor(contributor) && obj instanceof Role){
			VariabilityElement e = contributor.getVariabilityBasedOnElement();
			if(e != null){
				if(e.equals(obj)) return false;
				if(TngUtil.isContributor(e))
					return checkContribution(e, obj);
			}
		}
		return true;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.taskRolesPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.taskRolesPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.taskRolesPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.taskRolesPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.taskRolesPage_selectedLabel2;
	}
	
	
	@Override
	public EReference getReference(int ix) {
		if (ix == 1) {
			return UmaPackage.eINSTANCE.getTask_PerformedBy();
		}
		if (ix == 2) {
			return UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy();
		}		
		return super.getReference(ix);
	}

	
}
