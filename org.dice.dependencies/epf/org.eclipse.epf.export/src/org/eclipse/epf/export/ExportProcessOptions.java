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
package org.eclipse.epf.export;

import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * The export prcoess options.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ExportProcessOptions extends ExportOptions {

	protected MethodConfiguration config;

	private boolean publishWebSite;

	private PublishOptions publishingOptions;

	private boolean exportOnlyPlannedWBSElements;

	/**
	 * Creates a new instance.
	 */
	public ExportProcessOptions() {
		super();
	}

	/**
	 * Gets the method configuration that will be used export the process.
	 * 
	 * @return a method configuration
	 */
	public MethodConfiguration getMethodConfiguration() {
		return config;
	}

	/**
	 * Sets the method configuration that will be used export the process.
	 * 
	 * @param config
	 *            a method configuration
	 */
	public void setMethodConfiguration(MethodConfiguration config) {
		this.config = config;
	}

	/**
	 * Gets the publish process web site option.
	 * 
	 * @return <code>true</code> if the process web site should be published
	 *         as part of the export operation
	 */
	public boolean getPublishWebSite() {
		return publishWebSite;
	}

	/**
	 * Sets the publish process web site option.
	 * 
	 * @param publishWebSite
	 *            if <code>true</code>, publish the process web site
	 */
	public void setPublishWebSite(boolean publishWebSite) {
		this.publishWebSite = publishWebSite;
	}

	/**
	 * Gets the publishing options.
	 * 
	 * @return the publishing options that will be used to publish the process
	 *         web site
	 */
	public PublishOptions getPublishingOptions() {
		return publishingOptions;
	}

	/**
	 * Sets the publishing options.
	 * 
	 * @param publishingOptions
	 *            the publishing options that will be used to publish the
	 *            process web site
	 */
	public void setPublishingOptions(PublishOptions publishingOptions) {
		this.publishingOptions = publishingOptions;
	}

	/**
	 * Gets the export only planned WBS elements option.
	 * 
	 * @return <code>true</code> if only planned WBS elements should be
	 *         exported.
	 */
	public boolean getExportOnlyPlannedWBSElements() {
		return exportOnlyPlannedWBSElements;
	}

	/**
	 * Sets the export only planned WBS elements option.
	 * 
	 * @param exportOnlyPlannedWBSElements
	 *            if <code>true</code>, export only planned WBS elements
	 */
	public void setExportOnlyPlannedWBSElements(
			boolean exportOnlyPlannedWBSElements) {
		this.exportOnlyPlannedWBSElements = exportOnlyPlannedWBSElements;
	}

}
