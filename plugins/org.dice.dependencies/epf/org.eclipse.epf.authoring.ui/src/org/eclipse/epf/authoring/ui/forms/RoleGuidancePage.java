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
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Guidance page in the Role editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class RoleGuidancePage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "roleGuidancePage"; //$NON-NLS-1$

	private Role role;

	/**
	 * Creates a new instance.
	 */
	public RoleGuidancePage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.GUIDANCE_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		role = (Role) contentElement;
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
				return UIHelper.getSelectedGuidances(role);
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

				// Guidance for the content element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									role,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							role,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.ADD,
							role,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
				} else if (item instanceof Practice) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							(Practice) item,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(), role, -1);
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							role,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									role,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.ADD,
							role,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to add guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"to " + role.getType().getName() //$NON-NLS-1$
											+ ":" + role.getName()); //$NON-NLS-1$ 
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
									role,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							role,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							role,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
				} else if (item instanceof Practice) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							(Practice) item,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(), role, -1);
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							role,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									role,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							role,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to remove guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"from " + role.getType().getName() //$NON-NLS-1$
											+ ":" + role.getName()); //$NON-NLS-1$ 
				}
			}
			setDirty(true);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return role;
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
		return filter = new GuidanceFilter();
	}

}