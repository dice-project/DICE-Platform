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
package org.eclipse.epf.library.edit.element;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for an artifact.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ArtifactItemProvider extends
		org.eclipse.epf.uma.provider.ArtifactItemProvider implements
		IDefaultNameSetter, ILibraryItemProvider, IConfigurable {

	private IFilter filter;

	/**
	 * Creates a new instance.
	 */
	public ArtifactItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Object getParent(Object object) {
		Artifact artifact = (Artifact) object;
		if (artifact != null && artifact.eContainer() instanceof Artifact) {
			return super.getParent(object);
		}
		return TngUtil.getNavigatorParentItemProvider(artifact);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getArtifact_ContainedArtifacts(), UmaFactory.eINSTANCE
				.createArtifact()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {

		//TngUtil.setDefaultName(this, notification);
		setDefaultName(notification);
		
		updateChildren(notification);
		
		TngUtil.refreshParentIfNameChanged(notification, this);
		
		switch (notification.getFeatureID(Discipline.class)) {
		case UmaPackage.METHOD_ELEMENT__NAME:
			TngUtil.refreshContributors(this, notification, false, true);
			break;
		case UmaPackage.WORK_PRODUCT_TYPE__WORK_PRODUCTS:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			TngUtil.refreshWorkProductTypeUncategorized(UmaUtil
					.getMethodPlugin((EObject) target), notification);
			return;
		case UmaPackage.ARTIFACT__CONTAINED_ARTIFACTS:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getArtifact_ContainedArtifacts());
		}
		return childrenFeatures;
	}
	
	/**
	 * Method to setDefaultName. IDefaultNameSetter cannot be used 
	 * in case of Stateless ItemProvider.
	 * This is work-around for now. IDefaultNameSetter will be modified later.
	 */
	public void setDefaultName(Notification msg) {
		IDefaultNameSetter defaultNameSetter = this;
		if (defaultNameSetter.getInterestedFeatureOwnerClass() != null) {
			int featureId = msg.getFeatureID(defaultNameSetter
					.getInterestedFeatureOwnerClass());
			if (featureId == defaultNameSetter.getInterestedFeatureID()) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					setDefaultName(msg.getNewValue(), msg.getNotifier());
					break;
				case Notification.ADD_MANY:
					for (Iterator iter = ((Collection) msg.getNewValue())
							.iterator(); iter.hasNext();) {
						setDefaultName(iter.next(), msg.getNotifier());
					}
					break;
				}
			}
		}
	}

	private void setDefaultName(Object obj, Object parent) {
		String baseName = null;
		if (obj instanceof Artifact) {
			baseName = LibraryEditConstants.NEW_ARTIFACT;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((Artifact) parent)
					.getContainedArtifacts(), (MethodElement) obj, baseName);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#setDefaultName(java.lang.Object)
	 * not in use (for stateless itemprovider) see above setDefaultName(Object obj, Object parent).
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof Artifact) {
			baseName = LibraryEditConstants.NEW_ARTIFACT;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((Artifact) target).getContainedArtifacts(),
					(MethodElement) obj, baseName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.ARTIFACT__CONTAINED_ARTIFACTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return Artifact.class;
	}

	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_Artifact_type")); //$NON-NLS-1$
	}

	public Object getImage(Object object) {
		if (object instanceof DescribableElement) {
			if (((DescribableElement) object).getNodeicon() != null) {
				URI imgUri = TngUtil.getFullPathofNodeorShapeIconURI(
						(DescribableElement) object,
						((DescribableElement) object).getNodeicon());
				if (imgUri != null) {
					Object image = LibraryEditPlugin.INSTANCE
							.getSharedImage(imgUri);
					if (image != null)
						return image;
				}
			}
		}
		return super.getImage(object);
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, collection, index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IMethodElementItemProvider#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public void setLabel(String label) {
	}

	public Collection getChildren(Object object) {
		// return super.getChildren(object);
		Collection col = super.getChildren(object);
		if (filter != null) {
			for (Iterator itor = col.iterator(); itor.hasNext();) {
				Object child = itor.next();
				if (!filter.accept(child)) {
					itor.remove();
				}
			}
		}
		return col;
	}

	// private boolean isParentsAccepted(Object child) {
	// List list = new ArrayList();
	// getHierarchyList(list, child);
	// for (Iterator itor = list.iterator(); itor.hasNext();) {
	// Object obj = itor.next();
	// if (obj instanceof Artifact) {
	// if (filter.accept(obj))
	// return true;
	// }
	// }
	// return false;
	// }
	//
	// private boolean checkChildrensAccepted(Object object) {
	// Artifact parentartifact = ((Artifact) object).getContainerArtifact();
	// if (parentartifact != null) {
	// if (filter != null) {
	// if (filter.accept(parentartifact))
	// return true;
	// else {
	// return checkChildrensAccepted(parentartifact);
	// }
	// }
	// }
	// return false;
	// }
	//
	// private void getHierarchyList(List list, Object object) {
	// Artifact parent = ((Artifact) object).getContainerArtifact();
	// if (parent instanceof Artifact) {
	// list.add(parent);
	// getHierarchyList(list, parent);
	// }
	// }

}
