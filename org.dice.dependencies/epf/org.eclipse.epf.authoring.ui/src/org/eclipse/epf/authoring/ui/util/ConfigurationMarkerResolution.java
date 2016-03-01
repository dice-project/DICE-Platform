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
package org.eclipse.epf.authoring.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ConfigurationEditor;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;

/**
 *  MarkerResolution for Configuration errors
 * @author Jeff Hardy
 * @author Jinhua Xi
 *
 */
public class ConfigurationMarkerResolution implements IMarkerResolution {
	
	protected MethodConfiguration config;
	
	protected MethodElement errorElement;

	protected MethodElement causeElement;

	public ConfigurationMarkerResolution(MethodConfiguration config, MethodElement errorElement, MethodElement causeElement) {
		this.config = config;
		this.errorElement = errorElement;
		this.causeElement = causeElement;
	}

	public String getLabel() {
		return AuthoringUIResources.ConfigurationMarkerResolutionGenerator_resolveMissingReference;
	}

	public void run(IMarker marker) {
		//System.out.println("quick fix me");
		try {
			   // get the marker resource file
	        if ((marker.getResource() instanceof IFile)) {
	      
	        	IFile file = (IFile) marker.getResource();
	        	
	        	ResourceSet resourceSet = LibraryService.getInstance().getCurrentMethodLibrary().eResource().getResourceSet();
	        	MethodElement config = PersistenceUtil.getMethodElement(file, resourceSet);
	        	IEditorPart editor = EditorChooser.getInstance().findEditor(config);
	        	if ( editor instanceof ConfigurationEditor ) {
	        		((ConfigurationEditor)editor).doQuickFix(marker);
	        	}
	        	
	        }
		} catch (Exception ex ) {
			AuthoringUIPlugin.getDefault().getLogger().logError(ex);
		}
        
	}

}
