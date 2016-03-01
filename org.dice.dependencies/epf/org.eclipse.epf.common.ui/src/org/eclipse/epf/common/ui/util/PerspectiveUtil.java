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
package org.eclipse.epf.common.ui.util;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

/**
 * Utility class for accessing perspectives.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class PerspectiveUtil {

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private PerspectiveUtil() {
	}

	/**
	 * Returns the ID of the active perspective.
	 * 
	 * @return The active perspective ID.
	 */
	public static String getActivePerspectiveId() {
		if (PlatformUI.getWorkbench() == null || PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow() == null) {
			return null;
		}
		
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		if (activePage != null) {
			IPerspectiveDescriptor activePerspective = activePage
					.getPerspective();
			return activePerspective.getId();
		}
		return null;
	}

	/**
	 * Opens a perspective.
	 * 
	 * @param perspectiveId
	 *            The perspective ID.
	 * @return The previously active perspective.
	 */
	public static IPerspectiveDescriptor openPerspective(String perspectiveId) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		if (activePage != null) {
			IPerspectiveRegistry registry = PlatformUI.getWorkbench()
					.getPerspectiveRegistry();
			IPerspectiveDescriptor oldPerspective = activePage.getPerspective();
			if (!oldPerspective.getId().equals(perspectiveId)) {
				IPerspectiveDescriptor perspective = registry
						.findPerspectiveWithId(perspectiveId);
				activePage.setPerspective(perspective);
			}
			return oldPerspective;
		} else {
			IWorkbenchWindow activeWindow = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			try {
				PlatformUI.getWorkbench().showPerspective(
						perspectiveId, activeWindow);
			} catch (WorkbenchException e) {
			}
		}
		return null;
	}

	/**
	 * Returns true if the given perspective is already active.
	 * 
	 * @param perspectiveId
	 *            The perspective ID.
	 * @return
	 */
	public static boolean isActivePerspective(String perspectiveId) {
		String activePerspectiveId = PerspectiveUtil.getActivePerspectiveId();
		if (perspectiveId == null || activePerspectiveId == null) {
			return false;
		}
		return activePerspectiveId.equalsIgnoreCase(perspectiveId);
	}

	/**
	 * Returns the view part with the given view ID.
	 * 
	 * @param viewId
	 *            The view ID.
	 * @return The view part.
	 */
	public static IViewPart getView(String viewId) {
		try {
			IWorkbenchPage activePage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			if (activePage != null) {
				IViewPart view = activePage.findView(viewId);
				if (view == null) {
					view = activePage.showView(viewId);
				}
				return view;
			}
		} catch (Exception e) {
		}
		return null;
	}

}
