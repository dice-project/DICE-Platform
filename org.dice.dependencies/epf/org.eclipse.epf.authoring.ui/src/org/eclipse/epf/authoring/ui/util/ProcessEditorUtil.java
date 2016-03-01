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
package org.eclipse.epf.authoring.ui.util;

import java.util.Collection;

import org.eclipse.epf.authoring.ui.properties.RemoveDescriptorCommand;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.AddGuidanceToBreakdownElementCommand;
import org.eclipse.epf.library.edit.process.command.AssignPrimaryPerformerToTaskDescriptor;
import org.eclipse.epf.library.edit.process.command.AssignRoleToTaskDescriptor;
import org.eclipse.epf.library.edit.process.command.AssignWPToDeliverable;
import org.eclipse.epf.library.edit.process.command.AssignWPToRoleDescriptor;
import org.eclipse.epf.library.edit.process.command.AssignWPToTaskDescriptor;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.UmaPackage;

/**
 * Utility class that defines static methods for process authoring
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 * 
 * TODO - need to merge with ProcessUtil later. I can't do it now since we need to refactor
 * RemoveDescriptorCommand first
 */

public final class ProcessEditorUtil {

	/** 
	 * Deselect synchronize with source flag
	 */
	public static void deSelectSynchronize(IResourceAwareCommand cmd) {
		if (cmd instanceof AddGuidanceToBreakdownElementCommand ||
				cmd instanceof AssignPrimaryPerformerToTaskDescriptor ||
				cmd instanceof AssignRoleToTaskDescriptor ||
				cmd instanceof AssignWPToTaskDescriptor ||
				cmd instanceof AssignWPToRoleDescriptor ||
				cmd instanceof AssignWPToDeliverable ||
				cmd instanceof RemoveDescriptorCommand) {
			Collection c = cmd.getAffectedObjects();
			for (Object obj: c) {
				if (obj instanceof Descriptor) {
					((Descriptor) obj).setIsSynchronizedWithSource(false);
				}
			}
		}
		
	}
	
	public static void deSelectSynchonize(Descriptor desc, int featureID) {
		if (featureID == UmaPackage.BREAKDOWN_ELEMENT__NAME
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__PRESENTATION_NAME
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__BRIEF_DESCRIPTION
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__CHECKLISTS
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__CONCEPTS
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__EXAMPLES
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__GUIDELINES
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__REUSABLE_ASSETS
				|| featureID == UmaPackage.BREAKDOWN_ELEMENT__SUPPORTING_MATERIALS
				|| featureID == UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT
				|| featureID == UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT
				|| featureID == UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT
				|| featureID == UmaPackage.TASK_DESCRIPTOR__OUTPUT
				|| featureID == UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY
				|| featureID == UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY
				|| featureID == UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY
				|| featureID == UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS
				|| featureID == UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR
				|| featureID == UmaPackage.WORK_PRODUCT_DESCRIPTOR__DELIVERABLE_PARTS
				) {

			desc.setIsSynchronizedWithSource(false);
		}
	}
}

