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
import java.util.List;

import org.eclipse.epf.richtext.RichTextResources;

/**
 * Models a HTML font name.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class FontName {

	// The user friendly names.
	private static final String NAME_DEFAULT = RichTextResources.fontName_default;

	private static final String NAME_ARIAL = RichTextResources.fontName_arial;

	private static final String NAME_COURIER_NEW = RichTextResources.fontName_courierNew;

	private static final String NAME_TIMES_NEW_ROMAN = RichTextResources.fontName_timesNewRoman;

	private static final String NAME_VERDANA = RichTextResources.fontName_verdana;

	// The internal values.
	private static final String VALUE_DEFAULT = ""; //$NON-NLS-1$

	private static final String VALUE_ARIAL = "Arial, Helvetica, sans-serif"; //$NON-NLS-1$

	private static final String VALUE_COURIER_NEW = "Courier New, Courier, mono"; //$NON-NLS-1$

	private static final String VALUE_TIMES_NEW_ROMAN = "Times New Roman, Times, serif"; //$NON-NLS-1$

	private static final String VALUE_VERDANA = "Verdana, Arial, Helvetica, sans-serif"; //$NON-NLS-1$

	/**
	 * Default font.
	 */
	static public final FontName DEFAULT = new FontName(NAME_DEFAULT,
			VALUE_DEFAULT);

	/**
	 * Arial font.
	 */
	static public final FontName ARIAL = new FontName(NAME_ARIAL, VALUE_ARIAL);

	/**
	 * Courier New font.
	 */
	static public final FontName COURIER_NEW = new FontName(NAME_COURIER_NEW,
			VALUE_COURIER_NEW);

	/**
	 * Times New Roman font.
	 */
	static public final FontName TIMES_NEW_ROMAN = new FontName(
			NAME_TIMES_NEW_ROMAN, VALUE_TIMES_NEW_ROMAN);

	/**
	 * Verdana font.
	 */
	static public final FontName VERDANA = new FontName(NAME_VERDANA,
			VALUE_VERDANA);

	// A list of <code>FontName</code> objects.
	static private final List<FontName> FONT_NAMES = new ArrayList<FontName>();
	static {
		FONT_NAMES.add(DEFAULT);
		FONT_NAMES.add(ARIAL);
		FONT_NAMES.add(COURIER_NEW);
		FONT_NAMES.add(TIMES_NEW_ROMAN);
		FONT_NAMES.add(VERDANA);
	}

	// The font name.
	private String name;

	// The font value.
	private String value;

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            the font name
	 * @param value
	 *            the font value
	 */
	public FontName(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the font name.
	 * 
	 * @return the font name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the font value.
	 * 
	 * @return the font value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the <code>FontName</code> object that is mapped to the given
	 * index.
	 * 
	 * @param index
	 *            an index into the <code>FontName</code> list
	 * @return a <code>FontName</code> object
	 */
	public static FontName getFontName(int index) {
		FontName result = (FontName) FONT_NAMES.get(index);
		if (result != null) {
			return result;
		}
		return DEFAULT;
	}

}