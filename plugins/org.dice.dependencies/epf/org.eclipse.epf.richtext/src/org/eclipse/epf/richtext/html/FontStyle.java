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
package org.eclipse.epf.richtext.html;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.richtext.RichTextResources;

/**
 * Models a HTML font style.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class FontStyle {

	// The user friendly names.
	private static final String NAME_NORMAL = RichTextResources.fontStyle_normal;

	private static final String NAME_SECTION_HEADING = RichTextResources.fontStyle_sectionHeading;

	private static final String NAME_SUBSECTION_HEADING = RichTextResources.fontStyle_subsectionHeading;

	private static final String NAME_SUB_SUBSECTION_HEADING = RichTextResources.fontStyle_subSubsectionHeading;

	private static final String NAME_QUOTE = RichTextResources.fontStyle_quote;

	private static final String NAME_CODE_SAMPLE = RichTextResources.fontStyle_codeSample;

	// The internal values.
	private static final String VALUE_NORMAL = "<p>"; //$NON-NLS-1$

	private static final String VALUE_SECTION_HEADING = "<h3>"; //$NON-NLS-1$

	private static final String VALUE_SUBSECTION_HEADING = "<h4>"; //$NON-NLS-1$

	private static final String VALUE_SUB_SUBSECTION_HEADING = "<h5>"; //$NON-NLS-1$

	private static final String VALUE_QUOTE = "<quote>"; //$NON-NLS-1$

	private static final String VALUE_CODE_SAMPLE = "<code>"; //$NON-NLS-1$

	/**
	 * Font style for normal text.
	 */
	static public final FontStyle NORMAL = new FontStyle(NAME_NORMAL,
			VALUE_NORMAL);

	/**
	 * Font style for section heading.
	 */
	static public final FontStyle SECTION_HEADING = new FontStyle(
			NAME_SECTION_HEADING, VALUE_SECTION_HEADING);

	/**
	 * Font style for sub section heading.
	 */
	static public final FontStyle SUBSECTION_HEADING = new FontStyle(
			NAME_SUBSECTION_HEADING, VALUE_SUBSECTION_HEADING);
	/**
	 * Font style for sub sub section heading.
	 */
	static public final FontStyle SUB_SUBSECTION_HEADING = new FontStyle(
			NAME_SUB_SUBSECTION_HEADING, VALUE_SUB_SUBSECTION_HEADING);

	/**
	 * Font style for quotations.
	 */
	static public final FontStyle QUOTE = new FontStyle(NAME_QUOTE, VALUE_QUOTE);

	/**
	 * Font style for displaying program codes.
	 */
	static public final FontStyle CODE_SAMPLE = new FontStyle(NAME_CODE_SAMPLE,
			VALUE_CODE_SAMPLE);

	// A list of <code>FontStyle</code> objects.
	static private final List<FontStyle> FONT_STYLES = new ArrayList<FontStyle>();
	static {
		FONT_STYLES.add(NORMAL);
		FONT_STYLES.add(SECTION_HEADING);
		FONT_STYLES.add(SUBSECTION_HEADING);
		FONT_STYLES.add(SUB_SUBSECTION_HEADING);
		FONT_STYLES.add(QUOTE);
		FONT_STYLES.add(CODE_SAMPLE);
	}

	// The font style name.
	private String name;

	// The font style value.
	private String value;

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            the font style name
	 * @param value
	 *            the font style value
	 */
	public FontStyle(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the font style name.
	 * 
	 * @return the font style name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the font style value.
	 * 
	 * @return the font style value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the <code>FontStyle</code> object that is mapped to the given
	 * index.
	 * 
	 * @param index
	 *            an index into the <code>FontStyle</code> list
	 * @return a <code>FontStyle</code> object
	 */
	public static FontStyle getFontStyle(int index) {
		FontStyle result = (FontStyle) FONT_STYLES.get(index);
		if (result != null) {
			return result;
		}
		return NORMAL;
	}

	/**
	 * Gets the display name of the <code>FontStyle</code> object with the
	 * given value.
	 * 
	 * @param value
	 *            one of the FontStyles
	 * @return a display name of a FontStyle, or null if none found
	 */
	public static String getFontStyleName(String value) {
		for (Iterator<FontStyle> iter = FONT_STYLES.iterator(); iter.hasNext();) {
			FontStyle style = iter.next();
			if (style.getValue().equals(value)) {
				return style.getName();
			}
		}
		return null;
	}

	/**
	 * Gets the value of the <code>FontStyle</code> object with the
	 * given display name.
	 * 
	 * @param name
	 *            one of the FontStyles
	 * @return a value of a FontStyle, or null if none found
	 */
	public static String getFontStyleValue(String name) {
		for (Iterator<FontStyle> iter = FONT_STYLES.iterator(); iter.hasNext();) {
			FontStyle style = iter.next();
			if (style.getName().equals(name)) {
				return style.getValue();
			}
		}
		return null;
	}

}