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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.epf.library.edit.breakdownelement.MilestoneItemProvider;

/**
 * The item provider adapter factory for the Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ItemProviderAdapterFactory extends
		org.eclipse.epf.library.edit.navigator.ItemProviderAdapterFactory {

	public Adapter createMethodConfigurationAdapter() {
		Adapter adapter = new MethodConfigurationItemProvider(this);
		return adapter;
	}

	public Adapter createProcessComponentAdapter() {
		if (processComponentItemProvider == null) {
			processComponentItemProvider = new ProcessComponentItemProvider(
					this);
		}

		return processComponentItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.navigator.MethodpluginItemProviderAdapterFactory#createMethodPluginAdapter()
	 */
	/*
	public Adapter createMethodPluginAdapter() {
		MethodPluginItemProvider adapter = new MethodPluginItemProvider(this);
		adapter.setModelStructure(ModelStructure.DEFAULT);
		return adapter;
	}
	*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.navigator.MethodpluginItemProviderAdapterFactory#createProcessPackageAdapter()
	 */
	public Adapter createProcessPackageAdapter() {
		Adapter adapter = new ProcessPackageItemProvider(this);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createDeliveryProcessAdapter()
	 */
	public Adapter createDeliveryProcessAdapter() {
		return createActivityAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createCapabilityPatternAdapter()
	 */
	public Adapter createCapabilityPatternAdapter() {
		return createActivityAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createActivityAdapter()
	 */
	public Adapter createActivityAdapter() {
		Adapter adapter = new ActivityItemProvider(this);
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createPhaseAdapter()
	 */
	public Adapter createPhaseAdapter() {
		return createActivityAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.BreakdownAdapterFactory#createIterationAdapter()
	 */
	public Adapter createIterationAdapter() {
		return createActivityAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.navigator.ItemProviderAdapterFactory#createDisciplineGroupingAdapter()
	 */
	public Adapter createDisciplineGroupingAdapter() {
		return new DisciplineGroupingItemProvider(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.navigator.ItemProviderAdapterFactory#createDisciplineAdapter()
	 */
	public Adapter createDisciplineAdapter() {
		Adapter adapter = new DisciplineItemProvider(this);
		return adapter;
	}

	public Adapter createDomainAdapter() {
		if (domainItemProvider == null) {
			domainItemProvider = new DomainItemProvider(this);
		}

		return domainItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.navigator.ItemProviderAdapterFactory#createRoleSetAdapter()
	 */
	public Adapter createRoleSetAdapter() {
		Adapter adapter = new RoleSetItemProvider(this);
		return adapter;
	}
	
	@Override
	public Adapter createMilestoneAdapter() {
		return new MilestoneItemProvider(this);
	}
	
	@Override
	public Adapter createPracticeAdapter() {
		return new PracticeItemProvider(this);
	}
}
