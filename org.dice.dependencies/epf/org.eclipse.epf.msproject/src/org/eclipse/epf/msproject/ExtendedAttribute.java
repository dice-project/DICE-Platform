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
 * A representation of the model object '<em><b>Extended Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getPhoneticAlias <em>Phonetic Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getRollupType <em>Rollup Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getCalculationType <em>Calculation Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getFormula <em>Formula</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#isRestrictValues <em>Restrict Values</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getValuelistSortOrder <em>Valuelist Sort Order</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#isAppendNewValues <em>Append New Values</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.ExtendedAttribute#getValueList <em>Value List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute()
 * @model extendedMetaData="name='ExtendedAttribute_._type' kind='elementOnly'"
 * @generated
 */
public interface ExtendedAttribute extends EObject {
	/**
	 * Returns the value of the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This corresponds to the PID of the custom field.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field ID</em>' attribute.
	 * @see #setFieldID(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_FieldID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='FieldID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFieldID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getFieldID <em>Field ID</em>}' attribute.
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
	 * The name of the custom field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_FieldName()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='FieldName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getFieldName <em>Field Name</em>}' attribute.
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
	 * The alias of the custom field
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_Alias()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.AliasType"
	 *        extendedMetaData="kind='element' name='Alias' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getAlias <em>Alias</em>}' attribute.
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
	 * The phonetic pronunciation of the alias of the 
	 *                                 custom field
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phonetic Alias</em>' attribute.
	 * @see #setPhoneticAlias(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_PhoneticAlias()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.PhoneticAliasType"
	 *        extendedMetaData="kind='element' name='PhoneticAlias' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPhoneticAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getPhoneticAlias <em>Phonetic Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phonetic Alias</em>' attribute.
	 * @see #getPhoneticAlias()
	 * @generated
	 */
	void setPhoneticAlias(String value);

	/**
	 * Returns the value of the '<em><b>Rollup Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * How rollups are calculated. Values are: 0=Maximum 
	 *                                 (OR for flag fields), 1=Minimum (AND for flag fields), 2=Count all, 3=Sum, 
	 *                                 4=Average, 5=Average First Sublevel, 6=Count First Sublevel, 7=Count 
	 *                                 Nonsummaries
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rollup Type</em>' attribute.
	 * @see #setRollupType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_RollupType()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.RollupTypeType"
	 *        extendedMetaData="kind='element' name='RollupType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getRollupType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getRollupType <em>Rollup Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rollup Type</em>' attribute.
	 * @see #getRollupType()
	 * @generated
	 */
	void setRollupType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Calculation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether rollups are calculated for task and group summary rows. 
	 *                                 Values are: 0=None, 1=Rollup, 2=Calculation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calculation Type</em>' attribute.
	 * @see #setCalculationType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_CalculationType()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CalculationTypeType"
	 *        extendedMetaData="kind='element' name='CalculationType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCalculationType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getCalculationType <em>Calculation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calculation Type</em>' attribute.
	 * @see #getCalculationType()
	 * @generated
	 */
	void setCalculationType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Formula</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The formula that Microsoft Project uses to populate the 
	 *                                 custom task field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Formula</em>' attribute.
	 * @see #setFormula(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_Formula()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Formula' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFormula();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getFormula <em>Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formula</em>' attribute.
	 * @see #getFormula()
	 * @generated
	 */
	void setFormula(String value);

	/**
	 * Returns the value of the '<em><b>Restrict Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If RestrictValues=True then only values in the list are 
	 *                                 allowed in the file
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Restrict Values</em>' attribute.
	 * @see #isSetRestrictValues()
	 * @see #unsetRestrictValues()
	 * @see #setRestrictValues(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_RestrictValues()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='RestrictValues' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isRestrictValues();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#isRestrictValues <em>Restrict Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Restrict Values</em>' attribute.
	 * @see #isSetRestrictValues()
	 * @see #unsetRestrictValues()
	 * @see #isRestrictValues()
	 * @generated
	 */
	void setRestrictValues(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#isRestrictValues <em>Restrict Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRestrictValues()
	 * @see #isRestrictValues()
	 * @see #setRestrictValues(boolean)
	 * @generated
	 */
	void unsetRestrictValues();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#isRestrictValues <em>Restrict Values</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Restrict Values</em>' attribute is set.
	 * @see #unsetRestrictValues()
	 * @see #isRestrictValues()
	 * @see #setRestrictValues(boolean)
	 * @generated
	 */
	boolean isSetRestrictValues();

	/**
	 * Returns the value of the '<em><b>Valuelist Sort Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * How value lists are sorted. Values are: 
	 *                                 0=Descending, 1=Ascending
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Valuelist Sort Order</em>' attribute.
	 * @see #setValuelistSortOrder(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_ValuelistSortOrder()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.ValuelistSortOrderType"
	 *        extendedMetaData="kind='element' name='ValuelistSortOrder' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getValuelistSortOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getValuelistSortOrder <em>Valuelist Sort Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valuelist Sort Order</em>' attribute.
	 * @see #getValuelistSortOrder()
	 * @generated
	 */
	void setValuelistSortOrder(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Append New Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If AppendNewValues=True then any new values added in a project 
	 *                                 are automatically appended to the list.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Append New Values</em>' attribute.
	 * @see #isSetAppendNewValues()
	 * @see #unsetAppendNewValues()
	 * @see #setAppendNewValues(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_AppendNewValues()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='AppendNewValues' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isAppendNewValues();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#isAppendNewValues <em>Append New Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Append New Values</em>' attribute.
	 * @see #isSetAppendNewValues()
	 * @see #unsetAppendNewValues()
	 * @see #isAppendNewValues()
	 * @generated
	 */
	void setAppendNewValues(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#isAppendNewValues <em>Append New Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAppendNewValues()
	 * @see #isAppendNewValues()
	 * @see #setAppendNewValues(boolean)
	 * @generated
	 */
	void unsetAppendNewValues();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#isAppendNewValues <em>Append New Values</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Append New Values</em>' attribute is set.
	 * @see #unsetAppendNewValues()
	 * @see #isAppendNewValues()
	 * @see #setAppendNewValues(boolean)
	 * @generated
	 */
	boolean isSetAppendNewValues();

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This points to the default value in the list. 
	 *                                 Not present if no default is set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_Default()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Default' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

	/**
	 * Returns the value of the '<em><b>Value List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When values of extended attributes are specified as 
	 *                                 properties of elements in the schema, they may either be specified by values 
	 *                                 or by references to the values contained in this list.  Applications may 
	 *                                 assume ordering of the list by ordering specified here.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value List</em>' containment reference.
	 * @see #setValueList(ValueList)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getExtendedAttribute_ValueList()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='ValueList' namespace='##targetNamespace'"
	 * @generated
	 */
	ValueList getValueList();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.ExtendedAttribute#getValueList <em>Value List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value List</em>' containment reference.
	 * @see #getValueList()
	 * @generated
	 */
	void setValueList(ValueList value);

} // ExtendedAttribute
