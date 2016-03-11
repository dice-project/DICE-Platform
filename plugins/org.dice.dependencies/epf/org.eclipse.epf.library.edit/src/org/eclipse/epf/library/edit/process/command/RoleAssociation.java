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

import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RoleAssociation {

	public static final int PRIMARY = 0;

	public static final int ADDITIONAL = 1;

	public static final int ASSISTED = 2;

	private int type;

	private RoleDescriptor roleDescriptor;

	private TaskDescriptor taskDescriptor;

	private RoleDescriptor oldRoleDescriptor;

	public RoleAssociation(int type, RoleDescriptor roleDescriptor,
			TaskDescriptor taskDescriptor) {
		this.type = type;
		this.roleDescriptor = roleDescriptor;
		this.taskDescriptor = taskDescriptor;
	}

	public RoleDescriptor getOldRoleDescriptor() {
		return oldRoleDescriptor;
	}

	public void setOldRoleDescriptor(RoleDescriptor oldRoleDescriptor) {
		this.oldRoleDescriptor = oldRoleDescriptor;
	}

	public RoleDescriptor getRoleDescriptor() {
		return roleDescriptor;
	}

	public TaskDescriptor getTaskDescriptor() {
		return taskDescriptor;
	}

	public int getType() {
		return type;
	}

}
