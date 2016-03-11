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
package org.eclipse.epf.authoring.ui.dialogs;

import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

/**
 * To improve the performance of ItemsFilterDialog, EPF use a cache when retrieve
 * all the base method plugins, see Misc.getAllBase1(MethodPlugin plugin), but it
 * is the caller's responsibility to manage the cache.
 * 
 * In the case of ItemsFilterDialog, this class guarantee the cache will be clear at openning
 * and closing.
 * 
 * @author Alex Chen
 *
 */

public class BaseItemsFilterDialog extends Dialog {
	public BaseItemsFilterDialog(Shell parentShell) {
		super(parentShell);
		Misc.clearCachedMap();	
	}
	
	public boolean close() {
		Misc.clearCachedMap();
		return super.close();
	}
	
}
