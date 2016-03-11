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
import java.util.Date;
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
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessComponentInterface;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getChangeDate <em>Change Date</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getCopyrightStatement <em>Copyright Statement</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link org.eclipse.epf.uma.impl.ProcessComponentImpl#getProcess <em>Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessComponentImpl extends ProcessPackageImpl implements
		ProcessComponent {
	/**
	 * The default value of the '{@link #getAuthors() <em>Authors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthors()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHORS_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAuthors() <em>Authors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthors()
	 * @generated
	 * @ordered
	 */
	protected String authors = AUTHORS_EDEFAULT;

	/**
	 * The default value of the '{@link #getChangeDate() <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date CHANGE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChangeDate() <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDate()
	 * @generated
	 * @ordered
	 */
	protected Date changeDate = CHANGE_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getChangeDescription() <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANGE_DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getChangeDescription() <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDescription()
	 * @generated
	 * @ordered
	 */
	protected String changeDescription = CHANGE_DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCopyrightStatement() <em>Copyright Statement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCopyrightStatement()
	 * @generated
	 * @ordered
	 */
	protected SupportingMaterial copyrightStatement;

	/**
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<ProcessComponentInterface> interfaces;

	/**
	 * The cached value of the '{@link #getProcess() <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcess()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.epf.uma.Process process;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessComponentImpl() {
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
		return UmaPackage.Literals.PROCESS_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthors(String newAuthors) {
		String oldAuthors = authors;
		authors = newAuthors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_COMPONENT__AUTHORS, oldAuthors, authors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getChangeDate() {
		return changeDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeDate(Date newChangeDate) {
		Date oldChangeDate = changeDate;
		changeDate = newChangeDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_COMPONENT__CHANGE_DATE, oldChangeDate,
					changeDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChangeDescription() {
		return changeDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeDescription(String newChangeDescription) {
		String oldChangeDescription = changeDescription;
		changeDescription = newChangeDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION,
					oldChangeDescription, changeDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_COMPONENT__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportingMaterial getCopyrightStatement() {
		if (copyrightStatement != null
				&& ((EObject) copyrightStatement).eIsProxy()) {
			InternalEObject oldCopyrightStatement = (InternalEObject) copyrightStatement;
			copyrightStatement = (SupportingMaterial) eResolveProxy(oldCopyrightStatement);
			if (copyrightStatement != oldCopyrightStatement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT,
							oldCopyrightStatement, copyrightStatement));
			}
		}
		return copyrightStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportingMaterial basicGetCopyrightStatement() {
		return copyrightStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCopyrightStatement(SupportingMaterial newCopyrightStatement) {
		SupportingMaterial oldCopyrightStatement = copyrightStatement;
		copyrightStatement = newCopyrightStatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT,
					oldCopyrightStatement, copyrightStatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ProcessComponentInterface> getInterfaces() {
		if (interfaces == null) {
			interfaces = new EObjectResolvingEList<ProcessComponentInterface>(
					ProcessComponentInterface.class, this,
					UmaPackage.PROCESS_COMPONENT__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Process getProcess() {
		if (process != null && ((EObject) process).eIsProxy()) {
			InternalEObject oldProcess = (InternalEObject) process;
			process = (org.eclipse.epf.uma.Process) eResolveProxy(oldProcess);
			if (process != oldProcess) {
				InternalEObject newProcess = (InternalEObject) process;
				NotificationChain msgs = oldProcess.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_COMPONENT__PROCESS, null,
						null);
				if (newProcess.eInternalContainer() == null) {
					msgs = newProcess
							.eInverseAdd(this, EOPPOSITE_FEATURE_BASE
									- UmaPackage.PROCESS_COMPONENT__PROCESS,
									null, msgs);
				}
				if (msgs != null)
					msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							UmaPackage.PROCESS_COMPONENT__PROCESS, oldProcess,
							process));
			}
		}
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.epf.uma.Process basicGetProcess() {
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcess(
			org.eclipse.epf.uma.Process newProcess, NotificationChain msgs) {
		org.eclipse.epf.uma.Process oldProcess = process;
		process = newProcess;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, UmaPackage.PROCESS_COMPONENT__PROCESS,
					oldProcess, newProcess);
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
	public void setProcess(org.eclipse.epf.uma.Process newProcess) {
		if (newProcess != process) {
			NotificationChain msgs = null;
			if (process != null)
				msgs = ((InternalEObject) process).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_COMPONENT__PROCESS, null,
						msgs);
			if (newProcess != null)
				msgs = ((InternalEObject) newProcess).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- UmaPackage.PROCESS_COMPONENT__PROCESS, null,
						msgs);
			msgs = basicSetProcess(newProcess, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					UmaPackage.PROCESS_COMPONENT__PROCESS, newProcess,
					newProcess));
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
		case UmaPackage.PROCESS_COMPONENT__PROCESS:
			return basicSetProcess(null, msgs);
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
		case UmaPackage.PROCESS_COMPONENT__AUTHORS:
			return getAuthors();
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DATE:
			return getChangeDate();
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			return getChangeDescription();
		case UmaPackage.PROCESS_COMPONENT__VERSION:
			return getVersion();
		case UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			if (resolve)
				return getCopyrightStatement();
			return basicGetCopyrightStatement();
		case UmaPackage.PROCESS_COMPONENT__INTERFACES:
			return getInterfaces();
		case UmaPackage.PROCESS_COMPONENT__PROCESS:
			if (resolve)
				return getProcess();
			return basicGetProcess();
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
		case UmaPackage.PROCESS_COMPONENT__AUTHORS:
			setAuthors((String) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DATE:
			setChangeDate((Date) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			setChangeDescription((String) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT__VERSION:
			setVersion((String) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			setCopyrightStatement((SupportingMaterial) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT__INTERFACES:
			getInterfaces().clear();
			getInterfaces().addAll(
					(Collection<? extends ProcessComponentInterface>) newValue);
			return;
		case UmaPackage.PROCESS_COMPONENT__PROCESS:
			setProcess((org.eclipse.epf.uma.Process) newValue);
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
		case UmaPackage.PROCESS_COMPONENT__AUTHORS:
			setAuthors(AUTHORS_EDEFAULT);
			return;
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DATE:
			setChangeDate(CHANGE_DATE_EDEFAULT);
			return;
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			setChangeDescription(CHANGE_DESCRIPTION_EDEFAULT);
			return;
		case UmaPackage.PROCESS_COMPONENT__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			setCopyrightStatement((SupportingMaterial) null);
			return;
		case UmaPackage.PROCESS_COMPONENT__INTERFACES:
			getInterfaces().clear();
			return;
		case UmaPackage.PROCESS_COMPONENT__PROCESS:
			setProcess((org.eclipse.epf.uma.Process) null);
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
		case UmaPackage.PROCESS_COMPONENT__AUTHORS:
			return AUTHORS_EDEFAULT == null ? authors != null
					: !AUTHORS_EDEFAULT.equals(authors);
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DATE:
			return CHANGE_DATE_EDEFAULT == null ? changeDate != null
					: !CHANGE_DATE_EDEFAULT.equals(changeDate);
		case UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION:
			return CHANGE_DESCRIPTION_EDEFAULT == null ? changeDescription != null
					: !CHANGE_DESCRIPTION_EDEFAULT.equals(changeDescription);
		case UmaPackage.PROCESS_COMPONENT__VERSION:
			return VERSION_EDEFAULT == null ? version != null
					: !VERSION_EDEFAULT.equals(version);
		case UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
			return copyrightStatement != null;
		case UmaPackage.PROCESS_COMPONENT__INTERFACES:
			return interfaces != null && !interfaces.isEmpty();
		case UmaPackage.PROCESS_COMPONENT__PROCESS:
			return process != null;
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
		if (baseClass == MethodUnit.class) {
			switch (derivedFeatureID) {
			case UmaPackage.PROCESS_COMPONENT__AUTHORS:
				return UmaPackage.METHOD_UNIT__AUTHORS;
			case UmaPackage.PROCESS_COMPONENT__CHANGE_DATE:
				return UmaPackage.METHOD_UNIT__CHANGE_DATE;
			case UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION:
				return UmaPackage.METHOD_UNIT__CHANGE_DESCRIPTION;
			case UmaPackage.PROCESS_COMPONENT__VERSION:
				return UmaPackage.METHOD_UNIT__VERSION;
			case UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT:
				return UmaPackage.METHOD_UNIT__COPYRIGHT_STATEMENT;
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
		if (baseClass == MethodUnit.class) {
			switch (baseFeatureID) {
			case UmaPackage.METHOD_UNIT__AUTHORS:
				return UmaPackage.PROCESS_COMPONENT__AUTHORS;
			case UmaPackage.METHOD_UNIT__CHANGE_DATE:
				return UmaPackage.PROCESS_COMPONENT__CHANGE_DATE;
			case UmaPackage.METHOD_UNIT__CHANGE_DESCRIPTION:
				return UmaPackage.PROCESS_COMPONENT__CHANGE_DESCRIPTION;
			case UmaPackage.METHOD_UNIT__VERSION:
				return UmaPackage.PROCESS_COMPONENT__VERSION;
			case UmaPackage.METHOD_UNIT__COPYRIGHT_STATEMENT:
				return UmaPackage.PROCESS_COMPONENT__COPYRIGHT_STATEMENT;
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
		result.append(" (authors: "); //$NON-NLS-1$
		result.append(authors);
		result.append(", changeDate: "); //$NON-NLS-1$
		result.append(changeDate);
		result.append(", changeDescription: "); //$NON-NLS-1$
		result.append(changeDescription);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //ProcessComponentImpl
