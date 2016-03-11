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
package org.eclipse.epf.uma;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Semantic Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.SimpleSemanticModelElement#getTypeInfo <em>Type Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.uma.UmaPackage#getSimpleSemanticModelElement()
 * @model
 * @generated
 */
public interface SimpleSemanticModelElement extends SemanticModelBridge {
	/**
	 * Returns the value of the '<em><b>Type Info</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Info</em>' attribute.
	 * @see #setTypeInfo(String)
	 * @see org.eclipse.epf.uma.UmaPackage#getSimpleSemanticModelElement_TypeInfo()
	 * @model default="" dataType="org.eclipse.epf.uma.String" ordered="false"
	 * @generated
	 */
	String getTypeInfo();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.uma.SimpleSemanticModelElement#getTypeInfo <em>Type Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Info</em>' attribute.
	 * @see #getTypeInfo()
	 * @generated
	 */
	void setTypeInfo(String value);

} // SimpleSemanticModelElement
