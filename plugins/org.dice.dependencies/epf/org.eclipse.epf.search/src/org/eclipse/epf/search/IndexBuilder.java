//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.eclipse.epf.search.analysis.TextAnalyzer;
import org.eclipse.epf.search.utils.JarCreator;
import org.eclipse.epf.search.utils.LHTMLParser;
import org.eclipse.epf.search.utils.UNCUtil;

/**
 * This class is the main class that creates the Index from the file
 * associations in the process layout.
 */
public class IndexBuilder {

	static final String VERSION_FILE_NAME = "version.txt"; //$NON-NLS-1$
	static final String VERSION_DELIMITER = "*"; //$NON-NLS-1$

	/**
	 * Document fields.
	 */
	public static final String BRIEF_DESCRIPTION_FIELD = "briefDescription"; //$NON-NLS-1$
	public static final String CONTENT_FIELD = "contents"; //$NON-NLS-1$
	public static final String ID_FIELD = "id"; //$NON-NLS-1$
	public static final String MODIFIED_FIELD = "modified"; //$NON-NLS-1$
	public static final String NAME_FIELD = "name"; //$NON-NLS-1$
	public static final String ROLE_FIELD = "role"; //$NON-NLS-1$
	public static final String SUMMARY_FIELD = "summary"; //$NON-NLS-1$
	public static final String TYPE_FIELD = "type"; //$NON-NLS-1$
	public static final String URL_FIELD = "url"; //$NON-NLS-1$
	public static final String TITLE_FIELD = "title"; //$NON-NLS-1$
	public static final String UMA_ELEMENT_TYPE_FIELD = "uma.type"; //$NON-NLS-1$
	public static final String GENERAL_CONTENT = "general_content"; //$NON-NLS-1$

	// List of UMA elements that should be included in the search index.
	private static List NO_SEARCHEABLE_UMA_ELEMENTS = new ArrayList();
	static {
		NO_SEARCHEABLE_UMA_ELEMENTS.add("summary"); //$NON-NLS-1$
		NO_SEARCHEABLE_UMA_ELEMENTS.add("workproductdescriptor"); //$NON-NLS-1$
		NO_SEARCHEABLE_UMA_ELEMENTS.add("taskdescriptor"); //$NON-NLS-1$
		NO_SEARCHEABLE_UMA_ELEMENTS.add("roledescriptor"); //$NON-NLS-1$
	}

	// A list of top level directories that should be excluded from the search
	// index.
	public static List dirsToSkip = new ArrayList();
	public static String pDirectory = null;
	private StringBuffer indexFolder = null;
	private String productName = null;
	private List filesToSkip = new ArrayList();
	private File parentFolder = null;
	
	public IndexBuilder(String publishDir) {
		int appletIndex = -1;
		if (publishDir == null)
			return;

		appletIndex = publishDir.indexOf(File.separator + "applet"); //$NON-NLS-1$

		pDirectory = UNCUtil.convertFilename((appletIndex > -1) ? publishDir
				.substring(0, appletIndex + 1) : publishDir);
		String siteName = pDirectory.replace(File.separatorChar, '/');
		parentFolder = new File(pDirectory);
		int index = siteName.length();
		if (siteName.endsWith("/")) { //$NON-NLS-1$
			index = index - 1;
		}

		int index2 = siteName.lastIndexOf("/", index - 1); //$NON-NLS-1$

		productName = siteName.substring(index2 + 1, index);

		// create the index
		StringBuffer searchFolder = new StringBuffer(pDirectory);
		if (!searchFolder.toString().endsWith(File.separator)) {
			searchFolder.append(File.separator);
		}
		searchFolder.append("search"); //$NON-NLS-1$

		indexFolder = new StringBuffer(searchFolder.toString());
		indexFolder.append(File.separator).append("index"); //$NON-NLS-1$

		dirsToSkip.add(pDirectory + "applet"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "css"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "ext_help"); //$NON-NLS-1$		
		dirsToSkip.add(pDirectory + "icons"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "images"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "index"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "logs"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "manuals"); //$NON-NLS-1$		
		dirsToSkip.add(pDirectory + "noapplet"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "pages_not_installed"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "process"); //$NON-NLS-1$		
		dirsToSkip.add(pDirectory + "scripts"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "stylesheets"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "xml"); //$NON-NLS-1$
		dirsToSkip.add(pDirectory + "search"); //$NON-NLS-1$

		filesToSkip.add("_desc.htm");  //$NON-NLS-1$
		filesToSkip.add("_wbs.htm"); //$NON-NLS-1$
		filesToSkip.add("_tbs.htm"); //$NON-NLS-1$
		filesToSkip.add("_wpbs.htm"); //$NON-NLS-1$
	}
	
	public boolean createIndex(boolean jarIt) throws SearchServiceException {
		synchronized (IndexBuilder.class) {
			
			if (indexFolder == null || pDirectory == null) {
				throw new IllegalStateException(
						"Invalid indexFolder or pDirectory"); //$NON-NLS-1$
			}

			
			boolean jako = false;
			Locale locale = Locale.getDefault();
			String lang = locale.getLanguage();
			if (lang.equals(Locale.JAPANESE.getLanguage()) ||
				lang.equals(Locale.KOREA.getLanguage())) {
				jako = true;
			}
			Analyzer analyzer = jako ? new CJKAnalyzer(Version.LUCENE_CURRENT) : new TextAnalyzer();
			
			try {
				// RAMDirectory ramDir = new RAMDirectory();
				IndexWriter fsWriter = new IndexWriter(FSDirectory
						.open(new File(indexFolder.toString())),
						analyzer, true, MaxFieldLength.UNLIMITED);

				// IndexWriter ramWriter = new IndexWriter(ramDir,
				// new TextAnalyzer(), true);

				if ((fsWriter != null)) {
					// fsWriter.mergeFactor = 1000;
					// fsWriter.maxMergeDocs = 10000;
					fsWriter.setMaxFieldLength(1000000);

					indexDocs(new File(pDirectory), fsWriter);

					// fsWriter.addIndexes(new Directory[] { ramDir });
					fsWriter.optimize();
					// ramWriter.close();
					fsWriter.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// create the version file.
			Date today = new Date();
			long milliseconds = today.getTime();

			if (!jarIt) {
				try {
					FileWriter fw = new FileWriter(indexFolder + File.separator
							+ VERSION_FILE_NAME);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(productName + VERSION_DELIMITER + milliseconds
							+ "\n"); //$NON-NLS-1$
					if (analyzer instanceof CJKAnalyzer) {
						bw.write("CJKAnalyzer" + "\n"); //$NON-NLS-1$	//$NON-NLS-2$
					}
					bw.close();
					fw.close();
				} catch (IOException ioe) {
					throw new SearchServiceException(
							SearchResources.createSearchIndexError);
				}

				return true;
			}

			// jar up the created index.
			JarCreator.jarFolder(indexFolder.toString());

			System.out.println("index Jarred successfully"); //$NON-NLS-1$

			try {
				// delete the files now that they've been jarred.
				File indexDir = new File(indexFolder.toString());
				File[] files = indexDir.listFiles();
				for (int i = 0; i < files.length; i++) {
					File tempFile = files[i];
					if (!tempFile.getName().equals(JarCreator.INDEX_JAR)) {
						tempFile.delete();
					}
				}

				// String rupName = publishDir.substring(index);
				File newIndexJar = new File(indexFolder + File.separator
						+ JarCreator.INDEX_JAR);
				if (newIndexJar.exists()) {
					String fileSize = "" + newIndexJar.length(); //$NON-NLS-1$
					FileWriter fw = new FileWriter(indexFolder + File.separator
							+ VERSION_FILE_NAME);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(productName + VERSION_DELIMITER + milliseconds
							+ VERSION_DELIMITER + fileSize + "\n"); //$NON-NLS-1$
					if (analyzer instanceof CJKAnalyzer) {
						bw.write("CJKAnalyzer" + "\n"); //$NON-NLS-1$	//$NON-NLS-2$
					}
					bw.close();
					fw.close();
				} else {
					throw new SearchServiceException(
							SearchResources.createSearchIndexError);
				}
			} catch (IOException ioe) {
				throw new SearchServiceException(
						SearchResources.createSearchIndexError);
			}

			return true;
		}
	}

	/**
	 * Index the actual documents specified by the files and recursively get all
	 * file in the specified folder file
	 * 
	 */
	private void indexDocs(File file, IndexWriter writer) throws Exception {
		if (dirsToSkip.contains(file.getAbsolutePath())) {
			return;
		}
		if (file.isFile()) {
			for (Iterator iter = filesToSkip.iterator(); iter.hasNext();) {
				String fileToSkip = (String) iter.next();
				if (file.getName().indexOf(fileToSkip) > -1) {
					return;
				}
			}
		}

		if (file.isDirectory()) {
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				indexDocs(new File(file, files[i]), writer);
			}
		} else if (isHtmlDoc(file)) {
			if (shouldBeExcluded(file)) {
				return;
			}
			try {
				
				Document doc = getHTMLDocument(file);		
				
				if (doc != null) {
					writer.addDocument(doc);				
				}
				
			} catch (Exception e1) {
				System.out.println(file.getName());
				System.out.println("indexDocs"); //$NON-NLS-1$
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Checks whether the given file should be excluded from the search index.
	 * 
	 * @param file
	 *            The file to be verified.
	 * @return <code>true</code> if the given file should be excluded from the
	 *         search index.
	 */
	private boolean shouldBeExcluded(File file) {
		String path = file.getParentFile().getAbsolutePath();
		if (pDirectory.startsWith(path)) {
			return true;
		}

		return false;
	}

	private static boolean isHtmlDoc(File file) {
		String path = file.getPath();
		return path.endsWith(".html") || path.endsWith(".htm"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private boolean isNoSearchableDocument(Properties metaTags) {
		String value = metaTags.getProperty(UMA_ELEMENT_TYPE_FIELD);
		
		// value == null is treated as general document
		return (value != null) && NO_SEARCHEABLE_UMA_ELEMENTS.contains(value);
	}
	
	char[] cbuf = new char[1024];
	int skipCount = 0;
	
	private Document getHTMLDocument(File file) {
		Document luceneDocument = null;
		InputStreamReader input = null;
		Reader reader = null;
		BufferedReader bufferedReader = null; 
		try {
			
			input = new InputStreamReader(new FileInputStream(file), "UTF-8"); //$NON-NLS-1$

			LHTMLParser parser = new LHTMLParser(input);

			reader = parser.getReader();
			if ( reader == null ) {
				return null;
			}
			
			StringBuffer htmlContent = new StringBuffer("");
			String line = "";
			bufferedReader = new BufferedReader(reader);
			while((line = bufferedReader.readLine()) != null)
			{
				htmlContent.append(line + "\n");
			}

			Properties metaTags = parser.getMetaTags();
			if ( isNoSearchableDocument(metaTags) ) {
				
				// the LHTMLParser thread will not end if the reader is not processed
				// causing major resource leak
//				while ( reader.read(cbuf) > 0 ) {
//					;
//				}			
				//System.out.println( ++skipCount + " file skipped: " + file.getAbsolutePath());
				parser = null;
				return null;
			}
			
			luceneDocument = new Document();
			
			String url = productName
					+ file.getPath().substring(parentFolder.getPath().length())
							.replace(File.separatorChar, '/'); //$NON-NLS-1$
			luceneDocument.add(new Field(URL_FIELD, url, Store.NO, Index.NO));
			// luceneDocument.add(Field.UnIndexed(URL_FIELD, url));
			
//			luceneDocument.add(Field.Text(CONTENT_FIELD, reader));
			luceneDocument.add(new Field(CONTENT_FIELD, htmlContent.toString(), Store.NO, Index.NO));
			// luceneDocument.add(Field.UnStored(CONTENT_FIELD, htmlContent.toString()));

			String title = parser.getTitle();
			if (title != null && title.length() > 0) {
				// Workaround a Linux specific issue.
				title = title.replaceAll("\\xa0", " "); //$NON-NLS-1$ //$NON-NLS-2$
				//luceneDocument.add(Field.Keyword(TITLE_FIELD, title));
				luceneDocument.add(new Field(TITLE_FIELD, title, Field.Store.YES, Field.Index.ANALYZED));
				// luceneDocument.add(new Field(TITLE_FIELD, title, Field.Store.YES, Field.Index.TOKENIZED));//Tokenized the title to be searchable
			} else {
				return null;
			}

			String summary = parser.getSummary();
			if (summary.startsWith(title) && summary.length() > title.length()) {
				luceneDocument.add(new Field(SUMMARY_FIELD, summary
						.substring(title.length() + 1), Field.Store.YES, Field.Index.ANALYZED));
				// luceneDocument.add(Field.Keyword(SUMMARY_FIELD, summary
				//		.substring(title.length() + 1)));
			} else
				luceneDocument.add(new Field(SUMMARY_FIELD, parser.getSummary(), Field.Store.YES, Field.Index.ANALYZED));
				// luceneDocument.add(Field.Keyword(SUMMARY_FIELD, parser
				//		.getSummary()));

			for (Enumeration names = metaTags.propertyNames(); names
					.hasMoreElements();) {
				String tagName = (String) names.nextElement();
				if (tagName != null) {
					if (tagName.equals(ROLE_FIELD)) {
						String roleName = metaTags.getProperty(tagName);
						if (roleName != null) {
							luceneDocument.add(new Field(tagName, roleName, Field.Store.YES, Field.Index.ANALYZED));
							// luceneDocument.add(Field.Text(tagName, roleName));
						}
					} else {
						String tagValue = metaTags.getProperty(tagName);
						if (tagValue != null) {
							luceneDocument.add(new Field(tagName, tagValue, Field.Store.YES, Field.Index.ANALYZED));
							// luceneDocument.add(Field.Text(tagName, tagValue));
						}
					}
				}
			}

			if (luceneDocument.getField(ROLE_FIELD) == null) {
				// Default to "na" to support searching for files without
				// role meta tags.
				luceneDocument.add(new Field(ROLE_FIELD, "NORUPROLE", Field.Store.YES, Field.Index.ANALYZED));
				// luceneDocument.add(Field.Text(ROLE_FIELD, "NORUPROLE")); //$NON-NLS-1$
			}

			Field umaTypeField = luceneDocument
					.getField(UMA_ELEMENT_TYPE_FIELD);
			if (umaTypeField == null) {
				// Default to general content.
				luceneDocument.add(new Field(UMA_ELEMENT_TYPE_FIELD, GENERAL_CONTENT, Field.Store.YES, Field.Index.ANALYZED));
				// luceneDocument.add(Field.Text(UMA_ELEMENT_TYPE_FIELD,
				//		GENERAL_CONTENT));
			} 

			parser = null;
		} catch (Exception e) {
			luceneDocument = null;
			SearchPlugin.getDefault().getLogger().logError(e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
				}
			}
		}

		return luceneDocument;
	}

}