/*******************************************************************************
 * Copyright (c) 2008 IBM, TietoEnator, corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Brian Schlosser - initial implementation
 *  Roman Smirak  - update for EPFC 1.2 and 1.5
 *******************************************************************************/ 
package org.eclipse.epf.publishing.cmdline;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;

public class TextProgressMonitor
        extends NullProgressMonitor {

    private boolean needNewline;

    public void beginTask(String name, int totalWork) {
        System.out.println(NLS.bind(Messages.startingTask, name));
    }

    public void done() {
        println();
    }

    public void setTaskName(String name) {
        println();
        System.out.println(NLS.bind(Messages.startingTask, name));
    }

    public void subTask(String name) {
        println();
        System.out.println('\t' + name);
    }

    public void worked(int work) {
        needNewline = true;
        System.out.print('.');
    }

	private void println() {
		if(needNewline) {
            System.out.println();
            needNewline = false;
        }
	}
}
