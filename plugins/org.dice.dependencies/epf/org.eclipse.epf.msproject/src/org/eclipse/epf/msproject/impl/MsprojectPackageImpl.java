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

import java.io.IOException;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;
import org.eclipse.epf.msproject.MsprojectFactory;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.util.MsprojectValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MsprojectPackageImpl extends EPackageImpl implements MsprojectPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String packageFilename = "msproject.ecore";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignmentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass availabilityPeriodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass availabilityPeriodsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baselineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseline2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseline3EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass calendarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass calendarsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttribute2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttribute3EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttribute4EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttributesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass maskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass masksEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outlineCodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outlineCode2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outlineCode3EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outlineCodesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass predecessorLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass projectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ratesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourcesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tasksEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timePeriodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timephasedDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass value2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valuesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wbsMaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wbsMasksEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weekDayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weekDaysEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workingTimeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workingTimesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType accrueAtEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType activeDirectoryGUIDTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType aliasTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType authorTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType baselineForEarnedValueTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bookingTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bookingTypeType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType calculationTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType categoryTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType codeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType companyTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType constraintTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType contactTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType costRateTableTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType currencySymbolPositionTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType currencySymbolTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dayTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType defaultFixedCostAccrualTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType defaultTaskEVMethodTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType defaultTaskTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationFormatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationFormatType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationFormatType2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationFormatType3EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationFormatType4EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationFormatType5EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType earnedValueMethodTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType earnedValueMethodType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType emailAddressTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType externalTaskProjectTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType fixedCostAccrualTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType fyStartDateTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType groupTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkAddressTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkAddressType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkAddressType2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkSubAddressTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkSubAddressType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkSubAddressType2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType hyperlinkType2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType initialsTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType lagFormatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType levelingDelayFormatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType levelingDelayFormatType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType managerTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType materialLabelTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nameType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nameType2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nameType3EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType newTaskStartDateTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ntAccountTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType outlineNumberTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType overtimeRateFormatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType overtimeRateFormatType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType phoneticAliasTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType phoneticsTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType prefixTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rateTableTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rollupTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType standardRateFormatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType standardRateFormatType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType subjectTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType subprojectNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType titleTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeType1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeType2EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeType3EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeType4EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeType5EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uidTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType unitTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType valuelistSortOrderTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType weekStartDayTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType workContourTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType workFormatTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType workGroupTypeEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.epf.msproject.MsprojectPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MsprojectPackageImpl() {
		super(eNS_URI, MsprojectFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static MsprojectPackage init() {
		if (isInited) return (MsprojectPackage)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI);

		// Obtain or create and register package
		MsprojectPackageImpl theMsprojectPackage = (MsprojectPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof MsprojectPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new MsprojectPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackageImpl.init();

		// Load packages
		theMsprojectPackage.loadPackage();

		// Fix loaded packages
		theMsprojectPackage.fixPackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theMsprojectPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return MsprojectValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theMsprojectPackage.freeze();

		return theMsprojectPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignment() {
		if (assignmentEClass == null) {
			assignmentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(3);
		}
		return assignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_UID() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_TaskUID() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ResourceUID() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_PercentWorkComplete() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualCost() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualFinish() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualOvertimeCost() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualOvertimeWork() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualStart() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualWork() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ACWP() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Confirmed() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Cost() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_CostRateTable() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_CostVariance() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_CV() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Delay() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Finish() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_FinishVariance() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Hyperlink() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_HyperlinkAddress() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_HyperlinkSubAddress() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_WorkVariance() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_HasFixedRateUnits() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_FixedMaterial() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_LevelingDelay() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_LevelingDelayFormat() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_LinkedFields() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Milestone() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Notes() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Overallocated() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_OvertimeCost() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_OvertimeWork() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_RegularWork() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_RemainingCost() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_RemainingOvertimeCost() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_RemainingOvertimeWork() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_RemainingWork() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ResponsePending() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Start() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Stop() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Resume() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_StartVariance() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Units() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_UpdateNeeded() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_VAC() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Work() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_WorkContour() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_BCWS() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_BCWP() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_BookingType() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualWorkProtected() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_ActualOvertimeWorkProtected() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_CreationDate() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_ExtendedAttribute() {
        return (EReference)getAssignment().getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_Baseline() {
        return (EReference)getAssignment().getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_TimephasedData() {
        return (EReference)getAssignment().getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignments() {
		if (assignmentsEClass == null) {
			assignmentsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(4);
		}
		return assignmentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignments_Assignment() {
        return (EReference)getAssignments().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAvailabilityPeriod() {
		if (availabilityPeriodEClass == null) {
			availabilityPeriodEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(6);
		}
		return availabilityPeriodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailabilityPeriod_AvailableFrom() {
        return (EAttribute)getAvailabilityPeriod().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailabilityPeriod_AvailableTo() {
        return (EAttribute)getAvailabilityPeriod().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAvailabilityPeriod_AvailableUnits() {
        return (EAttribute)getAvailabilityPeriod().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAvailabilityPeriods() {
		if (availabilityPeriodsEClass == null) {
			availabilityPeriodsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(7);
		}
		return availabilityPeriodsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAvailabilityPeriods_AvailabilityPeriod() {
        return (EReference)getAvailabilityPeriods().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseline() {
		if (baselineEClass == null) {
			baselineEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(8);
		}
		return baselineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseline_TimephasedData() {
        return (EReference)getBaseline().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Number() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Interim() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Start() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Finish() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Duration() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_DurationFormat() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_EstimatedDuration() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Work() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_Cost() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_BCWS() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline_BCWP() {
        return (EAttribute)getBaseline().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseline2() {
		if (baseline2EClass == null) {
			baseline2EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(9);
		}
		return baseline2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline2_Number() {
        return (EAttribute)getBaseline2().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline2_Work() {
        return (EAttribute)getBaseline2().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline2_Cost() {
        return (EAttribute)getBaseline2().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline2_BCWS() {
        return (EAttribute)getBaseline2().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline2_BCWP() {
        return (EAttribute)getBaseline2().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseline3() {
		if (baseline3EClass == null) {
			baseline3EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(10);
		}
		return baseline3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBaseline3_TimephasedData() {
        return (EReference)getBaseline3().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_Number() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_Start() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_Finish() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_Work() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_Cost() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_BCWS() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseline3_BCWP() {
        return (EAttribute)getBaseline3().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCalendar() {
		if (calendarEClass == null) {
			calendarEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(15);
		}
		return calendarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalendar_UID() {
        return (EAttribute)getCalendar().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalendar_Name() {
        return (EAttribute)getCalendar().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalendar_IsBaseCalendar() {
        return (EAttribute)getCalendar().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalendar_BaseCalendarUID() {
        return (EAttribute)getCalendar().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCalendar_WeekDays() {
        return (EReference)getCalendar().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCalendars() {
		if (calendarsEClass == null) {
			calendarsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(16);
		}
		return calendarsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCalendars_Calendar() {
        return (EReference)getCalendars().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		if (documentRootEClass == null) {
			documentRootEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(29);
		}
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Project() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttribute() {
		if (extendedAttributeEClass == null) {
			extendedAttributeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(39);
		}
		return extendedAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_FieldID() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_FieldName() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_Alias() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_PhoneticAlias() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_RollupType() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_CalculationType() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_Formula() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_RestrictValues() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_ValuelistSortOrder() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_AppendNewValues() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_Default() {
        return (EAttribute)getExtendedAttribute().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendedAttribute_ValueList() {
        return (EReference)getExtendedAttribute().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttribute2() {
		if (extendedAttribute2EClass == null) {
			extendedAttribute2EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(40);
		}
		return extendedAttribute2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute2_UID() {
        return (EAttribute)getExtendedAttribute2().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute2_FieldID() {
        return (EAttribute)getExtendedAttribute2().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute2_Value() {
        return (EAttribute)getExtendedAttribute2().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute2_ValueID() {
        return (EAttribute)getExtendedAttribute2().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute2_DurationFormat() {
        return (EAttribute)getExtendedAttribute2().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttribute3() {
		if (extendedAttribute3EClass == null) {
			extendedAttribute3EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(41);
		}
		return extendedAttribute3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute3_UID() {
        return (EAttribute)getExtendedAttribute3().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute3_FieldID() {
        return (EAttribute)getExtendedAttribute3().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute3_Value() {
        return (EAttribute)getExtendedAttribute3().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute3_ValueID() {
        return (EAttribute)getExtendedAttribute3().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute3_DurationFormat() {
        return (EAttribute)getExtendedAttribute3().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttribute4() {
		if (extendedAttribute4EClass == null) {
			extendedAttribute4EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(42);
		}
		return extendedAttribute4EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute4_UID() {
        return (EAttribute)getExtendedAttribute4().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute4_FieldID() {
        return (EAttribute)getExtendedAttribute4().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute4_Value() {
        return (EAttribute)getExtendedAttribute4().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute4_ValueID() {
        return (EAttribute)getExtendedAttribute4().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute4_DurationFormat() {
        return (EAttribute)getExtendedAttribute4().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttributes() {
		if (extendedAttributesEClass == null) {
			extendedAttributesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(43);
		}
		return extendedAttributesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendedAttributes_ExtendedAttribute() {
        return (EReference)getExtendedAttributes().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMask() {
		if (maskEClass == null) {
			maskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(62);
		}
		return maskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMask_Level() {
        return (EAttribute)getMask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMask_Type() {
        return (EAttribute)getMask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMask_Length() {
        return (EAttribute)getMask().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMask_Separator() {
        return (EAttribute)getMask().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMasks() {
		if (masksEClass == null) {
			masksEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(63);
		}
		return masksEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMasks_Mask() {
        return (EReference)getMasks().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutlineCode() {
		if (outlineCodeEClass == null) {
			outlineCodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(71);
		}
		return outlineCodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_FieldID() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_FieldName() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_Alias() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_PhoneticAlias() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutlineCode_Values() {
        return (EReference)getOutlineCode().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_Enterprise() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_EnterpriseOutlineCodeAlias() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_ResourceSubstitutionEnabled() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_LeafOnly() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_AllLevelsRequired() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode_OnlyTableValuesAllowed() {
        return (EAttribute)getOutlineCode().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutlineCode_Masks() {
        return (EReference)getOutlineCode().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutlineCode2() {
		if (outlineCode2EClass == null) {
			outlineCode2EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(72);
		}
		return outlineCode2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode2_UID() {
        return (EAttribute)getOutlineCode2().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode2_FieldID() {
        return (EAttribute)getOutlineCode2().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode2_ValueID() {
        return (EAttribute)getOutlineCode2().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutlineCode3() {
		if (outlineCode3EClass == null) {
			outlineCode3EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(73);
		}
		return outlineCode3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode3_UID() {
        return (EAttribute)getOutlineCode3().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode3_FieldID() {
        return (EAttribute)getOutlineCode3().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutlineCode3_ValueID() {
        return (EAttribute)getOutlineCode3().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutlineCodes() {
		if (outlineCodesEClass == null) {
			outlineCodesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(74);
		}
		return outlineCodesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutlineCodes_OutlineCode() {
        return (EReference)getOutlineCodes().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPredecessorLink() {
		if (predecessorLinkEClass == null) {
			predecessorLinkEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(80);
		}
		return predecessorLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredecessorLink_PredecessorUID() {
        return (EAttribute)getPredecessorLink().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredecessorLink_Type() {
        return (EAttribute)getPredecessorLink().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredecessorLink_CrossProject() {
        return (EAttribute)getPredecessorLink().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredecessorLink_CrossProjectName() {
        return (EAttribute)getPredecessorLink().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredecessorLink_LinkLag() {
        return (EAttribute)getPredecessorLink().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredecessorLink_LagFormat() {
        return (EAttribute)getPredecessorLink().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProject() {
		if (projectEClass == null) {
			projectEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(82);
		}
		return projectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_UID() {
        return (EAttribute)getProject().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Name() {
        return (EAttribute)getProject().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Title() {
        return (EAttribute)getProject().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Subject() {
        return (EAttribute)getProject().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Category() {
        return (EAttribute)getProject().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Company() {
        return (EAttribute)getProject().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Manager() {
        return (EAttribute)getProject().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Author() {
        return (EAttribute)getProject().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CreationDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Revision() {
        return (EAttribute)getProject().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_LastSaved() {
        return (EAttribute)getProject().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_ScheduleFromStart() {
        return (EAttribute)getProject().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_StartDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_FinishDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_FYStartDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CriticalSlackLimit() {
        return (EAttribute)getProject().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CurrencyDigits() {
        return (EAttribute)getProject().getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CurrencySymbol() {
        return (EAttribute)getProject().getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CurrencySymbolPosition() {
        return (EAttribute)getProject().getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CalendarUID() {
        return (EAttribute)getProject().getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultStartTime() {
        return (EAttribute)getProject().getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultFinishTime() {
        return (EAttribute)getProject().getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MinutesPerDay() {
        return (EAttribute)getProject().getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MinutesPerWeek() {
        return (EAttribute)getProject().getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DaysPerMonth() {
        return (EAttribute)getProject().getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultTaskType() {
        return (EAttribute)getProject().getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultFixedCostAccrual() {
        return (EAttribute)getProject().getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultStandardRate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultOvertimeRate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DurationFormat() {
        return (EAttribute)getProject().getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_WorkFormat() {
        return (EAttribute)getProject().getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_EditableActualCosts() {
        return (EAttribute)getProject().getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_HonorConstraints() {
        return (EAttribute)getProject().getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_EarnedValueMethod() {
        return (EAttribute)getProject().getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_InsertedProjectsLikeSummary() {
        return (EAttribute)getProject().getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MultipleCriticalPaths() {
        return (EAttribute)getProject().getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_NewTasksEffortDriven() {
        return (EAttribute)getProject().getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_NewTasksEstimated() {
        return (EAttribute)getProject().getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_SplitsInProgressTasks() {
        return (EAttribute)getProject().getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_SpreadActualCost() {
        return (EAttribute)getProject().getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_SpreadPercentComplete() {
        return (EAttribute)getProject().getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_TaskUpdatesResource() {
        return (EAttribute)getProject().getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_FiscalYearStart() {
        return (EAttribute)getProject().getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_WeekStartDay() {
        return (EAttribute)getProject().getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MoveCompletedEndsBack() {
        return (EAttribute)getProject().getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MoveRemainingStartsBack() {
        return (EAttribute)getProject().getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MoveRemainingStartsForward() {
        return (EAttribute)getProject().getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MoveCompletedEndsForward() {
        return (EAttribute)getProject().getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_BaselineForEarnedValue() {
        return (EAttribute)getProject().getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_AutoAddNewResourcesAndTasks() {
        return (EAttribute)getProject().getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_StatusDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_CurrentDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_MicrosoftProjectServerURL() {
        return (EAttribute)getProject().getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_Autolink() {
        return (EAttribute)getProject().getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_NewTaskStartDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_DefaultTaskEVMethod() {
        return (EAttribute)getProject().getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_ProjectExternallyEdited() {
        return (EAttribute)getProject().getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_ExtendedCreationDate() {
        return (EAttribute)getProject().getEStructuralFeatures().get(57);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_ActualsInSync() {
        return (EAttribute)getProject().getEStructuralFeatures().get(58);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_RemoveFileProperties() {
        return (EAttribute)getProject().getEStructuralFeatures().get(59);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProject_AdminProject() {
        return (EAttribute)getProject().getEStructuralFeatures().get(60);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_OutlineCodes() {
        return (EReference)getProject().getEStructuralFeatures().get(61);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_WBSMasks() {
        return (EReference)getProject().getEStructuralFeatures().get(62);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_ExtendedAttributes() {
        return (EReference)getProject().getEStructuralFeatures().get(63);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_Calendars() {
        return (EReference)getProject().getEStructuralFeatures().get(64);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_Tasks() {
        return (EReference)getProject().getEStructuralFeatures().get(65);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_Resources() {
        return (EReference)getProject().getEStructuralFeatures().get(66);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProject_Assignments() {
        return (EReference)getProject().getEStructuralFeatures().get(67);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRate() {
		if (rateEClass == null) {
			rateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(83);
		}
		return rateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_RatesFrom() {
        return (EAttribute)getRate().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_RatesTo() {
        return (EAttribute)getRate().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_RateTable() {
        return (EAttribute)getRate().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_StandardRate() {
        return (EAttribute)getRate().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_StandardRateFormat() {
        return (EAttribute)getRate().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_OvertimeRate() {
        return (EAttribute)getRate().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_OvertimeRateFormat() {
        return (EAttribute)getRate().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRate_CostPerUse() {
        return (EAttribute)getRate().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRates() {
		if (ratesEClass == null) {
			ratesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(84);
		}
		return ratesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRates_Rate() {
        return (EReference)getRates().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource() {
		if (resourceEClass == null) {
			resourceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(86);
		}
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_UID() {
        return (EAttribute)getResource().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ID() {
        return (EAttribute)getResource().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Name() {
        return (EAttribute)getResource().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Type() {
        return (EAttribute)getResource().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_IsNull() {
        return (EAttribute)getResource().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Initials() {
        return (EAttribute)getResource().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Phonetics() {
        return (EAttribute)getResource().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_NTAccount() {
        return (EAttribute)getResource().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_MaterialLabel() {
        return (EAttribute)getResource().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Code() {
        return (EAttribute)getResource().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Group() {
        return (EAttribute)getResource().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_WorkGroup() {
        return (EAttribute)getResource().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_EmailAddress() {
        return (EAttribute)getResource().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Hyperlink() {
        return (EAttribute)getResource().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_HyperlinkAddress() {
        return (EAttribute)getResource().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_HyperlinkSubAddress() {
        return (EAttribute)getResource().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_MaxUnits() {
        return (EAttribute)getResource().getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_PeakUnits() {
        return (EAttribute)getResource().getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_OverAllocated() {
        return (EAttribute)getResource().getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_AvailableFrom() {
        return (EAttribute)getResource().getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_AvailableTo() {
        return (EAttribute)getResource().getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Start() {
        return (EAttribute)getResource().getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Finish() {
        return (EAttribute)getResource().getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_CanLevel() {
        return (EAttribute)getResource().getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_AccrueAt() {
        return (EAttribute)getResource().getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Work() {
        return (EAttribute)getResource().getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_RegularWork() {
        return (EAttribute)getResource().getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_OvertimeWork() {
        return (EAttribute)getResource().getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActualWork() {
        return (EAttribute)getResource().getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_RemainingWork() {
        return (EAttribute)getResource().getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActualOvertimeWork() {
        return (EAttribute)getResource().getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_RemainingOvertimeWork() {
        return (EAttribute)getResource().getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_PercentWorkComplete() {
        return (EAttribute)getResource().getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_StandardRate() {
        return (EAttribute)getResource().getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_StandardRateFormat() {
        return (EAttribute)getResource().getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Cost() {
        return (EAttribute)getResource().getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_OvertimeRate() {
        return (EAttribute)getResource().getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_OvertimeRateFormat() {
        return (EAttribute)getResource().getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_OvertimeCost() {
        return (EAttribute)getResource().getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_CostPerUse() {
        return (EAttribute)getResource().getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActualCost() {
        return (EAttribute)getResource().getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActualOvertimeCost() {
        return (EAttribute)getResource().getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_RemainingCost() {
        return (EAttribute)getResource().getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_RemainingOvertimeCost() {
        return (EAttribute)getResource().getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_WorkVariance() {
        return (EAttribute)getResource().getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_CostVariance() {
        return (EAttribute)getResource().getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_SV() {
        return (EAttribute)getResource().getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_CV() {
        return (EAttribute)getResource().getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ACWP() {
        return (EAttribute)getResource().getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_CalendarUID() {
        return (EAttribute)getResource().getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_Notes() {
        return (EAttribute)getResource().getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_BCWS() {
        return (EAttribute)getResource().getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_BCWP() {
        return (EAttribute)getResource().getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_IsGeneric() {
        return (EAttribute)getResource().getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_IsInactive() {
        return (EAttribute)getResource().getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_IsEnterprise() {
        return (EAttribute)getResource().getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_BookingType() {
        return (EAttribute)getResource().getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActualWorkProtected() {
        return (EAttribute)getResource().getEStructuralFeatures().get(57);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActualOvertimeWorkProtected() {
        return (EAttribute)getResource().getEStructuralFeatures().get(58);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_ActiveDirectoryGUID() {
        return (EAttribute)getResource().getEStructuralFeatures().get(59);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResource_CreationDate() {
        return (EAttribute)getResource().getEStructuralFeatures().get(60);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_ExtendedAttribute() {
        return (EReference)getResource().getEStructuralFeatures().get(61);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_Baseline() {
        return (EReference)getResource().getEStructuralFeatures().get(62);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_OutlineCode() {
        return (EReference)getResource().getEStructuralFeatures().get(63);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_AvailabilityPeriods() {
        return (EReference)getResource().getEStructuralFeatures().get(64);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_Rates() {
        return (EReference)getResource().getEStructuralFeatures().get(65);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_TimephasedData() {
        return (EReference)getResource().getEStructuralFeatures().get(66);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResources() {
		if (resourcesEClass == null) {
			resourcesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(87);
		}
		return resourcesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResources_Resource() {
        return (EReference)getResources().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTask() {
		if (taskEClass == null) {
			taskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(93);
		}
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_UID() {
        return (EAttribute)getTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ID() {
        return (EAttribute)getTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Name() {
        return (EAttribute)getTask().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Type() {
        return (EAttribute)getTask().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_IsNull() {
        return (EAttribute)getTask().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_CreateDate() {
        return (EAttribute)getTask().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Contact() {
        return (EAttribute)getTask().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_WBS() {
        return (EAttribute)getTask().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_WBSLevel() {
        return (EAttribute)getTask().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_OutlineNumber() {
        return (EAttribute)getTask().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_OutlineLevel() {
        return (EAttribute)getTask().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Priority() {
        return (EAttribute)getTask().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Start() {
        return (EAttribute)getTask().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Finish() {
        return (EAttribute)getTask().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Duration() {
        return (EAttribute)getTask().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_DurationFormat() {
        return (EAttribute)getTask().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Work() {
        return (EAttribute)getTask().getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Stop() {
        return (EAttribute)getTask().getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Resume() {
        return (EAttribute)getTask().getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ResumeValid() {
        return (EAttribute)getTask().getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_EffortDriven() {
        return (EAttribute)getTask().getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Recurring() {
        return (EAttribute)getTask().getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_OverAllocated() {
        return (EAttribute)getTask().getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Estimated() {
        return (EAttribute)getTask().getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Milestone() {
        return (EAttribute)getTask().getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Summary() {
        return (EAttribute)getTask().getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Critical() {
        return (EAttribute)getTask().getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_IsSubproject() {
        return (EAttribute)getTask().getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_IsSubprojectReadOnly() {
        return (EAttribute)getTask().getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_SubprojectName() {
        return (EAttribute)getTask().getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ExternalTask() {
        return (EAttribute)getTask().getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ExternalTaskProject() {
        return (EAttribute)getTask().getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_EarlyStart() {
        return (EAttribute)getTask().getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_EarlyFinish() {
        return (EAttribute)getTask().getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_LateStart() {
        return (EAttribute)getTask().getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_LateFinish() {
        return (EAttribute)getTask().getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_StartVariance() {
        return (EAttribute)getTask().getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_FinishVariance() {
        return (EAttribute)getTask().getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_WorkVariance() {
        return (EAttribute)getTask().getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_FreeSlack() {
        return (EAttribute)getTask().getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_TotalSlack() {
        return (EAttribute)getTask().getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_FixedCost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_FixedCostAccrual() {
        return (EAttribute)getTask().getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PercentComplete() {
        return (EAttribute)getTask().getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PercentWorkComplete() {
        return (EAttribute)getTask().getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Cost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_OvertimeCost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_OvertimeWork() {
        return (EAttribute)getTask().getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualStart() {
        return (EAttribute)getTask().getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualFinish() {
        return (EAttribute)getTask().getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualDuration() {
        return (EAttribute)getTask().getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualCost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualOvertimeCost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualWork() {
        return (EAttribute)getTask().getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualOvertimeWork() {
        return (EAttribute)getTask().getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_RegularWork() {
        return (EAttribute)getTask().getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_RemainingDuration() {
        return (EAttribute)getTask().getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_RemainingCost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(57);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_RemainingWork() {
        return (EAttribute)getTask().getEStructuralFeatures().get(58);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_RemainingOvertimeCost() {
        return (EAttribute)getTask().getEStructuralFeatures().get(59);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_RemainingOvertimeWork() {
        return (EAttribute)getTask().getEStructuralFeatures().get(60);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ACWP() {
        return (EAttribute)getTask().getEStructuralFeatures().get(61);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_CV() {
        return (EAttribute)getTask().getEStructuralFeatures().get(62);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ConstraintType() {
        return (EAttribute)getTask().getEStructuralFeatures().get(63);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_CalendarUID() {
        return (EAttribute)getTask().getEStructuralFeatures().get(64);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ConstraintDate() {
        return (EAttribute)getTask().getEStructuralFeatures().get(65);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Deadline() {
        return (EAttribute)getTask().getEStructuralFeatures().get(66);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_LevelAssignments() {
        return (EAttribute)getTask().getEStructuralFeatures().get(67);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_LevelingCanSplit() {
        return (EAttribute)getTask().getEStructuralFeatures().get(68);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_LevelingDelay() {
        return (EAttribute)getTask().getEStructuralFeatures().get(69);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_LevelingDelayFormat() {
        return (EAttribute)getTask().getEStructuralFeatures().get(70);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PreLeveledStart() {
        return (EAttribute)getTask().getEStructuralFeatures().get(71);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PreLeveledFinish() {
        return (EAttribute)getTask().getEStructuralFeatures().get(72);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Hyperlink() {
        return (EAttribute)getTask().getEStructuralFeatures().get(73);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_HyperlinkAddress() {
        return (EAttribute)getTask().getEStructuralFeatures().get(74);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_HyperlinkSubAddress() {
        return (EAttribute)getTask().getEStructuralFeatures().get(75);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_IgnoreResourceCalendar() {
        return (EAttribute)getTask().getEStructuralFeatures().get(76);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Notes() {
        return (EAttribute)getTask().getEStructuralFeatures().get(77);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_HideBar() {
        return (EAttribute)getTask().getEStructuralFeatures().get(78);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_Rollup() {
        return (EAttribute)getTask().getEStructuralFeatures().get(79);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_BCWS() {
        return (EAttribute)getTask().getEStructuralFeatures().get(80);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_BCWP() {
        return (EAttribute)getTask().getEStructuralFeatures().get(81);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_PhysicalPercentComplete() {
        return (EAttribute)getTask().getEStructuralFeatures().get(82);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_EarnedValueMethod() {
        return (EAttribute)getTask().getEStructuralFeatures().get(83);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_PredecessorLink() {
        return (EReference)getTask().getEStructuralFeatures().get(84);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualWorkProtected() {
        return (EAttribute)getTask().getEStructuralFeatures().get(85);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTask_ActualOvertimeWorkProtected() {
        return (EAttribute)getTask().getEStructuralFeatures().get(86);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_ExtendedAttribute() {
        return (EReference)getTask().getEStructuralFeatures().get(87);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_Baseline() {
        return (EReference)getTask().getEStructuralFeatures().get(88);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_OutlineCode() {
        return (EReference)getTask().getEStructuralFeatures().get(89);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTask_TimephasedData() {
        return (EReference)getTask().getEStructuralFeatures().get(90);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTasks() {
		if (tasksEClass == null) {
			tasksEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(94);
		}
		return tasksEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTasks_Task() {
        return (EReference)getTasks().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimePeriod() {
		if (timePeriodEClass == null) {
			timePeriodEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(95);
		}
		return timePeriodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimePeriod_FromDate() {
        return (EAttribute)getTimePeriod().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimePeriod_ToDate() {
        return (EAttribute)getTimePeriod().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimephasedDataType() {
		if (timephasedDataTypeEClass == null) {
			timephasedDataTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(96);
		}
		return timephasedDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimephasedDataType_Type() {
        return (EAttribute)getTimephasedDataType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimephasedDataType_UID() {
        return (EAttribute)getTimephasedDataType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimephasedDataType_Start() {
        return (EAttribute)getTimephasedDataType().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimephasedDataType_Finish() {
        return (EAttribute)getTimephasedDataType().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimephasedDataType_Unit() {
        return (EAttribute)getTimephasedDataType().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimephasedDataType_Value() {
        return (EAttribute)getTimephasedDataType().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValue() {
		if (valueEClass == null) {
			valueEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(106);
		}
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue_ValueID() {
        return (EAttribute)getValue().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue_ParentValueID() {
        return (EAttribute)getValue().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue_Value() {
        return (EAttribute)getValue().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue_Description() {
        return (EAttribute)getValue().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValue2() {
		if (value2EClass == null) {
			value2EClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(107);
		}
		return value2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue2_ID() {
        return (EAttribute)getValue2().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue2_Value() {
        return (EAttribute)getValue2().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValue2_Description() {
        return (EAttribute)getValue2().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueList() {
		if (valueListEClass == null) {
			valueListEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(108);
		}
		return valueListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValueList_Value() {
        return (EReference)getValueList().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValues() {
		if (valuesEClass == null) {
			valuesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(110);
		}
		return valuesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValues_Group() {
        return (EAttribute)getValues().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValues_Value() {
        return (EReference)getValues().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWBSMask() {
		if (wbsMaskEClass == null) {
			wbsMaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(111);
		}
		return wbsMaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMask_Level() {
        return (EAttribute)getWBSMask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMask_Type() {
        return (EAttribute)getWBSMask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMask_Length() {
        return (EAttribute)getWBSMask().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMask_Separator() {
        return (EAttribute)getWBSMask().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWBSMasks() {
		if (wbsMasksEClass == null) {
			wbsMasksEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(112);
		}
		return wbsMasksEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMasks_VerifyUniqueCodes() {
        return (EAttribute)getWBSMasks().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMasks_GenerateCodes() {
        return (EAttribute)getWBSMasks().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWBSMasks_Prefix() {
        return (EAttribute)getWBSMasks().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWBSMasks_WBSMask() {
        return (EReference)getWBSMasks().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeekDay() {
		if (weekDayEClass == null) {
			weekDayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(113);
		}
		return weekDayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeekDay_DayType() {
        return (EAttribute)getWeekDay().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeekDay_DayWorking() {
        return (EAttribute)getWeekDay().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeekDay_TimePeriod() {
        return (EReference)getWeekDay().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeekDay_WorkingTimes() {
        return (EReference)getWeekDay().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeekDays() {
		if (weekDaysEClass == null) {
			weekDaysEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(114);
		}
		return weekDaysEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeekDays_WeekDay() {
        return (EReference)getWeekDays().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkingTime() {
		if (workingTimeEClass == null) {
			workingTimeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(119);
		}
		return workingTimeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkingTime_FromTime() {
        return (EAttribute)getWorkingTime().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkingTime_ToTime() {
        return (EAttribute)getWorkingTime().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkingTimes() {
		if (workingTimesEClass == null) {
			workingTimesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(120);
		}
		return workingTimesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkingTimes_WorkingTime() {
        return (EReference)getWorkingTimes().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAccrueAt() {
		if (accrueAtEDataType == null) {
			accrueAtEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(0);
		}
		return accrueAtEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getActiveDirectoryGUIDType() {
		if (activeDirectoryGUIDTypeEDataType == null) {
			activeDirectoryGUIDTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(1);
		}
		return activeDirectoryGUIDTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAliasType() {
		if (aliasTypeEDataType == null) {
			aliasTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(2);
		}
		return aliasTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAuthorType() {
		if (authorTypeEDataType == null) {
			authorTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(5);
		}
		return authorTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBaselineForEarnedValueType() {
		if (baselineForEarnedValueTypeEDataType == null) {
			baselineForEarnedValueTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(11);
		}
		return baselineForEarnedValueTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBookingTypeType() {
		if (bookingTypeTypeEDataType == null) {
			bookingTypeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(12);
		}
		return bookingTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBookingTypeType1() {
		if (bookingTypeType1EDataType == null) {
			bookingTypeType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(13);
		}
		return bookingTypeType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCalculationTypeType() {
		if (calculationTypeTypeEDataType == null) {
			calculationTypeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(14);
		}
		return calculationTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCategoryType() {
		if (categoryTypeEDataType == null) {
			categoryTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(17);
		}
		return categoryTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCodeType() {
		if (codeTypeEDataType == null) {
			codeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(18);
		}
		return codeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCompanyType() {
		if (companyTypeEDataType == null) {
			companyTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(19);
		}
		return companyTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getConstraintTypeType() {
		if (constraintTypeTypeEDataType == null) {
			constraintTypeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(20);
		}
		return constraintTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getContactType() {
		if (contactTypeEDataType == null) {
			contactTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(21);
		}
		return contactTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCostRateTableType() {
		if (costRateTableTypeEDataType == null) {
			costRateTableTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(22);
		}
		return costRateTableTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCurrencySymbolPositionType() {
		if (currencySymbolPositionTypeEDataType == null) {
			currencySymbolPositionTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(23);
		}
		return currencySymbolPositionTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCurrencySymbolType() {
		if (currencySymbolTypeEDataType == null) {
			currencySymbolTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(24);
		}
		return currencySymbolTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDayTypeType() {
		if (dayTypeTypeEDataType == null) {
			dayTypeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(25);
		}
		return dayTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDefaultFixedCostAccrualType() {
		if (defaultFixedCostAccrualTypeEDataType == null) {
			defaultFixedCostAccrualTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(26);
		}
		return defaultFixedCostAccrualTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDefaultTaskEVMethodType() {
		if (defaultTaskEVMethodTypeEDataType == null) {
			defaultTaskEVMethodTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(27);
		}
		return defaultTaskEVMethodTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDefaultTaskTypeType() {
		if (defaultTaskTypeTypeEDataType == null) {
			defaultTaskTypeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(28);
		}
		return defaultTaskTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationFormatType() {
		if (durationFormatTypeEDataType == null) {
			durationFormatTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(30);
		}
		return durationFormatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationFormatType1() {
		if (durationFormatType1EDataType == null) {
			durationFormatType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(31);
		}
		return durationFormatType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationFormatType2() {
		if (durationFormatType2EDataType == null) {
			durationFormatType2EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(32);
		}
		return durationFormatType2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationFormatType3() {
		if (durationFormatType3EDataType == null) {
			durationFormatType3EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(33);
		}
		return durationFormatType3EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationFormatType4() {
		if (durationFormatType4EDataType == null) {
			durationFormatType4EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(34);
		}
		return durationFormatType4EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDurationFormatType5() {
		if (durationFormatType5EDataType == null) {
			durationFormatType5EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(35);
		}
		return durationFormatType5EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEarnedValueMethodType() {
		if (earnedValueMethodTypeEDataType == null) {
			earnedValueMethodTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(36);
		}
		return earnedValueMethodTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEarnedValueMethodType1() {
		if (earnedValueMethodType1EDataType == null) {
			earnedValueMethodType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(37);
		}
		return earnedValueMethodType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEmailAddressType() {
		if (emailAddressTypeEDataType == null) {
			emailAddressTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(38);
		}
		return emailAddressTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getExternalTaskProjectType() {
		if (externalTaskProjectTypeEDataType == null) {
			externalTaskProjectTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(44);
		}
		return externalTaskProjectTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFixedCostAccrualType() {
		if (fixedCostAccrualTypeEDataType == null) {
			fixedCostAccrualTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(45);
		}
		return fixedCostAccrualTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFYStartDateType() {
		if (fyStartDateTypeEDataType == null) {
			fyStartDateTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(46);
		}
		return fyStartDateTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGroupType() {
		if (groupTypeEDataType == null) {
			groupTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(47);
		}
		return groupTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkAddressType() {
		if (hyperlinkAddressTypeEDataType == null) {
			hyperlinkAddressTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(48);
		}
		return hyperlinkAddressTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkAddressType1() {
		if (hyperlinkAddressType1EDataType == null) {
			hyperlinkAddressType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(49);
		}
		return hyperlinkAddressType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkAddressType2() {
		if (hyperlinkAddressType2EDataType == null) {
			hyperlinkAddressType2EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(50);
		}
		return hyperlinkAddressType2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkSubAddressType() {
		if (hyperlinkSubAddressTypeEDataType == null) {
			hyperlinkSubAddressTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(51);
		}
		return hyperlinkSubAddressTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkSubAddressType1() {
		if (hyperlinkSubAddressType1EDataType == null) {
			hyperlinkSubAddressType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(52);
		}
		return hyperlinkSubAddressType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkSubAddressType2() {
		if (hyperlinkSubAddressType2EDataType == null) {
			hyperlinkSubAddressType2EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(53);
		}
		return hyperlinkSubAddressType2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkType() {
		if (hyperlinkTypeEDataType == null) {
			hyperlinkTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(54);
		}
		return hyperlinkTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkType1() {
		if (hyperlinkType1EDataType == null) {
			hyperlinkType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(55);
		}
		return hyperlinkType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHyperlinkType2() {
		if (hyperlinkType2EDataType == null) {
			hyperlinkType2EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(56);
		}
		return hyperlinkType2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInitialsType() {
		if (initialsTypeEDataType == null) {
			initialsTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(57);
		}
		return initialsTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLagFormatType() {
		if (lagFormatTypeEDataType == null) {
			lagFormatTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(58);
		}
		return lagFormatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLevelingDelayFormatType() {
		if (levelingDelayFormatTypeEDataType == null) {
			levelingDelayFormatTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(59);
		}
		return levelingDelayFormatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLevelingDelayFormatType1() {
		if (levelingDelayFormatType1EDataType == null) {
			levelingDelayFormatType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(60);
		}
		return levelingDelayFormatType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getManagerType() {
		if (managerTypeEDataType == null) {
			managerTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(61);
		}
		return managerTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMaterialLabelType() {
		if (materialLabelTypeEDataType == null) {
			materialLabelTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(64);
		}
		return materialLabelTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNameType() {
		if (nameTypeEDataType == null) {
			nameTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(65);
		}
		return nameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNameType1() {
		if (nameType1EDataType == null) {
			nameType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(66);
		}
		return nameType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNameType2() {
		if (nameType2EDataType == null) {
			nameType2EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(67);
		}
		return nameType2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNameType3() {
		if (nameType3EDataType == null) {
			nameType3EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(68);
		}
		return nameType3EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNewTaskStartDateType() {
		if (newTaskStartDateTypeEDataType == null) {
			newTaskStartDateTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(69);
		}
		return newTaskStartDateTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNTAccountType() {
		if (ntAccountTypeEDataType == null) {
			ntAccountTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(70);
		}
		return ntAccountTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOutlineNumberType() {
		if (outlineNumberTypeEDataType == null) {
			outlineNumberTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(75);
		}
		return outlineNumberTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOvertimeRateFormatType() {
		if (overtimeRateFormatTypeEDataType == null) {
			overtimeRateFormatTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(76);
		}
		return overtimeRateFormatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOvertimeRateFormatType1() {
		if (overtimeRateFormatType1EDataType == null) {
			overtimeRateFormatType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(77);
		}
		return overtimeRateFormatType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPhoneticAliasType() {
		if (phoneticAliasTypeEDataType == null) {
			phoneticAliasTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(78);
		}
		return phoneticAliasTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPhoneticsType() {
		if (phoneticsTypeEDataType == null) {
			phoneticsTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(79);
		}
		return phoneticsTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPrefixType() {
		if (prefixTypeEDataType == null) {
			prefixTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(81);
		}
		return prefixTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRateTableType() {
		if (rateTableTypeEDataType == null) {
			rateTableTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(85);
		}
		return rateTableTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRollupTypeType() {
		if (rollupTypeTypeEDataType == null) {
			rollupTypeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(88);
		}
		return rollupTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getStandardRateFormatType() {
		if (standardRateFormatTypeEDataType == null) {
			standardRateFormatTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(89);
		}
		return standardRateFormatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getStandardRateFormatType1() {
		if (standardRateFormatType1EDataType == null) {
			standardRateFormatType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(90);
		}
		return standardRateFormatType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSubjectType() {
		if (subjectTypeEDataType == null) {
			subjectTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(91);
		}
		return subjectTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSubprojectNameType() {
		if (subprojectNameTypeEDataType == null) {
			subprojectNameTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(92);
		}
		return subprojectNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTitleType() {
		if (titleTypeEDataType == null) {
			titleTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(97);
		}
		return titleTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType() {
		if (typeTypeEDataType == null) {
			typeTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(98);
		}
		return typeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType1() {
		if (typeType1EDataType == null) {
			typeType1EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(99);
		}
		return typeType1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType2() {
		if (typeType2EDataType == null) {
			typeType2EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(100);
		}
		return typeType2EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType3() {
		if (typeType3EDataType == null) {
			typeType3EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(101);
		}
		return typeType3EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType4() {
		if (typeType4EDataType == null) {
			typeType4EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(102);
		}
		return typeType4EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType5() {
		if (typeType5EDataType == null) {
			typeType5EDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(103);
		}
		return typeType5EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUIDType() {
		if (uidTypeEDataType == null) {
			uidTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(104);
		}
		return uidTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUnitType() {
		if (unitTypeEDataType == null) {
			unitTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(105);
		}
		return unitTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getValuelistSortOrderType() {
		if (valuelistSortOrderTypeEDataType == null) {
			valuelistSortOrderTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(109);
		}
		return valuelistSortOrderTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWeekStartDayType() {
		if (weekStartDayTypeEDataType == null) {
			weekStartDayTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(115);
		}
		return weekStartDayTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWorkContourType() {
		if (workContourTypeEDataType == null) {
			workContourTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(116);
		}
		return workContourTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWorkFormatType() {
		if (workFormatTypeEDataType == null) {
			workFormatTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(117);
		}
		return workFormatTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWorkGroupType() {
		if (workGroupTypeEDataType == null) {
			workGroupTypeEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(MsprojectPackage.eNS_URI).getEClassifiers().get(118);
		}
		return workGroupTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsprojectFactory getMsprojectFactory() {
		return (MsprojectFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded) return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: " + packageFilename);
		}
		Resource resource = new EcoreResourceFactoryImpl().createResource(URI.createURI(url.toString()));
		try {
			resource.load(null);
		}
		catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
		createResource(eNS_URI);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}
} //MsprojectPackageImpl
