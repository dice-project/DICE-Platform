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
package org.eclipse.epf.diagram.add;

import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class ADDImages {
	public static final String ELCL16 = "full/elcl16/"; //$NON-NLS-1$
	public static final String DLCL16 = "full/dlcl16/"; //$NON-NLS-1$
	public static final String ETOOL16 = "full/etool16/"; //$NON-NLS-1$
	public static final String DTOOL16 = "full/dtool16/"; //$NON-NLS-1$
	
	
	public static final String IMG_PATH_RESET_DIAGRAM_LAYOUT = ELCL16 + "reset_diagram.gif"; //$NON-NLS-1$	
	public static final String IMG_PATH_EDITOR = ETOOL16 + "ADDEditor.gif"; //$NON-NLS-1$	

	public static final String DISABLED_IMG_PATH_RESET_DIAGRAM_LAYOUT = DLCL16 + "reset_diagram.gif"; //$NON-NLS-1$
	public static final String DISABLED_IMG_PATH_EDITOR = DTOOL16 + "ADDEditor.gif"; //$NON-NLS-1$	
	
	public static final ImageDescriptor DISABLED_IMG_DESC_RESET_DIAGRAM_LAYOUT = ActivityDetailDiagramEditorPlugin
			.getDefault().getImageDescriptor(
					DISABLED_IMG_PATH_RESET_DIAGRAM_LAYOUT);
	public static final ImageDescriptor DISABLED_IMG_DESC_EDITOR = ActivityDetailDiagramEditorPlugin
			.getDefault().getImageDescriptor(DISABLED_IMG_PATH_EDITOR);		
	
	public static final ImageDescriptor IMG_DESC_RESET_DIAGRAM_LAYOUT = ActivityDetailDiagramEditorPlugin
			.getDefault().getImageDescriptor(IMG_PATH_RESET_DIAGRAM_LAYOUT);
	public static final ImageDescriptor IMG_DESC_EDITOR = ActivityDetailDiagramEditorPlugin
			.getDefault().getImageDescriptor(IMG_PATH_EDITOR);		

}
