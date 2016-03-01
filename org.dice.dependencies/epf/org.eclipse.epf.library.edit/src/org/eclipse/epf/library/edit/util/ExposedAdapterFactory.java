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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IReferencer;
import org.eclipse.epf.library.edit.process.IColumnAware;


/**
 * An EditingDomainComposedAdapterFactory that exposes internal data
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ExposedAdapterFactory extends EditingDomainComposedAdapterFactory
		implements IColumnAware, IReferencer {

	private Map columnIndexToNameMap;

	/**
	 * @param adapterFactories
	 */
	public ExposedAdapterFactory(AdapterFactory[] adapterFactories) {
		super(adapterFactories);
	}

	public List getChangeListeners() {
		return changeNotifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IColumnAware#setColumnIndexToNameMap(java.util.Map)
	 */
	public void setColumnIndexToNameMap(Map map) {
		columnIndexToNameMap = map;

		for (Iterator iter = adapterFactories.iterator(); iter.hasNext();) {
			Object adapterFactory = iter.next();
			if (adapterFactory instanceof IColumnAware) {
				((IColumnAware) adapterFactory).setColumnIndexToNameMap(map);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IColumnAware#getColumnIndexToNameMap()
	 */
	public Map getColumnIndexToNameMap() {
		return columnIndexToNameMap;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.IReferencer#cleanUp()
	 */
	public void cleanUp() {
		int size = adapterFactories.size();
		for (int i = 0; i < size; i++) {
			Object adapterFactory = adapterFactories.get(i);
			if(adapterFactory instanceof IReferencer) {
				((IReferencer)adapterFactory).cleanUp();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.IReferencer#remove(java.lang.Object)
	 */
	public boolean remove(Object ref) {
		boolean removed = false;
		int size = adapterFactories.size();
		for (int i = 0; i < size; i++) {
			Object adapterFactory = adapterFactories.get(i);
			if(adapterFactory instanceof IReferencer) {
				((IReferencer)adapterFactory).remove(ref);
				removed = true;
			}
		}
		return removed;
	}

}
