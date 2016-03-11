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

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.Assignments;
import org.eclipse.epf.msproject.Calendars;
import org.eclipse.epf.msproject.ExtendedAttributes;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.OutlineCodes;
import org.eclipse.epf.msproject.Project;
import org.eclipse.epf.msproject.Resources;
import org.eclipse.epf.msproject.Tasks;
import org.eclipse.epf.msproject.WBSMasks;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getLastSaved <em>Last Saved</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isScheduleFromStart <em>Schedule From Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getFinishDate <em>Finish Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getFYStartDate <em>FY Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCriticalSlackLimit <em>Critical Slack Limit</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCurrencyDigits <em>Currency Digits</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCurrencySymbol <em>Currency Symbol</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCurrencySymbolPosition <em>Currency Symbol Position</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCalendarUID <em>Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultStartTime <em>Default Start Time</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultFinishTime <em>Default Finish Time</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getMinutesPerDay <em>Minutes Per Day</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getMinutesPerWeek <em>Minutes Per Week</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDaysPerMonth <em>Days Per Month</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultTaskType <em>Default Task Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultFixedCostAccrual <em>Default Fixed Cost Accrual</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultStandardRate <em>Default Standard Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultOvertimeRate <em>Default Overtime Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDurationFormat <em>Duration Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getWorkFormat <em>Work Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isEditableActualCosts <em>Editable Actual Costs</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isHonorConstraints <em>Honor Constraints</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getEarnedValueMethod <em>Earned Value Method</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isInsertedProjectsLikeSummary <em>Inserted Projects Like Summary</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isMultipleCriticalPaths <em>Multiple Critical Paths</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isNewTasksEffortDriven <em>New Tasks Effort Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isNewTasksEstimated <em>New Tasks Estimated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isSplitsInProgressTasks <em>Splits In Progress Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isSpreadActualCost <em>Spread Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isSpreadPercentComplete <em>Spread Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isTaskUpdatesResource <em>Task Updates Resource</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isFiscalYearStart <em>Fiscal Year Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getWeekStartDay <em>Week Start Day</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isMoveCompletedEndsBack <em>Move Completed Ends Back</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isMoveRemainingStartsBack <em>Move Remaining Starts Back</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isMoveRemainingStartsForward <em>Move Remaining Starts Forward</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isMoveCompletedEndsForward <em>Move Completed Ends Forward</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getBaselineForEarnedValue <em>Baseline For Earned Value</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isAutoAddNewResourcesAndTasks <em>Auto Add New Resources And Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getStatusDate <em>Status Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCurrentDate <em>Current Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isMicrosoftProjectServerURL <em>Microsoft Project Server URL</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isAutolink <em>Autolink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getNewTaskStartDate <em>New Task Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getDefaultTaskEVMethod <em>Default Task EV Method</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isProjectExternallyEdited <em>Project Externally Edited</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getExtendedCreationDate <em>Extended Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isActualsInSync <em>Actuals In Sync</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isRemoveFileProperties <em>Remove File Properties</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#isAdminProject <em>Admin Project</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getOutlineCodes <em>Outline Codes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getWBSMasks <em>WBS Masks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getCalendars <em>Calendars</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getTasks <em>Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ProjectImpl#getAssignments <em>Assignments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProjectImpl extends EObjectImpl implements Project {
	/**
	 * The default value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected static final String UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUID() <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUID()
	 * @generated
	 * @ordered
	 */
	protected String uID = UID_EDEFAULT;

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
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubject() <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected String subject = SUBJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected String category = CATEGORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompany() <em>Company</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompany()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPANY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompany() <em>Company</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompany()
	 * @generated
	 * @ordered
	 */
	protected String company = COMPANY_EDEFAULT;

	/**
	 * The default value of the '{@link #getManager() <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManager()
	 * @generated
	 * @ordered
	 */
	protected static final String MANAGER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getManager() <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManager()
	 * @generated
	 * @ordered
	 */
	protected String manager = MANAGER_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected String author = AUTHOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object CREATION_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected Object creationDate = CREATION_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevision() <em>Revision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevision()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger REVISION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRevision() <em>Revision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRevision()
	 * @generated
	 * @ordered
	 */
	protected BigInteger revision = REVISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastSaved() <em>Last Saved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastSaved()
	 * @generated
	 * @ordered
	 */
	protected static final Object LAST_SAVED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastSaved() <em>Last Saved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastSaved()
	 * @generated
	 * @ordered
	 */
	protected Object lastSaved = LAST_SAVED_EDEFAULT;

	/**
	 * The default value of the '{@link #isScheduleFromStart() <em>Schedule From Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScheduleFromStart()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCHEDULE_FROM_START_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isScheduleFromStart() <em>Schedule From Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScheduleFromStart()
	 * @generated
	 * @ordered
	 */
	protected boolean scheduleFromStart = SCHEDULE_FROM_START_EDEFAULT;

	/**
	 * This is true if the Schedule From Start attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean scheduleFromStartESet = false;

	/**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected Object startDate = START_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinishDate() <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object FINISH_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinishDate() <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishDate()
	 * @generated
	 * @ordered
	 */
	protected Object finishDate = FINISH_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFYStartDate() <em>FY Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFYStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger FYSTART_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFYStartDate() <em>FY Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFYStartDate()
	 * @generated
	 * @ordered
	 */
	protected BigInteger fYStartDate = FYSTART_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCriticalSlackLimit() <em>Critical Slack Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCriticalSlackLimit()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CRITICAL_SLACK_LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCriticalSlackLimit() <em>Critical Slack Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCriticalSlackLimit()
	 * @generated
	 * @ordered
	 */
	protected BigInteger criticalSlackLimit = CRITICAL_SLACK_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrencyDigits() <em>Currency Digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrencyDigits()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CURRENCY_DIGITS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrencyDigits() <em>Currency Digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrencyDigits()
	 * @generated
	 * @ordered
	 */
	protected BigInteger currencyDigits = CURRENCY_DIGITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrencySymbol() <em>Currency Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrencySymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String CURRENCY_SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrencySymbol() <em>Currency Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrencySymbol()
	 * @generated
	 * @ordered
	 */
	protected String currencySymbol = CURRENCY_SYMBOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrencySymbolPosition() <em>Currency Symbol Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrencySymbolPosition()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CURRENCY_SYMBOL_POSITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrencySymbolPosition() <em>Currency Symbol Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrencySymbolPosition()
	 * @generated
	 * @ordered
	 */
	protected BigInteger currencySymbolPosition = CURRENCY_SYMBOL_POSITION_EDEFAULT;

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
	 * The default value of the '{@link #getDefaultStartTime() <em>Default Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final Object DEFAULT_START_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultStartTime() <em>Default Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultStartTime()
	 * @generated
	 * @ordered
	 */
	protected Object defaultStartTime = DEFAULT_START_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultFinishTime() <em>Default Finish Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultFinishTime()
	 * @generated
	 * @ordered
	 */
	protected static final Object DEFAULT_FINISH_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultFinishTime() <em>Default Finish Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultFinishTime()
	 * @generated
	 * @ordered
	 */
	protected Object defaultFinishTime = DEFAULT_FINISH_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinutesPerDay() <em>Minutes Per Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinutesPerDay()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MINUTES_PER_DAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinutesPerDay() <em>Minutes Per Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinutesPerDay()
	 * @generated
	 * @ordered
	 */
	protected BigInteger minutesPerDay = MINUTES_PER_DAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinutesPerWeek() <em>Minutes Per Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinutesPerWeek()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MINUTES_PER_WEEK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinutesPerWeek() <em>Minutes Per Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinutesPerWeek()
	 * @generated
	 * @ordered
	 */
	protected BigInteger minutesPerWeek = MINUTES_PER_WEEK_EDEFAULT;

	/**
	 * The default value of the '{@link #getDaysPerMonth() <em>Days Per Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaysPerMonth()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DAYS_PER_MONTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDaysPerMonth() <em>Days Per Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaysPerMonth()
	 * @generated
	 * @ordered
	 */
	protected BigInteger daysPerMonth = DAYS_PER_MONTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultTaskType() <em>Default Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTaskType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DEFAULT_TASK_TYPE_EDEFAULT = new BigInteger("1");

	/**
	 * The cached value of the '{@link #getDefaultTaskType() <em>Default Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTaskType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger defaultTaskType = DEFAULT_TASK_TYPE_EDEFAULT;

	/**
	 * This is true if the Default Task Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean defaultTaskTypeESet = false;

	/**
	 * The default value of the '{@link #getDefaultFixedCostAccrual() <em>Default Fixed Cost Accrual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultFixedCostAccrual()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DEFAULT_FIXED_COST_ACCRUAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultFixedCostAccrual() <em>Default Fixed Cost Accrual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultFixedCostAccrual()
	 * @generated
	 * @ordered
	 */
	protected BigInteger defaultFixedCostAccrual = DEFAULT_FIXED_COST_ACCRUAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultStandardRate() <em>Default Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultStandardRate()
	 * @generated
	 * @ordered
	 */
	protected static final float DEFAULT_STANDARD_RATE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getDefaultStandardRate() <em>Default Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultStandardRate()
	 * @generated
	 * @ordered
	 */
	protected float defaultStandardRate = DEFAULT_STANDARD_RATE_EDEFAULT;

	/**
	 * This is true if the Default Standard Rate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean defaultStandardRateESet = false;

	/**
	 * The default value of the '{@link #getDefaultOvertimeRate() <em>Default Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOvertimeRate()
	 * @generated
	 * @ordered
	 */
	protected static final float DEFAULT_OVERTIME_RATE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getDefaultOvertimeRate() <em>Default Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultOvertimeRate()
	 * @generated
	 * @ordered
	 */
	protected float defaultOvertimeRate = DEFAULT_OVERTIME_RATE_EDEFAULT;

	/**
	 * This is true if the Default Overtime Rate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean defaultOvertimeRateESet = false;

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
	 * The default value of the '{@link #getWorkFormat() <em>Work Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger WORK_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkFormat() <em>Work Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger workFormat = WORK_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #isEditableActualCosts() <em>Editable Actual Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditableActualCosts()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITABLE_ACTUAL_COSTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEditableActualCosts() <em>Editable Actual Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditableActualCosts()
	 * @generated
	 * @ordered
	 */
	protected boolean editableActualCosts = EDITABLE_ACTUAL_COSTS_EDEFAULT;

	/**
	 * This is true if the Editable Actual Costs attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean editableActualCostsESet = false;

	/**
	 * The default value of the '{@link #isHonorConstraints() <em>Honor Constraints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHonorConstraints()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HONOR_CONSTRAINTS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isHonorConstraints() <em>Honor Constraints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHonorConstraints()
	 * @generated
	 * @ordered
	 */
	protected boolean honorConstraints = HONOR_CONSTRAINTS_EDEFAULT;

	/**
	 * This is true if the Honor Constraints attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean honorConstraintsESet = false;

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
	 * The default value of the '{@link #isInsertedProjectsLikeSummary() <em>Inserted Projects Like Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsertedProjectsLikeSummary()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSERTED_PROJECTS_LIKE_SUMMARY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isInsertedProjectsLikeSummary() <em>Inserted Projects Like Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsertedProjectsLikeSummary()
	 * @generated
	 * @ordered
	 */
	protected boolean insertedProjectsLikeSummary = INSERTED_PROJECTS_LIKE_SUMMARY_EDEFAULT;

	/**
	 * This is true if the Inserted Projects Like Summary attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean insertedProjectsLikeSummaryESet = false;

	/**
	 * The default value of the '{@link #isMultipleCriticalPaths() <em>Multiple Critical Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultipleCriticalPaths()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_CRITICAL_PATHS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultipleCriticalPaths() <em>Multiple Critical Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultipleCriticalPaths()
	 * @generated
	 * @ordered
	 */
	protected boolean multipleCriticalPaths = MULTIPLE_CRITICAL_PATHS_EDEFAULT;

	/**
	 * This is true if the Multiple Critical Paths attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean multipleCriticalPathsESet = false;

	/**
	 * The default value of the '{@link #isNewTasksEffortDriven() <em>New Tasks Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewTasksEffortDriven()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEW_TASKS_EFFORT_DRIVEN_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isNewTasksEffortDriven() <em>New Tasks Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewTasksEffortDriven()
	 * @generated
	 * @ordered
	 */
	protected boolean newTasksEffortDriven = NEW_TASKS_EFFORT_DRIVEN_EDEFAULT;

	/**
	 * This is true if the New Tasks Effort Driven attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean newTasksEffortDrivenESet = false;

	/**
	 * The default value of the '{@link #isNewTasksEstimated() <em>New Tasks Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewTasksEstimated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEW_TASKS_ESTIMATED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isNewTasksEstimated() <em>New Tasks Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewTasksEstimated()
	 * @generated
	 * @ordered
	 */
	protected boolean newTasksEstimated = NEW_TASKS_ESTIMATED_EDEFAULT;

	/**
	 * This is true if the New Tasks Estimated attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean newTasksEstimatedESet = false;

	/**
	 * The default value of the '{@link #isSplitsInProgressTasks() <em>Splits In Progress Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSplitsInProgressTasks()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SPLITS_IN_PROGRESS_TASKS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSplitsInProgressTasks() <em>Splits In Progress Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSplitsInProgressTasks()
	 * @generated
	 * @ordered
	 */
	protected boolean splitsInProgressTasks = SPLITS_IN_PROGRESS_TASKS_EDEFAULT;

	/**
	 * This is true if the Splits In Progress Tasks attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean splitsInProgressTasksESet = false;

	/**
	 * The default value of the '{@link #isSpreadActualCost() <em>Spread Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpreadActualCost()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SPREAD_ACTUAL_COST_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSpreadActualCost() <em>Spread Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpreadActualCost()
	 * @generated
	 * @ordered
	 */
	protected boolean spreadActualCost = SPREAD_ACTUAL_COST_EDEFAULT;

	/**
	 * This is true if the Spread Actual Cost attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean spreadActualCostESet = false;

	/**
	 * The default value of the '{@link #isSpreadPercentComplete() <em>Spread Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpreadPercentComplete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SPREAD_PERCENT_COMPLETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSpreadPercentComplete() <em>Spread Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpreadPercentComplete()
	 * @generated
	 * @ordered
	 */
	protected boolean spreadPercentComplete = SPREAD_PERCENT_COMPLETE_EDEFAULT;

	/**
	 * This is true if the Spread Percent Complete attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean spreadPercentCompleteESet = false;

	/**
	 * The default value of the '{@link #isTaskUpdatesResource() <em>Task Updates Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTaskUpdatesResource()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TASK_UPDATES_RESOURCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTaskUpdatesResource() <em>Task Updates Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTaskUpdatesResource()
	 * @generated
	 * @ordered
	 */
	protected boolean taskUpdatesResource = TASK_UPDATES_RESOURCE_EDEFAULT;

	/**
	 * This is true if the Task Updates Resource attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean taskUpdatesResourceESet = false;

	/**
	 * The default value of the '{@link #isFiscalYearStart() <em>Fiscal Year Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFiscalYearStart()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FISCAL_YEAR_START_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFiscalYearStart() <em>Fiscal Year Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFiscalYearStart()
	 * @generated
	 * @ordered
	 */
	protected boolean fiscalYearStart = FISCAL_YEAR_START_EDEFAULT;

	/**
	 * This is true if the Fiscal Year Start attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fiscalYearStartESet = false;

	/**
	 * The default value of the '{@link #getWeekStartDay() <em>Week Start Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeekStartDay()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger WEEK_START_DAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWeekStartDay() <em>Week Start Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeekStartDay()
	 * @generated
	 * @ordered
	 */
	protected BigInteger weekStartDay = WEEK_START_DAY_EDEFAULT;

	/**
	 * The default value of the '{@link #isMoveCompletedEndsBack() <em>Move Completed Ends Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveCompletedEndsBack()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVE_COMPLETED_ENDS_BACK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMoveCompletedEndsBack() <em>Move Completed Ends Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveCompletedEndsBack()
	 * @generated
	 * @ordered
	 */
	protected boolean moveCompletedEndsBack = MOVE_COMPLETED_ENDS_BACK_EDEFAULT;

	/**
	 * This is true if the Move Completed Ends Back attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean moveCompletedEndsBackESet = false;

	/**
	 * The default value of the '{@link #isMoveRemainingStartsBack() <em>Move Remaining Starts Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveRemainingStartsBack()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVE_REMAINING_STARTS_BACK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMoveRemainingStartsBack() <em>Move Remaining Starts Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveRemainingStartsBack()
	 * @generated
	 * @ordered
	 */
	protected boolean moveRemainingStartsBack = MOVE_REMAINING_STARTS_BACK_EDEFAULT;

	/**
	 * This is true if the Move Remaining Starts Back attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean moveRemainingStartsBackESet = false;

	/**
	 * The default value of the '{@link #isMoveRemainingStartsForward() <em>Move Remaining Starts Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveRemainingStartsForward()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVE_REMAINING_STARTS_FORWARD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMoveRemainingStartsForward() <em>Move Remaining Starts Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveRemainingStartsForward()
	 * @generated
	 * @ordered
	 */
	protected boolean moveRemainingStartsForward = MOVE_REMAINING_STARTS_FORWARD_EDEFAULT;

	/**
	 * This is true if the Move Remaining Starts Forward attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean moveRemainingStartsForwardESet = false;

	/**
	 * The default value of the '{@link #isMoveCompletedEndsForward() <em>Move Completed Ends Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveCompletedEndsForward()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MOVE_COMPLETED_ENDS_FORWARD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMoveCompletedEndsForward() <em>Move Completed Ends Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMoveCompletedEndsForward()
	 * @generated
	 * @ordered
	 */
	protected boolean moveCompletedEndsForward = MOVE_COMPLETED_ENDS_FORWARD_EDEFAULT;

	/**
	 * This is true if the Move Completed Ends Forward attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean moveCompletedEndsForwardESet = false;

	/**
	 * The default value of the '{@link #getBaselineForEarnedValue() <em>Baseline For Earned Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaselineForEarnedValue()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger BASELINE_FOR_EARNED_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaselineForEarnedValue() <em>Baseline For Earned Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaselineForEarnedValue()
	 * @generated
	 * @ordered
	 */
	protected BigInteger baselineForEarnedValue = BASELINE_FOR_EARNED_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAutoAddNewResourcesAndTasks() <em>Auto Add New Resources And Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoAddNewResourcesAndTasks()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_ADD_NEW_RESOURCES_AND_TASKS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAutoAddNewResourcesAndTasks() <em>Auto Add New Resources And Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoAddNewResourcesAndTasks()
	 * @generated
	 * @ordered
	 */
	protected boolean autoAddNewResourcesAndTasks = AUTO_ADD_NEW_RESOURCES_AND_TASKS_EDEFAULT;

	/**
	 * This is true if the Auto Add New Resources And Tasks attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean autoAddNewResourcesAndTasksESet = false;

	/**
	 * The default value of the '{@link #getStatusDate() <em>Status Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatusDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object STATUS_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatusDate() <em>Status Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatusDate()
	 * @generated
	 * @ordered
	 */
	protected Object statusDate = STATUS_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrentDate() <em>Current Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object CURRENT_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrentDate() <em>Current Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentDate()
	 * @generated
	 * @ordered
	 */
	protected Object currentDate = CURRENT_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isMicrosoftProjectServerURL() <em>Microsoft Project Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMicrosoftProjectServerURL()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MICROSOFT_PROJECT_SERVER_URL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMicrosoftProjectServerURL() <em>Microsoft Project Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMicrosoftProjectServerURL()
	 * @generated
	 * @ordered
	 */
	protected boolean microsoftProjectServerURL = MICROSOFT_PROJECT_SERVER_URL_EDEFAULT;

	/**
	 * This is true if the Microsoft Project Server URL attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean microsoftProjectServerURLESet = false;

	/**
	 * The default value of the '{@link #isAutolink() <em>Autolink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutolink()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTOLINK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutolink() <em>Autolink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutolink()
	 * @generated
	 * @ordered
	 */
	protected boolean autolink = AUTOLINK_EDEFAULT;

	/**
	 * This is true if the Autolink attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean autolinkESet = false;

	/**
	 * The default value of the '{@link #getNewTaskStartDate() <em>New Task Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewTaskStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger NEW_TASK_START_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNewTaskStartDate() <em>New Task Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewTaskStartDate()
	 * @generated
	 * @ordered
	 */
	protected BigInteger newTaskStartDate = NEW_TASK_START_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultTaskEVMethod() <em>Default Task EV Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTaskEVMethod()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DEFAULT_TASK_EV_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultTaskEVMethod() <em>Default Task EV Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTaskEVMethod()
	 * @generated
	 * @ordered
	 */
	protected BigInteger defaultTaskEVMethod = DEFAULT_TASK_EV_METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #isProjectExternallyEdited() <em>Project Externally Edited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProjectExternallyEdited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PROJECT_EXTERNALLY_EDITED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isProjectExternallyEdited() <em>Project Externally Edited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProjectExternallyEdited()
	 * @generated
	 * @ordered
	 */
	protected boolean projectExternallyEdited = PROJECT_EXTERNALLY_EDITED_EDEFAULT;

	/**
	 * This is true if the Project Externally Edited attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean projectExternallyEditedESet = false;

	/**
	 * The default value of the '{@link #getExtendedCreationDate() <em>Extended Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedCreationDate()
	 * @generated
	 * @ordered
	 */
	protected static final Object EXTENDED_CREATION_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtendedCreationDate() <em>Extended Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedCreationDate()
	 * @generated
	 * @ordered
	 */
	protected Object extendedCreationDate = EXTENDED_CREATION_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isActualsInSync() <em>Actuals In Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActualsInSync()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTUALS_IN_SYNC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActualsInSync() <em>Actuals In Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActualsInSync()
	 * @generated
	 * @ordered
	 */
	protected boolean actualsInSync = ACTUALS_IN_SYNC_EDEFAULT;

	/**
	 * This is true if the Actuals In Sync attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean actualsInSyncESet = false;

	/**
	 * The default value of the '{@link #isRemoveFileProperties() <em>Remove File Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRemoveFileProperties()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REMOVE_FILE_PROPERTIES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRemoveFileProperties() <em>Remove File Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRemoveFileProperties()
	 * @generated
	 * @ordered
	 */
	protected boolean removeFileProperties = REMOVE_FILE_PROPERTIES_EDEFAULT;

	/**
	 * This is true if the Remove File Properties attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean removeFilePropertiesESet = false;

	/**
	 * The default value of the '{@link #isAdminProject() <em>Admin Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAdminProject()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ADMIN_PROJECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAdminProject() <em>Admin Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAdminProject()
	 * @generated
	 * @ordered
	 */
	protected boolean adminProject = ADMIN_PROJECT_EDEFAULT;

	/**
	 * This is true if the Admin Project attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean adminProjectESet = false;

	/**
	 * The cached value of the '{@link #getOutlineCodes() <em>Outline Codes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutlineCodes()
	 * @generated
	 * @ordered
	 */
	protected OutlineCodes outlineCodes = null;

	/**
	 * The cached value of the '{@link #getWBSMasks() <em>WBS Masks</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWBSMasks()
	 * @generated
	 * @ordered
	 */
	protected WBSMasks wBSMasks = null;

	/**
	 * The cached value of the '{@link #getExtendedAttributes() <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedAttributes()
	 * @generated
	 * @ordered
	 */
	protected ExtendedAttributes extendedAttributes = null;

	/**
	 * The cached value of the '{@link #getCalendars() <em>Calendars</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalendars()
	 * @generated
	 * @ordered
	 */
	protected Calendars calendars = null;

	/**
	 * The cached value of the '{@link #getTasks() <em>Tasks</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTasks()
	 * @generated
	 * @ordered
	 */
	protected Tasks tasks = null;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected Resources resources = null;

	/**
	 * The cached value of the '{@link #getAssignments() <em>Assignments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignments()
	 * @generated
	 * @ordered
	 */
	protected Assignments assignments = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getProject();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUID() {
		return uID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUID(String newUID) {
		String oldUID = uID;
		uID = newUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__UID, oldUID, uID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubject(String newSubject) {
		String oldSubject = subject;
		subject = newSubject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__SUBJECT, oldSubject, subject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategory(String newCategory) {
		String oldCategory = category;
		category = newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CATEGORY, oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompany(String newCompany) {
		String oldCompany = company;
		company = newCompany;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__COMPANY, oldCompany, company));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getManager() {
		return manager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManager(String newManager) {
		String oldManager = manager;
		manager = newManager;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MANAGER, oldManager, manager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__AUTHOR, oldAuthor, author));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getCreationDate() {
		return creationDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationDate(Object newCreationDate) {
		Object oldCreationDate = creationDate;
		creationDate = newCreationDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getRevision() {
		return revision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevision(BigInteger newRevision) {
		BigInteger oldRevision = revision;
		revision = newRevision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__REVISION, oldRevision, revision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getLastSaved() {
		return lastSaved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastSaved(Object newLastSaved) {
		Object oldLastSaved = lastSaved;
		lastSaved = newLastSaved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__LAST_SAVED, oldLastSaved, lastSaved));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScheduleFromStart() {
		return scheduleFromStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScheduleFromStart(boolean newScheduleFromStart) {
		boolean oldScheduleFromStart = scheduleFromStart;
		scheduleFromStart = newScheduleFromStart;
		boolean oldScheduleFromStartESet = scheduleFromStartESet;
		scheduleFromStartESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__SCHEDULE_FROM_START, oldScheduleFromStart, scheduleFromStart, !oldScheduleFromStartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetScheduleFromStart() {
		boolean oldScheduleFromStart = scheduleFromStart;
		boolean oldScheduleFromStartESet = scheduleFromStartESet;
		scheduleFromStart = SCHEDULE_FROM_START_EDEFAULT;
		scheduleFromStartESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__SCHEDULE_FROM_START, oldScheduleFromStart, SCHEDULE_FROM_START_EDEFAULT, oldScheduleFromStartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetScheduleFromStart() {
		return scheduleFromStartESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getStartDate() {
		return startDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(Object newStartDate) {
		Object oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__START_DATE, oldStartDate, startDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getFinishDate() {
		return finishDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinishDate(Object newFinishDate) {
		Object oldFinishDate = finishDate;
		finishDate = newFinishDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__FINISH_DATE, oldFinishDate, finishDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getFYStartDate() {
		return fYStartDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFYStartDate(BigInteger newFYStartDate) {
		BigInteger oldFYStartDate = fYStartDate;
		fYStartDate = newFYStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__FYSTART_DATE, oldFYStartDate, fYStartDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCriticalSlackLimit() {
		return criticalSlackLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCriticalSlackLimit(BigInteger newCriticalSlackLimit) {
		BigInteger oldCriticalSlackLimit = criticalSlackLimit;
		criticalSlackLimit = newCriticalSlackLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CRITICAL_SLACK_LIMIT, oldCriticalSlackLimit, criticalSlackLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCurrencyDigits() {
		return currencyDigits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrencyDigits(BigInteger newCurrencyDigits) {
		BigInteger oldCurrencyDigits = currencyDigits;
		currencyDigits = newCurrencyDigits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CURRENCY_DIGITS, oldCurrencyDigits, currencyDigits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrencySymbol(String newCurrencySymbol) {
		String oldCurrencySymbol = currencySymbol;
		currencySymbol = newCurrencySymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CURRENCY_SYMBOL, oldCurrencySymbol, currencySymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCurrencySymbolPosition() {
		return currencySymbolPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrencySymbolPosition(BigInteger newCurrencySymbolPosition) {
		BigInteger oldCurrencySymbolPosition = currencySymbolPosition;
		currencySymbolPosition = newCurrencySymbolPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CURRENCY_SYMBOL_POSITION, oldCurrencySymbolPosition, currencySymbolPosition));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CALENDAR_UID, oldCalendarUID, calendarUID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getDefaultStartTime() {
		return defaultStartTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultStartTime(Object newDefaultStartTime) {
		Object oldDefaultStartTime = defaultStartTime;
		defaultStartTime = newDefaultStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_START_TIME, oldDefaultStartTime, defaultStartTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getDefaultFinishTime() {
		return defaultFinishTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultFinishTime(Object newDefaultFinishTime) {
		Object oldDefaultFinishTime = defaultFinishTime;
		defaultFinishTime = newDefaultFinishTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_FINISH_TIME, oldDefaultFinishTime, defaultFinishTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMinutesPerDay() {
		return minutesPerDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinutesPerDay(BigInteger newMinutesPerDay) {
		BigInteger oldMinutesPerDay = minutesPerDay;
		minutesPerDay = newMinutesPerDay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MINUTES_PER_DAY, oldMinutesPerDay, minutesPerDay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMinutesPerWeek() {
		return minutesPerWeek;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinutesPerWeek(BigInteger newMinutesPerWeek) {
		BigInteger oldMinutesPerWeek = minutesPerWeek;
		minutesPerWeek = newMinutesPerWeek;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MINUTES_PER_WEEK, oldMinutesPerWeek, minutesPerWeek));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDaysPerMonth() {
		return daysPerMonth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDaysPerMonth(BigInteger newDaysPerMonth) {
		BigInteger oldDaysPerMonth = daysPerMonth;
		daysPerMonth = newDaysPerMonth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DAYS_PER_MONTH, oldDaysPerMonth, daysPerMonth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDefaultTaskType() {
		return defaultTaskType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultTaskType(BigInteger newDefaultTaskType) {
		BigInteger oldDefaultTaskType = defaultTaskType;
		defaultTaskType = newDefaultTaskType;
		boolean oldDefaultTaskTypeESet = defaultTaskTypeESet;
		defaultTaskTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_TASK_TYPE, oldDefaultTaskType, defaultTaskType, !oldDefaultTaskTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDefaultTaskType() {
		BigInteger oldDefaultTaskType = defaultTaskType;
		boolean oldDefaultTaskTypeESet = defaultTaskTypeESet;
		defaultTaskType = DEFAULT_TASK_TYPE_EDEFAULT;
		defaultTaskTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__DEFAULT_TASK_TYPE, oldDefaultTaskType, DEFAULT_TASK_TYPE_EDEFAULT, oldDefaultTaskTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDefaultTaskType() {
		return defaultTaskTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDefaultFixedCostAccrual() {
		return defaultFixedCostAccrual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultFixedCostAccrual(BigInteger newDefaultFixedCostAccrual) {
		BigInteger oldDefaultFixedCostAccrual = defaultFixedCostAccrual;
		defaultFixedCostAccrual = newDefaultFixedCostAccrual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_FIXED_COST_ACCRUAL, oldDefaultFixedCostAccrual, defaultFixedCostAccrual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getDefaultStandardRate() {
		return defaultStandardRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultStandardRate(float newDefaultStandardRate) {
		float oldDefaultStandardRate = defaultStandardRate;
		defaultStandardRate = newDefaultStandardRate;
		boolean oldDefaultStandardRateESet = defaultStandardRateESet;
		defaultStandardRateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_STANDARD_RATE, oldDefaultStandardRate, defaultStandardRate, !oldDefaultStandardRateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDefaultStandardRate() {
		float oldDefaultStandardRate = defaultStandardRate;
		boolean oldDefaultStandardRateESet = defaultStandardRateESet;
		defaultStandardRate = DEFAULT_STANDARD_RATE_EDEFAULT;
		defaultStandardRateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__DEFAULT_STANDARD_RATE, oldDefaultStandardRate, DEFAULT_STANDARD_RATE_EDEFAULT, oldDefaultStandardRateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDefaultStandardRate() {
		return defaultStandardRateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getDefaultOvertimeRate() {
		return defaultOvertimeRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultOvertimeRate(float newDefaultOvertimeRate) {
		float oldDefaultOvertimeRate = defaultOvertimeRate;
		defaultOvertimeRate = newDefaultOvertimeRate;
		boolean oldDefaultOvertimeRateESet = defaultOvertimeRateESet;
		defaultOvertimeRateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_OVERTIME_RATE, oldDefaultOvertimeRate, defaultOvertimeRate, !oldDefaultOvertimeRateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDefaultOvertimeRate() {
		float oldDefaultOvertimeRate = defaultOvertimeRate;
		boolean oldDefaultOvertimeRateESet = defaultOvertimeRateESet;
		defaultOvertimeRate = DEFAULT_OVERTIME_RATE_EDEFAULT;
		defaultOvertimeRateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__DEFAULT_OVERTIME_RATE, oldDefaultOvertimeRate, DEFAULT_OVERTIME_RATE_EDEFAULT, oldDefaultOvertimeRateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDefaultOvertimeRate() {
		return defaultOvertimeRateESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DURATION_FORMAT, oldDurationFormat, durationFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getWorkFormat() {
		return workFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkFormat(BigInteger newWorkFormat) {
		BigInteger oldWorkFormat = workFormat;
		workFormat = newWorkFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__WORK_FORMAT, oldWorkFormat, workFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEditableActualCosts() {
		return editableActualCosts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditableActualCosts(boolean newEditableActualCosts) {
		boolean oldEditableActualCosts = editableActualCosts;
		editableActualCosts = newEditableActualCosts;
		boolean oldEditableActualCostsESet = editableActualCostsESet;
		editableActualCostsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__EDITABLE_ACTUAL_COSTS, oldEditableActualCosts, editableActualCosts, !oldEditableActualCostsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEditableActualCosts() {
		boolean oldEditableActualCosts = editableActualCosts;
		boolean oldEditableActualCostsESet = editableActualCostsESet;
		editableActualCosts = EDITABLE_ACTUAL_COSTS_EDEFAULT;
		editableActualCostsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__EDITABLE_ACTUAL_COSTS, oldEditableActualCosts, EDITABLE_ACTUAL_COSTS_EDEFAULT, oldEditableActualCostsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEditableActualCosts() {
		return editableActualCostsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHonorConstraints() {
		return honorConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHonorConstraints(boolean newHonorConstraints) {
		boolean oldHonorConstraints = honorConstraints;
		honorConstraints = newHonorConstraints;
		boolean oldHonorConstraintsESet = honorConstraintsESet;
		honorConstraintsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__HONOR_CONSTRAINTS, oldHonorConstraints, honorConstraints, !oldHonorConstraintsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHonorConstraints() {
		boolean oldHonorConstraints = honorConstraints;
		boolean oldHonorConstraintsESet = honorConstraintsESet;
		honorConstraints = HONOR_CONSTRAINTS_EDEFAULT;
		honorConstraintsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__HONOR_CONSTRAINTS, oldHonorConstraints, HONOR_CONSTRAINTS_EDEFAULT, oldHonorConstraintsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHonorConstraints() {
		return honorConstraintsESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__EARNED_VALUE_METHOD, oldEarnedValueMethod, earnedValueMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInsertedProjectsLikeSummary() {
		return insertedProjectsLikeSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInsertedProjectsLikeSummary(boolean newInsertedProjectsLikeSummary) {
		boolean oldInsertedProjectsLikeSummary = insertedProjectsLikeSummary;
		insertedProjectsLikeSummary = newInsertedProjectsLikeSummary;
		boolean oldInsertedProjectsLikeSummaryESet = insertedProjectsLikeSummaryESet;
		insertedProjectsLikeSummaryESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY, oldInsertedProjectsLikeSummary, insertedProjectsLikeSummary, !oldInsertedProjectsLikeSummaryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInsertedProjectsLikeSummary() {
		boolean oldInsertedProjectsLikeSummary = insertedProjectsLikeSummary;
		boolean oldInsertedProjectsLikeSummaryESet = insertedProjectsLikeSummaryESet;
		insertedProjectsLikeSummary = INSERTED_PROJECTS_LIKE_SUMMARY_EDEFAULT;
		insertedProjectsLikeSummaryESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY, oldInsertedProjectsLikeSummary, INSERTED_PROJECTS_LIKE_SUMMARY_EDEFAULT, oldInsertedProjectsLikeSummaryESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInsertedProjectsLikeSummary() {
		return insertedProjectsLikeSummaryESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultipleCriticalPaths() {
		return multipleCriticalPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultipleCriticalPaths(boolean newMultipleCriticalPaths) {
		boolean oldMultipleCriticalPaths = multipleCriticalPaths;
		multipleCriticalPaths = newMultipleCriticalPaths;
		boolean oldMultipleCriticalPathsESet = multipleCriticalPathsESet;
		multipleCriticalPathsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MULTIPLE_CRITICAL_PATHS, oldMultipleCriticalPaths, multipleCriticalPaths, !oldMultipleCriticalPathsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMultipleCriticalPaths() {
		boolean oldMultipleCriticalPaths = multipleCriticalPaths;
		boolean oldMultipleCriticalPathsESet = multipleCriticalPathsESet;
		multipleCriticalPaths = MULTIPLE_CRITICAL_PATHS_EDEFAULT;
		multipleCriticalPathsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__MULTIPLE_CRITICAL_PATHS, oldMultipleCriticalPaths, MULTIPLE_CRITICAL_PATHS_EDEFAULT, oldMultipleCriticalPathsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMultipleCriticalPaths() {
		return multipleCriticalPathsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNewTasksEffortDriven() {
		return newTasksEffortDriven;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewTasksEffortDriven(boolean newNewTasksEffortDriven) {
		boolean oldNewTasksEffortDriven = newTasksEffortDriven;
		newTasksEffortDriven = newNewTasksEffortDriven;
		boolean oldNewTasksEffortDrivenESet = newTasksEffortDrivenESet;
		newTasksEffortDrivenESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__NEW_TASKS_EFFORT_DRIVEN, oldNewTasksEffortDriven, newTasksEffortDriven, !oldNewTasksEffortDrivenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetNewTasksEffortDriven() {
		boolean oldNewTasksEffortDriven = newTasksEffortDriven;
		boolean oldNewTasksEffortDrivenESet = newTasksEffortDrivenESet;
		newTasksEffortDriven = NEW_TASKS_EFFORT_DRIVEN_EDEFAULT;
		newTasksEffortDrivenESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__NEW_TASKS_EFFORT_DRIVEN, oldNewTasksEffortDriven, NEW_TASKS_EFFORT_DRIVEN_EDEFAULT, oldNewTasksEffortDrivenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetNewTasksEffortDriven() {
		return newTasksEffortDrivenESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNewTasksEstimated() {
		return newTasksEstimated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewTasksEstimated(boolean newNewTasksEstimated) {
		boolean oldNewTasksEstimated = newTasksEstimated;
		newTasksEstimated = newNewTasksEstimated;
		boolean oldNewTasksEstimatedESet = newTasksEstimatedESet;
		newTasksEstimatedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__NEW_TASKS_ESTIMATED, oldNewTasksEstimated, newTasksEstimated, !oldNewTasksEstimatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetNewTasksEstimated() {
		boolean oldNewTasksEstimated = newTasksEstimated;
		boolean oldNewTasksEstimatedESet = newTasksEstimatedESet;
		newTasksEstimated = NEW_TASKS_ESTIMATED_EDEFAULT;
		newTasksEstimatedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__NEW_TASKS_ESTIMATED, oldNewTasksEstimated, NEW_TASKS_ESTIMATED_EDEFAULT, oldNewTasksEstimatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetNewTasksEstimated() {
		return newTasksEstimatedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSplitsInProgressTasks() {
		return splitsInProgressTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplitsInProgressTasks(boolean newSplitsInProgressTasks) {
		boolean oldSplitsInProgressTasks = splitsInProgressTasks;
		splitsInProgressTasks = newSplitsInProgressTasks;
		boolean oldSplitsInProgressTasksESet = splitsInProgressTasksESet;
		splitsInProgressTasksESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__SPLITS_IN_PROGRESS_TASKS, oldSplitsInProgressTasks, splitsInProgressTasks, !oldSplitsInProgressTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSplitsInProgressTasks() {
		boolean oldSplitsInProgressTasks = splitsInProgressTasks;
		boolean oldSplitsInProgressTasksESet = splitsInProgressTasksESet;
		splitsInProgressTasks = SPLITS_IN_PROGRESS_TASKS_EDEFAULT;
		splitsInProgressTasksESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__SPLITS_IN_PROGRESS_TASKS, oldSplitsInProgressTasks, SPLITS_IN_PROGRESS_TASKS_EDEFAULT, oldSplitsInProgressTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSplitsInProgressTasks() {
		return splitsInProgressTasksESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSpreadActualCost() {
		return spreadActualCost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpreadActualCost(boolean newSpreadActualCost) {
		boolean oldSpreadActualCost = spreadActualCost;
		spreadActualCost = newSpreadActualCost;
		boolean oldSpreadActualCostESet = spreadActualCostESet;
		spreadActualCostESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__SPREAD_ACTUAL_COST, oldSpreadActualCost, spreadActualCost, !oldSpreadActualCostESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSpreadActualCost() {
		boolean oldSpreadActualCost = spreadActualCost;
		boolean oldSpreadActualCostESet = spreadActualCostESet;
		spreadActualCost = SPREAD_ACTUAL_COST_EDEFAULT;
		spreadActualCostESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__SPREAD_ACTUAL_COST, oldSpreadActualCost, SPREAD_ACTUAL_COST_EDEFAULT, oldSpreadActualCostESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSpreadActualCost() {
		return spreadActualCostESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSpreadPercentComplete() {
		return spreadPercentComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpreadPercentComplete(boolean newSpreadPercentComplete) {
		boolean oldSpreadPercentComplete = spreadPercentComplete;
		spreadPercentComplete = newSpreadPercentComplete;
		boolean oldSpreadPercentCompleteESet = spreadPercentCompleteESet;
		spreadPercentCompleteESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__SPREAD_PERCENT_COMPLETE, oldSpreadPercentComplete, spreadPercentComplete, !oldSpreadPercentCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSpreadPercentComplete() {
		boolean oldSpreadPercentComplete = spreadPercentComplete;
		boolean oldSpreadPercentCompleteESet = spreadPercentCompleteESet;
		spreadPercentComplete = SPREAD_PERCENT_COMPLETE_EDEFAULT;
		spreadPercentCompleteESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__SPREAD_PERCENT_COMPLETE, oldSpreadPercentComplete, SPREAD_PERCENT_COMPLETE_EDEFAULT, oldSpreadPercentCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSpreadPercentComplete() {
		return spreadPercentCompleteESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTaskUpdatesResource() {
		return taskUpdatesResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskUpdatesResource(boolean newTaskUpdatesResource) {
		boolean oldTaskUpdatesResource = taskUpdatesResource;
		taskUpdatesResource = newTaskUpdatesResource;
		boolean oldTaskUpdatesResourceESet = taskUpdatesResourceESet;
		taskUpdatesResourceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__TASK_UPDATES_RESOURCE, oldTaskUpdatesResource, taskUpdatesResource, !oldTaskUpdatesResourceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTaskUpdatesResource() {
		boolean oldTaskUpdatesResource = taskUpdatesResource;
		boolean oldTaskUpdatesResourceESet = taskUpdatesResourceESet;
		taskUpdatesResource = TASK_UPDATES_RESOURCE_EDEFAULT;
		taskUpdatesResourceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__TASK_UPDATES_RESOURCE, oldTaskUpdatesResource, TASK_UPDATES_RESOURCE_EDEFAULT, oldTaskUpdatesResourceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTaskUpdatesResource() {
		return taskUpdatesResourceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFiscalYearStart() {
		return fiscalYearStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFiscalYearStart(boolean newFiscalYearStart) {
		boolean oldFiscalYearStart = fiscalYearStart;
		fiscalYearStart = newFiscalYearStart;
		boolean oldFiscalYearStartESet = fiscalYearStartESet;
		fiscalYearStartESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__FISCAL_YEAR_START, oldFiscalYearStart, fiscalYearStart, !oldFiscalYearStartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFiscalYearStart() {
		boolean oldFiscalYearStart = fiscalYearStart;
		boolean oldFiscalYearStartESet = fiscalYearStartESet;
		fiscalYearStart = FISCAL_YEAR_START_EDEFAULT;
		fiscalYearStartESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__FISCAL_YEAR_START, oldFiscalYearStart, FISCAL_YEAR_START_EDEFAULT, oldFiscalYearStartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFiscalYearStart() {
		return fiscalYearStartESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getWeekStartDay() {
		return weekStartDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeekStartDay(BigInteger newWeekStartDay) {
		BigInteger oldWeekStartDay = weekStartDay;
		weekStartDay = newWeekStartDay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__WEEK_START_DAY, oldWeekStartDay, weekStartDay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMoveCompletedEndsBack() {
		return moveCompletedEndsBack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMoveCompletedEndsBack(boolean newMoveCompletedEndsBack) {
		boolean oldMoveCompletedEndsBack = moveCompletedEndsBack;
		moveCompletedEndsBack = newMoveCompletedEndsBack;
		boolean oldMoveCompletedEndsBackESet = moveCompletedEndsBackESet;
		moveCompletedEndsBackESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_BACK, oldMoveCompletedEndsBack, moveCompletedEndsBack, !oldMoveCompletedEndsBackESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMoveCompletedEndsBack() {
		boolean oldMoveCompletedEndsBack = moveCompletedEndsBack;
		boolean oldMoveCompletedEndsBackESet = moveCompletedEndsBackESet;
		moveCompletedEndsBack = MOVE_COMPLETED_ENDS_BACK_EDEFAULT;
		moveCompletedEndsBackESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_BACK, oldMoveCompletedEndsBack, MOVE_COMPLETED_ENDS_BACK_EDEFAULT, oldMoveCompletedEndsBackESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMoveCompletedEndsBack() {
		return moveCompletedEndsBackESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMoveRemainingStartsBack() {
		return moveRemainingStartsBack;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMoveRemainingStartsBack(boolean newMoveRemainingStartsBack) {
		boolean oldMoveRemainingStartsBack = moveRemainingStartsBack;
		moveRemainingStartsBack = newMoveRemainingStartsBack;
		boolean oldMoveRemainingStartsBackESet = moveRemainingStartsBackESet;
		moveRemainingStartsBackESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_BACK, oldMoveRemainingStartsBack, moveRemainingStartsBack, !oldMoveRemainingStartsBackESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMoveRemainingStartsBack() {
		boolean oldMoveRemainingStartsBack = moveRemainingStartsBack;
		boolean oldMoveRemainingStartsBackESet = moveRemainingStartsBackESet;
		moveRemainingStartsBack = MOVE_REMAINING_STARTS_BACK_EDEFAULT;
		moveRemainingStartsBackESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_BACK, oldMoveRemainingStartsBack, MOVE_REMAINING_STARTS_BACK_EDEFAULT, oldMoveRemainingStartsBackESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMoveRemainingStartsBack() {
		return moveRemainingStartsBackESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMoveRemainingStartsForward() {
		return moveRemainingStartsForward;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMoveRemainingStartsForward(boolean newMoveRemainingStartsForward) {
		boolean oldMoveRemainingStartsForward = moveRemainingStartsForward;
		moveRemainingStartsForward = newMoveRemainingStartsForward;
		boolean oldMoveRemainingStartsForwardESet = moveRemainingStartsForwardESet;
		moveRemainingStartsForwardESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_FORWARD, oldMoveRemainingStartsForward, moveRemainingStartsForward, !oldMoveRemainingStartsForwardESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMoveRemainingStartsForward() {
		boolean oldMoveRemainingStartsForward = moveRemainingStartsForward;
		boolean oldMoveRemainingStartsForwardESet = moveRemainingStartsForwardESet;
		moveRemainingStartsForward = MOVE_REMAINING_STARTS_FORWARD_EDEFAULT;
		moveRemainingStartsForwardESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_FORWARD, oldMoveRemainingStartsForward, MOVE_REMAINING_STARTS_FORWARD_EDEFAULT, oldMoveRemainingStartsForwardESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMoveRemainingStartsForward() {
		return moveRemainingStartsForwardESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMoveCompletedEndsForward() {
		return moveCompletedEndsForward;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMoveCompletedEndsForward(boolean newMoveCompletedEndsForward) {
		boolean oldMoveCompletedEndsForward = moveCompletedEndsForward;
		moveCompletedEndsForward = newMoveCompletedEndsForward;
		boolean oldMoveCompletedEndsForwardESet = moveCompletedEndsForwardESet;
		moveCompletedEndsForwardESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_FORWARD, oldMoveCompletedEndsForward, moveCompletedEndsForward, !oldMoveCompletedEndsForwardESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMoveCompletedEndsForward() {
		boolean oldMoveCompletedEndsForward = moveCompletedEndsForward;
		boolean oldMoveCompletedEndsForwardESet = moveCompletedEndsForwardESet;
		moveCompletedEndsForward = MOVE_COMPLETED_ENDS_FORWARD_EDEFAULT;
		moveCompletedEndsForwardESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_FORWARD, oldMoveCompletedEndsForward, MOVE_COMPLETED_ENDS_FORWARD_EDEFAULT, oldMoveCompletedEndsForwardESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMoveCompletedEndsForward() {
		return moveCompletedEndsForwardESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBaselineForEarnedValue() {
		return baselineForEarnedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaselineForEarnedValue(BigInteger newBaselineForEarnedValue) {
		BigInteger oldBaselineForEarnedValue = baselineForEarnedValue;
		baselineForEarnedValue = newBaselineForEarnedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__BASELINE_FOR_EARNED_VALUE, oldBaselineForEarnedValue, baselineForEarnedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoAddNewResourcesAndTasks() {
		return autoAddNewResourcesAndTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoAddNewResourcesAndTasks(boolean newAutoAddNewResourcesAndTasks) {
		boolean oldAutoAddNewResourcesAndTasks = autoAddNewResourcesAndTasks;
		autoAddNewResourcesAndTasks = newAutoAddNewResourcesAndTasks;
		boolean oldAutoAddNewResourcesAndTasksESet = autoAddNewResourcesAndTasksESet;
		autoAddNewResourcesAndTasksESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS, oldAutoAddNewResourcesAndTasks, autoAddNewResourcesAndTasks, !oldAutoAddNewResourcesAndTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAutoAddNewResourcesAndTasks() {
		boolean oldAutoAddNewResourcesAndTasks = autoAddNewResourcesAndTasks;
		boolean oldAutoAddNewResourcesAndTasksESet = autoAddNewResourcesAndTasksESet;
		autoAddNewResourcesAndTasks = AUTO_ADD_NEW_RESOURCES_AND_TASKS_EDEFAULT;
		autoAddNewResourcesAndTasksESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS, oldAutoAddNewResourcesAndTasks, AUTO_ADD_NEW_RESOURCES_AND_TASKS_EDEFAULT, oldAutoAddNewResourcesAndTasksESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAutoAddNewResourcesAndTasks() {
		return autoAddNewResourcesAndTasksESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getStatusDate() {
		return statusDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatusDate(Object newStatusDate) {
		Object oldStatusDate = statusDate;
		statusDate = newStatusDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__STATUS_DATE, oldStatusDate, statusDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getCurrentDate() {
		return currentDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentDate(Object newCurrentDate) {
		Object oldCurrentDate = currentDate;
		currentDate = newCurrentDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CURRENT_DATE, oldCurrentDate, currentDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMicrosoftProjectServerURL() {
		return microsoftProjectServerURL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMicrosoftProjectServerURL(boolean newMicrosoftProjectServerURL) {
		boolean oldMicrosoftProjectServerURL = microsoftProjectServerURL;
		microsoftProjectServerURL = newMicrosoftProjectServerURL;
		boolean oldMicrosoftProjectServerURLESet = microsoftProjectServerURLESet;
		microsoftProjectServerURLESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__MICROSOFT_PROJECT_SERVER_URL, oldMicrosoftProjectServerURL, microsoftProjectServerURL, !oldMicrosoftProjectServerURLESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMicrosoftProjectServerURL() {
		boolean oldMicrosoftProjectServerURL = microsoftProjectServerURL;
		boolean oldMicrosoftProjectServerURLESet = microsoftProjectServerURLESet;
		microsoftProjectServerURL = MICROSOFT_PROJECT_SERVER_URL_EDEFAULT;
		microsoftProjectServerURLESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__MICROSOFT_PROJECT_SERVER_URL, oldMicrosoftProjectServerURL, MICROSOFT_PROJECT_SERVER_URL_EDEFAULT, oldMicrosoftProjectServerURLESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMicrosoftProjectServerURL() {
		return microsoftProjectServerURLESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutolink() {
		return autolink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutolink(boolean newAutolink) {
		boolean oldAutolink = autolink;
		autolink = newAutolink;
		boolean oldAutolinkESet = autolinkESet;
		autolinkESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__AUTOLINK, oldAutolink, autolink, !oldAutolinkESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAutolink() {
		boolean oldAutolink = autolink;
		boolean oldAutolinkESet = autolinkESet;
		autolink = AUTOLINK_EDEFAULT;
		autolinkESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__AUTOLINK, oldAutolink, AUTOLINK_EDEFAULT, oldAutolinkESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAutolink() {
		return autolinkESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getNewTaskStartDate() {
		return newTaskStartDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewTaskStartDate(BigInteger newNewTaskStartDate) {
		BigInteger oldNewTaskStartDate = newTaskStartDate;
		newTaskStartDate = newNewTaskStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__NEW_TASK_START_DATE, oldNewTaskStartDate, newTaskStartDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getDefaultTaskEVMethod() {
		return defaultTaskEVMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultTaskEVMethod(BigInteger newDefaultTaskEVMethod) {
		BigInteger oldDefaultTaskEVMethod = defaultTaskEVMethod;
		defaultTaskEVMethod = newDefaultTaskEVMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__DEFAULT_TASK_EV_METHOD, oldDefaultTaskEVMethod, defaultTaskEVMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProjectExternallyEdited() {
		return projectExternallyEdited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProjectExternallyEdited(boolean newProjectExternallyEdited) {
		boolean oldProjectExternallyEdited = projectExternallyEdited;
		projectExternallyEdited = newProjectExternallyEdited;
		boolean oldProjectExternallyEditedESet = projectExternallyEditedESet;
		projectExternallyEditedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__PROJECT_EXTERNALLY_EDITED, oldProjectExternallyEdited, projectExternallyEdited, !oldProjectExternallyEditedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetProjectExternallyEdited() {
		boolean oldProjectExternallyEdited = projectExternallyEdited;
		boolean oldProjectExternallyEditedESet = projectExternallyEditedESet;
		projectExternallyEdited = PROJECT_EXTERNALLY_EDITED_EDEFAULT;
		projectExternallyEditedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__PROJECT_EXTERNALLY_EDITED, oldProjectExternallyEdited, PROJECT_EXTERNALLY_EDITED_EDEFAULT, oldProjectExternallyEditedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetProjectExternallyEdited() {
		return projectExternallyEditedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getExtendedCreationDate() {
		return extendedCreationDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedCreationDate(Object newExtendedCreationDate) {
		Object oldExtendedCreationDate = extendedCreationDate;
		extendedCreationDate = newExtendedCreationDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__EXTENDED_CREATION_DATE, oldExtendedCreationDate, extendedCreationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActualsInSync() {
		return actualsInSync;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualsInSync(boolean newActualsInSync) {
		boolean oldActualsInSync = actualsInSync;
		actualsInSync = newActualsInSync;
		boolean oldActualsInSyncESet = actualsInSyncESet;
		actualsInSyncESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__ACTUALS_IN_SYNC, oldActualsInSync, actualsInSync, !oldActualsInSyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetActualsInSync() {
		boolean oldActualsInSync = actualsInSync;
		boolean oldActualsInSyncESet = actualsInSyncESet;
		actualsInSync = ACTUALS_IN_SYNC_EDEFAULT;
		actualsInSyncESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__ACTUALS_IN_SYNC, oldActualsInSync, ACTUALS_IN_SYNC_EDEFAULT, oldActualsInSyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetActualsInSync() {
		return actualsInSyncESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRemoveFileProperties() {
		return removeFileProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoveFileProperties(boolean newRemoveFileProperties) {
		boolean oldRemoveFileProperties = removeFileProperties;
		removeFileProperties = newRemoveFileProperties;
		boolean oldRemoveFilePropertiesESet = removeFilePropertiesESet;
		removeFilePropertiesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__REMOVE_FILE_PROPERTIES, oldRemoveFileProperties, removeFileProperties, !oldRemoveFilePropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRemoveFileProperties() {
		boolean oldRemoveFileProperties = removeFileProperties;
		boolean oldRemoveFilePropertiesESet = removeFilePropertiesESet;
		removeFileProperties = REMOVE_FILE_PROPERTIES_EDEFAULT;
		removeFilePropertiesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__REMOVE_FILE_PROPERTIES, oldRemoveFileProperties, REMOVE_FILE_PROPERTIES_EDEFAULT, oldRemoveFilePropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRemoveFileProperties() {
		return removeFilePropertiesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAdminProject() {
		return adminProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdminProject(boolean newAdminProject) {
		boolean oldAdminProject = adminProject;
		adminProject = newAdminProject;
		boolean oldAdminProjectESet = adminProjectESet;
		adminProjectESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__ADMIN_PROJECT, oldAdminProject, adminProject, !oldAdminProjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAdminProject() {
		boolean oldAdminProject = adminProject;
		boolean oldAdminProjectESet = adminProjectESet;
		adminProject = ADMIN_PROJECT_EDEFAULT;
		adminProjectESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.PROJECT__ADMIN_PROJECT, oldAdminProject, ADMIN_PROJECT_EDEFAULT, oldAdminProjectESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAdminProject() {
		return adminProjectESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutlineCodes getOutlineCodes() {
		return outlineCodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutlineCodes(OutlineCodes newOutlineCodes, NotificationChain msgs) {
		OutlineCodes oldOutlineCodes = outlineCodes;
		outlineCodes = newOutlineCodes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__OUTLINE_CODES, oldOutlineCodes, newOutlineCodes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutlineCodes(OutlineCodes newOutlineCodes) {
		if (newOutlineCodes != outlineCodes) {
			NotificationChain msgs = null;
			if (outlineCodes != null)
				msgs = ((InternalEObject)outlineCodes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__OUTLINE_CODES, null, msgs);
			if (newOutlineCodes != null)
				msgs = ((InternalEObject)newOutlineCodes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__OUTLINE_CODES, null, msgs);
			msgs = basicSetOutlineCodes(newOutlineCodes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__OUTLINE_CODES, newOutlineCodes, newOutlineCodes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WBSMasks getWBSMasks() {
		return wBSMasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWBSMasks(WBSMasks newWBSMasks, NotificationChain msgs) {
		WBSMasks oldWBSMasks = wBSMasks;
		wBSMasks = newWBSMasks;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__WBS_MASKS, oldWBSMasks, newWBSMasks);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWBSMasks(WBSMasks newWBSMasks) {
		if (newWBSMasks != wBSMasks) {
			NotificationChain msgs = null;
			if (wBSMasks != null)
				msgs = ((InternalEObject)wBSMasks).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__WBS_MASKS, null, msgs);
			if (newWBSMasks != null)
				msgs = ((InternalEObject)newWBSMasks).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__WBS_MASKS, null, msgs);
			msgs = basicSetWBSMasks(newWBSMasks, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__WBS_MASKS, newWBSMasks, newWBSMasks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributes getExtendedAttributes() {
		return extendedAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtendedAttributes(ExtendedAttributes newExtendedAttributes, NotificationChain msgs) {
		ExtendedAttributes oldExtendedAttributes = extendedAttributes;
		extendedAttributes = newExtendedAttributes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES, oldExtendedAttributes, newExtendedAttributes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedAttributes(ExtendedAttributes newExtendedAttributes) {
		if (newExtendedAttributes != extendedAttributes) {
			NotificationChain msgs = null;
			if (extendedAttributes != null)
				msgs = ((InternalEObject)extendedAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES, null, msgs);
			if (newExtendedAttributes != null)
				msgs = ((InternalEObject)newExtendedAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES, null, msgs);
			msgs = basicSetExtendedAttributes(newExtendedAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES, newExtendedAttributes, newExtendedAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Calendars getCalendars() {
		return calendars;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCalendars(Calendars newCalendars, NotificationChain msgs) {
		Calendars oldCalendars = calendars;
		calendars = newCalendars;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CALENDARS, oldCalendars, newCalendars);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalendars(Calendars newCalendars) {
		if (newCalendars != calendars) {
			NotificationChain msgs = null;
			if (calendars != null)
				msgs = ((InternalEObject)calendars).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__CALENDARS, null, msgs);
			if (newCalendars != null)
				msgs = ((InternalEObject)newCalendars).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__CALENDARS, null, msgs);
			msgs = basicSetCalendars(newCalendars, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__CALENDARS, newCalendars, newCalendars));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tasks getTasks() {
		return tasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTasks(Tasks newTasks, NotificationChain msgs) {
		Tasks oldTasks = tasks;
		tasks = newTasks;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__TASKS, oldTasks, newTasks);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTasks(Tasks newTasks) {
		if (newTasks != tasks) {
			NotificationChain msgs = null;
			if (tasks != null)
				msgs = ((InternalEObject)tasks).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__TASKS, null, msgs);
			if (newTasks != null)
				msgs = ((InternalEObject)newTasks).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__TASKS, null, msgs);
			msgs = basicSetTasks(newTasks, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__TASKS, newTasks, newTasks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resources getResources() {
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResources(Resources newResources, NotificationChain msgs) {
		Resources oldResources = resources;
		resources = newResources;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__RESOURCES, oldResources, newResources);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResources(Resources newResources) {
		if (newResources != resources) {
			NotificationChain msgs = null;
			if (resources != null)
				msgs = ((InternalEObject)resources).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__RESOURCES, null, msgs);
			if (newResources != null)
				msgs = ((InternalEObject)newResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__RESOURCES, null, msgs);
			msgs = basicSetResources(newResources, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__RESOURCES, newResources, newResources));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assignments getAssignments() {
		return assignments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignments(Assignments newAssignments, NotificationChain msgs) {
		Assignments oldAssignments = assignments;
		assignments = newAssignments;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__ASSIGNMENTS, oldAssignments, newAssignments);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignments(Assignments newAssignments) {
		if (newAssignments != assignments) {
			NotificationChain msgs = null;
			if (assignments != null)
				msgs = ((InternalEObject)assignments).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__ASSIGNMENTS, null, msgs);
			if (newAssignments != null)
				msgs = ((InternalEObject)newAssignments).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.PROJECT__ASSIGNMENTS, null, msgs);
			msgs = basicSetAssignments(newAssignments, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.PROJECT__ASSIGNMENTS, newAssignments, newAssignments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.PROJECT__OUTLINE_CODES:
					return basicSetOutlineCodes(null, msgs);
				case MsprojectPackage.PROJECT__WBS_MASKS:
					return basicSetWBSMasks(null, msgs);
				case MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES:
					return basicSetExtendedAttributes(null, msgs);
				case MsprojectPackage.PROJECT__CALENDARS:
					return basicSetCalendars(null, msgs);
				case MsprojectPackage.PROJECT__TASKS:
					return basicSetTasks(null, msgs);
				case MsprojectPackage.PROJECT__RESOURCES:
					return basicSetResources(null, msgs);
				case MsprojectPackage.PROJECT__ASSIGNMENTS:
					return basicSetAssignments(null, msgs);
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
			case MsprojectPackage.PROJECT__UID:
				return getUID();
			case MsprojectPackage.PROJECT__NAME:
				return getName();
			case MsprojectPackage.PROJECT__TITLE:
				return getTitle();
			case MsprojectPackage.PROJECT__SUBJECT:
				return getSubject();
			case MsprojectPackage.PROJECT__CATEGORY:
				return getCategory();
			case MsprojectPackage.PROJECT__COMPANY:
				return getCompany();
			case MsprojectPackage.PROJECT__MANAGER:
				return getManager();
			case MsprojectPackage.PROJECT__AUTHOR:
				return getAuthor();
			case MsprojectPackage.PROJECT__CREATION_DATE:
				return getCreationDate();
			case MsprojectPackage.PROJECT__REVISION:
				return getRevision();
			case MsprojectPackage.PROJECT__LAST_SAVED:
				return getLastSaved();
			case MsprojectPackage.PROJECT__SCHEDULE_FROM_START:
				return isScheduleFromStart() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__START_DATE:
				return getStartDate();
			case MsprojectPackage.PROJECT__FINISH_DATE:
				return getFinishDate();
			case MsprojectPackage.PROJECT__FYSTART_DATE:
				return getFYStartDate();
			case MsprojectPackage.PROJECT__CRITICAL_SLACK_LIMIT:
				return getCriticalSlackLimit();
			case MsprojectPackage.PROJECT__CURRENCY_DIGITS:
				return getCurrencyDigits();
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL:
				return getCurrencySymbol();
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL_POSITION:
				return getCurrencySymbolPosition();
			case MsprojectPackage.PROJECT__CALENDAR_UID:
				return getCalendarUID();
			case MsprojectPackage.PROJECT__DEFAULT_START_TIME:
				return getDefaultStartTime();
			case MsprojectPackage.PROJECT__DEFAULT_FINISH_TIME:
				return getDefaultFinishTime();
			case MsprojectPackage.PROJECT__MINUTES_PER_DAY:
				return getMinutesPerDay();
			case MsprojectPackage.PROJECT__MINUTES_PER_WEEK:
				return getMinutesPerWeek();
			case MsprojectPackage.PROJECT__DAYS_PER_MONTH:
				return getDaysPerMonth();
			case MsprojectPackage.PROJECT__DEFAULT_TASK_TYPE:
				return getDefaultTaskType();
			case MsprojectPackage.PROJECT__DEFAULT_FIXED_COST_ACCRUAL:
				return getDefaultFixedCostAccrual();
			case MsprojectPackage.PROJECT__DEFAULT_STANDARD_RATE:
				return new Float(getDefaultStandardRate());
			case MsprojectPackage.PROJECT__DEFAULT_OVERTIME_RATE:
				return new Float(getDefaultOvertimeRate());
			case MsprojectPackage.PROJECT__DURATION_FORMAT:
				return getDurationFormat();
			case MsprojectPackage.PROJECT__WORK_FORMAT:
				return getWorkFormat();
			case MsprojectPackage.PROJECT__EDITABLE_ACTUAL_COSTS:
				return isEditableActualCosts() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__HONOR_CONSTRAINTS:
				return isHonorConstraints() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__EARNED_VALUE_METHOD:
				return getEarnedValueMethod();
			case MsprojectPackage.PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY:
				return isInsertedProjectsLikeSummary() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__MULTIPLE_CRITICAL_PATHS:
				return isMultipleCriticalPaths() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__NEW_TASKS_EFFORT_DRIVEN:
				return isNewTasksEffortDriven() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__NEW_TASKS_ESTIMATED:
				return isNewTasksEstimated() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__SPLITS_IN_PROGRESS_TASKS:
				return isSplitsInProgressTasks() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__SPREAD_ACTUAL_COST:
				return isSpreadActualCost() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__SPREAD_PERCENT_COMPLETE:
				return isSpreadPercentComplete() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__TASK_UPDATES_RESOURCE:
				return isTaskUpdatesResource() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__FISCAL_YEAR_START:
				return isFiscalYearStart() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__WEEK_START_DAY:
				return getWeekStartDay();
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_BACK:
				return isMoveCompletedEndsBack() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_BACK:
				return isMoveRemainingStartsBack() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_FORWARD:
				return isMoveRemainingStartsForward() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_FORWARD:
				return isMoveCompletedEndsForward() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__BASELINE_FOR_EARNED_VALUE:
				return getBaselineForEarnedValue();
			case MsprojectPackage.PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS:
				return isAutoAddNewResourcesAndTasks() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__STATUS_DATE:
				return getStatusDate();
			case MsprojectPackage.PROJECT__CURRENT_DATE:
				return getCurrentDate();
			case MsprojectPackage.PROJECT__MICROSOFT_PROJECT_SERVER_URL:
				return isMicrosoftProjectServerURL() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__AUTOLINK:
				return isAutolink() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__NEW_TASK_START_DATE:
				return getNewTaskStartDate();
			case MsprojectPackage.PROJECT__DEFAULT_TASK_EV_METHOD:
				return getDefaultTaskEVMethod();
			case MsprojectPackage.PROJECT__PROJECT_EXTERNALLY_EDITED:
				return isProjectExternallyEdited() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__EXTENDED_CREATION_DATE:
				return getExtendedCreationDate();
			case MsprojectPackage.PROJECT__ACTUALS_IN_SYNC:
				return isActualsInSync() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__REMOVE_FILE_PROPERTIES:
				return isRemoveFileProperties() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__ADMIN_PROJECT:
				return isAdminProject() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.PROJECT__OUTLINE_CODES:
				return getOutlineCodes();
			case MsprojectPackage.PROJECT__WBS_MASKS:
				return getWBSMasks();
			case MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES:
				return getExtendedAttributes();
			case MsprojectPackage.PROJECT__CALENDARS:
				return getCalendars();
			case MsprojectPackage.PROJECT__TASKS:
				return getTasks();
			case MsprojectPackage.PROJECT__RESOURCES:
				return getResources();
			case MsprojectPackage.PROJECT__ASSIGNMENTS:
				return getAssignments();
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
			case MsprojectPackage.PROJECT__UID:
				setUID((String)newValue);
				return;
			case MsprojectPackage.PROJECT__NAME:
				setName((String)newValue);
				return;
			case MsprojectPackage.PROJECT__TITLE:
				setTitle((String)newValue);
				return;
			case MsprojectPackage.PROJECT__SUBJECT:
				setSubject((String)newValue);
				return;
			case MsprojectPackage.PROJECT__CATEGORY:
				setCategory((String)newValue);
				return;
			case MsprojectPackage.PROJECT__COMPANY:
				setCompany((String)newValue);
				return;
			case MsprojectPackage.PROJECT__MANAGER:
				setManager((String)newValue);
				return;
			case MsprojectPackage.PROJECT__AUTHOR:
				setAuthor((String)newValue);
				return;
			case MsprojectPackage.PROJECT__CREATION_DATE:
				setCreationDate((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__REVISION:
				setRevision((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__LAST_SAVED:
				setLastSaved((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__SCHEDULE_FROM_START:
				setScheduleFromStart(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__START_DATE:
				setStartDate((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__FINISH_DATE:
				setFinishDate((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__FYSTART_DATE:
				setFYStartDate((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__CRITICAL_SLACK_LIMIT:
				setCriticalSlackLimit((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__CURRENCY_DIGITS:
				setCurrencyDigits((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL:
				setCurrencySymbol((String)newValue);
				return;
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL_POSITION:
				setCurrencySymbolPosition((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__CALENDAR_UID:
				setCalendarUID((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_START_TIME:
				setDefaultStartTime((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_FINISH_TIME:
				setDefaultFinishTime((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__MINUTES_PER_DAY:
				setMinutesPerDay((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__MINUTES_PER_WEEK:
				setMinutesPerWeek((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__DAYS_PER_MONTH:
				setDaysPerMonth((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_TASK_TYPE:
				setDefaultTaskType((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_FIXED_COST_ACCRUAL:
				setDefaultFixedCostAccrual((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_STANDARD_RATE:
				setDefaultStandardRate(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.PROJECT__DEFAULT_OVERTIME_RATE:
				setDefaultOvertimeRate(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.PROJECT__DURATION_FORMAT:
				setDurationFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__WORK_FORMAT:
				setWorkFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__EDITABLE_ACTUAL_COSTS:
				setEditableActualCosts(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__HONOR_CONSTRAINTS:
				setHonorConstraints(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__EARNED_VALUE_METHOD:
				setEarnedValueMethod((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY:
				setInsertedProjectsLikeSummary(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__MULTIPLE_CRITICAL_PATHS:
				setMultipleCriticalPaths(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__NEW_TASKS_EFFORT_DRIVEN:
				setNewTasksEffortDriven(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__NEW_TASKS_ESTIMATED:
				setNewTasksEstimated(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__SPLITS_IN_PROGRESS_TASKS:
				setSplitsInProgressTasks(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__SPREAD_ACTUAL_COST:
				setSpreadActualCost(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__SPREAD_PERCENT_COMPLETE:
				setSpreadPercentComplete(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__TASK_UPDATES_RESOURCE:
				setTaskUpdatesResource(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__FISCAL_YEAR_START:
				setFiscalYearStart(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__WEEK_START_DAY:
				setWeekStartDay((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_BACK:
				setMoveCompletedEndsBack(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_BACK:
				setMoveRemainingStartsBack(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_FORWARD:
				setMoveRemainingStartsForward(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_FORWARD:
				setMoveCompletedEndsForward(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__BASELINE_FOR_EARNED_VALUE:
				setBaselineForEarnedValue((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS:
				setAutoAddNewResourcesAndTasks(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__STATUS_DATE:
				setStatusDate((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__CURRENT_DATE:
				setCurrentDate((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__MICROSOFT_PROJECT_SERVER_URL:
				setMicrosoftProjectServerURL(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__AUTOLINK:
				setAutolink(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__NEW_TASK_START_DATE:
				setNewTaskStartDate((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_TASK_EV_METHOD:
				setDefaultTaskEVMethod((BigInteger)newValue);
				return;
			case MsprojectPackage.PROJECT__PROJECT_EXTERNALLY_EDITED:
				setProjectExternallyEdited(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__EXTENDED_CREATION_DATE:
				setExtendedCreationDate((Object)newValue);
				return;
			case MsprojectPackage.PROJECT__ACTUALS_IN_SYNC:
				setActualsInSync(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__REMOVE_FILE_PROPERTIES:
				setRemoveFileProperties(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__ADMIN_PROJECT:
				setAdminProject(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.PROJECT__OUTLINE_CODES:
				setOutlineCodes((OutlineCodes)newValue);
				return;
			case MsprojectPackage.PROJECT__WBS_MASKS:
				setWBSMasks((WBSMasks)newValue);
				return;
			case MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributes)newValue);
				return;
			case MsprojectPackage.PROJECT__CALENDARS:
				setCalendars((Calendars)newValue);
				return;
			case MsprojectPackage.PROJECT__TASKS:
				setTasks((Tasks)newValue);
				return;
			case MsprojectPackage.PROJECT__RESOURCES:
				setResources((Resources)newValue);
				return;
			case MsprojectPackage.PROJECT__ASSIGNMENTS:
				setAssignments((Assignments)newValue);
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
			case MsprojectPackage.PROJECT__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__SUBJECT:
				setSubject(SUBJECT_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__COMPANY:
				setCompany(COMPANY_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__MANAGER:
				setManager(MANAGER_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__REVISION:
				setRevision(REVISION_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__LAST_SAVED:
				setLastSaved(LAST_SAVED_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__SCHEDULE_FROM_START:
				unsetScheduleFromStart();
				return;
			case MsprojectPackage.PROJECT__START_DATE:
				setStartDate(START_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__FINISH_DATE:
				setFinishDate(FINISH_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__FYSTART_DATE:
				setFYStartDate(FYSTART_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CRITICAL_SLACK_LIMIT:
				setCriticalSlackLimit(CRITICAL_SLACK_LIMIT_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CURRENCY_DIGITS:
				setCurrencyDigits(CURRENCY_DIGITS_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL:
				setCurrencySymbol(CURRENCY_SYMBOL_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL_POSITION:
				setCurrencySymbolPosition(CURRENCY_SYMBOL_POSITION_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CALENDAR_UID:
				setCalendarUID(CALENDAR_UID_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_START_TIME:
				setDefaultStartTime(DEFAULT_START_TIME_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_FINISH_TIME:
				setDefaultFinishTime(DEFAULT_FINISH_TIME_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__MINUTES_PER_DAY:
				setMinutesPerDay(MINUTES_PER_DAY_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__MINUTES_PER_WEEK:
				setMinutesPerWeek(MINUTES_PER_WEEK_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__DAYS_PER_MONTH:
				setDaysPerMonth(DAYS_PER_MONTH_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_TASK_TYPE:
				unsetDefaultTaskType();
				return;
			case MsprojectPackage.PROJECT__DEFAULT_FIXED_COST_ACCRUAL:
				setDefaultFixedCostAccrual(DEFAULT_FIXED_COST_ACCRUAL_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_STANDARD_RATE:
				unsetDefaultStandardRate();
				return;
			case MsprojectPackage.PROJECT__DEFAULT_OVERTIME_RATE:
				unsetDefaultOvertimeRate();
				return;
			case MsprojectPackage.PROJECT__DURATION_FORMAT:
				setDurationFormat(DURATION_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__WORK_FORMAT:
				setWorkFormat(WORK_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__EDITABLE_ACTUAL_COSTS:
				unsetEditableActualCosts();
				return;
			case MsprojectPackage.PROJECT__HONOR_CONSTRAINTS:
				unsetHonorConstraints();
				return;
			case MsprojectPackage.PROJECT__EARNED_VALUE_METHOD:
				setEarnedValueMethod(EARNED_VALUE_METHOD_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY:
				unsetInsertedProjectsLikeSummary();
				return;
			case MsprojectPackage.PROJECT__MULTIPLE_CRITICAL_PATHS:
				unsetMultipleCriticalPaths();
				return;
			case MsprojectPackage.PROJECT__NEW_TASKS_EFFORT_DRIVEN:
				unsetNewTasksEffortDriven();
				return;
			case MsprojectPackage.PROJECT__NEW_TASKS_ESTIMATED:
				unsetNewTasksEstimated();
				return;
			case MsprojectPackage.PROJECT__SPLITS_IN_PROGRESS_TASKS:
				unsetSplitsInProgressTasks();
				return;
			case MsprojectPackage.PROJECT__SPREAD_ACTUAL_COST:
				unsetSpreadActualCost();
				return;
			case MsprojectPackage.PROJECT__SPREAD_PERCENT_COMPLETE:
				unsetSpreadPercentComplete();
				return;
			case MsprojectPackage.PROJECT__TASK_UPDATES_RESOURCE:
				unsetTaskUpdatesResource();
				return;
			case MsprojectPackage.PROJECT__FISCAL_YEAR_START:
				unsetFiscalYearStart();
				return;
			case MsprojectPackage.PROJECT__WEEK_START_DAY:
				setWeekStartDay(WEEK_START_DAY_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_BACK:
				unsetMoveCompletedEndsBack();
				return;
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_BACK:
				unsetMoveRemainingStartsBack();
				return;
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_FORWARD:
				unsetMoveRemainingStartsForward();
				return;
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_FORWARD:
				unsetMoveCompletedEndsForward();
				return;
			case MsprojectPackage.PROJECT__BASELINE_FOR_EARNED_VALUE:
				setBaselineForEarnedValue(BASELINE_FOR_EARNED_VALUE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS:
				unsetAutoAddNewResourcesAndTasks();
				return;
			case MsprojectPackage.PROJECT__STATUS_DATE:
				setStatusDate(STATUS_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__CURRENT_DATE:
				setCurrentDate(CURRENT_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__MICROSOFT_PROJECT_SERVER_URL:
				unsetMicrosoftProjectServerURL();
				return;
			case MsprojectPackage.PROJECT__AUTOLINK:
				unsetAutolink();
				return;
			case MsprojectPackage.PROJECT__NEW_TASK_START_DATE:
				setNewTaskStartDate(NEW_TASK_START_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__DEFAULT_TASK_EV_METHOD:
				setDefaultTaskEVMethod(DEFAULT_TASK_EV_METHOD_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__PROJECT_EXTERNALLY_EDITED:
				unsetProjectExternallyEdited();
				return;
			case MsprojectPackage.PROJECT__EXTENDED_CREATION_DATE:
				setExtendedCreationDate(EXTENDED_CREATION_DATE_EDEFAULT);
				return;
			case MsprojectPackage.PROJECT__ACTUALS_IN_SYNC:
				unsetActualsInSync();
				return;
			case MsprojectPackage.PROJECT__REMOVE_FILE_PROPERTIES:
				unsetRemoveFileProperties();
				return;
			case MsprojectPackage.PROJECT__ADMIN_PROJECT:
				unsetAdminProject();
				return;
			case MsprojectPackage.PROJECT__OUTLINE_CODES:
				setOutlineCodes((OutlineCodes)null);
				return;
			case MsprojectPackage.PROJECT__WBS_MASKS:
				setWBSMasks((WBSMasks)null);
				return;
			case MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributes)null);
				return;
			case MsprojectPackage.PROJECT__CALENDARS:
				setCalendars((Calendars)null);
				return;
			case MsprojectPackage.PROJECT__TASKS:
				setTasks((Tasks)null);
				return;
			case MsprojectPackage.PROJECT__RESOURCES:
				setResources((Resources)null);
				return;
			case MsprojectPackage.PROJECT__ASSIGNMENTS:
				setAssignments((Assignments)null);
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
			case MsprojectPackage.PROJECT__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.PROJECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MsprojectPackage.PROJECT__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case MsprojectPackage.PROJECT__SUBJECT:
				return SUBJECT_EDEFAULT == null ? subject != null : !SUBJECT_EDEFAULT.equals(subject);
			case MsprojectPackage.PROJECT__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case MsprojectPackage.PROJECT__COMPANY:
				return COMPANY_EDEFAULT == null ? company != null : !COMPANY_EDEFAULT.equals(company);
			case MsprojectPackage.PROJECT__MANAGER:
				return MANAGER_EDEFAULT == null ? manager != null : !MANAGER_EDEFAULT.equals(manager);
			case MsprojectPackage.PROJECT__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case MsprojectPackage.PROJECT__CREATION_DATE:
				return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
			case MsprojectPackage.PROJECT__REVISION:
				return REVISION_EDEFAULT == null ? revision != null : !REVISION_EDEFAULT.equals(revision);
			case MsprojectPackage.PROJECT__LAST_SAVED:
				return LAST_SAVED_EDEFAULT == null ? lastSaved != null : !LAST_SAVED_EDEFAULT.equals(lastSaved);
			case MsprojectPackage.PROJECT__SCHEDULE_FROM_START:
				return isSetScheduleFromStart();
			case MsprojectPackage.PROJECT__START_DATE:
				return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
			case MsprojectPackage.PROJECT__FINISH_DATE:
				return FINISH_DATE_EDEFAULT == null ? finishDate != null : !FINISH_DATE_EDEFAULT.equals(finishDate);
			case MsprojectPackage.PROJECT__FYSTART_DATE:
				return FYSTART_DATE_EDEFAULT == null ? fYStartDate != null : !FYSTART_DATE_EDEFAULT.equals(fYStartDate);
			case MsprojectPackage.PROJECT__CRITICAL_SLACK_LIMIT:
				return CRITICAL_SLACK_LIMIT_EDEFAULT == null ? criticalSlackLimit != null : !CRITICAL_SLACK_LIMIT_EDEFAULT.equals(criticalSlackLimit);
			case MsprojectPackage.PROJECT__CURRENCY_DIGITS:
				return CURRENCY_DIGITS_EDEFAULT == null ? currencyDigits != null : !CURRENCY_DIGITS_EDEFAULT.equals(currencyDigits);
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL:
				return CURRENCY_SYMBOL_EDEFAULT == null ? currencySymbol != null : !CURRENCY_SYMBOL_EDEFAULT.equals(currencySymbol);
			case MsprojectPackage.PROJECT__CURRENCY_SYMBOL_POSITION:
				return CURRENCY_SYMBOL_POSITION_EDEFAULT == null ? currencySymbolPosition != null : !CURRENCY_SYMBOL_POSITION_EDEFAULT.equals(currencySymbolPosition);
			case MsprojectPackage.PROJECT__CALENDAR_UID:
				return CALENDAR_UID_EDEFAULT == null ? calendarUID != null : !CALENDAR_UID_EDEFAULT.equals(calendarUID);
			case MsprojectPackage.PROJECT__DEFAULT_START_TIME:
				return DEFAULT_START_TIME_EDEFAULT == null ? defaultStartTime != null : !DEFAULT_START_TIME_EDEFAULT.equals(defaultStartTime);
			case MsprojectPackage.PROJECT__DEFAULT_FINISH_TIME:
				return DEFAULT_FINISH_TIME_EDEFAULT == null ? defaultFinishTime != null : !DEFAULT_FINISH_TIME_EDEFAULT.equals(defaultFinishTime);
			case MsprojectPackage.PROJECT__MINUTES_PER_DAY:
				return MINUTES_PER_DAY_EDEFAULT == null ? minutesPerDay != null : !MINUTES_PER_DAY_EDEFAULT.equals(minutesPerDay);
			case MsprojectPackage.PROJECT__MINUTES_PER_WEEK:
				return MINUTES_PER_WEEK_EDEFAULT == null ? minutesPerWeek != null : !MINUTES_PER_WEEK_EDEFAULT.equals(minutesPerWeek);
			case MsprojectPackage.PROJECT__DAYS_PER_MONTH:
				return DAYS_PER_MONTH_EDEFAULT == null ? daysPerMonth != null : !DAYS_PER_MONTH_EDEFAULT.equals(daysPerMonth);
			case MsprojectPackage.PROJECT__DEFAULT_TASK_TYPE:
				return isSetDefaultTaskType();
			case MsprojectPackage.PROJECT__DEFAULT_FIXED_COST_ACCRUAL:
				return DEFAULT_FIXED_COST_ACCRUAL_EDEFAULT == null ? defaultFixedCostAccrual != null : !DEFAULT_FIXED_COST_ACCRUAL_EDEFAULT.equals(defaultFixedCostAccrual);
			case MsprojectPackage.PROJECT__DEFAULT_STANDARD_RATE:
				return isSetDefaultStandardRate();
			case MsprojectPackage.PROJECT__DEFAULT_OVERTIME_RATE:
				return isSetDefaultOvertimeRate();
			case MsprojectPackage.PROJECT__DURATION_FORMAT:
				return DURATION_FORMAT_EDEFAULT == null ? durationFormat != null : !DURATION_FORMAT_EDEFAULT.equals(durationFormat);
			case MsprojectPackage.PROJECT__WORK_FORMAT:
				return WORK_FORMAT_EDEFAULT == null ? workFormat != null : !WORK_FORMAT_EDEFAULT.equals(workFormat);
			case MsprojectPackage.PROJECT__EDITABLE_ACTUAL_COSTS:
				return isSetEditableActualCosts();
			case MsprojectPackage.PROJECT__HONOR_CONSTRAINTS:
				return isSetHonorConstraints();
			case MsprojectPackage.PROJECT__EARNED_VALUE_METHOD:
				return EARNED_VALUE_METHOD_EDEFAULT == null ? earnedValueMethod != null : !EARNED_VALUE_METHOD_EDEFAULT.equals(earnedValueMethod);
			case MsprojectPackage.PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY:
				return isSetInsertedProjectsLikeSummary();
			case MsprojectPackage.PROJECT__MULTIPLE_CRITICAL_PATHS:
				return isSetMultipleCriticalPaths();
			case MsprojectPackage.PROJECT__NEW_TASKS_EFFORT_DRIVEN:
				return isSetNewTasksEffortDriven();
			case MsprojectPackage.PROJECT__NEW_TASKS_ESTIMATED:
				return isSetNewTasksEstimated();
			case MsprojectPackage.PROJECT__SPLITS_IN_PROGRESS_TASKS:
				return isSetSplitsInProgressTasks();
			case MsprojectPackage.PROJECT__SPREAD_ACTUAL_COST:
				return isSetSpreadActualCost();
			case MsprojectPackage.PROJECT__SPREAD_PERCENT_COMPLETE:
				return isSetSpreadPercentComplete();
			case MsprojectPackage.PROJECT__TASK_UPDATES_RESOURCE:
				return isSetTaskUpdatesResource();
			case MsprojectPackage.PROJECT__FISCAL_YEAR_START:
				return isSetFiscalYearStart();
			case MsprojectPackage.PROJECT__WEEK_START_DAY:
				return WEEK_START_DAY_EDEFAULT == null ? weekStartDay != null : !WEEK_START_DAY_EDEFAULT.equals(weekStartDay);
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_BACK:
				return isSetMoveCompletedEndsBack();
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_BACK:
				return isSetMoveRemainingStartsBack();
			case MsprojectPackage.PROJECT__MOVE_REMAINING_STARTS_FORWARD:
				return isSetMoveRemainingStartsForward();
			case MsprojectPackage.PROJECT__MOVE_COMPLETED_ENDS_FORWARD:
				return isSetMoveCompletedEndsForward();
			case MsprojectPackage.PROJECT__BASELINE_FOR_EARNED_VALUE:
				return BASELINE_FOR_EARNED_VALUE_EDEFAULT == null ? baselineForEarnedValue != null : !BASELINE_FOR_EARNED_VALUE_EDEFAULT.equals(baselineForEarnedValue);
			case MsprojectPackage.PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS:
				return isSetAutoAddNewResourcesAndTasks();
			case MsprojectPackage.PROJECT__STATUS_DATE:
				return STATUS_DATE_EDEFAULT == null ? statusDate != null : !STATUS_DATE_EDEFAULT.equals(statusDate);
			case MsprojectPackage.PROJECT__CURRENT_DATE:
				return CURRENT_DATE_EDEFAULT == null ? currentDate != null : !CURRENT_DATE_EDEFAULT.equals(currentDate);
			case MsprojectPackage.PROJECT__MICROSOFT_PROJECT_SERVER_URL:
				return isSetMicrosoftProjectServerURL();
			case MsprojectPackage.PROJECT__AUTOLINK:
				return isSetAutolink();
			case MsprojectPackage.PROJECT__NEW_TASK_START_DATE:
				return NEW_TASK_START_DATE_EDEFAULT == null ? newTaskStartDate != null : !NEW_TASK_START_DATE_EDEFAULT.equals(newTaskStartDate);
			case MsprojectPackage.PROJECT__DEFAULT_TASK_EV_METHOD:
				return DEFAULT_TASK_EV_METHOD_EDEFAULT == null ? defaultTaskEVMethod != null : !DEFAULT_TASK_EV_METHOD_EDEFAULT.equals(defaultTaskEVMethod);
			case MsprojectPackage.PROJECT__PROJECT_EXTERNALLY_EDITED:
				return isSetProjectExternallyEdited();
			case MsprojectPackage.PROJECT__EXTENDED_CREATION_DATE:
				return EXTENDED_CREATION_DATE_EDEFAULT == null ? extendedCreationDate != null : !EXTENDED_CREATION_DATE_EDEFAULT.equals(extendedCreationDate);
			case MsprojectPackage.PROJECT__ACTUALS_IN_SYNC:
				return isSetActualsInSync();
			case MsprojectPackage.PROJECT__REMOVE_FILE_PROPERTIES:
				return isSetRemoveFileProperties();
			case MsprojectPackage.PROJECT__ADMIN_PROJECT:
				return isSetAdminProject();
			case MsprojectPackage.PROJECT__OUTLINE_CODES:
				return outlineCodes != null;
			case MsprojectPackage.PROJECT__WBS_MASKS:
				return wBSMasks != null;
			case MsprojectPackage.PROJECT__EXTENDED_ATTRIBUTES:
				return extendedAttributes != null;
			case MsprojectPackage.PROJECT__CALENDARS:
				return calendars != null;
			case MsprojectPackage.PROJECT__TASKS:
				return tasks != null;
			case MsprojectPackage.PROJECT__RESOURCES:
				return resources != null;
			case MsprojectPackage.PROJECT__ASSIGNMENTS:
				return assignments != null;
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
		result.append(", name: ");
		result.append(name);
		result.append(", title: ");
		result.append(title);
		result.append(", subject: ");
		result.append(subject);
		result.append(", category: ");
		result.append(category);
		result.append(", company: ");
		result.append(company);
		result.append(", manager: ");
		result.append(manager);
		result.append(", author: ");
		result.append(author);
		result.append(", creationDate: ");
		result.append(creationDate);
		result.append(", revision: ");
		result.append(revision);
		result.append(", lastSaved: ");
		result.append(lastSaved);
		result.append(", scheduleFromStart: ");
		if (scheduleFromStartESet) result.append(scheduleFromStart); else result.append("<unset>");
		result.append(", startDate: ");
		result.append(startDate);
		result.append(", finishDate: ");
		result.append(finishDate);
		result.append(", fYStartDate: ");
		result.append(fYStartDate);
		result.append(", criticalSlackLimit: ");
		result.append(criticalSlackLimit);
		result.append(", currencyDigits: ");
		result.append(currencyDigits);
		result.append(", currencySymbol: ");
		result.append(currencySymbol);
		result.append(", currencySymbolPosition: ");
		result.append(currencySymbolPosition);
		result.append(", calendarUID: ");
		result.append(calendarUID);
		result.append(", defaultStartTime: ");
		result.append(defaultStartTime);
		result.append(", defaultFinishTime: ");
		result.append(defaultFinishTime);
		result.append(", minutesPerDay: ");
		result.append(minutesPerDay);
		result.append(", minutesPerWeek: ");
		result.append(minutesPerWeek);
		result.append(", daysPerMonth: ");
		result.append(daysPerMonth);
		result.append(", defaultTaskType: ");
		if (defaultTaskTypeESet) result.append(defaultTaskType); else result.append("<unset>");
		result.append(", defaultFixedCostAccrual: ");
		result.append(defaultFixedCostAccrual);
		result.append(", defaultStandardRate: ");
		if (defaultStandardRateESet) result.append(defaultStandardRate); else result.append("<unset>");
		result.append(", defaultOvertimeRate: ");
		if (defaultOvertimeRateESet) result.append(defaultOvertimeRate); else result.append("<unset>");
		result.append(", durationFormat: ");
		result.append(durationFormat);
		result.append(", workFormat: ");
		result.append(workFormat);
		result.append(", editableActualCosts: ");
		if (editableActualCostsESet) result.append(editableActualCosts); else result.append("<unset>");
		result.append(", honorConstraints: ");
		if (honorConstraintsESet) result.append(honorConstraints); else result.append("<unset>");
		result.append(", earnedValueMethod: ");
		result.append(earnedValueMethod);
		result.append(", insertedProjectsLikeSummary: ");
		if (insertedProjectsLikeSummaryESet) result.append(insertedProjectsLikeSummary); else result.append("<unset>");
		result.append(", multipleCriticalPaths: ");
		if (multipleCriticalPathsESet) result.append(multipleCriticalPaths); else result.append("<unset>");
		result.append(", newTasksEffortDriven: ");
		if (newTasksEffortDrivenESet) result.append(newTasksEffortDriven); else result.append("<unset>");
		result.append(", newTasksEstimated: ");
		if (newTasksEstimatedESet) result.append(newTasksEstimated); else result.append("<unset>");
		result.append(", splitsInProgressTasks: ");
		if (splitsInProgressTasksESet) result.append(splitsInProgressTasks); else result.append("<unset>");
		result.append(", spreadActualCost: ");
		if (spreadActualCostESet) result.append(spreadActualCost); else result.append("<unset>");
		result.append(", spreadPercentComplete: ");
		if (spreadPercentCompleteESet) result.append(spreadPercentComplete); else result.append("<unset>");
		result.append(", taskUpdatesResource: ");
		if (taskUpdatesResourceESet) result.append(taskUpdatesResource); else result.append("<unset>");
		result.append(", fiscalYearStart: ");
		if (fiscalYearStartESet) result.append(fiscalYearStart); else result.append("<unset>");
		result.append(", weekStartDay: ");
		result.append(weekStartDay);
		result.append(", moveCompletedEndsBack: ");
		if (moveCompletedEndsBackESet) result.append(moveCompletedEndsBack); else result.append("<unset>");
		result.append(", moveRemainingStartsBack: ");
		if (moveRemainingStartsBackESet) result.append(moveRemainingStartsBack); else result.append("<unset>");
		result.append(", moveRemainingStartsForward: ");
		if (moveRemainingStartsForwardESet) result.append(moveRemainingStartsForward); else result.append("<unset>");
		result.append(", moveCompletedEndsForward: ");
		if (moveCompletedEndsForwardESet) result.append(moveCompletedEndsForward); else result.append("<unset>");
		result.append(", baselineForEarnedValue: ");
		result.append(baselineForEarnedValue);
		result.append(", autoAddNewResourcesAndTasks: ");
		if (autoAddNewResourcesAndTasksESet) result.append(autoAddNewResourcesAndTasks); else result.append("<unset>");
		result.append(", statusDate: ");
		result.append(statusDate);
		result.append(", currentDate: ");
		result.append(currentDate);
		result.append(", microsoftProjectServerURL: ");
		if (microsoftProjectServerURLESet) result.append(microsoftProjectServerURL); else result.append("<unset>");
		result.append(", autolink: ");
		if (autolinkESet) result.append(autolink); else result.append("<unset>");
		result.append(", newTaskStartDate: ");
		result.append(newTaskStartDate);
		result.append(", defaultTaskEVMethod: ");
		result.append(defaultTaskEVMethod);
		result.append(", projectExternallyEdited: ");
		if (projectExternallyEditedESet) result.append(projectExternallyEdited); else result.append("<unset>");
		result.append(", extendedCreationDate: ");
		result.append(extendedCreationDate);
		result.append(", actualsInSync: ");
		if (actualsInSyncESet) result.append(actualsInSync); else result.append("<unset>");
		result.append(", removeFileProperties: ");
		if (removeFilePropertiesESet) result.append(removeFileProperties); else result.append("<unset>");
		result.append(", adminProject: ");
		if (adminProjectESet) result.append(adminProject); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ProjectImpl
