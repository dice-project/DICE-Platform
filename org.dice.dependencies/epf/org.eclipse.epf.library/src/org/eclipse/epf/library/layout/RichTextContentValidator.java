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
package org.eclipse.epf.library.layout;

import java.io.UnsupportedEncodingException;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;

/**
 * Special content validator for the Rich Text Editor
 * @author Jeff Hardy
 *
 */
public class RichTextContentValidator extends DefaultContentValidator {

	/**
	 * validates a URL without decoding it
	 */
	public LinkInfo validateLink(MethodElement owner, String attributes,
			String text, MethodConfiguration config, String tag) {

		LinkInfo info = new LinkInfo(owner, this, pubDir, tag) {
			protected String decode(String str) throws UnsupportedEncodingException {
				return str;
			}
		};
		info.validateLink(attributes, text, config);

		return info;
	}

}
