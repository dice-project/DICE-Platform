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
package org.eclipse.epf.xml.uma;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Descriptor that represents a Role in the context of one specific Activity.  Every breakdown structure can define different relationships of Role Descriptors to Task Descriptors and Work Product Descriptors.  Therefore one Role can be represented by many Role Descriptors each within the context of an Activity with its own set of relationships.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.RoleDescriptor#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.RoleDescriptor#getResponsibleFor <em>Responsible For</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getRoleDescriptor()
 * @model extendedMetaData="name='RoleDescriptor' kind='elementOnly'"
 * @generated
 */
public interface RoleDescriptor extends Descriptor {
	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see #setRole(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getRoleDescriptor_Role()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Role'"
	 * @generated
	 */
	String getRole();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.RoleDescriptor#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(String value);

	/**
	 * Returns the value of the '<em><b>Responsible For</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible For</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible For</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getRoleDescriptor_ResponsibleFor()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ResponsibleFor'"
	 * @generated
	 */
	EList<String> getResponsibleFor();

} // RoleDescriptor
