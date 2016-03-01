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
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;


/**
 * Base Command for add method elements to descriptor. 
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AddMethodElementCommand extends AbstractCommand implements
		IResourceAwareCommand {

	protected List elementsToAddToDefaultConfig;

	private HashSet addedObjects;

	private Process process;

	public AddMethodElementCommand(Process process) {
		super();

		this.process = process;
	}

	/**
	 * Checks and adds elements to the default configuration
	 * 
	 * @return false if user canceled the operation
	 */
	protected boolean addToDefaultConfiguration(List elements)
	{
		return addToDefaultConfiguration(elements, null);
	}
	
	
	/**
	 * Checks and adds elements to the default configuration
	 * 
	 * @return false if user canceled the operation
	 */
	protected boolean addToDefaultConfiguration(List elements, IConfigurator configurator) {
		if (elementsToAddToDefaultConfig == null) {
			elementsToAddToDefaultConfig = new ArrayList();
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				Object element = iter.next();
				
				switch (UserInteractionHelper.checkAgainstDefaultConfiguration(
						process, element, configurator)) {
				case 0:
					iter.remove();
					break;
				case 2:
					elementsToAddToDefaultConfig.add(element);
					break;
				case -1:
					return false;
				}
			}
			if (!elementsToAddToDefaultConfig.isEmpty()) {
				addedObjects = new HashSet();
				for (Iterator iter = elementsToAddToDefaultConfig.iterator(); iter
						.hasNext();) {
					EObject element = (EObject) iter.next();
					ProcessUtil.addToDefaultConfiguration(process, element,
							addedObjects);
				}
				if (!addedObjects.isEmpty()) {
					getModifiedResources().add(
							process.getDefaultContext().eResource());
				}

			}
		}
		return true;
	}

	public void execute() {

	}

	public void redo() {

	}

	public void undo() {
		if (addedObjects != null && !addedObjects.isEmpty()) {
			MethodConfiguration defaultConfig = process.getDefaultContext();
			for (Iterator iter = addedObjects.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof MethodPlugin) {
					defaultConfig.getMethodPluginSelection().remove(element);
				} else if (element instanceof MethodPackage) {
					defaultConfig.getMethodPackageSelection().remove(element);
				}
			}
		}
	}

	public Collection getModifiedResources() {
		return Collections.EMPTY_LIST;
	}
}
