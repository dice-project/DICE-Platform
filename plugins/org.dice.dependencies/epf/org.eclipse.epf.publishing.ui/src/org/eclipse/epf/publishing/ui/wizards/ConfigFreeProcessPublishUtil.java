/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2009. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.publishing.ui.wizards;

import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaFactory;

public class ConfigFreeProcessPublishUtil {
	private static final ConfigFreeProcessPublishUtil INSTANCE = new ConfigFreeProcessPublishUtil();
	
	private final String mockMethodConfigurationName =  LibraryEditResources.scope_defualtName;
	private final String mockMethodConfigurationGUID = "ABC0123456789CBA"; //$NON-NLS-1$
	private MethodConfiguration config = null;
	
	private ConfigFreeProcessPublishUtil() {
		//
	}
	
	public static ConfigFreeProcessPublishUtil getInstance() {
		return INSTANCE;
	}
	
	public MethodConfiguration getMethodConfigurationForConfigFreeProcess() {
		if (config == null) {		
			config = UmaFactory.eINSTANCE.createMethodConfiguration();
			config.setGuid(mockMethodConfigurationGUID);
			config.setName(mockMethodConfigurationName);
		}
		
		return config;
	}
	
	public boolean isSameMethodConfiguration(MethodConfiguration input) {
		if (input.getName().equals(config.getName())
				&& input.getGuid().equals(config.getGuid())) {
			return true;
		}
		
		return false;
	}

}
