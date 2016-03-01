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
package org.eclipse.epf.common.utils;
import com.ibm.icu.util.Calendar;

/**
 * A simple timer for tracking time.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class Timer {

	private long initTime;

	private long startTime;

	private long endTime;

	public Timer() {
		start();
		initTime = startTime;
	}

	public void start() {
		startTime = Calendar.getInstance().getTimeInMillis();
	}

	public void stop() {
		endTime = Calendar.getInstance().getTimeInMillis();
	}

	public int getTime() {
		return (int) (endTime - startTime);
	}

	public int getTotalTime() {
		return (int) (endTime - initTime);
	}

}
