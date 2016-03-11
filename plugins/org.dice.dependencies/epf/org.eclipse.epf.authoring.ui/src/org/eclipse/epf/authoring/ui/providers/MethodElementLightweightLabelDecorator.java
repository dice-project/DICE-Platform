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
package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.util.LibraryValidationMarkerHelper;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.authoring.ui.views.LibraryViewExtender;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;

/**
 * @author Phong Nguyen Le
 * @since  1.1
 */
public class MethodElementLightweightLabelDecorator implements
		ILightweightLabelDecorator {
	private static ImageDescriptor overlay = null;
	

	public static final String DECORATOR_ID = "org.eclipse.epf.authoring.ui.providers.MethodElementLightweightLabelDecorator"; //$NON-NLS-1$
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 *      org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object element, IDecoration decoration) {
		try {
			if(LibraryValidationMarkerHelper.isInvalid(element)
//					|| MethodRichTextMarkerHelper.isInvalid(element)
					) {
				decoration.addOverlay(getErrorOverlay());
			}
		}
		catch(Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		LibraryView libaryView = LibraryView.getViewInstance();
		LibraryViewExtender extender = libaryView == null ? null : libaryView.getExtender();
		if (extender != null) {
			extender.decorate(element, decoration);
		}
	}	

	/**
	 * @return
	 */
	private static ImageDescriptor getErrorOverlay() {
		if(overlay == null) {
			overlay = AuthoringUIPlugin.getDefault().getImageDescriptor("full/ovr16/error_ovr.gif"); //$NON-NLS-1$
		}
		return overlay;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
