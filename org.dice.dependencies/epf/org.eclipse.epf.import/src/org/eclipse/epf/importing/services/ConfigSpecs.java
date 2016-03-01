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
package org.eclipse.epf.importing.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.export.services.ConfigurationSpec;
import org.eclipse.epf.uma.MethodConfiguration;


/**
 * Models a list of library configuration specifications.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigSpecs {

	// A list of configuration entries.
	public List configs = new ArrayList();

	/**
	 * Creates a new instance.
	 */
	public ConfigSpecs() {
	}

	/**
	 * Returns a new entry.
	 */
	public Entry newEntry() {
		Entry e = new Entry();
		configs.add(e);

		return e;
	}

	/**
	 * Returns a iterator for the config specs.
	 */
	public Iterator iterator() {
		return configs.iterator();
	}

	public class Entry {

		public ConfigurationSpec configSpec = null;

		public MethodConfiguration existingConfig = null;

		public boolean selected = true;

		public String toString() {
			if (configSpec != null)
				if (configSpec.name != null)
					return configSpec.name;
			return "unknown configuration specification"; //$NON-NLS-1$
		}

	}

}
