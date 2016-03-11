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
package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeListenerWrapper;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;

/**
 * The abstract base item provider adapter class for the "Processes" folder in
 * the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class AbstractProcessesItemProvider extends ItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IElementItemProvider, IGroupContainer {
	protected Map groupItemProviderMap;

	protected ModelStructure modelStruct;

	protected ArrayList children;

	protected static final boolean processContributionEnabled = false;

	private IPropertyChangeListenerWrapper prefStoreListener;

	/**
	 * @param adapterFactory
	 */
	public AbstractProcessesItemProvider(AdapterFactory adapterFactory,
			ModelStructure modelStruct) {
		super(adapterFactory);
		this.modelStruct = modelStruct;

		// final IPreferenceStore prefStore = Providers.getPreferenceStore();
		// if (prefStore != null) {
		// processContributionEnabled = prefStore
		// .getBoolean(LibraryEditConstants.PREF_ENABLE_PROCESS_CONTRIBUTION);
		// prefStoreListener = new IPropertyChangeListener() {
		//
		// public void propertyChange(PropertyChangeEvent event) {
		// if (event.getProperty() ==
		// LibraryEditConstants.PREF_ENABLE_PROCESS_CONTRIBUTION) {
		// boolean newValue = prefStore.getBoolean(event
		// .getProperty());
		// if (processContributionEnabled != newValue) {
		// processContributionEnabled = newValue;
		// fireNotifyChanged(new ViewerNotification(
		// new NotificationImpl(-1, -1, -1),
		// AbstractProcessesItemProvider.this, true,
		// false));
		// }
		// }
		// }
		//
		// };
		// prefStore.addPropertyChangeListener(prefStoreListener);
		// }

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		IPreferenceStoreWrapper prefStore = Providers.getPreferenceStore();
		if (prefStore != null && prefStoreListener != null) {
			prefStore.removePropertyChangeListener(prefStoreListener);
		}
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Processes"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"); //$NON-NLS-1$
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}
}
