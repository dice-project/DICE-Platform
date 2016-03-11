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
package org.eclipse.epf.common;


public interface IMessageCallback {

	public void displayWarning(AbstractActivator plugin, String title, String msg, String reason);
	public void displayWarning(AbstractActivator plugin, String msg, String reason, Throwable ex);
	public void displayWarning(AbstractActivator plugin, String msg, String reason, String details, Throwable ex);
	public void displayWarning(AbstractActivator plugin, String title, String msg, String reason, String details, Throwable ex);
	
	public void displayError(AbstractActivator plugin, String title, String msg);
	public void displayError(AbstractActivator plugin, String title, String msg, Throwable ex);
	public void displayError(AbstractActivator plugin, String title, String msg, String reason, String details, Throwable ex);
	

}
