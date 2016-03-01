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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#isIsNull <em>Is Null</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getInitials <em>Initials</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getPhonetics <em>Phonetics</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getNTAccount <em>NT Account</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getMaterialLabel <em>Material Label</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCode <em>Code</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getWorkGroup <em>Work Group</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getEmailAddress <em>Email Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getHyperlink <em>Hyperlink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getHyperlinkAddress <em>Hyperlink Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getMaxUnits <em>Max Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getPeakUnits <em>Peak Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#isOverAllocated <em>Over Allocated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getAvailableFrom <em>Available From</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getAvailableTo <em>Available To</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#isCanLevel <em>Can Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getAccrueAt <em>Accrue At</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getRegularWork <em>Regular Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getOvertimeWork <em>Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActualWork <em>Actual Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getRemainingWork <em>Remaining Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActualOvertimeWork <em>Actual Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getPercentWorkComplete <em>Percent Work Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getStandardRate <em>Standard Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getStandardRateFormat <em>Standard Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getOvertimeRate <em>Overtime Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getOvertimeRateFormat <em>Overtime Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getOvertimeCost <em>Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCostPerUse <em>Cost Per Use</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActualCost <em>Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActualOvertimeCost <em>Actual Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getRemainingCost <em>Remaining Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getWorkVariance <em>Work Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCostVariance <em>Cost Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getSV <em>SV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCV <em>CV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getACWP <em>ACWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCalendarUID <em>Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getBCWP <em>BCWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#isIsGeneric <em>Is Generic</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#isIsInactive <em>Is Inactive</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#isIsEnterprise <em>Is Enterprise</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getBookingType <em>Booking Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActualWorkProtected <em>Actual Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getActiveDirectoryGUID <em>Active Directory GUID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getBaseline <em>Baseline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getOutlineCode <em>Outline Code</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getAvailabilityPeriods <em>Availability Periods</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getRates <em>Rates</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Resource#getTimephasedData <em>Timephased Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource()
 * @model extendedMetaData="name='Resource_._type' kind='elementOnly'"
 * @generated
 */
public interface Resource extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getUID <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UID</em>' attribute.
	 * @see #getUID()
	 * @generated
	 */
	void setUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The position identifier of the resource within the list of resources.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ID</em>' attribute.
	 * @see #setID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='ID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getID <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ID</em>' attribute.
	 * @see #getID()
	 * @generated
	 */
	void setID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Name()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.NameType1"
	 *        extendedMetaData="kind='element' name='Name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of resource. Values are: 0=Material, 1=Work
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Type()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.TypeType4"
	 *        extendedMetaData="kind='element' name='Type' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Is Null</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the resource is null.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Null</em>' attribute.
	 * @see #isSetIsNull()
	 * @see #unsetIsNull()
	 * @see #setIsNull(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_IsNull()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsNull' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsNull();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsNull <em>Is Null</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Null</em>' attribute.
	 * @see #isSetIsNull()
	 * @see #unsetIsNull()
	 * @see #isIsNull()
	 * @generated
	 */
	void setIsNull(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsNull <em>Is Null</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsNull()
	 * @see #isIsNull()
	 * @see #setIsNull(boolean)
	 * @generated
	 */
	void unsetIsNull();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#isIsNull <em>Is Null</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Null</em>' attribute is set.
	 * @see #unsetIsNull()
	 * @see #isIsNull()
	 * @see #setIsNull(boolean)
	 * @generated
	 */
	boolean isSetIsNull();

	/**
	 * Returns the value of the '<em><b>Initials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The initials of the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initials</em>' attribute.
	 * @see #setInitials(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Initials()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.InitialsType"
	 *        extendedMetaData="kind='element' name='Initials' namespace='##targetNamespace'"
	 * @generated
	 */
	String getInitials();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getInitials <em>Initials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initials</em>' attribute.
	 * @see #getInitials()
	 * @generated
	 */
	void setInitials(String value);

	/**
	 * Returns the value of the '<em><b>Phonetics</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The phonetic spelling of the resource name.  
	 *                                 For use with Japanese only.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phonetics</em>' attribute.
	 * @see #setPhonetics(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Phonetics()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.PhoneticsType"
	 *        extendedMetaData="kind='element' name='Phonetics' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPhonetics();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getPhonetics <em>Phonetics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phonetics</em>' attribute.
	 * @see #getPhonetics()
	 * @generated
	 */
	void setPhonetics(String value);

	/**
	 * Returns the value of the '<em><b>NT Account</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The NT account associated with the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>NT Account</em>' attribute.
	 * @see #setNTAccount(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_NTAccount()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.NTAccountType"
	 *        extendedMetaData="kind='element' name='NTAccount' namespace='##targetNamespace'"
	 * @generated
	 */
	String getNTAccount();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getNTAccount <em>NT Account</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>NT Account</em>' attribute.
	 * @see #getNTAccount()
	 * @generated
	 */
	void setNTAccount(String value);

	/**
	 * Returns the value of the '<em><b>Material Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unit of measure for the material resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Material Label</em>' attribute.
	 * @see #setMaterialLabel(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_MaterialLabel()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.MaterialLabelType"
	 *        extendedMetaData="kind='element' name='MaterialLabel' namespace='##targetNamespace'"
	 * @generated
	 */
	String getMaterialLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getMaterialLabel <em>Material Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Material Label</em>' attribute.
	 * @see #getMaterialLabel()
	 * @generated
	 */
	void setMaterialLabel(String value);

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The code or other information about the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Code()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CodeType"
	 *        extendedMetaData="kind='element' name='Code' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The group to which the resource belongs.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Group</em>' attribute.
	 * @see #setGroup(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Group()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.GroupType"
	 *        extendedMetaData="kind='element' name='Group' namespace='##targetNamespace'"
	 * @generated
	 */
	String getGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getGroup <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' attribute.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(String value);

	/**
	 * Returns the value of the '<em><b>Work Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of workgroup to which the resource belongs. 
	 *                                 Values are: 0=Default, 1=None, 2=Email, 3=Web
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work Group</em>' attribute.
	 * @see #setWorkGroup(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_WorkGroup()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.WorkGroupType"
	 *        extendedMetaData="kind='element' name='WorkGroup' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getWorkGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getWorkGroup <em>Work Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Group</em>' attribute.
	 * @see #getWorkGroup()
	 * @generated
	 */
	void setWorkGroup(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Email Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The email address of the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Email Address</em>' attribute.
	 * @see #setEmailAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_EmailAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.EmailAddressType"
	 *        extendedMetaData="kind='element' name='EmailAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getEmailAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getEmailAddress <em>Email Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email Address</em>' attribute.
	 * @see #getEmailAddress()
	 * @generated
	 */
	void setEmailAddress(String value);

	/**
	 * Returns the value of the '<em><b>Hyperlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The title of the hyperlink associated with the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink</em>' attribute.
	 * @see #setHyperlink(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Hyperlink()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkType1"
	 *        extendedMetaData="kind='element' name='Hyperlink' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlink();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getHyperlink <em>Hyperlink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hyperlink</em>' attribute.
	 * @see #getHyperlink()
	 * @generated
	 */
	void setHyperlink(String value);

	/**
	 * Returns the value of the '<em><b>Hyperlink Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The hyperlink associated with the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink Address</em>' attribute.
	 * @see #setHyperlinkAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_HyperlinkAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkAddressType1"
	 *        extendedMetaData="kind='element' name='HyperlinkAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlinkAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getHyperlinkAddress <em>Hyperlink Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hyperlink Address</em>' attribute.
	 * @see #getHyperlinkAddress()
	 * @generated
	 */
	void setHyperlinkAddress(String value);

	/**
	 * Returns the value of the '<em><b>Hyperlink Sub Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The document bookmark of the hyperlink associated with the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink Sub Address</em>' attribute.
	 * @see #setHyperlinkSubAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_HyperlinkSubAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkSubAddressType"
	 *        extendedMetaData="kind='element' name='HyperlinkSubAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlinkSubAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hyperlink Sub Address</em>' attribute.
	 * @see #getHyperlinkSubAddress()
	 * @generated
	 */
	void setHyperlinkSubAddress(String value);

	/**
	 * Returns the value of the '<em><b>Max Units</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The maximum number of units that the resource is available.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Units</em>' attribute.
	 * @see #isSetMaxUnits()
	 * @see #unsetMaxUnits()
	 * @see #setMaxUnits(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_MaxUnits()
	 * @model default="1.0" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='MaxUnits' namespace='##targetNamespace'"
	 * @generated
	 */
	float getMaxUnits();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getMaxUnits <em>Max Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Units</em>' attribute.
	 * @see #isSetMaxUnits()
	 * @see #unsetMaxUnits()
	 * @see #getMaxUnits()
	 * @generated
	 */
	void setMaxUnits(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getMaxUnits <em>Max Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMaxUnits()
	 * @see #getMaxUnits()
	 * @see #setMaxUnits(float)
	 * @generated
	 */
	void unsetMaxUnits();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getMaxUnits <em>Max Units</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Max Units</em>' attribute is set.
	 * @see #unsetMaxUnits()
	 * @see #getMaxUnits()
	 * @see #setMaxUnits(float)
	 * @generated
	 */
	boolean isSetMaxUnits();

	/**
	 * Returns the value of the '<em><b>Peak Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The largest number of units assigned to the resource at any time.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Peak Units</em>' attribute.
	 * @see #isSetPeakUnits()
	 * @see #unsetPeakUnits()
	 * @see #setPeakUnits(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_PeakUnits()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='PeakUnits' namespace='##targetNamespace'"
	 * @generated
	 */
	float getPeakUnits();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getPeakUnits <em>Peak Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Units</em>' attribute.
	 * @see #isSetPeakUnits()
	 * @see #unsetPeakUnits()
	 * @see #getPeakUnits()
	 * @generated
	 */
	void setPeakUnits(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getPeakUnits <em>Peak Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPeakUnits()
	 * @see #getPeakUnits()
	 * @see #setPeakUnits(float)
	 * @generated
	 */
	void unsetPeakUnits();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getPeakUnits <em>Peak Units</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Peak Units</em>' attribute is set.
	 * @see #unsetPeakUnits()
	 * @see #getPeakUnits()
	 * @see #setPeakUnits(float)
	 * @generated
	 */
	boolean isSetPeakUnits();

	/**
	 * Returns the value of the '<em><b>Over Allocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the resource is overallocated.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Over Allocated</em>' attribute.
	 * @see #isSetOverAllocated()
	 * @see #unsetOverAllocated()
	 * @see #setOverAllocated(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_OverAllocated()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='OverAllocated' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isOverAllocated();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#isOverAllocated <em>Over Allocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Over Allocated</em>' attribute.
	 * @see #isSetOverAllocated()
	 * @see #unsetOverAllocated()
	 * @see #isOverAllocated()
	 * @generated
	 */
	void setOverAllocated(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#isOverAllocated <em>Over Allocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOverAllocated()
	 * @see #isOverAllocated()
	 * @see #setOverAllocated(boolean)
	 * @generated
	 */
	void unsetOverAllocated();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#isOverAllocated <em>Over Allocated</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Over Allocated</em>' attribute is set.
	 * @see #unsetOverAllocated()
	 * @see #isOverAllocated()
	 * @see #setOverAllocated(boolean)
	 * @generated
	 */
	boolean isSetOverAllocated();

	/**
	 * Returns the value of the '<em><b>Available From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The first date that the resource is available.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Available From</em>' attribute.
	 * @see #setAvailableFrom(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_AvailableFrom()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='AvailableFrom' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getAvailableFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getAvailableFrom <em>Available From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Available From</em>' attribute.
	 * @see #getAvailableFrom()
	 * @generated
	 */
	void setAvailableFrom(Object value);

	/**
	 * Returns the value of the '<em><b>Available To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The last date the resource is available.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Available To</em>' attribute.
	 * @see #setAvailableTo(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_AvailableTo()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='AvailableTo' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getAvailableTo();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getAvailableTo <em>Available To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Available To</em>' attribute.
	 * @see #getAvailableTo()
	 * @generated
	 */
	void setAvailableTo(Object value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled start date of the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Start()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Start' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Object value);

	/**
	 * Returns the value of the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled finish date of the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish</em>' attribute.
	 * @see #setFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Finish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Finish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getFinish <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish</em>' attribute.
	 * @see #getFinish()
	 * @generated
	 */
	void setFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Can Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the resource can be leveled.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Can Level</em>' attribute.
	 * @see #isSetCanLevel()
	 * @see #unsetCanLevel()
	 * @see #setCanLevel(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_CanLevel()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='CanLevel' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isCanLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#isCanLevel <em>Can Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Level</em>' attribute.
	 * @see #isSetCanLevel()
	 * @see #unsetCanLevel()
	 * @see #isCanLevel()
	 * @generated
	 */
	void setCanLevel(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#isCanLevel <em>Can Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCanLevel()
	 * @see #isCanLevel()
	 * @see #setCanLevel(boolean)
	 * @generated
	 */
	void unsetCanLevel();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#isCanLevel <em>Can Level</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Can Level</em>' attribute is set.
	 * @see #unsetCanLevel()
	 * @see #isCanLevel()
	 * @see #setCanLevel(boolean)
	 * @generated
	 */
	boolean isSetCanLevel();

	/**
	 * Returns the value of the '<em><b>Accrue At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * How cost is accrued against the resource. 
	 *                                 Values are: 1=Start, 2=End, 3=Prorated
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Accrue At</em>' attribute.
	 * @see #setAccrueAt(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_AccrueAt()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.AccrueAt"
	 *        extendedMetaData="kind='element' name='AccrueAt' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getAccrueAt();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getAccrueAt <em>Accrue At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accrue At</em>' attribute.
	 * @see #getAccrueAt()
	 * @generated
	 */
	void setAccrueAt(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total work assigned to the resource across all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work</em>' attribute.
	 * @see #setWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Work()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Work' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getWork <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work</em>' attribute.
	 * @see #getWork()
	 * @generated
	 */
	void setWork(Object value);

	/**
	 * Returns the value of the '<em><b>Regular Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of non-overtime work assigned to the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Regular Work</em>' attribute.
	 * @see #setRegularWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_RegularWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RegularWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRegularWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getRegularWork <em>Regular Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regular Work</em>' attribute.
	 * @see #getRegularWork()
	 * @generated
	 */
	void setRegularWork(Object value);

	/**
	 * Returns the value of the '<em><b>Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of overtime work assigned to the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Work</em>' attribute.
	 * @see #setOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_OvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='OvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getOvertimeWork <em>Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Work</em>' attribute.
	 * @see #getOvertimeWork()
	 * @generated
	 */
	void setOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of actual work performed by the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Work</em>' attribute.
	 * @see #setActualWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActualWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActualWork <em>Actual Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Work</em>' attribute.
	 * @see #getActualWork()
	 * @generated
	 */
	void setActualWork(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of remaining work required to complete all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Work</em>' attribute.
	 * @see #setRemainingWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_RemainingWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getRemainingWork <em>Remaining Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Work</em>' attribute.
	 * @see #getRemainingWork()
	 * @generated
	 */
	void setRemainingWork(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of actual overtime work performed by the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Work</em>' attribute.
	 * @see #setActualOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActualOvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualOvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActualOvertimeWork <em>Actual Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Work</em>' attribute.
	 * @see #getActualOvertimeWork()
	 * @generated
	 */
	void setActualOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of remaining overtime work required to complete all tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Overtime Work</em>' attribute.
	 * @see #setRemainingOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_RemainingOvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingOvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Overtime Work</em>' attribute.
	 * @see #getRemainingOvertimeWork()
	 * @generated
	 */
	void setRemainingOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Percent Work Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The percentage of work completed across all tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Percent Work Complete</em>' attribute.
	 * @see #setPercentWorkComplete(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_PercentWorkComplete()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='PercentWorkComplete' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPercentWorkComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getPercentWorkComplete <em>Percent Work Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percent Work Complete</em>' attribute.
	 * @see #getPercentWorkComplete()
	 * @generated
	 */
	void setPercentWorkComplete(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Standard Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The standard rate of the resource. 
	 *                                 This value is as of the current date if a rate table exists for the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Standard Rate</em>' attribute.
	 * @see #setStandardRate(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_StandardRate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='StandardRate' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getStandardRate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getStandardRate <em>Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Rate</em>' attribute.
	 * @see #getStandardRate()
	 * @generated
	 */
	void setStandardRate(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Standard Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The units used by Microsoft Project to display the standard rate.  
	 *                                 1=m, 2=h, 3=d, 4=w, 5=mo, 7=y, 8=material resource rate (or blank symbol specified)
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Standard Rate Format</em>' attribute.
	 * @see #setStandardRateFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_StandardRateFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.StandardRateFormatType1"
	 *        extendedMetaData="kind='element' name='StandardRateFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getStandardRateFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getStandardRateFormat <em>Standard Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Rate Format</em>' attribute.
	 * @see #getStandardRateFormat()
	 * @generated
	 */
	void setStandardRateFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total project cost for the resource across all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Cost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Overtime Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The overtime rate of the resource. 
	 *                                 This value is as of the current date if a rate table exists for the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Rate</em>' attribute.
	 * @see #setOvertimeRate(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_OvertimeRate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='OvertimeRate' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getOvertimeRate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getOvertimeRate <em>Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Rate</em>' attribute.
	 * @see #getOvertimeRate()
	 * @generated
	 */
	void setOvertimeRate(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Overtime Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The units used by Microsoft Project to display the overtime rate.  
	 *                                 1=m, 2=h, 3=d, 4=w, 5=mo, 7=y
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Rate Format</em>' attribute.
	 * @see #setOvertimeRateFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_OvertimeRateFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.OvertimeRateFormatType1"
	 *        extendedMetaData="kind='element' name='OvertimeRateFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getOvertimeRateFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getOvertimeRateFormat <em>Overtime Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Rate Format</em>' attribute.
	 * @see #getOvertimeRateFormat()
	 * @generated
	 */
	void setOvertimeRateFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total overtime cost for the resource including actual and remaining overtime costs.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Cost</em>' attribute.
	 * @see #setOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_OvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='OvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getOvertimeCost <em>Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Cost</em>' attribute.
	 * @see #getOvertimeCost()
	 * @generated
	 */
	void setOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Cost Per Use</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The cost per use of the resource. This value is as of the current date if a rate table 
	 *                                 exists for the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost Per Use</em>' attribute.
	 * @see #setCostPerUse(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_CostPerUse()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='CostPerUse' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getCostPerUse();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCostPerUse <em>Cost Per Use</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Per Use</em>' attribute.
	 * @see #getCostPerUse()
	 * @generated
	 */
	void setCostPerUse(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual cost incurred by the resource across all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Cost</em>' attribute.
	 * @see #setActualCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActualCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='ActualCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getActualCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActualCost <em>Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Cost</em>' attribute.
	 * @see #getActualCost()
	 * @generated
	 */
	void setActualCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Actual Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual overtime cost incurred by the resource across all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Cost</em>' attribute.
	 * @see #setActualOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActualOvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='ActualOvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getActualOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActualOvertimeCost <em>Actual Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Cost</em>' attribute.
	 * @see #getActualOvertimeCost()
	 * @generated
	 */
	void setActualOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Remaining Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining projected cost of the resource to complete all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Cost</em>' attribute.
	 * @see #setRemainingCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_RemainingCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='RemainingCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getRemainingCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getRemainingCost <em>Remaining Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Cost</em>' attribute.
	 * @see #getRemainingCost()
	 * @generated
	 */
	void setRemainingCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Remaining Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining projected overtime cost of the resource to complete all assigned tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Overtime Cost</em>' attribute.
	 * @see #setRemainingOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_RemainingOvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='RemainingOvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getRemainingOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Overtime Cost</em>' attribute.
	 * @see #getRemainingOvertimeCost()
	 * @generated
	 */
	void setRemainingOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Work Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The difference between the baseline work and the work as minutes x 1000.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work Variance</em>' attribute.
	 * @see #isSetWorkVariance()
	 * @see #unsetWorkVariance()
	 * @see #setWorkVariance(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_WorkVariance()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='WorkVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	float getWorkVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getWorkVariance <em>Work Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Variance</em>' attribute.
	 * @see #isSetWorkVariance()
	 * @see #unsetWorkVariance()
	 * @see #getWorkVariance()
	 * @generated
	 */
	void setWorkVariance(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getWorkVariance <em>Work Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWorkVariance()
	 * @see #getWorkVariance()
	 * @see #setWorkVariance(float)
	 * @generated
	 */
	void unsetWorkVariance();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getWorkVariance <em>Work Variance</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Work Variance</em>' attribute is set.
	 * @see #unsetWorkVariance()
	 * @see #getWorkVariance()
	 * @see #setWorkVariance(float)
	 * @generated
	 */
	boolean isSetWorkVariance();

	/**
	 * Returns the value of the '<em><b>Cost Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The difference between the baseline cost and the cost.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost Variance</em>' attribute.
	 * @see #isSetCostVariance()
	 * @see #unsetCostVariance()
	 * @see #setCostVariance(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_CostVariance()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='CostVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	float getCostVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCostVariance <em>Cost Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Variance</em>' attribute.
	 * @see #isSetCostVariance()
	 * @see #unsetCostVariance()
	 * @see #getCostVariance()
	 * @generated
	 */
	void setCostVariance(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getCostVariance <em>Cost Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCostVariance()
	 * @see #getCostVariance()
	 * @see #setCostVariance(float)
	 * @generated
	 */
	void unsetCostVariance();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getCostVariance <em>Cost Variance</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cost Variance</em>' attribute is set.
	 * @see #unsetCostVariance()
	 * @see #getCostVariance()
	 * @see #setCostVariance(float)
	 * @generated
	 */
	boolean isSetCostVariance();

	/**
	 * Returns the value of the '<em><b>SV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Earned value schedule variance, through the project status date.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>SV</em>' attribute.
	 * @see #isSetSV()
	 * @see #unsetSV()
	 * @see #setSV(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_SV()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='SV' namespace='##targetNamespace'"
	 * @generated
	 */
	float getSV();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getSV <em>SV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SV</em>' attribute.
	 * @see #isSetSV()
	 * @see #unsetSV()
	 * @see #getSV()
	 * @generated
	 */
	void setSV(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getSV <em>SV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSV()
	 * @see #getSV()
	 * @see #setSV(float)
	 * @generated
	 */
	void unsetSV();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getSV <em>SV</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>SV</em>' attribute is set.
	 * @see #unsetSV()
	 * @see #getSV()
	 * @see #setSV(float)
	 * @generated
	 */
	boolean isSetSV();

	/**
	 * Returns the value of the '<em><b>CV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Earned value cost variance, through the project status date.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>CV</em>' attribute.
	 * @see #isSetCV()
	 * @see #unsetCV()
	 * @see #setCV(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_CV()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='CV' namespace='##targetNamespace'"
	 * @generated
	 */
	float getCV();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCV <em>CV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CV</em>' attribute.
	 * @see #isSetCV()
	 * @see #unsetCV()
	 * @see #getCV()
	 * @generated
	 */
	void setCV(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getCV <em>CV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCV()
	 * @see #getCV()
	 * @see #setCV(float)
	 * @generated
	 */
	void unsetCV();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getCV <em>CV</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>CV</em>' attribute is set.
	 * @see #unsetCV()
	 * @see #getCV()
	 * @see #setCV(float)
	 * @generated
	 */
	boolean isSetCV();

	/**
	 * Returns the value of the '<em><b>ACWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual cost of the work performed by the resource for the project to-date.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ACWP</em>' attribute.
	 * @see #isSetACWP()
	 * @see #unsetACWP()
	 * @see #setACWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ACWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='ACWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getACWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getACWP <em>ACWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ACWP</em>' attribute.
	 * @see #isSetACWP()
	 * @see #unsetACWP()
	 * @see #getACWP()
	 * @generated
	 */
	void setACWP(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getACWP <em>ACWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetACWP()
	 * @see #getACWP()
	 * @see #setACWP(float)
	 * @generated
	 */
	void unsetACWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getACWP <em>ACWP</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>ACWP</em>' attribute is set.
	 * @see #unsetACWP()
	 * @see #getACWP()
	 * @see #setACWP(float)
	 * @generated
	 */
	boolean isSetACWP();

	/**
	 * Returns the value of the '<em><b>Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The resource calendar.Refers to a valid UID in the Calendars element of 
	 *                                 the Microsoft Project XML Schema.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calendar UID</em>' attribute.
	 * @see #setCalendarUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_CalendarUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='CalendarUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCalendarUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCalendarUID <em>Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calendar UID</em>' attribute.
	 * @see #getCalendarUID()
	 * @generated
	 */
	void setCalendarUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text notes associated with the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notes</em>' attribute.
	 * @see #setNotes(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Notes()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Notes' namespace='##targetNamespace'"
	 * @generated
	 */
	String getNotes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getNotes <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notes</em>' attribute.
	 * @see #getNotes()
	 * @generated
	 */
	void setNotes(String value);

	/**
	 * Returns the value of the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budget cost of work scheduled for the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #setBCWS(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_BCWS()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWS' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWS();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #getBCWS()
	 * @generated
	 */
	void setBCWS(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	void unsetBCWS();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getBCWS <em>BCWS</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>BCWS</em>' attribute is set.
	 * @see #unsetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	boolean isSetBCWS();

	/**
	 * Returns the value of the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budgeted cost of of the work performed by the resource for the project to-date.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #setBCWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_BCWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #getBCWP()
	 * @generated
	 */
	void setBCWP(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	void unsetBCWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#getBCWP <em>BCWP</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>BCWP</em>' attribute is set.
	 * @see #unsetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	boolean isSetBCWP();

	/**
	 * Returns the value of the '<em><b>Is Generic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the resource is generic.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Generic</em>' attribute.
	 * @see #isSetIsGeneric()
	 * @see #unsetIsGeneric()
	 * @see #setIsGeneric(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_IsGeneric()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsGeneric' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsGeneric();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsGeneric <em>Is Generic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Generic</em>' attribute.
	 * @see #isSetIsGeneric()
	 * @see #unsetIsGeneric()
	 * @see #isIsGeneric()
	 * @generated
	 */
	void setIsGeneric(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsGeneric <em>Is Generic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsGeneric()
	 * @see #isIsGeneric()
	 * @see #setIsGeneric(boolean)
	 * @generated
	 */
	void unsetIsGeneric();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#isIsGeneric <em>Is Generic</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Generic</em>' attribute is set.
	 * @see #unsetIsGeneric()
	 * @see #isIsGeneric()
	 * @see #setIsGeneric(boolean)
	 * @generated
	 */
	boolean isSetIsGeneric();

	/**
	 * Returns the value of the '<em><b>Is Inactive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the resource is set to inactive.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Inactive</em>' attribute.
	 * @see #isSetIsInactive()
	 * @see #unsetIsInactive()
	 * @see #setIsInactive(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_IsInactive()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsInactive' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsInactive();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsInactive <em>Is Inactive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Inactive</em>' attribute.
	 * @see #isSetIsInactive()
	 * @see #unsetIsInactive()
	 * @see #isIsInactive()
	 * @generated
	 */
	void setIsInactive(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsInactive <em>Is Inactive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsInactive()
	 * @see #isIsInactive()
	 * @see #setIsInactive(boolean)
	 * @generated
	 */
	void unsetIsInactive();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#isIsInactive <em>Is Inactive</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Inactive</em>' attribute is set.
	 * @see #unsetIsInactive()
	 * @see #isIsInactive()
	 * @see #setIsInactive(boolean)
	 * @generated
	 */
	boolean isSetIsInactive();

	/**
	 * Returns the value of the '<em><b>Is Enterprise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the resource is an Enterprise resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Enterprise</em>' attribute.
	 * @see #isSetIsEnterprise()
	 * @see #unsetIsEnterprise()
	 * @see #setIsEnterprise(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_IsEnterprise()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsEnterprise' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsEnterprise();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsEnterprise <em>Is Enterprise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Enterprise</em>' attribute.
	 * @see #isSetIsEnterprise()
	 * @see #unsetIsEnterprise()
	 * @see #isIsEnterprise()
	 * @generated
	 */
	void setIsEnterprise(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Resource#isIsEnterprise <em>Is Enterprise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsEnterprise()
	 * @see #isIsEnterprise()
	 * @see #setIsEnterprise(boolean)
	 * @generated
	 */
	void unsetIsEnterprise();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Resource#isIsEnterprise <em>Is Enterprise</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Enterprise</em>' attribute is set.
	 * @see #unsetIsEnterprise()
	 * @see #isIsEnterprise()
	 * @see #setIsEnterprise(boolean)
	 * @generated
	 */
	boolean isSetIsEnterprise();

	/**
	 * Returns the value of the '<em><b>Booking Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the booking type of the resource. 1=Commited, 2=Proposed
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Booking Type</em>' attribute.
	 * @see #setBookingType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_BookingType()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.BookingTypeType1"
	 *        extendedMetaData="kind='element' name='BookingType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getBookingType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getBookingType <em>Booking Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Booking Type</em>' attribute.
	 * @see #getBookingType()
	 * @generated
	 */
	void setBookingType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Actual Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the duration through which actual work is protected.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Work Protected</em>' attribute.
	 * @see #setActualWorkProtected(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActualWorkProtected()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualWorkProtected' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualWorkProtected();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActualWorkProtected <em>Actual Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Work Protected</em>' attribute.
	 * @see #getActualWorkProtected()
	 * @generated
	 */
	void setActualWorkProtected(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Overtime Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the duration through which actual overtime work is protected.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Work Protected</em>' attribute.
	 * @see #setActualOvertimeWorkProtected(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActualOvertimeWorkProtected()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualOvertimeWorkProtected' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualOvertimeWorkProtected();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Work Protected</em>' attribute.
	 * @see #getActualOvertimeWorkProtected()
	 * @generated
	 */
	void setActualOvertimeWorkProtected(Object value);

	/**
	 * Returns the value of the '<em><b>Active Directory GUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Active Directory GUID for the resource.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Active Directory GUID</em>' attribute.
	 * @see #setActiveDirectoryGUID(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ActiveDirectoryGUID()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.ActiveDirectoryGUIDType"
	 *        extendedMetaData="kind='element' name='ActiveDirectoryGUID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getActiveDirectoryGUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getActiveDirectoryGUID <em>Active Directory GUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Directory GUID</em>' attribute.
	 * @see #getActiveDirectoryGUID()
	 * @generated
	 */
	void setActiveDirectoryGUID(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the resource was created.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_CreationDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='CreationDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getCreationDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(Object value);

	/**
	 * Returns the value of the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.ExtendedAttribute3}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of an extended attribute.  Two pieces of data are necessary - 
	 *                                 a pointer back to the extended attribute table which is specified either by the unique ID or the Field ID, 
	 *                                 and the value which is specified either with the value, or a pointer back to the value list.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Attribute</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_ExtendedAttribute()
	 * @model type="org.eclipse.epf.msproject.ExtendedAttribute3" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='ExtendedAttribute' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getExtendedAttribute();

	/**
	 * Returns the value of the '<em><b>Baseline</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.Baseline2}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The baseline values for the resources.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Baseline</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Baseline()
	 * @model type="org.eclipse.epf.msproject.Baseline2" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Baseline' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getBaseline();

	/**
	 * Returns the value of the '<em><b>Outline Code</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.OutlineCode3}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of an outline code.  Two pieces of data are necessary - a pointer back to 
	 *                                 the outline code table which is specified either by the unique ID or the Field ID, and the value which is 
	 *                                 specified either with the value, or a pointer back to the value list.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline Code</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_OutlineCode()
	 * @model type="org.eclipse.epf.msproject.OutlineCode3" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='OutlineCode' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getOutlineCode();

	/**
	 * Returns the value of the '<em><b>Availability Periods</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of periods during which the resource is available.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Availability Periods</em>' containment reference.
	 * @see #setAvailabilityPeriods(AvailabilityPeriods)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_AvailabilityPeriods()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='AvailabilityPeriods' namespace='##targetNamespace'"
	 * @generated
	 */
	AvailabilityPeriods getAvailabilityPeriods();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getAvailabilityPeriods <em>Availability Periods</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Availability Periods</em>' containment reference.
	 * @see #getAvailabilityPeriods()
	 * @generated
	 */
	void setAvailabilityPeriods(AvailabilityPeriods value);

	/**
	 * Returns the value of the '<em><b>Rates</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of periods and the rates associated with each one.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rates</em>' containment reference.
	 * @see #setRates(Rates)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_Rates()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Rates' namespace='##targetNamespace'"
	 * @generated
	 */
	Rates getRates();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Resource#getRates <em>Rates</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rates</em>' containment reference.
	 * @see #getRates()
	 * @generated
	 */
	void setRates(Rates value);

	/**
	 * Returns the value of the '<em><b>Timephased Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.TimephasedDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time phased data
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timephased Data</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getResource_TimephasedData()
	 * @model type="org.eclipse.epf.msproject.TimephasedDataType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='TimephasedData' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getTimephasedData();

} // Resource
