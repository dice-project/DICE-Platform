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
import org.eclipse.epf.library.edit.util.SectionList;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Section;

/**
 * This command is used to add a new section to the section list.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AddToSectionListCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private ContentElement contentElement;

	private SectionList sectionList;

	private Section section;

	private Collection modifiedResources;

	private boolean added;

	public AddToSectionListCommand(ContentElement contentElement,
			Section section, SectionList sectionList) {
		this.contentElement = contentElement;
		this.sectionList = sectionList;
		this.section = section;

		modifiedResources = new HashSet();
	}

	protected boolean prepare() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		if (contentElement.getPresentation().eResource() != null) {
			modifiedResources.add(contentElement.getPresentation().eResource());
		}else{
			// This check is done to bypass addition of section to method element
			// for first time, if plugin.xmi file checked-out with version control. 
			if(contentElement.eResource() != null){
				modifiedResources.add(contentElement.eResource());
			}
		}
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

		if (contentElement == null)
			return;
		sectionList.add(section);
		added = true;
		sectionList.apply();

	}

	public void undo() {
		if (added) {
			sectionList.remove(section);
			sectionList.apply();
			added = false;
		}
	}

	public Collection getAffectedObjects() {
		return Collections.singletonList(contentElement);
	}

}
