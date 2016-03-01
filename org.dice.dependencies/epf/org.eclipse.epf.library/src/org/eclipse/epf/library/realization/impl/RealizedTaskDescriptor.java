package org.eclipse.epf.library.realization.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.realization.IRealizedTaskDescriptor;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;

public class RealizedTaskDescriptor extends RealizedDescriptor implements
		IRealizedTaskDescriptor {

	private static Set<EStructuralFeature> featureSet = new HashSet<EStructuralFeature>();
	static {
		UmaPackage up = UmaPackage.eINSTANCE;		
		featureSet.add(up.getTaskDescriptor_PerformedPrimarilyBy());
		featureSet.add(up.getTaskDescriptor_MandatoryInput());
		featureSet.add(up.getTaskDescriptor_AdditionallyPerformedBy());
		featureSet.add(up.getTaskDescriptor_Output());
		featureSet.add(up.getTaskDescriptor_SelectedSteps());
		
	}
	
	public RealizedTaskDescriptor(TaskDescriptor td) {
		super(td);
	}
	
	public boolean handleFeature(EStructuralFeature feature) {
		if (featureSet.contains(feature)) {
			return true;
		}
		return super.handleFeature(feature);
	}
	
	public Object getFeatureValue(EStructuralFeature feature) {		
		UmaPackage up = UmaPackage.eINSTANCE;		
		if (feature == up.getTaskDescriptor_PerformedPrimarilyBy()) {
			return getPerformedPrimarilyBy();
		}
		if (feature == up.getTaskDescriptor_MandatoryInput()) {
			return getMandatoryInput();
		}
		if (feature == up.getTaskDescriptor_AdditionallyPerformedBy()) {
			return getAdditionallyPerformedBy();
		}
		if (feature == up.getTaskDescriptor_Output()) {
			return getOutput();
		}
		if (feature == up.getTaskDescriptor_SelectedSteps()) {
			return getSelectedSteps();
		}

		return super.getFeatureValue(feature); 
	}	
	
	public List<RoleDescriptor> getPerformedPrimarilyBy() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference tdReference = up.getTaskDescriptor_PerformedPrimarilyBy();
		List<RoleDescriptor> rdList = (List<RoleDescriptor>) getCachedValue(tdReference);
		if (rdList == null) {
			EReference[] tdFeatures = { tdReference,
					up.getTaskDescriptor_PerformedPrimarilyByExcluded(), };
			rdList = (List<RoleDescriptor>) getDescriptorList(up
					.getTask_PerformedBy(), tdFeatures);
			storeCachedValue(tdReference, rdList);
		}
		return rdList;
	}

	public List<RoleDescriptor> getAdditionallyPerformedBy() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference tdReference = up.getTaskDescriptor_AdditionallyPerformedBy();
		List<RoleDescriptor> rdList = (List<RoleDescriptor>) getCachedValue(tdReference);
		if (rdList == null) {
			EReference[] tdFeatures = { tdReference,
					up.getTaskDescriptor_AdditionallyPerformedByExclude(),};
			rdList = (List<RoleDescriptor>) getDescriptorList(up
					.getTask_AdditionallyPerformedBy(), tdFeatures);
			storeCachedValue(tdReference, rdList);
		}
		return rdList;
	}
	
	public List<WorkProductDescriptor> getMandatoryInput() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference tdReference = up.getTaskDescriptor_MandatoryInput();
		List<WorkProductDescriptor> wpdList = (List<WorkProductDescriptor>) getCachedValue(tdReference);
		if (wpdList == null) {
			EReference[] tdFeatures = { tdReference,
					up.getTaskDescriptor_MandatoryInputExclude(),};
			wpdList = (List<WorkProductDescriptor>) getDescriptorList(up
					.getTask_MandatoryInput(), tdFeatures);
			storeCachedValue(tdReference, wpdList);
		}
		markWpdsReferenced(wpdList);
		return wpdList;
	}
	
	public List<WorkProductDescriptor> getOptionalInput() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference tdReference = up.getTaskDescriptor_OptionalInput();
		List<WorkProductDescriptor> wpdList = (List<WorkProductDescriptor>) getCachedValue(tdReference);
		if (wpdList == null) {
			EReference[] tdFeatures = { tdReference,
					up.getTaskDescriptor_OptionalInputExclude(), };
			wpdList = (List<WorkProductDescriptor>) getDescriptorList(up
					.getTask_OptionalInput(), tdFeatures);
			storeCachedValue(tdReference, wpdList);
		}
		markWpdsReferenced(wpdList);
		return wpdList;
	}
	
	public List<Section> getSelectedSteps() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference dFeature = up.getTaskDescriptor_SelectedSteps();
		
		List<Section> stepList = (List<Section>) getCachedValue(dFeature);
		if (stepList == null) {
			stepList = calculateSelectedSteps();
		}
		return stepList;
	}
	
	private List<Section> calculateSelectedSteps() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference dFeature = up.getTaskDescriptor_SelectedSteps();
		EReference dFeatureExclude = up.getTaskDescriptor_SelectedStepsExclude();
		EReference elementFeature = up.getTask_Steps();
		
		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfig());
		MethodElement element = getLinkedElement();
		if (element == null) {
			return ConfigurationHelper.calc0nFeatureValue(getDescriptor(),
					dFeature, realizer);
		}		
		List<Section> elementStepList = ConfigurationHelper.calc0nFeatureValue(element,
				elementFeature, realizer);
		if (elementStepList == null) {
			elementStepList = new ArrayList<Section>();
		}		

		if (!elementStepList.isEmpty()) {
			Set<MethodElement> excludeElements = getExcludeOrAddtionalRefSet(
					getDescriptor(), up.getTaskDescriptor_SelectedStepsExclude(), realizer);
			if (excludeElements != null && ! excludeElements.isEmpty()) {
				elementStepList.removeAll(excludeElements);
			}
		} 		
		boolean same = true;
		List<Section> valueList = ((TaskDescriptor) getDescriptor()).getSelectedSteps();
		if (elementStepList.size() == valueList.size()) {
			for (int i = 0; i < valueList.size(); i++) {
				if (valueList.get(i) != elementStepList.get(i)) {
					same = false;
					break;
				}
			}
		} else {
			same = false;
		}
		
		if (! same) {
			valueList.clear();
			if (! elementStepList.isEmpty()) {
				valueList.addAll(elementStepList);
			}
		}

		return valueList;
	}
	
	public List<WorkProductDescriptor> getOutput() {
		UmaPackage up = UmaPackage.eINSTANCE;
		EReference tdReference = up.getTaskDescriptor_Output();
		List<WorkProductDescriptor> wpdList = (List<WorkProductDescriptor>) getCachedValue(tdReference);
		if (wpdList == null) {
			EReference[] tdFeatures = { tdReference,
					up.getTaskDescriptor_OutputExclude(), };
			wpdList = (List<WorkProductDescriptor>) getDescriptorList(up
					.getTask_Output(), tdFeatures);
			storeCachedValue(tdReference, wpdList);
		}
		markWpdsReferenced(wpdList);
		return wpdList;
	}
		
	protected MethodElement getRawLinkedElement() {
		return getTaskDescriptor().getTask();
	}
	
	private TaskDescriptor getTaskDescriptor() {
		return (TaskDescriptor) getElement();
	}
	
	@Override
	public Set<Descriptor> updateAndGetAllReferenced() {		
		Set<Descriptor> referenced = new HashSet<Descriptor>();
		addToSet(referenced, getPerformedPrimarilyBy());
		addToSet(referenced, getMandatoryInput());
		addToSet(referenced, getAdditionallyPerformedBy());
		addToSet(referenced, getOptionalInput());
		addToSet(referenced, getOutput());
		
		getSelectedSteps();
		
		Set<Descriptor> superSet = super.updateAndGetAllReferenced();
		referenced.addAll(superSet);		
		return referenced;
	}
	
}
