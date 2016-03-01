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
package org.eclipse.graphiti.platform.ga;

import java.util.ArrayList;

/**
 * The Class VisualState, a simple implementation of {@link IVisualState}.
 */
public class VisualState implements IVisualState {

	private ArrayList<IVisualStateChangeListener> changeListeners = new ArrayList<IVisualStateChangeListener>();

	private int actionTargetFeedback = ACTION_TARGET_FEEDBACK_OFF;

	private int selectionFeedback = SELECTION_FEEDBACK_OFF;

	private int hoverFeedback = HOVER_OFF;

	public void addChangeListener(IVisualStateChangeListener listener) {
		this.changeListeners.add(listener);
	}

	public void removeChangeListener(IVisualStateChangeListener listener) {
		this.changeListeners.remove(listener);
	}

	/**
	 * Propagates the visual state change to registered listeners.
	 * 
	 * @param e
	 *            the {@link VisualStateChangedEvent } event
	 */
	protected void fireVisualStateChanged(VisualStateChangedEvent e) {
		for (IVisualStateChangeListener listener : this.changeListeners) {
			listener.visualStateChanged(e);
		}
	}

	public int getActionTargetFeedback() {
		return this.actionTargetFeedback;
	}

	public void setActionTargetFeedback(int feedback) {
		int oldActionTargetFeedback = this.actionTargetFeedback;
		this.actionTargetFeedback = feedback;
		if (oldActionTargetFeedback != this.actionTargetFeedback) {
			fireVisualStateChanged(new VisualStateChangedEvent(Type.ACTION_TARGET_FEEDBACK, oldActionTargetFeedback, feedback));
		}
	}

	public int getSelectionFeedback() {
		return this.selectionFeedback;
	}

	public void setSelectionFeedback(int feedback) {
		int oldSelectionFeedback = this.selectionFeedback;
		this.selectionFeedback = feedback;
		if (oldSelectionFeedback != this.selectionFeedback) {
			fireVisualStateChanged(new VisualStateChangedEvent(Type.SELECTION, oldSelectionFeedback, feedback));
		}
	}

	public void setHoverFeedback(int feedback) {
		int oldHoverFeedback = this.hoverFeedback;
		this.hoverFeedback = feedback;
		if (oldHoverFeedback != this.hoverFeedback) {
			fireVisualStateChanged(new VisualStateChangedEvent(Type.HOVER, oldHoverFeedback, feedback));
		}

	}

	public int getHoverFeedback() {
		return this.hoverFeedback;
	}
}
