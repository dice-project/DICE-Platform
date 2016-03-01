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
package org.eclipse.epf.publishing.services.index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epf.library.util.ResourceHelper;


class KeyWordStatic {

	// note: pattern with [^>] does not match when there are non-english
	// characters, maybe it's a jre bug?
	static final Pattern p_index_ref = Pattern.compile(
			"<a\\s+?(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
	// "<a\\s+?([^>]*?(name\\s*=\\s*\"XE_|class\\s*=\\s*\"index).*?)>",
	// Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	/**
	 * get the key words with key and text description. There are two types of
	 * index format: 1. old format: <a name="KEY__TEXT" ...> 2. new format: <a
	 * class="index" name="bookmark" key="KEY" text="TEXT" ...> TEXT part is
	 * optional
	 */
	static List getKeyWords(String realString) {
		if (realString == null) {
			System.err
					.println("KeyWordStatic:getKeyWords\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
			return null;
		}

		List anchors = new ArrayList(2); //$NON-NLS-1$

		Matcher m = p_index_ref.matcher(realString);
		while (m.find()) {
			String attributes = m.group(1);
			if (attributes.length() == 0) {
				continue;
			}
			
			Map attrMap = ResourceHelper.getTagAttributes(attributes);
			String keyWord = (String) attrMap.get(ResourceHelper.TAG_ATTR_NAME);
			if (keyWord == null || keyWord.length() == 0) {
				continue;
			}

			String cls = (String) attrMap.get(ResourceHelper.TAG_ATTR_CLASS);
			String key = null;
			String text = null;
			if ((cls != null)
					&& cls.equals(ResourceHelper.TAG_ATTR_VALUE_INDEX)) {
				key = (String) attrMap.get(ResourceHelper.TAG_ATTR_KEY);
				text = (String) attrMap.get(ResourceHelper.TAG_ATTR_TEXT);
			}
			// The getKeyWordPrefix() returns the tag which is used to delimit
			// keywords.
			// Currently this tag is XE and is defined in keywordconfig.txt
			if ((key != null)
					|| keyWord.startsWith(KeyWordIndexHelper.defObj
							.getKeyWordPrefix())) {
				anchors.add(new KeyWordDef(keyWord, key, text));
			}
		}
		return anchors;

	}

	static String convertKeyWord(String keyWord) {
		return convertKeyWord(keyWord, null, null);
	}

	static String convertKeyWord(String keyWord, String key, String text) {

		if (keyWord == null) {
			System.err
					.println("KeyWordStatic:convertKeyWord\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
			return null;
		}
		IO.printDebug("convertKeyWord " + keyWord); // Error if we get a keyword //$NON-NLS-1$
		// that does not contain the
		// tag. //$NON-NLS-1$
		if ((key != null) && (key.length() > 0)) {
			if (text == null || text.length() == 0) {
				return key;
			} else {
				return key + KeyWordIndexHelper.defObj.levelSeparatorReplace
						+ text;
			}
		}

		if (!keyWord.startsWith(KeyWordIndexHelper.defObj.getKeyWordPrefix())) {
			System.out
					.println("KeyWordStatic:convertKeyWord\n" + keyWord + HelpMessages.KEYWORD_SYNTAX_ERROR + KeyWordIndexHelper.defObj.getKeyWordPrefix()); //$NON-NLS-1$
			return null;
		}

		// Remove the first three characters from the keyWord (namely XE_).
		String tmpK = keyWord.substring(3);
		String tmp = ""; //$NON-NLS-1$
		int i = 0;
		int pos = 0;
		while (i != -1) {
			// getKeyWordLevelSeparator() is hard-coded as __
			i = tmpK.indexOf(KeyWordIndexHelper.defObj
					.getKeyWordLevelSeparator(), pos);
			if (i != -1) {
				// Insert a separator between keywords, which in this case is
				// the ":"
				tmp += tmpK.substring(pos, i);
				tmp += KeyWordIndexHelper.defObj.levelSeparatorReplace;
				pos = i + 2;
			} else {
				tmp += tmpK.substring(pos);
			}
		}
		return tmp.replace('_', ' ');
	}

}