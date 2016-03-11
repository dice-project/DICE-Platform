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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.epf.authoring.ui.PerspectiveListUtil;
import org.eclipse.epf.authoring.ui.UIActionDispatcher;
import org.eclipse.epf.authoring.ui.dialogs.ContributionSelection;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.views.ConfigurationView;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;


/**
 * Open a method element editor delegation action
 * 
 * @author BingXue Xu
 * @since 1.0
 */
public class OpenElementActionDelegate implements IObjectActionDelegate {

	private IWorkbenchPart targetPart;

	private ISelection selection;

	private ContributionSelection contributorSelector;

	/**
	 * Creates an instance
	 *
	 */
	public OpenElementActionDelegate() {
		super();
		contributorSelector = new ContributionSelection();
	}

	/**
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	
	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		// System.out.println("$$$ Open element action!");

		// System.out.println("$$$ 5: " + selection);
		IStructuredSelection selected = (IStructuredSelection) selection;

		ArrayList elementList = new ArrayList();

		// System.out.println("$$$ targetPart = " + targetPart); //$NON-NLS-1$
		if (targetPart instanceof ConfigurationView) {
			for (Iterator objects = selected.iterator(); objects.hasNext();) {
				Object element = TngUtil.unwrap(objects.next());
				if (element instanceof VariabilityElement) {
					Object obj = contributorSelector
							.getSelectedContributor((VariabilityElement) element);
					if (obj != null)
						elementList.add(obj);
				} else {
					elementList.add(element);
				}
			}
		} else {
			for (Iterator objects = selected.iterator(); objects.hasNext();) {
				Object element = TngUtil.unwrap(objects.next());
				if (element instanceof MethodElement) {
					elementList.add(element);
				}
			}
		}

		if (elementList.size() < 1)
			return;

		if (PerspectiveListUtil.isBrowsingPerspective()) {
			UIActionDispatcher.openAuthoringPerspective();
		}

		for (Iterator objects = elementList.iterator(); objects.hasNext();) {
			Object element = objects.next();
			EditorChooser.getInstance().openEditor(element);
		}

	}

	/**
	 * Dispose 
	 */
	public void dispose() {

	}
}
