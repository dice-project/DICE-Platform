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
package org.eclipse.epf.library.edit.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger;

/**
 * Default messenger that will deliver messages using the messenger of
 * {@link ExtensionManager#getDefaultUserInteractionHandler() default user
 * interaction handler} if one exists. Otherwise, it will log the messages using
 * plugin's logger.
 * 
 * @author Phong Nguyen Le - Oct 25, 2006
 * @since 1.0
 */
public class Messenger implements IUserInteractionHandler.IMessenger {
	public static final Messenger INSTANCE = new Messenger();

	private static final String NEW_LINE = System.getProperty(
			"line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

	private IMessenger delegate;

	private Logger logger = LibraryEditPlugin.getDefault().getLogger();

	private Messenger() {
		IUserInteractionHandler uiHandler = ExtensionManager
				.getDefaultUserInteractionHandler();
		if (uiHandler != null) {
			delegate = uiHandler.getMessenger();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
	 *      java.lang.String)
	 */
	public void showError(String title, String msg) {
		if (delegate != null) {
			delegate.showError(title, msg);
		} else {
			StringBuffer strBuf = new StringBuffer(title);
			if(!StrUtil.isBlank(msg)) {
				strBuf.append(NEW_LINE).append(msg);
			}
			logger.logError(strBuf.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
	 *      java.lang.String, org.eclipse.core.runtime.IStatus)
	 */
	public void showError(String title, String msg, IStatus status) {
		if (delegate != null) {
			delegate.showError(title, msg, status);
		} else {
			LibraryEditPlugin.getDefault().getLog().log(status);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Exception)
	 */
	public void showError(String title, String msg, String reason,
			Exception exception) {
		if (delegate != null) {
			delegate.showError(title, msg, reason, exception);
		} else {
			StringBuffer strBuf = new StringBuffer(title);
			if(!StrUtil.isBlank(msg)) {
				strBuf.append(NEW_LINE).append(msg);
			}
			logger.logError(strBuf.toString(), exception);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showWarning(java.lang.String,
	 *      java.lang.String)
	 */
	public void showWarning(String title, String msg) {
		if (delegate != null) {
			delegate.showWarning(title, msg);
		} else {
			StringBuffer strBuf = new StringBuffer(title);
			if(!StrUtil.isBlank(msg)) {
				strBuf.append(NEW_LINE).append(msg);
			}
			logger.logWarning(strBuf.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showWarning(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void showWarning(String title, String msg, String reason) {
		if (delegate != null) {
			delegate.showWarning(title, msg, reason);
		} else {
			StringBuffer strBuf = new StringBuffer(title);
			if(!StrUtil.isBlank(msg)) {
				strBuf.append(NEW_LINE).append(msg);
			}
			if(!StrUtil.isBlank(reason)) {
				strBuf.append(NEW_LINE).append(reason);
			}
			logger.logWarning(strBuf.toString());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showInfo(java.lang.String, java.lang.String)
	 */
	public void showInfo(String title, String msg) {
		if (delegate != null) {
			delegate.showInfo(title, msg);
		} else {
			StringBuffer strBuf = new StringBuffer(title);
			if(!StrUtil.isBlank(msg)) {
				strBuf.append(NEW_LINE).append(msg);
			}
			logger.logInfo(strBuf.toString());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.IUserInteractionHandler.IMessenger#showError(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Exception)
	 */
	public void showError(String title, String msg, String reason, String details, Exception exception) {
		if (delegate != null) {
			delegate.showInfo(title, msg);
		} else {
			StringBuffer strBuf = new StringBuffer(title);
			if(!StrUtil.isBlank(msg)) {
				strBuf.append(NEW_LINE).append(msg);
			}
			if(!StrUtil.isBlank(reason)) {
				strBuf.append(NEW_LINE).append(reason);
			}
			if(!StrUtil.isBlank(details)) {
				strBuf.append(NEW_LINE).append(details);
			}
			logger.logError(strBuf.toString(), exception);
		}
	}

}
