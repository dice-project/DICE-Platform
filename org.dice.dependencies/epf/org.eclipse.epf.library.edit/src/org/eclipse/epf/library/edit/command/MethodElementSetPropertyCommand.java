/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.MethodElementPropertyMgr;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.UmaPackage;

public class MethodElementSetPropertyCommand extends AbstractCommand implements
		IResourceAwareCommand {
	
	protected MethodElement element;
	protected String key;
	protected String value;

	protected String oldValue;
	protected Collection<Resource> modifiedResources;
	
	public MethodElementSetPropertyCommand(MethodElement element, String key, String value) {
		this.element = element;
		this.key = key;
		this.value = value;
	}
	
	public MethodElementSetPropertyCommand(MethodElement element, String key) {
		this.element = element;
		this.key = key;
	}
	
	protected boolean prepare() {
		// check if this operation will modify the element in opposite feature
		// value
		//
		return UserInteractionHelper.checkModifyOpposite(element, UmaPackage.Literals.METHOD_ELEMENT__METHOD_ELEMENT_PROPERTY,
				element);
	}

	public Collection<Resource> getModifiedResources() {
		if (element != null && element.eResource() != null
				&& modifiedResources == null) {
			modifiedResources = new HashSet<Resource>();
			modifiedResources.add(element.eResource());
		}
		
		if (modifiedResources == null) {
			return Collections.emptyList();
		}
		return modifiedResources;
	}

	public void execute() {
		redo();
	}

	public void redo() {
		MethodElementProperty oldProperty = MethodElementPropertyMgr.getInstance().getProperty(element, key);
		if (oldProperty != null) {
			oldValue = oldProperty.getValue();
		} else {
			oldValue = null;
		}
		MethodElementPropertyMgr.getInstance().setProperty(element, key, value);

	}
	
	@Override
	public void undo() {
		if (oldValue != null) {
			MethodElementPropertyHelper.setProperty(element, key, oldValue);
		} else {
			MethodElementPropertyHelper.removeProperty(element, key);
		}
	}

	@Override
	public Collection<?> getAffectedObjects() {
		return Collections.singletonList(element);
	}
	
}
