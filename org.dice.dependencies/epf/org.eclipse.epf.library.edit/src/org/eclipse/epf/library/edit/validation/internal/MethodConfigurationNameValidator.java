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
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.osgi.util.NLS;


/**
 * Name validator for MethodConfiguration.
 * 
 * @author Phong Nguyen Le - May 12, 2006
 * @since  1.0
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=177973
 */
public class MethodConfigurationNameValidator extends AbstractStringValidator {

	private MethodLibrary library;
	private MethodConfiguration config;

	public MethodConfigurationNameValidator(MethodLibrary lib, MethodConfiguration config) {
		library = lib;
		this.config = config;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.String)
	 */
	public String isValid(String newText) {
		newText = newText.trim(); //fix https://bugs.eclipse.org/bugs/show_bug.cgi?id=177973
		Integer max = new Integer(MaxFilePathNameLength);
		if (! NameChecker.checkFilePathLength(library, config, newText, max.intValue(), null)) {
			String msg = NLS.bind(LibraryEditResources.filePathNameTooLong_msg, 
					new Object[] { max } );
			return msg;
		}
		String configFolder = ((IFileBasedLibraryPersister)Services.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE)) 
			.getFolderRelativePath(config);
		File dir = new File(new File(library.eResource().getURI().toFileString())
						.getParent(), configFolder);
		return TngUtil.checkName(dir, library.getPredefinedConfigurations(), config,
				newText, TngUtil.getTypeText(config), false);
	}
}