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
import org.eclipse.epf.uma.RoleSet;
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
public class RoleSetDescriptionPage extends DescriptionFormPage {

	private static final String FORM_PAGE_ID = "roleSetDescriptionPage"; //$NON-NLS-1$

	private RoleSet roleSet;

	/**
	 * Creates a new instance.
	 */
	public RoleSetDescriptionPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.DESCRIPTION_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		roleSet = (RoleSet) contentElement;
		setExternalIDOn(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return roleSet;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		filter = new CategoryFilter() {
			protected boolean childAccept(Object obj) {
				return (obj instanceof RoleSet);
			}
		};

		// set additional filter for variability base element checking
		((CategoryFilter) filter)
				.setAdditionalFilters(new IFilter[] { new VariabilityBaseElementFilter(
						roleSet) });
		return filter;
	}

	protected String getTabString() {
		return FilterConstants.ROLESETS;
	}
	
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#loadSectionDescription()
	 */
	public void loadSectionDescription() {
		this.generalSectionDescription = AuthoringUIResources.roleset_generalInfoSection_desc;
		this.detailSectionDescription = AuthoringUIResources.roleset_detailSection_desc;
		this.variabilitySectionDescription = AuthoringUIResources.roleset_variabilitySection_desc;
		this.versionSectionDescription = AuthoringUIResources.roleset_versionInfoSection_desc;
	}

}
