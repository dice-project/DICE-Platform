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
package org.eclipse.epf.authoring.ui.richtext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.widgets.Label;


/**
 * The interface for a Rich Text control used in the Method editors.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IMethodRichText extends IRichText {

	/**
	 * Returns the method element associated with this rich text control.
	 */
	public MethodElement getMethodElement();

	/**
	 * Returns the modal object associated with this rich text control.
	 */
	public EObject getModalObject();

	/**
	 * Sets the modal object associated with this rich text control.
	 */
	public void setModalObject(EObject modalObject);

	/**
	 * Returns modal object feature associated with this rich text control.
	 */
	public EStructuralFeature getModalObjectFeature();

	/**
	 * Sets the modal object feature associated with this rich text control.
	 */
	public void setModalObjectFeature(EStructuralFeature modalObjectFeature);

	/**
	 * Initialize the MethodRichText with the edited MethodElemnt and name of the field
	 * @param element
	 * @param fieldName
	 */
	public void init(MethodElement element, Label label);

}
