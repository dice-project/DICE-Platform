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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;


/**
 * Name validator for ContentElement.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ContentElementNameValidator extends UniquenessValidator {

	/**
	 * Creates a new instance.
	 */
	public ContentElementNameValidator(EObject container, ContentElement e,
			IFilter childFilter) {
		super(container, UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), childFilter, e,
				UmaPackage.eINSTANCE.getNamedElement_Name());
	}

	/**
	 * Creates a new instance.
	 */
	public ContentElementNameValidator(EObject parent,
			EStructuralFeature containingFeature, ContentElement e,
			IFilter childFilter) {
		super(parent, containingFeature, childFilter, e, UmaPackage.eINSTANCE
				.getNamedElement_Name());
	}

	/**
	 * @see org.eclipse.epf.library.edit.validation.internal.UniquenessValidator#isValid(String)
	 */
	public String isValid(String newText) {
		ContentElement e = (ContentElement) object;

		// 156945 - Element name need to allow more special characters such as ? 
		// if the element does not generate a resource file
		if ( e instanceof Section ) {
			return newText;
		}
		
		String msg = TngUtil.checkElementName(newText, TngUtil.getTypeText(e));
		if (msg != null)
			return msg;
		
		if (container != null) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(container);
		
			Integer max = new Integer(MaxFilePathNameLength);
			if (! NameChecker.checkFilePathLength(plugin, e, newText, max.intValue(), null)) {
				msg = NLS.bind(LibraryEditResources.filePathNameTooLong_msg, 
						new Object[] { max } );
				return msg;
			}
		}

		// Check whether a file with the same name already exists.
		if (container != null && ContentDescriptionFactory.hasPresentation(e)) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(container);
			if(plugin == UmaUtil.getMethodPlugin(e)) {
				URI uri = plugin.eResource().getURI();
				if(uri.isFile()) {
					File pluginDir = new File(uri.toFileString()).getParentFile();
					IFileBasedLibraryPersister persister = ((IFileBasedLibraryPersister)Services.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE));			
					String folderPath = persister.getFolderRelativePath(e);
					String dir = new StringBuffer(pluginDir.getAbsolutePath()).append(
							File.separator).append(folderPath).toString();
					String fileName = newText + persister.getFileExtension(e);
					File file = new File(dir, fileName);
					File currentFile = null;
					ContentDescription content = e.getPresentation();
					if (content.eResource() != null) {
						currentFile = new File(content.eResource().getURI()
								.toFileString());
					} else {
						currentFile = file;
					}
					if (file.exists() && !newText.equals(e.getName()) && !file.equals(currentFile)) {
						return NLS.bind(LibraryEditResources.duplicateContentFileError_msg, file); 
					}
				}
			}
		}

		return super.isValid(newText);
	}

}