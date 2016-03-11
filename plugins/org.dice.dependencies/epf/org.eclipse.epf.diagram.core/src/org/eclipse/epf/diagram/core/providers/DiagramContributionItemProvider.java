/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.core.providers;

import org.eclipse.epf.diagram.core.actions.DelegateAction;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Core diagram contribution item provider. 
 * It provides contributions generic to EPF diagram editors
 * @author Shashidhar Kannoori
 *
 */
public class DiagramContributionItemProvider
		extends
		org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContributionItemProvider {


	@Override
	protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
		if(actionId.equals(ActionFactory.DELETE.getId())){
			return new DelegateAction(partDescriptor.getPartPage());
		}
		return super.createAction(actionId, partDescriptor);
	}
}
