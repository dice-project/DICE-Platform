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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The full project schema definition from the project level down, 
 * 		starting with data type definitions.
 * <!-- end-model-doc -->
 * @see org.eclipse.epf.msproject.MsprojectFactory
 * @model kind="package"
 * @generated
 */
public interface MsprojectPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "msproject";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://epf.eclipse.org/msproject";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "msproject";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MsprojectPackage eINSTANCE = org.eclipse.epf.msproject.impl.MsprojectPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.AssignmentImpl <em>Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.AssignmentImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAssignment()
	 * @generated
	 */
	int ASSIGNMENT = 0;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__UID = 0;

	/**
	 * The feature id for the '<em><b>Task UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__TASK_UID = 1;

	/**
	 * The feature id for the '<em><b>Resource UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__RESOURCE_UID = 2;

	/**
	 * The feature id for the '<em><b>Percent Work Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__PERCENT_WORK_COMPLETE = 3;

	/**
	 * The feature id for the '<em><b>Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_COST = 4;

	/**
	 * The feature id for the '<em><b>Actual Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_FINISH = 5;

	/**
	 * The feature id for the '<em><b>Actual Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_OVERTIME_COST = 6;

	/**
	 * The feature id for the '<em><b>Actual Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_OVERTIME_WORK = 7;

	/**
	 * The feature id for the '<em><b>Actual Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_START = 8;

	/**
	 * The feature id for the '<em><b>Actual Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_WORK = 9;

	/**
	 * The feature id for the '<em><b>ACWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACWP = 10;

	/**
	 * The feature id for the '<em><b>Confirmed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__CONFIRMED = 11;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__COST = 12;

	/**
	 * The feature id for the '<em><b>Cost Rate Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__COST_RATE_TABLE = 13;

	/**
	 * The feature id for the '<em><b>Cost Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__COST_VARIANCE = 14;

	/**
	 * The feature id for the '<em><b>CV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__CV = 15;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__DELAY = 16;

	/**
	 * The feature id for the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__FINISH = 17;

	/**
	 * The feature id for the '<em><b>Finish Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__FINISH_VARIANCE = 18;

	/**
	 * The feature id for the '<em><b>Hyperlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__HYPERLINK = 19;

	/**
	 * The feature id for the '<em><b>Hyperlink Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__HYPERLINK_ADDRESS = 20;

	/**
	 * The feature id for the '<em><b>Hyperlink Sub Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__HYPERLINK_SUB_ADDRESS = 21;

	/**
	 * The feature id for the '<em><b>Work Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__WORK_VARIANCE = 22;

	/**
	 * The feature id for the '<em><b>Has Fixed Rate Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__HAS_FIXED_RATE_UNITS = 23;

	/**
	 * The feature id for the '<em><b>Fixed Material</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__FIXED_MATERIAL = 24;

	/**
	 * The feature id for the '<em><b>Leveling Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__LEVELING_DELAY = 25;

	/**
	 * The feature id for the '<em><b>Leveling Delay Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__LEVELING_DELAY_FORMAT = 26;

	/**
	 * The feature id for the '<em><b>Linked Fields</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__LINKED_FIELDS = 27;

	/**
	 * The feature id for the '<em><b>Milestone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__MILESTONE = 28;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__NOTES = 29;

	/**
	 * The feature id for the '<em><b>Overallocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__OVERALLOCATED = 30;

	/**
	 * The feature id for the '<em><b>Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__OVERTIME_COST = 31;

	/**
	 * The feature id for the '<em><b>Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__OVERTIME_WORK = 32;

	/**
	 * The feature id for the '<em><b>Regular Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__REGULAR_WORK = 33;

	/**
	 * The feature id for the '<em><b>Remaining Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__REMAINING_COST = 34;

	/**
	 * The feature id for the '<em><b>Remaining Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__REMAINING_OVERTIME_COST = 35;

	/**
	 * The feature id for the '<em><b>Remaining Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__REMAINING_OVERTIME_WORK = 36;

	/**
	 * The feature id for the '<em><b>Remaining Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__REMAINING_WORK = 37;

	/**
	 * The feature id for the '<em><b>Response Pending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__RESPONSE_PENDING = 38;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__START = 39;

	/**
	 * The feature id for the '<em><b>Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__STOP = 40;

	/**
	 * The feature id for the '<em><b>Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__RESUME = 41;

	/**
	 * The feature id for the '<em><b>Start Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__START_VARIANCE = 42;

	/**
	 * The feature id for the '<em><b>Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__UNITS = 43;

	/**
	 * The feature id for the '<em><b>Update Needed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__UPDATE_NEEDED = 44;

	/**
	 * The feature id for the '<em><b>VAC</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__VAC = 45;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__WORK = 46;

	/**
	 * The feature id for the '<em><b>Work Contour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__WORK_CONTOUR = 47;

	/**
	 * The feature id for the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__BCWS = 48;

	/**
	 * The feature id for the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__BCWP = 49;

	/**
	 * The feature id for the '<em><b>Booking Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__BOOKING_TYPE = 50;

	/**
	 * The feature id for the '<em><b>Actual Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_WORK_PROTECTED = 51;

	/**
	 * The feature id for the '<em><b>Actual Overtime Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__ACTUAL_OVERTIME_WORK_PROTECTED = 52;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__CREATION_DATE = 53;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__EXTENDED_ATTRIBUTE = 54;

	/**
	 * The feature id for the '<em><b>Baseline</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__BASELINE = 55;

	/**
	 * The feature id for the '<em><b>Timephased Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__TIMEPHASED_DATA = 56;

	/**
	 * The number of structural features of the the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_FEATURE_COUNT = 57;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.AssignmentsImpl <em>Assignments</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.AssignmentsImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAssignments()
	 * @generated
	 */
	int ASSIGNMENTS = 1;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENTS__ASSIGNMENT = 0;

	/**
	 * The number of structural features of the the '<em>Assignments</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENTS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.AvailabilityPeriodImpl <em>Availability Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.AvailabilityPeriodImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAvailabilityPeriod()
	 * @generated
	 */
	int AVAILABILITY_PERIOD = 2;

	/**
	 * The feature id for the '<em><b>Available From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_PERIOD__AVAILABLE_FROM = 0;

	/**
	 * The feature id for the '<em><b>Available To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_PERIOD__AVAILABLE_TO = 1;

	/**
	 * The feature id for the '<em><b>Available Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_PERIOD__AVAILABLE_UNITS = 2;

	/**
	 * The number of structural features of the the '<em>Availability Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_PERIOD_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.AvailabilityPeriodsImpl <em>Availability Periods</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.AvailabilityPeriodsImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAvailabilityPeriods()
	 * @generated
	 */
	int AVAILABILITY_PERIODS = 3;

	/**
	 * The feature id for the '<em><b>Availability Period</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_PERIODS__AVAILABILITY_PERIOD = 0;

	/**
	 * The number of structural features of the the '<em>Availability Periods</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AVAILABILITY_PERIODS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.BaselineImpl <em>Baseline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.BaselineImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getBaseline()
	 * @generated
	 */
	int BASELINE = 4;

	/**
	 * The feature id for the '<em><b>Timephased Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__TIMEPHASED_DATA = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__NUMBER = 1;

	/**
	 * The feature id for the '<em><b>Interim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__INTERIM = 2;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__START = 3;

	/**
	 * The feature id for the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__FINISH = 4;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__DURATION = 5;

	/**
	 * The feature id for the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__DURATION_FORMAT = 6;

	/**
	 * The feature id for the '<em><b>Estimated Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__ESTIMATED_DURATION = 7;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__WORK = 8;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__COST = 9;

	/**
	 * The feature id for the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__BCWS = 10;

	/**
	 * The feature id for the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE__BCWP = 11;

	/**
	 * The number of structural features of the the '<em>Baseline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.Baseline2Impl <em>Baseline2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.Baseline2Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getBaseline2()
	 * @generated
	 */
	int BASELINE2 = 5;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE2__NUMBER = 0;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE2__WORK = 1;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE2__COST = 2;

	/**
	 * The feature id for the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE2__BCWS = 3;

	/**
	 * The feature id for the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE2__BCWP = 4;

	/**
	 * The number of structural features of the the '<em>Baseline2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE2_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.Baseline3Impl <em>Baseline3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.Baseline3Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getBaseline3()
	 * @generated
	 */
	int BASELINE3 = 6;

	/**
	 * The feature id for the '<em><b>Timephased Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__TIMEPHASED_DATA = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__NUMBER = 1;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__START = 2;

	/**
	 * The feature id for the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__FINISH = 3;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__WORK = 4;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__COST = 5;

	/**
	 * The feature id for the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__BCWS = 6;

	/**
	 * The feature id for the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3__BCWP = 7;

	/**
	 * The number of structural features of the the '<em>Baseline3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASELINE3_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.CalendarImpl <em>Calendar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.CalendarImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCalendar()
	 * @generated
	 */
	int CALENDAR = 7;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR__UID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR__NAME = 1;

	/**
	 * The feature id for the '<em><b>Is Base Calendar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR__IS_BASE_CALENDAR = 2;

	/**
	 * The feature id for the '<em><b>Base Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR__BASE_CALENDAR_UID = 3;

	/**
	 * The feature id for the '<em><b>Week Days</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR__WEEK_DAYS = 4;

	/**
	 * The number of structural features of the the '<em>Calendar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.CalendarsImpl <em>Calendars</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.CalendarsImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCalendars()
	 * @generated
	 */
	int CALENDARS = 8;

	/**
	 * The feature id for the '<em><b>Calendar</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDARS__CALENDAR = 0;

	/**
	 * The number of structural features of the the '<em>Calendars</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDARS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.DocumentRootImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 9;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Project</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROJECT = 3;

	/**
	 * The number of structural features of the the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl <em>Extended Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ExtendedAttributeImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getExtendedAttribute()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTE = 10;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__FIELD_ID = 0;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__FIELD_NAME = 1;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__ALIAS = 2;

	/**
	 * The feature id for the '<em><b>Phonetic Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__PHONETIC_ALIAS = 3;

	/**
	 * The feature id for the '<em><b>Rollup Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__ROLLUP_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Calculation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__CALCULATION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__FORMULA = 6;

	/**
	 * The feature id for the '<em><b>Restrict Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__RESTRICT_VALUES = 7;

	/**
	 * The feature id for the '<em><b>Valuelist Sort Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__VALUELIST_SORT_ORDER = 8;

	/**
	 * The feature id for the '<em><b>Append New Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES = 9;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__DEFAULT = 10;

	/**
	 * The feature id for the '<em><b>Value List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__VALUE_LIST = 11;

	/**
	 * The number of structural features of the the '<em>Extended Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ExtendedAttribute2Impl <em>Extended Attribute2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ExtendedAttribute2Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getExtendedAttribute2()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTE2 = 11;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE2__UID = 0;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE2__FIELD_ID = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE2__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE2__VALUE_ID = 3;

	/**
	 * The feature id for the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE2__DURATION_FORMAT = 4;

	/**
	 * The number of structural features of the the '<em>Extended Attribute2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE2_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ExtendedAttribute3Impl <em>Extended Attribute3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ExtendedAttribute3Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getExtendedAttribute3()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTE3 = 12;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE3__UID = 0;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE3__FIELD_ID = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE3__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE3__VALUE_ID = 3;

	/**
	 * The feature id for the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE3__DURATION_FORMAT = 4;

	/**
	 * The number of structural features of the the '<em>Extended Attribute3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE3_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl <em>Extended Attribute4</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ExtendedAttribute4Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getExtendedAttribute4()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTE4 = 13;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE4__UID = 0;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE4__FIELD_ID = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE4__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE4__VALUE_ID = 3;

	/**
	 * The feature id for the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE4__DURATION_FORMAT = 4;

	/**
	 * The number of structural features of the the '<em>Extended Attribute4</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE4_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ExtendedAttributesImpl <em>Extended Attributes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ExtendedAttributesImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getExtendedAttributes()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTES = 14;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTES__EXTENDED_ATTRIBUTE = 0;

	/**
	 * The number of structural features of the the '<em>Extended Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.MaskImpl <em>Mask</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.MaskImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getMask()
	 * @generated
	 */
	int MASK = 15;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASK__LEVEL = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASK__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASK__LENGTH = 2;

	/**
	 * The feature id for the '<em><b>Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASK__SEPARATOR = 3;

	/**
	 * The number of structural features of the the '<em>Mask</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASK_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.MasksImpl <em>Masks</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.MasksImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getMasks()
	 * @generated
	 */
	int MASKS = 16;

	/**
	 * The feature id for the '<em><b>Mask</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASKS__MASK = 0;

	/**
	 * The number of structural features of the the '<em>Masks</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASKS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.OutlineCodeImpl <em>Outline Code</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.OutlineCodeImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOutlineCode()
	 * @generated
	 */
	int OUTLINE_CODE = 17;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__FIELD_ID = 0;

	/**
	 * The feature id for the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__FIELD_NAME = 1;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__ALIAS = 2;

	/**
	 * The feature id for the '<em><b>Phonetic Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__PHONETIC_ALIAS = 3;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__VALUES = 4;

	/**
	 * The feature id for the '<em><b>Enterprise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__ENTERPRISE = 5;

	/**
	 * The feature id for the '<em><b>Enterprise Outline Code Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__ENTERPRISE_OUTLINE_CODE_ALIAS = 6;

	/**
	 * The feature id for the '<em><b>Resource Substitution Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__RESOURCE_SUBSTITUTION_ENABLED = 7;

	/**
	 * The feature id for the '<em><b>Leaf Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__LEAF_ONLY = 8;

	/**
	 * The feature id for the '<em><b>All Levels Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__ALL_LEVELS_REQUIRED = 9;

	/**
	 * The feature id for the '<em><b>Only Table Values Allowed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__ONLY_TABLE_VALUES_ALLOWED = 10;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE__MASKS = 11;

	/**
	 * The number of structural features of the the '<em>Outline Code</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.OutlineCode2Impl <em>Outline Code2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.OutlineCode2Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOutlineCode2()
	 * @generated
	 */
	int OUTLINE_CODE2 = 18;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE2__UID = 0;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE2__FIELD_ID = 1;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE2__VALUE_ID = 2;

	/**
	 * The number of structural features of the the '<em>Outline Code2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE2_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.OutlineCode3Impl <em>Outline Code3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.OutlineCode3Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOutlineCode3()
	 * @generated
	 */
	int OUTLINE_CODE3 = 19;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE3__UID = 0;

	/**
	 * The feature id for the '<em><b>Field ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE3__FIELD_ID = 1;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE3__VALUE_ID = 2;

	/**
	 * The number of structural features of the the '<em>Outline Code3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODE3_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.OutlineCodesImpl <em>Outline Codes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.OutlineCodesImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOutlineCodes()
	 * @generated
	 */
	int OUTLINE_CODES = 20;

	/**
	 * The feature id for the '<em><b>Outline Code</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODES__OUTLINE_CODE = 0;

	/**
	 * The number of structural features of the the '<em>Outline Codes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLINE_CODES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.PredecessorLinkImpl <em>Predecessor Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.PredecessorLinkImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getPredecessorLink()
	 * @generated
	 */
	int PREDECESSOR_LINK = 21;

	/**
	 * The feature id for the '<em><b>Predecessor UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK__PREDECESSOR_UID = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Cross Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK__CROSS_PROJECT = 2;

	/**
	 * The feature id for the '<em><b>Cross Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK__CROSS_PROJECT_NAME = 3;

	/**
	 * The feature id for the '<em><b>Link Lag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK__LINK_LAG = 4;

	/**
	 * The feature id for the '<em><b>Lag Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK__LAG_FORMAT = 5;

	/**
	 * The number of structural features of the the '<em>Predecessor Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_LINK_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ProjectImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 22;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__UID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__TITLE = 2;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SUBJECT = 3;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CATEGORY = 4;

	/**
	 * The feature id for the '<em><b>Company</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__COMPANY = 5;

	/**
	 * The feature id for the '<em><b>Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MANAGER = 6;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__AUTHOR = 7;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CREATION_DATE = 8;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__REVISION = 9;

	/**
	 * The feature id for the '<em><b>Last Saved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__LAST_SAVED = 10;

	/**
	 * The feature id for the '<em><b>Schedule From Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SCHEDULE_FROM_START = 11;

	/**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__START_DATE = 12;

	/**
	 * The feature id for the '<em><b>Finish Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__FINISH_DATE = 13;

	/**
	 * The feature id for the '<em><b>FY Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__FYSTART_DATE = 14;

	/**
	 * The feature id for the '<em><b>Critical Slack Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CRITICAL_SLACK_LIMIT = 15;

	/**
	 * The feature id for the '<em><b>Currency Digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CURRENCY_DIGITS = 16;

	/**
	 * The feature id for the '<em><b>Currency Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CURRENCY_SYMBOL = 17;

	/**
	 * The feature id for the '<em><b>Currency Symbol Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CURRENCY_SYMBOL_POSITION = 18;

	/**
	 * The feature id for the '<em><b>Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CALENDAR_UID = 19;

	/**
	 * The feature id for the '<em><b>Default Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_START_TIME = 20;

	/**
	 * The feature id for the '<em><b>Default Finish Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_FINISH_TIME = 21;

	/**
	 * The feature id for the '<em><b>Minutes Per Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MINUTES_PER_DAY = 22;

	/**
	 * The feature id for the '<em><b>Minutes Per Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MINUTES_PER_WEEK = 23;

	/**
	 * The feature id for the '<em><b>Days Per Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DAYS_PER_MONTH = 24;

	/**
	 * The feature id for the '<em><b>Default Task Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_TASK_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Default Fixed Cost Accrual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_FIXED_COST_ACCRUAL = 26;

	/**
	 * The feature id for the '<em><b>Default Standard Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_STANDARD_RATE = 27;

	/**
	 * The feature id for the '<em><b>Default Overtime Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_OVERTIME_RATE = 28;

	/**
	 * The feature id for the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DURATION_FORMAT = 29;

	/**
	 * The feature id for the '<em><b>Work Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__WORK_FORMAT = 30;

	/**
	 * The feature id for the '<em><b>Editable Actual Costs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__EDITABLE_ACTUAL_COSTS = 31;

	/**
	 * The feature id for the '<em><b>Honor Constraints</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__HONOR_CONSTRAINTS = 32;

	/**
	 * The feature id for the '<em><b>Earned Value Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__EARNED_VALUE_METHOD = 33;

	/**
	 * The feature id for the '<em><b>Inserted Projects Like Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__INSERTED_PROJECTS_LIKE_SUMMARY = 34;

	/**
	 * The feature id for the '<em><b>Multiple Critical Paths</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MULTIPLE_CRITICAL_PATHS = 35;

	/**
	 * The feature id for the '<em><b>New Tasks Effort Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__NEW_TASKS_EFFORT_DRIVEN = 36;

	/**
	 * The feature id for the '<em><b>New Tasks Estimated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__NEW_TASKS_ESTIMATED = 37;

	/**
	 * The feature id for the '<em><b>Splits In Progress Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SPLITS_IN_PROGRESS_TASKS = 38;

	/**
	 * The feature id for the '<em><b>Spread Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SPREAD_ACTUAL_COST = 39;

	/**
	 * The feature id for the '<em><b>Spread Percent Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SPREAD_PERCENT_COMPLETE = 40;

	/**
	 * The feature id for the '<em><b>Task Updates Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__TASK_UPDATES_RESOURCE = 41;

	/**
	 * The feature id for the '<em><b>Fiscal Year Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__FISCAL_YEAR_START = 42;

	/**
	 * The feature id for the '<em><b>Week Start Day</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__WEEK_START_DAY = 43;

	/**
	 * The feature id for the '<em><b>Move Completed Ends Back</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MOVE_COMPLETED_ENDS_BACK = 44;

	/**
	 * The feature id for the '<em><b>Move Remaining Starts Back</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MOVE_REMAINING_STARTS_BACK = 45;

	/**
	 * The feature id for the '<em><b>Move Remaining Starts Forward</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MOVE_REMAINING_STARTS_FORWARD = 46;

	/**
	 * The feature id for the '<em><b>Move Completed Ends Forward</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MOVE_COMPLETED_ENDS_FORWARD = 47;

	/**
	 * The feature id for the '<em><b>Baseline For Earned Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__BASELINE_FOR_EARNED_VALUE = 48;

	/**
	 * The feature id for the '<em><b>Auto Add New Resources And Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__AUTO_ADD_NEW_RESOURCES_AND_TASKS = 49;

	/**
	 * The feature id for the '<em><b>Status Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__STATUS_DATE = 50;

	/**
	 * The feature id for the '<em><b>Current Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CURRENT_DATE = 51;

	/**
	 * The feature id for the '<em><b>Microsoft Project Server URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__MICROSOFT_PROJECT_SERVER_URL = 52;

	/**
	 * The feature id for the '<em><b>Autolink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__AUTOLINK = 53;

	/**
	 * The feature id for the '<em><b>New Task Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__NEW_TASK_START_DATE = 54;

	/**
	 * The feature id for the '<em><b>Default Task EV Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DEFAULT_TASK_EV_METHOD = 55;

	/**
	 * The feature id for the '<em><b>Project Externally Edited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__PROJECT_EXTERNALLY_EDITED = 56;

	/**
	 * The feature id for the '<em><b>Extended Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__EXTENDED_CREATION_DATE = 57;

	/**
	 * The feature id for the '<em><b>Actuals In Sync</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__ACTUALS_IN_SYNC = 58;

	/**
	 * The feature id for the '<em><b>Remove File Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__REMOVE_FILE_PROPERTIES = 59;

	/**
	 * The feature id for the '<em><b>Admin Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__ADMIN_PROJECT = 60;

	/**
	 * The feature id for the '<em><b>Outline Codes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__OUTLINE_CODES = 61;

	/**
	 * The feature id for the '<em><b>WBS Masks</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__WBS_MASKS = 62;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__EXTENDED_ATTRIBUTES = 63;

	/**
	 * The feature id for the '<em><b>Calendars</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__CALENDARS = 64;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__TASKS = 65;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__RESOURCES = 66;

	/**
	 * The feature id for the '<em><b>Assignments</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__ASSIGNMENTS = 67;

	/**
	 * The number of structural features of the the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = 68;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.RateImpl <em>Rate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.RateImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getRate()
	 * @generated
	 */
	int RATE = 23;

	/**
	 * The feature id for the '<em><b>Rates From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__RATES_FROM = 0;

	/**
	 * The feature id for the '<em><b>Rates To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__RATES_TO = 1;

	/**
	 * The feature id for the '<em><b>Rate Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__RATE_TABLE = 2;

	/**
	 * The feature id for the '<em><b>Standard Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__STANDARD_RATE = 3;

	/**
	 * The feature id for the '<em><b>Standard Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__STANDARD_RATE_FORMAT = 4;

	/**
	 * The feature id for the '<em><b>Overtime Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__OVERTIME_RATE = 5;

	/**
	 * The feature id for the '<em><b>Overtime Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__OVERTIME_RATE_FORMAT = 6;

	/**
	 * The feature id for the '<em><b>Cost Per Use</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE__COST_PER_USE = 7;

	/**
	 * The number of structural features of the the '<em>Rate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.RatesImpl <em>Rates</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.RatesImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getRates()
	 * @generated
	 */
	int RATES = 24;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATES__RATE = 0;

	/**
	 * The number of structural features of the the '<em>Rates</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RATES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ResourceImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 25;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__UID = 0;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Is Null</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__IS_NULL = 4;

	/**
	 * The feature id for the '<em><b>Initials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__INITIALS = 5;

	/**
	 * The feature id for the '<em><b>Phonetics</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PHONETICS = 6;

	/**
	 * The feature id for the '<em><b>NT Account</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NTACCOUNT = 7;

	/**
	 * The feature id for the '<em><b>Material Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MATERIAL_LABEL = 8;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CODE = 9;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__GROUP = 10;

	/**
	 * The feature id for the '<em><b>Work Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__WORK_GROUP = 11;

	/**
	 * The feature id for the '<em><b>Email Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__EMAIL_ADDRESS = 12;

	/**
	 * The feature id for the '<em><b>Hyperlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__HYPERLINK = 13;

	/**
	 * The feature id for the '<em><b>Hyperlink Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__HYPERLINK_ADDRESS = 14;

	/**
	 * The feature id for the '<em><b>Hyperlink Sub Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__HYPERLINK_SUB_ADDRESS = 15;

	/**
	 * The feature id for the '<em><b>Max Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MAX_UNITS = 16;

	/**
	 * The feature id for the '<em><b>Peak Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PEAK_UNITS = 17;

	/**
	 * The feature id for the '<em><b>Over Allocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__OVER_ALLOCATED = 18;

	/**
	 * The feature id for the '<em><b>Available From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__AVAILABLE_FROM = 19;

	/**
	 * The feature id for the '<em><b>Available To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__AVAILABLE_TO = 20;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__START = 21;

	/**
	 * The feature id for the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__FINISH = 22;

	/**
	 * The feature id for the '<em><b>Can Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CAN_LEVEL = 23;

	/**
	 * The feature id for the '<em><b>Accrue At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACCRUE_AT = 24;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__WORK = 25;

	/**
	 * The feature id for the '<em><b>Regular Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REGULAR_WORK = 26;

	/**
	 * The feature id for the '<em><b>Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__OVERTIME_WORK = 27;

	/**
	 * The feature id for the '<em><b>Actual Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTUAL_WORK = 28;

	/**
	 * The feature id for the '<em><b>Remaining Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REMAINING_WORK = 29;

	/**
	 * The feature id for the '<em><b>Actual Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTUAL_OVERTIME_WORK = 30;

	/**
	 * The feature id for the '<em><b>Remaining Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REMAINING_OVERTIME_WORK = 31;

	/**
	 * The feature id for the '<em><b>Percent Work Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PERCENT_WORK_COMPLETE = 32;

	/**
	 * The feature id for the '<em><b>Standard Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__STANDARD_RATE = 33;

	/**
	 * The feature id for the '<em><b>Standard Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__STANDARD_RATE_FORMAT = 34;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__COST = 35;

	/**
	 * The feature id for the '<em><b>Overtime Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__OVERTIME_RATE = 36;

	/**
	 * The feature id for the '<em><b>Overtime Rate Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__OVERTIME_RATE_FORMAT = 37;

	/**
	 * The feature id for the '<em><b>Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__OVERTIME_COST = 38;

	/**
	 * The feature id for the '<em><b>Cost Per Use</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__COST_PER_USE = 39;

	/**
	 * The feature id for the '<em><b>Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTUAL_COST = 40;

	/**
	 * The feature id for the '<em><b>Actual Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTUAL_OVERTIME_COST = 41;

	/**
	 * The feature id for the '<em><b>Remaining Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REMAINING_COST = 42;

	/**
	 * The feature id for the '<em><b>Remaining Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REMAINING_OVERTIME_COST = 43;

	/**
	 * The feature id for the '<em><b>Work Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__WORK_VARIANCE = 44;

	/**
	 * The feature id for the '<em><b>Cost Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__COST_VARIANCE = 45;

	/**
	 * The feature id for the '<em><b>SV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SV = 46;

	/**
	 * The feature id for the '<em><b>CV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CV = 47;

	/**
	 * The feature id for the '<em><b>ACWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACWP = 48;

	/**
	 * The feature id for the '<em><b>Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CALENDAR_UID = 49;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NOTES = 50;

	/**
	 * The feature id for the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__BCWS = 51;

	/**
	 * The feature id for the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__BCWP = 52;

	/**
	 * The feature id for the '<em><b>Is Generic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__IS_GENERIC = 53;

	/**
	 * The feature id for the '<em><b>Is Inactive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__IS_INACTIVE = 54;

	/**
	 * The feature id for the '<em><b>Is Enterprise</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__IS_ENTERPRISE = 55;

	/**
	 * The feature id for the '<em><b>Booking Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__BOOKING_TYPE = 56;

	/**
	 * The feature id for the '<em><b>Actual Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTUAL_WORK_PROTECTED = 57;

	/**
	 * The feature id for the '<em><b>Actual Overtime Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTUAL_OVERTIME_WORK_PROTECTED = 58;

	/**
	 * The feature id for the '<em><b>Active Directory GUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTIVE_DIRECTORY_GUID = 59;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CREATION_DATE = 60;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__EXTENDED_ATTRIBUTE = 61;

	/**
	 * The feature id for the '<em><b>Baseline</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__BASELINE = 62;

	/**
	 * The feature id for the '<em><b>Outline Code</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__OUTLINE_CODE = 63;

	/**
	 * The feature id for the '<em><b>Availability Periods</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__AVAILABILITY_PERIODS = 64;

	/**
	 * The feature id for the '<em><b>Rates</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__RATES = 65;

	/**
	 * The feature id for the '<em><b>Timephased Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__TIMEPHASED_DATA = 66;

	/**
	 * The number of structural features of the the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 67;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ResourcesImpl <em>Resources</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ResourcesImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getResources()
	 * @generated
	 */
	int RESOURCES = 26;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__RESOURCE = 0;

	/**
	 * The number of structural features of the the '<em>Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.TaskImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 27;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__UID = 0;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NAME = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Is Null</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__IS_NULL = 4;

	/**
	 * The feature id for the '<em><b>Create Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CREATE_DATE = 5;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONTACT = 6;

	/**
	 * The feature id for the '<em><b>WBS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__WBS = 7;

	/**
	 * The feature id for the '<em><b>WBS Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__WBS_LEVEL = 8;

	/**
	 * The feature id for the '<em><b>Outline Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OUTLINE_NUMBER = 9;

	/**
	 * The feature id for the '<em><b>Outline Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OUTLINE_LEVEL = 10;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRIORITY = 11;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__START = 12;

	/**
	 * The feature id for the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FINISH = 13;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DURATION = 14;

	/**
	 * The feature id for the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DURATION_FORMAT = 15;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__WORK = 16;

	/**
	 * The feature id for the '<em><b>Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__STOP = 17;

	/**
	 * The feature id for the '<em><b>Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__RESUME = 18;

	/**
	 * The feature id for the '<em><b>Resume Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__RESUME_VALID = 19;

	/**
	 * The feature id for the '<em><b>Effort Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EFFORT_DRIVEN = 20;

	/**
	 * The feature id for the '<em><b>Recurring</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__RECURRING = 21;

	/**
	 * The feature id for the '<em><b>Over Allocated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OVER_ALLOCATED = 22;

	/**
	 * The feature id for the '<em><b>Estimated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ESTIMATED = 23;

	/**
	 * The feature id for the '<em><b>Milestone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__MILESTONE = 24;

	/**
	 * The feature id for the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUMMARY = 25;

	/**
	 * The feature id for the '<em><b>Critical</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CRITICAL = 26;

	/**
	 * The feature id for the '<em><b>Is Subproject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__IS_SUBPROJECT = 27;

	/**
	 * The feature id for the '<em><b>Is Subproject Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__IS_SUBPROJECT_READ_ONLY = 28;

	/**
	 * The feature id for the '<em><b>Subproject Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SUBPROJECT_NAME = 29;

	/**
	 * The feature id for the '<em><b>External Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXTERNAL_TASK = 30;

	/**
	 * The feature id for the '<em><b>External Task Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXTERNAL_TASK_PROJECT = 31;

	/**
	 * The feature id for the '<em><b>Early Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EARLY_START = 32;

	/**
	 * The feature id for the '<em><b>Early Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EARLY_FINISH = 33;

	/**
	 * The feature id for the '<em><b>Late Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LATE_START = 34;

	/**
	 * The feature id for the '<em><b>Late Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LATE_FINISH = 35;

	/**
	 * The feature id for the '<em><b>Start Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__START_VARIANCE = 36;

	/**
	 * The feature id for the '<em><b>Finish Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FINISH_VARIANCE = 37;

	/**
	 * The feature id for the '<em><b>Work Variance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__WORK_VARIANCE = 38;

	/**
	 * The feature id for the '<em><b>Free Slack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FREE_SLACK = 39;

	/**
	 * The feature id for the '<em><b>Total Slack</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TOTAL_SLACK = 40;

	/**
	 * The feature id for the '<em><b>Fixed Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FIXED_COST = 41;

	/**
	 * The feature id for the '<em><b>Fixed Cost Accrual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__FIXED_COST_ACCRUAL = 42;

	/**
	 * The feature id for the '<em><b>Percent Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PERCENT_COMPLETE = 43;

	/**
	 * The feature id for the '<em><b>Percent Work Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PERCENT_WORK_COMPLETE = 44;

	/**
	 * The feature id for the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__COST = 45;

	/**
	 * The feature id for the '<em><b>Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OVERTIME_COST = 46;

	/**
	 * The feature id for the '<em><b>Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OVERTIME_WORK = 47;

	/**
	 * The feature id for the '<em><b>Actual Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_START = 48;

	/**
	 * The feature id for the '<em><b>Actual Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_FINISH = 49;

	/**
	 * The feature id for the '<em><b>Actual Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_DURATION = 50;

	/**
	 * The feature id for the '<em><b>Actual Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_COST = 51;

	/**
	 * The feature id for the '<em><b>Actual Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_OVERTIME_COST = 52;

	/**
	 * The feature id for the '<em><b>Actual Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_WORK = 53;

	/**
	 * The feature id for the '<em><b>Actual Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_OVERTIME_WORK = 54;

	/**
	 * The feature id for the '<em><b>Regular Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REGULAR_WORK = 55;

	/**
	 * The feature id for the '<em><b>Remaining Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REMAINING_DURATION = 56;

	/**
	 * The feature id for the '<em><b>Remaining Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REMAINING_COST = 57;

	/**
	 * The feature id for the '<em><b>Remaining Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REMAINING_WORK = 58;

	/**
	 * The feature id for the '<em><b>Remaining Overtime Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REMAINING_OVERTIME_COST = 59;

	/**
	 * The feature id for the '<em><b>Remaining Overtime Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REMAINING_OVERTIME_WORK = 60;

	/**
	 * The feature id for the '<em><b>ACWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACWP = 61;

	/**
	 * The feature id for the '<em><b>CV</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CV = 62;

	/**
	 * The feature id for the '<em><b>Constraint Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONSTRAINT_TYPE = 63;

	/**
	 * The feature id for the '<em><b>Calendar UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CALENDAR_UID = 64;

	/**
	 * The feature id for the '<em><b>Constraint Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONSTRAINT_DATE = 65;

	/**
	 * The feature id for the '<em><b>Deadline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DEADLINE = 66;

	/**
	 * The feature id for the '<em><b>Level Assignments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LEVEL_ASSIGNMENTS = 67;

	/**
	 * The feature id for the '<em><b>Leveling Can Split</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LEVELING_CAN_SPLIT = 68;

	/**
	 * The feature id for the '<em><b>Leveling Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LEVELING_DELAY = 69;

	/**
	 * The feature id for the '<em><b>Leveling Delay Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LEVELING_DELAY_FORMAT = 70;

	/**
	 * The feature id for the '<em><b>Pre Leveled Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRE_LEVELED_START = 71;

	/**
	 * The feature id for the '<em><b>Pre Leveled Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PRE_LEVELED_FINISH = 72;

	/**
	 * The feature id for the '<em><b>Hyperlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__HYPERLINK = 73;

	/**
	 * The feature id for the '<em><b>Hyperlink Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__HYPERLINK_ADDRESS = 74;

	/**
	 * The feature id for the '<em><b>Hyperlink Sub Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__HYPERLINK_SUB_ADDRESS = 75;

	/**
	 * The feature id for the '<em><b>Ignore Resource Calendar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__IGNORE_RESOURCE_CALENDAR = 76;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NOTES = 77;

	/**
	 * The feature id for the '<em><b>Hide Bar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__HIDE_BAR = 78;

	/**
	 * The feature id for the '<em><b>Rollup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ROLLUP = 79;

	/**
	 * The feature id for the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__BCWS = 80;

	/**
	 * The feature id for the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__BCWP = 81;

	/**
	 * The feature id for the '<em><b>Physical Percent Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PHYSICAL_PERCENT_COMPLETE = 82;

	/**
	 * The feature id for the '<em><b>Earned Value Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EARNED_VALUE_METHOD = 83;

	/**
	 * The feature id for the '<em><b>Predecessor Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PREDECESSOR_LINK = 84;

	/**
	 * The feature id for the '<em><b>Actual Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_WORK_PROTECTED = 85;

	/**
	 * The feature id for the '<em><b>Actual Overtime Work Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__ACTUAL_OVERTIME_WORK_PROTECTED = 86;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXTENDED_ATTRIBUTE = 87;

	/**
	 * The feature id for the '<em><b>Baseline</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__BASELINE = 88;

	/**
	 * The feature id for the '<em><b>Outline Code</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__OUTLINE_CODE = 89;

	/**
	 * The feature id for the '<em><b>Timephased Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TIMEPHASED_DATA = 90;

	/**
	 * The number of structural features of the the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 91;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.TasksImpl <em>Tasks</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.TasksImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTasks()
	 * @generated
	 */
	int TASKS = 28;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASKS__TASK = 0;

	/**
	 * The number of structural features of the the '<em>Tasks</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASKS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.TimePeriodImpl <em>Time Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.TimePeriodImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTimePeriod()
	 * @generated
	 */
	int TIME_PERIOD = 29;

	/**
	 * The feature id for the '<em><b>From Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD__FROM_DATE = 0;

	/**
	 * The feature id for the '<em><b>To Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD__TO_DATE = 1;

	/**
	 * The number of structural features of the the '<em>Time Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_PERIOD_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.TimephasedDataTypeImpl <em>Timephased Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.TimephasedDataTypeImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTimephasedDataType()
	 * @generated
	 */
	int TIMEPHASED_DATA_TYPE = 30;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE__UID = 1;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE__START = 2;

	/**
	 * The feature id for the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE__FINISH = 3;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE__UNIT = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE__VALUE = 5;

	/**
	 * The number of structural features of the the '<em>Timephased Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPHASED_DATA_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ValueImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 31;

	/**
	 * The feature id for the '<em><b>Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__VALUE_ID = 0;

	/**
	 * The feature id for the '<em><b>Parent Value ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__PARENT_VALUE_ID = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__DESCRIPTION = 3;

	/**
	 * The number of structural features of the the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.Value2Impl <em>Value2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.Value2Impl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getValue2()
	 * @generated
	 */
	int VALUE2 = 32;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE2__ID = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE2__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE2__DESCRIPTION = 2;

	/**
	 * The number of structural features of the the '<em>Value2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE2_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ValueListImpl <em>Value List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ValueListImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getValueList()
	 * @generated
	 */
	int VALUE_LIST = 33;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_LIST__VALUE = 0;

	/**
	 * The number of structural features of the the '<em>Value List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_LIST_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.ValuesImpl <em>Values</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.ValuesImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getValues()
	 * @generated
	 */
	int VALUES = 34;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUES__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUES__VALUE = 1;

	/**
	 * The number of structural features of the the '<em>Values</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUES_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.WBSMaskImpl <em>WBS Mask</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.WBSMaskImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWBSMask()
	 * @generated
	 */
	int WBS_MASK = 35;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASK__LEVEL = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASK__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASK__LENGTH = 2;

	/**
	 * The feature id for the '<em><b>Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASK__SEPARATOR = 3;

	/**
	 * The number of structural features of the the '<em>WBS Mask</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASK_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.WBSMasksImpl <em>WBS Masks</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.WBSMasksImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWBSMasks()
	 * @generated
	 */
	int WBS_MASKS = 36;

	/**
	 * The feature id for the '<em><b>Verify Unique Codes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASKS__VERIFY_UNIQUE_CODES = 0;

	/**
	 * The feature id for the '<em><b>Generate Codes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASKS__GENERATE_CODES = 1;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASKS__PREFIX = 2;

	/**
	 * The feature id for the '<em><b>WBS Mask</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASKS__WBS_MASK = 3;

	/**
	 * The number of structural features of the the '<em>WBS Masks</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WBS_MASKS_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.WeekDayImpl <em>Week Day</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.WeekDayImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWeekDay()
	 * @generated
	 */
	int WEEK_DAY = 37;

	/**
	 * The feature id for the '<em><b>Day Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAY__DAY_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Day Working</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAY__DAY_WORKING = 1;

	/**
	 * The feature id for the '<em><b>Time Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAY__TIME_PERIOD = 2;

	/**
	 * The feature id for the '<em><b>Working Times</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAY__WORKING_TIMES = 3;

	/**
	 * The number of structural features of the the '<em>Week Day</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAY_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.WeekDaysImpl <em>Week Days</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.WeekDaysImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWeekDays()
	 * @generated
	 */
	int WEEK_DAYS = 38;

	/**
	 * The feature id for the '<em><b>Week Day</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAYS__WEEK_DAY = 0;

	/**
	 * The number of structural features of the the '<em>Week Days</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEEK_DAYS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.WorkingTimeImpl <em>Working Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.WorkingTimeImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWorkingTime()
	 * @generated
	 */
	int WORKING_TIME = 39;

	/**
	 * The feature id for the '<em><b>From Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKING_TIME__FROM_TIME = 0;

	/**
	 * The feature id for the '<em><b>To Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKING_TIME__TO_TIME = 1;

	/**
	 * The number of structural features of the the '<em>Working Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKING_TIME_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epf.msproject.impl.WorkingTimesImpl <em>Working Times</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epf.msproject.impl.WorkingTimesImpl
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWorkingTimes()
	 * @generated
	 */
	int WORKING_TIMES = 40;

	/**
	 * The feature id for the '<em><b>Working Time</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKING_TIMES__WORKING_TIME = 0;

	/**
	 * The number of structural features of the the '<em>Working Times</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKING_TIMES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '<em>Accrue At</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAccrueAt()
	 * @generated
	 */
	int ACCRUE_AT = 41;

	/**
	 * The meta object id for the '<em>Active Directory GUID Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getActiveDirectoryGUIDType()
	 * @generated
	 */
	int ACTIVE_DIRECTORY_GUID_TYPE = 42;

	/**
	 * The meta object id for the '<em>Alias Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAliasType()
	 * @generated
	 */
	int ALIAS_TYPE = 43;

	/**
	 * The meta object id for the '<em>Author Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getAuthorType()
	 * @generated
	 */
	int AUTHOR_TYPE = 44;

	/**
	 * The meta object id for the '<em>Baseline For Earned Value Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getBaselineForEarnedValueType()
	 * @generated
	 */
	int BASELINE_FOR_EARNED_VALUE_TYPE = 45;

	/**
	 * The meta object id for the '<em>Booking Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getBookingTypeType()
	 * @generated
	 */
	int BOOKING_TYPE_TYPE = 46;

	/**
	 * The meta object id for the '<em>Booking Type Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getBookingTypeType1()
	 * @generated
	 */
	int BOOKING_TYPE_TYPE1 = 47;

	/**
	 * The meta object id for the '<em>Calculation Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCalculationTypeType()
	 * @generated
	 */
	int CALCULATION_TYPE_TYPE = 48;

	/**
	 * The meta object id for the '<em>Category Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCategoryType()
	 * @generated
	 */
	int CATEGORY_TYPE = 49;

	/**
	 * The meta object id for the '<em>Code Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCodeType()
	 * @generated
	 */
	int CODE_TYPE = 50;

	/**
	 * The meta object id for the '<em>Company Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCompanyType()
	 * @generated
	 */
	int COMPANY_TYPE = 51;

	/**
	 * The meta object id for the '<em>Constraint Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getConstraintTypeType()
	 * @generated
	 */
	int CONSTRAINT_TYPE_TYPE = 52;

	/**
	 * The meta object id for the '<em>Contact Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getContactType()
	 * @generated
	 */
	int CONTACT_TYPE = 53;

	/**
	 * The meta object id for the '<em>Cost Rate Table Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCostRateTableType()
	 * @generated
	 */
	int COST_RATE_TABLE_TYPE = 54;

	/**
	 * The meta object id for the '<em>Currency Symbol Position Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCurrencySymbolPositionType()
	 * @generated
	 */
	int CURRENCY_SYMBOL_POSITION_TYPE = 55;

	/**
	 * The meta object id for the '<em>Currency Symbol Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getCurrencySymbolType()
	 * @generated
	 */
	int CURRENCY_SYMBOL_TYPE = 56;

	/**
	 * The meta object id for the '<em>Day Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDayTypeType()
	 * @generated
	 */
	int DAY_TYPE_TYPE = 57;

	/**
	 * The meta object id for the '<em>Default Fixed Cost Accrual Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDefaultFixedCostAccrualType()
	 * @generated
	 */
	int DEFAULT_FIXED_COST_ACCRUAL_TYPE = 58;

	/**
	 * The meta object id for the '<em>Default Task EV Method Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDefaultTaskEVMethodType()
	 * @generated
	 */
	int DEFAULT_TASK_EV_METHOD_TYPE = 59;

	/**
	 * The meta object id for the '<em>Default Task Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDefaultTaskTypeType()
	 * @generated
	 */
	int DEFAULT_TASK_TYPE_TYPE = 60;

	/**
	 * The meta object id for the '<em>Duration Format Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDurationFormatType()
	 * @generated
	 */
	int DURATION_FORMAT_TYPE = 61;

	/**
	 * The meta object id for the '<em>Duration Format Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDurationFormatType1()
	 * @generated
	 */
	int DURATION_FORMAT_TYPE1 = 62;

	/**
	 * The meta object id for the '<em>Duration Format Type2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDurationFormatType2()
	 * @generated
	 */
	int DURATION_FORMAT_TYPE2 = 63;

	/**
	 * The meta object id for the '<em>Duration Format Type3</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDurationFormatType3()
	 * @generated
	 */
	int DURATION_FORMAT_TYPE3 = 64;

	/**
	 * The meta object id for the '<em>Duration Format Type4</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDurationFormatType4()
	 * @generated
	 */
	int DURATION_FORMAT_TYPE4 = 65;

	/**
	 * The meta object id for the '<em>Duration Format Type5</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getDurationFormatType5()
	 * @generated
	 */
	int DURATION_FORMAT_TYPE5 = 66;

	/**
	 * The meta object id for the '<em>Earned Value Method Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getEarnedValueMethodType()
	 * @generated
	 */
	int EARNED_VALUE_METHOD_TYPE = 67;

	/**
	 * The meta object id for the '<em>Earned Value Method Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getEarnedValueMethodType1()
	 * @generated
	 */
	int EARNED_VALUE_METHOD_TYPE1 = 68;

	/**
	 * The meta object id for the '<em>Email Address Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getEmailAddressType()
	 * @generated
	 */
	int EMAIL_ADDRESS_TYPE = 69;

	/**
	 * The meta object id for the '<em>External Task Project Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getExternalTaskProjectType()
	 * @generated
	 */
	int EXTERNAL_TASK_PROJECT_TYPE = 70;

	/**
	 * The meta object id for the '<em>Fixed Cost Accrual Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getFixedCostAccrualType()
	 * @generated
	 */
	int FIXED_COST_ACCRUAL_TYPE = 71;

	/**
	 * The meta object id for the '<em>FY Start Date Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getFYStartDateType()
	 * @generated
	 */
	int FY_START_DATE_TYPE = 72;

	/**
	 * The meta object id for the '<em>Group Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getGroupType()
	 * @generated
	 */
	int GROUP_TYPE = 73;

	/**
	 * The meta object id for the '<em>Hyperlink Address Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkAddressType()
	 * @generated
	 */
	int HYPERLINK_ADDRESS_TYPE = 74;

	/**
	 * The meta object id for the '<em>Hyperlink Address Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkAddressType1()
	 * @generated
	 */
	int HYPERLINK_ADDRESS_TYPE1 = 75;

	/**
	 * The meta object id for the '<em>Hyperlink Address Type2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkAddressType2()
	 * @generated
	 */
	int HYPERLINK_ADDRESS_TYPE2 = 76;

	/**
	 * The meta object id for the '<em>Hyperlink Sub Address Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkSubAddressType()
	 * @generated
	 */
	int HYPERLINK_SUB_ADDRESS_TYPE = 77;

	/**
	 * The meta object id for the '<em>Hyperlink Sub Address Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkSubAddressType1()
	 * @generated
	 */
	int HYPERLINK_SUB_ADDRESS_TYPE1 = 78;

	/**
	 * The meta object id for the '<em>Hyperlink Sub Address Type2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkSubAddressType2()
	 * @generated
	 */
	int HYPERLINK_SUB_ADDRESS_TYPE2 = 79;

	/**
	 * The meta object id for the '<em>Hyperlink Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkType()
	 * @generated
	 */
	int HYPERLINK_TYPE = 80;

	/**
	 * The meta object id for the '<em>Hyperlink Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkType1()
	 * @generated
	 */
	int HYPERLINK_TYPE1 = 81;

	/**
	 * The meta object id for the '<em>Hyperlink Type2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getHyperlinkType2()
	 * @generated
	 */
	int HYPERLINK_TYPE2 = 82;

	/**
	 * The meta object id for the '<em>Initials Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getInitialsType()
	 * @generated
	 */
	int INITIALS_TYPE = 83;

	/**
	 * The meta object id for the '<em>Lag Format Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getLagFormatType()
	 * @generated
	 */
	int LAG_FORMAT_TYPE = 84;

	/**
	 * The meta object id for the '<em>Leveling Delay Format Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getLevelingDelayFormatType()
	 * @generated
	 */
	int LEVELING_DELAY_FORMAT_TYPE = 85;

	/**
	 * The meta object id for the '<em>Leveling Delay Format Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getLevelingDelayFormatType1()
	 * @generated
	 */
	int LEVELING_DELAY_FORMAT_TYPE1 = 86;

	/**
	 * The meta object id for the '<em>Manager Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getManagerType()
	 * @generated
	 */
	int MANAGER_TYPE = 87;

	/**
	 * The meta object id for the '<em>Material Label Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getMaterialLabelType()
	 * @generated
	 */
	int MATERIAL_LABEL_TYPE = 88;

	/**
	 * The meta object id for the '<em>Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getNameType()
	 * @generated
	 */
	int NAME_TYPE = 89;

	/**
	 * The meta object id for the '<em>Name Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getNameType1()
	 * @generated
	 */
	int NAME_TYPE1 = 90;

	/**
	 * The meta object id for the '<em>Name Type2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getNameType2()
	 * @generated
	 */
	int NAME_TYPE2 = 91;

	/**
	 * The meta object id for the '<em>Name Type3</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getNameType3()
	 * @generated
	 */
	int NAME_TYPE3 = 92;

	/**
	 * The meta object id for the '<em>New Task Start Date Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getNewTaskStartDateType()
	 * @generated
	 */
	int NEW_TASK_START_DATE_TYPE = 93;

	/**
	 * The meta object id for the '<em>NT Account Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getNTAccountType()
	 * @generated
	 */
	int NT_ACCOUNT_TYPE = 94;

	/**
	 * The meta object id for the '<em>Outline Number Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOutlineNumberType()
	 * @generated
	 */
	int OUTLINE_NUMBER_TYPE = 95;

	/**
	 * The meta object id for the '<em>Overtime Rate Format Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOvertimeRateFormatType()
	 * @generated
	 */
	int OVERTIME_RATE_FORMAT_TYPE = 96;

	/**
	 * The meta object id for the '<em>Overtime Rate Format Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getOvertimeRateFormatType1()
	 * @generated
	 */
	int OVERTIME_RATE_FORMAT_TYPE1 = 97;

	/**
	 * The meta object id for the '<em>Phonetic Alias Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getPhoneticAliasType()
	 * @generated
	 */
	int PHONETIC_ALIAS_TYPE = 98;

	/**
	 * The meta object id for the '<em>Phonetics Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getPhoneticsType()
	 * @generated
	 */
	int PHONETICS_TYPE = 99;

	/**
	 * The meta object id for the '<em>Prefix Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getPrefixType()
	 * @generated
	 */
	int PREFIX_TYPE = 100;

	/**
	 * The meta object id for the '<em>Rate Table Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getRateTableType()
	 * @generated
	 */
	int RATE_TABLE_TYPE = 101;

	/**
	 * The meta object id for the '<em>Rollup Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getRollupTypeType()
	 * @generated
	 */
	int ROLLUP_TYPE_TYPE = 102;

	/**
	 * The meta object id for the '<em>Standard Rate Format Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getStandardRateFormatType()
	 * @generated
	 */
	int STANDARD_RATE_FORMAT_TYPE = 103;

	/**
	 * The meta object id for the '<em>Standard Rate Format Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getStandardRateFormatType1()
	 * @generated
	 */
	int STANDARD_RATE_FORMAT_TYPE1 = 104;

	/**
	 * The meta object id for the '<em>Subject Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getSubjectType()
	 * @generated
	 */
	int SUBJECT_TYPE = 105;

	/**
	 * The meta object id for the '<em>Subproject Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getSubprojectNameType()
	 * @generated
	 */
	int SUBPROJECT_NAME_TYPE = 106;

	/**
	 * The meta object id for the '<em>Title Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTitleType()
	 * @generated
	 */
	int TITLE_TYPE = 107;

	/**
	 * The meta object id for the '<em>Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTypeType()
	 * @generated
	 */
	int TYPE_TYPE = 108;

	/**
	 * The meta object id for the '<em>Type Type1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTypeType1()
	 * @generated
	 */
	int TYPE_TYPE1 = 109;

	/**
	 * The meta object id for the '<em>Type Type2</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTypeType2()
	 * @generated
	 */
	int TYPE_TYPE2 = 110;

	/**
	 * The meta object id for the '<em>Type Type3</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTypeType3()
	 * @generated
	 */
	int TYPE_TYPE3 = 111;

	/**
	 * The meta object id for the '<em>Type Type4</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTypeType4()
	 * @generated
	 */
	int TYPE_TYPE4 = 112;

	/**
	 * The meta object id for the '<em>Type Type5</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getTypeType5()
	 * @generated
	 */
	int TYPE_TYPE5 = 113;

	/**
	 * The meta object id for the '<em>UID Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getUIDType()
	 * @generated
	 */
	int UID_TYPE = 114;

	/**
	 * The meta object id for the '<em>Unit Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getUnitType()
	 * @generated
	 */
	int UNIT_TYPE = 115;

	/**
	 * The meta object id for the '<em>Valuelist Sort Order Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getValuelistSortOrderType()
	 * @generated
	 */
	int VALUELIST_SORT_ORDER_TYPE = 116;

	/**
	 * The meta object id for the '<em>Week Start Day Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWeekStartDayType()
	 * @generated
	 */
	int WEEK_START_DAY_TYPE = 117;

	/**
	 * The meta object id for the '<em>Work Contour Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWorkContourType()
	 * @generated
	 */
	int WORK_CONTOUR_TYPE = 118;

	/**
	 * The meta object id for the '<em>Work Format Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWorkFormatType()
	 * @generated
	 */
	int WORK_FORMAT_TYPE = 119;

	/**
	 * The meta object id for the '<em>Work Group Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.epf.msproject.impl.MsprojectPackageImpl#getWorkGroupType()
	 * @generated
	 */
	int WORK_GROUP_TYPE = 120;


	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment</em>'.
	 * @see org.eclipse.epf.msproject.Assignment
	 * @generated
	 */
	EClass getAssignment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getUID()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getTaskUID <em>Task UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task UID</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getTaskUID()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_TaskUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getResourceUID <em>Resource UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource UID</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getResourceUID()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ResourceUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getPercentWorkComplete <em>Percent Work Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percent Work Complete</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getPercentWorkComplete()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_PercentWorkComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualCost <em>Actual Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Cost</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualCost()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualFinish <em>Actual Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Finish</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualFinish()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualFinish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeCost <em>Actual Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualOvertimeCost()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualOvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeWork <em>Actual Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualOvertimeWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualOvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualStart <em>Actual Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Start</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualStart()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualWork <em>Actual Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getACWP <em>ACWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ACWP</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getACWP()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ACWP();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isConfirmed <em>Confirmed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Confirmed</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isConfirmed()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Confirmed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getCost()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getCostRateTable <em>Cost Rate Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Rate Table</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getCostRateTable()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_CostRateTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getCostVariance <em>Cost Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Variance</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getCostVariance()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_CostVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getCV <em>CV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CV</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getCV()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_CV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getDelay()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Delay();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getFinish <em>Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getFinish()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Finish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getFinishVariance <em>Finish Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish Variance</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getFinishVariance()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_FinishVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getHyperlink <em>Hyperlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getHyperlink()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Hyperlink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getHyperlinkAddress <em>Hyperlink Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink Address</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getHyperlinkAddress()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_HyperlinkAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink Sub Address</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getHyperlinkSubAddress()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_HyperlinkSubAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getWorkVariance <em>Work Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Variance</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getWorkVariance()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_WorkVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isHasFixedRateUnits <em>Has Fixed Rate Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Fixed Rate Units</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isHasFixedRateUnits()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_HasFixedRateUnits();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isFixedMaterial <em>Fixed Material</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Material</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isFixedMaterial()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_FixedMaterial();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getLevelingDelay <em>Leveling Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leveling Delay</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getLevelingDelay()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_LevelingDelay();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getLevelingDelayFormat <em>Leveling Delay Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leveling Delay Format</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getLevelingDelayFormat()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_LevelingDelayFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isLinkedFields <em>Linked Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Linked Fields</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isLinkedFields()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_LinkedFields();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isMilestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Milestone</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isMilestone()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Milestone();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getNotes <em>Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notes</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getNotes()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Notes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isOverallocated <em>Overallocated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overallocated</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isOverallocated()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Overallocated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getOvertimeCost <em>Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getOvertimeCost()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_OvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getOvertimeWork <em>Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getOvertimeWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_OvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getRegularWork <em>Regular Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regular Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getRegularWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_RegularWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getRemainingCost <em>Remaining Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Cost</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getRemainingCost()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_RemainingCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getRemainingOvertimeCost()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_RemainingOvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getRemainingOvertimeWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_RemainingOvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getRemainingWork <em>Remaining Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getRemainingWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_RemainingWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isResponsePending <em>Response Pending</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Response Pending</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isResponsePending()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ResponsePending();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getStart()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getStop <em>Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getStop()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Stop();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getResume <em>Resume</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resume</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getResume()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Resume();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getStartVariance <em>Start Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Variance</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getStartVariance()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_StartVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getUnits <em>Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Units</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getUnits()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Units();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#isUpdateNeeded <em>Update Needed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Update Needed</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#isUpdateNeeded()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_UpdateNeeded();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getVAC <em>VAC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>VAC</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getVAC()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_VAC();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getWork()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Work();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getWorkContour <em>Work Contour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Contour</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getWorkContour()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_WorkContour();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getBCWS <em>BCWS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWS</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getBCWS()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_BCWS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getBCWP <em>BCWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWP</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getBCWP()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_BCWP();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getBookingType <em>Booking Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Booking Type</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getBookingType()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_BookingType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualWorkProtected <em>Actual Work Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Work Protected</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualWorkProtected()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualWorkProtected();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Work Protected</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getActualOvertimeWorkProtected()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_ActualOvertimeWorkProtected();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Assignment#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getCreationDate()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_CreationDate();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Assignment#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getExtendedAttribute()
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_ExtendedAttribute();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Assignment#getBaseline <em>Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Baseline</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getBaseline()
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_Baseline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Assignment#getTimephasedData <em>Timephased Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timephased Data</em>'.
	 * @see org.eclipse.epf.msproject.Assignment#getTimephasedData()
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_TimephasedData();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Assignments <em>Assignments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignments</em>'.
	 * @see org.eclipse.epf.msproject.Assignments
	 * @generated
	 */
	EClass getAssignments();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Assignments#getAssignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assignment</em>'.
	 * @see org.eclipse.epf.msproject.Assignments#getAssignment()
	 * @see #getAssignments()
	 * @generated
	 */
	EReference getAssignments_Assignment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.AvailabilityPeriod <em>Availability Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Availability Period</em>'.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriod
	 * @generated
	 */
	EClass getAvailabilityPeriod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.AvailabilityPeriod#getAvailableFrom <em>Available From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Available From</em>'.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriod#getAvailableFrom()
	 * @see #getAvailabilityPeriod()
	 * @generated
	 */
	EAttribute getAvailabilityPeriod_AvailableFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.AvailabilityPeriod#getAvailableTo <em>Available To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Available To</em>'.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriod#getAvailableTo()
	 * @see #getAvailabilityPeriod()
	 * @generated
	 */
	EAttribute getAvailabilityPeriod_AvailableTo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.AvailabilityPeriod#getAvailableUnits <em>Available Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Available Units</em>'.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriod#getAvailableUnits()
	 * @see #getAvailabilityPeriod()
	 * @generated
	 */
	EAttribute getAvailabilityPeriod_AvailableUnits();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.AvailabilityPeriods <em>Availability Periods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Availability Periods</em>'.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriods
	 * @generated
	 */
	EClass getAvailabilityPeriods();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.AvailabilityPeriods#getAvailabilityPeriod <em>Availability Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Availability Period</em>'.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriods#getAvailabilityPeriod()
	 * @see #getAvailabilityPeriods()
	 * @generated
	 */
	EReference getAvailabilityPeriods_AvailabilityPeriod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Baseline <em>Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Baseline</em>'.
	 * @see org.eclipse.epf.msproject.Baseline
	 * @generated
	 */
	EClass getBaseline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Baseline#getTimephasedData <em>Timephased Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timephased Data</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getTimephasedData()
	 * @see #getBaseline()
	 * @generated
	 */
	EReference getBaseline_TimephasedData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getNumber()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Number();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#isInterim <em>Interim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interim</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#isInterim()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Interim();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getStart()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getFinish <em>Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getFinish()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Finish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getDuration()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Duration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getDurationFormat <em>Duration Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Format</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getDurationFormat()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_DurationFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#isEstimatedDuration <em>Estimated Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Estimated Duration</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#isEstimatedDuration()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_EstimatedDuration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getWork()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Work();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getCost()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getBCWS <em>BCWS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWS</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getBCWS()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_BCWS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline#getBCWP <em>BCWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWP</em>'.
	 * @see org.eclipse.epf.msproject.Baseline#getBCWP()
	 * @see #getBaseline()
	 * @generated
	 */
	EAttribute getBaseline_BCWP();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Baseline2 <em>Baseline2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Baseline2</em>'.
	 * @see org.eclipse.epf.msproject.Baseline2
	 * @generated
	 */
	EClass getBaseline2();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline2#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.eclipse.epf.msproject.Baseline2#getNumber()
	 * @see #getBaseline2()
	 * @generated
	 */
	EAttribute getBaseline2_Number();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline2#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see org.eclipse.epf.msproject.Baseline2#getWork()
	 * @see #getBaseline2()
	 * @generated
	 */
	EAttribute getBaseline2_Work();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline2#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.eclipse.epf.msproject.Baseline2#getCost()
	 * @see #getBaseline2()
	 * @generated
	 */
	EAttribute getBaseline2_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline2#getBCWS <em>BCWS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWS</em>'.
	 * @see org.eclipse.epf.msproject.Baseline2#getBCWS()
	 * @see #getBaseline2()
	 * @generated
	 */
	EAttribute getBaseline2_BCWS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline2#getBCWP <em>BCWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWP</em>'.
	 * @see org.eclipse.epf.msproject.Baseline2#getBCWP()
	 * @see #getBaseline2()
	 * @generated
	 */
	EAttribute getBaseline2_BCWP();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Baseline3 <em>Baseline3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Baseline3</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3
	 * @generated
	 */
	EClass getBaseline3();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Baseline3#getTimephasedData <em>Timephased Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timephased Data</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getTimephasedData()
	 * @see #getBaseline3()
	 * @generated
	 */
	EReference getBaseline3_TimephasedData();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getNumber()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_Number();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getStart()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getFinish <em>Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getFinish()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_Finish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getWork()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_Work();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getCost()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getBCWS <em>BCWS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWS</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getBCWS()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_BCWS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Baseline3#getBCWP <em>BCWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWP</em>'.
	 * @see org.eclipse.epf.msproject.Baseline3#getBCWP()
	 * @see #getBaseline3()
	 * @generated
	 */
	EAttribute getBaseline3_BCWP();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Calendar <em>Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Calendar</em>'.
	 * @see org.eclipse.epf.msproject.Calendar
	 * @generated
	 */
	EClass getCalendar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Calendar#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.Calendar#getUID()
	 * @see #getCalendar()
	 * @generated
	 */
	EAttribute getCalendar_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Calendar#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.msproject.Calendar#getName()
	 * @see #getCalendar()
	 * @generated
	 */
	EAttribute getCalendar_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Calendar#isIsBaseCalendar <em>Is Base Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Base Calendar</em>'.
	 * @see org.eclipse.epf.msproject.Calendar#isIsBaseCalendar()
	 * @see #getCalendar()
	 * @generated
	 */
	EAttribute getCalendar_IsBaseCalendar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Calendar#getBaseCalendarUID <em>Base Calendar UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Calendar UID</em>'.
	 * @see org.eclipse.epf.msproject.Calendar#getBaseCalendarUID()
	 * @see #getCalendar()
	 * @generated
	 */
	EAttribute getCalendar_BaseCalendarUID();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Calendar#getWeekDays <em>Week Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Week Days</em>'.
	 * @see org.eclipse.epf.msproject.Calendar#getWeekDays()
	 * @see #getCalendar()
	 * @generated
	 */
	EReference getCalendar_WeekDays();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Calendars <em>Calendars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Calendars</em>'.
	 * @see org.eclipse.epf.msproject.Calendars
	 * @generated
	 */
	EClass getCalendars();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Calendars#getCalendar <em>Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Calendar</em>'.
	 * @see org.eclipse.epf.msproject.Calendars#getCalendar()
	 * @see #getCalendars()
	 * @generated
	 */
	EReference getCalendars_Calendar();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.epf.msproject.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.msproject.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.epf.msproject.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.epf.msproject.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.epf.msproject.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.epf.msproject.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.epf.msproject.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.DocumentRoot#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Project</em>'.
	 * @see org.eclipse.epf.msproject.DocumentRoot#getProject()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Project();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.ExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attribute</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute
	 * @generated
	 */
	EClass getExtendedAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getFieldID()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getFieldName()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_FieldName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getAlias()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_Alias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getPhoneticAlias <em>Phonetic Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phonetic Alias</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getPhoneticAlias()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_PhoneticAlias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getRollupType <em>Rollup Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rollup Type</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getRollupType()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_RollupType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getCalculationType <em>Calculation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Calculation Type</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getCalculationType()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_CalculationType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getFormula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Formula</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getFormula()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_Formula();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#isRestrictValues <em>Restrict Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Restrict Values</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#isRestrictValues()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_RestrictValues();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getValuelistSortOrder <em>Valuelist Sort Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valuelist Sort Order</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getValuelistSortOrder()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_ValuelistSortOrder();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#isAppendNewValues <em>Append New Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Append New Values</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#isAppendNewValues()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_AppendNewValues();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getDefault()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_Default();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.ExtendedAttribute#getValueList <em>Value List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value List</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute#getValueList()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EReference getExtendedAttribute_ValueList();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.ExtendedAttribute2 <em>Extended Attribute2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attribute2</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2
	 * @generated
	 */
	EClass getExtendedAttribute2();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2#getUID()
	 * @see #getExtendedAttribute2()
	 * @generated
	 */
	EAttribute getExtendedAttribute2_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2#getFieldID()
	 * @see #getExtendedAttribute2()
	 * @generated
	 */
	EAttribute getExtendedAttribute2_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2#getValue()
	 * @see #getExtendedAttribute2()
	 * @generated
	 */
	EAttribute getExtendedAttribute2_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2#getValueID()
	 * @see #getExtendedAttribute2()
	 * @generated
	 */
	EAttribute getExtendedAttribute2_ValueID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute2#getDurationFormat <em>Duration Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Format</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2#getDurationFormat()
	 * @see #getExtendedAttribute2()
	 * @generated
	 */
	EAttribute getExtendedAttribute2_DurationFormat();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.ExtendedAttribute3 <em>Extended Attribute3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attribute3</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3
	 * @generated
	 */
	EClass getExtendedAttribute3();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute3#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3#getUID()
	 * @see #getExtendedAttribute3()
	 * @generated
	 */
	EAttribute getExtendedAttribute3_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute3#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3#getFieldID()
	 * @see #getExtendedAttribute3()
	 * @generated
	 */
	EAttribute getExtendedAttribute3_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute3#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3#getValue()
	 * @see #getExtendedAttribute3()
	 * @generated
	 */
	EAttribute getExtendedAttribute3_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute3#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3#getValueID()
	 * @see #getExtendedAttribute3()
	 * @generated
	 */
	EAttribute getExtendedAttribute3_ValueID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute3#getDurationFormat <em>Duration Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Format</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3#getDurationFormat()
	 * @see #getExtendedAttribute3()
	 * @generated
	 */
	EAttribute getExtendedAttribute3_DurationFormat();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.ExtendedAttribute4 <em>Extended Attribute4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attribute4</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4
	 * @generated
	 */
	EClass getExtendedAttribute4();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute4#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4#getUID()
	 * @see #getExtendedAttribute4()
	 * @generated
	 */
	EAttribute getExtendedAttribute4_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute4#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4#getFieldID()
	 * @see #getExtendedAttribute4()
	 * @generated
	 */
	EAttribute getExtendedAttribute4_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute4#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4#getValue()
	 * @see #getExtendedAttribute4()
	 * @generated
	 */
	EAttribute getExtendedAttribute4_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute4#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4#getValueID()
	 * @see #getExtendedAttribute4()
	 * @generated
	 */
	EAttribute getExtendedAttribute4_ValueID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.ExtendedAttribute4#getDurationFormat <em>Duration Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Format</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4#getDurationFormat()
	 * @see #getExtendedAttribute4()
	 * @generated
	 */
	EAttribute getExtendedAttribute4_DurationFormat();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.ExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attributes</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttributes
	 * @generated
	 */
	EClass getExtendedAttributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.ExtendedAttributes#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
	 * @see org.eclipse.epf.msproject.ExtendedAttributes#getExtendedAttribute()
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	EReference getExtendedAttributes_ExtendedAttribute();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Mask <em>Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mask</em>'.
	 * @see org.eclipse.epf.msproject.Mask
	 * @generated
	 */
	EClass getMask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Mask#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see org.eclipse.epf.msproject.Mask#getLevel()
	 * @see #getMask()
	 * @generated
	 */
	EAttribute getMask_Level();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Mask#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.msproject.Mask#getType()
	 * @see #getMask()
	 * @generated
	 */
	EAttribute getMask_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Mask#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.epf.msproject.Mask#getLength()
	 * @see #getMask()
	 * @generated
	 */
	EAttribute getMask_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Mask#getSeparator <em>Separator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Separator</em>'.
	 * @see org.eclipse.epf.msproject.Mask#getSeparator()
	 * @see #getMask()
	 * @generated
	 */
	EAttribute getMask_Separator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Masks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Masks</em>'.
	 * @see org.eclipse.epf.msproject.Masks
	 * @generated
	 */
	EClass getMasks();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Masks#getMask <em>Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mask</em>'.
	 * @see org.eclipse.epf.msproject.Masks#getMask()
	 * @see #getMasks()
	 * @generated
	 */
	EReference getMasks_Mask();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.OutlineCode <em>Outline Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outline Code</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode
	 * @generated
	 */
	EClass getOutlineCode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getFieldID()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#getFieldName <em>Field Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Name</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getFieldName()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_FieldName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getAlias()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_Alias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#getPhoneticAlias <em>Phonetic Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phonetic Alias</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getPhoneticAlias()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_PhoneticAlias();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.OutlineCode#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Values</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getValues()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EReference getOutlineCode_Values();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#isEnterprise <em>Enterprise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enterprise</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#isEnterprise()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_Enterprise();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#getEnterpriseOutlineCodeAlias <em>Enterprise Outline Code Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enterprise Outline Code Alias</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getEnterpriseOutlineCodeAlias()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_EnterpriseOutlineCodeAlias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#isResourceSubstitutionEnabled <em>Resource Substitution Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Substitution Enabled</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#isResourceSubstitutionEnabled()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_ResourceSubstitutionEnabled();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#isLeafOnly <em>Leaf Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leaf Only</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#isLeafOnly()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_LeafOnly();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#isAllLevelsRequired <em>All Levels Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All Levels Required</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#isAllLevelsRequired()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_AllLevelsRequired();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode#isOnlyTableValuesAllowed <em>Only Table Values Allowed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Only Table Values Allowed</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#isOnlyTableValuesAllowed()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EAttribute getOutlineCode_OnlyTableValuesAllowed();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.OutlineCode#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Masks</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode#getMasks()
	 * @see #getOutlineCode()
	 * @generated
	 */
	EReference getOutlineCode_Masks();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.OutlineCode2 <em>Outline Code2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outline Code2</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode2
	 * @generated
	 */
	EClass getOutlineCode2();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode2#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode2#getUID()
	 * @see #getOutlineCode2()
	 * @generated
	 */
	EAttribute getOutlineCode2_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode2#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode2#getFieldID()
	 * @see #getOutlineCode2()
	 * @generated
	 */
	EAttribute getOutlineCode2_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode2#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode2#getValueID()
	 * @see #getOutlineCode2()
	 * @generated
	 */
	EAttribute getOutlineCode2_ValueID();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.OutlineCode3 <em>Outline Code3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outline Code3</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode3
	 * @generated
	 */
	EClass getOutlineCode3();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode3#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode3#getUID()
	 * @see #getOutlineCode3()
	 * @generated
	 */
	EAttribute getOutlineCode3_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode3#getFieldID <em>Field ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field ID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode3#getFieldID()
	 * @see #getOutlineCode3()
	 * @generated
	 */
	EAttribute getOutlineCode3_FieldID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.OutlineCode3#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCode3#getValueID()
	 * @see #getOutlineCode3()
	 * @generated
	 */
	EAttribute getOutlineCode3_ValueID();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.OutlineCodes <em>Outline Codes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outline Codes</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCodes
	 * @generated
	 */
	EClass getOutlineCodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.OutlineCodes#getOutlineCode <em>Outline Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outline Code</em>'.
	 * @see org.eclipse.epf.msproject.OutlineCodes#getOutlineCode()
	 * @see #getOutlineCodes()
	 * @generated
	 */
	EReference getOutlineCodes_OutlineCode();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.PredecessorLink <em>Predecessor Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predecessor Link</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink
	 * @generated
	 */
	EClass getPredecessorLink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.PredecessorLink#getPredecessorUID <em>Predecessor UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predecessor UID</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink#getPredecessorUID()
	 * @see #getPredecessorLink()
	 * @generated
	 */
	EAttribute getPredecessorLink_PredecessorUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.PredecessorLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink#getType()
	 * @see #getPredecessorLink()
	 * @generated
	 */
	EAttribute getPredecessorLink_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.PredecessorLink#isCrossProject <em>Cross Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cross Project</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink#isCrossProject()
	 * @see #getPredecessorLink()
	 * @generated
	 */
	EAttribute getPredecessorLink_CrossProject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.PredecessorLink#getCrossProjectName <em>Cross Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cross Project Name</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink#getCrossProjectName()
	 * @see #getPredecessorLink()
	 * @generated
	 */
	EAttribute getPredecessorLink_CrossProjectName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.PredecessorLink#getLinkLag <em>Link Lag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link Lag</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink#getLinkLag()
	 * @see #getPredecessorLink()
	 * @generated
	 */
	EAttribute getPredecessorLink_LinkLag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.PredecessorLink#getLagFormat <em>Lag Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lag Format</em>'.
	 * @see org.eclipse.epf.msproject.PredecessorLink#getLagFormat()
	 * @see #getPredecessorLink()
	 * @generated
	 */
	EAttribute getPredecessorLink_LagFormat();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project</em>'.
	 * @see org.eclipse.epf.msproject.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.Project#getUID()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.msproject.Project#getName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.eclipse.epf.msproject.Project#getTitle()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Title();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subject</em>'.
	 * @see org.eclipse.epf.msproject.Project#getSubject()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Subject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCategory()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Category();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Company</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCompany()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Company();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getManager <em>Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manager</em>'.
	 * @see org.eclipse.epf.msproject.Project#getManager()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Manager();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see org.eclipse.epf.msproject.Project#getAuthor()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Author();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCreationDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CreationDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getRevision <em>Revision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Revision</em>'.
	 * @see org.eclipse.epf.msproject.Project#getRevision()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Revision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getLastSaved <em>Last Saved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Saved</em>'.
	 * @see org.eclipse.epf.msproject.Project#getLastSaved()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_LastSaved();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isScheduleFromStart <em>Schedule From Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schedule From Start</em>'.
	 * @see org.eclipse.epf.msproject.Project#isScheduleFromStart()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ScheduleFromStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getStartDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_StartDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getFinishDate <em>Finish Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getFinishDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_FinishDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getFYStartDate <em>FY Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>FY Start Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getFYStartDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_FYStartDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCriticalSlackLimit <em>Critical Slack Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Critical Slack Limit</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCriticalSlackLimit()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CriticalSlackLimit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCurrencyDigits <em>Currency Digits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Currency Digits</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCurrencyDigits()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CurrencyDigits();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCurrencySymbol <em>Currency Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Currency Symbol</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCurrencySymbol()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CurrencySymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCurrencySymbolPosition <em>Currency Symbol Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Currency Symbol Position</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCurrencySymbolPosition()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CurrencySymbolPosition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCalendarUID <em>Calendar UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Calendar UID</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCalendarUID()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CalendarUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultStartTime <em>Default Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Start Time</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultStartTime()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultStartTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultFinishTime <em>Default Finish Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Finish Time</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultFinishTime()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultFinishTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getMinutesPerDay <em>Minutes Per Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minutes Per Day</em>'.
	 * @see org.eclipse.epf.msproject.Project#getMinutesPerDay()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MinutesPerDay();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getMinutesPerWeek <em>Minutes Per Week</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minutes Per Week</em>'.
	 * @see org.eclipse.epf.msproject.Project#getMinutesPerWeek()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MinutesPerWeek();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDaysPerMonth <em>Days Per Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Days Per Month</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDaysPerMonth()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DaysPerMonth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultTaskType <em>Default Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Task Type</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultTaskType()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultTaskType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultFixedCostAccrual <em>Default Fixed Cost Accrual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Fixed Cost Accrual</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultFixedCostAccrual()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultFixedCostAccrual();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultStandardRate <em>Default Standard Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Standard Rate</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultStandardRate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultStandardRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultOvertimeRate <em>Default Overtime Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Overtime Rate</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultOvertimeRate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultOvertimeRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDurationFormat <em>Duration Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Format</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDurationFormat()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DurationFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getWorkFormat <em>Work Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Format</em>'.
	 * @see org.eclipse.epf.msproject.Project#getWorkFormat()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_WorkFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isEditableActualCosts <em>Editable Actual Costs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Editable Actual Costs</em>'.
	 * @see org.eclipse.epf.msproject.Project#isEditableActualCosts()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_EditableActualCosts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isHonorConstraints <em>Honor Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Honor Constraints</em>'.
	 * @see org.eclipse.epf.msproject.Project#isHonorConstraints()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_HonorConstraints();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getEarnedValueMethod <em>Earned Value Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Earned Value Method</em>'.
	 * @see org.eclipse.epf.msproject.Project#getEarnedValueMethod()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_EarnedValueMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isInsertedProjectsLikeSummary <em>Inserted Projects Like Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inserted Projects Like Summary</em>'.
	 * @see org.eclipse.epf.msproject.Project#isInsertedProjectsLikeSummary()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_InsertedProjectsLikeSummary();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isMultipleCriticalPaths <em>Multiple Critical Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiple Critical Paths</em>'.
	 * @see org.eclipse.epf.msproject.Project#isMultipleCriticalPaths()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MultipleCriticalPaths();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isNewTasksEffortDriven <em>New Tasks Effort Driven</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Tasks Effort Driven</em>'.
	 * @see org.eclipse.epf.msproject.Project#isNewTasksEffortDriven()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_NewTasksEffortDriven();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isNewTasksEstimated <em>New Tasks Estimated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Tasks Estimated</em>'.
	 * @see org.eclipse.epf.msproject.Project#isNewTasksEstimated()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_NewTasksEstimated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isSplitsInProgressTasks <em>Splits In Progress Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Splits In Progress Tasks</em>'.
	 * @see org.eclipse.epf.msproject.Project#isSplitsInProgressTasks()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_SplitsInProgressTasks();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isSpreadActualCost <em>Spread Actual Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spread Actual Cost</em>'.
	 * @see org.eclipse.epf.msproject.Project#isSpreadActualCost()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_SpreadActualCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isSpreadPercentComplete <em>Spread Percent Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spread Percent Complete</em>'.
	 * @see org.eclipse.epf.msproject.Project#isSpreadPercentComplete()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_SpreadPercentComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isTaskUpdatesResource <em>Task Updates Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task Updates Resource</em>'.
	 * @see org.eclipse.epf.msproject.Project#isTaskUpdatesResource()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_TaskUpdatesResource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isFiscalYearStart <em>Fiscal Year Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fiscal Year Start</em>'.
	 * @see org.eclipse.epf.msproject.Project#isFiscalYearStart()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_FiscalYearStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getWeekStartDay <em>Week Start Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Week Start Day</em>'.
	 * @see org.eclipse.epf.msproject.Project#getWeekStartDay()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_WeekStartDay();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsBack <em>Move Completed Ends Back</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Move Completed Ends Back</em>'.
	 * @see org.eclipse.epf.msproject.Project#isMoveCompletedEndsBack()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MoveCompletedEndsBack();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsBack <em>Move Remaining Starts Back</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Move Remaining Starts Back</em>'.
	 * @see org.eclipse.epf.msproject.Project#isMoveRemainingStartsBack()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MoveRemainingStartsBack();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isMoveRemainingStartsForward <em>Move Remaining Starts Forward</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Move Remaining Starts Forward</em>'.
	 * @see org.eclipse.epf.msproject.Project#isMoveRemainingStartsForward()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MoveRemainingStartsForward();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isMoveCompletedEndsForward <em>Move Completed Ends Forward</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Move Completed Ends Forward</em>'.
	 * @see org.eclipse.epf.msproject.Project#isMoveCompletedEndsForward()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MoveCompletedEndsForward();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getBaselineForEarnedValue <em>Baseline For Earned Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Baseline For Earned Value</em>'.
	 * @see org.eclipse.epf.msproject.Project#getBaselineForEarnedValue()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_BaselineForEarnedValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isAutoAddNewResourcesAndTasks <em>Auto Add New Resources And Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Add New Resources And Tasks</em>'.
	 * @see org.eclipse.epf.msproject.Project#isAutoAddNewResourcesAndTasks()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_AutoAddNewResourcesAndTasks();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getStatusDate <em>Status Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getStatusDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_StatusDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getCurrentDate <em>Current Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCurrentDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_CurrentDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isMicrosoftProjectServerURL <em>Microsoft Project Server URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Microsoft Project Server URL</em>'.
	 * @see org.eclipse.epf.msproject.Project#isMicrosoftProjectServerURL()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_MicrosoftProjectServerURL();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isAutolink <em>Autolink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Autolink</em>'.
	 * @see org.eclipse.epf.msproject.Project#isAutolink()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Autolink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getNewTaskStartDate <em>New Task Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Task Start Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getNewTaskStartDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_NewTaskStartDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getDefaultTaskEVMethod <em>Default Task EV Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Task EV Method</em>'.
	 * @see org.eclipse.epf.msproject.Project#getDefaultTaskEVMethod()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_DefaultTaskEVMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isProjectExternallyEdited <em>Project Externally Edited</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Externally Edited</em>'.
	 * @see org.eclipse.epf.msproject.Project#isProjectExternallyEdited()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ProjectExternallyEdited();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#getExtendedCreationDate <em>Extended Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extended Creation Date</em>'.
	 * @see org.eclipse.epf.msproject.Project#getExtendedCreationDate()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ExtendedCreationDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isActualsInSync <em>Actuals In Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actuals In Sync</em>'.
	 * @see org.eclipse.epf.msproject.Project#isActualsInSync()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ActualsInSync();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isRemoveFileProperties <em>Remove File Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remove File Properties</em>'.
	 * @see org.eclipse.epf.msproject.Project#isRemoveFileProperties()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_RemoveFileProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Project#isAdminProject <em>Admin Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Admin Project</em>'.
	 * @see org.eclipse.epf.msproject.Project#isAdminProject()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_AdminProject();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getOutlineCodes <em>Outline Codes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outline Codes</em>'.
	 * @see org.eclipse.epf.msproject.Project#getOutlineCodes()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_OutlineCodes();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getWBSMasks <em>WBS Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>WBS Masks</em>'.
	 * @see org.eclipse.epf.msproject.Project#getWBSMasks()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_WBSMasks();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
	 * @see org.eclipse.epf.msproject.Project#getExtendedAttributes()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_ExtendedAttributes();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getCalendars <em>Calendars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Calendars</em>'.
	 * @see org.eclipse.epf.msproject.Project#getCalendars()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Calendars();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tasks</em>'.
	 * @see org.eclipse.epf.msproject.Project#getTasks()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Tasks();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resources</em>'.
	 * @see org.eclipse.epf.msproject.Project#getResources()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Resources();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Project#getAssignments <em>Assignments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assignments</em>'.
	 * @see org.eclipse.epf.msproject.Project#getAssignments()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Assignments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Rate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rate</em>'.
	 * @see org.eclipse.epf.msproject.Rate
	 * @generated
	 */
	EClass getRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getRatesFrom <em>Rates From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rates From</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getRatesFrom()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_RatesFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getRatesTo <em>Rates To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rates To</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getRatesTo()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_RatesTo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getRateTable <em>Rate Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate Table</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getRateTable()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_RateTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getStandardRate <em>Standard Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Rate</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getStandardRate()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_StandardRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getStandardRateFormat <em>Standard Rate Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Rate Format</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getStandardRateFormat()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_StandardRateFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getOvertimeRate <em>Overtime Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Rate</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getOvertimeRate()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_OvertimeRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getOvertimeRateFormat <em>Overtime Rate Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Rate Format</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getOvertimeRateFormat()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_OvertimeRateFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Rate#getCostPerUse <em>Cost Per Use</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Per Use</em>'.
	 * @see org.eclipse.epf.msproject.Rate#getCostPerUse()
	 * @see #getRate()
	 * @generated
	 */
	EAttribute getRate_CostPerUse();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Rates <em>Rates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rates</em>'.
	 * @see org.eclipse.epf.msproject.Rates
	 * @generated
	 */
	EClass getRates();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Rates#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rate</em>'.
	 * @see org.eclipse.epf.msproject.Rates#getRate()
	 * @see #getRates()
	 * @generated
	 */
	EReference getRates_Rate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.eclipse.epf.msproject.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getUID()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getID()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getName()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getType()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#isIsNull <em>Is Null</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Null</em>'.
	 * @see org.eclipse.epf.msproject.Resource#isIsNull()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_IsNull();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getInitials <em>Initials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initials</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getInitials()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Initials();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getPhonetics <em>Phonetics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phonetics</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getPhonetics()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Phonetics();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getNTAccount <em>NT Account</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>NT Account</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getNTAccount()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_NTAccount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getMaterialLabel <em>Material Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Material Label</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getMaterialLabel()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_MaterialLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCode()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Code();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getGroup()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Group();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getWorkGroup <em>Work Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Group</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getWorkGroup()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_WorkGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getEmailAddress <em>Email Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email Address</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getEmailAddress()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_EmailAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getHyperlink <em>Hyperlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getHyperlink()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Hyperlink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getHyperlinkAddress <em>Hyperlink Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink Address</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getHyperlinkAddress()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_HyperlinkAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink Sub Address</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getHyperlinkSubAddress()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_HyperlinkSubAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getMaxUnits <em>Max Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Units</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getMaxUnits()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_MaxUnits();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getPeakUnits <em>Peak Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Units</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getPeakUnits()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_PeakUnits();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#isOverAllocated <em>Over Allocated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Over Allocated</em>'.
	 * @see org.eclipse.epf.msproject.Resource#isOverAllocated()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_OverAllocated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getAvailableFrom <em>Available From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Available From</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getAvailableFrom()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_AvailableFrom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getAvailableTo <em>Available To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Available To</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getAvailableTo()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_AvailableTo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getStart()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getFinish <em>Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getFinish()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Finish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#isCanLevel <em>Can Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Level</em>'.
	 * @see org.eclipse.epf.msproject.Resource#isCanLevel()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_CanLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getAccrueAt <em>Accrue At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accrue At</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getAccrueAt()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_AccrueAt();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Work();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getRegularWork <em>Regular Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regular Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getRegularWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_RegularWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getOvertimeWork <em>Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getOvertimeWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_OvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActualWork <em>Actual Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActualWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActualWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getRemainingWork <em>Remaining Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getRemainingWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_RemainingWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActualOvertimeWork <em>Actual Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActualOvertimeWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActualOvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getRemainingOvertimeWork()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_RemainingOvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getPercentWorkComplete <em>Percent Work Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percent Work Complete</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getPercentWorkComplete()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_PercentWorkComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getStandardRate <em>Standard Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Rate</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getStandardRate()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_StandardRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getStandardRateFormat <em>Standard Rate Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Rate Format</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getStandardRateFormat()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_StandardRateFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCost()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getOvertimeRate <em>Overtime Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Rate</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getOvertimeRate()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_OvertimeRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getOvertimeRateFormat <em>Overtime Rate Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Rate Format</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getOvertimeRateFormat()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_OvertimeRateFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getOvertimeCost <em>Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getOvertimeCost()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_OvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCostPerUse <em>Cost Per Use</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Per Use</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCostPerUse()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_CostPerUse();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActualCost <em>Actual Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Cost</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActualCost()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActualCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActualOvertimeCost <em>Actual Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActualOvertimeCost()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActualOvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getRemainingCost <em>Remaining Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Cost</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getRemainingCost()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_RemainingCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getRemainingOvertimeCost()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_RemainingOvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getWorkVariance <em>Work Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Variance</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getWorkVariance()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_WorkVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCostVariance <em>Cost Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost Variance</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCostVariance()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_CostVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getSV <em>SV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SV</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getSV()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_SV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCV <em>CV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CV</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCV()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_CV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getACWP <em>ACWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ACWP</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getACWP()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ACWP();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCalendarUID <em>Calendar UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Calendar UID</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCalendarUID()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_CalendarUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getNotes <em>Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notes</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getNotes()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Notes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getBCWS <em>BCWS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWS</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getBCWS()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_BCWS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getBCWP <em>BCWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWP</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getBCWP()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_BCWP();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#isIsGeneric <em>Is Generic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Generic</em>'.
	 * @see org.eclipse.epf.msproject.Resource#isIsGeneric()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_IsGeneric();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#isIsInactive <em>Is Inactive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Inactive</em>'.
	 * @see org.eclipse.epf.msproject.Resource#isIsInactive()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_IsInactive();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#isIsEnterprise <em>Is Enterprise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Enterprise</em>'.
	 * @see org.eclipse.epf.msproject.Resource#isIsEnterprise()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_IsEnterprise();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getBookingType <em>Booking Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Booking Type</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getBookingType()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_BookingType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActualWorkProtected <em>Actual Work Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Work Protected</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActualWorkProtected()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActualWorkProtected();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Work Protected</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActualOvertimeWorkProtected()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActualOvertimeWorkProtected();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getActiveDirectoryGUID <em>Active Directory GUID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Directory GUID</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getActiveDirectoryGUID()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_ActiveDirectoryGUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Resource#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getCreationDate()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_CreationDate();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Resource#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getExtendedAttribute()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_ExtendedAttribute();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Resource#getBaseline <em>Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Baseline</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getBaseline()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Baseline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Resource#getOutlineCode <em>Outline Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outline Code</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getOutlineCode()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_OutlineCode();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Resource#getAvailabilityPeriods <em>Availability Periods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Availability Periods</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getAvailabilityPeriods()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_AvailabilityPeriods();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.Resource#getRates <em>Rates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rates</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getRates()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Rates();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Resource#getTimephasedData <em>Timephased Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timephased Data</em>'.
	 * @see org.eclipse.epf.msproject.Resource#getTimephasedData()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_TimephasedData();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Resources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resources</em>'.
	 * @see org.eclipse.epf.msproject.Resources
	 * @generated
	 */
	EClass getResources();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Resources#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource</em>'.
	 * @see org.eclipse.epf.msproject.Resources#getResource()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Resource();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see org.eclipse.epf.msproject.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.Task#getUID()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.eclipse.epf.msproject.Task#getID()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.epf.msproject.Task#getName()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.msproject.Task#getType()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isIsNull <em>Is Null</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Null</em>'.
	 * @see org.eclipse.epf.msproject.Task#isIsNull()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_IsNull();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getCreateDate <em>Create Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create Date</em>'.
	 * @see org.eclipse.epf.msproject.Task#getCreateDate()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_CreateDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contact</em>'.
	 * @see org.eclipse.epf.msproject.Task#getContact()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Contact();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getWBS <em>WBS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>WBS</em>'.
	 * @see org.eclipse.epf.msproject.Task#getWBS()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_WBS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getWBSLevel <em>WBS Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>WBS Level</em>'.
	 * @see org.eclipse.epf.msproject.Task#getWBSLevel()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_WBSLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getOutlineNumber <em>Outline Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outline Number</em>'.
	 * @see org.eclipse.epf.msproject.Task#getOutlineNumber()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_OutlineNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getOutlineLevel <em>Outline Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outline Level</em>'.
	 * @see org.eclipse.epf.msproject.Task#getOutlineLevel()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_OutlineLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPriority()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Priority();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.eclipse.epf.msproject.Task#getStart()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getFinish <em>Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish</em>'.
	 * @see org.eclipse.epf.msproject.Task#getFinish()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Finish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.eclipse.epf.msproject.Task#getDuration()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Duration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getDurationFormat <em>Duration Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration Format</em>'.
	 * @see org.eclipse.epf.msproject.Task#getDurationFormat()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_DurationFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Work();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getStop <em>Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop</em>'.
	 * @see org.eclipse.epf.msproject.Task#getStop()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Stop();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getResume <em>Resume</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resume</em>'.
	 * @see org.eclipse.epf.msproject.Task#getResume()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Resume();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isResumeValid <em>Resume Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resume Valid</em>'.
	 * @see org.eclipse.epf.msproject.Task#isResumeValid()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ResumeValid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isEffortDriven <em>Effort Driven</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Effort Driven</em>'.
	 * @see org.eclipse.epf.msproject.Task#isEffortDriven()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_EffortDriven();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isRecurring <em>Recurring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recurring</em>'.
	 * @see org.eclipse.epf.msproject.Task#isRecurring()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Recurring();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isOverAllocated <em>Over Allocated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Over Allocated</em>'.
	 * @see org.eclipse.epf.msproject.Task#isOverAllocated()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_OverAllocated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isEstimated <em>Estimated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Estimated</em>'.
	 * @see org.eclipse.epf.msproject.Task#isEstimated()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Estimated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isMilestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Milestone</em>'.
	 * @see org.eclipse.epf.msproject.Task#isMilestone()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Milestone();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isSummary <em>Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Summary</em>'.
	 * @see org.eclipse.epf.msproject.Task#isSummary()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Summary();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isCritical <em>Critical</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Critical</em>'.
	 * @see org.eclipse.epf.msproject.Task#isCritical()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Critical();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isIsSubproject <em>Is Subproject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Subproject</em>'.
	 * @see org.eclipse.epf.msproject.Task#isIsSubproject()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_IsSubproject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isIsSubprojectReadOnly <em>Is Subproject Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Subproject Read Only</em>'.
	 * @see org.eclipse.epf.msproject.Task#isIsSubprojectReadOnly()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_IsSubprojectReadOnly();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getSubprojectName <em>Subproject Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subproject Name</em>'.
	 * @see org.eclipse.epf.msproject.Task#getSubprojectName()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_SubprojectName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isExternalTask <em>External Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Task</em>'.
	 * @see org.eclipse.epf.msproject.Task#isExternalTask()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ExternalTask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getExternalTaskProject <em>External Task Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Task Project</em>'.
	 * @see org.eclipse.epf.msproject.Task#getExternalTaskProject()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ExternalTaskProject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getEarlyStart <em>Early Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Early Start</em>'.
	 * @see org.eclipse.epf.msproject.Task#getEarlyStart()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_EarlyStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getEarlyFinish <em>Early Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Early Finish</em>'.
	 * @see org.eclipse.epf.msproject.Task#getEarlyFinish()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_EarlyFinish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getLateStart <em>Late Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Late Start</em>'.
	 * @see org.eclipse.epf.msproject.Task#getLateStart()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_LateStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getLateFinish <em>Late Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Late Finish</em>'.
	 * @see org.eclipse.epf.msproject.Task#getLateFinish()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_LateFinish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getStartVariance <em>Start Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Variance</em>'.
	 * @see org.eclipse.epf.msproject.Task#getStartVariance()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_StartVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getFinishVariance <em>Finish Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish Variance</em>'.
	 * @see org.eclipse.epf.msproject.Task#getFinishVariance()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_FinishVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getWorkVariance <em>Work Variance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work Variance</em>'.
	 * @see org.eclipse.epf.msproject.Task#getWorkVariance()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_WorkVariance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getFreeSlack <em>Free Slack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Free Slack</em>'.
	 * @see org.eclipse.epf.msproject.Task#getFreeSlack()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_FreeSlack();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getTotalSlack <em>Total Slack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Slack</em>'.
	 * @see org.eclipse.epf.msproject.Task#getTotalSlack()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_TotalSlack();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getFixedCost <em>Fixed Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getFixedCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_FixedCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getFixedCostAccrual <em>Fixed Cost Accrual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Cost Accrual</em>'.
	 * @see org.eclipse.epf.msproject.Task#getFixedCostAccrual()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_FixedCostAccrual();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getPercentComplete <em>Percent Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percent Complete</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPercentComplete()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PercentComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getPercentWorkComplete <em>Percent Work Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percent Work Complete</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPercentWorkComplete()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PercentWorkComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getCost <em>Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Cost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getOvertimeCost <em>Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getOvertimeCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_OvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getOvertimeWork <em>Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getOvertimeWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_OvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualStart <em>Actual Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Start</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualStart()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualFinish <em>Actual Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Finish</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualFinish()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualFinish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualDuration <em>Actual Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Duration</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualDuration()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualDuration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualCost <em>Actual Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualOvertimeCost <em>Actual Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualOvertimeCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualOvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualWork <em>Actual Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualOvertimeWork <em>Actual Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualOvertimeWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualOvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getRegularWork <em>Regular Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regular Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getRegularWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_RegularWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getRemainingDuration <em>Remaining Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Duration</em>'.
	 * @see org.eclipse.epf.msproject.Task#getRemainingDuration()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_RemainingDuration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getRemainingCost <em>Remaining Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getRemainingCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_RemainingCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getRemainingWork <em>Remaining Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getRemainingWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_RemainingWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Overtime Cost</em>'.
	 * @see org.eclipse.epf.msproject.Task#getRemainingOvertimeCost()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_RemainingOvertimeCost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remaining Overtime Work</em>'.
	 * @see org.eclipse.epf.msproject.Task#getRemainingOvertimeWork()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_RemainingOvertimeWork();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getACWP <em>ACWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ACWP</em>'.
	 * @see org.eclipse.epf.msproject.Task#getACWP()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ACWP();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getCV <em>CV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>CV</em>'.
	 * @see org.eclipse.epf.msproject.Task#getCV()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_CV();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getConstraintType <em>Constraint Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constraint Type</em>'.
	 * @see org.eclipse.epf.msproject.Task#getConstraintType()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ConstraintType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getCalendarUID <em>Calendar UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Calendar UID</em>'.
	 * @see org.eclipse.epf.msproject.Task#getCalendarUID()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_CalendarUID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getConstraintDate <em>Constraint Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constraint Date</em>'.
	 * @see org.eclipse.epf.msproject.Task#getConstraintDate()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ConstraintDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getDeadline <em>Deadline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deadline</em>'.
	 * @see org.eclipse.epf.msproject.Task#getDeadline()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Deadline();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isLevelAssignments <em>Level Assignments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level Assignments</em>'.
	 * @see org.eclipse.epf.msproject.Task#isLevelAssignments()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_LevelAssignments();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isLevelingCanSplit <em>Leveling Can Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leveling Can Split</em>'.
	 * @see org.eclipse.epf.msproject.Task#isLevelingCanSplit()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_LevelingCanSplit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getLevelingDelay <em>Leveling Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leveling Delay</em>'.
	 * @see org.eclipse.epf.msproject.Task#getLevelingDelay()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_LevelingDelay();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getLevelingDelayFormat <em>Leveling Delay Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leveling Delay Format</em>'.
	 * @see org.eclipse.epf.msproject.Task#getLevelingDelayFormat()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_LevelingDelayFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getPreLeveledStart <em>Pre Leveled Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Leveled Start</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPreLeveledStart()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PreLeveledStart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getPreLeveledFinish <em>Pre Leveled Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Leveled Finish</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPreLeveledFinish()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PreLeveledFinish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getHyperlink <em>Hyperlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink</em>'.
	 * @see org.eclipse.epf.msproject.Task#getHyperlink()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Hyperlink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getHyperlinkAddress <em>Hyperlink Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink Address</em>'.
	 * @see org.eclipse.epf.msproject.Task#getHyperlinkAddress()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_HyperlinkAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hyperlink Sub Address</em>'.
	 * @see org.eclipse.epf.msproject.Task#getHyperlinkSubAddress()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_HyperlinkSubAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isIgnoreResourceCalendar <em>Ignore Resource Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Resource Calendar</em>'.
	 * @see org.eclipse.epf.msproject.Task#isIgnoreResourceCalendar()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_IgnoreResourceCalendar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getNotes <em>Notes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notes</em>'.
	 * @see org.eclipse.epf.msproject.Task#getNotes()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Notes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isHideBar <em>Hide Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hide Bar</em>'.
	 * @see org.eclipse.epf.msproject.Task#isHideBar()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_HideBar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#isRollup <em>Rollup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rollup</em>'.
	 * @see org.eclipse.epf.msproject.Task#isRollup()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Rollup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getBCWS <em>BCWS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWS</em>'.
	 * @see org.eclipse.epf.msproject.Task#getBCWS()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_BCWS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getBCWP <em>BCWP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>BCWP</em>'.
	 * @see org.eclipse.epf.msproject.Task#getBCWP()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_BCWP();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getPhysicalPercentComplete <em>Physical Percent Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Physical Percent Complete</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPhysicalPercentComplete()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_PhysicalPercentComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getEarnedValueMethod <em>Earned Value Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Earned Value Method</em>'.
	 * @see org.eclipse.epf.msproject.Task#getEarnedValueMethod()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_EarnedValueMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Task#getPredecessorLink <em>Predecessor Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Predecessor Link</em>'.
	 * @see org.eclipse.epf.msproject.Task#getPredecessorLink()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_PredecessorLink();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualWorkProtected <em>Actual Work Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Work Protected</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualWorkProtected()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualWorkProtected();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Task#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Overtime Work Protected</em>'.
	 * @see org.eclipse.epf.msproject.Task#getActualOvertimeWorkProtected()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_ActualOvertimeWorkProtected();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Task#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
	 * @see org.eclipse.epf.msproject.Task#getExtendedAttribute()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_ExtendedAttribute();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Task#getBaseline <em>Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Baseline</em>'.
	 * @see org.eclipse.epf.msproject.Task#getBaseline()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Baseline();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Task#getOutlineCode <em>Outline Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outline Code</em>'.
	 * @see org.eclipse.epf.msproject.Task#getOutlineCode()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_OutlineCode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Task#getTimephasedData <em>Timephased Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timephased Data</em>'.
	 * @see org.eclipse.epf.msproject.Task#getTimephasedData()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_TimephasedData();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Tasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tasks</em>'.
	 * @see org.eclipse.epf.msproject.Tasks
	 * @generated
	 */
	EClass getTasks();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Tasks#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task</em>'.
	 * @see org.eclipse.epf.msproject.Tasks#getTask()
	 * @see #getTasks()
	 * @generated
	 */
	EReference getTasks_Task();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.TimePeriod <em>Time Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Period</em>'.
	 * @see org.eclipse.epf.msproject.TimePeriod
	 * @generated
	 */
	EClass getTimePeriod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimePeriod#getFromDate <em>From Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From Date</em>'.
	 * @see org.eclipse.epf.msproject.TimePeriod#getFromDate()
	 * @see #getTimePeriod()
	 * @generated
	 */
	EAttribute getTimePeriod_FromDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimePeriod#getToDate <em>To Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Date</em>'.
	 * @see org.eclipse.epf.msproject.TimePeriod#getToDate()
	 * @see #getTimePeriod()
	 * @generated
	 */
	EAttribute getTimePeriod_ToDate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.TimephasedDataType <em>Timephased Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timephased Data Type</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType
	 * @generated
	 */
	EClass getTimephasedDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimephasedDataType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType#getType()
	 * @see #getTimephasedDataType()
	 * @generated
	 */
	EAttribute getTimephasedDataType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimephasedDataType#getUID <em>UID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UID</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType#getUID()
	 * @see #getTimephasedDataType()
	 * @generated
	 */
	EAttribute getTimephasedDataType_UID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimephasedDataType#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType#getStart()
	 * @see #getTimephasedDataType()
	 * @generated
	 */
	EAttribute getTimephasedDataType_Start();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimephasedDataType#getFinish <em>Finish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finish</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType#getFinish()
	 * @see #getTimephasedDataType()
	 * @generated
	 */
	EAttribute getTimephasedDataType_Finish();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimephasedDataType#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType#getUnit()
	 * @see #getTimephasedDataType()
	 * @generated
	 */
	EAttribute getTimephasedDataType_Unit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.TimephasedDataType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.TimephasedDataType#getValue()
	 * @see #getTimephasedDataType()
	 * @generated
	 */
	EAttribute getTimephasedDataType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value#getValueID <em>Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value ID</em>'.
	 * @see org.eclipse.epf.msproject.Value#getValueID()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_ValueID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value#getParentValueID <em>Parent Value ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent Value ID</em>'.
	 * @see org.eclipse.epf.msproject.Value#getParentValueID()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_ParentValueID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.Value#getValue()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.epf.msproject.Value#getDescription()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Description();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Value2 <em>Value2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value2</em>'.
	 * @see org.eclipse.epf.msproject.Value2
	 * @generated
	 */
	EClass getValue2();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value2#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.eclipse.epf.msproject.Value2#getID()
	 * @see #getValue2()
	 * @generated
	 */
	EAttribute getValue2_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value2#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.Value2#getValue()
	 * @see #getValue2()
	 * @generated
	 */
	EAttribute getValue2_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.Value2#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.epf.msproject.Value2#getDescription()
	 * @see #getValue2()
	 * @generated
	 */
	EAttribute getValue2_Description();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.ValueList <em>Value List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value List</em>'.
	 * @see org.eclipse.epf.msproject.ValueList
	 * @generated
	 */
	EClass getValueList();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.ValueList#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.ValueList#getValue()
	 * @see #getValueList()
	 * @generated
	 */
	EReference getValueList_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.Values <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Values</em>'.
	 * @see org.eclipse.epf.msproject.Values
	 * @generated
	 */
	EClass getValues();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.epf.msproject.Values#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.epf.msproject.Values#getGroup()
	 * @see #getValues()
	 * @generated
	 */
	EAttribute getValues_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.Values#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.eclipse.epf.msproject.Values#getValue()
	 * @see #getValues()
	 * @generated
	 */
	EReference getValues_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.WBSMask <em>WBS Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>WBS Mask</em>'.
	 * @see org.eclipse.epf.msproject.WBSMask
	 * @generated
	 */
	EClass getWBSMask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMask#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see org.eclipse.epf.msproject.WBSMask#getLevel()
	 * @see #getWBSMask()
	 * @generated
	 */
	EAttribute getWBSMask_Level();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMask#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.epf.msproject.WBSMask#getType()
	 * @see #getWBSMask()
	 * @generated
	 */
	EAttribute getWBSMask_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMask#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.epf.msproject.WBSMask#getLength()
	 * @see #getWBSMask()
	 * @generated
	 */
	EAttribute getWBSMask_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMask#getSeparator <em>Separator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Separator</em>'.
	 * @see org.eclipse.epf.msproject.WBSMask#getSeparator()
	 * @see #getWBSMask()
	 * @generated
	 */
	EAttribute getWBSMask_Separator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.WBSMasks <em>WBS Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>WBS Masks</em>'.
	 * @see org.eclipse.epf.msproject.WBSMasks
	 * @generated
	 */
	EClass getWBSMasks();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMasks#isVerifyUniqueCodes <em>Verify Unique Codes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verify Unique Codes</em>'.
	 * @see org.eclipse.epf.msproject.WBSMasks#isVerifyUniqueCodes()
	 * @see #getWBSMasks()
	 * @generated
	 */
	EAttribute getWBSMasks_VerifyUniqueCodes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMasks#isGenerateCodes <em>Generate Codes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generate Codes</em>'.
	 * @see org.eclipse.epf.msproject.WBSMasks#isGenerateCodes()
	 * @see #getWBSMasks()
	 * @generated
	 */
	EAttribute getWBSMasks_GenerateCodes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WBSMasks#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see org.eclipse.epf.msproject.WBSMasks#getPrefix()
	 * @see #getWBSMasks()
	 * @generated
	 */
	EAttribute getWBSMasks_Prefix();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.WBSMasks#getWBSMask <em>WBS Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>WBS Mask</em>'.
	 * @see org.eclipse.epf.msproject.WBSMasks#getWBSMask()
	 * @see #getWBSMasks()
	 * @generated
	 */
	EReference getWBSMasks_WBSMask();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.WeekDay <em>Week Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Week Day</em>'.
	 * @see org.eclipse.epf.msproject.WeekDay
	 * @generated
	 */
	EClass getWeekDay();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WeekDay#getDayType <em>Day Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day Type</em>'.
	 * @see org.eclipse.epf.msproject.WeekDay#getDayType()
	 * @see #getWeekDay()
	 * @generated
	 */
	EAttribute getWeekDay_DayType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WeekDay#isDayWorking <em>Day Working</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day Working</em>'.
	 * @see org.eclipse.epf.msproject.WeekDay#isDayWorking()
	 * @see #getWeekDay()
	 * @generated
	 */
	EAttribute getWeekDay_DayWorking();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.WeekDay#getTimePeriod <em>Time Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Period</em>'.
	 * @see org.eclipse.epf.msproject.WeekDay#getTimePeriod()
	 * @see #getWeekDay()
	 * @generated
	 */
	EReference getWeekDay_TimePeriod();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epf.msproject.WeekDay#getWorkingTimes <em>Working Times</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Working Times</em>'.
	 * @see org.eclipse.epf.msproject.WeekDay#getWorkingTimes()
	 * @see #getWeekDay()
	 * @generated
	 */
	EReference getWeekDay_WorkingTimes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.WeekDays <em>Week Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Week Days</em>'.
	 * @see org.eclipse.epf.msproject.WeekDays
	 * @generated
	 */
	EClass getWeekDays();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.WeekDays#getWeekDay <em>Week Day</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Week Day</em>'.
	 * @see org.eclipse.epf.msproject.WeekDays#getWeekDay()
	 * @see #getWeekDays()
	 * @generated
	 */
	EReference getWeekDays_WeekDay();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.WorkingTime <em>Working Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Working Time</em>'.
	 * @see org.eclipse.epf.msproject.WorkingTime
	 * @generated
	 */
	EClass getWorkingTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WorkingTime#getFromTime <em>From Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From Time</em>'.
	 * @see org.eclipse.epf.msproject.WorkingTime#getFromTime()
	 * @see #getWorkingTime()
	 * @generated
	 */
	EAttribute getWorkingTime_FromTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epf.msproject.WorkingTime#getToTime <em>To Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Time</em>'.
	 * @see org.eclipse.epf.msproject.WorkingTime#getToTime()
	 * @see #getWorkingTime()
	 * @generated
	 */
	EAttribute getWorkingTime_ToTime();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epf.msproject.WorkingTimes <em>Working Times</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Working Times</em>'.
	 * @see org.eclipse.epf.msproject.WorkingTimes
	 * @generated
	 */
	EClass getWorkingTimes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epf.msproject.WorkingTimes#getWorkingTime <em>Working Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Working Time</em>'.
	 * @see org.eclipse.epf.msproject.WorkingTimes#getWorkingTime()
	 * @see #getWorkingTimes()
	 * @generated
	 */
	EReference getWorkingTimes_WorkingTime();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Accrue At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Accrue At</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='AccrueAt_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3'" 
	 * @generated
	 */
	EDataType getAccrueAt();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Active Directory GUID Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Active Directory GUID Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='ActiveDirectoryGUID_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='16'" 
	 * @generated
	 */
	EDataType getActiveDirectoryGUIDType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Alias Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Alias Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Alias_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='50'" 
	 * @generated
	 */
	EDataType getAliasType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Author Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Author Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Author_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getAuthorType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Baseline For Earned Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Baseline For Earned Value Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='BaselineForEarnedValue_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4 5 6 7 8 9 10'" 
	 * @generated
	 */
	EDataType getBaselineForEarnedValueType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Booking Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Booking Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='BookingType_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getBookingTypeType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Booking Type Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Booking Type Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='BookingType_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getBookingTypeType1();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Calculation Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Calculation Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='CalculationType_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2'" 
	 * @generated
	 */
	EDataType getCalculationTypeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Category Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Category Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Category_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getCategoryType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Code Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Code Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Code_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getCodeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Company Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Company Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Company_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getCompanyType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Constraint Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Constraint Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='ConstraintType_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4 5 6 7'" 
	 * @generated
	 */
	EDataType getConstraintTypeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Contact Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Contact Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Contact_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getContactType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Cost Rate Table Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Cost Rate Table Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='CostRateTable_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4'" 
	 * @generated
	 */
	EDataType getCostRateTableType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Currency Symbol Position Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Currency Symbol Position Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='CurrencySymbolPosition_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3'" 
	 * @generated
	 */
	EDataType getCurrencySymbolPositionType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Currency Symbol Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Currency Symbol Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='CurrencySymbol_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='20'" 
	 * @generated
	 */
	EDataType getCurrencySymbolType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Day Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Day Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DayType_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4 5 6 7'" 
	 * @generated
	 */
	EDataType getDayTypeType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Default Fixed Cost Accrual Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Default Fixed Cost Accrual Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DefaultFixedCostAccrual_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3'" 
	 * @generated
	 */
	EDataType getDefaultFixedCostAccrualType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Default Task EV Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Default Task EV Method Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DefaultTaskEVMethod_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getDefaultTaskEVMethodType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Default Task Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Default Task Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DefaultTaskType_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2'" 
	 * @generated
	 */
	EDataType getDefaultTaskTypeType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Duration Format Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Format Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DurationFormat_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getDurationFormatType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Duration Format Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Format Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DurationFormat_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getDurationFormatType1();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Duration Format Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Format Type2</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DurationFormat_._2_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getDurationFormatType2();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Duration Format Type3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Format Type3</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DurationFormat_._3_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getDurationFormatType3();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Duration Format Type4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Format Type4</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DurationFormat_._4_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getDurationFormatType4();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Duration Format Type5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Duration Format Type5</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='DurationFormat_._5_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getDurationFormatType5();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Earned Value Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Earned Value Method Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='EarnedValueMethod_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getEarnedValueMethodType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Earned Value Method Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Earned Value Method Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='EarnedValueMethod_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getEarnedValueMethodType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Email Address Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Email Address Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='EmailAddress_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getEmailAddressType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>External Task Project Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>External Task Project Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='ExternalTaskProject_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getExternalTaskProjectType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Fixed Cost Accrual Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Fixed Cost Accrual Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='FixedCostAccrual_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' enumeration='1 2 3'" 
	 * @generated
	 */
	EDataType getFixedCostAccrualType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>FY Start Date Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>FY Start Date Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='FYStartDate_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5 6 7 8 9 10 11 12'" 
	 * @generated
	 */
	EDataType getFYStartDateType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Group Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Group Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Group_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getGroupType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Address Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Address Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='HyperlinkAddress_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkAddressType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Address Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Address Type1</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='HyperlinkAddress_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkAddressType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Address Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Address Type2</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='HyperlinkAddress_._2_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkAddressType2();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Sub Address Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Sub Address Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='HyperlinkSubAddress_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkSubAddressType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Sub Address Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Sub Address Type1</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='HyperlinkSubAddress_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkSubAddressType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Sub Address Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Sub Address Type2</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='HyperlinkSubAddress_._2_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkSubAddressType2();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Hyperlink_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Type1</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Hyperlink_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Hyperlink Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Hyperlink Type2</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Hyperlink_._2_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getHyperlinkType2();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Initials Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Initials Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Initials_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getInitialsType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Lag Format Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Lag Format Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='LagFormat_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getLagFormatType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Leveling Delay Format Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Leveling Delay Format Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='LevelingDelayFormat_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getLevelingDelayFormatType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Leveling Delay Format Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Leveling Delay Format Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='LevelingDelayFormat_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='3 4 5 6 7 8 9 10 11 12 19 20 21 35 36 37 38 39 40 41 42 43 44 51 52 53'" 
	 * @generated
	 */
	EDataType getLevelingDelayFormatType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Manager Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Manager Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Manager_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getManagerType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Material Label Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Material Label Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='MaterialLabel_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getMaterialLabelType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Name Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Name_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getNameType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Name Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Name Type1</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Name_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getNameType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Name Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Name Type2</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Name_._2_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getNameType2();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Name Type3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Name Type3</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Name_._3_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='255'" 
	 * @generated
	 */
	EDataType getNameType3();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>New Task Start Date Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>New Task Start Date Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='NewTaskStartDate_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getNewTaskStartDateType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>NT Account Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>NT Account Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='NTAccount_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getNTAccountType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Outline Number Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Outline Number Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='OutlineNumber_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getOutlineNumberType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Overtime Rate Format Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Overtime Rate Format Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='OvertimeRateFormat_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5 7'" 
	 * @generated
	 */
	EDataType getOvertimeRateFormatType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Overtime Rate Format Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Overtime Rate Format Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='OvertimeRateFormat_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5 7'" 
	 * @generated
	 */
	EDataType getOvertimeRateFormatType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Phonetic Alias Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Phonetic Alias Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='PhoneticAlias_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='50'" 
	 * @generated
	 */
	EDataType getPhoneticAliasType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Phonetics Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Phonetics Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Phonetics_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getPhoneticsType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Prefix Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Prefix Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Prefix_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='50'" 
	 * @generated
	 */
	EDataType getPrefixType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Rate Table Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Rate Table Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='RateTable_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4'" 
	 * @generated
	 */
	EDataType getRateTableType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Rollup Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Rollup Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='RollupType_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4 5 6 7'" 
	 * @generated
	 */
	EDataType getRollupTypeType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Standard Rate Format Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Standard Rate Format Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='StandardRateFormat_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5 7'" 
	 * @generated
	 */
	EDataType getStandardRateFormatType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Standard Rate Format Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Standard Rate Format Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='StandardRateFormat_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5 7 8'" 
	 * @generated
	 */
	EDataType getStandardRateFormatType1();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Subject Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Subject Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Subject_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getSubjectType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Subproject Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Subproject Name Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='SubprojectName_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getSubprojectNameType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Title Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Title Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='Title_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='512'" 
	 * @generated
	 */
	EDataType getTitleType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Type_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2'" 
	 * @generated
	 */
	EDataType getTypeType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Type Type1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type1</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Type_._1_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3'" 
	 * @generated
	 */
	EDataType getTypeType1();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Type Type2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type2</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Type_._2_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3'" 
	 * @generated
	 */
	EDataType getTypeType2();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Type Type3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type3</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Type_._3_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5 6 7 8 9 10 11 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76'" 
	 * @generated
	 */
	EDataType getTypeType3();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Type Type4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type4</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Type_._4_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getTypeType4();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Type Type5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type5</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Type_._5_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3'" 
	 * @generated
	 */
	EDataType getTypeType5();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>UID Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>UID Type</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='UID_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#string' maxLength='16'" 
	 * @generated
	 */
	EDataType getUIDType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Unit Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Unit Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='Unit_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 5 8'" 
	 * @generated
	 */
	EDataType getUnitType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Valuelist Sort Order Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Valuelist Sort Order Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='ValuelistSortOrder_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1'" 
	 * @generated
	 */
	EDataType getValuelistSortOrderType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Week Start Day Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Week Start Day Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='WeekStartDay_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4 5 6'" 
	 * @generated
	 */
	EDataType getWeekStartDayType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Work Contour Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Work Contour Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='WorkContour_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3 4 5 6 7 8'" 
	 * @generated
	 */
	EDataType getWorkContourType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Work Format Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Work Format Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='WorkFormat_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='1 2 3 4 5'" 
	 * @generated
	 */
	EDataType getWorkFormatType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Work Group Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Work Group Type</em>'.
	 * @see java.math.BigInteger
	 * @model instanceClass="java.math.BigInteger"
	 *        extendedMetaData="name='WorkGroup_._type' baseType='http://www.eclipse.org/emf/2003/XMLType#integer' enumeration='0 1 2 3'" 
	 * @generated
	 */
	EDataType getWorkGroupType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MsprojectFactory getMsprojectFactory();

} //MsprojectPackage
