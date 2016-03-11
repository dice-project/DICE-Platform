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
import java.util.HashMap;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PluginUIPackageContext;
import org.eclipse.epf.library.edit.StructuredMethodPluginItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.provider.UmaEditPlugin;

/**
 * The item provider adapter for a method plug-in in the Library
 * view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodPluginItemProvider extends StructuredMethodPluginItemProvider
		implements IStatefulItemProvider, ILibraryItemProvider {

	protected Collection children;

	protected Object parent;

	/**
	 * Creates a new instance.
	 */
	public MethodPluginItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.MethodPluginItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @return the parent of this ItemProvider
	 */
	public Object getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.MethodPluginItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		return Collections.EMPTY_LIST;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			MethodPlugin plugin = (MethodPlugin) object;

			children = new ArrayList();
			groupItemProviderMap = new HashMap();

			String name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Content_group"); //$NON-NLS-1$
			Object child = new ContentItemProvider(adapterFactory, plugin,
					getModelStructure());
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"); //$NON-NLS-1$
			child = new ProcessesItemProvider(adapterFactory, plugin,
					getModelStructure());
			children.add(child);
			groupItemProviderMap.put(name, child);
		}

		return children;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (children != null) {
			children.clear();
			children = null;
		}

		super.dispose();
	}

	public Object getImage(Object object) {
		return (((MethodPlugin) object).getUserChangeable().booleanValue()) ? UmaEditPlugin.INSTANCE
				.getImage("full/obj16/MethodPlugin") : //$NON-NLS-1$
				UmaEditPlugin.INSTANCE.getImage("full/obj16/MethodPlugin_grey"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.MethodPluginItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(MethodPlugin.class)) {
		case UmaPackage.METHOD_PLUGIN__COPYRIGHT_STATEMENT:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		case UmaPackage.METHOD_PLUGIN__NAME:
			TngUtil.refreshParentIfNameChanged(notification, this);
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, true));
			TngUtil.refreshPluginExtenders(this, notification, true, true);
			break;
		}

		super.notifyChanged(notification);
	}
	
	@Override
	public String getText(Object object) {
		String name = super.getText(object);
		
		if ((getParent() instanceof PluginUIPackagesItemProvider) && !PluginUIPackageContext.INSTANCE.isFlatLayout()) {
			String deltaName = PluginUIPackagesItemProvider.getNameDelta((PluginUIPackagesItemProvider)getParent(), object);
			return deltaName;
		}
		return name;
	}

	public String getFullName(Object object) {
		return super.getText(object);
	}

	

}