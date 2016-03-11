//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.wpdd.providers;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;

import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;

import org.eclipse.gmf.runtime.diagram.ui.printing.actions.PrintPreviewAction;

import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.EnhancedPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.RenderedPrintPreviewAction;

import org.eclipse.jface.action.IAction;

/**
 * @generated
 */
public class DiagramContributionItemProvider extends
		AbstractContributionItemProvider {

	/**
	 * @generated
	 */
	protected IAction createAction(String actionId,
			IWorkbenchPartDescriptor partDescriptor) {
		if (actionId.equals(PrintPreviewAction.ID)) {
			return new RenderedPrintPreviewAction(
					new EnhancedPrintActionHelper());
		}
		return super.createAction(actionId, partDescriptor);
	}

}
