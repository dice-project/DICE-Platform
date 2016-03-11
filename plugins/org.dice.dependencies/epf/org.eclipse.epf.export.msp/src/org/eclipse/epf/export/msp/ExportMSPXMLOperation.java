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
package org.eclipse.epf.export.msp;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.publishing.services.AbstractPublishManager;

/**
 * The Export Microsoft Project XML file operation.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportMSPXMLOperation implements IRunnableWithProgress {

	private AbstractPublishManager publishMgr;

	private String published_url;

	private Exception exception;

	/**
	 * Creates a new instance.
	 */
	public ExportMSPXMLOperation(AbstractPublishManager publishMgr) {
		super();
		this.publishMgr = publishMgr;
	}

	/**
	 * Returns the published URL.
	 */
	public String getPublishedUrl() {
		return published_url;
	}

	/**
	 * Returns the exception, if any, that occurred while running the operation.
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		exception = null;
		monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
		monitor.setTaskName(ExportMSPResources.exportMSPTask_name);
		try {
			publishMgr.publish(monitor);
			published_url = publishMgr.getPublishedUrl();
		} catch (Exception e) {
			exception = e;
		} finally {
			monitor.done();
			publishMgr = null;
		}
	}

}