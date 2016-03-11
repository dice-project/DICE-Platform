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
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.util.DefaultValueManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getIsSynchronizedWithSource <em>Is Synchronized With Source</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getGuidanceExclude <em>Guidance Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getGuidanceAdditional <em>Guidance Additional</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getTask <em>Task</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getAdditionallyPerformedBy <em>Additionally Performed By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getAssistedBy <em>Assisted By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getExternalInput <em>External Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getMandatoryInput <em>Mandatory Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getOptionalInput <em>Optional Input</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getPerformedPrimarilyBy <em>Performed Primarily By</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getSelectedSteps <em>Selected Steps</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getPerformedPrimarilyByExcluded <em>Performed Primarily By Excluded</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getAdditionallyPerformedByExclude <em>Additionally Performed By Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getMandatoryInputExclude <em>Mandatory Input Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getOptionalInputExclude <em>Optional Input Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getOutputExclude <em>Output Exclude</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.TaskDescriptorImpl#getSelectedStepsExclude <em>Selected Steps Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskDescriptorImpl extends WorkBreakdownElementImpl implements
		TaskDescriptor {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -4419414664273910863L;

	/**
	 * The default value of the '{@link #getIsSynchronizedWithSource() <em>Is Synchronized With Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSynchronizedWithSource()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getIsSynchronizedWithSource() <em>Is Synchronized With Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSynchronizedWithSource()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSynchronizedWithSource = IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGuidanceExclude() <em>Guidance Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuidanceExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<Guidance> guidanceExclude;

	/**
	 * The cached value of the '{@link #getGuidanceAdditional() <em>Guidance Additional</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuidanceAdditional()
	 * @generated
	 * @ordered
	 */
	protected EList<Guidance> guidanceAdditional;

	/**
	 * The cached value of the '{@link #getTask() <em>Task</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTask()
	 * @generated
	 * @ordered
	 */
	protected Task task;

	/**
	 * The cached value of the '{@link #getAdditionallyPerformedBy() <em>Additionally Performed By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionallyPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleDescriptor> additionallyPerformedBy;

	/**
	 * The cached value of the '{@link #getAssistedBy() <em>Assisted By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssistedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleDescriptor> assistedBy;

	/**
	 * The cached value of the '{@link #getExternalInput() <em>External Input</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalInput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> externalInput;

	/**
	 * The cached value of the '{@link #getMandatoryInput() <em>Mandatory Input</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMandatoryInput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> mandatoryInput;

	/**
	 * The cached value of the '{@link #getOptionalInput() <em>Optional Input</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptionalInput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> optionalInput;

	/**
	 * The cached value of the '{@link #getOutput() <em>Output</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutput()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProductDescriptor> output;

	/**
	 * The cached value of the '{@link #getPerformedPrimarilyBy() <em>Performed Primarily By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedPrimarilyBy()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleDescriptor> performedPrimarilyBy;

	/**
	 * The cached value of the '{@link #getSelectedSteps() <em>Selected Steps</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectedSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<Section> selectedSteps;

	/**
	 * The cached value of the '{@link #getPerformedPrimarilyByExcluded() <em>Performed Primarily By Excluded</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedPrimarilyByExcluded()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> performedPrimarilyByExcluded;

	/**
	 * The cached value of the '{@link #getAdditionallyPerformedByExclude() <em>Additionally Performed By Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionallyPerformedByExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> additionallyPerformedByExclude;

	/**
	 * The cached value of the '{@link #getMandatoryInputExclude() <em>Mandatory Input Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMandatoryInputExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> mandatoryInputExclude;

	/**
	 * The cached value of the '{@link #getOptionalInputExclude() <em>Optional Input Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptionalInputExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> optionalInputExclude;

	/**
	 * The cached value of the '{@link #getOutputExclude() <em>Output Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkProduct> outputExclude;

	/**
	 * The cached value of the '{@link #getSelectedStepsExclude() <em>Selected Steps Exclude</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectedStepsExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<Section> selectedStepsExclude;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskDescriptorImpl() {
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
		return UmaPackage.Literals.TASK_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsSynchronizedWithSource() {
		return isSynchronizedWithSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSynchronizedWithSource(Boolean newIsSynchronizedWithSource) {
		Boolean oldIsSynchronizedWithSource = isSynchronizedWithSource;
		isSynchronizedWithSource = newIsSynchronizedWithSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE,
					oldIsSynchronizedWithSource, isSynchronizedWithSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Guidance> getGuidanceExclude() {
		if (guidanceExclude == null) {
			guidanceExclude = new EObjectResolvingEList<Guidance>(
					Guidance.class, this,
					UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE);
		}
		return guidanceExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Guidance> getGuidanceAdditional() {
		if (guidanceAdditional == null) {
			guidanceAdditional = new EObjectResolvingEList<Guidance>(
					Guidance.class, this,
					UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL);
		}
		return guidanceAdditional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Task getTask() {
		if (task != null && ((EObject) task).eIsProxy()) {
			InternalEObject oldTask = (InternalEObject) task;
			task = (Task) eResolveProxy(oldTask);
			if (task != oldTask) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.TASK_DESCRIPTOR__TASK, oldTask, task));
			}
		}
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Task basicGetTask() {
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTask(Task newTask) {
		Task oldTask = task;
		task = newTask;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.TASK_DESCRIPTOR__TASK, oldTask, task));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RoleDescriptor> getAdditionallyPerformedBy() {
		if (additionallyPerformedBy == null) {
			additionallyPerformedBy = new EObjectResolvingEList<RoleDescriptor>(
					RoleDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY);
		}
		return additionallyPerformedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RoleDescriptor> getAssistedBy() {
		if (assistedBy == null) {
			assistedBy = new EObjectResolvingEList<RoleDescriptor>(
					RoleDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY);
		}
		return assistedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getExternalInput() {
		if (externalInput == null) {
			externalInput = new EObjectResolvingEList<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT);
		}
		return externalInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getMandatoryInput() {
		if (mandatoryInput == null) {
			mandatoryInput = new EObjectResolvingEList<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT);
		}
		return mandatoryInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getOptionalInput() {
		if (optionalInput == null) {
			optionalInput = new EObjectResolvingEList<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT);
		}
		return optionalInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProductDescriptor> getOutput() {
		if (output == null) {
			output = new EObjectResolvingEList<WorkProductDescriptor>(
					WorkProductDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__OUTPUT);
		}
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<RoleDescriptor> getPerformedPrimarilyBy() {
		if (performedPrimarilyBy == null) {
			performedPrimarilyBy = new EObjectResolvingEList<RoleDescriptor>(
					RoleDescriptor.class, this,
					UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY);
		}
		return performedPrimarilyBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Section> getSelectedSteps() {
		if (selectedSteps == null) {
			selectedSteps = new EObjectResolvingEList<Section>(Section.class,
					this, UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS);
		}
		return selectedSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getPerformedPrimarilyByExcluded() {
		if (performedPrimarilyByExcluded == null) {
			performedPrimarilyByExcluded = new EObjectResolvingEList<Role>(
					Role.class, this,
					UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED);
		}
		return performedPrimarilyByExcluded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Role> getAdditionallyPerformedByExclude() {
		if (additionallyPerformedByExclude == null) {
			additionallyPerformedByExclude = new EObjectResolvingEList<Role>(
					Role.class,
					this,
					UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE);
		}
		return additionallyPerformedByExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getMandatoryInputExclude() {
		if (mandatoryInputExclude == null) {
			mandatoryInputExclude = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this,
					UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE);
		}
		return mandatoryInputExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getOptionalInputExclude() {
		if (optionalInputExclude == null) {
			optionalInputExclude = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this,
					UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE);
		}
		return optionalInputExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<WorkProduct> getOutputExclude() {
		if (outputExclude == null) {
			outputExclude = new EObjectResolvingEList<WorkProduct>(
					WorkProduct.class, this,
					UmaPackage.TASK_DESCRIPTOR__OUTPUT_EXCLUDE);
		}
		return outputExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Section> getSelectedStepsExclude() {
		if (selectedStepsExclude == null) {
			selectedStepsExclude = new EObjectResolvingEList<Section>(
					Section.class, this,
					UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE);
		}
		return selectedStepsExclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			return getIsSynchronizedWithSource();
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE:
			return getGuidanceExclude();
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL:
			return getGuidanceAdditional();
		case UmaPackage.TASK_DESCRIPTOR__TASK:
			if (resolve)
				return getTask();
			return basicGetTask();
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
			return getAdditionallyPerformedBy();
		case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
			return getAssistedBy();
		case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
			return getExternalInput();
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
			return getMandatoryInput();
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
			return getOptionalInput();
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
			return getOutput();
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
			return getPerformedPrimarilyBy();
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS:
			return getSelectedSteps();
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED:
			return getPerformedPrimarilyByExcluded();
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE:
			return getAdditionallyPerformedByExclude();
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE:
			return getMandatoryInputExclude();
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE:
			return getOptionalInputExclude();
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT_EXCLUDE:
			return getOutputExclude();
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE:
			return getSelectedStepsExclude();
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
		case UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			setIsSynchronizedWithSource((Boolean) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE:
			getGuidanceExclude().clear();
			getGuidanceExclude().addAll(
					(Collection<? extends Guidance>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL:
			getGuidanceAdditional().clear();
			getGuidanceAdditional().addAll(
					(Collection<? extends Guidance>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__TASK:
			setTask((Task) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
			getAdditionallyPerformedBy().clear();
			getAdditionallyPerformedBy().addAll(
					(Collection<? extends RoleDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
			getAssistedBy().clear();
			getAssistedBy().addAll(
					(Collection<? extends RoleDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
			getExternalInput().clear();
			getExternalInput().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
			getMandatoryInput().clear();
			getMandatoryInput().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
			getOptionalInput().clear();
			getOptionalInput().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
			getOutput().clear();
			getOutput().addAll(
					(Collection<? extends WorkProductDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
			getPerformedPrimarilyBy().clear();
			getPerformedPrimarilyBy().addAll(
					(Collection<? extends RoleDescriptor>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS:
			getSelectedSteps().clear();
			getSelectedSteps().addAll((Collection<? extends Section>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED:
			getPerformedPrimarilyByExcluded().clear();
			getPerformedPrimarilyByExcluded().addAll(
					(Collection<? extends Role>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE:
			getAdditionallyPerformedByExclude().clear();
			getAdditionallyPerformedByExclude().addAll(
					(Collection<? extends Role>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE:
			getMandatoryInputExclude().clear();
			getMandatoryInputExclude().addAll(
					(Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE:
			getOptionalInputExclude().clear();
			getOptionalInputExclude().addAll(
					(Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT_EXCLUDE:
			getOutputExclude().clear();
			getOutputExclude().addAll(
					(Collection<? extends WorkProduct>) newValue);
			return;
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE:
			getSelectedStepsExclude().clear();
			getSelectedStepsExclude().addAll(
					(Collection<? extends Section>) newValue);
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
		case UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			setIsSynchronizedWithSource(IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT);
			return;
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE:
			getGuidanceExclude().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL:
			getGuidanceAdditional().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__TASK:
			setTask((Task) null);
			return;
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
			getAdditionallyPerformedBy().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
			getAssistedBy().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
			getExternalInput().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
			getMandatoryInput().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
			getOptionalInput().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
			getOutput().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
			getPerformedPrimarilyBy().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS:
			getSelectedSteps().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED:
			getPerformedPrimarilyByExcluded().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE:
			getAdditionallyPerformedByExclude().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE:
			getMandatoryInputExclude().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE:
			getOptionalInputExclude().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT_EXCLUDE:
			getOutputExclude().clear();
			return;
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE:
			getSelectedStepsExclude().clear();
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
		case UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
			return IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT == null ? isSynchronizedWithSource != null
					: !IS_SYNCHRONIZED_WITH_SOURCE_EDEFAULT
							.equals(isSynchronizedWithSource);
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE:
			return guidanceExclude != null && !guidanceExclude.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL:
			return guidanceAdditional != null && !guidanceAdditional.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__TASK:
			return task != null;
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
			return additionallyPerformedBy != null
					&& !additionallyPerformedBy.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
			return assistedBy != null && !assistedBy.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__EXTERNAL_INPUT:
			return externalInput != null && !externalInput.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT:
			return mandatoryInput != null && !mandatoryInput.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT:
			return optionalInput != null && !optionalInput.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT:
			return output != null && !output.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
			return performedPrimarilyBy != null
					&& !performedPrimarilyBy.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS:
			return selectedSteps != null && !selectedSteps.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY_EXCLUDED:
			return performedPrimarilyByExcluded != null
					&& !performedPrimarilyByExcluded.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY_EXCLUDE:
			return additionallyPerformedByExclude != null
					&& !additionallyPerformedByExclude.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__MANDATORY_INPUT_EXCLUDE:
			return mandatoryInputExclude != null
					&& !mandatoryInputExclude.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__OPTIONAL_INPUT_EXCLUDE:
			return optionalInputExclude != null
					&& !optionalInputExclude.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__OUTPUT_EXCLUDE:
			return outputExclude != null && !outputExclude.isEmpty();
		case UmaPackage.TASK_DESCRIPTOR__SELECTED_STEPS_EXCLUDE:
			return selectedStepsExclude != null
					&& !selectedStepsExclude.isEmpty();
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
		if (baseClass == Descriptor.class) {
			switch (derivedFeatureID) {
			case UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
				return UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;
			case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE:
				return UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE;
			case UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL:
				return UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL;
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
		if (baseClass == Descriptor.class) {
			switch (baseFeatureID) {
			case UmaPackage.DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE:
				return UmaPackage.TASK_DESCRIPTOR__IS_SYNCHRONIZED_WITH_SOURCE;
			case UmaPackage.DESCRIPTOR__GUIDANCE_EXCLUDE:
				return UmaPackage.TASK_DESCRIPTOR__GUIDANCE_EXCLUDE;
			case UmaPackage.DESCRIPTOR__GUIDANCE_ADDITIONAL:
				return UmaPackage.TASK_DESCRIPTOR__GUIDANCE_ADDITIONAL;
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
		result.append(" (isSynchronizedWithSource: "); //$NON-NLS-1$
		result.append(isSynchronizedWithSource);
		result.append(')');
		return result.toString();
	}

	////////////////////////
	// Begin of custom code 
	////////////////////////

	static {
		// override the default value of BreakdownElement.isPlanned
		//
				DefaultValueManager.INSTANCE.setStaticDefaultValue(UmaPackage.eINSTANCE
						.getTaskDescriptor(), UmaPackage.eINSTANCE
						.getBreakdownElement_IsPlanned(), Boolean.FALSE);
	}
} //TaskDescriptorImpl
