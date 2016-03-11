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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.util.ExposedAdapterFactory;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class UIHelper implements IUIHelper {
	/**
	 * Get teams in scope
	 * 
	 * @param adapterFactory
	 * @param e
	 * @param role
	 * @param teamList
	 */
	private static void getTeamsInScope(AdapterFactory adapterFactory,
			BreakdownElement e, Role role, List teamList) {
		// get children for activity
		ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(e, ITreeItemContentProvider.class);
		Collection children = itemProvider.getChildren(e);
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof TeamProfile) {
				TeamProfile team = (TeamProfile) obj;
				List allTeams = new ArrayList();
				// get all sub teams as well
				ProcessUtil.getAllSubTeams(team, allTeams);

				for (Iterator teamItor = allTeams.iterator(); teamItor
						.hasNext();) {
					Object o = teamItor.next();
					if (o instanceof TeamProfile) {
						// get roles from teams
						List roles = ProcessUtil.getRoles(((TeamProfile) o)
								.getTeamRoles());
						if (roles.contains(role)) {
							teamList.add(o);
						}
					}
				}
			}
		}

		// get parent
		Object currentParent = itemProvider.getParent(e);
		if (currentParent != null) {
			// go up
			getTeamsInScope(adapterFactory, (BreakdownElement) currentParent,
					role, teamList);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.util.IUIHelper#getTeam(org.eclipse.epf.uma.Activity, org.eclipse.epf.uma.Role, java.lang.Object)
	 */
	public TeamProfile getTeam(Activity activity, Role role, Object UIContext) {
		List teamList = new ArrayList();
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		// find out all team in visible scope
		getTeamsInScope(adapterFactory, activity, role, teamList);
		if (teamList.size() == 1) {
			return (TeamProfile) teamList.get(0);
		}
		if (teamList.size() > 1) {
			return TeamSelection.getSelectedTeam(teamList, role,
					UIContext instanceof Shell ? (Shell) UIContext : null);
		}
		// there are no teams to assign
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.util.IUIHelper#runInModalContext(org.eclipse.epf.library.edit.util.IRunnableWithProgress, boolean, org.eclipse.core.runtime.IProgressMonitor, java.lang.Object)
	 */
	public IStatus runInModalContext(final IRunnableWithProgress operation,
			boolean fork, IProgressMonitor monitor, Object uiContext) {
		org.eclipse.jface.operation.IRunnableWithProgress op = new org.eclipse.jface.operation.IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				operation.run(monitor);
			}
			
		};
		try {
			ModalContext.run(op, true, monitor, ((Shell)uiContext)
					.getDisplay());
			return Status.OK_STATUS;
		} catch (Exception e) {
			LibraryEditPlugin.INSTANCE.log(e);
			Throwable ex;
			if (e instanceof InvocationTargetException) {
				ex = ((InvocationTargetException) e)
						.getTargetException();
			} else {
				ex = e;
			}
			String msg = TngUtil.toStackTraceString(ex);
			return new Status(IStatus.ERROR,
					LibraryEditPlugin.INSTANCE.getSymbolicName(), 0,
					msg, ex);
		}

	}

	public List selectTasks(List taskList, WorkProduct wp) {
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public String getColumnText(Object obj, int column) {
				if (obj instanceof MethodElement) {
					return TngUtil.getPresentationName(obj);
				}
				return super.getText(obj);
			}
		};

		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return ((List) object).toArray();
			}
		};

		try {
			ProcessListSelectionDialog dlg = new ProcessListSelectionDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getShell(),
					taskList,
					contentProvider,
					labelProvider,
					NLS
							.bind(
									LibraryEditResources.ui_UserInteractionHelper_wplistdlg_msg,
									wp.getName()));

			dlg.setTitle(LibraryEditResources.ui_UserInteractionHelper_tasks);
			dlg.setBlockOnOpen(true);
			dlg.open();
			Object[] objs = dlg.getResult();

			List selectedTasks = new ArrayList();
			if ((objs != null) && (objs.length > 0)) {
				for (int i = 0; i < objs.length; i++) {
					selectedTasks.add(objs[i]);
				}
			}
			return selectedTasks;
		} finally {
			// dispose
			labelProvider.dispose();
			contentProvider.dispose();
		}
	}

	public List selectWorkProducts(List wpList, Role role) {
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public String getColumnText(Object obj, int column) {
				if (obj instanceof MethodElement) {
					return TngUtil.getPresentationName(obj);
				}
				return super.getText(obj);
			}
		};

		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				return ((List) object).toArray();
			}
		};

		try {
			ProcessListSelectionDialog dlg = new ProcessListSelectionDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getShell(),
					// MsgBox.getDefaultShell(),
					wpList,
					contentProvider,
					labelProvider,
					NLS
							.bind(
									LibraryEditResources.ui_UserInteractionHelper_rolelistdlg_msg,
									role.getName()));

			dlg
					.setTitle(LibraryEditResources.ui_UserInteractionHelper_workproducts); 
			dlg.setBlockOnOpen(true);
			dlg.open();
			Object[] objs = dlg.getResult();

			List selectedWps = new ArrayList();
			if ((objs != null) && (objs.length > 0)) {
				for (int i = 0; i < objs.length; i++) {
					selectedWps.add(objs[i]);
				}
			}
			return selectedWps;
		} finally {
			// dispose
			labelProvider.dispose();
			contentProvider.dispose();
		}
	}
	
	private static class RunnableWithProgress implements org.eclipse.jface.operation.IRunnableWithProgress {
		private IRunnableWithProgress operation;

		public RunnableWithProgress(IRunnableWithProgress op) {
			this.operation = op;
		}

		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException {
			operation.run(monitor);
		}
		
	}

	public IStatus runAsJob(final IRunnableWithProgress runnable, String taskName,
			Object shell) {
		Job job = new WorkspaceJob(taskName) {

			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				try {
					// monitor.beginTask(taskName, IProgressMonitor.UNKNOWN);
					runnable.run(monitor);
					return Status.OK_STATUS;
				} catch (InvocationTargetException e) {
					Throwable ex;
					if (e.getCause() != null) {
						ex = e.getCause();
					} else {
						ex = e;
					}
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, ex.toString(), ex);
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, e.toString(), e);
				} finally {
					monitor.done();
				}
			}

		};
		PlatformUI.getWorkbench().getProgressService().showInDialog((Shell) shell, job);
		job.schedule();
		return job.getResult();
	}

	public boolean runInUI(IRunnableWithProgress runnable,
			ISchedulingRule rule, Object shell) {
		if (shell == null) {
			shell = LibraryEditPlugin.getDefault().getContext();
		}
		IRunnableContext context = new ProgressMonitorDialog((Shell) shell);
		try {
			PlatformUI.getWorkbench().getProgressService().runInUI(context,
					new RunnableWithProgress(runnable), rule);
			return true;
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
			String title = LibraryEditResources.errorDialog_title;
			ExtensionManager.getDefaultUserInteractionHandler().getMessenger()
				.showError(title, e.toString(), null, e);
//			LibraryEditPlugin.getDefault().getMsgDialog().displayError(title,
//					e.toString(), e);
		}
		return false;
	}

	public static boolean runWithProgress(final IRunnableWithProgress runnable,
			final IRunnableContext runnableContext, final boolean canCancel, final String msg) {
		final MultiStatus status = new MultiStatus(LibraryEditPlugin.INSTANCE
				.getSymbolicName(), IStatus.OK,
				LibraryEditResources.error_reason, null); 

		final org.eclipse.jface.operation.IRunnableWithProgress operation = new org.eclipse.jface.operation.IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				monitor.beginTask(msg, IProgressMonitor.UNKNOWN);
				monitor.subTask(""); //$NON-NLS-1$
				try {
					runnable.run(monitor);
				} catch (RuntimeException e) {
					String msg;
					if (e instanceof MessageException) {
						msg = e.getMessage();
					} else {
						StringWriter strWriter = new StringWriter();
						e.printStackTrace(new PrintWriter(strWriter));
						msg = strWriter.toString();
					}
					status.add(new Status(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(), 0,
							msg, e));
				} finally {
					monitor.done();
				}
			}

		};

		try {
			if (Display.getCurrent() == null) {
				// current thread is not a user-interface thread
				//
				Display.getDefault().syncExec(new Runnable() {

					public void run() {
						try {
							runnableContext.run(true, canCancel, operation);
						} catch (Exception e) {
							LibraryEditPlugin.getDefault().getLogger().logError(e);
						}
					}

				});
			} else {
				runnableContext.run(true, canCancel, operation);
			}

			if (!status.isOK()) {
				ExtensionManager.getDefaultUserInteractionHandler()
						.getMessenger().showError(
								LibraryEditResources.errorDialog_title,
								LibraryEditResources.error_msgWithDetails,
								status);
			} else {
				return true;
			}
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			//
			LibraryEditPlugin.getDefault().getLogger().logError(exception);
		}

		return false;
	}

	public boolean runWithProgress(IRunnableWithProgress runnable,
			boolean canCancel, String msg) {
		return runWithProgress(runnable, getRunnableContext(), false, msg);
	}

	public IRunnableContext getRunnableContext() {
		Shell shell;
		Object uiCtx = ExtensionManager.getDefaultUserInteractionHandler().getUIContext();
		if (uiCtx instanceof Shell) {
			shell = (Shell) uiCtx;
		} else {
			shell = MsgBox.getDefaultShell();
		}
		return new ProgressMonitorDialog(shell);
	}
	
	public static Viewer doGetViewer(AdapterFactory adapterFactory, Process proc) {
		if (adapterFactory instanceof ExposedAdapterFactory) {
			for (Iterator iter = Collections.unmodifiableList(
					((ExposedAdapterFactory) adapterFactory)
							.getChangeListeners()).iterator(); iter.hasNext();) {
				Object listener = iter.next();
				if (listener instanceof IContentProvider && listener instanceof IViewerProvider) {					
					Viewer viewer = ((IViewerProvider) listener).getViewer();
					if (viewer.getInput() instanceof ProcessComponent
							&& ((ProcessComponent) viewer.getInput())
									.getProcess() == proc)
						return viewer;
				}
			}
		}
		return null;
	}

	public boolean refreshNeeded(AdapterFactory adapterFactory,
			BSActivityItemProvider itemProvider) {
		Process process = (Process) itemProvider.getTopItem();
		if (process == null)
			return false;

		// check if the given process is currently opened in editor
		//
		Viewer viewer = doGetViewer(adapterFactory, process);
		if (viewer != null && viewer.getControl() != null
				&& !viewer.getControl().isDisposed()) {
			return true;
		}

		for (Iterator iter = TngUtil.getContributors(process); iter.hasNext();) {
			Object element = iter.next();
			BSActivityItemProvider adapter = (BSActivityItemProvider) adapterFactory
					.adapt(element, ITreeItemContentProvider.class);
			if (refreshNeeded(adapterFactory, adapter))
				return true;
		}

		for (Iterator iter = TngUtil.getGeneralizers(process,
				VariabilityType.EXTENDS); iter.hasNext();) {
			Object element = iter.next();
			BSActivityItemProvider adapter = (BSActivityItemProvider) adapterFactory
					.adapt(element, ITreeItemContentProvider.class);
			if (refreshNeeded(adapterFactory, adapter))
				return true;
		}

		return false;
	}

	public void refreshAllViewers(final ExposedAdapterFactory adapterFactory) {
		UserInteractionHelper.getUIHelper().runSafely(new Runnable() {

			public void run() {
				for (Iterator iter = Collections.unmodifiableList(
						adapterFactory.getChangeListeners()).iterator(); iter.hasNext();) {
					Object listener = iter.next();
					if (listener instanceof IContentProvider && listener instanceof IViewerProvider) {					
						Viewer viewer = ((IViewerProvider) listener).getViewer();
						if (viewer != null && viewer.getControl() != null
								&& !viewer.getControl().isDisposed()
								&& viewer.getInput() instanceof ProcessComponent) {
							viewer.refresh();
						}
					}
				}
			}
			
		}, true);
	}

	public void refreshViewer(AdapterFactory factory, Process proc) {
		final Viewer viewer = doGetViewer(factory, proc);
		if (viewer != null && viewer.getControl() != null
				&& !viewer.getControl().isDisposed()) {
			UserInteractionHelper.getUIHelper().runSafely(new Runnable() {

				public void run() {
					viewer.refresh();
				}
				
			}, true);
		}
	}

	public void refreshIDsInViewer(final ExposedAdapterFactory adapterFactory) {
		UserInteractionHelper.getUIHelper().runSafely(new Runnable() {

			public void run() {
				for (Iterator iter = Collections.unmodifiableList(
						adapterFactory.getChangeListeners()).iterator(); iter.hasNext();) {
					Object listener = iter.next();
					if (listener instanceof IContentProvider && listener instanceof IViewerProvider) {					
						Viewer viewer = ((IViewerProvider) listener).getViewer();
						if (viewer != null && viewer.getControl() != null
								&& !viewer.getControl().isDisposed()
								&& viewer.getInput() instanceof ProcessComponent) {
							Process proc = ((ProcessComponent) viewer.getInput())
									.getProcess();
							BSActivityItemProvider itemProvider = (BSActivityItemProvider) adapterFactory
									.adapt(proc, ITreeItemContentProvider.class);
							if (itemProvider.isRefreshAllIDsRequired()) {
								ProcessUtil.updateIDs(adapterFactory, proc);
								viewer.refresh();
								itemProvider.setRefreshAllIDsRequired(false);
							}
						}
					}
				}
			}
			
		}, true);
	}

	public Object getViewer(AdapterFactory adapterFactory, Process proc) {
		return doGetViewer(adapterFactory, proc);
	}

	public void runSafely(final Runnable runnable, boolean synchronous) {
		Display display = null;
		try {
			display = Display.getCurrent();
		} catch (Exception e) {
			//
		}
		
		Runnable runner = new Runnable() {

			public void run() {
				try {
					runnable.run();
				} catch (Exception e) {
					LibraryEditUIPlugin.getDefault().getLogger().logError(e);
				}
			}

		};

		if(display == null) {
			// current thread is not a user-interface thread
			//
			try {
				display = Display.getDefault();
			}
			catch(Exception e) {

			}
			if(display != null) {
				if(synchronous) {
					display.syncExec(runner);
				}
				else {
					display.asyncExec(runner);
				}
			}
			else {
				runner.run();
			}
		}
		else {
			// current thread is a user-interface thread
			//
			if(synchronous) {
				runner.run();
			}
			else {
				display.asyncExec(runner);
			}
		}
	}

	public void runWithBusyIndicator(Runnable runnable) {
		BusyIndicator.showWhile(Display.getCurrent(), runnable);
	}

	public void runInUI(final IRunnableWithProgress runnable, String taskName) {
		Shell shell = (Shell) LibraryEditPlugin.getDefault().getContext();
		if (shell == null) {
			try {
				runnable.run(new NullProgressMonitor());
				return;
			} catch (Exception e) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);
				throw new WrappedException(e);
			}
		}
		Job job = new WorkbenchJob(taskName) {

			public IStatus runInUIThread(IProgressMonitor monitor) {
				monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
				try {
					runnable.run(monitor);
					return Status.OK_STATUS;
				} catch (InvocationTargetException e) {
					Throwable ex;
					if (e.getCause() != null) {
						ex = e.getCause();
					} else {
						ex = e;
					}
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, ex.toString(), ex);
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, LibraryEditPlugin
							.getPlugin().getId(), 0, e.toString(), e);
				} finally {
					monitor.done();
				}
			}

		};
		PlatformUI.getWorkbench().getProgressService().showInDialog(shell, job);
		job.schedule();
	}

}
