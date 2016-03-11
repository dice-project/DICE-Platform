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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.swt.graphics.Font;

/**
 * @author Weiping Lu
 */
public class AssociationFormLabelProvider extends AdapterFactoryLabelProvider
		implements ITableFontProvider {
	
	private FormPageProviderExtender providerExtender;
	private EReference reference;

	private int ix;
	private int typeIx;

	public AssociationFormLabelProvider(AdapterFactory adapterFactory, FormPageProviderExtender providerExtender, int ix) {
		super(adapterFactory);
		this.providerExtender = providerExtender;
		this.ix = ix;
		this.typeIx = ix;
	}  	
	
	public void setTypeIx(int typeIx) {
		this.typeIx = typeIx;
	}
	
	@Override
	public String getColumnText(Object object, int columnIndex) {
		String text = TngUtil.getLabelWithPath(object);
		if (typeIx == 1) {
			text = providerExtender.getFormPage().getDecorator(object, getReference()) + text;
		}
		return text;
	}
	
	@Override
    public Font getFont(Object obj, int columnIndex) {		
    	return super.getFont(obj, columnIndex);
    }	  
	
    @Override
    public void dispose() {
    	super.dispose();
    	providerExtender = null;
    }
	
	protected FormPageProviderExtender getProviderExtender() {
		return providerExtender;
	}
    
	protected int getIx() {
		return ix;
	}
	
	public EReference getReference() {
		return reference;
	}

	public void setReference(EReference reference) {
		this.reference = reference;
	}

	protected void setIx(int ix) {
		this.ix = ix;
	}

	protected void setProviderExtender(FormPageProviderExtender providerExtender) {
		this.providerExtender = providerExtender;
	}
	
}
