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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;

/**
 * @author Weiping Lu - Mar 20, 2007
 * @since 1.2
 */
public class Migrator102_103 extends Migrator102 {

	private Map<String, Process> diagramElemMap; 
	
	protected void initMigrate() {
		diagramElemMap = new LinkedHashMap<String, Process>();
	}
	
	protected void update(MethodElement e, IProgressMonitor monitor) throws Exception {
		super.update(e, monitor);
		Migrator103.updateElement(e, diagramElemMap, monitor);
	}

	protected void migrateDiagram(IProgressMonitor monitor) throws Exception {
		Migrator103.diagramMigrate(diagramElemMap, monitor);
	}
}
