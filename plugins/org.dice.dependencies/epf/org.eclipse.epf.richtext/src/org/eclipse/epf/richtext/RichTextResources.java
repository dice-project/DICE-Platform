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
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.richtext;

import org.eclipse.osgi.util.NLS;

/**
 * The RichText resources.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public final class RichTextResources extends NLS {

	private static String BUNDLE_NAME = RichTextResources.class
		.getPackage().getName() + ".Resources"; //$NON-NLS-1$	

	private RichTextResources() {
		// Do not instantiate.
	}

	public static String fontNameAction_CSS_Default;
	public static String fontNameAction_CSS_Default_Mozilla;
	public static String fontNameAction_DefaultFontName;
	public static String fontStyle_normal;
	public static String fontStyle_sectionHeading;
	public static String fontStyle_subsectionHeading;
	public static String fontStyle_subSubsectionHeading;
	public static String fontStyle_quote;
	public static String fontStyle_codeSample;
	public static String blockTag_paragraph;
	public static String blockTag_heading1;
	public static String blockTag_heading2;
	public static String blockTag_heading3;
	public static String blockTag_heading4;
	public static String blockTag_heading5;
	public static String blockTag_heading6;
	public static String blockTag_address;
	public static String blockTag_preformattedText;
	public static String fontName_default;
	public static String fontName_arial;
	public static String fontName_courierNew;
	public static String fontName_timesNewRoman;
	public static String fontName_verdana;
	public static String addHorizontalBarAction_toolTipText;
	public static String addImageAction_toolTipText;
	public static String addLineAction_toolTipText;
	public static String addLinkAction_toolTipText;
	public static String addOrderedListAction_toolTipText;
	public static String addTableAction_toolTipText;
	public static String addUnorderedListAction_toolTipText;
	public static String boldAction_toolTipText;
	public static String clearContentAction_toolTipText;
	public static String copyAction_toolTipText;
	public static String cutAction_toolTipText;
	public static String findReplaceAction_toolTipText;
	public static String indentAction_toolTipText;
	public static String italicAction_toolTipText;
	public static String justifyCenterAction_toolTipText;
	public static String justifyFullAction_toolTipText;
	public static String justifyLeftAction_toolTipText;
	public static String justifyRightAction_toolTipText;
	public static String outdentAction_toolTipText;
	public static String pasteAction_toolTipText;
	public static String pastePlainTextAction_toolTipText;
	public static String subscriptAction_toolTipText;
	public static String superscriptAction_toolTipText;
	public static String underlineAction_toolTipText;
	public static String fontStyleAction_toolTipText;
	public static String blockTagAction_toolTipText;
	public static String fontNameAction_toolTipText;
	public static String fontSizeAction_toolTipText;
	public static String cutAction_text;
	public static String copyAction_text;
	public static String pasteAction_text;
	public static String pastePlainTextAction_text;
	public static String richTextTab_text;
	public static String richTextTab_toolTipText;
	public static String htmlTab_text;
	public static String htmlTab_toolTipText;
	public static String addImageDialog_title;
	public static String addLinkDialog_title;
	public static String addTableDialog_title;
	public static String findReplaceDialog_title;
	public static String urlDisplayNameLabel_text;
	public static String urlLabel_text;
	public static String summaryLabel_text;
	public static String captionLabel_text;
	public static String rowsLabel_text;
	public static String columnsLabel_text;
	public static String widthLabel_text;
	public static String tableStyleLabel_text;
	public static String tableHeaderNone_text;
	public static String tableHeaderCols_text;
	public static String tableHeaderRows_text;
	public static String tableHeaderBoth_text;
	public static String browseButton_text;
	public static String clearContentDialog_title;
	public static String clearContentDialog_text;
	public static String findLabel_text;
	public static String replaceLabel_text;
	public static String optionsGroup_text;
	public static String caseSensitiveCheckbox_text;
	public static String wholeWordCheckbox_text;
	public static String directionGroup_text;
	public static String forwardRadioButton_text;
	public static String backwardRadioButton_text;
	public static String findButton_text;
	public static String replaceButton_text;
	public static String replaceFindButton_text;
	public static String replaceallButton_text;
	public static String FindReplace_Status_noMatch_label;
	public static String maxCharsPerLineLabel_text;
	public static String indentHTMLCheckbox_text;
	public static String indentSizeLabel_text;
	public static String tidy_clean_text;
	public static String tidy_cleaner_text;
	public static String tidy_cleanest_text;
	public static String tidy_clean_toolTipText;
	public static String tidy_cleaner_toolTipText;
	public static String tidy_cleanest_toolTipText;
	public static String addCodeAction_toolTipText;
	public static String addCodeDialog_title;
	public static String addCodeDialog_Msg;

	
	public static String addRowAction_text;
	public static String addColumnAction_text;
//	public static String addRowAction_toolTipText;
//	public static String addColumnAction_toolTipText;
	public static String deleteLastRowAction_text;
	public static String deleteLastColumnAction_text;
//	public static String deleteLastRowAction_toolTipText;
//	public static String deleteLastColumnAction_toolTipText;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, RichTextResources.class);
	}
}