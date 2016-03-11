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

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Process Package that applies the principles of encapsulation.  A Process Component realizes one or more Interfaces which specify inputs and outputs of the component. There might be many components realizing the same interfaces, but using different techniques to achieve similar outputs for similar inputs.  Whereas the Component Interfaces represent component specifications (black box descriptions of the component), good candidates for component realizations can be found in Capability Patterns (white box descriptions for the component).
 * UMA supports replaceable and reusable Process Components realizing the principles of encapsulation. Certain situations in a software development project might require that concrete realizations of parts of the process remain undecided or will be decided by the executing team itself (e.g. in outsourcing situations).  UMA provides a unique component concept defining interfaces for work product input and output, allowing treating the actual definition of the work that produces the outputs as a "black box".  At any point during a project the component "realization" detailing the work can be added to the process.  The component approach also allows that different styles or techniques of doing work can be replaced with one another.  For example, a software code output of a component could be produced with a model-driven development or a code-centric technique.  The component concept encapsulates the actual work and lets the development team choose the appropriate technique and fill the component's realization with their choice of Activities that produce the required outputs.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getCopyright <em>Copyright</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getAuthors <em>Authors</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getChangeDate <em>Change Date</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link org.eclipse.epf.xml.uma.ProcessComponent#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent()
 * @model extendedMetaData="name='ProcessComponent' kind='elementOnly'"
 * @generated
 */
public interface ProcessComponent extends ProcessPackage {
	/**
	 * Returns the value of the '<em><b>Copyright</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copyright</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copyright</em>' attribute.
	 * @see #setCopyright(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_Copyright()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Copyright'"
	 * @generated
	 */
	String getCopyright();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getCopyright <em>Copyright</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copyright</em>' attribute.
	 * @see #getCopyright()
	 * @generated
	 */
	void setCopyright(String value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' containment reference.
	 * @see #setInterface(ProcessComponentInterface)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_Interface()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Interface'"
	 * @generated
	 */
	ProcessComponentInterface getInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getInterface <em>Interface</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' containment reference.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(ProcessComponentInterface value);

	/**
	 * Returns the value of the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' containment reference.
	 * @see #setProcess(org.eclipse.epf.xml.uma.Process)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_Process()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Process'"
	 * @generated
	 */
	org.eclipse.epf.xml.uma.Process getProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getProcess <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' containment reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(org.eclipse.epf.xml.uma.Process value);

	/**
	 * Returns the value of the '<em><b>Authors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Method Unit is being created and owned by an author or authoring team.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Authors</em>' attribute.
	 * @see #setAuthors(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_Authors()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='authors'"
	 * @generated
	 */
	String getAuthors();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getAuthors <em>Authors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authors</em>' attribute.
	 * @see #getAuthors()
	 * @generated
	 */
	void setAuthors(String value);

	/**
	 * Returns the value of the '<em><b>Change Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date the last change that resulted into this version has been made.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Date</em>' attribute.
	 * @see #setChangeDate(XMLGregorianCalendar)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_ChangeDate()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime"
	 *        extendedMetaData="kind='attribute' name='changeDate'"
	 * @generated
	 */
	XMLGregorianCalendar getChangeDate();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getChangeDate <em>Change Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Date</em>' attribute.
	 * @see #getChangeDate()
	 * @generated
	 */
	void setChangeDate(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The description of the last change that resulted into this version.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Description</em>' attribute.
	 * @see #setChangeDescription(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_ChangeDescription()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='changeDescription'"
	 * @generated
	 */
	String getChangeDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getChangeDescription <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Description</em>' attribute.
	 * @see #getChangeDescription()
	 * @generated
	 */
	void setChangeDescription(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Every Package has a version number used to track changes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.epf.xml.uma.UmaPackage#getProcessComponent_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.epf.xml.uma.ProcessComponent#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // ProcessComponent
