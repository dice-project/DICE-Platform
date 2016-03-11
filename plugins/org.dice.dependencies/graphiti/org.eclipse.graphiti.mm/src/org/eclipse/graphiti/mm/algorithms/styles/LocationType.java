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
package org.eclipse.graphiti.mm.algorithms.styles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Location Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getLocationType()
 * @model
 * @generated
 */
public enum LocationType implements Enumerator {
	/**
	 * The '<em><b>LOCATION TYPE RELATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCATION_TYPE_RELATIVE_VALUE
	 * @generated
	 * @ordered
	 */
	LOCATION_TYPE_RELATIVE(1, "LOCATION_TYPE_RELATIVE", "LOCATION_TYPE_RELATIVE"),

	/**
	 * The '<em><b>LOCATION TYPE ABSOLUTE START</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCATION_TYPE_ABSOLUTE_START_VALUE
	 * @generated
	 * @ordered
	 */
	LOCATION_TYPE_ABSOLUTE_START(2, "LOCATION_TYPE_ABSOLUTE_START", "LOCATION_TYPE_ABSOLUTE_START"),

	/**
	 * The '<em><b>LOCATION TYPE ABSOLUTE END</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCATION_TYPE_ABSOLUTE_END_VALUE
	 * @generated
	 * @ordered
	 */
	LOCATION_TYPE_ABSOLUTE_END(3, "LOCATION_TYPE_ABSOLUTE_END", "LOCATION_TYPE_ABSOLUTE_END");

	/**
	 * The '<em><b>LOCATION TYPE RELATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCATION TYPE RELATIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCATION_TYPE_RELATIVE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOCATION_TYPE_RELATIVE_VALUE = 1;

	/**
	 * The '<em><b>LOCATION TYPE ABSOLUTE START</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCATION TYPE ABSOLUTE START</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCATION_TYPE_ABSOLUTE_START
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOCATION_TYPE_ABSOLUTE_START_VALUE = 2;

	/**
	 * The '<em><b>LOCATION TYPE ABSOLUTE END</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCATION TYPE ABSOLUTE END</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCATION_TYPE_ABSOLUTE_END
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOCATION_TYPE_ABSOLUTE_END_VALUE = 3;

	/**
	 * An array of all the '<em><b>Location Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LocationType[] VALUES_ARRAY =
		new LocationType[] {
			LOCATION_TYPE_RELATIVE,
			LOCATION_TYPE_ABSOLUTE_START,
			LOCATION_TYPE_ABSOLUTE_END,
		};

	/**
	 * A public read-only list of all the '<em><b>Location Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<LocationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Location Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LocationType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LocationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Location Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LocationType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LocationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Location Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LocationType get(int value) {
		switch (value) {
			case LOCATION_TYPE_RELATIVE_VALUE: return LOCATION_TYPE_RELATIVE;
			case LOCATION_TYPE_ABSOLUTE_START_VALUE: return LOCATION_TYPE_ABSOLUTE_START;
			case LOCATION_TYPE_ABSOLUTE_END_VALUE: return LOCATION_TYPE_ABSOLUTE_END;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LocationType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //LocationType
