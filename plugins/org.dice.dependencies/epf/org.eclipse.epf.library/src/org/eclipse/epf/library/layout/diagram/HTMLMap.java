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
package org.eclipse.epf.library.layout.diagram;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Encapsulates the HTML MAP tag.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class HTMLMap {

	private String name;

	private ArrayList areas = new ArrayList();

	/**
	 * Creates a new <code>HTMLMap</code>.
	 */
	public HTMLMap(String name) {
		this.name = name;
	}

	/**
	 * Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a new area to the map.
	 */
	public void addArea(HTMLArea area) {
		if (area != null) {
			areas.add(area);
		}
	}

	/**
	 * Returns an iterator to iterate the areas.
	 */
	public Iterator getAreas() {
		return areas.iterator();
	}

	public int size() {
		return areas.size();
	}

}
