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
package org.eclipse.epf.authoring.ui.properties;

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Utility class for properties 
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class PropertiesUtil {

	/**
	 * Returns type of the object
	 * 
	 * @param obj
	 * @return
	 * 		Type
	 */
	public static String getType(Object obj) {
		String prefix = PropertiesResources.BreakdownElement_Type_Name; 

		if (obj instanceof CapabilityPattern) {
			prefix = LibraryUIText.TEXT_CAPABILITY_PATTERN;
		} else if (obj instanceof DeliveryProcess) {
			prefix = LibraryUIText.TEXT_DELIVERY_PROCESS;
		} else if (obj instanceof Process) {
			prefix = LibraryUIText.TEXT_PROCESS;
		} else if (obj instanceof Phase) {
			prefix = LibraryUIText.TEXT_PHASE;
		} else if (obj instanceof Iteration) {
			prefix = LibraryUIText.TEXT_ITERATION;
		} else if (obj instanceof Activity) {
			prefix = LibraryUIText.TEXT_ACTIVITY;
		} else if (obj instanceof TaskDescriptor) {
			prefix = LibraryUIText.TEXT_TASK_DESCRIPTOR;
		} else if (obj instanceof RoleDescriptor) {
			prefix = LibraryUIText.TEXT_ROLE_DESCRIPTOR;
		} else if (obj instanceof WorkProductDescriptor) {
			prefix = LibraryUIText.TEXT_WORK_PRODUCT_DESCRIPTOR;
		} else if (obj instanceof Milestone) {
			prefix = LibraryUIText.TEXT_MILESTONE;
		} else if (obj instanceof TeamProfile) {
			prefix = LibraryUIText.TEXT_TEAM_PROFILE;
		} else if (obj instanceof Artifact) {
			prefix = LibraryUIText.TEXT_ARTIFACT;
		} else if (obj instanceof Deliverable) {
			prefix = LibraryUIText.TEXT_DELIVERABLE;
		} else if (obj instanceof Outcome) {
			prefix = LibraryUIText.TEXT_OUTCOME;
		}

		return prefix;
	}
	
	/**
	 * Return type of the object in lower case
	 * @param obj
	 * @return
	 * 		Type
	 */
	public static String getTypeLower(Object obj) {
		String type = getType(obj);
		
		return StrUtil.toLower(type);
	}

}
