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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Standard Categories" folder.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class StandardCategoriesItemProvider extends TransientGroupItemProvider
		implements IGroupContainer {

	private ArrayList children;

	private Map groupItemProviderMap;

	public StandardCategoriesItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory, parent, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getMethodPackage_ChildPackages(),
		// UmaFactory.eINSTANCE.createContentPackage()));
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
					.getMethodPackage_ChildPackages());
		}
		return childrenFeatures;
	}

	protected boolean acceptAsChild(Object obj) {
		return obj instanceof ContentPackage;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE
				.getImage("full/obj16/StandardCategories"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification msg) {
		if (msg.getNotifier() == target) {
			int featureId = msg.getFeatureID(ContentPackage.class);
			if (featureId == UmaPackage.CONTENT_PACKAGE__CHILD_PACKAGES) {
				boolean notify = false;
				switch (msg.getEventType()) {
				case Notification.ADD:
				case Notification.MOVE:
					Object obj = msg.getNewValue();
					notify = acceptAsChild(obj);
					break;
				case Notification.REMOVE:
					obj = msg.getOldValue();
					notify = acceptAsChild(obj);
					break;
				case Notification.ADD_MANY:
					Collection collection = (Collection) msg.getNewValue();
					for_check: for (Iterator iter = collection.iterator(); iter
							.hasNext();) {
						if (acceptAsChild(iter.next())) {
							notify = true;
							break for_check;
						}
					}
					break;
				case Notification.REMOVE_MANY:
					collection = (Collection) msg.getOldValue();
					for_check: for (Iterator iter = collection.iterator(); iter
							.hasNext();) {
						if (acceptAsChild(iter.next())) {
							notify = true;
							break for_check;
						}
					}
					break;

				}
				if (notify) {
					fireNotifyChanged(new ViewerNotification(msg, this, true,
							false));
				}
			}
		}
		super.notifyChanged(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		// if(obj instanceof ContentPackage) {
		// TngUtil.setDefaultName(((ContentPackage)target).getChildPackages(),
		// (MethodElement) obj, "New Content Package");
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.CONTENT_PACKAGE__CHILD_PACKAGES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return ContentPackage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			groupItemProviderMap = new HashMap();
			String name;
			ILibraryItemProvider child;

			// create the disciplines categories folder
			MethodPlugin plugin = UmaUtil
					.getMethodPlugin((Element) ((ItemProviderAdapter) object)
							.getTarget());
			ContentPackage contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.disciplineDefinitionPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_Disciplines_group"); //$NON-NLS-1$
				child = new DisciplineCategoriesItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);

			}

			// create domain categories folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.domainPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_Domains_group"); //$NON-NLS-1$
				child = new DomainsItemProvider(adapterFactory, contentPkg,
						name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			// create work product types folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.workProductTypePath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_WorkProductTypes_group"); //$NON-NLS-1$
				child = new WorkProductTypesItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			// create role set folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.roleSetPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_Role_Sets_group"); //$NON-NLS-1$
				child = new RoleSetsItemProvider(adapterFactory, contentPkg,
						name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			// create tool folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.toolPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group"); //$NON-NLS-1$
				child = new ToolsItemProvider(adapterFactory, contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}
		}
		return children;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}

}
