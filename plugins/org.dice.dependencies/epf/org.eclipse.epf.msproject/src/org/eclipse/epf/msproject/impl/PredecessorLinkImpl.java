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

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.PredecessorLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Predecessor Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl#getPredecessorUID <em>Predecessor UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl#isCrossProject <em>Cross Project</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl#getCrossProjectName <em>Cross Project Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl#getLinkLag <em>Link Lag</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl#getLagFormat <em>Lag Format</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredecessorLinkImpl extends EObjectImpl implements PredecessorLink {
	/**
	 * The default value of the '{@link #getPredecessorUID() <em>Predecessor UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessorUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PREDECESSOR_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredecessorUID() <em>Predecessor UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessorUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger predecessorUID = PREDECESSOR_UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isCrossProject() <em>Cross Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCrossProject()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CROSS_PROJECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCrossProject() <em>Cross Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCrossProject()
	 * @generated
	 * @ordered
	 */
	protected boolean crossProject = CROSS_PROJECT_EDEFAULT;

	/**
	 * This is true if the Cross Project attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean crossProjectESet = false;

	/**
	 * The default value of the '{@link #getCrossProjectName() <em>Cross Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String CROSS_PROJECT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCrossProjectName() <em>Cross Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCrossProjectName()
	 * @generated
	 * @ordered
	 */
	protected String crossProjectName = CROSS_PROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLinkLag() <em>Link Lag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkLag()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger LINK_LAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLinkLag() <em>Link Lag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkLag()
	 * @generated
	 * @ordered
	 */
	protected BigInteger linkLag = LINK_LAG_EDEFAULT;

	/**
	 * The default value of the '{@link #getLagFormat() <em>Lag Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLagFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger LAG_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLagFormat() <em>Lag Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLagFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger lagFormat = LAG_FORMAT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PredecessorLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getPredecessorLink();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getPredecessorUID() {
		return predecessorUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredecessorUID(BigInteger newPredecessorUID) {
		BigInteger oldPredecessorUID = predecessorUID;
		predecessorUID = newPredecessorUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PREDECESSOR_LINK__PREDECESSOR_UID, oldPredecessorUID, predecessorUID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(BigInteger newType) {
		BigInteger oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PREDECESSOR_LINK__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCrossProject() {
		return crossProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCrossProject(boolean newCrossProject) {
		boolean oldCrossProject = crossProject;
		crossProject = newCrossProject;
		boolean oldCrossProjectESet = crossProjectESet;
		crossProjectESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT, oldCrossProject, crossProject, !oldCrossProjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCrossProject() {
		boolean oldCrossProject = crossProject;
		boolean oldCrossProjectESet = crossProjectESet;
		crossProject = CROSS_PROJECT_EDEFAULT;
		crossProjectESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT, oldCrossProject, CROSS_PROJECT_EDEFAULT, oldCrossProjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCrossProject() {
		return crossProjectESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCrossProjectName() {
		return crossProjectName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCrossProjectName(String newCrossProjectName) {
		String oldCrossProjectName = crossProjectName;
		crossProjectName = newCrossProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT_NAME, oldCrossProjectName, crossProjectName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getLinkLag() {
		return linkLag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkLag(BigInteger newLinkLag) {
		BigInteger oldLinkLag = linkLag;
		linkLag = newLinkLag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PREDECESSOR_LINK__LINK_LAG, oldLinkLag, linkLag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getLagFormat() {
		return lagFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLagFormat(BigInteger newLagFormat) {
		BigInteger oldLagFormat = lagFormat;
		lagFormat = newLagFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PREDECESSOR_LINK__LAG_FORMAT, oldLagFormat, lagFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.PREDECESSOR_LINK__PREDECESSOR_UID:
				return getPredecessorUID();
			case MsprojectPackage.PREDECESSOR_LINK__TYPE:
				return getType();
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT:
				return isCrossProject() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT_NAME:
				return getCrossProjectName();
			case MsprojectPackage.PREDECESSOR_LINK__LINK_LAG:
				return getLinkLag();
			case MsprojectPackage.PREDECESSOR_LINK__LAG_FORMAT:
				return getLagFormat();
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
			case MsprojectPackage.PREDECESSOR_LINK__PREDECESSOR_UID:
				setPredecessorUID((BigInteger)newValue);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__TYPE:
				setType((BigInteger)newValue);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT:
				setCrossProject(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT_NAME:
				setCrossProjectName((String)newValue);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__LINK_LAG:
				setLinkLag((BigInteger)newValue);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__LAG_FORMAT:
				setLagFormat((BigInteger)newValue);
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
			case MsprojectPackage.PREDECESSOR_LINK__PREDECESSOR_UID:
				setPredecessorUID(PREDECESSOR_UID_EDEFAULT);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT:
				unsetCrossProject();
				return;
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT_NAME:
				setCrossProjectName(CROSS_PROJECT_NAME_EDEFAULT);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__LINK_LAG:
				setLinkLag(LINK_LAG_EDEFAULT);
				return;
			case MsprojectPackage.PREDECESSOR_LINK__LAG_FORMAT:
				setLagFormat(LAG_FORMAT_EDEFAULT);
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
			case MsprojectPackage.PREDECESSOR_LINK__PREDECESSOR_UID:
				return PREDECESSOR_UID_EDEFAULT == null ? predecessorUID != null : !PREDECESSOR_UID_EDEFAULT.equals(predecessorUID);
			case MsprojectPackage.PREDECESSOR_LINK__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT:
				return isSetCrossProject();
			case MsprojectPackage.PREDECESSOR_LINK__CROSS_PROJECT_NAME:
				return CROSS_PROJECT_NAME_EDEFAULT == null ? crossProjectName != null : !CROSS_PROJECT_NAME_EDEFAULT.equals(crossProjectName);
			case MsprojectPackage.PREDECESSOR_LINK__LINK_LAG:
				return LINK_LAG_EDEFAULT == null ? linkLag != null : !LINK_LAG_EDEFAULT.equals(linkLag);
			case MsprojectPackage.PREDECESSOR_LINK__LAG_FORMAT:
				return LAG_FORMAT_EDEFAULT == null ? lagFormat != null : !LAG_FORMAT_EDEFAULT.equals(lagFormat);
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
		result.append(" (predecessorUID: ");
		result.append(predecessorUID);
		result.append(", type: ");
		result.append(type);
		result.append(", crossProject: ");
		if (crossProjectESet) result.append(crossProject); else result.append("<unset>");
		result.append(", crossProjectName: ");
		result.append(crossProjectName);
		result.append(", linkLag: ");
		result.append(linkLag);
		result.append(", lagFormat: ");
		result.append(lagFormat);
		result.append(')');
		return result.toString();
	}

} //PredecessorLinkImpl
