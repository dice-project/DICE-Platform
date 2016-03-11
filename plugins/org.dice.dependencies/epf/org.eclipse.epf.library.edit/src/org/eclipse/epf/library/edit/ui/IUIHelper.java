//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.ui;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.util.ExposedAdapterFactory;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.WorkProduct;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public interface IUIHelper {
	public void runSafely(Runnable runnable, boolean synchronous);
	
	public void runWithBusyIndicator(Runnable runnable);
	
	public boolean runWithProgress(IRunnableWithProgress runnable, boolean canCancel, String msg);

	public IStatus runAsJob(IRunnableWithProgress runnable, String taskName,
			Object shell);

	public boolean runInUI(IRunnableWithProgress runnable,
			ISchedulingRule rule, Object shell);
	
	public void runInUI(IRunnableWithProgress runnable,
			String taskName);

	IStatus runInModalContext(IRunnableWithProgress operation, boolean fork,
			IProgressMonitor monitor, Object uiContext);

	public TeamProfile getTeam(Activity activity, Role role, Object UIContext);

	/**
	 * Select tasks which has given workproduct as output.
	 * 
	 * @param taskList
	 * @param wp
	 * @return
	 */
	public List selectTasks(List taskList, WorkProduct wp);

	/**
	 * Select responsible work products for the given role
	 * 
	 * @param wpList
	 * @param role
	 * @return
	 */
	public List selectWorkProducts(List wpList, Role role);

	public boolean refreshNeeded(AdapterFactory adapterFactory,
			BSActivityItemProvider itemProvider);

	public void refreshViewer(AdapterFactory factory, Process proc);
	
	public void refreshAllViewers(final ExposedAdapterFactory adapterFactory);

	public void refreshIDsInViewer(ExposedAdapterFactory adapterFactory);
	
	public Object getViewer(AdapterFactory adapterFactory, Process proc);

}
