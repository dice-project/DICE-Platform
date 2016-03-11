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
package org.eclipse.epf.library.edit;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.provider.MethodPluginItemProvider;

/**
 * The item provider adapter for a structured method plug-in.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class StructuredMethodPluginItemProvider extends
		MethodPluginItemProvider implements IGroupContainer,
		IStatefulItemProvider {

	protected Map groupItemProviderMap;

	private ModelStructure modelStruct;

	/**
	 * Creates a new instance.
	 */
	public StructuredMethodPluginItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public void setModelStructure(ModelStructure modelStruct) {
		this.modelStruct = modelStruct;
	}

	public ModelStructure getModelStructure() {
		return modelStruct;
	}

	public Object getGroupItemProvider(String name) {
		if (groupItemProviderMap == null)
			return null;
		return groupItemProviderMap.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_MethodPlugin_type")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (groupItemProviderMap != null) {
			for (Iterator iter = groupItemProviderMap.values().iterator(); iter
					.hasNext();) {
				Object adapter = iter.next();
				if (adapter instanceof IDisposable) {
					((IDisposable) adapter).dispose();
				}
			}
			groupItemProviderMap.clear();
			groupItemProviderMap = null;
		}

		super.dispose();
	}

}
