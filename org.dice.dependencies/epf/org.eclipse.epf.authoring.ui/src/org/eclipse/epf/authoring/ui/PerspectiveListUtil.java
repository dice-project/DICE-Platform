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

import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.ui.IPageLayout;

/**
 * Helper class for the Authoring and Browsing perspectives.
 * 
 * @author Bingxue Xu
 * @since 1.0
 */
public class PerspectiveListUtil {

	/**
	 * Add the Browsing and Authoring perspective shortcuts to the given page
	 * layout.
	 */
	public static void addPerspectiveShortList(IPageLayout layout) {
		layout.addPerspectiveShortcut(BrowsingPerspective.PERSPECTIVE_ID);
		layout.addPerspectiveShortcut(AuthoringPerspective.PERSPECTIVE_ID);
	}

	/**
	 * Returns <code>true</code> if the current perspective is the Authoring
	 * perspective.
	 */
	public static boolean isAuthoringPerspective() {
		String activePerspectiveId = PerspectiveUtil.getActivePerspectiveId();
		if (activePerspectiveId == null) {
			return false;
		} else {
			return activePerspectiveId
					.equalsIgnoreCase(AuthoringPerspective.PERSPECTIVE_ID);
		}
	}

	/**
	 * Returns <code>true</code> if the current perspective is the Browsing
	 * perspective.
	 */
	public static boolean isBrowsingPerspective() {
		String activePerspectiveId = PerspectiveUtil.getActivePerspectiveId();
		if (activePerspectiveId == null) {
			return false;
		} else {
			return activePerspectiveId
					.equalsIgnoreCase(BrowsingPerspective.PERSPECTIVE_ID);
		}
	}

}
