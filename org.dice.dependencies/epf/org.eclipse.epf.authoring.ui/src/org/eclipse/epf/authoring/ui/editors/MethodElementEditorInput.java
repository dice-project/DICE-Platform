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
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.ui.IMethodElementProvider;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;


/**
 * An editor input for method element editor
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodElementEditorInput implements IEditorInput, IMethodElementProvider {

	private MethodElement methodElement;

	private ImageDescriptor imageDescriptor = null;

	// the current object being edited by the RTE
	private EObject fModalObject;

	// the current feature being edited by the RTE
	private EStructuralFeature fModalObjectFeature;

	public MethodElementEditorInput(MethodElement e) {
		methodElement = e;
	}

	public MethodElement getMethodElement() {
		return methodElement;
	}

	/**
	 * 
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		return false;
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {
		return methodElement.getName();
	}
	
	/**
	 * 
	 * @return Name of method element + "." + name of feature being edited
	 */
	public String getFullName() {
		String result = getName();
		if (fModalObjectFeature != null) {
			result += ".";	//$NON-NLS-1$
			result += fModalObjectFeature.getName();
		}
		return result;
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass().isInstance(obj)) {
			return methodElement == ((MethodElementEditorInput) obj).getMethodElement() &&
			fModalObject == ((MethodElementEditorInput) obj).getModalObject() &&
			fModalObjectFeature == ((MethodElementEditorInput) obj).getModalObjectFeature();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = methodElement.hashCode();
		if (fModalObject != null) {
			result ^= fModalObject.hashCode();
		}
		if (fModalObjectFeature != null) {
			result ^= fModalObjectFeature.hashCode();
		}
		return result;
	}

	public EObject getModalObject() {
		return fModalObject;
	}

	public void setModalObject(EObject object) {
		fModalObject = object;
	}

	
	public EStructuralFeature getModalObjectFeature() {
		return fModalObjectFeature;
	}

	public void setModalObjectFeature(EStructuralFeature feature) {
		fModalObjectFeature = feature;
	}

}
