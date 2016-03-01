//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
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
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.LibraryViewSimpleAction.CustomeCategoryAction;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.services.LibraryModificationHelper;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;

/**
 * Unassign method element.
 * 
 * @author Weiping Lu
 * @since  1.5
 */
public class UnassignAction extends CustomeCategoryAction {

	/**
	 * Creates an instance
	 * @param text
	 */
	public UnassignAction(LibraryView libView) {
		super(libView, AuthoringUIResources.unassignAction_text);
	}
	
	protected void doRun() {
		if (! checkModify()) {
			return;
		}
		Collection<Resource> resouceToSave = doRunBeforeSave();
		save(resouceToSave);
	}
	
	/**
	 * @return resources to save
	 */
	private Collection<Resource> doRunBeforeSave() {
		IStructuredSelection selection = (IStructuredSelection) getLibraryView().getSelection();
		Object element = selection.getFirstElement();
		element = TngUtil.unwrap(element);

		if (!(element instanceof CustomCategory)) {
			return null;
		}

		CustomCategory category = (CustomCategory) element;

		EObject container = category.eContainer();
		IStatus status = UserInteractionHelper.checkModify(container,
				getLibraryView().getSite().getShell());
		if (container != null && !status.isOK()) {
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.errorDialog_title,
					AuthoringUIResources.errorDialog_moveError, status);
			return null;
		}
		
		Object parent = getSelectionParentObject();
		if (! (parent instanceof CustomCategory)) {
			return null;
		}
		CustomCategory parentCc = (CustomCategory) parent;						
		unassign(getLibraryView().getSite().getShell(), element, parentCc, new ArrayList());
		
		return Collections.singleton(parentCc.eResource());
		
	}

	public static void unassign(Shell shell, final Object element, final CustomCategory parentCc, final ArrayList usedCategories) {
		final LibraryModificationHelper helper = new LibraryModificationHelper();
		final ArrayList elements  = new ArrayList();
		elements.add(element);
		
		shell.getDisplay().syncExec(new Runnable() {
			public void run() {
				CustomCategoryAssignPage.removeItemsFromModel1(elements, parentCc, usedCategories,
						helper.getActionManager(), CustomCategoryAssignPage.getAncestors(parentCc));
			}
		});

		
		
	}
	

}
