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
package org.eclipse.epf.library.configuration;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TeamProfile;


/**
 * 
 * @author Phong Le
 * @author Shilpa Toraskar
 * @since 1.0
 *
 */
public class ProcessConfigurator extends ConfigurationFilter {

	private boolean checkOwningProcess;

	/**
	 * @param methodConfig
	 * @param viewer
	 */
	public ProcessConfigurator(MethodConfiguration methodConfig) {
		super(methodConfig);
	}
	
	/**
	 * @param methodConfig
	 * @param viewer
	 * @param checkOwningProcess if true will check on owning process of activities whether it is in the configuration.
	 *          This check is not required in process editor and skipping it helps the performance.
	 */
	public ProcessConfigurator(MethodConfiguration methodConfig, boolean checkOwningProcess) {
		this(methodConfig);
		this.checkOwningProcess = checkOwningProcess;
	}

	public void setMethodConfiguration(MethodConfiguration newConfig) {
		methodConfig = newConfig;
	}

	public boolean accept(Object obj) {
		if (methodConfig == null)
			return true;

		if (obj instanceof BreakdownElement) {
			ElementRealizer realizer = getRealizer();
			return accept((BreakdownElement)obj, realizer);
		}
		
		return super.accept(obj);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.configuration.ProcessConfigurator#accept(org.eclipse.epf.uma.Descriptor)
	 */
	protected boolean accept(BreakdownElement e, final ElementRealizer realizer) {
		if (e instanceof Milestone || e instanceof TeamProfile) {
			// accept all process elements that are not associated with any content
			// element
			//
			return true;
		} else if (e instanceof Activity) {
			return ProcessUtil.accept((Activity) e, new IFilter() {

				public boolean accept(Object obj) {
					return obj instanceof MethodElement ? realizer.inConfig((MethodElement) obj) : true;
				}
				
			}, checkOwningProcess);
		}
		else if(e instanceof Descriptor) {
			// TODO: need to consider checking on owning process of the descriptor whether it is in the configuration.
			// Currently, this check is not required and skipping it helps the performance.
			// But this check might affect the current code, e.g.: process properties view.
			//
			MethodElement linked_obj = ProcessUtil.getAssociatedElement((Descriptor) e);
			if (linked_obj == null || linked_obj.eIsProxy()) {
				// this is the processes own descriptor (independent from the content)
				// always accept it
				return true;
			} else {
				// make sure that element with replacer might still be accepted
				//
				linked_obj = ConfigurationHelper.getCalculatedElement(linked_obj, realizer);

				// if the linked element is not in config, don't accept it
				return realizer.inConfig(linked_obj);
			}		
		}
		
		return super.accept(e);
	}
	
	/**
	 * @return an IRealizationManager instance
	 */
	public IRealizationManager getRealizationManager() {
		MethodConfiguration c = LibraryService.getInstance().getCurrentMethodConfiguration();
		return ConfigurationHelper.getDelegate().getRealizationManager(c);
	}
	
}