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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * This interface is used by to obtain and resolve method element attributes and
 * references in a method configuration.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public interface IConfigurationApplicator {

	/**
	 * Gets the attribute value of a variability element in a method
	 * configuration.
	 * 
	 * @param ve
	 *            a variability element
	 * @param attr
	 *            an attribute
	 * @param config
	 *            a method configuration
	 * @return the value of the attribute derived from the method configuration
	 *         closure
	 */
	public Object getAttribute(VariabilityElement ve, EAttribute attr,
			MethodConfiguration config);

	/**
	 * Gets the attribute value of a content description in a method
	 * configuration.
	 * 
	 * @param desc
	 *            a content description
	 * @param owner
	 *            the owner of the content description
	 * @param attr
	 *            an attribute of the content description
	 * @param config
	 *            a method configuration
	 * @return the value of the reference derived from the method configuration
	 *         closure
	 */
	public Object getAttribute(ContentDescription desc, DescribableElement owner,
			EAttribute attr, MethodConfiguration config);

	/**
	 * Gets the reference value of a variability element in a method
	 * configuration.
	 * 
	 * @param ve
	 *            a variability element
	 * @param ref
	 *            a reference representation
	 * @param config
	 *            a method configuration
	 * @return the value of the reference derived from the method configuration
	 *         closure
	 */
	public Object getReference(VariabilityElement ve, EReference ref,
			MethodConfiguration config);

	/**
	 * Gets the reference value of a content description in a method
	 * configuration.
	 * 
	 * @param desc
	 *            a content description
	 * @param owner
	 *            the owner of the content description
	 * @param ref
	 *            a reference representation
	 * @param config
	 *            a method configuration
	 * @return the value of the reference derived from the method configuration
	 *         closure
	 */
	public Object getReference(ContentDescription desc, DescribableElement owner,
			EReference ref, MethodConfiguration config);

	/**
	 * Gets the reference value of a method element opposite feature in a method
	 * configuration.
	 * 
	 * @param element
	 *            a method element
	 * @param feature
	 *            an opposite feature
	 * @param config
	 *            a method configuration
	 * @return the value of the reference derived from the method configuration
	 *         closure
	 */
	public Object getReference(MethodElement element, OppositeFeature feature,
			MethodConfiguration config);

	/**
	 * Resolves the given object using the given method configuration.
	 * 
	 * @param object
	 *            a method element
	 * @param config
	 *            a method configuration
	 * @return the resolved method element derived from the method configuration
	 *         closure
	 */
	Object resolve(Object object, MethodConfiguration config);

}
