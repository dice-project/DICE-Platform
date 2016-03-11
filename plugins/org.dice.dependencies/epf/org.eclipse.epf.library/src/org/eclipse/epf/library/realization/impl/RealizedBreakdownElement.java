package org.eclipse.epf.library.realization.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.realization.IRealizedBreakdownElement;
import org.eclipse.epf.library.edit.realization.IRealizedElement;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;

public class RealizedBreakdownElement extends RealizedElement implements IRealizedBreakdownElement {

	public RealizedBreakdownElement(BreakdownElement element) {
		super(element);
	}

	protected ContentElement getLinkedElement() {
		MethodElement element = getRawLinkedElement();
		if (! (element instanceof ContentElement)) {
			return null;
		}
		return (ContentElement) ConfigurationHelper.getCalculatedElement(element, getConfig());
	}
	
	protected MethodElement getRawLinkedElement() {
		return PropUtil.getPropUtil().getLinkedElement(getElement());
	}
	
	public Set<Descriptor> updateAndGetAllReferenced() {
		return updateExtendedReferences();				
	}
	
	private Set<Descriptor> updateExtendedReferences() {
		Set<Descriptor> set = updateExtendedReferences_();
		markWpdsReferenced(set);
		return set;
	}
	
	private Set<Descriptor> updateExtendedReferences_() {
		Set<Descriptor> set = new HashSet<Descriptor>();
		ContentElement element = getLinkedElement();
		if (element == null) {
			return set;
		}

		ModifiedTypeMeta meta = PropUtil.getPropUtil()
				.getGlobalMdtMeta(element);
		if (meta == null) {
			return set;
		}		

		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfig());
		for (ExtendedReference eRef : meta.getReferences()) {
			 Set<Descriptor> descriptors = updateExtendedReference(eRef);
			 if (! descriptors.isEmpty()) {
				 set.addAll(descriptors);
			 }
		}
		return set;
	}
	
	protected void markWpdsReferenced(Collection<? extends Descriptor> descriptors) {
		if (descriptors == null) {
			return;
		}
		for (Descriptor des : descriptors) {
			if (des instanceof WorkProductDescriptor) {
				PropUtil.getPropUtil().setExcludedFromPublish(des, false);
			}
		}
	}

	protected Set<Descriptor> updateExtendedReference(ExtendedReference eRef) {
		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfig());
		List elementList = ConfigurationHelper.calc0nFeatureValue(
				getLinkedElement(), eRef.getReference(), realizer);
		Object value = TypeDefUtil.getInstance().eGet(getElement(),
				eRef.getReference(), true);
		if (value instanceof List) {
			List dList = (List) value;
			dList.clear();
			if (elementList != null && !elementList.isEmpty()) {
				if (getDescriptor() != null && eRef.getContributeTo() != null) {
					Set<Descriptor> set = new HashSet<Descriptor>();
					Activity parentAct = getDescriptor().getSuperActivities();
					if (parentAct == null) {
						return set;
					}

					for (int i = 0; i < elementList.size(); i++) {
						Object item = elementList.get(i);
						if (item instanceof Role || item instanceof WorkProduct) {
							//Note: this call add to dList
							Descriptor des = (Descriptor) getMgr().getDescriptor(
									getDescriptor(), parentAct, (MethodElement) item, eRef.getReference());
							set.add(des);
						}
					}
					return set;
				} else {
					dList.addAll(elementList);
				}
			}
		}
		return Collections.EMPTY_SET;
	}
	
	protected Descriptor getDescriptor() {
		return null;
	}
	
	public void updateStringValues() {
		ContentElement element = getLinkedElement();
		if (element == null) {
			return;
		}

		ModifiedTypeMeta meta = PropUtil.getPropUtil()
				.getGlobalMdtMeta(element);
		if (meta == null) {
			return;
		}

		for (ExtendedAttribute eAtt : meta.getRtes()) {
			getFeatureValue_(eAtt.getAttribute());
		}
		for (ExtendedAttribute eAtt : meta.getAttributes()) {
			getFeatureValue_(eAtt.getAttribute());
		}
	}
	
	public Object getFeatureValue(EStructuralFeature feature) {
		return getFeatureValue_(feature);
	}
		
	private Object getFeatureValue_(EStructuralFeature feature) {
		if (TypeDefUtil.getInstance().getAssociatedExtendedAttribute(feature) == null) {
			return null;
		}
		return getContentFeatureValue(feature);
	}
	
	private Object getContentFeatureValue(EStructuralFeature feature) {
		if (getLinkedElement() == null) {
			return null;
		}
		if (! ContentDescriptionFactory.hasPresentation(getLinkedElement())) {
			return null;
		}
				
		EStructuralFeature elementFeature = null;
		if (TypeDefUtil.getInstance().getAssociatedExtendedAttribute(feature) != null) {
			elementFeature = feature;
		}
		
		if (elementFeature != null) {	

			Object value = null;
			if (getDescribableElement().getPresentation() != null) {
				try {
					value = TypeDefUtil.getInstance().eGet(getDescribableElement().getPresentation(), feature);
				} catch (Throwable e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
					return null;
				}
			}
			
			if (getLinkedElement() == null || getLinkedElement().getPresentation() == null) {
				return value;
			}
		
			
			Object linkedValue = ConfigurationHelper.calcAttributeFeatureValue(
						getLinkedElement().getPresentation(), elementFeature, getConfig());

			if (linkedValue == null && value != null ||  linkedValue != null && !linkedValue.equals(value)) {
				TypeDefUtil.getInstance().eSet(getDescribableElement().getPresentation(), feature, linkedValue);
			}

			return linkedValue;
		}
	
		return null;
	}
	
	protected DescribableElement getDescribableElement() {
		if (getElement() instanceof DescribableElement) {
			return (DescribableElement) getElement();
		}
		return null;
	}
	
}
