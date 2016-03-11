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

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.graphiti.ui.internal.Messages;

/**
 * A ToolEntry for a {@link GFPanningSelectionTool}.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPanningSelectionToolEntry extends PanningSelectionToolEntry {

	/**
	 * Creates GFPanningSelectionToolEntry.
	 */
	public GFPanningSelectionToolEntry() {
		this(null);
	}

	/**
	 * Constructor for GFPanningSelectionToolEntry.
	 * 
	 * @param label
	 *            the label
	 */
	public GFPanningSelectionToolEntry(String label) {
		this(null, label);
	}

	/**
	 * Constructor for GFPanningSelectionToolEntry.
	 * 
	 * @param label
	 *            the label
	 * @param shortDesc
	 *            the description
	 */
	public GFPanningSelectionToolEntry(String label, String shortDesc) {
		super(label, shortDesc);
		if (label == null || label.length() == 0) {
			setLabel(GEFMessages.SelectionTool_Label);
		}
		if (shortDesc == null || shortDesc.length() == 0) {
			setDescription(Messages.GFPanningSelectionToolEntry_0_xmsg);
		}
		setToolClass(GFPanningSelectionTool.class);
	}

}
