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
package org.eclipse.epf.library.layout;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.uma.MethodConfiguration;



/**
 * The process filter with adaptor factory.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ProcessAdapterFactoryFilter extends ProcessConfigurator implements
		IAdapterFactoryFilter {

	private ElementLayoutManager.LayoutAdapterFactory factory;

	/**
	 * constructor the filter
	 * 
	 * @param methodConfig
	 * @param factory
	 */
	public ProcessAdapterFactoryFilter(MethodConfiguration methodConfig, ElementLayoutManager.LayoutAdapterFactory factory) {
		super(methodConfig);
		this.factory = factory;
	}

	/**
	 * @return AdapterFactory
	 */
	public AdapterFactory getWBSAdapterFactory() {
		return factory != null ? factory.wbsAdapterFactory : null;
	}

	/**
	 * @return AdapterFactory
	 */
	public AdapterFactory getTBSAdapterFactory() {
		return factory != null ? factory.tbsAdapterFactory : null;
	}

	/**
	 * @return AdapterFactory
	 */
	public AdapterFactory getWPBSAdapterFactory() {
		return factory != null ? factory.wpbsAdapterFactory : null;
	}

	/**
	 * @return AdapterFactory
	 */
	public AdapterFactory getCBSAdapterFactory() {
		return factory != null ? factory.cbsAdapterFactory : null;
	}

	/**
	 * accept the object or not.
	 * @param obj Object
	 * @return boolean
	 */
	public boolean accept(Object obj) {
		return super.accept(obj);
	}

	public void dispose() {
		factory = null;
	}
}
