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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
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
import org.eclipse.epf.msproject.MsprojectFactory;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MsprojectFactoryImpl extends EFactoryImpl implements MsprojectFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsprojectFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MsprojectPackage.ASSIGNMENT: return createAssignment();
			case MsprojectPackage.ASSIGNMENTS: return createAssignments();
			case MsprojectPackage.AVAILABILITY_PERIOD: return createAvailabilityPeriod();
			case MsprojectPackage.AVAILABILITY_PERIODS: return createAvailabilityPeriods();
			case MsprojectPackage.BASELINE: return createBaseline();
			case MsprojectPackage.BASELINE2: return createBaseline2();
			case MsprojectPackage.BASELINE3: return createBaseline3();
			case MsprojectPackage.CALENDAR: return createCalendar();
			case MsprojectPackage.CALENDARS: return createCalendars();
			case MsprojectPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case MsprojectPackage.EXTENDED_ATTRIBUTE: return createExtendedAttribute();
			case MsprojectPackage.EXTENDED_ATTRIBUTE2: return createExtendedAttribute2();
			case MsprojectPackage.EXTENDED_ATTRIBUTE3: return createExtendedAttribute3();
			case MsprojectPackage.EXTENDED_ATTRIBUTE4: return createExtendedAttribute4();
			case MsprojectPackage.EXTENDED_ATTRIBUTES: return createExtendedAttributes();
			case MsprojectPackage.MASK: return createMask();
			case MsprojectPackage.MASKS: return createMasks();
			case MsprojectPackage.OUTLINE_CODE: return createOutlineCode();
			case MsprojectPackage.OUTLINE_CODE2: return createOutlineCode2();
			case MsprojectPackage.OUTLINE_CODE3: return createOutlineCode3();
			case MsprojectPackage.OUTLINE_CODES: return createOutlineCodes();
			case MsprojectPackage.PREDECESSOR_LINK: return createPredecessorLink();
			case MsprojectPackage.PROJECT: return createProject();
			case MsprojectPackage.RATE: return createRate();
			case MsprojectPackage.RATES: return createRates();
			case MsprojectPackage.RESOURCE: return createResource();
			case MsprojectPackage.RESOURCES: return createResources();
			case MsprojectPackage.TASK: return createTask();
			case MsprojectPackage.TASKS: return createTasks();
			case MsprojectPackage.TIME_PERIOD: return createTimePeriod();
			case MsprojectPackage.TIMEPHASED_DATA_TYPE: return createTimephasedDataType();
			case MsprojectPackage.VALUE: return createValue();
			case MsprojectPackage.VALUE2: return createValue2();
			case MsprojectPackage.VALUE_LIST: return createValueList();
			case MsprojectPackage.VALUES: return createValues();
			case MsprojectPackage.WBS_MASK: return createWBSMask();
			case MsprojectPackage.WBS_MASKS: return createWBSMasks();
			case MsprojectPackage.WEEK_DAY: return createWeekDay();
			case MsprojectPackage.WEEK_DAYS: return createWeekDays();
			case MsprojectPackage.WORKING_TIME: return createWorkingTime();
			case MsprojectPackage.WORKING_TIMES: return createWorkingTimes();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case MsprojectPackage.ACCRUE_AT:
				return createAccrueAtFromString(eDataType, initialValue);
			case MsprojectPackage.ACTIVE_DIRECTORY_GUID_TYPE:
				return createActiveDirectoryGUIDTypeFromString(eDataType, initialValue);
			case MsprojectPackage.ALIAS_TYPE:
				return createAliasTypeFromString(eDataType, initialValue);
			case MsprojectPackage.AUTHOR_TYPE:
				return createAuthorTypeFromString(eDataType, initialValue);
			case MsprojectPackage.BASELINE_FOR_EARNED_VALUE_TYPE:
				return createBaselineForEarnedValueTypeFromString(eDataType, initialValue);
			case MsprojectPackage.BOOKING_TYPE_TYPE:
				return createBookingTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.BOOKING_TYPE_TYPE1:
				return createBookingTypeType1FromString(eDataType, initialValue);
			case MsprojectPackage.CALCULATION_TYPE_TYPE:
				return createCalculationTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.CATEGORY_TYPE:
				return createCategoryTypeFromString(eDataType, initialValue);
			case MsprojectPackage.CODE_TYPE:
				return createCodeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.COMPANY_TYPE:
				return createCompanyTypeFromString(eDataType, initialValue);
			case MsprojectPackage.CONSTRAINT_TYPE_TYPE:
				return createConstraintTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.CONTACT_TYPE:
				return createContactTypeFromString(eDataType, initialValue);
			case MsprojectPackage.COST_RATE_TABLE_TYPE:
				return createCostRateTableTypeFromString(eDataType, initialValue);
			case MsprojectPackage.CURRENCY_SYMBOL_POSITION_TYPE:
				return createCurrencySymbolPositionTypeFromString(eDataType, initialValue);
			case MsprojectPackage.CURRENCY_SYMBOL_TYPE:
				return createCurrencySymbolTypeFromString(eDataType, initialValue);
			case MsprojectPackage.DAY_TYPE_TYPE:
				return createDayTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.DEFAULT_FIXED_COST_ACCRUAL_TYPE:
				return createDefaultFixedCostAccrualTypeFromString(eDataType, initialValue);
			case MsprojectPackage.DEFAULT_TASK_EV_METHOD_TYPE:
				return createDefaultTaskEVMethodTypeFromString(eDataType, initialValue);
			case MsprojectPackage.DEFAULT_TASK_TYPE_TYPE:
				return createDefaultTaskTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE:
				return createDurationFormatTypeFromString(eDataType, initialValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE1:
				return createDurationFormatType1FromString(eDataType, initialValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE2:
				return createDurationFormatType2FromString(eDataType, initialValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE3:
				return createDurationFormatType3FromString(eDataType, initialValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE4:
				return createDurationFormatType4FromString(eDataType, initialValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE5:
				return createDurationFormatType5FromString(eDataType, initialValue);
			case MsprojectPackage.EARNED_VALUE_METHOD_TYPE:
				return createEarnedValueMethodTypeFromString(eDataType, initialValue);
			case MsprojectPackage.EARNED_VALUE_METHOD_TYPE1:
				return createEarnedValueMethodType1FromString(eDataType, initialValue);
			case MsprojectPackage.EMAIL_ADDRESS_TYPE:
				return createEmailAddressTypeFromString(eDataType, initialValue);
			case MsprojectPackage.EXTERNAL_TASK_PROJECT_TYPE:
				return createExternalTaskProjectTypeFromString(eDataType, initialValue);
			case MsprojectPackage.FIXED_COST_ACCRUAL_TYPE:
				return createFixedCostAccrualTypeFromString(eDataType, initialValue);
			case MsprojectPackage.FY_START_DATE_TYPE:
				return createFYStartDateTypeFromString(eDataType, initialValue);
			case MsprojectPackage.GROUP_TYPE:
				return createGroupTypeFromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE:
				return createHyperlinkAddressTypeFromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE1:
				return createHyperlinkAddressType1FromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE2:
				return createHyperlinkAddressType2FromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE:
				return createHyperlinkSubAddressTypeFromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE1:
				return createHyperlinkSubAddressType1FromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE2:
				return createHyperlinkSubAddressType2FromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_TYPE:
				return createHyperlinkTypeFromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_TYPE1:
				return createHyperlinkType1FromString(eDataType, initialValue);
			case MsprojectPackage.HYPERLINK_TYPE2:
				return createHyperlinkType2FromString(eDataType, initialValue);
			case MsprojectPackage.INITIALS_TYPE:
				return createInitialsTypeFromString(eDataType, initialValue);
			case MsprojectPackage.LAG_FORMAT_TYPE:
				return createLagFormatTypeFromString(eDataType, initialValue);
			case MsprojectPackage.LEVELING_DELAY_FORMAT_TYPE:
				return createLevelingDelayFormatTypeFromString(eDataType, initialValue);
			case MsprojectPackage.LEVELING_DELAY_FORMAT_TYPE1:
				return createLevelingDelayFormatType1FromString(eDataType, initialValue);
			case MsprojectPackage.MANAGER_TYPE:
				return createManagerTypeFromString(eDataType, initialValue);
			case MsprojectPackage.MATERIAL_LABEL_TYPE:
				return createMaterialLabelTypeFromString(eDataType, initialValue);
			case MsprojectPackage.NAME_TYPE:
				return createNameTypeFromString(eDataType, initialValue);
			case MsprojectPackage.NAME_TYPE1:
				return createNameType1FromString(eDataType, initialValue);
			case MsprojectPackage.NAME_TYPE2:
				return createNameType2FromString(eDataType, initialValue);
			case MsprojectPackage.NAME_TYPE3:
				return createNameType3FromString(eDataType, initialValue);
			case MsprojectPackage.NEW_TASK_START_DATE_TYPE:
				return createNewTaskStartDateTypeFromString(eDataType, initialValue);
			case MsprojectPackage.NT_ACCOUNT_TYPE:
				return createNTAccountTypeFromString(eDataType, initialValue);
			case MsprojectPackage.OUTLINE_NUMBER_TYPE:
				return createOutlineNumberTypeFromString(eDataType, initialValue);
			case MsprojectPackage.OVERTIME_RATE_FORMAT_TYPE:
				return createOvertimeRateFormatTypeFromString(eDataType, initialValue);
			case MsprojectPackage.OVERTIME_RATE_FORMAT_TYPE1:
				return createOvertimeRateFormatType1FromString(eDataType, initialValue);
			case MsprojectPackage.PHONETIC_ALIAS_TYPE:
				return createPhoneticAliasTypeFromString(eDataType, initialValue);
			case MsprojectPackage.PHONETICS_TYPE:
				return createPhoneticsTypeFromString(eDataType, initialValue);
			case MsprojectPackage.PREFIX_TYPE:
				return createPrefixTypeFromString(eDataType, initialValue);
			case MsprojectPackage.RATE_TABLE_TYPE:
				return createRateTableTypeFromString(eDataType, initialValue);
			case MsprojectPackage.ROLLUP_TYPE_TYPE:
				return createRollupTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.STANDARD_RATE_FORMAT_TYPE:
				return createStandardRateFormatTypeFromString(eDataType, initialValue);
			case MsprojectPackage.STANDARD_RATE_FORMAT_TYPE1:
				return createStandardRateFormatType1FromString(eDataType, initialValue);
			case MsprojectPackage.SUBJECT_TYPE:
				return createSubjectTypeFromString(eDataType, initialValue);
			case MsprojectPackage.SUBPROJECT_NAME_TYPE:
				return createSubprojectNameTypeFromString(eDataType, initialValue);
			case MsprojectPackage.TITLE_TYPE:
				return createTitleTypeFromString(eDataType, initialValue);
			case MsprojectPackage.TYPE_TYPE:
				return createTypeTypeFromString(eDataType, initialValue);
			case MsprojectPackage.TYPE_TYPE1:
				return createTypeType1FromString(eDataType, initialValue);
			case MsprojectPackage.TYPE_TYPE2:
				return createTypeType2FromString(eDataType, initialValue);
			case MsprojectPackage.TYPE_TYPE3:
				return createTypeType3FromString(eDataType, initialValue);
			case MsprojectPackage.TYPE_TYPE4:
				return createTypeType4FromString(eDataType, initialValue);
			case MsprojectPackage.TYPE_TYPE5:
				return createTypeType5FromString(eDataType, initialValue);
			case MsprojectPackage.UID_TYPE:
				return createUIDTypeFromString(eDataType, initialValue);
			case MsprojectPackage.UNIT_TYPE:
				return createUnitTypeFromString(eDataType, initialValue);
			case MsprojectPackage.VALUELIST_SORT_ORDER_TYPE:
				return createValuelistSortOrderTypeFromString(eDataType, initialValue);
			case MsprojectPackage.WEEK_START_DAY_TYPE:
				return createWeekStartDayTypeFromString(eDataType, initialValue);
			case MsprojectPackage.WORK_CONTOUR_TYPE:
				return createWorkContourTypeFromString(eDataType, initialValue);
			case MsprojectPackage.WORK_FORMAT_TYPE:
				return createWorkFormatTypeFromString(eDataType, initialValue);
			case MsprojectPackage.WORK_GROUP_TYPE:
				return createWorkGroupTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case MsprojectPackage.ACCRUE_AT:
				return convertAccrueAtToString(eDataType, instanceValue);
			case MsprojectPackage.ACTIVE_DIRECTORY_GUID_TYPE:
				return convertActiveDirectoryGUIDTypeToString(eDataType, instanceValue);
			case MsprojectPackage.ALIAS_TYPE:
				return convertAliasTypeToString(eDataType, instanceValue);
			case MsprojectPackage.AUTHOR_TYPE:
				return convertAuthorTypeToString(eDataType, instanceValue);
			case MsprojectPackage.BASELINE_FOR_EARNED_VALUE_TYPE:
				return convertBaselineForEarnedValueTypeToString(eDataType, instanceValue);
			case MsprojectPackage.BOOKING_TYPE_TYPE:
				return convertBookingTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.BOOKING_TYPE_TYPE1:
				return convertBookingTypeType1ToString(eDataType, instanceValue);
			case MsprojectPackage.CALCULATION_TYPE_TYPE:
				return convertCalculationTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.CATEGORY_TYPE:
				return convertCategoryTypeToString(eDataType, instanceValue);
			case MsprojectPackage.CODE_TYPE:
				return convertCodeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.COMPANY_TYPE:
				return convertCompanyTypeToString(eDataType, instanceValue);
			case MsprojectPackage.CONSTRAINT_TYPE_TYPE:
				return convertConstraintTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.CONTACT_TYPE:
				return convertContactTypeToString(eDataType, instanceValue);
			case MsprojectPackage.COST_RATE_TABLE_TYPE:
				return convertCostRateTableTypeToString(eDataType, instanceValue);
			case MsprojectPackage.CURRENCY_SYMBOL_POSITION_TYPE:
				return convertCurrencySymbolPositionTypeToString(eDataType, instanceValue);
			case MsprojectPackage.CURRENCY_SYMBOL_TYPE:
				return convertCurrencySymbolTypeToString(eDataType, instanceValue);
			case MsprojectPackage.DAY_TYPE_TYPE:
				return convertDayTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.DEFAULT_FIXED_COST_ACCRUAL_TYPE:
				return convertDefaultFixedCostAccrualTypeToString(eDataType, instanceValue);
			case MsprojectPackage.DEFAULT_TASK_EV_METHOD_TYPE:
				return convertDefaultTaskEVMethodTypeToString(eDataType, instanceValue);
			case MsprojectPackage.DEFAULT_TASK_TYPE_TYPE:
				return convertDefaultTaskTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE:
				return convertDurationFormatTypeToString(eDataType, instanceValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE1:
				return convertDurationFormatType1ToString(eDataType, instanceValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE2:
				return convertDurationFormatType2ToString(eDataType, instanceValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE3:
				return convertDurationFormatType3ToString(eDataType, instanceValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE4:
				return convertDurationFormatType4ToString(eDataType, instanceValue);
			case MsprojectPackage.DURATION_FORMAT_TYPE5:
				return convertDurationFormatType5ToString(eDataType, instanceValue);
			case MsprojectPackage.EARNED_VALUE_METHOD_TYPE:
				return convertEarnedValueMethodTypeToString(eDataType, instanceValue);
			case MsprojectPackage.EARNED_VALUE_METHOD_TYPE1:
				return convertEarnedValueMethodType1ToString(eDataType, instanceValue);
			case MsprojectPackage.EMAIL_ADDRESS_TYPE:
				return convertEmailAddressTypeToString(eDataType, instanceValue);
			case MsprojectPackage.EXTERNAL_TASK_PROJECT_TYPE:
				return convertExternalTaskProjectTypeToString(eDataType, instanceValue);
			case MsprojectPackage.FIXED_COST_ACCRUAL_TYPE:
				return convertFixedCostAccrualTypeToString(eDataType, instanceValue);
			case MsprojectPackage.FY_START_DATE_TYPE:
				return convertFYStartDateTypeToString(eDataType, instanceValue);
			case MsprojectPackage.GROUP_TYPE:
				return convertGroupTypeToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE:
				return convertHyperlinkAddressTypeToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE1:
				return convertHyperlinkAddressType1ToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_ADDRESS_TYPE2:
				return convertHyperlinkAddressType2ToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE:
				return convertHyperlinkSubAddressTypeToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE1:
				return convertHyperlinkSubAddressType1ToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_SUB_ADDRESS_TYPE2:
				return convertHyperlinkSubAddressType2ToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_TYPE:
				return convertHyperlinkTypeToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_TYPE1:
				return convertHyperlinkType1ToString(eDataType, instanceValue);
			case MsprojectPackage.HYPERLINK_TYPE2:
				return convertHyperlinkType2ToString(eDataType, instanceValue);
			case MsprojectPackage.INITIALS_TYPE:
				return convertInitialsTypeToString(eDataType, instanceValue);
			case MsprojectPackage.LAG_FORMAT_TYPE:
				return convertLagFormatTypeToString(eDataType, instanceValue);
			case MsprojectPackage.LEVELING_DELAY_FORMAT_TYPE:
				return convertLevelingDelayFormatTypeToString(eDataType, instanceValue);
			case MsprojectPackage.LEVELING_DELAY_FORMAT_TYPE1:
				return convertLevelingDelayFormatType1ToString(eDataType, instanceValue);
			case MsprojectPackage.MANAGER_TYPE:
				return convertManagerTypeToString(eDataType, instanceValue);
			case MsprojectPackage.MATERIAL_LABEL_TYPE:
				return convertMaterialLabelTypeToString(eDataType, instanceValue);
			case MsprojectPackage.NAME_TYPE:
				return convertNameTypeToString(eDataType, instanceValue);
			case MsprojectPackage.NAME_TYPE1:
				return convertNameType1ToString(eDataType, instanceValue);
			case MsprojectPackage.NAME_TYPE2:
				return convertNameType2ToString(eDataType, instanceValue);
			case MsprojectPackage.NAME_TYPE3:
				return convertNameType3ToString(eDataType, instanceValue);
			case MsprojectPackage.NEW_TASK_START_DATE_TYPE:
				return convertNewTaskStartDateTypeToString(eDataType, instanceValue);
			case MsprojectPackage.NT_ACCOUNT_TYPE:
				return convertNTAccountTypeToString(eDataType, instanceValue);
			case MsprojectPackage.OUTLINE_NUMBER_TYPE:
				return convertOutlineNumberTypeToString(eDataType, instanceValue);
			case MsprojectPackage.OVERTIME_RATE_FORMAT_TYPE:
				return convertOvertimeRateFormatTypeToString(eDataType, instanceValue);
			case MsprojectPackage.OVERTIME_RATE_FORMAT_TYPE1:
				return convertOvertimeRateFormatType1ToString(eDataType, instanceValue);
			case MsprojectPackage.PHONETIC_ALIAS_TYPE:
				return convertPhoneticAliasTypeToString(eDataType, instanceValue);
			case MsprojectPackage.PHONETICS_TYPE:
				return convertPhoneticsTypeToString(eDataType, instanceValue);
			case MsprojectPackage.PREFIX_TYPE:
				return convertPrefixTypeToString(eDataType, instanceValue);
			case MsprojectPackage.RATE_TABLE_TYPE:
				return convertRateTableTypeToString(eDataType, instanceValue);
			case MsprojectPackage.ROLLUP_TYPE_TYPE:
				return convertRollupTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.STANDARD_RATE_FORMAT_TYPE:
				return convertStandardRateFormatTypeToString(eDataType, instanceValue);
			case MsprojectPackage.STANDARD_RATE_FORMAT_TYPE1:
				return convertStandardRateFormatType1ToString(eDataType, instanceValue);
			case MsprojectPackage.SUBJECT_TYPE:
				return convertSubjectTypeToString(eDataType, instanceValue);
			case MsprojectPackage.SUBPROJECT_NAME_TYPE:
				return convertSubprojectNameTypeToString(eDataType, instanceValue);
			case MsprojectPackage.TITLE_TYPE:
				return convertTitleTypeToString(eDataType, instanceValue);
			case MsprojectPackage.TYPE_TYPE:
				return convertTypeTypeToString(eDataType, instanceValue);
			case MsprojectPackage.TYPE_TYPE1:
				return convertTypeType1ToString(eDataType, instanceValue);
			case MsprojectPackage.TYPE_TYPE2:
				return convertTypeType2ToString(eDataType, instanceValue);
			case MsprojectPackage.TYPE_TYPE3:
				return convertTypeType3ToString(eDataType, instanceValue);
			case MsprojectPackage.TYPE_TYPE4:
				return convertTypeType4ToString(eDataType, instanceValue);
			case MsprojectPackage.TYPE_TYPE5:
				return convertTypeType5ToString(eDataType, instanceValue);
			case MsprojectPackage.UID_TYPE:
				return convertUIDTypeToString(eDataType, instanceValue);
			case MsprojectPackage.UNIT_TYPE:
				return convertUnitTypeToString(eDataType, instanceValue);
			case MsprojectPackage.VALUELIST_SORT_ORDER_TYPE:
				return convertValuelistSortOrderTypeToString(eDataType, instanceValue);
			case MsprojectPackage.WEEK_START_DAY_TYPE:
				return convertWeekStartDayTypeToString(eDataType, instanceValue);
			case MsprojectPackage.WORK_CONTOUR_TYPE:
				return convertWorkContourTypeToString(eDataType, instanceValue);
			case MsprojectPackage.WORK_FORMAT_TYPE:
				return convertWorkFormatTypeToString(eDataType, instanceValue);
			case MsprojectPackage.WORK_GROUP_TYPE:
				return convertWorkGroupTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assignment createAssignment() {
		AssignmentImpl assignment = new AssignmentImpl();
		return assignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assignments createAssignments() {
		AssignmentsImpl assignments = new AssignmentsImpl();
		return assignments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityPeriod createAvailabilityPeriod() {
		AvailabilityPeriodImpl availabilityPeriod = new AvailabilityPeriodImpl();
		return availabilityPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityPeriods createAvailabilityPeriods() {
		AvailabilityPeriodsImpl availabilityPeriods = new AvailabilityPeriodsImpl();
		return availabilityPeriods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Baseline createBaseline() {
		BaselineImpl baseline = new BaselineImpl();
		return baseline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Baseline2 createBaseline2() {
		Baseline2Impl baseline2 = new Baseline2Impl();
		return baseline2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Baseline3 createBaseline3() {
		Baseline3Impl baseline3 = new Baseline3Impl();
		return baseline3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Calendar createCalendar() {
		CalendarImpl calendar = new CalendarImpl();
		return calendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Calendars createCalendars() {
		CalendarsImpl calendars = new CalendarsImpl();
		return calendars;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttribute createExtendedAttribute() {
		ExtendedAttributeImpl extendedAttribute = new ExtendedAttributeImpl();
		return extendedAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttribute2 createExtendedAttribute2() {
		ExtendedAttribute2Impl extendedAttribute2 = new ExtendedAttribute2Impl();
		return extendedAttribute2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttribute3 createExtendedAttribute3() {
		ExtendedAttribute3Impl extendedAttribute3 = new ExtendedAttribute3Impl();
		return extendedAttribute3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttribute4 createExtendedAttribute4() {
		ExtendedAttribute4Impl extendedAttribute4 = new ExtendedAttribute4Impl();
		return extendedAttribute4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributes createExtendedAttributes() {
		ExtendedAttributesImpl extendedAttributes = new ExtendedAttributesImpl();
		return extendedAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mask createMask() {
		MaskImpl mask = new MaskImpl();
		return mask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Masks createMasks() {
		MasksImpl masks = new MasksImpl();
		return masks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutlineCode createOutlineCode() {
		OutlineCodeImpl outlineCode = new OutlineCodeImpl();
		return outlineCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutlineCode2 createOutlineCode2() {
		OutlineCode2Impl outlineCode2 = new OutlineCode2Impl();
		return outlineCode2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutlineCode3 createOutlineCode3() {
		OutlineCode3Impl outlineCode3 = new OutlineCode3Impl();
		return outlineCode3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutlineCodes createOutlineCodes() {
		OutlineCodesImpl outlineCodes = new OutlineCodesImpl();
		return outlineCodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredecessorLink createPredecessorLink() {
		PredecessorLinkImpl predecessorLink = new PredecessorLinkImpl();
		return predecessorLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Project createProject() {
		ProjectImpl project = new ProjectImpl();
		return project;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rate createRate() {
		RateImpl rate = new RateImpl();
		return rate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rates createRates() {
		RatesImpl rates = new RatesImpl();
		return rates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resources createResources() {
		ResourcesImpl resources = new ResourcesImpl();
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Task createTask() {
		TaskImpl task = new TaskImpl();
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tasks createTasks() {
		TasksImpl tasks = new TasksImpl();
		return tasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimePeriod createTimePeriod() {
		TimePeriodImpl timePeriod = new TimePeriodImpl();
		return timePeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimephasedDataType createTimephasedDataType() {
		TimephasedDataTypeImpl timephasedDataType = new TimephasedDataTypeImpl();
		return timephasedDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value createValue() {
		ValueImpl value = new ValueImpl();
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value2 createValue2() {
		Value2Impl value2 = new Value2Impl();
		return value2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueList createValueList() {
		ValueListImpl valueList = new ValueListImpl();
		return valueList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Values createValues() {
		ValuesImpl values = new ValuesImpl();
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WBSMask createWBSMask() {
		WBSMaskImpl wbsMask = new WBSMaskImpl();
		return wbsMask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WBSMasks createWBSMasks() {
		WBSMasksImpl wbsMasks = new WBSMasksImpl();
		return wbsMasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeekDay createWeekDay() {
		WeekDayImpl weekDay = new WeekDayImpl();
		return weekDay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeekDays createWeekDays() {
		WeekDaysImpl weekDays = new WeekDaysImpl();
		return weekDays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkingTime createWorkingTime() {
		WorkingTimeImpl workingTime = new WorkingTimeImpl();
		return workingTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkingTimes createWorkingTimes() {
		WorkingTimesImpl workingTimes = new WorkingTimesImpl();
		return workingTimes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createAccrueAtFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAccrueAtToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createActiveDirectoryGUIDTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActiveDirectoryGUIDTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createAliasTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAliasTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createAuthorTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAuthorTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createBaselineForEarnedValueTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBaselineForEarnedValueTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createBookingTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBookingTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createBookingTypeType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBookingTypeType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createCalculationTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCalculationTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCategoryTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCategoryTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCodeTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCodeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCompanyTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCompanyTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createConstraintTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConstraintTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createContactTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContactTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createCostRateTableTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCostRateTableTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createCurrencySymbolPositionTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCurrencySymbolPositionTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCurrencySymbolTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCurrencySymbolTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDayTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDayTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDefaultFixedCostAccrualTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDefaultFixedCostAccrualTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDefaultTaskEVMethodTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDefaultTaskEVMethodTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDefaultTaskTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDefaultTaskTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDurationFormatTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationFormatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDurationFormatType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationFormatType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDurationFormatType2FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationFormatType2ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDurationFormatType3FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationFormatType3ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDurationFormatType4FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationFormatType4ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createDurationFormatType5FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationFormatType5ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createEarnedValueMethodTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEarnedValueMethodTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createEarnedValueMethodType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEarnedValueMethodType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEmailAddressTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEmailAddressTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createExternalTaskProjectTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExternalTaskProjectTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createFixedCostAccrualTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFixedCostAccrualTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createFYStartDateTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFYStartDateTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createGroupTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGroupTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkAddressTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkAddressTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkAddressType1FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkAddressType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkAddressType2FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkAddressType2ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkSubAddressTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkSubAddressTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkSubAddressType1FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkSubAddressType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkSubAddressType2FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkSubAddressType2ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkType1FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHyperlinkType2FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHyperlinkType2ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createInitialsTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInitialsTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createLagFormatTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLagFormatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createLevelingDelayFormatTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLevelingDelayFormatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createLevelingDelayFormatType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLevelingDelayFormatType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createManagerTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertManagerTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMaterialLabelTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMaterialLabelTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNameTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createNameType1FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNameType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createNameType2FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNameType2ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createNameType3FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNameType3ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createNewTaskStartDateTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNewTaskStartDateTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createNTAccountTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNTAccountTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createOutlineNumberTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOutlineNumberTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createOvertimeRateFormatTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOvertimeRateFormatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createOvertimeRateFormatType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOvertimeRateFormatType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createPhoneticAliasTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPhoneticAliasTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createPhoneticsTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPhoneticsTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createPrefixTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrefixTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createRateTableTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRateTableTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createRollupTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRollupTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createStandardRateFormatTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStandardRateFormatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createStandardRateFormatType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStandardRateFormatType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSubjectTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSubjectTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createSubprojectNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSubprojectNameTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createTitleTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTitleTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTypeType1FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTypeType2FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType2ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTypeType3FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType3ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTypeType4FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType4ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTypeType5FromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeType5ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createUIDTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getString(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUIDTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getString(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createUnitTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnitTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createValuelistSortOrderTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValuelistSortOrderTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createWeekStartDayTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWeekStartDayTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createWorkContourTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkContourTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createWorkFormatTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkFormatTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createWorkGroupTypeFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInteger(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWorkGroupTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInteger(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsprojectPackage getMsprojectPackage() {
		return (MsprojectPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static MsprojectPackage getPackage() {
		return MsprojectPackage.eINSTANCE;
	}

} //MsprojectFactoryImpl
