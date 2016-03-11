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
 * A representation of the literals of the enumeration '<em><b>Orientation</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.graphiti.mm.algorithms.styles.StylesPackage#getOrientation()
 * @model
 * @generated
 */
public enum Orientation implements Enumerator {
	/**
	 * The '<em><b>ALIGNMENT CENTER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_CENTER_VALUE
	 * @generated
	 * @ordered
	 */
	ALIGNMENT_CENTER(0, "ALIGNMENT_CENTER", "ALIGNMENT_CENTER"),

	/**
	 * The '<em><b>ALIGNMENT LEFT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	ALIGNMENT_LEFT(1, "ALIGNMENT_LEFT", "ALIGNMENT_LEFT"),

	/**
	 * The '<em><b>ALIGNMENT TOP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_TOP_VALUE
	 * @generated
	 * @ordered
	 */
	ALIGNMENT_TOP(2, "ALIGNMENT_TOP", "ALIGNMENT_TOP"),

	/**
	 * The '<em><b>ALIGNMENT RIGHT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_RIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	ALIGNMENT_RIGHT(3, "ALIGNMENT_RIGHT", "ALIGNMENT_RIGHT"),

	/**
	 * The '<em><b>ALIGNMENT BOTTOM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_BOTTOM_VALUE
	 * @generated
	 * @ordered
	 */
	ALIGNMENT_BOTTOM(4, "ALIGNMENT_BOTTOM", "ALIGNMENT_BOTTOM"),

	/**
	 * The '<em><b>ALIGNMENT MIDDLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_MIDDLE_VALUE
	 * @generated
	 * @ordered
	 */
	ALIGNMENT_MIDDLE(5, "ALIGNMENT_MIDDLE", "ALIGNMENT_MIDDLE"),

	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED_VALUE
	 * @generated
	 * @ordered
	 */
	UNSPECIFIED(6, "UNSPECIFIED", "UNSPECIFIED");

	/**
	 * The '<em><b>ALIGNMENT CENTER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALIGNMENT CENTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_CENTER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALIGNMENT_CENTER_VALUE = 0;

	/**
	 * The '<em><b>ALIGNMENT LEFT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALIGNMENT LEFT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_LEFT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALIGNMENT_LEFT_VALUE = 1;

	/**
	 * The '<em><b>ALIGNMENT TOP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALIGNMENT TOP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_TOP
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALIGNMENT_TOP_VALUE = 2;

	/**
	 * The '<em><b>ALIGNMENT RIGHT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALIGNMENT RIGHT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_RIGHT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALIGNMENT_RIGHT_VALUE = 3;

	/**
	 * The '<em><b>ALIGNMENT BOTTOM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALIGNMENT BOTTOM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_BOTTOM
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALIGNMENT_BOTTOM_VALUE = 4;

	/**
	 * The '<em><b>ALIGNMENT MIDDLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ALIGNMENT MIDDLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALIGNMENT_MIDDLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ALIGNMENT_MIDDLE_VALUE = 5;

	/**
	 * The '<em><b>UNSPECIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSPECIFIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSPECIFIED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNSPECIFIED_VALUE = 6;

	/**
	 * An array of all the '<em><b>Orientation</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Orientation[] VALUES_ARRAY =
		new Orientation[] {
			ALIGNMENT_CENTER,
			ALIGNMENT_LEFT,
			ALIGNMENT_TOP,
			ALIGNMENT_RIGHT,
			ALIGNMENT_BOTTOM,
			ALIGNMENT_MIDDLE,
			UNSPECIFIED,
		};

	/**
	 * A public read-only list of all the '<em><b>Orientation</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Orientation> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Orientation</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Orientation get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Orientation result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Orientation</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Orientation getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Orientation result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Orientation</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Orientation get(int value) {
		switch (value) {
			case ALIGNMENT_CENTER_VALUE: return ALIGNMENT_CENTER;
			case ALIGNMENT_LEFT_VALUE: return ALIGNMENT_LEFT;
			case ALIGNMENT_TOP_VALUE: return ALIGNMENT_TOP;
			case ALIGNMENT_RIGHT_VALUE: return ALIGNMENT_RIGHT;
			case ALIGNMENT_BOTTOM_VALUE: return ALIGNMENT_BOTTOM;
			case ALIGNMENT_MIDDLE_VALUE: return ALIGNMENT_MIDDLE;
			case UNSPECIFIED_VALUE: return UNSPECIFIED;
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
	private Orientation(int value, String name, String literal) {
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
	
} //Orientation
