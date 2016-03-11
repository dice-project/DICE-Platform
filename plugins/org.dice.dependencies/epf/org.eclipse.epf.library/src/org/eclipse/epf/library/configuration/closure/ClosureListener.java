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
package org.eclipse.epf.library.configuration.closure;

import java.util.EventListener;

import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 *  Base class for listeners that listen to a ConfigurationClosure for errors
 * @author Jeff Hardy
 *
 */
public abstract class ClosureListener implements EventListener {

	// error removed
	public static final int ERROR_REMOVED = 0;
	
	// error added
	public static final int ERROR_ADDED = 1;

	// error changed
	public static final int ERROR_UPDATED = 2;

	public abstract void errorRemoved(MethodConfiguration config, IConfigurationError error);

	public abstract void errorAdded(MethodConfiguration config, IConfigurationError error);

	public abstract void errorUpdated(MethodConfiguration config, IConfigurationError error);
	
	public void fireEvent(int type, final MethodConfiguration config, final IConfigurationError error) {
		switch (type) {
			case ERROR_REMOVED:
				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						errorRemoved(config, error);
					}
				});
				break;
			case ERROR_ADDED:
				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						errorAdded(config, error);
					}
				});
				break;
			case ERROR_UPDATED:
				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						errorUpdated(config, error);
					}
				});
				break;
		}
	}
}
