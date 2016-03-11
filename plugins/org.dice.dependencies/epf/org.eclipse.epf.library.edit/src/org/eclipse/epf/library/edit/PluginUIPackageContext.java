//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Stores (in memory only) the Flat/Hierarchical plugin-package layout
 * @author Jeff Hardy
 *
 */
public class PluginUIPackageContext {
	public static final PluginUIPackageContext INSTANCE = new PluginUIPackageContext(); 

	private boolean isFlatLayout = false;
	
	private List<IPluginUIPackageContextChangedListener> listeners;

	public static final String HIERARCHICAL_LAYOUT= "hierarchical"; //$NON-NLS-1$
	public static final String FLAT_LAYOUT= "flat"; //$NON-NLS-1$

	
	public boolean isFlatLayout() {
		return isFlatLayout;
	}
	
	public void toggleLayout() {
		isFlatLayout = !isFlatLayout;
		notifyListeners();
	}
	
	public String getLayoutAsString() {
		if (isFlatLayout()) {
			return FLAT_LAYOUT;
		} else {
			return HIERARCHICAL_LAYOUT;
		}
	}
	
	public void setLayoutHierarchical() {
		isFlatLayout = false;
		notifyListeners();
	}

	public void setLayoutFlat() {
		isFlatLayout = true;
		notifyListeners();
	}
	
	public void setLayoutFlatWithoutNotify(boolean flag) {
		isFlatLayout = flag;
	}
	
	public void addListener(IPluginUIPackageContextChangedListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<IPluginUIPackageContextChangedListener>();
		}
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removeListener(IPluginUIPackageContextChangedListener listener) {
		if (listeners != null) {
			if (listeners.contains(listener)) {
				listeners.remove(listener);
			}
		}
	}
	
	private void notifyListeners() {
		if (listeners != null) {
			for (Iterator<IPluginUIPackageContextChangedListener> iter = listeners.iterator();iter.hasNext();) {
				IPluginUIPackageContextChangedListener listener = iter.next();
				listener.layoutChanged(isFlatLayout);
			}
		}
	}
}
