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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.msproject.MsprojectPackage
 * @generated
 */
public class MsprojectSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MsprojectPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsprojectSwitch() {
		if (modelPackage == null) {
			modelPackage = MsprojectPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case MsprojectPackage.ASSIGNMENT: {
				Assignment assignment = (Assignment)theEObject;
				Object result = caseAssignment(assignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.ASSIGNMENTS: {
				Assignments assignments = (Assignments)theEObject;
				Object result = caseAssignments(assignments);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.AVAILABILITY_PERIOD: {
				AvailabilityPeriod availabilityPeriod = (AvailabilityPeriod)theEObject;
				Object result = caseAvailabilityPeriod(availabilityPeriod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.AVAILABILITY_PERIODS: {
				AvailabilityPeriods availabilityPeriods = (AvailabilityPeriods)theEObject;
				Object result = caseAvailabilityPeriods(availabilityPeriods);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.BASELINE: {
				Baseline baseline = (Baseline)theEObject;
				Object result = caseBaseline(baseline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.BASELINE2: {
				Baseline2 baseline2 = (Baseline2)theEObject;
				Object result = caseBaseline2(baseline2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.BASELINE3: {
				Baseline3 baseline3 = (Baseline3)theEObject;
				Object result = caseBaseline3(baseline3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.CALENDAR: {
				Calendar calendar = (Calendar)theEObject;
				Object result = caseCalendar(calendar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.CALENDARS: {
				Calendars calendars = (Calendars)theEObject;
				Object result = caseCalendars(calendars);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				Object result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.EXTENDED_ATTRIBUTE: {
				ExtendedAttribute extendedAttribute = (ExtendedAttribute)theEObject;
				Object result = caseExtendedAttribute(extendedAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.EXTENDED_ATTRIBUTE2: {
				ExtendedAttribute2 extendedAttribute2 = (ExtendedAttribute2)theEObject;
				Object result = caseExtendedAttribute2(extendedAttribute2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.EXTENDED_ATTRIBUTE3: {
				ExtendedAttribute3 extendedAttribute3 = (ExtendedAttribute3)theEObject;
				Object result = caseExtendedAttribute3(extendedAttribute3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.EXTENDED_ATTRIBUTE4: {
				ExtendedAttribute4 extendedAttribute4 = (ExtendedAttribute4)theEObject;
				Object result = caseExtendedAttribute4(extendedAttribute4);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.EXTENDED_ATTRIBUTES: {
				ExtendedAttributes extendedAttributes = (ExtendedAttributes)theEObject;
				Object result = caseExtendedAttributes(extendedAttributes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.MASK: {
				Mask mask = (Mask)theEObject;
				Object result = caseMask(mask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.MASKS: {
				Masks masks = (Masks)theEObject;
				Object result = caseMasks(masks);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.OUTLINE_CODE: {
				OutlineCode outlineCode = (OutlineCode)theEObject;
				Object result = caseOutlineCode(outlineCode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.OUTLINE_CODE2: {
				OutlineCode2 outlineCode2 = (OutlineCode2)theEObject;
				Object result = caseOutlineCode2(outlineCode2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.OUTLINE_CODE3: {
				OutlineCode3 outlineCode3 = (OutlineCode3)theEObject;
				Object result = caseOutlineCode3(outlineCode3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.OUTLINE_CODES: {
				OutlineCodes outlineCodes = (OutlineCodes)theEObject;
				Object result = caseOutlineCodes(outlineCodes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.PREDECESSOR_LINK: {
				PredecessorLink predecessorLink = (PredecessorLink)theEObject;
				Object result = casePredecessorLink(predecessorLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.PROJECT: {
				Project project = (Project)theEObject;
				Object result = caseProject(project);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.RATE: {
				Rate rate = (Rate)theEObject;
				Object result = caseRate(rate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.RATES: {
				Rates rates = (Rates)theEObject;
				Object result = caseRates(rates);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				Object result = caseResource(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.RESOURCES: {
				Resources resources = (Resources)theEObject;
				Object result = caseResources(resources);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.TASK: {
				Task task = (Task)theEObject;
				Object result = caseTask(task);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.TASKS: {
				Tasks tasks = (Tasks)theEObject;
				Object result = caseTasks(tasks);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.TIME_PERIOD: {
				TimePeriod timePeriod = (TimePeriod)theEObject;
				Object result = caseTimePeriod(timePeriod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.TIMEPHASED_DATA_TYPE: {
				TimephasedDataType timephasedDataType = (TimephasedDataType)theEObject;
				Object result = caseTimephasedDataType(timephasedDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.VALUE: {
				Value value = (Value)theEObject;
				Object result = caseValue(value);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.VALUE2: {
				Value2 value2 = (Value2)theEObject;
				Object result = caseValue2(value2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.VALUE_LIST: {
				ValueList valueList = (ValueList)theEObject;
				Object result = caseValueList(valueList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.VALUES: {
				Values values = (Values)theEObject;
				Object result = caseValues(values);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.WBS_MASK: {
				WBSMask wbsMask = (WBSMask)theEObject;
				Object result = caseWBSMask(wbsMask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.WBS_MASKS: {
				WBSMasks wbsMasks = (WBSMasks)theEObject;
				Object result = caseWBSMasks(wbsMasks);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.WEEK_DAY: {
				WeekDay weekDay = (WeekDay)theEObject;
				Object result = caseWeekDay(weekDay);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.WEEK_DAYS: {
				WeekDays weekDays = (WeekDays)theEObject;
				Object result = caseWeekDays(weekDays);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.WORKING_TIME: {
				WorkingTime workingTime = (WorkingTime)theEObject;
				Object result = caseWorkingTime(workingTime);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MsprojectPackage.WORKING_TIMES: {
				WorkingTimes workingTimes = (WorkingTimes)theEObject;
				Object result = caseWorkingTimes(workingTimes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAssignment(Assignment object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Assignments</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Assignments</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAssignments(Assignments object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Availability Period</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Availability Period</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAvailabilityPeriod(AvailabilityPeriod object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Availability Periods</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Availability Periods</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAvailabilityPeriods(AvailabilityPeriods object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Baseline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Baseline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBaseline(Baseline object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Baseline2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Baseline2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBaseline2(Baseline2 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Baseline3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Baseline3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBaseline3(Baseline3 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Calendar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Calendar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCalendar(Calendar object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Calendars</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Calendars</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCalendars(Calendars object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Extended Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Extended Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtendedAttribute(ExtendedAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Extended Attribute2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Extended Attribute2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtendedAttribute2(ExtendedAttribute2 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Extended Attribute3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Extended Attribute3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtendedAttribute3(ExtendedAttribute3 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Extended Attribute4</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Extended Attribute4</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtendedAttribute4(ExtendedAttribute4 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Extended Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Extended Attributes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtendedAttributes(ExtendedAttributes object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Mask</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Mask</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMask(Mask object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Masks</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Masks</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMasks(Masks object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Outline Code</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Outline Code</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseOutlineCode(OutlineCode object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Outline Code2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Outline Code2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseOutlineCode2(OutlineCode2 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Outline Code3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Outline Code3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseOutlineCode3(OutlineCode3 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Outline Codes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Outline Codes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseOutlineCodes(OutlineCodes object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Predecessor Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Predecessor Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePredecessorLink(PredecessorLink object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Project</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Project</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseProject(Project object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Rate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Rate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRate(Rate object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Rates</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Rates</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRates(Rates object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Resources</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Resources</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseResources(Resources object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTask(Task object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Tasks</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Tasks</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTasks(Tasks object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Time Period</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Time Period</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTimePeriod(TimePeriod object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Timephased Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Timephased Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTimephasedDataType(TimephasedDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseValue(Value object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Value2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Value2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseValue2(Value2 object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Value List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Value List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseValueList(ValueList object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Values</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Values</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseValues(Values object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>WBS Mask</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>WBS Mask</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWBSMask(WBSMask object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>WBS Masks</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>WBS Masks</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWBSMasks(WBSMasks object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Week Day</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Week Day</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWeekDay(WeekDay object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Week Days</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Week Days</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWeekDays(WeekDays object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Working Time</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Working Time</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWorkingTime(WorkingTime object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Working Times</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Working Times</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWorkingTimes(WorkingTimes object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //MsprojectSwitch
