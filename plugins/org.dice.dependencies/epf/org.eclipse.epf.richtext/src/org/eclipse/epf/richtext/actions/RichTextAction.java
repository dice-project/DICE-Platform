/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext.actions;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolItem;

/**
 * The abstract implementation of a rich text action.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public abstract class RichTextAction extends Action {

	protected IRichText richText;
	
	protected ToolItem toolItem;
	
	protected Collection<IAction> fMenuActions = new LinkedHashSet<IAction>();
	
    protected IMenuCreator menuCreator = new IMenuCreator() {

    	private MenuManager dropDownMenuMgr;

        /**
         * Creates the menu manager for the drop-down.
         */
        private void createDropDownMenuMgr() {
            if (dropDownMenuMgr == null) {
                dropDownMenuMgr = new MenuManager();
                for (Iterator<IAction> iter = getMenuActions().iterator();iter.hasNext();) {
                	IAction item = iter.next();
                    dropDownMenuMgr.add(item);
                }
            }
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
         */
        public Menu getMenu(Control parent) {
            createDropDownMenuMgr();
            return dropDownMenuMgr.createContextMenu(parent);
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
         */
        public Menu getMenu(Menu parent) {
            createDropDownMenuMgr();
            Menu menu = new Menu(parent);
            IContributionItem[] items = dropDownMenuMgr.getItems();
            for (int i = 0; i < items.length; i++) {
                IContributionItem item = items[i];
                IContributionItem newItem = item;
                if (item instanceof ActionContributionItem) {
                    newItem = new ActionContributionItem(
                            ((ActionContributionItem) item).getAction());
                }
                newItem.fill(menu, -1);
            }
            return menu;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.action.IMenuCreator#dispose()
         */
        public void dispose() {
            if (dropDownMenuMgr != null) {
                dropDownMenuMgr.dispose();
                dropDownMenuMgr = null;
            }
        }
    };

	/**
	 * Creates a new instance.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public RichTextAction(IRichText richText, int style) {
		super(null, style);
		this.richText = richText;
	}

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in readonly mode.
	 */
	public boolean disableInReadOnlyMode() {
		return true;
	}

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in source edit mode.
	 */
	public boolean disableInSourceMode() {
		return true;
	}

	public void setToolItem(ToolItem toolItem) {
		this.toolItem = toolItem;
	}
	
	@Override
	public void run() {
		execute(richText);
	}
	
	@Override
	public String getId() {
		return getClass().getCanonicalName();
	}
	
	public Collection<IAction> getMenuActions() {
		return fMenuActions;
	}
	
	public void addActionToMenu(IAction action) {
		fMenuActions.add(action);
	}
	
	public abstract void execute(IRichText richText);
}
