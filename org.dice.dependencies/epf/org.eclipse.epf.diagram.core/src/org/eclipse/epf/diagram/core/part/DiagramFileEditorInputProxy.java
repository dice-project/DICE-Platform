//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
/**
 * 
 */
package org.eclipse.epf.diagram.core.part;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInputFactory;

/**
 * @author Shashidhar Kannoori
 *
 */
public class DiagramFileEditorInputProxy implements
		IDiagramFileEditorInputProxy {

	private DiagramEditorInput diagramEditorInput;
    private IFile file;
    private TransactionalEditingDomain domain;

	protected DiagramFileEditorInputProxy(DiagramEditorInput input) {
		this.diagramEditorInput = input;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.diagram.core.part.IDiagramFileEditorInputProxy#getDiagramEditorInput()
	 */
	public DiagramEditorInput getDiagramEditorInput() {
		return diagramEditorInput;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.MEditingDomainElement#getEditingDomain()
	 */
	public TransactionalEditingDomain getEditingDomain() {
		// TODO Auto-generated method stub
		return domain;
	}

	/* (non-Javadoc)
     * Method declared on IEditorInput.
     */
    public boolean exists() {
        return file.exists();
    }

    /* (non-Javadoc)
     * Method declared on IAdaptable.
     */
    public Object getAdapter(Class adapter) {
        if (adapter == IFile.class) {
			return file;
		}
        return file.getAdapter(adapter);
    }

    /* (non-Javadoc)
     * Method declared on IPersistableElement.
     */
    public String getFactoryId() {
        return FileEditorInputFactory.getFactoryId();
    }

    /* (non-Javadoc)
     * Method declared on IDiagramFileEditorInput.
     */
    public IFile getFile() {
        return file;
    }

    /* (non-Javadoc)
     * Method declared on IEditorInput.
     */
    public ImageDescriptor getImageDescriptor() {
        IContentType contentType = IDE.getContentType(file);
		return PlatformUI.getWorkbench().getEditorRegistry()
                .getImageDescriptor(file.getName(), contentType);
    }

    /* (non-Javadoc)
     * Method declared on IEditorInput.
     */
    public String getName() {
        return file.getName();
    }

    /* (non-Javadoc)
     * Method declared on IEditorInput.
     */
    public IPersistableElement getPersistable() {
        return null;
    }

    /* (non-Javadoc)
     * Method declared on IStorageEditorInput.
     */
    public IStorage getStorage() throws CoreException {
        return file;
    }

    /* (non-Javadoc)
     * Method declared on IEditorInput.
     */
    public String getToolTipText() {
        return diagramEditorInput.getMethodElement().getName();
    }

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPersistable#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento memento) {
		// TODO Auto-generated method stub

	}
	public void setTransactionalEditingDomain(TransactionalEditingDomain domain){
		this.domain = domain;
	}

	   /* (non-Javadoc)
     * Method declared on IPathEditorInput
     * @since 3.0
     * @issue consider using an internal adapter for IPathEditorInput rather than adding this as API
     */
    public IPath getPath() {
        return file.getLocation();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getClass().getName() + "(" + getFile().getFullPath() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
}
