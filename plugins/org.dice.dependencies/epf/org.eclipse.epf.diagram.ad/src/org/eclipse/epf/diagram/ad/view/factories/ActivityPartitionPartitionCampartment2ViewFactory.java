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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;

import org.eclipse.epf.diagram.ad.edit.parts.ActivityEditPart;

import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionPartitionCampartment2EditPart;
import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;

import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.ListCompartmentViewFactory;

import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;

import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;

import org.eclipse.swt.graphics.RGB;

/**
 * @generated
 */
public class ActivityPartitionPartitionCampartment2ViewFactory extends
		ListCompartmentViewFactory {

	/**
	 * @generated 
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createSortingStyle());
		styles.add(NotationFactory.eINSTANCE.createFilteringStyle());
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
		styles.add(NotationFactory.eINSTANCE.createDescriptionStyle());
		styles.add(NotationFactory.eINSTANCE.createFillStyle());
		styles.add(NotationFactory.eINSTANCE.createLineStyle());
		return styles;
	}

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = UMLVisualIDRegistry
					.getType(ActivityPartitionPartitionCampartment2EditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		setupCompartmentTitle(view);
		setupCompartmentCollapsed(view);
		if (!ActivityEditPart.MODEL_ID.equals(UMLVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", ActivityEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
	}

	/**
	 * @generated
	 */
	protected LayoutConstraint createLayoutConstraint() {
		return NotationFactory.eINSTANCE.createBounds();
	}

	/**
	 * @generated
	 */
	protected void initializeFromPreferences(View view) {
		super.initializeFromPreferences(view);

		IPreferenceStore store = (IPreferenceStore) getPreferencesHint()
				.getPreferenceStore();

		// fill color
		RGB fillRGB = PreferenceConverter.getColor(store,
				IPreferenceConstants.PREF_FILL_COLOR);
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE
				.getFillStyle_FillColor(), FigureUtilities
				.RGBToInteger(fillRGB));
	}

	/**
	 * @generated
	 */
	protected void setupCompartmentTitle(View view) {
		TitleStyle titleStyle = (TitleStyle) view
				.getStyle(NotationPackage.eINSTANCE.getTitleStyle());
		if (titleStyle != null) {
			titleStyle.setShowTitle(true);
		}
	}

	/**
	 * @generated
	 */
	protected void setupCompartmentCollapsed(View view) {
		DrawerStyle drawerStyle = (DrawerStyle) view
				.getStyle(NotationPackage.eINSTANCE.getDrawerStyle());
		if (drawerStyle != null) {
			drawerStyle.setCollapsed(false);
		}
	}

}
