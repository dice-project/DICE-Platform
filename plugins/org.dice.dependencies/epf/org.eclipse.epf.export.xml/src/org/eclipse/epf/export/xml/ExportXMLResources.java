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
package org.eclipse.epf.export.xml;

import org.eclipse.osgi.util.NLS;

/**
 * The Export XML message resource bundle accessor class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class ExportXMLResources extends NLS {

	private static String BUNDLE_NAME = ExportXMLResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private ExportXMLResources() {
		// Do not instantiate
	}

	public static String exportXMLWizard_title;

	public static String exportLibraryRadioButton_text;

	public static String exportPluginsRadioButton_text;

	public static String exportConfigsRadioButton_text;

	public static String selectXMLFilePage_title;

	public static String selectXMLFilePage_desc;

	public static String fileLabel_text;

	public static String browseButton_text;

	public static String overwriteText_msg;
	
	public static String overwriteText_msg1;

	public static String exportingXML_text;

	public static String invalidXMLFile_error;

	public static String exportXMLWizard_error;

	public static String exportXMLWizard_reviewLog;

	public static String selectExportTypePage_desc;

	public static String exportXMLService_error;

	public static String exportXMLService_feature_error;

	public static String xmlLibrary_error_load_xml;

	public static String xmlLibrary_no_plugin;

	public static String xmlLibrary_no_feature;

	public static String xmlLibrary_no_class;

	public static String xmlLibrary_error_create_element;

	public static String xmlLibrary_error_set_value;

	public static String xmlLibrary_invalid_feature_value;

	public static String xmlLibrary_error_set_value_2;

	public static String xmlLibrary_error_set_value_3;

	public static String xmlLibrary_error_set_reference;

	public static String xmlLibrary_error_process_wrong_container;

	public static String xmlLibrary_new_id;

	public static String xmlLibrary_id_not_unique;
	
	public static String export_config_to_temp_location;
	
	public static String open_lib_from_temp_exported_location;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ExportXMLResources.class);
	}
}