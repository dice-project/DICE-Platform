/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.view.factories;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;

import org.eclipse.gmf.runtime.notation.NotationFactory;

/**
 * @generated
 */
public class ActivityPartitionName2ViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
	}

	/**
	 * @generated
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createDescriptionStyle());
		styles.add(NotationFactory.eINSTANCE.createFillStyle());
		styles.add(NotationFactory.eINSTANCE.createLineStyle());
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
		return styles;
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory#createView(org.eclipse.core.runtime.IAdaptable, org.eclipse.gmf.runtime.notation.View, java.lang.String, int, boolean, org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint)
	 * @modified
	 */
	public View createView(IAdaptable semanticAdapter, View containerView,
			String semanticHint, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node view = (Node) super.createView(semanticAdapter, containerView,
				semanticHint, index, persisted, preferencesHint);
		Location location = (Location) view.getLayoutConstraint();
		IMapMode mapMode = MeasurementUnitHelper.getMapMode(containerView
				.getDiagram().getMeasurementUnit());
		location.setX(mapMode.DPtoLP(0));
		location.setY(mapMode.DPtoLP(5));
		return view;
	}
}
