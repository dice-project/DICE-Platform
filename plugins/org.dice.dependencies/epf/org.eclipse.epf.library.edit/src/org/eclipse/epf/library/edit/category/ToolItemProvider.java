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
package org.eclipse.epf.library.edit.category;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter a tool.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ToolItemProvider extends org.eclipse.epf.uma.provider.ToolItemProvider
		implements ILibraryItemProvider, IStatefulItemProvider {

	private Object parent;

	/**
	 * Creates a new instance.
	 */
	public ToolItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		if (object instanceof DescribableElement) {
			if (((DescribableElement) object).getNodeicon() != null) {
				URI imgUri = TngUtil.getFullPathofNodeorShapeIconURI(
						(DescribableElement) object,
						((DescribableElement) object).getNodeicon());
				Object image = LibraryEditPlugin.INSTANCE
						.getSharedImage(imgUri);
				if (image != null)
					return image;
			}
		}
		
		return super.getImage(object);
	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null) {
			return parent;
		}

		if (object instanceof EObject) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin((EObject) object);
			if (plugin != null) {
				String[] path = {
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Content_group") //$NON-NLS-1$
						,
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
						,
						LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group") //$NON-NLS-1$
				};

				return TngUtil.getAdapter(plugin, path);
			}
		}

		return super.getParent(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE.getTool_ToolMentors());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		switch (notification.getFeatureID(Discipline.class)) {
		case UmaPackage.METHOD_ELEMENT__NAME:
			TngUtil.refreshContributors(this, notification, false, true);
			break;
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel((VariabilityElement) object,
				getString("_UI_Tool_type"), true); //$NON-NLS-1$
	}

	protected boolean isWrappingNeeded(Object object) {
		return true;
	}

	protected Object createWrapper(EObject object, EStructuralFeature feature,
			Object value, int index) {
		if (!isWrappingNeeded(object))
			return value;
		return TngUtil.createWrapper(adapterFactory, object, feature, value,
				index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		return false;
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return UnexecutableCommand.INSTANCE;
	}

}
