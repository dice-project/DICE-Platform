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
 * Models a HTML block tag.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class BlockTag {

	// The user friendly names.
	private static final String NAME_PARAGRAPH = RichTextResources.blockTag_paragraph;

	private static final String NAME_HEADING_1 = RichTextResources.blockTag_heading1;

	private static final String NAME_HEADING_2 = RichTextResources.blockTag_heading2;

	private static final String NAME_HEADING_3 = RichTextResources.blockTag_heading3;

	private static final String NAME_HEADING_4 = RichTextResources.blockTag_heading4;

	private static final String NAME_HEADING_5 = RichTextResources.blockTag_heading5;

	private static final String NAME_HEADING_6 = RichTextResources.blockTag_heading6;

	private static final String NAME_ADDRESS = RichTextResources.blockTag_address;

	private static final String NAME_PREFORMATTED_TEXT = RichTextResources.blockTag_preformattedText;

	// The internal values.
	private static final String VALUE_PARAGRAPH = "<p>"; //$NON-NLS-1$

	private static final String VALUE_HEADING_1 = "<h1>"; //$NON-NLS-1$

	private static final String VALUE_HEADING_2 = "<h2>"; //$NON-NLS-1$

	private static final String VALUE_HEADING_3 = "<h3>"; //$NON-NLS-1$

	private static final String VALUE_HEADING_4 = "<h4>"; //$NON-NLS-1$

	private static final String VALUE_HEADING_5 = "<h5>"; //$NON-NLS-1$

	private static final String VALUE_HEADING_6 = "<h6>"; //$NON-NLS-1$

	private static final String VALUE_ADDRESS = "<address>"; //$NON-NLS-1$

	private static final String VALUE_PREFORMATTED_TEXT = "<pre>"; //$NON-NLS-1$

	/**
	 * The &lt;p&gt; tag.
	 */
	static public final BlockTag PARAGRAPH = new BlockTag(NAME_PARAGRAPH,
			VALUE_PARAGRAPH);

	/**
	 * &lt;h1&gt; tag.
	 */
	static public final BlockTag HEADING_1 = new BlockTag(NAME_HEADING_1,
			VALUE_HEADING_1);

	/**
	 * &lt;h2&gt; tag.
	 */
	static public final BlockTag HEADING_2 = new BlockTag(NAME_HEADING_2,
			VALUE_HEADING_2);

	/**
	 * &lt;h3&gt; tag.
	 */
	static public final BlockTag HEADING_3 = new BlockTag(NAME_HEADING_3,
			VALUE_HEADING_3);

	/**
	 * &lt;h4&gt; tag.
	 */
	static public final BlockTag HEADING_4 = new BlockTag(NAME_HEADING_4,
			VALUE_HEADING_4);

	/**
	 * &lt;h5&gt; tag.
	 */
	static public final BlockTag HEADING_5 = new BlockTag(NAME_HEADING_5,
			VALUE_HEADING_5);

	/**
	 * &lt;h6&gt; tag.
	 */
	static public final BlockTag HEADING_6 = new BlockTag(NAME_HEADING_6,
			VALUE_HEADING_6);

	/**
	 * &lt;address&gt; tag.
	 */
	static public final BlockTag ADDRESS = new BlockTag(NAME_ADDRESS,
			VALUE_ADDRESS);

	/**
	 * &lt;pre&gt; tag.
	 */
	static public final BlockTag PREFORMATTED_TEXT = new BlockTag(
			NAME_PREFORMATTED_TEXT, VALUE_PREFORMATTED_TEXT);

	// A list of <code>BlockTag</code> objects.
	static private final List<BlockTag> BLOCK_TAGS = new ArrayList<BlockTag>();
	static {
		BLOCK_TAGS.add(PARAGRAPH);
		BLOCK_TAGS.add(HEADING_1);
		BLOCK_TAGS.add(HEADING_2);
		BLOCK_TAGS.add(HEADING_3);
		BLOCK_TAGS.add(HEADING_4);
		BLOCK_TAGS.add(HEADING_5);
		BLOCK_TAGS.add(HEADING_6);
		BLOCK_TAGS.add(ADDRESS);
		BLOCK_TAGS.add(PREFORMATTED_TEXT);
	}

	// The block tag name.
	private String name;

	// The block tag value.
	private String value;

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            the block tag name
	 * @param value
	 *            the block tag value
	 */
	public BlockTag(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the block tag name.
	 * 
	 * @return the block tag name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the block tag value.
	 * 
	 * @return the block tag value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the <code>BlockTag</code> object that is mapped to the given
	 * index.
	 * 
	 * @param index
	 *            an index into the <code>BlockTag</code> list
	 * @return a <code>BlockTag</code> object
	 */
	public static BlockTag getBlockTag(int index) {
		BlockTag result = (BlockTag) BLOCK_TAGS.get(index);
		if (result != null) {
			return result;
		}
		return PARAGRAPH;
	}

}