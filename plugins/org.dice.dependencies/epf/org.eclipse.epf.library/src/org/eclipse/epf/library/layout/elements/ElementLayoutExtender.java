//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.layout.elements;

import java.util.List;

import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;


/**
 * The layout extender for all Method Elements.
 * 
 * @author Weiping Lu
 * @sicne 1.5
 */
public abstract class ElementLayoutExtender {

	private AbstractElementLayout layout;
	public ElementLayoutExtender(AbstractElementLayout layout) {
		this.layout = layout;
	}
	
	public abstract List<MethodElement> getTagQualifiedList(MethodConfiguration config, List<MethodElement> items);

	public AbstractElementLayout getLayout() {
		return layout;
	}
	
	protected void addAttributes(XmlElement xmlElement) {		
	}
	
}