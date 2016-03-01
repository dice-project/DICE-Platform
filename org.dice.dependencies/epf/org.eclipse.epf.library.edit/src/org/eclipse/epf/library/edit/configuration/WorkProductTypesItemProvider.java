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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.util.WrapperManager;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * The item provider adapter for the "Work Product Kinds" folder in the
 * Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductTypesItemProvider extends CategoriesItemProvider {

	/**
	 * Creates a new instance.
	 */
	public WorkProductTypesItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig, String name, Object image,
			String[] categoryPkgPath) {
		super(adapterFactory, methodConfig, name, image, categoryPkgPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.configuration.CategoriesItemProvider#createUncategorizedItemProvider()
	 */
	protected UncategorizedItemProvider createUncategorizedItemProvider() {
		return new UncategorizedItemProvider(adapterFactory, methodConfig,
				this.uncategorizedFilter, this.uncategorizedLabel,
				this.uncategorizedImage) {
			final class ElementList extends MethodConfigurationElementList {

				/**
				 * @param methodConfig
				 * @param filterList
				 */
				public ElementList(MethodConfiguration methodConfig,
						List filterList) {
					super(methodConfig, filterList);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.epf.library.edit.configuration.MethodConfigurationElementList#newIterator(org.eclipse.epf.uma.ContentPackage)
				 */
				protected Iterator newIterator(ContentPackage pkg) {
					return new ElementIterator(pkg) {
						/**
						 * Comment for <code>serialVersionUID</code>
						 */
						private static final long serialVersionUID = 2307136307657359282L;

						/*
						 * (non-Javadoc)
						 * 
						 * @see org.eclipse.epf.library.edit.configuration.MethodConfigurationElementList.ElementIterator#getChildrenList(java.lang.Object)
						 */
						protected List getChildrenList(Object object) {
							List children = super.getChildrenList(object);
							if (object instanceof Artifact) {
								children.addAll(((Artifact) object)
										.getContainedArtifacts());
							}
							return children;
						}
					};
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.epf.library.edit.configuration.MethodConfigurationElementList#checkAcceptance()
				 */
				protected boolean checkAcceptance() {
					return false;
				}

				public boolean superAccept(Object object) {
					return super.accept(object);
				}
			};

			// keeps wrappers of artifacts that is not associated with any work
			// product type
			//
			private WrapperManager wrapperManager = new WrapperManager(
					adapterFactory) {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.epf.library.edit.util.WrapperManager#createWrapper(java.lang.Object,
				 *      java.lang.Object,
				 *      org.eclipse.emf.common.notify.AdapterFactory)
				 */
				protected IWrapperItemProvider createWrapper(Object value,
						Object owner, AdapterFactory adapterFactory) {
					return new DelegatingWrapperItemProvider(value, owner,
							null, CommandParameter.NO_INDEX, adapterFactory) {
						/*
						 * (non-Javadoc)
						 * 
						 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#getChildren(java.lang.Object)
						 */
						public Collection getChildren(Object object) {
							// Sub-artifact can have a different set of work
							// product types than the containing artifact.
							// Do not show the composition hierarchies in the
							// Work Product Type tree browsers in Lib. Nav.
							// and Config. Explorer. Show them in flat lists
							// according to their categorizations instead.
							//
							// This is not true for Domains, which should show
							// the artifact nesting!
							return Collections.EMPTY_LIST;
						}

						/*
						 * (non-Javadoc)
						 * 
						 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#hasChildren(java.lang.Object)
						 */
						public boolean hasChildren(Object object) {
							return false;
						}
					};
				}
			};

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.epf.library.edit.configuration.UncategorizedItemProvider#createMethodConfigurationElementList(java.util.List)
			 */
			protected MethodConfigurationElementList createMethodConfigurationElementList(
					List filterList) {
				return new ElementList(methodConfig, filterList);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.epf.library.edit.configuration.UncategorizedItemProvider#getChildren(java.lang.Object)
			 */
			public Collection getChildren(Object object) {
				ElementList elementList = (ElementList) createMethodConfigurationElementList(createFilterList());
				Collection children = elementList.getList();
				ArrayList artifacts = new ArrayList();
				for (Iterator iter = children.iterator(); iter.hasNext();) {
					Object element = (Object) iter.next();
					if (elementList.superAccept(element)) {
						if (element instanceof Artifact) {
							artifacts.add(element);
							iter.remove();
						}
					} else {
						iter.remove();
					}
				}
				wrapperManager.update(artifacts);
				children.addAll(wrapperManager.getWrappers());
				return children;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
			 */
			public void dispose() {
				wrapperManager.dispose();
				super.dispose();
			}
		};
	}

}
