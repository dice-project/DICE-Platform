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
package org.eclipse.epf.diagram.core.services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class DiagramService {

	private Set<DiagramManager> diagramManagers = new HashSet<DiagramManager>();

	/**
	 * 
	 */
	public DiagramService() {
	}
	
	public void dispose() {
		for (DiagramManager mgr : diagramManagers) {
			mgr.removeConsumer(this);
		}
	}

	public DiagramManager getDiagramManager(Activity act) {
		Process proc = TngUtil.getOwningProcess(act);
		if(proc != null) {
			DiagramManager mgr = DiagramManager.getInstance(TngUtil.getOwningProcess(act), this);
			diagramManagers.add(mgr);
			return mgr;
		}
		return null;
	}
	
	public Collection<Diagram> getDiagrams(Activity act) {
		DiagramManager mgr = getDiagramManager(act);
		return mgr != null ? DiagramHelper.getDiagrams(act, mgr) : Collections.EMPTY_LIST;
	}
	
	/**
	 * Returns base diagram.
	 * @param act
	 * @param type
	 * @return
	 */
	
	public Diagram getBaseDiagram(Activity act, int type) {		
		try {
			Activity baseAct = act;
			while (ProcessUtil.isExtendingOrLocallyContributing(act)) {
				baseAct = (Activity) baseAct.getVariabilityBasedOnElement();
				DiagramManager mgr = getDiagramManager(baseAct);
				List<Diagram> baseDiagrams = mgr.getDiagrams(baseAct, type);
				if (!baseDiagrams.isEmpty()) {
					return baseDiagrams.get(0);
				}
			}
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(
					"Error in getting base diagram: ", e); //$NON-NLS-1$
		}
		return null;
	}

	public Diagram getDiagram(Activity act, int type) {
		try {
			DiagramManager mgr = getDiagramManager(act);
			List<Diagram> diagrams = mgr.getDiagrams(act, type);
			return diagrams.isEmpty() ? null : diagrams.get(0);
		}
		catch(Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}
}
