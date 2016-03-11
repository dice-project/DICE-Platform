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
package org.eclipse.epf.library.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.layout.IContentValidator;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.UmaPackage;

import com.ibm.icu.util.StringTokenizer;

/**
 * utility class to scan content and copy resources to the target location for browsing and publishing
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ContentResourceScanner {

	protected static final Pattern p_image_ref = Pattern
			.compile(
					"(<(img|iframe).*?src\\s*=\\s*\")(.*?)(\")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	private File sourceRootPath;

	private File targetRootPath;

	private String rootContentPath;
	
	private IContentValidator validator;

	private List processedItems = new ArrayList();

	/**
	 * construct the object with the root path of the source content
	 * @param sourceRootPath File
	 */
	public ContentResourceScanner(File sourceRootPath, File targetRootPath, String rootContentPath) {
		this(sourceRootPath, targetRootPath, rootContentPath, null);
	}

	/**
	 * construct the object instance with the root path of the source content and a content validator
	 * 
	 * @param sourceRootPath File
	 * @param validator IContentValidator
	 */
	public ContentResourceScanner(File sourceRootPath, File targetRootPath, String rootContentPath, 
			IContentValidator validator) {
		this.sourceRootPath = sourceRootPath;
		this.targetRootPath = targetRootPath;
		this.rootContentPath = rootContentPath;
		this.validator = validator;
	}

	/**
	 * set content validator
	 * @param validator IContentValidator
	 */
	public void setValidator(IContentValidator validator) {
		this.validator = validator;
	}

	/**
	 * get the source root path
	 * @return File
	 */
	public File getSourceRootPath() {
		return this.sourceRootPath;
	}
	
	/**
	 * get the target root path
	 * @return File
	 */
	public File getTargetRootPath() {
		return this.targetRootPath;
	}
	
	/**
	 * set the target root path
	 * @param targetRootPath File
	 */
	public void setTargetRootPath(File targetRootPath) {
		this.targetRootPath = targetRootPath;
	}

	/**
	 * resolve the images in the text. copy the image to destination if needed
	 * This is used for copying resources from a library to another destination
	 * 
	 * @param content
	 * @param contentPath
	 *            the content path of the source
	 */
	public void resolveResources(String content, String contentPath) {
		resolveResources(null, content, contentPath);
	}

	/**
	 * resolve the images in the text. copy the image to destination if needed
	 * This is used for copying resources from a library to another destination
	 * 
	 * @param owner MethodElement
	 * @param content String
	 * @param contentPath String
	 *            the content path of the source
	 */
	public void resolveResources(MethodElement owner, String content,
			String contentPath) {
		processedItems.clear();
		ResourceHelper.resolveResources(owner, null, content, contentPath,
				this.sourceRootPath, this.targetRootPath, this.rootContentPath, processedItems,
				validator);
	}

	/**
	 * resolve the images in the text. copy the image to destination if needed
	 * This is used for moving resources within a library ie, when
	 * sourceRootPath.equals(targetRootPath)
	 * 
	 * @param source
	 * @param contentPath
	 *            the content path of the source
	 * @return String
	 */
	public String resolveResourcesPlugin(String content, String contentPath,
			String oldContentPath) {
		StringBuffer sb = new StringBuffer();
		try {
			// process images
			Matcher m = ResourceHelper.p_image_ref.matcher(content);
			while (m.find()) {
				String url = m.group(3);
				url = processResourceUrlPlugin(url, contentPath, oldContentPath);
				m.appendReplacement(sb, m.group(1) + url + m.group(4));
			}
			m.appendTail(sb);

			content = sb.toString();
			sb = new StringBuffer();

			// process attachments
			m = ResourceHelper.p_link_ref_gen.matcher(content);
			while (m.find()) {
				StringBuffer sbLink = new StringBuffer();
				// String tag = m.group(1);
				String urltext = " " + m.group(2) + " "; //$NON-NLS-1$ //$NON-NLS-2$
				if (ResourceHelper.getGuidFromUrl(urltext) == null) {
					Matcher m2 = ResourceHelper.p_link_href_picker
							.matcher(urltext);
					if (m2.find()) {
						String url = m2.group(1).trim().replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
						url = processResourceUrlPlugin(url, contentPath,
								oldContentPath);
						m2.appendReplacement(sbLink, urltext.substring(m2
								.start(), m2.start(1))
								+ url + urltext.substring(m2.end(1), m2.end()));
						m2.appendTail(sbLink);
						m.appendReplacement(sb, content.substring(m.start(), m
								.start(2))
								+ sbLink.toString()
								+ content.substring(m.end(2), m.end()));
					}
				}

			}
			m.appendTail(sb);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * Processes a resource url for moving within a library
	 * 
	 * @param url
	 * @param contentPath
	 *            new plugin path
	 * @param oldContentPath
	 *            old plugin path
	 * @return String
	 */
	private String processResourceUrlPlugin(String url, String contentPath,
			String oldContentPath) {
		String imageFile = ResourceHelper.getFilePathFromUrl(url,
				oldContentPath);
		if (imageFile == null) {
			return url;
		}
		String newUrl = null;
		File source;
		File dest;
		// targetRootPath is set to the target plugin directory
		source = new File(this.sourceRootPath.getParentFile(), imageFile);
		if (imageFile.indexOf(File.separator) != -1) {
			String oldPlugin = imageFile.substring(0, imageFile
					.indexOf(File.separator));
			String newPlugin = this.targetRootPath.getName();
			newUrl = url.replaceFirst(oldPlugin, newPlugin);
			imageFile = imageFile.substring(imageFile.indexOf(File.separator));
		}
		dest = new File(this.targetRootPath, imageFile);

		FileManager.copyFile(source, dest);

		return newUrl;
	}

	
	/**
	 * copy all resources referenced by the the element and all its contained elements, recursively
	 * @param element EObject
	 */
	public void copyResources(EObject element) {
		
		if ( element == null ) {
			return;
		}
		
		if ( element instanceof DescribableElement ) {
			DescribableElement de = (DescribableElement)element;
			String elementPath = ResourceHelper.getElementPath(de);
			
			//1. icons
			java.net.URI icon = de.getShapeicon();
			if ( icon != null ) {
				String path = icon.getPath();
				FileUtil.copyFile(
						new File(getSourceRootPath(), path), 
						new File(getTargetRootPath(), path));
			}
			
			icon = de.getNodeicon();
			if ( icon != null ) {
				String path = icon.getPath();
				FileUtil.copyFile(
						new File(getSourceRootPath(), path), 
						new File(getTargetRootPath(), path));
			}
			
			// 2. attachments
		
			
			// 3. contents
			ContentDescription desc = de.getPresentation();
			if ( desc.eContainer() != null ) {
				List features = LibraryUtil.getStructuralFeatures(desc, true);
//				List properties = desc.getInstanceProperties();
								
				// get all string type attributes
				for (int i = 0; i < features.size(); i++) {
					EStructuralFeature feature = (EStructuralFeature) features
							.get(i);
					if ( !(feature instanceof EAttribute) ) {
						continue;
					}
					
//					Object value = desc.eGet(feature);
					Object value = TypeDefUtil.getInstance().eGet(desc, feature);
					if ( value == null ) {
						continue;
					}
					
					
					if (feature == UmaPackage.eINSTANCE.getContentDescription_Sections() ) {
						List sections = (List)value;
						for (Iterator it = sections.iterator(); it.hasNext(); ) {
							String text = ((Section)it.next()).getSectionDescription();
							resolveResources(de, text, elementPath);
						}
					}  else if (feature == UmaPackage.eINSTANCE.getGuidanceDescription_Attachments() ) {
						String urls = value.toString().trim();
						if (urls.length() != 0 ) {
							StringTokenizer st = new StringTokenizer(urls, "|"); // this is hardcoded somehow //$NON-NLS-1$
							while (st.hasMoreTokens() ) {
								String url = st.nextToken();
								FileUtil.copyFile(
										new File(getSourceRootPath(), elementPath + url), 
										new File(getTargetRootPath(), elementPath + url));

							}
						}
					} else if ( value instanceof String ) {
						resolveResources(de, value.toString(), elementPath);
					} else {
						// do nothing
					}
				}
			}
		}
	}
	
}
