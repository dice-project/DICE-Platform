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

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * The item provider adapter for a custom category.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class CustomCategoryItemProvider extends
		org.eclipse.epf.uma.provider.CustomCategoryItemProvider implements
		IDefaultNameSetter, ILibraryItemProvider, IListenerProvider {

	/**
	 * Creates a new instance.
	 */
	public CustomCategoryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public void setParent(Object parent) {
		// this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {

		// CustomCategory category = (CustomCategory) object;
		// if(category.eContainer() instanceof CustomCategory) {
		// return super.getParent(object);
		// }
		//		
		// if(parent != null) {
		// return parent;
		// }
		//		
		// if(object instanceof EObject) {
		// MethodPlugin plugin = UmaUtil.getMethodPlugin((EObject) object);
		// //if(plugin == null) return null;
		//			
		// if(plugin != null) {
		// String[] path = {
		// LibraryEditPlugin.INSTANCE.getString("_UI_Content_group")
		// ,LibraryEditPlugin.INSTANCE.getString("_UI_Custom_Categories_group")
		// };
		// return TngUtil.getAdapter(plugin, path);
		// }
		//			
		// }

		if (TngUtil.isRootCustomCategory((CustomCategory) object)) {
			return null;
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
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getCustomCategory_CategorizedElements(), UmaFactory.eINSTANCE
				.createCustomCategory()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			// childrenFeatures.add(UmaPackage.eINSTANCE.getCustomCategory_SubCategories());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getCustomCategory_CategorizedElements());
			// childrenFeatures.add(UmaPackage.eINSTANCE.getCustomCategory_ProcessElements());
		}
		return childrenFeatures;
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

	private static void handleRemoved(CustomCategory category) {
		if (AssociationHelper.getCustomCategories(category).isEmpty()) {
			// remove all subcategories
			//
			for (Iterator iter = category.getCategorizedElements().iterator(); iter
					.hasNext();) {
				Object element = iter.next();
				if (element instanceof CustomCategory) {
					iter.remove();
				}
			}

			EcoreUtil.remove(category);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		setDefaultName(notification);

		updateChildren(notification);

		ContentPackage pkg;
		// boolean refreshCustomCategoryRoot = false;
		switch (notification.getFeatureID(CustomCategory.class)) {
		case UmaPackage.METHOD_ELEMENT__NAME:
			TngUtil.refreshContributors(this, notification, false, true);
			//for epf defect:174043
			if(!TngUtil.isRootCustomCategory((CustomCategory)notification.getNotifier())){
				MethodPlugin plugin = MethodElementUtil.getMethodModel(notification.getNotifier());
				if(plugin !=null){
					CustomCategory root = TngUtil.getRootCustomCategory(plugin);
					fireNotifyChanged(new ViewerNotification(notification, root, true, false));
				}
			}
			break;
		case UmaPackage.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			break;
		// case UmaPackage.CUSTOM_CATEGORY__SUB_CATEGORIES:
		case UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS:
			pkg = (ContentPackage) ((EObject) notification.getNotifier())
					.eContainer();
			if ((pkg != null)
					&& (ModelStructure.HIDDEN_PACKAGE_NAME
							.equals(pkg.getName()))) {
				// notifier is the root custom category
				//
				pkg = (ContentPackage) pkg.eContainer();
			}
			Object obj;
			CustomCategory category;
			switch (notification.getEventType()) {
			case Notification.ADD:
				obj = notification.getNewValue();
				if (obj instanceof CustomCategory) {
					category = (CustomCategory) obj;
					if (category.eContainer() == null) {
						pkg.getContentElements().add(category);
					} else {
						// refreshCustomCategoryRoot = true;
					}
				}
				break;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) notification.getNewValue())
						.iterator(); iter.hasNext();) {
					obj = iter.next();
					if (obj instanceof CustomCategory) {
						category = (CustomCategory) obj;
						if (category.eContainer() == null) {
							pkg.getContentElements().add(category);
						} else {
							// refreshCustomCategoryRoot = true;
						}
					}
				}
				break;
				
				// removing subcategories is handled by the remove command
				//
//			case Notification.REMOVE:
//				obj = notification.getOldValue();
//				if (obj instanceof CustomCategory) {
//					handleRemoved((CustomCategory) obj);
//				}
//				break;
//			case Notification.REMOVE_MANY:
//				for (Iterator iter = ((Collection) notification.getOldValue())
//						.iterator(); iter.hasNext();) {
//					obj = iter.next();
//					if (obj instanceof CustomCategory) {
//						handleRemoved((CustomCategory) obj);
//					}
//				}
//				break;
			}
			
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));

			// // refresh wrappers
			// //
			// if(wrappers != null) {
			// for (Iterator iter = new ArrayList(wrappers).iterator();
			// iter.hasNext();) {
			// fireNotifyChanged(new ViewerNotification(notification,
			// iter.next(), true, false));
			// }
			// }
			//			
			// if(refreshCustomCategoryRoot) {
			// // sub category has been added or removed from the custom
			// category
			// // need to refresh the folder for all custom categories
			// //
			// MethodPlugin plugin = UmaUtil.getMethodPlugin((EObject) target);
			//				
			// if(plugin != null) {
			// String[] path = {
			// LibraryEditPlugin.INSTANCE.getString("_UI_Content_group")
			// ,LibraryEditPlugin.INSTANCE.getString("_UI_Custom_Categories_group")
			// };
			// fireNotifyChanged(new ViewerNotification(notification,
			// TngUtil.getAdapter(plugin, path), true, false));
			// }
			// }

			return;
		}
		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#isWrappingNeeded(java.lang.Object)
	 */
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
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel((VariabilityElement) object,
				getString("_UI_CustomCategory_type"), true); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		CustomCategory category = (CustomCategory) object;
		return !category.getCategorizedElements().isEmpty()
		// || !category.getSubCategories().isEmpty()
		;
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
		if (obj instanceof CustomCategory) {
			String ccName = ((CustomCategory)obj).getName();
			if (ccName == null || ccName.trim().length() == 0) {
				baseName = LibraryEditConstants.NEW_CUSTOM_CATEGORY;
				TngUtil.setDefaultName((CustomCategory) parent, (CustomCategory) obj, baseName);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return CustomCategory.class;
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof CustomCategory) {
				// check custom category for circular dependency
				//
				if (!(owner instanceof DescribableElement && TngUtil
						.isAncessorOf((CustomCategory) element,
								(DescribableElement) owner))) {
					selection.add(element);
				}
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
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createRemoveCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection)
	 */
	protected Command createRemoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection) {
		return super.createRemoveCommand(domain, owner, feature, collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
	}

	public Object getImage(Object object) {
		if (TngUtil.isRootCustomCategory((CustomCategory) object))
			return LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/CustomCategories"); //$NON-NLS-1$
		else {
			if (object instanceof DescribableElement) {
				if (((DescribableElement) object).getNodeicon() != null) {
					URI imgUri = TngUtil.getFullPathofNodeorShapeIconURI(
							(DescribableElement) object,
							((DescribableElement) object).getNodeicon());
					Object image = LibraryEditPlugin.INSTANCE
							.getSharedImage(imgUri);
					if (image != null) {
						//To handle case: during copy/paste, the file may not get copied before this method gets called
						File file = new File (imgUri.getPath());
						if (file.exists()) {					
							return image;
						}
					}
				}
			}
			return super.getImage(object);
		}
	}

	public void setDefaultName(Object obj) {
		// do nothing, already handled by setDefaultName(Notification)
	}

	public List<INotifyChangedListener> getNotifyChangedListeners() {
		if(changeNotifier instanceof ChangeNotifier) {
			return new ArrayList<INotifyChangedListener>((ChangeNotifier) changeNotifier);
		}
		return Collections.emptyList();
	}
	
	public Collection getChildren(Object object) {
		
		Collection col = super.getChildren(object);
		if(TngUtil.isRootCustomCategory((CustomCategory)object)){
			List sortList = new ArrayList();
			sortList.addAll(col);
			Collections.sort(sortList, PresentationContext.INSTANCE.getComparator());
			return sortList;
		} else if (object instanceof MethodElement) {
			List sortList = CategorySortHelper.sortCategoryElements((MethodElement)object, col.toArray());
			return sortList;
		}
		return col;
	}
	
}

