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
package org.eclipse.epf.persistence;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * This class keep the transaction data to support fail-safe persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
class TxRecord {

	private String txID;

	private List resourcesToCommit;

	protected List warnings;

	/**
	 * @param txid
	 * @param commit
	 */
	public TxRecord() {
		super();
		resourcesToCommit = new UniqueEList();
	}

	public String getTxID() {
		if (txID == null) {
			txID = UmaUtil.generateGUID();
		}
		return txID;
	}

	public List getResourcesToCommit() {
		return resourcesToCommit;
	}

	/**
	 * @return the warnings
	 */
	public List getWarnings() {
		if (warnings == null) {
			warnings = new ArrayList();
		}
		return warnings;
	}

	public void clear() {
		txID = null;
		resourcesToCommit.clear();
		if (warnings != null) {
			warnings.clear();
		}
	}

}
