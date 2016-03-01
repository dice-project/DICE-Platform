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

import org.eclipse.emf.common.notify.Adapter;

/**
 * The item provider adapter factory for the method element selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ItemProviderAdapterFactory extends
		org.eclipse.epf.library.edit.navigator.ItemProviderAdapterFactory {

	public ItemProviderAdapterFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createCustomCategoryAdapter()
	 */
	public Adapter createCustomCategoryAdapter() {
		if (customCategoryItemProvider == null) {
			customCategoryItemProvider = new CustomCategoryItemProvider(this);
		}

		return customCategoryItemProvider;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory#createMethodLibraryAdapter()
	 */
	public Adapter createMethodLibraryAdapter() {
		if (methodLibraryItemProvider == null) {
			methodLibraryItemProvider = new MethodLibraryItemProvider(this);
		}
		return methodLibraryItemProvider;
	}

	public Adapter createMethodPluginAdapter() {
		if (methodPluginItemProvider == null) {
			methodPluginItemProvider = new MethodPluginItemProvider(this);
		}
		return methodPluginItemProvider;
	}

	public Adapter createContentPackageAdapter() {
		if (contentPackageItemProvider == null) {
			contentPackageItemProvider = new ContentPackageItemProvider(this);
		}
		return contentPackageItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.ContentcategoriesAdapterFactory#createDisciplineGroupingAdapter()
	 */
	public Adapter createDisciplineGroupingAdapter() {
		// if(disciplineGroupingItemProvider == null){
		// disciplineGroupingItemProvider = new
		// DisciplineGroupingItemProvider(this);
		// }
		// return disciplineGroupingItemProvider;
		return new DisciplineGroupingItemProvider(this);
	}

	public Adapter createRoleSetGroupingAdapter() {
		// if(roleSetGroupingItemProvider == null){
		// roleSetGroupingItemProvider = new RoleSetGroupingItemProvider(this);
		// }
		// return roleSetGroupingItemProvider;
		return new RoleSetGroupingItemProvider(this);
	}

	public Adapter createRoleSetAdapter() {
		return new RoleSetItemProvider(this);
	}

	public Adapter createMethodConfigurationAdapter() {
		return new MethodConfigurationItemProvider(this);
	}

	public Adapter createDeliveryProcessAdapter() {
//		if (deliveryProcessItemProvider == null) {
//			deliveryProcessItemProvider = new DeliveryProcessItemProvider(this);
//		}
//
//		return deliveryProcessItemProvider;
		
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

	public Adapter createActivityAdapter() {
		return new ActivityItemProvider(this);
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

	public Adapter createDomainAdapter() {
		return new DomainItemProvider(this);
	}

	public Adapter createDisciplineAdapter() {
		return new DisciplineItemProvider(this);
	}

	public Adapter createProcessPackageAdapter() {
		return new ProcessPackageItemProvider(this);
	}

	public Adapter createProcessComponentAdapter() {
		return new ProcessComponentItemProvider(this);
	}

}
