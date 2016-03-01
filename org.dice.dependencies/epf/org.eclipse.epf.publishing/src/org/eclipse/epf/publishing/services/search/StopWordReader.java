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
package org.eclipse.epf.publishing.services.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * This class reads in a configuration file of stop words
 * and provides the stop words as a list. <br>
 */
class StopWordReader
{
	private static Vector _stopwords = null;

	/**
	 * Default constructor.
	 */
	private StopWordReader()
	{
	}

	/**
	 * Retrieve the set of stopwords from the given file.
	 */
	static String[] getStopwords( String filepath,
	                                     String filename )
	{
		if( null == _stopwords )
		{
			_stopwords = new Vector();

	    	try
 	   		{
				// read in the file
				String fullFilename = filepath +
									  File.separator +
		 							  filename;
		        FileInputStream inStream = new FileInputStream( fullFilename );
				InputStreamReader streamReader = new InputStreamReader( inStream );
				BufferedReader read = new BufferedReader(streamReader);
/*	  	    	char[] charRead = new char[ inStream.available() ];
	        	streamReader.read( charRead );
	        	String content = new String( charRead );
*/

				String word = read.readLine();
				while (word != null)
				{
					_stopwords.add(word);
					word = read.readLine();
				}

	        	// close stream
	        	read.close();
	        	streamReader.close();
	        	inStream.close();

	        	// parseContent
//	        	parseContent( content, _stopwords );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}

		Object[] stopObjects = _stopwords.toArray();
		String[] stopStrings = new String[ stopObjects.length ];
		for( int i = 0; i < stopObjects.length; i++ )
		{
			stopStrings[ i ] = (String)stopObjects[ i ];
		}
		return( stopStrings );

	}

	/**
	 * Parses the content into stopwords.
	 */
/*
	private static void parseContent( String content,
	                                  Vector _stopwords )
	{
		StringTokenizer tokenizer = new StringTokenizer( content,
		                                                 System.getProperty( "line.separator" ) );
		while( tokenizer.hasMoreElements() )
		{
			_stopwords.addElement( tokenizer.nextElement() );
		}
	}
*/
}

