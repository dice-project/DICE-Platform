/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2009. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.diagram.model.util;

import org.eclipse.jface.preference.IPreferenceStore;

public class DiagramModelPreference {
	private static final String USE_STATE_ON_WORKPRODUCT = "Use_State_on_Workproduct";  //$NON-NLS-1$
	
	private static IPreferenceStore prefStore = DiagramModelPlugin.getDefault().getPreferenceStore();
	
	static {
		prefStore.setDefault(USE_STATE_ON_WORKPRODUCT, false);
	}
	
	public static void setUseStateOnWorkproduct(boolean enabled) {
		prefStore.setValue(USE_STATE_ON_WORKPRODUCT, enabled);
	}

	public static boolean getUseStateOnWorkproduct() {
		return prefStore.getBoolean(USE_STATE_ON_WORKPRODUCT);
	}
	
}
