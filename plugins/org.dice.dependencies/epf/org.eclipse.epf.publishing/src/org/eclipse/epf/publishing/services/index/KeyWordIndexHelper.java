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

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.ibm.icu.util.StringTokenizer;

public class KeyWordIndexHelper {
	KeyWordHolder holder = new KeyWordHolder();

	static DefinitionObject defObj = null;

	private static String deffile = null;

	private static String charSet = null;

	private static String helpFile = null;

	// Constructor
	KeyWordIndexHelper() {

		deffile = null;
		// do nothing

	}

	/**
	 * Constructor
	 * @param pDeffile
	 * @param pCharSet
	 * @param pHelpFile
	 */
	public KeyWordIndexHelper(String pDeffile, String pCharSet, String pHelpFile) {
		deffile = pDeffile;
		charSet = pCharSet;
		helpFile = pHelpFile;
	}

	void setDeffile(String pFile) {

		deffile = pFile;

	}

	void setCharSet(String pCharSet) {

		charSet = pCharSet;

	}

	void setHelpFile(String pHelpFile) {

		helpFile = pHelpFile;

	}

	/**
	 * load the definition object 
	 * 
	 * @param pubDir String
	 * @return DefinitionObject
	 * @throws Exception
	 */
	public DefinitionObject loadDefinition(String pubDir) throws Exception {
		if (deffile == null) {
			throw new KeyWordIndexException(
					"KeyWordIndexHelper:execute()", StringDefinitions.ERROR_DEFINITION_FILE_NOT_FOUND); //$NON-NLS-1$
		}

		if (helpFile != null) {
			HelpMessages.loadHelpMessages(helpFile, charSet);
		}

		defObj = new DefinitionObject(pubDir, deffile, charSet);

		return defObj;
	}

	/**
	 * execute the keyword index generation.
	 * 
	 * @param monitor IProgressMonitor
	 * @throws KeyWordIndexException
	 */
	public void execute(IProgressMonitor monitor) throws KeyWordIndexException {

		if (monitor.isCanceled()) {
			return;
		}

		// Check if enough input in definitionfile
		if (defObj.enoughInput()) {
			File topDir = new File(defObj.getWwwRoot());

			// The wwwRoot have to be a directory
			if (topDir.isDirectory()) {
				System.out.println(HelpMessages.PARSE_DIRECTORY
						+ defObj.getWwwRoot());
				System.out.println(HelpMessages.TAKE_A_WHILE);
				readDir(topDir, monitor);
			} else {
				throw new KeyWordIndexException(
						"KeyWordIndexHelper:execute()", //$NON-NLS-1$
						Def.StartDir
								+ ": " + defObj.getWwwRoot() + HelpMessages.NO_DIRECTORY); //$NON-NLS-1$
			}

			if (monitor.isCanceled()) {
				return;
			}

			// Splits and sorts the keywords
			holder.divide();

			String specKeyWordsFile = defObj.getKeyWordFile();
			if (specKeyWordsFile != null) {
				// Reads the speckeywordfile
				String specKeyWords = MiscStatic.loadFile(specKeyWordsFile,
						defObj.getCharacterSet());
				if (!specKeyWords.equals(Def.None)) {
					StringTokenizer parser = new StringTokenizer(specKeyWords,
							"\t\n"); //$NON-NLS-1$
					holder.readSpecKeyWords(parser);
				} else {
					throw new KeyWordIndexException(
							"KeyWordIndexHelper:execute()", //$NON-NLS-1$
							specKeyWordsFile
									+ HelpMessages.KEYWORD_FILE_SYNTAX_ERROR);
				}
			}

			// Print the KeyWordIndexHelper
			holder.print();
		} else {
			throw new KeyWordIndexException("KeyWordIndexHelper:execute()", //$NON-NLS-1$
					HelpMessages.NOT_ENOUGH_INPUT);
		}

	}

	// ------------------------------------------------------------------------------------------------------
	// Recursively process each directory in the directory heirarchy.
	private void readDir(File file, IProgressMonitor monitor) {
		String files[] = file.list();

		for (int i = 0; i < files.length; i++) {
			if (monitor.isCanceled()) {
				return;
			}

			String filePath = file.getAbsolutePath();

			try {
				filePath = file.getCanonicalPath();
			} catch (Exception e) {
				System.out.println("KeyWordIndexHelper:readDir\n" + //$NON-NLS-1$
						HelpMessages.EXCEPTION + e.toString());
			}
			filePath = filePath.replace('\\', '/');
			File tmp = new File(filePath + "/" + files[i]); //$NON-NLS-1$
			
			if (tmp.isDirectory()) {
				// If the file is a directory
				String tmpPath = tmp.getAbsolutePath();
				try {
					tmpPath = tmp.getCanonicalPath();
				} catch (Exception e) {
					System.out
							.println("KeyWordIndexHelper:readDir\n" + HelpMessages.EXCEPTION + e.toString()); //$NON-NLS-1$
				}
				// On pc the separator may be \ and not /
				tmpPath = tmpPath.replace('\\', '/');
				if (!defObj.isInStopdir(tmpPath)) {
					// If not in stopdir
					readDir(tmp, monitor);
					if (monitor.isCanceled()) {
						return;
					}

				} else {
					try {

						System.out.println(HelpMessages.DIRECTORY_NOT_PARSED
								+ tmp.getCanonicalPath());
					} catch (Exception e) {
						System.out
								.println("KeyWordIndexHelper:readDir\n" + HelpMessages.EXCEPTION + e.toString()); //$NON-NLS-1$
					}
				}

			}
			// Parse all the files which have one of the extensions we are
			// interested in.
			else if (files[i].endsWith(".htm") || files[i].endsWith(".html") //$NON-NLS-1$ //$NON-NLS-2$
					|| files[i].endsWith(".shtml") || files[i].endsWith(".shtm")) //$NON-NLS-1$ //$NON-NLS-2$
			{
				loadFile(file.getAbsolutePath() + "/" + files[i]); //$NON-NLS-1$
			}

		}
	}

	// ------------------------------------------------------------------------------------------------------
	// Parse each of the found files and extract the keywords from them.
	private void loadFile(String fileName) {
		String htmlFile = MiscStatic.loadFile(fileName, defObj
				.getCharacterSet());
		if (htmlFile != null && htmlFile.length() > 0) 
		{
			List keyWord = KeyWordStatic.getKeyWords(htmlFile);
			if (keyWord != null && keyWord.size() > 0 ) {
				IO.printDebug("keyword " + keyWord); //$NON-NLS-1$
				String title = MiscStatic.getTitle(htmlFile);

				// Adds a file with keywords to the holder object
				holder.add(new KeyWordFile(title, defObj.getRelativePath()
						+ "" + fileName.substring(defObj.getWwwRootLength()), //$NON-NLS-1$
						keyWord));
				
				//System.out.println("keywords=" + keyWord.size()  + " file: " + fileName);
			}
			
		}
		

	}

}