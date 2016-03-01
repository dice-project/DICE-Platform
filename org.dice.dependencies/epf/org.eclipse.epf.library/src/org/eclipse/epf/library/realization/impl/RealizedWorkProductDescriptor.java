package org.eclipse.epf.library.realization.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.realization.IRealizedWorkProductDescriptor;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;

public class RealizedWorkProductDescriptor extends
		RealizedDescriptor implements IRealizedWorkProductDescriptor {

	private static Set<EStructuralFeature> featureSet = new HashSet<EStructuralFeature>();
	static {
		UmaPackage up = UmaPackage.eINSTANCE;		
		featureSet.add(up.getWorkProductDescriptor_DeliverableParts());
	}
	
	public RealizedWorkProductDescriptor(WorkProductDescriptor wpd) {
		super(wpd);
	}
	
	
	public boolean handleFeature(EStructuralFeature feature) {
		if (featureSet.contains(feature)) {
			return true;
		}
		return super.handleFeature(feature);
	}
	
	protected MethodElement getRawLinkedElement() {
		return getWorkProductDescriptor().getWorkProduct();
	}
	
	private WorkProductDescriptor getWorkProductDescriptor() {
		return (WorkProductDescriptor) getElement();
	}
	
	public List<WorkProductDescriptor> getDeliverableParts() {
		if (!(getLinkedElement() instanceof Deliverable)) {
			return Collections.EMPTY_LIST;
		}
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference wpdReference = up
				.getWorkProductDescriptor_DeliverableParts();
		List<WorkProductDescriptor> wpdList = (List<WorkProductDescriptor>) getCachedValue(wpdReference);
		if (wpdList == null) {
			EReference[] wpdFeatures = { wpdReference,
					up.getWorkProductDescriptor_DeliverablePartsExclude(), };
			wpdList = (List<WorkProductDescriptor>) getDescriptorList(up
					.getDeliverable_DeliveredWorkProducts(), wpdFeatures);
			storeCachedValue(wpdReference, wpdList);
		}
		return wpdList;
	}
	
	public List<WorkProductDescriptor> getContainedArtifacts() {
		if (!(getLinkedElement() instanceof Artifact)) {
			return Collections.EMPTY_LIST;
		}
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference wpdReference = ArtifactDescriptor_ContainedArtifacts;
		List<WorkProductDescriptor> wpdList = (List<WorkProductDescriptor>) getCachedValue(wpdReference);
		if (wpdList == null) {
			wpdList = new ArrayList<WorkProductDescriptor>();
			ElementRealizer realizer = DefaultElementRealizer
					.newElementRealizer(getConfig());
			List<MethodElement> elementList = ConfigurationHelper
					.calc0nFeatureValue(getLinkedElement(), up
							.getArtifact_ContainedArtifacts(), realizer);
			if (elementList != null && !elementList.isEmpty()) {
				Activity parentAct = getDescriptor().getSuperActivities();
				if (parentAct != null) {

					for (MethodElement me : elementList) {
						WorkProductDescriptor des = (WorkProductDescriptor) getMgr()
								.getDescriptor(getDescriptor(), parentAct, me,
										wpdReference);
						wpdList.add(des);
					}
				}
			}
			storeCachedValue(wpdReference, wpdList);
		}
		if (! PropUtil.getPropUtil().isExcludedFromPublish(getDescriptor())) {
			markWpdsReferenced(wpdList);
		}
		return wpdList;
	}
	
	@Override
	public Set<Descriptor> updateAndGetAllReferenced() {		
		Set<Descriptor> referenced = new HashSet<Descriptor>();
		addToSet(referenced, getDeliverableParts());
		addToSet(referenced, getContainedArtifacts());
		
		Set<Descriptor> superSet = super.updateAndGetAllReferenced();
		referenced.addAll(superSet);		
		return referenced;
	}
	
	public Object getFeatureValue(EStructuralFeature feature) {		
		UmaPackage up = UmaPackage.eINSTANCE;		
		if (feature == up.getWorkProductDescriptor_DeliverableParts()) {
			return getDeliverableParts();
		}

		return super.getFeatureValue(feature); 
	}
	
}
