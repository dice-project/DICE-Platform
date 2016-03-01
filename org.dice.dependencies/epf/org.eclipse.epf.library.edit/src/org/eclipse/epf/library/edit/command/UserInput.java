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
package org.eclipse.epf.library.edit.command;

import java.util.List;

import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.epf.library.edit.validation.IValidator;

/**
 * Class that keeps the information about the input request and reference to the
 * input user specified or selected
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UserInput {
	public static final int TEXT = 0;

	public static final int SELECTION = 1;

	private String label;

	private int type;

	private boolean multiple;

	private List choices;

	private Object input;

	private IItemLabelProvider labelProvider;

	private Object context;

	private IValidator validator;

	public UserInput(String label, int type, boolean multiple, List choices,
			IItemLabelProvider labelProvider, Object context) {
		this(label, type, multiple, choices, labelProvider, null, context);
	}

	/**
	 * @param label
	 * @param type
	 * @param multiple
	 * @param choices
	 * @param labelProvider
	 *            the label provider to represent the element in the
	 *            <code>choices</code> list or <code>null</code> if
	 *            <code>choices</code> list is <code>null</code>
	 * @param validator
	 *            the validator to validate the input
	 * @param context
	 *            the context of the user input that is command-specific and can
	 *            contain addional information about the user input.
	 */
	public UserInput(String label, int type, boolean multiple, List choices,
			IItemLabelProvider labelProvider, IValidator validator, Object context) {
		super();
		this.label = label;
		this.type = type;
		this.multiple = multiple;
		this.choices = choices;
		this.labelProvider = labelProvider;
		this.validator = validator;
		this.context = context;
	}

	public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	public List getChoices() {
		return choices;
	}

	public String getLabel() {
		return label;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public int getType() {
		return type;
	}

	/**
	 * Gets the label provider
	 * 
	 * @return the label provider
	 */
	public IItemLabelProvider getLabelProvider() {
		return labelProvider;
	}
	
	/**
	 * Gets the validator
	 * 
	 * @return the validator
	 */
	public IValidator getValidator() {
		return validator;
	}

	/**
	 * Gets the context of this user input
	 * 
	 * @return the context
	 */
	public Object getContext() {
		return context;
	}
}
