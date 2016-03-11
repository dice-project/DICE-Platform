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

import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;


/**
 * Breakdown Element input for an editor
 * 
 * @author Phong Nguyen Le - Oct 7, 2005
 * @since 1.0
 */
public class BreakdownElementEditorInput extends MethodElementEditorInput {

	private Suppression suppression;

	private BreakdownElementWrapperItemProvider wrapper;

	/**
	 * Creates an instance
	 * @param object
	 */
	public BreakdownElementEditorInput(Object object, Suppression suppression) {
		super((MethodElement) TngUtil.unwrap(object));
		if (object instanceof BreakdownElementWrapperItemProvider) {
			wrapper = (BreakdownElementWrapperItemProvider) object;
		}
		this.suppression = suppression;
	}

	/**
	 * @return Returns the suppression.
	 */
	public Suppression getSuppression() {
		return suppression;
	}

	/**
	 * @return Returns the wrapper.
	 */
	public BreakdownElementWrapperItemProvider getWrapper() {
		return wrapper;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass().isInstance(obj)) {
			BreakdownElementEditorInput other = (BreakdownElementEditorInput) obj;
			return getMethodElement() == other.getMethodElement()
					&& suppression.getProcess() == other.getSuppression()
							.getProcess();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result ^= suppression.getProcess().hashCode();
		return result;
	}
}
