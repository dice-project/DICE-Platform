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

import java.util.List;

import org.eclipse.epf.library.layout.Bookmark;
import org.eclipse.epf.library.layout.HtmlBuilder;

/**
 * define the interface for site generator. 
 * Each publisher should implement a site generator to handle the site generation properly.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public interface ISiteGenerator {
			
	public void prePublish() throws Exception;
	public void postPublish() throws Exception;

	public void writePublishedBookmarks(List bookmarks, Bookmark defaultView) throws Exception;

	public HtmlBuilder getHtmlBuilder();
	public PublishOptions getPublishOptions();
	public String getIndexFilePath();
	public String getNodeIconPath(); 
	public String getCustomizedNodeIconPath();
	public void dispose();
	
}
