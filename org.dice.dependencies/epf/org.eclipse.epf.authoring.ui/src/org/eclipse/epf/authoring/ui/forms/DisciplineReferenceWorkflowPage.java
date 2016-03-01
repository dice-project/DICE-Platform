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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.ReferenceWorkFlowFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Reference Workflow page in the Discipline editor.
 * 
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @since 1.0
 */
public class DisciplineReferenceWorkflowPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "disciplineReferenceWorkflowPage"; //$NON-NLS-1$

	private Discipline discipline;

	/**
	 * Creates a new instance.
	 */
	public DisciplineReferenceWorkflowPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID,
				AuthoringUIText.REFERENCE_WORKFLOW_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		discipline = (Discipline) contentElement;
		setAllowChange1(true);
		setAllowChange2(true);
		setUseCategory2(false);
		setUseCategory3(false);
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
				return discipline.getReferenceWorkflows().toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		for (Iterator itor = addItems.iterator(); itor.hasNext();) {
			// Object object = itor.next();
			// if (object instanceof ProcessComponent) {
			// Process process =
			// (Process)((ProcessComponent)object).getProcess();
			// if (process != null) {
			// editor.getActionManager().doAction(IActionManager.ADD,
			// discipline,
			// UmaPackage.eINSTANCE.getDiscipline_ReferenceWorkflows(), process,
			// -1);
			// }
			// }
			// else {
			// editor.getActionManager().doAction(IActionManager.ADD,
			// discipline,
			// UmaPackage.eINSTANCE.getDiscipline_ReferenceWorkflows(), object,
			// -1);
			// }
			MethodElement object = (MethodElement) itor.next();
			if (object instanceof ProcessComponent) {
				object = ((ProcessComponent) object).getProcess();
			}
			if (object != null) {
				EStructuralFeature feature = UmaPackage.eINSTANCE
						.getDiscipline_ReferenceWorkflows();
				if (UserInteractionHelper.checkModifyOpposite(discipline,
						feature, object)) {
					editor.getActionManager().doAction(IActionManager.ADD,
							discipline, feature, object, -1);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			editor.getActionManager().doAction(IActionManager.REMOVE_MANY,
					discipline,
					UmaPackage.eINSTANCE.getDiscipline_ReferenceWorkflows(),
					rmItems, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return discipline;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.PROCESSES;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new ReferenceWorkFlowFilter();
	}
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.disciplineReferenceWorkflowPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.disciplineReferenceWorkflowPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.disciplineReferenceWorkflowPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.disciplineReferenceWorkflowPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.disciplineReferenceWorkflowPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.disciplineReferenceWorkflowPage_selectedLabel;
	}
	
	@Override
	public EReference getReference(int ix) {
		if (ix == 1) {
			return UmaPackage.eINSTANCE.getDiscipline_ReferenceWorkflows();
		}	
		return super.getReference(ix);
	}


}
