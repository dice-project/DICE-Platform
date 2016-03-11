/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.pictograms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#isLocationRelative <em>Location Relative</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnectionDecorator()
 * @model
 * @generated
 */
public interface ConnectionDecorator extends Shape {
	/**
	 * Returns the value of the '<em><b>Location Relative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location Relative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location Relative</em>' attribute.
	 * @see #setLocationRelative(boolean)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnectionDecorator_LocationRelative()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isLocationRelative();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#isLocationRelative <em>Location Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location Relative</em>' attribute.
	 * @see #isLocationRelative()
	 * @generated
	 */
	void setLocationRelative(boolean value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(double)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnectionDecorator_Location()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	double getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(double value);

	/**
	 * Returns the value of the '<em><b>Connection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Connection#getConnectionDecorators <em>Connection Decorators</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection</em>' container reference.
	 * @see #setConnection(Connection)
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getConnectionDecorator_Connection()
	 * @see org.eclipse.graphiti.mm.pictograms.Connection#getConnectionDecorators
	 * @model opposite="connectionDecorators" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Connection getConnection();

	/**
	 * Sets the value of the '{@link org.eclipse.graphiti.mm.pictograms.ConnectionDecorator#getConnection <em>Connection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection</em>' container reference.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(Connection value);

} // ConnectionDecorator
