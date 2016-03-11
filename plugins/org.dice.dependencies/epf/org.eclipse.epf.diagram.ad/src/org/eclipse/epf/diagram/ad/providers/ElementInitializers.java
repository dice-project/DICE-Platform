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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.epf.diagram.ad.expressions.UMLAbstractExpression;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class ElementInitializers {

	/**
	 * @generated
	 */
	public static class Initializers {
		/**
		 * @generated
		 */
		public static final IObjectInitializer StructuredActivityNode_1007 = new ObjectInitializer(
				UMLPackage.eINSTANCE.getStructuredActivityNode()) {
			protected void init() {
				add(createExpressionFeatureInitializer(EcorePackage.eINSTANCE
						.getEModelElement_EAnnotations(),
						new UMLAbstractExpression(UMLPackage.eINSTANCE
								.getStructuredActivityNode()) {
							protected Object doEvaluate(Object context, Map env) {
								StructuredActivityNode self = (StructuredActivityNode) context;
								return Java.createUmaType_Activity(self);
							}
						}));
			}
		}; // StructuredActivityNode_1007 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ActivityParameterNode_1009 = new ObjectInitializer(
				UMLPackage.eINSTANCE.getActivityParameterNode()) {
			protected void init() {
				add(createExpressionFeatureInitializer(EcorePackage.eINSTANCE
						.getEModelElement_EAnnotations(),
						new UMLAbstractExpression(UMLPackage.eINSTANCE
								.getActivityParameterNode()) {
							protected Object doEvaluate(Object context, Map env) {
								ActivityParameterNode self = (ActivityParameterNode) context;
								return Java.createUmaType_TaskDescriptor(self);
							}
						}));
			}
		}; // ActivityParameterNode_1009 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer StructuredActivityNode_1010 = new ObjectInitializer(
				UMLPackage.eINSTANCE.getStructuredActivityNode()) {
			protected void init() {
				add(createExpressionFeatureInitializer(EcorePackage.eINSTANCE
						.getEModelElement_EAnnotations(),
						new UMLAbstractExpression(UMLPackage.eINSTANCE
								.getStructuredActivityNode()) {
							protected Object doEvaluate(Object context, Map env) {
								StructuredActivityNode self = (StructuredActivityNode) context;
								return Java.createUmaType_Phase(self);
							}
						}));
			}
		}; // StructuredActivityNode_1010 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer StructuredActivityNode_1011 = new ObjectInitializer(
				UMLPackage.eINSTANCE.getStructuredActivityNode()) {
			protected void init() {
				add(createExpressionFeatureInitializer(EcorePackage.eINSTANCE
						.getEModelElement_EAnnotations(),
						new UMLAbstractExpression(UMLPackage.eINSTANCE
								.getStructuredActivityNode()) {
							protected Object doEvaluate(Object context, Map env) {
								StructuredActivityNode self = (StructuredActivityNode) context;
								return Java.createUmaType_Iteration(self);
							}
						}));
			}
		}; // StructuredActivityNode_1011 ObjectInitializer		
		/**
		 * @generated
		 */
		public static final IObjectInitializer ActivityParameterNode_1012 = new ObjectInitializer(
				UMLPackage.eINSTANCE.getActivityParameterNode()) {
			protected void init() {
				add(createExpressionFeatureInitializer(EcorePackage.eINSTANCE
						.getEModelElement_EAnnotations(),
						new UMLAbstractExpression(UMLPackage.eINSTANCE
								.getActivityParameterNode()) {
							protected Object doEvaluate(Object context, Map env) {
								ActivityParameterNode self = (ActivityParameterNode) context;
								return Java.createUmaType_Milestone(self);
							}
						}));
			}
		}; // ActivityParameterNode_1012 ObjectInitializer

		/** 
		 * @generated
		 */
		private Initializers() {
		}

		/** 
		 * @generated
		 */
		public static interface IObjectInitializer {
			/** 
			 * @generated
			 */
			public void init(EObject instance);
		}

		/** 
		 * @generated
		 */
		public static abstract class ObjectInitializer implements
				IObjectInitializer {
			/** 
			 * @generated
			 */
			final EClass element;
			/** 
			 * @generated
			 */
			private List featureInitializers = new ArrayList();

			/** 
			 * @generated
			 */
			ObjectInitializer(EClass element) {
				this.element = element;
				init();
			}

			/**
			 * @generated
			 */
			protected abstract void init();

			/** 
			 * @generated
			 */
			protected final IFeatureInitializer add(
					IFeatureInitializer initializer) {
				featureInitializers.add(initializer);
				return initializer;
			}

			/** 
			 * @generated
			 */
			public void init(EObject instance) {
				for (Iterator it = featureInitializers.iterator(); it.hasNext();) {
					IFeatureInitializer nextExpr = (IFeatureInitializer) it
							.next();
					try {
						nextExpr.init(instance);
					} catch (RuntimeException e) {
						ActivityDiagramEditorPlugin.getInstance().logError(
								"Feature initialization failed", e); //$NON-NLS-1$						
					}
				}
			}
		} // end of ObjectInitializer

		/** 
		 * @generated
		 */
		interface IFeatureInitializer {
			/**
			 * @generated
			 */
			void init(EObject contextInstance);
		}

		/**
		 * @generated
		 */
		static IFeatureInitializer createNewElementFeatureInitializer(
				EStructuralFeature initFeature,
				ObjectInitializer[] newObjectInitializers) {
			final EStructuralFeature feature = initFeature;
			final ObjectInitializer[] initializers = newObjectInitializers;
			return new IFeatureInitializer() {
				public void init(EObject contextInstance) {
					for (int i = 0; i < initializers.length; i++) {
						EObject newInstance = initializers[i].element
								.getEPackage().getEFactoryInstance().create(
										initializers[i].element);
						if (feature.isMany()) {
							((Collection) contextInstance.eGet(feature))
									.add(newInstance);
						} else {
							contextInstance.eSet(feature, newInstance);
						}
						initializers[i].init(newInstance);
					}
				}
			};
		}

		/**
		 * @generated
		 */
		static IFeatureInitializer createExpressionFeatureInitializer(
				EStructuralFeature initFeature,
				UMLAbstractExpression valueExpression) {
			final EStructuralFeature feature = initFeature;
			final UMLAbstractExpression expression = valueExpression;
			return new IFeatureInitializer() {
				public void init(EObject contextInstance) {
					expression.assignTo(feature, contextInstance);
				}
			};
		}

		/** 
		 * @generated
		 */
		static class Java {

			/**
			 * @modified
			 */
			private static EList<EAnnotation> createUmaType_Activity(
					StructuredActivityNode self) {
				return genericCreateType(self, BridgeHelper.UMA_ACTIVITY);
			}

			/**
			 * @modified
			 */
			private static EList<EAnnotation> createUmaType_TaskDescriptor(
					ActivityParameterNode self) {
				return genericCreateType(self, BridgeHelper.UMA_TASK_DESCRIPTOR);
			}

			/**
			 * @modified
			 */
			private static EList<EAnnotation> createUmaType_Phase(
					StructuredActivityNode self) {

				return genericCreateType(self, BridgeHelper.UMA_PHASE);
			}

			/**
			 * @modified
			 */
			private static EList<EAnnotation> createUmaType_Iteration(
					StructuredActivityNode self) {
				return genericCreateType(self, BridgeHelper.UMA_ITERATION);
			}

			/**
			 * @modified
			 */
			private static EList<EAnnotation> createUmaType_Milestone(
					ActivityParameterNode self) {
				return genericCreateType(self, BridgeHelper.UMA_MILESTONE);
			}
		} //Java
	} // end of Initializers
	
	
	/**
	 * @param self
	 * @param type
	 * @return
	 */
	private static EList<EAnnotation> genericCreateType(ActivityNode self, String type){
		EList<EAnnotation> list = BridgeHelper.addEAnnotationType(self,	type);
		BridgeHelper.setDefaultName(self);
		return list;
	}
}
