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
package org.eclipse.epf.ui;

import org.eclipse.osgi.util.NLS;

/**
 * The EPF UI resource bundle.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.2
 */
public final class EPFUIResources extends NLS {

	private static String BUNDLE_NAME = EPFUIResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private EPFUIResources() {
	}

	public static String Dialog_fileNameConflict_title;

	public static String Dialog_fileNameConflict_msg;

	public static String Dialog_fileName_selectAction_label;

	public static String Dialog_fileName_destination;

	public static String Dialog_fileNameConflict_note;

	public static String Dialog_fileName_replace_msg;

	public static String Dialog_fileName_unique_msg;

	public static String internalError_reason;

	public static String initializeWizardError_reason;

	public static String initializeWizardPagesError_reason;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EPFUIResources.class);
	}

}
