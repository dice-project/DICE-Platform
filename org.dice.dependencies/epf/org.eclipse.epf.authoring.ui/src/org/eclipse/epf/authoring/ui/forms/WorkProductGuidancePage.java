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
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
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
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Guidance page in the Work Product editor.
 * 
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductGuidancePage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "workProductGuidancePage"; //$NON-NLS-1$

	private WorkProduct workProduct;

	private IActionManager actionMgr;

	/**
	 * Creates a new instance.
	 */
	public WorkProductGuidancePage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.GUIDANCE_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		workProduct = (WorkProduct) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
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
				return UIHelper.getSelectedGuidances(workProduct);
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

				// The guidance for the content element.
				if (item instanceof Checklist) {
					actionMgr
							.doAction(IActionManager.ADD, workProduct,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									(Checklist) item, -1);
				} else if (item instanceof Concept) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							item, -1);
				} else if (item instanceof Example) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							item, -1);
				} else if (item instanceof Guideline) {
					actionMgr
							.doAction(IActionManager.ADD, workProduct,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									item, -1);
				} else if (item instanceof Practice) {
					actionMgr.doAction(IActionManager.ADD, (Practice) item,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(),
							workProduct, -1);
				} else if (item instanceof Report) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE.getWorkProduct_Reports(),
							item, -1);
				} else if (item instanceof ReusableAsset) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							item, -1);
				} else if (item instanceof SupportingMaterial) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							item, -1);
				} else if (item instanceof Template) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE.getWorkProduct_Templates(),
							item, -1);
				} else if (item instanceof ToolMentor) {
					actionMgr.doAction(IActionManager.ADD, workProduct,
							UmaPackage.eINSTANCE.getWorkProduct_ToolMentors(),
							item, -1);
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to add guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"to " //$NON-NLS-1$
											+ workProduct.getType().getName()
											+ ":" + workProduct.getName()); //$NON-NLS-1$ 
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
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Guidance item = (Guidance) it.next();

				// The guidance for the content element.
				if (item instanceof Checklist) {
					actionMgr
							.doAction(IActionManager.REMOVE, workProduct,
									UmaPackage.eINSTANCE
											.getContentElement_Checklists(),
									item, -1);
				} else if (item instanceof Concept) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE
									.getContentElement_ConceptsAndPapers(),
							item, -1);
				} else if (item instanceof Example) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE.getContentElement_Examples(),
							item, -1);
				} else if (item instanceof Guideline) {
					actionMgr
							.doAction(IActionManager.REMOVE, workProduct,
									UmaPackage.eINSTANCE
											.getContentElement_Guidelines(),
									item, -1);
				} else if (item instanceof Practice) {
					actionMgr.doAction(IActionManager.REMOVE, (Practice) item,
							UmaPackage.eINSTANCE
									.getPractice_ContentReferences(),
							workProduct, -1);
				} else if (item instanceof Report) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE.getWorkProduct_Reports(),
							item, -1);
				} else if (item instanceof ReusableAsset) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE.getContentElement_Assets(),
							item, -1);
				} else if (item instanceof SupportingMaterial) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE
									.getContentElement_SupportingMaterials(),
							item, -1);
				} else if (item instanceof Template) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE.getWorkProduct_Templates(),
							item, -1);
				} else if (item instanceof ToolMentor) {
					actionMgr.doAction(IActionManager.REMOVE, workProduct,
							UmaPackage.eINSTANCE.getWorkProduct_ToolMentors(),
							item, -1);
				} else {
					AuthoringUIPlugin
							.getDefault()
							.getLogger()
							.logError(
									"Unable to remove guidance " + item.getType().getName() + ":" + item.getName() + //$NON-NLS-1$ //$NON-NLS-2$
											"from " //$NON-NLS-1$
											+ workProduct.getType().getName()
											+ ":" + workProduct.getName()); //$NON-NLS-1$ 
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return workProduct;
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
				if (obj instanceof Template)
					return true;
				if (obj instanceof Report)
					return true;
				if (obj instanceof ToolMentor)
					return true;
				return false;
			}
		};
	}

}