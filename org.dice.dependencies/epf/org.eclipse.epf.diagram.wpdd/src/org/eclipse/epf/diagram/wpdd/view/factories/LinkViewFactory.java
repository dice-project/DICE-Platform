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
package org.eclipse.epf.diagram.wpdd.view.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;

import org.eclipse.epf.diagram.wpdd.edit.parts.LinkEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.LinkNameEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductDependencyDiagramEditPart;

import org.eclipse.epf.diagram.wpdd.part.DiagramVisualIDRegistry;

import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;

import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;

import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class LinkViewFactory extends ConnectionViewFactory {

	private static final Map options = new HashMap();
	static {
		options.put(Transaction.OPTION_UNPROTECTED, Boolean.TRUE);
		options.put(Transaction.OPTION_NO_NOTIFICATIONS, Boolean.TRUE);
		options.put(Transaction.OPTION_NO_TRIGGERS, Boolean.TRUE);
	}

	/**
	 * @generated 
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createRoutingStyle());
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
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
			semanticHint = DiagramVisualIDRegistry
					.getType(LinkEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		if (!WorkProductDependencyDiagramEditPart.MODEL_ID
				.equals(DiagramVisualIDRegistry.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", WorkProductDependencyDiagramEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		getViewService().createNode(semanticAdapter, view,
				DiagramVisualIDRegistry.getType(LinkNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.core.view.factories.ViewFactory#createView(org.eclipse.core.runtime.IAdaptable, org.eclipse.gmf.runtime.notation.View, java.lang.String, int, boolean, java.lang.String)
	 * 
	 * Override createView since there is a bug in 
	 * org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory.java
	 */
	public View createView(final IAdaptable semanticAdapter,
			final View containerView, final String semanticHint,
			final int index, final boolean persisted,
			final PreferencesHint preferencesHint) {

		setPreferencesHint(preferencesHint);

		final Edge edge = NotationFactory.eINSTANCE.createEdge();
		List styles = createStyles(edge);
		if (styles.size() > 0) {
			edge.getStyles().addAll(styles);
		}

		Bendpoints bendPoints = createBendpoints();
		if (bendPoints != null) {
			edge.setBendpoints(bendPoints);
		}

		EObject semanticEl = semanticAdapter == null ? null
				: (EObject) semanticAdapter.getAdapter(EObject.class);
		if (semanticEl == null)
			// enforce a set to NULL
			edge.setElement(null);
		else if (requiresElement(semanticAdapter, containerView)) {
			edge.setElement(semanticEl);
		}

		edge.setType(semanticHint);

		// decorate view assumes the view had been inserted already, so 
		// we had to call insert child before calling decorate view
		ViewUtil.insertChildView(containerView, edge, index, persisted);

		TransactionalEditingDomain domain = getEditingDomain(semanticEl,
				containerView);

		if (domain != null) {
			if (isUnProtectedSilentTransactionInProgress(domain)) {
				// decorate view had to run as a silent operation other wise
				// it will generate too many events
				decorateView(containerView, edge, semanticAdapter,
						semanticHint, index, true);

			} else {
				AbstractEMFOperation operation = new AbstractEMFOperation(
						domain, StringStatics.BLANK, options) {

					protected IStatus doExecute(IProgressMonitor monitor,
							IAdaptable info) throws ExecutionException {
						decorateView(containerView, edge, semanticAdapter,
								semanticHint, index, true);

						return Status.OK_STATUS;
					}
				};
				try {
					operation.execute(new NullProgressMonitor(), null);
				} catch (ExecutionException e) {
					Trace.catching(DiagramUIPlugin.getInstance(),
							DiagramUIDebugOptions.EXCEPTIONS_CATCHING,
							getClass(), "createView", e); //$NON-NLS-1$
					Log.warning(DiagramUIPlugin.getInstance(),
							DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING,
							"createView", e); //$NON-NLS-1$
				}
			}
		}

		return edge;
	}
}
