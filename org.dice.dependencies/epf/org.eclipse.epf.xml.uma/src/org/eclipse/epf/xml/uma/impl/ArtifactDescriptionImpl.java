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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.epf.xml.uma.ArtifactDescription;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifact Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl#getBriefOutline <em>Brief Outline</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl#getRepresentationOptions <em>Representation Options</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl#getRepresentation <em>Representation</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ArtifactDescriptionImpl#getNotation <em>Notation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArtifactDescriptionImpl extends WorkProductDescriptionImpl implements ArtifactDescription {
	/**
	 * The default value of the '{@link #getBriefOutline() <em>Brief Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBriefOutline()
	 * @generated
	 * @ordered
	 */
	protected static final String BRIEF_OUTLINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBriefOutline() <em>Brief Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBriefOutline()
	 * @generated
	 * @ordered
	 */
	protected String briefOutline = BRIEF_OUTLINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepresentationOptions() <em>Representation Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentationOptions()
	 * @generated
	 * @ordered
	 */
	protected static final String REPRESENTATION_OPTIONS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepresentationOptions() <em>Representation Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentationOptions()
	 * @generated
	 * @ordered
	 */
	protected String representationOptions = REPRESENTATION_OPTIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepresentation() <em>Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentation()
	 * @generated
	 * @ordered
	 */
	protected static final String REPRESENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepresentation() <em>Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentation()
	 * @generated
	 * @ordered
	 */
	protected String representation = REPRESENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNotation() <em>Notation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotation()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotation() <em>Notation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotation()
	 * @generated
	 * @ordered
	 */
	protected String notation = NOTATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.ARTIFACT_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBriefOutline() {
		return briefOutline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBriefOutline(String newBriefOutline) {
		String oldBriefOutline = briefOutline;
		briefOutline = newBriefOutline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ARTIFACT_DESCRIPTION__BRIEF_OUTLINE, oldBriefOutline, briefOutline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRepresentationOptions() {
		return representationOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepresentationOptions(String newRepresentationOptions) {
		String oldRepresentationOptions = representationOptions;
		representationOptions = newRepresentationOptions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS, oldRepresentationOptions, representationOptions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRepresentation() {
		return representation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepresentation(String newRepresentation) {
		String oldRepresentation = representation;
		representation = newRepresentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION, oldRepresentation, representation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotation() {
		return notation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotation(String newNotation) {
		String oldNotation = notation;
		notation = newNotation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.ARTIFACT_DESCRIPTION__NOTATION, oldNotation, notation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UmaPackage.ARTIFACT_DESCRIPTION__BRIEF_OUTLINE:
				return getBriefOutline();
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS:
				return getRepresentationOptions();
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION:
				return getRepresentation();
			case UmaPackage.ARTIFACT_DESCRIPTION__NOTATION:
				return getNotation();
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
			case UmaPackage.ARTIFACT_DESCRIPTION__BRIEF_OUTLINE:
				setBriefOutline((String)newValue);
				return;
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS:
				setRepresentationOptions((String)newValue);
				return;
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION:
				setRepresentation((String)newValue);
				return;
			case UmaPackage.ARTIFACT_DESCRIPTION__NOTATION:
				setNotation((String)newValue);
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
			case UmaPackage.ARTIFACT_DESCRIPTION__BRIEF_OUTLINE:
				setBriefOutline(BRIEF_OUTLINE_EDEFAULT);
				return;
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS:
				setRepresentationOptions(REPRESENTATION_OPTIONS_EDEFAULT);
				return;
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION:
				setRepresentation(REPRESENTATION_EDEFAULT);
				return;
			case UmaPackage.ARTIFACT_DESCRIPTION__NOTATION:
				setNotation(NOTATION_EDEFAULT);
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
			case UmaPackage.ARTIFACT_DESCRIPTION__BRIEF_OUTLINE:
				return BRIEF_OUTLINE_EDEFAULT == null ? briefOutline != null : !BRIEF_OUTLINE_EDEFAULT.equals(briefOutline);
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION_OPTIONS:
				return REPRESENTATION_OPTIONS_EDEFAULT == null ? representationOptions != null : !REPRESENTATION_OPTIONS_EDEFAULT.equals(representationOptions);
			case UmaPackage.ARTIFACT_DESCRIPTION__REPRESENTATION:
				return REPRESENTATION_EDEFAULT == null ? representation != null : !REPRESENTATION_EDEFAULT.equals(representation);
			case UmaPackage.ARTIFACT_DESCRIPTION__NOTATION:
				return NOTATION_EDEFAULT == null ? notation != null : !NOTATION_EDEFAULT.equals(notation);
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
		result.append(" (briefOutline: ");
		result.append(briefOutline);
		result.append(", representationOptions: ");
		result.append(representationOptions);
		result.append(", representation: ");
		result.append(representation);
		result.append(", notation: ");
		result.append(notation);
		result.append(')');
		return result.toString();
	}

} //ArtifactDescriptionImpl
