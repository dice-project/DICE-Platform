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
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;


/**
 * Edits an element in the Configuration view.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationViewEditAction extends BaseSelectionListenerAction {

	/**
	 * The ID for this action.
	 */
	public static final String ACTION_ID = ConfigurationViewEditAction.class
			.getName();

	private IStructuredSelection selected;

	private ContributionSelection contributorSelector;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationViewEditAction(String text) {
		super(text);
		contributorSelector = new ContributionSelection();
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		ArrayList elementList = new ArrayList();
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
	 * Updates this action in response to the given selection changes.
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		selected = selection;

		boolean enableTheMenu = true;

		for (Iterator objects = selection.iterator(); objects.hasNext();) {
			Object element = objects.next();
			Object realObj = TngUtil.unwrap(element);
			if (!(realObj instanceof MethodElement)) {
				enableTheMenu = false;
				break;
			} else if ((realObj instanceof DeliveryProcess)
					|| (realObj instanceof CapabilityPattern)) {
				break;
			} else if ((realObj instanceof Phase)
					|| (realObj instanceof Activity)
					|| (realObj instanceof ProcessPackage)) {
				enableTheMenu = false;
				break;
			}
		}

		return enableTheMenu;
	}

}
