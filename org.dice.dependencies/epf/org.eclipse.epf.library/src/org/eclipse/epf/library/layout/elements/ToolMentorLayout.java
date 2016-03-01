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
package org.eclipse.epf.library.layout.elements;

import java.util.List;

import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * The element layout for a Tool Mentor.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class ToolMentorLayout extends AbstractElementLayout {

	public ToolMentorLayout() {
		super();
	}

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}

	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		XmlElement elementXml = super.getXmlElement(includeReferences);

		if (includeReferences) {
			List contentElements = ConfigurationHelper.calc0nFeatureValue(super.element,
					AssociationHelper.ToolMentor_Tasks, layoutManager
							.getElementRealizer());
			addReferences(AssociationHelper.ToolMentor_Tasks, elementXml, "contentElements", contentElements); //$NON-NLS-1$

			List<MethodElement> tools = ConfigurationHelper.calc0nFeatureValue(
					super.element, AssociationHelper.ToolMentor_Tools,
					layoutManager.getElementRealizer());
			int sz = tools == null ? 0 : tools.size();
			for (int i = 0; i < sz; i++) {
				MethodElement tool = tools.get(i);
				addReference(AssociationHelper.ToolMentor_Tools, elementXml, "tool", tool); //$NON-NLS-1$
			}
		}

		return elementXml;
	}

}
