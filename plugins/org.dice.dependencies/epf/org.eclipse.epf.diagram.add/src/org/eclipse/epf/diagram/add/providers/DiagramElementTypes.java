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
package org.eclipse.epf.diagram.add.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;

import org.eclipse.epf.diagram.model.ModelPackage;

/**
 * @generated
 */
public class DiagramElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private DiagramElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return ActivityDetailDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(ActivityDetailDiagram_79, ModelPackage.eINSTANCE
					.getActivityDetailDiagram());

			elements.put(RoleTaskComposite_1001, ModelPackage.eINSTANCE
					.getRoleTaskComposite());

			elements.put(WorkProductComposite_1002, ModelPackage.eINSTANCE
					.getWorkProductComposite());

			elements.put(RoleNode_2001, ModelPackage.eINSTANCE.getRoleNode());

			elements.put(TaskNode_2002, ModelPackage.eINSTANCE.getTaskNode());

			elements.put(WorkProductDescriptorNode_2003, ModelPackage.eINSTANCE
					.getWorkProductDescriptorNode());

			elements.put(Link_3001, ModelPackage.eINSTANCE.getLink());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	public static final IElementType ActivityDetailDiagram_79 = getElementType("org.eclipse.epf.diagram.add.ActivityDetailDiagram_79"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType RoleNode_2001 = getElementType("org.eclipse.epf.diagram.add.RoleNode_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TaskNode_2002 = getElementType("org.eclipse.epf.diagram.add.TaskNode_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType WorkProductDescriptorNode_2003 = getElementType("org.eclipse.epf.diagram.add.WorkProductDescriptorNode_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType RoleTaskComposite_1001 = getElementType("org.eclipse.epf.diagram.add.RoleTaskComposite_1001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType WorkProductComposite_1002 = getElementType("org.eclipse.epf.diagram.add.WorkProductComposite_1002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Link_3001 = getElementType("org.eclipse.epf.diagram.add.Link_3001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(ActivityDetailDiagram_79);
			KNOWN_ELEMENT_TYPES.add(RoleTaskComposite_1001);
			KNOWN_ELEMENT_TYPES.add(WorkProductComposite_1002);
			KNOWN_ELEMENT_TYPES.add(RoleNode_2001);
			KNOWN_ELEMENT_TYPES.add(TaskNode_2002);
			KNOWN_ELEMENT_TYPES.add(WorkProductDescriptorNode_2003);
			KNOWN_ELEMENT_TYPES.add(Link_3001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}
}
