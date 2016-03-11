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
 * A representation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Project#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getRevision <em>Revision</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getLastSaved <em>Last Saved</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isScheduleFromStart <em>Schedule From Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getFinishDate <em>Finish Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getFYStartDate <em>FY Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCriticalSlackLimit <em>Critical Slack Limit</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCurrencyDigits <em>Currency Digits</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCurrencySymbol <em>Currency Symbol</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCurrencySymbolPosition <em>Currency Symbol Position</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCalendarUID <em>Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultStartTime <em>Default Start Time</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultFinishTime <em>Default Finish Time</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getMinutesPerDay <em>Minutes Per Day</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getMinutesPerWeek <em>Minutes Per Week</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDaysPerMonth <em>Days Per Month</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultTaskType <em>Default Task Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultFixedCostAccrual <em>Default Fixed Cost Accrual</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultStandardRate <em>Default Standard Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultOvertimeRate <em>Default Overtime Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDurationFormat <em>Duration Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getWorkFormat <em>Work Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isEditableActualCosts <em>Editable Actual Costs</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isHonorConstraints <em>Honor Constraints</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getEarnedValueMethod <em>Earned Value Method</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isInsertedProjectsLikeSummary <em>Inserted Projects Like Summary</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isMultipleCriticalPaths <em>Multiple Critical Paths</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isNewTasksEffortDriven <em>New Tasks Effort Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isNewTasksEstimated <em>New Tasks Estimated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isSplitsInProgressTasks <em>Splits In Progress Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isSpreadActualCost <em>Spread Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isSpreadPercentComplete <em>Spread Percent Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isTaskUpdatesResource <em>Task Updates Resource</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isFiscalYearStart <em>Fiscal Year Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getWeekStartDay <em>Week Start Day</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsBack <em>Move Completed Ends Back</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsBack <em>Move Remaining Starts Back</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsForward <em>Move Remaining Starts Forward</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsForward <em>Move Completed Ends Forward</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getBaselineForEarnedValue <em>Baseline For Earned Value</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isAutoAddNewResourcesAndTasks <em>Auto Add New Resources And Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getStatusDate <em>Status Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCurrentDate <em>Current Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isMicrosoftProjectServerURL <em>Microsoft Project Server URL</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isAutolink <em>Autolink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getNewTaskStartDate <em>New Task Start Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getDefaultTaskEVMethod <em>Default Task EV Method</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isProjectExternallyEdited <em>Project Externally Edited</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getExtendedCreationDate <em>Extended Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isActualsInSync <em>Actuals In Sync</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isRemoveFileProperties <em>Remove File Properties</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#isAdminProject <em>Admin Project</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getOutlineCodes <em>Outline Codes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getWBSMasks <em>WBS Masks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getCalendars <em>Calendars</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getTasks <em>Tasks</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getResources <em>Resources</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Project#getAssignments <em>Assignments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject()
 * @model extendedMetaData="name='Project_._type' kind='elementOnly'"
 * @generated
 */
public interface Project extends EObject {
	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique ID of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_UID()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.UIDType"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getUID <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UID</em>' attribute.
	 * @see #getUID()
	 * @generated
	 */
	void setUID(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Name()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.NameType3"
	 *        extendedMetaData="kind='element' name='Name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The title of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Title()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.TitleType"
	 *        extendedMetaData="kind='element' name='Title' namespace='##targetNamespace'"
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The subject of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Subject</em>' attribute.
	 * @see #setSubject(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Subject()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.SubjectType"
	 *        extendedMetaData="kind='element' name='Subject' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSubject();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getSubject <em>Subject</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' attribute.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(String value);

	/**
	 * Returns the value of the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The category of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Category</em>' attribute.
	 * @see #setCategory(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Category()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CategoryType"
	 *        extendedMetaData="kind='element' name='Category' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCategory();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCategory <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' attribute.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(String value);

	/**
	 * Returns the value of the '<em><b>Company</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The company that owns the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Company</em>' attribute.
	 * @see #setCompany(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Company()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CompanyType"
	 *        extendedMetaData="kind='element' name='Company' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCompany();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCompany <em>Company</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' attribute.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(String value);

	/**
	 * Returns the value of the '<em><b>Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The manager of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Manager</em>' attribute.
	 * @see #setManager(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Manager()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.ManagerType"
	 *        extendedMetaData="kind='element' name='Manager' namespace='##targetNamespace'"
	 * @generated
	 */
	String getManager();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getManager <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' attribute.
	 * @see #getManager()
	 * @generated
	 */
	void setManager(String value);

	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The author of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Author()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.AuthorType"
	 *        extendedMetaData="kind='element' name='Author' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the project was created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CreationDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='CreationDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getCreationDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(Object value);

	/**
	 * Returns the value of the '<em><b>Revision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of times a project has been saved.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Revision</em>' attribute.
	 * @see #setRevision(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Revision()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='Revision' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getRevision();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getRevision <em>Revision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Revision</em>' attribute.
	 * @see #getRevision()
	 * @generated
	 */
	void setRevision(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Last Saved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date that the project was last saved.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Last Saved</em>' attribute.
	 * @see #setLastSaved(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_LastSaved()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='LastSaved' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getLastSaved();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getLastSaved <em>Last Saved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Saved</em>' attribute.
	 * @see #getLastSaved()
	 * @generated
	 */
	void setLastSaved(Object value);

	/**
	 * Returns the value of the '<em><b>Schedule From Start</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the project is schduled from the start date or finish date.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schedule From Start</em>' attribute.
	 * @see #isSetScheduleFromStart()
	 * @see #unsetScheduleFromStart()
	 * @see #setScheduleFromStart(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_ScheduleFromStart()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ScheduleFromStart' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isScheduleFromStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isScheduleFromStart <em>Schedule From Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schedule From Start</em>' attribute.
	 * @see #isSetScheduleFromStart()
	 * @see #unsetScheduleFromStart()
	 * @see #isScheduleFromStart()
	 * @generated
	 */
	void setScheduleFromStart(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isScheduleFromStart <em>Schedule From Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetScheduleFromStart()
	 * @see #isScheduleFromStart()
	 * @see #setScheduleFromStart(boolean)
	 * @generated
	 */
	void unsetScheduleFromStart();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isScheduleFromStart <em>Schedule From Start</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Schedule From Start</em>' attribute is set.
	 * @see #unsetScheduleFromStart()
	 * @see #isScheduleFromStart()
	 * @see #setScheduleFromStart(boolean)
	 * @generated
	 */
	boolean isSetScheduleFromStart();

	/**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The start date of the project. Required if ScheduleFromStart is true.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_StartDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='StartDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStartDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(Object value);

	/**
	 * Returns the value of the '<em><b>Finish Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The finish date of the project. Required if ScheduleFromStart is false.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish Date</em>' attribute.
	 * @see #setFinishDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_FinishDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='FinishDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFinishDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getFinishDate <em>Finish Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Date</em>' attribute.
	 * @see #getFinishDate()
	 * @generated
	 */
	void setFinishDate(Object value);

	/**
	 * Returns the value of the '<em><b>FY Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fiscal Year starting month. Values are: 
	 * 						1=January, 2=February, 3=March, 4=April, 5=May, 6=June, 7=July, 
	 * 						8=August, 9=September, 10=October, 11=November, 12=December 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>FY Start Date</em>' attribute.
	 * @see #setFYStartDate(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_FYStartDate()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.FYStartDateType"
	 *        extendedMetaData="kind='element' name='FYStartDate' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getFYStartDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getFYStartDate <em>FY Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FY Start Date</em>' attribute.
	 * @see #getFYStartDate()
	 * @generated
	 */
	void setFYStartDate(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Critical Slack Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of days past its end date that a task can go 
	 * 						before Microsoft Project marks that task as a critical task.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Critical Slack Limit</em>' attribute.
	 * @see #setCriticalSlackLimit(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CriticalSlackLimit()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='CriticalSlackLimit' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCriticalSlackLimit();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCriticalSlackLimit <em>Critical Slack Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Critical Slack Limit</em>' attribute.
	 * @see #getCriticalSlackLimit()
	 * @generated
	 */
	void setCriticalSlackLimit(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Currency Digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of digits after the decimal symbol.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Currency Digits</em>' attribute.
	 * @see #setCurrencyDigits(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CurrencyDigits()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='CurrencyDigits' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCurrencyDigits();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCurrencyDigits <em>Currency Digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Currency Digits</em>' attribute.
	 * @see #getCurrencyDigits()
	 * @generated
	 */
	void setCurrencyDigits(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Currency Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The currency symbol used in the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Currency Symbol</em>' attribute.
	 * @see #setCurrencySymbol(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CurrencySymbol()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CurrencySymbolType"
	 *        extendedMetaData="kind='element' name='CurrencySymbol' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCurrencySymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCurrencySymbol <em>Currency Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Currency Symbol</em>' attribute.
	 * @see #getCurrencySymbol()
	 * @generated
	 */
	void setCurrencySymbol(String value);

	/**
	 * Returns the value of the '<em><b>Currency Symbol Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The position of the currency symbol.  Values are: 
	 * 						0=Before, 1=After, 2=Before With Space, 3=After with space.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Currency Symbol Position</em>' attribute.
	 * @see #setCurrencySymbolPosition(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CurrencySymbolPosition()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.CurrencySymbolPositionType"
	 *        extendedMetaData="kind='element' name='CurrencySymbolPosition' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCurrencySymbolPosition();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCurrencySymbolPosition <em>Currency Symbol Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Currency Symbol Position</em>' attribute.
	 * @see #getCurrencySymbolPosition()
	 * @generated
	 */
	void setCurrencySymbolPosition(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The project calendar.  Refers to a valid UID in the 
	 * 						Calendars element of the Microsoft Project XML Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calendar UID</em>' attribute.
	 * @see #setCalendarUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CalendarUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='CalendarUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getCalendarUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCalendarUID <em>Calendar UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calendar UID</em>' attribute.
	 * @see #getCalendarUID()
	 * @generated
	 */
	void setCalendarUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Default Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default start time of new tasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Start Time</em>' attribute.
	 * @see #setDefaultStartTime(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultStartTime()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Time"
	 *        extendedMetaData="kind='element' name='DefaultStartTime' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getDefaultStartTime();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultStartTime <em>Default Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Start Time</em>' attribute.
	 * @see #getDefaultStartTime()
	 * @generated
	 */
	void setDefaultStartTime(Object value);

	/**
	 * Returns the value of the '<em><b>Default Finish Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default finish time of new tasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Finish Time</em>' attribute.
	 * @see #setDefaultFinishTime(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultFinishTime()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Time"
	 *        extendedMetaData="kind='element' name='DefaultFinishTime' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getDefaultFinishTime();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultFinishTime <em>Default Finish Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Finish Time</em>' attribute.
	 * @see #getDefaultFinishTime()
	 * @generated
	 */
	void setDefaultFinishTime(Object value);

	/**
	 * Returns the value of the '<em><b>Minutes Per Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of minutes per day.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Minutes Per Day</em>' attribute.
	 * @see #setMinutesPerDay(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MinutesPerDay()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='MinutesPerDay' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getMinutesPerDay();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getMinutesPerDay <em>Minutes Per Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minutes Per Day</em>' attribute.
	 * @see #getMinutesPerDay()
	 * @generated
	 */
	void setMinutesPerDay(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Minutes Per Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of minutes per week.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Minutes Per Week</em>' attribute.
	 * @see #setMinutesPerWeek(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MinutesPerWeek()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='MinutesPerWeek' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getMinutesPerWeek();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getMinutesPerWeek <em>Minutes Per Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minutes Per Week</em>' attribute.
	 * @see #getMinutesPerWeek()
	 * @generated
	 */
	void setMinutesPerWeek(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Days Per Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of days per month.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Days Per Month</em>' attribute.
	 * @see #setDaysPerMonth(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DaysPerMonth()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='DaysPerMonth' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDaysPerMonth();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDaysPerMonth <em>Days Per Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Days Per Month</em>' attribute.
	 * @see #getDaysPerMonth()
	 * @generated
	 */
	void setDaysPerMonth(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Default Task Type</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default type of new tasks. Values are: 
	 * 						0=Fixed Units, 1=Fixed Duration, 2=Fixed Work.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Task Type</em>' attribute.
	 * @see #isSetDefaultTaskType()
	 * @see #unsetDefaultTaskType()
	 * @see #setDefaultTaskType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultTaskType()
	 * @model default="1" unique="false" unsettable="true" dataType="org.eclipse.epf.msproject.DefaultTaskTypeType"
	 *        extendedMetaData="kind='element' name='DefaultTaskType' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDefaultTaskType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultTaskType <em>Default Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Task Type</em>' attribute.
	 * @see #isSetDefaultTaskType()
	 * @see #unsetDefaultTaskType()
	 * @see #getDefaultTaskType()
	 * @generated
	 */
	void setDefaultTaskType(BigInteger value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultTaskType <em>Default Task Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDefaultTaskType()
	 * @see #getDefaultTaskType()
	 * @see #setDefaultTaskType(BigInteger)
	 * @generated
	 */
	void unsetDefaultTaskType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultTaskType <em>Default Task Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Default Task Type</em>' attribute is set.
	 * @see #unsetDefaultTaskType()
	 * @see #getDefaultTaskType()
	 * @see #setDefaultTaskType(BigInteger)
	 * @generated
	 */
	boolean isSetDefaultTaskType();

	/**
	 * Returns the value of the '<em><b>Default Fixed Cost Accrual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default from where fixed costs are accrued.  Values are: 
	 * 						1=Start, 2=Prorated, 3=End
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Fixed Cost Accrual</em>' attribute.
	 * @see #setDefaultFixedCostAccrual(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultFixedCostAccrual()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DefaultFixedCostAccrualType"
	 *        extendedMetaData="kind='element' name='DefaultFixedCostAccrual' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDefaultFixedCostAccrual();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultFixedCostAccrual <em>Default Fixed Cost Accrual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Fixed Cost Accrual</em>' attribute.
	 * @see #getDefaultFixedCostAccrual()
	 * @generated
	 */
	void setDefaultFixedCostAccrual(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Default Standard Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default standard rate for new resources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Standard Rate</em>' attribute.
	 * @see #isSetDefaultStandardRate()
	 * @see #unsetDefaultStandardRate()
	 * @see #setDefaultStandardRate(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultStandardRate()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='DefaultStandardRate' namespace='##targetNamespace'"
	 * @generated
	 */
	float getDefaultStandardRate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultStandardRate <em>Default Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Standard Rate</em>' attribute.
	 * @see #isSetDefaultStandardRate()
	 * @see #unsetDefaultStandardRate()
	 * @see #getDefaultStandardRate()
	 * @generated
	 */
	void setDefaultStandardRate(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultStandardRate <em>Default Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDefaultStandardRate()
	 * @see #getDefaultStandardRate()
	 * @see #setDefaultStandardRate(float)
	 * @generated
	 */
	void unsetDefaultStandardRate();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultStandardRate <em>Default Standard Rate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Default Standard Rate</em>' attribute is set.
	 * @see #unsetDefaultStandardRate()
	 * @see #getDefaultStandardRate()
	 * @see #setDefaultStandardRate(float)
	 * @generated
	 */
	boolean isSetDefaultStandardRate();

	/**
	 * Returns the value of the '<em><b>Default Overtime Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default overtime rate for new resources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Overtime Rate</em>' attribute.
	 * @see #isSetDefaultOvertimeRate()
	 * @see #unsetDefaultOvertimeRate()
	 * @see #setDefaultOvertimeRate(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultOvertimeRate()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='DefaultOvertimeRate' namespace='##targetNamespace'"
	 * @generated
	 */
	float getDefaultOvertimeRate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultOvertimeRate <em>Default Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Overtime Rate</em>' attribute.
	 * @see #isSetDefaultOvertimeRate()
	 * @see #unsetDefaultOvertimeRate()
	 * @see #getDefaultOvertimeRate()
	 * @generated
	 */
	void setDefaultOvertimeRate(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultOvertimeRate <em>Default Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDefaultOvertimeRate()
	 * @see #getDefaultOvertimeRate()
	 * @see #setDefaultOvertimeRate(float)
	 * @generated
	 */
	void unsetDefaultOvertimeRate();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultOvertimeRate <em>Default Overtime Rate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Default Overtime Rate</em>' attribute is set.
	 * @see #unsetDefaultOvertimeRate()
	 * @see #getDefaultOvertimeRate()
	 * @see #setDefaultOvertimeRate(float)
	 * @generated
	 */
	boolean isSetDefaultOvertimeRate();

	/**
	 * Returns the value of the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The format for expressing the bulk duration.  Values are: 
	 * 						3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 19=%, 20=e%, 21=null, 
	 * 						35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 42=ew?, 43=mo?, 44=emo?, 51=%?, 52=e%? and 
	 * 						53=null.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration Format</em>' attribute.
	 * @see #setDurationFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DurationFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DurationFormatType5"
	 *        extendedMetaData="kind='element' name='DurationFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDurationFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDurationFormat <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration Format</em>' attribute.
	 * @see #getDurationFormat()
	 * @generated
	 */
	void setDurationFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Work Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default work unit format. Values are: 
	 * 						1=m, 2=h, 3=d, 4=w, 5=mo
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work Format</em>' attribute.
	 * @see #setWorkFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_WorkFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.WorkFormatType"
	 *        extendedMetaData="kind='element' name='WorkFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getWorkFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getWorkFormat <em>Work Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Format</em>' attribute.
	 * @see #getWorkFormat()
	 * @generated
	 */
	void setWorkFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Editable Actual Costs</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether or not actual costs are editable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Editable Actual Costs</em>' attribute.
	 * @see #isSetEditableActualCosts()
	 * @see #unsetEditableActualCosts()
	 * @see #setEditableActualCosts(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_EditableActualCosts()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='EditableActualCosts' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isEditableActualCosts();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isEditableActualCosts <em>Editable Actual Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editable Actual Costs</em>' attribute.
	 * @see #isSetEditableActualCosts()
	 * @see #unsetEditableActualCosts()
	 * @see #isEditableActualCosts()
	 * @generated
	 */
	void setEditableActualCosts(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isEditableActualCosts <em>Editable Actual Costs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEditableActualCosts()
	 * @see #isEditableActualCosts()
	 * @see #setEditableActualCosts(boolean)
	 * @generated
	 */
	void unsetEditableActualCosts();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isEditableActualCosts <em>Editable Actual Costs</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Editable Actual Costs</em>' attribute is set.
	 * @see #unsetEditableActualCosts()
	 * @see #isEditableActualCosts()
	 * @see #setEditableActualCosts(boolean)
	 * @generated
	 */
	boolean isSetEditableActualCosts();

	/**
	 * Returns the value of the '<em><b>Honor Constraints</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether tasks honour their constraint dates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Honor Constraints</em>' attribute.
	 * @see #isSetHonorConstraints()
	 * @see #unsetHonorConstraints()
	 * @see #setHonorConstraints(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_HonorConstraints()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='HonorConstraints' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isHonorConstraints();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isHonorConstraints <em>Honor Constraints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Honor Constraints</em>' attribute.
	 * @see #isSetHonorConstraints()
	 * @see #unsetHonorConstraints()
	 * @see #isHonorConstraints()
	 * @generated
	 */
	void setHonorConstraints(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isHonorConstraints <em>Honor Constraints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHonorConstraints()
	 * @see #isHonorConstraints()
	 * @see #setHonorConstraints(boolean)
	 * @generated
	 */
	void unsetHonorConstraints();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isHonorConstraints <em>Honor Constraints</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Honor Constraints</em>' attribute is set.
	 * @see #unsetHonorConstraints()
	 * @see #isHonorConstraints()
	 * @see #setHonorConstraints(boolean)
	 * @generated
	 */
	boolean isSetHonorConstraints();

	/**
	 * Returns the value of the '<em><b>Earned Value Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default method for calculating earned value. Values are: 
	 * 						0=Percent Complete, 1=Physical Percent Complete
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Earned Value Method</em>' attribute.
	 * @see #setEarnedValueMethod(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_EarnedValueMethod()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.EarnedValueMethodType1"
	 *        extendedMetaData="kind='element' name='EarnedValueMethod' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getEarnedValueMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getEarnedValueMethod <em>Earned Value Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Earned Value Method</em>' attribute.
	 * @see #getEarnedValueMethod()
	 * @generated
	 */
	void setEarnedValueMethod(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Inserted Projects Like Summary</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether to calculate subtasks as summary tasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inserted Projects Like Summary</em>' attribute.
	 * @see #isSetInsertedProjectsLikeSummary()
	 * @see #unsetInsertedProjectsLikeSummary()
	 * @see #setInsertedProjectsLikeSummary(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_InsertedProjectsLikeSummary()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='InsertedProjectsLikeSummary' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isInsertedProjectsLikeSummary();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isInsertedProjectsLikeSummary <em>Inserted Projects Like Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inserted Projects Like Summary</em>' attribute.
	 * @see #isSetInsertedProjectsLikeSummary()
	 * @see #unsetInsertedProjectsLikeSummary()
	 * @see #isInsertedProjectsLikeSummary()
	 * @generated
	 */
	void setInsertedProjectsLikeSummary(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isInsertedProjectsLikeSummary <em>Inserted Projects Like Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInsertedProjectsLikeSummary()
	 * @see #isInsertedProjectsLikeSummary()
	 * @see #setInsertedProjectsLikeSummary(boolean)
	 * @generated
	 */
	void unsetInsertedProjectsLikeSummary();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isInsertedProjectsLikeSummary <em>Inserted Projects Like Summary</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Inserted Projects Like Summary</em>' attribute is set.
	 * @see #unsetInsertedProjectsLikeSummary()
	 * @see #isInsertedProjectsLikeSummary()
	 * @see #setInsertedProjectsLikeSummary(boolean)
	 * @generated
	 */
	boolean isSetInsertedProjectsLikeSummary();

	/**
	 * Returns the value of the '<em><b>Multiple Critical Paths</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether multiple critical paths are calculated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Multiple Critical Paths</em>' attribute.
	 * @see #isSetMultipleCriticalPaths()
	 * @see #unsetMultipleCriticalPaths()
	 * @see #setMultipleCriticalPaths(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MultipleCriticalPaths()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='MultipleCriticalPaths' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMultipleCriticalPaths();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isMultipleCriticalPaths <em>Multiple Critical Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiple Critical Paths</em>' attribute.
	 * @see #isSetMultipleCriticalPaths()
	 * @see #unsetMultipleCriticalPaths()
	 * @see #isMultipleCriticalPaths()
	 * @generated
	 */
	void setMultipleCriticalPaths(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isMultipleCriticalPaths <em>Multiple Critical Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMultipleCriticalPaths()
	 * @see #isMultipleCriticalPaths()
	 * @see #setMultipleCriticalPaths(boolean)
	 * @generated
	 */
	void unsetMultipleCriticalPaths();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isMultipleCriticalPaths <em>Multiple Critical Paths</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Multiple Critical Paths</em>' attribute is set.
	 * @see #unsetMultipleCriticalPaths()
	 * @see #isMultipleCriticalPaths()
	 * @see #setMultipleCriticalPaths(boolean)
	 * @generated
	 */
	boolean isSetMultipleCriticalPaths();

	/**
	 * Returns the value of the '<em><b>New Tasks Effort Driven</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether new tasks are effort driven.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>New Tasks Effort Driven</em>' attribute.
	 * @see #isSetNewTasksEffortDriven()
	 * @see #unsetNewTasksEffortDriven()
	 * @see #setNewTasksEffortDriven(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_NewTasksEffortDriven()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='NewTasksEffortDriven' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isNewTasksEffortDriven();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isNewTasksEffortDriven <em>New Tasks Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Tasks Effort Driven</em>' attribute.
	 * @see #isSetNewTasksEffortDriven()
	 * @see #unsetNewTasksEffortDriven()
	 * @see #isNewTasksEffortDriven()
	 * @generated
	 */
	void setNewTasksEffortDriven(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isNewTasksEffortDriven <em>New Tasks Effort Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetNewTasksEffortDriven()
	 * @see #isNewTasksEffortDriven()
	 * @see #setNewTasksEffortDriven(boolean)
	 * @generated
	 */
	void unsetNewTasksEffortDriven();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isNewTasksEffortDriven <em>New Tasks Effort Driven</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>New Tasks Effort Driven</em>' attribute is set.
	 * @see #unsetNewTasksEffortDriven()
	 * @see #isNewTasksEffortDriven()
	 * @see #setNewTasksEffortDriven(boolean)
	 * @generated
	 */
	boolean isSetNewTasksEffortDriven();

	/**
	 * Returns the value of the '<em><b>New Tasks Estimated</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether to show the estimated duration by default.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>New Tasks Estimated</em>' attribute.
	 * @see #isSetNewTasksEstimated()
	 * @see #unsetNewTasksEstimated()
	 * @see #setNewTasksEstimated(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_NewTasksEstimated()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='NewTasksEstimated' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isNewTasksEstimated();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isNewTasksEstimated <em>New Tasks Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Tasks Estimated</em>' attribute.
	 * @see #isSetNewTasksEstimated()
	 * @see #unsetNewTasksEstimated()
	 * @see #isNewTasksEstimated()
	 * @generated
	 */
	void setNewTasksEstimated(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isNewTasksEstimated <em>New Tasks Estimated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetNewTasksEstimated()
	 * @see #isNewTasksEstimated()
	 * @see #setNewTasksEstimated(boolean)
	 * @generated
	 */
	void unsetNewTasksEstimated();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isNewTasksEstimated <em>New Tasks Estimated</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>New Tasks Estimated</em>' attribute is set.
	 * @see #unsetNewTasksEstimated()
	 * @see #isNewTasksEstimated()
	 * @see #setNewTasksEstimated(boolean)
	 * @generated
	 */
	boolean isSetNewTasksEstimated();

	/**
	 * Returns the value of the '<em><b>Splits In Progress Tasks</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether in-progress tasks can be split.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Splits In Progress Tasks</em>' attribute.
	 * @see #isSetSplitsInProgressTasks()
	 * @see #unsetSplitsInProgressTasks()
	 * @see #setSplitsInProgressTasks(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_SplitsInProgressTasks()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='SplitsInProgressTasks' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSplitsInProgressTasks();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isSplitsInProgressTasks <em>Splits In Progress Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Splits In Progress Tasks</em>' attribute.
	 * @see #isSetSplitsInProgressTasks()
	 * @see #unsetSplitsInProgressTasks()
	 * @see #isSplitsInProgressTasks()
	 * @generated
	 */
	void setSplitsInProgressTasks(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isSplitsInProgressTasks <em>Splits In Progress Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSplitsInProgressTasks()
	 * @see #isSplitsInProgressTasks()
	 * @see #setSplitsInProgressTasks(boolean)
	 * @generated
	 */
	void unsetSplitsInProgressTasks();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isSplitsInProgressTasks <em>Splits In Progress Tasks</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Splits In Progress Tasks</em>' attribute is set.
	 * @see #unsetSplitsInProgressTasks()
	 * @see #isSplitsInProgressTasks()
	 * @see #setSplitsInProgressTasks(boolean)
	 * @generated
	 */
	boolean isSetSplitsInProgressTasks();

	/**
	 * Returns the value of the '<em><b>Spread Actual Cost</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether actual costs are spread to the status date.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Spread Actual Cost</em>' attribute.
	 * @see #isSetSpreadActualCost()
	 * @see #unsetSpreadActualCost()
	 * @see #setSpreadActualCost(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_SpreadActualCost()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='SpreadActualCost' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSpreadActualCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isSpreadActualCost <em>Spread Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spread Actual Cost</em>' attribute.
	 * @see #isSetSpreadActualCost()
	 * @see #unsetSpreadActualCost()
	 * @see #isSpreadActualCost()
	 * @generated
	 */
	void setSpreadActualCost(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isSpreadActualCost <em>Spread Actual Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSpreadActualCost()
	 * @see #isSpreadActualCost()
	 * @see #setSpreadActualCost(boolean)
	 * @generated
	 */
	void unsetSpreadActualCost();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isSpreadActualCost <em>Spread Actual Cost</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Spread Actual Cost</em>' attribute is set.
	 * @see #unsetSpreadActualCost()
	 * @see #isSpreadActualCost()
	 * @see #setSpreadActualCost(boolean)
	 * @generated
	 */
	boolean isSetSpreadActualCost();

	/**
	 * Returns the value of the '<em><b>Spread Percent Complete</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether percent complete is spread to the status date.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Spread Percent Complete</em>' attribute.
	 * @see #isSetSpreadPercentComplete()
	 * @see #unsetSpreadPercentComplete()
	 * @see #setSpreadPercentComplete(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_SpreadPercentComplete()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='SpreadPercentComplete' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSpreadPercentComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isSpreadPercentComplete <em>Spread Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spread Percent Complete</em>' attribute.
	 * @see #isSetSpreadPercentComplete()
	 * @see #unsetSpreadPercentComplete()
	 * @see #isSpreadPercentComplete()
	 * @generated
	 */
	void setSpreadPercentComplete(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isSpreadPercentComplete <em>Spread Percent Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSpreadPercentComplete()
	 * @see #isSpreadPercentComplete()
	 * @see #setSpreadPercentComplete(boolean)
	 * @generated
	 */
	void unsetSpreadPercentComplete();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isSpreadPercentComplete <em>Spread Percent Complete</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Spread Percent Complete</em>' attribute is set.
	 * @see #unsetSpreadPercentComplete()
	 * @see #isSpreadPercentComplete()
	 * @see #setSpreadPercentComplete(boolean)
	 * @generated
	 */
	boolean isSetSpreadPercentComplete();

	/**
	 * Returns the value of the '<em><b>Task Updates Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether updates to tasks update resources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Task Updates Resource</em>' attribute.
	 * @see #isSetTaskUpdatesResource()
	 * @see #unsetTaskUpdatesResource()
	 * @see #setTaskUpdatesResource(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_TaskUpdatesResource()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='TaskUpdatesResource' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isTaskUpdatesResource();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isTaskUpdatesResource <em>Task Updates Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Updates Resource</em>' attribute.
	 * @see #isSetTaskUpdatesResource()
	 * @see #unsetTaskUpdatesResource()
	 * @see #isTaskUpdatesResource()
	 * @generated
	 */
	void setTaskUpdatesResource(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isTaskUpdatesResource <em>Task Updates Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTaskUpdatesResource()
	 * @see #isTaskUpdatesResource()
	 * @see #setTaskUpdatesResource(boolean)
	 * @generated
	 */
	void unsetTaskUpdatesResource();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isTaskUpdatesResource <em>Task Updates Resource</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Task Updates Resource</em>' attribute is set.
	 * @see #unsetTaskUpdatesResource()
	 * @see #isTaskUpdatesResource()
	 * @see #setTaskUpdatesResource(boolean)
	 * @generated
	 */
	boolean isSetTaskUpdatesResource();

	/**
	 * Returns the value of the '<em><b>Fiscal Year Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether to use fiscal year numbering
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fiscal Year Start</em>' attribute.
	 * @see #isSetFiscalYearStart()
	 * @see #unsetFiscalYearStart()
	 * @see #setFiscalYearStart(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_FiscalYearStart()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='FiscalYearStart' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isFiscalYearStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isFiscalYearStart <em>Fiscal Year Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fiscal Year Start</em>' attribute.
	 * @see #isSetFiscalYearStart()
	 * @see #unsetFiscalYearStart()
	 * @see #isFiscalYearStart()
	 * @generated
	 */
	void setFiscalYearStart(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isFiscalYearStart <em>Fiscal Year Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFiscalYearStart()
	 * @see #isFiscalYearStart()
	 * @see #setFiscalYearStart(boolean)
	 * @generated
	 */
	void unsetFiscalYearStart();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isFiscalYearStart <em>Fiscal Year Start</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fiscal Year Start</em>' attribute is set.
	 * @see #unsetFiscalYearStart()
	 * @see #isFiscalYearStart()
	 * @see #setFiscalYearStart(boolean)
	 * @generated
	 */
	boolean isSetFiscalYearStart();

	/**
	 * Returns the value of the '<em><b>Week Start Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Start day of the week. Values are: 
	 * 						0=Sunday, 1=Monday, 2=Tuesday, 3=Wednesday, 4=Thursday, 5=Friday, 6=Saturday
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Week Start Day</em>' attribute.
	 * @see #setWeekStartDay(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_WeekStartDay()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.WeekStartDayType"
	 *        extendedMetaData="kind='element' name='WeekStartDay' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getWeekStartDay();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getWeekStartDay <em>Week Start Day</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Week Start Day</em>' attribute.
	 * @see #getWeekStartDay()
	 * @generated
	 */
	void setWeekStartDay(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Move Completed Ends Back</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 							Specifies whether the end of completed portions of tasks scheduled to begin after the 
	 * 							status date but begun early should be moved back to the status date.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Move Completed Ends Back</em>' attribute.
	 * @see #isSetMoveCompletedEndsBack()
	 * @see #unsetMoveCompletedEndsBack()
	 * @see #setMoveCompletedEndsBack(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MoveCompletedEndsBack()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='MoveCompletedEndsBack' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMoveCompletedEndsBack();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsBack <em>Move Completed Ends Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Move Completed Ends Back</em>' attribute.
	 * @see #isSetMoveCompletedEndsBack()
	 * @see #unsetMoveCompletedEndsBack()
	 * @see #isMoveCompletedEndsBack()
	 * @generated
	 */
	void setMoveCompletedEndsBack(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsBack <em>Move Completed Ends Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMoveCompletedEndsBack()
	 * @see #isMoveCompletedEndsBack()
	 * @see #setMoveCompletedEndsBack(boolean)
	 * @generated
	 */
	void unsetMoveCompletedEndsBack();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsBack <em>Move Completed Ends Back</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Move Completed Ends Back</em>' attribute is set.
	 * @see #unsetMoveCompletedEndsBack()
	 * @see #isMoveCompletedEndsBack()
	 * @see #setMoveCompletedEndsBack(boolean)
	 * @generated
	 */
	boolean isSetMoveCompletedEndsBack();

	/**
	 * Returns the value of the '<em><b>Move Remaining Starts Back</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 							Specifies whether the beginning of remaining portions of tasks scheduled to begin after the 
	 * 							status date but begun early should be moved back to the status date.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Move Remaining Starts Back</em>' attribute.
	 * @see #isSetMoveRemainingStartsBack()
	 * @see #unsetMoveRemainingStartsBack()
	 * @see #setMoveRemainingStartsBack(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MoveRemainingStartsBack()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='MoveRemainingStartsBack' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMoveRemainingStartsBack();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsBack <em>Move Remaining Starts Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Move Remaining Starts Back</em>' attribute.
	 * @see #isSetMoveRemainingStartsBack()
	 * @see #unsetMoveRemainingStartsBack()
	 * @see #isMoveRemainingStartsBack()
	 * @generated
	 */
	void setMoveRemainingStartsBack(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsBack <em>Move Remaining Starts Back</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMoveRemainingStartsBack()
	 * @see #isMoveRemainingStartsBack()
	 * @see #setMoveRemainingStartsBack(boolean)
	 * @generated
	 */
	void unsetMoveRemainingStartsBack();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsBack <em>Move Remaining Starts Back</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Move Remaining Starts Back</em>' attribute is set.
	 * @see #unsetMoveRemainingStartsBack()
	 * @see #isMoveRemainingStartsBack()
	 * @see #setMoveRemainingStartsBack(boolean)
	 * @generated
	 */
	boolean isSetMoveRemainingStartsBack();

	/**
	 * Returns the value of the '<em><b>Move Remaining Starts Forward</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 							Specifies whether the beginning of remaining portions of tasks scheduled to have begun late 
	 * 							should be moved up to the status date.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Move Remaining Starts Forward</em>' attribute.
	 * @see #isSetMoveRemainingStartsForward()
	 * @see #unsetMoveRemainingStartsForward()
	 * @see #setMoveRemainingStartsForward(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MoveRemainingStartsForward()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='MoveRemainingStartsForward' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMoveRemainingStartsForward();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsForward <em>Move Remaining Starts Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Move Remaining Starts Forward</em>' attribute.
	 * @see #isSetMoveRemainingStartsForward()
	 * @see #unsetMoveRemainingStartsForward()
	 * @see #isMoveRemainingStartsForward()
	 * @generated
	 */
	void setMoveRemainingStartsForward(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsForward <em>Move Remaining Starts Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMoveRemainingStartsForward()
	 * @see #isMoveRemainingStartsForward()
	 * @see #setMoveRemainingStartsForward(boolean)
	 * @generated
	 */
	void unsetMoveRemainingStartsForward();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsForward <em>Move Remaining Starts Forward</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Move Remaining Starts Forward</em>' attribute is set.
	 * @see #unsetMoveRemainingStartsForward()
	 * @see #isMoveRemainingStartsForward()
	 * @see #setMoveRemainingStartsForward(boolean)
	 * @generated
	 */
	boolean isSetMoveRemainingStartsForward();

	/**
	 * Returns the value of the '<em><b>Move Completed Ends Forward</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 							Specifies whether the end of completed portions of tasks scheduled to have been completed 
	 * 							before the status date but begun late should be moved up to the status date.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Move Completed Ends Forward</em>' attribute.
	 * @see #isSetMoveCompletedEndsForward()
	 * @see #unsetMoveCompletedEndsForward()
	 * @see #setMoveCompletedEndsForward(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MoveCompletedEndsForward()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='MoveCompletedEndsForward' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMoveCompletedEndsForward();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsForward <em>Move Completed Ends Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Move Completed Ends Forward</em>' attribute.
	 * @see #isSetMoveCompletedEndsForward()
	 * @see #unsetMoveCompletedEndsForward()
	 * @see #isMoveCompletedEndsForward()
	 * @generated
	 */
	void setMoveCompletedEndsForward(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsForward <em>Move Completed Ends Forward</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMoveCompletedEndsForward()
	 * @see #isMoveCompletedEndsForward()
	 * @see #setMoveCompletedEndsForward(boolean)
	 * @generated
	 */
	void unsetMoveCompletedEndsForward();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsForward <em>Move Completed Ends Forward</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Move Completed Ends Forward</em>' attribute is set.
	 * @see #unsetMoveCompletedEndsForward()
	 * @see #isMoveCompletedEndsForward()
	 * @see #setMoveCompletedEndsForward(boolean)
	 * @generated
	 */
	boolean isSetMoveCompletedEndsForward();

	/**
	 * Returns the value of the '<em><b>Baseline For Earned Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The specific baseline used to calculate Variance values.  Values are: 
	 * 						0=Baseline, 1=Baseline 1, 2=Baseline 2, 3=Baseline 3, 4=Baseline 4, 5=Baseline 5, 6=Baseline 6, 
	 * 						7=Baseline 7, 8=Baseline 8, 9=Baseline 9, 10=Baseline 10
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Baseline For Earned Value</em>' attribute.
	 * @see #setBaselineForEarnedValue(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_BaselineForEarnedValue()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.BaselineForEarnedValueType"
	 *        extendedMetaData="kind='element' name='BaselineForEarnedValue' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getBaselineForEarnedValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getBaselineForEarnedValue <em>Baseline For Earned Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Baseline For Earned Value</em>' attribute.
	 * @see #getBaselineForEarnedValue()
	 * @generated
	 */
	void setBaselineForEarnedValue(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Auto Add New Resources And Tasks</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether to automatically add new resources to the resource pool.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Auto Add New Resources And Tasks</em>' attribute.
	 * @see #isSetAutoAddNewResourcesAndTasks()
	 * @see #unsetAutoAddNewResourcesAndTasks()
	 * @see #setAutoAddNewResourcesAndTasks(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_AutoAddNewResourcesAndTasks()
	 * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='AutoAddNewResourcesAndTasks' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isAutoAddNewResourcesAndTasks();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isAutoAddNewResourcesAndTasks <em>Auto Add New Resources And Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Add New Resources And Tasks</em>' attribute.
	 * @see #isSetAutoAddNewResourcesAndTasks()
	 * @see #unsetAutoAddNewResourcesAndTasks()
	 * @see #isAutoAddNewResourcesAndTasks()
	 * @generated
	 */
	void setAutoAddNewResourcesAndTasks(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isAutoAddNewResourcesAndTasks <em>Auto Add New Resources And Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAutoAddNewResourcesAndTasks()
	 * @see #isAutoAddNewResourcesAndTasks()
	 * @see #setAutoAddNewResourcesAndTasks(boolean)
	 * @generated
	 */
	void unsetAutoAddNewResourcesAndTasks();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isAutoAddNewResourcesAndTasks <em>Auto Add New Resources And Tasks</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Auto Add New Resources And Tasks</em>' attribute is set.
	 * @see #unsetAutoAddNewResourcesAndTasks()
	 * @see #isAutoAddNewResourcesAndTasks()
	 * @see #setAutoAddNewResourcesAndTasks(boolean)
	 * @generated
	 */
	boolean isSetAutoAddNewResourcesAndTasks();

	/**
	 * Returns the value of the '<em><b>Status Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Date used for calculation and reporting.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status Date</em>' attribute.
	 * @see #setStatusDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_StatusDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='StatusDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStatusDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getStatusDate <em>Status Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status Date</em>' attribute.
	 * @see #getStatusDate()
	 * @generated
	 */
	void setStatusDate(Object value);

	/**
	 * Returns the value of the '<em><b>Current Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The system date that the XML was generated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Current Date</em>' attribute.
	 * @see #setCurrentDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_CurrentDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='CurrentDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getCurrentDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCurrentDate <em>Current Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Date</em>' attribute.
	 * @see #getCurrentDate()
	 * @generated
	 */
	void setCurrentDate(Object value);

	/**
	 * Returns the value of the '<em><b>Microsoft Project Server URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the project was created by a Project Server user as opposed to an NT user.
	 * 						
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Microsoft Project Server URL</em>' attribute.
	 * @see #isSetMicrosoftProjectServerURL()
	 * @see #unsetMicrosoftProjectServerURL()
	 * @see #setMicrosoftProjectServerURL(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_MicrosoftProjectServerURL()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='MicrosoftProjectServerURL' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMicrosoftProjectServerURL();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isMicrosoftProjectServerURL <em>Microsoft Project Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Microsoft Project Server URL</em>' attribute.
	 * @see #isSetMicrosoftProjectServerURL()
	 * @see #unsetMicrosoftProjectServerURL()
	 * @see #isMicrosoftProjectServerURL()
	 * @generated
	 */
	void setMicrosoftProjectServerURL(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isMicrosoftProjectServerURL <em>Microsoft Project Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMicrosoftProjectServerURL()
	 * @see #isMicrosoftProjectServerURL()
	 * @see #setMicrosoftProjectServerURL(boolean)
	 * @generated
	 */
	void unsetMicrosoftProjectServerURL();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isMicrosoftProjectServerURL <em>Microsoft Project Server URL</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Microsoft Project Server URL</em>' attribute is set.
	 * @see #unsetMicrosoftProjectServerURL()
	 * @see #isMicrosoftProjectServerURL()
	 * @see #setMicrosoftProjectServerURL(boolean)
	 * @generated
	 */
	boolean isSetMicrosoftProjectServerURL();

	/**
	 * Returns the value of the '<em><b>Autolink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether to autolink inserted or moved tasks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Autolink</em>' attribute.
	 * @see #isSetAutolink()
	 * @see #unsetAutolink()
	 * @see #setAutolink(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Autolink()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Autolink' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isAutolink();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isAutolink <em>Autolink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Autolink</em>' attribute.
	 * @see #isSetAutolink()
	 * @see #unsetAutolink()
	 * @see #isAutolink()
	 * @generated
	 */
	void setAutolink(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isAutolink <em>Autolink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAutolink()
	 * @see #isAutolink()
	 * @see #setAutolink(boolean)
	 * @generated
	 */
	void unsetAutolink();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isAutolink <em>Autolink</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Autolink</em>' attribute is set.
	 * @see #unsetAutolink()
	 * @see #isAutolink()
	 * @see #setAutolink(boolean)
	 * @generated
	 */
	boolean isSetAutolink();

	/**
	 * Returns the value of the '<em><b>New Task Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default date for new tasks start.  Values are: 
	 * 						0=Project Start Date, 1=Current Date
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>New Task Start Date</em>' attribute.
	 * @see #setNewTaskStartDate(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_NewTaskStartDate()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.NewTaskStartDateType"
	 *        extendedMetaData="kind='element' name='NewTaskStartDate' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getNewTaskStartDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getNewTaskStartDate <em>New Task Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Task Start Date</em>' attribute.
	 * @see #getNewTaskStartDate()
	 * @generated
	 */
	void setNewTaskStartDate(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Default Task EV Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default earned value method for tasks.  Values are: 
	 * 						0=Percent Complete, 1=Physical Percent Complete
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Task EV Method</em>' attribute.
	 * @see #setDefaultTaskEVMethod(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_DefaultTaskEVMethod()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DefaultTaskEVMethodType"
	 *        extendedMetaData="kind='element' name='DefaultTaskEVMethod' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDefaultTaskEVMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getDefaultTaskEVMethod <em>Default Task EV Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Task EV Method</em>' attribute.
	 * @see #getDefaultTaskEVMethod()
	 * @generated
	 */
	void setDefaultTaskEVMethod(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Project Externally Edited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the project XML was edited.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Project Externally Edited</em>' attribute.
	 * @see #isSetProjectExternallyEdited()
	 * @see #unsetProjectExternallyEdited()
	 * @see #setProjectExternallyEdited(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_ProjectExternallyEdited()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ProjectExternallyEdited' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isProjectExternallyEdited();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isProjectExternallyEdited <em>Project Externally Edited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Externally Edited</em>' attribute.
	 * @see #isSetProjectExternallyEdited()
	 * @see #unsetProjectExternallyEdited()
	 * @see #isProjectExternallyEdited()
	 * @generated
	 */
	void setProjectExternallyEdited(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isProjectExternallyEdited <em>Project Externally Edited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProjectExternallyEdited()
	 * @see #isProjectExternallyEdited()
	 * @see #setProjectExternallyEdited(boolean)
	 * @generated
	 */
	void unsetProjectExternallyEdited();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isProjectExternallyEdited <em>Project Externally Edited</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Project Externally Edited</em>' attribute is set.
	 * @see #unsetProjectExternallyEdited()
	 * @see #isProjectExternallyEdited()
	 * @see #setProjectExternallyEdited(boolean)
	 * @generated
	 */
	boolean isSetProjectExternallyEdited();

	/**
	 * Returns the value of the '<em><b>Extended Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Date used for calculation and reporting.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Creation Date</em>' attribute.
	 * @see #setExtendedCreationDate(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_ExtendedCreationDate()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='ExtendedCreationDate' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getExtendedCreationDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getExtendedCreationDate <em>Extended Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Creation Date</em>' attribute.
	 * @see #getExtendedCreationDate()
	 * @generated
	 */
	void setExtendedCreationDate(Object value);

	/**
	 * Returns the value of the '<em><b>Actuals In Sync</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether all actual work has been synchronized with the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actuals In Sync</em>' attribute.
	 * @see #isSetActualsInSync()
	 * @see #unsetActualsInSync()
	 * @see #setActualsInSync(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_ActualsInSync()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='ActualsInSync' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isActualsInSync();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isActualsInSync <em>Actuals In Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actuals In Sync</em>' attribute.
	 * @see #isSetActualsInSync()
	 * @see #unsetActualsInSync()
	 * @see #isActualsInSync()
	 * @generated
	 */
	void setActualsInSync(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isActualsInSync <em>Actuals In Sync</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetActualsInSync()
	 * @see #isActualsInSync()
	 * @see #setActualsInSync(boolean)
	 * @generated
	 */
	void unsetActualsInSync();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isActualsInSync <em>Actuals In Sync</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Actuals In Sync</em>' attribute is set.
	 * @see #unsetActualsInSync()
	 * @see #isActualsInSync()
	 * @see #setActualsInSync(boolean)
	 * @generated
	 */
	boolean isSetActualsInSync();

	/**
	 * Returns the value of the '<em><b>Remove File Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether to remove all file properties on save.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Remove File Properties</em>' attribute.
	 * @see #isSetRemoveFileProperties()
	 * @see #unsetRemoveFileProperties()
	 * @see #setRemoveFileProperties(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_RemoveFileProperties()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='RemoveFileProperties' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isRemoveFileProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isRemoveFileProperties <em>Remove File Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remove File Properties</em>' attribute.
	 * @see #isSetRemoveFileProperties()
	 * @see #unsetRemoveFileProperties()
	 * @see #isRemoveFileProperties()
	 * @generated
	 */
	void setRemoveFileProperties(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isRemoveFileProperties <em>Remove File Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRemoveFileProperties()
	 * @see #isRemoveFileProperties()
	 * @see #setRemoveFileProperties(boolean)
	 * @generated
	 */
	void unsetRemoveFileProperties();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isRemoveFileProperties <em>Remove File Properties</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Remove File Properties</em>' attribute is set.
	 * @see #unsetRemoveFileProperties()
	 * @see #isRemoveFileProperties()
	 * @see #setRemoveFileProperties(boolean)
	 * @generated
	 */
	boolean isSetRemoveFileProperties();

	/**
	 * Returns the value of the '<em><b>Admin Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the project is an administrative project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Admin Project</em>' attribute.
	 * @see #isSetAdminProject()
	 * @see #unsetAdminProject()
	 * @see #setAdminProject(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_AdminProject()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='AdminProject' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isAdminProject();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#isAdminProject <em>Admin Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Admin Project</em>' attribute.
	 * @see #isSetAdminProject()
	 * @see #unsetAdminProject()
	 * @see #isAdminProject()
	 * @generated
	 */
	void setAdminProject(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Project#isAdminProject <em>Admin Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAdminProject()
	 * @see #isAdminProject()
	 * @see #setAdminProject(boolean)
	 * @generated
	 */
	void unsetAdminProject();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Project#isAdminProject <em>Admin Project</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Admin Project</em>' attribute is set.
	 * @see #unsetAdminProject()
	 * @see #isAdminProject()
	 * @see #setAdminProject(boolean)
	 * @generated
	 */
	boolean isSetAdminProject();

	/**
	 * Returns the value of the '<em><b>Outline Codes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of outline code definitions associated with the project.  These codes may be associated only with this project, or may be common among several projects (enterprise codes).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline Codes</em>' containment reference.
	 * @see #setOutlineCodes(OutlineCodes)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_OutlineCodes()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='OutlineCodes' namespace='##targetNamespace'"
	 * @generated
	 */
	OutlineCodes getOutlineCodes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getOutlineCodes <em>Outline Codes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outline Codes</em>' containment reference.
	 * @see #getOutlineCodes()
	 * @generated
	 */
	void setOutlineCodes(OutlineCodes value);

	/**
	 * Returns the value of the '<em><b>WBS Masks</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The table of entries that define the outline code mask.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>WBS Masks</em>' containment reference.
	 * @see #setWBSMasks(WBSMasks)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_WBSMasks()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='WBSMasks' namespace='##targetNamespace'"
	 * @generated
	 */
	WBSMasks getWBSMasks();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getWBSMasks <em>WBS Masks</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>WBS Masks</em>' containment reference.
	 * @see #getWBSMasks()
	 * @generated
	 */
	void setWBSMasks(WBSMasks value);

	/**
	 * Returns the value of the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of extended attribute (custom field) definitions 
	 *         associated with the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #setExtendedAttributes(ExtendedAttributes)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_ExtendedAttributes()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributes getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributes value);

	/**
	 * Returns the value of the '<em><b>Calendars</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of calendars that is associated with the 
	 *         project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calendars</em>' containment reference.
	 * @see #setCalendars(Calendars)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Calendars()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Calendars' namespace='##targetNamespace'"
	 * @generated
	 */
	Calendars getCalendars();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getCalendars <em>Calendars</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calendars</em>' containment reference.
	 * @see #getCalendars()
	 * @generated
	 */
	void setCalendars(Calendars value);

	/**
	 * Returns the value of the '<em><b>Tasks</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of tasks that make up the project.
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tasks</em>' containment reference.
	 * @see #setTasks(Tasks)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Tasks()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Tasks' namespace='##targetNamespace'"
	 * @generated
	 */
	Tasks getTasks();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getTasks <em>Tasks</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tasks</em>' containment reference.
	 * @see #getTasks()
	 * @generated
	 */
	void setTasks(Tasks value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of resources that make up the project.
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference.
	 * @see #setResources(Resources)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Resources()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Resources' namespace='##targetNamespace'"
	 * @generated
	 */
	Resources getResources();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getResources <em>Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resources</em>' containment reference.
	 * @see #getResources()
	 * @generated
	 */
	void setResources(Resources value);

	/**
	 * Returns the value of the '<em><b>Assignments</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection of assignments that make up the project.
	 *     
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assignments</em>' containment reference.
	 * @see #setAssignments(Assignments)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getProject_Assignments()
	 * @model containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='Assignments' namespace='##targetNamespace'"
	 * @generated
	 */
	Assignments getAssignments();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Project#getAssignments <em>Assignments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignments</em>' containment reference.
	 * @see #getAssignments()
	 * @generated
	 */
	void setAssignments(Assignments value);

} // Project
