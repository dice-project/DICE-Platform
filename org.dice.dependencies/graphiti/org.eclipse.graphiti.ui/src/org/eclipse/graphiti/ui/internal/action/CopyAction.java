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

import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.impl.CopyContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CopyAction extends AbstractPreDefinedAction {

	private static final String TOOL_TIP = Messages.CopyAction_0_xmsg;

	private static final String TEXT = Messages.CopyAction_1_xfld;

	public static final String ACTION_ID = ActionFactory.COPY.getId(); // "predefined

	public CopyAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part, configurationProvider);
		setId(ACTION_ID);
		setText(TEXT);
		setToolTipText(TOOL_TIP);
	}

	public boolean isAvailable() {
		PictogramElement[] pes = getSelectedPictogramElements();
		ICopyContext context = new CopyContext(pes);
		ICopyFeature feature = getFeatureProvider().getCopyFeature(context);
		if (feature == null) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean calculateEnabled() {
		PictogramElement[] pes = getSelectedPictogramElements();
		ICopyContext context = new CopyContext(pes);
		IFeatureProvider featureProvider = getFeatureProvider();
		if (featureProvider == null) {
			return false;
		}
		ICopyFeature feature = featureProvider.getCopyFeature(context);
		if (feature == null || !feature.canCopy(context)) {
			return false;
		}

		return true;
	}

	@Override
	public void run() {
		PictogramElement[] pes = getSelectedPictogramElements();
		ICopyContext context = new CopyContext(pes);
		ICopyFeature copyFeature = getFeatureProvider().getCopyFeature(context);
		if (copyFeature != null) {
			copyFeature.copy(context);
		}
	}
}
