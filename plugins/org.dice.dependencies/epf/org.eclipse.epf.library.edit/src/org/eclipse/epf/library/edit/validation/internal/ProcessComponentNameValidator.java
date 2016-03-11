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

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.AbstractStringValidator;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;


/**
 * Validates the name of a process component.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessComponentNameValidator extends AbstractStringValidator {

	private ProcessPackage parent;

	private ProcessComponent procComp;

	/**
	 * Creates a new instance.
	 */
	public ProcessComponentNameValidator(ProcessPackage parent,
			ProcessComponent procComp) {
		this.parent = parent;
		this.procComp = procComp;
	}

	/**
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.String)
	 */
	public String isValid(String newText) {
		String relativePath = ((IFileBasedLibraryPersister)Services.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE))
			.getFolderRelativePath(procComp);
		MethodPlugin plugin = UmaUtil.getMethodPlugin(parent);
		
		Integer max = new Integer(MaxFilePathNameLength);
		if (! NameChecker.checkFilePathLength(plugin, procComp, newText, max.intValue(), null)) {
			String msg = NLS.bind(LibraryEditResources.filePathNameTooLong_msg, 
					new Object[] { max } );
			return msg;
		}
		
		File dir = new File(
				new File(plugin.eResource().getURI().toFileString())
						.getParent()
						+ File.separator + relativePath);
		if (! newText.equals(StrUtil.makeValidFileName(newText))) {
			return NLS.bind(LibraryEditResources.invalidElementNameError3_msg, newText); 
		}
		return TngUtil.checkName(dir, parent.getChildPackages(), procComp,
				newText, TngUtil.getTypeText(procComp), true);
	}

}