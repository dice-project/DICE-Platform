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
package org.eclipse.epf.authoring.ui.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * ContainerCheckedTreeViewer that does not check a parent when all children are checked.
 * Parent will remain grayed until checked by the user.
 * @author Jeff Hardy
 *
 */
public class MethodContainerCheckedTreeViewer2 extends MethodContainerCheckedTreeViewer {

    /**
     * Constructor for ContainerCheckedTreeViewer2.
     * @see CheckboxTreeViewer#CheckboxTreeViewer(Composite)
     */
    public MethodContainerCheckedTreeViewer2(Composite parent) {
        super(parent);
        initViewer();
    }

    /**
     * Constructor for ContainerCheckedTreeViewer2.
     * @see CheckboxTreeViewer#CheckboxTreeViewer(Composite,int)
     */
    public MethodContainerCheckedTreeViewer2(Composite parent, int style) {
        super(parent, style);
        initViewer();
    }

    /**
     * Constructor for ContainerCheckedTreeViewer2.
     * @see CheckboxTreeViewer#CheckboxTreeViewer(Tree)
     */
    public MethodContainerCheckedTreeViewer2(Tree tree) {
        super(tree);
        initViewer();
    }
    
    /**
     * Updates the check / gray state of all parent items
     */
    protected void updateParentItems(TreeItem item) {
        if (item != null) {
            Item[] children = getChildren(item);
            boolean containsChecked = false;
            boolean containsUnchecked = false;
            for (int i = 0; i < children.length; i++) {
                TreeItem curr = (TreeItem) children[i];
                containsChecked |= curr.getChecked();
                containsUnchecked |= (!curr.getChecked() || curr.getGrayed());
            }
            item.setChecked(containsChecked);
//            item.setGrayed(containsChecked && containsUnchecked);
            item.setGrayed(containsChecked);
            if (expandWhenChecking && item.getChecked()) {
            	item.setExpanded(true);
            }
            updateParentItems(item.getParentItem());
        }
    }
    
    public Object[] getCheckButNotGrayedElements() {
    	Object[] checked = getCheckedElements();
    	ArrayList<Object> elements = new ArrayList<Object>();
    	for (int i = 0; i < checked.length; i++) {
			Object element = checked[i];
			if(!getGrayed(element)) {
				elements.add(element);
			}
		}
    	return elements.toArray();
    }
}
