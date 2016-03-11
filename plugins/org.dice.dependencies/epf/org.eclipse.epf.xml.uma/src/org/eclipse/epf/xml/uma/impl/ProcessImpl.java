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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessImpl#getIncludesPattern <em>Includes Pattern</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessImpl#getDefaultContext <em>Default Context</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessImpl#getValidContext <em>Valid Context</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.ProcessImpl#getDiagramURI <em>Diagram URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessImpl extends ActivityImpl implements org.eclipse.epf.xml.uma.Process {
	/**
	 * The cached value of the '{@link #getIncludesPattern() <em>Includes Pattern</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludesPattern()
	 * @generated
	 * @ordered
	 */
	protected EList<String> includesPattern;

	/**
	 * The default value of the '{@link #getDefaultContext() <em>Default Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultContext()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_CONTEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultContext() <em>Default Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultContext()
	 * @generated
	 * @ordered
	 */
	protected String defaultContext = DEFAULT_CONTEXT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValidContext() <em>Valid Context</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidContext()
	 * @generated
	 * @ordered
	 */
	protected EList<String> validContext;

	/**
	 * The default value of the '{@link #getDiagramURI() <em>Diagram URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramURI()
	 * @generated
	 * @ordered
	 */
	protected static final String DIAGRAM_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDiagramURI() <em>Diagram URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagramURI()
	 * @generated
	 * @ordered
	 */
	protected String diagramURI = DIAGRAM_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getIncludesPattern() {
		if (includesPattern == null) {
			includesPattern = new EDataTypeEList<String>(String.class, this, UmaPackage.PROCESS__INCLUDES_PATTERN);
		}
		return includesPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultContext() {
		return defaultContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultContext(String newDefaultContext) {
		String oldDefaultContext = defaultContext;
		defaultContext = newDefaultContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.PROCESS__DEFAULT_CONTEXT, oldDefaultContext, defaultContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getValidContext() {
		if (validContext == null) {
			validContext = new EDataTypeEList<String>(String.class, this, UmaPackage.PROCESS__VALID_CONTEXT);
		}
		return validContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDiagramURI() {
		return diagramURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagramURI(String newDiagramURI) {
		String oldDiagramURI = diagramURI;
		diagramURI = newDiagramURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UmaPackage.PROCESS__DIAGRAM_URI, oldDiagramURI, diagramURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UmaPackage.PROCESS__INCLUDES_PATTERN:
				return getIncludesPattern();
			case UmaPackage.PROCESS__DEFAULT_CONTEXT:
				return getDefaultContext();
			case UmaPackage.PROCESS__VALID_CONTEXT:
				return getValidContext();
			case UmaPackage.PROCESS__DIAGRAM_URI:
				return getDiagramURI();
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
			case UmaPackage.PROCESS__INCLUDES_PATTERN:
				getIncludesPattern().clear();
				getIncludesPattern().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.PROCESS__DEFAULT_CONTEXT:
				setDefaultContext((String)newValue);
				return;
			case UmaPackage.PROCESS__VALID_CONTEXT:
				getValidContext().clear();
				getValidContext().addAll((Collection<? extends String>)newValue);
				return;
			case UmaPackage.PROCESS__DIAGRAM_URI:
				setDiagramURI((String)newValue);
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
			case UmaPackage.PROCESS__INCLUDES_PATTERN:
				getIncludesPattern().clear();
				return;
			case UmaPackage.PROCESS__DEFAULT_CONTEXT:
				setDefaultContext(DEFAULT_CONTEXT_EDEFAULT);
				return;
			case UmaPackage.PROCESS__VALID_CONTEXT:
				getValidContext().clear();
				return;
			case UmaPackage.PROCESS__DIAGRAM_URI:
				setDiagramURI(DIAGRAM_URI_EDEFAULT);
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
			case UmaPackage.PROCESS__INCLUDES_PATTERN:
				return includesPattern != null && !includesPattern.isEmpty();
			case UmaPackage.PROCESS__DEFAULT_CONTEXT:
				return DEFAULT_CONTEXT_EDEFAULT == null ? defaultContext != null : !DEFAULT_CONTEXT_EDEFAULT.equals(defaultContext);
			case UmaPackage.PROCESS__VALID_CONTEXT:
				return validContext != null && !validContext.isEmpty();
			case UmaPackage.PROCESS__DIAGRAM_URI:
				return DIAGRAM_URI_EDEFAULT == null ? diagramURI != null : !DIAGRAM_URI_EDEFAULT.equals(diagramURI);
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
		result.append(" (includesPattern: ");
		result.append(includesPattern);
		result.append(", defaultContext: ");
		result.append(defaultContext);
		result.append(", validContext: ");
		result.append(validContext);
		result.append(", diagramURI: ");
		result.append(diagramURI);
		result.append(')');
		return result.toString();
	}

} //ProcessImpl
