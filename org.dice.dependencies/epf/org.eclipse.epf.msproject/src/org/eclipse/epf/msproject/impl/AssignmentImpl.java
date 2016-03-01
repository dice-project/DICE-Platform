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
import org.eclipse.epf.msproject.Assignment;
import org.eclipse.epf.msproject.Baseline3;
import org.eclipse.epf.msproject.ExtendedAttribute4;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.TimephasedDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getTaskUID <em>Task UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getResourceUID <em>Resource UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getPercentWorkComplete <em>Percent Work Complete</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualCost <em>Actual Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualFinish <em>Actual Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualOvertimeCost <em>Actual Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualOvertimeWork <em>Actual Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualStart <em>Actual Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualWork <em>Actual Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getACWP <em>ACWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isConfirmed <em>Confirmed</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getCostRateTable <em>Cost Rate Table</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getCostVariance <em>Cost Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getCV <em>CV</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getDelay <em>Delay</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getFinishVariance <em>Finish Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getHyperlink <em>Hyperlink</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getHyperlinkAddress <em>Hyperlink Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getHyperlinkSubAddress <em>Hyperlink Sub Address</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getWorkVariance <em>Work Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isHasFixedRateUnits <em>Has Fixed Rate Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isFixedMaterial <em>Fixed Material</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getLevelingDelay <em>Leveling Delay</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getLevelingDelayFormat <em>Leveling Delay Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isLinkedFields <em>Linked Fields</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isOverallocated <em>Overallocated</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getOvertimeCost <em>Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getOvertimeWork <em>Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getRegularWork <em>Regular Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getRemainingCost <em>Remaining Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getRemainingOvertimeCost <em>Remaining Overtime Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getRemainingOvertimeWork <em>Remaining Overtime Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getRemainingWork <em>Remaining Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isResponsePending <em>Response Pending</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getStop <em>Stop</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getResume <em>Resume</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getStartVariance <em>Start Variance</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#isUpdateNeeded <em>Update Needed</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getVAC <em>VAC</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getWorkContour <em>Work Contour</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getBCWP <em>BCWP</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getBookingType <em>Booking Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualWorkProtected <em>Actual Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getActualOvertimeWorkProtected <em>Actual Overtime Work Protected</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getBaseline <em>Baseline</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.AssignmentImpl#getTimephasedData <em>Timephased Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentImpl extends EObjectImpl implements Assignment {
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
	 * The default value of the '{@link #getTaskUID() <em>Task UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger TASK_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTaskUID() <em>Task UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger taskUID = TASK_UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceUID() <em>Resource UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceUID()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger RESOURCE_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceUID() <em>Resource UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceUID()
	 * @generated
	 * @ordered
	 */
	protected BigInteger resourceUID = RESOURCE_UID_EDEFAULT;

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
	 * The default value of the '{@link #isConfirmed() <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfirmed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFIRMED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConfirmed() <em>Confirmed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfirmed()
	 * @generated
	 * @ordered
	 */
	protected boolean confirmed = CONFIRMED_EDEFAULT;

	/**
	 * This is true if the Confirmed attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean confirmedESet = false;

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
	 * The default value of the '{@link #getCostRateTable() <em>Cost Rate Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostRateTable()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger COST_RATE_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCostRateTable() <em>Cost Rate Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostRateTable()
	 * @generated
	 * @ordered
	 */
	protected BigInteger costRateTable = COST_RATE_TABLE_EDEFAULT;

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
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger DELAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected BigInteger delay = DELAY_EDEFAULT;

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
	 * The default value of the '{@link #isHasFixedRateUnits() <em>Has Fixed Rate Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasFixedRateUnits()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_FIXED_RATE_UNITS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasFixedRateUnits() <em>Has Fixed Rate Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasFixedRateUnits()
	 * @generated
	 * @ordered
	 */
	protected boolean hasFixedRateUnits = HAS_FIXED_RATE_UNITS_EDEFAULT;

	/**
	 * This is true if the Has Fixed Rate Units attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hasFixedRateUnitsESet = false;

	/**
	 * The default value of the '{@link #isFixedMaterial() <em>Fixed Material</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFixedMaterial()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FIXED_MATERIAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFixedMaterial() <em>Fixed Material</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFixedMaterial()
	 * @generated
	 * @ordered
	 */
	protected boolean fixedMaterial = FIXED_MATERIAL_EDEFAULT;

	/**
	 * This is true if the Fixed Material attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fixedMaterialESet = false;

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
	 * The default value of the '{@link #isLinkedFields() <em>Linked Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLinkedFields()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LINKED_FIELDS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLinkedFields() <em>Linked Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLinkedFields()
	 * @generated
	 * @ordered
	 */
	protected boolean linkedFields = LINKED_FIELDS_EDEFAULT;

	/**
	 * This is true if the Linked Fields attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean linkedFieldsESet = false;

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
	 * The default value of the '{@link #isOverallocated() <em>Overallocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverallocated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERALLOCATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverallocated() <em>Overallocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverallocated()
	 * @generated
	 * @ordered
	 */
	protected boolean overallocated = OVERALLOCATED_EDEFAULT;

	/**
	 * This is true if the Overallocated attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean overallocatedESet = false;

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
	 * The default value of the '{@link #isResponsePending() <em>Response Pending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResponsePending()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESPONSE_PENDING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isResponsePending() <em>Response Pending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResponsePending()
	 * @generated
	 * @ordered
	 */
	protected boolean responsePending = RESPONSE_PENDING_EDEFAULT;

	/**
	 * This is true if the Response Pending attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean responsePendingESet = false;

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
	 * The default value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
	protected static final float UNITS_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getUnits() <em>Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnits()
	 * @generated
	 * @ordered
	 */
	protected float units = UNITS_EDEFAULT;

	/**
	 * This is true if the Units attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean unitsESet = false;

	/**
	 * The default value of the '{@link #isUpdateNeeded() <em>Update Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpdateNeeded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPDATE_NEEDED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUpdateNeeded() <em>Update Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpdateNeeded()
	 * @generated
	 * @ordered
	 */
	protected boolean updateNeeded = UPDATE_NEEDED_EDEFAULT;

	/**
	 * This is true if the Update Needed attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean updateNeededESet = false;

	/**
	 * The default value of the '{@link #getVAC() <em>VAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVAC()
	 * @generated
	 * @ordered
	 */
	protected static final float VAC_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getVAC() <em>VAC</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVAC()
	 * @generated
	 * @ordered
	 */
	protected float vAC = VAC_EDEFAULT;

	/**
	 * This is true if the VAC attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean vACESet = false;

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
	 * The default value of the '{@link #getWorkContour() <em>Work Contour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkContour()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger WORK_CONTOUR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkContour() <em>Work Contour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkContour()
	 * @generated
	 * @ordered
	 */
	protected BigInteger workContour = WORK_CONTOUR_EDEFAULT;

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
	protected AssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getAssignment();
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__UID, oldUID, uID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getTaskUID() {
		return taskUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskUID(BigInteger newTaskUID) {
		BigInteger oldTaskUID = taskUID;
		taskUID = newTaskUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__TASK_UID, oldTaskUID, taskUID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getResourceUID() {
		return resourceUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceUID(BigInteger newResourceUID) {
		BigInteger oldResourceUID = resourceUID;
		resourceUID = newResourceUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__RESOURCE_UID, oldResourceUID, resourceUID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__PERCENT_WORK_COMPLETE, oldPercentWorkComplete, percentWorkComplete));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_COST, oldActualCost, actualCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_FINISH, oldActualFinish, actualFinish));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_COST, oldActualOvertimeCost, actualOvertimeCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK, oldActualOvertimeWork, actualOvertimeWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_START, oldActualStart, actualStart));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_WORK, oldActualWork, actualWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACWP, oldACWP, aCWP, !oldACWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__ACWP, oldACWP, ACWP_EDEFAULT, oldACWPESet));
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
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfirmed(boolean newConfirmed) {
		boolean oldConfirmed = confirmed;
		confirmed = newConfirmed;
		boolean oldConfirmedESet = confirmedESet;
		confirmedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__CONFIRMED, oldConfirmed, confirmed, !oldConfirmedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetConfirmed() {
		boolean oldConfirmed = confirmed;
		boolean oldConfirmedESet = confirmedESet;
		confirmed = CONFIRMED_EDEFAULT;
		confirmedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__CONFIRMED, oldConfirmed, CONFIRMED_EDEFAULT, oldConfirmedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetConfirmed() {
		return confirmedESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__COST, oldCost, cost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCostRateTable() {
		return costRateTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostRateTable(BigInteger newCostRateTable) {
		BigInteger oldCostRateTable = costRateTable;
		costRateTable = newCostRateTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__COST_RATE_TABLE, oldCostRateTable, costRateTable));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__COST_VARIANCE, oldCostVariance, costVariance, !oldCostVarianceESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__COST_VARIANCE, oldCostVariance, COST_VARIANCE_EDEFAULT, oldCostVarianceESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__CV, oldCV, cV, !oldCVESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__CV, oldCV, CV_EDEFAULT, oldCVESet));
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
	public BigInteger getDelay() {
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelay(BigInteger newDelay) {
		BigInteger oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__DELAY, oldDelay, delay));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__FINISH, oldFinish, finish));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__FINISH_VARIANCE, oldFinishVariance, finishVariance));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__HYPERLINK, oldHyperlink, hyperlink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__HYPERLINK_ADDRESS, oldHyperlinkAddress, hyperlinkAddress));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__HYPERLINK_SUB_ADDRESS, oldHyperlinkSubAddress, hyperlinkSubAddress));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__WORK_VARIANCE, oldWorkVariance, workVariance, !oldWorkVarianceESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__WORK_VARIANCE, oldWorkVariance, WORK_VARIANCE_EDEFAULT, oldWorkVarianceESet));
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
	public boolean isHasFixedRateUnits() {
		return hasFixedRateUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasFixedRateUnits(boolean newHasFixedRateUnits) {
		boolean oldHasFixedRateUnits = hasFixedRateUnits;
		hasFixedRateUnits = newHasFixedRateUnits;
		boolean oldHasFixedRateUnitsESet = hasFixedRateUnitsESet;
		hasFixedRateUnitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__HAS_FIXED_RATE_UNITS, oldHasFixedRateUnits, hasFixedRateUnits, !oldHasFixedRateUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHasFixedRateUnits() {
		boolean oldHasFixedRateUnits = hasFixedRateUnits;
		boolean oldHasFixedRateUnitsESet = hasFixedRateUnitsESet;
		hasFixedRateUnits = HAS_FIXED_RATE_UNITS_EDEFAULT;
		hasFixedRateUnitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__HAS_FIXED_RATE_UNITS, oldHasFixedRateUnits, HAS_FIXED_RATE_UNITS_EDEFAULT, oldHasFixedRateUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHasFixedRateUnits() {
		return hasFixedRateUnitsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFixedMaterial() {
		return fixedMaterial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFixedMaterial(boolean newFixedMaterial) {
		boolean oldFixedMaterial = fixedMaterial;
		fixedMaterial = newFixedMaterial;
		boolean oldFixedMaterialESet = fixedMaterialESet;
		fixedMaterialESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__FIXED_MATERIAL, oldFixedMaterial, fixedMaterial, !oldFixedMaterialESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFixedMaterial() {
		boolean oldFixedMaterial = fixedMaterial;
		boolean oldFixedMaterialESet = fixedMaterialESet;
		fixedMaterial = FIXED_MATERIAL_EDEFAULT;
		fixedMaterialESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__FIXED_MATERIAL, oldFixedMaterial, FIXED_MATERIAL_EDEFAULT, oldFixedMaterialESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFixedMaterial() {
		return fixedMaterialESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__LEVELING_DELAY, oldLevelingDelay, levelingDelay));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__LEVELING_DELAY_FORMAT, oldLevelingDelayFormat, levelingDelayFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLinkedFields() {
		return linkedFields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkedFields(boolean newLinkedFields) {
		boolean oldLinkedFields = linkedFields;
		linkedFields = newLinkedFields;
		boolean oldLinkedFieldsESet = linkedFieldsESet;
		linkedFieldsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__LINKED_FIELDS, oldLinkedFields, linkedFields, !oldLinkedFieldsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetLinkedFields() {
		boolean oldLinkedFields = linkedFields;
		boolean oldLinkedFieldsESet = linkedFieldsESet;
		linkedFields = LINKED_FIELDS_EDEFAULT;
		linkedFieldsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__LINKED_FIELDS, oldLinkedFields, LINKED_FIELDS_EDEFAULT, oldLinkedFieldsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetLinkedFields() {
		return linkedFieldsESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__MILESTONE, oldMilestone, milestone, !oldMilestoneESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__MILESTONE, oldMilestone, MILESTONE_EDEFAULT, oldMilestoneESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__NOTES, oldNotes, notes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverallocated() {
		return overallocated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverallocated(boolean newOverallocated) {
		boolean oldOverallocated = overallocated;
		overallocated = newOverallocated;
		boolean oldOverallocatedESet = overallocatedESet;
		overallocatedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__OVERALLOCATED, oldOverallocated, overallocated, !oldOverallocatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetOverallocated() {
		boolean oldOverallocated = overallocated;
		boolean oldOverallocatedESet = overallocatedESet;
		overallocated = OVERALLOCATED_EDEFAULT;
		overallocatedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__OVERALLOCATED, oldOverallocated, OVERALLOCATED_EDEFAULT, oldOverallocatedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetOverallocated() {
		return overallocatedESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__OVERTIME_COST, oldOvertimeCost, overtimeCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__OVERTIME_WORK, oldOvertimeWork, overtimeWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__REGULAR_WORK, oldRegularWork, regularWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__REMAINING_COST, oldRemainingCost, remainingCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_COST, oldRemainingOvertimeCost, remainingOvertimeCost));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_WORK, oldRemainingOvertimeWork, remainingOvertimeWork));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__REMAINING_WORK, oldRemainingWork, remainingWork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isResponsePending() {
		return responsePending;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponsePending(boolean newResponsePending) {
		boolean oldResponsePending = responsePending;
		responsePending = newResponsePending;
		boolean oldResponsePendingESet = responsePendingESet;
		responsePendingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__RESPONSE_PENDING, oldResponsePending, responsePending, !oldResponsePendingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResponsePending() {
		boolean oldResponsePending = responsePending;
		boolean oldResponsePendingESet = responsePendingESet;
		responsePending = RESPONSE_PENDING_EDEFAULT;
		responsePendingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__RESPONSE_PENDING, oldResponsePending, RESPONSE_PENDING_EDEFAULT, oldResponsePendingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResponsePending() {
		return responsePendingESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__START, oldStart, start));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__STOP, oldStop, stop));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__RESUME, oldResume, resume));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__START_VARIANCE, oldStartVariance, startVariance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getUnits() {
		return units;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnits(float newUnits) {
		float oldUnits = units;
		units = newUnits;
		boolean oldUnitsESet = unitsESet;
		unitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__UNITS, oldUnits, units, !oldUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnits() {
		float oldUnits = units;
		boolean oldUnitsESet = unitsESet;
		units = UNITS_EDEFAULT;
		unitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__UNITS, oldUnits, UNITS_EDEFAULT, oldUnitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnits() {
		return unitsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUpdateNeeded() {
		return updateNeeded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpdateNeeded(boolean newUpdateNeeded) {
		boolean oldUpdateNeeded = updateNeeded;
		updateNeeded = newUpdateNeeded;
		boolean oldUpdateNeededESet = updateNeededESet;
		updateNeededESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__UPDATE_NEEDED, oldUpdateNeeded, updateNeeded, !oldUpdateNeededESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUpdateNeeded() {
		boolean oldUpdateNeeded = updateNeeded;
		boolean oldUpdateNeededESet = updateNeededESet;
		updateNeeded = UPDATE_NEEDED_EDEFAULT;
		updateNeededESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__UPDATE_NEEDED, oldUpdateNeeded, UPDATE_NEEDED_EDEFAULT, oldUpdateNeededESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUpdateNeeded() {
		return updateNeededESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getVAC() {
		return vAC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVAC(float newVAC) {
		float oldVAC = vAC;
		vAC = newVAC;
		boolean oldVACESet = vACESet;
		vACESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__VAC, oldVAC, vAC, !oldVACESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVAC() {
		float oldVAC = vAC;
		boolean oldVACESet = vACESet;
		vAC = VAC_EDEFAULT;
		vACESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__VAC, oldVAC, VAC_EDEFAULT, oldVACESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVAC() {
		return vACESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__WORK, oldWork, work));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getWorkContour() {
		return workContour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkContour(BigInteger newWorkContour) {
		BigInteger oldWorkContour = workContour;
		workContour = newWorkContour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__WORK_CONTOUR, oldWorkContour, workContour));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__BCWS, oldBCWS, bCWS, !oldBCWSESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__BCWS, oldBCWS, BCWS_EDEFAULT, oldBCWSESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__BCWP, oldBCWP, bCWP, !oldBCWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.ASSIGNMENT__BCWP, oldBCWP, BCWP_EDEFAULT, oldBCWPESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__BOOKING_TYPE, oldBookingType, bookingType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_WORK_PROTECTED, oldActualWorkProtected, actualWorkProtected));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK_PROTECTED, oldActualOvertimeWorkProtected, actualOvertimeWorkProtected));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.ASSIGNMENT__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedAttribute() {
		if (extendedAttribute == null) {
			extendedAttribute = new EObjectContainmentEList(ExtendedAttribute4.class, this, MsprojectPackage.ASSIGNMENT__EXTENDED_ATTRIBUTE);
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
			baseline = new EObjectContainmentEList(Baseline3.class, this, MsprojectPackage.ASSIGNMENT__BASELINE);
		}
		return baseline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTimephasedData() {
		if (timephasedData == null) {
			timephasedData = new EObjectContainmentEList(TimephasedDataType.class, this, MsprojectPackage.ASSIGNMENT__TIMEPHASED_DATA);
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
				case MsprojectPackage.ASSIGNMENT__EXTENDED_ATTRIBUTE:
					return ((InternalEList)getExtendedAttribute()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.ASSIGNMENT__BASELINE:
					return ((InternalEList)getBaseline()).basicRemove(otherEnd, msgs);
				case MsprojectPackage.ASSIGNMENT__TIMEPHASED_DATA:
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
			case MsprojectPackage.ASSIGNMENT__UID:
				return getUID();
			case MsprojectPackage.ASSIGNMENT__TASK_UID:
				return getTaskUID();
			case MsprojectPackage.ASSIGNMENT__RESOURCE_UID:
				return getResourceUID();
			case MsprojectPackage.ASSIGNMENT__PERCENT_WORK_COMPLETE:
				return getPercentWorkComplete();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_COST:
				return getActualCost();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_FINISH:
				return getActualFinish();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_COST:
				return getActualOvertimeCost();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK:
				return getActualOvertimeWork();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_START:
				return getActualStart();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK:
				return getActualWork();
			case MsprojectPackage.ASSIGNMENT__ACWP:
				return new Float(getACWP());
			case MsprojectPackage.ASSIGNMENT__CONFIRMED:
				return isConfirmed() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__COST:
				return getCost();
			case MsprojectPackage.ASSIGNMENT__COST_RATE_TABLE:
				return getCostRateTable();
			case MsprojectPackage.ASSIGNMENT__COST_VARIANCE:
				return new Float(getCostVariance());
			case MsprojectPackage.ASSIGNMENT__CV:
				return new Float(getCV());
			case MsprojectPackage.ASSIGNMENT__DELAY:
				return getDelay();
			case MsprojectPackage.ASSIGNMENT__FINISH:
				return getFinish();
			case MsprojectPackage.ASSIGNMENT__FINISH_VARIANCE:
				return getFinishVariance();
			case MsprojectPackage.ASSIGNMENT__HYPERLINK:
				return getHyperlink();
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_ADDRESS:
				return getHyperlinkAddress();
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_SUB_ADDRESS:
				return getHyperlinkSubAddress();
			case MsprojectPackage.ASSIGNMENT__WORK_VARIANCE:
				return new Float(getWorkVariance());
			case MsprojectPackage.ASSIGNMENT__HAS_FIXED_RATE_UNITS:
				return isHasFixedRateUnits() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__FIXED_MATERIAL:
				return isFixedMaterial() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY:
				return getLevelingDelay();
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY_FORMAT:
				return getLevelingDelayFormat();
			case MsprojectPackage.ASSIGNMENT__LINKED_FIELDS:
				return isLinkedFields() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__MILESTONE:
				return isMilestone() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__NOTES:
				return getNotes();
			case MsprojectPackage.ASSIGNMENT__OVERALLOCATED:
				return isOverallocated() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__OVERTIME_COST:
				return getOvertimeCost();
			case MsprojectPackage.ASSIGNMENT__OVERTIME_WORK:
				return getOvertimeWork();
			case MsprojectPackage.ASSIGNMENT__REGULAR_WORK:
				return getRegularWork();
			case MsprojectPackage.ASSIGNMENT__REMAINING_COST:
				return getRemainingCost();
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_COST:
				return getRemainingOvertimeCost();
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_WORK:
				return getRemainingOvertimeWork();
			case MsprojectPackage.ASSIGNMENT__REMAINING_WORK:
				return getRemainingWork();
			case MsprojectPackage.ASSIGNMENT__RESPONSE_PENDING:
				return isResponsePending() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__START:
				return getStart();
			case MsprojectPackage.ASSIGNMENT__STOP:
				return getStop();
			case MsprojectPackage.ASSIGNMENT__RESUME:
				return getResume();
			case MsprojectPackage.ASSIGNMENT__START_VARIANCE:
				return getStartVariance();
			case MsprojectPackage.ASSIGNMENT__UNITS:
				return new Float(getUnits());
			case MsprojectPackage.ASSIGNMENT__UPDATE_NEEDED:
				return isUpdateNeeded() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.ASSIGNMENT__VAC:
				return new Float(getVAC());
			case MsprojectPackage.ASSIGNMENT__WORK:
				return getWork();
			case MsprojectPackage.ASSIGNMENT__WORK_CONTOUR:
				return getWorkContour();
			case MsprojectPackage.ASSIGNMENT__BCWS:
				return new Float(getBCWS());
			case MsprojectPackage.ASSIGNMENT__BCWP:
				return new Float(getBCWP());
			case MsprojectPackage.ASSIGNMENT__BOOKING_TYPE:
				return getBookingType();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK_PROTECTED:
				return getActualWorkProtected();
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK_PROTECTED:
				return getActualOvertimeWorkProtected();
			case MsprojectPackage.ASSIGNMENT__CREATION_DATE:
				return getCreationDate();
			case MsprojectPackage.ASSIGNMENT__EXTENDED_ATTRIBUTE:
				return getExtendedAttribute();
			case MsprojectPackage.ASSIGNMENT__BASELINE:
				return getBaseline();
			case MsprojectPackage.ASSIGNMENT__TIMEPHASED_DATA:
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
			case MsprojectPackage.ASSIGNMENT__UID:
				setUID((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__TASK_UID:
				setTaskUID((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__RESOURCE_UID:
				setResourceUID((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__PERCENT_WORK_COMPLETE:
				setPercentWorkComplete((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_COST:
				setActualCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_FINISH:
				setActualFinish((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_COST:
				setActualOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK:
				setActualOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_START:
				setActualStart((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK:
				setActualWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACWP:
				setACWP(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__CONFIRMED:
				setConfirmed(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__COST:
				setCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__COST_RATE_TABLE:
				setCostRateTable((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__COST_VARIANCE:
				setCostVariance(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__CV:
				setCV(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__DELAY:
				setDelay((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__FINISH:
				setFinish((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__FINISH_VARIANCE:
				setFinishVariance((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__HYPERLINK:
				setHyperlink((String)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_ADDRESS:
				setHyperlinkAddress((String)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_SUB_ADDRESS:
				setHyperlinkSubAddress((String)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__WORK_VARIANCE:
				setWorkVariance(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__HAS_FIXED_RATE_UNITS:
				setHasFixedRateUnits(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__FIXED_MATERIAL:
				setFixedMaterial(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY:
				setLevelingDelay((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY_FORMAT:
				setLevelingDelayFormat((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__LINKED_FIELDS:
				setLinkedFields(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__MILESTONE:
				setMilestone(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__NOTES:
				setNotes((String)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__OVERALLOCATED:
				setOverallocated(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__OVERTIME_COST:
				setOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__OVERTIME_WORK:
				setOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__REGULAR_WORK:
				setRegularWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_COST:
				setRemainingCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_COST:
				setRemainingOvertimeCost((BigDecimal)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_WORK:
				setRemainingOvertimeWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_WORK:
				setRemainingWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__RESPONSE_PENDING:
				setResponsePending(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__START:
				setStart((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__STOP:
				setStop((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__RESUME:
				setResume((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__START_VARIANCE:
				setStartVariance((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__UNITS:
				setUnits(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__UPDATE_NEEDED:
				setUpdateNeeded(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.ASSIGNMENT__VAC:
				setVAC(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__WORK:
				setWork((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__WORK_CONTOUR:
				setWorkContour((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__BCWS:
				setBCWS(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__BCWP:
				setBCWP(((Float)newValue).floatValue());
				return;
			case MsprojectPackage.ASSIGNMENT__BOOKING_TYPE:
				setBookingType((BigInteger)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK_PROTECTED:
				setActualWorkProtected((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK_PROTECTED:
				setActualOvertimeWorkProtected((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__CREATION_DATE:
				setCreationDate((Object)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				getExtendedAttribute().addAll((Collection)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__BASELINE:
				getBaseline().clear();
				getBaseline().addAll((Collection)newValue);
				return;
			case MsprojectPackage.ASSIGNMENT__TIMEPHASED_DATA:
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
			case MsprojectPackage.ASSIGNMENT__UID:
				setUID(UID_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__TASK_UID:
				setTaskUID(TASK_UID_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__RESOURCE_UID:
				setResourceUID(RESOURCE_UID_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__PERCENT_WORK_COMPLETE:
				setPercentWorkComplete(PERCENT_WORK_COMPLETE_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_COST:
				setActualCost(ACTUAL_COST_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_FINISH:
				setActualFinish(ACTUAL_FINISH_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_COST:
				setActualOvertimeCost(ACTUAL_OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK:
				setActualOvertimeWork(ACTUAL_OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_START:
				setActualStart(ACTUAL_START_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK:
				setActualWork(ACTUAL_WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACWP:
				unsetACWP();
				return;
			case MsprojectPackage.ASSIGNMENT__CONFIRMED:
				unsetConfirmed();
				return;
			case MsprojectPackage.ASSIGNMENT__COST:
				setCost(COST_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__COST_RATE_TABLE:
				setCostRateTable(COST_RATE_TABLE_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__COST_VARIANCE:
				unsetCostVariance();
				return;
			case MsprojectPackage.ASSIGNMENT__CV:
				unsetCV();
				return;
			case MsprojectPackage.ASSIGNMENT__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__FINISH:
				setFinish(FINISH_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__FINISH_VARIANCE:
				setFinishVariance(FINISH_VARIANCE_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__HYPERLINK:
				setHyperlink(HYPERLINK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_ADDRESS:
				setHyperlinkAddress(HYPERLINK_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_SUB_ADDRESS:
				setHyperlinkSubAddress(HYPERLINK_SUB_ADDRESS_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__WORK_VARIANCE:
				unsetWorkVariance();
				return;
			case MsprojectPackage.ASSIGNMENT__HAS_FIXED_RATE_UNITS:
				unsetHasFixedRateUnits();
				return;
			case MsprojectPackage.ASSIGNMENT__FIXED_MATERIAL:
				unsetFixedMaterial();
				return;
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY:
				setLevelingDelay(LEVELING_DELAY_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY_FORMAT:
				setLevelingDelayFormat(LEVELING_DELAY_FORMAT_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__LINKED_FIELDS:
				unsetLinkedFields();
				return;
			case MsprojectPackage.ASSIGNMENT__MILESTONE:
				unsetMilestone();
				return;
			case MsprojectPackage.ASSIGNMENT__NOTES:
				setNotes(NOTES_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__OVERALLOCATED:
				unsetOverallocated();
				return;
			case MsprojectPackage.ASSIGNMENT__OVERTIME_COST:
				setOvertimeCost(OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__OVERTIME_WORK:
				setOvertimeWork(OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__REGULAR_WORK:
				setRegularWork(REGULAR_WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_COST:
				setRemainingCost(REMAINING_COST_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_COST:
				setRemainingOvertimeCost(REMAINING_OVERTIME_COST_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_WORK:
				setRemainingOvertimeWork(REMAINING_OVERTIME_WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__REMAINING_WORK:
				setRemainingWork(REMAINING_WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__RESPONSE_PENDING:
				unsetResponsePending();
				return;
			case MsprojectPackage.ASSIGNMENT__START:
				setStart(START_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__STOP:
				setStop(STOP_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__RESUME:
				setResume(RESUME_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__START_VARIANCE:
				setStartVariance(START_VARIANCE_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__UNITS:
				unsetUnits();
				return;
			case MsprojectPackage.ASSIGNMENT__UPDATE_NEEDED:
				unsetUpdateNeeded();
				return;
			case MsprojectPackage.ASSIGNMENT__VAC:
				unsetVAC();
				return;
			case MsprojectPackage.ASSIGNMENT__WORK:
				setWork(WORK_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__WORK_CONTOUR:
				setWorkContour(WORK_CONTOUR_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__BCWS:
				unsetBCWS();
				return;
			case MsprojectPackage.ASSIGNMENT__BCWP:
				unsetBCWP();
				return;
			case MsprojectPackage.ASSIGNMENT__BOOKING_TYPE:
				setBookingType(BOOKING_TYPE_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK_PROTECTED:
				setActualWorkProtected(ACTUAL_WORK_PROTECTED_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK_PROTECTED:
				setActualOvertimeWorkProtected(ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case MsprojectPackage.ASSIGNMENT__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				return;
			case MsprojectPackage.ASSIGNMENT__BASELINE:
				getBaseline().clear();
				return;
			case MsprojectPackage.ASSIGNMENT__TIMEPHASED_DATA:
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
			case MsprojectPackage.ASSIGNMENT__UID:
				return UID_EDEFAULT == null ? uID != null : !UID_EDEFAULT.equals(uID);
			case MsprojectPackage.ASSIGNMENT__TASK_UID:
				return TASK_UID_EDEFAULT == null ? taskUID != null : !TASK_UID_EDEFAULT.equals(taskUID);
			case MsprojectPackage.ASSIGNMENT__RESOURCE_UID:
				return RESOURCE_UID_EDEFAULT == null ? resourceUID != null : !RESOURCE_UID_EDEFAULT.equals(resourceUID);
			case MsprojectPackage.ASSIGNMENT__PERCENT_WORK_COMPLETE:
				return PERCENT_WORK_COMPLETE_EDEFAULT == null ? percentWorkComplete != null : !PERCENT_WORK_COMPLETE_EDEFAULT.equals(percentWorkComplete);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_COST:
				return ACTUAL_COST_EDEFAULT == null ? actualCost != null : !ACTUAL_COST_EDEFAULT.equals(actualCost);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_FINISH:
				return ACTUAL_FINISH_EDEFAULT == null ? actualFinish != null : !ACTUAL_FINISH_EDEFAULT.equals(actualFinish);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_COST:
				return ACTUAL_OVERTIME_COST_EDEFAULT == null ? actualOvertimeCost != null : !ACTUAL_OVERTIME_COST_EDEFAULT.equals(actualOvertimeCost);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK:
				return ACTUAL_OVERTIME_WORK_EDEFAULT == null ? actualOvertimeWork != null : !ACTUAL_OVERTIME_WORK_EDEFAULT.equals(actualOvertimeWork);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_START:
				return ACTUAL_START_EDEFAULT == null ? actualStart != null : !ACTUAL_START_EDEFAULT.equals(actualStart);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK:
				return ACTUAL_WORK_EDEFAULT == null ? actualWork != null : !ACTUAL_WORK_EDEFAULT.equals(actualWork);
			case MsprojectPackage.ASSIGNMENT__ACWP:
				return isSetACWP();
			case MsprojectPackage.ASSIGNMENT__CONFIRMED:
				return isSetConfirmed();
			case MsprojectPackage.ASSIGNMENT__COST:
				return COST_EDEFAULT == null ? cost != null : !COST_EDEFAULT.equals(cost);
			case MsprojectPackage.ASSIGNMENT__COST_RATE_TABLE:
				return COST_RATE_TABLE_EDEFAULT == null ? costRateTable != null : !COST_RATE_TABLE_EDEFAULT.equals(costRateTable);
			case MsprojectPackage.ASSIGNMENT__COST_VARIANCE:
				return isSetCostVariance();
			case MsprojectPackage.ASSIGNMENT__CV:
				return isSetCV();
			case MsprojectPackage.ASSIGNMENT__DELAY:
				return DELAY_EDEFAULT == null ? delay != null : !DELAY_EDEFAULT.equals(delay);
			case MsprojectPackage.ASSIGNMENT__FINISH:
				return FINISH_EDEFAULT == null ? finish != null : !FINISH_EDEFAULT.equals(finish);
			case MsprojectPackage.ASSIGNMENT__FINISH_VARIANCE:
				return FINISH_VARIANCE_EDEFAULT == null ? finishVariance != null : !FINISH_VARIANCE_EDEFAULT.equals(finishVariance);
			case MsprojectPackage.ASSIGNMENT__HYPERLINK:
				return HYPERLINK_EDEFAULT == null ? hyperlink != null : !HYPERLINK_EDEFAULT.equals(hyperlink);
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_ADDRESS:
				return HYPERLINK_ADDRESS_EDEFAULT == null ? hyperlinkAddress != null : !HYPERLINK_ADDRESS_EDEFAULT.equals(hyperlinkAddress);
			case MsprojectPackage.ASSIGNMENT__HYPERLINK_SUB_ADDRESS:
				return HYPERLINK_SUB_ADDRESS_EDEFAULT == null ? hyperlinkSubAddress != null : !HYPERLINK_SUB_ADDRESS_EDEFAULT.equals(hyperlinkSubAddress);
			case MsprojectPackage.ASSIGNMENT__WORK_VARIANCE:
				return isSetWorkVariance();
			case MsprojectPackage.ASSIGNMENT__HAS_FIXED_RATE_UNITS:
				return isSetHasFixedRateUnits();
			case MsprojectPackage.ASSIGNMENT__FIXED_MATERIAL:
				return isSetFixedMaterial();
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY:
				return LEVELING_DELAY_EDEFAULT == null ? levelingDelay != null : !LEVELING_DELAY_EDEFAULT.equals(levelingDelay);
			case MsprojectPackage.ASSIGNMENT__LEVELING_DELAY_FORMAT:
				return LEVELING_DELAY_FORMAT_EDEFAULT == null ? levelingDelayFormat != null : !LEVELING_DELAY_FORMAT_EDEFAULT.equals(levelingDelayFormat);
			case MsprojectPackage.ASSIGNMENT__LINKED_FIELDS:
				return isSetLinkedFields();
			case MsprojectPackage.ASSIGNMENT__MILESTONE:
				return isSetMilestone();
			case MsprojectPackage.ASSIGNMENT__NOTES:
				return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
			case MsprojectPackage.ASSIGNMENT__OVERALLOCATED:
				return isSetOverallocated();
			case MsprojectPackage.ASSIGNMENT__OVERTIME_COST:
				return OVERTIME_COST_EDEFAULT == null ? overtimeCost != null : !OVERTIME_COST_EDEFAULT.equals(overtimeCost);
			case MsprojectPackage.ASSIGNMENT__OVERTIME_WORK:
				return OVERTIME_WORK_EDEFAULT == null ? overtimeWork != null : !OVERTIME_WORK_EDEFAULT.equals(overtimeWork);
			case MsprojectPackage.ASSIGNMENT__REGULAR_WORK:
				return REGULAR_WORK_EDEFAULT == null ? regularWork != null : !REGULAR_WORK_EDEFAULT.equals(regularWork);
			case MsprojectPackage.ASSIGNMENT__REMAINING_COST:
				return REMAINING_COST_EDEFAULT == null ? remainingCost != null : !REMAINING_COST_EDEFAULT.equals(remainingCost);
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_COST:
				return REMAINING_OVERTIME_COST_EDEFAULT == null ? remainingOvertimeCost != null : !REMAINING_OVERTIME_COST_EDEFAULT.equals(remainingOvertimeCost);
			case MsprojectPackage.ASSIGNMENT__REMAINING_OVERTIME_WORK:
				return REMAINING_OVERTIME_WORK_EDEFAULT == null ? remainingOvertimeWork != null : !REMAINING_OVERTIME_WORK_EDEFAULT.equals(remainingOvertimeWork);
			case MsprojectPackage.ASSIGNMENT__REMAINING_WORK:
				return REMAINING_WORK_EDEFAULT == null ? remainingWork != null : !REMAINING_WORK_EDEFAULT.equals(remainingWork);
			case MsprojectPackage.ASSIGNMENT__RESPONSE_PENDING:
				return isSetResponsePending();
			case MsprojectPackage.ASSIGNMENT__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case MsprojectPackage.ASSIGNMENT__STOP:
				return STOP_EDEFAULT == null ? stop != null : !STOP_EDEFAULT.equals(stop);
			case MsprojectPackage.ASSIGNMENT__RESUME:
				return RESUME_EDEFAULT == null ? resume != null : !RESUME_EDEFAULT.equals(resume);
			case MsprojectPackage.ASSIGNMENT__START_VARIANCE:
				return START_VARIANCE_EDEFAULT == null ? startVariance != null : !START_VARIANCE_EDEFAULT.equals(startVariance);
			case MsprojectPackage.ASSIGNMENT__UNITS:
				return isSetUnits();
			case MsprojectPackage.ASSIGNMENT__UPDATE_NEEDED:
				return isSetUpdateNeeded();
			case MsprojectPackage.ASSIGNMENT__VAC:
				return isSetVAC();
			case MsprojectPackage.ASSIGNMENT__WORK:
				return WORK_EDEFAULT == null ? work != null : !WORK_EDEFAULT.equals(work);
			case MsprojectPackage.ASSIGNMENT__WORK_CONTOUR:
				return WORK_CONTOUR_EDEFAULT == null ? workContour != null : !WORK_CONTOUR_EDEFAULT.equals(workContour);
			case MsprojectPackage.ASSIGNMENT__BCWS:
				return isSetBCWS();
			case MsprojectPackage.ASSIGNMENT__BCWP:
				return isSetBCWP();
			case MsprojectPackage.ASSIGNMENT__BOOKING_TYPE:
				return BOOKING_TYPE_EDEFAULT == null ? bookingType != null : !BOOKING_TYPE_EDEFAULT.equals(bookingType);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_WORK_PROTECTED:
				return ACTUAL_WORK_PROTECTED_EDEFAULT == null ? actualWorkProtected != null : !ACTUAL_WORK_PROTECTED_EDEFAULT.equals(actualWorkProtected);
			case MsprojectPackage.ASSIGNMENT__ACTUAL_OVERTIME_WORK_PROTECTED:
				return ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT == null ? actualOvertimeWorkProtected != null : !ACTUAL_OVERTIME_WORK_PROTECTED_EDEFAULT.equals(actualOvertimeWorkProtected);
			case MsprojectPackage.ASSIGNMENT__CREATION_DATE:
				return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
			case MsprojectPackage.ASSIGNMENT__EXTENDED_ATTRIBUTE:
				return extendedAttribute != null && !extendedAttribute.isEmpty();
			case MsprojectPackage.ASSIGNMENT__BASELINE:
				return baseline != null && !baseline.isEmpty();
			case MsprojectPackage.ASSIGNMENT__TIMEPHASED_DATA:
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
		result.append(", taskUID: ");
		result.append(taskUID);
		result.append(", resourceUID: ");
		result.append(resourceUID);
		result.append(", percentWorkComplete: ");
		result.append(percentWorkComplete);
		result.append(", actualCost: ");
		result.append(actualCost);
		result.append(", actualFinish: ");
		result.append(actualFinish);
		result.append(", actualOvertimeCost: ");
		result.append(actualOvertimeCost);
		result.append(", actualOvertimeWork: ");
		result.append(actualOvertimeWork);
		result.append(", actualStart: ");
		result.append(actualStart);
		result.append(", actualWork: ");
		result.append(actualWork);
		result.append(", aCWP: ");
		if (aCWPESet) result.append(aCWP); else result.append("<unset>");
		result.append(", confirmed: ");
		if (confirmedESet) result.append(confirmed); else result.append("<unset>");
		result.append(", cost: ");
		result.append(cost);
		result.append(", costRateTable: ");
		result.append(costRateTable);
		result.append(", costVariance: ");
		if (costVarianceESet) result.append(costVariance); else result.append("<unset>");
		result.append(", cV: ");
		if (cVESet) result.append(cV); else result.append("<unset>");
		result.append(", delay: ");
		result.append(delay);
		result.append(", finish: ");
		result.append(finish);
		result.append(", finishVariance: ");
		result.append(finishVariance);
		result.append(", hyperlink: ");
		result.append(hyperlink);
		result.append(", hyperlinkAddress: ");
		result.append(hyperlinkAddress);
		result.append(", hyperlinkSubAddress: ");
		result.append(hyperlinkSubAddress);
		result.append(", workVariance: ");
		if (workVarianceESet) result.append(workVariance); else result.append("<unset>");
		result.append(", hasFixedRateUnits: ");
		if (hasFixedRateUnitsESet) result.append(hasFixedRateUnits); else result.append("<unset>");
		result.append(", fixedMaterial: ");
		if (fixedMaterialESet) result.append(fixedMaterial); else result.append("<unset>");
		result.append(", levelingDelay: ");
		result.append(levelingDelay);
		result.append(", levelingDelayFormat: ");
		result.append(levelingDelayFormat);
		result.append(", linkedFields: ");
		if (linkedFieldsESet) result.append(linkedFields); else result.append("<unset>");
		result.append(", milestone: ");
		if (milestoneESet) result.append(milestone); else result.append("<unset>");
		result.append(", notes: ");
		result.append(notes);
		result.append(", overallocated: ");
		if (overallocatedESet) result.append(overallocated); else result.append("<unset>");
		result.append(", overtimeCost: ");
		result.append(overtimeCost);
		result.append(", overtimeWork: ");
		result.append(overtimeWork);
		result.append(", regularWork: ");
		result.append(regularWork);
		result.append(", remainingCost: ");
		result.append(remainingCost);
		result.append(", remainingOvertimeCost: ");
		result.append(remainingOvertimeCost);
		result.append(", remainingOvertimeWork: ");
		result.append(remainingOvertimeWork);
		result.append(", remainingWork: ");
		result.append(remainingWork);
		result.append(", responsePending: ");
		if (responsePendingESet) result.append(responsePending); else result.append("<unset>");
		result.append(", start: ");
		result.append(start);
		result.append(", stop: ");
		result.append(stop);
		result.append(", resume: ");
		result.append(resume);
		result.append(", startVariance: ");
		result.append(startVariance);
		result.append(", units: ");
		if (unitsESet) result.append(units); else result.append("<unset>");
		result.append(", updateNeeded: ");
		if (updateNeededESet) result.append(updateNeeded); else result.append("<unset>");
		result.append(", vAC: ");
		if (vACESet) result.append(vAC); else result.append("<unset>");
		result.append(", work: ");
		result.append(work);
		result.append(", workContour: ");
		result.append(workContour);
		result.append(", bCWS: ");
		if (bCWSESet) result.append(bCWS); else result.append("<unset>");
		result.append(", bCWP: ");
		if (bCWPESet) result.append(bCWP); else result.append("<unset>");
		result.append(", bookingType: ");
		result.append(bookingType);
		result.append(", actualWorkProtected: ");
		result.append(actualWorkProtected);
		result.append(", actualOvertimeWorkProtected: ");
		result.append(actualOvertimeWorkProtected);
		result.append(", creationDate: ");
		result.append(creationDate);
		result.append(')');
		return result.toString();
	}

} //AssignmentImpl
