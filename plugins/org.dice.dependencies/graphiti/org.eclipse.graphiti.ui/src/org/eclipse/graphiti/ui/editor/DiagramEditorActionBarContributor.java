/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

/**
 * Provides some standard-actions and adds them to the toolbar or the
 * toplevel-menu.
 * <p>
 * Some of the standard-actions are: undo/redo, delete, copy/paste, alignment,
 * zooming.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class DiagramEditorActionBarContributor extends ActionBarContributor {

	/**
	 * Creates and initializes all Actions.
	 * 
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
	 */
	@Override
	protected void buildActions() {
		addRetargetAction((RetargetAction) ActionFactory.UNDO.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		addRetargetAction((RetargetAction) ActionFactory.REDO.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		addRetargetAction((RetargetAction) ActionFactory.DELETE.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		addRetargetAction((RetargetAction) ActionFactory.COPY.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		addRetargetAction((RetargetAction) ActionFactory.PASTE.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));
		addRetargetAction((RetargetAction) ActionFactory.PRINT.create(PlatformUI.getWorkbench().getActiveWorkbenchWindow()));

		addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));
		addRetargetAction(new MatchWidthRetargetAction());
		addRetargetAction(new MatchHeightRetargetAction());

		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());

		// addRetargetAction(new ConfigureAutoLayoutRetargetAction());
		// addRetargetAction(new PerformAutoLayoutRetargetAction());
		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY, GEFMessages.ToggleGrid_Label, IAction.AS_CHECK_BOX));

		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY, GEFMessages.ToggleSnapToGeometry_Label,
				IAction.AS_CHECK_BOX));
	}

	/**
	 * Declare global action keys.
	 * 
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#declareGlobalActionKeys()
	 */
	@Override
	protected void declareGlobalActionKeys() {
		addGlobalActionKey(ActionFactory.UNDO.getId());
		addGlobalActionKey(ActionFactory.REDO.getId());
		addGlobalActionKey(ActionFactory.COPY.getId());
		addGlobalActionKey(ActionFactory.PASTE.getId());
		addGlobalActionKey(ActionFactory.PRINT.getId());
		addGlobalActionKey(ActionFactory.SAVE_AS.getId());
		addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
	}

	/**
	 * Adds Actions to the given IToolBarManager, which is displayed above the
	 * editor.
	 * 
	 * @param tbm
	 *            the tbm
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(IToolBarManager)
	 */
	@Override
	public void contributeToToolBar(IToolBarManager tbm) {
		tbm.add(getAction(ActionFactory.UNDO.getId()));
		tbm.add(getAction(ActionFactory.REDO.getId()));

		// tbm.add(new Separator());
		// tbm.add(getAction(ActionFactory.PRINT.getId()));
		// tbm.add(getAction(ActionFactory.SAVE_AS.getId()));

		tbm.add(new Separator());
		tbm.add(getAction(ActionFactory.COPY.getId()));
		tbm.add(getAction(ActionFactory.PASTE.getId()));

		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ALIGN_LEFT));
		tbm.add(getAction(GEFActionConstants.ALIGN_CENTER));
		tbm.add(getAction(GEFActionConstants.ALIGN_RIGHT));
		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ALIGN_TOP));
		tbm.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
		tbm.add(getAction(GEFActionConstants.ALIGN_BOTTOM));
		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.MATCH_WIDTH));
		tbm.add(getAction(GEFActionConstants.MATCH_HEIGHT));

		tbm.add(new Separator());
		tbm.add(getAction(GEFActionConstants.ZOOM_OUT));
		tbm.add(getAction(GEFActionConstants.ZOOM_IN));
		ZoomComboContributionItem zoomCombo = new ZoomComboContributionItem(getPage());
		tbm.add(zoomCombo);

		tbm.add(new Separator());
	}

	/**
	 * Adds Actions to the given IMenuManager, which is displayed as the
	 * main-menu of Eclipse.
	 * 
	 * @param menubar
	 *            the menubar
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(IMenuManager)
	 */
	@Override
	public void contributeToMenu(IMenuManager menubar) {
		super.contributeToMenu(menubar);
		MenuManager viewMenu = new MenuManager(Messages.GraphicsActionBarContributor_0_xmen);
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));

		menubar.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
	}

}
