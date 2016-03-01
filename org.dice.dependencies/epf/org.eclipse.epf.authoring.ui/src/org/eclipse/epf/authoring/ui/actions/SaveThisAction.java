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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * Action for saving resources
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SaveThisAction extends Action {

	private ArrayList selectedObjects;

	public SaveThisAction() {
		super(AuthoringUIResources.SaveThisAction_saveobject); 
	}

	/**
	 * Update selection
	 * @param selection
	 * 			Selection
	 * @return 
	 * 			boolean value to indicate whether update was done
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		selectedObjects = new ArrayList();
		for (Iterator objects = selection.iterator(); objects.hasNext();) {
			selectedObjects.add(AdapterFactoryEditingDomain.unwrap(objects
					.next()));
		}

		return selectedObjects.size() == 1
				&& selectedObjects.get(0) instanceof EObject;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		if (selectedObjects == null || selectedObjects.isEmpty())
			return;
		FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(),
				SWT.SAVE);
		String path = fd.open();
		if (path == null)
			return;
		URI fileURI = URI.createFileURI(path);
		// Create the resource to persist the model.
		Resource res = new XMIResourceFactoryImpl().createResource(fileURI);
		Object obj = selectedObjects.get(0);
		res.getContents().add((EObject) obj);
		try {
			res.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
