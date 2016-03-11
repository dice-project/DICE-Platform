/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext;

import org.eclipse.swt.widgets.Listener;

/**
 * Listens to a rich text editing event.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextListener {

	private int eventType;

	private Listener listener;

	/**
	 * Creates a new instance.
	 * 
	 * @param eventType
	 *            the event type
	 * @param listener
	 *            the event listener
	 */
	public RichTextListener(int eventType, Listener listener) {
		this.eventType = eventType;
		this.listener = listener;
	}

	/**
	 * Returns the event type.
	 * 
	 * @return the event type
	 */
	public int getEventType() {
		return eventType;
	}

	/**
	 * Returns the event listener.
	 */
	public Listener getListener() {
		return listener;
	}

}
