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
package org.eclipse.epf.msproject.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.epf.msproject.ExtendedAttribute;
import org.eclipse.epf.msproject.MsprojectPackage;
import org.eclipse.epf.msproject.ValueList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getFieldID <em>Field ID</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getPhoneticAlias <em>Phonetic Alias</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getRollupType <em>Rollup Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getCalculationType <em>Calculation Type</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getFormula <em>Formula</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#isRestrictValues <em>Restrict Values</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getValuelistSortOrder <em>Valuelist Sort Order</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#isAppendNewValues <em>Append New Values</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.epf.msproject.impl.ExtendedAttributeImpl#getValueList <em>Value List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendedAttributeImpl extends EObjectImpl implements ExtendedAttribute {
	/**
	 * The default value of the '{@link #getFieldID() <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldID()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldID() <em>Field ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldID()
	 * @generated
	 * @ordered
	 */
	protected String fieldID = FIELD_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldName()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldName() <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldName()
	 * @generated
	 * @ordered
	 */
	protected String fieldName = FIELD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhoneticAlias() <em>Phonetic Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhoneticAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String PHONETIC_ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhoneticAlias() <em>Phonetic Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPhoneticAlias()
	 * @generated
	 * @ordered
	 */
	protected String phoneticAlias = PHONETIC_ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRollupType() <em>Rollup Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRollupType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ROLLUP_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRollupType() <em>Rollup Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRollupType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger rollupType = ROLLUP_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCalculationType() <em>Calculation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalculationType()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger CALCULATION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCalculationType() <em>Calculation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalculationType()
	 * @generated
	 * @ordered
	 */
	protected BigInteger calculationType = CALCULATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFormula() <em>Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected static final String FORMULA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFormula() <em>Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected String formula = FORMULA_EDEFAULT;

	/**
	 * The default value of the '{@link #isRestrictValues() <em>Restrict Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRestrictValues()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESTRICT_VALUES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRestrictValues() <em>Restrict Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRestrictValues()
	 * @generated
	 * @ordered
	 */
	protected boolean restrictValues = RESTRICT_VALUES_EDEFAULT;

	/**
	 * This is true if the Restrict Values attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean restrictValuesESet = false;

	/**
	 * The default value of the '{@link #getValuelistSortOrder() <em>Valuelist Sort Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValuelistSortOrder()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger VALUELIST_SORT_ORDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValuelistSortOrder() <em>Valuelist Sort Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValuelistSortOrder()
	 * @generated
	 * @ordered
	 */
	protected BigInteger valuelistSortOrder = VALUELIST_SORT_ORDER_EDEFAULT;

	/**
	 * The default value of the '{@link #isAppendNewValues() <em>Append New Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAppendNewValues()
	 * @generated
	 * @ordered
	 */
	protected static final boolean APPEND_NEW_VALUES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAppendNewValues() <em>Append New Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAppendNewValues()
	 * @generated
	 * @ordered
	 */
	protected boolean appendNewValues = APPEND_NEW_VALUES_EDEFAULT;

	/**
	 * This is true if the Append New Values attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean appendNewValuesESet = false;

	/**
	 * The default value of the '{@link #getDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected String default_ = DEFAULT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValueList() <em>Value List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueList()
	 * @generated
	 * @ordered
	 */
	protected ValueList valueList = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MsprojectPackage.eINSTANCE.getExtendedAttribute();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldID() {
		return fieldID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldID(String newFieldID) {
		String oldFieldID = fieldID;
		fieldID = newFieldID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_ID, oldFieldID, fieldID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldName(String newFieldName) {
		String oldFieldName = fieldName;
		fieldName = newFieldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_NAME, oldFieldName, fieldName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlias(String newAlias) {
		String oldAlias = alias;
		alias = newAlias;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__ALIAS, oldAlias, alias));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPhoneticAlias() {
		return phoneticAlias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhoneticAlias(String newPhoneticAlias) {
		String oldPhoneticAlias = phoneticAlias;
		phoneticAlias = newPhoneticAlias;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__PHONETIC_ALIAS, oldPhoneticAlias, phoneticAlias));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getRollupType() {
		return rollupType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRollupType(BigInteger newRollupType) {
		BigInteger oldRollupType = rollupType;
		rollupType = newRollupType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__ROLLUP_TYPE, oldRollupType, rollupType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCalculationType() {
		return calculationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalculationType(BigInteger newCalculationType) {
		BigInteger oldCalculationType = calculationType;
		calculationType = newCalculationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__CALCULATION_TYPE, oldCalculationType, calculationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormula(String newFormula) {
		String oldFormula = formula;
		formula = newFormula;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__FORMULA, oldFormula, formula));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRestrictValues() {
		return restrictValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRestrictValues(boolean newRestrictValues) {
		boolean oldRestrictValues = restrictValues;
		restrictValues = newRestrictValues;
		boolean oldRestrictValuesESet = restrictValuesESet;
		restrictValuesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__RESTRICT_VALUES, oldRestrictValues, restrictValues, !oldRestrictValuesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRestrictValues() {
		boolean oldRestrictValues = restrictValues;
		boolean oldRestrictValuesESet = restrictValuesESet;
		restrictValues = RESTRICT_VALUES_EDEFAULT;
		restrictValuesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.EXTENDED_ATTRIBUTE__RESTRICT_VALUES, oldRestrictValues, RESTRICT_VALUES_EDEFAULT, oldRestrictValuesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRestrictValues() {
		return restrictValuesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getValuelistSortOrder() {
		return valuelistSortOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValuelistSortOrder(BigInteger newValuelistSortOrder) {
		BigInteger oldValuelistSortOrder = valuelistSortOrder;
		valuelistSortOrder = newValuelistSortOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__VALUELIST_SORT_ORDER, oldValuelistSortOrder, valuelistSortOrder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAppendNewValues() {
		return appendNewValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppendNewValues(boolean newAppendNewValues) {
		boolean oldAppendNewValues = appendNewValues;
		appendNewValues = newAppendNewValues;
		boolean oldAppendNewValuesESet = appendNewValuesESet;
		appendNewValuesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES, oldAppendNewValues, appendNewValues, !oldAppendNewValuesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAppendNewValues() {
		boolean oldAppendNewValues = appendNewValues;
		boolean oldAppendNewValuesESet = appendNewValuesESet;
		appendNewValues = APPEND_NEW_VALUES_EDEFAULT;
		appendNewValuesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, MsprojectPackage.EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES, oldAppendNewValues, APPEND_NEW_VALUES_EDEFAULT, oldAppendNewValuesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAppendNewValues() {
		return appendNewValuesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(String newDefault) {
		String oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__DEFAULT, oldDefault, default_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueList getValueList() {
		return valueList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueList(ValueList newValueList, NotificationChain msgs) {
		ValueList oldValueList = valueList;
		valueList = newValueList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST, oldValueList, newValueList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueList(ValueList newValueList) {
		if (newValueList != valueList) {
			NotificationChain msgs = null;
			if (valueList != null)
				msgs = ((InternalEObject)valueList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST, null, msgs);
			if (newValueList != null)
				msgs = ((InternalEObject)newValueList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST, null, msgs);
			msgs = basicSetValueList(newValueList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST, newValueList, newValueList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST:
					return basicSetValueList(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_ID:
				return getFieldID();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_NAME:
				return getFieldName();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ALIAS:
				return getAlias();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__PHONETIC_ALIAS:
				return getPhoneticAlias();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ROLLUP_TYPE:
				return getRollupType();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__CALCULATION_TYPE:
				return getCalculationType();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FORMULA:
				return getFormula();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__RESTRICT_VALUES:
				return isRestrictValues() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUELIST_SORT_ORDER:
				return getValuelistSortOrder();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES:
				return isAppendNewValues() ? Boolean.TRUE : Boolean.FALSE;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__DEFAULT:
				return getDefault();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST:
				return getValueList();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_ID:
				setFieldID((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_NAME:
				setFieldName((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ALIAS:
				setAlias((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__PHONETIC_ALIAS:
				setPhoneticAlias((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ROLLUP_TYPE:
				setRollupType((BigInteger)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__CALCULATION_TYPE:
				setCalculationType((BigInteger)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FORMULA:
				setFormula((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__RESTRICT_VALUES:
				setRestrictValues(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUELIST_SORT_ORDER:
				setValuelistSortOrder((BigInteger)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES:
				setAppendNewValues(((Boolean)newValue).booleanValue());
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__DEFAULT:
				setDefault((String)newValue);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST:
				setValueList((ValueList)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_ID:
				setFieldID(FIELD_ID_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_NAME:
				setFieldName(FIELD_NAME_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ALIAS:
				setAlias(ALIAS_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__PHONETIC_ALIAS:
				setPhoneticAlias(PHONETIC_ALIAS_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ROLLUP_TYPE:
				setRollupType(ROLLUP_TYPE_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__CALCULATION_TYPE:
				setCalculationType(CALCULATION_TYPE_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FORMULA:
				setFormula(FORMULA_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__RESTRICT_VALUES:
				unsetRestrictValues();
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUELIST_SORT_ORDER:
				setValuelistSortOrder(VALUELIST_SORT_ORDER_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES:
				unsetAppendNewValues();
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__DEFAULT:
				setDefault(DEFAULT_EDEFAULT);
				return;
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST:
				setValueList((ValueList)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_ID:
				return FIELD_ID_EDEFAULT == null ? fieldID != null : !FIELD_ID_EDEFAULT.equals(fieldID);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FIELD_NAME:
				return FIELD_NAME_EDEFAULT == null ? fieldName != null : !FIELD_NAME_EDEFAULT.equals(fieldName);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ALIAS:
				return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__PHONETIC_ALIAS:
				return PHONETIC_ALIAS_EDEFAULT == null ? phoneticAlias != null : !PHONETIC_ALIAS_EDEFAULT.equals(phoneticAlias);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__ROLLUP_TYPE:
				return ROLLUP_TYPE_EDEFAULT == null ? rollupType != null : !ROLLUP_TYPE_EDEFAULT.equals(rollupType);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__CALCULATION_TYPE:
				return CALCULATION_TYPE_EDEFAULT == null ? calculationType != null : !CALCULATION_TYPE_EDEFAULT.equals(calculationType);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__FORMULA:
				return FORMULA_EDEFAULT == null ? formula != null : !FORMULA_EDEFAULT.equals(formula);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__RESTRICT_VALUES:
				return isSetRestrictValues();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUELIST_SORT_ORDER:
				return VALUELIST_SORT_ORDER_EDEFAULT == null ? valuelistSortOrder != null : !VALUELIST_SORT_ORDER_EDEFAULT.equals(valuelistSortOrder);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__APPEND_NEW_VALUES:
				return isSetAppendNewValues();
			case MsprojectPackage.EXTENDED_ATTRIBUTE__DEFAULT:
				return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
			case MsprojectPackage.EXTENDED_ATTRIBUTE__VALUE_LIST:
				return valueList != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fieldID: ");
		result.append(fieldID);
		result.append(", fieldName: ");
		result.append(fieldName);
		result.append(", alias: ");
		result.append(alias);
		result.append(", phoneticAlias: ");
		result.append(phoneticAlias);
		result.append(", rollupType: ");
		result.append(rollupType);
		result.append(", calculationType: ");
		result.append(calculationType);
		result.append(", formula: ");
		result.append(formula);
		result.append(", restrictValues: ");
		if (restrictValuesESet) result.append(restrictValues); else result.append("<unset>");
		result.append(", valuelistSortOrder: ");
		result.append(valuelistSortOrder);
		result.append(", appendNewValues: ");
		if (appendNewValuesESet) result.append(appendNewValues); else result.append("<unset>");
		result.append(", default: ");
		result.append(default_);
		result.append(')');
		return result.toString();
	}

} //ExtendedAttributeImpl
