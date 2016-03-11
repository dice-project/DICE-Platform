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
 * A representation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getTaskUID <em>Task UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getResourceUID <em>Resource UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getPercentWorkComplete <em>Percent Work Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualCost <em>Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualFinish <em>Actual Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeCost <em>Actual Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeWork <em>Actual Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualStart <em>Actual Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualWork <em>Actual Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getACWP <em>ACWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isConfirmed <em>Confirmed</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getCostRateTable <em>Cost Rate Table</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getCostVariance <em>Cost Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getCV <em>CV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getDelay <em>Delay</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getFinishVariance <em>Finish Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getHyperlink <em>Hyperlink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getHyperlinkAddress <em>Hyperlink Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getWorkVariance <em>Work Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isHasFixedRateUnits <em>Has Fixed Rate Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isFixedMaterial <em>Fixed Material</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getLevelingDelay <em>Leveling Delay</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getLevelingDelayFormat <em>Leveling Delay Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isLinkedFields <em>Linked Fields</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isOverallocated <em>Overallocated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getOvertimeCost <em>Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getOvertimeWork <em>Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getRegularWork <em>Regular Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getRemainingCost <em>Remaining Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getRemainingWork <em>Remaining Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isResponsePending <em>Response Pending</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getStop <em>Stop</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getResume <em>Resume</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getStartVariance <em>Start Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getUnits <em>Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#isUpdateNeeded <em>Update Needed</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getVAC <em>VAC</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getWorkContour <em>Work Contour</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getBCWP <em>BCWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getBookingType <em>Booking Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualWorkProtected <em>Actual Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getBaseline <em>Baseline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Assignment#getTimephasedData <em>Timephased Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment()
 * @model extendedMetaData="name='Assignment_._type' kind='elementOnly'"
 * @generated
 */
public interface Assignment extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getUID <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UID</em>' attribute.
	 * @see #getUID()
	 * @generated
	 */
	void setUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Task UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the task.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Task UID</em>' attribute.
	 * @see #setTaskUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_TaskUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='TaskUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getTaskUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getTaskUID <em>Task UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task UID</em>' attribute.
	 * @see #getTaskUID()
	 * @generated
	 */
	void setTaskUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Resource UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the resource.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource UID</em>' attribute.
	 * @see #setResourceUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ResourceUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='ResourceUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getResourceUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getResourceUID <em>Resource UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource UID</em>' attribute.
	 * @see #getResourceUID()
	 * @generated
	 */
	void setResourceUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Percent Work Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of work completed on the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Percent Work Complete</em>' attribute.
	 * @see #setPercentWorkComplete(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_PercentWorkComplete()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='PercentWorkComplete' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPercentWorkComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getPercentWorkComplete <em>Percent Work Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percent Work Complete</em>' attribute.
	 * @see #getPercentWorkComplete()
	 * @generated
	 */
	void setPercentWorkComplete(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual cost incurred on the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Cost</em>' attribute.
	 * @see #setActualCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='ActualCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getActualCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualCost <em>Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Cost</em>' attribute.
	 * @see #getActualCost()
	 * @generated
	 */
	void setActualCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Actual Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual finish date of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Finish</em>' attribute.
	 * @see #setActualFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualFinish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ActualFinish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualFinish <em>Actual Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Finish</em>' attribute.
	 * @see #getActualFinish()
	 * @generated
	 */
	void setActualFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual overtime cost incurred on the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Cost</em>' attribute.
	 * @see #setActualOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualOvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='ActualOvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getActualOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeCost <em>Actual Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Cost</em>' attribute.
	 * @see #getActualOvertimeCost()
	 * @generated
	 */
	void setActualOvertimeCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Actual Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual amount of overtime work incurred on the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Overtime Work</em>' attribute.
	 * @see #setActualOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualOvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualOvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeWork <em>Actual Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Work</em>' attribute.
	 * @see #getActualOvertimeWork()
	 * @generated
	 */
	void setActualOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual start date of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Start</em>' attribute.
	 * @see #setActualStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualStart()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ActualStart' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualStart <em>Actual Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Start</em>' attribute.
	 * @see #getActualStart()
	 * @generated
	 */
	void setActualStart(Object value);

	/**
	 * Returns the value of the '<em><b>Actual Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual amount of work incurred on the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Work</em>' attribute.
	 * @see #setActualWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualWork <em>Actual Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Work</em>' attribute.
	 * @see #getActualWork()
	 * @generated
	 */
	void setActualWork(Object value);

	/**
	 * Returns the value of the '<em><b>ACWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual cost of work performed on the assignment to-date.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>ACWP</em>' attribute.
	 * @see #isSetACWP()
	 * @see #unsetACWP()
	 * @see #setACWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ACWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='ACWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getACWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getACWP <em>ACWP</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getACWP <em>ACWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetACWP()
	 * @see #getACWP()
	 * @see #setACWP(float)
	 * @generated
	 */
	void unsetACWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getACWP <em>ACWP</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Confirmed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the Resource has accepted all of his or her assignments.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Confirmed</em>' attribute.
	 * @see #isSetConfirmed()
	 * @see #unsetConfirmed()
	 * @see #setConfirmed(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Confirmed()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Confirmed' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isConfirmed();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isConfirmed <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confirmed</em>' attribute.
	 * @see #isSetConfirmed()
	 * @see #unsetConfirmed()
	 * @see #isConfirmed()
	 * @generated
	 */
	void setConfirmed(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isConfirmed <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfirmed()
	 * @see #isConfirmed()
	 * @see #setConfirmed(boolean)
	 * @generated
	 */
	void unsetConfirmed();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isConfirmed <em>Confirmed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Confirmed</em>' attribute is set.
	 * @see #unsetConfirmed()
	 * @see #isConfirmed()
	 * @see #setConfirmed(boolean)
	 * @generated
	 */
	boolean isSetConfirmed();

	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The projected or scheduled cost of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Cost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>Cost Rate Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The cost rate table used for the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost Rate Table</em>' attribute.
	 * @see #setCostRateTable(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_CostRateTable()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CostRateTableType"
	 *        extendedMetaData="kind='element' name='CostRateTable' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCostRateTable();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCostRateTable <em>Cost Rate Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Rate Table</em>' attribute.
	 * @see #getCostRateTable()
	 * @generated
	 */
	void setCostRateTable(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cost Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The difference between the cost and baseline cost for a resource.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost Variance</em>' attribute.
	 * @see #isSetCostVariance()
	 * @see #unsetCostVariance()
	 * @see #setCostVariance(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_CostVariance()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='CostVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	float getCostVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCostVariance <em>Cost Variance</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCostVariance <em>Cost Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCostVariance()
	 * @see #getCostVariance()
	 * @see #setCostVariance(float)
	 * @generated
	 */
	void unsetCostVariance();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getCostVariance <em>Cost Variance</em>}' attribute is set.
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
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_CV()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='CV' namespace='##targetNamespace'"
	 * @generated
	 */
	float getCV();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCV <em>CV</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCV <em>CV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCV()
	 * @see #getCV()
	 * @see #setCV(float)
	 * @generated
	 */
	void unsetCV();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getCV <em>CV</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount that the assignment is delayed.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Delay()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='Delay' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDelay();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	void setDelay(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled finish date of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish</em>' attribute.
	 * @see #setFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Finish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Finish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getFinish <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish</em>' attribute.
	 * @see #getFinish()
	 * @generated
	 */
	void setFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Finish Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variance of the assignment finish date from the baseline finish date.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish Variance</em>' attribute.
	 * @see #setFinishVariance(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_FinishVariance()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='FinishVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getFinishVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getFinishVariance <em>Finish Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Variance</em>' attribute.
	 * @see #getFinishVariance()
	 * @generated
	 */
	void setFinishVariance(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Hyperlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The title of the hyperlink associated with the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink</em>' attribute.
	 * @see #setHyperlink(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Hyperlink()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkType"
	 *        extendedMetaData="kind='element' name='Hyperlink' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlink();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getHyperlink <em>Hyperlink</em>}' attribute.
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
	 * The hyperlink associated with the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink Address</em>' attribute.
	 * @see #setHyperlinkAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_HyperlinkAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkAddressType"
	 *        extendedMetaData="kind='element' name='HyperlinkAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlinkAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getHyperlinkAddress <em>Hyperlink Address</em>}' attribute.
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
	 * The document bookmark of the hyperlink associated with the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperlink Sub Address</em>' attribute.
	 * @see #setHyperlinkSubAddress(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_HyperlinkSubAddress()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.HyperlinkSubAddressType1"
	 *        extendedMetaData="kind='element' name='HyperlinkSubAddress' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHyperlinkSubAddress();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hyperlink Sub Address</em>' attribute.
	 * @see #getHyperlinkSubAddress()
	 * @generated
	 */
	void setHyperlinkSubAddress(String value);

	/**
	 * Returns the value of the '<em><b>Work Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variance of assignment work from the baseline work as minutes x 1000.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work Variance</em>' attribute.
	 * @see #isSetWorkVariance()
	 * @see #unsetWorkVariance()
	 * @see #setWorkVariance(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_WorkVariance()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='WorkVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	float getWorkVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getWorkVariance <em>Work Variance</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getWorkVariance <em>Work Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWorkVariance()
	 * @see #getWorkVariance()
	 * @see #setWorkVariance(float)
	 * @generated
	 */
	void unsetWorkVariance();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getWorkVariance <em>Work Variance</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Has Fixed Rate Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the Units are Fixed Rate.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has Fixed Rate Units</em>' attribute.
	 * @see #isSetHasFixedRateUnits()
	 * @see #unsetHasFixedRateUnits()
	 * @see #setHasFixedRateUnits(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_HasFixedRateUnits()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='HasFixedRateUnits' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isHasFixedRateUnits();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isHasFixedRateUnits <em>Has Fixed Rate Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Fixed Rate Units</em>' attribute.
	 * @see #isSetHasFixedRateUnits()
	 * @see #unsetHasFixedRateUnits()
	 * @see #isHasFixedRateUnits()
	 * @generated
	 */
	void setHasFixedRateUnits(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isHasFixedRateUnits <em>Has Fixed Rate Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHasFixedRateUnits()
	 * @see #isHasFixedRateUnits()
	 * @see #setHasFixedRateUnits(boolean)
	 * @generated
	 */
	void unsetHasFixedRateUnits();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isHasFixedRateUnits <em>Has Fixed Rate Units</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Has Fixed Rate Units</em>' attribute is set.
	 * @see #unsetHasFixedRateUnits()
	 * @see #isHasFixedRateUnits()
	 * @see #setHasFixedRateUnits(boolean)
	 * @generated
	 */
	boolean isSetHasFixedRateUnits();

	/**
	 * Returns the value of the '<em><b>Fixed Material</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the consumption of the assigned material resource occurs in a single, fixed amount.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fixed Material</em>' attribute.
	 * @see #isSetFixedMaterial()
	 * @see #unsetFixedMaterial()
	 * @see #setFixedMaterial(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_FixedMaterial()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='FixedMaterial' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isFixedMaterial();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isFixedMaterial <em>Fixed Material</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed Material</em>' attribute.
	 * @see #isSetFixedMaterial()
	 * @see #unsetFixedMaterial()
	 * @see #isFixedMaterial()
	 * @generated
	 */
	void setFixedMaterial(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isFixedMaterial <em>Fixed Material</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFixedMaterial()
	 * @see #isFixedMaterial()
	 * @see #setFixedMaterial(boolean)
	 * @generated
	 */
	void unsetFixedMaterial();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isFixedMaterial <em>Fixed Material</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fixed Material</em>' attribute is set.
	 * @see #unsetFixedMaterial()
	 * @see #isFixedMaterial()
	 * @see #setFixedMaterial(boolean)
	 * @generated
	 */
	boolean isSetFixedMaterial();

	/**
	 * Returns the value of the '<em><b>Leveling Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The delay caused by leveling.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leveling Delay</em>' attribute.
	 * @see #setLevelingDelay(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_LevelingDelay()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='LevelingDelay' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLevelingDelay();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getLevelingDelay <em>Leveling Delay</em>}' attribute.
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
	 *                             Values are: 3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 19=%, 20=e%, 21=null, 
	 *                             35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 42=ew?, 43=mo?, 44=emo?, 51=%?, 52=e%? and 53=null.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Leveling Delay Format</em>' attribute.
	 * @see #setLevelingDelayFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_LevelingDelayFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.LevelingDelayFormatType"
	 *        extendedMetaData="kind='element' name='LevelingDelayFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLevelingDelayFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getLevelingDelayFormat <em>Leveling Delay Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leveling Delay Format</em>' attribute.
	 * @see #getLevelingDelayFormat()
	 * @generated
	 */
	void setLevelingDelayFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Linked Fields</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the Project is linked to another OLE object.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Linked Fields</em>' attribute.
	 * @see #isSetLinkedFields()
	 * @see #unsetLinkedFields()
	 * @see #setLinkedFields(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_LinkedFields()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='LinkedFields' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isLinkedFields();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isLinkedFields <em>Linked Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Fields</em>' attribute.
	 * @see #isSetLinkedFields()
	 * @see #unsetLinkedFields()
	 * @see #isLinkedFields()
	 * @generated
	 */
	void setLinkedFields(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isLinkedFields <em>Linked Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLinkedFields()
	 * @see #isLinkedFields()
	 * @see #setLinkedFields(boolean)
	 * @generated
	 */
	void unsetLinkedFields();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isLinkedFields <em>Linked Fields</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Linked Fields</em>' attribute is set.
	 * @see #unsetLinkedFields()
	 * @see #isLinkedFields()
	 * @see #setLinkedFields(boolean)
	 * @generated
	 */
	boolean isSetLinkedFields();

	/**
	 * Returns the value of the '<em><b>Milestone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the assignment is a milestone.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Milestone</em>' attribute.
	 * @see #isSetMilestone()
	 * @see #unsetMilestone()
	 * @see #setMilestone(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Milestone()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Milestone' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMilestone();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isMilestone <em>Milestone</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isMilestone <em>Milestone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMilestone()
	 * @see #isMilestone()
	 * @see #setMilestone(boolean)
	 * @generated
	 */
	void unsetMilestone();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isMilestone <em>Milestone</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text notes associated with the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notes</em>' attribute.
	 * @see #setNotes(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Notes()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Notes' namespace='##targetNamespace'"
	 * @generated
	 */
	String getNotes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getNotes <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notes</em>' attribute.
	 * @see #getNotes()
	 * @generated
	 */
	void setNotes(String value);

	/**
	 * Returns the value of the '<em><b>Overallocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the assignment is overallocated.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overallocated</em>' attribute.
	 * @see #isSetOverallocated()
	 * @see #unsetOverallocated()
	 * @see #setOverallocated(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Overallocated()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Overallocated' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isOverallocated();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isOverallocated <em>Overallocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overallocated</em>' attribute.
	 * @see #isSetOverallocated()
	 * @see #unsetOverallocated()
	 * @see #isOverallocated()
	 * @generated
	 */
	void setOverallocated(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isOverallocated <em>Overallocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOverallocated()
	 * @see #isOverallocated()
	 * @see #setOverallocated(boolean)
	 * @generated
	 */
	void unsetOverallocated();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isOverallocated <em>Overallocated</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Overallocated</em>' attribute is set.
	 * @see #unsetOverallocated()
	 * @see #isOverallocated()
	 * @see #setOverallocated(boolean)
	 * @generated
	 */
	boolean isSetOverallocated();

	/**
	 * Returns the value of the '<em><b>Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The sum of the actual and remaining overtime cost of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Cost</em>' attribute.
	 * @see #setOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_OvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='OvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getOvertimeCost <em>Overtime Cost</em>}' attribute.
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
	 * The scheduled overtime work scheduled for the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overtime Work</em>' attribute.
	 * @see #setOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_OvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='OvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getOvertimeWork <em>Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overtime Work</em>' attribute.
	 * @see #getOvertimeWork()
	 * @generated
	 */
	void setOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Regular Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of non-overtime work scheduled for the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Regular Work</em>' attribute.
	 * @see #setRegularWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_RegularWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RegularWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRegularWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getRegularWork <em>Regular Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regular Work</em>' attribute.
	 * @see #getRegularWork()
	 * @generated
	 */
	void setRegularWork(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining projected cost of completing the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Cost</em>' attribute.
	 * @see #setRemainingCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_RemainingCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='RemainingCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getRemainingCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getRemainingCost <em>Remaining Cost</em>}' attribute.
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
	 * The remaining projected overtime cost of completing the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Overtime Cost</em>' attribute.
	 * @see #setRemainingOvertimeCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_RemainingOvertimeCost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='RemainingOvertimeCost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getRemainingOvertimeCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}' attribute.
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
	 * The remaining overtime work scheduled to complete the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Overtime Work</em>' attribute.
	 * @see #setRemainingOvertimeWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_RemainingOvertimeWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingOvertimeWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingOvertimeWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Overtime Work</em>' attribute.
	 * @see #getRemainingOvertimeWork()
	 * @generated
	 */
	void setRemainingOvertimeWork(Object value);

	/**
	 * Returns the value of the '<em><b>Remaining Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The remaining work scheduled to complete the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remaining Work</em>' attribute.
	 * @see #setRemainingWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_RemainingWork()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='RemainingWork' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getRemainingWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getRemainingWork <em>Remaining Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remaining Work</em>' attribute.
	 * @see #getRemainingWork()
	 * @generated
	 */
	void setRemainingWork(Object value);

	/**
	 * Returns the value of the '<em><b>Response Pending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if a response has not been received for a TeamAssign message. 
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Response Pending</em>' attribute.
	 * @see #isSetResponsePending()
	 * @see #unsetResponsePending()
	 * @see #setResponsePending(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ResponsePending()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ResponsePending' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isResponsePending();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isResponsePending <em>Response Pending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response Pending</em>' attribute.
	 * @see #isSetResponsePending()
	 * @see #unsetResponsePending()
	 * @see #isResponsePending()
	 * @generated
	 */
	void setResponsePending(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isResponsePending <em>Response Pending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResponsePending()
	 * @see #isResponsePending()
	 * @see #setResponsePending(boolean)
	 * @generated
	 */
	void unsetResponsePending();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isResponsePending <em>Response Pending</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Response Pending</em>' attribute is set.
	 * @see #unsetResponsePending()
	 * @see #isResponsePending()
	 * @see #setResponsePending(boolean)
	 * @generated
	 */
	boolean isSetResponsePending();

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled start date of the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Start()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Start' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Object value);

	/**
	 * Returns the value of the '<em><b>Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the assignment was stopped.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stop</em>' attribute.
	 * @see #setStop(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Stop()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Stop' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStop();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getStop <em>Stop</em>}' attribute.
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
	 * The date that the assignment resumed.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resume</em>' attribute.
	 * @see #setResume(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Resume()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Resume' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getResume();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getResume <em>Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resume</em>' attribute.
	 * @see #getResume()
	 * @generated
	 */
	void setResume(Object value);

	/**
	 * Returns the value of the '<em><b>Start Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The variance of the assignment start date from the baseline start date.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Variance</em>' attribute.
	 * @see #setStartVariance(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_StartVariance()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='StartVariance' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getStartVariance();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getStartVariance <em>Start Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Variance</em>' attribute.
	 * @see #getStartVariance()
	 * @generated
	 */
	void setStartVariance(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of units for the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Units</em>' attribute.
	 * @see #isSetUnits()
	 * @see #unsetUnits()
	 * @see #setUnits(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Units()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='Units' namespace='##targetNamespace'"
	 * @generated
	 */
	float getUnits();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getUnits <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Units</em>' attribute.
	 * @see #isSetUnits()
	 * @see #unsetUnits()
	 * @see #getUnits()
	 * @generated
	 */
	void setUnits(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getUnits <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnits()
	 * @see #getUnits()
	 * @see #setUnits(float)
	 * @generated
	 */
	void unsetUnits();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getUnits <em>Units</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Units</em>' attribute is set.
	 * @see #unsetUnits()
	 * @see #getUnits()
	 * @see #setUnits(float)
	 * @generated
	 */
	boolean isSetUnits();

	/**
	 * Returns the value of the '<em><b>Update Needed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if the resource assigned to a task needs to be updated as to the status of the task.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Update Needed</em>' attribute.
	 * @see #isSetUpdateNeeded()
	 * @see #unsetUpdateNeeded()
	 * @see #setUpdateNeeded(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_UpdateNeeded()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='UpdateNeeded' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isUpdateNeeded();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#isUpdateNeeded <em>Update Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Needed</em>' attribute.
	 * @see #isSetUpdateNeeded()
	 * @see #unsetUpdateNeeded()
	 * @see #isUpdateNeeded()
	 * @generated
	 */
	void setUpdateNeeded(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#isUpdateNeeded <em>Update Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUpdateNeeded()
	 * @see #isUpdateNeeded()
	 * @see #setUpdateNeeded(boolean)
	 * @generated
	 */
	void unsetUpdateNeeded();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#isUpdateNeeded <em>Update Needed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Update Needed</em>' attribute is set.
	 * @see #unsetUpdateNeeded()
	 * @see #isUpdateNeeded()
	 * @see #setUpdateNeeded(boolean)
	 * @generated
	 */
	boolean isSetUpdateNeeded();

	/**
	 * Returns the value of the '<em><b>VAC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The difference between basline cost and total cost.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>VAC</em>' attribute.
	 * @see #isSetVAC()
	 * @see #unsetVAC()
	 * @see #setVAC(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_VAC()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='VAC' namespace='##targetNamespace'"
	 * @generated
	 */
	float getVAC();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getVAC <em>VAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>VAC</em>' attribute.
	 * @see #isSetVAC()
	 * @see #unsetVAC()
	 * @see #getVAC()
	 * @generated
	 */
	void setVAC(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getVAC <em>VAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVAC()
	 * @see #getVAC()
	 * @see #setVAC(float)
	 * @generated
	 */
	void unsetVAC();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getVAC <em>VAC</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>VAC</em>' attribute is set.
	 * @see #unsetVAC()
	 * @see #getVAC()
	 * @see #setVAC(float)
	 * @generated
	 */
	boolean isSetVAC();

	/**
	 * Returns the value of the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of scheduled work for the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work</em>' attribute.
	 * @see #setWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Work()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Work' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getWork <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work</em>' attribute.
	 * @see #getWork()
	 * @generated
	 */
	void setWork(Object value);

	/**
	 * Returns the value of the '<em><b>Work Contour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The work contour of the assignment. 
	 *                             Values are: 0=Flat, 1=Back Loaded, 2=Front Loaded, 3=Double Peak, 4=Early Peak, 5=Late Peak, 
	 *                             6=Bell, 7=Turtle, 8=Contoured
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work Contour</em>' attribute.
	 * @see #setWorkContour(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_WorkContour()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.WorkContourType"
	 *        extendedMetaData="kind='element' name='WorkContour' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getWorkContour();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getWorkContour <em>Work Contour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Contour</em>' attribute.
	 * @see #getWorkContour()
	 * @generated
	 */
	void setWorkContour(BigInteger value);

	/**
	 * Returns the value of the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budgeted cost of work on the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #setBCWS(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_BCWS()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWS' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWS();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getBCWS <em>BCWS</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	void unsetBCWS();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getBCWS <em>BCWS</em>}' attribute is set.
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
	 * The budgeted cost of work performed on the assignment to-date.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #setBCWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_BCWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getBCWP <em>BCWP</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Assignment#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	void unsetBCWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Assignment#getBCWP <em>BCWP</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Booking Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the booking type of the assignment. 1=Commited, 2=Proposed
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Booking Type</em>' attribute.
	 * @see #setBookingType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_BookingType()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.BookingTypeType"
	 *        extendedMetaData="kind='element' name='BookingType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getBookingType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getBookingType <em>Booking Type</em>}' attribute.
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
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualWorkProtected()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualWorkProtected' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualWorkProtected();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualWorkProtected <em>Actual Work Protected</em>}' attribute.
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
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ActualOvertimeWorkProtected()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='ActualOvertimeWorkProtected' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getActualOvertimeWorkProtected();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Overtime Work Protected</em>' attribute.
	 * @see #getActualOvertimeWorkProtected()
	 * @generated
	 */
	void setActualOvertimeWorkProtected(Object value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the assignment was created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_CreationDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='CreationDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getCreationDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Assignment#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(Object value);

	/**
	 * Returns the value of the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.ExtendedAttribute4}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of an extended attribute.  Two pieces of data are necessary - 
	 *                             a pointer back to the extended attribute table which is specified either by the unique ID or the 
	 *                             Field ID, and the value which is specified either with the value, or a pointer back to the value list.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Attribute</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_ExtendedAttribute()
	 * @model type="org.eclipse.epf.msproject.ExtendedAttribute4" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='ExtendedAttribute' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getExtendedAttribute();

	/**
	 * Returns the value of the '<em><b>Baseline</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.Baseline3}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of baseline values associated with the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Baseline</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_Baseline()
	 * @model type="org.eclipse.epf.msproject.Baseline3" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Baseline' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getBaseline();

	/**
	 * Returns the value of the '<em><b>Timephased Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.TimephasedDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time phased data associated with the assignment.
	 *                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timephased Data</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getAssignment_TimephasedData()
	 * @model type="org.eclipse.epf.msproject.TimephasedDataType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='TimephasedData' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getTimephasedData();

} // Assignment
