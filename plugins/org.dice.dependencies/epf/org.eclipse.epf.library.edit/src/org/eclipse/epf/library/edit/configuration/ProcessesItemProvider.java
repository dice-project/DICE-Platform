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
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.navigator.AbstractProcessesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.ProcessComponent;

/**
 * The item provider adapter for the "Processes" folder in the Configuration
 * view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessesItemProvider extends AbstractProcessesItemProvider
		implements ILibraryItemProvider {
	private MethodConfiguration methodConfig;

	private IFilter filter;

	/**
	 * Creates a new instance.
	 */
	public ProcessesItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig, ModelStructure modelStruct) {
		super(adapterFactory, modelStruct);
		this.methodConfig = methodConfig;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();

			if (groupItemProviderMap == null) {
				groupItemProviderMap = new HashMap();
			}

			filter = new IFilter() {

				public boolean accept(Object obj) {
					return obj instanceof ProcessComponent;
				}

			};

			Object image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/CapabilityPatterns"); //$NON-NLS-1$
			String name = LibraryEditPlugin.INSTANCE
					.getString("_UI_CapabilityPatterns_text"); //$NON-NLS-1$
			CategorizedProcessesItemProvider child = new CategorizedProcessesItemProvider(
					adapterFactory, methodConfig, name, image,
					modelStruct.capabilityPatternPath);
			child.setFilter(filter);
			children.add(child);
			groupItemProviderMap.put(name, child);

			image = LibraryEditPlugin.getPlugin().getImage(
					"full/obj16/DeliveryProcesses"); //$NON-NLS-1$
			name = LibraryEditPlugin.INSTANCE
					.getString("_UI_DeliveryProcesses_text"); //$NON-NLS-1$
			child = new CategorizedProcessesItemProvider(adapterFactory,
					methodConfig, name, image, modelStruct.deliveryProcessPath);
			child.setFilter(filter);
			children.add(child);
			groupItemProviderMap.put(name, child);
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		return methodConfig;
	}

	public void setParent(Object parent) {
	}

	public Collection getChildrenFeatures(Object object) {
		return super.getChildrenFeatures(object);
	}

}
