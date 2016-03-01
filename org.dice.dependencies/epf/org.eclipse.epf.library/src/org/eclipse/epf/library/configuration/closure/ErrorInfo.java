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
package org.eclipse.epf.library.configuration.closure;


/**
 * An ErrorInfo object holds the error message info caused by an element. The
 * ErrorInfo object is owned by the owner element which has this error
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ErrorInfo {
	
	public static final int NONE = 0;

	public static final int ERROR = 1;

	public static final int WARNING = 2;

	public static final int INFO = 4;

	public static final int CHILD_ERROR = 8;

	public static final int CHILD_WARNING = 16;

	/**
	 * this defines the relationship between the owner element and the cause element. 
	 * The owner element references to the the cause element
	 */
	public static final int REFERENCE_TO = 16;

	/**
	 * this defines the relationship between the owner element and the cause element. 
	 * The owner element is referenced by the the cause element
	 */
	public static final int REFERENCED_BY = 32;

	// error category
	protected String category;
	
	protected int errorLevel;

	protected Object ownerElement;

	protected Object causeElement;

	protected String errorMessage;

	protected int relation = 0;
	
	private String messageId;

	//protected ConfigurationClosure closure = null;
	
	/**
	 * constructor
	 * 
	 * @param errorLevel int the error level
	 * @param message String the error message
	 * @param ownerElement Object, the element that owns this error
	 * @param causeElement Object, the element that caused this error
	 * @param relation int the relathioship between the owner element and the cause element.
	 */
	public ErrorInfo(int errorLevel, String message, Object ownerElement,
			Object causeElement, int relation, String messageId) {
		//this.closure = closure;
		this.ownerElement = ownerElement;
		this.causeElement = causeElement;
		this.errorLevel = errorLevel;
		this.errorMessage = message;
		this.relation = relation;
		this.messageId = messageId;
	}

	/**
	 * 
	 * @return int
	 */
	public int getRelation() {
		return relation;
	}

	/**
	 * 
	 * @return Object
	 */
	public Object getOwnerElement() {
		return ownerElement;
	}

	/**
	 * 
	 * @return Object
	 */
	public Object getCauseElement() {
		return causeElement;
	}

	/**
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @return int
	 */
	public int getErrorLevel() {
		return errorLevel;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isError() {
		return (errorLevel & ERROR) > 0;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isWarning() {
		return (errorLevel & WARNING) > 0;
	}

	/**
	 * get the error category
	 * 
	 * @return String
	 */
	public String getCategory() {
		return category;
	}

	public String getMessageId() {
		return messageId;
	}
	
}
