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

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.diagram.MethodElementDiagram;
import org.eclipse.epf.library.layout.diagram.RoleDiagramPublisher;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * The element layout for a Role.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @author Weiping Lu
 * @since 1.0
 */
public class RoleLayout extends AbstractElementLayout {

	protected Map referenceMap = new HashMap();

	public RoleLayout() {
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
			List performs = ConfigurationHelper.calc0nFeatureValue(
					super.element, AssociationHelper.Role_Primary_Tasks,
					layoutManager.getElementRealizer());
			referenceMap.put("performs", performs); //$NON-NLS-1$

			// NO, this is wrong! should calculate the realized feature value!
			// moved to acceptFeatureValue to get the realized value and save to
			// the map
			// if (super.element instanceof RoleImpl)
			// {
			// referenceMap.put("responsibleFor",
			// ((RoleImpl)super.element).getResponsibleFor());
			// }

			List additionallyPerforms = ConfigurationHelper.calc0nFeatureValue(
					super.element, AssociationHelper.Role_Secondary_Tasks,
					layoutManager.getElementRealizer());
			// referenceMap.put("additionallyPerforms", additionallyPerforms);
			// //$NON-NLS-1$
			addReferences(AssociationHelper.Role_Secondary_Tasks, elementXml,
					"additionallyPerforms", additionallyPerforms); //$NON-NLS-1$
			RoleDiagramPublisher diagramPublisher = new RoleDiagramPublisher();
			MethodElementDiagram diagram = diagramPublisher.publish(this,
					new File(layoutManager.getPublishDir()));

			if (diagram != null)
				elementXml.setContent("diagram", diagram.getHTML()); //$NON-NLS-1$

			List roleSets = ConfigurationHelper.calc0nFeatureValue(
					super.element, AssociationHelper.Role_RoleSets,
					layoutManager.getElementRealizer());
			addReferences(AssociationHelper.Role_RoleSets, elementXml,
					"roleSets", roleSets); //$NON-NLS-1$
			
			// add the descriptors referencing this element
			super.processDescriptors(elementXml);
			
		}
		
		elementXml.setAttribute("ShowFullMethodContent", (layoutManager.getValidator().showExtraInfoForDescriptors()) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		return elementXml;
	}
	
	public List getPerforms() {
		List list = (List) referenceMap.get("performs"); //$NON-NLS-1$
		Collections.sort(list, Comparators.PRESENTATION_NAME_COMPARATOR);
		return list;
	}

	public List getAdditionallyPerforms() {
		List list = (List) referenceMap.get("additionallyPerforms"); //$NON-NLS-1$
		Collections.sort(list, Comparators.PRESENTATION_NAME_COMPARATOR);
		return list;		
	}

	public List getResponsibleFor() {
		List list =  (List) referenceMap.get("responsibleFor"); //$NON-NLS-1$
		Collections.sort(list, Comparators.PRESENTATION_NAME_COMPARATOR);
		return list;		
	}

	public List getModifies() {
		List list =  (List) referenceMap.get("modifies"); //$NON-NLS-1$
		Collections.sort(list, Comparators.PRESENTATION_NAME_COMPARATOR);
		return list;		
	}

	/**
	 * some layout need to have the feature values for further processing. So
	 * this method will be called when a feature is calculated in this abstract
	 * class
	 * 
	 * @param name
	 * @param value
	 */
	public void notifyFeatureValue(String name, Object value) {
		referenceMap.put(name, value);
	}

	/**
	 * some layout need to have the feature values for further processing. So
	 * this method will be called when a feature is calculated in this abstract
	 * class
	 * 
	 * @param name
	 * @param value
	 */
	protected boolean acceptFeatureValue(EStructuralFeature feature,
			Object value) {

		// save the feature value for diagram generation
		if (feature == UmaPackage.eINSTANCE.getRole_ResponsibleFor()) {
			referenceMap.put("responsibleFor", value); //$NON-NLS-1$
		}

		return super.acceptFeatureValue(feature, value);
	}

	protected boolean acceptFeatureValue(OppositeFeature feature, Object value) {
		return super.acceptFeatureValue(feature, value);
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

		if (feature == UmaPackage.eINSTANCE.getRole_ResponsibleFor() ||
			feature == UmaPackage.eINSTANCE.getRole_Modifies()) {
			OppositeFeature oFulfillingFeature = AssociationHelper.FulFills_FullFillableElements;
			List items = wpChildLayout.calc0nFeatureValue(childElememt, oFulfillingFeature, realizer);
			items = getTagQualifiedList(realizer.getConfiguration(), items);
			wpChildLayout.addReferences(oFulfillingFeature, childXmlElement, oFulfillingFeature
					.getName(), items);
		}
	}

}
