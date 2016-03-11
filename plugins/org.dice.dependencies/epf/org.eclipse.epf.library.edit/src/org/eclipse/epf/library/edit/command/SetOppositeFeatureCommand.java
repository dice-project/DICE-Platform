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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * This command is used to set an opposite feature.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SetOppositeFeatureCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private MethodElement element;

	private OppositeFeature oppositeFeature;

	private EObject value;

	private EObject oldValue;

	private Collection modifiedResources;

	/**
	 * Creates a new instance.
	 */
	public SetOppositeFeatureCommand(MethodElement e,
			OppositeFeature oppositeFeature, EObject value) {
		this.element = e;
		this.oppositeFeature = oppositeFeature;
		this.value = value;
	}

	public Collection getModifiedResources() {
		if (modifiedResources == null) {
			return Collections.EMPTY_LIST;
		}
		return modifiedResources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		if (value instanceof MethodElement) {
			return UserInteractionHelper.checkModifyOpposite(
					(MethodElement) value, oppositeFeature.getTargetFeature(),
					element);
		}
		return true;
	}

	public void execute() {
		oldValue = (EObject) ((MultiResourceEObject) element)
				.getOppositeFeatureValue(oppositeFeature);
		modifiedResources = new HashSet();

		redo();
	}

	public void redo() {
		if (oldValue != null && value == null) {
			if (oppositeFeature.getTargetFeature().isMany()) {
				((Collection) oldValue.eGet(oppositeFeature.getTargetFeature()))
						.remove(element);
			} else {
				oldValue.eSet(oppositeFeature.getTargetFeature(), null);
			}
			if (oldValue.eResource() != null) {
				modifiedResources.add(oldValue.eResource());
			}
		}

		if (value != null) {
			if (oppositeFeature.getTargetFeature().isMany()) {
				((Collection) value.eGet(oppositeFeature.getTargetFeature()))
						.add(element);
			} else {
				value.eSet(oppositeFeature.getTargetFeature(), element);
			}
			if (value.eResource() != null) {
				modifiedResources.add(value.eResource());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		if (value != null) {
			if (oppositeFeature.getTargetFeature().isMany()) {
				((Collection) value.eGet(oppositeFeature.getTargetFeature()))
						.remove(element);
			} else {
				value.eSet(oppositeFeature.getTargetFeature(), null);
			}
		}

		if (oldValue != null) {
			if (oppositeFeature.getTargetFeature().isMany()) {
				((Collection) oldValue.eGet(oppositeFeature.getTargetFeature()))
						.add(element);
			} else {
				oldValue.eSet(oppositeFeature.getTargetFeature(), element);
			}
		}
	}

}
