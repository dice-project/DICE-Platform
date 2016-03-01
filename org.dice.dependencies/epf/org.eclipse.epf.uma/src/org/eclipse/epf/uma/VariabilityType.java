//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.uma;

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
 * Variability Type is an Enumeration used for values for instances of Variability Element's attribute variabilityType.  It defines the nature of how a Variability Element extends another Variability Element. See enumeration literals for definitions for each type.
 * <!-- end-model-doc -->
 * @see org.eclipse.epf.uma.UmaPackage#getVariabilityType()
 * @model
 * @generated
 */
public enum VariabilityType implements Enumerator {
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
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the default "not assigned" value of a Variabillity Element's variabilityType attribute which is set in the case no variability association is present between the Variability Element and other Variability Elements.
	 * 
	 * <!-- end-model-doc -->
	 * @see #NA
	 * @model name="na"
	 * @generated
	 * @ordered
	 */
	public static final int NA_VALUE = 0;

	/**
	 * The '<em><b>Contributes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contributes provides a way for instances of Variability Elements to contribute their properties into their base Variability Element without directly altering any of its existing properties, i.e. in an additive fashion.  Properties contributed are: attribute values and association instances.  The effect after interpretation of contribution is that the base Variability Element is logically replaced with an augmented variant of the element that combines attribute values and association instances.  The way this combination is realized depends on the type of the attribute or association.  For example, String attributes are concatenated resolving embedded commands for dependent text or merging text fragments (e.g. descriptions for content elements).  Additional elements in to-many associations are added (e.g. additional Guidance elements or Task Descriptors of an Activity).  Different elements in to-one associations are ignored (e.g. the one Primary Performer of a Task).  Multiple Content Elements can contribute to the same base element and all of these contributions properties are added to the base in the same fashion. The following table provides the detailed list of interpretation rules:
	 * attribute values:	String values from the special Variability Element are concatenated with values from the based-on Variability Element.  Other values from the special Variability Element of any other type such as Integer, Date are ignored.
	 * The identifying attributes guid and name of Method Element are exempt from this rule and will not be modified.
	 * 0..1-association instances:	The one association instance of the based-on Variability Element is kept and any association from the contributing special Variability Element is ignored.
	 * 0..n-association instances:	Association instances of the special Variability Element are added to the already existing association instances of the based-on element.  If both Variability Elements refer to the same object then only one instance of the association will remain.
	 * 
	 * <!-- end-model-doc -->
	 * @see #CONTRIBUTES
	 * @model name="contributes"
	 * @generated
	 * @ordered
	 */
	public static final int CONTRIBUTES_VALUE = 1;

	/**
	 * The '<em><b>Extends</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Extension allows Method Plugins to easily reuse elements from a Base Plugin by providing a kind of inheritance for the special Variability Element.  Attribute values and Association instances are inherited from the based-on Variability Element to the special Variability Element.  The result of interpretation is that the special element has the same properties as the based-on has, but might define its own additions.  Hence extends is not used to modify content of the base Plugin, but to provide the ability for the extending Plugin to define its own content which is a variant of content already defined (e.g. a special version of a generic Review Record for a specific type of review).  The effect of this is that the base Variability Element and any number of extending Variability Elements can be used side by side, but refer to each other via the extends relationship.  Extends also provides the key mechanism for binding Capability Patterns to Processes: A pattern is applied by defining an extends relationships from an Activity of the applying Processes to the Pattern.  The Activity inherits associations instances from the pattern and the pattern appears to be part of the resulting Process after Interpretation.
	 * attribute values:	Values from the based-on Variability element are inherited and used to populate the special Variability Elements attributes.  If the special element attributes are already populated the inherited values are ignored.  
	 * The identifying attributes guid and name of Method Element are exempt from this rule and will not be modified.
	 * 0..1-association instances:	The one association instance of the based-on Variability Element is inherited to the special Variability Element.  If the special Variability Element defines its own association instance then the inherited one is ignored.
	 * 0..n-association instances:	Association instances defined for the based-on Variability Element are inherited to the special Variability Element.  The special element can add additional association instances.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @see #EXTENDS
	 * @model name="extends"
	 * @generated
	 * @ordered
	 */
	public static final int EXTENDS_VALUE = 2;

	/**
	 * The '<em><b>Replaces</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Replaces provides a way for Variability Elements to replace a base Variability Element without directly changing any of its existing properties.  This is in most cases used for Method Plugins that aim to replace specific Content Elements such as Roles, Task, or Activities with either a complete new variant or to change fundamental relationships of these elements (e.g. Role-Artifact responsibility).  Properties replaced are attribute values and association instances.  The effect of this is that the base Content Element is logically replaced with this new variant of the element to which all incoming associations still point as before, but which has potentially new attribute values and outgoing association properties.  This provides a very powerful mechanism to replace, for example, whole Activities in a Process with complete new realizations of the Activity.  For instance, replacing an Activity doing use case-based design with an activity doing agile code-centric development doing the same work using a different development technique utilizing different Roles, Tasks, etc.  Another example, would be to replace an Activity that describes database design for an RDBMS with an Activity that describes database design for an OODBMS.  A Variability Element can only be replaced by one other element at a time.  For example, if two Method Plugins replace the same element only one Method Plugin can be used for interpretation (see concept of Method Configuration for more details on how to resolve such conflicts, Section 7.1.2).  The following table provides the detailed list of interpretation rules:
	 * attribute values:	Values from the special Variability Element are replaced with values from the based-on Variability Element including unassigned values.
	 * The identifying attributes guid and name of Method Element are exempt from this rule and will not be modified.
	 * 0..1-association instances:	The one association instance of the based-on Variability Element is replaced with the association instance from the replacing special Variability Element.  If the special Variability Element does not have an association instance then resulting element will also not have an association.
	 * 0..n-association instances:	Association instances of the special Variability Element replace all association instances of the based-on Variability Element.
	 * 
	 * 
	 * <!-- end-model-doc -->
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
	private static final VariabilityType[] VALUES_ARRAY = new VariabilityType[] {
			NA, CONTRIBUTES, EXTENDS, REPLACES, LOCAL_CONTRIBUTION,
			LOCAL_REPLACEMENT, EXTENDS_REPLACES, };

	/**
	 * A public read-only list of all the '<em><b>Variability Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<VariabilityType> VALUES = Collections
			.unmodifiableList(Arrays.asList(VALUES_ARRAY));

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
		case NA_VALUE:
			return NA;
		case CONTRIBUTES_VALUE:
			return CONTRIBUTES;
		case EXTENDS_VALUE:
			return EXTENDS;
		case REPLACES_VALUE:
			return REPLACES;
		case LOCAL_CONTRIBUTION_VALUE:
			return LOCAL_CONTRIBUTION;
		case LOCAL_REPLACEMENT_VALUE:
			return LOCAL_REPLACEMENT;
		case EXTENDS_REPLACES_VALUE:
			return EXTENDS_REPLACES;
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
