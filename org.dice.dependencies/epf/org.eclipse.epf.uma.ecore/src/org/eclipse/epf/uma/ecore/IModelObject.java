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
package org.eclipse.epf.uma.ecore;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * An interface supported by all UMA model objects.
 * <p>
 * This interface provides dynamic bidirectional relationships support between
 * model objects through the use of opposite features. It also allows the
 * default value of a feature, as defined in a metal model, to be overwritten.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IModelObject extends EObject {

	/**
	 * Gets the default value of a feature.
	 * <p>
	 * This method provides a way to query the overriden default value defined in the
	 * meta-model for a UMA class.
	 * 
	 * @param feature
	 *            a feature
	 * @return the default value for the specified feature
	 */
	public Object getDefaultValue(EStructuralFeature feature);

	/**
	 * Gets the value of an opposite feature.
	 * 
	 * @param feature
	 *            an opposite feature
	 * @return the value for the specified opposite feature
	 */
	public Object getOppositeFeatureValue(OppositeFeature feature);

	/**
	 * Gets all the opposite features associated with this model object.
	 * 
	 * @return a collection of opposite features
	 */
	public Collection getOppositeFeatures();
	
	//=======================================================
	// EDataObject methods
	//=======================================================
	
	public Type getType();
	
	public IModelObject getContainer();
	
	public List<Property> getInstanceProperties();
	
	public List getList(int propertyIndex);
	
	public void set(int propertyIndex, Object value);

}
