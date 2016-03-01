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
package org.eclipse.epf.library.ui.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.persistence.util.LibrarySchedulingRule;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Runs operation that locks the library for the duration of its execution
 * 
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class LibraryLockingOperationRunner implements IRunnableContext {

	private IProgressMonitor progressMonitor;
	
	public LibraryLockingOperationRunner() {
	}	
	
	private void doRun(final IRunnableWithProgress runnable, final boolean workUnknown) throws InvocationTargetException,
			InterruptedException {
        final InvocationTargetException[] iteHolder = new InvocationTargetException[1];
        try {
            IWorkspaceRunnable workspaceRunnable = new IWorkspaceRunnable() {
                public void run(IProgressMonitor pm) throws CoreException {
                    try {
                        if(workUnknown) {
        					pm.beginTask("", 2); //$NON-NLS-1$
        					pm.worked(1);
        					try {
        						runnable.run(pm);
        						pm.worked(2);
        					}
        					finally {
        						pm.done();
        					}
                        }
                        else {
                        	runnable.run(pm);
                        }
                    } catch (InvocationTargetException e) {
                        // Pass it outside the workspace runnable
                        iteHolder[0] = e;
                    } catch (InterruptedException e) {
                        // Re-throw as OperationCanceledException, which will be
                        // caught and re-thrown as InterruptedException below.
                        throw new OperationCanceledException(e.getMessage());
                    }
                    // CoreException and OperationCanceledException are propagated
                }
            };
            ResourcesPlugin.getWorkspace().run(workspaceRunnable,
            		new LibrarySchedulingRule(LibraryService.getInstance().getCurrentMethodLibrary()),
            		IWorkspace.AVOID_UPDATE, getProgressMonitor());
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        } catch (OperationCanceledException e) {
            throw new InterruptedException(e.getMessage());
        }
        // Re-throw the InvocationTargetException, if any occurred
        if (iteHolder[0] != null) {
            throw iteHolder[0];
        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableContext#run(boolean, boolean, org.eclipse.jface.operation.IRunnableWithProgress)
	 */
	public void run(boolean fork, boolean cancelable,
			final IRunnableWithProgress runnable) throws InvocationTargetException,
			InterruptedException {
		doRun(runnable, false);
	}

	public void run(IRunnableWithProgress runnable) {
		try {
			doRun(runnable, true);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			if(cause instanceof RuntimeException) {
				throw (RuntimeException)cause;
			}
			else {
				if(cause == null) {
					cause = e;
				}
				LibraryUIPlugin.getDefault().getLogger().logError(cause);
				try {
					String msg = cause.getMessage() != null ? cause.getMessage() : cause.toString(); 
					LibraryUIPlugin.getDefault().getMsgDialog().displayError(LibraryUIResources.errorDialog_title, msg, cause);
				}
				catch(Exception ex) {
					
				}				
			}
		} catch (InterruptedException e) {
			return;
		}
	}
	
	/**
	 * @return
	 */
	public IProgressMonitor getProgressMonitor() {
		if(progressMonitor == null) {
			progressMonitor = new NullProgressMonitor();
		}
		return progressMonitor;
	}

	/**
	 * @param progressMonitor the progressMonitor to set
	 */
	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
	}
}
