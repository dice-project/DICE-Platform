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
package org.eclipse.epf.library.layout.elements;

import java.util.List;

import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * The element layout for a EstimationConsiderations.
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class EstimationConsiderationsLayout extends AbstractElementLayout {

	public EstimationConsiderationsLayout() {
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
			List tasks = ConfigurationHelper.calc0nFeatureValue(super.element,
					AssociationHelper.EstimationConsiderations_Tasks, layoutManager
							.getElementRealizer());
			addReferences(AssociationHelper.EstimationConsiderations_Tasks, elementXml, "contentElements", tasks); //$NON-NLS-1$
			
			List workProducts = ConfigurationHelper.calc0nFeatureValue(super.element,
					AssociationHelper.EstimationConsiderations_WorkProducts, layoutManager
							.getElementRealizer());
			addReferences(AssociationHelper.EstimationConsiderations_WorkProducts, elementXml, "contentElements", workProducts); //$NON-NLS-1$
		}

		return elementXml;
	}


}
