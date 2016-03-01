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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.SectionList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * This command is used to reorder a section within a section list.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class MoveInSectionListCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private ContentElement contentElement;

	private SectionList sectionList;

	private List<? extends Section> elementsList;

	private ContentElement usedContentElement = null;

	private Collection modifiedResources;

	private static int UP = 1;

	private static int Down = 0;

	private int direction = -1;

	private boolean moved;

	/**
	 * @deprecated
	 */
	public MoveInSectionListCommand() {
		super();
	}

	public MoveInSectionListCommand(ContentElement contentElement,
			List<? extends Section> elementsList, SectionList sectionList, int direction) {
		this.contentElement = contentElement;
		this.sectionList = sectionList;
		this.elementsList = elementsList;
		this.direction = direction;

		modifiedResources = new HashSet();
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * @param label
	 */
	public MoveInSectionListCommand(String label) {
		super(label);
	}

	/**
	 * @param label
	 * @param description
	 */
	public MoveInSectionListCommand(String label, String description) {
		super(label, description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		String orderingGuide = contentElement.getOrderingGuide();
		if (orderingGuide != null && orderingGuide.length() > 0) {
			modifiedResources.add(contentElement.eResource());
		}
		if (contentElement.getPresentation() != null) {
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
		MethodPlugin taskPlugin = UmaUtil.getMethodPlugin(contentElement);
		MethodPlugin elementPlugin = UmaUtil
				.getMethodPlugin((MethodElement) elementsList.get(0));

		if (taskPlugin != elementPlugin
				&& Misc.isBaseOf(taskPlugin, elementPlugin, new HashMap<String, Boolean>())) {
			for (Iterator iter = TngUtil.getContributors(contentElement); iter
					.hasNext();) {
				VariabilityElement ve = (VariabilityElement) iter.next();
				if (ve instanceof ContentElement
						&& UmaUtil.getMethodPlugin(ve) == elementPlugin) {
					usedContentElement = (ContentElement) ve;
					break;
				}
			}
		}
		if (usedContentElement == null) {
			usedContentElement = contentElement;
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		if (usedContentElement == null)
			return;
		int size = elementsList.size();
		for (Section object : elementsList) {
			int index = sectionList.indexOf(object);
			if (direction == UP) {
				if (index > 0)
					sectionList.move(index - 1, object);
			} else if (direction == Down) {
				if (index < sectionList.size())
					sectionList.move(index + size, object);
			}
			moved = true;
		}
		sectionList.apply();

	}

	public void undo() {
		if (moved) {
			int size = elementsList.size();
			for (Section object : elementsList) {
				int index = sectionList.indexOf(object);
				if (direction == UP) {
					if (index < sectionList.size())
						sectionList.move(index + size, object);
				} else if (direction == Down) {
					if (index > 0)
						sectionList.move(index - 1, object);
				}
				moved = true;
			}
			sectionList.apply();
			moved = false;
		}
	}

	public Collection getAffectedObjects() {
		return Collections.singletonList(usedContentElement);
	}

}
