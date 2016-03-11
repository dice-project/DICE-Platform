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
package org.eclipse.epf.library.layout.elements;

import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.uma.MethodElement;


/**
 * simple holder of process element item info
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ProcessElementItem {
	
	public Object rawItem = null;
	public MethodElement element = null;
	public String path = null;
	public String proc_guid = null;
	
	public ProcessElementItem(Object rawItem, MethodElement element, String path)
	{
		this.rawItem = rawItem;
		this.element = element;
		this.path = path;
		this.proc_guid = AbstractProcessElementLayout.getOwningProcessGuidFromPath(path);
	}
	
	public ProcessElementItem(Object rawItem, MethodElement element, ProcessElementItem parent)
	{
		this.rawItem = rawItem;
		this.element = element;
		this.path = AbstractProcessElementLayout.makePath(parent.path, this.element); // make path here
		this.proc_guid = parent.proc_guid;		
	}

	public String getQueryString()
	{
		return ElementLayoutManager.getQueryString(proc_guid, path);		
	}
}
