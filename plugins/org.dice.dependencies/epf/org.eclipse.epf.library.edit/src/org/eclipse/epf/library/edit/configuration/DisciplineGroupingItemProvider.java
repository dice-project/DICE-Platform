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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.category.DisciplineCategoryItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for a discipline grouping in the Configuration
 * view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class DisciplineGroupingItemProvider extends
		DisciplineCategoryItemProvider implements IConfigurable {

	private IConfigurator configurator;

	/**
	 * Creates a new instance.
	 */
	public DisciplineGroupingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.category.DisciplineCategoryItemProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.category.DisciplineCategoryItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children;

		// TODO: commented out until Jinhua fixed bugs in ConfigurationFilter
		if (configurator != null) {
			children = configurator.getChildren(object, UmaPackage.eINSTANCE
					.getDisciplineGrouping_Disciplines());
			if (children != null) {
				System.out
						.println("DisciplineGroupingItemProvider.getChildren(): returned from configurator: " + children); //$NON-NLS-1$
				return children;
			}
		}

		children = super.getChildren(object);
		DisciplineGrouping grouping = (DisciplineGrouping) object;
		MethodConfiguration methodConf = (MethodConfiguration) ((CategoriesItemProvider) getParent(object))
				.getTarget();
		TngUtil.addExtendedChildren(grouping, methodConf, children,
				ModelStructure.DEFAULT.disciplineDefinitionPath);

		// set parent
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object adapter = (TngAdapterFactory.INSTANCE)
					.getConfigurationView_ComposedAdapterFactory().adapt(
							iter.next(), ITreeItemContentProvider.class);
			if (adapter instanceof ILibraryItemProvider) {
				((ILibraryItemProvider) adapter).setParent(this);
			}
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		if (filter instanceof IConfigurator) {
			configurator = (IConfigurator) filter;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
	}

}
