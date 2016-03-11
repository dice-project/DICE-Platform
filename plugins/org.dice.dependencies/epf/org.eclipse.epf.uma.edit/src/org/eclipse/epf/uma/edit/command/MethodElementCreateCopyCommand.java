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
package org.eclipse.epf.uma.edit.command;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;

/**
 * A command that creates an uninitialized copy of a method element. The
 * necessary initialization will be executed using
 * {@link MethodElementInitializeCopyCommand}.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodElementCreateCopyCommand extends CreateCopyCommand {

	/**
	 * Creates a new instance.
	 * 
	 * @param domain
	 *            the editing domain
	 * @param owner
	 *            the object being copied
	 * @param copyHelper
	 *            a helper class that is used to keep track of copied objects
	 *            and their associated copies
	 */
	public MethodElementCreateCopyCommand(EditingDomain domain, EObject owner,
			Helper copyHelper) {
		super(domain, owner, copyHelper);
	}

	/**
	 * Gets the result of the copy operation.
	 * 
	 * @return a collection containing the copied method element.
	 */
	public Collection doGetResult() {
		Collection collection = super.doGetResult();

		if (domain instanceof TraceableAdapterFactoryEditingDomain) {
			((TraceableAdapterFactoryEditingDomain) domain).addCopyInfo(
					collection, copyHelper);
		}

		return collection;
	}

}
