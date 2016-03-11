package org.eclipse.epf.library.realization.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.realization.IRealizedDescriptor;
import org.eclipse.epf.library.edit.realization.IRealizedElement;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.UmaUtil;

public class RealizedDescriptor extends RealizedBreakdownElement implements
		IRealizedDescriptor, IRealizedElement {

	private static Set<EStructuralFeature> featureSet = new HashSet<EStructuralFeature>();
	private static Map<EStructuralFeature, EStructuralFeature> contentFeatureMap = new HashMap<EStructuralFeature, EStructuralFeature>();

	static {
		UmaPackage up = UmaPackage.eINSTANCE;
		featureSet.add(up.getNamedElement_Name());
		featureSet.add(up.getMethodElement_PresentationName());
		featureSet.add(up.getMethodElement_BriefDescription());

		contentFeatureMap.put(up.getDescriptorDescription_RefinedDescription(),
				up.getContentDescription_MainDescription());
		contentFeatureMap.put(up.getContentDescription_KeyConsiderations(), up
				.getContentDescription_KeyConsiderations());		

	}
	
	public RealizedDescriptor(Descriptor descriptor) {
		super(descriptor);
	}
	
	public boolean handleFeature(EStructuralFeature feature) {
		return featureSet.contains(feature);
	}
	
	private Object getContentFeatureValue(EStructuralFeature feature) {
		if (getLinkedElement() == null) {
			return null;
		}
//	Cannot do this if the linked element has variability, e.g. an extend-replacer		
//		if (! ContentDescriptionFactory.hasPresentation(getLinkedElement())) {
//			return null;
//		}
				
		EStructuralFeature elementFeature = contentFeatureMap.get(feature);
		
		if (elementFeature != null) {	

			Object value = null;
			if (getDescriptor().getPresentation() != null) {
				try {
					value = getDescriptor().getPresentation().eGet(feature);
				} catch (Throwable e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
					return null;
				}
			}
			
			if (getLinkedElement() == null || getLinkedElement().getPresentation() == null) {
				return value;
			}
			
			DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
			if (propUtil.isValueReplaced(feature, getDescriptor())) {
				return value;
			}
						
			Descriptor greenParent = propUtil.getGreenParentDescriptor(getDescriptor());			
			Object linkedValue;
			if (greenParent == null) {
				
//	This API would not combine owner element's variability's effect				
//				linkedValue = ConfigurationHelper.calcAttributeFeatureValue(
//						getLinkedElement().getPresentation(), elementFeature, getConfig());
				linkedValue = ConfigurationHelper.calcAttributeFeatureValue(
						getLinkedElement().getPresentation(), getLinkedElement(), elementFeature, getConfig());
			} else {
				linkedValue = greenParent.getPresentation().eGet(feature);
			}

			if (linkedValue == null && value != null ||  linkedValue != null && !linkedValue.equals(value)) {
				getDescriptor().getPresentation().eSet(feature, linkedValue);
			}

			return linkedValue;
		}
		
		throw new UnsupportedOperationException();
	}
	
	public Object getFeatureValue(EStructuralFeature feature) {
		Object value = super.getFeatureValue(feature);
		if (value != null) {
			return value;
		}
		return getFeatureValue_(feature);
	}
	
	private Object getFeatureValue_(EStructuralFeature feature) {
		if (contentFeatureMap.containsKey(feature)) {
			return getContentFeatureValue(feature);
		}
		
		if (feature instanceof EAttribute) {	

			Object value = getDescriptor().eGet(feature);
			if (getLinkedElement() == null) {
				return value;
			}
			
			DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
			if (propUtil.isValueReplaced(feature, getDescriptor())) {
				return value;
			}
			
			Descriptor greenParent = propUtil.getGreenParentDescriptor(getDescriptor());
			
			Object linkedValue;
			if (greenParent == null) {
				linkedValue = calcAttributeFeatureValue(getLinkedElement(), feature, getConfig());
			} else {
				linkedValue = greenParent.eGet(feature);
			}
			if (linkedValue == null && value != null ||  linkedValue != null && !linkedValue.equals(value)) {
				getDescriptor().eSet(feature, linkedValue);
			}
			
			return linkedValue;
		}

		return null;
	}
	
	private Object calcAttributeFeatureValue(MethodElement linkedElement,
			EStructuralFeature feature, MethodConfiguration config) {
		if (feature == UmaPackage.eINSTANCE.getMethodElement_PresentationName()) {
			return ConfigurationHelper.getPresentationName(linkedElement, config);
		}
		return ConfigurationHelper.calcAttributeFeatureValue(
				getLinkedElement(), feature, getConfig());
	}
	
	public Object getOFeatureValue(OppositeFeature ofeature) {
		return super.getOFeatureValue(ofeature);
	}
	
	protected ContentElement getLinkedElement() {
		MethodElement element = getRawLinkedElement();
		if (element == null) {
			return null;
		}
		return (ContentElement) ConfigurationHelper.getCalculatedElement(element, getConfig());
	}
	
	protected MethodElement getRawLinkedElement() {
		throw new UnsupportedOperationException();
	}
				
	protected Descriptor getDescriptor() {
		return (Descriptor) getElement();
	}
		
	protected List<? extends Descriptor> getDescriptorList(EReference elementFeature,
			EReference[] descriptorFeatures) {
		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfig());

		EReference dFeature = descriptorFeatures[0];
		EReference dFeatureExclude = descriptorFeatures[1];
		
		MethodElement element = getLinkedElement();
		if (element == null) {
			return ConfigurationHelper.calc0nFeatureValue(getDescriptor(),
					dFeature, realizer);
		}
						
		List<MethodElement> elementList = ConfigurationHelper.calc0nFeatureValue(element,
				elementFeature, realizer);
		
		List<Descriptor> resultDescriptorList = new ArrayList<Descriptor>();		
		
		EReference eRef = LibraryEditUtil.getInstance().getExcludeFeature(
				dFeature);
		Set<MethodElement> excludeElements = this.getExcludeOrAddtionalRefSet(
				getDescriptor(), eRef, realizer);

		Set<MethodElement> elementSet = new LinkedHashSet<MethodElement>();
		if (elementList != null) {
			for (MethodElement elem : elementList) {
				if (!excludeElements.contains(elem)) {
					elementSet.add(elem);
				}
			}
		}
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		Set<Descriptor> localUsedDescriptors = propUtil.getLocalUsedDescriptors(getDescriptor(), dFeature);
		if (! localUsedDescriptors.isEmpty()) {
			for (Descriptor des : localUsedDescriptors) {
				MethodElement localUseElement = getLinkedElement(des);
				if (localUseElement != null) {
					localUseElement = ConfigurationHelper.getCalculatedElement(localUseElement, getConfig());
					if (localUseElement != null && !excludeElements.contains(localUseElement)) {
						elementSet.add(localUseElement);
					}
				}
			}
		}
		
		List<Descriptor> descriptorList = ConfigurationHelper.calc0nFeatureValue(
				getDescriptor(), dFeature, realizer);

		for (Descriptor des : descriptorList) {
			MethodElement me = getLinkedElement(des);
			if (me == null || propUtil.localUse(des, getDescriptor(), dFeature)) {
				resultDescriptorList.add(des);
				if (me != null) {
					elementSet.remove(me);
				}

			} else if (elementSet.contains(me)) {
				resultDescriptorList.add(des);
				elementSet.remove(me);
			}
		}

		if (elementSet.isEmpty()) {
			return processResultDescriptorList(resultDescriptorList, dFeature);
		}

		Activity parentAct = getDescriptor().getSuperActivities();
		if (parentAct == null) {
			return processResultDescriptorList(resultDescriptorList, dFeature);
		}
		Descriptor referencingDes = getDescriptor();//shijin 41175
		for (MethodElement me : elementSet) {
			Descriptor des = (Descriptor) getMgr().getDescriptor(
					referencingDes, parentAct, me, dFeature);
			
			//shijin<Defect 41175 Ensure that published URLs stay the same for the same configuration when using free configuration process
			Activity checkParentAct = parentAct;
			while(!(checkParentAct instanceof Process)) {
				checkParentAct = checkParentAct.getSuperActivities();
			}
			Process p = (Process)checkParentAct;
			if(p.getDefaultContext() == null && (me instanceof Role || me instanceof WorkProduct)) {
				des.setGuid(referencingDes.getGuid() + me.getGuid());
			}
			//>shijin 41175
			
			resultDescriptorList.add(des);
		}
		
		return processResultDescriptorList(resultDescriptorList, dFeature);
	}
	
	private List<Descriptor> processResultDescriptorList(
			List<Descriptor> resultDescriptorList, EReference dFeature) {
		if (dFeature.isMany()) {
			List<Descriptor> listValue = (List<Descriptor>) getDescriptor().eGet(
					dFeature);
			if (listValue != null && !listValue.isEmpty()) {
				Set<Descriptor> resultSet = new HashSet<Descriptor>(
						resultDescriptorList);

				LibraryEditUtil libEditUtil = LibraryEditUtil.getInstance();
				
				boolean oldDeliver = getDescriptor().eDeliver();
				getDescriptor().eSetDeliver(false);
				try {
					for (int i = listValue.size() - 1; i >= 0; i--) {
						Descriptor des = listValue.get(i);
						if (!resultSet.contains(des)) {
							listValue.remove(i);
							libEditUtil.removeOppositeFeature(getDescriptor(), des, dFeature);
						}
					}
				} finally {
					getDescriptor().eSetDeliver(oldDeliver);
				}								
			}
		}
		
		return resultDescriptorList;
	}
	
	private MethodElement getLinkedElement(Descriptor des) {
		MethodElement element = DescriptorPropUtil.getDesciptorPropUtil().getLinkedElement(des);
		if (element == null) {
			return null;
		}
		return ConfigurationHelper.getCalculatedElement(element, getConfig());
	}	
	
	public Set<Descriptor> updateAndGetAllReferenced() {		
		List<Guidance> gList = getGuidances();
		return super.updateAndGetAllReferenced();
	}
	
	protected void addToSet(Set<Descriptor> set, List<? extends Descriptor> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		set.addAll(list);
	}
	
	public void updateStringValues() {
		super.updateStringValues();
		for (EStructuralFeature feature : featureSet) {
			getFeatureValue_(feature);
		}
		
		for (EStructuralFeature feature : contentFeatureMap.keySet()) {
			getFeatureValue_(feature);
		}
	}
	
	protected Set<MethodElement> getExcludeOrAddtionalRefSet(Descriptor des,
			EReference eRef, ElementRealizer realizer) {
		Set<MethodElement> set = new LinkedHashSet<MethodElement>();
		if (! ProcessUtil.isSynFree()) {
			return set;
		}
		if (des == null) {
			return set;
		}
		try {
			Set<MethodElement> rawSet = getRawExcludeOrAddtionalRefSet(des, eRef,
					realizer.getConfiguration(), true);
			for (MethodElement elem : rawSet) {
				MethodElement realized = ConfigurationHelper.getCalculatedElement(elem, realizer);
				if (realized != null) {
					set.add(realized);
				}
			}
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}

		return set;
	}	

	private Set<MethodElement> getRawExcludeOrAddtionalRefSet(Descriptor des, EReference eRef, MethodConfiguration config, boolean topLevelCall) {
		List<MethodElement> list;
		Set<MethodElement> refSet = new LinkedHashSet<MethodElement>();
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		Descriptor greenParent = propUtil.getGreenParentDescriptor(des);
		
		//An "exclude" or "additional guidance" list of customized descriptor is always
		//calculated in the following block - its eGet value is never used as the realized
		//value.
		if (greenParent != null && ConfigurationHelper.inConfig(greenParent, config)) {
			Set<MethodElement> parentSet = getRawExcludeOrAddtionalRefSet(greenParent, eRef, config, false);
			refSet.addAll(parentSet);
			list = propUtil.getGreenRefDeltaList(des, eRef, false);
			if (list != null && !list.isEmpty()) {
				refSet.removeAll(list);
			}
			list = propUtil.getGreenRefDeltaList(des, eRef, true);
			if (list != null && !list.isEmpty()) {
				refSet.addAll(list);
			}
		}
		list = (List<MethodElement>) des.eGet(eRef);
		if (greenParent == null && list != null && ! list.isEmpty()) {
			refSet.addAll(list);
		}
		if (topLevelCall && greenParent != null) {
			if (list.size() != refSet.size()) {
				list.clear();
				list.addAll(refSet);
			}
		}
		return refSet;
	}

	public List<Guidance> getGuidances() {
		UmaPackage up = UmaPackage.eINSTANCE;
		List<Guidance> resultList = new ArrayList<Guidance>();

		Map<EReference, EReference> refMap = LibraryEditUtil.getInstance()
				.getGuidanceRefMap(getLinkedElementType());
		
		if (refMap == null) {
			return resultList;
		}

		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfig());

//		List<Guidance> excludeList = ConfigurationHelper.calc0nFeatureValue(
//				getDescriptor(), up.getDescriptor_GuidanceExclude(), realizer);

		Set<MethodElement> excludeList = getExcludeOrAddtionalRefSet(
				getDescriptor(), up.getDescriptor_GuidanceExclude(), realizer);

		Set<MethodElement> addtionList = getExcludeOrAddtionalRefSet(
				getDescriptor(), up.getDescriptor_GuidanceAdditional(),
				realizer);
			
		for (Map.Entry<EReference, EReference> entry : refMap.entrySet()) {
			List<Guidance> subList = calculateGuidances(entry.getKey(), entry
					.getValue(), excludeList, addtionList);
			resultList.addAll(subList);
		}

		return resultList;
	}
	
	private List<Guidance> calculateGuidances(EReference eRef, EReference dRef,
			Collection<MethodElement> excludeList, Collection<MethodElement> addtionList) {
		
//		System.out.println("LD> eRef: " + eRef);
//		System.out.println("LD> dRef: " + dRef);
//		System.out.println("");
		
		UmaPackage up = UmaPackage.eINSTANCE;
		
		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfig());
		
		MethodElement element = getLinkedElement();
		if (element == null) {
			return ConfigurationHelper.calc0nFeatureValue(getDescriptor(),
					dRef, realizer);
		}
		
		List<Guidance> elementGuidanceList = ConfigurationHelper.calc0nFeatureValue(element,
				eRef, realizer);
		
		Set<Guidance> resultGuidanceSet = new LinkedHashSet<Guidance>();
		if (addtionList != null) {
			for (MethodElement me : addtionList) {
				Guidance g = (Guidance) me;
				if (eRef == UmaUtil.MethodElement_UdtList) {
					if (PracticePropUtil.getPracticePropUtil().isUdtType(g)) {
						resultGuidanceSet.add(g);		
					}
				} else if (eRef.getEType().isInstance(g)) {
					resultGuidanceSet.add(g);
				}
			}
		}
		if (!elementGuidanceList.isEmpty()) {
			resultGuidanceSet.addAll(elementGuidanceList);
			if (excludeList != null && ! excludeList.isEmpty()) {
				resultGuidanceSet.removeAll(excludeList);
			}
		} 
				
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		List<Guidance> resultGuidanceList = new ArrayList<Guidance>();
		boolean oldDeliver =  getDescriptor().eDeliver();		
		try {
			LibraryEditUtil libEditUtil = LibraryEditUtil.getInstance();
			getDescriptor().eSetDeliver(false);
//			List<Guidance> desCuidanceList = (List<Guidance>) getDescriptor().eGet(dRef);
			List<Guidance> desCuidanceList = (List<Guidance>) propUtil.eGet(getDescriptor(), dRef, true);
			for (int i = desCuidanceList.size() -1; i >= 0 ; i--) {
				boolean keepInList = resultGuidanceSet.remove(desCuidanceList.get(i));
				if (! keepInList) {
					Guidance g = desCuidanceList.remove(i);
					libEditUtil.removeOppositeFeature(getDescriptor(), g, dRef);
				}
			}
			if (! resultGuidanceSet.isEmpty()) {
				Set<Guidance> desGuidanceSet = new HashSet<Guidance>();
				desGuidanceSet.addAll(desCuidanceList);
				for (Guidance g : resultGuidanceSet) {
					if (! desGuidanceSet.contains(g)) {
						desCuidanceList.add(g);
						libEditUtil.addOppositeFeature(getDescriptor(), g, dRef);
					}
				}
			}
			if (dRef == UmaUtil.MethodElement_UdtList) {
				try {
					propUtil.storeReferences(getDescriptor(), false);
				} catch (Exception e) {
					LibraryPlugin.getDefault().getLogger().logError(e);	
				}
			}
			resultGuidanceList.addAll(desCuidanceList);
			
		} finally {
			getDescriptor().eSetDeliver(oldDeliver);
		}

		return resultGuidanceList;
	}
	
	
	private EClass getLinkedElementType() {
		if (getDescriptor() instanceof TaskDescriptor) {
			return UmaPackage.eINSTANCE.getTask();
		}
		if (getDescriptor() instanceof RoleDescriptor) {
			return UmaPackage.eINSTANCE.getRole();
		}
		if (getDescriptor() instanceof WorkProductDescriptor) {
			return UmaPackage.eINSTANCE.getWorkProduct();
		}
	
		throw new UnsupportedOperationException();
	}
}
