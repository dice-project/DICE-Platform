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

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timephased Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The definition of the time phased data block.
 * 		        
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.TimephasedDataType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.TimephasedDataType#getUID <em>UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.TimephasedDataType#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.TimephasedDataType#getFinish <em>Finish</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.TimephasedDataType#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.TimephasedDataType#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType()
 * @model extendedMetaData="name='TimephasedDataType' kind='elementOnly'"
 * @generated
 */
public interface TimephasedDataType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 The type of task timephased data. Values are:
	 *                     1=Assignment Remaining Work
	 *                     2=Assignment Actual Work
	 *                     3=Assignment Actual Overtime Work
	 *                     4=Assignment Baseline Work
	 *                     5=Assignment Baseline Cost
	 *                     6=Assignment Actual Cost
	 *                     7=Resource Baseline Work
	 *                     8=Resource Baseline Cost
	 *                     9=Task Baseline Work
	 *                     10=Task Baseline Cost
	 *                     11=Task Percent Complete
	 *                     16=Assignment Baseline 1 Work
	 *                     17=Assignment Baseline 1 Cost
	 *                     18=Task Baseline 1 Work
	 *                     19=Task Baseline 1 Cost
	 *                     20=Resource Baseline 1 Work
	 *                     21=Resource Baseline 1 Cost
	 *                     22=Assignment Baseline 2 Work
	 *                     23=Assignment Baseline 2 Cost
	 *                     24=Task Baseline 2 Work
	 *                     25=Task Baseline 2 Cost
	 *                     26=Resource Baseline 2 Work
	 *                     27=Resource Baseline 2 Cost
	 *                     28=Assignment Baseline 3 Work
	 *                     29=Assignment Baseline 3 Cost
	 *                     30=Task Baseline 3 Work
	 *                     31=Task Baseline 3 Cost
	 *                     32=Resource Baseline 3 Work
	 *                     33=Resource Baseline 3 Cost
	 *                     34=Assignment Baseline 4 Work
	 *                     35=Assignment Baseline 4 Cost
	 *                     36=Task Baseline 4 Work
	 *                     37=Task Baseline 4 Cost
	 *                     38=Resource Baseline 4 Work
	 *                     39=Resource Baseline 4 Cost
	 *                     40=Assignment Baseline 5 Work
	 *                     41=Assignment Baseline 5 Cost
	 *                     42=Task Baseline 5 Work
	 *                     43=Task Baseline 5 Cost
	 *                     44=Resource Baseline 5 Work
	 *                     45=Resource Baseline 5 Cost
	 *                     46=Assignment Baseline 6 Work
	 *                     47=Assignment Baseline 6 Cost
	 *                     48=Task Baseline 6 Work
	 *                     49=Task Baseline 6 Cost
	 *                     50=Resource Baseline 6 Work
	 *                     51=Resource Baseline 6 Cost
	 *                     52=Assignment Baseline 7 Work
	 *                     53=Assignment Baseline 7 Cost
	 *                     54=Task Baseline 7 Work
	 *                     55=Task Baseline 7 Cost
	 *                     56=Resource Baseline 7 Work
	 *                     57=Resource Baseline 7 Cost
	 *                     58=Assignment Baseline 8 Work
	 *                     59=Assignment Baseline 8 Cost
	 *                     60=Task Baseline 8 Work
	 *                     61=Task Baseline 8 Cost
	 *                     62=Resource Baseline 8 Work
	 *                     63=Resource Baseline 8 Cost
	 *                     64=Assignment Baseline 9 Work
	 *                     65=Assignment Baseline 9 Cost
	 *                     66=Task Baseline 9 Work
	 *                     67=Task Baseline 9 Cost
	 *                     68=Resource Baseline 9 Work
	 *                     69=Resource Baseline 9 Cost
	 *                     70=Assignment Baseline 10 Work
	 *                     71=Assignment Baseline 10 Cost
	 *                     72=Task Baseline 10 Work
	 *                     73=Task Baseline 10 Cost
	 *                     74=Resource Baseline 10 Work
	 *                     75=Resource Baseline 10 Cost
	 *                     76=Physical Percent Complete
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType_Type()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.TypeType3"
	 *        extendedMetaData="kind='element' name='Type' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimephasedDataType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the timephased data record.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>UID</em>' attribute.
	 * @see #setUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType_UID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer" required="true"
	 *        extendedMetaData="kind='element' name='UID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimephasedDataType#getUID <em>UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UID</em>' attribute.
	 * @see #getUID()
	 * @generated
	 */
	void setUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The start date of the timephased data period.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType_Start()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Start' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimephasedDataType#getStart <em>Start</em>}' attribute.
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
	 * The finish date of the timephased data period.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Finish</em>' attribute.
	 * @see #setFinish(Object)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType_Finish()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='element' name='Finish' namespace='##targetNamespace'"
	 * @generated
	 */
	Object getFinish();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimephasedDataType#getFinish <em>Finish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish</em>' attribute.
	 * @see #getFinish()
	 * @generated
	 */
	void setFinish(Object value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The time unit of the timephased data period. 
	 *                 Values are: 0=m, 1=h, 2=d, 3=w, 5=mo, 8=y
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType_Unit()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.UnitType"
	 *        extendedMetaData="kind='element' name='Unit' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimephasedDataType#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value per unit of time for the timephased data period.
	 *                 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getTimephasedDataType_Value()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Value' namespace='##targetNamespace'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.TimephasedDataType#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // TimephasedDataType
