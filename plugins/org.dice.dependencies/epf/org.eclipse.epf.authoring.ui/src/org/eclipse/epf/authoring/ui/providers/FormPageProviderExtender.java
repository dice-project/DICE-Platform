//------------------------------------------------------------------------------
// Copyright (c) 2005, 2012 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.providers;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.authoring.ui.forms.AssociationFormPage;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * @author Weiping Lu
 */
public class FormPageProviderExtender {

	private AssociationFormPage formPage;

	public FormPageProviderExtender(AssociationFormPage formPage) {
		this.formPage = formPage;
	}
	
	public boolean useContentProviderAPIs() {
		return false;
	}
	
	public Object[] getElements(Object object, int ix) {
		return new Object[0];
	}
	
	public boolean handleAddItems(ISelection selection, int ix) {
		return false;
	}
		
	public boolean handleRemoveItems(ISelection selection, int ix) {
		return false;
	}
	
	public AssociationFormPage getFormPage() {
		return formPage;
	}
	
	public AssociationFormLabelProvider newLabelProvider(AdapterFactory adapterFactory, int ix) {
			return new AssociationFormLabelProvider(adapterFactory, this, ix);
	}
	
	public ContentElementOrderList newContentElementOrderList(VariabilityElement e, int scope, EStructuralFeature feature, int ix) {
		return new ContentElementOrderList(e, scope, feature);
	}
	
	public MethodConfiguration getConfig() {
		return LibraryService.getInstance().getCurrentMethodConfiguration();
	}
	
	protected ElementRealizer newRealizer() {
		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(getConfig());
		return realizer;
	}
		
	protected List getSelectionList(ISelection selection) {
		if (! (selection instanceof IStructuredSelection)) {
			return Collections.EMPTY_LIST;
		}
		return ((IStructuredSelection) selection).toList();
	}
	
	public void dispose() {		
	}
	
}
