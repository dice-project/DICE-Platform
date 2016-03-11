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
package org.eclipse.epf.diagram.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.MethodElement;

/**
 * This interface represents objects that is linked with other object in a
 * difference model
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model abstract="true"
 */
public interface LinkedObject extends EObject {

	Object getObject();

	void setObject(Object value);

	/**
	 * @model
	 */
	MethodElement getLinkedElement();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.diagram.model.LinkedObject#getLinkedElement <em>Linked Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Element</em>' reference.
	 * @see #getLinkedElement()
	 * @generated
	 */
	void setLinkedElement(MethodElement value);

}
