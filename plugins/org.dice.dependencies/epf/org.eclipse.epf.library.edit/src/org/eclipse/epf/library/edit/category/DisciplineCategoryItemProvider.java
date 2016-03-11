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
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a discipline grouping.
 * <p>
 * This class will be renamed as DisciplineGroupingItemProvider in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class DisciplineCategoryItemProvider extends
		org.eclipse.epf.uma.provider.DisciplineGroupingItemProvider implements
		IDefaultNameSetter, ILibraryItemProvider {

	protected Object parent;

	/**
	 * Creates a new instance.
	 */
	public DisciplineCategoryItemProvider(AdapterFactory adapterFactory) {
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
				.getDisciplineGrouping_Disciplines(), UmaFactory.eINSTANCE
				.createDiscipline()));
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
					.getDisciplineGrouping_Disciplines());
		}
		return childrenFeatures;
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
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Disciplines_group") //$NON-NLS-1$
				};

				return TngUtil.getAdapter(plugin, path);
			}
		}

		// MethodPlugin model = UmaUtil.getMethodPlugin((MethodElement) object);
		// if(model == null) return null;
		// IGroupContainer groupContainer = (IGroupContainer)
		// TngUtil.getAdapter((EObject)model, IGroupContainer.class);
		// if(groupContainer != null) {
		// groupContainer = (IGroupContainer)
		// groupContainer.getGroupItemProvider(LibraryEditPlugin.INSTANCE.getString("_UI_Content_group"));
		// return
		// groupContainer.getGroupItemProvider(LibraryEditPlugin.INSTANCE.getString("_UI_Disciplines_group"));
		// }
		// // MethodModelItemProvider itemProvider = (MethodModelItemProvider)
		// TngUtil.getAdapter((EObject)model, MethodModelItemProvider.class);
		// // if(itemProvider != null) {
		// // return itemProvider.getDisciplineCategories();
		// // }

		return super.getParent(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		if (children instanceof List)
			Collections.sort((List) children, PresentationContext.INSTANCE
					.getComparator());
		return children;
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
		// getString("_UI_DisciplineGrouping_type"));
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
				getString("_UI_DisciplineGrouping_type"), true); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.DisciplineGroupingItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		// commenting below, useful for stateful itemprovider.
		//TngUtil.setDefaultName(this, notification);
		
		// For stateless itemprovider, need this.
		setDefaultName(notification);
		
		updateChildren(notification);
		ContentPackage pkg;
		Discipline discipline;
		switch (notification.getFeatureID(DisciplineGrouping.class)) {
		case UmaPackage.DISCIPLINE_GROUPING__DISCIPLINES:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));

			boolean refreshParent = false;
			pkg = (ContentPackage) ((EObject) notification.getNotifier())
					.eContainer();
			switch (notification.getEventType()) {
			case Notification.ADD:
				discipline = (Discipline) notification.getNewValue();
				if (discipline.eContainer() == null) {
					pkg.getContentElements().add(discipline);
				} else if (discipline.eContainer() == pkg) {
					refreshParent = true;
				}
				break;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) notification.getNewValue())
						.iterator(); iter.hasNext();) {
					discipline = (Discipline) iter.next();
					if (discipline.eContainer() == null) {
						pkg.getContentElements().add(discipline);
					} else if (discipline.eContainer() == pkg) {
						refreshParent = true;
					}
				}
				break;
			case Notification.REMOVE:
			case Notification.REMOVE_MANY:
				refreshParent = true;
				break;
			}
			if (refreshParent) {
				fireNotifyChanged(new ViewerNotification(notification,
						getParent(target), true, false));
			}
			return;
		}

		super.notifyChanged(notification);
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

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	// */
	// public Collection getChildren(Object object) {
	// Collection children = super.getChildren(object);
	// for (Iterator iter = children.iterator(); iter.hasNext();) {
	// Discipline child = (Discipline) iter.next();
	// DisciplineItemProvider itemProvider = (DisciplineItemProvider)
	// adapterFactory.adapt(child, ITreeItemContentProvider.class);
	// itemProvider.setParent(object);
	// }
	// return children;
	// }

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
		if (obj instanceof Discipline) {
			baseName = LibraryEditConstants.NEW_DISCIPLINE;
		}
		if (baseName != null) {
			// Making plugin level uniqueness of discipline, on removing discipline reference 
			// from discipline grouping, discipline goes back to toplevel causing duplicate. 
			TngUtil.setDefaultName(TngUtil.extract(
					((ContentPackage) (((DisciplineGrouping) parent)
							.eContainer())).getContentElements(),
					Discipline.class), (MethodElement) obj,
					LibraryEditConstants.NEW_DISCIPLINE);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof Discipline) {
			baseName = LibraryEditConstants.NEW_DISCIPLINE;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((DisciplineGrouping) target)
					.getDisciplines(), (MethodElement) obj, baseName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.DISCIPLINE_GROUPING__DISCIPLINES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return DisciplineGrouping.class;
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
		this.parent = parent;
	}

}
