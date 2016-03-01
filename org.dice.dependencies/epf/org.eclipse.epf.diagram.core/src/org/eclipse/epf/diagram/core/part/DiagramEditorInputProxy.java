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
package org.eclipse.epf.diagram.core.part;

import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.services.IDisposable;

/**
 * @author Phong Nguyen Le
 * 
 * @since 1.2
 */
public class DiagramEditorInputProxy extends DiagramFileEditorInputProxy implements IDisposable
{
	private static final boolean DEBUG = DiagramCorePlugin.getDefault().isDebugging();
	
	private Diagram diagram;

	private PreferencesHint preferenceHint;

	private boolean isNewDiagram;
	
	public DiagramEditorInputProxy(DiagramEditorInput input, PreferencesHint hint) {
		super(input);
		this.preferenceHint = hint;
	}
	
	public void dispose() {
		diagram = null;
	}
	
	private Resource getResource() {
		if(diagram == null || diagram.eResource() == null) {
			Activity act = (Activity) getDiagramEditorInput().getMethodElement();
			org.eclipse.epf.uma.Process process = TngUtil.getOwningProcess(act);
			if(process != null) {
				DiagramManager mgr = DiagramManager.getInstance(process, this);
				try {
					int diagramType = getDiagramEditorInput().getDiagramType();
					List<Diagram> list = mgr.getDiagrams(act, diagramType);
					if(list.isEmpty()) {
						diagram = mgr.createDiagram(act, diagramType, preferenceHint);
						isNewDiagram = diagram != null;
					}
					else {
						diagram = (Diagram) list.get(0);
					}
				}
				catch(Exception e) {
					CommonPlugin.getDefault().getLogger().logError(e);
					if(DEBUG) {
						e.printStackTrace();
					}
				}
				finally {
					mgr.removeConsumer(this);
				}
			}
		}
		return diagram != null ? diagram.eResource() : null;
	}
	
	public IFile getFile() {
		Resource resource = getResource();
		if(resource != null) {
			return WorkspaceSynchronizer.getFile(resource);
		}
		return null;
	}
	
	public IStorage getStorage() throws CoreException {
		final Resource resource = getResource();
		if(resource != null) {
			return new org.eclipse.epf.diagram.core.resources.IDiagramStorage() {
				private IFile file;
				
				private IFile getFile() {
					if(file == null) {
						file = DiagramEditorInputProxy.this.getFile();
					}
					return file;
				}

				public InputStream getContents() throws CoreException {
					return null;
				}

				public IPath getFullPath() {
					return getFile().getFullPath();
				}

				public String getName() {
					return resource.getURI().toString();
				}

				public boolean isReadOnly() {
					return getFile().isReadOnly();
				}

				public Object getAdapter(Class adapter) {
					return getFile().getAdapter(adapter);
				}

				public Diagram getDiagram() {
					return diagram;
				}
				
			};
		}
		return null;
	}
	
	public String getName() {
		try {
			return getDiagramEditorInput().getMethodElement().getName();
		}
		catch(NullPointerException e) {
			return null;
		}
	}
	
	public ImageDescriptor getImageDescriptor() {
		return null;
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.diagram.core.part.DiagramFileEditorInputProxy#exists()
	 */
	public boolean exists() {
		// don't show this input in the list of recently open files
		//
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.diagram.core.part.DiagramFileEditorInputProxy#getPath()
	 */
	public IPath getPath() {
		return getFile().getLocation();
	}
	
	public Object getAdapter(Class adapter) {
		IFile file = getFile();
		if(file != null) {
			if(adapter == IFile.class) {
				return file;
			}
			else {
				return file.getAdapter(adapter);
			}
		}
		return null;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(obj instanceof DiagramEditorInputProxy) {
			return getDiagramEditorInput().equals(((DiagramEditorInputProxy)obj).getDiagramEditorInput());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return getDiagramEditorInput().hashCode();
	}
	
	public PreferencesHint getPreferenceHint() {
		return preferenceHint;
	}
	
	public boolean isNewDiagram() {
		return isNewDiagram;
	}
}
