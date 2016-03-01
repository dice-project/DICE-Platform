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

import org.eclipse.core.runtime.IStatus;

/**
 * Checks the status of an executed command.
 * 
 * @author Phong Nguyen Le - Oct 21, 2005
 * @since 1.0
 */
public final class CommandStatusChecker {

	public static final boolean hasRollbackError(IStatus status) {
		return hasError(status, SaveStatus.ROLLBACK_ERROR);
	}

	public static final boolean hasSaveError(IStatus status) {
		return hasError(status, SaveStatus.SAVE_ERROR);
	}

	public static final boolean hasError(IStatus status, int errCode) {
		if (status == null)
			return false;

		IStatus[] statuses = status.getChildren();
		boolean ret = false;
		for (int i = 0; i < statuses.length; i++) {
			IStatus childStatus = statuses[i];
			if (childStatus instanceof SaveStatus
					&& (childStatus.getCode() & errCode) == errCode) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
