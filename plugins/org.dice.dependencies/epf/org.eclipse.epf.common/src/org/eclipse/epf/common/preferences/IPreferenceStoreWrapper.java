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
package org.eclipse.epf.common.preferences;


/**
 * wrapper interface for preference store
 * 
 * @author Jinhua Xi
 * @since 1.5
 *
 */
public interface IPreferenceStoreWrapper {

	//public Object getStore();
	
	public void addPropertyChangeListener(IPropertyChangeListenerWrapper listener);
	public void firePropertyChangeEvent(String name, Object oldValue,
	            Object newValue);
    public void removePropertyChangeListener(IPropertyChangeListenerWrapper listener);

	/**
     * Returns the current value of the boolean-valued preference with the
     * given name.
     * Returns the default-default value (<code>false</code>) if there
     * is no preference with the given name, or if the current value 
     * cannot be treated as a boolean.
     *
     * @param name the name of the preference
     * @return the boolean-valued preference
     */
    public boolean getBoolean(String name);

    /**
     * Returns the default value for the boolean-valued preference
     * with the given name.
     * Returns the default-default value (<code>false</code>) if there
     * is no default preference with the given name, or if the default 
     * value cannot be treated as a boolean.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public boolean getDefaultBoolean(String name);

    /**
     * Returns the default value for the double-valued preference
     * with the given name.
     * Returns the default-default value (<code>0.0</code>) if there
     * is no default preference with the given name, or if the default 
     * value cannot be treated as a double.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public double getDefaultDouble(String name);

    /**
     * Returns the default value for the float-valued preference
     * with the given name.
     * Returns the default-default value (<code>0.0f</code>) if there
     * is no default preference with the given name, or if the default 
     * value cannot be treated as a float.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public float getDefaultFloat(String name);

    /**
     * Returns the default value for the integer-valued preference
     * with the given name.
     * Returns the default-default value (<code>0</code>) if there
     * is no default preference with the given name, or if the default 
     * value cannot be treated as an integer.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public int getDefaultInt(String name);

    /**
     * Returns the default value for the long-valued preference
     * with the given name.
     * Returns the default-default value (<code>0L</code>) if there
     * is no default preference with the given name, or if the default 
     * value cannot be treated as a long.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public long getDefaultLong(String name);

    /**
     * Returns the default value for the string-valued preference
     * with the given name.
     * Returns the default-default value (the empty string <code>""</code>) 
     * is no default preference with the given name, or if the default 
     * value cannot be treated as a string.
     *
     * @param name the name of the preference
     * @return the default value of the named preference
     */
    public String getDefaultString(String name);

    /**
     * Returns the current value of the double-valued preference with the
     * given name.
     * Returns the default-default value (<code>0.0</code>) if there
     * is no preference with the given name, or if the current value 
     * cannot be treated as a double.
     *
     * @param name the name of the preference
     * @return the double-valued preference
     */
    public double getDouble(String name);

    /**
     * Returns the current value of the float-valued preference with the
     * given name.
     * Returns the default-default value (<code>0.0f</code>) if there
     * is no preference with the given name, or if the current value 
     * cannot be treated as a float.
     *
     * @param name the name of the preference
     * @return the float-valued preference
     */
    public float getFloat(String name);

    /**
     * Returns the current value of the integer-valued preference with the
     * given name.
     * Returns the default-default value (<code>0</code>) if there
     * is no preference with the given name, or if the current value 
     * cannot be treated as an integter.
     *
     * @param name the name of the preference
     * @return the int-valued preference
     */
    public int getInt(String name);

    /**
     * Returns the current value of the long-valued preference with the
     * given name.
     * Returns the default-default value (<code>0L</code>) if there
     * is no preference with the given name, or if the current value 
     * cannot be treated as a long.
     *
     * @param name the name of the preference
     * @return the long-valued preference
     */
    public long getLong(String name);

    /**
     * Returns the current value of the string-valued preference with the
     * given name.
     * Returns the default-default value (the empty string <code>""</code>)
     * if there is no preference with the given name, or if the current value 
     * cannot be treated as a string.
     *
     * @param name the name of the preference
     * @return the string-valued preference
     */
    public String getString(String name);

    /**
     * Returns whether the current value of the preference with the given name
     * has the default value.
     *
     * @param name the name of the preference
     * @return <code>true</code> if the preference has a known default value
     * and its current value is the same, and <code>false</code> otherwise
     * (including the case where the preference is unknown to this store)
     */
    public boolean isDefault(String name);

    /**
     * Returns whether the current values in this property store
     * require saving.
     *
     * @return <code>true</code> if at least one of values of 
     *  the preferences known to this store has changed and 
     *  requires saving, and <code>false</code> otherwise.
     */
    public boolean needsSaving();

    /**
     * Sets the current value of the preference with the given name to
     * the given string value without sending a property change.
     * <p>
     * This method does not fire a property change event and 
     * should only be used for setting internal preferences 
     * that are not meant to be processed by listeners.
     * Normal clients should instead call #setValue.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void putValue(String name, String value);

    /**
     * Sets the default value for the double-valued preference with the
     * given name. 
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No property change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, double value);

    /**
     * Sets the default value for the float-valued preference with the
     * given name. 
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No property change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, float value);

    /**
     * Sets the default value for the integer-valued preference with the
     * given name. 
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No property change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, int value);

    /**
     * Sets the default value for the long-valued preference with the
     * given name. 
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No property change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, long value);

    /**
     * Sets the default value for the string-valued preference with the
     * given name. 
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No property change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param defaultObject the new default value for the preference
     */
    public void setDefault(String name, String defaultObject);

    /**
     * Sets the default value for the boolean-valued preference with the
     * given name. 
     * <p>
     * Note that the current value of the preference is affected if
     * the preference's current value was its old default value, in which
     * case it changes to the new default value. If the preference's current
     * is different from its old default value, its current value is
     * unaffected. No property change events are reported by changing default
     * values.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new default value for the preference
     */
    public void setDefault(String name, boolean value);

    /**
     * Sets the current value of the preference with the given name back
     * to its default value.
     * <p>
     * Note that the preferred way of re-initializing a preference to the
     * appropriate default value is to call <code>setToDefault</code>.
     * This is implemented by removing the named value from the store, 
     * thereby exposing the default value.
     * </p>
     *
     * @param name the name of the preference
     */
    public void setToDefault(String name);

    /**
     * Sets the current value of the double-valued preference with the
     * given name.
     * <p>
     * A property change event is reported if the current value of the 
     * preference actually changes from its previous value. In the event
     * object, the property name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, double value);

    /**
     * Sets the current value of the float-valued preference with the
     * given name.
     * <p>
     * A property change event is reported if the current value of the 
     * preference actually changes from its previous value. In the event
     * object, the property name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, float value);

    /**
     * Sets the current value of the integer-valued preference with the
     * given name.
     * <p>
     * A property change event is reported if the current value of the 
     * preference actually changes from its previous value. In the event
     * object, the property name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, int value);

    /**
     * Sets the current value of the long-valued preference with the
     * given name.
     * <p>
     * A property change event is reported if the current value of the 
     * preference actually changes from its previous value. In the event
     * object, the property name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, long value);

    /**
     * Sets the current value of the string-valued preference with the
     * given name.
     * <p>
     * A property change event is reported if the current value of the 
     * preference actually changes from its previous value. In the event
     * object, the property name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, String value);

    /**
     * Sets the current value of the boolean-valued preference with the
     * given name.
     * <p>
     * A property change event is reported if the current value of the 
     * preference actually changes from its previous value. In the event
     * object, the property name is the name of the preference, and the
     * old and new values are wrapped as objects.
     * </p>
     * <p>
     * Note that the preferred way of re-initializing a preference to its
     * default value is to call <code>setToDefault</code>.
     * </p>
     *
     * @param name the name of the preference
     * @param value the new current value of the preference
     */
    public void setValue(String name, boolean value);
    
}
