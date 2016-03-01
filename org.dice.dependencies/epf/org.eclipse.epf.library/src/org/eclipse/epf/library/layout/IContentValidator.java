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
package org.eclipse.epf.library.layout;

import java.io.File;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;

/**
 * interface for content validator at browsing and publishing
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public interface IContentValidator  {

	final Object elementUrlFeature = new Object();
	
	/**
	 * set publish dir
	 * @param pubDir String
	 */
	public void setPublishDir(String pubDir);
	
	/**
	 * show broken links if true
	 * @return boolean
	 */
	public boolean showBrokenLinks();
	
	public void scanContent(IElementLayout layout, String content) throws Exception;

	/**
	 * validate the link attribute and linked text for the owning element within the configuration. 
	 * Returns a LinkInfo object.
	 * 
	 * @param owner {@link MethodElement} the owner element
	 * @param attributes String the url attributes
	 * @param text String the linked text
	 * @param config MethodConfiguration
	 * @param tag the HTML tag (a, area)
	 * @return LinkInfo
	 */
	public LinkInfo validateLink(MethodElement owner, String attributes,
			String text, MethodConfiguration config, String tag);

	/**
	 * log the info for the element
	 * @param owner MethodElement
	 * @param message String
	 */
	public void logInfo(MethodElement owner, String message);

	/**
	 * log a missing reference
	 * @param owner MethodElement
	 * @param refElement MethodElement the referneced element that is missing in configuration
	 */
	public void logMissingReference(MethodElement owner,
			MethodElement refElement);

	/**
	 * log a missing reference
	 * @param owner MethodElement the owner element
	 * @param guid String the referenced guid which is not a valid element in the configuration any more
	 * @param linkedText String the linked text along with the link.
	 */
	public void logMissingReference(MethodElement owner, String guid,
			String linkedText);

	/**
	 * log a missing resource 
	 * @param owner MethodElement the owner
	 * @param resourceFile File the file that owned by the element and references the mising resource, 
	 * null if the mising reference is referenced by the element itself 
	 * @param url String the url that can't be resovled to a resource.
	 */
	public void logMissingResource(MethodElement owner, File resourceFile,
			String url);

	/**
	 * logn a warning message
	 * @param owner MethodElement
	 * @param message String
	 */
	public void logWarning(MethodElement owner, String message);

	/**
	 * log an error message
	 * @param owner MethodElement
	 * @param message String
	 * @param th Throwable, null if not
	 */
	public void logError(MethodElement owner, String message, Throwable th);

	/**
	 * log an invalid external link
	 * @param owner MethodElement
	 * @param url String the url
	 * @param message String
	 */
	public void logInvalidExternalLink(MethodElement owner, String url,
			String message);

	/**
	 * log a message
	 * @param message String
	 */
	public void logInfo(String message);

	/**
	 * log a warning
	 * @param message String
 	 */
	public void logWarning(String message);

	/**
	 * log an error
	 * @param message String
	 * @param th Throwable
	 */
	public void logError(String message, Throwable th);

	/**
	 * check if the element is discarded or not
	 * discarded elements will be treated as out side the configursation
	 * 
	 * @param owner MethodElement the owner of the element
	 * @param Object feature EStructuralFeature or OppositeFeature
	 * @param e MethodElement the element to be checked
	 */
	public boolean isDiscarded(MethodElement owner, Object feature, MethodElement e);
	
	public boolean isDiscarded(MethodElement owner, Object feature, MethodElement e, MethodConfiguration config);

	/**
	 * set an discarded element
	 * @param e MethodElement
	 */
	public void setDiscardedElement(MethodElement e);

	/**
	 * dispose the object and free resources
	 *
	 */
	public void dispose();
		
	/**
	 * add a referneced element
	 * 
	 * @param owner MethodElement
	 * @param e MethodElement the element referenced by the owner element
	 */
	public void addReferencedElement(MethodElement owner, MethodElement e);
		
	/**
	 * get the flag about showing extra information from the linked elements for descriptors
	 * @return boolean
	 */
	public boolean showExtraInfoForDescriptors();

	/**
	 * show descriptors on method element page
	 */
	public boolean showRelatedDescriptors();
	
	/**
	 * get the tab id for the activity layout
	 * 
	 * @return String
	 */
	public String getDefaultActivityTab();
	
	/**
	 * show lined element page for a descriptor if true
	 */
	public boolean showLinkedPageForDescriptor();
	
}
