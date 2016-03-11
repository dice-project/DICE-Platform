/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.epf.diagram.ad.expressions.UMLAbstractExpression;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.gmf.runtime.common.ui.services.icon.IconService;
import org.eclipse.gmf.runtime.diagram.ui.geoshapes.internal.providers.GeoShapeIconProvider;
import org.eclipse.gmf.runtime.diagram.ui.geoshapes.internal.providers.GeoshapeConstants;
import org.eclipse.gmf.runtime.diagram.ui.util.INotationType;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private UMLElementTypes() {
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
	
	private static GeoShapeIconProvider geoShapeIconProvider = null;

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
				return ActivityDiagramEditorPlugin.getInstance()
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
	 * @modified
	 */
	public static Image getImage(IAdaptable hint) {
		if(isGeoShape(hint)){
			return getGeoShapeIconProvider().getIcon(((INotationType)hint).getSemanticHint());
		}
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		// modified START
		Object type = hint.getAdapter(IElementType.class);
		if (type != null) {
			Image image = getImage(element, (IElementType) type);
			if (image != null) {
				return image;
			}
		}
		// modified END
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

			elements.put(Activity_79, UMLPackage.eINSTANCE.getActivity());

			elements.put(ActivityFinalNode_1001, UMLPackage.eINSTANCE
					.getActivityFinalNode());

			elements.put(MergeNode_1002, UMLPackage.eINSTANCE.getMergeNode());

			elements.put(ForkNode_1003, UMLPackage.eINSTANCE.getForkNode());

			elements.put(InitialNode_1004, UMLPackage.eINSTANCE
					.getInitialNode());

			elements.put(DecisionNode_1005, UMLPackage.eINSTANCE
					.getDecisionNode());

			elements.put(JoinNode_1006, UMLPackage.eINSTANCE.getJoinNode());

			elements.put(StructuredActivityNode_1007, UMLPackage.eINSTANCE
					.getStructuredActivityNode());

			elements.put(ActivityPartition_1008, UMLPackage.eINSTANCE
					.getActivityPartition());

			elements.put(ActivityParameterNode_1009, UMLPackage.eINSTANCE
					.getActivityParameterNode());

			elements.put(StructuredActivityNode_1010, UMLPackage.eINSTANCE
					.getStructuredActivityNode());

			elements.put(StructuredActivityNode_1011, UMLPackage.eINSTANCE
					.getStructuredActivityNode());

			elements.put(ActivityParameterNode_1012, UMLPackage.eINSTANCE
					.getActivityParameterNode());

			elements.put(ActivityPartition_2001, UMLPackage.eINSTANCE
					.getActivityPartition());

			elements.put(ControlFlow_3001, UMLPackage.eINSTANCE
					.getControlFlow());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	public static final IElementType Activity_79 = getElementType("org.eclipse.epf.diagram.ad.Activity_79"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ActivityPartition_2001 = getElementType("org.eclipse.epf.diagram.ad.ActivityPartition_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ActivityFinalNode_1001 = getElementType("org.eclipse.epf.diagram.ad.ActivityFinalNode_1001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType MergeNode_1002 = getElementType("org.eclipse.epf.diagram.ad.MergeNode_1002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ForkNode_1003 = getElementType("org.eclipse.epf.diagram.ad.ForkNode_1003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InitialNode_1004 = getElementType("org.eclipse.epf.diagram.ad.InitialNode_1004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType DecisionNode_1005 = getElementType("org.eclipse.epf.diagram.ad.DecisionNode_1005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType JoinNode_1006 = getElementType("org.eclipse.epf.diagram.ad.JoinNode_1006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType StructuredActivityNode_1007 = getElementType("org.eclipse.epf.diagram.ad.StructuredActivityNode_1007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ActivityPartition_1008 = getElementType("org.eclipse.epf.diagram.ad.ActivityPartition_1008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ActivityParameterNode_1009 = getElementType("org.eclipse.epf.diagram.ad.ActivityParameterNode_1009"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType StructuredActivityNode_1010 = getElementType("org.eclipse.epf.diagram.ad.StructuredActivityNode_1010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType StructuredActivityNode_1011 = getElementType("org.eclipse.epf.diagram.ad.StructuredActivityNode_1011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ActivityParameterNode_1012 = getElementType("org.eclipse.epf.diagram.ad.ActivityParameterNode_1012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ControlFlow_3001 = getElementType("org.eclipse.epf.diagram.ad.ControlFlow_3001"); //$NON-NLS-1$

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
			KNOWN_ELEMENT_TYPES.add(Activity_79);
			KNOWN_ELEMENT_TYPES.add(ActivityFinalNode_1001);
			KNOWN_ELEMENT_TYPES.add(MergeNode_1002);
			KNOWN_ELEMENT_TYPES.add(ForkNode_1003);
			KNOWN_ELEMENT_TYPES.add(InitialNode_1004);
			KNOWN_ELEMENT_TYPES.add(DecisionNode_1005);
			KNOWN_ELEMENT_TYPES.add(JoinNode_1006);
			KNOWN_ELEMENT_TYPES.add(StructuredActivityNode_1007);
			KNOWN_ELEMENT_TYPES.add(ActivityPartition_1008);
			KNOWN_ELEMENT_TYPES.add(ActivityParameterNode_1009);
			KNOWN_ELEMENT_TYPES.add(StructuredActivityNode_1010);
			KNOWN_ELEMENT_TYPES.add(StructuredActivityNode_1011);
			KNOWN_ELEMENT_TYPES.add(ActivityParameterNode_1012);
			KNOWN_ELEMENT_TYPES.add(ActivityPartition_2001);
			KNOWN_ELEMENT_TYPES.add(ControlFlow_3001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @modified
	 */
	public static Image getImage(ENamedElement element, IElementType type) {
		String key = type.getId();
		Image image = getImageRegistry().get(key);

		if (image == null) {
			ImageDescriptor imageDescriptor = null;
			if (key.equals(StructuredActivityNode_1007.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Activity.gif"); //$NON-NLS-1$
			} else if (key.equals(StructuredActivityNode_1010.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Phase.gif"); //$NON-NLS-1$
			} else if (key.equals(StructuredActivityNode_1011.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Iteration.gif"); //$NON-NLS-1$
			} else if (key.equals(ActivityParameterNode_1012.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Milestone.gif"); //$NON-NLS-1$
			} else if (key.equals(ActivityParameterNode_1009.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/TaskDescriptor.gif"); //$NON-NLS-1$
			}
			if (imageDescriptor != null) {
				getImageRegistry().put(key, imageDescriptor);
				image = getImageRegistry().get(key);
			}
		}
		return image;
	}

	/**
	 * @modified
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element,
			IElementType type) {
		String key = type.getId();
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);

		if (imageDescriptor == null) {
			imageDescriptor = null;
			if (key.equals(StructuredActivityNode_1007.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Activity.gif"); //$NON-NLS-1$
			} else if (key.equals(StructuredActivityNode_1010.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Phase.gif"); //$NON-NLS-1$
			} else if (key.equals(StructuredActivityNode_1011.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Iteration.gif"); //$NON-NLS-1$
			} else if (key.equals(ActivityParameterNode_1012.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/Milestone.gif"); //$NON-NLS-1$
			} else if (key.equals(ActivityParameterNode_1009.getId())) {
				imageDescriptor = ActivityDiagramEditorPlugin.getInstance()
						.getImageDescriptor("full/obj16/TaskDescriptor.gif"); //$NON-NLS-1$
			}
			if (imageDescriptor != null) {
				getImageRegistry().put(key, imageDescriptor);
				imageDescriptor = getImageRegistry().getDescriptor(key);
			} else {
				imageDescriptor = getImageDescriptor(element);
			}
		}
		return imageDescriptor;
	}

	private static boolean isGeoShape(IAdaptable hint) {
		if(hint instanceof INotationType){
			String x = ((INotationType)hint).getSemanticHint();
			if(GeoshapeConstants.getSupportedShapes().contains(x))
				return true;
		}
		return false;
	}
	
	private static GeoShapeIconProvider getGeoShapeIconProvider(){
		if(geoShapeIconProvider == null){
			geoShapeIconProvider = new GeoShapeIconProvider();
		}
		return geoShapeIconProvider;
	}
}
