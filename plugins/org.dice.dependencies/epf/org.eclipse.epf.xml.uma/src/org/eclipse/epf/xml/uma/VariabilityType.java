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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Variability Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * An Enumeration used for values for instances of Variability Element's attribute variabilityType.  It defines the nature of how a Variability Element extends another Variability Element. See enumeration literals for definitions for each type.
 * <!-- end-model-doc -->
 * @see org.eclipse.epf.xml.uma.UmaPackage#getVariabilityType()
 * @model extendedMetaData="name='VariabilityType'"
 * @generated
 */
public enum VariabilityType implements Enumerator
{
	/**
	 * The '<em><b>Na</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NA_VALUE
	 * @generated
	 * @ordered
	 */
	NA(0, "na", "na"),
	/**
	 * The '<em><b>Contributes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTRIBUTES_VALUE
	 * @generated
	 * @ordered
	 */
	CONTRIBUTES(1, "contributes", "contributes"),
	/**
	 * The '<em><b>Extends</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXTENDS_VALUE
	 * @generated
	 * @ordered
	 */
	EXTENDS(2, "extends", "extends"),
	/**
	 * The '<em><b>Replaces</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPLACES_VALUE
	 * @generated
	 * @ordered
	 */
	REPLACES(3, "replaces", "replaces"),
	/**
	 * The '<em><b>Local Contribution</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_CONTRIBUTION_VALUE
	 * @generated
	 * @ordered
	 */
	LOCAL_CONTRIBUTION(4, "localContribution", "localContribution"),
	/**
	 * The '<em><b>Local Replacement</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_REPLACEMENT_VALUE
	 * @generated
	 * @ordered
	 */
	LOCAL_REPLACEMENT(5, "localReplacement", "localReplacement"),
	/**
	 * The '<em><b>Extends Replaces</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXTENDS_REPLACES_VALUE
	 * @generated
	 * @ordered
	 */
	EXTENDS_REPLACES(6, "extendsReplaces", "extendsReplaces");
	/**
	 * The '<em><b>Na</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Na</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NA
	 * @model name="na"
	 * @generated
	 * @ordered
	 */
	public static final int NA_VALUE = 0;

	/**
	 * The '<em><b>Contributes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Contributes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTRIBUTES
	 * @model name="contributes"
	 * @generated
	 * @ordered
	 */
	public static final int CONTRIBUTES_VALUE = 1;

	/**
	 * The '<em><b>Extends</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Extends</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXTENDS
	 * @model name="extends"
	 * @generated
	 * @ordered
	 */
	public static final int EXTENDS_VALUE = 2;

	/**
	 * The '<em><b>Replaces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Replaces</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REPLACES
	 * @model name="replaces"
	 * @generated
	 * @ordered
	 */
	public static final int REPLACES_VALUE = 3;

	/**
	 * The '<em><b>Local Contribution</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local Contribution</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_CONTRIBUTION
	 * @model name="localContribution"
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL_CONTRIBUTION_VALUE = 4;

	/**
	 * The '<em><b>Local Replacement</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local Replacement</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_REPLACEMENT
	 * @model name="localReplacement"
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL_REPLACEMENT_VALUE = 5;

	/**
	 * The '<em><b>Extends Replaces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Extends Replaces</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXTENDS_REPLACES
	 * @model name="extendsReplaces"
	 * @generated
	 * @ordered
	 */
	public static final int EXTENDS_REPLACES_VALUE = 6;

	/**
	 * An array of all the '<em><b>Variability Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final VariabilityType[] VALUES_ARRAY =
		new VariabilityType[] {
			NA,
			CONTRIBUTES,
			EXTENDS,
			REPLACES,
			LOCAL_CONTRIBUTION,
			LOCAL_REPLACEMENT,
			EXTENDS_REPLACES,
		};

	/**
	 * A public read-only list of all the '<em><b>Variability Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<VariabilityType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Variability Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VariabilityType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VariabilityType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Variability Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VariabilityType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VariabilityType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Variability Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VariabilityType get(int value) {
		switch (value) {
			case NA_VALUE: return NA;
			case CONTRIBUTES_VALUE: return CONTRIBUTES;
			case EXTENDS_VALUE: return EXTENDS;
			case REPLACES_VALUE: return REPLACES;
			case LOCAL_CONTRIBUTION_VALUE: return LOCAL_CONTRIBUTION;
			case LOCAL_REPLACEMENT_VALUE: return LOCAL_REPLACEMENT;
			case EXTENDS_REPLACES_VALUE: return EXTENDS_REPLACES;
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
	private VariabilityType(int value, String name, String literal) {
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
}
