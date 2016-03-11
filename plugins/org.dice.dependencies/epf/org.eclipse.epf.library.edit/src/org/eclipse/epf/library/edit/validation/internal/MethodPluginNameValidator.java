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
package org.eclipse.epf.library.edit.validation.internal;

import java.io.File;

import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.AbstractStringValidator;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.osgi.util.NLS;

/**
 * @author Phong Nguyen Le
 * @since  1.1
 */
public class MethodPluginNameValidator extends AbstractStringValidator {
	private MethodLibrary lib;
	private MethodPlugin plugin;	

	/**
	 * @param lib
	 */
	public MethodPluginNameValidator(MethodLibrary lib, MethodPlugin plugin) {
		super();
		this.lib = lib;
		this.plugin = plugin;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.String)
	 */
	public String isValid(String newText) {
		String msg = TngUtil.checkPluginName(newText);
		if(msg != null) {
			return msg;
		}
		
		Integer max = new Integer(MaxFilePathNameLength);
		if (! NameChecker.checkFilePathLength(lib, plugin, newText, max.intValue(), null)) {
			msg = NLS.bind(LibraryEditResources.filePathNameTooLong_msg, 
					new Object[] { max } );
			return msg;
		}
		
		File dir = new File(lib.eResource().getURI().toFileString())
				.getParentFile();
		String elementTypeText = (plugin != null) ? TngUtil.getTypeText(plugin)
				: TngUtil.getTypeText("MethodPlugin"); //$NON-NLS-1$
		return TngUtil.checkName(dir, lib.getMethodPlugins(), plugin, newText,
				elementTypeText, true);
	}

}
