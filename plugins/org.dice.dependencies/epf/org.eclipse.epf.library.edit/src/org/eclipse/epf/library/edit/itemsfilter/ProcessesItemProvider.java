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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Processes" folder in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessesItemProvider extends ItemProviderAdapter implements
		ITreeItemContentProvider, IEditingDomainItemProvider,
		IItemLabelProvider, IItemPropertySource,
		IStructuredItemContentProvider, IElementItemProvider, IGroupContainer,
		IConfigurable {

	private Map groupItemProviderMap;

	protected ArrayList children;

	protected MethodPlugin plugin;

	private IFilter filter;

	/**
	 * @param adapterFactory
	 */
	public ProcessesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @param adapterFactory
	 * @param MethodPlugin
	 * @param ModelStructure
	 * 
	 * Plugin is required to pass, used in finding MethodPackage for particular
	 * ModelStructure, ModelStructure is optional.
	 */
	public ProcessesItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin) {
		super(adapterFactory);
		this.plugin = plugin;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			groupItemProviderMap = new HashMap();
			String[] names = new String[] {
					LibraryEditPlugin.INSTANCE
							.getString("_UI_CapabilityPatterns_text"), //$NON-NLS-1$
					LibraryEditPlugin.INSTANCE
							.getString("_UI_DeliveryProcesses_text"), //$NON-NLS-1$
					LibraryEditPlugin.INSTANCE
							.getString("_UI_ProcessContributions_text") }; //$NON-NLS-1$
			String[][] processPaths = new String[][] {
					ModelStructure.DEFAULT.capabilityPatternPath,
					ModelStructure.DEFAULT.deliveryProcessPath,
					ModelStructure.DEFAULT.processContributionPath };
			for (int i = 0; i < processPaths.length; i++) {
				ProcessPackage pkg1 = (ProcessPackage) UmaUtil
						.findMethodPackage(plugin, processPaths[i]);
				if (pkg1 != null) {
					Object image = UmaEditPlugin.getPlugin().getImage(
							"full/obj16/ProcessComponent"); //$NON-NLS-1$
					CategorizedProcessesItemProvider itemProvider = new CategorizedProcessesItemProvider(
							adapterFactory, pkg1, names[i], image);
					itemProvider.setFilter(filter);
					children.add(itemProvider);
				}
			}

		}

		return children;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Processes"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public void setLabel(String label) {
	}

	public void setParent(Object parent) {
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

}
