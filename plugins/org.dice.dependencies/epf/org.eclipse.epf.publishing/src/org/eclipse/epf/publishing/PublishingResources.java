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
package org.eclipse.epf.publishing;

import org.eclipse.osgi.util.NLS;

/**
 * The Publishing resource bundle.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public final class PublishingResources extends NLS {

	private static String BUNDLE_NAME = PublishingResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private PublishingResources() {
		// Do not instantiate
	}

	public static String publishingConfigurationTask_name;

	public static String initializingDirTask_name;

	public static String copyingFilesTask_name;

	public static String generatingBookmarksTask_name;

	public static String generatingBookmarkTask_name;

	public static String generatingBookmarkIndexTask_name;

	public static String publishingLinkedElementTask_name;

	public static String publishingElementTask_name;

	public static String generatingGlossaryTask_name;

	public static String generatingSearchIndexTask_name;

	public static String generatingWARFileTask_name;

	public static String buildingProcessClosureTask_name;

	public static String buildingElementClosureTask_name;

	public static String loadLibraryTask_name;

	public static String publishElementError_msg;

	public static String serverError_msg;

	public static String invalidHttpResponseError_msg;

	public static String invalidElementWarning_msg;

	public static String invalidMethodElementWarning_msg;

	public static String copyFileWarning_msg;

	public static String missingIconFileWarning_msg;

	public static String missingIconNameWarning_msg;

	public static String externalUrl_msg;

	public static String createSearchIndexError_msg;

	public static String referenceWorkflowsNode_text;

	public static String taskNode_text;

	public static String primarilyPerformsNode_text;

	public static String additionallyPerformsNode_text;

	public static String performingRolesNode_text;

	public static String inputWorkProductsNode_text;

	public static String outputWorkProductsNode_text;

	public static String responsibleForNode_text;

	public static String modifiesNode_text;

	public static String responsibleRoleNode_text;

	public static String containingWorkProductNode_text;

	public static String containedWorkProductsNode_text;

	public static String guidanceNode_text;

	public static String inputToNode_text;

	public static String outputFromNode_text;

	public static String indexLabel_text;

	public static String discaredCategoryWarning_msg;

	static {
		NLS.initializeMessages(BUNDLE_NAME, PublishingResources.class);
	}

}