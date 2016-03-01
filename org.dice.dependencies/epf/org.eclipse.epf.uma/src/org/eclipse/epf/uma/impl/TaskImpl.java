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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Step;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkDefinition;
import org.eclipse.epf.uma.WorkProduct;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getMandatoryInput <em>Mandatory Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getAdditionallyPerformedBy <em>Additionally Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getOptionalInput <em>Optional Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getSteps <em>Steps</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getToolMentors <em>Tool Mentors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskImpl#getEstimationConsiderations <em>Estimation Considerations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskImpl extends ContentElementImpl implements Task {
	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected Constraint precondition;

	/**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected Constraint postcondition;

	/**
	 * The cached value of the '{@link #getPerformedBy() <em>Performed By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> performedBy;

	/**
	 * The cached value of the '{@link #getMandatoryInput() <em>Mandatory Input</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMandatoryInput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> mandatoryInput;

	/**
	 * The cached value of the '{@link #getOutput() <em>Output</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> output;

	/**
	 * The cached value of the '{@link #getAdditionallyPerformedBy() <em>Additionally Performed By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionallyPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> additionallyPerformedBy;

	/**
	 * The cached value of the '{@link #getOptionalInput() <em>Optional Input</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptionalInput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> optionalInput;

	/**
	 * The cached value of the '{@link #getToolMentors() <em>Tool Mentors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToolMentors()
	 * @generated
	 * @ordered
	 */
	protected EList<ToolMentor> toolMentors;

	/**
	 * The cached value of the '{@link #getEstimationConsiderations() <em>Estimation Considerations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEstimationConsiderations()
	 * @generated
	 * @ordered
	 */
	protected EList<EstimationConsiderations> estimationConsiderations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
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
		return UmaPackage.Literals.TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint getPrecondition() {
		if (precondition != null && ((EObject) precondition).eIsProxy()) {
			InternalEObject oldPrecondition = (InternalEObject) precondition;
			precondition = (Constraint) eResolveProxy(oldPrecondition);
			if (precondition != oldPrecondition) {
				InternalEObject newPrecondition = (InternalEObject) precondition;
				NotificationChain msgs = oldPrecondition.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - UmaPackage.TASK__PRECONDITION,
						null, null);
				if (newPrecondition.eInternalContainer() == null) {
					msgs = newPrecondition
							.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
									- UmaPackage.TASK__PRECONDITION, null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.TASK__PRECONDITION, oldPrecondition,
							precondition));
			}
		}
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint basicGetPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrecondition(Constraint newPrecondition,
			NotificationChain msgs) {
		Constraint oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.TASK__PRECONDITION,
					oldPrecondition, newPrecondition);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecondition(Constraint newPrecondition) {
		if (newPrecondition != precondition) {
			NotificationChain msgs = null;
			if (precondition != null)
				msgs = ((InternalEObject) precondition).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - UmaPackage.TASK__PRECONDITION,
						null, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject) newPrecondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - UmaPackage.TASK__PRECONDITION,
						null, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TASK__PRECONDITION, newPrecondition,
					newPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint getPostcondition() {
		if (postcondition != null && ((EObject) postcondition).eIsProxy()) {
			InternalEObject oldPostcondition = (InternalEObject) postcondition;
			postcondition = (Constraint) eResolveProxy(oldPostcondition);
			if (postcondition != oldPostcondition) {
				InternalEObject newPostcondition = (InternalEObject) postcondition;
				NotificationChain msgs = oldPostcondition
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.TASK__POSTCONDITION, null, null);
				if (newPostcondition.eInternalContainer() == null) {
					msgs = newPostcondition.eInverseAdd(this,
							EOPPOSITE_FEATURE_BASE
									- UmaPackage.TASK__POSTCONDITION, null,
							msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.TASK__POSTCONDITION, oldPostcondition,
							postcondition));
			}
		}
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint basicGetPostcondition() {
		return postcondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPostcondition(Constraint newPostcondition,
			NotificationChain msgs) {
		Constraint oldPostcondition = postcondition;
		postcondition = newPostcondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.TASK__POSTCONDITION,
					oldPostcondition, newPostcondition);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostcondition(Constraint newPostcondition) {
		if (newPostcondition != postcondition) {
			NotificationChain msgs = null;
			if (postcondition != null)
				msgs = ((InternalEObject) postcondition)
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.TASK__POSTCONDITION, null, msgs);
			if (newPostcondition != null)
				msgs = ((InternalEObject) newPostcondition)
						.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
								- UmaPackage.TASK__POSTCONDITION, null, msgs);
			msgs = basicSetPostcondition(newPostcondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TASK__POSTCONDITION, newPostcondition,
					newPostcondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getPerformedBy() {
		if (performedBy == null) {
			performedBy = new EObjectResolvingEList<Role>(Role.class, this,
					UmaPackage.TASK__PERFORMED_BY);
		}
		return performedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getMandatoryInput() {
		if (mandatoryInput == null) {
			mandatoryInput = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this, UmaPackage.TASK__MANDATORY_INPUT);
		}
		return mandatoryInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getOutput() {
		if (output == null) {
			output = new EObjectResolvingEList<WorkProduct>(WorkProduct.class,
					this, UmaPackage.TASK__OUTPUT);
		}
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getAdditionallyPerformedBy() {
		if (additionallyPerformedBy == null) {
			additionallyPerformedBy = new EObjectResolvingEList<Role>(
					Role.class, this,
					UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY);
		}
		return additionallyPerformedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getOptionalInput() {
		if (optionalInput == null) {
			optionalInput = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this, UmaPackage.TASK__OPTIONAL_INPUT);
		}
		return optionalInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getSteps() {
		return getPresentation().getSections();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ToolMentor> getToolMentors() {
		if (toolMentors == null) {
			toolMentors = new EObjectResolvingEList<ToolMentor>(
					ToolMentor.class, this, UmaPackage.TASK__TOOL_MENTORS);
		}
		return toolMentors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<EstimationConsiderations> getEstimationConsiderations() {
		if (estimationConsiderations == null) {
			estimationConsiderations = new EObjectResolvingEList<EstimationConsiderations>(
					EstimationConsiderations.class, this,
					UmaPackage.TASK__ESTIMATION_CONSIDERATIONS);
		}
		return estimationConsiderations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case UmaPackage.TASK__PRECONDITION:
			return basicSetPrecondition(null, msgs);
		case UmaPackage.TASK__POSTCONDITION:
			return basicSetPostcondition(null, msgs);
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
			if (resolve)
				return getPrecondition();
			return basicGetPrecondition();
		case UmaPackage.TASK__POSTCONDITION:
			if (resolve)
				return getPostcondition();
			return basicGetPostcondition();
		case UmaPackage.TASK__PERFORMED_BY:
			return getPerformedBy();
		case UmaPackage.TASK__MANDATORY_INPUT:
			return getMandatoryInput();
		case UmaPackage.TASK__OUTPUT:
			return getOutput();
		case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
			return getAdditionallyPerformedBy();
		case UmaPackage.TASK__OPTIONAL_INPUT:
			return getOptionalInput();
		case UmaPackage.TASK__STEPS:
			return getSteps();
		case UmaPackage.TASK__TOOL_MENTORS:
			return getToolMentors();
		case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
			return getEstimationConsiderations();
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
			setPrecondition((Constraint) newValue);
			return;
		case UmaPackage.TASK__POSTCONDITION:
			setPostcondition((Constraint) newValue);
			return;
		case UmaPackage.TASK__PERFORMED_BY:
			getPerformedBy().clear();
			getPerformedBy().addAll((Collection<? extends Role>) newValue);
			return;
		case UmaPackage.TASK__MANDATORY_INPUT:
			getMandatoryInput().clear();
			getMandatoryInput().addAll(
					(Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.TASK__OUTPUT:
			getOutput().clear();
			getOutput().addAll((Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
			getAdditionallyPerformedBy().clear();
			getAdditionallyPerformedBy().addAll(
					(Collection<? extends Role>) newValue);
			return;
		case UmaPackage.TASK__OPTIONAL_INPUT:
			getOptionalInput().clear();
			getOptionalInput().addAll(
					(Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.TASK__STEPS:
			getSteps().clear();
			getSteps().addAll((Collection<? extends Step>) newValue);
			return;
		case UmaPackage.TASK__TOOL_MENTORS:
			getToolMentors().clear();
			getToolMentors()
					.addAll((Collection<? extends ToolMentor>) newValue);
			return;
		case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
			getEstimationConsiderations().clear();
			getEstimationConsiderations().addAll(
					(Collection<? extends EstimationConsiderations>) newValue);
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
			setPrecondition((Constraint) null);
			return;
		case UmaPackage.TASK__POSTCONDITION:
			setPostcondition((Constraint) null);
			return;
		case UmaPackage.TASK__PERFORMED_BY:
			getPerformedBy().clear();
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
		case UmaPackage.TASK__STEPS:
			getSteps().clear();
			return;
		case UmaPackage.TASK__TOOL_MENTORS:
			getToolMentors().clear();
			return;
		case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
			getEstimationConsiderations().clear();
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
		case UmaPackage.TASK__PRECONDITION:
			return precondition != null;
		case UmaPackage.TASK__POSTCONDITION:
			return postcondition != null;
		case UmaPackage.TASK__PERFORMED_BY:
			return performedBy != null && !performedBy.isEmpty();
		case UmaPackage.TASK__MANDATORY_INPUT:
			return mandatoryInput != null && !mandatoryInput.isEmpty();
		case UmaPackage.TASK__OUTPUT:
			return output != null && !output.isEmpty();
		case UmaPackage.TASK__ADDITIONALLY_PERFORMED_BY:
			return additionallyPerformedBy != null
					&& !additionallyPerformedBy.isEmpty();
		case UmaPackage.TASK__OPTIONAL_INPUT:
			return optionalInput != null && !optionalInput.isEmpty();
		case UmaPackage.TASK__STEPS:
			return !getSteps().isEmpty();
		case UmaPackage.TASK__TOOL_MENTORS:
			return toolMentors != null && !toolMentors.isEmpty();
		case UmaPackage.TASK__ESTIMATION_CONSIDERATIONS:
			return estimationConsiderations != null
					&& !estimationConsiderations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == WorkDefinition.class) {
			switch (derivedFeatureID) {
			case UmaPackage.TASK__PRECONDITION:
				return UmaPackage.WORK_DEFINITION__PRECONDITION;
			case UmaPackage.TASK__POSTCONDITION:
				return UmaPackage.WORK_DEFINITION__POSTCONDITION;
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
		if (baseClass == WorkDefinition.class) {
			switch (baseFeatureID) {
			case UmaPackage.WORK_DEFINITION__PRECONDITION:
				return UmaPackage.TASK__PRECONDITION;
			case UmaPackage.WORK_DEFINITION__POSTCONDITION:
				return UmaPackage.TASK__POSTCONDITION;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //TaskImpl
