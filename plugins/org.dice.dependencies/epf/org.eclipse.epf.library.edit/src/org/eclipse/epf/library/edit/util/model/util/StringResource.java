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
package org.eclipse.epf.library.edit.util.model.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * Resource to store string
 * 
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class StringResource extends XMIResourceImpl {
	private ByteArrayOutputStream out;

	private String str;

	public StringResource(String str) {
		this.str = str;
	}

	public void save(Map options) throws IOException {
		out = new ByteArrayOutputStream();
		try {
			save(out, options);
		} finally {
			out.close();
		}
	}

	public String getString() {
		if (out == null)
			return null;
		if (encoding == null) {
			return out.toString();
		}
		try {
			return out.toString(encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void load(Map options) throws IOException {
		if (!isLoaded) {
			InputStream inputStream = new StringBufferInputStream(str);
			try {
				load(inputStream, options);
			} finally {
				inputStream.close();
			}
		}
	}

}
