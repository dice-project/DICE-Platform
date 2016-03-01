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

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import org.eclipse.epf.library.layout.elements.ActivityLayout;

/**
 * Extends tbe basic publish options to include HTML options.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class PublishHTMLOptions extends PublishOptions {

	// The HTML title.
	protected String title;

	// The about HTML.
	protected String aboutHTML;

	// The feedback URL.
	protected String feedbackURL;

	// Publish glossary option.
	protected boolean publishGlossary;

	// Publish index option.
	protected boolean publishIndex;

	// The banner image.
	protected String bannerImage;
	
	protected int bannerImageHeight = 67; // banner image height, can we auto calc it???

	// Check external links option.
	protected boolean checkExternalLinks;

	// Convert broken links to plain text option.
	protected boolean convertBrokenLinks;

	// Publish JavaScript navigation tree option.
	protected boolean publishJavaScriptTree = true;

	// Publish dynamic web application option.
	protected boolean publishDynamicWebApp;

	// The dynamic web application name.
	protected String dynamicWebAppName;

	// Include servlet search option.
	protected boolean includeServletSearch;

	// define the default activity tab
	protected String defaultActivityTab = ActivityLayout.TAB_NAME_ACTIVITY_WBS;
	
	/**
	 * Creates a new instance.
	 */
	public PublishHTMLOptions() {
	}

	/**
	 * Creates a new instance.
	 */
	public PublishHTMLOptions(PublishOptions options) {
		this.publishDir = options.getPublishDir();
		this.processes = options.getProcesses();
		this.publishConfiguration = options.isPublishConfiguration();
		this.publishProcess = options.isPublishProcess();
		this.publishUnopenADD = options.isPublishUnopenADD();
		this.publishBaseAD = options.isPublishBaseAD();
		this.publishLightWeightTree = options.isPublishLightWeightTree();
		this.showRelatedLinks = options.isShowRelatedLinks();
		this.showMethodContentInDescriptors = options
				.isShowMethodContentInDescriptors();
		this.showLinkedPageForDescriptor = options.isShowLinkedPageForDescriptor();
		this.showRelatedDescriptors = options.isShowRelatedDescriptors();
		this.showRelatedDescriptorsOption = options.isShowRelatedDescriptorsOption();
		this.showDescriptorsInNavigationTree = options
				.isShowDescriptorsInNavigationTree();
		this.publishEmptyCategories = options.isPublishEmptyCategories();
	}

	/**
	 * Gets the HTML title.
	 * 
	 * @return the HTML title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the HTML title.
	 * 
	 * @param title
	 *            the HTML title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the About HTML.
	 * 
	 * @return the About HTML
	 */
	public String getAboutHTML() {
		return aboutHTML;
	}

	/**
	 * Sets the About HTML.
	 * 
	 * @param aboutHTML
	 *            the About HTML
	 */
	public void setAboutHTML(String aboutHTML) {
		this.aboutHTML = aboutHTML;
	}

	/**
	 * Gets the feedback URL.
	 * 
	 * @return the feedback URL
	 */
	public String getFeedbackURL() {
		return feedbackURL;
	}

	/**
	 * Sets the feedback URL.
	 * 
	 * @param feedbackURL
	 *            the feedback URL
	 */
	public void setFeedbackURL(String feedbackURL) {
		this.feedbackURL = feedbackURL;
	}

	/**
	 * Gets the publish glossary option.
	 * 
	 * @return <code>true</code> if the glossary needs to be published
	 */
	public boolean isPublishGlossary() {
		return publishGlossary;
	}

	/**
	 * Sets the publish glossary option.
	 * 
	 * @param publishGlossary
	 *            if <code>true</code>, publish the glossary
	 */
	public void setPublishGlossary(boolean publishGlossary) {
		this.publishGlossary = publishGlossary;
	}

	/**
	 * Gets the publish index option.
	 * 
	 * @return <code>true</code> if the index needs to be published
	 */
	public boolean isPublishIndex() {
		return publishIndex;
	}

	/**
	 * Sets the publish index option.
	 * 
	 * @param publishIndex
	 *            if <code>true</code>, publish the index
	 */
	public void setPublishIndex(boolean publishIndex) {
		this.publishIndex = publishIndex;
	}

	/**
	 * Gets the banner image.
	 * 
	 * @return the banner image
	 */
	public String getBannerImage() {
		return bannerImage;
	}

	/**
	 * Sets the banner image.
	 * 
	 * @param bannerImage
	 *            the banner image
	 */
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
		
		// set the height of the banner
		Image image = null;
		File f = new File(this.bannerImage);
		if ( f.exists() ) {
			try {
				image = ImageIO.read(f);
				if ( image != null ) {
					int height = image.getHeight(null);
					if ( height > 0 ) {
						this.bannerImageHeight = height;
					}
				}
			} catch (Exception ex ) {
				// error read the file, ignore error and use the default banner height
				;
			} finally {
			}
		}
	}

	/**
	 * Gets the banner image.
	 * 
	 * @return the banner image height
	 */
	public int getBannerImageHeight() {
		return bannerImageHeight;
	}

	/**
	 * Sets the banner image.
	 * 
	 * @param bannerImage
	 *            the banner image height in pixels
	 */
	public void setBannerImageHeight(int bannerImageheight) {
		this.bannerImageHeight = bannerImageheight;
	}
	
	/**
	 * Gets the check external links option.
	 * 
	 * @return <code>true</code> if the external links need to be checked
	 */
	public boolean isCheckExternalLinks() {
		return checkExternalLinks;
	}

	/**
	 * Sets the check external links option.
	 * 
	 * @param checkExternalLinks
	 *            if <code>true</code>, check the external links
	 */
	public void setCheckExternalLinks(boolean checkExternalLinks) {
		this.checkExternalLinks = checkExternalLinks;
	}

	/**
	 * Gets the convert broken links option.
	 * 
	 * @return <code>true</code> if the convert broken links need to be
	 *         converted to plain text
	 */
	public boolean isConvertBrokenLinks() {
		return convertBrokenLinks;
	}

	/**
	 * Sets the convert broken links option.
	 * 
	 * @param convertBrokenLinks
	 *            if <code>true</code>, convert broken links to plian text
	 */
	public void setConvertBrokenLinks(boolean convertBrokenLinks) {
		this.convertBrokenLinks = convertBrokenLinks;
	}

	/**
	 * Gets the publish JavaScript navigation tree option.
	 * 
	 * @return <code>true</code> if a JavaScript navigation tree will be
	 *         published
	 */
	public boolean isPublishJavaScriptTree() {
		return publishJavaScriptTree;
	}

	/**
	 * Sets the publish JavaScript navigation tree option.
	 * 
	 * @param publishJavaScriptTree
	 *            if <code>true</code>, publish a JavaScript navigation tree
	 */
	public void setPublishJavaScriptTree(boolean publishJavaScriptTree) {
		this.publishJavaScriptTree = publishJavaScriptTree;
	}

	/**
	 * Gets the publish dynamic web application option.
	 * 
	 * @return <code>true</code> if a dynamic web application will be
	 *         published
	 */
	public boolean isPublishDynamicWebApp() {
		return this.publishDynamicWebApp 
		&& (this.dynamicWebAppName != null)
		&& (this.dynamicWebAppName.length() > 0);
	}

	/**
	 * Sets the publish dynamic web application option.
	 * 
	 * @param publishDynamicWebApp
	 *            if <code>true</code>, publish a dynamic web application
	 */
	public void setPublishDynamicWebApp(boolean publishDynamicWebApp) {
		this.publishDynamicWebApp = publishDynamicWebApp;
	}

	/**
	 * Gets the dynamic web application name.
	 * 
	 * @return the dynamic web application name
	 */
	public String getDynamicWebAppName() {
		return dynamicWebAppName;
	}

	/**
	 * Sets the dynamic web application name.
	 * 
	 * @param dynamicWebAppName
	 *            the dynamic web application name.
	 */
	public void setDynamicWebAppName(String dynamicWebAppName) {
		this.dynamicWebAppName = dynamicWebAppName;
	}

	/**
	 * Gets the include servlet search option.
	 * 
	 * @return <code>true</code> if include servlet search will be added to
	 *         the published dynamic web application
	 */
	public boolean isIncludeServletSearch() {
		return includeServletSearch && isPublishDynamicWebApp();
	}

	/**
	 * Sets the include servlet search option.
	 * 
	 * @param includeServletSearch
	 *            if <code>true</code>, include servlet search in the
	 *            published dynamic web application
	 */
	public void setIncludeServletSearch(boolean includeServletSearch) {
		this.includeServletSearch = includeServletSearch;
	}

	/**
	 * get the default activity tab
	 * @return String
	 */
	public String getDefaultActivityTab() {
		return this.defaultActivityTab;
	}
	
	/**
	 * set the default activity tab
	 * @param tabName
	 */
	public void setDefaultActivityTab(String tabName) {
		this.defaultActivityTab = tabName;
	}
	
	/**
	 * Validates the publish options.
	 * 
	 * @return <code>true</code> if the publish options is valid.
	 */
	public void validate() {
		super.validate();
		
	}
}
