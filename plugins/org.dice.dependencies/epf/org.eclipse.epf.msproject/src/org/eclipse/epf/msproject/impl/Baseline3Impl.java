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
import org.eclipse.epf.msproject.Baseline3;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.TimephasedDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Baseline3</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getTimephasedData <em>Timephased Data</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getNumber <em>Number</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.Baseline3Impl#getBCWP <em>BCWP</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Baseline3Impl extends EObjectImpl implements Baseline3 {
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
	protected static final String NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected String number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final String START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected String start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinish() <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinish()
	 * @generated
	 * @ordered
	 */
	protected static final String FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinish() <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinish()
	 * @generated
	 * @ordered
	 */
	protected String finish = FINISH_EDEFAULT;

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
	protected static final String COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected String cost = COST_EDEFAULT;

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
	protected Baseline3Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getBaseline3();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTimephasedData() {
		if (timephasedData == null) {
			timephasedData = new EObjectContainmentEList(TimephasedDataType.class, this, MsprojectPackage.BASELINE3__TIMEPHASED_DATA);
		}
		return timephasedData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(String newNumber) {
		String oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(String newStart) {
		String oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFinish() {
		return finish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinish(String newFinish) {
		String oldFinish = finish;
		finish = newFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__FINISH, oldFinish, finish));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__WORK, oldWork, work));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCost(String newCost) {
		String oldCost = cost;
		cost = newCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__COST, oldCost, cost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__BCWS, oldBCWS, bCWS, !oldBCWSESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.BASELINE3__BCWS, oldBCWS, BCWS_EDEFAULT, oldBCWSESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.BASELINE3__BCWP, oldBCWP, bCWP, !oldBCWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.BASELINE3__BCWP, oldBCWP, BCWP_EDEFAULT, oldBCWPESet));
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
				case MsprojectPackage.BASELINE3__TIMEPHASED_DATA:
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
			case MsprojectPackage.BASELINE3__TIMEPHASED_DATA:
				return getTimephasedData();
			case MsprojectPackage.BASELINE3__NUMBER:
				return getNumber();
			case MsprojectPackage.BASELINE3__START:
				return getStart();
			case MsprojectPackage.BASELINE3__FINISH:
				return getFinish();
			case MsprojectPackage.BASELINE3__WORK:
				return getWork();
			case MsprojectPackage.BASELINE3__COST:
				return getCost();
			case MsprojectPackage.BASELINE3__BCWS:
				return new Float(getBCWS());
			case MsprojectPackage.BASELINE3__BCWP:
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
			case MsprojectPackage.BASELINE3__TIMEPHASED_DATA:
				getTimephasedData().clear();
				getTimephasedData().addAll((Collection)newValue);
				return;
			case MsprojectPackage.BASELINE3__NUMBER:
				setNumber((String)newValue);
				return;
			case MsprojectPackage.BASELINE3__START:
				setStart((String)newValue);
				return;
			case MsprojectPackage.BASELINE3__FINISH:
				setFinish((String)newValue);
				return;
			case MsprojectPackage.BASELINE3__WORK:
				setWork((Object)newValue);
				return;
			case MsprojectPackage.BASELINE3__COST:
				setCost((String)newValue);
				return;
			case MsprojectPackage.BASELINE3__BCWS:
				setBCWS(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.BASELINE3__BCWP:
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
			case MsprojectPackage.BASELINE3__TIMEPHASED_DATA:
				getTimephasedData().clear();
				return;
			case MsprojectPackage.BASELINE3__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE3__START:
				setStart(START_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE3__FINISH:
				setFinish(FINISH_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE3__WORK:
				setWork(WORK_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE3__COST:
				setCost(COST_EDEFAULT);
				return;
			case MsprojectPackage.BASELINE3__BCWS:
				unsetBCWS();
				return;
			case MsprojectPackage.BASELINE3__BCWP:
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
			case MsprojectPackage.BASELINE3__TIMEPHASED_DATA:
				return timephasedData != null && !timephasedData.isEmpty();
			case MsprojectPackage.BASELINE3__NUMBER:
				return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
			case MsprojectPackage.BASELINE3__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case MsprojectPackage.BASELINE3__FINISH:
				return FINISH_EDEFAULT == null ? finish != null : !FINISH_EDEFAULT.equals(finish);
			case MsprojectPackage.BASELINE3__WORK:
				return WORK_EDEFAULT == null ? work != null : !WORK_EDEFAULT.equals(work);
			case MsprojectPackage.BASELINE3__COST:
				return COST_EDEFAULT == null ? cost != null : !COST_EDEFAULT.equals(cost);
			case MsprojectPackage.BASELINE3__BCWS:
				return isSetBCWS();
			case MsprojectPackage.BASELINE3__BCWP:
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
		result.append(", start: ");
		result.append(start);
		result.append(", finish: ");
		result.append(finish);
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

} //Baseline3Impl
