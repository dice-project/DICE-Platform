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

package org.eclipse.epf.diagram.add.preferences;

import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.PrintingPreferencePage;

/**
 * @generated
 */
public class DiagramPrintingPreferencePage extends PrintingPreferencePage {

	/**
	 * @generated
	 */
	public DiagramPrintingPreferencePage() {
		setPreferenceStore(ActivityDetailDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}
}
