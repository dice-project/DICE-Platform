/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2009. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.diagram.model.util;

import org.eclipse.epf.common.ui.AbstractPlugin;

public class DiagramModelPlugin extends AbstractPlugin {
	public static final String PLUGIN_ID = "org.eclipse.epf.diagram.model"; //$NON-NLS-1$
	
	private static DiagramModelPlugin plugin;
	
	public DiagramModelPlugin() {
		plugin = this;
	}
	
	public static DiagramModelPlugin getDefault() {
		return plugin;
	}
	
	public static String getPluginId() {
		return PLUGIN_ID;
	}

}
