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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a domain.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class DomainItemProvider extends
		org.eclipse.epf.uma.provider.DomainItemProvider implements
		IDefaultNameSetter, ILibraryItemProvider {

	/**
	 * Creates a new instance.
	 */
	public DomainItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
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
				.getDomain_Subdomains(), UmaFactory.eINSTANCE.createDomain()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE.getDomain_Subdomains());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		Domain domain = (Domain) object;
		if (domain.eContainer() instanceof Domain) {
			return super.getParent(object);
		}
		MethodPlugin model = UmaUtil.getMethodPlugin(domain);
		if (model == null)
			return null;

		String[] path = {
				LibraryEditPlugin.INSTANCE.getString("_UI_Content_group") //$NON-NLS-1$
				,
				LibraryEditPlugin.INSTANCE
						.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
				, LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group") //$NON-NLS-1$
		};

		return TngUtil.getAdapter(model, path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel((VariabilityElement) object,
				getString("_UI_Domain_type"), true); //$NON-NLS-1$
	}

	public void notifyChanged(Notification notification) {
		//TngUtil.setDefaultName(this, notification);
		setDefaultName(notification);

		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);
		switch (notification.getFeatureID(Domain.class)) {
		case UmaPackage.METHOD_ELEMENT__NAME:
			TngUtil.refreshContributors(this, notification, false, true);
			break;

		case UmaPackage.DOMAIN__SUBDOMAINS:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		}

		super.notifyChanged(notification);
	}

	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		if (children instanceof List)
			Collections.sort((List) children, PresentationContext.INSTANCE
					.getComparator());
		return children;
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

	protected Collection createWrappers(Collection objects) {
		if (wrappers == null) {
			wrappers = new Disposable();
		}
		FeatureValueWrapperItemProvider.fill(wrappers, null, objects, this,
				adapterFactory);
		ArrayList list = new ArrayList(wrappers);
		return list;
	}

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
		if (obj instanceof Domain) {
			baseName = LibraryEditConstants.NEW_DOMAIN;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((Domain) parent)
					.getSubdomains(), (MethodElement) obj, baseName);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof Domain) {
			baseName = LibraryEditConstants.NEW_DOMAIN;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((Domain) target).getSubdomains(),
					(MethodElement) obj, baseName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.DOMAIN__SUBDOMAINS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return Domain.class;
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof Domain) {
				selection.add(element);
			}
		}
		if (selection.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}

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

}
