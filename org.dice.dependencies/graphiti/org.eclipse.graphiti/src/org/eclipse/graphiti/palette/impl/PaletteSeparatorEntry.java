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
package org.eclipse.graphiti.palette.impl;

import org.eclipse.graphiti.palette.IPaletteSeparatorEntry;

/**
 * A separator for the palette.
 */
public class PaletteSeparatorEntry extends AbstractPaletteEntry implements IPaletteSeparatorEntry {

	/**
	 * Creates a new {@link PaletteSeparatorEntry}.
	 */
	public PaletteSeparatorEntry() {
		super("", ""); //$NON-NLS-1$//$NON-NLS-2$
	}
}
