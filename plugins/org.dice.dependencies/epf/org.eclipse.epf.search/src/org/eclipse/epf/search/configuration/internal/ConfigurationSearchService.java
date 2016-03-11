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
package org.eclipse.epf.search.configuration.internal;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.lucene.document.DateField;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.IHTMLParser;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.search.GenerateSearchIndexException;
import org.eclipse.epf.search.SearchConfigurationException;
import org.eclipse.epf.search.SearchResources;
import org.eclipse.epf.search.SearchServiceException;
import org.eclipse.epf.search.analysis.TextAnalyzer;
import org.eclipse.epf.search.configuration.ConfigurationHitEntry;
import org.eclipse.epf.search.configuration.ConfigurationSearchQuery;

/**
 * The default implementation for searching a published configuration.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationSearchService {

	/**
	 * Document fields.
	 */
	public static final String BRIEF_DESCRIPTION_FIELD = "briefDescription"; //$NON-NLS-1$

	public static final String CONTENT_FIELD = "content"; //$NON-NLS-1$

	public static final String ID_FIELD = "id"; //$NON-NLS-1$

	public static final String MODIFIED_FIELD = "modified"; //$NON-NLS-1$

	public static final String NAME_FIELD = "name"; //$NON-NLS-1$

	public static final String ROLE_FIELD = "role"; //$NON-NLS-1$

	public static final String SUMMARY_FIELD = "summary"; //$NON-NLS-1$

	public static final String TYPE_FIELD = "type"; //$NON-NLS-1$

	public static final String URL_FIELD = "url"; //$NON-NLS-1$

	// The HTML parser.
	private IHTMLParser parser;

	// The directory containing the documents to be indexed.
	private String docDir = null;

	// The directory where the index files will be generated.
	private String indexDir = null;

	/**
	 * Creates a new instance.
	 * 
	 * @param docDir
	 *            the absolute path to a published configuration
	 */
	public ConfigurationSearchService(String docDir) {
		String userHome = System.getProperty("user.home"); //$NON-NLS-1$
		int hashCode = Math.abs(docDir.hashCode());
		String indexPath = userHome + File.separator
				+ "rup" + File.separator + hashCode + File.separator + "index"; //$NON-NLS-1$ //$NON-NLS-2$
		setDirs(docDir, indexPath);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param docDir
	 *            the absolute path to a published configuration
	 * @param indexDir
	 *            the absolute path to the indexes
	 */
	public ConfigurationSearchService(String docDir, String indexDir) {
		setDirs(docDir, indexDir);
	}

	/**
	 * Sets up the configuration and index directory.
	 * 
	 * @param docDir
	 *            the absolute path to a published configuration
	 * @param indexDir
	 *            the absolute path to the indexes
	 */
	public void setDirs(String docDir, String indexDir) {
		this.docDir = docDir;
		this.indexDir = indexDir;
	}

	/**
	 * Performs a search based on the given search query.
	 * 
	 * @param query
	 *            the search query string
	 * @return an array of <code>ConfigurationHitEntry</code> objects
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public ConfigurationHitEntry[] search(ConfigurationSearchQuery query)
			throws SearchServiceException {
		return search(query.getQueryString());
	}

	/**
	 * Performs a search on the given query string.
	 * 
	 * @param qstr
	 *            a Lucene compatible query string
	 * @return an array of <code>ConfigurationHitEntry</code> objects
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public ConfigurationHitEntry[] search(String qstr)
			throws SearchServiceException {
		Searcher searcher = null;
		try {
			searcher = new IndexSearcher(FSDirectory.open(new File(indexDir)));
			Query query = new QueryParser(Version.LUCENE_CURRENT, CONTENT_FIELD, new TextAnalyzer()).parse(qstr);
//			Query query = QueryParser.parse(qstr, CONTENT_FIELD,
//					new TextAnalyzer());
			
			TopDocs topDocs = searcher.search(query, 1000);
			ConfigurationHitEntry[] hits = new ConfigurationHitEntry[topDocs.totalHits];

//			Hits lhits = searcher.search(query);
//			ConfigurationHitEntry[] hits = new ConfigurationHitEntry[lhits
//					.length()];

			for (int i = 0; i < hits.length; i++) {
//				Document doc = lhits.doc(i);
				Document doc = searcher.doc(i);
				hits[i] = new ConfigurationHitEntry();
				hits[i].setName(doc.get(NAME_FIELD));
				hits[i].setUrl(doc.get(URL_FIELD));
				hits[i].setBriefDesc(doc.get(BRIEF_DESCRIPTION_FIELD));
				hits[i].setId(doc.get(ID_FIELD));
				hits[i].setType(doc.get(TYPE_FIELD));
			}
			return hits;
		} catch (Exception e) {
			throw new SearchConfigurationException(e);
		} finally {
			if (searcher != null) {
				try {
					searcher.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * Returns true of the index database already exists.
	 * 
	 * @return <code>true</code> if the index database already exists
	 */
	public boolean indexExists() {
		File[] files = new File(indexDir).listFiles();
		return (files != null && files.length != 0);
	}

	/**
	 * Creates the search index.
	 * 
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public void index() throws SearchServiceException {
		index(null, false);
	}

	/**
	 * Creates the search index.
	 * 
	 * @param reindex
	 *            if <code>true</code>, performs a reindex
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public void index(boolean reindex) throws SearchServiceException {
		index(null, reindex);
	}

	/**
	 * Creates the index database.
	 * 
	 * @param pm
	 *            a progress monitor
	 * @param reindex
	 *            if <code>true</code>, performs a reindex
	 * @throws SearchServiceException
	 *             if an error occurs while executing the operation
	 */
	public void index(IProgressMonitor pm, boolean reindex)
			throws SearchServiceException {
		synchronized (ConfigurationSearchService.class) {
			if (indexDir == null || docDir == null) {
				throw new IllegalStateException("Invalid indexDir or docDir"); //$NON-NLS-1$
			}

			if (!reindex && indexExists()) {
				return;
			}

			if (pm != null) {
				pm.beginTask(SearchResources.indexConfigFilesTask_name,
						getTotalDocsToIndex(docDir)); 
			}

			try {
				deleteAllIndexDirs();
				new File(indexDir).mkdirs();
				IndexWriter writer = new IndexWriter(FSDirectory.open(new File(indexDir)), new TextAnalyzer(), true, new MaxFieldLength(1000000));

//				IndexWriter writer = new IndexWriter(new FSdire indexDir,
//						new TextAnalyzer(), true);
//				writer.maxFieldLength = 1000000;
//				parser = new HTMLParser();
				parser = (IHTMLParser) ExtensionHelper.createExtensionForJTidy(
						CommonPlugin.getDefault().getId(), "htmlParser");  //$NON-NLS-1$
				indexDocs(new File(docDir), writer, pm);
				writer.optimize();
				writer.close();
			} catch (Exception e) {
				throw new GenerateSearchIndexException(e);
			} finally {
				if (pm != null) {
					pm.done();
				}
			}
		}
	}

	/**
	 * Deletes all existing index databases.
	 */
	public void deleteAllIndexDirs() {
		File parent = new File(indexDir).getParentFile();
		File[] files = parent.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().startsWith("index")) { //$NON-NLS-1$
					File[] file = files[i].listFiles();
					for (int j = 0; j < file.length; j++) {
						file[j].delete();
					}
					files[i].delete();
				}
			}
		}
	}

	/**
	 * Returns the total number of files that will be added to the search index.
	 * 
	 * @param dir
	 *            the directory name
	 * @return the number of files that will be added to the search index,
	 *         including all sub-directories
	 */
	public static int getTotalDocsToIndex(String dir) {
		int total = 0;
		File dirFile = new File(dir);
		File files[] = dirFile.listFiles();
		if (files == null) {
			return total;
		}
		for (int i = 0; i < files.length; i++) {
			if (isXMIDoc(files[i])) {
				total++;
			} else if (files[i].isDirectory()) {
				total += getTotalDocsToIndex(files[i].getAbsolutePath());
			}
		}
		return total;
	}

	/**
	 * Returns true if the file needs to be indexed.
	 * 
	 * @param file
	 *            the file to be verified
	 * @return <code>true</code> if the file needs to be indexed
	 */
	private static boolean isXMIDoc(File file) {
		return file.getPath().endsWith(".xmi"); //$NON-NLS-1$
	}

	/**
	 * Returns true if the file needs to be indexed.
	 * 
	 * @param file
	 *            the file to be verified
	 * @return <code>true</code> if the file needs to be indexed
	 */
	private static boolean isHtmlOrTextDoc(File file) {
		String path = file.getPath();
		return path.endsWith(".html") || path.endsWith(".htm") || path.endsWith(".txt"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * A recursive function to index files.
	 * 
	 * @param file
	 *            a directory or file to be indexed
	 * @param writer
	 *            the index writer
	 * @param pm
	 *            a progress monitor
	 * @throws Exception
	 */
	private void indexDocs(File file, IndexWriter writer, IProgressMonitor pm)
			throws Exception {
		if (file.isDirectory()) {
			if (file.getName().equalsIgnoreCase("applet")) { //$NON-NLS-1$
				return;
			}
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				indexDocs(new File(file, files[i]), writer, pm);
			}
		} else if (isHtmlOrTextDoc(file)) {
			if (pm != null) {
				pm.subTask(file.getName());
				pm.worked(1);
			}
			if (shouldBeExcluded(file)) {
				return;
			}
			Document doc = getHTMLDocument(file);
			writer.addDocument(doc);
		}
	}

	/**
	 * Returns <ocde>true</code> if the file should be excluded from indexing.
	 * 
	 * @param file
	 *            a file
	 * @return <code>true</code> if the file should be excluded from indexing
	 */
	public boolean shouldBeExcluded(File file) {
		String p = file.getParentFile().getAbsolutePath();
		return docDir.startsWith(p);
	}

	/**
	 * Gets the document object of a HTML file.
	 * 
	 * @param file
	 *            the HTML file to be indexed
	 * @return a document object
	 * @throws IOException
	 *             if an I/O error occurs while parsing the HTML file
	 * @throws InterruptedException
	 *             if the operation is interrupted by a user
	 */
	public Document getHTMLDocument(File file) throws IOException,
			InterruptedException {
		Document doc = new Document();
		doc.add(new Field(URL_FIELD, file.getPath().replace(
				File.pathSeparatorChar, '/'), Field.Store.YES, Field.Index.NO));
		doc.add(new Field(MODIFIED_FIELD, DateField.timeToString(file
				.lastModified()), Field.Store.YES, Field.Index.ANALYZED));
//		doc.add(Field.UnIndexed(URL_FIELD, file.getPath().replace(
//				File.pathSeparatorChar, '/')));
//		doc.add(Field.Keyword(MODIFIED_FIELD, DateField.timeToString(file
//				.lastModified())));

		try {
			parser.parse(file);
		} catch (Exception e) {
		}

		doc.add(new Field(CONTENT_FIELD, parser.getText(), Field.Store.YES, Field.Index.ANALYZED));
		doc.add(new Field(SUMMARY_FIELD, parser.getSummary(), Field.Store.YES, Field.Index.NO));
//		doc.add(Field.Text(CONTENT_FIELD, parser.getText()));
//		doc.add(Field.UnIndexed(SUMMARY_FIELD, parser.getSummary()));0

		Properties metaTags = parser.getMetaTags();
		for (Enumeration e = metaTags.propertyNames(); e.hasMoreElements();) {
			String tagName = (String) e.nextElement();
			doc.add(new Field(tagName, metaTags.getProperty(tagName), Field.Store.YES, Field.Index.ANALYZED));
//			doc.add(Field.Text(tagName, metaTags.getProperty(tagName)));
		}

		if (doc.getField(ROLE_FIELD) == null) {
			doc.add(new Field(ROLE_FIELD, "NORUPROLE", Field.Store.YES, Field.Index.ANALYZED));
//			doc.add(Field.Text(ROLE_FIELD, "NORUPROLE")); //$NON-NLS-1$
		}

		return doc;
	}

}