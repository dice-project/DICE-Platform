/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.xml.uma.Task;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getMandatoryInput <em>Mandatory Input</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getAdditionallyPerformedBy <em>Additionally Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getOptionalInput <em>Optional Input</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getEstimate <em>Estimate</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getEstimationConsiderations <em>Estimation Considerations</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.TaskImpl#getToolMentor <em>Tool Mentor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskImpl extends ContentElementImpl implements Task {
	/**
	 * The default value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected static final String PRECONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected String precondition = PRECONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPostcondition() <em>Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected static final String POSTCONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected String postcondition = POSTCONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPerformedBy() <em>Performed By</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<String> performedBy;

	/**
	 * The cached value of the '{@link #getGroup2() <em>Group2</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup2()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecondition(String newPrecondition) {
		String oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.TASK__PRECONDITION, oldPrecondition, precondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPostcondition() {
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostcondition(String newPostcondition) {
		String oldPostcondition = postcondition;
		postcondition = newPostcondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.TASK__POSTCONDITION, oldPostcondition, postcondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPerformedBy() {
		if (performedBy == null) {
			performedBy = new EDataTypeEList<String>(String.class, this, UmaPackage.TASK__PERFORMED_BY);
		}
		return performedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, UmaPackage.TASK__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMandatoryInput() {
		return getGroup2().list(UmaPackage.Literals.TASK__MANDATORY_INPUT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOutput() {
		return getGroup2().list(UmaPackage.Literals.TASK__OUTPUT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAdditionallyPerformedBy() {
		return getGroup2().list(UmaPackage.Literals.TASK__ADDITIONALLY_PERFORMED_BY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOptionalInput() {
		return getGroup2().list(UmaPackage.Literals.TASK__OPTIONAL_INPUT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getEstimate() {
		return getGroup2().list(UmaPackage.Literals.TASK__ESTIMATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getEstimationConsiderations() {
		return getGroup2().list(UmaPackage.Literals.TASK__ESTIMATION_CONSIDERATIONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getToolMentor() {
		return getGroup2().list(UmaPackage.Literals.TASK__TOOL_MENTOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.TASK__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UmaPackage.TASK__PRECONDITION:
				return getPrecondition();
			case UmaPackage.TASK__POSTCONDITION:
				return getPostcondition();
			case UmaPackage.TASK__PERFORMED_BY:
				return getPerformedBy();
			case UmaPackage.TASK__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case UmaPackage.TASK__MANDATORY_INPUT:
				return getMandatoryInput();
			case UmaPackage.TASK__OUTPUT:
				return getOutput();
			case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
				return getAdditionallyPerformedBy();
			case UmaPackage.TASK__OPTIONAL_INPUT:
				return getOptionalInput();
			case UmaPackage.TASK__ESTIMATE:
				return getEstimate();
			case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
				return getEstimationConsiderations();
			case UmaPackage.TASK__TOOL_MENTOR:
				return getToolMentor();
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
			case UmaPackage.TASK__PRECONDITION:
				setPrecondition((String)newValue);
				return;
			case UmaPackage.TASK__POSTCONDITION:
				setPostcondition((String)newValue);
				return;
			case UmaPackage.TASK__PERFORMED_BY:
				getPerformedBy().clear();
				getPerformedBy().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case UmaPackage.TASK__MANDATORY_INPUT:
				getMandatoryInput().clear();
				getMandatoryInput().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__OUTPUT:
				getOutput().clear();
				getOutput().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
				getAdditionallyPerformedBy().clear();
				getAdditionallyPerformedBy().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__OPTIONAL_INPUT:
				getOptionalInput().clear();
				getOptionalInput().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__ESTIMATE:
				getEstimate().clear();
				getEstimate().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
				getEstimationConsiderations().clear();
				getEstimationConsiderations().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.TASK__TOOL_MENTOR:
				getToolMentor().clear();
				getToolMentor().addAll((Collection<? extends String>)newValue);
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
			case UmaPackage.TASK__PRECONDITION:
				setPrecondition(PRECONDITION_EDEFAULT);
				return;
			case UmaPackage.TASK__POSTCONDITION:
				setPostcondition(POSTCONDITION_EDEFAULT);
				return;
			case UmaPackage.TASK__PERFORMED_BY:
				getPerformedBy().clear();
				return;
			case UmaPackage.TASK__GROUP2:
				getGroup2().clear();
				return;
			case UmaPackage.TASK__MANDATORY_INPUT:
				getMandatoryInput().clear();
				return;
			case UmaPackage.TASK__OUTPUT:
				getOutput().clear();
				return;
			case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
				getAdditionallyPerformedBy().clear();
				return;
			case UmaPackage.TASK__OPTIONAL_INPUT:
				getOptionalInput().clear();
				return;
			case UmaPackage.TASK__ESTIMATE:
				getEstimate().clear();
				return;
			case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
				getEstimationConsiderations().clear();
				return;
			case UmaPackage.TASK__TOOL_MENTOR:
				getToolMentor().clear();
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
		switch (featureID) {
			case UmaPackage.TASK__PRECONDITION:
				return PRECONDITION_EDEFAULT == null ? precondition != null : !PRECONDITION_EDEFAULT.equals(precondition);
			case UmaPackage.TASK__POSTCONDITION:
				return POSTCONDITION_EDEFAULT == null ? postcondition != null : !POSTCONDITION_EDEFAULT.equals(postcondition);
			case UmaPackage.TASK__PERFORMED_BY:
				return performedBy != null && !performedBy.isEmpty();
			case UmaPackage.TASK__GROUP2:
				return group2 != null && !group2.isEmpty();
			case UmaPackage.TASK__MANDATORY_INPUT:
				return !getMandatoryInput().isEmpty();
			case UmaPackage.TASK__OUTPUT:
				return !getOutput().isEmpty();
			case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
				return !getAdditionallyPerformedBy().isEmpty();
			case UmaPackage.TASK__OPTIONAL_INPUT:
				return !getOptionalInput().isEmpty();
			case UmaPackage.TASK__ESTIMATE:
				return !getEstimate().isEmpty();
			case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
				return !getEstimationConsiderations().isEmpty();
			case UmaPackage.TASK__TOOL_MENTOR:
				return !getToolMentor().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (precondition: ");
		result.append(precondition);
		result.append(", postcondition: ");
		result.append(postcondition);
		result.append(", performedBy: ");
		result.append(performedBy);
		result.append(", group2: ");
		result.append(group2);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
