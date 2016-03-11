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

import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * This interface is used by item providers and commands that want to filter
 * down related method elements in a method configuration.
 * 
 * @author Phong Nguyen Le
 * @author Jinhua Xi
 * @since 1.0
 */
public interface IConfigurator extends IMethodConfigurationProvider, IFilter, Adapter {

	void setMethodConfiguration(MethodConfiguration config);
	
	MethodConfiguration getMethodConfiguration();

	/**
	 * If this method return a non-NULL, the item provider will use the returned
	 * collection as children in place of its default chilren.
	 * 
	 * @param obj
	 * @param childFeature
	 * @return
	 */
	Collection getChildren(Object obj, EStructuralFeature childFeature);

	/**
	 * Resolves this object according to variability rules.
	 * 
	 * @param object
	 * @return resolved object that is either the object itself, or its replacer
	 *         depending on the variability of this object in the configuration.
	 */
	Object resolve(Object object);

	/**
	 * Gets variability information for the given VariabilityElement calculated
	 * based on the configuration of this configurator and according to the
	 * variability rules.
	 * 
	 * @param ve
	 * @return
	 * @see VariabilityInfo
	 */
	VariabilityInfo getVariabilityInfo(VariabilityElement ve);
	
	/**
	 * get filter for uncategorized tasks
	 * 
	 * @return IFilter
	 */
	public IFilter getUncategorizedTaskFilter();
	
	/**
	 * get filter for workproducts without a domain
	 * 
	 * @return IFilter
	 */
	public IFilter getDomainUncategorizedWorkProductFilter();
	
	/**
	 * get filter for workproducts without a WP Type
	 * 
	 * @return IFilter
	 */
	public IFilter getWpTypeUncategorizedWorkProductFilter();
	
	/**
	 * get filter for uncategorized roles
	 * 
	 * @return IFilter
	 */
	public IFilter getUncategorizedRoleFilter();

	/**
	 * get filter for uncategorized tool mentors
	 * 
	 * @return IFilter
	 */
	public IFilter getUncategorizedToolMentorFilter();
	
	/**
	 * get filter for disciplines and displine groupings
	 * 
	 * @return IFilter
	 */
	public IFilter getDisciplinesFilter();
	
	/**
	 * get filter for rolesets and roleset groupings
	 * 
	 * @return IFilter
	 */
	public IFilter getRoleSetsFilter();

	/**
	 * @return an IRealizationManager instance
	 */
	public IRealizationManager getRealizationManager();
		
	/**
	 * @param parentObject
	 * @param children
	 * @return
	 */
	public Collection<?> getModifiedChildren(Object parentObject, Collection children);
	
}
