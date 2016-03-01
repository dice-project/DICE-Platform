package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.uma.DescriptorExt;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.DescriptorDescription;
import org.eclipse.epf.uma.EstimationConsiderations;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Guideline;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject.ExtendObject;
import org.eclipse.epf.uma.util.UmaUtil;

public class DescriptorPropUtil extends WbePropUtil {
	
	public static boolean useLinkedElementInDiagram = false; 
	
	private static boolean localDebug = false;
	
	private static final String plus = "+"; 							//$NON-NLS-1$
	private static final String minus = "-"; 							//$NON-NLS-1$

	//Method property name strings
	public static final String DESCRIPTOR_NoAutoSyn = "descriptor_noAutoSyn"; 							//$NON-NLS-1$	
	public static final String DESCRIPTOR_CreatedByReference = "descriptor_createdByReference"; 		//$NON-NLS-1$
	public static final String DESCRIPTOR_Customization = "descriptor_customization"; 					//$NON-NLS-1$
	public static final String DESCRIPTOR_LocalUsingInfo = "descriptor_localUsingInfo";					//$NON-NLS-1$
	public static final String DESCRIPTOR_GreenParent = "descriptor_greenParent";						//$NON-NLS-1$
	public static final String DESCRIPTOR_GreenRefDelta = "descriptor_greenRefDelta";	
	
	private static int nameReplace = 				1;		//0000000000000001
	private static int presentatioNameReplace = 	2;		//0000000000000010
	private static int briefDesReplace = 			4;		//0000000000000100
	private static int mainDesReplace = 			8;		//0000000000001000
	private static int keyConsiderReplace =		   16;		//0000000000010000
	
	private static DescriptorPropUtil descriptorPropUtil = new DescriptorPropUtil();
	public static DescriptorPropUtil getDesciptorPropUtil() {
		return descriptorPropUtil;
	}
	public static DescriptorPropUtil getDesciptorPropUtil(IActionManager actionManager) {
		return new DescriptorPropUtil(actionManager);
	}
	
	protected DescriptorPropUtil() {		
	}
	
	protected DescriptorPropUtil(IActionManager actionManager) {
		super(actionManager);
	}
	
	//Test if the descriptor is not to be automatically synchronized
	public boolean isNoAutoSyn(Descriptor d) {
		if (getLinkedElement(d) == null) {
			return true;
		}
		Boolean value = getBooleanValue(d, DESCRIPTOR_NoAutoSyn);
		return value == null ? false : value.booleanValue();
	}
	
	public void setNoAutoSyn(Descriptor d, boolean value) {
		setBooleanValue(d, DESCRIPTOR_NoAutoSyn, value);
	}
	
	//Test if the descriptor is created indirectly due to a reference relationship 
	public boolean isCreatedByReference(Descriptor d) {
		Boolean value = getBooleanValue(d, DESCRIPTOR_CreatedByReference);
		return value == null ? false : value.booleanValue();
	}
	
	public void setCreatedByReference(Descriptor d, boolean value) {
		setBooleanValue(d, DESCRIPTOR_CreatedByReference, value);
	}
	
	public boolean isNameRepalce(Descriptor d) {
		if (hasNoValue(d.getName())) {
			return false;
		}
		return getCustomization(d, nameReplace);
	}
	
	public void setNameRepalce(Descriptor d, boolean value) {
		setCustomization(d, nameReplace, value);
	}	
	
	public boolean isPresentationNameRepalce(Descriptor d) {
		if (hasNoValue(d.getPresentationName())) {
			return false;
		}
		return getCustomization(d, presentatioNameReplace);
	}
	
	public void setPresentationNameRepalce(Descriptor d, boolean value) {
		setCustomization(d, presentatioNameReplace, value);
	}
	
	public boolean isBriefDesRepalce(Descriptor d) {
		if (hasNoValue(d.getBriefDescription())) {
			return false;
		}
		return getCustomization(d, briefDesReplace);
	}
	
	public void setBriefDesRepalce(Descriptor d, boolean value) {
		setCustomization(d, briefDesReplace, value);
	}
	
	public boolean isMainDesRepalce(Descriptor d) {
		if (d.getPresentation() instanceof DescriptorDescription) {
			String value = ((DescriptorDescription) d.getPresentation()).getRefinedDescription();
			if (hasNoValue(value)) {
				return false;
			}
		}
		return getCustomization(d, mainDesReplace);
	}
	
	public void setMainDesRepalce(Descriptor d, boolean value) {
		setCustomization(d, mainDesReplace, value);
	}
	
	public boolean isKeyConsiderRepalce(Descriptor d) {
		if (d.getPresentation() instanceof DescriptorDescription) {
			String value = ((DescriptorDescription) d.getPresentation()).getKeyConsiderations();
			if (hasNoValue(value)) {
				return false;
			}
		}
		return getCustomization(d, keyConsiderReplace);
	}
	
	public void setKeyConsiderRepalce(Descriptor d, boolean value) {
		setCustomization(d, keyConsiderReplace, value);
	}
	
	public Set<Descriptor> getLocalUsedDescriptors(Descriptor usingD) {
		return getLocalUsedDescriptors(usingD, null);
	}
	
	// if feature == null, get all
	public Set<Descriptor> getLocalUsedDescriptors(Descriptor usingD,
			EReference feature) {
		if (! ProcessUtil.isSynFree()) {
			return new HashSet<Descriptor>();
		}
		Set<Descriptor> descriptors = getLocalUsedDescriptors(usingD, feature,
				""); //$NON-NLS-1$

		Descriptor greenParent = getGreenParentDescriptor(usingD);
		if (greenParent != null && feature != null) {
			Set<Descriptor> descriptorsFromParent = getLocalUsedDescriptors(
					greenParent, feature, ""); //$NON-NLS-1$
			for (Descriptor usedD : descriptorsFromParent) {
				if (! localUse_(usedD, usingD, feature, minus)) {
					descriptors.add(usedD);
				}
			}
//			descriptors.addAll(descriptorsFromParent);
		}

		if (localDebug) {
			System.out
					.println("LD> getLocalUsedDescriptors, feature: " + feature + "\nl" + descriptors); //$NON-NLS-1$  //$NON-NLS-2$ 
		}
		return descriptors;
	}
	
	private Set<Descriptor> getLocalUsedDescriptors(Descriptor usingD,
			EReference feature, String featureSuffix) {
		Set<Descriptor> descriptors = new HashSet<Descriptor>();

		String value = getStringValue(usingD, DESCRIPTOR_LocalUsingInfo);
		if (value == null || value.length() == 0) {
			return descriptors;
		}

		String[] infos = value.split(infoSeperator);
		if (infos == null || infos.length == 0) {
			return descriptors;
		}

		boolean modified = false;
		String newValue = ""; //$NON-NLS-1$
		int sz = infos.length / 2;
		for (int i = 0; i < sz; i++) {
			int i1 = i * 2;
			int i2 = i1 + 1;
			String iGuid = infos[i1];
			String iFeature = infos[i2];
			MethodElement element = LibraryEditUtil.getInstance()
					.getMethodElement(iGuid);

			if (element instanceof Descriptor && UmaUtil.isInLibrary(element)) {
				if (feature == null || iFeature.equals(feature.getName() + featureSuffix)) {
					descriptors.add((Descriptor) element);
				}
				newValue = incNewValue(newValue, iGuid, iFeature);
			} else {
				modified = true;
			}
		}
		
		if (modified) {
			setStringValue(usingD, DESCRIPTOR_LocalUsingInfo, newValue);
		}
		return descriptors;
	}
	
	private String incNewValue(String newValue, String iGuid, String iFeature) {
		if (newValue.length() > 0) {
			newValue = newValue.concat(infoSeperator);
		}
		newValue = newValue.concat(iGuid.concat(infoSeperator).concat(iFeature));
		return newValue;
	}
	
	//Test if usedD is locally used by usingD through relationship specified by the
	//given feature.
	public boolean localUse(Descriptor usedD, Descriptor usingD, EReference feature) {
		try {
			boolean be = localUse_(usedD, usingD, feature);
			if (!be) {
				Descriptor greenParent = getGreenParentDescriptor(usingD);
				if (greenParent != null) {
					if (! localUse_(usedD, usingD, feature, minus)) {	//if no toggled off in child
						be = localUse_(usedD, greenParent, feature);
					}
				}
			}
			if (localDebug) {
				System.out.println("LD> localUse: " + be + 		
						", usingD: " + usingD.getName() +
						", usedD: " + usedD.getName() +
						", feature: " + feature.getName());;
						//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
			
			return be;
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}		
		return false;
	}
	
	private boolean localUse_(Descriptor usedD, Descriptor usingD, EReference feature) {
		return localUse_(usedD, usingD, feature, ""); //$NON-NLS-1$
	}
	
	private boolean localUse_(Descriptor usedD, Descriptor usingD, EReference feature, String featureSuffix) {
		String value = getStringValue(usingD, DESCRIPTOR_LocalUsingInfo);
		if (value == null || value.length() == 0) {
			return false;
		}
		
		String[] infos = value.split(infoSeperator);
		if (infos == null || infos.length == 0) {
			return false;
		}
				
		int sz = infos.length / 2; 		
		for (int i = 0; i < sz; i++) {
			int i1 = i*2;
			int i2 = i1 + 1;
			String iGuid = infos[i1];
			String iFeature = infos[i2];
			if (iGuid.equals(usedD.getGuid()) && iFeature.equals(feature.getName()+ featureSuffix)) {
				return true;
			} 
		}		
		return false;
		
	}	
	
	private void toggleOffGParentLocalUse(Descriptor usedD, Descriptor usingD, EReference feature) {
		addLocalUse(usedD, usingD, feature, minus);	//$NON-NLS-1$
	}
	
	private void untoggleOffGParentLocalUse(Descriptor usedD, Descriptor usingD, EReference feature) {
		removeLocalUse(usedD, usingD, feature, minus);	//$NON-NLS-1$
	}
	
	public void addLocalUse(Descriptor usedD, Descriptor usingD, EReference feature) {
		Descriptor greenParent = getGreenParentDescriptor(usingD);
		if (greenParent != null && localUse(usedD, greenParent, feature)) {
			removeLocalUse(usedD, usingD, feature, minus);	//$NON-NLS-1$
			return;
		}
		addLocalUse(usedD, usingD, feature, "");	//$NON-NLS-1$
	}
	
	private void addLocalUse(Descriptor usedD, Descriptor usingD, EReference feature, String featureSuffix) {
		try {
			if (localDebug) {
				System.out.println("LD> addLocalUse, usingD: " + usingD.getName() +
						", usedD: " + usedD.getName() + ", feature: " + feature.getName() + featureSuffix);
				//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			addReferenceInfo(usingD, usedD, DESCRIPTOR_LocalUsingInfo, feature.getName() + featureSuffix);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public void removeLocalUse(Descriptor usedD, Descriptor usingD, EReference feature) {
		Descriptor greenParent = getGreenParentDescriptor(usingD);
		if (greenParent != null && localUse(usedD, greenParent, feature)) {
			addLocalUse(usedD, usingD, feature, minus);	//$NON-NLS-1$
			return;
		}
		removeLocalUse(usedD, usingD, feature, "");	//$NON-NLS-1$
	}
	
	private void removeLocalUse(Descriptor usedD, Descriptor usingD, EReference feature, String featureSuffix) {
		try {
			removeReferenceInfo(usingD, usedD, DESCRIPTOR_LocalUsingInfo, feature.getName() + featureSuffix);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
	}

	public void addLocalUsingInfo(List<Descriptor> deslIst, Descriptor desc, EReference feature) {
		if (! ProcessUtil.isSynFree() || deslIst == null || desc == null || feature == null) {
			return;
		}
		
		for (Descriptor des : deslIst) {
			addLocalUse(des, desc, feature);
		}
	}
	
	public void removeLocalUsingInfo(List<Descriptor> deslIst, Descriptor desc, EReference feature) {
		if (! ProcessUtil.isSynFree() || deslIst == null || desc == null || feature == null) {
			return;
		}		

		for (Descriptor des : deslIst) {
			removeLocalUse(des, desc, feature);
		}		
	}
	
	public boolean hasNoValue(String str) {
		return str == null || str.trim().length() == 0;
	}
		
	protected boolean getCustomization(Descriptor d, int maskBit) {
		Integer cusValue = getIntValue(d, DESCRIPTOR_Customization);
		int cus = cusValue == null ? 0 : cusValue.intValue();
		return (cus & maskBit) > 0;
	}
	
	protected void setCustomization(Descriptor d, int maskBit, boolean value) {
		Integer cusValue = getIntValue(d, DESCRIPTOR_Customization);
		int cus = cusValue == null ? 0 : cusValue.intValue();
		boolean oldValue = (cus & maskBit) > 0;
		if (oldValue == value) {
			return;
		}
		if (value) {
			cus |= maskBit;
		} else {
			cus ^= maskBit;
		}
		setIntValue(d, DESCRIPTOR_Customization, cus);
	}	
	
	public MethodElement getLinkedElement(Descriptor des) {
		return ProcessUtil.getAssociatedElement(des);
	}
	
	public boolean isValueReplaced(EStructuralFeature feature, Descriptor d) {
		UmaPackage up = UmaPackage.eINSTANCE;
		if (feature == up.getNamedElement_Name()) {
			return isNameRepalce(d);
		}
		if (feature == up.getMethodElement_PresentationName()) {
			return isPresentationNameRepalce(d);
		}
		if (feature == up.getMethodElement_BriefDescription()) {
			return isBriefDesRepalce(d);
		}
		if (feature == up.getDescriptorDescription_RefinedDescription()) {
			return isMainDesRepalce(d);
		}
		if (feature == up.getContentDescription_KeyConsiderations()) {
			return isKeyConsiderRepalce(d);
		}
		return false;
	}
	
	//Return green parent guid
	public String getGreenParent(Descriptor d) {
		String guid = getStringValue(d, this.DESCRIPTOR_GreenParent);
		if (guid == null || guid.trim().length() == 0) {
			return null;
		}
		return guid;
	}
	
	public void setGreenParent(Descriptor d, String value) {
		setStringValue(d, this.DESCRIPTOR_GreenParent, value);
	}
	
 	public EReference getExcludeFeature(EReference ref) {		
		return LibraryEditUtil.getInstance().getExcludeFeature(ref);
	}
	
	/**
	 * 
	 * Check all the elements in list, to see if contains elements with
	 * different type
	 * @return true single type
	 *         flase multiple type
	 * 
	 */
	public boolean checkSelection(List list, Descriptor desc, EReference ref, MethodConfiguration config) {		
		int dynamic = 0;
		int dynamicExclude = 0;
		int local = 0;
		
		for (int i = 0; i < list.size(); i++) {
			MethodElement des = (MethodElement) list.get(i);
			if (isDynamicAndExclude(des, desc, ref, config)) {
				dynamicExclude ++;
			} else if (isDynamic(des, desc, ref)) {
				dynamic ++;
			} else {
				local ++;
			}
		}
		
		if (((dynamic > 0) && (dynamicExclude > 0))
			|| ((dynamic > 0) && (local > 0))
			|| ((local > 0) && (dynamicExclude > 0))) {
			return false;
		}
		
		return true;		
	}
	
	public boolean CheckSelectionForGuidance(List list, Descriptor desc, MethodConfiguration config) {
		int dynamic = 0;
		int dynamicExclude = 0;
		int local = 0;
		
		for (int i = 0; i < list.size(); i++) {
			MethodElement des = (MethodElement) list.get(i);
			EReference ref = getGuidanceEReference((Guidance)des);
			if (isDynamicAndExclude(des, desc, ref, config)) {
				dynamicExclude ++;
			} else if (isGuidanceDynamic(des, desc, config)) {
				dynamic ++;
			} else {
				local ++;
			}
		}
		
		if (((dynamic > 0) && (dynamicExclude > 0))
			|| ((dynamic > 0) && (local > 0))
			|| ((local > 0) && (dynamicExclude > 0))) {
			return false;
		}
		
		return true;
	}
	
	public Descriptor getGreenParentDescriptor(Descriptor des) {
		String guid = getGreenParent(des);
		if (guid == null) {
			return null;
		}
		Descriptor parent = (Descriptor) LibraryEditUtil.getInstance().getMethodElement(guid);
		if (! UmaUtil.isInLibrary(parent)) {
			setGreenParent(des, "");		//$NON-NLS-1$
			return null;
		}
		addToCustomizingChildren(parent, des);
		return parent;
	}	
	
	//positive = true:  feature name appended with "+" -> extra exclude (or additional guidance) added
	//positive = false: feature name appended with "-" -> toggle off parent's exclude (or additional guidance)
	public void addGreenRefDelta(Descriptor des, MethodElement referenced, EReference feature, boolean positive) {
		String refName = feature.getName() + (positive ? plus : minus);
		try {
			addReferenceInfo(des, referenced, DESCRIPTOR_GreenRefDelta, refName);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public List<MethodElement> getGreenRefDeltaList(Descriptor des,			
			EReference feature, boolean positive) {
		
		String refName = feature.getName() + (positive ? plus : minus);
		try {
			return getGreenRefDeltaList_(des, refName);			
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}
	
	public void replaceLocalUseGuidStrings(Descriptor des, Map<String, String> replacedToReplacerMap) {
		replaceGuidStrings(des, DESCRIPTOR_LocalUsingInfo, replacedToReplacerMap);
	}
	
	private void replaceGuidStrings(Descriptor des, String propName, Map<String, String> replacedToReplacerMap) {
		String value = getStringValue(des, propName);
		if (value == null || value.length() == 0) {
			return ;
		}

		String[] infos = value.split(infoSeperator);
		if (infos == null || infos.length == 0) {
			return;
		}

		int sz = infos.length / 2;
		Set<String> replacedSet = new HashSet<String>();
		for (int i = 0; i < sz; i++) {
			String oldGuid = infos[i * 2];
			String newGuid = null;
			if (! replacedSet.contains(oldGuid)) {
				newGuid = replacedToReplacerMap.get(oldGuid);
				replacedSet.add(oldGuid);
			}
			if (newGuid != null) {
				value = value.replaceAll(oldGuid, newGuid);
			}
		}		
		setStringValue(des, propName, value);
	}
	
	private List<MethodElement> getGreenRefDeltaList_(Descriptor des, String refName) { 		
		String value = getStringValue(des, DESCRIPTOR_GreenRefDelta);
		
		if (value == null || value.length() == 0) {
			return null;
		}
		
		String[] infos = value.split(infoSeperator);
		if (infos == null || infos.length == 0) {
			return null;
		}
		
		boolean modified = false;
		String newValue = ""; //$NON-NLS-1$
		List<MethodElement> deltaList = new ArrayList<MethodElement>();
		int sz = infos.length / 2; 		
		for (int i = 0; i < sz; i++) {
			int i1 = i*2;
			int i2 = i1 + 1;
			String iGuid = infos[i1];
			String iFeature = infos[i2];
			if (iFeature.equals(refName)) {
				MethodElement element = LibraryEditUtil.getInstance().getMethodElement(iGuid);
				if (element != null && UmaUtil.isInLibrary(element)) {
					deltaList.add(element);
					newValue =  incNewValue(newValue, iGuid, iFeature);
				} else {
					modified = true;
				}
			} else {
				newValue =  incNewValue(newValue, iGuid, iFeature);
			}
		}		
		
		if (modified) {
			setStringValue(des, DESCRIPTOR_GreenRefDelta, newValue);
		}
		
		return deltaList;		
	}
	
	public void removeGreenRefDelta(Descriptor des, MethodElement referenced, EReference feature, boolean positive) {
		String refName = feature.getName() + (positive ? plus : minus);
		try {
			removeReferenceInfo(des, referenced, DESCRIPTOR_GreenRefDelta, refName);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
	}
	
//	private void addRefInfo(Descriptor descriptor, MethodElement referenced, String propName, String refName) {
//		addReferenceInfo(descriptor, referenced, propName, refName);
//	}
//		
//	private void removeRefInfo(Descriptor descriptor, MethodElement referenced, String propName, String refName) {
//		removeReferenceInfo(descriptor, referenced, propName, refName);
//	}
	
	public List<? extends Descriptor> getCustomizingChildren(Descriptor des) {
		if (des == null) {
			return null;
		}
		MethodElementExt mext =  getExtendObject(des, false);
		if (! (mext instanceof DescriptorExt)) {
			return null;
		}
		
		DescriptorExt ext = (DescriptorExt) mext;		 
		return ext == null ? null : ext.getCustomizingChildren();
	}
	
	public void collectCustomizingDescendants(Descriptor des, Set<MethodElement> descendants) {
		List<? extends Descriptor> children = getCustomizingChildren(des);
		if (children == null || children.isEmpty()) {
			return;
		}
		for (Descriptor cdes : children) {
			if (! descendants.contains(cdes)) {
				descendants.add(cdes);
				collectCustomizingDescendants(cdes, descendants);
			}		
		}
	}
	
	public void addToCustomizingChildren(Descriptor parent, Descriptor child) {
		if (parent == null || child == null) {
			return;
		}
		Object eObj = getExtendObject(parent, true);
		if (eObj instanceof DescriptorExt) {
			DescriptorExt ext = (DescriptorExt) eObj;
			ext.addToCustomizingChildren(child);
		}
	}
	
	public boolean isDynamicAndExclude(Object obj, Descriptor desc,
			EReference ref, MethodConfiguration config) {
		return LibraryEditUtil.getInstance().isDynamicAndExclude(obj, desc,
				ref, config);
	}

	public boolean isDynamic(Object obj, Descriptor desc, EReference ref) {
		return LibraryEditUtil.getInstance().isDynamic(obj, desc, ref);
	}
	
	public boolean isGuidanceDynamic(Object obj, Descriptor desc,
			MethodConfiguration config) {
		return LibraryEditUtil.getInstance().isGuidanceDynamic(obj, desc, config);
	}
	
    public boolean isFromGreenParentLocalList(Object obj, Descriptor desc, EReference ref) {
    	if ((obj instanceof Descriptor) && !isDynamic(obj, desc, ref)) {
    		Descriptor greenParent = getGreenParentDescriptor(desc);
    		if (greenParent != null) {
    			if (localUse((Descriptor)obj, greenParent, ref)) {
    				return true;
    			}    			
    		}    		
    	}
    	
    	return false;
    }
    
    public boolean isGuidanceFromGreenParentLocalList(Object obj, Descriptor desc,
    		MethodConfiguration config) {
    	if (!isGuidanceDynamic(obj, desc, config)) {
    		Descriptor greenParent = getGreenParentDescriptor(desc);
    		if (greenParent != null) {
    			if (!isGuidanceDynamic(obj, greenParent, config)) {
    				return true;
    			}    			
    		}    		
    	}
    	
    	return false;
    }
    
	public boolean isDescriptor(BreakdownElement element) {
		if ((element instanceof TaskDescriptor) || (element instanceof RoleDescriptor)
				|| (element instanceof WorkProductDescriptor)) {
			return true;
		}
		
		return false;
	}
	
	public EReference getGuidanceEReference(Guidance item) {
		EReference ref = null;
		
		if (item instanceof Checklist) {		
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Checklists();		
		} else if (item instanceof Concept) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Concepts();
		} else if (item instanceof Example) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Examples();
		} else if (item instanceof SupportingMaterial) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_SupportingMaterials();
		} else if (item instanceof Guideline) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Guidelines();
		} else if (item instanceof ReusableAsset) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_ReusableAssets();
		}else if (item instanceof Template) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Templates();
		}else if (item instanceof Report) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Reports();
		}else if (item instanceof ToolMentor) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Toolmentor();
		}else if (item instanceof EstimationConsiderations) {
			ref = UmaPackage.eINSTANCE.getBreakdownElement_Estimationconsiderations();
		} else if (item instanceof Practice) {
			if (PracticePropUtil.getPracticePropUtil().isUdtType((Practice) item)) {
				ref = UmaUtil.MethodElement_UdtList;
			}
		}
		
		return ref;
	}
	
	public void clearAllAutoSynProps(Descriptor des) {		
		removeProperty(des, DESCRIPTOR_Customization);
		removeProperty(des, DESCRIPTOR_LocalUsingInfo);
		removeProperty(des, DESCRIPTOR_GreenParent);
		removeProperty(des, DESCRIPTOR_GreenRefDelta);		
	}
	
	
}