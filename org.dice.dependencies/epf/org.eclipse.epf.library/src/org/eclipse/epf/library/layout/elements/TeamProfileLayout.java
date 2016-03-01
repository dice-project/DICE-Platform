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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;


/**
 * Team Profile layout for browsing
 * 
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class TeamProfileLayout extends AbstractProcessElementLayout {

	public TeamProfileLayout() {
		super();
	}

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}

	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		return super.getXmlElement(includeReferences);
	}
	/**
	 * load the non-attribute feature
	 * 
	 * @param feature
	 * @param elementXml
	 * @param includeReferences
	 */
	public void loadFeature(EStructuralFeature feature, XmlElement elementXml,
			boolean includeReferences) {
		if (!(feature.getEType() instanceof EClass)) {
			return;
		}

		String name = feature.getName();

		if (!feature.isMany()) {
			MethodElement e = ConfigurationHelper.calc01FeatureValue(element,
					feature, layoutManager.getElementRealizer());
			// Browsing stops working when a role is set to
			// replaced another role
			// for replacer, the base will be evaluated to the replacer
			// and causing deadlock
			if (e != null && e != element) {
				boolean showDetail = (ConfigurationHelper
						.isDescriptionElement(e)
				/*
				 * || (p ==
				 * UmaPackage.eINSTANCE.getMethodUnit_CopyrightStatement())
				 */) ? true : includeReferences;

				if ( acceptFeatureValue(feature, e) ) {
					processChild(feature, 
							elementXml
									.newChild("reference").setAttribute("name", name), e, showDetail); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		} else if (feature.isMany()) {
			if (isBreakdownElement_Guidances(feature)) {
				super.loadFeature(feature, elementXml, includeReferences);
				return;
			}
			List pv = ConfigurationHelper.calc0nFeatureValue(element, feature,
					getLayoutMgr().getElementRealizer());
			List roleDescList = new ArrayList();
			if ((pv != null) && (!pv.isEmpty()))
			{
				for (Iterator itor=pv.iterator(); itor.hasNext();)
				{
					Object obj = itor.next();
					if (obj instanceof RoleDescriptor) 
					{
						RoleDescriptor roleDesc = (RoleDescriptor) obj;
						if ((roleDesc.getSuperActivities() == null)
							|| (roleDesc.getSuperActivities() == null))
							roleDescList.add(obj);
					}
				}
			}

			if ( acceptFeatureValue(feature, roleDescList) && roleDescList.size() > 0 ) {
				addReferences(feature, elementXml, name, roleDescList);
			}
		}
	}
}
