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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Breakdown Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Breakdown Element that provides specific properties for Breakdown Elements that represent or refer to Work Definitions.  For example its subclass Activity defines work as it is also a subclass of Work Definition.  Its subclass Task Descriptor does not define work by itself, but refers to a Work Definition and therefore can have the same common properties and Work Breakdown Element has.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#getPredecessor <em>Predecessor</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsEventDriven <em>Is Event Driven</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsOngoing <em>Is Ongoing</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsRepeatable <em>Is Repeatable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkBreakdownElement()
 * @model extendedMetaData="name='WorkBreakdownElement' kind='elementOnly'"
 * @generated
 */
public interface WorkBreakdownElement extends BreakdownElement {
	/**
	 * Returns the value of the '<em><b>Group2</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group2</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group2</em>' attribute list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkBreakdownElement_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:30'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Predecessor</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epf.xml.uma.WorkOrder}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predecessor</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predecessor</em>' containment reference list.
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkBreakdownElement_Predecessor()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Predecessor' group='#group:30'"
	 * @generated
	 */
	EList<WorkOrder> getPredecessor();

	/**
	 * Returns the value of the '<em><b>Is Event Driven</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The isEventDriven attribute indicates that the Process Work Definition describes an instance of work which is not started because it has been scheduled to start at a certain point of time, because preceding work is being completed, or input work products are available, but because another specific event has occurred.  Examples for such events are exceptions or problem situations which require specific work to be performed as a result.  Also change management work can be modeled as event driven work analyzing a change request or defect and allocating work dynamically to resources to deal with it following the work described with such Process Work Definition.  The events themselves are not modeled in this version of the specification.  They shall be described as part of the normal descriptions fields available.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Event Driven</em>' attribute.
	 * @see #isSetIsEventDriven()
	 * @see #unsetIsEventDriven()
	 * @see #setIsEventDriven(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkBreakdownElement_IsEventDriven()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isEventDriven'"
	 * @generated
	 */
	boolean isIsEventDriven();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsEventDriven <em>Is Event Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Event Driven</em>' attribute.
	 * @see #isSetIsEventDriven()
	 * @see #unsetIsEventDriven()
	 * @see #isIsEventDriven()
	 * @generated
	 */
	void setIsEventDriven(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsEventDriven <em>Is Event Driven</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsEventDriven()
	 * @see #isIsEventDriven()
	 * @see #setIsEventDriven(boolean)
	 * @generated
	 */
	void unsetIsEventDriven();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsEventDriven <em>Is Event Driven</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Event Driven</em>' attribute is set.
	 * @see #unsetIsEventDriven()
	 * @see #isIsEventDriven()
	 * @see #setIsEventDriven(boolean)
	 * @generated
	 */
	boolean isSetIsEventDriven();

	/**
	 * Returns the value of the '<em><b>Is Ongoing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If the isOngoing attribute is set to True for a Process Work Definition instance, then the element describes an ongoing piece of work without a fixed duration or end state.  For example, the Process Work Definition could represent work of an administrator continuously (e.g. 3h a day) working to ensure that systems are kept in a certain state.  Another example would be program management work overseeing many different projects being scheduled for one particular project at specific reoccurring intervals during the whole lifecycle of the project.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Ongoing</em>' attribute.
	 * @see #isSetIsOngoing()
	 * @see #unsetIsOngoing()
	 * @see #setIsOngoing(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkBreakdownElement_IsOngoing()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isOngoing'"
	 * @generated
	 */
	boolean isIsOngoing();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsOngoing <em>Is Ongoing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Ongoing</em>' attribute.
	 * @see #isSetIsOngoing()
	 * @see #unsetIsOngoing()
	 * @see #isIsOngoing()
	 * @generated
	 */
	void setIsOngoing(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsOngoing <em>Is Ongoing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsOngoing()
	 * @see #isIsOngoing()
	 * @see #setIsOngoing(boolean)
	 * @generated
	 */
	void unsetIsOngoing();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsOngoing <em>Is Ongoing</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Ongoing</em>' attribute is set.
	 * @see #unsetIsOngoing()
	 * @see #isIsOngoing()
	 * @see #setIsOngoing(boolean)
	 * @generated
	 */
	boolean isSetIsOngoing();

	/**
	 * Returns the value of the '<em><b>Is Repeatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This attribute is used to define repetition of work, e.g. iterations.  A Process Work Definition with this attribute set to True shall be repeated more than once on the same set of artifacts.  For example, for an instance of Iteration (defined as a special Process Work Definition below) this attribute is set to True by default indicating that every sub-Activity will be repeated more than once.  However, any Process Work Definition can set this attribute to True to define iterations (e.g. to iterate one Activity consisting of many sub-activities or even Phases, but to iterate just one Task).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Repeatable</em>' attribute.
	 * @see #isSetIsRepeatable()
	 * @see #unsetIsRepeatable()
	 * @see #setIsRepeatable(boolean)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getWorkBreakdownElement_IsRepeatable()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isRepeatable'"
	 * @generated
	 */
	boolean isIsRepeatable();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsRepeatable <em>Is Repeatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Repeatable</em>' attribute.
	 * @see #isSetIsRepeatable()
	 * @see #unsetIsRepeatable()
	 * @see #isIsRepeatable()
	 * @generated
	 */
	void setIsRepeatable(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsRepeatable <em>Is Repeatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsRepeatable()
	 * @see #isIsRepeatable()
	 * @see #setIsRepeatable(boolean)
	 * @generated
	 */
	void unsetIsRepeatable();

	/**
	 * Returns whether the value of the '{@link org.eclipse.epf.xml.uma.WorkBreakdownElement#isIsRepeatable <em>Is Repeatable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Repeatable</em>' attribute is set.
	 * @see #unsetIsRepeatable()
	 * @see #isIsRepeatable()
	 * @see #setIsRepeatable(boolean)
	 * @generated
	 */
	boolean isSetIsRepeatable();

} // WorkBreakdownElement
