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
import java.util.HashSet;
import java.util.List;

import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command for linking method element to descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */

public class LinkMethodElementCommand extends AddMethodElementCommand {

	private Descriptor desc;

	private Object obj;

	private List objects;

	private int featureID;

	private Collection modifiedResources;

	/**
	 * Assign role to task descriptor Used for both additionally performed by
	 * and assisted by
	 */
	public LinkMethodElementCommand(Descriptor desc, Object obj, int featureID) {

		super(TngUtil.getOwningProcess(desc));

		this.desc = desc;
		this.obj = obj;
		this.featureID = featureID;

		if (obj != null) {
			objects = new ArrayList();
			objects.add(obj);
		}
		this.modifiedResources = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {

		if (desc == null)
			return;

		// add to default configuration if not there already
		if (!super.addToDefaultConfiguration(objects))
			return;

		if (!objects.isEmpty())
			redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		switch (featureID) {
		case UmaPackage.TASK_DESCRIPTOR__TASK:
			((TaskDescriptor) desc).setTask((Task) obj);
			break;
		case UmaPackage.ROLE_DESCRIPTOR__ROLE:
			((RoleDescriptor) desc).setRole((Role) obj);
			break;
		case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
			((WorkProductDescriptor) desc).setWorkProduct((WorkProduct) obj);
			break;
		}
	}

	public void undo() {
		if (!objects.isEmpty()) {
			// basically remove from configuration if anything was added
			super.undo();

			switch (featureID) {
			case UmaPackage.TASK_DESCRIPTOR__TASK:
				((TaskDescriptor) desc).setTask(null);
				break;
			case UmaPackage.ROLE_DESCRIPTOR__ROLE:
				((RoleDescriptor) desc).setRole(null);
				break;
			case UmaPackage.WORK_PRODUCT_DESCRIPTOR__WORK_PRODUCT:
				((WorkProductDescriptor) desc).setWorkProduct(null);
				break;
			}
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		if (desc.eResource() != null && !objects.isEmpty()) {
			modifiedResources.add(desc.eResource());
		}
		return modifiedResources;
	}
}
