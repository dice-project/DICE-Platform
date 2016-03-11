/*******************************************************************************
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
 *
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IReason;

/**
 * The Class Reason.
 */
public class Reason implements IReason {

	private static final String FALSE_REASON_TEXT = ""; //$NON-NLS-1$

	private static final String TRUE_REASON_TEXT = ""; //$NON-NLS-1$

	/**
	 * Creates the false reason.
	 * 
	 * @return the i reason
	 */
	public static IReason createFalseReason() {
		return createFalseReason(FALSE_REASON_TEXT);
	}

	/**
	 * Creates the false reason.
	 * 
	 * @param string
	 *            the string
	 * @return the i reason
	 */
	public static IReason createFalseReason(String string) {
		return new Reason(false, string);
	}

	/**
	 * Creates the true reason.
	 * 
	 * @return the i reason
	 */
	public static IReason createTrueReason() {
		return createTrueReason(TRUE_REASON_TEXT);
	}

	/**
	 * Creates the true reason.
	 * 
	 * @param string
	 *            the string
	 * @return the i reason
	 */
	public static IReason createTrueReason(String string) {
		return new Reason(true, string);
	}

	private boolean bool;

	private String text;

	/**
	 * Creates a new {@link Reason}.
	 * 
	 * @param b
	 *            the bool
	 */
	public Reason(boolean b) {
		super();
		setBool(b);
	}

	/**
	 * Creates a new {@link Reason}.
	 * 
	 * @param b
	 *            the bool
	 * @param reasonText
	 *            the reason text
	 */
	public Reason(boolean b, String reasonText) {
		this(b);
		setText(reasonText);
	}

	public String getText() {
		return this.text;
	}

	/**
	 * Sets the bool.
	 * 
	 * @param bool
	 *            the new bool
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

	/**
	 * Sets the text.
	 * 
	 * @param reasonText
	 *            the new text
	 */
	public void setText(String reasonText) {
		this.text = reasonText;
	}

	public boolean toBoolean() {
		return this.bool;
	}

	@Override
	public String toString() {
		return super.toString() + " bool: " + this.bool + "; text: " + this.text; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
