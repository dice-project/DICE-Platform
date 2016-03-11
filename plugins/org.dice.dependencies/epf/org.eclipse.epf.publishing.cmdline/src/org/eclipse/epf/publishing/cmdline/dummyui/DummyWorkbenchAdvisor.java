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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.statushandlers.AbstractStatusHandler;

public class DummyWorkbenchAdvisor extends WorkbenchAdvisor {

	public DummyWorkbenchAdvisor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getInitialWindowPerspectiveId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createWindowContents(IWorkbenchWindowConfigurer configurer, Shell shell) {
		// TODO Auto-generated method stub
		super.createWindowContents(configurer, shell);
	}

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
//		return super.createWorkbenchWindowAdvisor(configurer);
		return new DummyWorkbenchWindowAdvisor(configurer);
	}

	@Override
	public void eventLoopException(Throwable exception) {
		// TODO Auto-generated method stub
		exception.printStackTrace();
	}

	@Override
	public void eventLoopIdle(Display display) {
		// TODO Auto-generated method stub
//		super.eventLoopIdle(display);
	}

	@Override
	public void fillActionBars(IWorkbenchWindow window, IActionBarConfigurer configurer, int flags) {
		// TODO Auto-generated method stub
//		super.fillActionBars(window, configurer, flags);
	}

	@Override
	public IAdaptable getDefaultPageInput() {
		// TODO Auto-generated method stub
//		return super.getDefaultPageInput();
		return null;
	}

	@Override
	public String getMainPreferencePageId() {
		// TODO Auto-generated method stub
//		return super.getMainPreferencePageId();
		return null;
	}

	@Override
	protected IWorkbenchConfigurer getWorkbenchConfigurer() {
//		return null;
		// TODO Auto-generated method stub
		return new DummyWorkbenchConfigurer();
	}

	@Override
	public AbstractStatusHandler getWorkbenchErrorHandler() {
		// TODO Auto-generated method stub
//		return super.getWorkbenchErrorHandler();
		return null;
	}

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		// TODO Auto-generated method stub
		super.initialize(configurer);
	}

	@Override
	public boolean isApplicationMenu(IWorkbenchWindowConfigurer configurer, String menuId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openIntro(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
//		super.openIntro(configurer);
	}

	@Override
	public boolean openWindows() {
		// TODO Auto-generated method stub
		return super.openWindows();
//		return false;
	}

	@Override
	public void postShutdown() {
		// TODO Auto-generated method stub
//		super.postShutdown();
	}

	@Override
	public void postStartup() {
		// TODO Auto-generated method stub
//		super.postStartup();
	}

	@Override
	public void postWindowClose(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
//		super.postWindowClose(configurer);
	}

	@Override
	public void postWindowCreate(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
//		super.postWindowCreate(configurer);
	}

	@Override
	public void postWindowOpen(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
//		super.postWindowOpen(configurer);
	}

	@Override
	public void postWindowRestore(IWorkbenchWindowConfigurer configurer) throws WorkbenchException {
		// TODO Auto-generated method stub
//		super.postWindowRestore(configurer);
	}

	@Override
	public boolean preShutdown() {
		return false;
		// TODO Auto-generated method stub
//		return super.preShutdown();
	}

	@Override
	public void preStartup() {
		// TODO Auto-generated method stub
//		super.preStartup();
	}

	@Override
	public void preWindowOpen(IWorkbenchWindowConfigurer configurer) {
		// TODO Auto-generated method stub
//		super.preWindowOpen(configurer);
	}

	@Override
	public boolean preWindowShellClose(IWorkbenchWindowConfigurer configurer) {
		return false;
		// TODO Auto-generated method stub
//		return super.preWindowShellClose(configurer);
	}

	@Override
	public IStatus restoreState(IMemento memento) {
		// TODO Auto-generated method stub
//		return super.restoreState(memento);
		return null;
	}

	@Override
	public IStatus saveState(IMemento memento) {
		// TODO Auto-generated method stub
//		return super.saveState(memento);
		return null;
	}

}
