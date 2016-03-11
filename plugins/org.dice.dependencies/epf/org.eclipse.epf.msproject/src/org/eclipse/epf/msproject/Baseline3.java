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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Baseline3</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getTimephasedData <em>Timephased Data</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getNumber <em>Number</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getWork <em>Work</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getCost <em>Cost</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getBCWS <em>BCWS</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.Baseline3#getBCWP <em>BCWP</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3()
 * @model extendedMetaData="name='Baseline_._2_._type' kind='elementOnly'"
 * @generated
 */
public interface Baseline3 extends EObject {
	/**
	 * Returns the value of the '<em><b>Timephased Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.msproject.TimephasedDataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time phased data associated with the Baseline.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Timephased Data</em>' containment reference list.
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_TimephasedData()
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
	 * @see #setNumber(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_Number()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='Number' namespace='##targetNamespace'"
	 * @generated
	 */
	String getNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(String value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled start date of the assignment when the baseline was saved.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_Start()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Start' namespace='##targetNamespace'"
	 * @generated
	 */
	String getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(String value);

	/**
	 * Returns the value of the '<em><b>Finish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The scheduled finish date of the assignment when the baseline was saved.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish</em>' attribute.
	 * @see #setFinish(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_Finish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Finish' namespace='##targetNamespace'"
	 * @generated
	 */
	String getFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getFinish <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish</em>' attribute.
	 * @see #getFinish()
	 * @generated
	 */
	void setFinish(String value);

	/**
	 * Returns the value of the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total amount of work scheduled on the assignment when the baseline was saved.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Work</em>' attribute.
	 * @see #setWork(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_Work()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Duration"
	 *        extendedMetaData="kind='element' name='Work' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getWork();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getWork <em>Work</em>}' attribute.
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
	 * The total projected cost of the assignment when the baseline was saved.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_Cost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCost();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(String value);

	/**
	 * Returns the value of the '<em><b>BCWS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The budgeted cost of work on the assignment.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWS</em>' attribute.
	 * @see #isSetBCWS()
	 * @see #unsetBCWS()
	 * @see #setBCWS(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_BCWS()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWS' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWS();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getBCWS <em>BCWS</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getBCWS <em>BCWS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWS()
	 * @see #getBCWS()
	 * @see #setBCWS(float)
	 * @generated
	 */
	void unsetBCWS();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Baseline3#getBCWS <em>BCWS</em>}' attribute is set.
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
	 * The budgeted cost of work performed on the assignment to-date.
	 *                                         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>BCWP</em>' attribute.
	 * @see #isSetBCWP()
	 * @see #unsetBCWP()
	 * @see #setBCWP(float)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getBaseline3_BCWP()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
	 *        extendedMetaData="kind='element' name='BCWP' namespace='##targetNamespace'"
	 * @generated
	 */
	float getBCWP();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getBCWP <em>BCWP</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.Baseline3#getBCWP <em>BCWP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	void unsetBCWP();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.Baseline3#getBCWP <em>BCWP</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>BCWP</em>' attribute is set.
	 * @see #unsetBCWP()
	 * @see #getBCWP()
	 * @see #setBCWP(float)
	 * @generated
	 */
	boolean isSetBCWP();

} // Baseline3
