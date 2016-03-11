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
import java.util.MissingResourceException;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a role set grouping.
 * <p>
 * This class will be renamed as RoleSetGroupingItemProvider in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class RoleSetCategoryItemProvider extends
		org.eclipse.epf.uma.provider.RoleSetGroupingItemProvider implements
		IDefaultNameSetter, ILibraryItemProvider, IStatefulItemProvider {

	private Disposable children;

	/**
	 * Creates a new instance.
	 */
	public RoleSetCategoryItemProvider(AdapterFactory adapterFactory) {
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
				.getRoleSetGrouping_RoleSets(), UmaFactory.eINSTANCE
				.createRoleSet()));
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
					.getRoleSetGrouping_RoleSets());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
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
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Role_Sets_group") //$NON-NLS-1$
				};

				return TngUtil.getAdapter(plugin, path);
			}
		}

		// MethodModelItemProvider itemProvider = (MethodModelItemProvider)
		// TngUtil.getAdapter((EObject)model, MethodModelItemProvider.class);
		// if(itemProvider != null) {
		// return itemProvider.getRoleSets();
		// }
		return super.getParent(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.command.CreateChildCommand.Helper#getCreateChildImage(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.util.Collection)
	 */
	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		if (feature instanceof EReference && child instanceof EObject) {
			EReference reference = (EReference) feature;
			EClass parentClass = reference.getEContainingClass();
			EClass childClass = ((EObject) child).eClass();
			String name = "full/ctool16/Create" + parentClass.getName() + "_" + reference.getName() + "_" + childClass.getName(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			try {
				return LibraryEditPlugin.INSTANCE.getImage(name);
			} catch (MissingResourceException e) {
				System.err.println(e.getMessage());
				return null;
			}
		}

		return EMFEditPlugin.INSTANCE.getImage("full/ctool16/CreateChild"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {

		// String label = TngUtil.getLabel(object,
		// getString("_UI_RoleSetGrouping_type"));
		// ContentCategory category = (ContentCategory) object;
		// if(category.getVariabilityBasedOnElement() != null) {
		// MethodPlugin basePlugin =
		// UmaUtil.getMethodPlugin(category.getVariabilityBasedOnElement());
		// if(basePlugin != null) {
		// label = label + " (inherited from " + basePlugin.getName() + ')';
		// }
		// }
		// return label;
		return TngUtil.getLabel((VariabilityElement) object,
				getString("_UI_RoleSetGrouping_type"), true); //$NON-NLS-1$

	}

	// protected void updateChildren(Notification notification) {
	// EObject object = (EObject)notification.getNotifier();
	// ChildrenStore childrenStore = getChildrenStore(object);
	//		
	// if (childrenStore != null)
	// {
	// EStructuralFeature feature =
	// (EStructuralFeature)notification.getFeature();
	// EList children = childrenStore.getList(feature);
	// if (children != null)
	// {
	// int index = notification.getPosition();
	//				
	// switch (notification.getEventType())
	// {
	// case Notification.REMOVE:
	// {
	// EList values = (EList)object.eGet(feature);
	//						
	// if (values.size() == 0)
	// {
	// disposeWrappers(children);
	// return;
	// }
	// break;
	// }
	// }
	// }
	// }
	//		
	// super.updateChildren(notification);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.RoleSetGroupingItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		//TngUtil.setDefaultName(this, notification);
		setDefaultName(notification);
		
		updateChildren(notification);

		ContentPackage pkg;
		switch (notification.getFeatureID(RoleSetGrouping.class)) {
		case UmaPackage.ROLE_SET_GROUPING__ROLE_SETS:

			pkg = (ContentPackage) ((EObject) notification.getNotifier())
					.eContainer();
			RoleSet roleSet;
			boolean refreshParent = false;
			switch (notification.getEventType()) {
			case Notification.ADD:
				roleSet = (RoleSet) notification.getNewValue();
				if (roleSet.eContainer() == null) {
					pkg.getContentElements().add(roleSet);
				} else if (roleSet.eContainer() == pkg) {
					refreshParent = true;
				}
				break;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) notification.getNewValue())
						.iterator(); iter.hasNext();) {
					roleSet = (RoleSet) iter.next();
					if (roleSet.eContainer() == null) {
						pkg.getContentElements().add(roleSet);
					} else if (roleSet.eContainer() == pkg) {
						refreshParent = true;
					}
				}
				break;
			case Notification.REMOVE:
			case Notification.REMOVE_MANY:
				refreshParent = true;
				break;
			}
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			if (refreshParent) {
				fireNotifyChanged(new ViewerNotification(notification,
						getParent(target), true, false));
			}
			return;
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		// for (Iterator iter = children.iterator(); iter.hasNext();) {
		// RoleSet child = (RoleSet) iter.next();
		// RoleSetItemProvider itemProvider = (RoleSetItemProvider)
		// adapterFactory.adapt(child, ITreeItemContentProvider.class);
		// itemProvider.setParent(object);
		// }
		if (children instanceof List)
			Collections.sort((List) children, PresentationContext.INSTANCE.getComparator());
		return children;

		// return createWrappers(children);
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

	// protected Object createWrapper(Object object) {
	// Object wrapper = TngUtil.createWrapper(adapterFactory, (EObject) target,
	// UmaPackage.eINSTANCE.getRoleSetGrouping_RoleSets(), object, -1);
	// if(wrappers == null) {
	// wrappers = new Disposable();
	// }
	// wrappers.add(wrapper);
	// return wrapper;
	// }

	protected Collection createWrappers(Collection objects) {
		if (children == null) {
			children = new Disposable();
		}
		FeatureValueWrapperItemProvider.fill(children, null, objects, target,
				adapterFactory);
		ArrayList list = new ArrayList(children);
		return list;
	}

	public void dispose() {
		if (children != null) {
			children.dispose();
		}

		super.dispose();
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
		if (obj instanceof RoleSet) {
			baseName = LibraryEditConstants.NEW_ROLE_SET;
		}
		if (baseName != null) {
			// Making plugin level uniqueness of roleset, on removing roleset reference 
			// from roleset grouping, roleset goes back to toplevel causing duplicate. 
			TngUtil.setDefaultName(TngUtil.extract(
					((ContentPackage) (((RoleSetGrouping) parent)
							.eContainer())).getContentElements(),
							RoleSet.class), (MethodElement) obj,
					LibraryEditConstants.NEW_ROLE_SET);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof RoleSet) {
			baseName = LibraryEditConstants.NEW_ROLE_SET;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((RoleSetGrouping) target).getRoleSets(),
					(MethodElement) obj, baseName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.ROLE_SET_GROUPING__ROLE_SETS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return RoleSetGrouping.class;
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

}
