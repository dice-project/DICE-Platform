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
package org.eclipse.epf.library.edit.process.command;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Task;

/**
 * Drag and drop command to drop tasks in WBS structure
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class WBSDragAndDropCommand extends BSDragAndDropCommand {

	/**
	 * @param domain
	 * @param owner
	 * @param location
	 * @param operations
	 * @param operation
	 * @param collection
	 */
	public WBSDragAndDropCommand(EditingDomain domain, Object owner,
			float location, int operations, int operation, Collection collection) {
		super(domain, owner, location, operations, operation, collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDragAndDropCommand#accept(java.lang.Object)
	 */
	protected boolean accept(Object obj) {
		// System.out.println("WBSDragAndDropCommand.accept(): obj="+obj);

		if(obj instanceof Task) {
			if(owner instanceof Activity) {
				return true;
//				return ProcessCommandUtil.getValidDescriptor(obj, (Activity) owner,
//						((AdapterFactoryEditingDomain)domain).getAdapterFactory()) == null;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDragAndDropCommand#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	protected Command createDropCommand(Object owner, List dropSrc) {
		return new WBSDropCommand((Activity) owner, dropSrc);
	}

}
