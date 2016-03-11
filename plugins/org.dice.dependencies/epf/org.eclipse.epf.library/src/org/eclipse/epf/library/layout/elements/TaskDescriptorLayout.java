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
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * The layout for a TaskDescriptor.
 * 
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class TaskDescriptorLayout extends DescriptorLayout {
	public static final Collection<EStructuralFeature> extraFeaturesFromTask = Arrays.asList(new EStructuralFeature[] {
			UmaPackage.eINSTANCE.getTask_ToolMentors()
	});
	
	public TaskDescriptorLayout() {
		super();
	}

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}

	public XmlElement getXmlElement(boolean includeReferences) {
		return super.getXmlElement(includeReferences);
	}
	
	/**
	 * @see org.eclipse.epf.library.layout.elements.AbstractElementLayout#loadReferences(XmlElement, boolean)
	 */
	public void loadReferences(XmlElement elementXml, boolean includeReferences) {
		List features = LibraryUtil.getStructuralFeatures(element);
//		List properties = element.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features
						.get(i);
				if (feature == UmaPackage.eINSTANCE
						.getTaskDescriptor_SelectedSteps()) {
					// need to handle the step contents by fixing the links
					processSteps(elementXml, includeReferences);

				} else if (feature == UmaPackage.eINSTANCE
						.getWorkBreakdownElement_LinkToPredecessor()) {
					super.loadWorkOrder(elementXml);
				} else if (feature.getEType() instanceof EClass) {
					loadFeature(feature, elementXml, includeReferences);
				}
			}
		}
		
		elementXml.setAttribute("ShowFullMethodContent", (layoutManager.getValidator().showExtraInfoForDescriptors()) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		if ((super.elementLayout != null ) && layoutManager.getValidator().showExtraInfoForDescriptors() ) {
			// also load the linked element referenced information
			for (Iterator<EStructuralFeature> iterator = getExtraFeaturesFromContentElement().iterator(); iterator.hasNext();) {
				EStructuralFeature feature = iterator.next();
				if (! isSynReferece(feature)) {
					super.elementLayout.loadFeature(feature, elementXml, false);
				}
			}
		}
	}

	private void processSteps(XmlElement elementXml, boolean includeReferences) {
		
		// 196094 - Task descriptor page should show the realized content of the linked element
		
		EStructuralFeature feature = UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps();
		String feature_name = feature.getName();

		List items;
		
		// per Peter, this is not the right behavior
		// descriptors should always show the selected steps of the original element
		// it should not show the realized steps
		// so rollback to the old behavior
		// Jinhua Xi 08/28/07
		/*
		if ( layoutManager.getValidator().showExtraInfoForDescriptors()) {
			if (linkedElement == null) {
				items = null;
			} else {
				EStructuralFeature f = UmaPackage.eINSTANCE.getTask_Steps();
				items = ConfigurationHelper.calc0nFeatureValue(linkedElement, f,
						getLayoutMgr().getElementRealizer());
			}

		} else 
		*/
		{
			items = ConfigurationHelper.calc0nFeatureValue(element, feature,
					getLayoutMgr().getElementRealizer());
		}
		
		XmlElement stepXml = elementXml
				.newChild("referenceList").setAttribute("name", feature_name); //$NON-NLS-1$ //$NON-NLS-2$

		if (items != null && items.size() > 0) {
			for (Iterator it = items.iterator(); it.hasNext();) {
				Object e = it.next();
				if (e instanceof MethodElement) {
					MethodElement me = (MethodElement) e;
					e = ConfigurationHelper.getCalculatedElement(me,
							layoutManager.getConfiguration());
					if (e != null) {
						IElementLayout l = layoutManager.getLayout(me, true);
						if (l != null) {
							l.setContentTarget(element);
							stepXml.addChild(l
									.getXmlElement(ConfigurationHelper
											.isDescriptionElement(me) ? true
											: includeReferences));
						}
					}
				}
			}
		}
	}

	@Override
	protected Collection<EStructuralFeature> getExtraFeaturesFromContentElement() {
		ArrayList<EStructuralFeature> features = new ArrayList<EStructuralFeature>(extraFeaturesFromTask);
		features.addAll(super.getExtraFeaturesFromContentElement());
		return features;
	}

	protected boolean isSuppressed(Suppression sup, EStructuralFeature feature, Object element) {
		
		// make sure all cases are covered
		if ( element instanceof RoleDescriptor || element instanceof WorkProductDescriptor ) {
			String path = super.makePath(super.elementProcessPath, (MethodElement)element);
			String[] paths = super.getPathArray(path);
			ComposedAdapterFactory adapterFactory = layoutManager.getCBSAdapterFactory();
			Object wrapper = sup.getObjectByPath(paths, adapterFactory);
			if ( wrapper == null ) {
				wrapper = element;
			}
			
			return sup.isSuppressed(wrapper, feature);
	
		} else {
			return super.isSuppressed(sup, feature, element);
		}
	}
	
}
