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
package org.eclipse.epf.xml.uma;

import org.eclipse.epf.uma.ecore.IModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Order</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a relationship between two Breakdown Elements in which one Breakdown Elements depends on the start or finish of another Breakdown Elements in order to begin or end.  
 * The Work Order class defines predecessor and successor relations amongst Breakdown Elements.  This information is in particular critical for planning applications.  See more details on different types of Work Order relationships at Work Order Type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkOrder#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkOrder#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkOrder#getLinkType <em>Link Type</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkOrder#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkOrder()
 * @model extendedMetaData="name='WorkOrder' kind='simple'"
 * @extends IModelObject
 * @generated
 */
public interface WorkOrder extends IModelObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkOrder_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkOrder#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines a global unique id for a work order.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkOrder_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkOrder#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Link Type</b></em>' attribute.
	 * The default value is <code>"finishToStart"</code>.
	 * The literals are from the enumeration {@link org.eclipse.epf.xml.uma.WorkOrderType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This attribute expresses the type of the Work Order relationship by assigning a value from the Work Order Type enumeration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link Type</em>' attribute.
	 * @see org.eclipse.epf.xml.uma.WorkOrderType
	 * @see #isSetLinkType()
	 * @see #unsetLinkType()
	 * @see #setLinkType(WorkOrderType)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkOrder_LinkType()
	 * @model default="finishToStart" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='linkType'"
	 * @generated
	 */
	WorkOrderType getLinkType();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkOrder#getLinkType <em>Link Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Type</em>' attribute.
	 * @see org.eclipse.epf.xml.uma.WorkOrderType
	 * @see #isSetLinkType()
	 * @see #unsetLinkType()
	 * @see #getLinkType()
	 * @generated
	 */
	void setLinkType(WorkOrderType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.WorkOrder#getLinkType <em>Link Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLinkType()
	 * @see #getLinkType()
	 * @see #setLinkType(WorkOrderType)
	 * @generated
	 */
	void unsetLinkType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.WorkOrder#getLinkType <em>Link Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Link Type</em>' attribute is set.
	 * @see #unsetLinkType()
	 * @see #getLinkType()
	 * @see #setLinkType(WorkOrderType)
	 * @generated
	 */
	boolean isSetLinkType();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This attribute store method element property values associated with this work order
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Properties</em>' attribute.
	 * @see #setProperties(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkOrder_Properties()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='properties'"
	 * @generated
	 */
	String getProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkOrder#getProperties <em>Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Properties</em>' attribute.
	 * @see #getProperties()
	 * @generated
	 */
	void setProperties(String value);

} // WorkOrder
