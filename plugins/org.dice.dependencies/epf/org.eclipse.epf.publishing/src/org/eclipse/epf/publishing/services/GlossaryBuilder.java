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
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.configuration.SupportingElementData;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.configuration.GuidanceGroupingItemProvider;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TermDefinition;

/**
 * Build the glossary file for the published configuration.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class GlossaryBuilder {

	public static final String GLOSSARY_BACKPATH = "./../"; //$NON-NLS-1$

	private AdapterFactory adapterFactory;

	private static final Class ITreeItemContentProviderClass = ITreeItemContentProvider.class;

	// generated glossary html file relative to the publishing dir
	private static final String GLOSSARY_HTML_FILE = "process/glossary.htm"; //$NON-NLS-1$

	private static final String GLOSSARY_INDEX_HTML_FILE = "process/glossary/index.htm"; //$NON-NLS-1$	

	private static final String GLOSSARY_NAVIG_HTML_FILE = "process/glossary/navig.htm"; //$NON-NLS-1$

	// xsl template
	private static final String GLOSSARY_XSL_FILE = "xsl/glossary.xsl"; //$NON-NLS-1$

	private static final String GLOSSARY_INDEX_XSL_FILE = "xsl/glossary_index.xsl"; //$NON-NLS-1$	

	private static final String GLOSSARY_NAVIG_XSL_FILE = "xsl/glossary_navig.xsl"; //$NON-NLS-1$

	// list to hold all glossary items.
	private GlossaryList glossaryItems;

	/**
	 * default constructor
	 *
	 */
	public GlossaryBuilder() {

	}

	/**
	 * build the glossary and generate the related files.
	 * 
	 * @param config MethodConfiguration
	 * @param pubDir String
	 * @param title String the title of the glossary page
	 * @param monitor IProgressMonitor
	 */
	public void execute(MethodConfiguration config, String pubDir,
			String title, IProgressMonitor monitor) {
		
		SupportingElementData sdata = LibraryService.getInstance()
				.getConfigurationManager(config).getSupportingElementData();
		
		boolean oldValue = false;
		if (sdata != null) {
			oldValue = sdata.isEnabled();
			sdata.setEnabled(false);
		}
		
		try {
			execute_(config, pubDir, title, monitor);
		} finally {
			if (sdata != null) {
				sdata.setEnabled(oldValue);
			}
		}
		
	}
	
	private void execute_(MethodConfiguration config, String pubDir,
			String title, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			return;
		}

		glossaryItems = new GlossaryList(pubDir);
		
		glossaryItems.clear();
		IFilter configFilter = new ConfigurationFilter(config);
		adapterFactory = TngAdapterFactory.INSTANCE
				.getConfigurationView_AdapterFactory(configFilter);
		// iterate thru configuration to get all glossary items
		iterate(config, monitor);

		// create the glossary file
		createGlossary(pubDir, title);

	}

	/**
	 * Iterate thru the configuration tree and find all term definition elements
	 * in the configuration
	 * 
	 * @param obj
	 * @param parent
	 */
	private void iterate(Object obj, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			return;
		}

		// Get the adapter from the factory.
		ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(obj, ITreeItemContentProviderClass);

		// Either delegate the call or return nothing.
		if (treeItemContentProvider != null) {
			Collection items = treeItemContentProvider.getChildren(obj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				if (monitor.isCanceled()) {
					return;
				}

				// create bookmark
				Object itorObj = LibraryUtil.unwrap(it.next());
				if (itorObj instanceof GuidanceGroupingItemProvider) {
					iterate(itorObj, monitor);
				} else if (itorObj instanceof GuidanceItemProvider) {
					iterate(itorObj, monitor);
				} else if (itorObj instanceof TermDefinition) {
					// create a glossary entry for the TermDefinition
					// System.out.println(itorObj);
					glossaryItems.add((TermDefinition) itorObj);
				}

			}
		}
	}

	private void createGlossary(String pubDir, String title) {
		try {
			URL url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					GLOSSARY_XSL_FILE);
			if (url == null) {
				System.out
						.println("Unable to get glossary xsl template " + GLOSSARY_XSL_FILE); //$NON-NLS-1$
				return;
			}

			String xsl_uri = Platform.resolve(url).getPath();
			StringBuffer xml = glossaryItems.getXml();

			File f = new File(pubDir, GLOSSARY_HTML_FILE);
			File parent = f.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}

			// geenrate the html
			Properties xslParams = PublishingPlugin.getDefault().getProperties(
					"/xsl/resources.properties"); //$NON-NLS-1$
			// Properties xslParams = new Properties();
			xslParams.setProperty("title", title); //$NON-NLS-1$			
			StringWriter sw = new StringWriter();
			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, sw);
			sw.flush();
			// String content = sw.getBuffer().toString();

			// need to decode the urls
			String content = ResourceHelper.decodeUrlsInContent(sw.getBuffer()
					.toString());
			FileUtil.writeUTF8File(f.getAbsolutePath(), fixContent(content));

			// // fix the element links
			// FileWriter output = new FileWriter(f);
			// output.write(fixContent(content));
			// output.flush();
			// output.close();
			sw.close();

			// also save the glossary navigation header html file
			url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					GLOSSARY_INDEX_XSL_FILE);
			if (url == null) {
				System.out
						.println("Unable to get glossary xsl template " + GLOSSARY_INDEX_XSL_FILE); //$NON-NLS-1$
				return;
			}

			xsl_uri = Platform.resolve(url).getPath();
			f = new File(pubDir, GLOSSARY_INDEX_HTML_FILE);
			sw = new StringWriter();
			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, sw);
			sw.flush();

			// make sure the file is created.
			if ( !f.exists() ) {
				File pf = f.getParentFile();
				if (!pf.exists()) {
					pf.mkdirs();
				}
				f.createNewFile();
			}
			
			FileUtil.writeUTF8File(f.getAbsolutePath(), sw.toString());

			sw.close();

			// also save the glossary navigation header html file
			url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					GLOSSARY_NAVIG_XSL_FILE);
			if (url == null) {
				System.out
						.println("Unable to get glossary xsl template " + GLOSSARY_NAVIG_XSL_FILE); //$NON-NLS-1$
				return;
			}

			xsl_uri = Platform.resolve(url).getPath();
			f = new File(pubDir, GLOSSARY_NAVIG_HTML_FILE);
			sw = new StringWriter();
			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, sw);
			sw.flush();

			FileUtil.writeUTF8File(f.getAbsolutePath(), sw.toString());

			sw.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String fixContent(String source) {
		StringBuffer sb = new StringBuffer();
		Matcher m = ResourceHelper.p_link_ref.matcher(source);

		while (m.find()) {
			String urltext = m.group(1);
			String linkedText = m.group(2);

			urltext = fixUrlText(urltext);

			// update the url text
			String text = "<a " + urltext + ">" + linkedText + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			m.appendReplacement(sb, ResourceHelper.regExpEscape(text));
		}

		m.appendTail(sb);
		return sb.toString();
	}

	private String fixUrlText(String urltext) {
		try {
			return fixUrlText_(urltext);
		} catch (Throwable e) {			
			PublishingPlugin.getDefault().getLogger().logError(e);
		}
		
		return urltext;
	}
	
	private String fixUrlText_(String urltext) {
		String guid = ResourceHelper.getGuidFromUrl(urltext);
		if (guid == null) {
			return urltext;
		}

		ILibraryManager manager = LibraryService.getInstance()
				.getCurrentLibraryManager();
		MethodElement e = manager != null ? manager.getMethodElement(guid)
				: null;
		if (e == null) {
			return urltext;
		}

		// if the element is a TermDefinition, use the bookmark in the file
		// otherwise, find the path to the element
		String href = ""; //$NON-NLS-1$
		if (e instanceof TermDefinition) {
			href = "#" + e.getName(); //$NON-NLS-1$
		} else {
			// one level back
			href = "../" + ResourceHelper.getElementPath(e).replace(File.separatorChar, '/') + ResourceHelper.getFileName(e, ".html"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		StringBuffer sb = new StringBuffer();
		Matcher m = ResourceHelper.p_link_href_picker
				.matcher(" " + urltext + " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (m.find()) {
			m.appendReplacement(sb, " href=\"" + href + "\" "); //$NON-NLS-1$ //$NON-NLS-2$
			m.appendTail(sb);

			return sb.toString();
		}

		return urltext;
	}
}
