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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;


/**
 * @author Phong Nguyen Le - Oct 12, 2005
 * @since 1.0
 */
public class WorkProductDescriptorCreateCopyCommand extends
		MethodElementCreateCopyCommand {

	/**
	 * @param domain
	 * @param owner
	 * @param copyHelper
	 */
	public WorkProductDescriptorCreateCopyCommand(EditingDomain domain,
			EObject owner, Helper copyHelper) {
		super(domain, owner, copyHelper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand#doExecute()
	 */
	public void doExecute() {
		super.doExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.command.CreateCopyCommand#doGetChildrenToCopy()
	 */
	public Collection doGetChildrenToCopy() {
		Collection children = super.doGetChildrenToCopy();

		WorkProductDescriptor wpd = (WorkProductDescriptor) owner;
		WorkProduct wp = wpd.getWorkProduct();
		if (wp instanceof Deliverable) {
			for (Iterator iter = wpd.getDeliverableParts().iterator(); iter
					.hasNext();) {
				WorkProductDescriptor part = (WorkProductDescriptor) iter
						.next();
				if (part.getSuperActivities() == null && part != wpd) {
					children.add(part);
				}
			}
		} else if (wp instanceof Artifact) {
			if (domain instanceof AdapterFactoryEditingDomain) {
				AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain) domain)
						.getAdapterFactory();
				ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
						.adapt(owner, ITreeItemContentProvider.class);
				for (Iterator iter = itemProvider.getChildren(owner).iterator(); iter
						.hasNext();) {
					children.add(TngUtil.unwrap(iter.next()));
				}
			}
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.edit.command.MethodElementCreateCopyCommand#doGetResult()
	 */
	public Collection doGetResult() {
		ArrayList result = new ArrayList();
		result.add(copy);

		// Children that are shown in BS due to child relationship of its linked
		// ContentElement
		// but they are not directly associated with the owner. We need to add
		// their copies to the result
		// so they will be added to clipboard for later paste
		//
		WorkProductDescriptor wpd = (WorkProductDescriptor) owner;
		WorkProduct wp = wpd.getWorkProduct();
		if (wp instanceof Artifact) {
			if (domain instanceof AdapterFactoryEditingDomain) {
				AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain) domain)
						.getAdapterFactory();
				for (Iterator iter = new AdapterFactoryTreeIterator(
						adapterFactory, wpd, false); iter.hasNext();) {
					Object childCopy = copyHelper.get(TngUtil.unwrap(iter
							.next()));
					if (childCopy != null) {
						result.add(childCopy);
					}
				}
			}
		}

		if (domain instanceof TraceableAdapterFactoryEditingDomain)
			((TraceableAdapterFactoryEditingDomain) domain).addCopyInfo(result,
					copyHelper);
		return result;
	}

}
