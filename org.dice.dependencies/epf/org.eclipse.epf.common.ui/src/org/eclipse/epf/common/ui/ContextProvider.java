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
package org.eclipse.epf.common.ui;

import org.eclipse.epf.common.IContextProvider;
import org.eclipse.epf.common.ui.util.MsgBox;

/**
 * content provider for non-ui plugins
 * 
 * @author Jinhua Xi
 * @since 1.5
 *
 */
public class ContextProvider implements IContextProvider {

	public Object getContext() {
		//TODO: revisit
		Object ctx = null;
		try {
			ctx = CommonUIPlugin.getDefault().getWorkbench().getDisplay().getActiveShell();
		}
		catch(Exception e) {
			//
		}
		return ctx != null ? ctx : MsgBox.getDefaultShell();
	}

}
