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
package org.eclipse.epf.library.edit.navigator;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * The item provider adapter for a practice.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class PracticeItemProvider extends
		org.eclipse.epf.uma.provider.PracticeItemProvider implements IDefaultNameSetter {

	public PracticeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.PracticeItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		
		//User defined type won't have sub practice
		if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)object)) {
			return;
		}
		
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getPractice_SubPractices(), UmaFactory.eINSTANCE
				.createPractice()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.PracticeItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getPractice_SubPractices());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		Object parent = TngUtil
				.getNavigatorParentItemProvider((Guidance) object);
		if (parent == null) {
			return super.getParent(object);
		}
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_Practice_type")); //$NON-NLS-1$
	}

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
		
		if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)object)) {	
			ImageDescriptor img = TngUtil.getImageForUdt((Practice)object);
			if (img != null) {
				return img;
			}
			return overlayImage(object, getResourceLocator().getImage(
			"full/obj16/UdtNode")); //$NON-NLS-1$
		}
				
		return super.getImage(object);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification msg) {
		TngUtil.setDefaultName(this, msg);

		super.notifyChanged(msg);
	}

	public void setDefaultName(Object obj) {
		Practice practice = (Practice) getParent(obj);
		if (obj instanceof Practice) {
			TngUtil.setDefaultName(practice.getSubPractices(),
					(MethodElement) obj, LibraryEditConstants.NEW_PRACTICE); 
		}
	}

	public int getInterestedFeatureID() {
		return UmaPackage.PRACTICE__SUB_PRACTICES;
	}

	public Class getInterestedFeatureOwnerClass() {
		return Practice.class;
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, collection, index));
	}

}