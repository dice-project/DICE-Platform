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

import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;


/**
 * @author Phong Nguyen Le - Oct 10, 2005
 * @since 1.0
 */
public class ContributeToActivityCommand extends VaryActivityCommand {

	/**
	 * @param wrapper
	 */
	public ContributeToActivityCommand(
			BreakdownElementWrapperItemProvider wrapper) {
		super(wrapper);
		setLabel(LibraryEditResources.contributeToActivity_text); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.VaryActivityCommand#doVary()
	 */
	protected void doVary() {
		ProcessUtil.contributeToActivity(wrapper, createdActivities);
	}

}
