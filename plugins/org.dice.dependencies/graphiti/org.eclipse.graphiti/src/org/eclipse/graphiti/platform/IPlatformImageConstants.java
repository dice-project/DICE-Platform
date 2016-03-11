/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.platform;

/**
 * This interface provides predefined image constants which can be used by the
 * features for image graphics algorithm.
 */
public interface IPlatformImageConstants {

	/**
	 * The Constant PRE.
	 */
	public static final String PRE = "org.eclipse.graphiti."; //$NON-NLS-1$

	/**
	 * The Constant IMG_MISSING.
	 */
	public static final String IMG_MISSING = PRE + "missing"; //$NON-NLS-1$

	/**
	 * The Constant IMG_EDIT_EXPANDALL.
	 */
	public static final String IMG_EDIT_EXPAND = PRE + "edit.expand"; //$NON-NLS-1$

	/**
	 * The Constant IMG_EDIT_EXPANDALL.
	 */
	public static final String IMG_EDIT_EXPANDALL = PRE + "edit.expandall"; //$NON-NLS-1$

	/**
	 * The Constant IMG_EDIT_COLLAPSEALL.
	 */
	public static final String IMG_EDIT_COLLAPSE = PRE + "edit.collapse"; //$NON-NLS-1$
	/**
	 * The Constant IMG_EDIT_COLLAPSEALL.
	 */
	public static final String IMG_EDIT_COLLAPSEALL = PRE + "edit.collapseall"; //$NON-NLS-1$

	/**
	 * The Constant IMG_EDIT_REFRESH.
	 */
	public static final String IMG_EDIT_REFRESH = PRE + "edit.refresh"; //$NON-NLS-1$

	/**
	 * The Constant IMG_EDIT_DELETE.
	 */
	public static final String IMG_EDIT_DELETE = PRE + "edit.delete"; //$NON-NLS-1$

	/**
	 * The Constant IMG_EDIT_REMOVE.
	 */
	public static final String IMG_EDIT_REMOVE = PRE + "edit.remove"; //$NON-NLS-1$

	/**
	 * The Constant IMG_DIAGRAM.
	 */
	public static final String IMG_DIAGRAM = PRE + "diagram"; //$NON-NLS-1$
	//
	// eclipse
	//
	/**
	 * The Constant IMG_ECLIPSE_WARNING.
	 */
	public static final String IMG_ECLIPSE_WARNING = PRE + "eclipse.warning"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ECLIPSE_WARNING_TSK.
	 */
	public static final String IMG_ECLIPSE_WARNING_TSK = PRE + "eclipse.warning.tsk"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ECLIPSE_ERROR.
	 */
	public static final String IMG_ECLIPSE_ERROR = PRE + "eclipse.error"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ECLIPSE_ERROR_TSK.
	 */
	public static final String IMG_ECLIPSE_ERROR_TSK = PRE + "eclipse.error.tsk"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ECLIPSE_INFORMATION.
	 */
	public static final String IMG_ECLIPSE_INFORMATION = PRE + "eclipse.information"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ECLIPSE_INFORMATION_TSK.
	 */
	public static final String IMG_ECLIPSE_INFORMATION_TSK = PRE + "eclipse.information.tsk"; //$NON-NLS-1$

	/**
	 * The Constant IMG_ECLIPSE_QUICKASSIST.
	 */
	public static final String IMG_ECLIPSE_QUICKASSIST = PRE + "eclipse.quickassist"; //$NON-NLS-1$

}
