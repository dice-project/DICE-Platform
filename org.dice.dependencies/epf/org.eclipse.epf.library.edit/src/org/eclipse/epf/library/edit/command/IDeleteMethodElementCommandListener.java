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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.library.edit.ICommandListener;
import org.eclipse.epf.uma.MethodElement;

/**
 * @author Phong Nguyen Le - Aug 17, 2006
 * @since  1.0
 */
public interface IDeleteMethodElementCommandListener extends ICommandListener {
	/**
	 * Collects objects whose content must be deleted if the given element is deleted.
	 * 
	 * @param collectedObjects
	 * @param element the element that will be deleted.
	 */
	void collectObjectsToDeleteContent(Collection collectedObjects, MethodElement element);	

	/**
	 * Collects objects that should be removed if <code>elementToDelete</code> will be removed from 
	 * <code>references</code> of <code>referencer</code>.
	 * 
	 * @param objectsToRemove output 
	 * @param referencer element that references to elementToDelete
	 * @param references collection of {@link EReference} that contains elementToDelete
	 * @return true if one of the collected objects is the <code>referencer</code> or container of <code>referencer</code>
	 */
	boolean collectObjectsToRemove(Collection objectsToRemove, EObject elementToDelete, EObject referencer, Collection references);

}
