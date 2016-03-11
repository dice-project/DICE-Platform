//------------------------------------------------------------------------------
// Copyright (c) 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------

package org.eclipse.epf.library.diagram.providers;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.graphics.Image;

/**
 * This interface is used to implement an icon provider for the diagrams.
 * 
 * @author Pierre Padovani
 * 
 */
public interface IDiagramIconProvider extends IExecutableExtension {

	/**
	 * This required method, should return an image for the supplied method
	 * element, or null if not available. A boolean indicating the size small
	 * (16x16) or large (32x32) of the icon must be honored as well.
	 * 
	 * @param me
	 *            MethodElement
	 * @param smallIcon
	 *            boolean
	 * @return Image
	 */
	public Image getImageForElement(MethodElement me, boolean smallIcon);

}
