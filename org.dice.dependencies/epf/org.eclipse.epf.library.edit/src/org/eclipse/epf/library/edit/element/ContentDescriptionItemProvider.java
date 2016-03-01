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
package org.eclipse.epf.library.edit.element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.edit.command.MethodElementInitializeCopyCommand;

/**
 * The item provider adapter for a content description.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ContentDescriptionItemProvider extends
		org.eclipse.epf.uma.provider.ContentDescriptionItemProvider {

	/**
	 * A map of EClass / ItemProviderAdapter for each type of ContentDescription
	 */
	private static Map itemProviderMap = new HashMap();

	/**
	 * Creates a new instance.
	 */	
	public ContentDescriptionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		itemProviderMap.clear();

		super.dispose();
	}

	public List getPropertyDescriptors(Object object) {
		if (object instanceof EObject) {
			return getAdapter((EObject) object).getPropertyDescriptors(object);
		}
		return super.getPropertyDescriptors(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getPropertyDescriptor(java.lang.Object,
	 *      java.lang.Object)
	 */
	public IItemPropertyDescriptor getPropertyDescriptor(Object object,
			Object propertyId) {
		return getAdapter((EObject) object).getPropertyDescriptor(object,
				propertyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getEditableValue(java.lang.Object)
	 */
	public Object getEditableValue(Object object) {
		return getAdapter((EObject) object).getEditableValue(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		if (object instanceof EObject) {
			return getAdapter((EObject) object).getImage(object);
		}
		return super.getImage(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object);
	}

	public static ItemProviderAdapter getAdapter(EObject eObj) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) itemProviderMap
				.get(eObj.eClass());
		if (adapter == null) {
			adapter = (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
					.createAdapter(eObj);
			itemProviderMap.put(eObj.eClass(), adapter);
		}
		return adapter;
	}

	protected Command createInitializeCopyCommand(EditingDomain domain,
			EObject owner, Helper helper) {
		return new MethodElementInitializeCopyCommand(domain, owner, helper);
	}

}
