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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for the "Content Packages" folder in the Library
 * view.
 * <p>
 * This class will be renamed as ContentPackagesItemProvider in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ContentPackageItemProvider extends
		org.eclipse.epf.uma.provider.ContentPackageItemProvider implements
		IElementItemProvider, ILibraryItemProvider, IStatefulItemProvider {

	protected RolesItemProvider roles;

	protected TasksItemProvider tasks;

	protected WorkProductsItemProvider workProducts;

	protected GuidancesItemProvider guidances;

	protected UdtElementsItemProvider udtElements;

	protected String label;

	protected Object parent;

	/**
	 * Creates a new instance.
	 */
	public ContentPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @return Returns the roles.
	 */
	public RolesItemProvider getRoles() {
		return roles;
	}

	/**
	 * @return Returns the tasks.
	 */
	public TasksItemProvider getTasks() {
		return tasks;
	}

	/**
	 * @return Returns the workProducts.
	 */
	public WorkProductsItemProvider getWorkProducts() {
		return workProducts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	// public Collection getChildrenFeatures(Object object) {
	// if (childrenFeatures == null) {
	// childrenFeatures = new ArrayList();
	// childrenFeatures.add(UmaPackage.eINSTANCE.getMethodPackage_ChildPackages());
	// }
	// return childrenFeatures;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		List allChildren = new ArrayList();
		ChildrenStore store = getChildrenStore(object);
		List result = null;
		if (store == null) {
			store = createChildrenStore(object);
			result = store != null ? null : new ArrayList();
			EObject eObject = (EObject) object;

			for (Iterator i = getChildrenFeatures(object).iterator(); i
					.hasNext();) {
				EStructuralFeature feature = (EStructuralFeature) i.next();
				if (feature.isMany()) {
					List children = (List) eObject.eGet(feature);
					children = new ArrayList(children);		
					List contentPkgs = new ArrayList();
					int index = 0;
					for (Iterator ci = children.iterator(); ci.hasNext(); index++) {
						Object child = ci.next();
						if (child instanceof ContentPackage) {
							child = wrap(eObject, feature, child, index);
							// if (store != null)
							// {
							// store.getList(feature).add(child);
							// }
							// else
							// {
							// result.add(child);
							// }
							contentPkgs.add(child);
						}
					}
					
					// clear parent for children
					//
					for (Iterator iter = children.iterator(); iter.hasNext();) {
						Object child = (Object) iter.next();
						Object adapter = adapterFactory.adapt(child,
								ITreeItemContentProvider.class);
						if (adapter instanceof ILibraryItemProvider) {
							((ILibraryItemProvider) adapter).setParent(null);
						}
					}

					// sorting the content packages
					//
					Collections.sort(contentPkgs, PresentationContext.INSTANCE
							.getComparator());

					if (store != null) {
						store.getList(feature).addAll(contentPkgs);
					} else {
						result.addAll(contentPkgs);
					}
				} else {
					Object child = eObject.eGet(feature);
					if (child instanceof ContentPackage) {
						child = wrap(eObject, feature, child,
								CommandParameter.NO_INDEX);
						if (store != null) {
							store.setValue(feature, child);
						} else {
							result.add(child);
						}
					}
				}
			}
		}
		allChildren.addAll(store != null ? store.getChildren() : result);
		ContentPackage contentPkg = (ContentPackage) object;

		// remove old adapters (roles, tasks, workproducts) from the the
		// contentPkg
		//
		// EObject eObj = (EObject) object;
		if (roles == null) {
			// eObj.eAdapters().remove(roles);
			roles = new RolesItemProvider(adapterFactory, contentPkg);
		}
		if (tasks == null) {
			// eObj.eAdapters().remove(tasks);
			tasks = new TasksItemProvider(adapterFactory, contentPkg);
		}
		if (workProducts == null) {
			// eObj.eAdapters().remove(workProducts);
			workProducts = new WorkProductsItemProvider(adapterFactory,
					contentPkg);
		}
		if (guidances == null) {
			// eObj.eAdapters().remove(guidances);
			guidances = new GuidancesItemProvider(adapterFactory, contentPkg);
		}

		if (udtElements == null) {
			udtElements = new UdtElementsItemProvider(adapterFactory, contentPkg);
		}
		
		allChildren.add(roles);
		allChildren.add(tasks);
		allChildren.add(workProducts);
		allChildren.add(guidances);
		allChildren.add(udtElements);
		
		if(udtElements.getChildren(null).isEmpty()) {
			allChildren.remove(udtElements);
			udtElements = null;
		}
		return allChildren;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
				.createContentPackage()));
	}

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.edit.provider.ItemProviderAdapter#createCommand(java.lang.Object,
	// org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
	// org.eclipse.emf.edit.command.CommandParameter)
	// */
	// public Command createCommand(Object object, EditingDomain domain,
	// Class commandClass, CommandParameter commandParameter) {
	// // commandParameter.setOwner(((EObject)object).eContainer());
	// Command cmd = super.createCommand(object, domain, commandClass,
	// commandParameter);
	// System.out.println(getClass().getName() + "#createCommand: " +
	// commandClass + ", can execute: " + cmd.canExecute() + ", owner: " +
	// commandParameter.getOwner() + ", collection: " +
	// commandParameter.getCollection());
	// return cmd;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		String txt;
		if (label != null) {
			txt = label;
		} else {
			txt = TngUtil
					.getLabel(object, getString("_UI_ContentPackage_type")); //$NON-NLS-1$
		}
		return txt;
	}

	private void setDefaultName(Object obj) {
		ContentPackage contentPackage = (ContentPackage) target;
		if (obj instanceof ContentPackage) {
			TngUtil.setDefaultName(contentPackage.getChildPackages(),
					(MethodElement) obj, "content_package"); //$NON-NLS-1$
		} else {
			TngUtil.setDefaultName(contentPackage.getChildPackages(),
					(MethodElement) obj, "method_package"); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification msg) {
		TngUtil.refreshParentIfNameChanged(msg, this);

		switch (msg.getFeatureID(ContentPackage.class)) {
		case UmaPackage.CONTENT_PACKAGE__CONTENT_ELEMENTS:
			return;
		case UmaPackage.CONTENT_PACKAGE__CHILD_PACKAGES:
			switch (msg.getEventType()) {
			case Notification.ADD:
				setDefaultName(msg.getNewValue());
				break;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) msg.getNewValue())
						.iterator(); iter.hasNext();) {
					setDefaultName(iter.next());
				}
				break;
			}
		}

		super.notifyChanged(msg);
	}

	/**
	 * @return
	 */
	public GuidancesItemProvider getGuidances() {
		return guidances;
	}
	
	public UdtElementsItemProvider getUdtElements() {
		return udtElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		return super.getParent(object);
	}

	// public Object getImage(Object object) {
	// return TngEditPlugin.INSTANCE.getImage("full/obj16/MethodPackages");
	// }

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		ArrayList selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();

			// make sure that you cannot add MethodPackage that is not a
			// ContentPackage
			//
			if (element instanceof MethodPackage
					&& !(element instanceof ContentPackage)) {
				continue;
			}

			selection.add(element);
		}

		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, selection, index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (guidances != null) {
			guidances.dispose();
			guidances = null;
		}
		if (roles != null) {
			roles.dispose();
			roles = null;
		}
		if (tasks != null) {
			tasks.dispose();
			tasks = null;
		}
		if (workProducts != null) {
			workProducts.dispose();
			workProducts = null;
		}
		if (udtElements != null) {
			udtElements.dispose();
			udtElements = null;
		}
		super.dispose();
	}

}
