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
package org.eclipse.epf.library.edit;

public interface IPluginUIPackageContextChangedListener {

	/**
	 * Called when Plugin Package presentation changed.
	 * @param isFlat if <code>true</code>, layout is now flat mode. if <code>false</code>,
	 * layout is hierarchical.
	 */
	void layoutChanged(boolean isFlat);

}
