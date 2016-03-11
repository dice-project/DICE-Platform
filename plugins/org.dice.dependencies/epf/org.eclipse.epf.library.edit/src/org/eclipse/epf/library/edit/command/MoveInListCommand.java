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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.uma.MethodElement;

/**
 * This command is used to reorder a method element within a category.
 * 
 * 1.5:  Refactored to work for any MethodElement's reference lists.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class MoveInListCommand extends AbstractCommand implements
		IResourceAwareCommand {
	final public static int UP = 1;
	final public static int DOWN = 0;
	
	private MethodElement element;

	private EStructuralFeature feature;

	private List<Object> elementsList;
	
	private ContentElementOrderList elementOrderList;

	private Collection<Resource> modifiedResources;

	private int direction = -1;

	private boolean moved;

	public MoveInListCommand(MethodElement element, List<Object> elementsList,
			ContentElementOrderList orderList,
			EStructuralFeature feature,
			int direction) {
		this.element = element;
		this.feature = feature;
		this.elementsList = elementsList;		
		this.elementOrderList = orderList ;
		this.direction = direction;
		modifiedResources = new HashSet<Resource>();
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * @param label
	 */
	public MoveInListCommand(String label) {
		super(label);
	}

	/**
	 * @param label
	 * @param description
	 */
	public MoveInListCommand(String label, String description) {
		super(label, description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection<Resource> getModifiedResources() {
		modifiedResources.add(element.eResource());
		return modifiedResources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		if (element == null)
			return;
		for (Iterator<Object> it = elementsList.iterator(); it.hasNext();) {
			Object object = it.next();
			if (feature.isMany()) {
				int index = elementOrderList.indexOf(object);
				if (direction == UP) {
					if (index > 0)
						elementOrderList.move(index - 1, object);
				} else if (direction == DOWN) {
					if (index < elementOrderList.size())
						elementOrderList.move(index + elementsList.size(), object);
				}
				moved = true;
			} else {
				element.eSet(feature, null);
			}
		}
		elementOrderList.apply();
	}

	public void undo() {
		if (moved) {
			for (Iterator<Object> it = elementsList.iterator(); it.hasNext();) {
				Object object = it.next();
				if (feature.isMany()) {
					int index = elementOrderList.indexOf(object);
					if (direction == UP) {
					if (index < elementOrderList.size())
						elementOrderList.move(index + elementsList.size(), object);
					} else if (direction == DOWN) {
						if (index > 0)
							elementOrderList.move(index - 1, object);
					}
					moved = true;
				} else {
					element.eSet(feature, object);
				}
			}
			elementOrderList.apply();
			moved = false;
		}
	}

	public Collection<MethodElement> getAffectedObjects() {
		return Collections.singletonList(element);
	}

}
