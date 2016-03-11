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
package org.eclipse.epf.library.edit.process;

import java.util.Map;

/**
 * Interface used to pass the column index / name map from the user interface to
 * the adapter that implements it so the adapter can provide the correct data
 * for the columns even their index have changed.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IColumnAware {

	Map getColumnIndexToNameMap();

	void setColumnIndexToNameMap(Map map);

}
