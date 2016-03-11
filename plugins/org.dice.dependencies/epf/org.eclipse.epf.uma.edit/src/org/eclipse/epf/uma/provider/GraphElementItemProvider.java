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
package org.eclipse.epf.uma.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.uma.GraphElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.epf.uma.GraphElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphElementItemProvider extends DiagramElementItemProvider
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphElementItemProvider(AdapterFactory adapterFactory) {
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

			addPositionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Position feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPositionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory)
								.getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_GraphElement_position_feature"), //$NON-NLS-1$
						getString(
								"_UI_PropertyDescriptor_description", "_UI_GraphElement_position_feature", "_UI_GraphElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						UmaPackage.Literals.GRAPH_ELEMENT__POSITION, true,
						false, true, null, null, null));
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
	public Collection<? extends EStructuralFeature> getChildrenFeatures(
			Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED);
			childrenFeatures.add(UmaPackage.Literals.GRAPH_ELEMENT__LINK);
			childrenFeatures.add(UmaPackage.Literals.GRAPH_ELEMENT__ANCHORAGE);
			childrenFeatures
					.add(UmaPackage.Literals.GRAPH_ELEMENT__SEMANTIC_MODEL);
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
		String label = ((GraphElement) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_GraphElement_type") : //$NON-NLS-1$
				getString("_UI_GraphElement_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(GraphElement.class)) {
		case UmaPackage.GRAPH_ELEMENT__CONTAINED:
		case UmaPackage.GRAPH_ELEMENT__LINK:
		case UmaPackage.GRAPH_ELEMENT__ANCHORAGE:
		case UmaPackage.GRAPH_ELEMENT__SEMANTIC_MODEL:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
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
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createGraphNode()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createDiagram()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createReference()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createProperty()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createDiagramLink()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createGraphConnector()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createGraphEdge()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createSimpleSemanticModelElement()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createUMASemanticModelBridge()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createCoreSemanticModelBridge()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createTextElement()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createImage()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createPolyline()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED,
				UmaFactory.eINSTANCE.createEllipse()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__LINK, UmaFactory.eINSTANCE
						.createDiagramLink()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__ANCHORAGE,
				UmaFactory.eINSTANCE.createGraphConnector()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__SEMANTIC_MODEL,
				UmaFactory.eINSTANCE.createSimpleSemanticModelElement()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__SEMANTIC_MODEL,
				UmaFactory.eINSTANCE.createUMASemanticModelBridge()));

		newChildDescriptors.add(createChildParameter(
				UmaPackage.Literals.GRAPH_ELEMENT__SEMANTIC_MODEL,
				UmaFactory.eINSTANCE.createCoreSemanticModelBridge()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == UmaPackage.Literals.DIAGRAM_ELEMENT__PROPERTY
				|| childFeature == UmaPackage.Literals.GRAPH_ELEMENT__CONTAINED
				|| childFeature == UmaPackage.Literals.GRAPH_ELEMENT__LINK
				|| childFeature == UmaPackage.Literals.GRAPH_ELEMENT__ANCHORAGE
				|| childFeature == UmaPackage.Literals.GRAPH_ELEMENT__SEMANTIC_MODEL;

		if (qualify) {
			return getString("_UI_CreateChild_text2", //$NON-NLS-1$
					new Object[] { getTypeText(childObject),
							getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return UmaEditPlugin.INSTANCE;
	}

}
