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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * The layout for a RoleDescriptor.
 * 
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RoleDescriptorLayout extends DescriptorLayout {
	
	public RoleDescriptorLayout() {
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

		// use the default
		// if you need to generate diagram, you may need to keep the feature
		// values.
		// just do something similar to the RoleLayout
		// NOTE: for role-tasks relationship, the feature is
		// RoleDescriptor#getPerformsAsOwner
		// it's a bidirectional feature, don't need opposite feature
		return elementXml;
	}
	
	public void loadReferences(XmlElement elementXml, boolean includeReferences) {

		super.loadReferences(elementXml, includeReferences);
		
		elementXml.setAttribute("ShowFullMethodContent", (layoutManager.getValidator().showExtraInfoForDescriptors()) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		if ( (super.elementLayout != null ) && layoutManager.getValidator().showExtraInfoForDescriptors() ) {
			// also load the linked element referenced information
			for (Iterator<EStructuralFeature> iter = getExtraFeaturesFromContentElement().iterator(); iter.hasNext();) {
				EStructuralFeature feature = (EStructuralFeature) iter.next();
				if (! isSynReferece(feature)) {
					super.elementLayout.loadFeature(feature, elementXml, false);
				}
			}
		}
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
		if ( feature == UmaPackage.eINSTANCE.getRoleDescriptor_Modifies() ) {
			List v = calcModifiedWorkProductDescriptors();
			addReferences(feature, elementXml, feature.getName(), v);
		} else {
			getFeatureValue(feature,  elementXml, includeReferences);
		}
	}
	
	public List calcModifiedWorkProductDescriptors() {
		List v = new ArrayList();
		ElementRealizer realizer = getLayoutMgr().getElementRealizer();
		OppositeFeature ofeature = AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors;
		List v2 = ConfigurationHelper.calc0nFeatureValue(element, ofeature, realizer);
		if ( v2.size() > 0 ) {
			EStructuralFeature feature = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
			for (Iterator it = v2.iterator(); it.hasNext(); ) {
				MethodElement e = (MethodElement)it.next();
				
				String path = super.makePath(getSuperActivityPath(), e);
				TaskDescriptorLayout tl = (TaskDescriptorLayout)layoutManager.createLayout(e, owningProcess, path);
				//RoleDescriptor r = (RoleDescriptor)tl.getFeatureValue(UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(), null, false);
				List rList = (List)tl.getFeatureValue(UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(), null, false);
				//if ( r == this.element ) {
				if (rList != null && rList.contains(this.element) ) {
					List value = (List)tl.getFeatureValue(feature, null, false);
					if ( value != null && value.size() > 0 ) {
						for ( Iterator itr = value.iterator(); itr.hasNext(); ) {
							Object o = itr.next();
							if ( !v.contains(o) ) {
								v.add(o);
							}
						}
					}
				}
			}
		}
		
		return v;
	}
		
	protected boolean acceptFeatureValue(OppositeFeature feature, Object value) {
		
		if ( !super.acceptFeatureValue(feature, value) ) {
			return false;
		}
		
		if ( !isTaskOppositeFeature(feature) || !(value instanceof List) ) {
			return true;
		}
		
		List items = (List)value;
		int i = 0;
		while (i < items.size() ) {
			MethodElement e = (MethodElement)items.get(i);
			String path = super.makePath(getSuperActivityPath(), e);
			TaskDescriptorLayout tl = (TaskDescriptorLayout)layoutManager.createLayout(e, owningProcess, path);
			Object o = null;
			if ( feature == AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors ) {
				o = tl.getFeatureValue(UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(), null, false);			
			} else if ( feature == AssociationHelper.RoleDescriptor_AdditionalTaskDescriptors ) {
				o = tl.getFeatureValue(UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy(), null, false);						
			} else if ( feature == AssociationHelper.RoleDescriptor_AssistsIn_TaskDescriptors ) {
			 o = tl.getFeatureValue(UmaPackage.eINSTANCE.getTaskDescriptor_AssistedBy(), null, false);						
			}
			
			if ( (o == this.element) || (o instanceof List) && ((List)o).contains(this.element) ) {
				i++;
			} else {
				items.remove(i);
			}
		}


		
		return true;
	}
	
	
}
