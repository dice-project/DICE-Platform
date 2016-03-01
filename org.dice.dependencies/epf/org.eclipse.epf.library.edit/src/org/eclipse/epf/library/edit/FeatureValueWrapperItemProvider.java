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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;

/**
 * A subclass of DelegatingWrapperItemProvider that keeps a reference to the
 * containing feature of the wrapped value and implements Comparable to support
 * sorting.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class FeatureValueWrapperItemProvider extends
		DelegatingWrapperItemProvider implements Comparable {

	public FeatureValueWrapperItemProvider(EStructuralFeature feature,
			Object value, Object owner, AdapterFactory adapterFactory) {
		super(value, owner, feature, CommandParameter.NO_INDEX, adapterFactory);
	}

	public EStructuralFeature getFeature() {
		return feature;
	}		

	public List getNotifyChangedListeners() {
		if(changeNotifier instanceof Collection) {
			return new ArrayList((Collection) changeNotifier);
		}
		return Collections.EMPTY_LIST;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#createWrapper(java.lang.Object, java.lang.Object, org.eclipse.emf.common.notify.AdapterFactory)
	 */
	protected IWrapperItemProvider createWrapper(Object value, Object owner, AdapterFactory adapterFactory) {
		return new FeatureValueWrapperItemProvider(null, TngUtil.unwrap(value), owner, adapterFactory);
	}

	/**
	 * Fills the given wrappers by keeping existing, removing/dispose old, and
	 * adding new ones.
	 * 
	 * @param wrappers
	 * @param feature
	 * @param values
	 * @param owner
	 * @param adapterFactory
	 */
	public static void fill(Collection wrappers, EStructuralFeature feature,
			Collection values, Object owner, AdapterFactory adapterFactory) {
		for (Iterator iter = wrappers.iterator(); iter.hasNext();) {
			FeatureValueWrapperItemProvider wrapper = (FeatureValueWrapperItemProvider) iter
					.next();
			if (!values.remove(wrapper.getValue())) {
				iter.remove();
				wrapper.dispose();
			}
		}
		for (Iterator iter = values.iterator(); iter.hasNext();) {
			wrappers.add(new FeatureValueWrapperItemProvider(feature, iter
					.next(), owner, adapterFactory));
		}
	}

	public int compareTo(Object o) {
		Object otherValue = ((IWrapperItemProvider) o).getValue();
		if (!(value instanceof MethodElement)
				|| !(otherValue instanceof MethodElement))
			return 0;
		return ((MethodElement) value).getName().compareTo(
				((MethodElement) otherValue).getName());
	}

	public void fireNotifyChanged(Notification notification) {
		super.fireNotifyChanged(notification);
	}

}
