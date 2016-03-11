/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;

/**
 * This class is a simple container for the preferences fo the FlyoutPalette.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultFlyoutPalettePreferences implements FlyoutPaletteComposite.FlyoutPreferences {

	/**
	 * The _dock location.
	 */
	protected int _dockLocation;

	/**
	 * The _palette state.
	 */
	protected int _paletteState;

	/**
	 * The _palette width.
	 */
	protected int _paletteWidth;

	/**
	 * Creates a new DefaultFlyoutPalettePreferences.
	 */
	public DefaultFlyoutPalettePreferences() {
		_dockLocation = PositionConstants.WEST;
		_paletteWidth = 125;
		_paletteState = -1;
	}

	// ======================== standard behaviour ============================

	/**
	 * Returns the dock location.
	 * 
	 * @return The dock location.
	 */
	public final int getDockLocation() {
		beforeGetValue();
		return _dockLocation;
	}

	/**
	 * Sets the dock location.
	 * 
	 * @param dockLocation
	 *            The dock location to set.
	 */
	public final void setDockLocation(int dockLocation) {
		_dockLocation = dockLocation;
		afterSetValue();
	}

	/**
	 * Returns the palette state.
	 * 
	 * @return The palette state.
	 */
	public final int getPaletteState() {
		beforeGetValue();
		return _paletteState;
	}

	/**
	 * Sets the palette state.
	 * 
	 * @param paletteState
	 *            The palette state to set.
	 */
	public final void setPaletteState(int paletteState) {
		_paletteState = paletteState;
		afterSetValue();
	}

	/**
	 * Returns the palette width.
	 * 
	 * @return The palette width.
	 */
	public final int getPaletteWidth() {
		beforeGetValue();
		return _paletteWidth;
	}

	/**
	 * Sets the palette width.
	 * 
	 * @param paletteWidth
	 *            The palette width to set.
	 */
	public final void setPaletteWidth(int paletteWidth) {
		_paletteWidth = paletteWidth;
		afterSetValue();
	}

	// ======================== overwriteable behaviour =======================

	/**
	 * Is called in every get-method for one of the preference-values before the
	 * value is returned. This method can be overwritten to load the values from
	 * a persistent storage.
	 * <p>
	 * By default this method does nothing.
	 */
	protected void beforeGetValue() {
		// by default do nothing
	}

	/**
	 * Is called in every set-method for one of the preference-values after the
	 * value was set. This method can be overwritten to save the values to a
	 * persistent storage.
	 * <p>
	 * By default this method does nothing.
	 */
	protected void afterSetValue() {
		// by default do nothing
	}
}
