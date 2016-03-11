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
package org.eclipse.epf.importing.wizards;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ElementDiffTree;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * The label provider for the diff report.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class DiffReportLabelProvider implements ILabelProvider,
		ITableLabelProvider {

	
	/**
	 * Creates a new instance.
	 */
	public DiffReportLabelProvider() {
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object object) {
		return null;
	}

	protected Image getImageFromObject(Object object) {
		return ExtendedImageRegistry.getInstance().getImage(object);
	}

	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object object, int columnIndex) {
		if (columnIndex == 0) {
			return this.getImage(object);
		}

		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object object, int columnIndex) {
		if (object instanceof ElementDiffTree) {
			ElementDiffTree diffTree = (ElementDiffTree) object;
			if (columnIndex == 0) {
				return diffTree.getName();

			} else if (columnIndex == 1) {
				return diffTree.getContentType();
			} else if (columnIndex == 2) {
				return (diffTree.getImportElement() == null) ? "" : ImportResources.DiffReportLabelProvider_yes; //$NON-NLS-1$			
 
			} else if (columnIndex == 3) {
				return (diffTree.getBaseElement() == null) ? "" : ImportResources.DiffReportLabelProvider_yes; //$NON-NLS-1$		
 
			} else if (columnIndex == 4) {
				return diffTree.getDiffMessage();
			}
		}

		return ""; //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return this.getColumnText(object, 0);
	}

	/**
	 * Since we won't ever generate these notifications, we can just ignore
	 * this.
	 */
	public void addListener(ILabelProviderListener listener) {
	}

	/**
	 * Since we won't ever add listeners, we can just ignore this.
	 */
	public void removeListener(ILabelProviderListener listener) {
	}

	/**
	 * Always returns true for now.
	 */
	public boolean isLabelProperty(Object object, String id) {
		return true;
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
	}
}
