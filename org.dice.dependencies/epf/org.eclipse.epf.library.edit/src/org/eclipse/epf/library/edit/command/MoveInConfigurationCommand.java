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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * This command is used to move a view in a method configuration
 * @author Xiang Dong Hu
 * @since 1.2
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=173827
 */
public class MoveInConfigurationCommand extends AbstractCommand implements
		IResourceAwareCommand {
	
	private MethodConfiguration config;

	private EStructuralFeature feature;

	private List elementsList;
	
	private Collection modifiedResources;

	private static int UP = 1;

	private static int Down = 0;

	private int direction = -1;

	private boolean moved;
	
	public MoveInConfigurationCommand(MethodConfiguration config, List elementsList,
			EStructuralFeature feature, int direction) {
		this.config = config;
		this.feature = feature;
		this.elementsList = elementsList;
		this.direction = direction;
	}
	
	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		return modifiedResources;
	}

	public void execute() {
		modifiedResources = new HashSet();
		redo();

	}

	public void redo() {
		if (config == null)
			return;
		for (Iterator it = elementsList.iterator(); it.hasNext();) {
			Object object = it.next();
			if (feature.isMany()) {
				EList list = (EList) config.eGet(feature);
				int index = list.indexOf(object);
				if (direction == UP) {
					if (index > 0)
						list.move(index - 1, object);
				} else if (direction == Down) {
					if (index < list.size())
						list.move(index + elementsList.size(), object);
				}
				moved = true;
			} else {
				config.eSet(feature, null);
			}
		}
		if (config.eResource() != null) {
			modifiedResources.add(config.eResource());
		}

	}
	
	public void undo() {
		if (moved) {
			for (Iterator it = elementsList.iterator(); it.hasNext();) {
				Object object = it.next();
				if (feature.isMany()) {
					EList list = (EList) config.eGet(feature);
					int index = list.indexOf(object);
					if (direction == UP) {
						if (index < list.size())
							list.move(index + elementsList.size(), object);
					} else if (direction == Down) {
						if (index > 0)
							list.move(index - 1, object);
					}
					moved = true;
				} else {
					config.eSet(feature, object);
				}
			}
			moved = false;
		}
	}
	
	public Collection getAffectedObjects() {
		return Collections.singletonList(config);
	}

}
