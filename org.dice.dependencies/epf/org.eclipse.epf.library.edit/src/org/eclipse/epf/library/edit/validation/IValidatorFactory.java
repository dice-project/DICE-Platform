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
package org.eclipse.epf.library.edit.validation;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.validation.internal.ValidatorFactory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.NamedElement;

/**
 * Factory interface to create various validators to validate string value of
 * specified object feature.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IValidatorFactory {
	public static final IValidatorFactory INSTANCE = ValidatorFactory
			.createValidatorFactory();

	IValidator createNameValidator(NamedElement e, AdapterFactory adapterFactory);

	/**
	 * Creates name validator for a content element
	 * 
	 * @param e
	 * @return
	 */
	IValidator createNameValidator(Object parent, ContentElement e);

	/**
	 * Creates name validator for a element
	 * 
	 * @param e
	 * @return
	 */
	IValidator createNameValidator(Object parent, NamedElement e);

	/**
	 * Creates name validator for an element of newType with name of e.getName()
	 * 
	 * @param e
	 * @return
	 */
	IValidator createNameValidator(Object parent, NamedElement e, EClass newType);

	IValidator createPresentationNameValidator(Object parent,
			DescribableElement e);

	/**
	 * Creates a validator for the given feature of the given object. The object
	 * is referenced by the given parent via the specified reference.
	 * 
	 * @param parent
	 * @param reference 
	 * @param object
	 * @param feature
	 *            the feature to validate its value
	 * @param eClass
	 *            the EClass of the object to validate or <code>null</code> if
	 *            not applicable
	 * @return
	 */
	IValidator createValidator(EObject parent, EReference reference,
			EClass eClass, EObject object, EStructuralFeature feature);
	
	IValidator createValidator(EObject parent, EReference reference,
			IFilter childFilter, EObject object, EStructuralFeature feature);	
	
	IValidator createValidator(Object parent, EClass eClass, EObject object,
			EStructuralFeature feature, AdapterFactory adapterFactory);
}
