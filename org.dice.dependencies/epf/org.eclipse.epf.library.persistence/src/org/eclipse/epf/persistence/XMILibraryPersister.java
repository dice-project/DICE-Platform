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
package org.eclipse.epf.persistence;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * @author Phong Nguyen Le - Oct 10, 2006
 * @since  1.0
 */
public class XMILibraryPersister implements IFileBasedLibraryPersister {
	
	/**
	 * Contructs a new XMILibraryPersister instance.
	 */
	public XMILibraryPersister() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#getFileExtension(java.lang.Object)
	 */
	public String getFileExtension(Object e) {
		return MethodLibraryPersister.INSTANCE.getFileExtension(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#getFolderRelativePath(org.eclipse.epf.uma.MethodElement)
	 */
	public String getFolderRelativePath(MethodElement e) {
		return MethodLibraryPersister.INSTANCE.getFolderRelativePath(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#hasOwnFolder(java.lang.Object)
	 */
	public boolean hasOwnFolder(Object e) {
		return MethodLibraryPersister.INSTANCE.hasOwnFolder(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#adjustLocation(org.eclipse.emf.ecore.resource.Resource)
	 */
	public void adjustLocation(Resource resource) {
		MethodLibraryPersister.INSTANCE.adjustLocation(resource);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#delete(org.eclipse.epf.uma.MethodElement)
	 */
	public void delete(MethodElement e) {
		MethodLibraryPersister.INSTANCE.delete(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#getFailSafePersister()
	 */
	public FailSafeMethodLibraryPersister getFailSafePersister() {
		return MethodLibraryPersister.INSTANCE.getFailSafePersister();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#getWarnings()
	 */
	public List getWarnings() {
		return MethodLibraryPersister.INSTANCE.getWarnings();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#hasOwnResource(java.lang.Object)
	 */
	public boolean hasOwnResource(Object e) {
		return MethodLibraryPersister.INSTANCE.hasOwnResource(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#hasOwnResourceWithoutReferrer(java.lang.Object)
	 */
	public boolean hasOwnResourceWithoutReferrer(Object e) {
		return MethodLibraryPersister.INSTANCE.hasOwnResourceWithoutReferrer(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#save(org.eclipse.emf.ecore.resource.Resource)
	 */
	public void save(Resource resource) throws Exception {
		MethodLibraryPersister.INSTANCE.save(resource);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.ILibraryPersister#save(org.eclipse.epf.uma.MethodElement)
	 */
	public void save(MethodElement element) throws Exception {
		MethodLibraryPersister.INSTANCE.save(element);
	}

	public File createMethodPluginFolder(String pluginName, MethodLibrary library) {
		return MethodLibraryPersister.INSTANCE.createMethodPluginFolder(pluginName, library);
	}

	public File getDefaultMethodConfigurationFolder(MethodLibrary library) {
		return MethodLibraryPersister.INSTANCE.getDefaultMethodConfigurationFolder(library);
	}
	
	public File getDefaultMethodConfigurationFolder(MethodLibrary library,
			boolean create) {
		return MethodLibraryPersister.INSTANCE.getDefaultMethodConfigurationFolder(library, create);
	}

	public void setDefaultMethodConfigurationFolder(MethodLibrary library, File file) {
		MethodLibraryPersister.INSTANCE.setDefaultMethodConfigurationFolder(library, file);
	}

	public boolean isContainedBy(Resource resource, Resource containerResource) {
		return MethodLibraryPersister.INSTANCE.isContainedBy(resource, containerResource);
	}

	public String getResourceFolderPath(MethodElement e) {
		return MethodLibraryPersister.INSTANCE.getResourceFolderPath(e);
	}

	public String getFolderAbsolutePath(MethodElement e) {
		return MethodLibraryPersister.INSTANCE.getFolderAbsolutePath(e);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.services.ILibraryPersister#delete(java.util.Collection)
	 */
	public void delete(Collection<MethodElement> elements) {
		MethodLibraryPersister.INSTANCE.delete(elements);
	}

	public File getFile(Resource resource) {
		return MethodLibraryPersister.INSTANCE.getFile(resource);
	}
}
