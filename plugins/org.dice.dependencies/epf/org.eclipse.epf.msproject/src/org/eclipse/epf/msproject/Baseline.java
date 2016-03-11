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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Baseline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getTimephasedData <em>Timephased Data</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getNumber <em>Number</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#isInterim <em>Interim</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getDurationFormat <em>Duration Format</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#isEstimatedDuration <em>Estimated Duration</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline#getBCWP <em>BCWP</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline()
 * @model extendedMetaData="name='Baseline_._type' kind='elementOnly'"
 * @generated
 */
public interface Baseline extends EObject {
	/**
	 * Returns the value of the '<em><b>Timephased Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.TimephasedDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time phased data block associated with the task baseline.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timephased Data</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_TimephasedData()
	 * @model type="org.eclipse.epf.msproject.TimephasedDataType" containment="true" resolveProxies="false"
	 *        extendedMetaData="kind='element' name='TimephasedData' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getTimephasedData();

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique number of the baseline data record.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Number()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='Number' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Interim</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether this is an Interim Baseline.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interim</em>' attribute.
	 * @see #isSetInterim()
	 * @see #unsetInterim()
	 * @see #setInterim(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Interim()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='Interim' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isInterim();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#isInterim <em>Interim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interim</em>' attribute.
	 * @see #isSetInterim()
	 * @see #unsetInterim()
	 * @see #isInterim()
	 * @generated
	 */
	void setInterim(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Baseline#isInterim <em>Interim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInterim()
	 * @see #isInterim()
	 * @see #setInterim(boolean)
	 * @generated
	 */
	void unsetInterim();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Baseline#isInterim <em>Interim</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Interim</em>' attribute is set.
	 * @see #unsetInterim()
	 * @see #isInterim()
	 * @see #setInterim(boolean)
	 * @generated
	 */
	boolean isSetInterim();

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled start date of the task when the baseline was saved.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Start()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Start' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Object value);

	/**
	 * Returns the value of the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled finish date of the task when the baseline was saved.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish</em>' attribute.
	 * @see #setFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Finish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Finish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getFinish <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish</em>' attribute.
	 * @see #getFinish()
	 * @generated
	 */
	void setFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled duration of the task when the baseline was saved.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Duration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Duration' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getDuration();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(Object value);

	/**
	 * Returns the value of the '<em><b>Duration Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The format for expressing the Duration of the Task baseline.  
	 *                                             Values are: 3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 19=%, 20=e%, 
	 *                                             21=null, 35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 42=ew?, 43=mo?, 44=emo?, 
	 *                                             51=%?, 52=e%? and 53=null.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration Format</em>' attribute.
	 * @see #setDurationFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_DurationFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.DurationFormatType1"
	 *        extendedMetaData="kind='element' name='DurationFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getDurationFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getDurationFormat <em>Duration Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration Format</em>' attribute.
	 * @see #getDurationFormat()
	 * @generated
	 */
	void setDurationFormat(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Estimated Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the baseline duration of the task was estimated.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Estimated Duration</em>' attribute.
	 * @see #isSetEstimatedDuration()
	 * @see #unsetEstimatedDuration()
	 * @see #setEstimatedDuration(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_EstimatedDuration()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='EstimatedDuration' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isEstimatedDuration();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#isEstimatedDuration <em>Estimated Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Estimated Duration</em>' attribute.
	 * @see #isSetEstimatedDuration()
	 * @see #unsetEstimatedDuration()
	 * @see #isEstimatedDuration()
	 * @generated
	 */
	void setEstimatedDuration(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Baseline#isEstimatedDuration <em>Estimated Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEstimatedDuration()
	 * @see #isEstimatedDuration()
	 * @see #setEstimatedDuration(boolean)
	 * @generated
	 */
	void unsetEstimatedDuration();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Baseline#isEstimatedDuration <em>Estimated Duration</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Estimated Duration</em>' attribute is set.
	 * @see #unsetEstimatedDuration()
	 * @see #isEstimatedDuration()
	 * @see #setEstimatedDuration(boolean)
	 * @generated
	 */
	boolean isSetEstimatedDuration();

	/**
	 * Returns the value of the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled work of the task when the baseline was saved.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work</em>' attribute.
	 * @see #setWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Work()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Work' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getWork <em>Work</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work</em>' attribute.
	 * @see #getWork()
	 * @generated
	 */
	void setWork(Object value);

	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The projected cost of the task when the baseline was saved.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(BigDecimal)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_Cost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	BigDecimal getCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(BigDecimal value);

	/**
	 * Returns the value of the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budgeted cost of work scheduled for the task.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #setBCWS(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_BCWS()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWS' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWS();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #getBCWS()
	 * @generated
	 */
	void setBCWS(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Baseline#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	void unsetBCWS();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Baseline#getBCWS <em>BCWS</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>BCWS</em>' attribute is set.
	 * @see #unsetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	boolean isSetBCWS();

	/**
	 * Returns the value of the '<em><b>BCWP</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budgeted cost of work performed on the task to-date.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #setBCWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline_BCWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #getBCWP()
	 * @generated
	 */
	void setBCWP(float value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Baseline#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	void unsetBCWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Baseline#getBCWP <em>BCWP</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>BCWP</em>' attribute is set.
	 * @see #unsetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	boolean isSetBCWP();

} // Baseline
