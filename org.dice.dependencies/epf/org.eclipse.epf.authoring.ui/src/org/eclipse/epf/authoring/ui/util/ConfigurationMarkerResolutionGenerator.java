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

import org.eclipse.core.resources.IMarker;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

/**
 * Generates ConfigurationMarkerResolution's
 * @author Jeff Hardy
 *
 */
public class ConfigurationMarkerResolutionGenerator implements IMarkerResolutionGenerator {
	
	private static final IMarkerResolution[] EMPTY_RESOLUTIONS = new IMarkerResolution[0];

	private static final ConfigurationMarkerHelper markerHelper = ConfigurationMarkerHelper.INSTANCE;
	
	/**
	 * @see org.eclipse.ui.IMarkerResolutionGenerator#getResolutions(org.eclipse.core.resources.IMarker)
	 */
	public IMarkerResolution[] getResolutions(IMarker marker) {
		MethodConfiguration config = markerHelper.getConfig(marker);
		MethodElement errorElement = markerHelper.getErrorMethodElement(marker);
		MethodElement causeElement = markerHelper.getCauseMethodElement(marker);
		try {
			
			// create MarkerResolutions
			return new IMarkerResolution[] {new ConfigurationMarkerResolution(config, errorElement, causeElement)};
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return EMPTY_RESOLUTIONS;
	}
}
