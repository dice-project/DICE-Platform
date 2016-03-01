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
package org.eclipse.epf.authoring.ui.actions;

import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.preferences.ApplicationPreferenceConstants;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.edit.PluginUIPackageContext;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * Code taken from org.eclipse.jdt.internal.ui.packageview.LayoutActionGroup
 *
 */
public class LayoutActionGroup extends MultiActionGroup {

	public LayoutActionGroup(LibraryView libView) {
		super(createActions(libView), getSelectedState(libView));
	}

	/* (non-Javadoc)
	 * @see ActionGroup#fillActionBars(IActionBars)
	 */
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		contributeToViewMenu(actionBars.getMenuManager());
	}
	
	private void contributeToViewMenu(IMenuManager viewMenu) {
		viewMenu.add(new Separator());

		// Create layout sub menu
		
		IMenuManager layoutSubMenu= new MenuManager(AuthoringUIResources.LayoutActionGroup_label); 
		final String layoutGroupName= "layout"; //$NON-NLS-1$
		Separator marker= new Separator(layoutGroupName);

		viewMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		viewMenu.add(marker);
		viewMenu.appendToGroup(layoutGroupName, layoutSubMenu);
		viewMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS+"-end"));//$NON-NLS-1$		
		addActions(layoutSubMenu);
	}

	static int getSelectedState(LibraryView libView) {
		if (PluginUIPackageContext.INSTANCE.isFlatLayout())
			return 0;
		else
			return 1;
	}
	
	static IAction[] createActions(LibraryView libView) {
		IAction flatLayoutAction= new LayoutAction(libView, true);
		flatLayoutAction.setText(AuthoringUIResources.LayoutActionGroup_flatLayoutAction_label); 
		flatLayoutAction.setImageDescriptor(AuthoringUIImages.IMG_DESC_LAYOUT_FLAT);
		IAction hierarchicalLayout= new LayoutAction(libView, false);
		hierarchicalLayout.setText(AuthoringUIResources.LayoutActionGroup_hierarchicalLayoutAction_label);
		hierarchicalLayout.setImageDescriptor(AuthoringUIImages.IMG_DESC_LAYOUT_HIERARCHICAL);
		
		return new IAction[]{flatLayoutAction, hierarchicalLayout};
	}
}

class LayoutAction extends Action implements IAction {

	private boolean fIsFlatLayout;
	private LibraryView flibView;

	public LayoutAction(LibraryView libView, boolean flat) {
		super("", AS_RADIO_BUTTON); //$NON-NLS-1$

		fIsFlatLayout= flat;
		flibView= libView;
		if (fIsFlatLayout)
			PlatformUI.getWorkbench().getHelpSystem().setHelp(this, AuthoringUIHelpContexts.LAYOUT_FLAT_ACTION);
		else
			PlatformUI.getWorkbench().getHelpSystem().setHelp(this, AuthoringUIHelpContexts.LAYOUT_HIERARCHICAL_ACTION);
	}

	/*
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		if (PluginUIPackageContext.INSTANCE.isFlatLayout() != fIsFlatLayout) {
			PluginUIPackageContext.INSTANCE.toggleLayout();
			// save state
			ApplicationPreferenceConstants.setLayout(PluginUIPackageContext.INSTANCE.getLayoutAsString());
		}
		flibView.getViewer().getControl().setRedraw(false);
		flibView.getViewer().refresh();
		flibView.getViewer().getControl().setRedraw(true);

	}
}
