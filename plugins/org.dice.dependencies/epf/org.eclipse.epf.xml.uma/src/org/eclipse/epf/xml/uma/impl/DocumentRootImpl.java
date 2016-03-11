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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.xml.uma.DocumentRoot;
import org.eclipse.epf.xml.uma.MethodConfiguration;
import org.eclipse.epf.xml.uma.MethodLibrary;
import org.eclipse.epf.xml.uma.MethodPlugin;
import org.eclipse.epf.xml.uma.UmaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl#getMethodConfiguration <em>Method Configuration</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl#getMethodLibrary <em>Method Library</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.impl.DocumentRootImpl#getMethodPlugin <em>Method Plugin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends MultiResourceEObject implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UmaPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, UmaPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, UmaPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, UmaPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodConfiguration getMethodConfiguration() {
		return (MethodConfiguration)getMixed().get(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_CONFIGURATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodConfiguration(MethodConfiguration newMethodConfiguration, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_CONFIGURATION, newMethodConfiguration, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodConfiguration(MethodConfiguration newMethodConfiguration) {
		((FeatureMap.Internal)getMixed()).set(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_CONFIGURATION, newMethodConfiguration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodLibrary getMethodLibrary() {
		return (MethodLibrary)getMixed().get(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_LIBRARY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodLibrary(MethodLibrary newMethodLibrary, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_LIBRARY, newMethodLibrary, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodLibrary(MethodLibrary newMethodLibrary) {
		((FeatureMap.Internal)getMixed()).set(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_LIBRARY, newMethodLibrary);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodPlugin getMethodPlugin() {
		return (MethodPlugin)getMixed().get(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_PLUGIN, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodPlugin(MethodPlugin newMethodPlugin, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_PLUGIN, newMethodPlugin, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodPlugin(MethodPlugin newMethodPlugin) {
		((FeatureMap.Internal)getMixed()).set(UmaPackage.Literals.DOCUMENT_ROOT__METHOD_PLUGIN, newMethodPlugin);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UmaPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case UmaPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case UmaPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case UmaPackage.DOCUMENT_ROOT__METHOD_CONFIGURATION:
				return basicSetMethodConfiguration(null, msgs);
			case UmaPackage.DOCUMENT_ROOT__METHOD_LIBRARY:
				return basicSetMethodLibrary(null, msgs);
			case UmaPackage.DOCUMENT_ROOT__METHOD_PLUGIN:
				return basicSetMethodPlugin(null, msgs);
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
			case UmaPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case UmaPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case UmaPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case UmaPackage.DOCUMENT_ROOT__METHOD_CONFIGURATION:
				return getMethodConfiguration();
			case UmaPackage.DOCUMENT_ROOT__METHOD_LIBRARY:
				return getMethodLibrary();
			case UmaPackage.DOCUMENT_ROOT__METHOD_PLUGIN:
				return getMethodPlugin();
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
			case UmaPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case UmaPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case UmaPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case UmaPackage.DOCUMENT_ROOT__METHOD_CONFIGURATION:
				setMethodConfiguration((MethodConfiguration)newValue);
				return;
			case UmaPackage.DOCUMENT_ROOT__METHOD_LIBRARY:
				setMethodLibrary((MethodLibrary)newValue);
				return;
			case UmaPackage.DOCUMENT_ROOT__METHOD_PLUGIN:
				setMethodPlugin((MethodPlugin)newValue);
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
			case UmaPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case UmaPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case UmaPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case UmaPackage.DOCUMENT_ROOT__METHOD_CONFIGURATION:
				setMethodConfiguration((MethodConfiguration)null);
				return;
			case UmaPackage.DOCUMENT_ROOT__METHOD_LIBRARY:
				setMethodLibrary((MethodLibrary)null);
				return;
			case UmaPackage.DOCUMENT_ROOT__METHOD_PLUGIN:
				setMethodPlugin((MethodPlugin)null);
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
			case UmaPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case UmaPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case UmaPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case UmaPackage.DOCUMENT_ROOT__METHOD_CONFIGURATION:
				return getMethodConfiguration() != null;
			case UmaPackage.DOCUMENT_ROOT__METHOD_LIBRARY:
				return getMethodLibrary() != null;
			case UmaPackage.DOCUMENT_ROOT__METHOD_PLUGIN:
				return getMethodPlugin() != null;
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
