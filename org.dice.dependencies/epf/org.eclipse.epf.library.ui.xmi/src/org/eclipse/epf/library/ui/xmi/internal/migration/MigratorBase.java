//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.xmi.internal.migration;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.xmi.XMILibraryPlugin;
import org.eclipse.epf.persistence.migration.MigratorImpl;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * Base class implementation for migrators
 * 
 * @author Weiping Lu - Feb 12, 2007
 * @since 1.2
 */
public abstract class MigratorBase extends MigratorImpl {
	private UpgradeCallerInfo callerInfo;	
	
	public UpgradeCallerInfo getCallerInfo() {
		return callerInfo;
	}

	public void setCallerInfo(UpgradeCallerInfo callerInfo) {
		this.callerInfo = callerInfo;
	}

	protected static final boolean DEBUG = XMILibraryPlugin.getDefault()
			.isDebugging();
	
	protected static void updateStatus(IProgressMonitor monitor, String msg) {
		if (monitor != null) {
			monitor.subTask(msg);
			monitor.worked(1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				//
			}
		} else {
			System.out.println(msg);
		}
	}
	
	protected void updateAllContents(IProgressMonitor monitor, MethodLibrary lib) throws Exception {
		for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (element instanceof MethodElement) {
				try {
					for (Iterator iterator = element.eCrossReferences()
							.iterator(); iterator.hasNext();) {
						iterator.next();
					}
				} catch (Exception e) {
					CommonPlugin.INSTANCE.log(e);
					if (DEBUG) {
						System.err
								.println("Error iterate thru cross references of element: " + element); //$NON-NLS-1$
					}
				}
				updateElement((MethodElement) element, monitor);
			}
		}
	}
	
	protected abstract void updateElement(MethodElement element, IProgressMonitor monitor) throws Exception;	
	
	public abstract void migrate(String libPath, IProgressMonitor monitor) throws Exception;
	
	public abstract void migrate(String libPath, IProgressMonitor monitor, UpgradeCallerInfo info) throws Exception;

	
}
