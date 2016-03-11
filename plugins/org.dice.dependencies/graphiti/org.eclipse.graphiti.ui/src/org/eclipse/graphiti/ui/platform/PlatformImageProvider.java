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
package org.eclipse.graphiti.ui.platform;

import org.eclipse.graphiti.platform.IPlatformImageConstants;

/**
 * The Class PlatformImageProvider.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PlatformImageProvider extends AbstractImageProvider {

	private static final String ROOT_FOLDER_FOR_IMG = "icons/"; //$NON-NLS-1$

	public final static String ID = "org.eclipse.graphiti.ui.platform.PlatformImageProvider"; //$NON-NLS-1$

	/**
	 * Creates a new {@link PlatformImageProvider}.
	 */
	public PlatformImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_COLLAPSE, ROOT_FOLDER_FOR_IMG + "edit/collapse.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_COLLAPSEALL, ROOT_FOLDER_FOR_IMG + "edit/collapseall.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_EXPAND, ROOT_FOLDER_FOR_IMG + "edit/expand.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_EXPANDALL, ROOT_FOLDER_FOR_IMG + "edit/expandall.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_REFRESH, ROOT_FOLDER_FOR_IMG + "edit/refresh.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_DELETE, ROOT_FOLDER_FOR_IMG + "edit/delete.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_EDIT_REMOVE, ROOT_FOLDER_FOR_IMG + "edit/remove.gif"); //$NON-NLS-1$

		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_ERROR, ROOT_FOLDER_FOR_IMG + "eclipse/error.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_ERROR_TSK, ROOT_FOLDER_FOR_IMG + "eclipse/error_tsk.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_INFORMATION, ROOT_FOLDER_FOR_IMG + "eclipse/information.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_INFORMATION_TSK, ROOT_FOLDER_FOR_IMG + "eclipse/info_tsk.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_QUICKASSIST, ROOT_FOLDER_FOR_IMG + "eclipse/quickassist.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_WARNING, ROOT_FOLDER_FOR_IMG + "eclipse/warning.gif"); //$NON-NLS-1$
		addImageFilePath(IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK, ROOT_FOLDER_FOR_IMG + "eclipse/warn_tsk.gif"); //$NON-NLS-1$

		addImageFilePath(IPlatformImageConstants.IMG_DIAGRAM, ROOT_FOLDER_FOR_IMG + "diagram.gif"); //$NON-NLS-1$
	}
}
