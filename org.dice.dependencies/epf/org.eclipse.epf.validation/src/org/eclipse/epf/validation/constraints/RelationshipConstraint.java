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
package org.eclipse.epf.validation.constraints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.validation.Activator;
import org.eclipse.epf.validation.ValidationResources;
import org.eclipse.epf.validation.util.ValidationStatus;
import org.eclipse.osgi.util.NLS;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class RelationshipConstraint extends AbstractModelConstraint {
	private static final boolean DEBUG = Activator.getDefault().isDebugging();

	private static final Collection referencesToCheckForCircularDependency = Arrays.asList(new Object[] {
			UmaPackage.Literals.METHOD_PLUGIN__BASES
			, UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT
			, UmaPackage.Literals.ACTIVITY__BREAKDOWN_ELEMENTS
			, UmaPackage.Literals.DELIVERABLE__DELIVERED_WORK_PRODUCTS
			, UmaPackage.Literals.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS
	});

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	public IStatus validate(IValidationContext ctx) {
		if (DependencyChecker.newCheck) {
			return Status.OK_STATUS;
		}
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		if(DEBUG) {
			if(eObj instanceof NamedElement) {
				NamedElement e = (NamedElement) eObj;
				System.out.println("RelationshipConstraint.validate(): " + TngUtil.getTypeText(e) + ": '" + e.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}

		MultiStatus multiStatus = new MultiStatus(Activator.PLUGIN_ID, 0, "", null); //$NON-NLS-1$

		// In the case of batch mode.
		if (eType == EMFEventType.NULL) {
			Collection features = new ArrayList(referencesToCheckForCircularDependency);
			features.retainAll(eObj.eClass().getEAllReferences());
			for (Iterator iter = features.iterator(); iter.hasNext();) {
				EReference ref = (EReference) iter.next();
				if(ref.isMany()) {
					for (Iterator iterator = ((List)eObj.eGet(ref)).iterator(); iterator.hasNext();) {
						Object o = iterator.next();
						IStatus status = DependencyChecker.checkCircularDependency(eObj, ref, o);
						if(!status.isOK()) {
							String msg = status.getMessage();
							if(StrUtil.isBlank(msg)) {
								msg = NLS.bind(ValidationResources.circularDependency_error, ref.getEContainingClass().getName(), ref.getName());
							}
							multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, msg, eObj, ref));
						}
					}
				}
				else {
					Object o = eObj.eGet(ref);
					if(o != null) {
						IStatus status = DependencyChecker.checkCircularDependency(eObj, ref, o);
						if(!status.isOK()) {
							String msg = status.getMessage();
							if(StrUtil.isBlank(msg)) {
								msg = NLS.bind(ValidationResources.circularDependency_error, ref.getEContainingClass().getName(), ref.getName());
							}
							multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, msg, eObj, ref));
						}
					}
				}
			}

			if(multiStatus.isOK()) {
				return ctx.createSuccessStatus();
			}
			else {
				return multiStatus;
			}			
		} 
		
		return ctx.createSuccessStatus();
		
	}

}
