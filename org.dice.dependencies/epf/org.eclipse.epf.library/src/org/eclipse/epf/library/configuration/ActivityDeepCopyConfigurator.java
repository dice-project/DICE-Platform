/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.configuration;

import org.eclipse.epf.library.edit.VariabilityInfo;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * A configurator used by activity deep copy command to resovle and deep copy contributors and
 * replacers of activity in a metohd configuration.
 *
 */
public class ActivityDeepCopyConfigurator extends ConfigurationFilter{

	
	/**
	 * constructor
	 * @param methodConfig a <code>MethodConfiguration</code>
	 * @param viewer a <code>Viewer</code>
	 */
	public ActivityDeepCopyConfigurator(MethodConfiguration methodConfig) {
		super(methodConfig);
	}
	
	
	/**
	 * Constructs with null methodConfig which could be set later.
	 */
	public ActivityDeepCopyConfigurator() {
		this(null);
	}
	

	@Override
	public VariabilityInfo getVariabilityInfo(VariabilityElement ve) {

		// calculate the element first
		ElementRealizer realizer = new DefaultElementRealizer(methodConfig, false, true){
			@Override
			public boolean canShow(MethodElement element) {
				return true;
			}			
		};
		return getVariabilityInfo(ve, realizer);
	}
	
	/**
	 * Overrides the super method for not resolving the base to include additional contributors. 
	 */
	@Override
	protected void resolveElementVariabilityList(VariabilityElement element,
			VariabilityInfo info, boolean includeBase, ElementRealizer realizer) {

		if (methodConfig == null) {
			return;
		}		
		//resolve to include contributors
		resolveElementContributors(element,info,realizer);
	}
}
