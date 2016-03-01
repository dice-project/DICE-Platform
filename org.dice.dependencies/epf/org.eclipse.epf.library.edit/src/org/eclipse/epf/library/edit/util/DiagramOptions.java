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
package org.eclipse.epf.library.edit.util;


import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElementProperty;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class DiagramOptions {
	
	private static final String PROP_PUBLISH_AD = "publish_ad"; //$NON-NLS-1$
	
	private static final String PROP_PUBLISH_ADD = "publish_add"; //$NON-NLS-1$
	
	private static final String PROP_PUBLISH_WPDD = "publish_wpdd"; //$NON-NLS-1$
	
	public static final void setPublishAD(Activity activity, boolean b){
		MethodElementPropertyHelper.setProperty(activity, PROP_PUBLISH_AD, b);
	}
	
	public static final boolean isPublishAD(Activity activity){
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(activity, PROP_PUBLISH_AD);
		return prop != null ? Boolean.parseBoolean(prop.getValue()) : true; 
	}
	
	public static final void setPublishADD(Activity activity, boolean b){
		MethodElementPropertyHelper.setProperty(activity, PROP_PUBLISH_ADD, b);
	}
	
	public static final boolean isPublishADD(Activity activity){
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(activity, PROP_PUBLISH_ADD);
		return prop != null ? Boolean.parseBoolean(prop.getValue()) : true; 
	}
	
	public static final void setPublishWPDD(Activity activity, boolean b){
		MethodElementPropertyHelper.setProperty(activity, PROP_PUBLISH_WPDD, b);
	}
	
	public static final boolean isPublishWPDD(Activity activity){
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(activity, PROP_PUBLISH_WPDD);
		return prop != null ? Boolean.parseBoolean(prop.getValue()) : true; 
	}
}
