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
package org.eclipse.epf.publishing.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.layout.util.XmlHelper;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;

/**
 * Helper utilities for publishing.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PublishingUtil {

	/**
	 * Zips the content of a directory into a JAR file.
	 * <p>
	 * Non-sub-folders are included.
	 * 
	 * @param jarDir
	 *            the directory to be zipped
	 * @param jarFile
	 *            the output JAR file
	 * @throws IOException
	 *             if an error occurs while creating the JAR file
	 */
	public static void jarFiles(File jarDir, File jarFile) throws IOException {
		File[] files = jarDir.listFiles();
		if (jarFile.exists()) {
			jarFile.delete();
		}
		BufferedOutputStream bStream = new BufferedOutputStream(
				new FileOutputStream(jarFile));
		ZipOutputStream zipperStream = new ZipOutputStream(bStream);

		byte[] bytes = new byte[4096];
		for (int i = 0; i < files.length; i++) {
			File currentFile = files[i];
			if (currentFile.isDirectory()) {
				continue;
			}
			ZipEntry currEntry = new ZipEntry(currentFile.getName());
			zipperStream.putNextEntry(currEntry);
			BufferedInputStream biStream = new BufferedInputStream(
					new FileInputStream(currentFile));
			while (biStream.available() > 0) {
				int num = biStream.read(bytes);
				zipperStream.write(bytes, 0, num);
			}
			biStream.close();
			zipperStream.closeEntry();
		}
		zipperStream.close();
		bStream.close();
	}

	/**
	 * Generates the HTML content for a given XML element and XSL file path
	 * 
	 * @param xmlElement
	 *            an object that contains the XML representation for a metod
	 *            element
	 * @param xslPath
	 *            path to a XSL file
	 * @return the output HTML
	 */
	public static String getHtml(XmlElement xmlElement, String xslPath) {
		try {
			// set the language attribute
			Locale locale = Locale.getDefault();
			String lang = locale.getLanguage();
			xmlElement.setAttribute("lang", lang); //$NON-NLS-1$

			URL url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					xslPath);
			if (url == null) {
				PublishingPlugin.getDefault().getLogger().logError(
						"Unable to access XSL template " + xslPath); //$NON-NLS-1$
				return null;
			}

			String xslURI = FileLocator.resolve(url).getPath();

			StringBuffer xml = new StringBuffer();
			xml.append(XmlHelper.XML_HEADER).append(xmlElement.toXml());

			Properties xslParams = PublishingPlugin.getDefault().getProperties(
					"/xsl/resources.properties"); //$NON-NLS-1$

			// generate the HTML
			StringWriter sw = new StringWriter();
			XSLTProcessor.transform(xslURI, xml.toString(), xslParams, sw);
			sw.flush();

			// urls are encoded in XSLT output, decode it
			return ResourceHelper
					.decodeUrlsInContent(sw.getBuffer().toString());
		} catch (Exception e) {
			PublishingPlugin.getDefault().getLogger().logError(
					"Unable to generate HTML", e); //$NON-NLS-1$
		}

		return null;
	}

	/**
	 * Checks whether a configuration contains a view that references a process.
	 * 
	 * @param config
	 *            a method configuration
	 * @param process
	 *            a capability pattern or delivery process
	 * @return <code>true</code> if the configuration contains a view that
	 *         references a process
	 */
	public static boolean hasValidProcessView(MethodConfiguration config,
			Process process) {
		for (Iterator<ContentCategory> views = config.getProcessViews()
				.iterator(); views.hasNext();) {
			ContentCategory view = views.next();
			if (view instanceof CustomCategory) {
				CustomCategory cc = (CustomCategory) view;
				for (Iterator<DescribableElement> it = cc
						.getCategorizedElements().iterator(); it.hasNext();) {
					DescribableElement element = it.next();
					if (element instanceof Process) {
						if (((Process) element).getGuid() == process.getGuid()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static void unloadResources(Collection<Resource> resources) {
		
		//Runtime.getRuntime().gc();
		//long m1 = Runtime.getRuntime().freeMemory()/1000000;
		PersistenceUtil.unload(resources);
		//Runtime.getRuntime().gc();		
		//long m2 = Runtime.getRuntime().freeMemory()/1000000;
		//System.out.println("*** " + Long.toString(m2-m1) + " MB freed by unloading resources");
	}

	public static void disposeDiagramManagers(Collection<DiagramManager> mgrs, Collection<DiagramManager> keeplist) {
		for ( Iterator<DiagramManager> it = mgrs.iterator(); it.hasNext(); ) {
			DiagramManager mgr = it.next();
			if ( keeplist == null || !keeplist.contains(mgr) ) {
				mgr.dispose();
			}
		}
	}
}
