/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.msproject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Working Times</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.WorkingTimes#getWorkingTime <em>Working Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getWorkingTimes()
 * @model extendedMetaData="name='WorkingTimes_._type' kind='elementOnly'"
 * @generated
 */
public interface WorkingTimes extends EObject {
	/**
	 * Returns the value of the '<em><b>Working Time</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.WorkingTime}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the working time during the weekday.
	 *                                                                          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Working Time</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getWorkingTimes_WorkingTime()
	 * @model type="org.eclipse.epf.msproject.WorkingTime" containment="true" resolveProxies="false" upper="5"
	 *        extendedMetaData="kind='element' name='WorkingTime' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getWorkingTime();

} // WorkingTimes
