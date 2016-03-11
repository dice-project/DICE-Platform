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
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Phong Nguyen Le - Aug 22, 2006
 * @since  1.0
 */
public class ResourceAwarePasteFromClipboardCommand extends
		PasteFromClipboardCommand implements IResourceAwareCommand {

	/**
	 * @param domain
	 * @param owner
	 * @param feature
	 * @param index
	 * @param optimize
	 */
	public ResourceAwarePasteFromClipboardCommand(EditingDomain domain, Object owner, Object feature, int index, boolean optimize) {
		super(domain, owner, feature, index, optimize);
	}

	/**
	 * @param domain
	 * @param owner
	 * @param feature
	 * @param index
	 */
	public ResourceAwarePasteFromClipboardCommand(EditingDomain domain, Object owner, Object feature, int index) {
		super(domain, owner, feature, index);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		Collection modifiedResources = new HashSet();
		if(owner instanceof EObject) {
			Resource resource = ((EObject)owner).eResource();
			if(resource != null) {
				modifiedResources.add(resource);
			}
		}
		for (Iterator iter = command.getCommandList().iterator(); iter.hasNext();) {
			Object cmd = iter.next();
			while(!(cmd instanceof IResourceAwareCommand) && cmd instanceof CommandWrapper) {
				cmd = ((CommandWrapper)cmd).getCommand();
			}
			if (cmd instanceof IResourceAwareCommand) {
				modifiedResources.addAll(((IResourceAwareCommand) cmd)
						.getModifiedResources());
			}
		}
		return modifiedResources;
	}

}
