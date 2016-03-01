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
package org.eclipse.epf.msproject.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.msproject.Baseline;
import org.eclipse.epf.msproject.ExtendedAttribute2;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.OutlineCode2;
import org.eclipse.epf.msproject.PredecessorLink;
import org.eclipse.epf.msproject.Task;
import org.eclipse.epf.msproject.TimephasedDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isIsNull <em>Is Null</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getCreateDate <em>Create Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getContact <em>Contact</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getWBS <em>WBS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getWBSLevel <em>WBS Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getOutlineNumber <em>Outline Number</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getOutlineLevel <em>Outline Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getDurationFormat <em>Duration Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getStop <em>Stop</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getResume <em>Resume</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isResumeValid <em>Resume Valid</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isEffortDriven <em>Effort Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isRecurring <em>Recurring</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isOverAllocated <em>Over Allocated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isEstimated <em>Estimated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isSummary <em>Summary</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isCritical <em>Critical</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isIsSubproject <em>Is Subproject</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isIsSubprojectReadOnly <em>Is Subproject Read Only</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getSubprojectName <em>Subproject Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isExternalTask <em>External Task</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getExternalTaskProject <em>External Task Project</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getEarlyStart <em>Early Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getEarlyFinish <em>Early Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getLateStart <em>Late Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getLateFinish <em>Late Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getStartVariance <em>Start Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getFinishVariance <em>Finish Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getWorkVariance <em>Work Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getFreeSlack <em>Free Slack</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getTotalSlack <em>Total Slack</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getFixedCost <em>Fixed Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getFixedCostAccrual <em>Fixed Cost Accrual</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPercentComplete <em>Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPercentWorkComplete <em>Percent Work Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getOvertimeCost <em>Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getOvertimeWork <em>Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualStart <em>Actual Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualFinish <em>Actual Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualDuration <em>Actual Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualCost <em>Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualOvertimeCost <em>Actual Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualWork <em>Actual Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualOvertimeWork <em>Actual Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getRegularWork <em>Regular Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getRemainingDuration <em>Remaining Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getRemainingCost <em>Remaining Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getRemainingWork <em>Remaining Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getACWP <em>ACWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getCV <em>CV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getCalendarUID <em>Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getConstraintDate <em>Constraint Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getDeadline <em>Deadline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isLevelAssignments <em>Level Assignments</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isLevelingCanSplit <em>Leveling Can Split</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getLevelingDelay <em>Leveling Delay</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getLevelingDelayFormat <em>Leveling Delay Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPreLeveledStart <em>Pre Leveled Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPreLeveledFinish <em>Pre Leveled Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getHyperlink <em>Hyperlink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getHyperlinkAddress <em>Hyperlink Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isIgnoreResourceCalendar <em>Ignore Resource Calendar</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isHideBar <em>Hide Bar</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#isRollup <em>Rollup</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getBCWP <em>BCWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPhysicalPercentComplete <em>Physical Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getEarnedValueMethod <em>Earned Value Method</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getPredecessorLink <em>Predecessor Link</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualWorkProtected <em>Actual Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getBaseline <em>Baseline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getOutlineCode <em>Outline Code</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.TaskImpl#getTimephasedData <em>Timephased Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskImpl extends EObjectImpl implements Task {
	/**
	 * The default value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger uID = UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger iD = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNull() <em>Is Null</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNull()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NULL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNull() <em>Is Null</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNull()
	 * @generated
	 * @ordered
	 */
	protected boolean isNull = IS_NULL_EDEFAULT;

	/**
	 * This is true if the Is Null attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isNullESet = false;

	/**
	 * The default value of the '{@link #getCreateDate() <em>Create Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object CREATE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreateDate() <em>Create Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateDate()
	 * @generated
	 * @ordered
	 */
	protected Object createDate = CREATE_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getContact() <em>Contact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContact()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContact() <em>Contact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContact()
	 * @generated
	 * @ordered
	 */
	protected String contact = CONTACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWBS() <em>WBS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWBS()
	 * @generated
	 * @ordered
	 */
	protected static final String WBS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWBS() <em>WBS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWBS()
	 * @generated
	 * @ordered
	 */
	protected String wBS = WBS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWBSLevel() <em>WBS Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWBSLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String WBS_LEVEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWBSLevel() <em>WBS Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWBSLevel()
	 * @generated
	 * @ordered
	 */
	protected String wBSLevel = WBS_LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutlineNumber() <em>Outline Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutlineNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTLINE_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutlineNumber() <em>Outline Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutlineNumber()
	 * @generated
	 * @ordered
	 */
	protected String outlineNumber = OUTLINE_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutlineLevel() <em>Outline Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutlineLevel()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger OUTLINE_LEVEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutlineLevel() <em>Outline Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutlineLevel()
	 * @generated
	 * @ordered
	 */
	protected BigInteger outlineLevel = OUTLINE_LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected BigInteger priority = PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Object start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinish() <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinish()
	 * @generated
	 * @ordered
	 */
	protected static final Object FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinish() <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinish()
	 * @generated
	 * @ordered
	 */
	protected Object finish = FINISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final Object DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected Object duration = DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDurationFormat() <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DURATION_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDurationFormat() <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDurationFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger durationFormat = DURATION_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWork() <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWork() <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected Object work = WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getStop() <em>Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStop()
	 * @generated
	 * @ordered
	 */
	protected static final Object STOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStop() <em>Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStop()
	 * @generated
	 * @ordered
	 */
	protected Object stop = STOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getResume() <em>Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResume()
	 * @generated
	 * @ordered
	 */
	protected static final Object RESUME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResume() <em>Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResume()
	 * @generated
	 * @ordered
	 */
	protected Object resume = RESUME_EDEFAULT;

	/**
	 * The default value of the '{@link #isResumeValid() <em>Resume Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResumeValid()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESUME_VALID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isResumeValid() <em>Resume Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResumeValid()
	 * @generated
	 * @ordered
	 */
	protected boolean resumeValid = RESUME_VALID_EDEFAULT;

	/**
	 * This is true if the Resume Valid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resumeValidESet = false;

	/**
	 * The default value of the '{@link #isEffortDriven() <em>Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEffortDriven()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EFFORT_DRIVEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEffortDriven() <em>Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEffortDriven()
	 * @generated
	 * @ordered
	 */
	protected boolean effortDriven = EFFORT_DRIVEN_EDEFAULT;

	/**
	 * This is true if the Effort Driven attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean effortDrivenESet = false;

	/**
	 * The default value of the '{@link #isRecurring() <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecurring()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECURRING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRecurring() <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecurring()
	 * @generated
	 * @ordered
	 */
	protected boolean recurring = RECURRING_EDEFAULT;

	/**
	 * This is true if the Recurring attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean recurringESet = false;

	/**
	 * The default value of the '{@link #isOverAllocated() <em>Over Allocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverAllocated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVER_ALLOCATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverAllocated() <em>Over Allocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverAllocated()
	 * @generated
	 * @ordered
	 */
	protected boolean overAllocated = OVER_ALLOCATED_EDEFAULT;

	/**
	 * This is true if the Over Allocated attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean overAllocatedESet = false;

	/**
	 * The default value of the '{@link #isEstimated() <em>Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEstimated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ESTIMATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEstimated() <em>Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEstimated()
	 * @generated
	 * @ordered
	 */
	protected boolean estimated = ESTIMATED_EDEFAULT;

	/**
	 * This is true if the Estimated attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean estimatedESet = false;

	/**
	 * The default value of the '{@link #isMilestone() <em>Milestone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMilestone()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MILESTONE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMilestone() <em>Milestone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMilestone()
	 * @generated
	 * @ordered
	 */
	protected boolean milestone = MILESTONE_EDEFAULT;

	/**
	 * This is true if the Milestone attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean milestoneESet = false;

	/**
	 * The default value of the '{@link #isSummary() <em>Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSummary()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUMMARY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSummary() <em>Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSummary()
	 * @generated
	 * @ordered
	 */
	protected boolean summary = SUMMARY_EDEFAULT;

	/**
	 * This is true if the Summary attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean summaryESet = false;

	/**
	 * The default value of the '{@link #isCritical() <em>Critical</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCritical()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CRITICAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCritical() <em>Critical</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCritical()
	 * @generated
	 * @ordered
	 */
	protected boolean critical = CRITICAL_EDEFAULT;

	/**
	 * This is true if the Critical attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean criticalESet = false;

	/**
	 * The default value of the '{@link #isIsSubproject() <em>Is Subproject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubproject()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SUBPROJECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSubproject() <em>Is Subproject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubproject()
	 * @generated
	 * @ordered
	 */
	protected boolean isSubproject = IS_SUBPROJECT_EDEFAULT;

	/**
	 * This is true if the Is Subproject attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isSubprojectESet = false;

	/**
	 * The default value of the '{@link #isIsSubprojectReadOnly() <em>Is Subproject Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubprojectReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SUBPROJECT_READ_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSubprojectReadOnly() <em>Is Subproject Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubprojectReadOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean isSubprojectReadOnly = IS_SUBPROJECT_READ_ONLY_EDEFAULT;

	/**
	 * This is true if the Is Subproject Read Only attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isSubprojectReadOnlyESet = false;

	/**
	 * The default value of the '{@link #getSubprojectName() <em>Subproject Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubprojectName()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBPROJECT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubprojectName() <em>Subproject Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubprojectName()
	 * @generated
	 * @ordered
	 */
	protected String subprojectName = SUBPROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isExternalTask() <em>External Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExternalTask()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTERNAL_TASK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExternalTask() <em>External Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExternalTask()
	 * @generated
	 * @ordered
	 */
	protected boolean externalTask = EXTERNAL_TASK_EDEFAULT;

	/**
	 * This is true if the External Task attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean externalTaskESet = false;

	/**
	 * The default value of the '{@link #getExternalTaskProject() <em>External Task Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalTaskProject()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTERNAL_TASK_PROJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExternalTaskProject() <em>External Task Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalTaskProject()
	 * @generated
	 * @ordered
	 */
	protected String externalTaskProject = EXTERNAL_TASK_PROJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEarlyStart() <em>Early Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEarlyStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object EARLY_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEarlyStart() <em>Early Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEarlyStart()
	 * @generated
	 * @ordered
	 */
	protected Object earlyStart = EARLY_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getEarlyFinish() <em>Early Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEarlyFinish()
	 * @generated
	 * @ordered
	 */
	protected static final Object EARLY_FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEarlyFinish() <em>Early Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEarlyFinish()
	 * @generated
	 * @ordered
	 */
	protected Object earlyFinish = EARLY_FINISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLateStart() <em>Late Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLateStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object LATE_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLateStart() <em>Late Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLateStart()
	 * @generated
	 * @ordered
	 */
	protected Object lateStart = LATE_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getLateFinish() <em>Late Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLateFinish()
	 * @generated
	 * @ordered
	 */
	protected static final Object LATE_FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLateFinish() <em>Late Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLateFinish()
	 * @generated
	 * @ordered
	 */
	protected Object lateFinish = LATE_FINISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartVariance() <em>Start Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartVariance()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger START_VARIANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartVariance() <em>Start Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartVariance()
	 * @generated
	 * @ordered
	 */
	protected BigInteger startVariance = START_VARIANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinishVariance() <em>Finish Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishVariance()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger FINISH_VARIANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinishVariance() <em>Finish Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishVariance()
	 * @generated
	 * @ordered
	 */
	protected BigInteger finishVariance = FINISH_VARIANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkVariance() <em>Work Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkVariance()
	 * @generated
	 * @ordered
	 */
	protected static final float WORK_VARIANCE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getWorkVariance() <em>Work Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkVariance()
	 * @generated
	 * @ordered
	 */
	protected float workVariance = WORK_VARIANCE_EDEFAULT;

	/**
	 * This is true if the Work Variance attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean workVarianceESet = false;

	/**
	 * The default value of the '{@link #getFreeSlack() <em>Free Slack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFreeSlack()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger FREE_SLACK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFreeSlack() <em>Free Slack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFreeSlack()
	 * @generated
	 * @ordered
	 */
	protected BigInteger freeSlack = FREE_SLACK_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalSlack() <em>Total Slack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalSlack()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger TOTAL_SLACK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTotalSlack() <em>Total Slack</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalSlack()
	 * @generated
	 * @ordered
	 */
	protected BigInteger totalSlack = TOTAL_SLACK_EDEFAULT;

	/**
	 * The default value of the '{@link #getFixedCost() <em>Fixed Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixedCost()
	 * @generated
	 * @ordered
	 */
	protected static final float FIXED_COST_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFixedCost() <em>Fixed Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixedCost()
	 * @generated
	 * @ordered
	 */
	protected float fixedCost = FIXED_COST_EDEFAULT;

	/**
	 * This is true if the Fixed Cost attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fixedCostESet = false;

	/**
	 * The default value of the '{@link #getFixedCostAccrual() <em>Fixed Cost Accrual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixedCostAccrual()
	 * @generated
	 * @ordered
	 */
	protected static final String FIXED_COST_ACCRUAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFixedCostAccrual() <em>Fixed Cost Accrual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFixedCostAccrual()
	 * @generated
	 * @ordered
	 */
	protected String fixedCostAccrual = FIXED_COST_ACCRUAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPercentComplete() <em>Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentComplete()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PERCENT_COMPLETE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPercentComplete() <em>Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentComplete()
	 * @generated
	 * @ordered
	 */
	protected BigInteger percentComplete = PERCENT_COMPLETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPercentWorkComplete() <em>Percent Work Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentWorkComplete()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PERCENT_WORK_COMPLETE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPercentWorkComplete() <em>Percent Work Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentWorkComplete()
	 * @generated
	 * @ordered
	 */
	protected BigInteger percentWorkComplete = PERCENT_WORK_COMPLETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal cost = COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getOvertimeCost() <em>Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal OVERTIME_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOvertimeCost() <em>Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal overtimeCost = OVERTIME_COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getOvertimeWork() <em>Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object OVERTIME_WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOvertimeWork() <em>Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeWork()
	 * @generated
	 * @ordered
	 */
	protected Object overtimeWork = OVERTIME_WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualStart() <em>Actual Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualStart() <em>Actual Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualStart()
	 * @generated
	 * @ordered
	 */
	protected Object actualStart = ACTUAL_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualFinish() <em>Actual Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualFinish()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualFinish() <em>Actual Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualFinish()
	 * @generated
	 * @ordered
	 */
	protected Object actualFinish = ACTUAL_FINISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualDuration() <em>Actual Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualDuration()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualDuration() <em>Actual Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualDuration()
	 * @generated
	 * @ordered
	 */
	protected Object actualDuration = ACTUAL_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualCost() <em>Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal ACTUAL_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualCost() <em>Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal actualCost = ACTUAL_COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualOvertimeCost() <em>Actual Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualOvertimeCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal ACTUAL_OVERTIME_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualOvertimeCost() <em>Actual Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualOvertimeCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal actualOvertimeCost = ACTUAL_OVERTIME_COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualWork() <em>Actual Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualWork() <em>Actual Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualWork()
	 * @generated
	 * @ordered
	 */
	protected Object actualWork = ACTUAL_WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualOvertimeWork() <em>Actual Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualOvertimeWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_OVERTIME_WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualOvertimeWork() <em>Actual Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualOvertimeWork()
	 * @generated
	 * @ordered
	 */
	protected Object actualOvertimeWork = ACTUAL_OVERTIME_WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getRegularWork() <em>Regular Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegularWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object REGULAR_WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRegularWork() <em>Regular Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegularWork()
	 * @generated
	 * @ordered
	 */
	protected Object regularWork = REGULAR_WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemainingDuration() <em>Remaining Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final Object REMAINING_DURATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemainingDuration() <em>Remaining Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingDuration()
	 * @generated
	 * @ordered
	 */
	protected Object remainingDuration = REMAINING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemainingCost() <em>Remaining Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal REMAINING_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemainingCost() <em>Remaining Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal remainingCost = REMAINING_COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemainingWork() <em>Remaining Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object REMAINING_WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemainingWork() <em>Remaining Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingWork()
	 * @generated
	 * @ordered
	 */
	protected Object remainingWork = REMAINING_WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemainingOvertimeCost() <em>Remaining Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingOvertimeCost()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal REMAINING_OVERTIME_COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemainingOvertimeCost() <em>Remaining Overtime Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingOvertimeCost()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal remainingOvertimeCost = REMAINING_OVERTIME_COST_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemainingOvertimeWork() <em>Remaining Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingOvertimeWork()
	 * @generated
	 * @ordered
	 */
	protected static final Object REMAINING_OVERTIME_WORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemainingOvertimeWork() <em>Remaining Overtime Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemainingOvertimeWork()
	 * @generated
	 * @ordered
	 */
	protected Object remainingOvertimeWork = REMAINING_OVERTIME_WORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getACWP() <em>ACWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getACWP()
	 * @generated
	 * @ordered
	 */
	protected static final float ACWP_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getACWP() <em>ACWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getACWP()
	 * @generated
	 * @ordered
	 */
	protected float aCWP = ACWP_EDEFAULT;

	/**
	 * This is true if the ACWP attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean aCWPESet = false;

	/**
	 * The default value of the '{@link #getCV() <em>CV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCV()
	 * @generated
	 * @ordered
	 */
	protected static final float CV_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getCV() <em>CV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCV()
	 * @generated
	 * @ordered
	 */
	protected float cV = CV_EDEFAULT;

	/**
	 * This is true if the CV attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean cVESet = false;

	/**
	 * The default value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CONSTRAINT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstraintType() <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger constraintType = CONSTRAINT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCalendarUID() <em>Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalendarUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CALENDAR_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCalendarUID() <em>Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalendarUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger calendarUID = CALENDAR_UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConstraintDate() <em>Constraint Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object CONSTRAINT_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstraintDate() <em>Constraint Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintDate()
	 * @generated
	 * @ordered
	 */
	protected Object constraintDate = CONSTRAINT_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeadline() <em>Deadline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadline()
	 * @generated
	 * @ordered
	 */
	protected static final Object DEADLINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeadline() <em>Deadline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadline()
	 * @generated
	 * @ordered
	 */
	protected Object deadline = DEADLINE_EDEFAULT;

	/**
	 * The default value of the '{@link #isLevelAssignments() <em>Level Assignments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLevelAssignments()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LEVEL_ASSIGNMENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLevelAssignments() <em>Level Assignments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLevelAssignments()
	 * @generated
	 * @ordered
	 */
	protected boolean levelAssignments = LEVEL_ASSIGNMENTS_EDEFAULT;

	/**
	 * This is true if the Level Assignments attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean levelAssignmentsESet = false;

	/**
	 * The default value of the '{@link #isLevelingCanSplit() <em>Leveling Can Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLevelingCanSplit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LEVELING_CAN_SPLIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLevelingCanSplit() <em>Leveling Can Split</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLevelingCanSplit()
	 * @generated
	 * @ordered
	 */
	protected boolean levelingCanSplit = LEVELING_CAN_SPLIT_EDEFAULT;

	/**
	 * This is true if the Leveling Can Split attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean levelingCanSplitESet = false;

	/**
	 * The default value of the '{@link #getLevelingDelay() <em>Leveling Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelingDelay()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger LEVELING_DELAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLevelingDelay() <em>Leveling Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelingDelay()
	 * @generated
	 * @ordered
	 */
	protected BigInteger levelingDelay = LEVELING_DELAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getLevelingDelayFormat() <em>Leveling Delay Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelingDelayFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger LEVELING_DELAY_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLevelingDelayFormat() <em>Leveling Delay Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevelingDelayFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger levelingDelayFormat = LEVELING_DELAY_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreLeveledStart() <em>Pre Leveled Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreLeveledStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object PRE_LEVELED_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreLeveledStart() <em>Pre Leveled Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreLeveledStart()
	 * @generated
	 * @ordered
	 */
	protected Object preLeveledStart = PRE_LEVELED_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreLeveledFinish() <em>Pre Leveled Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreLeveledFinish()
	 * @generated
	 * @ordered
	 */
	protected static final Object PRE_LEVELED_FINISH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreLeveledFinish() <em>Pre Leveled Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreLeveledFinish()
	 * @generated
	 * @ordered
	 */
	protected Object preLeveledFinish = PRE_LEVELED_FINISH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHyperlink() <em>Hyperlink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlink()
	 * @generated
	 * @ordered
	 */
	protected static final String HYPERLINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHyperlink() <em>Hyperlink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlink()
	 * @generated
	 * @ordered
	 */
	protected String hyperlink = HYPERLINK_EDEFAULT;

	/**
	 * The default value of the '{@link #getHyperlinkAddress() <em>Hyperlink Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlinkAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String HYPERLINK_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHyperlinkAddress() <em>Hyperlink Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlinkAddress()
	 * @generated
	 * @ordered
	 */
	protected String hyperlinkAddress = HYPERLINK_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getHyperlinkSubAddress() <em>Hyperlink Sub Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlinkSubAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String HYPERLINK_SUB_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHyperlinkSubAddress() <em>Hyperlink Sub Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlinkSubAddress()
	 * @generated
	 * @ordered
	 */
	protected String hyperlinkSubAddress = HYPERLINK_SUB_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #isIgnoreResourceCalendar() <em>Ignore Resource Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreResourceCalendar()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IGNORE_RESOURCE_CALENDAR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIgnoreResourceCalendar() <em>Ignore Resource Calendar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreResourceCalendar()
	 * @generated
	 * @ordered
	 */
	protected boolean ignoreResourceCalendar = IGNORE_RESOURCE_CALENDAR_EDEFAULT;

	/**
	 * This is true if the Ignore Resource Calendar attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ignoreResourceCalendarESet = false;

	/**
	 * The default value of the '{@link #getNotes() <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotes() <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected String notes = NOTES_EDEFAULT;

	/**
	 * The default value of the '{@link #isHideBar() <em>Hide Bar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHideBar()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDE_BAR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHideBar() <em>Hide Bar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHideBar()
	 * @generated
	 * @ordered
	 */
	protected boolean hideBar = HIDE_BAR_EDEFAULT;

	/**
	 * This is true if the Hide Bar attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hideBarESet = false;

	/**
	 * The default value of the '{@link #isRollup() <em>Rollup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRollup()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROLLUP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRollup() <em>Rollup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRollup()
	 * @generated
	 * @ordered
	 */
	protected boolean rollup = ROLLUP_EDEFAULT;

	/**
	 * This is true if the Rollup attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean rollupESet = false;

	/**
	 * The default value of the '{@link #getBCWS() <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWS()
	 * @generated
	 * @ordered
	 */
	protected static final float BCWS_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getBCWS() <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWS()
	 * @generated
	 * @ordered
	 */
	protected float bCWS = BCWS_EDEFAULT;

	/**
	 * This is true if the BCWS attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bCWSESet = false;

	/**
	 * The default value of the '{@link #getBCWP() <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWP()
	 * @generated
	 * @ordered
	 */
	protected static final float BCWP_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getBCWP() <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBCWP()
	 * @generated
	 * @ordered
	 */
	protected float bCWP = BCWP_EDEFAULT;

	/**
	 * This is true if the BCWP attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bCWPESet = false;

	/**
	 * The default value of the '{@link #getPhysicalPercentComplete() <em>Physical Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhysicalPercentComplete()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PHYSICAL_PERCENT_COMPLETE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhysicalPercentComplete() <em>Physical Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhysicalPercentComplete()
	 * @generated
	 * @ordered
	 */
	protected BigInteger physicalPercentComplete = PHYSICAL_PERCENT_COMPLETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEarnedValueMethod() <em>Earned Value Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEarnedValueMethod()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger EARNED_VALUE_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEarnedValueMethod() <em>Earned Value Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEarnedValueMethod()
	 * @generated
	 * @ordered
	 */
	protected BigInteger earnedValueMethod = EARNED_VALUE_METHOD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPredecessorLink() <em>Predecessor Link</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredecessorLink()
	 * @generated
	 * @ordered
	 */
	protected EList predecessorLink = null;

	/**
	 * The default value of the '{@link #getActualWorkProtected() <em>Actual Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualWorkProtected()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_WORK_PROTECTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualWorkProtected() <em>Actual Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualWorkProtected()
	 * @generated
	 * @ordered
	 */
	protected Object actualWorkProtected = ACTUAL_WORK_PROTECTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualOvertimeWorkProtected() <em>Actual Overtime Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualOvertimeWorkProtected()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualOvertimeWorkProtected() <em>Actual Overtime Work Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualOvertimeWorkProtected()
	 * @generated
	 * @ordered
	 */
	protected Object actualOvertimeWorkProtected = ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtendedAttribute() <em>Extended Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList extendedAttribute = null;

	/**
	 * The cached value of the '{@link #getBaseline() <em>Baseline</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseline()
	 * @generated
	 * @ordered
	 */
	protected EList baseline = null;

	/**
	 * The cached value of the '{@link #getOutlineCode() <em>Outline Code</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutlineCode()
	 * @generated
	 * @ordered
	 */
	protected EList outlineCode = null;

	/**
	 * The cached value of the '{@link #getTimephasedData() <em>Timephased Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimephasedData()
	 * @generated
	 * @ordered
	 */
	protected EList timephasedData = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getTask();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getUID() {
		return uID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUID(BigInteger newUID) {
		BigInteger oldUID = uID;
		uID = newUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__UID, oldUID, uID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getID() {
		return iD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setID(BigInteger newID) {
		BigInteger oldID = iD;
		iD = newID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ID, oldID, iD));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(BigInteger newType) {
		BigInteger oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNull() {
		return isNull;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNull(boolean newIsNull) {
		boolean oldIsNull = isNull;
		isNull = newIsNull;
		boolean oldIsNullESet = isNullESet;
		isNullESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__IS_NULL, oldIsNull, isNull, !oldIsNullESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsNull() {
		boolean oldIsNull = isNull;
		boolean oldIsNullESet = isNullESet;
		isNull = IS_NULL_EDEFAULT;
		isNullESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__IS_NULL, oldIsNull, IS_NULL_EDEFAULT, oldIsNullESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsNull() {
		return isNullESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getCreateDate() {
		return createDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateDate(Object newCreateDate) {
		Object oldCreateDate = createDate;
		createDate = newCreateDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CREATE_DATE, oldCreateDate, createDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContact(String newContact) {
		String oldContact = contact;
		contact = newContact;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CONTACT, oldContact, contact));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWBS() {
		return wBS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWBS(String newWBS) {
		String oldWBS = wBS;
		wBS = newWBS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__WBS, oldWBS, wBS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWBSLevel() {
		return wBSLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWBSLevel(String newWBSLevel) {
		String oldWBSLevel = wBSLevel;
		wBSLevel = newWBSLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__WBS_LEVEL, oldWBSLevel, wBSLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutlineNumber() {
		return outlineNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutlineNumber(String newOutlineNumber) {
		String oldOutlineNumber = outlineNumber;
		outlineNumber = newOutlineNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__OUTLINE_NUMBER, oldOutlineNumber, outlineNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getOutlineLevel() {
		return outlineLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutlineLevel(BigInteger newOutlineLevel) {
		BigInteger oldOutlineLevel = outlineLevel;
		outlineLevel = newOutlineLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__OUTLINE_LEVEL, oldOutlineLevel, outlineLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(BigInteger newPriority) {
		BigInteger oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(Object newStart) {
		Object oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getFinish() {
		return finish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinish(Object newFinish) {
		Object oldFinish = finish;
		finish = newFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__FINISH, oldFinish, finish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(Object newDuration) {
		Object oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDurationFormat() {
		return durationFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDurationFormat(BigInteger newDurationFormat) {
		BigInteger oldDurationFormat = durationFormat;
		durationFormat = newDurationFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__DURATION_FORMAT, oldDurationFormat, durationFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getWork() {
		return work;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWork(Object newWork) {
		Object oldWork = work;
		work = newWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__WORK, oldWork, work));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getStop() {
		return stop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStop(Object newStop) {
		Object oldStop = stop;
		stop = newStop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__STOP, oldStop, stop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getResume() {
		return resume;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResume(Object newResume) {
		Object oldResume = resume;
		resume = newResume;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__RESUME, oldResume, resume));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isResumeValid() {
		return resumeValid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResumeValid(boolean newResumeValid) {
		boolean oldResumeValid = resumeValid;
		resumeValid = newResumeValid;
		boolean oldResumeValidESet = resumeValidESet;
		resumeValidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__RESUME_VALID, oldResumeValid, resumeValid, !oldResumeValidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResumeValid() {
		boolean oldResumeValid = resumeValid;
		boolean oldResumeValidESet = resumeValidESet;
		resumeValid = RESUME_VALID_EDEFAULT;
		resumeValidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__RESUME_VALID, oldResumeValid, RESUME_VALID_EDEFAULT, oldResumeValidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResumeValid() {
		return resumeValidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEffortDriven() {
		return effortDriven;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEffortDriven(boolean newEffortDriven) {
		boolean oldEffortDriven = effortDriven;
		effortDriven = newEffortDriven;
		boolean oldEffortDrivenESet = effortDrivenESet;
		effortDrivenESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__EFFORT_DRIVEN, oldEffortDriven, effortDriven, !oldEffortDrivenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEffortDriven() {
		boolean oldEffortDriven = effortDriven;
		boolean oldEffortDrivenESet = effortDrivenESet;
		effortDriven = EFFORT_DRIVEN_EDEFAULT;
		effortDrivenESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__EFFORT_DRIVEN, oldEffortDriven, EFFORT_DRIVEN_EDEFAULT, oldEffortDrivenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEffortDriven() {
		return effortDrivenESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRecurring() {
		return recurring;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecurring(boolean newRecurring) {
		boolean oldRecurring = recurring;
		recurring = newRecurring;
		boolean oldRecurringESet = recurringESet;
		recurringESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__RECURRING, oldRecurring, recurring, !oldRecurringESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRecurring() {
		boolean oldRecurring = recurring;
		boolean oldRecurringESet = recurringESet;
		recurring = RECURRING_EDEFAULT;
		recurringESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__RECURRING, oldRecurring, RECURRING_EDEFAULT, oldRecurringESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRecurring() {
		return recurringESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverAllocated() {
		return overAllocated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverAllocated(boolean newOverAllocated) {
		boolean oldOverAllocated = overAllocated;
		overAllocated = newOverAllocated;
		boolean oldOverAllocatedESet = overAllocatedESet;
		overAllocatedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__OVER_ALLOCATED, oldOverAllocated, overAllocated, !oldOverAllocatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOverAllocated() {
		boolean oldOverAllocated = overAllocated;
		boolean oldOverAllocatedESet = overAllocatedESet;
		overAllocated = OVER_ALLOCATED_EDEFAULT;
		overAllocatedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__OVER_ALLOCATED, oldOverAllocated, OVER_ALLOCATED_EDEFAULT, oldOverAllocatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOverAllocated() {
		return overAllocatedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEstimated() {
		return estimated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEstimated(boolean newEstimated) {
		boolean oldEstimated = estimated;
		estimated = newEstimated;
		boolean oldEstimatedESet = estimatedESet;
		estimatedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ESTIMATED, oldEstimated, estimated, !oldEstimatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEstimated() {
		boolean oldEstimated = estimated;
		boolean oldEstimatedESet = estimatedESet;
		estimated = ESTIMATED_EDEFAULT;
		estimatedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__ESTIMATED, oldEstimated, ESTIMATED_EDEFAULT, oldEstimatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEstimated() {
		return estimatedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMilestone() {
		return milestone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMilestone(boolean newMilestone) {
		boolean oldMilestone = milestone;
		milestone = newMilestone;
		boolean oldMilestoneESet = milestoneESet;
		milestoneESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__MILESTONE, oldMilestone, milestone, !oldMilestoneESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMilestone() {
		boolean oldMilestone = milestone;
		boolean oldMilestoneESet = milestoneESet;
		milestone = MILESTONE_EDEFAULT;
		milestoneESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__MILESTONE, oldMilestone, MILESTONE_EDEFAULT, oldMilestoneESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMilestone() {
		return milestoneESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSummary() {
		return summary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSummary(boolean newSummary) {
		boolean oldSummary = summary;
		summary = newSummary;
		boolean oldSummaryESet = summaryESet;
		summaryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__SUMMARY, oldSummary, summary, !oldSummaryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSummary() {
		boolean oldSummary = summary;
		boolean oldSummaryESet = summaryESet;
		summary = SUMMARY_EDEFAULT;
		summaryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__SUMMARY, oldSummary, SUMMARY_EDEFAULT, oldSummaryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSummary() {
		return summaryESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCritical() {
		return critical;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCritical(boolean newCritical) {
		boolean oldCritical = critical;
		critical = newCritical;
		boolean oldCriticalESet = criticalESet;
		criticalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CRITICAL, oldCritical, critical, !oldCriticalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCritical() {
		boolean oldCritical = critical;
		boolean oldCriticalESet = criticalESet;
		critical = CRITICAL_EDEFAULT;
		criticalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__CRITICAL, oldCritical, CRITICAL_EDEFAULT, oldCriticalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCritical() {
		return criticalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSubproject() {
		return isSubproject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSubproject(boolean newIsSubproject) {
		boolean oldIsSubproject = isSubproject;
		isSubproject = newIsSubproject;
		boolean oldIsSubprojectESet = isSubprojectESet;
		isSubprojectESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__IS_SUBPROJECT, oldIsSubproject, isSubproject, !oldIsSubprojectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsSubproject() {
		boolean oldIsSubproject = isSubproject;
		boolean oldIsSubprojectESet = isSubprojectESet;
		isSubproject = IS_SUBPROJECT_EDEFAULT;
		isSubprojectESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__IS_SUBPROJECT, oldIsSubproject, IS_SUBPROJECT_EDEFAULT, oldIsSubprojectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsSubproject() {
		return isSubprojectESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSubprojectReadOnly() {
		return isSubprojectReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSubprojectReadOnly(boolean newIsSubprojectReadOnly) {
		boolean oldIsSubprojectReadOnly = isSubprojectReadOnly;
		isSubprojectReadOnly = newIsSubprojectReadOnly;
		boolean oldIsSubprojectReadOnlyESet = isSubprojectReadOnlyESet;
		isSubprojectReadOnlyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__IS_SUBPROJECT_READ_ONLY, oldIsSubprojectReadOnly, isSubprojectReadOnly, !oldIsSubprojectReadOnlyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsSubprojectReadOnly() {
		boolean oldIsSubprojectReadOnly = isSubprojectReadOnly;
		boolean oldIsSubprojectReadOnlyESet = isSubprojectReadOnlyESet;
		isSubprojectReadOnly = IS_SUBPROJECT_READ_ONLY_EDEFAULT;
		isSubprojectReadOnlyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__IS_SUBPROJECT_READ_ONLY, oldIsSubprojectReadOnly, IS_SUBPROJECT_READ_ONLY_EDEFAULT, oldIsSubprojectReadOnlyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsSubprojectReadOnly() {
		return isSubprojectReadOnlyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubprojectName() {
		return subprojectName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubprojectName(String newSubprojectName) {
		String oldSubprojectName = subprojectName;
		subprojectName = newSubprojectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__SUBPROJECT_NAME, oldSubprojectName, subprojectName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExternalTask() {
		return externalTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalTask(boolean newExternalTask) {
		boolean oldExternalTask = externalTask;
		externalTask = newExternalTask;
		boolean oldExternalTaskESet = externalTaskESet;
		externalTaskESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__EXTERNAL_TASK, oldExternalTask, externalTask, !oldExternalTaskESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExternalTask() {
		boolean oldExternalTask = externalTask;
		boolean oldExternalTaskESet = externalTaskESet;
		externalTask = EXTERNAL_TASK_EDEFAULT;
		externalTaskESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__EXTERNAL_TASK, oldExternalTask, EXTERNAL_TASK_EDEFAULT, oldExternalTaskESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExternalTask() {
		return externalTaskESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExternalTaskProject() {
		return externalTaskProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalTaskProject(String newExternalTaskProject) {
		String oldExternalTaskProject = externalTaskProject;
		externalTaskProject = newExternalTaskProject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__EXTERNAL_TASK_PROJECT, oldExternalTaskProject, externalTaskProject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getEarlyStart() {
		return earlyStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEarlyStart(Object newEarlyStart) {
		Object oldEarlyStart = earlyStart;
		earlyStart = newEarlyStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__EARLY_START, oldEarlyStart, earlyStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getEarlyFinish() {
		return earlyFinish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEarlyFinish(Object newEarlyFinish) {
		Object oldEarlyFinish = earlyFinish;
		earlyFinish = newEarlyFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__EARLY_FINISH, oldEarlyFinish, earlyFinish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getLateStart() {
		return lateStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLateStart(Object newLateStart) {
		Object oldLateStart = lateStart;
		lateStart = newLateStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__LATE_START, oldLateStart, lateStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getLateFinish() {
		return lateFinish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLateFinish(Object newLateFinish) {
		Object oldLateFinish = lateFinish;
		lateFinish = newLateFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__LATE_FINISH, oldLateFinish, lateFinish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getStartVariance() {
		return startVariance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartVariance(BigInteger newStartVariance) {
		BigInteger oldStartVariance = startVariance;
		startVariance = newStartVariance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__START_VARIANCE, oldStartVariance, startVariance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getFinishVariance() {
		return finishVariance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinishVariance(BigInteger newFinishVariance) {
		BigInteger oldFinishVariance = finishVariance;
		finishVariance = newFinishVariance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__FINISH_VARIANCE, oldFinishVariance, finishVariance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getWorkVariance() {
		return workVariance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkVariance(float newWorkVariance) {
		float oldWorkVariance = workVariance;
		workVariance = newWorkVariance;
		boolean oldWorkVarianceESet = workVarianceESet;
		workVarianceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__WORK_VARIANCE, oldWorkVariance, workVariance, !oldWorkVarianceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWorkVariance() {
		float oldWorkVariance = workVariance;
		boolean oldWorkVarianceESet = workVarianceESet;
		workVariance = WORK_VARIANCE_EDEFAULT;
		workVarianceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__WORK_VARIANCE, oldWorkVariance, WORK_VARIANCE_EDEFAULT, oldWorkVarianceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWorkVariance() {
		return workVarianceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getFreeSlack() {
		return freeSlack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFreeSlack(BigInteger newFreeSlack) {
		BigInteger oldFreeSlack = freeSlack;
		freeSlack = newFreeSlack;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__FREE_SLACK, oldFreeSlack, freeSlack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getTotalSlack() {
		return totalSlack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalSlack(BigInteger newTotalSlack) {
		BigInteger oldTotalSlack = totalSlack;
		totalSlack = newTotalSlack;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__TOTAL_SLACK, oldTotalSlack, totalSlack));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFixedCost() {
		return fixedCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFixedCost(float newFixedCost) {
		float oldFixedCost = fixedCost;
		fixedCost = newFixedCost;
		boolean oldFixedCostESet = fixedCostESet;
		fixedCostESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__FIXED_COST, oldFixedCost, fixedCost, !oldFixedCostESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFixedCost() {
		float oldFixedCost = fixedCost;
		boolean oldFixedCostESet = fixedCostESet;
		fixedCost = FIXED_COST_EDEFAULT;
		fixedCostESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__FIXED_COST, oldFixedCost, FIXED_COST_EDEFAULT, oldFixedCostESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFixedCost() {
		return fixedCostESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFixedCostAccrual() {
		return fixedCostAccrual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFixedCostAccrual(String newFixedCostAccrual) {
		String oldFixedCostAccrual = fixedCostAccrual;
		fixedCostAccrual = newFixedCostAccrual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__FIXED_COST_ACCRUAL, oldFixedCostAccrual, fixedCostAccrual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getPercentComplete() {
		return percentComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentComplete(BigInteger newPercentComplete) {
		BigInteger oldPercentComplete = percentComplete;
		percentComplete = newPercentComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__PERCENT_COMPLETE, oldPercentComplete, percentComplete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getPercentWorkComplete() {
		return percentWorkComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentWorkComplete(BigInteger newPercentWorkComplete) {
		BigInteger oldPercentWorkComplete = percentWorkComplete;
		percentWorkComplete = newPercentWorkComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__PERCENT_WORK_COMPLETE, oldPercentWorkComplete, percentWorkComplete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCost(BigDecimal newCost) {
		BigDecimal oldCost = cost;
		cost = newCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__COST, oldCost, cost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getOvertimeCost() {
		return overtimeCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertimeCost(BigDecimal newOvertimeCost) {
		BigDecimal oldOvertimeCost = overtimeCost;
		overtimeCost = newOvertimeCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__OVERTIME_COST, oldOvertimeCost, overtimeCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getOvertimeWork() {
		return overtimeWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertimeWork(Object newOvertimeWork) {
		Object oldOvertimeWork = overtimeWork;
		overtimeWork = newOvertimeWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__OVERTIME_WORK, oldOvertimeWork, overtimeWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualStart() {
		return actualStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualStart(Object newActualStart) {
		Object oldActualStart = actualStart;
		actualStart = newActualStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_START, oldActualStart, actualStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualFinish() {
		return actualFinish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualFinish(Object newActualFinish) {
		Object oldActualFinish = actualFinish;
		actualFinish = newActualFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_FINISH, oldActualFinish, actualFinish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualDuration() {
		return actualDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualDuration(Object newActualDuration) {
		Object oldActualDuration = actualDuration;
		actualDuration = newActualDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_DURATION, oldActualDuration, actualDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getActualCost() {
		return actualCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualCost(BigDecimal newActualCost) {
		BigDecimal oldActualCost = actualCost;
		actualCost = newActualCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_COST, oldActualCost, actualCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getActualOvertimeCost() {
		return actualOvertimeCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualOvertimeCost(BigDecimal newActualOvertimeCost) {
		BigDecimal oldActualOvertimeCost = actualOvertimeCost;
		actualOvertimeCost = newActualOvertimeCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_OVERTIME_COST, oldActualOvertimeCost, actualOvertimeCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualWork() {
		return actualWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualWork(Object newActualWork) {
		Object oldActualWork = actualWork;
		actualWork = newActualWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_WORK, oldActualWork, actualWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualOvertimeWork() {
		return actualOvertimeWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualOvertimeWork(Object newActualOvertimeWork) {
		Object oldActualOvertimeWork = actualOvertimeWork;
		actualOvertimeWork = newActualOvertimeWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK, oldActualOvertimeWork, actualOvertimeWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRegularWork() {
		return regularWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRegularWork(Object newRegularWork) {
		Object oldRegularWork = regularWork;
		regularWork = newRegularWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__REGULAR_WORK, oldRegularWork, regularWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRemainingDuration() {
		return remainingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemainingDuration(Object newRemainingDuration) {
		Object oldRemainingDuration = remainingDuration;
		remainingDuration = newRemainingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__REMAINING_DURATION, oldRemainingDuration, remainingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getRemainingCost() {
		return remainingCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemainingCost(BigDecimal newRemainingCost) {
		BigDecimal oldRemainingCost = remainingCost;
		remainingCost = newRemainingCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__REMAINING_COST, oldRemainingCost, remainingCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRemainingWork() {
		return remainingWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemainingWork(Object newRemainingWork) {
		Object oldRemainingWork = remainingWork;
		remainingWork = newRemainingWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__REMAINING_WORK, oldRemainingWork, remainingWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getRemainingOvertimeCost() {
		return remainingOvertimeCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemainingOvertimeCost(BigDecimal newRemainingOvertimeCost) {
		BigDecimal oldRemainingOvertimeCost = remainingOvertimeCost;
		remainingOvertimeCost = newRemainingOvertimeCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__REMAINING_OVERTIME_COST, oldRemainingOvertimeCost, remainingOvertimeCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRemainingOvertimeWork() {
		return remainingOvertimeWork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemainingOvertimeWork(Object newRemainingOvertimeWork) {
		Object oldRemainingOvertimeWork = remainingOvertimeWork;
		remainingOvertimeWork = newRemainingOvertimeWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__REMAINING_OVERTIME_WORK, oldRemainingOvertimeWork, remainingOvertimeWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getACWP() {
		return aCWP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setACWP(float newACWP) {
		float oldACWP = aCWP;
		aCWP = newACWP;
		boolean oldACWPESet = aCWPESet;
		aCWPESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACWP, oldACWP, aCWP, !oldACWPESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetACWP() {
		float oldACWP = aCWP;
		boolean oldACWPESet = aCWPESet;
		aCWP = ACWP_EDEFAULT;
		aCWPESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__ACWP, oldACWP, ACWP_EDEFAULT, oldACWPESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetACWP() {
		return aCWPESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getCV() {
		return cV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCV(float newCV) {
		float oldCV = cV;
		cV = newCV;
		boolean oldCVESet = cVESet;
		cVESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CV, oldCV, cV, !oldCVESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCV() {
		float oldCV = cV;
		boolean oldCVESet = cVESet;
		cV = CV_EDEFAULT;
		cVESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__CV, oldCV, CV_EDEFAULT, oldCVESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCV() {
		return cVESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getConstraintType() {
		return constraintType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintType(BigInteger newConstraintType) {
		BigInteger oldConstraintType = constraintType;
		constraintType = newConstraintType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CONSTRAINT_TYPE, oldConstraintType, constraintType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCalendarUID() {
		return calendarUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalendarUID(BigInteger newCalendarUID) {
		BigInteger oldCalendarUID = calendarUID;
		calendarUID = newCalendarUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CALENDAR_UID, oldCalendarUID, calendarUID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getConstraintDate() {
		return constraintDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintDate(Object newConstraintDate) {
		Object oldConstraintDate = constraintDate;
		constraintDate = newConstraintDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__CONSTRAINT_DATE, oldConstraintDate, constraintDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getDeadline() {
		return deadline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeadline(Object newDeadline) {
		Object oldDeadline = deadline;
		deadline = newDeadline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__DEADLINE, oldDeadline, deadline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLevelAssignments() {
		return levelAssignments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevelAssignments(boolean newLevelAssignments) {
		boolean oldLevelAssignments = levelAssignments;
		levelAssignments = newLevelAssignments;
		boolean oldLevelAssignmentsESet = levelAssignmentsESet;
		levelAssignmentsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__LEVEL_ASSIGNMENTS, oldLevelAssignments, levelAssignments, !oldLevelAssignmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetLevelAssignments() {
		boolean oldLevelAssignments = levelAssignments;
		boolean oldLevelAssignmentsESet = levelAssignmentsESet;
		levelAssignments = LEVEL_ASSIGNMENTS_EDEFAULT;
		levelAssignmentsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__LEVEL_ASSIGNMENTS, oldLevelAssignments, LEVEL_ASSIGNMENTS_EDEFAULT, oldLevelAssignmentsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetLevelAssignments() {
		return levelAssignmentsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLevelingCanSplit() {
		return levelingCanSplit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevelingCanSplit(boolean newLevelingCanSplit) {
		boolean oldLevelingCanSplit = levelingCanSplit;
		levelingCanSplit = newLevelingCanSplit;
		boolean oldLevelingCanSplitESet = levelingCanSplitESet;
		levelingCanSplitESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__LEVELING_CAN_SPLIT, oldLevelingCanSplit, levelingCanSplit, !oldLevelingCanSplitESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetLevelingCanSplit() {
		boolean oldLevelingCanSplit = levelingCanSplit;
		boolean oldLevelingCanSplitESet = levelingCanSplitESet;
		levelingCanSplit = LEVELING_CAN_SPLIT_EDEFAULT;
		levelingCanSplitESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__LEVELING_CAN_SPLIT, oldLevelingCanSplit, LEVELING_CAN_SPLIT_EDEFAULT, oldLevelingCanSplitESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetLevelingCanSplit() {
		return levelingCanSplitESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getLevelingDelay() {
		return levelingDelay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevelingDelay(BigInteger newLevelingDelay) {
		BigInteger oldLevelingDelay = levelingDelay;
		levelingDelay = newLevelingDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__LEVELING_DELAY, oldLevelingDelay, levelingDelay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getLevelingDelayFormat() {
		return levelingDelayFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevelingDelayFormat(BigInteger newLevelingDelayFormat) {
		BigInteger oldLevelingDelayFormat = levelingDelayFormat;
		levelingDelayFormat = newLevelingDelayFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__LEVELING_DELAY_FORMAT, oldLevelingDelayFormat, levelingDelayFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getPreLeveledStart() {
		return preLeveledStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreLeveledStart(Object newPreLeveledStart) {
		Object oldPreLeveledStart = preLeveledStart;
		preLeveledStart = newPreLeveledStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__PRE_LEVELED_START, oldPreLeveledStart, preLeveledStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getPreLeveledFinish() {
		return preLeveledFinish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreLeveledFinish(Object newPreLeveledFinish) {
		Object oldPreLeveledFinish = preLeveledFinish;
		preLeveledFinish = newPreLeveledFinish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__PRE_LEVELED_FINISH, oldPreLeveledFinish, preLeveledFinish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHyperlink() {
		return hyperlink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHyperlink(String newHyperlink) {
		String oldHyperlink = hyperlink;
		hyperlink = newHyperlink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__HYPERLINK, oldHyperlink, hyperlink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHyperlinkAddress() {
		return hyperlinkAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHyperlinkAddress(String newHyperlinkAddress) {
		String oldHyperlinkAddress = hyperlinkAddress;
		hyperlinkAddress = newHyperlinkAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__HYPERLINK_ADDRESS, oldHyperlinkAddress, hyperlinkAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHyperlinkSubAddress() {
		return hyperlinkSubAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHyperlinkSubAddress(String newHyperlinkSubAddress) {
		String oldHyperlinkSubAddress = hyperlinkSubAddress;
		hyperlinkSubAddress = newHyperlinkSubAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__HYPERLINK_SUB_ADDRESS, oldHyperlinkSubAddress, hyperlinkSubAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIgnoreResourceCalendar() {
		return ignoreResourceCalendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIgnoreResourceCalendar(boolean newIgnoreResourceCalendar) {
		boolean oldIgnoreResourceCalendar = ignoreResourceCalendar;
		ignoreResourceCalendar = newIgnoreResourceCalendar;
		boolean oldIgnoreResourceCalendarESet = ignoreResourceCalendarESet;
		ignoreResourceCalendarESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__IGNORE_RESOURCE_CALENDAR, oldIgnoreResourceCalendar, ignoreResourceCalendar, !oldIgnoreResourceCalendarESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIgnoreResourceCalendar() {
		boolean oldIgnoreResourceCalendar = ignoreResourceCalendar;
		boolean oldIgnoreResourceCalendarESet = ignoreResourceCalendarESet;
		ignoreResourceCalendar = IGNORE_RESOURCE_CALENDAR_EDEFAULT;
		ignoreResourceCalendarESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__IGNORE_RESOURCE_CALENDAR, oldIgnoreResourceCalendar, IGNORE_RESOURCE_CALENDAR_EDEFAULT, oldIgnoreResourceCalendarESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIgnoreResourceCalendar() {
		return ignoreResourceCalendarESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotes(String newNotes) {
		String oldNotes = notes;
		notes = newNotes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__NOTES, oldNotes, notes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHideBar() {
		return hideBar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHideBar(boolean newHideBar) {
		boolean oldHideBar = hideBar;
		hideBar = newHideBar;
		boolean oldHideBarESet = hideBarESet;
		hideBarESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__HIDE_BAR, oldHideBar, hideBar, !oldHideBarESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHideBar() {
		boolean oldHideBar = hideBar;
		boolean oldHideBarESet = hideBarESet;
		hideBar = HIDE_BAR_EDEFAULT;
		hideBarESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__HIDE_BAR, oldHideBar, HIDE_BAR_EDEFAULT, oldHideBarESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHideBar() {
		return hideBarESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRollup() {
		return rollup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRollup(boolean newRollup) {
		boolean oldRollup = rollup;
		rollup = newRollup;
		boolean oldRollupESet = rollupESet;
		rollupESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ROLLUP, oldRollup, rollup, !oldRollupESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRollup() {
		boolean oldRollup = rollup;
		boolean oldRollupESet = rollupESet;
		rollup = ROLLUP_EDEFAULT;
		rollupESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__ROLLUP, oldRollup, ROLLUP_EDEFAULT, oldRollupESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRollup() {
		return rollupESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getBCWS() {
		return bCWS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBCWS(float newBCWS) {
		float oldBCWS = bCWS;
		bCWS = newBCWS;
		boolean oldBCWSESet = bCWSESet;
		bCWSESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__BCWS, oldBCWS, bCWS, !oldBCWSESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBCWS() {
		float oldBCWS = bCWS;
		boolean oldBCWSESet = bCWSESet;
		bCWS = BCWS_EDEFAULT;
		bCWSESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__BCWS, oldBCWS, BCWS_EDEFAULT, oldBCWSESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBCWS() {
		return bCWSESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getBCWP() {
		return bCWP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBCWP(float newBCWP) {
		float oldBCWP = bCWP;
		bCWP = newBCWP;
		boolean oldBCWPESet = bCWPESet;
		bCWPESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__BCWP, oldBCWP, bCWP, !oldBCWPESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBCWP() {
		float oldBCWP = bCWP;
		boolean oldBCWPESet = bCWPESet;
		bCWP = BCWP_EDEFAULT;
		bCWPESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.TASK__BCWP, oldBCWP, BCWP_EDEFAULT, oldBCWPESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBCWP() {
		return bCWPESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getPhysicalPercentComplete() {
		return physicalPercentComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhysicalPercentComplete(BigInteger newPhysicalPercentComplete) {
		BigInteger oldPhysicalPercentComplete = physicalPercentComplete;
		physicalPercentComplete = newPhysicalPercentComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__PHYSICAL_PERCENT_COMPLETE, oldPhysicalPercentComplete, physicalPercentComplete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getEarnedValueMethod() {
		return earnedValueMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEarnedValueMethod(BigInteger newEarnedValueMethod) {
		BigInteger oldEarnedValueMethod = earnedValueMethod;
		earnedValueMethod = newEarnedValueMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__EARNED_VALUE_METHOD, oldEarnedValueMethod, earnedValueMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPredecessorLink() {
		if (predecessorLink == null) {
			predecessorLink = new EObjectContainmentEList(PredecessorLink.class, this, MsprojectPackage.TASK__PREDECESSOR_LINK);
		}
		return predecessorLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualWorkProtected() {
		return actualWorkProtected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualWorkProtected(Object newActualWorkProtected) {
		Object oldActualWorkProtected = actualWorkProtected;
		actualWorkProtected = newActualWorkProtected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_WORK_PROTECTED, oldActualWorkProtected, actualWorkProtected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getActualOvertimeWorkProtected() {
		return actualOvertimeWorkProtected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualOvertimeWorkProtected(Object newActualOvertimeWorkProtected) {
		Object oldActualOvertimeWorkProtected = actualOvertimeWorkProtected;
		actualOvertimeWorkProtected = newActualOvertimeWorkProtected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK_PROTECTED, oldActualOvertimeWorkProtected, actualOvertimeWorkProtected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedAttribute() {
		if (extendedAttribute == null) {
			extendedAttribute = new EObjectContainmentEList(ExtendedAttribute2.class, this, MsprojectPackage.TASK__EXTENDED_ATTRIBUTE);
		}
		return extendedAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getBaseline() {
		if (baseline == null) {
			baseline = new EObjectContainmentEList(Baseline.class, this, MsprojectPackage.TASK__BASELINE);
		}
		return baseline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOutlineCode() {
		if (outlineCode == null) {
			outlineCode = new EObjectContainmentEList(OutlineCode2.class, this, MsprojectPackage.TASK__OUTLINE_CODE);
		}
		return outlineCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTimephasedData() {
		if (timephasedData == null) {
			timephasedData = new EObjectContainmentEList(TimephasedDataType.class, this, MsprojectPackage.TASK__TIMEPHASED_DATA);
		}
		return timephasedData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.TASK__PREDECESSOR_LINK:
					return ((InternalEList)getPredecessorLink()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.TASK__EXTENDED_ATTRIBUTE:
					return ((InternalEList)getExtendedAttribute()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.TASK__BASELINE:
					return ((InternalEList)getBaseline()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.TASK__OUTLINE_CODE:
					return ((InternalEList)getOutlineCode()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.TASK__TIMEPHASED_DATA:
					return ((InternalEList)getTimephasedData()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.TASK__UID:
				return getUID();
			case MsprojectPackage.TASK__ID:
				return getID();
			case MsprojectPackage.TASK__NAME:
				return getName();
			case MsprojectPackage.TASK__TYPE:
				return getType();
			case MsprojectPackage.TASK__IS_NULL:
				return isIsNull() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__CREATE_DATE:
				return getCreateDate();
			case MsprojectPackage.TASK__CONTACT:
				return getContact();
			case MsprojectPackage.TASK__WBS:
				return getWBS();
			case MsprojectPackage.TASK__WBS_LEVEL:
				return getWBSLevel();
			case MsprojectPackage.TASK__OUTLINE_NUMBER:
				return getOutlineNumber();
			case MsprojectPackage.TASK__OUTLINE_LEVEL:
				return getOutlineLevel();
			case MsprojectPackage.TASK__PRIORITY:
				return getPriority();
			case MsprojectPackage.TASK__START:
				return getStart();
			case MsprojectPackage.TASK__FINISH:
				return getFinish();
			case MsprojectPackage.TASK__DURATION:
				return getDuration();
			case MsprojectPackage.TASK__DURATION_FORMAT:
				return getDurationFormat();
			case MsprojectPackage.TASK__WORK:
				return getWork();
			case MsprojectPackage.TASK__STOP:
				return getStop();
			case MsprojectPackage.TASK__RESUME:
				return getResume();
			case MsprojectPackage.TASK__RESUME_VALID:
				return isResumeValid() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__EFFORT_DRIVEN:
				return isEffortDriven() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__RECURRING:
				return isRecurring() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__OVER_ALLOCATED:
				return isOverAllocated() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__ESTIMATED:
				return isEstimated() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__MILESTONE:
				return isMilestone() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__SUMMARY:
				return isSummary() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__CRITICAL:
				return isCritical() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__IS_SUBPROJECT:
				return isIsSubproject() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__IS_SUBPROJECT_READ_ONLY:
				return isIsSubprojectReadOnly() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__SUBPROJECT_NAME:
				return getSubprojectName();
			case MsprojectPackage.TASK__EXTERNAL_TASK:
				return isExternalTask() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__EXTERNAL_TASK_PROJECT:
				return getExternalTaskProject();
			case MsprojectPackage.TASK__EARLY_START:
				return getEarlyStart();
			case MsprojectPackage.TASK__EARLY_FINISH:
				return getEarlyFinish();
			case MsprojectPackage.TASK__LATE_START:
				return getLateStart();
			case MsprojectPackage.TASK__LATE_FINISH:
				return getLateFinish();
			case MsprojectPackage.TASK__START_VARIANCE:
				return getStartVariance();
			case MsprojectPackage.TASK__FINISH_VARIANCE:
				return getFinishVariance();
			case MsprojectPackage.TASK__WORK_VARIANCE:
				return new Float(getWorkVariance());
			case MsprojectPackage.TASK__FREE_SLACK:
				return getFreeSlack();
			case MsprojectPackage.TASK__TOTAL_SLACK:
				return getTotalSlack();
			case MsprojectPackage.TASK__FIXED_COST:
				return new Float(getFixedCost());
			case MsprojectPackage.TASK__FIXED_COST_ACCRUAL:
				return getFixedCostAccrual();
			case MsprojectPackage.TASK__PERCENT_COMPLETE:
				return getPercentComplete();
			case MsprojectPackage.TASK__PERCENT_WORK_COMPLETE:
				return getPercentWorkComplete();
			case MsprojectPackage.TASK__COST:
				return getCost();
			case MsprojectPackage.TASK__OVERTIME_COST:
				return getOvertimeCost();
			case MsprojectPackage.TASK__OVERTIME_WORK:
				return getOvertimeWork();
			case MsprojectPackage.TASK__ACTUAL_START:
				return getActualStart();
			case MsprojectPackage.TASK__ACTUAL_FINISH:
				return getActualFinish();
			case MsprojectPackage.TASK__ACTUAL_DURATION:
				return getActualDuration();
			case MsprojectPackage.TASK__ACTUAL_COST:
				return getActualCost();
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_COST:
				return getActualOvertimeCost();
			case MsprojectPackage.TASK__ACTUAL_WORK:
				return getActualWork();
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK:
				return getActualOvertimeWork();
			case MsprojectPackage.TASK__REGULAR_WORK:
				return getRegularWork();
			case MsprojectPackage.TASK__REMAINING_DURATION:
				return getRemainingDuration();
			case MsprojectPackage.TASK__REMAINING_COST:
				return getRemainingCost();
			case MsprojectPackage.TASK__REMAINING_WORK:
				return getRemainingWork();
			case MsprojectPackage.TASK__REMAINING_OVERTIME_COST:
				return getRemainingOvertimeCost();
			case MsprojectPackage.TASK__REMAINING_OVERTIME_WORK:
				return getRemainingOvertimeWork();
			case MsprojectPackage.TASK__ACWP:
				return new Float(getACWP());
			case MsprojectPackage.TASK__CV:
				return new Float(getCV());
			case MsprojectPackage.TASK__CONSTRAINT_TYPE:
				return getConstraintType();
			case MsprojectPackage.TASK__CALENDAR_UID:
				return getCalendarUID();
			case MsprojectPackage.TASK__CONSTRAINT_DATE:
				return getConstraintDate();
			case MsprojectPackage.TASK__DEADLINE:
				return getDeadline();
			case MsprojectPackage.TASK__LEVEL_ASSIGNMENTS:
				return isLevelAssignments() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__LEVELING_CAN_SPLIT:
				return isLevelingCanSplit() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__LEVELING_DELAY:
				return getLevelingDelay();
			case MsprojectPackage.TASK__LEVELING_DELAY_FORMAT:
				return getLevelingDelayFormat();
			case MsprojectPackage.TASK__PRE_LEVELED_START:
				return getPreLeveledStart();
			case MsprojectPackage.TASK__PRE_LEVELED_FINISH:
				return getPreLeveledFinish();
			case MsprojectPackage.TASK__HYPERLINK:
				return getHyperlink();
			case MsprojectPackage.TASK__HYPERLINK_ADDRESS:
				return getHyperlinkAddress();
			case MsprojectPackage.TASK__HYPERLINK_SUB_ADDRESS:
				return getHyperlinkSubAddress();
			case MsprojectPackage.TASK__IGNORE_RESOURCE_CALENDAR:
				return isIgnoreResourceCalendar() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__NOTES:
				return getNotes();
			case MsprojectPackage.TASK__HIDE_BAR:
				return isHideBar() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__ROLLUP:
				return isRollup() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.TASK__BCWS:
				return new Float(getBCWS());
			case MsprojectPackage.TASK__BCWP:
				return new Float(getBCWP());
			case MsprojectPackage.TASK__PHYSICAL_PERCENT_COMPLETE:
				return getPhysicalPercentComplete();
			case MsprojectPackage.TASK__EARNED_VALUE_METHOD:
				return getEarnedValueMethod();
			case MsprojectPackage.TASK__PREDECESSOR_LINK:
				return getPredecessorLink();
			case MsprojectPackage.TASK__ACTUAL_WORK_PROTECTED:
				return getActualWorkProtected();
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK_PROTECTED:
				return getActualOvertimeWorkProtected();
			case MsprojectPackage.TASK__EXTENDED_ATTRIBUTE:
				return getExtendedAttribute();
			case MsprojectPackage.TASK__BASELINE:
				return getBaseline();
			case MsprojectPackage.TASK__OUTLINE_CODE:
				return getOutlineCode();
			case MsprojectPackage.TASK__TIMEPHASED_DATA:
				return getTimephasedData();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.TASK__UID:
				setUID((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__ID:
				setID((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__NAME:
				setName((String)newValue);
				return;
			case MsprojectPackage.TASK__TYPE:
				setType((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__IS_NULL:
				setIsNull(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__CREATE_DATE:
				setCreateDate((Object)newValue);
				return;
			case MsprojectPackage.TASK__CONTACT:
				setContact((String)newValue);
				return;
			case MsprojectPackage.TASK__WBS:
				setWBS((String)newValue);
				return;
			case MsprojectPackage.TASK__WBS_LEVEL:
				setWBSLevel((String)newValue);
				return;
			case MsprojectPackage.TASK__OUTLINE_NUMBER:
				setOutlineNumber((String)newValue);
				return;
			case MsprojectPackage.TASK__OUTLINE_LEVEL:
				setOutlineLevel((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__PRIORITY:
				setPriority((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__START:
				setStart((Object)newValue);
				return;
			case MsprojectPackage.TASK__FINISH:
				setFinish((Object)newValue);
				return;
			case MsprojectPackage.TASK__DURATION:
				setDuration((Object)newValue);
				return;
			case MsprojectPackage.TASK__DURATION_FORMAT:
				setDurationFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__WORK:
				setWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__STOP:
				setStop((Object)newValue);
				return;
			case MsprojectPackage.TASK__RESUME:
				setResume((Object)newValue);
				return;
			case MsprojectPackage.TASK__RESUME_VALID:
				setResumeValid(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__EFFORT_DRIVEN:
				setEffortDriven(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__RECURRING:
				setRecurring(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__OVER_ALLOCATED:
				setOverAllocated(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__ESTIMATED:
				setEstimated(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__MILESTONE:
				setMilestone(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__SUMMARY:
				setSummary(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__CRITICAL:
				setCritical(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__IS_SUBPROJECT:
				setIsSubproject(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__IS_SUBPROJECT_READ_ONLY:
				setIsSubprojectReadOnly(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__SUBPROJECT_NAME:
				setSubprojectName((String)newValue);
				return;
			case MsprojectPackage.TASK__EXTERNAL_TASK:
				setExternalTask(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__EXTERNAL_TASK_PROJECT:
				setExternalTaskProject((String)newValue);
				return;
			case MsprojectPackage.TASK__EARLY_START:
				setEarlyStart((Object)newValue);
				return;
			case MsprojectPackage.TASK__EARLY_FINISH:
				setEarlyFinish((Object)newValue);
				return;
			case MsprojectPackage.TASK__LATE_START:
				setLateStart((Object)newValue);
				return;
			case MsprojectPackage.TASK__LATE_FINISH:
				setLateFinish((Object)newValue);
				return;
			case MsprojectPackage.TASK__START_VARIANCE:
				setStartVariance((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__FINISH_VARIANCE:
				setFinishVariance((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__WORK_VARIANCE:
				setWorkVariance(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.TASK__FREE_SLACK:
				setFreeSlack((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__TOTAL_SLACK:
				setTotalSlack((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__FIXED_COST:
				setFixedCost(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.TASK__FIXED_COST_ACCRUAL:
				setFixedCostAccrual((String)newValue);
				return;
			case MsprojectPackage.TASK__PERCENT_COMPLETE:
				setPercentComplete((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__PERCENT_WORK_COMPLETE:
				setPercentWorkComplete((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__COST:
				setCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.TASK__OVERTIME_COST:
				setOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.TASK__OVERTIME_WORK:
				setOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_START:
				setActualStart((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_FINISH:
				setActualFinish((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_DURATION:
				setActualDuration((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_COST:
				setActualCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_COST:
				setActualOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_WORK:
				setActualWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK:
				setActualOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__REGULAR_WORK:
				setRegularWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__REMAINING_DURATION:
				setRemainingDuration((Object)newValue);
				return;
			case MsprojectPackage.TASK__REMAINING_COST:
				setRemainingCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.TASK__REMAINING_WORK:
				setRemainingWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__REMAINING_OVERTIME_COST:
				setRemainingOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.TASK__REMAINING_OVERTIME_WORK:
				setRemainingOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACWP:
				setACWP(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.TASK__CV:
				setCV(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.TASK__CONSTRAINT_TYPE:
				setConstraintType((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__CALENDAR_UID:
				setCalendarUID((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__CONSTRAINT_DATE:
				setConstraintDate((Object)newValue);
				return;
			case MsprojectPackage.TASK__DEADLINE:
				setDeadline((Object)newValue);
				return;
			case MsprojectPackage.TASK__LEVEL_ASSIGNMENTS:
				setLevelAssignments(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__LEVELING_CAN_SPLIT:
				setLevelingCanSplit(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__LEVELING_DELAY:
				setLevelingDelay((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__LEVELING_DELAY_FORMAT:
				setLevelingDelayFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__PRE_LEVELED_START:
				setPreLeveledStart((Object)newValue);
				return;
			case MsprojectPackage.TASK__PRE_LEVELED_FINISH:
				setPreLeveledFinish((Object)newValue);
				return;
			case MsprojectPackage.TASK__HYPERLINK:
				setHyperlink((String)newValue);
				return;
			case MsprojectPackage.TASK__HYPERLINK_ADDRESS:
				setHyperlinkAddress((String)newValue);
				return;
			case MsprojectPackage.TASK__HYPERLINK_SUB_ADDRESS:
				setHyperlinkSubAddress((String)newValue);
				return;
			case MsprojectPackage.TASK__IGNORE_RESOURCE_CALENDAR:
				setIgnoreResourceCalendar(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__NOTES:
				setNotes((String)newValue);
				return;
			case MsprojectPackage.TASK__HIDE_BAR:
				setHideBar(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__ROLLUP:
				setRollup(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.TASK__BCWS:
				setBCWS(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.TASK__BCWP:
				setBCWP(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.TASK__PHYSICAL_PERCENT_COMPLETE:
				setPhysicalPercentComplete((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__EARNED_VALUE_METHOD:
				setEarnedValueMethod((BigInteger)newValue);
				return;
			case MsprojectPackage.TASK__PREDECESSOR_LINK:
				getPredecessorLink().clear();
				getPredecessorLink().addAll((Collection)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_WORK_PROTECTED:
				setActualWorkProtected((Object)newValue);
				return;
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK_PROTECTED:
				setActualOvertimeWorkProtected((Object)newValue);
				return;
			case MsprojectPackage.TASK__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				getExtendedAttribute().addAll((Collection)newValue);
				return;
			case MsprojectPackage.TASK__BASELINE:
				getBaseline().clear();
				getBaseline().addAll((Collection)newValue);
				return;
			case MsprojectPackage.TASK__OUTLINE_CODE:
				getOutlineCode().clear();
				getOutlineCode().addAll((Collection)newValue);
				return;
			case MsprojectPackage.TASK__TIMEPHASED_DATA:
				getTimephasedData().clear();
				getTimephasedData().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.TASK__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ID:
				setID(ID_EDEFAULT);
				return;
			case MsprojectPackage.TASK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MsprojectPackage.TASK__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__IS_NULL:
				unsetIsNull();
				return;
			case MsprojectPackage.TASK__CREATE_DATE:
				setCreateDate(CREATE_DATE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__CONTACT:
				setContact(CONTACT_EDEFAULT);
				return;
			case MsprojectPackage.TASK__WBS:
				setWBS(WBS_EDEFAULT);
				return;
			case MsprojectPackage.TASK__WBS_LEVEL:
				setWBSLevel(WBS_LEVEL_EDEFAULT);
				return;
			case MsprojectPackage.TASK__OUTLINE_NUMBER:
				setOutlineNumber(OUTLINE_NUMBER_EDEFAULT);
				return;
			case MsprojectPackage.TASK__OUTLINE_LEVEL:
				setOutlineLevel(OUTLINE_LEVEL_EDEFAULT);
				return;
			case MsprojectPackage.TASK__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case MsprojectPackage.TASK__START:
				setStart(START_EDEFAULT);
				return;
			case MsprojectPackage.TASK__FINISH:
				setFinish(FINISH_EDEFAULT);
				return;
			case MsprojectPackage.TASK__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case MsprojectPackage.TASK__DURATION_FORMAT:
				setDurationFormat(DURATION_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.TASK__WORK:
				setWork(WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__STOP:
				setStop(STOP_EDEFAULT);
				return;
			case MsprojectPackage.TASK__RESUME:
				setResume(RESUME_EDEFAULT);
				return;
			case MsprojectPackage.TASK__RESUME_VALID:
				unsetResumeValid();
				return;
			case MsprojectPackage.TASK__EFFORT_DRIVEN:
				unsetEffortDriven();
				return;
			case MsprojectPackage.TASK__RECURRING:
				unsetRecurring();
				return;
			case MsprojectPackage.TASK__OVER_ALLOCATED:
				unsetOverAllocated();
				return;
			case MsprojectPackage.TASK__ESTIMATED:
				unsetEstimated();
				return;
			case MsprojectPackage.TASK__MILESTONE:
				unsetMilestone();
				return;
			case MsprojectPackage.TASK__SUMMARY:
				unsetSummary();
				return;
			case MsprojectPackage.TASK__CRITICAL:
				unsetCritical();
				return;
			case MsprojectPackage.TASK__IS_SUBPROJECT:
				unsetIsSubproject();
				return;
			case MsprojectPackage.TASK__IS_SUBPROJECT_READ_ONLY:
				unsetIsSubprojectReadOnly();
				return;
			case MsprojectPackage.TASK__SUBPROJECT_NAME:
				setSubprojectName(SUBPROJECT_NAME_EDEFAULT);
				return;
			case MsprojectPackage.TASK__EXTERNAL_TASK:
				unsetExternalTask();
				return;
			case MsprojectPackage.TASK__EXTERNAL_TASK_PROJECT:
				setExternalTaskProject(EXTERNAL_TASK_PROJECT_EDEFAULT);
				return;
			case MsprojectPackage.TASK__EARLY_START:
				setEarlyStart(EARLY_START_EDEFAULT);
				return;
			case MsprojectPackage.TASK__EARLY_FINISH:
				setEarlyFinish(EARLY_FINISH_EDEFAULT);
				return;
			case MsprojectPackage.TASK__LATE_START:
				setLateStart(LATE_START_EDEFAULT);
				return;
			case MsprojectPackage.TASK__LATE_FINISH:
				setLateFinish(LATE_FINISH_EDEFAULT);
				return;
			case MsprojectPackage.TASK__START_VARIANCE:
				setStartVariance(START_VARIANCE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__FINISH_VARIANCE:
				setFinishVariance(FINISH_VARIANCE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__WORK_VARIANCE:
				unsetWorkVariance();
				return;
			case MsprojectPackage.TASK__FREE_SLACK:
				setFreeSlack(FREE_SLACK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__TOTAL_SLACK:
				setTotalSlack(TOTAL_SLACK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__FIXED_COST:
				unsetFixedCost();
				return;
			case MsprojectPackage.TASK__FIXED_COST_ACCRUAL:
				setFixedCostAccrual(FIXED_COST_ACCRUAL_EDEFAULT);
				return;
			case MsprojectPackage.TASK__PERCENT_COMPLETE:
				setPercentComplete(PERCENT_COMPLETE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__PERCENT_WORK_COMPLETE:
				setPercentWorkComplete(PERCENT_WORK_COMPLETE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__COST:
				setCost(COST_EDEFAULT);
				return;
			case MsprojectPackage.TASK__OVERTIME_COST:
				setOvertimeCost(OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.TASK__OVERTIME_WORK:
				setOvertimeWork(OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_START:
				setActualStart(ACTUAL_START_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_FINISH:
				setActualFinish(ACTUAL_FINISH_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_DURATION:
				setActualDuration(ACTUAL_DURATION_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_COST:
				setActualCost(ACTUAL_COST_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_COST:
				setActualOvertimeCost(ACTUAL_OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_WORK:
				setActualWork(ACTUAL_WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK:
				setActualOvertimeWork(ACTUAL_OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__REGULAR_WORK:
				setRegularWork(REGULAR_WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__REMAINING_DURATION:
				setRemainingDuration(REMAINING_DURATION_EDEFAULT);
				return;
			case MsprojectPackage.TASK__REMAINING_COST:
				setRemainingCost(REMAINING_COST_EDEFAULT);
				return;
			case MsprojectPackage.TASK__REMAINING_WORK:
				setRemainingWork(REMAINING_WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__REMAINING_OVERTIME_COST:
				setRemainingOvertimeCost(REMAINING_OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.TASK__REMAINING_OVERTIME_WORK:
				setRemainingOvertimeWork(REMAINING_OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACWP:
				unsetACWP();
				return;
			case MsprojectPackage.TASK__CV:
				unsetCV();
				return;
			case MsprojectPackage.TASK__CONSTRAINT_TYPE:
				setConstraintType(CONSTRAINT_TYPE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__CALENDAR_UID:
				setCalendarUID(CALENDAR_UID_EDEFAULT);
				return;
			case MsprojectPackage.TASK__CONSTRAINT_DATE:
				setConstraintDate(CONSTRAINT_DATE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__DEADLINE:
				setDeadline(DEADLINE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__LEVEL_ASSIGNMENTS:
				unsetLevelAssignments();
				return;
			case MsprojectPackage.TASK__LEVELING_CAN_SPLIT:
				unsetLevelingCanSplit();
				return;
			case MsprojectPackage.TASK__LEVELING_DELAY:
				setLevelingDelay(LEVELING_DELAY_EDEFAULT);
				return;
			case MsprojectPackage.TASK__LEVELING_DELAY_FORMAT:
				setLevelingDelayFormat(LEVELING_DELAY_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.TASK__PRE_LEVELED_START:
				setPreLeveledStart(PRE_LEVELED_START_EDEFAULT);
				return;
			case MsprojectPackage.TASK__PRE_LEVELED_FINISH:
				setPreLeveledFinish(PRE_LEVELED_FINISH_EDEFAULT);
				return;
			case MsprojectPackage.TASK__HYPERLINK:
				setHyperlink(HYPERLINK_EDEFAULT);
				return;
			case MsprojectPackage.TASK__HYPERLINK_ADDRESS:
				setHyperlinkAddress(HYPERLINK_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.TASK__HYPERLINK_SUB_ADDRESS:
				setHyperlinkSubAddress(HYPERLINK_SUB_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.TASK__IGNORE_RESOURCE_CALENDAR:
				unsetIgnoreResourceCalendar();
				return;
			case MsprojectPackage.TASK__NOTES:
				setNotes(NOTES_EDEFAULT);
				return;
			case MsprojectPackage.TASK__HIDE_BAR:
				unsetHideBar();
				return;
			case MsprojectPackage.TASK__ROLLUP:
				unsetRollup();
				return;
			case MsprojectPackage.TASK__BCWS:
				unsetBCWS();
				return;
			case MsprojectPackage.TASK__BCWP:
				unsetBCWP();
				return;
			case MsprojectPackage.TASK__PHYSICAL_PERCENT_COMPLETE:
				setPhysicalPercentComplete(PHYSICAL_PERCENT_COMPLETE_EDEFAULT);
				return;
			case MsprojectPackage.TASK__EARNED_VALUE_METHOD:
				setEarnedValueMethod(EARNED_VALUE_METHOD_EDEFAULT);
				return;
			case MsprojectPackage.TASK__PREDECESSOR_LINK:
				getPredecessorLink().clear();
				return;
			case MsprojectPackage.TASK__ACTUAL_WORK_PROTECTED:
				setActualWorkProtected(ACTUAL_WORK_PROTECTED_EDEFAULT);
				return;
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK_PROTECTED:
				setActualOvertimeWorkProtected(ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT);
				return;
			case MsprojectPackage.TASK__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				return;
			case MsprojectPackage.TASK__BASELINE:
				getBaseline().clear();
				return;
			case MsprojectPackage.TASK__OUTLINE_CODE:
				getOutlineCode().clear();
				return;
			case MsprojectPackage.TASK__TIMEPHASED_DATA:
				getTimephasedData().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.TASK__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.TASK__ID:
				return ID_EDEFAULT == null ? iD != null : !ID_EDEFAULT.equals(iD);
			case MsprojectPackage.TASK__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MsprojectPackage.TASK__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case MsprojectPackage.TASK__IS_NULL:
				return isSetIsNull();
			case MsprojectPackage.TASK__CREATE_DATE:
				return CREATE_DATE_EDEFAULT == null ? createDate != null : !CREATE_DATE_EDEFAULT.equals(createDate);
			case MsprojectPackage.TASK__CONTACT:
				return CONTACT_EDEFAULT == null ? contact != null : !CONTACT_EDEFAULT.equals(contact);
			case MsprojectPackage.TASK__WBS:
				return WBS_EDEFAULT == null ? wBS != null : !WBS_EDEFAULT.equals(wBS);
			case MsprojectPackage.TASK__WBS_LEVEL:
				return WBS_LEVEL_EDEFAULT == null ? wBSLevel != null : !WBS_LEVEL_EDEFAULT.equals(wBSLevel);
			case MsprojectPackage.TASK__OUTLINE_NUMBER:
				return OUTLINE_NUMBER_EDEFAULT == null ? outlineNumber != null : !OUTLINE_NUMBER_EDEFAULT.equals(outlineNumber);
			case MsprojectPackage.TASK__OUTLINE_LEVEL:
				return OUTLINE_LEVEL_EDEFAULT == null ? outlineLevel != null : !OUTLINE_LEVEL_EDEFAULT.equals(outlineLevel);
			case MsprojectPackage.TASK__PRIORITY:
				return PRIORITY_EDEFAULT == null ? priority != null : !PRIORITY_EDEFAULT.equals(priority);
			case MsprojectPackage.TASK__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case MsprojectPackage.TASK__FINISH:
				return FINISH_EDEFAULT == null ? finish != null : !FINISH_EDEFAULT.equals(finish);
			case MsprojectPackage.TASK__DURATION:
				return DURATION_EDEFAULT == null ? duration != null : !DURATION_EDEFAULT.equals(duration);
			case MsprojectPackage.TASK__DURATION_FORMAT:
				return DURATION_FORMAT_EDEFAULT == null ? durationFormat != null : !DURATION_FORMAT_EDEFAULT.equals(durationFormat);
			case MsprojectPackage.TASK__WORK:
				return WORK_EDEFAULT == null ? work != null : !WORK_EDEFAULT.equals(work);
			case MsprojectPackage.TASK__STOP:
				return STOP_EDEFAULT == null ? stop != null : !STOP_EDEFAULT.equals(stop);
			case MsprojectPackage.TASK__RESUME:
				return RESUME_EDEFAULT == null ? resume != null : !RESUME_EDEFAULT.equals(resume);
			case MsprojectPackage.TASK__RESUME_VALID:
				return isSetResumeValid();
			case MsprojectPackage.TASK__EFFORT_DRIVEN:
				return isSetEffortDriven();
			case MsprojectPackage.TASK__RECURRING:
				return isSetRecurring();
			case MsprojectPackage.TASK__OVER_ALLOCATED:
				return isSetOverAllocated();
			case MsprojectPackage.TASK__ESTIMATED:
				return isSetEstimated();
			case MsprojectPackage.TASK__MILESTONE:
				return isSetMilestone();
			case MsprojectPackage.TASK__SUMMARY:
				return isSetSummary();
			case MsprojectPackage.TASK__CRITICAL:
				return isSetCritical();
			case MsprojectPackage.TASK__IS_SUBPROJECT:
				return isSetIsSubproject();
			case MsprojectPackage.TASK__IS_SUBPROJECT_READ_ONLY:
				return isSetIsSubprojectReadOnly();
			case MsprojectPackage.TASK__SUBPROJECT_NAME:
				return SUBPROJECT_NAME_EDEFAULT == null ? subprojectName != null : !SUBPROJECT_NAME_EDEFAULT.equals(subprojectName);
			case MsprojectPackage.TASK__EXTERNAL_TASK:
				return isSetExternalTask();
			case MsprojectPackage.TASK__EXTERNAL_TASK_PROJECT:
				return EXTERNAL_TASK_PROJECT_EDEFAULT == null ? externalTaskProject != null : !EXTERNAL_TASK_PROJECT_EDEFAULT.equals(externalTaskProject);
			case MsprojectPackage.TASK__EARLY_START:
				return EARLY_START_EDEFAULT == null ? earlyStart != null : !EARLY_START_EDEFAULT.equals(earlyStart);
			case MsprojectPackage.TASK__EARLY_FINISH:
				return EARLY_FINISH_EDEFAULT == null ? earlyFinish != null : !EARLY_FINISH_EDEFAULT.equals(earlyFinish);
			case MsprojectPackage.TASK__LATE_START:
				return LATE_START_EDEFAULT == null ? lateStart != null : !LATE_START_EDEFAULT.equals(lateStart);
			case MsprojectPackage.TASK__LATE_FINISH:
				return LATE_FINISH_EDEFAULT == null ? lateFinish != null : !LATE_FINISH_EDEFAULT.equals(lateFinish);
			case MsprojectPackage.TASK__START_VARIANCE:
				return START_VARIANCE_EDEFAULT == null ? startVariance != null : !START_VARIANCE_EDEFAULT.equals(startVariance);
			case MsprojectPackage.TASK__FINISH_VARIANCE:
				return FINISH_VARIANCE_EDEFAULT == null ? finishVariance != null : !FINISH_VARIANCE_EDEFAULT.equals(finishVariance);
			case MsprojectPackage.TASK__WORK_VARIANCE:
				return isSetWorkVariance();
			case MsprojectPackage.TASK__FREE_SLACK:
				return FREE_SLACK_EDEFAULT == null ? freeSlack != null : !FREE_SLACK_EDEFAULT.equals(freeSlack);
			case MsprojectPackage.TASK__TOTAL_SLACK:
				return TOTAL_SLACK_EDEFAULT == null ? totalSlack != null : !TOTAL_SLACK_EDEFAULT.equals(totalSlack);
			case MsprojectPackage.TASK__FIXED_COST:
				return isSetFixedCost();
			case MsprojectPackage.TASK__FIXED_COST_ACCRUAL:
				return FIXED_COST_ACCRUAL_EDEFAULT == null ? fixedCostAccrual != null : !FIXED_COST_ACCRUAL_EDEFAULT.equals(fixedCostAccrual);
			case MsprojectPackage.TASK__PERCENT_COMPLETE:
				return PERCENT_COMPLETE_EDEFAULT == null ? percentComplete != null : !PERCENT_COMPLETE_EDEFAULT.equals(percentComplete);
			case MsprojectPackage.TASK__PERCENT_WORK_COMPLETE:
				return PERCENT_WORK_COMPLETE_EDEFAULT == null ? percentWorkComplete != null : !PERCENT_WORK_COMPLETE_EDEFAULT.equals(percentWorkComplete);
			case MsprojectPackage.TASK__COST:
				return COST_EDEFAULT == null ? cost != null : !COST_EDEFAULT.equals(cost);
			case MsprojectPackage.TASK__OVERTIME_COST:
				return OVERTIME_COST_EDEFAULT == null ? overtimeCost != null : !OVERTIME_COST_EDEFAULT.equals(overtimeCost);
			case MsprojectPackage.TASK__OVERTIME_WORK:
				return OVERTIME_WORK_EDEFAULT == null ? overtimeWork != null : !OVERTIME_WORK_EDEFAULT.equals(overtimeWork);
			case MsprojectPackage.TASK__ACTUAL_START:
				return ACTUAL_START_EDEFAULT == null ? actualStart != null : !ACTUAL_START_EDEFAULT.equals(actualStart);
			case MsprojectPackage.TASK__ACTUAL_FINISH:
				return ACTUAL_FINISH_EDEFAULT == null ? actualFinish != null : !ACTUAL_FINISH_EDEFAULT.equals(actualFinish);
			case MsprojectPackage.TASK__ACTUAL_DURATION:
				return ACTUAL_DURATION_EDEFAULT == null ? actualDuration != null : !ACTUAL_DURATION_EDEFAULT.equals(actualDuration);
			case MsprojectPackage.TASK__ACTUAL_COST:
				return ACTUAL_COST_EDEFAULT == null ? actualCost != null : !ACTUAL_COST_EDEFAULT.equals(actualCost);
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_COST:
				return ACTUAL_OVERTIME_COST_EDEFAULT == null ? actualOvertimeCost != null : !ACTUAL_OVERTIME_COST_EDEFAULT.equals(actualOvertimeCost);
			case MsprojectPackage.TASK__ACTUAL_WORK:
				return ACTUAL_WORK_EDEFAULT == null ? actualWork != null : !ACTUAL_WORK_EDEFAULT.equals(actualWork);
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK:
				return ACTUAL_OVERTIME_WORK_EDEFAULT == null ? actualOvertimeWork != null : !ACTUAL_OVERTIME_WORK_EDEFAULT.equals(actualOvertimeWork);
			case MsprojectPackage.TASK__REGULAR_WORK:
				return REGULAR_WORK_EDEFAULT == null ? regularWork != null : !REGULAR_WORK_EDEFAULT.equals(regularWork);
			case MsprojectPackage.TASK__REMAINING_DURATION:
				return REMAINING_DURATION_EDEFAULT == null ? remainingDuration != null : !REMAINING_DURATION_EDEFAULT.equals(remainingDuration);
			case MsprojectPackage.TASK__REMAINING_COST:
				return REMAINING_COST_EDEFAULT == null ? remainingCost != null : !REMAINING_COST_EDEFAULT.equals(remainingCost);
			case MsprojectPackage.TASK__REMAINING_WORK:
				return REMAINING_WORK_EDEFAULT == null ? remainingWork != null : !REMAINING_WORK_EDEFAULT.equals(remainingWork);
			case MsprojectPackage.TASK__REMAINING_OVERTIME_COST:
				return REMAINING_OVERTIME_COST_EDEFAULT == null ? remainingOvertimeCost != null : !REMAINING_OVERTIME_COST_EDEFAULT.equals(remainingOvertimeCost);
			case MsprojectPackage.TASK__REMAINING_OVERTIME_WORK:
				return REMAINING_OVERTIME_WORK_EDEFAULT == null ? remainingOvertimeWork != null : !REMAINING_OVERTIME_WORK_EDEFAULT.equals(remainingOvertimeWork);
			case MsprojectPackage.TASK__ACWP:
				return isSetACWP();
			case MsprojectPackage.TASK__CV:
				return isSetCV();
			case MsprojectPackage.TASK__CONSTRAINT_TYPE:
				return CONSTRAINT_TYPE_EDEFAULT == null ? constraintType != null : !CONSTRAINT_TYPE_EDEFAULT.equals(constraintType);
			case MsprojectPackage.TASK__CALENDAR_UID:
				return CALENDAR_UID_EDEFAULT == null ? calendarUID != null : !CALENDAR_UID_EDEFAULT.equals(calendarUID);
			case MsprojectPackage.TASK__CONSTRAINT_DATE:
				return CONSTRAINT_DATE_EDEFAULT == null ? constraintDate != null : !CONSTRAINT_DATE_EDEFAULT.equals(constraintDate);
			case MsprojectPackage.TASK__DEADLINE:
				return DEADLINE_EDEFAULT == null ? deadline != null : !DEADLINE_EDEFAULT.equals(deadline);
			case MsprojectPackage.TASK__LEVEL_ASSIGNMENTS:
				return isSetLevelAssignments();
			case MsprojectPackage.TASK__LEVELING_CAN_SPLIT:
				return isSetLevelingCanSplit();
			case MsprojectPackage.TASK__LEVELING_DELAY:
				return LEVELING_DELAY_EDEFAULT == null ? levelingDelay != null : !LEVELING_DELAY_EDEFAULT.equals(levelingDelay);
			case MsprojectPackage.TASK__LEVELING_DELAY_FORMAT:
				return LEVELING_DELAY_FORMAT_EDEFAULT == null ? levelingDelayFormat != null : !LEVELING_DELAY_FORMAT_EDEFAULT.equals(levelingDelayFormat);
			case MsprojectPackage.TASK__PRE_LEVELED_START:
				return PRE_LEVELED_START_EDEFAULT == null ? preLeveledStart != null : !PRE_LEVELED_START_EDEFAULT.equals(preLeveledStart);
			case MsprojectPackage.TASK__PRE_LEVELED_FINISH:
				return PRE_LEVELED_FINISH_EDEFAULT == null ? preLeveledFinish != null : !PRE_LEVELED_FINISH_EDEFAULT.equals(preLeveledFinish);
			case MsprojectPackage.TASK__HYPERLINK:
				return HYPERLINK_EDEFAULT == null ? hyperlink != null : !HYPERLINK_EDEFAULT.equals(hyperlink);
			case MsprojectPackage.TASK__HYPERLINK_ADDRESS:
				return HYPERLINK_ADDRESS_EDEFAULT == null ? hyperlinkAddress != null : !HYPERLINK_ADDRESS_EDEFAULT.equals(hyperlinkAddress);
			case MsprojectPackage.TASK__HYPERLINK_SUB_ADDRESS:
				return HYPERLINK_SUB_ADDRESS_EDEFAULT == null ? hyperlinkSubAddress != null : !HYPERLINK_SUB_ADDRESS_EDEFAULT.equals(hyperlinkSubAddress);
			case MsprojectPackage.TASK__IGNORE_RESOURCE_CALENDAR:
				return isSetIgnoreResourceCalendar();
			case MsprojectPackage.TASK__NOTES:
				return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
			case MsprojectPackage.TASK__HIDE_BAR:
				return isSetHideBar();
			case MsprojectPackage.TASK__ROLLUP:
				return isSetRollup();
			case MsprojectPackage.TASK__BCWS:
				return isSetBCWS();
			case MsprojectPackage.TASK__BCWP:
				return isSetBCWP();
			case MsprojectPackage.TASK__PHYSICAL_PERCENT_COMPLETE:
				return PHYSICAL_PERCENT_COMPLETE_EDEFAULT == null ? physicalPercentComplete != null : !PHYSICAL_PERCENT_COMPLETE_EDEFAULT.equals(physicalPercentComplete);
			case MsprojectPackage.TASK__EARNED_VALUE_METHOD:
				return EARNED_VALUE_METHOD_EDEFAULT == null ? earnedValueMethod != null : !EARNED_VALUE_METHOD_EDEFAULT.equals(earnedValueMethod);
			case MsprojectPackage.TASK__PREDECESSOR_LINK:
				return predecessorLink != null && !predecessorLink.isEmpty();
			case MsprojectPackage.TASK__ACTUAL_WORK_PROTECTED:
				return ACTUAL_WORK_PROTECTED_EDEFAULT == null ? actualWorkProtected != null : !ACTUAL_WORK_PROTECTED_EDEFAULT.equals(actualWorkProtected);
			case MsprojectPackage.TASK__ACTUAL_OVERTIME_WORK_PROTECTED:
				return ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT == null ? actualOvertimeWorkProtected != null : !ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT.equals(actualOvertimeWorkProtected);
			case MsprojectPackage.TASK__EXTENDED_ATTRIBUTE:
				return extendedAttribute != null && !extendedAttribute.isEmpty();
			case MsprojectPackage.TASK__BASELINE:
				return baseline != null && !baseline.isEmpty();
			case MsprojectPackage.TASK__OUTLINE_CODE:
				return outlineCode != null && !outlineCode.isEmpty();
			case MsprojectPackage.TASK__TIMEPHASED_DATA:
				return timephasedData != null && !timephasedData.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (uID: ");
		result.append(uID);
		result.append(", iD: ");
		result.append(iD);
		result.append(", name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", isNull: ");
		if (isNullESet) result.append(isNull); else result.append("<unset>");
		result.append(", createDate: ");
		result.append(createDate);
		result.append(", contact: ");
		result.append(contact);
		result.append(", wBS: ");
		result.append(wBS);
		result.append(", wBSLevel: ");
		result.append(wBSLevel);
		result.append(", outlineNumber: ");
		result.append(outlineNumber);
		result.append(", outlineLevel: ");
		result.append(outlineLevel);
		result.append(", priority: ");
		result.append(priority);
		result.append(", start: ");
		result.append(start);
		result.append(", finish: ");
		result.append(finish);
		result.append(", duration: ");
		result.append(duration);
		result.append(", durationFormat: ");
		result.append(durationFormat);
		result.append(", work: ");
		result.append(work);
		result.append(", stop: ");
		result.append(stop);
		result.append(", resume: ");
		result.append(resume);
		result.append(", resumeValid: ");
		if (resumeValidESet) result.append(resumeValid); else result.append("<unset>");
		result.append(", effortDriven: ");
		if (effortDrivenESet) result.append(effortDriven); else result.append("<unset>");
		result.append(", recurring: ");
		if (recurringESet) result.append(recurring); else result.append("<unset>");
		result.append(", overAllocated: ");
		if (overAllocatedESet) result.append(overAllocated); else result.append("<unset>");
		result.append(", estimated: ");
		if (estimatedESet) result.append(estimated); else result.append("<unset>");
		result.append(", milestone: ");
		if (milestoneESet) result.append(milestone); else result.append("<unset>");
		result.append(", summary: ");
		if (summaryESet) result.append(summary); else result.append("<unset>");
		result.append(", critical: ");
		if (criticalESet) result.append(critical); else result.append("<unset>");
		result.append(", isSubproject: ");
		if (isSubprojectESet) result.append(isSubproject); else result.append("<unset>");
		result.append(", isSubprojectReadOnly: ");
		if (isSubprojectReadOnlyESet) result.append(isSubprojectReadOnly); else result.append("<unset>");
		result.append(", subprojectName: ");
		result.append(subprojectName);
		result.append(", externalTask: ");
		if (externalTaskESet) result.append(externalTask); else result.append("<unset>");
		result.append(", externalTaskProject: ");
		result.append(externalTaskProject);
		result.append(", earlyStart: ");
		result.append(earlyStart);
		result.append(", earlyFinish: ");
		result.append(earlyFinish);
		result.append(", lateStart: ");
		result.append(lateStart);
		result.append(", lateFinish: ");
		result.append(lateFinish);
		result.append(", startVariance: ");
		result.append(startVariance);
		result.append(", finishVariance: ");
		result.append(finishVariance);
		result.append(", workVariance: ");
		if (workVarianceESet) result.append(workVariance); else result.append("<unset>");
		result.append(", freeSlack: ");
		result.append(freeSlack);
		result.append(", totalSlack: ");
		result.append(totalSlack);
		result.append(", fixedCost: ");
		if (fixedCostESet) result.append(fixedCost); else result.append("<unset>");
		result.append(", fixedCostAccrual: ");
		result.append(fixedCostAccrual);
		result.append(", percentComplete: ");
		result.append(percentComplete);
		result.append(", percentWorkComplete: ");
		result.append(percentWorkComplete);
		result.append(", cost: ");
		result.append(cost);
		result.append(", overtimeCost: ");
		result.append(overtimeCost);
		result.append(", overtimeWork: ");
		result.append(overtimeWork);
		result.append(", actualStart: ");
		result.append(actualStart);
		result.append(", actualFinish: ");
		result.append(actualFinish);
		result.append(", actualDuration: ");
		result.append(actualDuration);
		result.append(", actualCost: ");
		result.append(actualCost);
		result.append(", actualOvertimeCost: ");
		result.append(actualOvertimeCost);
		result.append(", actualWork: ");
		result.append(actualWork);
		result.append(", actualOvertimeWork: ");
		result.append(actualOvertimeWork);
		result.append(", regularWork: ");
		result.append(regularWork);
		result.append(", remainingDuration: ");
		result.append(remainingDuration);
		result.append(", remainingCost: ");
		result.append(remainingCost);
		result.append(", remainingWork: ");
		result.append(remainingWork);
		result.append(", remainingOvertimeCost: ");
		result.append(remainingOvertimeCost);
		result.append(", remainingOvertimeWork: ");
		result.append(remainingOvertimeWork);
		result.append(", aCWP: ");
		if (aCWPESet) result.append(aCWP); else result.append("<unset>");
		result.append(", cV: ");
		if (cVESet) result.append(cV); else result.append("<unset>");
		result.append(", constraintType: ");
		result.append(constraintType);
		result.append(", calendarUID: ");
		result.append(calendarUID);
		result.append(", constraintDate: ");
		result.append(constraintDate);
		result.append(", deadline: ");
		result.append(deadline);
		result.append(", levelAssignments: ");
		if (levelAssignmentsESet) result.append(levelAssignments); else result.append("<unset>");
		result.append(", levelingCanSplit: ");
		if (levelingCanSplitESet) result.append(levelingCanSplit); else result.append("<unset>");
		result.append(", levelingDelay: ");
		result.append(levelingDelay);
		result.append(", levelingDelayFormat: ");
		result.append(levelingDelayFormat);
		result.append(", preLeveledStart: ");
		result.append(preLeveledStart);
		result.append(", preLeveledFinish: ");
		result.append(preLeveledFinish);
		result.append(", hyperlink: ");
		result.append(hyperlink);
		result.append(", hyperlinkAddress: ");
		result.append(hyperlinkAddress);
		result.append(", hyperlinkSubAddress: ");
		result.append(hyperlinkSubAddress);
		result.append(", ignoreResourceCalendar: ");
		if (ignoreResourceCalendarESet) result.append(ignoreResourceCalendar); else result.append("<unset>");
		result.append(", notes: ");
		result.append(notes);
		result.append(", hideBar: ");
		if (hideBarESet) result.append(hideBar); else result.append("<unset>");
		result.append(", rollup: ");
		if (rollupESet) result.append(rollup); else result.append("<unset>");
		result.append(", bCWS: ");
		if (bCWSESet) result.append(bCWS); else result.append("<unset>");
		result.append(", bCWP: ");
		if (bCWPESet) result.append(bCWP); else result.append("<unset>");
		result.append(", physicalPercentComplete: ");
		result.append(physicalPercentComplete);
		result.append(", earnedValueMethod: ");
		result.append(earnedValueMethod);
		result.append(", actualWorkProtected: ");
		result.append(actualWorkProtected);
		result.append(", actualOvertimeWorkProtected: ");
		result.append(actualOvertimeWorkProtected);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
