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

import org.eclipse.epf.authoring.ui.views.ConfigurationView;
import org.eclipse.epf.authoring.ui.views.ContentView;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * The Method Browsing perspective.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class BrowsingPerspective implements IPerspectiveFactory {

	/**
	 * The perspective ID.
	 */
	public static final String PERSPECTIVE_ID = BrowsingPerspective.class
			.getName();

	/**
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		layout.addView(ConfigurationView.VIEW_ID, IPageLayout.LEFT, 0.30f,
				editorArea);
		PerspectiveListUtil.addPerspectiveShortList(layout);
		layout.addView(ContentView.VIEW_ID, IPageLayout.RIGHT, 0.70f, editorArea);
		IPlaceholderFolderLayout rightBottom = layout.createPlaceholderFolder("rightBottom", //$NON-NLS-1$
				IPageLayout.BOTTOM, 0.70f, ContentView.VIEW_ID);
		rightBottom.addPlaceholder(IPageLayout.ID_PROBLEM_VIEW);
	}

	/**
	 * Opens this perspective.
	 */
	public static void open() {
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			if (window == null) {
				IWorkbenchWindow[] windows = PlatformUI.getWorkbench()
						.getWorkbenchWindows();
				if (windows != null && windows.length > 0) {
					window = windows[0];
				}
			}

			if (window != null) {
				PlatformUI.getWorkbench().showPerspective(PERSPECTIVE_ID,
						window);
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(
					"Failed to open browsing perspective", e); //$NON-NLS-1$
		}
	}

}
