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

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class UpdateAction extends AbstractPreDefinedAction implements IContextAndFeatureProvider {

	private static final String TOOL_TIP = Messages.UpdateAction_0_xmsg;

	private static final String TEXT = Messages.UpdateAction_1_xfld;

	public static final String ACTION_ID = "predefined update action"; //$NON-NLS-1$

	public UpdateAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part, configurationProvider);
		setId(ACTION_ID);
		setText(TEXT);
		setToolTipText(TOOL_TIP);
		setLazyEnablementCalculation(true);
	}

	public boolean isAvailable() {
		PictogramElement pe[] = getSelectedPictogramElements();
		for (int i = 0; i < pe.length; i++) {
			IUpdateContext context = new UpdateContext(pe[i]);
			IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(context);
			if (updateFeature == null) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected boolean calculateEnabled() {
		PictogramElement pe[] = getSelectedPictogramElements();
		for (int i = 0; i < pe.length; i++) {
			IUpdateContext context = new UpdateContext(pe[i]);
			IFeatureProvider featureProvider = getFeatureProvider();
			if (featureProvider != null) {
				IUpdateFeature updateFeature = featureProvider.getUpdateFeature(context);
				if (updateFeature != null && updateFeature.canExecute(context)) {
					if (pe.length == 1 && pe[0] instanceof Diagram) {
						return true;
					} else if (updateFeature.updateNeeded(context).toBoolean()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Performs the delete action on the selected objects.
	 */
	@Override
	public void run() {
		genericRun(this);
	}

	public IContext createContext(PictogramElement pe) {
		return new UpdateContext(pe);
	}

	public IFeature provideFeatureForContext(IContext context) {
		return getFeatureProvider().getUpdateFeature((IUpdateContext) context);
	}
}