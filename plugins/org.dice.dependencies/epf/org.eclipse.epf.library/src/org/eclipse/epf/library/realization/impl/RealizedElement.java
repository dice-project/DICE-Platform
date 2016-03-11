package org.eclipse.epf.library.realization.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.realization.IRealizedElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

public class RealizedElement implements IRealizedElement {

	private MethodElement element;
	private RealizationManager mgr;

	private Map<EStructuralFeature, Object> featureValueMap = new HashMap<EStructuralFeature, Object>();
	
	public RealizedElement(MethodElement element) {
		this.element = element;
	}
	
	public MethodElement getElement() {
		return element;
	}
	
	public String getName() {
		return (String) getFeatureValue(UmaPackage.eINSTANCE.getNamedElement_Name());
	}
	
	public String getPresentationName() {
		return (String) getFeatureValue(UmaPackage.eINSTANCE.getMethodElement_PresentationName());
	}
	
	public String getBriefDescription() {
		return (String) getFeatureValue(UmaPackage.eINSTANCE.getMethodElement_BriefDescription());
	}
	
	protected Object getCachedValue(EStructuralFeature feature) {
		return getMgr().isCaching() ? featureValueMap.get(feature) : null;
	}
	
	protected void storeCachedValue(EStructuralFeature feature, Object value) {
		if (getMgr().isCaching()) { 
			featureValueMap.put(feature, value);
		}
	}
	
	public boolean handle(Object featureOrOFeature) {
		if (featureOrOFeature instanceof EStructuralFeature) {
			return handleFeature((EStructuralFeature) featureOrOFeature);
		}
		if (featureOrOFeature instanceof OppositeFeature) {
			return handleOFeature((OppositeFeature) featureOrOFeature);
		}
		
		return false;
	}
	
	public boolean handleFeature(EStructuralFeature feature) {
		return false;
	}
	
	public boolean handleOFeature(OppositeFeature ofeature) {
		return false;
	}
	
	public Object getFeatureValue(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}
	
	public Object getOFeatureValue(OppositeFeature ofeature) {
		throw new UnsupportedOperationException();
	}
	
	protected MethodConfiguration getConfig() {
		return getMgr().getConfig();
	}
	
	public RealizationManager getMgr() {
//		System.out.println("getMgr(), this: " + this);
//		System.out.println("getMgr(): " + mgr);
//		System.out.println("");		
		return mgr;
	}
	
	public void setMgr(RealizationManager mgr) {
//		System.out.println("setMgr(), this: " + this);
//		System.out.println("setMgr(), this.mgr: " + this.mgr);
//		System.out.println("setMgr(), mgr: " + mgr);
//		System.out.println("");
		this.mgr = mgr;
	}
	
	public void dispose() {
		featureValueMap = null;
		mgr = null;
	}
	
}
