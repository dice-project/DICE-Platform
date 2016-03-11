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
package org.eclipse.epf.library.edit.util;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public interface IRunnableWithProgress {
    /**
     * Runs this operation.  Progress should be reported to the given progress monitor.
     * This method is usually invoked by an <code>IRunnableContext</code>'s <code>run</code> method,
     * which supplies the progress monitor.
     * A request to cancel the operation should be honored and acknowledged 
     * by throwing <code>InterruptedException</code>.
     *
     * @param monitor the progress monitor to use to display progress and receive
     *   requests for cancelation
     * @exception InvocationTargetException if the run method must propagate a checked exception,
     * 	it should wrap it inside an <code>InvocationTargetException</code>; runtime exceptions are automatically
     *  wrapped in an <code>InvocationTargetException</code> by the calling context
     * @exception InterruptedException if the operation detects a request to cancel, 
     *  using <code>IProgressMonitor.isCanceled()</code>, it should exit by throwing 
     *  <code>InterruptedException</code>
     *
     * @see IRunnableContext#run
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException;
}
