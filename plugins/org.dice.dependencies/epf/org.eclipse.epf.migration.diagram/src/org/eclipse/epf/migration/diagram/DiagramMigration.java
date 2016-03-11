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
package org.eclipse.epf.migration.diagram;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.library.xmi.IDiagramMigration;
import org.eclipse.epf.migration.diagram.ad.services.WorkflowExportService;
import org.eclipse.epf.migration.diagram.util.MigrationUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Implementation class for digram migration 
 * 
 * @author Weiping Lu - April 04, 2007
 * @since  1.2
 */
public class DiagramMigration implements IDiagramMigration {

	protected boolean debug;
	
	public void migrate(Collection<Process> processes) throws Exception {	
		// debug
		debug = DiagramMigrationPlugin.getDefault().isDebugging();
		
		WorkflowExportService service = new WorkflowExportService();
		
		int  errorCount = 0;
		MethodLibrary lib = null;
		
		for (Iterator<Process> it = processes.iterator(); it.hasNext();) {
			Process proc = it.next();
			if (lib == null) {
				lib = UmaUtil.getMethodLibrary(proc);
			}
			
			if (debug) {
				System.out.println("LD> proc: " + proc);	//$NON-NLS-1$
				System.out.println("LD>  proc.eResource(): " +  proc.eResource());//$NON-NLS-1$
				if (proc.eResource() != null) {
					System.out.println("LD>  proc.eResource().getURI(): " +  proc.eResource()); //$NON-NLS-1$
				}
			}

			String modelFilePath = proc.eResource() == null ? null : 
										proc.eResource().getURI().toFileString();
			if (modelFilePath == null) {
				continue;
			}
						
			File cpFolderPath = new File(modelFilePath).getParentFile();
			String diagramFilePath = cpFolderPath.getAbsolutePath() + File.separator +
										"diagram.xmi";				//$NON-NLS-1$
			File diagramFile = new File(diagramFilePath);
			if (diagramFile.exists()) {
				diagramFile.delete();
				System.out.println("LD> Deleted diagramFile: " + diagramFile);//$NON-NLS-1$
				ResourceUtil.refreshResources(lib, null);
			}
			//diagramFile.createNewFile();
			if (debug) {
				System.out.println("LD> diagramFile: " + diagramFile);//$NON-NLS-1$
			}
			try {
				service.export(proc, proc, "diagram.xmi", cpFolderPath); //$NON-NLS-1$
			} catch (Exception e) {
				errorCount++;
				if (debug) {
					System.out.println("LD> failed: " + diagramFile);	//$NON-NLS-1$
				}
			}
		}
		if (debug) {
			System.out.println("LD> processes.size(): " + processes.size());	//$NON-NLS-1$
			System.out.println("LD> errorCount: " + errorCount);	//$NON-NLS-1$
			System.out.println(""); //$NON-NLS-1$
		}
		
		removeOldDiagrams(processes, lib);				

	}

	private void removeOldDiagrams(Collection<Process> processes, MethodLibrary lib) {		
		if (lib == null) {
			return;
		}
		boolean needSave = false;
		if (debug) {
			System.out.println("LD> removeOldDiagrams ...");	//$NON-NLS-1$
		}
		for (Iterator<Process> it = processes.iterator(); it.hasNext();) {
			Process proc = it.next();
			boolean dirty = false;
			try {
				Map activities = MigrationUtil.getActivities(proc, true);
				for (Iterator iit = activities.values().iterator(); iit.hasNext();) {				
						Activity act = (Activity) iit.next();						
						EObject container = act.eContainer();
						if (container instanceof ProcessPackage) {
							ProcessPackage pkg = (ProcessPackage) container;
							if (! pkg.getDiagrams().isEmpty()) {
								ArrayList removeList = new ArrayList(pkg.getDiagrams());
								pkg.getDiagrams().removeAll(removeList);
								dirty = true;
								if (debug) {
									System.out.println("LD> proc: " + proc);	//$NON-NLS-1$
									System.out.println("LD>  pkg: ");			//$NON-NLS-1$
								}
							}
						}
				}
				if (dirty) {
					proc.eResource().setModified(true);
					needSave = true;
				}
			} catch (Exception e) {
				if (debug) {
					e.printStackTrace();
				}
			}			
		}
		if (needSave) {
			Resource resource = lib.eResource();
			MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) resource.getResourceSet();
			Map saveOptions = resourceSet.getDefaultSaveOptions();
			try {
				resourceSet.save(saveOptions, true);
			} catch (Exception e) {
				if (debug) {
					e.printStackTrace();
				}
			}	
		}
	}
	
}
