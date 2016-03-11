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
package org.eclipse.epf.publishing.services;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.configuration.GuidanceGroupingItemProvider;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.TermDefinition;


/**
 * Build the glossary file for the published configuration.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class IndexBuilder {

	private AdapterFactory adapterFactory;

	private static final Class ITreeItemContentProviderClass = ITreeItemContentProvider.class;

	// generated glossary html file relative to the publishing dir
	private static final String INDEX_HTML_FILE = "index/contents.htm"; //$NON-NLS-1$

	private static final String INDEX_NAVIG_HTML_FILE = "index/navig.htm"; //$NON-NLS-1$

	// xsl template
	private static final String INDEX_XSL_FILE = "xsl/index_contents.xsl"; //$NON-NLS-1$

	private static final String INDEX_NAVIG_XSL_FILE = "xsl/index_navig.xsl"; //$NON-NLS-1$

	// list to hold all glossary items.
	private IndexList indexItems = new IndexList();

	/**
	 * default constructor
	 *
	 */
	public IndexBuilder() {

	}

	/**
	 * build the index for the published site.
	 * 
	 * @param config MethodConfiguration
	 * @param pubDir String
	 * @param title String the title for the glossary index page
	 * @param monitor IProgressMonitor
	 */
	public void execute(MethodConfiguration config, String pubDir,
			String title, IProgressMonitor monitor) {
		indexItems.clear();
		IFilter configFilter = new ConfigurationFilter(config);
		adapterFactory = TngAdapterFactory.INSTANCE
				.getConfigurationView_AdapterFactory(configFilter);
		// iterate thru configuration to get all glossary items
		iterate(config);

		// create the glossary file
		createIndex(pubDir, title);

	}

	/**
	 * Iterate thru the configuration tree and find all term definition elements
	 * in the configuration
	 * 
	 * @param obj
	 * @param parent
	 */
	private void iterate(Object obj) {
		// Get the adapter from the factory.
		ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(obj, ITreeItemContentProviderClass);

		// Either delegate the call or return nothing.
		if (treeItemContentProvider != null) {
			Collection items = treeItemContentProvider.getChildren(obj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				// create bookmark
				Object itorObj = LibraryUtil.unwrap(it.next());
				if (itorObj instanceof GuidanceGroupingItemProvider) {
					iterate(itorObj);
				} else if (itorObj instanceof GuidanceItemProvider) {
					iterate(itorObj);
				} else if (itorObj instanceof TermDefinition) {
					// index the term definition objects
					TermDefinition term = (TermDefinition) itorObj;
					String name = term.getName();
					String content = "definition in glossary"; //$NON-NLS-1$
					String url = "../glossary/glossary.htm#" + term.getName(); //$NON-NLS-1$
					indexItems.add(name, content, url);
				}

			}
		}
	}

	private void createIndex(String pubDir, String title) {
		try {
			URL url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					INDEX_XSL_FILE);
			if (url == null) {
				System.out
						.println("Unable to get index xsl template " + INDEX_XSL_FILE); //$NON-NLS-1$
				return;
			}

			String xsl_uri = Platform.resolve(url).getPath();
			StringBuffer xml = indexItems.getXml();
			// System.out.println(xml);

			File f = new File(pubDir, INDEX_HTML_FILE);
			File parent = f.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}

			// geenrate the html
			Properties xslParams = new Properties();
			xslParams.setProperty("title", title); //$NON-NLS-1$			
			StringWriter sw = new StringWriter();
			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, sw);
			sw.flush();
			String content = sw.getBuffer().toString();

			// fix the element links
			FileWriter output = new FileWriter(f);
			output.write(content);
			output.flush();
			output.close();

			// also save the glossary navigation header html file
			url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					INDEX_NAVIG_XSL_FILE);
			if (url == null) {
				System.out
						.println("Unable to get glossary xsl template " + INDEX_NAVIG_XSL_FILE); //$NON-NLS-1$
				return;
			}

			xsl_uri = Platform.resolve(url).getPath();
			f = new File(pubDir, INDEX_NAVIG_HTML_FILE);
			output = new FileWriter(f);
			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, output);
			output.flush();
			output.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
