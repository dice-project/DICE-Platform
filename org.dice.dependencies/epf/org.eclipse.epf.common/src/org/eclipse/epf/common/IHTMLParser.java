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

import java.io.File;
import java.util.Properties;

public interface IHTMLParser {

	public String getText();
	
	public String getSummary();
	
	public Properties getMetaTags();
	
	public void parse(File file) throws Exception;
}
