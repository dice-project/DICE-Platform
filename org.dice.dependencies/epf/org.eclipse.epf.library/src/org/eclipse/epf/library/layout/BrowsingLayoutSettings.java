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
import java.util.ArrayList;
import java.util.List;

/**
 * Define the browsing settings. UI preference can set the options to change the browsing behavior
 * 
 * @author Jinhua Xi
 * @since 1.2
 * 
 */
public class BrowsingLayoutSettings {

	public static BrowsingLayoutSettings INSTANCE = new BrowsingLayoutSettings();
	
	private boolean showExtraInfoForDescriptors = false;
	private boolean publishUnopenActivitydd = false;
	private boolean publishADForActivityExtension = false;
	private boolean fulfillDescriptorSlotByContent = false;
	private boolean showLinkedPageForDescriptor = false;
	private String forbiddenUrlChars;
	private boolean ignoreDynamicParents = true;
	private boolean excludeUnusedWPDs = true;

	// if true, the to-many outgoing association from base will be ignored if the extender has its own
	private boolean useNewExtendSemantics = false;
	
	private  File xslPath = null;
	private  File cssPath = null;
	private File scriptPath = null;
	
	// record all the sites updated since the last change
	private  List updatedSites = new ArrayList();
	
	public void setUseNewExtendSemantics(boolean flag) {
		this.useNewExtendSemantics = flag;
	}
	
	public boolean isUseNewExtendSemantics() {
		return this.useNewExtendSemantics;
	}
	
	public void setShowExtraInfoForDescriptors(boolean flag) {
		this.showExtraInfoForDescriptors = flag;
	}
	
	public boolean isShowExtraInfoForDescriptors() {
		return this.showExtraInfoForDescriptors;
	}
	
	public void setPublishUnopenActivitydd(boolean flag) {
		this.publishUnopenActivitydd = flag;
	}
	
	public boolean isPublishUnopenActivitydd() {
		return this.publishUnopenActivitydd;
	}
	
	public void setPublishADForActivityExtension(boolean flag) {
		this.publishADForActivityExtension = flag;
	}
	
	public boolean isPublishADForActivityExtension() {
		return this.publishADForActivityExtension;
	}
	
	public  void setXslPath(File path) {
		xslPath = path;
	}
	
	public  void setCssPath(File path) {
		cssPath = path;
	}
	
	public  void setScriptPath(File path) {
		scriptPath = path;
	}
	
	public  File getXslPath() {
		return xslPath;
	}
	
	public  File getCssPath() {
		return cssPath;
	}
	
	public  File getScriptPath() {
		return scriptPath;
	}
	
	public  void setChanged() {
		updatedSites.clear();
	}
	
	
	public  boolean needUpdate(String pubDir) {
		if ( pubDir == null ) {
			return false;
		}
		return !updatedSites.contains(pubDir);
	}
	
	public  void update(HtmlBuilder builder) {
		if ( (builder == null) || !needUpdate(builder.getPublishDir()) ) {
			if ((builder != null)&&(xslPath != null)) {
				builder.setLayoutXslRootPath(xslPath.getAbsolutePath());
			}
			return;
		}
		if ( xslPath == null || cssPath == null || scriptPath == null) {
			// use the default
			LayoutResources.copyLayoutFiles(builder.getPublishDir());
			builder.loadDefaultLayoutXsl();
		} else {
			
			// set the xsl path
			builder.setLayoutXslRootPath(xslPath.getAbsolutePath());
			
			// copy the fils to the publish folder
			String include = "*.*,**/*.*"; //$NON-NLS-1$
			String exclude = null;
			boolean override = true;
			
			File folder = new File(builder.getPublishDir(), "css");			 //$NON-NLS-1$
			LayoutResources.copyDir(cssPath, folder, include, exclude, override);
			
			folder = new File(builder.getPublishDir(), "scripts");			 //$NON-NLS-1$
			LayoutResources.copyDir(scriptPath, folder, include, exclude, override);
		}
		
		updatedSites.add(builder.getPublishDir());
	}

	public boolean isFulfillDescriptorSlotByContent() {
		return fulfillDescriptorSlotByContent;
	}

	public void setFulfillDescriptorSlotByContent(
			boolean fulfillDescriptorSlotByContent) {
		this.fulfillDescriptorSlotByContent = fulfillDescriptorSlotByContent;
	}
	
	public boolean isShowLinkedPageForDescriptor() {
		return showLinkedPageForDescriptor;
	}

	public void setShowLinkedPageForDescriptor(boolean showLinkedPageForDescriptor) {
		this.showLinkedPageForDescriptor = showLinkedPageForDescriptor;
	}
	
	public String getForbiddenUrlChars() {
		return forbiddenUrlChars;
	}

	public void setForbiddenUrlChars(String forbiddenUrlChars) {
		this.forbiddenUrlChars = forbiddenUrlChars;
	}
	
	public boolean isIgnoreDynamicParents() {
		return ignoreDynamicParents;
	}

	public void setIgnoreDynamicParents(boolean ignoreDynamicParents) {
		this.ignoreDynamicParents = ignoreDynamicParents;
	}
	
	public boolean isExcludeUnusedWPDs() {
		return excludeUnusedWPDs;
	}

	public void setExcludeUnusedWPDs(boolean excludeUnusedWPDs) {
		this.excludeUnusedWPDs = excludeUnusedWPDs;
	}
	
}
