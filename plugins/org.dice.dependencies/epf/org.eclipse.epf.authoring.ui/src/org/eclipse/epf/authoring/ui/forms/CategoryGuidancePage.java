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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.GuidanceFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.ChangeUdtCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MethodElementSetPropertyCommand;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Guidance page in the content element editor.
 * 
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 * TODO - change name of class to ContentElementGuidancePage
 */
public class CategoryGuidancePage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "categoryGuidancesPage"; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public CategoryGuidancePage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.GUIDANCE_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
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
				List list = MethodElementUtil
						.getSelectedGuidances(contentElement);
				List local = new ArrayList();
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object obj = it.next();
					if (!(obj instanceof Practice
							|| (obj instanceof ToolMentor && contentElement instanceof Tool) || obj
							.equals(contentElement
									.getVariabilityBasedOnElement()))) {
						local.add(obj);
					}
				}
				return local.toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		// Update the model.
		
		List<Practice> utdItems = new ArrayList<Practice>();
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// Guidance for content element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.ADD,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.ADD,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
				} else if (item instanceof Practice) {
					if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) item)) {
						utdItems.add((Practice) item);
					}
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to add guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"to " //$NON-NLS-1$
											+ contentElement.getType()
													.getName()
											+ ":" + contentElement.getName()); //$NON-NLS-1$
				}
			}
			setDirty(true);
		}
		if (! utdItems.isEmpty()) {
			editor.getActionManager().execute(new ChangeUdtCommand(contentElement, utdItems, false));
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		List<Practice> utdItems = new ArrayList<Practice>();
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// Guidance for the method element.
				if (item instanceof Checklist) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									item, -1);
				} else if (item instanceof Concept) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							(Concept) item, -1);
				} else if (item instanceof Example) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							(Example) item, -1);
				} else if (item instanceof SupportingMaterial) {
					editor.getActionManager().doAction(
							IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							(SupportingMaterial) item, -1);
				} else if (item instanceof Guideline) {
					editor.getActionManager()
							.doAction(
									IActionManager.REMOVE,
									contentElement,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									(Guideline) item, -1);
				} else if (item instanceof ReusableAsset) {
					editor.getActionManager().doAction(IActionManager.REMOVE,
							contentElement,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							(ReusableAsset) item, -1);
				} else if (item instanceof Practice) {
					if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) item)) {
						utdItems.add((Practice) item);
					}
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to remove guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"from " //$NON-NLS-1$
											+ contentElement.getType()
													.getName()
											+ ":" + contentElement.getName()); //$NON-NLS-1$ 
				}
			}
			setDirty(true);
		}
		if (! utdItems.isEmpty()) {
			editor.getActionManager().execute(new ChangeUdtCommand(contentElement, utdItems, true));
		}

	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return contentElement;
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
