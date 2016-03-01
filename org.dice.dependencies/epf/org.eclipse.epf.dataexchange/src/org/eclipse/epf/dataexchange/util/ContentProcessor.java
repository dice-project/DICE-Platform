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
package org.eclipse.epf.dataexchange.util;

import java.io.File;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.common.utils.FileUtil;



/**
 * content processer to fix references and copy resources to the target library location
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ContentProcessor {

	protected static final Pattern p_src_ref = Pattern.compile("src\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
//	protected static final Pattern p_link_ref = Pattern.compile("<a\\s+?([^>]*)>(.*?)</a>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
	protected static final Pattern p_href_ref = Pattern.compile("href\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	IResourceHandler handler;
	ILogger logger;
	
	public ContentProcessor(IResourceHandler handler, ILogger logger) {
		this.handler = handler;
		this.logger = logger;
	}
	
	/**
	 * resovle the resource string for the given owner. 
	 * The owner is a method element, but can be either from uma model or xml model. 
	 * @param owner
	 * @param source
	 * @return
	 */
	public String resolveResourceFiles(Object owner, String source) {
		StringBuffer sb = new StringBuffer();

		try
		{
			// process images and other src resources
			Matcher m = p_src_ref.matcher(source);
			while ( m.find() )
			{
				String url = m.group(1);
				String new_url  = processResourceUrl(owner, url);
				String replacement = "src=\"" + new_url + "\""; 		 //$NON-NLS-1$ //$NON-NLS-2$
				m.appendReplacement(sb, fixReplacementStr(replacement));
			}
			m.appendTail(sb);
			
			// process hrefs
			m = p_href_ref.matcher(sb.toString());
			sb.setLength(0);
			
			while ( m.find() )
			{
				String url = m.group(1);
				String mark = ""; //$NON-NLS-1$

				int index = url.indexOf("#"); //$NON-NLS-1$
				if ( index >= 0 )
				{
					mark = url.substring(index);
					url = url.substring(0, index);
				}

				if ( url.length() > 0 )
				{
					url = processResourceUrl(owner, url);
				}
				m.appendReplacement(sb, fixReplacementStr("href=\"" + url + mark + "\"")); //$NON-NLS-1$ //$NON-NLS-2$
			}
			m.appendTail(sb);
			
			// anything else ???
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		//System.out.println(sb);
		
		return sb.toString();
	}
	
	private String fixReplacementStr(String str) {
		// escape the $ since it's treated as a group sequence
		try {
			int index = str.indexOf("$"); //$NON-NLS-1$
			if ( index < 0 ) {
				return str;
			}
			
			StringBuffer b = new StringBuffer();
			int start = 0;
			while ( index >= 0 ) {
				b.append(str.substring(start, index)).append("\\$"); //$NON-NLS-1$
				start = index+1;
				index = str.indexOf("$", start); //$NON-NLS-1$
			}
			
			b.append(str.substring(start));
			
			return b.toString();
		
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	protected boolean isFileUrl(String url)
	{
		//System.out.println("Processing image url [" + url + "]");
		if ( 	url == null || url.length() == 0
				|| url.startsWith("http") //$NON-NLS-1$
				|| url.startsWith("www.") //$NON-NLS-1$
				|| url.startsWith("mailto:") //$NON-NLS-1$
				|| url.toLowerCase().indexOf("javascript:") >=0 ) //$NON-NLS-1$
		{
			return false;
		}

		return true;
	}
	
	protected boolean isValidFilePath(File f) {
		return (f != null) && f.exists();
	}
	

	public String processResourceUrl(Object owner, String url)
	{
		if ( !isFileUrl(url) )
		{
			return url;
		}

		UrlInfo info = null;
		try {
			info = handler.resolveFileUrl(owner, url);
		} catch (Exception ex) {
			logger.logWarning("error processing url '" + url + "'"); //$NON-NLS-1$ //$NON-NLS-2$
			return url;
		}
		
		if ( info.sourceFile == null || info.targetFile == null ) {
			return url;
		}
		
		if ( info.sourceFile.isFile() && info.sourceFile.exists() )
		{
			FileUtil.copyFile(info.sourceFile, info.targetFile);
			
			// return the fixed target url if the file exists 
			// and is copied over to the target location
			return info.targetUrl;
		}

		return url;
	}


	/**
	 * process the url, update the result buffer, and return the associated element if any
	 * @param source teh url text string
	 * @param result StringBuffer
	 * @return MethodElement the linked element or null
	 */
	protected void processUrlText(Object owner, String source)
	{
		Matcher m = p_href_ref.matcher(source);
		if ( m.find() )
		{
			String url = m.group(1);
			if ( url.toLowerCase().indexOf("javaScript:") < 0 ) //$NON-NLS-1$
			{
				int index = url.indexOf("#"); //$NON-NLS-1$
				if ( index >= 0 )
				{
					url = url.substring(0, index);
				}

				if ( url.length() > 0 )
				{
					processResourceUrl(owner, url);
				}
			}
		}
	}
	
	public void copyResource(String sourceFile) {
		handler.copyResource(sourceFile);
	}
	
	public void copyResource(String sourceFile, EObject obj, org.eclipse.epf.uma.MethodPlugin umaPlugin) {
		handler.copyResource(sourceFile, obj, umaPlugin);
	}
	
	/**
	 * this method is for resolveing the template attachment resources
	 * The resource is a set of urls seperated by |
	 * 
	 * @param owner
	 * @param urls
	 * @return String
	 */
	public String resolveAttachmentResources(Object owner, String urls) {
		if ( (urls != null) && urls.length() != 0 ) {
			StringBuffer buffer = new StringBuffer();
			StringTokenizer st = new StringTokenizer(urls, "|"); // this is hardcoded somehow //$NON-NLS-1$
			while (st.hasMoreTokens() ) {
				String url = st.nextToken();
				url = processResourceUrl(owner, url);
				if ( buffer.length() > 0 ) {
					buffer.append("|"); //$NON-NLS-1$
				}
				buffer.append(url);
			}
			
			return buffer.toString();
		}
		
		return ""; //$NON-NLS-1$
	}
}
