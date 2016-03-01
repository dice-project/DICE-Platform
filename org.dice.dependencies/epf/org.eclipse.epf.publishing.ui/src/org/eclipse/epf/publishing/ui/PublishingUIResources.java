/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epf.publishing.ui;

import org.eclipse.osgi.util.NLS;

/**
 * The Publishing UI resource bundle.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public final class PublishingUIResources extends NLS {

	private static final String BUNDLE_NAME = PublishingUIResources.class
			.getPackage().getName()
			+ ".Resources"; //$NON-NLS-1$

	private PublishingUIResources() {
	}

	public static String publishConfigWizard_title;

	public static String selectConfigWizardPage_title;

	public static String selectConfigWizardPage_text;

	public static String configLabel_text;

	public static String selectContentWizardPage_title;

	public static String selectContentWizardPage_text;

	public static String publishConfigRadioButton_text;

	public static String publishProcessesRadioButton_text;

	public static String includeBaseProcessesCheckboxLabel_text;

	public static String selectPublishingOptionsWizardPage_title;

	public static String selectPublishingOptionsWizardPage_text;

	public static String publishWebSiteGroup_text;
	public static String browsingLayoutGroup_text;
	public static String wpSlotDpRuleGroup_text;
	public static String forbiddenURLCharsGroup_text;
	public static String forbiddenURLCharsText_label;

	public static String titleAndLinksGroup_text;

	public static String lookAndFeelGroup_text;

	public static String titleLabel_text;

	public static String bannerImageLabel_text;

	public static String aboutHTMLLabel_text;

	public static String feedbackURLLabel_text;

	public static String glossaryAndIndexGroup_text;

	public static String publishGlossaryLabel_text;

	public static String publishIndexLabel_text;

	public static String validationGroup_text;

	public static String checkExternalLinksLabel_text;

	public static String convertBrokenLinksLabel_text;

	public static String diagramGroup_text;

	public static String publishActivityDetailDiagramsLabel_text;

	public static String publishExtendedActivityDiagramsLabel_text;

	public static String layoutGroup_text;

	public static String publishLightWeightTreeLabel_text;

	public static String publishExtraDescriptorInfoLabel_text;
	
	public static String showLinkedPageForDescriptorLabel_text;
	
	public static String ignoreDynamicParentsCheckbox_text;
	
	public static String excludeUnusedWPDsCheckbox_text;
	
	public static String fulfillDescriptorSlotByContentCheckbox_text;

	public static String publishEmptyCategoriesLabel_text;

	public static String selectDestinationWizardPage_title;

	public static String selectDestinationWizardPage_text;

	public static String dirLabel_text;

	public static String webAppGroup_text;

	public static String staticWebSiteRadioButton_text;

	public static String dynamicWebAppRadioButton_text;

	public static String includeSearchCheckbox_text;

	public static String webApplicationNameLabel_text;

	public static String destinationDirectoryGroup_text;

	public static String pathLabel_text;

	public static String useDefaultPathCheckbox_text;

	public static String useDefaultFeedbackLabel_text;

	public static String includeSearchesLabel_text;

	public static String clientSideSearchRadioButton_text;

	public static String serverSideSearchRadioButton_text;

	public static String generateWARFileLabel_text;

	public static String useAppletCheckbox_text;

	public static String siteCustomizationWizardPage_title;

	public static String siteCustomizationWizardPage_text;

	public static String themeAndSkinGroup_text;

	public static String showBannerCheckbox_text;

	public static String selectSkinLabel_text;

	public static String capabilityPatterns_text;

	public static String deliveryProcesses_text;

	public static String invalidViewsInfo_msg;

	public static String invalidViewContributorInfo_msg;

	public static String invalidViewSameViewInfo_msg;

	public static String preferencePage_defaultPath_text;

	public static String elementSpacingGroup_text;

	public static String tasksHorizonalSpacingLabel_text;

	public static String tasksVerticalSpacingLabel_text;

	public static String elementTextLabelGroup_text;

	public static String maxLineOfTextLabel_text;

	public static String invalidHorizonalSpacingError_msg;

	public static String invalidVerticalSpacingError_msg;

	public static String invalidTextLinesError_msg;

	public static String publishConfigDialog_title;

	public static String confirmPathDialog_text;

	public static String overwriteDialog_text;

	public static String cancelPublishConfig_msg;

	public static String previewConfigError_title;

	public static String viewReportError_title;

	public static String missingViewError_msg;
	
	public static String missingProcessError_msg;

	public static String cannotPublishError_msg;

	public static String invalidPathError_msg;

	public static String invalidPathCharsError_msg;

	public static String publishConfigError_msg;

	public static String previewConfigError_msg;

	public static String viewReportError_msg;

	public static String publishConfigError_reason;

	public static String deleteFilesError_reason;

	public static String createDirError_reason;

	public static String openBrowserError_reason;

	public static String webAppNameError_msg;

	public static String webAppNameCharsError_msg;

	public static String showRelatedDescriptors_text;
	
	public static String showRelatedLinks_text;
	
	public static String showRelatedDescriptors_option_text;

	public static String showDescriptorsInNavigationTree_text;

	public static String invalidAboutPathError_msg;

	public static String invalidAboutPathCharsError_msg;

	public static String selectDefaultActivityTab_text;

	static {
		NLS.initializeMessages(BUNDLE_NAME, PublishingUIResources.class);
	}

}