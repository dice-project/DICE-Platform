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
package org.eclipse.epf.publishing.services;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DefaultElementTypeResources 
{
	private static ResourceBundle resourceBundle;
	static
	{
		try 
		{
			String resource = DefaultElementTypeResources.class.getName();
		    resourceBundle = ResourceBundle.getBundle(resource);
			    		    
		} catch (MissingResourceException x) {
		    x.printStackTrace();
			resourceBundle = null;
		}
	}
	

	
	private static String getString(String key)
	{
		try 
		{
			return (resourceBundle != null) ? resourceBundle.getString(key) : null;
		} 
		catch (MissingResourceException e) 
		{
			return null;
		}		
	}
	
	/**
	 * check if a default icon is ised or not for the specified key
	 * 
	 * @param key String
	 * @return boolean
	 */
	public static boolean useDefaultIcon(String key)
	{
		String booleanResult = getString(key);
		if ( booleanResult == null )
		{
			return false;
		}
		
		return (booleanResult.toLowerCase().indexOf("true") > -1) ? true : false; //$NON-NLS-1$
	}	
}