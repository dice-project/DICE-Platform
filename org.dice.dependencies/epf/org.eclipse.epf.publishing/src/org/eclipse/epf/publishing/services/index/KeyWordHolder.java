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
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Vector;

import com.ibm.icu.util.StringTokenizer;

class KeyWordHolder {
	private static final String lang = Locale.getDefault().getLanguage();

	private Vector keyWordFiles = null;

	private Vector keyWords = null;

	KeyWordHolder() {
		keyWordFiles = new Vector();
	}

	void add(KeyWordFile key) {
		keyWordFiles.addElement(key);
	}

	void divide() {
		if (keyWordFiles != null) {
			for (int i = 0; i < keyWordFiles.size(); i++) {
				String nextKeyWord = ""; //$NON-NLS-1$
				KeyWordFile tmp = (KeyWordFile) keyWordFiles.elementAt(i);
				while (nextKeyWord != null) {
					nextKeyWord = null;
					KeyWordDef def = tmp.getNextKeyWord();
					if (def != null) {
						nextKeyWord = def.toString();
					}

					if (nextKeyWord != null) {

						int noOfDoc = 1;
						Document tmpD = tmp.getDocument(nextKeyWord, noOfDoc);

						while (tmpD != null) {
							int index = MiscStatic
									.getIndex(
											nextKeyWord,
											KeyWordIndexHelper.defObj.levelSeparatorReplace,
											0);
							if (index == -1) {
								index = nextKeyWord.length();
							}

							KeyWord tmpK = createKeyWord(def);
							if (tmpK != null && tmpD != null) {
								tmpK.addKeyWord(def, nextKeyWord, tmpD);
							}

							noOfDoc++;
							tmpD = tmp.getDocument(nextKeyWord, noOfDoc);

						}
					}
				}
			}
		}
	}

	private KeyWord createKeyWord(KeyWordDef def) {

		if (keyWords == null) {
			keyWords = new Vector();
			KeyWord newKeyWord = new KeyWord(def);
			keyWords.addElement(newKeyWord);
			return newKeyWord;
		} else {
			int index = -1;
			int i = 0;
			KeyWord newKeyWord2 = new KeyWord(def);
			String str2 = newKeyWord2.getKeyWord();

			while (index == -1 && i < keyWords.size()) {
				KeyWord tmpK = (KeyWord) keyWords.elementAt(i);
				String str = tmpK.getKeyWord();
				int cmp = str2.toUpperCase().compareTo(str.toUpperCase());
				if (cmp < 0) {
					index = i;
				} else if (cmp == 0) {
					int cmp2 = str2.compareTo(str);
					if (cmp2 != 0) {
						
						// this is wrong, will put the entry to the previous item
//						if (cmp2 > 0) {
//							index = i;
//						} else {
//							index = i - 1;
//						}
						if (cmp2 < 0) {
							index = i;
						}
					} else {
						return tmpK;
					}
				}
				i++;
			}

			if (index == -1) {
				index = keyWords.size();
			}
			keyWords.insertElementAt(newKeyWord2, index);
			
			return newKeyWord2;

		}
	}

	void readSpecKeyWords(StringTokenizer parser) {
		while (parser.hasMoreTokens()) {
			String specKeyWordString = parser.nextToken().trim();
			String relatedKeyWord = parser.nextToken().trim();
			KeyWordDef specKeyDef = new KeyWordDef(specKeyWordString);
			KeyWordDef relatedKeyDef = new KeyWordDef(relatedKeyWord);

			specKeyWordString = specKeyDef.toString();
			relatedKeyWord = relatedKeyWord.toString();

			if (specKeyWordString != null && relatedKeyWord != null
					&& relatedKeyWordExists(specKeyWordString, relatedKeyWord)) {
				boolean found = false;
				int i = 0;
				String firstKeyWord = specKeyWordString;
				int pos = specKeyWordString.indexOf(
						KeyWordIndexHelper.defObj.levelSeparatorReplace, 0);
				if (pos != -1) {
					firstKeyWord = specKeyWordString.substring(0, pos);
				}
				IO.printDebug("firstKeyWord " + firstKeyWord); //$NON-NLS-1$
				while (!found && i < keyWords.size()) {
					IO.printDebug("holder in first while " + i); //$NON-NLS-1$
					KeyWord tmpK = (KeyWord) keyWords.elementAt(i);
					if (firstKeyWord.equals(tmpK.getKeyWord())) {

						found = true;
						if (pos != -1) {
							tmpK.insertSpecKeyWord(specKeyWordString
									.substring(pos + 1), relatedKeyWord);
						} else {
							tmpK.setSpecKeyWord(relatedKeyWord, false);
						}
					}
					i++;
				}
				if (!found) {
					IO.printDebug("!found"); //$NON-NLS-1$
					int index = -1;
					i = 0;
					KeyWord newKeyWord2 = new KeyWord(specKeyDef);
					if (pos == -1) {
						newKeyWord2.setSpecKeyWord(relatedKeyWord, true);
					} else {
						newKeyWord2.insertSpecKeyWord(specKeyWordString
								.substring(pos + 1), relatedKeyWord);
					}
					while (index == -1 && i < keyWords.size()) {
						IO.printDebug("holder in second while " + i); //$NON-NLS-1$
						KeyWord tmpK = (KeyWord) keyWords.elementAt(i);
						if (newKeyWord2.getKeyWord().toUpperCase().compareTo(
								tmpK.getKeyWord().toUpperCase()) < 0) {
							index = i;
						} else if (newKeyWord2.getKeyWord().toUpperCase()
								.compareTo(tmpK.getKeyWord().toUpperCase()) == 0) {

							if (newKeyWord2.getKeyWord().compareTo(
									tmpK.getKeyWord()) != 0) {
								if (newKeyWord2.getKeyWord().compareTo(
										tmpK.getKeyWord()) > 0) {
									index = i;
								} else {
									index = i - 1;
								}
							}
						}
						i++;
					}

					if (index != -1) {
						keyWords.insertElementAt(newKeyWord2, index);
					} else {
						keyWords.insertElementAt(newKeyWord2, keyWords.size());
					}
				}
			}
		}
	}

	private boolean relatedKeyWordExists(String specKeyWordString,
			String relatedKeyWord) {
		if (keyWords != null) {
			int i = 0;

			while (i < keyWords.size()) {
				KeyWord tmpK = (KeyWord) keyWords.elementAt(i);
				if (tmpK.getKeyWord().equals(relatedKeyWord)) {
					return true;
				}
				i++;
			}
		}
		System.err.println(relatedKeyWord
				+ HelpMessages.RELATED_KEYWORD_NOT_FOUND + specKeyWordString);
		return false;

	}

	void print() {

		if (KeyWordIndexHelper.defObj.getIndexResultFile() != null) {
			printFrameset();
			printIndex();
		}
		printKeyWords();
	}

	// Prints the htmlfile containing the frameset
	private void printFrameset() {
		try {
			File file = new File(KeyWordIndexHelper.defObj.getWwwRoot()
					+ KeyWordIndexHelper.defObj.getMainResultFile());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			OutputStreamWriter outP;
			if (KeyWordIndexHelper.defObj.getCharacterSet() != null) {
				outP = new OutputStreamWriter(new FileOutputStream(file),
						KeyWordIndexHelper.defObj.getCharacterSet());
			} else {
				outP = new OutputStreamWriter(new FileOutputStream(file));
			}
			System.out.println(HelpMessages.WRITE_FILE
					+ KeyWordIndexHelper.defObj.getWwwRoot()
					+ "" + KeyWordIndexHelper.defObj.getMainResultFile()); //$NON-NLS-1$
			//MiscStatic.print(outP, "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"); //$NON-NLS-1$
			MiscStatic.print(outP, "<html lang=\"" + lang + "\" xml:lang=\"" + lang + "\">\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			MiscStatic.print(outP, "<head>\n"); //$NON-NLS-1$
			MiscStatic
					.print(outP,
							"<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"); //$NON-NLS-1$          
			MiscStatic
					.print(
							outP,
							"<title>" + KeyWordIndexHelper.defObj.getIndexTitle() + "</title>\n"); //$NON-NLS-1$ //$NON-NLS-2$
			MiscStatic.print(outP, "</head>\n"); //$NON-NLS-1$
			File keyWordsFile = new File(KeyWordIndexHelper.defObj.getWwwRoot()
					+ KeyWordIndexHelper.defObj.getKeywordResultFile());
			String keyWordPath = KeyWordIndexHelper.defObj.getRelativePath()
					+ KeyWordIndexHelper.defObj.getKeywordResultFile();

			File indxeFile = new File(KeyWordIndexHelper.defObj.getWwwRoot()
					+ KeyWordIndexHelper.defObj.getIndexResultFile());
			String indexPath = KeyWordIndexHelper.defObj.getRelativePath()
					+ KeyWordIndexHelper.defObj.getIndexResultFile();
			MiscStatic
					.print(
							outP,
							"<frameset rows=\"" + KeyWordIndexHelper.defObj.getIndexHeight() + ",*\">\n"); //$NON-NLS-1$ //$NON-NLS-2$

			MiscStatic
					.print(
							outP,
							"<frame name=\"" + KeyWordIndexHelper.defObj.getIndexTarget() //$NON-NLS-1$
									+ "\" title=\"Navigation bar\" src=\"" + indexPath //$NON-NLS-1$
									+ "\" marginheight=\"2\" marginwidth=\"2\" scrolling=\"auto\">\n"); //$NON-NLS-1$
			MiscStatic
					.print(
							outP,
							"<frame name=\"" + KeyWordIndexHelper.defObj.getKeyWordTarget() //$NON-NLS-1$
									+ "\" title=\"Contents\" src=\"" + keyWordPath //$NON-NLS-1$
									+ "\" marginheight=\"0\" marginwidth=\"2\" scrolling=\"auto\">\n"); //$NON-NLS-1$
			MiscStatic.print(outP, "</frameset>\n"); //$NON-NLS-1$
			MiscStatic.print(outP, "</html>"); //$NON-NLS-1$
			outP.close();
		} catch (Exception e) {
			System.err
					.println("KeyWordHolder:printFrameset\n" + HelpMessages.EXCEPTION + e.toString()); //$NON-NLS-1$
		}
	}

	// Prints the html file with the shortcuts to each anchor in the keywordfile
	private void printIndex() {
		try {

			File file = new File(KeyWordIndexHelper.defObj.getWwwRoot()
					+ KeyWordIndexHelper.defObj.getIndexResultFile());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			OutputStreamWriter outP;
			if (KeyWordIndexHelper.defObj.getCharacterSet() != null) {
				outP = new OutputStreamWriter(new FileOutputStream(file),
						KeyWordIndexHelper.defObj.getCharacterSet());
			} else {
				outP = new OutputStreamWriter(new FileOutputStream(file));
			}
			System.out.println(HelpMessages.WRITE_FILE
					+ KeyWordIndexHelper.defObj.getWwwRoot()
					+ "" + KeyWordIndexHelper.defObj.getIndexResultFile()); //$NON-NLS-1$

			//MiscStatic.print(outP, "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"); //$NON-NLS-1$
			MiscStatic.print(outP, "<html lang=\"" + lang + "\" xml:lang=\"" + lang + "\">\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			MiscStatic.print(outP, "<head>\n"); //$NON-NLS-1$
			MiscStatic
					.print(outP,
							"<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"); //$NON-NLS-1$          
			MiscStatic.print(outP, "</head><BODY>\n"); //$NON-NLS-1$

			String beginChar = ""; //$NON-NLS-1$
			if (keyWords != null) {
				File keyWordsFile = new File(KeyWordIndexHelper.defObj
						.getWwwRoot()
						+ KeyWordIndexHelper.defObj.getKeywordResultFile());
				String path = KeyWordIndexHelper.defObj.getRelativePath()
						+ KeyWordIndexHelper.defObj.getKeywordResultFile();

				for (int i = 0; i < keyWords.size(); i++) {
					KeyWord tmp = (KeyWord) keyWords.elementAt(i);
					String firstL = tmp.getKeyWord().substring(0, 1)
							.toUpperCase();

					if (firstL.compareTo(beginChar) != 0) {
						beginChar = firstL;
						MiscStatic.print(outP, "<A HREF=\"" + path + "#"); //$NON-NLS-1$ //$NON-NLS-2$
						MiscStatic.print(outP, beginChar);
						MiscStatic
								.print(
										outP,
										"\" TARGET=\"" + KeyWordIndexHelper.defObj.getKeyWordTarget() + "\">"); //$NON-NLS-1$ //$NON-NLS-2$
						MiscStatic.print(outP, beginChar);
						MiscStatic.print(outP, "</A>\n"); //$NON-NLS-1$
					}
				}
			}
			MiscStatic.print(outP, "</BODY></html>"); //$NON-NLS-1$
			outP.close();
		} catch (Exception e) {
			System.err
					.println("KeyWordHolder:printIndex\n" + HelpMessages.EXCEPTION + e.toString()); //$NON-NLS-1$
		}
	}

	// Prints the keywordfile
	private void printKeyWords() {

		try {
			OutputStreamWriter outP;

			File file = new File(KeyWordIndexHelper.defObj.getWwwRoot()
					+ KeyWordIndexHelper.defObj.getKeywordResultFile());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			if (KeyWordIndexHelper.defObj.getCharacterSet() != null) {
				outP = new OutputStreamWriter(new FileOutputStream(file),
						KeyWordIndexHelper.defObj.getCharacterSet());
			} else {
				outP = new OutputStreamWriter(new FileOutputStream(file));
			}
			System.out.println(HelpMessages.WRITE_FILE
					+ KeyWordIndexHelper.defObj.getWwwRoot() + "" + //$NON-NLS-1$
					KeyWordIndexHelper.defObj.getKeywordResultFile());
			String headerFile = MiscStatic.loadFile(KeyWordIndexHelper.defObj
					.getHeaderFile(), KeyWordIndexHelper.defObj
					.getCharacterSet());
			if (headerFile.equalsIgnoreCase(Def.None)) {
				System.err.println(HelpMessages.INCORRECT_HEADERFILE
						+ KeyWordIndexHelper.defObj.getHeaderFile());
			}
			MiscStatic.print(outP, headerFile);

			String beginChar = ""; //$NON-NLS-1$
			if (keyWords != null) {
				for (int i = 0; i < keyWords.size(); i++) {

					KeyWord tmp = (KeyWord) keyWords.elementAt(i);
					String firstL = tmp.getKeyWord().substring(0, 1)
							.toUpperCase();

					if (firstL.compareTo(beginChar) != 0) {
						beginChar = firstL;
						FontObject fs = KeyWordIndexHelper.defObj
								.getStyleSheet(Def.HeadLineStyle);
						if (fs == null) {
							fs = KeyWordIndexHelper.defObj
									.getStyleSheet(Def.DefaultStyle);
						}
						FontObject f = null;
						FontObject fr = fs;
						if (fs == null) {
							f = KeyWordIndexHelper.defObj
									.getFont(Def.HeadLineFont);
							if (f == null) {
								f = KeyWordIndexHelper.defObj
										.getFont(Def.DefaultFont);
							}
							fr = f;
						}
						KeyWordIndexHelper.defObj.printStart(outP, fr);
						if (fs == null) {
							MiscStatic.print(outP, "<BR>"); //$NON-NLS-1$
						}
						MiscStatic
								.print(
										outP,
										"<A ID=\"_INDEX_ITEM_" + beginChar + "\" NAME=\"" + beginChar + "\">" + beginChar + "</A>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						if (fs == null) {
							MiscStatic.print(outP, "<BR>"); //$NON-NLS-1$
						}
						KeyWordIndexHelper.defObj.printEnd(outP, fr);
					}
					tmp.print(outP, 0);
				}
			}
			String footerFile = MiscStatic.loadFile(KeyWordIndexHelper.defObj
					.getFooterFile(), KeyWordIndexHelper.defObj
					.getCharacterSet());
			if (footerFile.equalsIgnoreCase(Def.None)) {
				System.err.println(HelpMessages.INCORRECT_FOOTERFILE
						+ KeyWordIndexHelper.defObj.getFooterFile());
			}
			MiscStatic.print(outP, footerFile);
			outP.close();
		} catch (Exception e) {
			System.err
					.println("KeyWordHolder:printKeyWords\n" + HelpMessages.EXCEPTION + e.toString()); //$NON-NLS-1$
			e.printStackTrace();
		}
	}
}