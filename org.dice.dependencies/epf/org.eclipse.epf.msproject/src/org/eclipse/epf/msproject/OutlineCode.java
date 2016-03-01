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
package org.eclipse.epf.msproject;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outline Code</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getPhoneticAlias <em>Phonetic Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getValues <em>Values</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#isEnterprise <em>Enterprise</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getEnterpriseOutlineCodeAlias <em>Enterprise Outline Code Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#isResourceSubstitutionEnabled <em>Resource Substitution Enabled</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#isLeafOnly <em>Leaf Only</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#isAllLevelsRequired <em>All Levels Required</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#isOnlyTableValuesAllowed <em>Only Table Values Allowed</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.OutlineCode#getMasks <em>Masks</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode()
 * @model extendedMetaData="name='OutlineCode_._type' kind='elementOnly'"
 * @generated
 */
public interface OutlineCode extends EObject {
	/**
	 * Returns the value of the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Coresponds to the field number of outline code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field ID</em>' attribute.
	 * @see #setFieldID(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_FieldID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='FieldID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFieldID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getFieldID <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field ID</em>' attribute.
	 * @see #getFieldID()
	 * @generated
	 */
	void setFieldID(String value);

	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the custom outline code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_FieldName()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='FieldName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The alias of the custom outline code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_Alias()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Alias' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * Returns the value of the '<em><b>Phonetic Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The phonetic pronunciation of the alias of the custom outline code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phonetic Alias</em>' attribute.
	 * @see #setPhoneticAlias(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_PhoneticAlias()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PhoneticAlias' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPhoneticAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getPhoneticAlias <em>Phonetic Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phonetic Alias</em>' attribute.
	 * @see #getPhoneticAlias()
	 * @generated
	 */
	void setPhoneticAlias(String value);

	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The values of the table associated with this outline code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Values</em>' containment reference.
	 * @see #setValues(Values)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_Values()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Values' namespace='##targetNamespace'"
	 * @generated
	 */
	Values getValues();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getValues <em>Values</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Values</em>' containment reference.
	 * @see #getValues()
	 * @generated
	 */
	void setValues(Values value);

	/**
	 * Returns the value of the '<em><b>Enterprise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether the custom outline code is an enterprise custom outline code.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enterprise</em>' attribute.
	 * @see #isSetEnterprise()
	 * @see #unsetEnterprise()
	 * @see #setEnterprise(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_Enterprise()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Enterprise' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isEnterprise();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isEnterprise <em>Enterprise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enterprise</em>' attribute.
	 * @see #isSetEnterprise()
	 * @see #unsetEnterprise()
	 * @see #isEnterprise()
	 * @generated
	 */
	void setEnterprise(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isEnterprise <em>Enterprise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnterprise()
	 * @see #isEnterprise()
	 * @see #setEnterprise(boolean)
	 * @generated
	 */
	void unsetEnterprise();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isEnterprise <em>Enterprise</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enterprise</em>' attribute is set.
	 * @see #unsetEnterprise()
	 * @see #isEnterprise()
	 * @see #setEnterprise(boolean)
	 * @generated
	 */
	boolean isSetEnterprise();

	/**
	 * Returns the value of the '<em><b>Enterprise Outline Code Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A reference to another custom field for which this is an alias.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enterprise Outline Code Alias</em>' attribute.
	 * @see #setEnterpriseOutlineCodeAlias(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_EnterpriseOutlineCodeAlias()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='EnterpriseOutlineCodeAlias' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getEnterpriseOutlineCodeAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getEnterpriseOutlineCodeAlias <em>Enterprise Outline Code Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enterprise Outline Code Alias</em>' attribute.
	 * @see #getEnterpriseOutlineCodeAlias()
	 * @generated
	 */
	void setEnterpriseOutlineCodeAlias(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Resource Substitution Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the custom outline code can be used by the Resource Substitution Wizard in Microsoft Project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Substitution Enabled</em>' attribute.
	 * @see #isSetResourceSubstitutionEnabled()
	 * @see #unsetResourceSubstitutionEnabled()
	 * @see #setResourceSubstitutionEnabled(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_ResourceSubstitutionEnabled()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ResourceSubstitutionEnabled' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isResourceSubstitutionEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isResourceSubstitutionEnabled <em>Resource Substitution Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Substitution Enabled</em>' attribute.
	 * @see #isSetResourceSubstitutionEnabled()
	 * @see #unsetResourceSubstitutionEnabled()
	 * @see #isResourceSubstitutionEnabled()
	 * @generated
	 */
	void setResourceSubstitutionEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isResourceSubstitutionEnabled <em>Resource Substitution Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResourceSubstitutionEnabled()
	 * @see #isResourceSubstitutionEnabled()
	 * @see #setResourceSubstitutionEnabled(boolean)
	 * @generated
	 */
	void unsetResourceSubstitutionEnabled();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isResourceSubstitutionEnabled <em>Resource Substitution Enabled</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Resource Substitution Enabled</em>' attribute is set.
	 * @see #unsetResourceSubstitutionEnabled()
	 * @see #isResourceSubstitutionEnabled()
	 * @see #setResourceSubstitutionEnabled(boolean)
	 * @generated
	 */
	boolean isSetResourceSubstitutionEnabled();

	/**
	 * Returns the value of the '<em><b>Leaf Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether or not values specified in this outline code field must be leaf values.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leaf Only</em>' attribute.
	 * @see #isSetLeafOnly()
	 * @see #unsetLeafOnly()
	 * @see #setLeafOnly(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_LeafOnly()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='LeafOnly' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isLeafOnly();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isLeafOnly <em>Leaf Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leaf Only</em>' attribute.
	 * @see #isSetLeafOnly()
	 * @see #unsetLeafOnly()
	 * @see #isLeafOnly()
	 * @generated
	 */
	void setLeafOnly(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isLeafOnly <em>Leaf Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLeafOnly()
	 * @see #isLeafOnly()
	 * @see #setLeafOnly(boolean)
	 * @generated
	 */
	void unsetLeafOnly();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isLeafOnly <em>Leaf Only</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Leaf Only</em>' attribute is set.
	 * @see #unsetLeafOnly()
	 * @see #isLeafOnly()
	 * @see #setLeafOnly(boolean)
	 * @generated
	 */
	boolean isSetLeafOnly();

	/**
	 * Returns the value of the '<em><b>All Levels Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * New codes must have all levels present.  Not available for Enterprise Codes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>All Levels Required</em>' attribute.
	 * @see #isSetAllLevelsRequired()
	 * @see #unsetAllLevelsRequired()
	 * @see #setAllLevelsRequired(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_AllLevelsRequired()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='AllLevelsRequired' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isAllLevelsRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isAllLevelsRequired <em>All Levels Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Levels Required</em>' attribute.
	 * @see #isSetAllLevelsRequired()
	 * @see #unsetAllLevelsRequired()
	 * @see #isAllLevelsRequired()
	 * @generated
	 */
	void setAllLevelsRequired(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isAllLevelsRequired <em>All Levels Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAllLevelsRequired()
	 * @see #isAllLevelsRequired()
	 * @see #setAllLevelsRequired(boolean)
	 * @generated
	 */
	void unsetAllLevelsRequired();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isAllLevelsRequired <em>All Levels Required</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>All Levels Required</em>' attribute is set.
	 * @see #unsetAllLevelsRequired()
	 * @see #isAllLevelsRequired()
	 * @see #setAllLevelsRequired(boolean)
	 * @generated
	 */
	boolean isSetAllLevelsRequired();

	/**
	 * Returns the value of the '<em><b>Only Table Values Allowed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether values specified must come from values table.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Only Table Values Allowed</em>' attribute.
	 * @see #isSetOnlyTableValuesAllowed()
	 * @see #unsetOnlyTableValuesAllowed()
	 * @see #setOnlyTableValuesAllowed(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_OnlyTableValuesAllowed()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='OnlyTableValuesAllowed' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isOnlyTableValuesAllowed();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isOnlyTableValuesAllowed <em>Only Table Values Allowed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Only Table Values Allowed</em>' attribute.
	 * @see #isSetOnlyTableValuesAllowed()
	 * @see #unsetOnlyTableValuesAllowed()
	 * @see #isOnlyTableValuesAllowed()
	 * @generated
	 */
	void setOnlyTableValuesAllowed(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isOnlyTableValuesAllowed <em>Only Table Values Allowed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOnlyTableValuesAllowed()
	 * @see #isOnlyTableValuesAllowed()
	 * @see #setOnlyTableValuesAllowed(boolean)
	 * @generated
	 */
	void unsetOnlyTableValuesAllowed();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.OutlineCode#isOnlyTableValuesAllowed <em>Only Table Values Allowed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Only Table Values Allowed</em>' attribute is set.
	 * @see #unsetOnlyTableValuesAllowed()
	 * @see #isOnlyTableValuesAllowed()
	 * @see #setOnlyTableValuesAllowed(boolean)
	 * @generated
	 */
	boolean isSetOnlyTableValuesAllowed();

	/**
	 * Returns the value of the '<em><b>Masks</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The table of entries that define the outline code mask.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Masks</em>' containment reference.
	 * @see #setMasks(Masks)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getOutlineCode_Masks()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Masks' namespace='##targetNamespace'"
	 * @generated
	 */
	Masks getMasks();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.OutlineCode#getMasks <em>Masks</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Masks</em>' containment reference.
	 * @see #getMasks()
	 * @generated
	 */
	void setMasks(Masks value);

} // OutlineCode
