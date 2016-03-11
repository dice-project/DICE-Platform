/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.model;

import org.eclipse.osgi.util.NLS;

/**
 * The resource class of diagram plugin
 * 
 * @since  1.0
 */
public final class DiagramResources extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.epf.diagram.model.Resources";//$NON-NLS-1$

	private DiagramResources() {
		// Do not instantiate
	}

	public static String type_activity;
	public static String defaultBaseName;
	public static String type_WPD;
	public static String type_activityDetail;
	public static String addFreeTxt;
	public static String type_unknown;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DiagramResources.class);
	}
}
