//------------------------------------------------------------------------------
// Copyright (c) 2005, 2009 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.core.preferences;

import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author Phong Nguyen Le
 * @since 1.5.0.4
 */
public class DiagramPreferences {
	private static IPreferenceStore prefStore = LibraryUIPlugin.getDefault()
			.getPreferenceStore();
	
	public static final String AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS = "AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS"; //$NON-NLS-1$
	public static final boolean DEFAULT_AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS = false;

	static {
		prefStore.setDefault(DiagramPreferences.AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS, DiagramPreferences.DEFAULT_AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS);
	}

	public static boolean getDefaultADAllowConnectionToReadOnlyElements() {
		return DEFAULT_AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS;
	}

	public static boolean getADAllowConnectionToReadOnlyElements() {
		return prefStore.getBoolean(AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS);
	}

	public static void setADAllowConnectionToReadOnlyElements(boolean b) {
		prefStore.setValue(AD_ALLOW_CONNECTION_TO_READ_ONLY_ELEMENTS, b);
	}
}
