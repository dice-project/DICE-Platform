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
//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.model.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.epf.common.CommonPlugin;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public final class TxUtil {

	public static final Map DEFAULT_TX_OPTIONS = new HashMap();
	
	static {
		TxUtil.DEFAULT_TX_OPTIONS.put(Transaction.OPTION_UNPROTECTED, Boolean.TRUE);
	}

	public static final void runInTransaction(EObject objectToModify,
			Runnable modifyOperation) throws ExecutionException {
		TransactionalEditingDomain domain = null;
		try {
			domain = TransactionUtil.getEditingDomain(objectToModify);
		}
		catch(Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		}
				
		if (domain == null) {
			modifyOperation.run();
		} else {
			runInTransaction(domain, modifyOperation);
		}
	}
	
	public static final void runInTransaction(TransactionalEditingDomain domain,
			final Runnable modifyOperation) throws ExecutionException {
		new AbstractEMFOperation(domain, "", DEFAULT_TX_OPTIONS) { //$NON-NLS-1$

			@Override
			protected IStatus doExecute(IProgressMonitor monitor,
					IAdaptable info) throws ExecutionException {
				modifyOperation.run();
				return Status.OK_STATUS;
			}

		}.execute(new NullProgressMonitor(), null);
	}
}
