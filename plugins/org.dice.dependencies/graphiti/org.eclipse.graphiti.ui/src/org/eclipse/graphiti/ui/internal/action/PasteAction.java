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
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.PasteContext;
import org.eclipse.graphiti.internal.command.FeatureCommandWithContext;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class PasteAction extends AbstractPreDefinedAction {
	private static final String TEXT = Messages.PasteAction_0_xfld;

	private static final String TOOL_TIP = TEXT;

	public static final String ACTION_ID = ActionFactory.PASTE.getId(); // "predefined

	public PasteAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part, configurationProvider);
		setId(ACTION_ID);
		setText(TEXT);
		setToolTipText(TOOL_TIP);
	}

	public boolean isAvailable() {
		PictogramElement[] pes = getSelectedPictogramElements();
		IPasteContext context = new PasteContext(pes);
		IPasteFeature feature = getFeatureProvider().getPasteFeature(context);
		if (feature == null) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean calculateEnabled() {
		PictogramElement[] pes = getSelectedPictogramElements();
		IPasteContext context = new PasteContext(pes);
		IFeatureProvider featureProvider = getFeatureProvider();
		if (featureProvider == null) {
			return false;
		}
		IPasteFeature feature = featureProvider.getPasteFeature(context);
		if (feature == null || !feature.canPaste(context)) {
			return false;
		}

		return true;
	}

	@Override
	public void run() {
		PictogramElement[] pes = getSelectedPictogramElements();
		IPasteContext context = new PasteContext(pes);
		final IFeatureProvider featureProvider = getFeatureProvider();
		IPasteFeature feature = featureProvider.getPasteFeature(context);
		if (feature != null) {
			final FeatureCommandWithContext command = new GenericFeatureCommandWithContext(feature, context);
			executeOnCommandStack(command);
		}
	}
}
