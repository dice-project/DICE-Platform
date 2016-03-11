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
/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.core.part;

import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * DiagramEditor Input for {@link BreakdownElement} 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 */
public class DiagramEditorInput implements IEditorInput {

	private MethodElement methodElement;

	private ImageDescriptor imageDescriptor = null;
	
	private Suppression suppression;

	private BreakdownElementWrapperItemProvider wrapper;

	private int diagramType;

	/**
	 * Creates an instance of DiagramEditorInput for activity diagram
	 * @param object
	 * @deprecated use {@link #DiagramEditorInput(Object, Suppression, int)}
	 */
	public DiagramEditorInput(Object object, Suppression suppression) {
		this(object, suppression, IDiagramManager.ACTIVITY_DIAGRAM);
	}
	
	/**
	 * 
	 * @param object
	 * @param suppression
	 * @param diagramType
	 *            can be one of the constants:
	 *            {@link IDiagramManager#ACTIVITY_DIAGRAM},
	 *            {@link IDiagramManager#ACTIVITY_DETAIL_DIAGRAM},
	 *            {@link IDiagramManager#WORK_PRODUCT_DEPENDENCY_DIAGRAM}
	 */
	public DiagramEditorInput(Object object, Suppression suppression, int diagramType) {
		methodElement = (MethodElement) TngUtil.unwrap(object);
		if (object instanceof BreakdownElementWrapperItemProvider) {
			wrapper = (BreakdownElementWrapperItemProvider) object;
		}

		this.suppression = suppression;
		this.diagramType = diagramType;
	}
	
	public int getDiagramType() {
		return diagramType;
	}

	/**
	 * @return Returns the suppression.
	 */
	public Suppression getSuppression() {
		return suppression;
	}

	/**
	 * @return Returns the wrapper.
	 */
	public BreakdownElementWrapperItemProvider getWrapper() {
		return wrapper;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass().isInstance(obj)) {
			DiagramEditorInput other = (DiagramEditorInput) obj;
			return getMethodElement() == other.getMethodElement()
					&& ((suppression == null && other.getSuppression() == null) || 
							(suppression.getProcess() == other.getSuppression().getProcess()))
					&& getDiagramType() == other.getDiagramType();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = getMethodElement().hashCode();
		result ^= suppression.getProcess().hashCode();
		result ^= getDiagramType();
		return result;
	}
	
	public DiagramEditorInput(MethodElement e) {
		methodElement = e;
	}

	public MethodElement getMethodElement() {
		return methodElement;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return imageDescriptor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {
		return methodElement.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return ""; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
