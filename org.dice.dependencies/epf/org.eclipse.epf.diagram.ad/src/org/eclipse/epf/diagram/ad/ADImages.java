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
//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.ad;

import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class ADImages {
	public static final String ELCL16 = "full/elcl16/"; //$NON-NLS-1$
	public static final String DLCL16 = "full/dlcl16/"; //$NON-NLS-1$
	public static final String ETOOL16 = "full/etool16/"; //$NON-NLS-1$
	public static final String DTOOL16 = "full/dtool16/"; //$NON-NLS-1$
	
	
	public static final String IMG_PATH_EDITOR = ETOOL16 + "ADEditor.gif"; //$NON-NLS-1$	

	public static final String DISABLED_IMG_PATH_EDITOR = DTOOL16 + "ADEditor.gif"; //$NON-NLS-1$	
	
	public static final ImageDescriptor DISABLED_IMG_DESC_EDITOR = ActivityDiagramEditorPlugin
			.getDefault().getImageDescriptor(DISABLED_IMG_PATH_EDITOR);		
	
	public static final ImageDescriptor IMG_DESC_EDITOR = ActivityDiagramEditorPlugin
			.getDefault().getImageDescriptor(IMG_PATH_EDITOR);		

}
