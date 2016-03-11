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
package org.eclipse.epf.search.analysis;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.Tokenizer;

import com.ibm.icu.text.BreakIterator;

/**
 * A text tokenizer that uses ICU4J to segment text into words.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class TextTokenizer extends Tokenizer {

	private final static int BUFFER_SIZE = 4096;

	private String text;

	private BreakIterator iterator;

	/**
	 * Creates a new instance.
	 * 
	 * @param reader
	 *            the text source
	 */
	public TextTokenizer(Reader reader) {
		super(reader);
		StringBuffer textBuffer = new StringBuffer(BUFFER_SIZE);
		char[] buffer = new char[BUFFER_SIZE];
		int charsRead;
		try {
			while ((charsRead = reader.read(buffer, 0, BUFFER_SIZE)) > 0) {
				textBuffer.append(buffer, 0, charsRead);
			}
			text = textBuffer.toString();
			iterator = BreakIterator.getWordInstance();
			iterator.setText(text);
		} catch (IOException e) {
			iterator = null;
		}
	}

	/**
	 * @see org.apache.lucene.analysis.TokenStream#next()
	 */
	public final Token next() throws IOException {
		if (iterator != null) {
			while (true) {
				int start = iterator.current();
				int end = iterator.next();
				if (end != BreakIterator.DONE) {
					String tokenText = text.substring(start, end).toLowerCase();
					if (!tokenText.equals(" ")&&!tokenText.equals(":")) { //$NON-NLS-1$
						if (tokenText.endsWith("'s")) { //$NON-NLS-1$
							tokenText = tokenText.substring(0, tokenText
									.length() - 2);
						}
						return new Token(tokenText, 0, tokenText.length());
					}
				} else {
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public boolean incrementToken() throws IOException {
		return false;
	}

}