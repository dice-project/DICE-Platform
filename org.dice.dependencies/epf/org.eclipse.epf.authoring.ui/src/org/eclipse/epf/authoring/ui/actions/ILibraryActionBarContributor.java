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
package org.eclipse.epf.authoring.ui.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.part.IPage;

/**
 * The interface for the Library view action bar contributor.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public interface ILibraryActionBarContributor extends
		IEditorActionBarContributor {

	public IViewPart getActiveView();

	public void setActiveView(IViewPart part);

	public void shareGlobalActions(IPage page, IActionBars actionBars);

	public void menuAboutToShow(IMenuManager menuManager);

	public void deactivate();

	public void disableGlobalEditMenu();

	public void enableGlobalEditMenu();

}
