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
package org.eclipse.epf.authoring.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Shared images used in the Authoring UI.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class AuthoringUIImages {

	public static final String IMG_NAME_COLLAPSED = "full/obj16/Collapsed.gif"; //$NON-NLS-1$ 	

	public static final String IMG_NAME_EXPANDED = "full/obj16/Expanded.gif"; //$NON-NLS-1$

	public static final String IMG_NAME_REFRESH = "full/etool16/Refresh.gif"; //$NON-NLS-1$ 	

	public static final String IMG_NAME_STOP = "full/etool16/Stop.gif"; //$NON-NLS-1$

	public static final String IMG_NAME_PRINT = "full/etool16/Print.gif"; //$NON-NLS-1$
	
	public static final String IMG_NAME_OVR_ERROR = "full/ovr16/error_ovr.gif"; //$NON-NLS-1$
	
	public static final String IMG_NAME_LAYOUT_FLAT = "full/etool16/flatLayout.gif"; //$NON-NLS-1$

	public static final String IMG_NAME_LAYOUT_HIERARCHICAL = "full/etool16/hierarchicalLayout.gif"; //$NON-NLS-1$

	public static final ImageDescriptor IMG_DESC_COLLAPSED = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_COLLAPSED);

	public static final ImageDescriptor IMG_DESC_EXPANDED = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_EXPANDED);

	public static final ImageDescriptor IMG_DESC_REFRESH = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_REFRESH);

	public static final ImageDescriptor IMG_DESC_STOP = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_STOP);

	public static final ImageDescriptor IMG_DESC_PRINT = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_PRINT);
	
	public static final ImageDescriptor IMG_DESC_OVR_ERROR = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_OVR_ERROR);

	public static final ImageDescriptor IMG_DESC_LAYOUT_FLAT = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_LAYOUT_FLAT);
	
	public static final ImageDescriptor IMG_DESC_LAYOUT_HIERARCHICAL = AuthoringUIPlugin
			.getDefault().getImageDescriptor(IMG_NAME_LAYOUT_HIERARCHICAL);

	public static final Image IMG_BACK = PlatformUI.getWorkbench()
			.getSharedImages().getImage(ISharedImages.IMG_TOOL_BACK);

	public static final Image IMG_COLLAPSED = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_COLLAPSED);

	public static final Image IMG_EXPANDED = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_EXPANDED);

	public static final Image IMG_FORWARD = PlatformUI.getWorkbench()
			.getSharedImages().getImage(ISharedImages.IMG_TOOL_FORWARD);

	public static final Image IMG_REFRESH = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_REFRESH);

	public static final Image IMG_STOP = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_STOP);

	public static final Image IMG_PRINT = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_PRINT);
	
	public static final Image IMG_OVR_ERROR = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_OVR_ERROR);

	public static final Image IMG_LAYOUT_FLAT = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_LAYOUT_FLAT);

	public static final Image IMG_LAYOUT_HIERARCHICAL = AuthoringUIPlugin.getDefault()
			.getSharedImage(IMG_NAME_LAYOUT_HIERARCHICAL);

}
