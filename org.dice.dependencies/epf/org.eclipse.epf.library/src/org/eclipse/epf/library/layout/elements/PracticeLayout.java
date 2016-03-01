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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.configuration.PracticeItemProvider;
import org.eclipse.epf.library.edit.configuration.PracticeItemProvider.GroupingHelper;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;


/**
 * The element layout for a Practice
 * 
 * @author Weiping Lu
 * @since 1.5
 */
public class PracticeLayout extends AbstractElementLayout {

	public PracticeLayout() {
		super();
	}

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}
	
	@Override
	protected XmlElement getXmlElement() {
		XmlElement elementXml = super.getXmlElement();

		Practice practice = (Practice) getElement();
		PracticePropUtil propUtil = PracticePropUtil.getPracticePropUtil();
		try {
			UserDefinedTypeMeta meta = propUtil.getUtdData(practice);
			if (meta != null) {
				addReferences(udtFeauteObj, elementXml,
					"User defined type", Collections.singletonList(meta)); //$NON-NLS-1$
			}
		} catch (Exception e) {
		}
		return elementXml;
	}

	private static Object udtFeauteObj = new Object();	
	@Override
	protected void processChild(Object feature, XmlElement parent, List items,
			boolean includeReferences) {
		if (feature == udtFeauteObj) {
			if (items == null || items.isEmpty()) {
				return;
			}
			
			for (UserDefinedTypeMeta meta : (List<UserDefinedTypeMeta>) items) {
				XmlElement childXmlElement = new XmlElement("Element");//$NON-NLS-1$
				parent.addChild(childXmlElement);
				for (String name : UserDefinedTypeMeta.rteNames) {
					String value = meta.getRteNameMap().get(name);
					if (value == null) {
						value = name;
					}
					childXmlElement.setAttribute(name, value);//$NON-NLS-1$
				}
			}
			
			return;
		}
		super.processChild(feature, parent, items, includeReferences);
	}
	
	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		XmlElement elementXml = super.getXmlElement(includeReferences);

		if (includeReferences) {
			EStructuralFeature feature = UmaPackage.Literals.PRACTICE__CONTENT_REFERENCES;
			List<MethodElement> children = calc0nFeatureValue(element, null,
					feature, layoutManager.getElementRealizer());
			
			List<WorkProduct> wpSlotInputs = getInputWpSlots(children);
			
			EStructuralFeature feature1 = UmaPackage.Literals.PRACTICE__ACTIVITY_REFERENCES;
			List<MethodElement> children1 = calc0nFeatureValue(element, null,
					feature1, layoutManager.getElementRealizer());
			
//			EStructuralFeature feature2 = UmaPackage.Literals.PRACTICE__SUB_PRACTICES;
//			List<MethodElement> children2 = calc0nFeatureValue(element, null,
//					feature2, layoutManager.getElementRealizer());
			
			children.addAll(children1);
//			children.addAll(children2);
			
			List ret = new ArrayList();

			Practice practice = (Practice) getElement();
			PracticePropUtil propUtil = PracticePropUtil.getPracticePropUtil();
			boolean isUtdType = propUtil.isUdtType(practice);
			if (isUtdType) {
				Set<MethodElement> set = new HashSet<MethodElement>();
				for (EReference ref : propUtil.getUdtMeta(practice).getQualifiedReferences()) {
					List<MethodElement> qrList = ConfigurationHelper
							.calc0nFeatureValue(element, ref, layoutManager
									.getElementRealizer());
					if (qrList != null && !qrList.isEmpty()) {
						set.addAll(qrList);
					}
				}
				if (! set.isEmpty()) {
					children.removeAll(set);
				}
			}
			
			GroupingHelper groupingHelper = new GroupingHelper(this, isUtdType) {			
				protected void grouping(Object parentObject, List ret,
						Collection children, GroupingHelper groupingHelper) {					
					if (getGrouper() instanceof PracticeLayout) {
						((PracticeLayout) getGrouper()).grouping(parentObject, ret,
								children, groupingHelper);
					}
				}	
			};
			
			grouping(this, ret, children, groupingHelper);
					
			addReferences(feature,
					elementXml, "Practice guidance tree", ret); //$NON-NLS-1$
			
			if (wpSlotInputs != null) {
				addReferences(feature,
					elementXml, "Input work product slots", wpSlotInputs); //$NON-NLS-1$
			}

			if (isUtdType) {
				List<MethodElement> list = propUtil.getUdtReferencingList(practice);
				if (list != null && !list.isEmpty()) {
					list = ConfigurationHelper.getCalculatedElements(list, layoutManager.getElementRealizer());
				}
				addReferences(feature,
						elementXml, "Udt referencing list", list); //$NON-NLS-1$
			}	
		}

		return elementXml;
	}

	private List<WorkProduct> getInputWpSlots(List<MethodElement> elements) {
		Set<WorkProduct> slots = new HashSet<WorkProduct>();
		for (MethodElement elem: elements) {
			if (elem instanceof Task) {
				Task task = (Task) elem;
				for (WorkProduct wp: task.getMandatoryInput()) {
					if (wp.getIsAbstract()) {
						slots.add(wp);
					}
				}
				for (WorkProduct wp: task.getOptionalInput()) {
					if (wp.getIsAbstract()) {
						slots.add(wp);
					}
				}
			}
		}
		if (slots.isEmpty()) {
			return null;
		}

		List<WorkProduct> wpSlotInputs = new ArrayList<WorkProduct>();
		wpSlotInputs.addAll(slots);
		
		if (wpSlotInputs.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE.getPresNameComparator();
			Collections.<WorkProduct>sort(wpSlotInputs, comparator);
		}
		
		return wpSlotInputs;
	}
	
	protected boolean acceptFeatureValue(EStructuralFeature feature, Object value) {
		if (feature.isMany()) {
			//return false;
		}
		return super.acceptFeatureValue(feature, value);
	}

	private void grouping(Object parentObject, List ret, Collection children, 
			PracticeItemProvider.GroupingHelper groupingHelper) {
		Map<String, List> map = PracticeItemProvider.getSubGroupMap(children, groupingHelper);
		
		boolean toSort = true;
		if (parentObject instanceof PracticeLayout) {
			MethodElement elem = ((PracticeLayout) parentObject).element;
			if (elem != null) {
				toSort = ! CategorySortHelper.isManualCategorySort(elem);
			}
		}
		String[] keys = groupingHelper.getKeysInOrder();
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			List subgroupChildren = map.get(key);
			if (subgroupChildren == null || subgroupChildren.isEmpty()) {
				continue;
			}
			if (groupingHelper.toGroup(key, subgroupChildren)) {
				subgroupChildren = groupingHelper.nestedGrouping(parentObject, key, subgroupChildren);
				SubGroupValue subGroupValue = new SubGroupValue(key, subgroupChildren);
				ret.add(subGroupValue);
			} else {
				if (toSort) {
					PracticeItemProvider.sort(subgroupChildren);
				}
				ret.addAll(subgroupChildren);
			}
		}
	}
	
	protected void processNonMethodElementInProcessChild(
			Object nonMethodElementChild, Object feature, XmlElement parent,
			boolean includeReferences) {
		
		if (nonMethodElementChild instanceof SubGroupValue) {
			SubGroupValue subGroupValue = (SubGroupValue) nonMethodElementChild;
			String key = subGroupValue.key;
			List listValue = subGroupValue.listValue;
			addReferences(feature,
				parent, key, listValue); 
		}
	}
	
	protected boolean acceptFeatureValue(OppositeFeature feature, Object value) {
/*		if ( feature == AssociationHelper.DescribableElement_CustomCategories) {
			return true;
		}*/			
		return super.acceptFeatureValue(feature, value);
	}

	static class SubGroupValue {
		public String key;
		public List listValue;
		
		public SubGroupValue(String key, List listValue) {
			this.key = key;
			this.listValue = listValue;
		}
	}

	protected void loadQrReferences(XmlElement elementXml) {
		super.loadQrReferences(elementXml);
		
		Practice practice = (Practice) getElement();
		PracticePropUtil propUtil = PracticePropUtil.getPracticePropUtil();
		UserDefinedTypeMeta meta = propUtil.getUdtMeta(practice);
		if (meta == null || meta.getQualifiedReferences().isEmpty()) {
			return;
		}
		
		XmlElement qrWrapper = elementXml.newChild(TAG_REFERENCELIST);
		qrWrapper.setAttribute("name", "UDT reference qualifiers");  //$NON-NLS-1$//$NON-NLS-2$
		
		//List<EReference> references = getSortedReferences(meta.getQualifiedReferences(), meta);
		Set<EReference> references = meta.getQualifiedReferences();

		for (EReference ref : references) {
			List<MethodElement> qrList = ConfigurationHelper
					.calc0nFeatureValue(element, ref, layoutManager
							.getElementRealizer());
			if (qrList != null && !qrList.isEmpty()) {
				String name = meta.getReferenceQualifierName(ref.getName());
				addReferences(ref, qrWrapper, name, qrList);
			}
		}
	}
	
	private List<EReference> getSortedReferences(Set<EReference> references, final UserDefinedTypeMeta meta) {
		List<EReference> result = new ArrayList<EReference>();
		for (EReference ref : references) {
			result.add(ref);
		}
		
		Collections.sort(result, new Comparator<EReference>() {
			public int compare(EReference object1, EReference object2) {
				String name1 = meta.getReferenceQualifierName(object1.getName());
				String name2 = meta.getReferenceQualifierName(object2.getName());
				return name1.compareTo(name2);
			}			
		});
		
		return result;
	}
	
}
