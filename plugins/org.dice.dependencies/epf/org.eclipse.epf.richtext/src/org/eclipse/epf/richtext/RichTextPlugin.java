/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext;

import java.util.Date;

import org.eclipse.epf.common.ui.AbstractPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Rich Text plug-in class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextPlugin extends AbstractPlugin {

	// The shared plug-in instance.
	private static RichTextPlugin plugin;

	// The date and time when this plug-in was started.
	private Date startTime;

	/**
	 * Default constructor.
	 */
	public RichTextPlugin() {
		super();
		plugin = this;
	}

	/**
	 * @see AbstractPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		startTime = new Date();
	}

	/**
	 * @see AbstractPlugin#stop(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared plug-in instance.
	 */
	public static RichTextPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the date and time when this plug-in was started.
	 */
	public Date getStartTime() {
		return startTime;
	}

}
