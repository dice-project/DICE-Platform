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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * layout class for descriptor contents
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class DescriptorDescriptionLayout extends AbstractElementLayout {

	MethodElement descriptor;
	MethodElement linkedElement;
	AbstractElementLayout linkedDescriptionLayout = null;

	/**
	 * layout for descriptors
	 * @param descriptor
	 */
	public DescriptorDescriptionLayout(MethodElement descriptor) {
		this.descriptor = descriptor;
	}
	
	/**
	 * initialize the layout
	 */
	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
		
		super.targetElement = descriptor;
		super.ownerElement = descriptor;
		
		if (descriptor instanceof TaskDescriptor) {
			linkedElement = ((TaskDescriptor) descriptor).getTask();
		} else if (descriptor instanceof RoleDescriptor) {
			linkedElement = ((RoleDescriptor) descriptor).getRole();
		} else if (descriptor instanceof WorkProductDescriptor) {
			linkedElement = ((WorkProductDescriptor) descriptor).getWorkProduct();
		}
		
		// get the realized one
		// 196094 - Task descriptor page should show the realized content of the linked element
		linkedElement = ConfigurationHelper.getCalculatedElement(linkedElement, layoutManager.getElementRealizer());

		if (linkedElement != null) {
			linkedDescriptionLayout = new GeneralLayout();
			linkedDescriptionLayout.init(layoutManager, ((DescribableElement)linkedElement).getPresentation());
			linkedDescriptionLayout.setContentTarget(descriptor);
			linkedDescriptionLayout.setElementOwner(linkedElement);		
		}

	}

	public void loadAttributes(XmlElement elementXml) {

		Map featureValueMap = new HashMap();
		List features = LibraryUtil.getStructuralFeatures(element);
//		List properties = element.getInstanceProperties();
		if (features != null) {
			// get all string type attributes
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature p = (EStructuralFeature) features.get(i);			
				if (!(p instanceof EAttribute) ) {
					continue;
				}
				//EAttribute attrib = (EAttribute)p;
				String name = p.getName();
								
				Object value;
				if (name.equals("presentationName")) //$NON-NLS-1$
				{
					// value = TngUtil.getPresentationName(element);
					value = ConfigurationHelper.getPresentationName(element,
							layoutManager.getConfiguration());
				} else {
					value = getAttributeFeatureValue(p);
				}
				
				featureValueMap.put(name, value);
			}
		}
		
		String refinedDescName = UmaPackage.eINSTANCE
			.getDescriptorDescription_RefinedDescription().getName();
		String mainDescName = UmaPackage.eINSTANCE
		.getContentDescription_MainDescription().getName();
		
		// now, get the feature value from the link element's decription
		features = null;
		if ( ( linkedDescriptionLayout!=null ) && (linkedDescriptionLayout.getElement() != null) ) {
//			properties = linkedDescriptionLayout.getElement().getInstanceProperties();
			features = LibraryUtil.getStructuralFeatures(linkedDescriptionLayout.getElement());
		}
		
		if (features != null) {
			// get all string type attributes
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature p = (EStructuralFeature) features.get(i);			
				if (!(p instanceof EAttribute) ) {
					continue;
				}
				//EAttribute attrib = (EAttribute)p;
				String name = p.getName();
				if ( name.equals(mainDescName) ) {
					name = refinedDescName;
				}
				
				Object value = featureValueMap.get(name);
				if ( value == null || value.toString().length() == 0 ) {
					value = linkedDescriptionLayout.getAttributeFeatureValue(p);
					featureValueMap.put(name, value);
				}
			}
		}
		
		// now set the values
		for (Iterator it = featureValueMap.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry)it.next();
			String name = (String)entry.getKey();
			Object value = entry.getValue();
			elementXml.newChild("attribute")	//$NON-NLS-1$
				.setAttribute("name", name)		//$NON-NLS-1$
				.setValue((value == null) ? "" : value.toString());  //$NON-NLS-1$
		}

	}
}
