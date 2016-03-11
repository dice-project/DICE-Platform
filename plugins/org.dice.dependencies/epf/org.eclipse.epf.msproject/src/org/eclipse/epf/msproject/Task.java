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
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Task#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isIsNull <em>Is Null</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getCreateDate <em>Create Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getContact <em>Contact</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getWBS <em>WBS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getWBSLevel <em>WBS Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getOutlineNumber <em>Outline Number</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getOutlineLevel <em>Outline Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getDurationFormat <em>Duration Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getStop <em>Stop</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getResume <em>Resume</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isResumeValid <em>Resume Valid</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isEffortDriven <em>Effort Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isRecurring <em>Recurring</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isOverAllocated <em>Over Allocated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isEstimated <em>Estimated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isSummary <em>Summary</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isCritical <em>Critical</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isIsSubproject <em>Is Subproject</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isIsSubprojectReadOnly <em>Is Subproject Read Only</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getSubprojectName <em>Subproject Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isExternalTask <em>External Task</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getExternalTaskProject <em>External Task Project</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getEarlyStart <em>Early Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getEarlyFinish <em>Early Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getLateStart <em>Late Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getLateFinish <em>Late Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getStartVariance <em>Start Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getFinishVariance <em>Finish Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getWorkVariance <em>Work Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getFreeSlack <em>Free Slack</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getTotalSlack <em>Total Slack</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getFixedCost <em>Fixed Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getFixedCostAccrual <em>Fixed Cost Accrual</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPercentComplete <em>Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPercentWorkComplete <em>Percent Work Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getOvertimeCost <em>Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getOvertimeWork <em>Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualStart <em>Actual Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualFinish <em>Actual Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualDuration <em>Actual Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualCost <em>Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualOvertimeCost <em>Actual Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualWork <em>Actual Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualOvertimeWork <em>Actual Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getRegularWork <em>Regular Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getRemainingDuration <em>Remaining Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getRemainingCost <em>Remaining Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getRemainingWork <em>Remaining Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getACWP <em>ACWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getCV <em>CV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getCalendarUID <em>Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getConstraintDate <em>Constraint Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getDeadline <em>Deadline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isLevelAssignments <em>Level Assignments</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isLevelingCanSplit <em>Leveling Can Split</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getLevelingDelay <em>Leveling Delay</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getLevelingDelayFormat <em>Leveling Delay Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPreLeveledStart <em>Pre Leveled Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPreLeveledFinish <em>Pre Leveled Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getHyperlink <em>Hyperlink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getHyperlinkAddress <em>Hyperlink Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isIgnoreResourceCalendar <em>Ignore Resource Calendar</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isHideBar <em>Hide Bar</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#isRollup <em>Rollup</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getBCWP <em>BCWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPhysicalPercentComplete <em>Physical Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getEarnedValueMethod <em>Earned Value Method</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getPredecessorLink <em>Predecessor Link</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualWorkProtected <em>Actual Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getBaseline <em>Baseline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getOutlineCode <em>Outline Code</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Task#getTimephasedData <em>Timephased Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask()
 * @model extendedMetaData="name='Task_._type' kind='elementOnly'"
 * @generated
 */
public interface Task extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique ID of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getUID <em>UID</em>}' attribute.
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
	 * The position identifier of the task within the list of tasks.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ID</em>' attribute.
	 * @see #setID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='ID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getID <em>ID</em>}' attribute.
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
	 * The name of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Name()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.NameType2"
	 *        extendedMetaData="kind='element' name='Name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getName <em>Name</em>}' attribute.
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
	 * The type of task. 
	 *                                 Values are: 0=Fixed Units, 1=Fixed Duration, 2=Fixed Work.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Type()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.TypeType"
	 *        extendedMetaData="kind='element' name='Type' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getType <em>Type</em>}' attribute.
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
	 * Specifies whether the task is null.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Null</em>' attribute.
	 * @see #isSetIsNull()
	 * @see #unsetIsNull()
	 * @see #setIsNull(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_IsNull()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsNull' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsNull();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isIsNull <em>Is Null</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isIsNull <em>Is Null</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsNull()
	 * @see #isIsNull()
	 * @see #setIsNull(boolean)
	 * @generated
	 */
	void unsetIsNull();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isIsNull <em>Is Null</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Create Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the task was created.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Create Date</em>' attribute.
	 * @see #setCreateDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_CreateDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='CreateDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getCreateDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getCreateDate <em>Create Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Date</em>' attribute.
	 * @see #getCreateDate()
	 * @generated
	 */
	void setCreateDate(Object value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The contact person for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact</em>' attribute.
	 * @see #setContact(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Contact()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.ContactType"
	 *        extendedMetaData="kind='element' name='Contact' namespace='##targetNamespace'"
	 * @generated
	 */
	String getContact();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getContact <em>Contact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contact</em>' attribute.
	 * @see #getContact()
	 * @generated
	 */
	void setContact(String value);

	/**
	 * Returns the value of the '<em><b>WBS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The work breakdown structure code of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>WBS</em>' attribute.
	 * @see #setWBS(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_WBS()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='WBS' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWBS();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getWBS <em>WBS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>WBS</em>' attribute.
	 * @see #getWBS()
	 * @generated
	 */
	void setWBS(String value);

	/**
	 * Returns the value of the '<em><b>WBS Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The rightmost WBS level of the task. 
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>WBS Level</em>' attribute.
	 * @see #setWBSLevel(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_WBSLevel()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='WBSLevel' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWBSLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getWBSLevel <em>WBS Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>WBS Level</em>' attribute.
	 * @see #getWBSLevel()
	 * @generated
	 */
	void setWBSLevel(String value);

	/**
	 * Returns the value of the '<em><b>Outline Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The outline number of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline Number</em>' attribute.
	 * @see #setOutlineNumber(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_OutlineNumber()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.OutlineNumberType"
	 *        extendedMetaData="kind='element' name='OutlineNumber' namespace='##targetNamespace'"
	 * @generated
	 */
	String getOutlineNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getOutlineNumber <em>Outline Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outline Number</em>' attribute.
	 * @see #getOutlineNumber()
	 * @generated
	 */
	void setOutlineNumber(String value);

	/**
	 * Returns the value of the '<em><b>Outline Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The outline level of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline Level</em>' attribute.
	 * @see #setOutlineLevel(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_OutlineLevel()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='OutlineLevel' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getOutlineLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getOutlineLevel <em>Outline Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outline Level</em>' attribute.
	 * @see #getOutlineLevel()
	 * @generated
	 */
	void setOutlineLevel(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The priority of the task from 0 to 1000.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Priority()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='Priority' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPriority();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled start date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Start()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Start' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getStart <em>Start</em>}' attribute.
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
	 * The scheduled finish date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish</em>' attribute.
	 * @see #setFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Finish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Finish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getFinish <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish</em>' attribute.
	 * @see #getFinish()
	 * @generated
	 */
	void setFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The planned duration of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Duration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Duration' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getDuration();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(Object value);

	/**
	 * Returns the value of the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The format for expressing the Duration of the Task.  
	 *                                 Values are: 3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 19=%, 
	 *                                 20=e%, 21=null, 35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 42=ew?, 
	 *                                 43=mo?, 44=emo?, 51=%?, 52=e%? and 53=null.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration Format</em>' attribute.
	 * @see #setDurationFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_DurationFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DurationFormatType4"
	 *        extendedMetaData="kind='element' name='DurationFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDurationFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getDurationFormat <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration Format</em>' attribute.
	 * @see #getDurationFormat()
	 * @generated
	 */
	void setDurationFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of scheduled work for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work</em>' attribute.
	 * @see #setWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Work()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Work' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getWork <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work</em>' attribute.
	 * @see #getWork()
	 * @generated
	 */
	void setWork(Object value);

	/**
	 * Returns the value of the '<em><b>Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the task was stopped.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stop</em>' attribute.
	 * @see #setStop(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Stop()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Stop' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStop();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getStop <em>Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop</em>' attribute.
	 * @see #getStop()
	 * @generated
	 */
	void setStop(Object value);

	/**
	 * Returns the value of the '<em><b>Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the task resumed.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resume</em>' attribute.
	 * @see #setResume(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Resume()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Resume' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getResume();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getResume <em>Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resume</em>' attribute.
	 * @see #getResume()
	 * @generated
	 */
	void setResume(Object value);

	/**
	 * Returns the value of the '<em><b>Resume Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task can be resumed.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resume Valid</em>' attribute.
	 * @see #isSetResumeValid()
	 * @see #unsetResumeValid()
	 * @see #setResumeValid(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ResumeValid()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ResumeValid' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isResumeValid();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isResumeValid <em>Resume Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resume Valid</em>' attribute.
	 * @see #isSetResumeValid()
	 * @see #unsetResumeValid()
	 * @see #isResumeValid()
	 * @generated
	 */
	void setResumeValid(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isResumeValid <em>Resume Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResumeValid()
	 * @see #isResumeValid()
	 * @see #setResumeValid(boolean)
	 * @generated
	 */
	void unsetResumeValid();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isResumeValid <em>Resume Valid</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Resume Valid</em>' attribute is set.
	 * @see #unsetResumeValid()
	 * @see #isResumeValid()
	 * @see #setResumeValid(boolean)
	 * @generated
	 */
	boolean isSetResumeValid();

	/**
	 * Returns the value of the '<em><b>Effort Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is effort-driven.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Effort Driven</em>' attribute.
	 * @see #isSetEffortDriven()
	 * @see #unsetEffortDriven()
	 * @see #setEffortDriven(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_EffortDriven()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='EffortDriven' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isEffortDriven();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isEffortDriven <em>Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Effort Driven</em>' attribute.
	 * @see #isSetEffortDriven()
	 * @see #unsetEffortDriven()
	 * @see #isEffortDriven()
	 * @generated
	 */
	void setEffortDriven(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isEffortDriven <em>Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEffortDriven()
	 * @see #isEffortDriven()
	 * @see #setEffortDriven(boolean)
	 * @generated
	 */
	void unsetEffortDriven();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isEffortDriven <em>Effort Driven</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Effort Driven</em>' attribute is set.
	 * @see #unsetEffortDriven()
	 * @see #isEffortDriven()
	 * @see #setEffortDriven(boolean)
	 * @generated
	 */
	boolean isSetEffortDriven();

	/**
	 * Returns the value of the '<em><b>Recurring</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is a recurring task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Recurring</em>' attribute.
	 * @see #isSetRecurring()
	 * @see #unsetRecurring()
	 * @see #setRecurring(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Recurring()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Recurring' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isRecurring();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isRecurring <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recurring</em>' attribute.
	 * @see #isSetRecurring()
	 * @see #unsetRecurring()
	 * @see #isRecurring()
	 * @generated
	 */
	void setRecurring(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isRecurring <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRecurring()
	 * @see #isRecurring()
	 * @see #setRecurring(boolean)
	 * @generated
	 */
	void unsetRecurring();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isRecurring <em>Recurring</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Recurring</em>' attribute is set.
	 * @see #unsetRecurring()
	 * @see #isRecurring()
	 * @see #setRecurring(boolean)
	 * @generated
	 */
	boolean isSetRecurring();

	/**
	 * Returns the value of the '<em><b>Over Allocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is overallocated. 
	 *                                 This element is informational only.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Over Allocated</em>' attribute.
	 * @see #isSetOverAllocated()
	 * @see #unsetOverAllocated()
	 * @see #setOverAllocated(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_OverAllocated()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='OverAllocated' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isOverAllocated();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isOverAllocated <em>Over Allocated</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isOverAllocated <em>Over Allocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOverAllocated()
	 * @see #isOverAllocated()
	 * @see #setOverAllocated(boolean)
	 * @generated
	 */
	void unsetOverAllocated();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isOverAllocated <em>Over Allocated</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Estimated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is estimated.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Estimated</em>' attribute.
	 * @see #isSetEstimated()
	 * @see #unsetEstimated()
	 * @see #setEstimated(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Estimated()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Estimated' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isEstimated();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isEstimated <em>Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Estimated</em>' attribute.
	 * @see #isSetEstimated()
	 * @see #unsetEstimated()
	 * @see #isEstimated()
	 * @generated
	 */
	void setEstimated(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isEstimated <em>Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEstimated()
	 * @see #isEstimated()
	 * @see #setEstimated(boolean)
	 * @generated
	 */
	void unsetEstimated();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isEstimated <em>Estimated</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Estimated</em>' attribute is set.
	 * @see #unsetEstimated()
	 * @see #isEstimated()
	 * @see #setEstimated(boolean)
	 * @generated
	 */
	boolean isSetEstimated();

	/**
	 * Returns the value of the '<em><b>Milestone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is a milestone.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Milestone</em>' attribute.
	 * @see #isSetMilestone()
	 * @see #unsetMilestone()
	 * @see #setMilestone(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Milestone()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Milestone' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMilestone();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isMilestone <em>Milestone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Milestone</em>' attribute.
	 * @see #isSetMilestone()
	 * @see #unsetMilestone()
	 * @see #isMilestone()
	 * @generated
	 */
	void setMilestone(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isMilestone <em>Milestone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMilestone()
	 * @see #isMilestone()
	 * @see #setMilestone(boolean)
	 * @generated
	 */
	void unsetMilestone();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isMilestone <em>Milestone</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Milestone</em>' attribute is set.
	 * @see #unsetMilestone()
	 * @see #isMilestone()
	 * @see #setMilestone(boolean)
	 * @generated
	 */
	boolean isSetMilestone();

	/**
	 * Returns the value of the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is a summary task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Summary</em>' attribute.
	 * @see #isSetSummary()
	 * @see #unsetSummary()
	 * @see #setSummary(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Summary()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Summary' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSummary();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isSummary <em>Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Summary</em>' attribute.
	 * @see #isSetSummary()
	 * @see #unsetSummary()
	 * @see #isSummary()
	 * @generated
	 */
	void setSummary(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isSummary <em>Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSummary()
	 * @see #isSummary()
	 * @see #setSummary(boolean)
	 * @generated
	 */
	void unsetSummary();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isSummary <em>Summary</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Summary</em>' attribute is set.
	 * @see #unsetSummary()
	 * @see #isSummary()
	 * @see #setSummary(boolean)
	 * @generated
	 */
	boolean isSetSummary();

	/**
	 * Returns the value of the '<em><b>Critical</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is in the critical chain.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Critical</em>' attribute.
	 * @see #isSetCritical()
	 * @see #unsetCritical()
	 * @see #setCritical(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Critical()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Critical' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isCritical();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isCritical <em>Critical</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Critical</em>' attribute.
	 * @see #isSetCritical()
	 * @see #unsetCritical()
	 * @see #isCritical()
	 * @generated
	 */
	void setCritical(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isCritical <em>Critical</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCritical()
	 * @see #isCritical()
	 * @see #setCritical(boolean)
	 * @generated
	 */
	void unsetCritical();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isCritical <em>Critical</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Critical</em>' attribute is set.
	 * @see #unsetCritical()
	 * @see #isCritical()
	 * @see #setCritical(boolean)
	 * @generated
	 */
	boolean isSetCritical();

	/**
	 * Returns the value of the '<em><b>Is Subproject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is an inserted project.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Subproject</em>' attribute.
	 * @see #isSetIsSubproject()
	 * @see #unsetIsSubproject()
	 * @see #setIsSubproject(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_IsSubproject()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsSubproject' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsSubproject();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isIsSubproject <em>Is Subproject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Subproject</em>' attribute.
	 * @see #isSetIsSubproject()
	 * @see #unsetIsSubproject()
	 * @see #isIsSubproject()
	 * @generated
	 */
	void setIsSubproject(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isIsSubproject <em>Is Subproject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsSubproject()
	 * @see #isIsSubproject()
	 * @see #setIsSubproject(boolean)
	 * @generated
	 */
	void unsetIsSubproject();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isIsSubproject <em>Is Subproject</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Subproject</em>' attribute is set.
	 * @see #unsetIsSubproject()
	 * @see #isIsSubproject()
	 * @see #setIsSubproject(boolean)
	 * @generated
	 */
	boolean isSetIsSubproject();

	/**
	 * Returns the value of the '<em><b>Is Subproject Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the inserted project is read-only.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Subproject Read Only</em>' attribute.
	 * @see #isSetIsSubprojectReadOnly()
	 * @see #unsetIsSubprojectReadOnly()
	 * @see #setIsSubprojectReadOnly(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_IsSubprojectReadOnly()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IsSubprojectReadOnly' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIsSubprojectReadOnly();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isIsSubprojectReadOnly <em>Is Subproject Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Subproject Read Only</em>' attribute.
	 * @see #isSetIsSubprojectReadOnly()
	 * @see #unsetIsSubprojectReadOnly()
	 * @see #isIsSubprojectReadOnly()
	 * @generated
	 */
	void setIsSubprojectReadOnly(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isIsSubprojectReadOnly <em>Is Subproject Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsSubprojectReadOnly()
	 * @see #isIsSubprojectReadOnly()
	 * @see #setIsSubprojectReadOnly(boolean)
	 * @generated
	 */
	void unsetIsSubprojectReadOnly();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isIsSubprojectReadOnly <em>Is Subproject Read Only</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Subproject Read Only</em>' attribute is set.
	 * @see #unsetIsSubprojectReadOnly()
	 * @see #isIsSubprojectReadOnly()
	 * @see #setIsSubprojectReadOnly(boolean)
	 * @generated
	 */
	boolean isSetIsSubprojectReadOnly();

	/**
	 * Returns the value of the '<em><b>Subproject Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The source location of the inserted project.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Subproject Name</em>' attribute.
	 * @see #setSubprojectName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_SubprojectName()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.SubprojectNameType"
	 *        extendedMetaData="kind='element' name='SubprojectName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSubprojectName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getSubprojectName <em>Subproject Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subproject Name</em>' attribute.
	 * @see #getSubprojectName()
	 * @generated
	 */
	void setSubprojectName(String value);

	/**
	 * Returns the value of the '<em><b>External Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is external.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Task</em>' attribute.
	 * @see #isSetExternalTask()
	 * @see #unsetExternalTask()
	 * @see #setExternalTask(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ExternalTask()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ExternalTask' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isExternalTask();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isExternalTask <em>External Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Task</em>' attribute.
	 * @see #isSetExternalTask()
	 * @see #unsetExternalTask()
	 * @see #isExternalTask()
	 * @generated
	 */
	void setExternalTask(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isExternalTask <em>External Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExternalTask()
	 * @see #isExternalTask()
	 * @see #setExternalTask(boolean)
	 * @generated
	 */
	void unsetExternalTask();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isExternalTask <em>External Task</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>External Task</em>' attribute is set.
	 * @see #unsetExternalTask()
	 * @see #isExternalTask()
	 * @see #setExternalTask(boolean)
	 * @generated
	 */
	boolean isSetExternalTask();

	/**
	 * Returns the value of the '<em><b>External Task Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The source location and task identifier of the external task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Task Project</em>' attribute.
	 * @see #setExternalTaskProject(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ExternalTaskProject()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.ExternalTaskProjectType"
	 *        extendedMetaData="kind='element' name='ExternalTaskProject' namespace='##targetNamespace'"
	 * @generated
	 */
	String getExternalTaskProject();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getExternalTaskProject <em>External Task Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Task Project</em>' attribute.
	 * @see #getExternalTaskProject()
	 * @generated
	 */
	void setExternalTaskProject(String value);

	/**
	 * Returns the value of the '<em><b>Early Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The early start date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Early Start</em>' attribute.
	 * @see #setEarlyStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_EarlyStart()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='EarlyStart' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getEarlyStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getEarlyStart <em>Early Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Early Start</em>' attribute.
	 * @see #getEarlyStart()
	 * @generated
	 */
	void setEarlyStart(Object value);

	/**
	 * Returns the value of the '<em><b>Early Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The early finish date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Early Finish</em>' attribute.
	 * @see #setEarlyFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_EarlyFinish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='EarlyFinish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getEarlyFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getEarlyFinish <em>Early Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Early Finish</em>' attribute.
	 * @see #getEarlyFinish()
	 * @generated
	 */
	void setEarlyFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Late Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The late start date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Late Start</em>' attribute.
	 * @see #setLateStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_LateStart()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='LateStart' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getLateStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getLateStart <em>Late Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Late Start</em>' attribute.
	 * @see #getLateStart()
	 * @generated
	 */
	void setLateStart(Object value);

	/**
	 * Returns the value of the '<em><b>Late Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The late finish date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Late Finish</em>' attribute.
	 * @see #setLateFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_LateFinish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='LateFinish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getLateFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getLateFinish <em>Late Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Late Finish</em>' attribute.
	 * @see #getLateFinish()
	 * @generated
	 */
	void setLateFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Start Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variance of the task start date from 
	 *                                 the baseline start date as minutes x 1000.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Variance</em>' attribute.
	 * @see #setStartVariance(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_StartVariance()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='StartVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getStartVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getStartVariance <em>Start Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Variance</em>' attribute.
	 * @see #getStartVariance()
	 * @generated
	 */
	void setStartVariance(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Finish Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variance of the task finish date from 
	 *                                 the baseline finish date as minutes x 1000.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish Variance</em>' attribute.
	 * @see #setFinishVariance(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_FinishVariance()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='FinishVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getFinishVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getFinishVariance <em>Finish Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Variance</em>' attribute.
	 * @see #getFinishVariance()
	 * @generated
	 */
	void setFinishVariance(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Work Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variance of task work from the baseline 
	 *                                 task work as minutes x 1000.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work Variance</em>' attribute.
	 * @see #isSetWorkVariance()
	 * @see #unsetWorkVariance()
	 * @see #setWorkVariance(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_WorkVariance()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='WorkVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	float getWorkVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getWorkVariance <em>Work Variance</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#getWorkVariance <em>Work Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWorkVariance()
	 * @see #getWorkVariance()
	 * @see #setWorkVariance(float)
	 * @generated
	 */
	void unsetWorkVariance();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#getWorkVariance <em>Work Variance</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Free Slack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of free slack.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Free Slack</em>' attribute.
	 * @see #setFreeSlack(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_FreeSlack()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='FreeSlack' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getFreeSlack();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getFreeSlack <em>Free Slack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Free Slack</em>' attribute.
	 * @see #getFreeSlack()
	 * @generated
	 */
	void setFreeSlack(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Total Slack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of total slack.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Total Slack</em>' attribute.
	 * @see #setTotalSlack(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_TotalSlack()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='TotalSlack' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getTotalSlack();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getTotalSlack <em>Total Slack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Slack</em>' attribute.
	 * @see #getTotalSlack()
	 * @generated
	 */
	void setTotalSlack(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Fixed Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The fixed cost of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fixed Cost</em>' attribute.
	 * @see #isSetFixedCost()
	 * @see #unsetFixedCost()
	 * @see #setFixedCost(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_FixedCost()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='FixedCost' namespace='##targetNamespace'"
	 * @generated
	 */
	float getFixedCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getFixedCost <em>Fixed Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Cost</em>' attribute.
	 * @see #isSetFixedCost()
	 * @see #unsetFixedCost()
	 * @see #getFixedCost()
	 * @generated
	 */
	void setFixedCost(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#getFixedCost <em>Fixed Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFixedCost()
	 * @see #getFixedCost()
	 * @see #setFixedCost(float)
	 * @generated
	 */
	void unsetFixedCost();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#getFixedCost <em>Fixed Cost</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fixed Cost</em>' attribute is set.
	 * @see #unsetFixedCost()
	 * @see #getFixedCost()
	 * @see #setFixedCost(float)
	 * @generated
	 */
	boolean isSetFixedCost();

	/**
	 * Returns the value of the '<em><b>Fixed Cost Accrual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * How the fixed cost is accrued against the task. 
	 *                                 Values are: 1=Start, 2=Prorated and 3=End.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fixed Cost Accrual</em>' attribute.
	 * @see #setFixedCostAccrual(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_FixedCostAccrual()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.FixedCostAccrualType"
	 *        extendedMetaData="kind='element' name='FixedCostAccrual' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFixedCostAccrual();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getFixedCostAccrual <em>Fixed Cost Accrual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Cost Accrual</em>' attribute.
	 * @see #getFixedCostAccrual()
	 * @generated
	 */
	void setFixedCostAccrual(String value);

	/**
	 * Returns the value of the '<em><b>Percent Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The percentage of the task duration completed.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Percent Complete</em>' attribute.
	 * @see #setPercentComplete(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_PercentComplete()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='PercentComplete' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPercentComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getPercentComplete <em>Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percent Complete</em>' attribute.
	 * @see #getPercentComplete()
	 * @generated
	 */
	void setPercentComplete(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Percent Work Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The percentage of the task work completed.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Percent Work Complete</em>' attribute.
	 * @see #setPercentWorkComplete(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_PercentWorkComplete()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='PercentWorkComplete' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPercentWorkComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getPercentWorkComplete <em>Percent Work Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percent Work Complete</em>' attribute.
	 * @see #getPercentWorkComplete()
	 * @generated
	 */
	void setPercentWorkComplete(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The projected or scheduled cost of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Cost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The sum of the actual and remaining overtime cost of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Cost</em>' attribute.
	 * @see #setOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_OvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='OvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getOvertimeCost <em>Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Cost</em>' attribute.
	 * @see #getOvertimeCost()
	 * @generated
	 */
	void setOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of overtime work scheduled for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Work</em>' attribute.
	 * @see #setOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_OvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='OvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getOvertimeWork <em>Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Work</em>' attribute.
	 * @see #getOvertimeWork()
	 * @generated
	 */
	void setOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual start date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Start</em>' attribute.
	 * @see #setActualStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualStart()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ActualStart' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualStart <em>Actual Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Start</em>' attribute.
	 * @see #getActualStart()
	 * @generated
	 */
	void setActualStart(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual finish date of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Finish</em>' attribute.
	 * @see #setActualFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualFinish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ActualFinish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualFinish <em>Actual Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Finish</em>' attribute.
	 * @see #getActualFinish()
	 * @generated
	 */
	void setActualFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual duration of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Duration</em>' attribute.
	 * @see #setActualDuration(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualDuration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualDuration' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualDuration();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualDuration <em>Actual Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Duration</em>' attribute.
	 * @see #getActualDuration()
	 * @generated
	 */
	void setActualDuration(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual cost of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Cost</em>' attribute.
	 * @see #setActualCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='ActualCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getActualCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualCost <em>Actual Cost</em>}' attribute.
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
	 * The actual overtime cost of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Cost</em>' attribute.
	 * @see #setActualOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualOvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='ActualOvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getActualOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualOvertimeCost <em>Actual Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Cost</em>' attribute.
	 * @see #getActualOvertimeCost()
	 * @generated
	 */
	void setActualOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Actual Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual work for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Work</em>' attribute.
	 * @see #setActualWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualWork <em>Actual Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Work</em>' attribute.
	 * @see #getActualWork()
	 * @generated
	 */
	void setActualWork(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual overtime work for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Work</em>' attribute.
	 * @see #setActualOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualOvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualOvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualOvertimeWork <em>Actual Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Work</em>' attribute.
	 * @see #getActualOvertimeWork()
	 * @generated
	 */
	void setActualOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Regular Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of non-overtime work scheduled for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Regular Work</em>' attribute.
	 * @see #setRegularWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_RegularWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RegularWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRegularWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getRegularWork <em>Regular Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regular Work</em>' attribute.
	 * @see #getRegularWork()
	 * @generated
	 */
	void setRegularWork(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of time required to complete the unfinished portion of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Duration</em>' attribute.
	 * @see #setRemainingDuration(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_RemainingDuration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingDuration' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingDuration();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getRemainingDuration <em>Remaining Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Duration</em>' attribute.
	 * @see #getRemainingDuration()
	 * @generated
	 */
	void setRemainingDuration(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining projected cost of completing the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Cost</em>' attribute.
	 * @see #setRemainingCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_RemainingCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='RemainingCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getRemainingCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getRemainingCost <em>Remaining Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Cost</em>' attribute.
	 * @see #getRemainingCost()
	 * @generated
	 */
	void setRemainingCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Remaining Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining work scheduled to complete the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Work</em>' attribute.
	 * @see #setRemainingWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_RemainingWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getRemainingWork <em>Remaining Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Work</em>' attribute.
	 * @see #getRemainingWork()
	 * @generated
	 */
	void setRemainingWork(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining overtime cost projected to finish the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Overtime Cost</em>' attribute.
	 * @see #setRemainingOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_RemainingOvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='RemainingOvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getRemainingOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Overtime Cost</em>' attribute.
	 * @see #getRemainingOvertimeCost()
	 * @generated
	 */
	void setRemainingOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Remaining Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining overtime work scheduled to finish the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Overtime Work</em>' attribute.
	 * @see #setRemainingOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_RemainingOvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingOvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Overtime Work</em>' attribute.
	 * @see #getRemainingOvertimeWork()
	 * @generated
	 */
	void setRemainingOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>ACWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual cost of work performed on the task to-date.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ACWP</em>' attribute.
	 * @see #isSetACWP()
	 * @see #unsetACWP()
	 * @see #setACWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ACWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='ACWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getACWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getACWP <em>ACWP</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#getACWP <em>ACWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetACWP()
	 * @see #getACWP()
	 * @see #setACWP(float)
	 * @generated
	 */
	void unsetACWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#getACWP <em>ACWP</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>CV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Earned value cost variance.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>CV</em>' attribute.
	 * @see #isSetCV()
	 * @see #unsetCV()
	 * @see #setCV(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_CV()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='CV' namespace='##targetNamespace'"
	 * @generated
	 */
	float getCV();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getCV <em>CV</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#getCV <em>CV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCV()
	 * @see #getCV()
	 * @see #setCV(float)
	 * @generated
	 */
	void unsetCV();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#getCV <em>CV</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Constraint Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The constraint on the start or finish date of the task. 
	 *                                 Values are: 0=As Soon As Possible, 1=As Late As Possible, 2=Must Start On, 3=Must Finish On, 
	 *                                 4=Start No Earlier Than, 5=Start No Later Than, 6=Finish No Earlier Than and 7=Finish No Later Than
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Constraint Type</em>' attribute.
	 * @see #setConstraintType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ConstraintType()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.ConstraintTypeType"
	 *        extendedMetaData="kind='element' name='ConstraintType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getConstraintType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getConstraintType <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Type</em>' attribute.
	 * @see #getConstraintType()
	 * @generated
	 */
	void setConstraintType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The task calendar.Refers to a valid UID in the Calendars element of 
	 *                                 the Microsoft Project XML Schema.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calendar UID</em>' attribute.
	 * @see #setCalendarUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_CalendarUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='CalendarUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCalendarUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getCalendarUID <em>Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calendar UID</em>' attribute.
	 * @see #getCalendarUID()
	 * @generated
	 */
	void setCalendarUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Constraint Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date argument for the task constraint type.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Constraint Date</em>' attribute.
	 * @see #setConstraintDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ConstraintDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ConstraintDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getConstraintDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getConstraintDate <em>Constraint Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Date</em>' attribute.
	 * @see #getConstraintDate()
	 * @generated
	 */
	void setConstraintDate(Object value);

	/**
	 * Returns the value of the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The deadline for the task to be completed.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deadline</em>' attribute.
	 * @see #setDeadline(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Deadline()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Deadline' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getDeadline();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getDeadline <em>Deadline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deadline</em>' attribute.
	 * @see #getDeadline()
	 * @generated
	 */
	void setDeadline(Object value);

	/**
	 * Returns the value of the '<em><b>Level Assignments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether leveling can adjust assignments.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Level Assignments</em>' attribute.
	 * @see #isSetLevelAssignments()
	 * @see #unsetLevelAssignments()
	 * @see #setLevelAssignments(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_LevelAssignments()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='LevelAssignments' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isLevelAssignments();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isLevelAssignments <em>Level Assignments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level Assignments</em>' attribute.
	 * @see #isSetLevelAssignments()
	 * @see #unsetLevelAssignments()
	 * @see #isLevelAssignments()
	 * @generated
	 */
	void setLevelAssignments(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isLevelAssignments <em>Level Assignments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLevelAssignments()
	 * @see #isLevelAssignments()
	 * @see #setLevelAssignments(boolean)
	 * @generated
	 */
	void unsetLevelAssignments();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isLevelAssignments <em>Level Assignments</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Level Assignments</em>' attribute is set.
	 * @see #unsetLevelAssignments()
	 * @see #isLevelAssignments()
	 * @see #setLevelAssignments(boolean)
	 * @generated
	 */
	boolean isSetLevelAssignments();

	/**
	 * Returns the value of the '<em><b>Leveling Can Split</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether leveling can split the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leveling Can Split</em>' attribute.
	 * @see #isSetLevelingCanSplit()
	 * @see #unsetLevelingCanSplit()
	 * @see #setLevelingCanSplit(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_LevelingCanSplit()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='LevelingCanSplit' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isLevelingCanSplit();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isLevelingCanSplit <em>Leveling Can Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leveling Can Split</em>' attribute.
	 * @see #isSetLevelingCanSplit()
	 * @see #unsetLevelingCanSplit()
	 * @see #isLevelingCanSplit()
	 * @generated
	 */
	void setLevelingCanSplit(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isLevelingCanSplit <em>Leveling Can Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLevelingCanSplit()
	 * @see #isLevelingCanSplit()
	 * @see #setLevelingCanSplit(boolean)
	 * @generated
	 */
	void unsetLevelingCanSplit();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isLevelingCanSplit <em>Leveling Can Split</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Leveling Can Split</em>' attribute is set.
	 * @see #unsetLevelingCanSplit()
	 * @see #isLevelingCanSplit()
	 * @see #setLevelingCanSplit(boolean)
	 * @generated
	 */
	boolean isSetLevelingCanSplit();

	/**
	 * Returns the value of the '<em><b>Leveling Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The delay caused by leveling the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leveling Delay</em>' attribute.
	 * @see #setLevelingDelay(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_LevelingDelay()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='LevelingDelay' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLevelingDelay();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getLevelingDelay <em>Leveling Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leveling Delay</em>' attribute.
	 * @see #getLevelingDelay()
	 * @generated
	 */
	void setLevelingDelay(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Leveling Delay Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The format for expressing the duration of the delay.  
	 *                                 Values are: 3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 19=%, 
	 *                                 20=e%, 21=null, 35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 42=ew?, 
	 *                                 43=mo?, 44=emo?, 51=%?, 52=e%? and 53=null.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leveling Delay Format</em>' attribute.
	 * @see #setLevelingDelayFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_LevelingDelayFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.LevelingDelayFormatType1"
	 *        extendedMetaData="kind='element' name='LevelingDelayFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLevelingDelayFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getLevelingDelayFormat <em>Leveling Delay Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leveling Delay Format</em>' attribute.
	 * @see #getLevelingDelayFormat()
	 * @generated
	 */
	void setLevelingDelayFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Pre Leveled Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The start date of the task before it was leveled.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pre Leveled Start</em>' attribute.
	 * @see #setPreLeveledStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_PreLeveledStart()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='PreLeveledStart' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getPreLeveledStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getPreLeveledStart <em>Pre Leveled Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Leveled Start</em>' attribute.
	 * @see #getPreLeveledStart()
	 * @generated
	 */
	void setPreLeveledStart(Object value);

	/**
	 * Returns the value of the '<em><b>Pre Leveled Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The finish date of the task before it was leveled.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pre Leveled Finish</em>' attribute.
	 * @see #setPreLeveledFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_PreLeveledFinish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='PreLeveledFinish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getPreLeveledFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getPreLeveledFinish <em>Pre Leveled Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Leveled Finish</em>' attribute.
	 * @see #getPreLeveledFinish()
	 * @generated
	 */
	void setPreLeveledFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Hyperlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The title of the hyperlink associated with the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink</em>' attribute.
	 * @see #setHyperlink(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Hyperlink()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkType2"
	 *        extendedMetaData="kind='element' name='Hyperlink' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlink();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getHyperlink <em>Hyperlink</em>}' attribute.
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
	 * The hyperlink associated with the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink Address</em>' attribute.
	 * @see #setHyperlinkAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_HyperlinkAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkAddressType2"
	 *        extendedMetaData="kind='element' name='HyperlinkAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlinkAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getHyperlinkAddress <em>Hyperlink Address</em>}' attribute.
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
	 * The document bookmark of the hyperlink associated with the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink Sub Address</em>' attribute.
	 * @see #setHyperlinkSubAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_HyperlinkSubAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkSubAddressType2"
	 *        extendedMetaData="kind='element' name='HyperlinkSubAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlinkSubAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hyperlink Sub Address</em>' attribute.
	 * @see #getHyperlinkSubAddress()
	 * @generated
	 */
	void setHyperlinkSubAddress(String value);

	/**
	 * Returns the value of the '<em><b>Ignore Resource Calendar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task ignores the resource calendar.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ignore Resource Calendar</em>' attribute.
	 * @see #isSetIgnoreResourceCalendar()
	 * @see #unsetIgnoreResourceCalendar()
	 * @see #setIgnoreResourceCalendar(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_IgnoreResourceCalendar()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='IgnoreResourceCalendar' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isIgnoreResourceCalendar();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isIgnoreResourceCalendar <em>Ignore Resource Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore Resource Calendar</em>' attribute.
	 * @see #isSetIgnoreResourceCalendar()
	 * @see #unsetIgnoreResourceCalendar()
	 * @see #isIgnoreResourceCalendar()
	 * @generated
	 */
	void setIgnoreResourceCalendar(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isIgnoreResourceCalendar <em>Ignore Resource Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIgnoreResourceCalendar()
	 * @see #isIgnoreResourceCalendar()
	 * @see #setIgnoreResourceCalendar(boolean)
	 * @generated
	 */
	void unsetIgnoreResourceCalendar();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isIgnoreResourceCalendar <em>Ignore Resource Calendar</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ignore Resource Calendar</em>' attribute is set.
	 * @see #unsetIgnoreResourceCalendar()
	 * @see #isIgnoreResourceCalendar()
	 * @see #setIgnoreResourceCalendar(boolean)
	 * @generated
	 */
	boolean isSetIgnoreResourceCalendar();

	/**
	 * Returns the value of the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text notes associated with the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notes</em>' attribute.
	 * @see #setNotes(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Notes()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Notes' namespace='##targetNamespace'"
	 * @generated
	 */
	String getNotes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getNotes <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notes</em>' attribute.
	 * @see #getNotes()
	 * @generated
	 */
	void setNotes(String value);

	/**
	 * Returns the value of the '<em><b>Hide Bar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the GANTT bar of the task is hidden when displayed in Microsoft Project.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hide Bar</em>' attribute.
	 * @see #isSetHideBar()
	 * @see #unsetHideBar()
	 * @see #setHideBar(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_HideBar()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='HideBar' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isHideBar();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isHideBar <em>Hide Bar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hide Bar</em>' attribute.
	 * @see #isSetHideBar()
	 * @see #unsetHideBar()
	 * @see #isHideBar()
	 * @generated
	 */
	void setHideBar(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isHideBar <em>Hide Bar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHideBar()
	 * @see #isHideBar()
	 * @see #setHideBar(boolean)
	 * @generated
	 */
	void unsetHideBar();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isHideBar <em>Hide Bar</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Hide Bar</em>' attribute is set.
	 * @see #unsetHideBar()
	 * @see #isHideBar()
	 * @see #setHideBar(boolean)
	 * @generated
	 */
	boolean isSetHideBar();

	/**
	 * Returns the value of the '<em><b>Rollup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the task is rolled up.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rollup</em>' attribute.
	 * @see #isSetRollup()
	 * @see #unsetRollup()
	 * @see #setRollup(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Rollup()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Rollup' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isRollup();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#isRollup <em>Rollup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rollup</em>' attribute.
	 * @see #isSetRollup()
	 * @see #unsetRollup()
	 * @see #isRollup()
	 * @generated
	 */
	void setRollup(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#isRollup <em>Rollup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRollup()
	 * @see #isRollup()
	 * @see #setRollup(boolean)
	 * @generated
	 */
	void unsetRollup();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#isRollup <em>Rollup</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Rollup</em>' attribute is set.
	 * @see #unsetRollup()
	 * @see #isRollup()
	 * @see #setRollup(boolean)
	 * @generated
	 */
	boolean isSetRollup();

	/**
	 * Returns the value of the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budgeted cost of work scheduled for the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #setBCWS(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_BCWS()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWS' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWS();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getBCWS <em>BCWS</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	void unsetBCWS();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#getBCWS <em>BCWS</em>}' attribute is set.
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
	 * The budgeted cost of work performed on the task to-date.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #setBCWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_BCWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getBCWP <em>BCWP</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Task#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	void unsetBCWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Task#getBCWP <em>BCWP</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Physical Percent Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The percentage complete value entered by the Project Manager.  
	 *                                 This can be used as an alternative for calculating BCWP.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Physical Percent Complete</em>' attribute.
	 * @see #setPhysicalPercentComplete(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_PhysicalPercentComplete()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='PhysicalPercentComplete' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPhysicalPercentComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getPhysicalPercentComplete <em>Physical Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Physical Percent Complete</em>' attribute.
	 * @see #getPhysicalPercentComplete()
	 * @generated
	 */
	void setPhysicalPercentComplete(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Earned Value Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The method for calculating earned value. 
	 *                                 Values are: 0=Percent Complete, 1=Physical Percent Complete
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Earned Value Method</em>' attribute.
	 * @see #setEarnedValueMethod(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_EarnedValueMethod()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.EarnedValueMethodType"
	 *        extendedMetaData="kind='element' name='EarnedValueMethod' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getEarnedValueMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getEarnedValueMethod <em>Earned Value Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Earned Value Method</em>' attribute.
	 * @see #getEarnedValueMethod()
	 * @generated
	 */
	void setEarnedValueMethod(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Predecessor Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.PredecessorLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the predecessor task of the task that contains it.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predecessor Link</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_PredecessorLink()
	 * @model type="org.eclipse.epf.msproject.PredecessorLink" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='PredecessorLink' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getPredecessorLink();

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
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualWorkProtected()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualWorkProtected' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualWorkProtected();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualWorkProtected <em>Actual Work Protected</em>}' attribute.
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
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ActualOvertimeWorkProtected()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualOvertimeWorkProtected' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualOvertimeWorkProtected();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Task#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Work Protected</em>' attribute.
	 * @see #getActualOvertimeWorkProtected()
	 * @generated
	 */
	void setActualOvertimeWorkProtected(Object value);

	/**
	 * Returns the value of the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.ExtendedAttribute2}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of an extended attribute.  Two pieces of data are necessary - 
	 *                                 a pointer back to the extended attribute table which is specified either by the unique ID or 
	 *                                 the Field ID, and the value which is specified either with the value, or a pointer back to the value list.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Attribute</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_ExtendedAttribute()
	 * @model type="org.eclipse.epf.msproject.ExtendedAttribute2" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='ExtendedAttribute' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getExtendedAttribute();

	/**
	 * Returns the value of the '<em><b>Baseline</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.Baseline}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of baseline values of the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Baseline</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_Baseline()
	 * @model type="org.eclipse.epf.msproject.Baseline" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Baseline' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getBaseline();

	/**
	 * Returns the value of the '<em><b>Outline Code</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.OutlineCode2}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of an outline code.  Two pieces of data are necessary - 
	 *                                 a pointer back to the outline code table which is specified either by the unique ID or the Field ID, 
	 *                                 and the value which is specified either with the value, or a pointer back to the value list.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline Code</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_OutlineCode()
	 * @model type="org.eclipse.epf.msproject.OutlineCode2" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='OutlineCode' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getOutlineCode();

	/**
	 * Returns the value of the '<em><b>Timephased Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.TimephasedDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time phased data block associated with the task.
	 *                                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timephased Data</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTask_TimephasedData()
	 * @model type="org.eclipse.epf.msproject.TimephasedDataType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='TimephasedData' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getTimephasedData();

} // Task
