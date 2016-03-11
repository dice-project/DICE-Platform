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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.VariabilityInfo;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.VariabilityElement;


/**
 * Configuration filter that still shows certain elements during process authoring that
 * ProcessConfigurator does not show.
 * 
 * @author Phong Nguyen Le - Jan 11, 2006
 * @since  1.0
 */
public class ProcessAuthoringConfigurator extends ProcessConfigurator {
	public static final ProcessAuthoringConfigurator INSTANCE = new ProcessAuthoringConfigurator();
	
	private ProcessAuthoringConfigurator() {
		super(null);
		LibraryService.getInstance().addListener(new ILibraryServiceListener() {

			public void configurationSet(MethodConfiguration config) {
				setMethodConfiguration(config);
			}

			public void libraryClosed(MethodLibrary library) {
			}

			public void libraryCreated(MethodLibrary library) {
			}

			public void libraryOpened(MethodLibrary library) {
			}

			public void libraryReopened(MethodLibrary library) {
			}

			public void librarySet(MethodLibrary library) {
			}
			
		});
	}
	
	/**
	 * @param methodConfig
	 * @param viewer
	 */
	public ProcessAuthoringConfigurator(MethodConfiguration methodConfig) {
		super(methodConfig);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.library.configuration.ConfigurationFilter#getChildren(java.lang.Object, org.eclipse.emf.ecore.EStructuralFeature)
	 * override this,  for process authoring edit, should get direct children no realization.
	 *  
	 */
	public Collection getChildren(Object obj, EStructuralFeature childFeature) {
		EObject eObject = (EObject) obj;
		return (List)eObject.eGet(childFeature);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.configuration.ConfigurationFilter#resolve(java.lang.Object)
	 */
	public Object resolve(Object object) {
		return object;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.configuration.ConfigurationFilter#getVariabilityInfo(org.eclipse.epf.uma.VariabilityElement)
	 */
	public VariabilityInfo getVariabilityInfo(VariabilityElement ve) {
		return new VariabilityInfo(ve) {
			/* (non-Javadoc)
			 * @see org.eclipse.epf.library.edit.VariabilityInfo#getInheriranceList()
			 */
			public List getInheritanceList() {
				return Collections.singletonList(getOwner());
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.epf.library.edit.VariabilityInfo#getContributors()
			 */
			public List getContributors() {
				return Collections.EMPTY_LIST;
			}
		};
	}
	
}
