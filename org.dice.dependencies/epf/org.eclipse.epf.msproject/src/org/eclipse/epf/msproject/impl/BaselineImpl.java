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
package org.eclipse.epf.msproject.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.msproject.Baseline;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.TimephasedDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Baseline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getTimephasedData <em>Timephased Data</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#isInterim <em>Interim</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getDurationFormat <em>Duration Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#isEstimatedDuration <em>Estimated Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.BaselineImpl#getBCWP <em>BCWP</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BaselineImpl extends EObjectImpl implements Baseline {
	/**
	 * The cached value of the '{@link #getTimephasedData() <em>Timephased Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimephasedData()
	 * @generated
	 * @ordered
	 */
	protected EList timephasedData = null;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected BigInteger number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #isInterim() <em>Interim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterim()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERIM_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInterim() <em>Interim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterim()
	 * @generated
	 * @ordered
	 */
	protected boolean interim = INTERIM_EDEFAULT;

	/**
	 * This is true if the Interim attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean interimESet = false;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Object start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinish() <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinish()
	 * @generated
	 * @ordered
	 */
	protected static final Object FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinish() <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinish()
	 * @generated
	 * @ordered
	 */
	protected Object finish = FINISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final Object DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected Object duration = DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDurationFormat() <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DURATION_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDurationFormat() <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger durationFormat = DURATION_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #isEstimatedDuration() <em>Estimated Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEstimatedDuration()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ESTIMATED_DURATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEstimatedDuration() <em>Estimated Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEstimatedDuration()
	 * @generated
	 * @ordered
	 */
	protected boolean estimatedDuration = ESTIMATED_DURATION_EDEFAULT;

	/**
	 * This is true if the Estimated Duration attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean estimatedDurationESet = false;

	/**
	 * The default value of the '{@link #getWork() <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWork() <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected Object work = WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal cost = COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getBCWS() <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWS()
	 * @generated
	 * @ordered
	 */
	protected static final float BCWS_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getBCWS() <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWS()
	 * @generated
	 * @ordered
	 */
	protected float bCWS = BCWS_EDEFAULT;

	/**
	 * This is true if the BCWS attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bCWSESet = false;

	/**
	 * The default value of the '{@link #getBCWP() <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWP()
	 * @generated
	 * @ordered
	 */
	protected static final float BCWP_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getBCWP() <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWP()
	 * @generated
	 * @ordered
	 */
	protected float bCWP = BCWP_EDEFAULT;

	/**
	 * This is true if the BCWP attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bCWPESet = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BaselineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getBaseline();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTimephasedData() {
		if (timephasedData == null) {
			timephasedData = new EObjectContainmentEList(TimephasedDataType.class, this, MsprojectPackage.BASELINE__TIMEPHASED_DATA);
		}
		return timephasedData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(BigInteger newNumber) {
		BigInteger oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInterim() {
		return interim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterim(boolean newInterim) {
		boolean oldInterim = interim;
		interim = newInterim;
		boolean oldInterimESet = interimESet;
		interimESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__INTERIM, oldInterim, interim, !oldInterimESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInterim() {
		boolean oldInterim = interim;
		boolean oldInterimESet = interimESet;
		interim = INTERIM_EDEFAULT;
		interimESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.BASELINE__INTERIM, oldInterim, INTERIM_EDEFAULT, oldInterimESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInterim() {
		return interimESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(Object newStart) {
		Object oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getFinish() {
		return finish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinish(Object newFinish) {
		Object oldFinish = finish;
		finish = newFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__FINISH, oldFinish, finish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(Object newDuration) {
		Object oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDurationFormat() {
		return durationFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDurationFormat(BigInteger newDurationFormat) {
		BigInteger oldDurationFormat = durationFormat;
		durationFormat = newDurationFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__DURATION_FORMAT, oldDurationFormat, durationFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEstimatedDuration() {
		return estimatedDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEstimatedDuration(boolean newEstimatedDuration) {
		boolean oldEstimatedDuration = estimatedDuration;
		estimatedDuration = newEstimatedDuration;
		boolean oldEstimatedDurationESet = estimatedDurationESet;
		estimatedDurationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__ESTIMATED_DURATION, oldEstimatedDuration, estimatedDuration, !oldEstimatedDurationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEstimatedDuration() {
		boolean oldEstimatedDuration = estimatedDuration;
		boolean oldEstimatedDurationESet = estimatedDurationESet;
		estimatedDuration = ESTIMATED_DURATION_EDEFAULT;
		estimatedDurationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.BASELINE__ESTIMATED_DURATION, oldEstimatedDuration, ESTIMATED_DURATION_EDEFAULT, oldEstimatedDurationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEstimatedDuration() {
		return estimatedDurationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getWork() {
		return work;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWork(Object newWork) {
		Object oldWork = work;
		work = newWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__WORK, oldWork, work));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCost(BigDecimal newCost) {
		BigDecimal oldCost = cost;
		cost = newCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__COST, oldCost, cost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getBCWS() {
		return bCWS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBCWS(float newBCWS) {
		float oldBCWS = bCWS;
		bCWS = newBCWS;
		boolean oldBCWSESet = bCWSESet;
		bCWSESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__BCWS, oldBCWS, bCWS, !oldBCWSESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBCWS() {
		float oldBCWS = bCWS;
		boolean oldBCWSESet = bCWSESet;
		bCWS = BCWS_EDEFAULT;
		bCWSESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.BASELINE__BCWS, oldBCWS, BCWS_EDEFAULT, oldBCWSESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBCWS() {
		return bCWSESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getBCWP() {
		return bCWP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBCWP(float newBCWP) {
		float oldBCWP = bCWP;
		bCWP = newBCWP;
		boolean oldBCWPESet = bCWPESet;
		bCWPESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE__BCWP, oldBCWP, bCWP, !oldBCWPESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBCWP() {
		float oldBCWP = bCWP;
		boolean oldBCWPESet = bCWPESet;
		bCWP = BCWP_EDEFAULT;
		bCWPESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.BASELINE__BCWP, oldBCWP, BCWP_EDEFAULT, oldBCWPESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBCWP() {
		return bCWPESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.BASELINE__TIMEPHASED_DATA:
					return ((InternalEList)getTimephasedData()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.BASELINE__TIMEPHASED_DATA:
				return getTimephasedData();
			case MsprojectPackage.BASELINE__NUMBER:
				return getNumber();
			case MsprojectPackage.BASELINE__INTERIM:
				return isInterim() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.BASELINE__START:
				return getStart();
			case MsprojectPackage.BASELINE__FINISH:
				return getFinish();
			case MsprojectPackage.BASELINE__DURATION:
				return getDuration();
			case MsprojectPackage.BASELINE__DURATION_FORMAT:
				return getDurationFormat();
			case MsprojectPackage.BASELINE__ESTIMATED_DURATION:
				return isEstimatedDuration() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.BASELINE__WORK:
				return getWork();
			case MsprojectPackage.BASELINE__COST:
				return getCost();
			case MsprojectPackage.BASELINE__BCWS:
				return new Float(getBCWS());
			case MsprojectPackage.BASELINE__BCWP:
				return new Float(getBCWP());
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.BASELINE__TIMEPHASED_DATA:
				getTimephasedData().clear();
				getTimephasedData().addAll((Collection)newValue);
				return;
			case MsprojectPackage.BASELINE__NUMBER:
				setNumber((BigInteger)newValue);
				return;
			case MsprojectPackage.BASELINE__INTERIM:
				setInterim(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.BASELINE__START:
				setStart((Object)newValue);
				return;
			case MsprojectPackage.BASELINE__FINISH:
				setFinish((Object)newValue);
				return;
			case MsprojectPackage.BASELINE__DURATION:
				setDuration((Object)newValue);
				return;
			case MsprojectPackage.BASELINE__DURATION_FORMAT:
				setDurationFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.BASELINE__ESTIMATED_DURATION:
				setEstimatedDuration(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.BASELINE__WORK:
				setWork((Object)newValue);
				return;
			case MsprojectPackage.BASELINE__COST:
				setCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.BASELINE__BCWS:
				setBCWS(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.BASELINE__BCWP:
				setBCWP(((Float)newValue).floatValue());
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.BASELINE__TIMEPHASED_DATA:
				getTimephasedData().clear();
				return;
			case MsprojectPackage.BASELINE__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__INTERIM:
				unsetInterim();
				return;
			case MsprojectPackage.BASELINE__START:
				setStart(START_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__FINISH:
				setFinish(FINISH_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__DURATION_FORMAT:
				setDurationFormat(DURATION_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__ESTIMATED_DURATION:
				unsetEstimatedDuration();
				return;
			case MsprojectPackage.BASELINE__WORK:
				setWork(WORK_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__COST:
				setCost(COST_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE__BCWS:
				unsetBCWS();
				return;
			case MsprojectPackage.BASELINE__BCWP:
				unsetBCWP();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.BASELINE__TIMEPHASED_DATA:
				return timephasedData != null && !timephasedData.isEmpty();
			case MsprojectPackage.BASELINE__NUMBER:
				return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
			case MsprojectPackage.BASELINE__INTERIM:
				return isSetInterim();
			case MsprojectPackage.BASELINE__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case MsprojectPackage.BASELINE__FINISH:
				return FINISH_EDEFAULT == null ? finish != null : !FINISH_EDEFAULT.equals(finish);
			case MsprojectPackage.BASELINE__DURATION:
				return DURATION_EDEFAULT == null ? duration != null : !DURATION_EDEFAULT.equals(duration);
			case MsprojectPackage.BASELINE__DURATION_FORMAT:
				return DURATION_FORMAT_EDEFAULT == null ? durationFormat != null : !DURATION_FORMAT_EDEFAULT.equals(durationFormat);
			case MsprojectPackage.BASELINE__ESTIMATED_DURATION:
				return isSetEstimatedDuration();
			case MsprojectPackage.BASELINE__WORK:
				return WORK_EDEFAULT == null ? work != null : !WORK_EDEFAULT.equals(work);
			case MsprojectPackage.BASELINE__COST:
				return COST_EDEFAULT == null ? cost != null : !COST_EDEFAULT.equals(cost);
			case MsprojectPackage.BASELINE__BCWS:
				return isSetBCWS();
			case MsprojectPackage.BASELINE__BCWP:
				return isSetBCWP();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (number: ");
		result.append(number);
		result.append(", interim: ");
		if (interimESet) result.append(interim); else result.append("<unset>");
		result.append(", start: ");
		result.append(start);
		result.append(", finish: ");
		result.append(finish);
		result.append(", duration: ");
		result.append(duration);
		result.append(", durationFormat: ");
		result.append(durationFormat);
		result.append(", estimatedDuration: ");
		if (estimatedDurationESet) result.append(estimatedDuration); else result.append("<unset>");
		result.append(", work: ");
		result.append(work);
		result.append(", cost: ");
		result.append(cost);
		result.append(", bCWS: ");
		if (bCWSESet) result.append(bCWS); else result.append("<unset>");
		result.append(", bCWP: ");
		if (bCWPESet) result.append(bCWP); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //BaselineImpl
