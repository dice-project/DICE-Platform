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
package org.eclipse.epf.xml.uma;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.epf.uma.ecore.IModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodConfiguration <em>Method Configuration</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodLibrary <em>Method Library</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodPlugin <em>Method Plugin</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @extends IModelObject
 * @generated
 */
public interface DocumentRoot extends IModelObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Method Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of selected Method Models and MethodPackages. A configuration can be exported into its own standalone library when it includes the full transitive closure of all elements all other elements depend on.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Configuration</em>' containment reference.
	 * @see #setMethodConfiguration(MethodConfiguration)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot_MethodConfiguration()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MethodConfiguration' namespace='##targetNamespace'"
	 * @generated
	 */
	MethodConfiguration getMethodConfiguration();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodConfiguration <em>Method Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Configuration</em>' containment reference.
	 * @see #getMethodConfiguration()
	 * @generated
	 */
	void setMethodConfiguration(MethodConfiguration value);

	/**
	 * Returns the value of the '<em><b>Method Library</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Method Library is a physical container for Method Plugins and Method Configuration definitions.  All Method Elements are stored in a Method Library.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Library</em>' containment reference.
	 * @see #setMethodLibrary(MethodLibrary)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot_MethodLibrary()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MethodLibrary' namespace='##targetNamespace'"
	 * @generated
	 */
	MethodLibrary getMethodLibrary();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodLibrary <em>Method Library</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Library</em>' containment reference.
	 * @see #getMethodLibrary()
	 * @generated
	 */
	void setMethodLibrary(MethodLibrary value);

	/**
	 * Returns the value of the '<em><b>Method Plugin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A special Method Unit that represents a physical container for Method Packages.  It defines a granularity level for the modularization and organization of method content and processes.  A Method Plugin can extend many other Method Plugins and it can be extended by many Method Plugins.  It can also be used stand-alone, i.e. with no Extension relationship to other plug-ins.
	 * Method Plugin conceptually represents a unit for configuration, modularization, extension, packaging, and deployment of method content and processes.  A Process Engineer shall design his Plugins and allocate his content to these Plugins with requirements for extensibility, modularity, reuse, and maintainability in mind.
	 * Special extensibility mechanisms defined for the meta-classes Variability Element and Process Contribution allow Plugin content to directly contribute new content, replace existing content, or to cross-reference to any Content Element or Process within another Plugin that it extends.  Similar to UML 2.0's 'package merge' mechanism transformation interpretations, interpreting these Method Plugin mechanisms results into new extended Method Content and Processes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Plugin</em>' containment reference.
	 * @see #setMethodPlugin(MethodPlugin)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getDocumentRoot_MethodPlugin()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MethodPlugin' namespace='##targetNamespace'"
	 * @generated
	 */
	MethodPlugin getMethodPlugin();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.DocumentRoot#getMethodPlugin <em>Method Plugin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Plugin</em>' containment reference.
	 * @see #getMethodPlugin()
	 * @generated
	 */
	void setMethodPlugin(MethodPlugin value);

} // DocumentRoot
