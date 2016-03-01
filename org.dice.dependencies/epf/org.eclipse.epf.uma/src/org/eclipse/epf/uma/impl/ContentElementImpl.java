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
package org.eclipse.epf.uma.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getVariabilityType <em>Variability Type</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getVariabilityBasedOnElement <em>Variability Based On Element</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getSupportingMaterials <em>Supporting Materials</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getConceptsAndPapers <em>Concepts And Papers</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getChecklists <em>Checklists</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getGuidelines <em>Guidelines</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getExamples <em>Examples</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getAssets <em>Assets</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ContentElementImpl#getTermdefinition <em>Termdefinition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ContentElementImpl extends DescribableElementImpl
		implements ContentElement {
	/**
	 * The default value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityType()
	 * @generated
	 * @ordered
	 */
	protected static final VariabilityType VARIABILITY_TYPE_EDEFAULT = VariabilityType.NA;

	/**
	 * The cached value of the '{@link #getVariabilityType() <em>Variability Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityType()
	 * @generated
	 * @ordered
	 */
	protected VariabilityType variabilityType = VARIABILITY_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVariabilityBasedOnElement() <em>Variability Based On Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariabilityBasedOnElement()
	 * @generated
	 * @ordered
	 */
	protected VariabilityElement variabilityBasedOnElement;

	/**
	 * The cached value of the '{@link #getSupportingMaterials() <em>Supporting Materials</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportingMaterials()
	 * @generated
	 * @ordered
	 */
	protected EList<SupportingMaterial> supportingMaterials;

	/**
	 * The cached value of the '{@link #getConceptsAndPapers() <em>Concepts And Papers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConceptsAndPapers()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> conceptsAndPapers;

	/**
	 * The cached value of the '{@link #getChecklists() <em>Checklists</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChecklists()
	 * @generated
	 * @ordered
	 */
	protected EList<Checklist> checklists;

	/**
	 * The cached value of the '{@link #getGuidelines() <em>Guidelines</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuidelines()
	 * @generated
	 * @ordered
	 */
	protected EList<Guideline> guidelines;

	/**
	 * The cached value of the '{@link #getExamples() <em>Examples</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExamples()
	 * @generated
	 * @ordered
	 */
	protected EList<Example> examples;

	/**
	 * The cached value of the '{@link #getAssets() <em>Assets</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssets()
	 * @generated
	 * @ordered
	 */
	protected EList<ReusableAsset> assets;

	/**
	 * The cached value of the '{@link #getTermdefinition() <em>Termdefinition</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTermdefinition()
	 * @generated
	 * @ordered
	 */
	protected EList<TermDefinition> termdefinition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentElementImpl() {
		super();

		//UMA-->
		reassignDefaultValues();
		//UMA<--  
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.CONTENT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityType getVariabilityType() {
		return variabilityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariabilityType(VariabilityType newVariabilityType) {
		VariabilityType oldVariabilityType = variabilityType;
		variabilityType = newVariabilityType == null ? VARIABILITY_TYPE_EDEFAULT
				: newVariabilityType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE,
					oldVariabilityType, variabilityType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityElement getVariabilityBasedOnElement() {
		if (variabilityBasedOnElement != null
				&& ((EObject) variabilityBasedOnElement).eIsProxy()) {
			InternalEObject oldVariabilityBasedOnElement = (InternalEObject) variabilityBasedOnElement;
			variabilityBasedOnElement = (VariabilityElement) eResolveProxy(oldVariabilityBasedOnElement);
			if (variabilityBasedOnElement != oldVariabilityBasedOnElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT,
							oldVariabilityBasedOnElement,
							variabilityBasedOnElement));
			}
		}
		return variabilityBasedOnElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityElement basicGetVariabilityBasedOnElement() {
		return variabilityBasedOnElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariabilityBasedOnElement(
			VariabilityElement newVariabilityBasedOnElement) {
		VariabilityElement oldVariabilityBasedOnElement = variabilityBasedOnElement;
		variabilityBasedOnElement = newVariabilityBasedOnElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT,
					oldVariabilityBasedOnElement, variabilityBasedOnElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Concept> getConceptsAndPapers() {
		if (conceptsAndPapers == null) {
			conceptsAndPapers = new EObjectResolvingEList<Concept>(
					Concept.class, this,
					UmaPackage.CONTENT_ELEMENT__CONCEPTS_AND_PAPERS);
		}
		return conceptsAndPapers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Checklist> getChecklists() {
		if (checklists == null) {
			checklists = new EObjectResolvingEList<Checklist>(Checklist.class,
					this, UmaPackage.CONTENT_ELEMENT__CHECKLISTS);
		}
		return checklists;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Guideline> getGuidelines() {
		if (guidelines == null) {
			guidelines = new EObjectResolvingEList<Guideline>(Guideline.class,
					this, UmaPackage.CONTENT_ELEMENT__GUIDELINES);
		}
		return guidelines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Example> getExamples() {
		if (examples == null) {
			examples = new EObjectResolvingEList<Example>(Example.class, this,
					UmaPackage.CONTENT_ELEMENT__EXAMPLES);
		}
		return examples;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ReusableAsset> getAssets() {
		if (assets == null) {
			assets = new EObjectResolvingEList<ReusableAsset>(
					ReusableAsset.class, this,
					UmaPackage.CONTENT_ELEMENT__ASSETS);
		}
		return assets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<TermDefinition> getTermdefinition() {
		if (termdefinition == null) {
			termdefinition = new EObjectResolvingEList<TermDefinition>(
					TermDefinition.class, this,
					UmaPackage.CONTENT_ELEMENT__TERMDEFINITION);
		}
		return termdefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
			return getVariabilityType();
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
			if (resolve)
				return getVariabilityBasedOnElement();
			return basicGetVariabilityBasedOnElement();
		case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIALS:
			return getSupportingMaterials();
		case UmaPackage.CONTENT_ELEMENT__CONCEPTS_AND_PAPERS:
			return getConceptsAndPapers();
		case UmaPackage.CONTENT_ELEMENT__CHECKLISTS:
			return getChecklists();
		case UmaPackage.CONTENT_ELEMENT__GUIDELINES:
			return getGuidelines();
		case UmaPackage.CONTENT_ELEMENT__EXAMPLES:
			return getExamples();
		case UmaPackage.CONTENT_ELEMENT__ASSETS:
			return getAssets();
		case UmaPackage.CONTENT_ELEMENT__TERMDEFINITION:
			return getTermdefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
			setVariabilityType((VariabilityType) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
			setVariabilityBasedOnElement((VariabilityElement) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIALS:
			getSupportingMaterials().clear();
			getSupportingMaterials().addAll(
					(Collection<? extends SupportingMaterial>) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__CONCEPTS_AND_PAPERS:
			getConceptsAndPapers().clear();
			getConceptsAndPapers().addAll(
					(Collection<? extends Concept>) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__CHECKLISTS:
			getChecklists().clear();
			getChecklists().addAll((Collection<? extends Checklist>) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__GUIDELINES:
			getGuidelines().clear();
			getGuidelines().addAll((Collection<? extends Guideline>) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__EXAMPLES:
			getExamples().clear();
			getExamples().addAll((Collection<? extends Example>) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__ASSETS:
			getAssets().clear();
			getAssets().addAll((Collection<? extends ReusableAsset>) newValue);
			return;
		case UmaPackage.CONTENT_ELEMENT__TERMDEFINITION:
			getTermdefinition().clear();
			getTermdefinition().addAll(
					(Collection<? extends TermDefinition>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
			setVariabilityType(VARIABILITY_TYPE_EDEFAULT);
			return;
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
			setVariabilityBasedOnElement((VariabilityElement) null);
			return;
		case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIALS:
			getSupportingMaterials().clear();
			return;
		case UmaPackage.CONTENT_ELEMENT__CONCEPTS_AND_PAPERS:
			getConceptsAndPapers().clear();
			return;
		case UmaPackage.CONTENT_ELEMENT__CHECKLISTS:
			getChecklists().clear();
			return;
		case UmaPackage.CONTENT_ELEMENT__GUIDELINES:
			getGuidelines().clear();
			return;
		case UmaPackage.CONTENT_ELEMENT__EXAMPLES:
			getExamples().clear();
			return;
		case UmaPackage.CONTENT_ELEMENT__ASSETS:
			getAssets().clear();
			return;
		case UmaPackage.CONTENT_ELEMENT__TERMDEFINITION:
			getTermdefinition().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		//UMA-->
		EStructuralFeature feature = getFeatureWithOverridenDefaultValue(featureID);
		if (feature != null) {
			return isFeatureWithOverridenDefaultValueSet(feature);
		}
		//UMA<--		
		switch (featureID) {
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
			return variabilityType != VARIABILITY_TYPE_EDEFAULT;
		case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
			return variabilityBasedOnElement != null;
		case UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIALS:
			return supportingMaterials != null
					&& !supportingMaterials.isEmpty();
		case UmaPackage.CONTENT_ELEMENT__CONCEPTS_AND_PAPERS:
			return conceptsAndPapers != null && !conceptsAndPapers.isEmpty();
		case UmaPackage.CONTENT_ELEMENT__CHECKLISTS:
			return checklists != null && !checklists.isEmpty();
		case UmaPackage.CONTENT_ELEMENT__GUIDELINES:
			return guidelines != null && !guidelines.isEmpty();
		case UmaPackage.CONTENT_ELEMENT__EXAMPLES:
			return examples != null && !examples.isEmpty();
		case UmaPackage.CONTENT_ELEMENT__ASSETS:
			return assets != null && !assets.isEmpty();
		case UmaPackage.CONTENT_ELEMENT__TERMDEFINITION:
			return termdefinition != null && !termdefinition.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SupportingMaterial> getSupportingMaterials() {
		if (supportingMaterials == null) {
			supportingMaterials = new EObjectResolvingEList<SupportingMaterial>(
					SupportingMaterial.class, this,
					UmaPackage.CONTENT_ELEMENT__SUPPORTING_MATERIALS);
		}
		return supportingMaterials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == VariabilityElement.class) {
			switch (derivedFeatureID) {
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE:
				return UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_TYPE;
			case UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				return UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == VariabilityElement.class) {
			switch (baseFeatureID) {
			case UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_TYPE:
				return UmaPackage.CONTENT_ELEMENT__VARIABILITY_TYPE;
			case UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
				return UmaPackage.CONTENT_ELEMENT__VARIABILITY_BASED_ON_ELEMENT;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (variabilityType: "); //$NON-NLS-1$
		result.append(variabilityType);
		result.append(')');
		return result.toString();
	}

	//
	// Start custom code
	//

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.impl.NamedElementImpl#setName(java.lang.String)
	 */
	public void setName(String newName) {
		if (presentation == null) {
			super.setName(newName);
			return;
		}

		String oldName = name;
		super.setName(newName);
		if (newName != null && !newName.equals(oldName)) {
			presentation.setName(UmaUtil.createContentDescriptionName(this));
		}
	}

} //ContentElementImpl
