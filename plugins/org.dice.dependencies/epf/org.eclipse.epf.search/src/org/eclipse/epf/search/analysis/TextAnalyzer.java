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

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

/**
 * A text analyzer that handles Unicode 4.1 characters.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class TextAnalyzer extends Analyzer {

	/**
	 * Creates a new instance.
	 */
	public TextAnalyzer() {
		super();
	}

	/**
	 * @see org.apache.lucene.analysis.Analyzer#tokenStream(String, Reader)
	 */
	public final TokenStream tokenStream(String fieldName, Reader reader) {
		TokenStream result = new TextTokenizer(reader);
		return new TextFilter(result);
	}

}