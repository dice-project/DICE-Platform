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
package org.eclipse.epf.library.edit.process.command;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.NestedCommandExcecutor;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaFactory;

/**
 * Physically copies a process with all of its direct or inherited structural features and references.
 * Add the newly created process to the specified process package.
 *    
 * This class extends the ActivityDeepCopyCommand to reuse most of it's functionality.
 * 
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class ProcessDeepCopyCommand extends ActivityDeepCopyCommand {

	private ProcessPackage targetPackage;
	private ProcessComponent tempPc;
	private String newProcessName;
	private NestedCommandExcecutor nestedCommandExecutor;
	
	public ProcessDeepCopyCommand(org.eclipse.epf.uma.Process process, String newProcessName,
			CopyHelper copyHelper, MethodConfiguration config, ProcessPackage targetPackage,
			IProgressMonitor monitor,IConfigurator configurator) {

		super(process, copyHelper, config, null, monitor, configurator);

		this.newProcessName = newProcessName;		
		this.targetPackage = targetPackage;
		createtargetProcess();
		//TODO: disable copying diagrams for now
		//
		if (ProcessUtil.isProcessDeepcopyDiagarm()) {
			nestedCommandExecutor = new NestedCommandExcecutor(this);
		}
	}	
	
	public MethodConfiguration getMethodConfiguration() {
		return config;
	}	
	
	public Process getTargetProcess() {
		return targetProcess;
	}
	
	public Process getSourceProcess() {
		return (Process) activity;
	}
	
	private void createtargetProcess() {
		// create a placeholder for the new process
		// since the content processor needs to know the taret process in order to fix the urls
		
		tempPc = UmaFactory.eINSTANCE.createProcessComponent();
		targetPackage.getChildPackages().add(tempPc);
		
		if ( super.activity instanceof DeliveryProcess ) {
			targetProcess = UmaFactory.eINSTANCE.createDeliveryProcess();
		} else {
			targetProcess = UmaFactory.eINSTANCE.createCapabilityPattern();			
		}
		targetProcess.setName(newProcessName);
		targetProcess.setPresentationName(newProcessName);
		tempPc.setName(newProcessName);

		tempPc.setProcess(targetProcess);		
	}
	
	@Override
	public void dispose() {
		if(nestedCommandExecutor != null) {
			nestedCommandExecutor.dispose();
		}
		super.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.CopyCommand#execute()
	 */
	public void execute() {				
		super.execute();	
				
		// new copy of the process must be saved so other nested commands like diagram copy command 
		// can be executed.
		if(pkgCopy != null && pkgCopy instanceof ProcessComponent) {
			ILibraryPersister.FailSafeMethodLibraryPersister persister = Services.getDefaultLibraryPersister().getFailSafePersister();
			try {
				persister.save(pkgCopy.eResource());
				persister.commit();
			}
			catch(Exception e) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);
				try {
					persister.rollback();
				} catch (Exception ex) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
		
		if(nestedCommandExecutor != null) {
			nestedCommandExecutor.executeNestedCommands();
		}
	}
	
	@Override
	public void undo() {
		if(nestedCommandExecutor != null) {
			nestedCommandExecutor.undoNestedCommands();
		}
		super.undo();
	}
	
	protected void fixProcessComponent() {
		// replace the place holder with the actual copied process
		if (pkgCopy instanceof ProcessComponent ) {
			pkgCopy.setName(newProcessName);
			org.eclipse.epf.uma.Process proc = ((ProcessComponent)pkgCopy).getProcess();
			proc.setName(newProcessName);
			proc.setPresentationName(newProcessName);
			targetProcess = proc;
			EcoreUtil.replace(tempPc, pkgCopy);
		}
	}
	
	/* 
	 * returns a Collection containing the new process
	 * 
	 * @see org.eclipse.emf.common.command.CompoundCommand#getResult()
	 */
	public Collection<?> getResult() {
		if(pkgCopy != null && pkgCopy instanceof ProcessComponent) {
			return Collections.singletonList( ((ProcessComponent)pkgCopy).getProcess());
		}
		return Collections.EMPTY_LIST;
	}	
}
