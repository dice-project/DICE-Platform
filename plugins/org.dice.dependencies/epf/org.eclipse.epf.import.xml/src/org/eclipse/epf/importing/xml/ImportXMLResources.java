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
package org.eclipse.epf.importing.xml;

import org.eclipse.osgi.util.NLS;

/**
 * The Export XML message resource bundle accessor class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class ImportXMLResources extends NLS {

	private static String BUNDLE_NAME = ImportXMLResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private ImportXMLResources() {
		// Do not instantiate
	}

	public static String importXMLWizard_title;

	public static String selectXMLFilePage_title;

	public static String selectXMLFilePage_desc;

	public static String fileLabel_text;

	public static String browseButton_text;

	public static String optionGroup_text;
	
	public static String optionGroup1_text;

	public static String overwriteRadioButton_text;

	public static String mergeRadioButton_text;
	
	public static String mergeRadioButton2_text;

	public static String importingXML_text;

	public static String review_log_files;

	public static String importXMLError_msg;

	public static String importXMLError_reason;

	public static String invalidXMLFile_error;

	public static String importXMLService_element_not_handled;

	public static String importXMLService_element_without_id;

	public static String importXMLService_import_failed;

	public static String importXMLService_missing_plugin;

	public static String importXMLService_error_missing_plugins;

	public static String library_error_no_eclass;

	public static String library_no_package;

	public static String library_error_create_element;

	public static String library_object_string;

	public static String library_error_set_attribute_2;

	public static String library_error_set_attribute;

	public static String library_error_set_attribute_3;

	public static String library_error_set_reference;

	public static String library_error_set_reference_2;

	public static String versionMismatch_oldData;

	public static String versionMismatch_oldData_unknown;

	public static String versionMismatch_oldTool;
	
	public static String versionMismatch_oldTool_unknown;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ImportXMLResources.class);
	}
}