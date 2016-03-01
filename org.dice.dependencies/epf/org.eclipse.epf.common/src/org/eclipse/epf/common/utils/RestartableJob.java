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
package org.eclipse.epf.common.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Job class that is re-startable
 * 
 * @author Weiping Lu
 * @since 1.5
 */

public abstract class RestartableJob extends Job {
	
	protected static boolean localDebug = false;
	
	private boolean toRestart = true;
	private boolean schedulingLocked = false;
	private boolean enabled = true;
	
	public RestartableJob(String name) {
		super(name);
	}
	
	public final void guardedSchedule(long delay) {
		guardedSchedule(delay, false);
	}
	
	private void guardedSchedule(long delay, boolean inRunCall) {
		if ( ! isEnabled()) {
			return;
		}
		if (!inRunCall && getState() != Job.NONE) {
			return;
		}
		if (isSchedulingLocked()) {
			return;
		}
		if (localDebug) {
			System.out.println("LD> guardedSchedule completed"); //$NON-NLS-1$
		}
		setSchedulingLocked(true);
		schedule(delay);		
	}
	
	protected final IStatus run(IProgressMonitor monitor) {
		if (localDebug) {
			System.out.println("LD> RestartableJob.run"); //$NON-NLS-1$
		}
		setSchedulingLocked(false);
		setToRestart(false);
		IStatus status = Status.OK_STATUS; 
		try {
			status = restartableRun(monitor);
		} catch (RestartInterruptException e) {
			guardedSchedule(e.getDelay(), true);
		}
		
		return Status.OK_STATUS;
	}
	
	protected abstract IStatus restartableRun(IProgressMonitor monitor) throws RestartInterruptException;
	protected abstract void resetToRestart();

	private synchronized boolean isSchedulingLocked() {
		return schedulingLocked;
	}

	private synchronized void setSchedulingLocked(boolean schedulingLocked) {
		this.schedulingLocked = schedulingLocked;
	}

	private synchronized boolean isToRestart() {
		return toRestart;
	}

	private synchronized void setToRestart(boolean toRestart) {
		this.toRestart = toRestart;
	}
	
	public void enableToRestart() {
		setToRestart(true);
	}
	
	public void checkRestartInterruptException(long delay) throws RestartInterruptException {
		if (isToRestart()) {
			resetToRestart();
			throw new RestartInterruptException(delay);
		}
	}

	public static class RestartInterruptException extends Exception {
		private long delay = 0;
		public RestartInterruptException(long delay) {
			this.delay = delay;
		}
		
		public long getDelay() {
			return delay;
		}
	}

	public synchronized boolean isEnabled() {
		return enabled;
	}

	public synchronized void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	

}
