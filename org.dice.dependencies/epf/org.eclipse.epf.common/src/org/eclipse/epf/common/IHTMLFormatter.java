/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.common;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public interface IHTMLFormatter {

	/*
	 * String location = m.group(1);
	 * String lineStr = m.group(2);
	 * String columnStr = m.group(3);
	 * String errorMsg = m.group(4);
	 */
	public static final Pattern jTidyErrorParser = Pattern
			.compile(
					"(line\\s+(\\d+)\\s+column\\s+(\\d+))\\s+-\\s+(.+)", Pattern.CASE_INSENSITIVE); //$NON-NLS-1$
	
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.epf.common.IHTMLFormatter"; //$NON-NLS-1$
	
	public static final Pattern p_whitespace = Pattern.compile("^\\s+", Pattern.MULTILINE); //$NON-NLS-1$
	
	public String formatHTML(String html, boolean returnBodyOnly, boolean forceOutput, boolean makeBare, boolean word2000) throws UnsupportedEncodingException;
	
	public String formatHTML(String text) throws UnsupportedEncodingException;
	
	public String getLastErrorStr();
	
	public String removeLeadingWhitespace(String input);
}
