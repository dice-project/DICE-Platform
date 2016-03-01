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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.filters.GuidanceFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Tool Mentors page in the Tool editor.
 * 
 * @author Lokanath Jagga
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class ToolToolMentorsPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "toolToolMentorsPage"; //$NON-NLS-1$

	private Tool tool;

	private IActionManager actionMgr;
	
	private ContentElementOrderList allSteps;

	/**
	 * Creates a new instance.
	 */
	public ToolToolMentorsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TOOL_MENTORS_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		tool = (Tool) contentElement;
		actionMgr = ((MethodElementEditor) getEditor()).getActionManager();
		setUseCategory2(false);
		setUseCategory3(false);
		setIsUpAndDownButtonsRequired1(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				//return tool.getToolMentors().toArray();
				if (allSteps == null) {
//					allSteps = new ContentElementOrderList(
//							contentElement,
//							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
//							getOrderFeature());
					allSteps = getProviderExtender().newContentElementOrderList(contentElement, 
							ContentElementOrderList.CONTENT_ELEMENTS__FOR_ELEMENT_ONLY,
							getOrderFeature(), 1);
				}
				if (getProviderExtender().useContentProviderAPIs()) {
					return getProviderExtender().getElements(object, 1);
				}
				List returnList = CategorySortHelper.sortCategoryElements(
						contentElement, allSteps.toArray());
				return returnList.toArray();
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
			// check if this operation will modify the element in opposite feature value
			for (Iterator iter = addItems.iterator(); iter.hasNext();) {
				MethodElement element = (MethodElement) iter.next();
				if (!UserInteractionHelper.checkModifyOpposite(tool,
						UmaPackage.eINSTANCE.getTool_ToolMentors(), element)) {
					return;
				}
			}
			actionMgr.doAction(IActionManager.ADD_MANY, tool,
					UmaPackage.eINSTANCE.getTool_ToolMentors(), addItems, -1);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			actionMgr.doAction(IActionManager.REMOVE_MANY, tool,
					UmaPackage.eINSTANCE.getTool_ToolMentors(), rmItems, -1);
		}
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return tool;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.TOOL_MENTORS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new GuidanceFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof ToolMentor);
			}
		};
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.toolToolMentorsPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.toolToolMentorsPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.toolToolMentorsPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.toolToolMentorsPage_selectedLabel;
	}
	
	@Override
	protected EStructuralFeature getOrderFeature() {
		return UmaPackage.eINSTANCE.getTool_ToolMentors();
	}

	@Override
	protected String[] getModelStructurePath() {
		return ModelStructure.DEFAULT.toolPath;
	}
	
	@Override
	public ContentElementOrderList getContentElementOrderList() {
		return allSteps;
	}

}
