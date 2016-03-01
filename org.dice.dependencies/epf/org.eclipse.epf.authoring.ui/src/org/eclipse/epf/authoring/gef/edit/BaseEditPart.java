//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.gef.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.gef.edit.policies.LabelSelectionEditPolicy;
import org.eclipse.epf.authoring.gef.figures.SelectableLabel;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

/**
 * Provides infrastructure for deletion, direct-editing, property source and
 * model-listening.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class BaseEditPart extends AbstractGraphicalEditPart {

	protected DirectEditManager manager;

	protected Adapter modelListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			handlePropertyChanged(msg);
		}
	};

	/**
	 * Constructor 
	 * @param obj {@link EObject}
	 */
	public BaseEditPart(EObject obj) {
		super();
		setModel(obj);
	}

	public void activate() {
		super.activate();
		((EObject) getModel()).eAdapters().add(modelListener);
	}

	protected abstract DirectEditPolicy createDirectEditPolicy();

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
				new LabelSelectionEditPolicy());
	}

	protected IFigure createFigure() {
		return new SelectableLabel();
	}

	/**
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	public void deactivate() {
		((EObject) getModel()).eAdapters().remove(modelListener);
		super.deactivate();
	}

	IFigure getDirectEditFigure() {
		return getFigure();
	}

	String getDirectEditText() {
		IFigure fig = getDirectEditFigure();
		if (fig instanceof Label) {
			return ((Label) fig).getText();
		} else if (fig instanceof TextFlow) {
			return ((TextFlow) fig).getText();
		}
		return ""; //$NON-NLS-1$
	}

	protected abstract void handlePropertyChanged(Notification msg);

	protected DirectEditManager getDirectEditManager() {
		if (manager == null) {
			manager = createDirectEditManager();
		}
		return manager;
	}

	protected void performDirectEdit() {
		getDirectEditManager();

		if (manager != null) {
			manager.show();
		}
	}

	protected DirectEditManager createDirectEditManager() {
		return new LabelDirectEditManager(this, TextCellEditor.class,
				new LabelCellEditorLocator(getDirectEditFigure()));
	}

}