package org.eclipse.epf.library.realization.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.realization.IRealizedRoleDescriptor;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;

public class RealizedRoleDescriptor extends RealizedDescriptor implements
		IRealizedRoleDescriptor {

	private static Set<EStructuralFeature> featureSet = new HashSet<EStructuralFeature>();
	static {
		UmaPackage up = UmaPackage.eINSTANCE;		
		featureSet.add(up.getRoleDescriptor_ResponsibleFor());
	}
	
	public RealizedRoleDescriptor(RoleDescriptor rd) {
		super(rd);
	}
		
	public boolean handleFeature(EStructuralFeature feature) {
		if (featureSet.contains(feature)) {
			return true;
		}
		return super.handleFeature(feature);
	}
	
	protected MethodElement getRawLinkedElement() {
		return getRoleDescriptor().getRole();
	}
	
	private RoleDescriptor getRoleDescriptor() {
		return (RoleDescriptor) getElement();
	}
	
	public List<WorkProductDescriptor> getResponsibleFor() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference rdReference = up.getRoleDescriptor_ResponsibleFor();
		List<WorkProductDescriptor> wpdList = (List<WorkProductDescriptor>) getCachedValue(rdReference);
		if (wpdList == null) {
			EReference[] rdFeatures = { rdReference,
					up.getRoleDescriptor_ResponsibleForExclude(), };
			wpdList = (List<WorkProductDescriptor>) getDescriptorList(up
					.getRole_ResponsibleFor(), rdFeatures);
			storeCachedValue(rdReference, wpdList);
		}
		if (! DescriptorPropUtil.getDesciptorPropUtil().isCreatedByReference(getDescriptor())) {
			markWpdsReferenced(wpdList);
		}
		return wpdList;
	}
	
	@Override
	public Set<Descriptor> updateAndGetAllReferenced() {		
		Set<Descriptor> referenced = new HashSet<Descriptor>();
		addToSet(referenced, getResponsibleFor());
		
		Set<Descriptor> superSet = super.updateAndGetAllReferenced();
		referenced.addAll(superSet);		
		return referenced;
	}
	
	public Object getFeatureValue(EStructuralFeature feature) {		
		UmaPackage up = UmaPackage.eINSTANCE;		
		if (feature == up.getRoleDescriptor_ResponsibleFor()) {
			return getResponsibleFor();
		}

		return super.getFeatureValue(feature); 
	}
	
}
