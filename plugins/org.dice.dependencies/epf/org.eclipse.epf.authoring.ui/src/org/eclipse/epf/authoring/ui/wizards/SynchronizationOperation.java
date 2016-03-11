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
package org.eclipse.epf.authoring.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Runs synchonization operation
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SynchronizationOperation implements IRunnableWithProgress {

	private SynchronizationChoices synchChoices;
	private Collection elements;
	
	/**
	 * Creates an instance
	 * @param elements
	 * 			List of elements to be synchronized
	 * @param choices
	 * 			Synchronization options
	 */
	public SynchronizationOperation(Collection elements, SynchronizationChoices choices) {
		this.synchChoices = choices;
		this.elements = elements;
	}
	
	/**
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
			System.out.println("$$$ woke up after 1 second sleep, #" + (i+1)); //$NON-NLS-1$
		}
	}
}
