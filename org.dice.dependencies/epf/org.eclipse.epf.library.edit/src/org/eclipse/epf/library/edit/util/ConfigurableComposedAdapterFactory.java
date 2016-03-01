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
package org.eclipse.epf.library.edit.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;


/**
 * Instance of this class can be configured using the specified filter
 * 
 * @author Phong Nguyen Le - Jul 27, 2005
 * @since 1.0
 */
public class ConfigurableComposedAdapterFactory extends ComposedAdapterFactory {

	private IFilter filter;

	// private Set disposableAdapters;

	/**
	 * 
	 */
	public ConfigurableComposedAdapterFactory(AdapterFactory[] adapterFactories) {
		super(adapterFactories);
	}

	// private Set getDisposableAdapters() {
	// if(disposableAdapters == null) {
	// disposableAdapters = new HashSet();
	// }
	// return disposableAdapters;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.AdapterFactory#adapt(java.lang.Object,
	 *      java.lang.Object)
	 */
	public Object adapt(Object object, Object type) {
		Object adapter = super.adapt(object, type);
		if (adapter instanceof IConfigurable) {
			((IConfigurable) adapter).setFilter(filter);
		}
		// if(adapter instanceof IDisposable) {
		// getDisposableAdapters().add(adapter);
		// }
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.AdapterFactory#adapt(org.eclipse.emf.common.notify.Notifier,
	 *      java.lang.Object)
	 */
	public Adapter adapt(Notifier target, Object type) {
		Adapter adapter = super.adapt(target, type);
		if (adapter instanceof IConfigurable) {
			((IConfigurable) adapter).setFilter(filter);
		}
		// if(adapter instanceof IDisposable) {
		// getDisposableAdapters().add(adapter);
		// }
		return adapter;
	}

	// /* (non-Javadoc)
	// * @see org.eclipse.emf.edit.provider.ComposedAdapterFactory#dispose()
	// */
	// public void dispose() {
	// if(disposableAdapters != null && !disposableAdapters.isEmpty()) {
	// for (Iterator iter = disposableAdapters.iterator(); iter.hasNext();) {
	// IDisposable adapter = (IDisposable) iter.next();
	// adapter.dispose();
	// }
	// }
	// super.dispose();
	// }

	public IFilter getFilter() {
		return filter;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ComposedAdapterFactory#fireNotifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void fireNotifyChanged(Notification notification) {
		try {
			super.fireNotifyChanged(notification);
		} catch (RuntimeException e) {
			if (notification instanceof ViewerNotification) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);

				StringWriter strWriter = new StringWriter();
				e.printStackTrace(new PrintWriter(strWriter));
				Messenger.INSTANCE
						.showError(
								LibraryEditResources.util_configurablecomposedadapter_fatalerr,
								LibraryEditResources.util_configurablecomposedadapter_refershingviewer,
								LibraryEditResources.util_configurablecomposedadapter_unhandled_exception,
								strWriter.toString(), e); 
				return;
			}
			throw e;
		}
	}
	
	public void addFirstAdapterFactory(AdapterFactory adapterFactory) {
	    if (!adapterFactories.contains(adapterFactory)) {
			adapterFactories.add(0, adapterFactory);
			if (adapterFactory instanceof ComposeableAdapterFactory) {
				((ComposeableAdapterFactory) adapterFactory)
						.setParentAdapterFactory(this);
			}
		}
	}
}