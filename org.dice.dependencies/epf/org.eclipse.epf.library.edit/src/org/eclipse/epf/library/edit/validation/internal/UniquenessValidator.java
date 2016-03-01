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
package org.eclipse.epf.library.edit.validation.internal;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.osgi.util.NLS;

/**
 * Validates the uniqueness of an object within a given feature that contains it.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UniquenessValidator implements IValidator {
	protected EObject object;

	private EStructuralFeature feature;

	private String featureName;

	private EStructuralFeature containingFeature;

	private IFilter childFilter;

	protected EObject container;

	public UniquenessValidator(EObject container,
			EStructuralFeature containingFeature, IFilter childFilter,
			EObject obj, EStructuralFeature feature) {
		this(container, containingFeature, childFilter, obj, feature, StrUtil
				.toLower(TngUtil.getFeatureText(feature)));
	}

	public UniquenessValidator(EObject container,
			EStructuralFeature containingFeature, IFilter childFilter,
			EObject obj, EStructuralFeature feature, String featureName) {
		this.container = container;
		object = obj;
		this.containingFeature = containingFeature;
		this.childFilter = childFilter;
		this.feature = feature;
		this.featureName = featureName;
	}
	
	void setChildFilter(IFilter childFilter) {
		this.childFilter = childFilter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.String)
	 */
	public String isValid(String newText) {
		if (container == null)
			return null;
		newText = newText.trim();
		if (StrUtil.isBlank(newText)) {
			// return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
			if (object instanceof EObject && 
					feature == UmaPackage.eINSTANCE.getNamedElement_Name()) {				
				return NLS.bind(LibraryEditResources.emptyElementNameError_msg,
						TngUtil.getTypeText((EObject) object));
			}			
			return NLS.bind(LibraryEditResources.emptyElementNameError_msg,
					featureName);
		}
		Collection children;
		if (containingFeature == null) {
			children = container.eContents();
		} else {
			children = (Collection) container.eGet(containingFeature);
		}
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child != object && childFilter.accept(child)) {
				String name = (String) ((EObject) child).eGet(feature);
				if (name.equalsIgnoreCase(newText)) {
					// return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
					return NLS.bind(
							LibraryEditResources.duplicateElementNameError_msg,
							newText);
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.Object)
	 */
	public IStatus isValid(Object value) {
		if (container == null)
			return Status.OK_STATUS;
		Collection children;
		if (containingFeature == null) {
			children = container.eContents();
		} else {
			children = (Collection) container.eGet(containingFeature);
		}
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child != object && childFilter.accept(child)) {
				String o = (String) ((EObject) child).eGet(feature);
				if (o.equals(value)) {
					return new Status(
							IStatus.ERROR,
							LibraryEditPlugin.getDefault().getId(),
							0,
							NLS
									.bind(
											LibraryEditResources.duplicateFeatureValue,
											new Object[] {
													TngUtil.getLabel(container),
													featureName
											}), null);
				}
			}
		}
		return Status.OK_STATUS;
	}

	public EObject getEObject() {
		return object;
	}
}
