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
 * A representation of the model object '<em><b>Predecessor Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.PredecessorLink#getPredecessorUID <em>Predecessor UID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.PredecessorLink#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.PredecessorLink#isCrossProject <em>Cross Project</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.PredecessorLink#getCrossProjectName <em>Cross Project Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.PredecessorLink#getLinkLag <em>Link Lag</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.PredecessorLink#getLagFormat <em>Lag Format</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink()
 * @model extendedMetaData="name='PredecessorLink_._type' kind='elementOnly'"
 * @generated
 */
public interface PredecessorLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Predecessor UID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique identifier of the predecessor task.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predecessor UID</em>' attribute.
	 * @see #setPredecessorUID(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink_PredecessorUID()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='PredecessorUID' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getPredecessorUID();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#getPredecessorUID <em>Predecessor UID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predecessor UID</em>' attribute.
	 * @see #getPredecessorUID()
	 * @generated
	 */
	void setPredecessorUID(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The link type.  Values are 0=FF, 1=FS, 2=SF and 3=SS
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink_Type()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.TypeType1"
	 *        extendedMetaData="kind='element' name='Type' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cross Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the predecessor is part of another project.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cross Project</em>' attribute.
	 * @see #isSetCrossProject()
	 * @see #unsetCrossProject()
	 * @see #setCrossProject(boolean)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink_CrossProject()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='CrossProject' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isCrossProject();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#isCrossProject <em>Cross Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cross Project</em>' attribute.
	 * @see #isSetCrossProject()
	 * @see #unsetCrossProject()
	 * @see #isCrossProject()
	 * @generated
	 */
	void setCrossProject(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#isCrossProject <em>Cross Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCrossProject()
	 * @see #isCrossProject()
	 * @see #setCrossProject(boolean)
	 * @generated
	 */
	void unsetCrossProject();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#isCrossProject <em>Cross Project</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cross Project</em>' attribute is set.
	 * @see #unsetCrossProject()
	 * @see #isCrossProject()
	 * @see #setCrossProject(boolean)
	 * @generated
	 */
	boolean isSetCrossProject();

	/**
	 * Returns the value of the '<em><b>Cross Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The external predecessor project.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cross Project Name</em>' attribute.
	 * @see #setCrossProjectName(String)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink_CrossProjectName()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='CrossProjectName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCrossProjectName();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#getCrossProjectName <em>Cross Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cross Project Name</em>' attribute.
	 * @see #getCrossProjectName()
	 * @generated
	 */
	void setCrossProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Link Lag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The amount of lag in tenths of a minute.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link Lag</em>' attribute.
	 * @see #setLinkLag(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink_LinkLag()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='element' name='LinkLag' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLinkLag();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#getLinkLag <em>Link Lag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Lag</em>' attribute.
	 * @see #getLinkLag()
	 * @generated
	 */
	void setLinkLag(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Lag Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The format for expressing the lag format.  
	 *                                             Values are: 3=m, 4=em, 5=h, 6=eh, 7=d, 8=ed, 9=w, 10=ew, 11=mo, 12=emo, 19=%, 
	 *                                             20=e%, 35=m?, 36=em?, 37=h?, 38=eh?, 39=d?, 40=ed?, 41=w?, 42=ew?, 43=mo?, 
	 *                                             44=emo?, 51=%?, 52=e%? and 53=null.
	 *                                             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lag Format</em>' attribute.
	 * @see #setLagFormat(BigInteger)
	 * @see org.eclipse.epf.msproject.MsprojectPackage#getPredecessorLink_LagFormat()
	 * @model unique="false" dataType="org.eclipse.epf.msproject.LagFormatType"
	 *        extendedMetaData="kind='element' name='LagFormat' namespace='##targetNamespace'"
	 * @generated
	 */
	BigInteger getLagFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.msproject.PredecessorLink#getLagFormat <em>Lag Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lag Format</em>' attribute.
	 * @see #getLagFormat()
	 * @generated
	 */
	void setLagFormat(BigInteger value);

} // PredecessorLink
