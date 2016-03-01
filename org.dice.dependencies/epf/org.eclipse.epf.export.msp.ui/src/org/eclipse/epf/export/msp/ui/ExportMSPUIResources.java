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
package org.eclipse.epf.export.msp.ui;

import org.eclipse.osgi.util.NLS;

/**
 * The Export Microsoft Project UI message resource bundle accessor class.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public final class ExportMSPUIResources extends NLS {

	private static String BUNDLE_NAME = ExportMSPUIResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private ExportMSPUIResources() {
		// Do not instantiate
	}

	public static String exportMSPWizard_title;

	public static String selectExportOptionsWizardPage_title;

	public static String selectExportOptionsWizardPage_text;

	public static String selectedProcessLabel_text;

	public static String configurationLabel_text;

	public static String publishWebSiteCheckBox_text;

	public static String publishConfigButton_text;

	public static String publishProcessButton_text;

	public static String exportOnlyPlannedElementsCheckBox_text;

	public static String selectPublishOptionsWizardPage_title;

	public static String selectPublishOptionsWizardPage_text;

	public static String selectExportDirWizardPage_title;

	public static String selectExportDirWizardPage_text;

	public static String projectNameLabel_text;

	public static String dirLabel_text;

	public static String browseButton_text;

	public static String exportMSPTask_name;

	public static String overwriteText_msg;
	
	public static String missingProcessContentWarning_msg;

	public static String completedText_msg;

	public static String exportMSPError_msg;

	public static String exportMSPError_reason;

	public static String exportMSPInternalError_msg;

	public static String illegalArgument_msg;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ExportMSPUIResources.class);
	}

}