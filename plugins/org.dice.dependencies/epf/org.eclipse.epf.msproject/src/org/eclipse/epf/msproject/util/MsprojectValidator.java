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

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
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
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.epf.msproject.MsprojectPackage
 * @generated
 */
public class MsprojectValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final MsprojectValidator INSTANCE = new MsprojectValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.epf.msproject";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsprojectValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EPackage getEPackage() {
	  return MsprojectPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map context) {
		switch (classifierID) {
			case MsprojectPackage.ASSIGNMENT:
				return validateAssignment((Assignment)value, diagnostics, context);
			case MsprojectPackage.ASSIGNMENTS:
				return validateAssignments((Assignments)value, diagnostics, context);
			case MsprojectPackage.AVAILABILITY_PERIOD:
				return validateAvailabilityPeriod((AvailabilityPeriod)value, diagnostics, context);
			case MsprojectPackage.AVAILABILITY_PERIODS:
				return validateAvailabilityPeriods((AvailabilityPeriods)value, diagnostics, context);
			case MsprojectPackage.BASELINE:
				return validateBaseline((Baseline)value, diagnostics, context);
			case MsprojectPackage.BASELINE2:
				return validateBaseline2((Baseline2)value, diagnostics, context);
			case MsprojectPackage.BASELINE3:
				return validateBaseline3((Baseline3)value, diagnostics, context);
			case MsprojectPackage.CALENDAR:
				return validateCalendar((Calendar)value, diagnostics, context);
			case MsprojectPackage.CALENDARS:
				return validateCalendars((Calendars)value, diagnostics, context);
			case MsprojectPackage.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case MsprojectPackage.EXTENDED_ATTRIBUTE:
				return validateExtendedAttribute((ExtendedAttribute)value, diagnostics, context);
			case MsprojectPackage.EXTENDED_ATTRIBUTE2:
				return validateExtendedAttribute2((ExtendedAttribute2)value, diagnostics, context);
			case MsprojectPackage.EXTENDED_ATTRIBUTE3:
				return validateExtendedAttribute3((ExtendedAttribute3)value, diagnostics, context);
			case MsprojectPackage.EXTENDED_ATTRIBUTE4:
				return validateExtendedAttribute4((ExtendedAttribute4)value, diagnostics, context);
			case MsprojectPackage.EXTENDED_ATTRIBUTES:
				return validateExtendedAttributes((ExtendedAttributes)value, diagnostics, context);
			case MsprojectPackage.MASK:
				return validateMask((Mask)value, diagnostics, context);
			case MsprojectPackage.MASKS:
				return validateMasks((Masks)value, diagnostics, context);
			case MsprojectPackage.OUTLINE_CODE:
				return validateOutlineCode((OutlineCode)value, diagnostics, context);
			case MsprojectPackage.OUTLINE_CODE2:
				return validateOutlineCode2((OutlineCode2)value, diagnostics, context);
			case MsprojectPackage.OUTLINE_CODE3:
				return validateOutlineCode3((OutlineCode3)value, diagnostics, context);
			case MsprojectPackage.OUTLINE_CODES:
				return validateOutlineCodes((OutlineCodes)value, diagnostics, context);
			case MsprojectPackage.PREDECESSOR_LINK:
				return validatePredecessorLink((PredecessorLink)value, diagnostics, context);
			case MsprojectPackage.PROJECT:
				return validateProject((Project)value, diagnostics, context);
			case MsprojectPackage.RATE:
				return validateRate((Rate)value, diagnostics, context);
			case MsprojectPackage.RATES:
				return validateRates((Rates)value, diagnostics, context);
			case MsprojectPackage.RESOURCE:
				return validateResource((Resource)value, diagnostics, context);
			case MsprojectPackage.RESOURCES:
				return validateResources((Resources)value, diagnostics, context);
			case MsprojectPackage.TASK:
				return validateTask((Task)value, diagnostics, context);
			case MsprojectPackage.TASKS:
				return validateTasks((Tasks)value, diagnostics, context);
			case MsprojectPackage.TIME_PERIOD:
				return validateTimePeriod((TimePeriod)value, diagnostics, context);
			case MsprojectPackage.TIMEPHASED_DATA_TYPE:
				return validateTimephasedDataType((TimephasedDataType)value, diagnostics, context);
			case MsprojectPackage.VALUE:
				return validateValue((Value)value, diagnostics, context);
			case MsprojectPackage.VALUE2:
				return validateValue2((Value2)value, diagnostics, context);
			case MsprojectPackage.VALUE_LIST:
				return validateValueList((ValueList)value, diagnostics, context);
			case MsprojectPackage.VALUES:
				return validateValues((Values)value, diagnostics, context);
			case MsprojectPackage.WBS_MASK:
				return validateWBSMask((WBSMask)value, diagnostics, context);
			case MsprojectPackage.WBS_MASKS:
				return validateWBSMasks((WBSMasks)value, diagnostics, context);
			case MsprojectPackage.WEEK_DAY:
				return validateWeekDay((WeekDay)value, diagnostics, context);
			case MsprojectPackage.WEEK_DAYS:
				return validateWeekDays((WeekDays)value, diagnostics, context);
			case MsprojectPackage.WORKING_TIME:
				return validateWorkingTime((WorkingTime)value, diagnostics, context);
			case MsprojectPackage.WORKING_TIMES:
				return validateWorkingTimes((WorkingTimes)value, diagnostics, context);
			case MsprojectPackage.ACCRUE_AT:
				return validateAccrueAt((BigInteger)value, diagnostics, context);
			case MsprojectPackage.ACTIVE_DIRECTORY_GUID_TYPE:
				return validateActiveDirectoryGUIDType((String)value, diagnostics, context);
			case MsprojectPackage.ALIAS_TYPE:
				return validateAliasType((String)value, diagnostics, context);
			case MsprojectPackage.AUTHOR_TYPE:
				return validateAuthorType((String)value, diagnostics, context);
			case MsprojectPackage.BASELINE_FOR_EARNED_VALUE_TYPE:
				return validateBaselineForEarnedValueType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.BOOKING_TYPE_TYPE:
				return validateBookingTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.BOOKING_TYPE_TYPE1:
				return validateBookingTypeType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.CALCULATION_TYPE_TYPE:
				return validateCalculationTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.CATEGORY_TYPE:
				return validateCategoryType((String)value, diagnostics, context);
			case MsprojectPackage.CODE_TYPE:
				return validateCodeType((String)value, diagnostics, context);
			case MsprojectPackage.COMPANY_TYPE:
				return validateCompanyType((String)value, diagnostics, context);
			case MsprojectPackage.CONSTRAINT_TYPE_TYPE:
				return validateConstraintTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.CONTACT_TYPE:
				return validateContactType((String)value, diagnostics, context);
			case MsprojectPackage.COST_RATE_TABLE_TYPE:
				return validateCostRateTableType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.CURRENCY_SYMBOL_POSITION_TYPE:
				return validateCurrencySymbolPositionType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.CURRENCY_SYMBOL_TYPE:
				return validateCurrencySymbolType((String)value, diagnostics, context);
			case MsprojectPackage.DAY_TYPE_TYPE:
				return validateDayTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DEFAULT_FIXED_COST_ACCRUAL_TYPE:
				return validateDefaultFixedCostAccrualType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DEFAULT_TASK_EV_METHOD_TYPE:
				return validateDefaultTaskEVMethodType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DEFAULT_TASK_TYPE_TYPE:
				return validateDefaultTaskTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DURATION_FORMAT_TYPE:
				return validateDurationFormatType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DURATION_FORMAT_TYPE1:
				return validateDurationFormatType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DURATION_FORMAT_TYPE2:
				return validateDurationFormatType2((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DURATION_FORMAT_TYPE3:
				return validateDurationFormatType3((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DURATION_FORMAT_TYPE4:
				return validateDurationFormatType4((BigInteger)value, diagnostics, context);
			case MsprojectPackage.DURATION_FORMAT_TYPE5:
				return validateDurationFormatType5((BigInteger)value, diagnostics, context);
			case MsprojectPackage.EARNED_VALUE_METHOD_TYPE:
				return validateEarnedValueMethodType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.EARNED_VALUE_METHOD_TYPE1:
				return validateEarnedValueMethodType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.EMAIL_ADDRESS_TYPE:
				return validateEmailAddressType((String)value, diagnostics, context);
			case MsprojectPackage.EXTERNAL_TASK_PROJECT_TYPE:
				return validateExternalTaskProjectType((String)value, diagnostics, context);
			case MsprojectPackage.FIXED_COST_ACCRUAL_TYPE:
				return validateFixedCostAccrualType((String)value, diagnostics, context);
			case MsprojectPackage.FY_START_DATE_TYPE:
				return validateFYStartDateType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.GROUP_TYPE:
				return validateGroupType((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE:
				return validateHyperlinkAddressType((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE1:
				return validateHyperlinkAddressType1((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE2:
				return validateHyperlinkAddressType2((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE:
				return validateHyperlinkSubAddressType((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE1:
				return validateHyperlinkSubAddressType1((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE2:
				return validateHyperlinkSubAddressType2((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_TYPE:
				return validateHyperlinkType((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_TYPE1:
				return validateHyperlinkType1((String)value, diagnostics, context);
			case MsprojectPackage.HYPERLINK_TYPE2:
				return validateHyperlinkType2((String)value, diagnostics, context);
			case MsprojectPackage.INITIALS_TYPE:
				return validateInitialsType((String)value, diagnostics, context);
			case MsprojectPackage.LAG_FORMAT_TYPE:
				return validateLagFormatType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.LEVELING_DELAY_FORMAT_TYPE:
				return validateLevelingDelayFormatType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.LEVELING_DELAY_FORMAT_TYPE1:
				return validateLevelingDelayFormatType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.MANAGER_TYPE:
				return validateManagerType((String)value, diagnostics, context);
			case MsprojectPackage.MATERIAL_LABEL_TYPE:
				return validateMaterialLabelType((String)value, diagnostics, context);
			case MsprojectPackage.NAME_TYPE:
				return validateNameType((String)value, diagnostics, context);
			case MsprojectPackage.NAME_TYPE1:
				return validateNameType1((String)value, diagnostics, context);
			case MsprojectPackage.NAME_TYPE2:
				return validateNameType2((String)value, diagnostics, context);
			case MsprojectPackage.NAME_TYPE3:
				return validateNameType3((String)value, diagnostics, context);
			case MsprojectPackage.NEW_TASK_START_DATE_TYPE:
				return validateNewTaskStartDateType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.NT_ACCOUNT_TYPE:
				return validateNTAccountType((String)value, diagnostics, context);
			case MsprojectPackage.OUTLINE_NUMBER_TYPE:
				return validateOutlineNumberType((String)value, diagnostics, context);
			case MsprojectPackage.OVERTIME_RATE_FORMAT_TYPE:
				return validateOvertimeRateFormatType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.OVERTIME_RATE_FORMAT_TYPE1:
				return validateOvertimeRateFormatType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.PHONETIC_ALIAS_TYPE:
				return validatePhoneticAliasType((String)value, diagnostics, context);
			case MsprojectPackage.PHONETICS_TYPE:
				return validatePhoneticsType((String)value, diagnostics, context);
			case MsprojectPackage.PREFIX_TYPE:
				return validatePrefixType((String)value, diagnostics, context);
			case MsprojectPackage.RATE_TABLE_TYPE:
				return validateRateTableType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.ROLLUP_TYPE_TYPE:
				return validateRollupTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.STANDARD_RATE_FORMAT_TYPE:
				return validateStandardRateFormatType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.STANDARD_RATE_FORMAT_TYPE1:
				return validateStandardRateFormatType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.SUBJECT_TYPE:
				return validateSubjectType((String)value, diagnostics, context);
			case MsprojectPackage.SUBPROJECT_NAME_TYPE:
				return validateSubprojectNameType((String)value, diagnostics, context);
			case MsprojectPackage.TITLE_TYPE:
				return validateTitleType((String)value, diagnostics, context);
			case MsprojectPackage.TYPE_TYPE:
				return validateTypeType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.TYPE_TYPE1:
				return validateTypeType1((BigInteger)value, diagnostics, context);
			case MsprojectPackage.TYPE_TYPE2:
				return validateTypeType2((BigInteger)value, diagnostics, context);
			case MsprojectPackage.TYPE_TYPE3:
				return validateTypeType3((BigInteger)value, diagnostics, context);
			case MsprojectPackage.TYPE_TYPE4:
				return validateTypeType4((BigInteger)value, diagnostics, context);
			case MsprojectPackage.TYPE_TYPE5:
				return validateTypeType5((BigInteger)value, diagnostics, context);
			case MsprojectPackage.UID_TYPE:
				return validateUIDType((String)value, diagnostics, context);
			case MsprojectPackage.UNIT_TYPE:
				return validateUnitType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.VALUELIST_SORT_ORDER_TYPE:
				return validateValuelistSortOrderType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.WEEK_START_DAY_TYPE:
				return validateWeekStartDayType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.WORK_CONTOUR_TYPE:
				return validateWorkContourType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.WORK_FORMAT_TYPE:
				return validateWorkFormatType((BigInteger)value, diagnostics, context);
			case MsprojectPackage.WORK_GROUP_TYPE:
				return validateWorkGroupType((BigInteger)value, diagnostics, context);
			default: 
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssignment(Assignment assignment, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(assignment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssignments(Assignments assignments, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(assignments, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAvailabilityPeriod(AvailabilityPeriod availabilityPeriod, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(availabilityPeriod, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAvailabilityPeriods(AvailabilityPeriods availabilityPeriods, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(availabilityPeriods, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseline(Baseline baseline, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(baseline, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseline2(Baseline2 baseline2, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(baseline2, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaseline3(Baseline3 baseline3, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(baseline3, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCalendar(Calendar calendar, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(calendar, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCalendars(Calendars calendars, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(calendars, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtendedAttribute(ExtendedAttribute extendedAttribute, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(extendedAttribute, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtendedAttribute2(ExtendedAttribute2 extendedAttribute2, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(extendedAttribute2, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtendedAttribute3(ExtendedAttribute3 extendedAttribute3, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(extendedAttribute3, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtendedAttribute4(ExtendedAttribute4 extendedAttribute4, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(extendedAttribute4, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExtendedAttributes(ExtendedAttributes extendedAttributes, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(extendedAttributes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMask(Mask mask, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(mask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMasks(Masks masks, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(masks, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutlineCode(OutlineCode outlineCode, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(outlineCode, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutlineCode2(OutlineCode2 outlineCode2, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(outlineCode2, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutlineCode3(OutlineCode3 outlineCode3, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(outlineCode3, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutlineCodes(OutlineCodes outlineCodes, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(outlineCodes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePredecessorLink(PredecessorLink predecessorLink, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(predecessorLink, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProject(Project project, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(project, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRate(Rate rate, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(rate, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRates(Rates rates, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(rates, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResource(Resource resource, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(resource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResources(Resources resources, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(resources, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTask(Task task, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(task, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTasks(Tasks tasks, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(tasks, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimePeriod(TimePeriod timePeriod, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(timePeriod, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimephasedDataType(TimephasedDataType timephasedDataType, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(timephasedDataType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValue(Value value, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(value, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValue2(Value2 value2, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(value2, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValueList(ValueList valueList, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(valueList, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValues(Values values, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(values, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWBSMask(WBSMask wbsMask, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(wbsMask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWBSMasks(WBSMasks wbsMasks, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(wbsMasks, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWeekDay(WeekDay weekDay, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(weekDay, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWeekDays(WeekDays weekDays, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(weekDays, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkingTime(WorkingTime workingTime, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(workingTime, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkingTimes(WorkingTimes workingTimes, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint(workingTimes, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAccrueAt(BigInteger accrueAt, DiagnosticChain diagnostics, Map context) {
		boolean result = validateAccrueAt_Enumeration(accrueAt, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateAccrueAt_Enumeration
	 */
	public static final Collection ACCRUE_AT__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Accrue At</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAccrueAt_Enumeration(BigInteger accrueAt, DiagnosticChain diagnostics, Map context) {
		boolean result = ACCRUE_AT__ENUMERATION__VALUES.contains(accrueAt);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getAccrueAt(), accrueAt, ACCRUE_AT__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActiveDirectoryGUIDType(String activeDirectoryGUIDType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateActiveDirectoryGUIDType_MaxLength(activeDirectoryGUIDType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Active Directory GUID Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActiveDirectoryGUIDType_MaxLength(String activeDirectoryGUIDType, DiagnosticChain diagnostics, Map context) {
		int length = activeDirectoryGUIDType.length();  
		boolean result = length <= 16;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getActiveDirectoryGUIDType(), activeDirectoryGUIDType, length, 16, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAliasType(String aliasType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateAliasType_MaxLength(aliasType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Alias Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAliasType_MaxLength(String aliasType, DiagnosticChain diagnostics, Map context) {
		int length = aliasType.length();  
		boolean result = length <= 50;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getAliasType(), aliasType, length, 50, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAuthorType(String authorType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateAuthorType_MaxLength(authorType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Author Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAuthorType_MaxLength(String authorType, DiagnosticChain diagnostics, Map context) {
		int length = authorType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getAuthorType(), authorType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaselineForEarnedValueType(BigInteger baselineForEarnedValueType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateBaselineForEarnedValueType_Enumeration(baselineForEarnedValueType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateBaselineForEarnedValueType_Enumeration
	 */
	public static final Collection BASELINE_FOR_EARNED_VALUE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Baseline For Earned Value Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBaselineForEarnedValueType_Enumeration(BigInteger baselineForEarnedValueType, DiagnosticChain diagnostics, Map context) {
		boolean result = BASELINE_FOR_EARNED_VALUE_TYPE__ENUMERATION__VALUES.contains(baselineForEarnedValueType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getBaselineForEarnedValueType(), baselineForEarnedValueType, BASELINE_FOR_EARNED_VALUE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBookingTypeType(BigInteger bookingTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateBookingTypeType_Enumeration(bookingTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateBookingTypeType_Enumeration
	 */
	public static final Collection BOOKING_TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Booking Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBookingTypeType_Enumeration(BigInteger bookingTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = BOOKING_TYPE_TYPE__ENUMERATION__VALUES.contains(bookingTypeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getBookingTypeType(), bookingTypeType, BOOKING_TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBookingTypeType1(BigInteger bookingTypeType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateBookingTypeType1_Enumeration(bookingTypeType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateBookingTypeType1_Enumeration
	 */
	public static final Collection BOOKING_TYPE_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Booking Type Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBookingTypeType1_Enumeration(BigInteger bookingTypeType1, DiagnosticChain diagnostics, Map context) {
		boolean result = BOOKING_TYPE_TYPE1__ENUMERATION__VALUES.contains(bookingTypeType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getBookingTypeType1(), bookingTypeType1, BOOKING_TYPE_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCalculationTypeType(BigInteger calculationTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCalculationTypeType_Enumeration(calculationTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateCalculationTypeType_Enumeration
	 */
	public static final Collection CALCULATION_TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Calculation Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCalculationTypeType_Enumeration(BigInteger calculationTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = CALCULATION_TYPE_TYPE__ENUMERATION__VALUES.contains(calculationTypeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getCalculationTypeType(), calculationTypeType, CALCULATION_TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategoryType(String categoryType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCategoryType_MaxLength(categoryType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Category Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCategoryType_MaxLength(String categoryType, DiagnosticChain diagnostics, Map context) {
		int length = categoryType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getCategoryType(), categoryType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCodeType(String codeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCodeType_MaxLength(codeType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Code Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCodeType_MaxLength(String codeType, DiagnosticChain diagnostics, Map context) {
		int length = codeType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getCodeType(), codeType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompanyType(String companyType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCompanyType_MaxLength(companyType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Company Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompanyType_MaxLength(String companyType, DiagnosticChain diagnostics, Map context) {
		int length = companyType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getCompanyType(), companyType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintTypeType(BigInteger constraintTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateConstraintTypeType_Enumeration(constraintTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateConstraintTypeType_Enumeration
	 */
	public static final Collection CONSTRAINT_TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Constraint Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstraintTypeType_Enumeration(BigInteger constraintTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = CONSTRAINT_TYPE_TYPE__ENUMERATION__VALUES.contains(constraintTypeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getConstraintTypeType(), constraintTypeType, CONSTRAINT_TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContactType(String contactType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateContactType_MaxLength(contactType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Contact Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContactType_MaxLength(String contactType, DiagnosticChain diagnostics, Map context) {
		int length = contactType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getContactType(), contactType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCostRateTableType(BigInteger costRateTableType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCostRateTableType_Enumeration(costRateTableType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateCostRateTableType_Enumeration
	 */
	public static final Collection COST_RATE_TABLE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Cost Rate Table Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCostRateTableType_Enumeration(BigInteger costRateTableType, DiagnosticChain diagnostics, Map context) {
		boolean result = COST_RATE_TABLE_TYPE__ENUMERATION__VALUES.contains(costRateTableType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getCostRateTableType(), costRateTableType, COST_RATE_TABLE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCurrencySymbolPositionType(BigInteger currencySymbolPositionType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCurrencySymbolPositionType_Enumeration(currencySymbolPositionType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateCurrencySymbolPositionType_Enumeration
	 */
	public static final Collection CURRENCY_SYMBOL_POSITION_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Currency Symbol Position Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCurrencySymbolPositionType_Enumeration(BigInteger currencySymbolPositionType, DiagnosticChain diagnostics, Map context) {
		boolean result = CURRENCY_SYMBOL_POSITION_TYPE__ENUMERATION__VALUES.contains(currencySymbolPositionType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getCurrencySymbolPositionType(), currencySymbolPositionType, CURRENCY_SYMBOL_POSITION_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCurrencySymbolType(String currencySymbolType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateCurrencySymbolType_MaxLength(currencySymbolType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Currency Symbol Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCurrencySymbolType_MaxLength(String currencySymbolType, DiagnosticChain diagnostics, Map context) {
		int length = currencySymbolType.length();  
		boolean result = length <= 20;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getCurrencySymbolType(), currencySymbolType, length, 20, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDayTypeType(BigInteger dayTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDayTypeType_Enumeration(dayTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDayTypeType_Enumeration
	 */
	public static final Collection DAY_TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Day Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDayTypeType_Enumeration(BigInteger dayTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = DAY_TYPE_TYPE__ENUMERATION__VALUES.contains(dayTypeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDayTypeType(), dayTypeType, DAY_TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultFixedCostAccrualType(BigInteger defaultFixedCostAccrualType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDefaultFixedCostAccrualType_Enumeration(defaultFixedCostAccrualType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDefaultFixedCostAccrualType_Enumeration
	 */
	public static final Collection DEFAULT_FIXED_COST_ACCRUAL_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Default Fixed Cost Accrual Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultFixedCostAccrualType_Enumeration(BigInteger defaultFixedCostAccrualType, DiagnosticChain diagnostics, Map context) {
		boolean result = DEFAULT_FIXED_COST_ACCRUAL_TYPE__ENUMERATION__VALUES.contains(defaultFixedCostAccrualType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDefaultFixedCostAccrualType(), defaultFixedCostAccrualType, DEFAULT_FIXED_COST_ACCRUAL_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultTaskEVMethodType(BigInteger defaultTaskEVMethodType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDefaultTaskEVMethodType_Enumeration(defaultTaskEVMethodType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDefaultTaskEVMethodType_Enumeration
	 */
	public static final Collection DEFAULT_TASK_EV_METHOD_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Default Task EV Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultTaskEVMethodType_Enumeration(BigInteger defaultTaskEVMethodType, DiagnosticChain diagnostics, Map context) {
		boolean result = DEFAULT_TASK_EV_METHOD_TYPE__ENUMERATION__VALUES.contains(defaultTaskEVMethodType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDefaultTaskEVMethodType(), defaultTaskEVMethodType, DEFAULT_TASK_EV_METHOD_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultTaskTypeType(BigInteger defaultTaskTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDefaultTaskTypeType_Enumeration(defaultTaskTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDefaultTaskTypeType_Enumeration
	 */
	public static final Collection DEFAULT_TASK_TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Default Task Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultTaskTypeType_Enumeration(BigInteger defaultTaskTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = DEFAULT_TASK_TYPE_TYPE__ENUMERATION__VALUES.contains(defaultTaskTypeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDefaultTaskTypeType(), defaultTaskTypeType, DEFAULT_TASK_TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType(BigInteger durationFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDurationFormatType_Enumeration(durationFormatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDurationFormatType_Enumeration
	 */
	public static final Collection DURATION_FORMAT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Duration Format Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType_Enumeration(BigInteger durationFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = DURATION_FORMAT_TYPE__ENUMERATION__VALUES.contains(durationFormatType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDurationFormatType(), durationFormatType, DURATION_FORMAT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType1(BigInteger durationFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDurationFormatType1_Enumeration(durationFormatType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDurationFormatType1_Enumeration
	 */
	public static final Collection DURATION_FORMAT_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Duration Format Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType1_Enumeration(BigInteger durationFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = DURATION_FORMAT_TYPE1__ENUMERATION__VALUES.contains(durationFormatType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDurationFormatType1(), durationFormatType1, DURATION_FORMAT_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType2(BigInteger durationFormatType2, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDurationFormatType2_Enumeration(durationFormatType2, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDurationFormatType2_Enumeration
	 */
	public static final Collection DURATION_FORMAT_TYPE2__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Duration Format Type2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType2_Enumeration(BigInteger durationFormatType2, DiagnosticChain diagnostics, Map context) {
		boolean result = DURATION_FORMAT_TYPE2__ENUMERATION__VALUES.contains(durationFormatType2);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDurationFormatType2(), durationFormatType2, DURATION_FORMAT_TYPE2__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType3(BigInteger durationFormatType3, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDurationFormatType3_Enumeration(durationFormatType3, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDurationFormatType3_Enumeration
	 */
	public static final Collection DURATION_FORMAT_TYPE3__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Duration Format Type3</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType3_Enumeration(BigInteger durationFormatType3, DiagnosticChain diagnostics, Map context) {
		boolean result = DURATION_FORMAT_TYPE3__ENUMERATION__VALUES.contains(durationFormatType3);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDurationFormatType3(), durationFormatType3, DURATION_FORMAT_TYPE3__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType4(BigInteger durationFormatType4, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDurationFormatType4_Enumeration(durationFormatType4, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDurationFormatType4_Enumeration
	 */
	public static final Collection DURATION_FORMAT_TYPE4__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Duration Format Type4</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType4_Enumeration(BigInteger durationFormatType4, DiagnosticChain diagnostics, Map context) {
		boolean result = DURATION_FORMAT_TYPE4__ENUMERATION__VALUES.contains(durationFormatType4);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDurationFormatType4(), durationFormatType4, DURATION_FORMAT_TYPE4__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType5(BigInteger durationFormatType5, DiagnosticChain diagnostics, Map context) {
		boolean result = validateDurationFormatType5_Enumeration(durationFormatType5, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDurationFormatType5_Enumeration
	 */
	public static final Collection DURATION_FORMAT_TYPE5__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Duration Format Type5</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDurationFormatType5_Enumeration(BigInteger durationFormatType5, DiagnosticChain diagnostics, Map context) {
		boolean result = DURATION_FORMAT_TYPE5__ENUMERATION__VALUES.contains(durationFormatType5);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getDurationFormatType5(), durationFormatType5, DURATION_FORMAT_TYPE5__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEarnedValueMethodType(BigInteger earnedValueMethodType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateEarnedValueMethodType_Enumeration(earnedValueMethodType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateEarnedValueMethodType_Enumeration
	 */
	public static final Collection EARNED_VALUE_METHOD_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Earned Value Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEarnedValueMethodType_Enumeration(BigInteger earnedValueMethodType, DiagnosticChain diagnostics, Map context) {
		boolean result = EARNED_VALUE_METHOD_TYPE__ENUMERATION__VALUES.contains(earnedValueMethodType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getEarnedValueMethodType(), earnedValueMethodType, EARNED_VALUE_METHOD_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEarnedValueMethodType1(BigInteger earnedValueMethodType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateEarnedValueMethodType1_Enumeration(earnedValueMethodType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateEarnedValueMethodType1_Enumeration
	 */
	public static final Collection EARNED_VALUE_METHOD_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Earned Value Method Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEarnedValueMethodType1_Enumeration(BigInteger earnedValueMethodType1, DiagnosticChain diagnostics, Map context) {
		boolean result = EARNED_VALUE_METHOD_TYPE1__ENUMERATION__VALUES.contains(earnedValueMethodType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getEarnedValueMethodType1(), earnedValueMethodType1, EARNED_VALUE_METHOD_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmailAddressType(String emailAddressType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateEmailAddressType_MaxLength(emailAddressType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Email Address Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmailAddressType_MaxLength(String emailAddressType, DiagnosticChain diagnostics, Map context) {
		int length = emailAddressType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getEmailAddressType(), emailAddressType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExternalTaskProjectType(String externalTaskProjectType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateExternalTaskProjectType_MaxLength(externalTaskProjectType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>External Task Project Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExternalTaskProjectType_MaxLength(String externalTaskProjectType, DiagnosticChain diagnostics, Map context) {
		int length = externalTaskProjectType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getExternalTaskProjectType(), externalTaskProjectType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFixedCostAccrualType(String fixedCostAccrualType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateFixedCostAccrualType_Enumeration(fixedCostAccrualType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateFixedCostAccrualType_Enumeration
	 */
	public static final Collection FIXED_COST_ACCRUAL_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 "1",
				 "2",
				 "3"
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Fixed Cost Accrual Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFixedCostAccrualType_Enumeration(String fixedCostAccrualType, DiagnosticChain diagnostics, Map context) {
		boolean result = FIXED_COST_ACCRUAL_TYPE__ENUMERATION__VALUES.contains(fixedCostAccrualType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getFixedCostAccrualType(), fixedCostAccrualType, FIXED_COST_ACCRUAL_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFYStartDateType(BigInteger fyStartDateType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateFYStartDateType_Enumeration(fyStartDateType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateFYStartDateType_Enumeration
	 */
	public static final Collection FY_START_DATE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>FY Start Date Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFYStartDateType_Enumeration(BigInteger fyStartDateType, DiagnosticChain diagnostics, Map context) {
		boolean result = FY_START_DATE_TYPE__ENUMERATION__VALUES.contains(fyStartDateType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getFYStartDateType(), fyStartDateType, FY_START_DATE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGroupType(String groupType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateGroupType_MaxLength(groupType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Group Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGroupType_MaxLength(String groupType, DiagnosticChain diagnostics, Map context) {
		int length = groupType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getGroupType(), groupType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkAddressType(String hyperlinkAddressType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkAddressType_MaxLength(hyperlinkAddressType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Address Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkAddressType_MaxLength(String hyperlinkAddressType, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkAddressType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkAddressType(), hyperlinkAddressType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkAddressType1(String hyperlinkAddressType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkAddressType1_MaxLength(hyperlinkAddressType1, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Address Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkAddressType1_MaxLength(String hyperlinkAddressType1, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkAddressType1.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkAddressType1(), hyperlinkAddressType1, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkAddressType2(String hyperlinkAddressType2, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkAddressType2_MaxLength(hyperlinkAddressType2, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Address Type2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkAddressType2_MaxLength(String hyperlinkAddressType2, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkAddressType2.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkAddressType2(), hyperlinkAddressType2, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkSubAddressType(String hyperlinkSubAddressType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkSubAddressType_MaxLength(hyperlinkSubAddressType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Sub Address Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkSubAddressType_MaxLength(String hyperlinkSubAddressType, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkSubAddressType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkSubAddressType(), hyperlinkSubAddressType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkSubAddressType1(String hyperlinkSubAddressType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkSubAddressType1_MaxLength(hyperlinkSubAddressType1, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Sub Address Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkSubAddressType1_MaxLength(String hyperlinkSubAddressType1, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkSubAddressType1.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkSubAddressType1(), hyperlinkSubAddressType1, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkSubAddressType2(String hyperlinkSubAddressType2, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkSubAddressType2_MaxLength(hyperlinkSubAddressType2, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Sub Address Type2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkSubAddressType2_MaxLength(String hyperlinkSubAddressType2, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkSubAddressType2.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkSubAddressType2(), hyperlinkSubAddressType2, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkType(String hyperlinkType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkType_MaxLength(hyperlinkType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkType_MaxLength(String hyperlinkType, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkType(), hyperlinkType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkType1(String hyperlinkType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkType1_MaxLength(hyperlinkType1, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkType1_MaxLength(String hyperlinkType1, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkType1.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkType1(), hyperlinkType1, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkType2(String hyperlinkType2, DiagnosticChain diagnostics, Map context) {
		boolean result = validateHyperlinkType2_MaxLength(hyperlinkType2, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Hyperlink Type2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHyperlinkType2_MaxLength(String hyperlinkType2, DiagnosticChain diagnostics, Map context) {
		int length = hyperlinkType2.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getHyperlinkType2(), hyperlinkType2, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInitialsType(String initialsType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateInitialsType_MaxLength(initialsType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Initials Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInitialsType_MaxLength(String initialsType, DiagnosticChain diagnostics, Map context) {
		int length = initialsType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getInitialsType(), initialsType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLagFormatType(BigInteger lagFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateLagFormatType_Enumeration(lagFormatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateLagFormatType_Enumeration
	 */
	public static final Collection LAG_FORMAT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Lag Format Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLagFormatType_Enumeration(BigInteger lagFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = LAG_FORMAT_TYPE__ENUMERATION__VALUES.contains(lagFormatType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getLagFormatType(), lagFormatType, LAG_FORMAT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLevelingDelayFormatType(BigInteger levelingDelayFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateLevelingDelayFormatType_Enumeration(levelingDelayFormatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateLevelingDelayFormatType_Enumeration
	 */
	public static final Collection LEVELING_DELAY_FORMAT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Leveling Delay Format Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLevelingDelayFormatType_Enumeration(BigInteger levelingDelayFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = LEVELING_DELAY_FORMAT_TYPE__ENUMERATION__VALUES.contains(levelingDelayFormatType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getLevelingDelayFormatType(), levelingDelayFormatType, LEVELING_DELAY_FORMAT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLevelingDelayFormatType1(BigInteger levelingDelayFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateLevelingDelayFormatType1_Enumeration(levelingDelayFormatType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateLevelingDelayFormatType1_Enumeration
	 */
	public static final Collection LEVELING_DELAY_FORMAT_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("12"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Leveling Delay Format Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLevelingDelayFormatType1_Enumeration(BigInteger levelingDelayFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = LEVELING_DELAY_FORMAT_TYPE1__ENUMERATION__VALUES.contains(levelingDelayFormatType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getLevelingDelayFormatType1(), levelingDelayFormatType1, LEVELING_DELAY_FORMAT_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateManagerType(String managerType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateManagerType_MaxLength(managerType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Manager Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateManagerType_MaxLength(String managerType, DiagnosticChain diagnostics, Map context) {
		int length = managerType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getManagerType(), managerType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialLabelType(String materialLabelType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateMaterialLabelType_MaxLength(materialLabelType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Material Label Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMaterialLabelType_MaxLength(String materialLabelType, DiagnosticChain diagnostics, Map context) {
		int length = materialLabelType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getMaterialLabelType(), materialLabelType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType(String nameType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateNameType_MaxLength(nameType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType_MaxLength(String nameType, DiagnosticChain diagnostics, Map context) {
		int length = nameType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getNameType(), nameType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType1(String nameType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateNameType1_MaxLength(nameType1, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Name Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType1_MaxLength(String nameType1, DiagnosticChain diagnostics, Map context) {
		int length = nameType1.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getNameType1(), nameType1, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType2(String nameType2, DiagnosticChain diagnostics, Map context) {
		boolean result = validateNameType2_MaxLength(nameType2, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Name Type2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType2_MaxLength(String nameType2, DiagnosticChain diagnostics, Map context) {
		int length = nameType2.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getNameType2(), nameType2, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType3(String nameType3, DiagnosticChain diagnostics, Map context) {
		boolean result = validateNameType3_MaxLength(nameType3, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Name Type3</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameType3_MaxLength(String nameType3, DiagnosticChain diagnostics, Map context) {
		int length = nameType3.length();  
		boolean result = length <= 255;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getNameType3(), nameType3, length, 255, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNewTaskStartDateType(BigInteger newTaskStartDateType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateNewTaskStartDateType_Enumeration(newTaskStartDateType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateNewTaskStartDateType_Enumeration
	 */
	public static final Collection NEW_TASK_START_DATE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>New Task Start Date Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNewTaskStartDateType_Enumeration(BigInteger newTaskStartDateType, DiagnosticChain diagnostics, Map context) {
		boolean result = NEW_TASK_START_DATE_TYPE__ENUMERATION__VALUES.contains(newTaskStartDateType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getNewTaskStartDateType(), newTaskStartDateType, NEW_TASK_START_DATE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNTAccountType(String ntAccountType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateNTAccountType_MaxLength(ntAccountType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>NT Account Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNTAccountType_MaxLength(String ntAccountType, DiagnosticChain diagnostics, Map context) {
		int length = ntAccountType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getNTAccountType(), ntAccountType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutlineNumberType(String outlineNumberType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateOutlineNumberType_MaxLength(outlineNumberType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Outline Number Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOutlineNumberType_MaxLength(String outlineNumberType, DiagnosticChain diagnostics, Map context) {
		int length = outlineNumberType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getOutlineNumberType(), outlineNumberType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOvertimeRateFormatType(BigInteger overtimeRateFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateOvertimeRateFormatType_Enumeration(overtimeRateFormatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateOvertimeRateFormatType_Enumeration
	 */
	public static final Collection OVERTIME_RATE_FORMAT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("7")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Overtime Rate Format Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOvertimeRateFormatType_Enumeration(BigInteger overtimeRateFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = OVERTIME_RATE_FORMAT_TYPE__ENUMERATION__VALUES.contains(overtimeRateFormatType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getOvertimeRateFormatType(), overtimeRateFormatType, OVERTIME_RATE_FORMAT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOvertimeRateFormatType1(BigInteger overtimeRateFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateOvertimeRateFormatType1_Enumeration(overtimeRateFormatType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateOvertimeRateFormatType1_Enumeration
	 */
	public static final Collection OVERTIME_RATE_FORMAT_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("7")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Overtime Rate Format Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOvertimeRateFormatType1_Enumeration(BigInteger overtimeRateFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = OVERTIME_RATE_FORMAT_TYPE1__ENUMERATION__VALUES.contains(overtimeRateFormatType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getOvertimeRateFormatType1(), overtimeRateFormatType1, OVERTIME_RATE_FORMAT_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhoneticAliasType(String phoneticAliasType, DiagnosticChain diagnostics, Map context) {
		boolean result = validatePhoneticAliasType_MaxLength(phoneticAliasType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Phonetic Alias Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhoneticAliasType_MaxLength(String phoneticAliasType, DiagnosticChain diagnostics, Map context) {
		int length = phoneticAliasType.length();  
		boolean result = length <= 50;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getPhoneticAliasType(), phoneticAliasType, length, 50, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhoneticsType(String phoneticsType, DiagnosticChain diagnostics, Map context) {
		boolean result = validatePhoneticsType_MaxLength(phoneticsType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Phonetics Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhoneticsType_MaxLength(String phoneticsType, DiagnosticChain diagnostics, Map context) {
		int length = phoneticsType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getPhoneticsType(), phoneticsType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrefixType(String prefixType, DiagnosticChain diagnostics, Map context) {
		boolean result = validatePrefixType_MaxLength(prefixType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Prefix Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrefixType_MaxLength(String prefixType, DiagnosticChain diagnostics, Map context) {
		int length = prefixType.length();  
		boolean result = length <= 50;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getPrefixType(), prefixType, length, 50, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRateTableType(BigInteger rateTableType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateRateTableType_Enumeration(rateTableType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateRateTableType_Enumeration
	 */
	public static final Collection RATE_TABLE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Rate Table Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRateTableType_Enumeration(BigInteger rateTableType, DiagnosticChain diagnostics, Map context) {
		boolean result = RATE_TABLE_TYPE__ENUMERATION__VALUES.contains(rateTableType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getRateTableType(), rateTableType, RATE_TABLE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRollupTypeType(BigInteger rollupTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateRollupTypeType_Enumeration(rollupTypeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateRollupTypeType_Enumeration
	 */
	public static final Collection ROLLUP_TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Rollup Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRollupTypeType_Enumeration(BigInteger rollupTypeType, DiagnosticChain diagnostics, Map context) {
		boolean result = ROLLUP_TYPE_TYPE__ENUMERATION__VALUES.contains(rollupTypeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getRollupTypeType(), rollupTypeType, ROLLUP_TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardRateFormatType(BigInteger standardRateFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateStandardRateFormatType_Enumeration(standardRateFormatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateStandardRateFormatType_Enumeration
	 */
	public static final Collection STANDARD_RATE_FORMAT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("7")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Standard Rate Format Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardRateFormatType_Enumeration(BigInteger standardRateFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = STANDARD_RATE_FORMAT_TYPE__ENUMERATION__VALUES.contains(standardRateFormatType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getStandardRateFormatType(), standardRateFormatType, STANDARD_RATE_FORMAT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardRateFormatType1(BigInteger standardRateFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateStandardRateFormatType1_Enumeration(standardRateFormatType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateStandardRateFormatType1_Enumeration
	 */
	public static final Collection STANDARD_RATE_FORMAT_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("7"),
				 new BigInteger("8")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Standard Rate Format Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStandardRateFormatType1_Enumeration(BigInteger standardRateFormatType1, DiagnosticChain diagnostics, Map context) {
		boolean result = STANDARD_RATE_FORMAT_TYPE1__ENUMERATION__VALUES.contains(standardRateFormatType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getStandardRateFormatType1(), standardRateFormatType1, STANDARD_RATE_FORMAT_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubjectType(String subjectType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateSubjectType_MaxLength(subjectType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Subject Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubjectType_MaxLength(String subjectType, DiagnosticChain diagnostics, Map context) {
		int length = subjectType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getSubjectType(), subjectType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubprojectNameType(String subprojectNameType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateSubprojectNameType_MaxLength(subprojectNameType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Subproject Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubprojectNameType_MaxLength(String subprojectNameType, DiagnosticChain diagnostics, Map context) {
		int length = subprojectNameType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getSubprojectNameType(), subprojectNameType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTitleType(String titleType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTitleType_MaxLength(titleType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>Title Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTitleType_MaxLength(String titleType, DiagnosticChain diagnostics, Map context) {
		int length = titleType.length();  
		boolean result = length <= 512;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getTitleType(), titleType, length, 512, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType(BigInteger typeType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTypeType_Enumeration(typeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTypeType_Enumeration
	 */
	public static final Collection TYPE_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType_Enumeration(BigInteger typeType, DiagnosticChain diagnostics, Map context) {
		boolean result = TYPE_TYPE__ENUMERATION__VALUES.contains(typeType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getTypeType(), typeType, TYPE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType1(BigInteger typeType1, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTypeType1_Enumeration(typeType1, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTypeType1_Enumeration
	 */
	public static final Collection TYPE_TYPE1__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Type Type1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType1_Enumeration(BigInteger typeType1, DiagnosticChain diagnostics, Map context) {
		boolean result = TYPE_TYPE1__ENUMERATION__VALUES.contains(typeType1);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getTypeType1(), typeType1, TYPE_TYPE1__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType2(BigInteger typeType2, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTypeType2_Enumeration(typeType2, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTypeType2_Enumeration
	 */
	public static final Collection TYPE_TYPE2__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Type Type2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType2_Enumeration(BigInteger typeType2, DiagnosticChain diagnostics, Map context) {
		boolean result = TYPE_TYPE2__ENUMERATION__VALUES.contains(typeType2);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getTypeType2(), typeType2, TYPE_TYPE2__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType3(BigInteger typeType3, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTypeType3_Enumeration(typeType3, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTypeType3_Enumeration
	 */
	public static final Collection TYPE_TYPE3__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8"),
				 new BigInteger("9"),
				 new BigInteger("10"),
				 new BigInteger("11"),
				 new BigInteger("16"),
				 new BigInteger("17"),
				 new BigInteger("18"),
				 new BigInteger("19"),
				 new BigInteger("20"),
				 new BigInteger("21"),
				 new BigInteger("22"),
				 new BigInteger("23"),
				 new BigInteger("24"),
				 new BigInteger("25"),
				 new BigInteger("26"),
				 new BigInteger("27"),
				 new BigInteger("28"),
				 new BigInteger("29"),
				 new BigInteger("30"),
				 new BigInteger("31"),
				 new BigInteger("32"),
				 new BigInteger("33"),
				 new BigInteger("34"),
				 new BigInteger("35"),
				 new BigInteger("36"),
				 new BigInteger("37"),
				 new BigInteger("38"),
				 new BigInteger("39"),
				 new BigInteger("40"),
				 new BigInteger("41"),
				 new BigInteger("42"),
				 new BigInteger("43"),
				 new BigInteger("44"),
				 new BigInteger("45"),
				 new BigInteger("46"),
				 new BigInteger("47"),
				 new BigInteger("48"),
				 new BigInteger("49"),
				 new BigInteger("50"),
				 new BigInteger("51"),
				 new BigInteger("52"),
				 new BigInteger("53"),
				 new BigInteger("54"),
				 new BigInteger("55"),
				 new BigInteger("56"),
				 new BigInteger("57"),
				 new BigInteger("58"),
				 new BigInteger("59"),
				 new BigInteger("60"),
				 new BigInteger("61"),
				 new BigInteger("62"),
				 new BigInteger("63"),
				 new BigInteger("64"),
				 new BigInteger("65"),
				 new BigInteger("66"),
				 new BigInteger("67"),
				 new BigInteger("68"),
				 new BigInteger("69"),
				 new BigInteger("70"),
				 new BigInteger("71"),
				 new BigInteger("72"),
				 new BigInteger("73"),
				 new BigInteger("74"),
				 new BigInteger("75"),
				 new BigInteger("76")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Type Type3</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType3_Enumeration(BigInteger typeType3, DiagnosticChain diagnostics, Map context) {
		boolean result = TYPE_TYPE3__ENUMERATION__VALUES.contains(typeType3);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getTypeType3(), typeType3, TYPE_TYPE3__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType4(BigInteger typeType4, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTypeType4_Enumeration(typeType4, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTypeType4_Enumeration
	 */
	public static final Collection TYPE_TYPE4__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Type Type4</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType4_Enumeration(BigInteger typeType4, DiagnosticChain diagnostics, Map context) {
		boolean result = TYPE_TYPE4__ENUMERATION__VALUES.contains(typeType4);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getTypeType4(), typeType4, TYPE_TYPE4__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType5(BigInteger typeType5, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTypeType5_Enumeration(typeType5, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTypeType5_Enumeration
	 */
	public static final Collection TYPE_TYPE5__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Type Type5</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType5_Enumeration(BigInteger typeType5, DiagnosticChain diagnostics, Map context) {
		boolean result = TYPE_TYPE5__ENUMERATION__VALUES.contains(typeType5);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getTypeType5(), typeType5, TYPE_TYPE5__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUIDType(String uidType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateUIDType_MaxLength(uidType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MaxLength constraint of '<em>UID Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUIDType_MaxLength(String uidType, DiagnosticChain diagnostics, Map context) {
		int length = uidType.length();  
		boolean result = length <= 16;
		if (!result && diagnostics != null) 
			reportMaxLengthViolation(MsprojectPackage.eINSTANCE.getUIDType(), uidType, length, 16, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnitType(BigInteger unitType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateUnitType_Enumeration(unitType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateUnitType_Enumeration
	 */
	public static final Collection UNIT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("5"),
				 new BigInteger("8")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Unit Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnitType_Enumeration(BigInteger unitType, DiagnosticChain diagnostics, Map context) {
		boolean result = UNIT_TYPE__ENUMERATION__VALUES.contains(unitType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getUnitType(), unitType, UNIT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValuelistSortOrderType(BigInteger valuelistSortOrderType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateValuelistSortOrderType_Enumeration(valuelistSortOrderType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateValuelistSortOrderType_Enumeration
	 */
	public static final Collection VALUELIST_SORT_ORDER_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Valuelist Sort Order Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateValuelistSortOrderType_Enumeration(BigInteger valuelistSortOrderType, DiagnosticChain diagnostics, Map context) {
		boolean result = VALUELIST_SORT_ORDER_TYPE__ENUMERATION__VALUES.contains(valuelistSortOrderType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getValuelistSortOrderType(), valuelistSortOrderType, VALUELIST_SORT_ORDER_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWeekStartDayType(BigInteger weekStartDayType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateWeekStartDayType_Enumeration(weekStartDayType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateWeekStartDayType_Enumeration
	 */
	public static final Collection WEEK_START_DAY_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Week Start Day Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWeekStartDayType_Enumeration(BigInteger weekStartDayType, DiagnosticChain diagnostics, Map context) {
		boolean result = WEEK_START_DAY_TYPE__ENUMERATION__VALUES.contains(weekStartDayType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getWeekStartDayType(), weekStartDayType, WEEK_START_DAY_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkContourType(BigInteger workContourType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateWorkContourType_Enumeration(workContourType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateWorkContourType_Enumeration
	 */
	public static final Collection WORK_CONTOUR_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5"),
				 new BigInteger("6"),
				 new BigInteger("7"),
				 new BigInteger("8")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Work Contour Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkContourType_Enumeration(BigInteger workContourType, DiagnosticChain diagnostics, Map context) {
		boolean result = WORK_CONTOUR_TYPE__ENUMERATION__VALUES.contains(workContourType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getWorkContourType(), workContourType, WORK_CONTOUR_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkFormatType(BigInteger workFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateWorkFormatType_Enumeration(workFormatType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateWorkFormatType_Enumeration
	 */
	public static final Collection WORK_FORMAT_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3"),
				 new BigInteger("4"),
				 new BigInteger("5")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Work Format Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkFormatType_Enumeration(BigInteger workFormatType, DiagnosticChain diagnostics, Map context) {
		boolean result = WORK_FORMAT_TYPE__ENUMERATION__VALUES.contains(workFormatType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getWorkFormatType(), workFormatType, WORK_FORMAT_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkGroupType(BigInteger workGroupType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateWorkGroupType_Enumeration(workGroupType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateWorkGroupType_Enumeration
	 */
	public static final Collection WORK_GROUP_TYPE__ENUMERATION__VALUES =
		wrapEnumerationValues
			(new Object[] {
				 new BigInteger("0"),
				 new BigInteger("1"),
				 new BigInteger("2"),
				 new BigInteger("3")
			 });

	/**
	 * Validates the Enumeration constraint of '<em>Work Group Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkGroupType_Enumeration(BigInteger workGroupType, DiagnosticChain diagnostics, Map context) {
		boolean result = WORK_GROUP_TYPE__ENUMERATION__VALUES.contains(workGroupType);
		if (!result && diagnostics != null) 
			reportEnumerationViolation(MsprojectPackage.eINSTANCE.getWorkGroupType(), workGroupType, WORK_GROUP_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result; 
	}

} //MsprojectValidator
