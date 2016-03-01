//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
/**
 * <copyright>
 * </copyright>
 *
 * $Id: NodeContainerItemProvider.java,v 1.1 2008/01/15 08:53:03 jtham Exp $
 */
package org.eclipse.epf.diagram.model.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NodeContainer;

/**
 * This is the item provider adapter for a {@link org.eclipse.epf.diagram.model.NodeContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NodeContainerItemProvider
	extends NodeItemProvider
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeContainerItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ModelPackage.Literals.NODE_CONTAINER__NODES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Point labelValue = ((NodeContainer)object).getLocation();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_NodeContainer_type") : //$NON-NLS-1$
			getString("_UI_NodeContainer_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(NodeContainer.class)) {
			case ModelPackage.NODE_CONTAINER__NODES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createDiagram()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createNamedNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createActivityDiagram()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createTypedNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createWorkProductDependencyDiagram()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createWorkProductNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createActivityDetailDiagram()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createRoleNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createRoleTaskComposite()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createTaskNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createWorkProductDescriptorNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createWorkBreakdownElementNode()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.NODE_CONTAINER__NODES,
				 ModelFactory.eINSTANCE.createWorkProductComposite()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DiagramEditPlugin.INSTANCE;
	}

}
