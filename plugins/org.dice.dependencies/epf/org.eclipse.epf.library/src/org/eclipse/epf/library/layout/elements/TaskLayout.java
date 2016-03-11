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
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * The element layout for a Task.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public class TaskLayout extends AbstractElementLayout {

	public TaskLayout() {
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
			Task task = (Task) super.element;
			List performingRoles = task.getPerformedBy();
			for (java.util.Iterator itor = performingRoles.iterator(); itor.hasNext();) {
				Role performingRole = (Role) itor.next();
				if (performingRole != null) {
					MethodElement role = ConfigurationHelper.getCalculatedElement(
							(MethodElement) performingRole, layoutManager
									.getConfiguration());
					if (role != null) {
						String roleName = ((Role) role).getPresentationName();
						if (roleName == null || roleName.length() == 0) {
							roleName = role.getName();
						}
						elementXml.setAttribute("performingRoleName", roleName); //$NON-NLS-1$
						addReference(UmaPackage.eINSTANCE.getTask_PerformedBy(), elementXml, "performingRole", role); //$NON-NLS-1$
					}
				}
			}
			

			// calculate the categories opposite feature
			// multiplicity change for opposite features
			List disciplines = ConfigurationHelper.calc0nFeatureValue(
					super.element, AssociationHelper.Task_Disciplines,
					layoutManager.getElementRealizer());
			addReferences(AssociationHelper.Task_Disciplines, elementXml, "disciplines", disciplines); //$NON-NLS-1$

			List usercategories = ConfigurationHelper.calc0nFeatureValue(
					super.element,
					AssociationHelper.DescribableElement_CustomCategories,
					layoutManager.getElementRealizer());
			processChild(AssociationHelper.DescribableElement_CustomCategories, 
					elementXml
							.newChild("referenceList").setAttribute("name", "customCategories"), usercategories, false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			// add the descriptors referencing this element
			super.processDescriptors(elementXml);

		}
		elementXml.setAttribute("ShowFullMethodContent", (layoutManager.getValidator().showExtraInfoForDescriptors()) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		return elementXml;
	}
	
	protected void processGrandChild(Object feature,
			MethodElement childElememt, IElementLayout childLayout,
			XmlElement childXmlElement) {
		if (!(childLayout instanceof WorkProductLayout)
				|| childXmlElement == null
				|| !(childElememt instanceof WorkProduct)) {
			return;
		}
		if (! ((WorkProduct)childElememt).getIsAbstract()) {
			return;
		}
		
		WorkProductLayout wpChildLayout = (WorkProductLayout) childLayout;
		ElementRealizer realizer = wpChildLayout.layoutManager
				.getElementRealizer();

		if (feature == UmaPackage.eINSTANCE.getTask_MandatoryInput() ||
			feature == UmaPackage.eINSTANCE.getTask_OptionalInput() ||
			feature == UmaPackage.eINSTANCE.getTask_Output()) {
			OppositeFeature oFulfillingFeature = AssociationHelper.FulFills_FullFillableElements;
			List items = wpChildLayout.calc0nFeatureValue(childElememt, oFulfillingFeature, realizer);
			items = getTagQualifiedList(realizer.getConfiguration(), items);
			wpChildLayout.addReferences(oFulfillingFeature, childXmlElement, oFulfillingFeature
					.getName(), items);
		}
	}
	
}
