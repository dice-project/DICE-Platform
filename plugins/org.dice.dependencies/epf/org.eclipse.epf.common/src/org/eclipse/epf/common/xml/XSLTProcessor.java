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
package org.eclipse.epf.common.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.epf.common.CommonPlugin;

/**
 * A wrapper over the XSLT processor bundled with the JRE.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class XSLTProcessor {

	// If true, cache the compiled the XSL transformer with the compiled XSL
	// templates.
	private static boolean cacheXSL;

	// Caches the XSL transformers.
	private static Map<String, CachedTransformer> transformerCache;

	static {
		String cacheXSLProperty = CommonPlugin.getDefault().getString(
				"cacheXSL"); //$NON-NLS-1$
		if (cacheXSLProperty != null && !cacheXSLProperty.startsWith("[")) { //$NON-NLS-1$
			cacheXSL = Boolean.getBoolean(cacheXSLProperty);
		} else {
			cacheXSL = true;
		}
		if (cacheXSL) {
			transformerCache = new HashMap<String, CachedTransformer>();
		}

		// Increase the entity expansion line limit to handle a larger number of
		// XML entities.
		System.setProperty("entityExpansionLimit", "1000000"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Default private constructor to prevent this class from being
	 * instantiated.
	 */
	private XSLTProcessor() {
	}

	/**
	 * Executes the XSL transformation given the XSL source, XML source, target
	 * output and encoding.
	 * 
	 * @param xslSource
	 *            The XSL source.
	 * @param xmlSource
	 *            The XML source.
	 * @param output
	 *            The output target.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(Source xslSource, Source xmlSource,
			Writer output, Properties params, String encoding) throws Exception {
		if (xslSource != null && xmlSource != null) {
			Transformer transformer = null;
			String xslFile = xslSource.getSystemId();
			if (cacheXSL && xslFile != null) {
				CachedTransformer cachedTransformer = null;
				synchronized (transformerCache) {
					cachedTransformer = transformerCache.get(xslFile);
					if (cachedTransformer == null) {
						TransformerFactory factory = TransformerFactory
								.newInstance();
						transformer = factory.newTransformer(xslSource);
						transformerCache.put(xslFile, new CachedTransformer(
								transformer, params));
					} else {
						cachedTransformer.setParams(params);
						transformer = cachedTransformer.getTransformer();
					}
				}
			} else {
				TransformerFactory factory = TransformerFactory.newInstance();
				transformer = factory.newTransformer(xslSource);

				if (params != null && params.size() > 0) {
					for (Iterator<Object> i = params.keySet().iterator(); i
							.hasNext();) {
						String paramName = (String) i.next();
						String paramValue = params.getProperty(paramName);
						transformer.setParameter(paramName, paramValue);
					}
				}
			}

			if (encoding != null && encoding.length() > 0) {
				transformer.setOutputProperty("encoding", encoding); //$NON-NLS-1$
			} else {
				transformer.setOutputProperty("encoding", "utf-8"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			transformer.transform(xmlSource, new StreamResult(output));
		}
	}

	/**
	 * Executes the XSL transformation given the XSL source, XML source, target
	 * output and encoding.
	 * 
	 * @param xslSource
	 *            The XSL source.
	 * @param xmlSource
	 *            The XML source.
	 * @param output
	 *            The output target.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(Source xslSource, Source xmlSource,
			Writer output, String encoding) throws Exception {
		transform(xslSource, xmlSource, output, null, encoding);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML source,
	 * target output and encoding.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML source.
	 * @param output
	 *            The output target.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(String xslUri, Source xmlSource,
			Writer output, Properties params, String encoding) throws Exception {
		InputStream xslInput = getXslInputStream(xslUri);
		if (xslInput != null) {
			StreamSource xslSource = new StreamSource(xslInput);
			xslSource.setSystemId(new File(xslUri));
			transform(xslSource, xmlSource, output, params, encoding);
			try {
				xslInput.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML source,
	 * target output and encoding.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML source.
	 * @param output
	 *            The output target.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(String xslUri, Source xmlSource,
			Writer output, String encoding) throws Exception {
		transform(xslUri, xmlSource, output, null, encoding);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string,
	 * target output and encoding.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param output
	 *            The output target.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(String xslUri, String xmlStr, Writer output,
			Properties params, String encoding) throws Exception {
		InputStream xslInput = getXslInputStream(xslUri);
		if (xslInput != null) {
			StreamSource xslSource = new StreamSource(xslInput);
			xslSource.setSystemId(new File(xslUri));

			byte[] xml = xmlStr.getBytes("utf-8"); //$NON-NLS-1$
			ByteArrayInputStream xmlInput = new ByteArrayInputStream(xml);
			StreamSource xmlSource = new StreamSource(xmlInput);

			transform(xslSource, xmlSource, output, params, encoding);

			try {
				xslInput.close();
				xmlInput.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string,
	 * target output and encoding.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param output
	 *            The output target.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(String xslUri, String xmlStr, Writer output,
			String encoding) throws Exception {
		transform(xslUri, xmlStr, output, null, encoding);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string,
	 * target output and encoding.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param file
	 *            The output file.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(String xslUri, String xmlStr, File file,
			Properties params, String encoding) throws Exception {
		FileWriter output = new FileWriter(file);
		if (output != null) {
			transform(xslUri, xmlStr, output, params, encoding);
			try {
				output.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string,
	 * target output and encoding.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param file
	 *            The output file.
	 * @param encoding
	 *            The target encoding.
	 */
	public static void transform(String xslUri, String xmlStr, File file,
			String encoding) throws Exception {
		transform(xslUri, xmlStr, file, null, encoding);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML source
	 * and target output.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML source.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param output
	 *            The output target.
	 */
	public static void transform(String xslUri, Source xmlSource,
			Properties params, Writer output) throws Exception {
		transform(xslUri, xmlSource, output, params, null);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML source
	 * and target output.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML source.
	 * @param output
	 *            The output target.
	 */
	public static void transform(String xslUri, Source xmlSource, Writer output)
			throws Exception {
		transform(xslUri, xmlSource, output, null, null);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string
	 * and target output.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param output
	 *            The output target.
	 */
	public static void transform(String xslUri, String xmlStr,
			Properties params, Writer output) throws Exception {
		transform(xslUri, xmlStr, output, params, null);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string
	 * and target output.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param output
	 *            The output target.
	 */
	public static void transform(String xslUri, String xmlStr, Writer output)
			throws Exception {
		transform(xslUri, xmlStr, output, null, null);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string
	 * and target output file.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param params
	 *            The parameters for the XSL transformation.
	 * @param output
	 *            The output target.
	 */
	public static void transform(String xslUri, String xmlStr,
			Properties params, File file) throws Exception {
		transform(xslUri, xmlStr, file, params, null);
	}

	/**
	 * Executes the XSL transformation given the XSL stylesheet URI, XML string
	 * and target output file.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 * @param xmlStr
	 *            The XML string.
	 * @param output
	 *            The output target.
	 */
	public static void transform(String xslUri, String xmlStr, File file)
			throws Exception {
		transform(xslUri, xmlStr, file, null, null);
	}

	/**
	 * Returns the XSL input stream given the XSL stylesheet URI.
	 * 
	 * @param xslURI
	 *            The XSL stylesheet URI.
	 */
	private static InputStream getXslInputStream(String xslUri) {
		InputStream xslInput = null;
		try {
			xslInput = new FileInputStream(xslUri);
		} catch (Exception e) {
			if (xslInput == null) {
				xslInput = XSLTProcessor.class.getClassLoader()
						.getResourceAsStream(xslUri);
			}
		}
		return xslInput;
	}

}

/**
 * A cached XSL Transformer object.
 */
class CachedTransformer {

	private Transformer transformer;
	private Properties params;

	public CachedTransformer(Transformer transformer, Properties params) {
		this.transformer = transformer;
		setParams(params);
	}

	public Transformer getTransformer() {
		return transformer;
	}

	public Properties getParams() {
		return params;
	}

	public void setParams(Properties params) {
		if (params != this.params) {
			transformer.clearParameters();
			for (Iterator<Object> i = params.keySet().iterator(); i.hasNext();) {
				String paramName = (String) i.next();
				String paramValue = params.getProperty(paramName);
				transformer.setParameter(paramName, paramValue);
			}
			this.params = params;			
		}
	}

}
