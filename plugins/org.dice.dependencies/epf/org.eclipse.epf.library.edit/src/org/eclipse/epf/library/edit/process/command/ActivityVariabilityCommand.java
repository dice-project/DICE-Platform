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
package org.eclipse.epf.library.edit.process.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;

/**
 * Command to set variability on the activity
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ActivityVariabilityCommand extends AddMethodElementCommand {

	private Activity activity;

	private VariabilityElement ve;

	private VariabilityType type;

	private List elements;

	private IConfigurator configurator;

	private Collection modifiedResources;

	public ActivityVariabilityCommand(Activity activity, VariabilityElement ve,
			VariabilityType type, IConfigurator configurator) {

		super(TngUtil.getOwningProcess(activity));

		this.activity = activity;
		this.ve = ve;
		this.type = type;
		this.configurator = configurator;

		elements = Arrays.asList(new Object[] { ve });

		this.modifiedResources = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {

		// add to default configuration if not there already
		if (!super.addToDefaultConfiguration(elements, configurator))
			return;

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		activity.setVariabilityType(type);
		activity.setVariabilityBasedOnElement(ve);

		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#undo()
	 */
	public void undo() {
		if (!elements.isEmpty()) {
			// basically remove from configuration if anything was added
			super.undo();
			activity.setVariabilityType(VariabilityType.NA);
			activity.setVariabilityBasedOnElement(null);
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}
		return modifiedResources;
	}

	public Collection getAffectedObjects() {
		return super.getAffectedObjects();
	}
}
