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
 * This command is used to remove a section from a section list.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RemoveFromSectionList extends AbstractCommand implements
		IResourceAwareCommand {

	private ContentElement contentElement;

	private SectionList sectionList;

	private Section section;

	private Collection modifiedResources;

	private boolean removed;

	private int removedObjectIndex = -1;

	public RemoveFromSectionList(ContentElement contentElement,
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
		removedObjectIndex = sectionList.indexOf(section);
		sectionList.remove(section);
		removed = true;
		sectionList.apply();
	}

	public void undo() {
		if (removed) {
			if (removedObjectIndex != -1) {
				sectionList.add(removedObjectIndex, section);
			} else {
				sectionList.add(section);
			}
			sectionList.apply();
			removed = false;
		}
	}

	public Collection getAffectedObjects() {
		return Collections.singletonList(contentElement);
	}

}
