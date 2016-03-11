//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * A HTML parser used for extracting the meta tags and text from a HTML
 * document.
 * 
 * TODO: Implement our own HTML parser.
 */
public class LHTMLParser extends org.apache.lucene.demo.html.HTMLParser {

	/**
	 * Creates a new instance.
	 */
	public LHTMLParser(File file) throws FileNotFoundException {
		super(file);
	}

	/**
	 * Creates a new instance.
	 */
	public LHTMLParser(InputStream stream) throws FileNotFoundException {
		super(stream);
	}

	/**
	 * Creates a new instance.
	 */
	public LHTMLParser(Reader stream) throws FileNotFoundException {
		super(stream);
		doParse();
	}

	/**
	 * Add this method due to class org.apache.lucene.demo.html.HTMLParser has
	 * been changed since Eclipse 3.5
	 */
	@SuppressWarnings("restriction")
	private void doParse() {
		try {
			parse();
		} catch (Exception e) {
			
		}
//		try {
//			Method m = this.getClass().getMethod("parse");
//			m.invoke(this);
//		} catch (NoSuchMethodException e) {		
//		} catch (Exception e) {
//		}
	}
	
	/**
	 * Gets the HTML meta tags.
	 */
	public Properties getMetaTags() throws IOException, InterruptedException {
		return super.getMetaTags();
	}

	/**
	 * Gets the reader for the HTML content.
	 */
	public Reader getReader() throws IOException {
		return super.getReader();
	}

	/**
	 * Gets the HTML title.
	 */
	public String getTitle() throws IOException, InterruptedException {
		return super.getTitle();
	}
	
	/**
	 * Gets a summary of the HTL document.
	 */
	public String getSummary() throws IOException, InterruptedException {
		return super.getSummary();
	}
}