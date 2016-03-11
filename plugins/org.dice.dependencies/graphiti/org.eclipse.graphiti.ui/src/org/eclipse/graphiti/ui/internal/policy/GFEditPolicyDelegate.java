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
package org.eclipse.graphiti.ui.internal.policy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.graphiti.platform.ga.IVisualState;
import org.eclipse.graphiti.platform.ga.IVisualStateHolder;
import org.eclipse.graphiti.ui.internal.config.AbstractConfigurationProviderHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFEditPolicyDelegate extends AbstractConfigurationProviderHolder {

	public GFEditPolicyDelegate(IConfigurationProvider configurationProvider) {
		super(configurationProvider);
	}

	boolean isFigureOwnSelectionHandlingActive(IFigure figure) {
		if (figure instanceof IVisualStateHolder) {
			return true;
		} else {
			return false;
		}
	}

	boolean showPrimarySelection(IFigure figure) {
		if (figure instanceof IVisualStateHolder) {
			IVisualStateHolder visualStateHolder = (IVisualStateHolder) figure;
			visualStateHolder.getVisualState().setSelectionFeedback(IVisualState.SELECTION_PRIMARY);
			return true;
		} else {
			return false;
		}
	}

	boolean showSelection(IFigure figure) {
		if (figure instanceof IVisualStateHolder) {
			IVisualStateHolder visualStateHolder = (IVisualStateHolder) figure;
			visualStateHolder.getVisualState().setSelectionFeedback(IVisualState.SELECTION_SECONDARY);
			return true;
		} else {
			return false;
		}
	}

	boolean showOnHover(IFigure figure) {
		if (figure instanceof IVisualStateHolder) {
			IVisualStateHolder visualStateHolder = (IVisualStateHolder) figure;
			visualStateHolder.getVisualState().setSelectionFeedback(IVisualState.SELECTION_SECONDARY);
			return true;
		} else {
			return false;
		}
	}

	boolean hideSelection(IFigure figure) {
		if (figure instanceof IVisualStateHolder) {
			IVisualStateHolder visualStateHolder = (IVisualStateHolder) figure;
			visualStateHolder.getVisualState().setSelectionFeedback(IVisualState.SELECTION_FEEDBACK_OFF);
			return true;
		} else {
			return false;
		}
	}
}
