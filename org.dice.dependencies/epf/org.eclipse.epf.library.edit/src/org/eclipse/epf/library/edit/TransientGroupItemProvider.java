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
package org.eclipse.epf.library.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.provider.UmaEditPlugin;

/**
 * Item provider for UI transient objects that can be used to group/organize
 * model objects in a view.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class TransientGroupItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IElementItemProvider, IConfigurable, IDefaultNameSetter,
		ILibraryItemProvider {

	protected String name;

	protected IFilter childFilter;

	protected Object parent;

	/**
	 * @param adapterFactory
	 */
	public TransientGroupItemProvider(AdapterFactory adapterFactory,
			Notifier parent) {
		super(adapterFactory);
		parent.eAdapters().add(this);
	}

	/**
	 * @param adapterFactory
	 */
	public TransientGroupItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory);
		parent.eAdapters().add(this);
		this.name = name;
	}

	protected boolean acceptAsChild(Object obj) {
		if (childFilter != null) {
			return childFilter.accept(obj);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		return target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getNewChildDescriptors(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
	 */
	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return super.getNewChildDescriptors(target, editingDomain, sibling);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getResourceLocator()
	 */
	protected ResourceLocator getResourceLocator() {
		return UmaEditPlugin.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createCreateChildCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int,
	 *      java.util.Collection)
	 */
	protected Command createCreateChildCommand(EditingDomain domain,
			EObject owner, EStructuralFeature feature, Object value, int index,
			Collection collection) {
		Command cmd = super.createCreateChildCommand(domain, (EObject) target,
				feature, value, index, collection);
		// System.out.println(getClass().getName() + "#createCreateChildCommand:
		// can execute: " + cmd.canExecute());
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createAddCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection, int)
	 */
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		Collection selection = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (acceptAsChild(element)) {
				selection.add(element);
			}
		}
		if (selection.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}
		return new MethodElementAddCommand(super.createAddCommand(domain,
				(EObject) target, feature, selection, index));
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
		Command cmd = super.createRemoveCommand(domain, (EObject) target,
				feature, collection);
		// System.out.println(getClass().getName() + "#createRemoveCommand: can
		// execute: " + cmd.canExecute());
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createMoveCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int)
	 */
	protected Command createMoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Object value, int index) {
		Command cmd = super.createMoveCommand(domain, (EObject) target,
				feature, value, index);
		// System.out.println(getClass().getName() + "#createMoveCommand: can
		// execute: " + cmd.canExecute());
		return cmd;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createCommand(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
	 *      org.eclipse.emf.edit.command.CommandParameter)
	 */
	public Command createCommand(Object object, EditingDomain domain,
			Class commandClass, CommandParameter commandParameter) {
		if (commandClass == RemoveCommand.class) {
			commandParameter.setOwner(target);
		}
		Command cmd = super.createCommand(object, domain, commandClass,
				commandParameter);
		// System.out.println(getClass().getName() + "#createCommand: " +
		// commandClass + ", can execute: " + cmd.canExecute() + ", owner: " +
		// commandParameter.getOwner() + ", collection: " +
		// commandParameter.getCollection());
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		ChildrenStore store = getChildrenStore(target);
		if (store != null) {
			return store.getChildren();
		}

		store = createChildrenStore(target);
		List result = store != null ? null : new ArrayList();
		EObject eObject = (EObject) target;

		if (target == null) return result;
		
		for (Iterator i = getChildrenFeatures(target).iterator(); i.hasNext();) {
			EStructuralFeature feature = (EStructuralFeature) i.next();
			if (feature.isMany()) {
				List children = (List) eObject.eGet(feature);
				children = new ArrayList(children);
				int index = 0;
				for (Iterator ci = children.iterator(); ci.hasNext(); index++) {
					Object child = ci.next();
					if (acceptAsChild(child)) {
						child = wrap(eObject, feature, child, index);
						if (store != null) {
							store.getList(feature).add(child);
						} else {
							result.add(child);
						}
					}
				}
			} else {
				Object child = eObject.eGet(feature);
				if (acceptAsChild(child)) {
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
		List children = store != null ? store.getChildren() : result;
		Collections.sort(children, PresentationContext.INSTANCE.getComparator());
		
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createDragAndDropCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      java.lang.Object, float, int, int, java.util.Collection)
	 */
	protected Command createDragAndDropCommand(EditingDomain domain,
			Object owner, float location, int operations, int operation,
			Collection collection) {
		boolean canExec = true;
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			if (!acceptAsChild(iter.next())) {
				canExec = false;
				break;
			}
		}
		Command cmd;
		if (canExec) {
			cmd = super.createDragAndDropCommand(domain, owner, location,
					operations, operation, collection);
		} else {
			cmd = UnexecutableCommand.INSTANCE;
		}
		// System.out.println(getClass().getName() + "#createMoveCommand: can
		// execute: " + cmd.canExecute());
		return cmd;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification msg) {
		TngUtil.setDefaultName(this, msg);

		// if(msg.getNotifier() == target) {
		// if(featureId == UmaPackage.CONTENT_PACKAGE__CONTENT_ELEMENTS) {
		// boolean notify = false;
		// switch(msg.getEventType()) {
		// case Notification.ADD:
		// case Notification.MOVE:
		// Object obj = msg.getNewValue();
		// notify = acceptAsChild(obj);
		// break;
		// case Notification.REMOVE:
		// obj = msg.getOldValue();
		// notify = acceptAsChild(obj);
		// break;
		// case Notification.ADD_MANY:
		// Collection collection = (Collection) msg.getNewValue();
		// for_check:
		// for(Iterator iter = collection.iterator(); iter.hasNext();) {
		// if(acceptAsChild(iter.next())) {
		// notify = true;
		// break for_check;
		// }
		// }
		// break;
		// case Notification.REMOVE_MANY:
		// collection = (Collection) msg.getOldValue();
		// for_check:
		// for(Iterator iter = collection.iterator(); iter.hasNext();) {
		// if(acceptAsChild(iter.next())) {
		// notify = true;
		// break for_check;
		// }
		// }
		// break;
		//
		// }
		// if(notify) {
		// fireNotifyChanged(new ViewerNotification(msg, this, true, false));
		// }
		// }
		// }
		super.notifyChanged(msg);
	}

	public String getText(Object object) {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setConfiguration(org.eclipse.epf.uma.MethodConfiguration)
	 */
	public void setFilter(IFilter filter) {
		this.childFilter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
		this.parent = parent;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Folder"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IDefaultNameSetter#getInterestedFeatureClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		return super.getChildrenFeatures(object);
	}

}
