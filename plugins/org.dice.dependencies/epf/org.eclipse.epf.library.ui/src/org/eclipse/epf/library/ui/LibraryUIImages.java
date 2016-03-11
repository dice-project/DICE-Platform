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
package org.eclipse.epf.library.ui;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * Provides access to cached copy of commonly referenced images in the library UI.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryUIImages {

	/**
	 * The images associated with the Method Library elements.
	 * <p>
	 * These images are cached and should not be disposed by the user!
	 */
	public static final Image IMG_ACTIVITY = getElementImage("full/obj16/Activity"); //$NON-NLS-1$

	public static final Image IMG_ARTIFACT = getElementImage("full/obj16/Artifact"); //$NON-NLS-1$

	public static final Image IMG_CAPABILITY_PATTERN = getElementImage("full/obj16/CapabilityPattern"); //$NON-NLS-1$

	public static final Image IMG_CHECKLIST = getElementImage("full/obj16/Checklist"); //$NON-NLS-1$

	public static final Image IMG_COMPOSITE_ROLE = getElementImage("full/obj16/CompositeRole"); //$NON-NLS-1$

	public static final Image IMG_CONCEPT = getElementImage("full/obj16/Concept"); //$NON-NLS-1$

	public static final Image IMG_CONTENT_PACKAGE = getElementImage("full/obj16/ContentPackage"); //$NON-NLS-1$

	public static final Image IMG_CUSTOM_CATEGORY = getElementImage("full/obj16/CustomCategory"); //$NON-NLS-1$

	public static final Image IMG_DELIVERABLE = getElementImage("full/obj16/Deliverable"); //$NON-NLS-1$

	public static final Image IMG_DELIVERY_PROCESS = getElementImage("full/obj16/DeliveryProcess"); //$NON-NLS-1$

	public static final Image IMG_DISCIPLINE = getElementImage("full/obj16/Discipline"); //$NON-NLS-1$

	public static final Image IMG_DISCIPLINE_GROUPING = getElementImage("full/obj16/DisciplineGrouping"); //$NON-NLS-1$

	public static final Image IMG_DOMAIN = getElementImage("full/obj16/Domain"); //$NON-NLS-1$

	public static final Image IMG_ESTIMATE = getElementImage("full/obj16/Estimate"); //$NON-NLS-1$

	public static final Image IMG_ESTIMATING_METRIC = getElementImage("full/obj16/EstimatingMetric"); //$NON-NLS-1$

	public static final Image IMG_ESTIMATION_CONSIDERATIONS = getElementImage("full/obj16/EstimationConsiderations"); //$NON-NLS-1$

	public static final Image IMG_EXAMPLE = getElementImage("full/obj16/Example"); //$NON-NLS-1$

	public static final Image IMG_GUIDELINE = getElementImage("full/obj16/Guideline"); //$NON-NLS-1$

	public static final Image IMG_ITERATION = getElementImage("full/obj16/Iteration"); //$NON-NLS-1$	

	public static final Image IMG_METHOD_CONFIGURATON = getElementImage("full/obj16/MethodConfiguration"); //$NON-NLS-1$

	public static final Image IMG_METHOD_LIBARARY = getElementImage("full/obj16/MethodLibrary"); //$NON-NLS-1$

	public static final Image IMG_METHOD_PACKAGE = getElementImage("full/obj16/MethodPackage"); //$NON-NLS-1$

	public static final Image IMG_METHOD_PLUGIN = getElementImage("full/obj16/MethodPlugin"); //$NON-NLS-1$

	public static final Image IMG_MILESTONE = getElementImage("full/obj16/Milestone"); //$NON-NLS-1$

	public static final Image IMG_OUTCOME = getElementImage("full/obj16/Outcome"); //$NON-NLS-1$

	public static final Image IMG_PHASE = getElementImage("full/obj16/Phase"); //$NON-NLS-1$	

	public static final Image IMG_PRACTICE = getElementImage("full/obj16/Practice"); //$NON-NLS-1$

	public static final Image IMG_PROCESS = getElementImage("full/obj16/Process"); //$NON-NLS-1$	

	public static final Image IMG_PROCESS_CONTRIBUTION = getElementImage("full/obj16/ProcessContribution"); //$NON-NLS-1$

	public static final Image IMG_PROCESS_FAMILY = getElementImage("full/obj16/ProcessFamily"); //$NON-NLS-1$

	public static final Image IMG_PROCESS_PACKAGE = getElementImage("full/obj16/ProcessPackage"); //$NON-NLS-1$

	public static final Image IMG_REPORT = getElementImage("full/obj16/Report"); //$NON-NLS-1$

	public static final Image IMG_ROADMAP = getElementImage("full/obj16/Roadmap"); //$NON-NLS-1$

	public static final Image IMG_ROLE_DESCRIPTOR = getElementImage("full/obj16/RoleDescriptor"); //$NON-NLS-1$

	public static final Image IMG_ROLE = getElementImage("full/obj16/Role"); //$NON-NLS-1$

	public static final Image IMG_ROLE_SET = getElementImage("full/obj16/RoleSet"); //$NON-NLS-1$

	public static final Image IMG_ROLE_SET_GROUPING = getElementImage("full/obj16/RoleSetGrouping"); //$NON-NLS-1$

	public static final Image IMG_SUPPORTING_MATERIAL = getElementImage("full/obj16/SupportingMaterial"); //$NON-NLS-1$

	public static final Image IMG_TASK = getElementImage("full/obj16/Task"); //$NON-NLS-1$

	public static final Image IMG_TASK_DESCRIPTOR = getElementImage("full/obj16/TaskDescriptor"); //$NON-NLS-1$

	public static final Image IMG_TEAM_PROFILE = getElementImage("full/obj16/TeamProfile"); //$NON-NLS-1$

	public static final Image IMG_TEMPLATE = getElementImage("full/obj16/Template"); //$NON-NLS-1$

	public static final Image IMG_TERM_DEFINITION = getElementImage("full/obj16/TermDefinition"); //$NON-NLS-1$

	public static final Image IMG_TOOL = getElementImage("full/obj16/Tool"); //$NON-NLS-1$

	public static final Image IMG_TOOL_MENTOR = getElementImage("full/obj16/ToolMentor"); //$NON-NLS-1$

	public static final Image IMG_WHITEPAPER = getElementImage("full/obj16/Whitepaper"); //$NON-NLS-1$

	public static final Image IMG_WORK_PRODUCT = getElementImage("full/obj16/WorkProduct"); //$NON-NLS-1$

	public static final Image IMG_WORK_PRODUCT_TYPE = getElementImage("full/obj16/WorkProductType"); //$NON-NLS-1$

	/**
	 * Returns the image given the relative path.
	 * 
	 * @param path
	 *            a path relative to "icons" folder in the the UMA Edit plug-in
	 * @return an <code>Image</code> object
	 */
	protected static Image getElementImage(String path) {
		return ExtendedImageRegistry.getInstance().getImage(
				UmaEditPlugin.INSTANCE.getImage(path));
	}

}
