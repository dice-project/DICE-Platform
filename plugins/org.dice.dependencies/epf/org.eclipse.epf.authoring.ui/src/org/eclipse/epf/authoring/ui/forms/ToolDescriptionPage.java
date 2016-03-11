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

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.CategoryFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.VariabilityBaseElementFilter;
import org.eclipse.epf.uma.Tool;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The Description page for the Domain editor.
 * 
 * @author Kelvin Low
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ToolDescriptionPage extends DescriptionFormPage {

	private static final String FORM_PAGE_ID = "toolDescriptionPage"; //$NON-NLS-1$	

	private Tool tool;

	/**
	 * Creates a new instance.
	 */
	public ToolDescriptionPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		tool = (Tool) contentElement;
		setExternalIDOn(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return tool;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new CategoryFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof Tool);
			}
		};

		// set additional filter for variability base element checking
		((CategoryFilter) filter)
				.setAdditionalFilters(new IFilter[] { new VariabilityBaseElementFilter(
						tool) });
		return filter;

	}

	protected String getTabString() {
		return FilterConstants.TOOLS;
	}
	
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		this.generalSectionDescription = AuthoringUIResources.tool_generalInfoSection_desc;
		this.detailSectionDescription = AuthoringUIResources.tool_detailSection_desc;
		this.variabilitySectionDescription = AuthoringUIResources.tool_variabilitySection_desc;
		this.versionSectionDescription = AuthoringUIResources.tool_versionInfoSection_desc;
	}

}
