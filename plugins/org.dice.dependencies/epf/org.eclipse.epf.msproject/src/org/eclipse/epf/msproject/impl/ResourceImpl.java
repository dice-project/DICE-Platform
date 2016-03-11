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
import org.eclipse.epf.msproject.AvailabilityPeriods;
import org.eclipse.epf.msproject.Baseline2;
import org.eclipse.epf.msproject.ExtendedAttribute3;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.OutlineCode3;
import org.eclipse.epf.msproject.Rates;
import org.eclipse.epf.msproject.Resource;
import org.eclipse.epf.msproject.TimephasedDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#isIsNull <em>Is Null</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getInitials <em>Initials</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getPhonetics <em>Phonetics</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getNTAccount <em>NT Account</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getMaterialLabel <em>Material Label</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCode <em>Code</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getWorkGroup <em>Work Group</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getEmailAddress <em>Email Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getHyperlink <em>Hyperlink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getHyperlinkAddress <em>Hyperlink Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getMaxUnits <em>Max Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getPeakUnits <em>Peak Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#isOverAllocated <em>Over Allocated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getAvailableFrom <em>Available From</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getAvailableTo <em>Available To</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#isCanLevel <em>Can Level</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getAccrueAt <em>Accrue At</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getRegularWork <em>Regular Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getOvertimeWork <em>Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActualWork <em>Actual Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getRemainingWork <em>Remaining Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActualOvertimeWork <em>Actual Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getPercentWorkComplete <em>Percent Work Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getStandardRate <em>Standard Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getStandardRateFormat <em>Standard Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getOvertimeRate <em>Overtime Rate</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getOvertimeRateFormat <em>Overtime Rate Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getOvertimeCost <em>Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCostPerUse <em>Cost Per Use</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActualCost <em>Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActualOvertimeCost <em>Actual Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getRemainingCost <em>Remaining Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getWorkVariance <em>Work Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCostVariance <em>Cost Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getSV <em>SV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCV <em>CV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getACWP <em>ACWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCalendarUID <em>Calendar UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getBCWP <em>BCWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#isIsGeneric <em>Is Generic</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#isIsInactive <em>Is Inactive</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#isIsEnterprise <em>Is Enterprise</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getBookingType <em>Booking Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActualWorkProtected <em>Actual Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getActiveDirectoryGUID <em>Active Directory GUID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getBaseline <em>Baseline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getOutlineCode <em>Outline Code</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getAvailabilityPeriods <em>Availability Periods</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getRates <em>Rates</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ResourceImpl#getTimephasedData <em>Timephased Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceImpl extends EObjectImpl implements Resource {
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
	 * The default value of the '{@link #getInitials() <em>Initials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitials()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIALS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitials() <em>Initials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitials()
	 * @generated
	 * @ordered
	 */
	protected String initials = INITIALS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhonetics() <em>Phonetics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhonetics()
	 * @generated
	 * @ordered
	 */
	protected static final String PHONETICS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhonetics() <em>Phonetics</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhonetics()
	 * @generated
	 * @ordered
	 */
	protected String phonetics = PHONETICS_EDEFAULT;

	/**
	 * The default value of the '{@link #getNTAccount() <em>NT Account</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNTAccount()
	 * @generated
	 * @ordered
	 */
	protected static final String NTACCOUNT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNTAccount() <em>NT Account</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNTAccount()
	 * @generated
	 * @ordered
	 */
	protected String nTAccount = NTACCOUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaterialLabel() <em>Material Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaterialLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String MATERIAL_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaterialLabel() <em>Material Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaterialLabel()
	 * @generated
	 * @ordered
	 */
	protected String materialLabel = MATERIAL_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroup() <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected String group = GROUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkGroup() <em>Work Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkGroup()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger WORK_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkGroup() <em>Work Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkGroup()
	 * @generated
	 * @ordered
	 */
	protected BigInteger workGroup = WORK_GROUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmailAddress() <em>Email Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmailAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String EMAIL_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEmailAddress() <em>Email Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmailAddress()
	 * @generated
	 * @ordered
	 */
	protected String emailAddress = EMAIL_ADDRESS_EDEFAULT;

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
	 * The default value of the '{@link #getMaxUnits() <em>Max Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxUnits()
	 * @generated
	 * @ordered
	 */
	protected static final float MAX_UNITS_EDEFAULT = 1.0F;

	/**
	 * The cached value of the '{@link #getMaxUnits() <em>Max Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxUnits()
	 * @generated
	 * @ordered
	 */
	protected float maxUnits = MAX_UNITS_EDEFAULT;

	/**
	 * This is true if the Max Units attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean maxUnitsESet = false;

	/**
	 * The default value of the '{@link #getPeakUnits() <em>Peak Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakUnits()
	 * @generated
	 * @ordered
	 */
	protected static final float PEAK_UNITS_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getPeakUnits() <em>Peak Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakUnits()
	 * @generated
	 * @ordered
	 */
	protected float peakUnits = PEAK_UNITS_EDEFAULT;

	/**
	 * This is true if the Peak Units attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean peakUnitsESet = false;

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
	 * The default value of the '{@link #getAvailableFrom() <em>Available From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableFrom()
	 * @generated
	 * @ordered
	 */
	protected static final Object AVAILABLE_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAvailableFrom() <em>Available From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableFrom()
	 * @generated
	 * @ordered
	 */
	protected Object availableFrom = AVAILABLE_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvailableTo() <em>Available To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableTo()
	 * @generated
	 * @ordered
	 */
	protected static final Object AVAILABLE_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAvailableTo() <em>Available To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailableTo()
	 * @generated
	 * @ordered
	 */
	protected Object availableTo = AVAILABLE_TO_EDEFAULT;

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
	 * The default value of the '{@link #isCanLevel() <em>Can Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanLevel()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAN_LEVEL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCanLevel() <em>Can Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCanLevel()
	 * @generated
	 * @ordered
	 */
	protected boolean canLevel = CAN_LEVEL_EDEFAULT;

	/**
	 * This is true if the Can Level attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean canLevelESet = false;

	/**
	 * The default value of the '{@link #getAccrueAt() <em>Accrue At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccrueAt()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ACCRUE_AT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAccrueAt() <em>Accrue At</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccrueAt()
	 * @generated
	 * @ordered
	 */
	protected BigInteger accrueAt = ACCRUE_AT_EDEFAULT;

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
	 * The default value of the '{@link #getStandardRate() <em>Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRate()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal STANDARD_RATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStandardRate() <em>Standard Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRate()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal standardRate = STANDARD_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStandardRateFormat() <em>Standard Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRateFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger STANDARD_RATE_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStandardRateFormat() <em>Standard Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardRateFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger standardRateFormat = STANDARD_RATE_FORMAT_EDEFAULT;

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
	 * The default value of the '{@link #getOvertimeRate() <em>Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRate()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal OVERTIME_RATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOvertimeRate() <em>Overtime Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRate()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal overtimeRate = OVERTIME_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOvertimeRateFormat() <em>Overtime Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRateFormat()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger OVERTIME_RATE_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOvertimeRateFormat() <em>Overtime Rate Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOvertimeRateFormat()
	 * @generated
	 * @ordered
	 */
	protected BigInteger overtimeRateFormat = OVERTIME_RATE_FORMAT_EDEFAULT;

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
	 * The default value of the '{@link #getCostPerUse() <em>Cost Per Use</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostPerUse()
	 * @generated
	 * @ordered
	 */
	protected static final BigDecimal COST_PER_USE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCostPerUse() <em>Cost Per Use</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostPerUse()
	 * @generated
	 * @ordered
	 */
	protected BigDecimal costPerUse = COST_PER_USE_EDEFAULT;

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
	 * The default value of the '{@link #getCostVariance() <em>Cost Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostVariance()
	 * @generated
	 * @ordered
	 */
	protected static final float COST_VARIANCE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getCostVariance() <em>Cost Variance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostVariance()
	 * @generated
	 * @ordered
	 */
	protected float costVariance = COST_VARIANCE_EDEFAULT;

	/**
	 * This is true if the Cost Variance attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean costVarianceESet = false;

	/**
	 * The default value of the '{@link #getSV() <em>SV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSV()
	 * @generated
	 * @ordered
	 */
	protected static final float SV_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getSV() <em>SV</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSV()
	 * @generated
	 * @ordered
	 */
	protected float sV = SV_EDEFAULT;

	/**
	 * This is true if the SV attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sVESet = false;

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
	 * The default value of the '{@link #isIsGeneric() <em>Is Generic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsGeneric()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_GENERIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsGeneric() <em>Is Generic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsGeneric()
	 * @generated
	 * @ordered
	 */
	protected boolean isGeneric = IS_GENERIC_EDEFAULT;

	/**
	 * This is true if the Is Generic attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isGenericESet = false;

	/**
	 * The default value of the '{@link #isIsInactive() <em>Is Inactive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInactive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INACTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInactive() <em>Is Inactive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInactive()
	 * @generated
	 * @ordered
	 */
	protected boolean isInactive = IS_INACTIVE_EDEFAULT;

	/**
	 * This is true if the Is Inactive attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isInactiveESet = false;

	/**
	 * The default value of the '{@link #isIsEnterprise() <em>Is Enterprise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEnterprise()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ENTERPRISE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsEnterprise() <em>Is Enterprise</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsEnterprise()
	 * @generated
	 * @ordered
	 */
	protected boolean isEnterprise = IS_ENTERPRISE_EDEFAULT;

	/**
	 * This is true if the Is Enterprise attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isEnterpriseESet = false;

	/**
	 * The default value of the '{@link #getBookingType() <em>Booking Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBookingType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger BOOKING_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBookingType() <em>Booking Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBookingType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger bookingType = BOOKING_TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getActiveDirectoryGUID() <em>Active Directory GUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveDirectoryGUID()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVE_DIRECTORY_GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActiveDirectoryGUID() <em>Active Directory GUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveDirectoryGUID()
	 * @generated
	 * @ordered
	 */
	protected String activeDirectoryGUID = ACTIVE_DIRECTORY_GUID_EDEFAULT;

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
	 * The cached value of the '{@link #getAvailabilityPeriods() <em>Availability Periods</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailabilityPeriods()
	 * @generated
	 * @ordered
	 */
	protected AvailabilityPeriods availabilityPeriods = null;

	/**
	 * The cached value of the '{@link #getRates() <em>Rates</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRates()
	 * @generated
	 * @ordered
	 */
	protected Rates rates = null;

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
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getResource();
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__UID, oldUID, uID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ID, oldID, iD));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__IS_NULL, oldIsNull, isNull, !oldIsNullESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__IS_NULL, oldIsNull, IS_NULL_EDEFAULT, oldIsNullESet));
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
	public String getInitials() {
		return initials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitials(String newInitials) {
		String oldInitials = initials;
		initials = newInitials;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__INITIALS, oldInitials, initials));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhonetics() {
		return phonetics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhonetics(String newPhonetics) {
		String oldPhonetics = phonetics;
		phonetics = newPhonetics;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__PHONETICS, oldPhonetics, phonetics));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNTAccount() {
		return nTAccount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNTAccount(String newNTAccount) {
		String oldNTAccount = nTAccount;
		nTAccount = newNTAccount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__NTACCOUNT, oldNTAccount, nTAccount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaterialLabel() {
		return materialLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaterialLabel(String newMaterialLabel) {
		String oldMaterialLabel = materialLabel;
		materialLabel = newMaterialLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__MATERIAL_LABEL, oldMaterialLabel, materialLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__CODE, oldCode, code));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(String newGroup) {
		String oldGroup = group;
		group = newGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__GROUP, oldGroup, group));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getWorkGroup() {
		return workGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkGroup(BigInteger newWorkGroup) {
		BigInteger oldWorkGroup = workGroup;
		workGroup = newWorkGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__WORK_GROUP, oldWorkGroup, workGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEmailAddress(String newEmailAddress) {
		String oldEmailAddress = emailAddress;
		emailAddress = newEmailAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__EMAIL_ADDRESS, oldEmailAddress, emailAddress));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__HYPERLINK, oldHyperlink, hyperlink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__HYPERLINK_ADDRESS, oldHyperlinkAddress, hyperlinkAddress));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__HYPERLINK_SUB_ADDRESS, oldHyperlinkSubAddress, hyperlinkSubAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getMaxUnits() {
		return maxUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxUnits(float newMaxUnits) {
		float oldMaxUnits = maxUnits;
		maxUnits = newMaxUnits;
		boolean oldMaxUnitsESet = maxUnitsESet;
		maxUnitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__MAX_UNITS, oldMaxUnits, maxUnits, !oldMaxUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMaxUnits() {
		float oldMaxUnits = maxUnits;
		boolean oldMaxUnitsESet = maxUnitsESet;
		maxUnits = MAX_UNITS_EDEFAULT;
		maxUnitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__MAX_UNITS, oldMaxUnits, MAX_UNITS_EDEFAULT, oldMaxUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMaxUnits() {
		return maxUnitsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPeakUnits() {
		return peakUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeakUnits(float newPeakUnits) {
		float oldPeakUnits = peakUnits;
		peakUnits = newPeakUnits;
		boolean oldPeakUnitsESet = peakUnitsESet;
		peakUnitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__PEAK_UNITS, oldPeakUnits, peakUnits, !oldPeakUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPeakUnits() {
		float oldPeakUnits = peakUnits;
		boolean oldPeakUnitsESet = peakUnitsESet;
		peakUnits = PEAK_UNITS_EDEFAULT;
		peakUnitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__PEAK_UNITS, oldPeakUnits, PEAK_UNITS_EDEFAULT, oldPeakUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPeakUnits() {
		return peakUnitsESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__OVER_ALLOCATED, oldOverAllocated, overAllocated, !oldOverAllocatedESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__OVER_ALLOCATED, oldOverAllocated, OVER_ALLOCATED_EDEFAULT, oldOverAllocatedESet));
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
	public Object getAvailableFrom() {
		return availableFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailableFrom(Object newAvailableFrom) {
		Object oldAvailableFrom = availableFrom;
		availableFrom = newAvailableFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__AVAILABLE_FROM, oldAvailableFrom, availableFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getAvailableTo() {
		return availableTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailableTo(Object newAvailableTo) {
		Object oldAvailableTo = availableTo;
		availableTo = newAvailableTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__AVAILABLE_TO, oldAvailableTo, availableTo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__START, oldStart, start));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__FINISH, oldFinish, finish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCanLevel() {
		return canLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCanLevel(boolean newCanLevel) {
		boolean oldCanLevel = canLevel;
		canLevel = newCanLevel;
		boolean oldCanLevelESet = canLevelESet;
		canLevelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__CAN_LEVEL, oldCanLevel, canLevel, !oldCanLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCanLevel() {
		boolean oldCanLevel = canLevel;
		boolean oldCanLevelESet = canLevelESet;
		canLevel = CAN_LEVEL_EDEFAULT;
		canLevelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__CAN_LEVEL, oldCanLevel, CAN_LEVEL_EDEFAULT, oldCanLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCanLevel() {
		return canLevelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getAccrueAt() {
		return accrueAt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccrueAt(BigInteger newAccrueAt) {
		BigInteger oldAccrueAt = accrueAt;
		accrueAt = newAccrueAt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACCRUE_AT, oldAccrueAt, accrueAt));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__WORK, oldWork, work));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__REGULAR_WORK, oldRegularWork, regularWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__OVERTIME_WORK, oldOvertimeWork, overtimeWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTUAL_WORK, oldActualWork, actualWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__REMAINING_WORK, oldRemainingWork, remainingWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK, oldActualOvertimeWork, actualOvertimeWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__REMAINING_OVERTIME_WORK, oldRemainingOvertimeWork, remainingOvertimeWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__PERCENT_WORK_COMPLETE, oldPercentWorkComplete, percentWorkComplete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getStandardRate() {
		return standardRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardRate(BigDecimal newStandardRate) {
		BigDecimal oldStandardRate = standardRate;
		standardRate = newStandardRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__STANDARD_RATE, oldStandardRate, standardRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getStandardRateFormat() {
		return standardRateFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardRateFormat(BigInteger newStandardRateFormat) {
		BigInteger oldStandardRateFormat = standardRateFormat;
		standardRateFormat = newStandardRateFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__STANDARD_RATE_FORMAT, oldStandardRateFormat, standardRateFormat));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__COST, oldCost, cost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getOvertimeRate() {
		return overtimeRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertimeRate(BigDecimal newOvertimeRate) {
		BigDecimal oldOvertimeRate = overtimeRate;
		overtimeRate = newOvertimeRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__OVERTIME_RATE, oldOvertimeRate, overtimeRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getOvertimeRateFormat() {
		return overtimeRateFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOvertimeRateFormat(BigInteger newOvertimeRateFormat) {
		BigInteger oldOvertimeRateFormat = overtimeRateFormat;
		overtimeRateFormat = newOvertimeRateFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__OVERTIME_RATE_FORMAT, oldOvertimeRateFormat, overtimeRateFormat));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__OVERTIME_COST, oldOvertimeCost, overtimeCost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getCostPerUse() {
		return costPerUse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostPerUse(BigDecimal newCostPerUse) {
		BigDecimal oldCostPerUse = costPerUse;
		costPerUse = newCostPerUse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__COST_PER_USE, oldCostPerUse, costPerUse));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTUAL_COST, oldActualCost, actualCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_COST, oldActualOvertimeCost, actualOvertimeCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__REMAINING_COST, oldRemainingCost, remainingCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__REMAINING_OVERTIME_COST, oldRemainingOvertimeCost, remainingOvertimeCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__WORK_VARIANCE, oldWorkVariance, workVariance, !oldWorkVarianceESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__WORK_VARIANCE, oldWorkVariance, WORK_VARIANCE_EDEFAULT, oldWorkVarianceESet));
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
	public float getCostVariance() {
		return costVariance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostVariance(float newCostVariance) {
		float oldCostVariance = costVariance;
		costVariance = newCostVariance;
		boolean oldCostVarianceESet = costVarianceESet;
		costVarianceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__COST_VARIANCE, oldCostVariance, costVariance, !oldCostVarianceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCostVariance() {
		float oldCostVariance = costVariance;
		boolean oldCostVarianceESet = costVarianceESet;
		costVariance = COST_VARIANCE_EDEFAULT;
		costVarianceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__COST_VARIANCE, oldCostVariance, COST_VARIANCE_EDEFAULT, oldCostVarianceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCostVariance() {
		return costVarianceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getSV() {
		return sV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSV(float newSV) {
		float oldSV = sV;
		sV = newSV;
		boolean oldSVESet = sVESet;
		sVESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__SV, oldSV, sV, !oldSVESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSV() {
		float oldSV = sV;
		boolean oldSVESet = sVESet;
		sV = SV_EDEFAULT;
		sVESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__SV, oldSV, SV_EDEFAULT, oldSVESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSV() {
		return sVESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__CV, oldCV, cV, !oldCVESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__CV, oldCV, CV_EDEFAULT, oldCVESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACWP, oldACWP, aCWP, !oldACWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__ACWP, oldACWP, ACWP_EDEFAULT, oldACWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__CALENDAR_UID, oldCalendarUID, calendarUID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__BCWS, oldBCWS, bCWS, !oldBCWSESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__BCWS, oldBCWS, BCWS_EDEFAULT, oldBCWSESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__BCWP, oldBCWP, bCWP, !oldBCWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__BCWP, oldBCWP, BCWP_EDEFAULT, oldBCWPESet));
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
	public boolean isIsGeneric() {
		return isGeneric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsGeneric(boolean newIsGeneric) {
		boolean oldIsGeneric = isGeneric;
		isGeneric = newIsGeneric;
		boolean oldIsGenericESet = isGenericESet;
		isGenericESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__IS_GENERIC, oldIsGeneric, isGeneric, !oldIsGenericESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsGeneric() {
		boolean oldIsGeneric = isGeneric;
		boolean oldIsGenericESet = isGenericESet;
		isGeneric = IS_GENERIC_EDEFAULT;
		isGenericESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__IS_GENERIC, oldIsGeneric, IS_GENERIC_EDEFAULT, oldIsGenericESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsGeneric() {
		return isGenericESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsInactive() {
		return isInactive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsInactive(boolean newIsInactive) {
		boolean oldIsInactive = isInactive;
		isInactive = newIsInactive;
		boolean oldIsInactiveESet = isInactiveESet;
		isInactiveESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__IS_INACTIVE, oldIsInactive, isInactive, !oldIsInactiveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsInactive() {
		boolean oldIsInactive = isInactive;
		boolean oldIsInactiveESet = isInactiveESet;
		isInactive = IS_INACTIVE_EDEFAULT;
		isInactiveESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__IS_INACTIVE, oldIsInactive, IS_INACTIVE_EDEFAULT, oldIsInactiveESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsInactive() {
		return isInactiveESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsEnterprise() {
		return isEnterprise;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsEnterprise(boolean newIsEnterprise) {
		boolean oldIsEnterprise = isEnterprise;
		isEnterprise = newIsEnterprise;
		boolean oldIsEnterpriseESet = isEnterpriseESet;
		isEnterpriseESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__IS_ENTERPRISE, oldIsEnterprise, isEnterprise, !oldIsEnterpriseESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsEnterprise() {
		boolean oldIsEnterprise = isEnterprise;
		boolean oldIsEnterpriseESet = isEnterpriseESet;
		isEnterprise = IS_ENTERPRISE_EDEFAULT;
		isEnterpriseESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.RESOURCE__IS_ENTERPRISE, oldIsEnterprise, IS_ENTERPRISE_EDEFAULT, oldIsEnterpriseESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsEnterprise() {
		return isEnterpriseESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBookingType() {
		return bookingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBookingType(BigInteger newBookingType) {
		BigInteger oldBookingType = bookingType;
		bookingType = newBookingType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__BOOKING_TYPE, oldBookingType, bookingType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTUAL_WORK_PROTECTED, oldActualWorkProtected, actualWorkProtected));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK_PROTECTED, oldActualOvertimeWorkProtected, actualOvertimeWorkProtected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActiveDirectoryGUID() {
		return activeDirectoryGUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveDirectoryGUID(String newActiveDirectoryGUID) {
		String oldActiveDirectoryGUID = activeDirectoryGUID;
		activeDirectoryGUID = newActiveDirectoryGUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__ACTIVE_DIRECTORY_GUID, oldActiveDirectoryGUID, activeDirectoryGUID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedAttribute() {
		if (extendedAttribute == null) {
			extendedAttribute = new EObjectContainmentEList(ExtendedAttribute3.class, this, MsprojectPackage.RESOURCE__EXTENDED_ATTRIBUTE);
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
			baseline = new EObjectContainmentEList(Baseline2.class, this, MsprojectPackage.RESOURCE__BASELINE);
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
			outlineCode = new EObjectContainmentEList(OutlineCode3.class, this, MsprojectPackage.RESOURCE__OUTLINE_CODE);
		}
		return outlineCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AvailabilityPeriods getAvailabilityPeriods() {
		return availabilityPeriods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAvailabilityPeriods(AvailabilityPeriods newAvailabilityPeriods, NotificationChain msgs) {
		AvailabilityPeriods oldAvailabilityPeriods = availabilityPeriods;
		availabilityPeriods = newAvailabilityPeriods;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS, oldAvailabilityPeriods, newAvailabilityPeriods);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailabilityPeriods(AvailabilityPeriods newAvailabilityPeriods) {
		if (newAvailabilityPeriods != availabilityPeriods) {
			NotificationChain msgs = null;
			if (availabilityPeriods != null)
				msgs = ((InternalEObject)availabilityPeriods).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS, null, msgs);
			if (newAvailabilityPeriods != null)
				msgs = ((InternalEObject)newAvailabilityPeriods).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS, null, msgs);
			msgs = basicSetAvailabilityPeriods(newAvailabilityPeriods, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS, newAvailabilityPeriods, newAvailabilityPeriods));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rates getRates() {
		return rates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRates(Rates newRates, NotificationChain msgs) {
		Rates oldRates = rates;
		rates = newRates;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__RATES, oldRates, newRates);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRates(Rates newRates) {
		if (newRates != rates) {
			NotificationChain msgs = null;
			if (rates != null)
				msgs = ((InternalEObject)rates).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.RESOURCE__RATES, null, msgs);
			if (newRates != null)
				msgs = ((InternalEObject)newRates).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.RESOURCE__RATES, null, msgs);
			msgs = basicSetRates(newRates, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.RESOURCE__RATES, newRates, newRates));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTimephasedData() {
		if (timephasedData == null) {
			timephasedData = new EObjectContainmentEList(TimephasedDataType.class, this, MsprojectPackage.RESOURCE__TIMEPHASED_DATA);
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
				case MsprojectPackage.RESOURCE__EXTENDED_ATTRIBUTE:
					return ((InternalEList)getExtendedAttribute()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.RESOURCE__BASELINE:
					return ((InternalEList)getBaseline()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.RESOURCE__OUTLINE_CODE:
					return ((InternalEList)getOutlineCode()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS:
					return basicSetAvailabilityPeriods(null, msgs);
				case MsprojectPackage.RESOURCE__RATES:
					return basicSetRates(null, msgs);
				case MsprojectPackage.RESOURCE__TIMEPHASED_DATA:
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
			case MsprojectPackage.RESOURCE__UID:
				return getUID();
			case MsprojectPackage.RESOURCE__ID:
				return getID();
			case MsprojectPackage.RESOURCE__NAME:
				return getName();
			case MsprojectPackage.RESOURCE__TYPE:
				return getType();
			case MsprojectPackage.RESOURCE__IS_NULL:
				return isIsNull() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.RESOURCE__INITIALS:
				return getInitials();
			case MsprojectPackage.RESOURCE__PHONETICS:
				return getPhonetics();
			case MsprojectPackage.RESOURCE__NTACCOUNT:
				return getNTAccount();
			case MsprojectPackage.RESOURCE__MATERIAL_LABEL:
				return getMaterialLabel();
			case MsprojectPackage.RESOURCE__CODE:
				return getCode();
			case MsprojectPackage.RESOURCE__GROUP:
				return getGroup();
			case MsprojectPackage.RESOURCE__WORK_GROUP:
				return getWorkGroup();
			case MsprojectPackage.RESOURCE__EMAIL_ADDRESS:
				return getEmailAddress();
			case MsprojectPackage.RESOURCE__HYPERLINK:
				return getHyperlink();
			case MsprojectPackage.RESOURCE__HYPERLINK_ADDRESS:
				return getHyperlinkAddress();
			case MsprojectPackage.RESOURCE__HYPERLINK_SUB_ADDRESS:
				return getHyperlinkSubAddress();
			case MsprojectPackage.RESOURCE__MAX_UNITS:
				return new Float(getMaxUnits());
			case MsprojectPackage.RESOURCE__PEAK_UNITS:
				return new Float(getPeakUnits());
			case MsprojectPackage.RESOURCE__OVER_ALLOCATED:
				return isOverAllocated() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.RESOURCE__AVAILABLE_FROM:
				return getAvailableFrom();
			case MsprojectPackage.RESOURCE__AVAILABLE_TO:
				return getAvailableTo();
			case MsprojectPackage.RESOURCE__START:
				return getStart();
			case MsprojectPackage.RESOURCE__FINISH:
				return getFinish();
			case MsprojectPackage.RESOURCE__CAN_LEVEL:
				return isCanLevel() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.RESOURCE__ACCRUE_AT:
				return getAccrueAt();
			case MsprojectPackage.RESOURCE__WORK:
				return getWork();
			case MsprojectPackage.RESOURCE__REGULAR_WORK:
				return getRegularWork();
			case MsprojectPackage.RESOURCE__OVERTIME_WORK:
				return getOvertimeWork();
			case MsprojectPackage.RESOURCE__ACTUAL_WORK:
				return getActualWork();
			case MsprojectPackage.RESOURCE__REMAINING_WORK:
				return getRemainingWork();
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK:
				return getActualOvertimeWork();
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_WORK:
				return getRemainingOvertimeWork();
			case MsprojectPackage.RESOURCE__PERCENT_WORK_COMPLETE:
				return getPercentWorkComplete();
			case MsprojectPackage.RESOURCE__STANDARD_RATE:
				return getStandardRate();
			case MsprojectPackage.RESOURCE__STANDARD_RATE_FORMAT:
				return getStandardRateFormat();
			case MsprojectPackage.RESOURCE__COST:
				return getCost();
			case MsprojectPackage.RESOURCE__OVERTIME_RATE:
				return getOvertimeRate();
			case MsprojectPackage.RESOURCE__OVERTIME_RATE_FORMAT:
				return getOvertimeRateFormat();
			case MsprojectPackage.RESOURCE__OVERTIME_COST:
				return getOvertimeCost();
			case MsprojectPackage.RESOURCE__COST_PER_USE:
				return getCostPerUse();
			case MsprojectPackage.RESOURCE__ACTUAL_COST:
				return getActualCost();
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_COST:
				return getActualOvertimeCost();
			case MsprojectPackage.RESOURCE__REMAINING_COST:
				return getRemainingCost();
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_COST:
				return getRemainingOvertimeCost();
			case MsprojectPackage.RESOURCE__WORK_VARIANCE:
				return new Float(getWorkVariance());
			case MsprojectPackage.RESOURCE__COST_VARIANCE:
				return new Float(getCostVariance());
			case MsprojectPackage.RESOURCE__SV:
				return new Float(getSV());
			case MsprojectPackage.RESOURCE__CV:
				return new Float(getCV());
			case MsprojectPackage.RESOURCE__ACWP:
				return new Float(getACWP());
			case MsprojectPackage.RESOURCE__CALENDAR_UID:
				return getCalendarUID();
			case MsprojectPackage.RESOURCE__NOTES:
				return getNotes();
			case MsprojectPackage.RESOURCE__BCWS:
				return new Float(getBCWS());
			case MsprojectPackage.RESOURCE__BCWP:
				return new Float(getBCWP());
			case MsprojectPackage.RESOURCE__IS_GENERIC:
				return isIsGeneric() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.RESOURCE__IS_INACTIVE:
				return isIsInactive() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.RESOURCE__IS_ENTERPRISE:
				return isIsEnterprise() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.RESOURCE__BOOKING_TYPE:
				return getBookingType();
			case MsprojectPackage.RESOURCE__ACTUAL_WORK_PROTECTED:
				return getActualWorkProtected();
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK_PROTECTED:
				return getActualOvertimeWorkProtected();
			case MsprojectPackage.RESOURCE__ACTIVE_DIRECTORY_GUID:
				return getActiveDirectoryGUID();
			case MsprojectPackage.RESOURCE__CREATION_DATE:
				return getCreationDate();
			case MsprojectPackage.RESOURCE__EXTENDED_ATTRIBUTE:
				return getExtendedAttribute();
			case MsprojectPackage.RESOURCE__BASELINE:
				return getBaseline();
			case MsprojectPackage.RESOURCE__OUTLINE_CODE:
				return getOutlineCode();
			case MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS:
				return getAvailabilityPeriods();
			case MsprojectPackage.RESOURCE__RATES:
				return getRates();
			case MsprojectPackage.RESOURCE__TIMEPHASED_DATA:
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
			case MsprojectPackage.RESOURCE__UID:
				setUID((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__ID:
				setID((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__NAME:
				setName((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__TYPE:
				setType((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__IS_NULL:
				setIsNull(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.RESOURCE__INITIALS:
				setInitials((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__PHONETICS:
				setPhonetics((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__NTACCOUNT:
				setNTAccount((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__MATERIAL_LABEL:
				setMaterialLabel((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__CODE:
				setCode((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__GROUP:
				setGroup((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__WORK_GROUP:
				setWorkGroup((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__EMAIL_ADDRESS:
				setEmailAddress((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__HYPERLINK:
				setHyperlink((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__HYPERLINK_ADDRESS:
				setHyperlinkAddress((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__HYPERLINK_SUB_ADDRESS:
				setHyperlinkSubAddress((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__MAX_UNITS:
				setMaxUnits(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__PEAK_UNITS:
				setPeakUnits(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__OVER_ALLOCATED:
				setOverAllocated(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.RESOURCE__AVAILABLE_FROM:
				setAvailableFrom((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__AVAILABLE_TO:
				setAvailableTo((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__START:
				setStart((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__FINISH:
				setFinish((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__CAN_LEVEL:
				setCanLevel(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.RESOURCE__ACCRUE_AT:
				setAccrueAt((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__WORK:
				setWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__REGULAR_WORK:
				setRegularWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_WORK:
				setOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_WORK:
				setActualWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_WORK:
				setRemainingWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK:
				setActualOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_WORK:
				setRemainingOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__PERCENT_WORK_COMPLETE:
				setPercentWorkComplete((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__STANDARD_RATE:
				setStandardRate((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__STANDARD_RATE_FORMAT:
				setStandardRateFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__COST:
				setCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_RATE:
				setOvertimeRate((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_RATE_FORMAT:
				setOvertimeRateFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_COST:
				setOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__COST_PER_USE:
				setCostPerUse((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_COST:
				setActualCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_COST:
				setActualOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_COST:
				setRemainingCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_COST:
				setRemainingOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.RESOURCE__WORK_VARIANCE:
				setWorkVariance(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__COST_VARIANCE:
				setCostVariance(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__SV:
				setSV(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__CV:
				setCV(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__ACWP:
				setACWP(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__CALENDAR_UID:
				setCalendarUID((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__NOTES:
				setNotes((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__BCWS:
				setBCWS(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__BCWP:
				setBCWP(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.RESOURCE__IS_GENERIC:
				setIsGeneric(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.RESOURCE__IS_INACTIVE:
				setIsInactive(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.RESOURCE__IS_ENTERPRISE:
				setIsEnterprise(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.RESOURCE__BOOKING_TYPE:
				setBookingType((BigInteger)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_WORK_PROTECTED:
				setActualWorkProtected((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK_PROTECTED:
				setActualOvertimeWorkProtected((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__ACTIVE_DIRECTORY_GUID:
				setActiveDirectoryGUID((String)newValue);
				return;
			case MsprojectPackage.RESOURCE__CREATION_DATE:
				setCreationDate((Object)newValue);
				return;
			case MsprojectPackage.RESOURCE__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				getExtendedAttribute().addAll((Collection)newValue);
				return;
			case MsprojectPackage.RESOURCE__BASELINE:
				getBaseline().clear();
				getBaseline().addAll((Collection)newValue);
				return;
			case MsprojectPackage.RESOURCE__OUTLINE_CODE:
				getOutlineCode().clear();
				getOutlineCode().addAll((Collection)newValue);
				return;
			case MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS:
				setAvailabilityPeriods((AvailabilityPeriods)newValue);
				return;
			case MsprojectPackage.RESOURCE__RATES:
				setRates((Rates)newValue);
				return;
			case MsprojectPackage.RESOURCE__TIMEPHASED_DATA:
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
			case MsprojectPackage.RESOURCE__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ID:
				setID(ID_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__IS_NULL:
				unsetIsNull();
				return;
			case MsprojectPackage.RESOURCE__INITIALS:
				setInitials(INITIALS_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__PHONETICS:
				setPhonetics(PHONETICS_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__NTACCOUNT:
				setNTAccount(NTACCOUNT_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__MATERIAL_LABEL:
				setMaterialLabel(MATERIAL_LABEL_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__CODE:
				setCode(CODE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__GROUP:
				setGroup(GROUP_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__WORK_GROUP:
				setWorkGroup(WORK_GROUP_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__EMAIL_ADDRESS:
				setEmailAddress(EMAIL_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__HYPERLINK:
				setHyperlink(HYPERLINK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__HYPERLINK_ADDRESS:
				setHyperlinkAddress(HYPERLINK_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__HYPERLINK_SUB_ADDRESS:
				setHyperlinkSubAddress(HYPERLINK_SUB_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__MAX_UNITS:
				unsetMaxUnits();
				return;
			case MsprojectPackage.RESOURCE__PEAK_UNITS:
				unsetPeakUnits();
				return;
			case MsprojectPackage.RESOURCE__OVER_ALLOCATED:
				unsetOverAllocated();
				return;
			case MsprojectPackage.RESOURCE__AVAILABLE_FROM:
				setAvailableFrom(AVAILABLE_FROM_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__AVAILABLE_TO:
				setAvailableTo(AVAILABLE_TO_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__START:
				setStart(START_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__FINISH:
				setFinish(FINISH_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__CAN_LEVEL:
				unsetCanLevel();
				return;
			case MsprojectPackage.RESOURCE__ACCRUE_AT:
				setAccrueAt(ACCRUE_AT_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__WORK:
				setWork(WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__REGULAR_WORK:
				setRegularWork(REGULAR_WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_WORK:
				setOvertimeWork(OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_WORK:
				setActualWork(ACTUAL_WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_WORK:
				setRemainingWork(REMAINING_WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK:
				setActualOvertimeWork(ACTUAL_OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_WORK:
				setRemainingOvertimeWork(REMAINING_OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__PERCENT_WORK_COMPLETE:
				setPercentWorkComplete(PERCENT_WORK_COMPLETE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__STANDARD_RATE:
				setStandardRate(STANDARD_RATE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__STANDARD_RATE_FORMAT:
				setStandardRateFormat(STANDARD_RATE_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__COST:
				setCost(COST_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_RATE:
				setOvertimeRate(OVERTIME_RATE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_RATE_FORMAT:
				setOvertimeRateFormat(OVERTIME_RATE_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__OVERTIME_COST:
				setOvertimeCost(OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__COST_PER_USE:
				setCostPerUse(COST_PER_USE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_COST:
				setActualCost(ACTUAL_COST_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_COST:
				setActualOvertimeCost(ACTUAL_OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_COST:
				setRemainingCost(REMAINING_COST_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_COST:
				setRemainingOvertimeCost(REMAINING_OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__WORK_VARIANCE:
				unsetWorkVariance();
				return;
			case MsprojectPackage.RESOURCE__COST_VARIANCE:
				unsetCostVariance();
				return;
			case MsprojectPackage.RESOURCE__SV:
				unsetSV();
				return;
			case MsprojectPackage.RESOURCE__CV:
				unsetCV();
				return;
			case MsprojectPackage.RESOURCE__ACWP:
				unsetACWP();
				return;
			case MsprojectPackage.RESOURCE__CALENDAR_UID:
				setCalendarUID(CALENDAR_UID_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__NOTES:
				setNotes(NOTES_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__BCWS:
				unsetBCWS();
				return;
			case MsprojectPackage.RESOURCE__BCWP:
				unsetBCWP();
				return;
			case MsprojectPackage.RESOURCE__IS_GENERIC:
				unsetIsGeneric();
				return;
			case MsprojectPackage.RESOURCE__IS_INACTIVE:
				unsetIsInactive();
				return;
			case MsprojectPackage.RESOURCE__IS_ENTERPRISE:
				unsetIsEnterprise();
				return;
			case MsprojectPackage.RESOURCE__BOOKING_TYPE:
				setBookingType(BOOKING_TYPE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_WORK_PROTECTED:
				setActualWorkProtected(ACTUAL_WORK_PROTECTED_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK_PROTECTED:
				setActualOvertimeWorkProtected(ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__ACTIVE_DIRECTORY_GUID:
				setActiveDirectoryGUID(ACTIVE_DIRECTORY_GUID_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case MsprojectPackage.RESOURCE__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				return;
			case MsprojectPackage.RESOURCE__BASELINE:
				getBaseline().clear();
				return;
			case MsprojectPackage.RESOURCE__OUTLINE_CODE:
				getOutlineCode().clear();
				return;
			case MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS:
				setAvailabilityPeriods((AvailabilityPeriods)null);
				return;
			case MsprojectPackage.RESOURCE__RATES:
				setRates((Rates)null);
				return;
			case MsprojectPackage.RESOURCE__TIMEPHASED_DATA:
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
			case MsprojectPackage.RESOURCE__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.RESOURCE__ID:
				return ID_EDEFAULT == null ? iD != null : !ID_EDEFAULT.equals(iD);
			case MsprojectPackage.RESOURCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MsprojectPackage.RESOURCE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case MsprojectPackage.RESOURCE__IS_NULL:
				return isSetIsNull();
			case MsprojectPackage.RESOURCE__INITIALS:
				return INITIALS_EDEFAULT == null ? initials != null : !INITIALS_EDEFAULT.equals(initials);
			case MsprojectPackage.RESOURCE__PHONETICS:
				return PHONETICS_EDEFAULT == null ? phonetics != null : !PHONETICS_EDEFAULT.equals(phonetics);
			case MsprojectPackage.RESOURCE__NTACCOUNT:
				return NTACCOUNT_EDEFAULT == null ? nTAccount != null : !NTACCOUNT_EDEFAULT.equals(nTAccount);
			case MsprojectPackage.RESOURCE__MATERIAL_LABEL:
				return MATERIAL_LABEL_EDEFAULT == null ? materialLabel != null : !MATERIAL_LABEL_EDEFAULT.equals(materialLabel);
			case MsprojectPackage.RESOURCE__CODE:
				return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
			case MsprojectPackage.RESOURCE__GROUP:
				return GROUP_EDEFAULT == null ? group != null : !GROUP_EDEFAULT.equals(group);
			case MsprojectPackage.RESOURCE__WORK_GROUP:
				return WORK_GROUP_EDEFAULT == null ? workGroup != null : !WORK_GROUP_EDEFAULT.equals(workGroup);
			case MsprojectPackage.RESOURCE__EMAIL_ADDRESS:
				return EMAIL_ADDRESS_EDEFAULT == null ? emailAddress != null : !EMAIL_ADDRESS_EDEFAULT.equals(emailAddress);
			case MsprojectPackage.RESOURCE__HYPERLINK:
				return HYPERLINK_EDEFAULT == null ? hyperlink != null : !HYPERLINK_EDEFAULT.equals(hyperlink);
			case MsprojectPackage.RESOURCE__HYPERLINK_ADDRESS:
				return HYPERLINK_ADDRESS_EDEFAULT == null ? hyperlinkAddress != null : !HYPERLINK_ADDRESS_EDEFAULT.equals(hyperlinkAddress);
			case MsprojectPackage.RESOURCE__HYPERLINK_SUB_ADDRESS:
				return HYPERLINK_SUB_ADDRESS_EDEFAULT == null ? hyperlinkSubAddress != null : !HYPERLINK_SUB_ADDRESS_EDEFAULT.equals(hyperlinkSubAddress);
			case MsprojectPackage.RESOURCE__MAX_UNITS:
				return isSetMaxUnits();
			case MsprojectPackage.RESOURCE__PEAK_UNITS:
				return isSetPeakUnits();
			case MsprojectPackage.RESOURCE__OVER_ALLOCATED:
				return isSetOverAllocated();
			case MsprojectPackage.RESOURCE__AVAILABLE_FROM:
				return AVAILABLE_FROM_EDEFAULT == null ? availableFrom != null : !AVAILABLE_FROM_EDEFAULT.equals(availableFrom);
			case MsprojectPackage.RESOURCE__AVAILABLE_TO:
				return AVAILABLE_TO_EDEFAULT == null ? availableTo != null : !AVAILABLE_TO_EDEFAULT.equals(availableTo);
			case MsprojectPackage.RESOURCE__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case MsprojectPackage.RESOURCE__FINISH:
				return FINISH_EDEFAULT == null ? finish != null : !FINISH_EDEFAULT.equals(finish);
			case MsprojectPackage.RESOURCE__CAN_LEVEL:
				return isSetCanLevel();
			case MsprojectPackage.RESOURCE__ACCRUE_AT:
				return ACCRUE_AT_EDEFAULT == null ? accrueAt != null : !ACCRUE_AT_EDEFAULT.equals(accrueAt);
			case MsprojectPackage.RESOURCE__WORK:
				return WORK_EDEFAULT == null ? work != null : !WORK_EDEFAULT.equals(work);
			case MsprojectPackage.RESOURCE__REGULAR_WORK:
				return REGULAR_WORK_EDEFAULT == null ? regularWork != null : !REGULAR_WORK_EDEFAULT.equals(regularWork);
			case MsprojectPackage.RESOURCE__OVERTIME_WORK:
				return OVERTIME_WORK_EDEFAULT == null ? overtimeWork != null : !OVERTIME_WORK_EDEFAULT.equals(overtimeWork);
			case MsprojectPackage.RESOURCE__ACTUAL_WORK:
				return ACTUAL_WORK_EDEFAULT == null ? actualWork != null : !ACTUAL_WORK_EDEFAULT.equals(actualWork);
			case MsprojectPackage.RESOURCE__REMAINING_WORK:
				return REMAINING_WORK_EDEFAULT == null ? remainingWork != null : !REMAINING_WORK_EDEFAULT.equals(remainingWork);
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK:
				return ACTUAL_OVERTIME_WORK_EDEFAULT == null ? actualOvertimeWork != null : !ACTUAL_OVERTIME_WORK_EDEFAULT.equals(actualOvertimeWork);
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_WORK:
				return REMAINING_OVERTIME_WORK_EDEFAULT == null ? remainingOvertimeWork != null : !REMAINING_OVERTIME_WORK_EDEFAULT.equals(remainingOvertimeWork);
			case MsprojectPackage.RESOURCE__PERCENT_WORK_COMPLETE:
				return PERCENT_WORK_COMPLETE_EDEFAULT == null ? percentWorkComplete != null : !PERCENT_WORK_COMPLETE_EDEFAULT.equals(percentWorkComplete);
			case MsprojectPackage.RESOURCE__STANDARD_RATE:
				return STANDARD_RATE_EDEFAULT == null ? standardRate != null : !STANDARD_RATE_EDEFAULT.equals(standardRate);
			case MsprojectPackage.RESOURCE__STANDARD_RATE_FORMAT:
				return STANDARD_RATE_FORMAT_EDEFAULT == null ? standardRateFormat != null : !STANDARD_RATE_FORMAT_EDEFAULT.equals(standardRateFormat);
			case MsprojectPackage.RESOURCE__COST:
				return COST_EDEFAULT == null ? cost != null : !COST_EDEFAULT.equals(cost);
			case MsprojectPackage.RESOURCE__OVERTIME_RATE:
				return OVERTIME_RATE_EDEFAULT == null ? overtimeRate != null : !OVERTIME_RATE_EDEFAULT.equals(overtimeRate);
			case MsprojectPackage.RESOURCE__OVERTIME_RATE_FORMAT:
				return OVERTIME_RATE_FORMAT_EDEFAULT == null ? overtimeRateFormat != null : !OVERTIME_RATE_FORMAT_EDEFAULT.equals(overtimeRateFormat);
			case MsprojectPackage.RESOURCE__OVERTIME_COST:
				return OVERTIME_COST_EDEFAULT == null ? overtimeCost != null : !OVERTIME_COST_EDEFAULT.equals(overtimeCost);
			case MsprojectPackage.RESOURCE__COST_PER_USE:
				return COST_PER_USE_EDEFAULT == null ? costPerUse != null : !COST_PER_USE_EDEFAULT.equals(costPerUse);
			case MsprojectPackage.RESOURCE__ACTUAL_COST:
				return ACTUAL_COST_EDEFAULT == null ? actualCost != null : !ACTUAL_COST_EDEFAULT.equals(actualCost);
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_COST:
				return ACTUAL_OVERTIME_COST_EDEFAULT == null ? actualOvertimeCost != null : !ACTUAL_OVERTIME_COST_EDEFAULT.equals(actualOvertimeCost);
			case MsprojectPackage.RESOURCE__REMAINING_COST:
				return REMAINING_COST_EDEFAULT == null ? remainingCost != null : !REMAINING_COST_EDEFAULT.equals(remainingCost);
			case MsprojectPackage.RESOURCE__REMAINING_OVERTIME_COST:
				return REMAINING_OVERTIME_COST_EDEFAULT == null ? remainingOvertimeCost != null : !REMAINING_OVERTIME_COST_EDEFAULT.equals(remainingOvertimeCost);
			case MsprojectPackage.RESOURCE__WORK_VARIANCE:
				return isSetWorkVariance();
			case MsprojectPackage.RESOURCE__COST_VARIANCE:
				return isSetCostVariance();
			case MsprojectPackage.RESOURCE__SV:
				return isSetSV();
			case MsprojectPackage.RESOURCE__CV:
				return isSetCV();
			case MsprojectPackage.RESOURCE__ACWP:
				return isSetACWP();
			case MsprojectPackage.RESOURCE__CALENDAR_UID:
				return CALENDAR_UID_EDEFAULT == null ? calendarUID != null : !CALENDAR_UID_EDEFAULT.equals(calendarUID);
			case MsprojectPackage.RESOURCE__NOTES:
				return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
			case MsprojectPackage.RESOURCE__BCWS:
				return isSetBCWS();
			case MsprojectPackage.RESOURCE__BCWP:
				return isSetBCWP();
			case MsprojectPackage.RESOURCE__IS_GENERIC:
				return isSetIsGeneric();
			case MsprojectPackage.RESOURCE__IS_INACTIVE:
				return isSetIsInactive();
			case MsprojectPackage.RESOURCE__IS_ENTERPRISE:
				return isSetIsEnterprise();
			case MsprojectPackage.RESOURCE__BOOKING_TYPE:
				return BOOKING_TYPE_EDEFAULT == null ? bookingType != null : !BOOKING_TYPE_EDEFAULT.equals(bookingType);
			case MsprojectPackage.RESOURCE__ACTUAL_WORK_PROTECTED:
				return ACTUAL_WORK_PROTECTED_EDEFAULT == null ? actualWorkProtected != null : !ACTUAL_WORK_PROTECTED_EDEFAULT.equals(actualWorkProtected);
			case MsprojectPackage.RESOURCE__ACTUAL_OVERTIME_WORK_PROTECTED:
				return ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT == null ? actualOvertimeWorkProtected != null : !ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT.equals(actualOvertimeWorkProtected);
			case MsprojectPackage.RESOURCE__ACTIVE_DIRECTORY_GUID:
				return ACTIVE_DIRECTORY_GUID_EDEFAULT == null ? activeDirectoryGUID != null : !ACTIVE_DIRECTORY_GUID_EDEFAULT.equals(activeDirectoryGUID);
			case MsprojectPackage.RESOURCE__CREATION_DATE:
				return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
			case MsprojectPackage.RESOURCE__EXTENDED_ATTRIBUTE:
				return extendedAttribute != null && !extendedAttribute.isEmpty();
			case MsprojectPackage.RESOURCE__BASELINE:
				return baseline != null && !baseline.isEmpty();
			case MsprojectPackage.RESOURCE__OUTLINE_CODE:
				return outlineCode != null && !outlineCode.isEmpty();
			case MsprojectPackage.RESOURCE__AVAILABILITY_PERIODS:
				return availabilityPeriods != null;
			case MsprojectPackage.RESOURCE__RATES:
				return rates != null;
			case MsprojectPackage.RESOURCE__TIMEPHASED_DATA:
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
		result.append(", initials: ");
		result.append(initials);
		result.append(", phonetics: ");
		result.append(phonetics);
		result.append(", nTAccount: ");
		result.append(nTAccount);
		result.append(", materialLabel: ");
		result.append(materialLabel);
		result.append(", code: ");
		result.append(code);
		result.append(", group: ");
		result.append(group);
		result.append(", workGroup: ");
		result.append(workGroup);
		result.append(", emailAddress: ");
		result.append(emailAddress);
		result.append(", hyperlink: ");
		result.append(hyperlink);
		result.append(", hyperlinkAddress: ");
		result.append(hyperlinkAddress);
		result.append(", hyperlinkSubAddress: ");
		result.append(hyperlinkSubAddress);
		result.append(", maxUnits: ");
		if (maxUnitsESet) result.append(maxUnits); else result.append("<unset>");
		result.append(", peakUnits: ");
		if (peakUnitsESet) result.append(peakUnits); else result.append("<unset>");
		result.append(", overAllocated: ");
		if (overAllocatedESet) result.append(overAllocated); else result.append("<unset>");
		result.append(", availableFrom: ");
		result.append(availableFrom);
		result.append(", availableTo: ");
		result.append(availableTo);
		result.append(", start: ");
		result.append(start);
		result.append(", finish: ");
		result.append(finish);
		result.append(", canLevel: ");
		if (canLevelESet) result.append(canLevel); else result.append("<unset>");
		result.append(", accrueAt: ");
		result.append(accrueAt);
		result.append(", work: ");
		result.append(work);
		result.append(", regularWork: ");
		result.append(regularWork);
		result.append(", overtimeWork: ");
		result.append(overtimeWork);
		result.append(", actualWork: ");
		result.append(actualWork);
		result.append(", remainingWork: ");
		result.append(remainingWork);
		result.append(", actualOvertimeWork: ");
		result.append(actualOvertimeWork);
		result.append(", remainingOvertimeWork: ");
		result.append(remainingOvertimeWork);
		result.append(", percentWorkComplete: ");
		result.append(percentWorkComplete);
		result.append(", standardRate: ");
		result.append(standardRate);
		result.append(", standardRateFormat: ");
		result.append(standardRateFormat);
		result.append(", cost: ");
		result.append(cost);
		result.append(", overtimeRate: ");
		result.append(overtimeRate);
		result.append(", overtimeRateFormat: ");
		result.append(overtimeRateFormat);
		result.append(", overtimeCost: ");
		result.append(overtimeCost);
		result.append(", costPerUse: ");
		result.append(costPerUse);
		result.append(", actualCost: ");
		result.append(actualCost);
		result.append(", actualOvertimeCost: ");
		result.append(actualOvertimeCost);
		result.append(", remainingCost: ");
		result.append(remainingCost);
		result.append(", remainingOvertimeCost: ");
		result.append(remainingOvertimeCost);
		result.append(", workVariance: ");
		if (workVarianceESet) result.append(workVariance); else result.append("<unset>");
		result.append(", costVariance: ");
		if (costVarianceESet) result.append(costVariance); else result.append("<unset>");
		result.append(", sV: ");
		if (sVESet) result.append(sV); else result.append("<unset>");
		result.append(", cV: ");
		if (cVESet) result.append(cV); else result.append("<unset>");
		result.append(", aCWP: ");
		if (aCWPESet) result.append(aCWP); else result.append("<unset>");
		result.append(", calendarUID: ");
		result.append(calendarUID);
		result.append(", notes: ");
		result.append(notes);
		result.append(", bCWS: ");
		if (bCWSESet) result.append(bCWS); else result.append("<unset>");
		result.append(", bCWP: ");
		if (bCWPESet) result.append(bCWP); else result.append("<unset>");
		result.append(", isGeneric: ");
		if (isGenericESet) result.append(isGeneric); else result.append("<unset>");
		result.append(", isInactive: ");
		if (isInactiveESet) result.append(isInactive); else result.append("<unset>");
		result.append(", isEnterprise: ");
		if (isEnterpriseESet) result.append(isEnterprise); else result.append("<unset>");
		result.append(", bookingType: ");
		result.append(bookingType);
		result.append(", actualWorkProtected: ");
		result.append(actualWorkProtected);
		result.append(", actualOvertimeWorkProtected: ");
		result.append(actualOvertimeWorkProtected);
		result.append(", activeDirectoryGUID: ");
		result.append(activeDirectoryGUID);
		result.append(", creationDate: ");
		result.append(creationDate);
		result.append(')');
		return result.toString();
	}

} //ResourceImpl
