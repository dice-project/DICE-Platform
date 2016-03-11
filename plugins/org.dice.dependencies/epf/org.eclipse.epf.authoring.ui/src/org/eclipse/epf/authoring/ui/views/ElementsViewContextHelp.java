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
package org.eclipse.epf.authoring.ui.views;

import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * Context Help for element
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class ElementsViewContextHelp {

	/**
	 * Sets the help of the given selection
	 * @param selection
	 * @param uiComposite
	 */
	public static void setHelp(ISelection selection, Composite uiComposite) {
		IStructuredSelection selected = (IStructuredSelection) selection;
		// if multiple items selected, only the first item's context help will
		// be displayed
		Object obj = selected.getFirstElement();
		// System.out.println("$$$ Setting help for the selection obj = " +
		// obj);
		setHelp(obj, uiComposite);
	}

	/**
	 * Sets the help of the object
	 * @param obj
	 * @param uiComposite
	 */
	public static void setHelp(Object obj, Composite uiComposite) {
		if (obj == null)
			return;

		obj = LibraryUtil.unwrap(obj);

		if ((obj instanceof MethodPlugin)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof ContentPackage)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof Role)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof Task)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof WorkProduct)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof Guidance)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof Discipline)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof DisciplineGrouping)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(
					uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof Domain)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof WorkProductType)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof RoleSet)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof Tool)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
		if ((obj instanceof RoleSetGrouping)) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}

		if (obj instanceof CustomCategory) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}

		if (obj instanceof ProcessComponent) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}

		if (obj instanceof Process) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}

		if (obj instanceof MethodConfiguration) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.LIBRARY_VIEW_NODE_CONTEXT_NOT_IMPLEMENTED);
			return;
		}
	}
}
