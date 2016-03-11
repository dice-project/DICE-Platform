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
package org.eclipse.epf.authoring.ui.util;

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
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * Editors context helper
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class EditorsContextHelper {

	/**
	 * Set context help for method element
	 * @param uiComposite
	 * @param obj
	 */
	public static void setHelp(Control uiComposite, Object obj) {
		if (obj == null)
			return;

		obj = LibraryUtil.unwrap(obj);

		if ((obj instanceof Role)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_ROLE_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof Task)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_TASK_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof WorkProduct)) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_WORK_PRODUCT_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof Guidance)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_GUIDANCE_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof Discipline)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_DISCIPLINE_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof DisciplineGrouping)) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_DISCIPLINE_GROUPING_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof Domain)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_DOMAIN_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof WorkProductType)) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_WORK_PRODUCT_TYPE_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof RoleSet)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_ROLE_SET_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof RoleSetGrouping)) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_ROLSE_SET_GROUPING_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof Tool)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.EDITOR_TOOL_CONTEXT_HELP_ID);
			return;
		}
		if (obj instanceof CustomCategory) {
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_CUSTOM_CATEGORY_CONTEXT_HELP_ID);
			return;
		}
		if ((obj instanceof MethodPlugin)) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.PLUGIN_EDITOR_DESCRIPTION_ALL_CONTEXT);
			return;
		}
		if (obj instanceof ContentPackage) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.CONTENT_PACKAGE_EDITOR_ALL_CONTEXT);
			return;
		}
		if (obj instanceof MethodConfiguration) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
					AuthoringUIHelpContexts.CONFIGURATION_EDITOR_ALL_CONTEXT);
			return;
		}
		
	}

	/**
	 * Set context help for process type
	 * 
	 * @param uiComposite
	 * @param procType
	 */
	public static void setHelp(Control uiComposite, String procType) {
		if ((procType != null && procType
				.equalsIgnoreCase("capability pattern"))) { //$NON-NLS-1$
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_CP_DESCRIPTION_CONTEXT_HELP_ID);
			return;
		}

		if ((procType != null && procType.equalsIgnoreCase("delivery process"))) { //$NON-NLS-1$
			PlatformUI
					.getWorkbench()
					.getHelpSystem()
					.setHelp(
							uiComposite,
							AuthoringUIHelpContexts.EDITOR_DP_DESCRIPTION_CONTEXT_HELP_ID);
			return;
		}

		PlatformUI.getWorkbench().getHelpSystem().setHelp(uiComposite,
				AuthoringUIHelpContexts.EDITOR_DP_DESCRIPTION_CONTEXT_HELP_ID);
	}

}
