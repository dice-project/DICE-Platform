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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.GuidanceFilter;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Guidance page in the Task editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class TaskGuidancePage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "taskGuidancePage"; //$NON-NLS-1$

	private Task task;

	/**
	 * Creates a new instance.
	 */
	public TaskGuidancePage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.GUIDANCE_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		task = (Task) contentElement;
		setUseCategory2(false);
		setUseCategory3(false);
		setAllowChange1(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return UIHelper.getSelectedGuidances(task);
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
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// Guidances for content element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									task,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							task,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
				} 
				else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.ADD,
							task,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
				} else if (item instanceof Practice) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							(Practice) item,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(), task, -1);
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							task,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									task,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
				} else if (item instanceof ToolMentor) {
					editor.getActionManager().doAction(IActionManager.ADD,
							task, UmaPackage.eINSTANCE.getTask_ToolMentors(),
							(ToolMentor) item, -1);
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.ADD,
							task,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
				} 
				else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to add guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"to " + task.getType().getName() //$NON-NLS-1$
											+ ":" + task.getName()); //$NON-NLS-1$ 
				}
			}
			setDirty(true);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// Guidance for the method element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									task,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							task,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							task,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
				} else if (item instanceof Practice) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							(Practice) item,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(), task, -1);
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							task,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									task,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
				} else if (item instanceof ToolMentor) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							task, UmaPackage.eINSTANCE.getTask_ToolMentors(),
							item, -1);
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							task,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
				} 
//				else if (item instanceof Estimate) {
//					editor.getActionManager().doAction(IActionManager.SET,
//							task, UmaPackage.eINSTANCE.getTask_Estimate(),
//							null, -1);
//				} 
				else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to remove guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"from " + task.getType().getName() //$NON-NLS-1$
											+ ":" + task.getName()); //$NON-NLS-1$ 
				}
			}
			setDirty(true);
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
		return FilterConstants.GUIDANCE;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new GuidanceFilter() {
			protected boolean childAccept(Object obj) {
				if (super.childAccept(obj))
					return true;
				return (obj instanceof ToolMentor);
			}
		};
	}

}