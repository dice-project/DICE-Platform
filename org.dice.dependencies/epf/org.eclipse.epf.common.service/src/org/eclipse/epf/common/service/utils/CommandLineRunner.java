//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.common.service.utils;

import java.io.File;

/**
 * Base implementation for ICommandLineRunner
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class CommandLineRunner implements ICommandLineRunner {
	protected static boolean localDebug = true;
	
	private File inputFile;
	
	public boolean execute(String[] args) {
		if (localDebug) {
			debugShowArgs(args);
		}
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-batch")) { //$NON-NLS-1$
				if (i + 1 < args.length) {
					String path = args[i + 1];
					inputFile = new File(path);
					if (!inputFile.exists()) {
						String msg = "Cannot fine the file: " + path; //$NON-NLS-1$
						System.out.println("Error> " + msg); //$NON-NLS-1$
						return false;
					}
					break;
				}
			}
		}
						
		return true;
	}

	protected void debugShowArgs(String[] args) {
		System.out.println("LD> " + getClass().getName() + ".execute args: "); //$NON-NLS-1$
		for (int i = 0; i < args.length; i++) {
			System.out.println("LD> args[" + i + "]: " + args[i]); //$NON-NLS-1$//$NON-NLS-2$
		}
	}
	
	protected File getInputFile() {
		return inputFile;
	}
	
}
