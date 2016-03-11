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
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class DummyWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public DummyWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		// TODO Auto-generated method stub
		return super.createActionBarAdvisor(configurer);
	}

	@Override
	public Control createEmptyWindowContents(Composite parent) {
		// TODO Auto-generated method stub
		return super.createEmptyWindowContents(parent);
	}

	@Override
	public void createWindowContents(Shell shell) {
		// TODO Auto-generated method stub
		// super.createWindowContents(shell);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	protected IWorkbenchWindowConfigurer getWindowConfigurer() {
		// TODO Auto-generated method stub
		return super.getWindowConfigurer();
	}

	@Override
	public void openIntro() {
		// TODO Auto-generated method stub
		// super.openIntro();
	}

	@Override
	public void postWindowClose() {
		// TODO Auto-generated method stub
		super.postWindowClose();
	}

	@Override
	public void postWindowCreate() {
		// TODO Auto-generated method stub
		super.postWindowCreate();
	}

	@Override
	public void postWindowOpen() {
		// TODO Auto-generated method stub
		super.postWindowOpen();
	}

	@Override
	public void postWindowRestore() throws WorkbenchException {
		// TODO Auto-generated method stub
		super.postWindowRestore();
	}

	@Override
	public void preWindowOpen() {
		// TODO Auto-generated method stub
		super.preWindowOpen();
	}

	@Override
	public boolean preWindowShellClose() {
		// TODO Auto-generated method stub
		return super.preWindowShellClose();
	}

	@Override
	public IStatus restoreState(IMemento memento) {
		// TODO Auto-generated method stub
		return super.restoreState(memento);
	}

	@Override
	public IStatus saveState(IMemento memento) {
		// TODO Auto-generated method stub
		return super.saveState(memento);
	}

}
