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

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.uma.PlanningData;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Planning Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.PlanningDataImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.PlanningDataImpl#getFinishDate <em>Finish Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.PlanningDataImpl#getRank <em>Rank</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlanningDataImpl extends ProcessElementImpl implements
		PlanningData {
	/**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected Date startDate = START_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinishDate() <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date FINISH_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinishDate() <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishDate()
	 * @generated
	 * @ordered
	 */
	protected Date finishDate = FINISH_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected static final int RANK_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRank() <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRank()
	 * @generated
	 * @ordered
	 */
	protected int rank = RANK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanningDataImpl() {
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
		return UmaPackage.Literals.PLANNING_DATA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(Date newStartDate) {
		Date oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PLANNING_DATA__START_DATE, oldStartDate,
					startDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getFinishDate() {
		return finishDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinishDate(Date newFinishDate) {
		Date oldFinishDate = finishDate;
		finishDate = newFinishDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PLANNING_DATA__FINISH_DATE, oldFinishDate,
					finishDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRank(int newRank) {
		int oldRank = rank;
		rank = newRank;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PLANNING_DATA__RANK, oldRank, rank));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case UmaPackage.PLANNING_DATA__START_DATE:
			return getStartDate();
		case UmaPackage.PLANNING_DATA__FINISH_DATE:
			return getFinishDate();
		case UmaPackage.PLANNING_DATA__RANK:
			return new Integer(getRank());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case UmaPackage.PLANNING_DATA__START_DATE:
			setStartDate((Date) newValue);
			return;
		case UmaPackage.PLANNING_DATA__FINISH_DATE:
			setFinishDate((Date) newValue);
			return;
		case UmaPackage.PLANNING_DATA__RANK:
			setRank(((Integer) newValue).intValue());
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
		case UmaPackage.PLANNING_DATA__START_DATE:
			setStartDate(START_DATE_EDEFAULT);
			return;
		case UmaPackage.PLANNING_DATA__FINISH_DATE:
			setFinishDate(FINISH_DATE_EDEFAULT);
			return;
		case UmaPackage.PLANNING_DATA__RANK:
			setRank(RANK_EDEFAULT);
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
		case UmaPackage.PLANNING_DATA__START_DATE:
			return START_DATE_EDEFAULT == null ? startDate != null
					: !START_DATE_EDEFAULT.equals(startDate);
		case UmaPackage.PLANNING_DATA__FINISH_DATE:
			return FINISH_DATE_EDEFAULT == null ? finishDate != null
					: !FINISH_DATE_EDEFAULT.equals(finishDate);
		case UmaPackage.PLANNING_DATA__RANK:
			return rank != RANK_EDEFAULT;
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (startDate: "); //$NON-NLS-1$
		result.append(startDate);
		result.append(", finishDate: "); //$NON-NLS-1$
		result.append(finishDate);
		result.append(", rank: "); //$NON-NLS-1$
		result.append(rank);
		result.append(')');
		return result.toString();
	}

} //PlanningDataImpl
