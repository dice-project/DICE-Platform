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
package org.eclipse.epf.msproject.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.msproject.Assignment;
import org.eclipse.epf.msproject.Assignments;
import org.eclipse.epf.msproject.AvailabilityPeriod;
import org.eclipse.epf.msproject.AvailabilityPeriods;
import org.eclipse.epf.msproject.Baseline;
import org.eclipse.epf.msproject.Baseline2;
import org.eclipse.epf.msproject.Baseline3;
import org.eclipse.epf.msproject.Calendar;
import org.eclipse.epf.msproject.Calendars;
import org.eclipse.epf.msproject.DocumentRoot;
import org.eclipse.epf.msproject.ExtendedAttribute;
import org.eclipse.epf.msproject.ExtendedAttribute2;
import org.eclipse.epf.msproject.ExtendedAttribute3;
import org.eclipse.epf.msproject.ExtendedAttribute4;
import org.eclipse.epf.msproject.ExtendedAttributes;
import org.eclipse.epf.msproject.Mask;
import org.eclipse.epf.msproject.Masks;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.OutlineCode;
import org.eclipse.epf.msproject.OutlineCode2;
import org.eclipse.epf.msproject.OutlineCode3;
import org.eclipse.epf.msproject.OutlineCodes;
import org.eclipse.epf.msproject.PredecessorLink;
import org.eclipse.epf.msproject.Project;
import org.eclipse.epf.msproject.Rate;
import org.eclipse.epf.msproject.Rates;
import org.eclipse.epf.msproject.Resource;
import org.eclipse.epf.msproject.Resources;
import org.eclipse.epf.msproject.Task;
import org.eclipse.epf.msproject.Tasks;
import org.eclipse.epf.msproject.TimePeriod;
import org.eclipse.epf.msproject.TimephasedDataType;
import org.eclipse.epf.msproject.Value;
import org.eclipse.epf.msproject.Value2;
import org.eclipse.epf.msproject.ValueList;
import org.eclipse.epf.msproject.Values;
import org.eclipse.epf.msproject.WBSMask;
import org.eclipse.epf.msproject.WBSMasks;
import org.eclipse.epf.msproject.WeekDay;
import org.eclipse.epf.msproject.WeekDays;
import org.eclipse.epf.msproject.WorkingTime;
import org.eclipse.epf.msproject.WorkingTimes;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.msproject.MsprojectPackage
 * @generated
 */
public class MsprojectAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MsprojectPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsprojectAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MsprojectPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MsprojectSwitch modelSwitch =
		new MsprojectSwitch() {
			public Object caseAssignment(Assignment object) {
				return createAssignmentAdapter();
			}
			public Object caseAssignments(Assignments object) {
				return createAssignmentsAdapter();
			}
			public Object caseAvailabilityPeriod(AvailabilityPeriod object) {
				return createAvailabilityPeriodAdapter();
			}
			public Object caseAvailabilityPeriods(AvailabilityPeriods object) {
				return createAvailabilityPeriodsAdapter();
			}
			public Object caseBaseline(Baseline object) {
				return createBaselineAdapter();
			}
			public Object caseBaseline2(Baseline2 object) {
				return createBaseline2Adapter();
			}
			public Object caseBaseline3(Baseline3 object) {
				return createBaseline3Adapter();
			}
			public Object caseCalendar(Calendar object) {
				return createCalendarAdapter();
			}
			public Object caseCalendars(Calendars object) {
				return createCalendarsAdapter();
			}
			public Object caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			public Object caseExtendedAttribute(ExtendedAttribute object) {
				return createExtendedAttributeAdapter();
			}
			public Object caseExtendedAttribute2(ExtendedAttribute2 object) {
				return createExtendedAttribute2Adapter();
			}
			public Object caseExtendedAttribute3(ExtendedAttribute3 object) {
				return createExtendedAttribute3Adapter();
			}
			public Object caseExtendedAttribute4(ExtendedAttribute4 object) {
				return createExtendedAttribute4Adapter();
			}
			public Object caseExtendedAttributes(ExtendedAttributes object) {
				return createExtendedAttributesAdapter();
			}
			public Object caseMask(Mask object) {
				return createMaskAdapter();
			}
			public Object caseMasks(Masks object) {
				return createMasksAdapter();
			}
			public Object caseOutlineCode(OutlineCode object) {
				return createOutlineCodeAdapter();
			}
			public Object caseOutlineCode2(OutlineCode2 object) {
				return createOutlineCode2Adapter();
			}
			public Object caseOutlineCode3(OutlineCode3 object) {
				return createOutlineCode3Adapter();
			}
			public Object caseOutlineCodes(OutlineCodes object) {
				return createOutlineCodesAdapter();
			}
			public Object casePredecessorLink(PredecessorLink object) {
				return createPredecessorLinkAdapter();
			}
			public Object caseProject(Project object) {
				return createProjectAdapter();
			}
			public Object caseRate(Rate object) {
				return createRateAdapter();
			}
			public Object caseRates(Rates object) {
				return createRatesAdapter();
			}
			public Object caseResource(Resource object) {
				return createResourceAdapter();
			}
			public Object caseResources(Resources object) {
				return createResourcesAdapter();
			}
			public Object caseTask(Task object) {
				return createTaskAdapter();
			}
			public Object caseTasks(Tasks object) {
				return createTasksAdapter();
			}
			public Object caseTimePeriod(TimePeriod object) {
				return createTimePeriodAdapter();
			}
			public Object caseTimephasedDataType(TimephasedDataType object) {
				return createTimephasedDataTypeAdapter();
			}
			public Object caseValue(Value object) {
				return createValueAdapter();
			}
			public Object caseValue2(Value2 object) {
				return createValue2Adapter();
			}
			public Object caseValueList(ValueList object) {
				return createValueListAdapter();
			}
			public Object caseValues(Values object) {
				return createValuesAdapter();
			}
			public Object caseWBSMask(WBSMask object) {
				return createWBSMaskAdapter();
			}
			public Object caseWBSMasks(WBSMasks object) {
				return createWBSMasksAdapter();
			}
			public Object caseWeekDay(WeekDay object) {
				return createWeekDayAdapter();
			}
			public Object caseWeekDays(WeekDays object) {
				return createWeekDaysAdapter();
			}
			public Object caseWorkingTime(WorkingTime object) {
				return createWorkingTimeAdapter();
			}
			public Object caseWorkingTimes(WorkingTimes object) {
				return createWorkingTimesAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Assignment
	 * @generated
	 */
	public Adapter createAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Assignments <em>Assignments</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Assignments
	 * @generated
	 */
	public Adapter createAssignmentsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.AvailabilityPeriod <em>Availability Period</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriod
	 * @generated
	 */
	public Adapter createAvailabilityPeriodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.AvailabilityPeriods <em>Availability Periods</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.AvailabilityPeriods
	 * @generated
	 */
	public Adapter createAvailabilityPeriodsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Baseline <em>Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Baseline
	 * @generated
	 */
	public Adapter createBaselineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Baseline2 <em>Baseline2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Baseline2
	 * @generated
	 */
	public Adapter createBaseline2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Baseline3 <em>Baseline3</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Baseline3
	 * @generated
	 */
	public Adapter createBaseline3Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Calendar <em>Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Calendar
	 * @generated
	 */
	public Adapter createCalendarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Calendars <em>Calendars</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Calendars
	 * @generated
	 */
	public Adapter createCalendarsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.ExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute
	 * @generated
	 */
	public Adapter createExtendedAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.ExtendedAttribute2 <em>Extended Attribute2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute2
	 * @generated
	 */
	public Adapter createExtendedAttribute2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.ExtendedAttribute3 <em>Extended Attribute3</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute3
	 * @generated
	 */
	public Adapter createExtendedAttribute3Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.ExtendedAttribute4 <em>Extended Attribute4</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.ExtendedAttribute4
	 * @generated
	 */
	public Adapter createExtendedAttribute4Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.ExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.ExtendedAttributes
	 * @generated
	 */
	public Adapter createExtendedAttributesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Mask <em>Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Mask
	 * @generated
	 */
	public Adapter createMaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Masks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Masks
	 * @generated
	 */
	public Adapter createMasksAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.OutlineCode <em>Outline Code</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.OutlineCode
	 * @generated
	 */
	public Adapter createOutlineCodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.OutlineCode2 <em>Outline Code2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.OutlineCode2
	 * @generated
	 */
	public Adapter createOutlineCode2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.OutlineCode3 <em>Outline Code3</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.OutlineCode3
	 * @generated
	 */
	public Adapter createOutlineCode3Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.OutlineCodes <em>Outline Codes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.OutlineCodes
	 * @generated
	 */
	public Adapter createOutlineCodesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.PredecessorLink <em>Predecessor Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.PredecessorLink
	 * @generated
	 */
	public Adapter createPredecessorLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Project
	 * @generated
	 */
	public Adapter createProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Rate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Rate
	 * @generated
	 */
	public Adapter createRateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Rates <em>Rates</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Rates
	 * @generated
	 */
	public Adapter createRatesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Resource
	 * @generated
	 */
	public Adapter createResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Resources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Resources
	 * @generated
	 */
	public Adapter createResourcesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Tasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Tasks
	 * @generated
	 */
	public Adapter createTasksAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.TimePeriod <em>Time Period</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.TimePeriod
	 * @generated
	 */
	public Adapter createTimePeriodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.TimephasedDataType <em>Timephased Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.TimephasedDataType
	 * @generated
	 */
	public Adapter createTimephasedDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Value2 <em>Value2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Value2
	 * @generated
	 */
	public Adapter createValue2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.ValueList <em>Value List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.ValueList
	 * @generated
	 */
	public Adapter createValueListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.Values <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.Values
	 * @generated
	 */
	public Adapter createValuesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.WBSMask <em>WBS Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.WBSMask
	 * @generated
	 */
	public Adapter createWBSMaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.WBSMasks <em>WBS Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.WBSMasks
	 * @generated
	 */
	public Adapter createWBSMasksAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.WeekDay <em>Week Day</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.WeekDay
	 * @generated
	 */
	public Adapter createWeekDayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.WeekDays <em>Week Days</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.WeekDays
	 * @generated
	 */
	public Adapter createWeekDaysAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.WorkingTime <em>Working Time</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.WorkingTime
	 * @generated
	 */
	public Adapter createWorkingTimeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.epf.msproject.WorkingTimes <em>Working Times</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.epf.msproject.WorkingTimes
	 * @generated
	 */
	public Adapter createWorkingTimesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //MsprojectAdapterFactory
