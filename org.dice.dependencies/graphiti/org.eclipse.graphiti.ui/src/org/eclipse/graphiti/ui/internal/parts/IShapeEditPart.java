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
/*

 */
package org.eclipse.graphiti.ui.internal.parts;

import org.eclipse.gef.EditPart;

/**
 * Window - Preferences - Java - Code Style - Code Templates.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IShapeEditPart extends IAnchorContainerEditPart {

	/**
	 * Delete child and refresh.
	 * 
	 * @param childEditPart
	 *            the child edit part
	 */
	void deleteChildAndRefresh(EditPart childEditPart);

	/**
	 * 
	 */
	void refreshDecorators();
}