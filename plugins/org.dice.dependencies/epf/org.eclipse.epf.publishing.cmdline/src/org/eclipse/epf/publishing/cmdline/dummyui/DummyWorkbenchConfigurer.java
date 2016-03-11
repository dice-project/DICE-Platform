/*******************************************************************************
 * Copyright (c) 2008 TietoEnator, corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Roman Smirak  - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.epf.publishing.cmdline.dummyui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.WindowManager;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;

public class DummyWorkbenchConfigurer implements IWorkbenchConfigurer {

	public DummyWorkbenchConfigurer() {
		// TODO Auto-generated constructor stub
	}

	public void declareImage(String symbolicName, ImageDescriptor descriptor, boolean shared) {
		// TODO Auto-generated method stub

	}

	public void emergencyClose() {
		// TODO Auto-generated method stub

	}

	public boolean emergencyClosing() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getData(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getExitOnLastWindowClose() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getSaveAndRestore() {
		// TODO Auto-generated method stub
		return false;
	}

	public IWorkbenchWindowConfigurer getWindowConfigurer(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		return null;
	}

	public IWorkbench getWorkbench() {
		return PlatformUI.getWorkbench();
	}

	public WindowManager getWorkbenchWindowManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void openFirstTimeWindow() {
		// TODO Auto-generated method stub

	}

	public IStatus restoreState() {
		// TODO Auto-generated method stub
		return new IStatus() {

			public IStatus[] getChildren() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getCode() {
				// TODO Auto-generated method stub
				return OK;
			}

			public Throwable getException() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getMessage() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getPlugin() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getSeverity() {
				// TODO Auto-generated method stub
				return 0;
			}

			public boolean isMultiStatus() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean isOK() {
				// TODO Auto-generated method stub
				return true;
			}

			public boolean matches(int severityMask) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

	public IWorkbenchWindowConfigurer restoreWorkbenchWindow(IMemento memento) throws WorkbenchException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setData(String key, Object data) {
		// TODO Auto-generated method stub

	}

	public void setExitOnLastWindowClose(boolean enabled) {
		// TODO Auto-generated method stub

	}

	public void setSaveAndRestore(boolean enabled) {
		// TODO Auto-generated method stub

	}

}
