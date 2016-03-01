package org.eclipse.epf.uma.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.epf.uma.ecore.IUserDefinedTypeMeta;

public class UserDefinedTypeMeta implements IUserDefinedTypeMeta {

	public static final String QReference_ = "qReference_";		//$NON-NLS-1$
	
	public static final UserDefinedTypeMeta noneValue = new UserDefinedTypeMeta();
	public static final String Type_Practice = "Practice";					//$NON-NLS-1$
	
	public static final String _typeName = "typeName";						//$NON-NLS-1$
	public static final String _typeId = "typeId";                                  //$NON-NLS-1$
	public static final String _problems = "problems";						//$NON-NLS-1$
	public static final String _goals = "goals";							//$NON-NLS-1$
	public static final String _background = "background";					//$NON-NLS-1$	
	public static final String _mainDescription = "mainDescription";		//$NON-NLS-1$	
	public static final String _application = "application"; 				//$NON-NLS-1$
	public static final String _levelsOfAdoption = "levelsOfAdoption"; 		//$NON-NLS-1$
	public static final String _additionalInfo = "additionalInfo"; 			//$NON-NLS-1$
	public static final String _icon = "icon";                              //$NON-NLS-1$
	public static final String _iconRelative = "iconRelative";              //$NON-NLS-1$
	public static final String _shapeIcon = "shapeIcon";                    //$NON-NLS-1$
	public static final String _shapeIconRelative = "shapeIconRelative";    //$NON-NLS-1$
	public static final String _referenceQualifiers = "referenceQualifiers";//$NON-NLS-1$
	public static final String _referenceQualifierNames = "referenceQualifierNames";//$NON-NLS-1$
	
	public static String[] rteNames = {
		_typeName,
		_typeId,
		_problems,
		_goals,
		_background,
		_mainDescription,
		_application,
		_levelsOfAdoption,
		_additionalInfo,
		_icon,
		_iconRelative,
		_shapeIcon,
		_shapeIconRelative,
		_referenceQualifiers,
		_referenceQualifierNames
	};
		
	private Map<String, String> rteNameMap;
	
	private String id;
	
	private Set<EReference> qualifiedReferences;
	
	private boolean qualifiedReferencesLoaded = false;
	
	private Map<String, String> referenceQualifiedNameToIdMap;
	private Map<String, String> referenceQualifiedIdToNameMap;

	public static UserDefinedTypeMeta newPracticeUtdpeMeta(String typeId) {
		UserDefinedTypeMeta meta = new UserDefinedTypeMeta();
		meta.setId(getPracticeUdtId(typeId));
		return meta;
	}
	
	public static String getPracticeUdtId(String typeId) {
		return Type_Practice + ":" + typeId;			//$NON-NLS-1$
	}
					
	public UserDefinedTypeMeta() {
	}
	
	public String getTypeId() {
		return getRteNameMap().get(_typeId);		//$NON-NLS-1$
	}
	
	public Map<String, String> getRteNameMap() {
		if (rteNameMap == null) {
			rteNameMap  = new HashMap<String, String>();
		}
		return rteNameMap;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean same(UserDefinedTypeMeta other) {
		if (other == null) {
			return false;
		}
		if (! same(this.id, other.id)) {
			return false;
		}
		for (String name : rteNames) {
			if (name.equals(_icon) || name.equals(_shapeIcon)) {
				continue;
			}
			String thisValue = this.getRteNameMap().get(name);
			String otherValue = other.getRteNameMap().get(name);
			if (! same(thisValue, otherValue)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean same(String a, String b) {
		if (a == null) {
			return b == null;
		} 
		return a.equals(b);
	}
		
	private List<String> convertStringsToList(String string) {
		ArrayList<String> strList = new ArrayList<String>();
		if (string == null)
			string = ""; //$NON-NLS-1$
		String strings[] = string
				.split("\\,"); 		//$NON-NLS-1$);
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].trim().length() > 0)
				strList.add(strings[i].trim());
		}
		return strList;
	}
	
	public boolean isQualifiedRefernce(EReference ref) {
		return getQualifiedReferences().contains(ref);
	}
	
	public Set<EReference> getQualifiedReferences() {
		if (qualifiedReferences == null) {
			qualifiedReferences = new LinkedHashSet<EReference>();
		}
		String idStrValue = getRteNameMap().get(_referenceQualifiers);
		String nameStrValue = getRteNameMap().get(_referenceQualifierNames);
		if (idStrValue == null || idStrValue.trim().length() == 0) {
			qualifiedReferencesLoaded = false;
			if (!qualifiedReferences.isEmpty()) {
				qualifiedReferences.clear();
			}
		} else if (!qualifiedReferencesLoaded) {
			if (!qualifiedReferences.isEmpty()) {
				qualifiedReferences.clear();
			}
			List<String> refIds = convertStringsToList(idStrValue);
			List<String> refNames = convertStringsToList(nameStrValue);
			if (refIds.size() != refNames.size()) {
				refNames = refIds;
			}
			getReferenceQualifiedNameToIdMap().clear();
			getReferenceQualifiedIdToNameMap().clear();
			for (int i = 0; i < refIds.size(); i++) {
				String refId = QReference_ + refIds.get(i);
				String refName = refNames.get(i);
				getReferenceQualifiedNameToIdMap().put(refName, refId);
				getReferenceQualifiedIdToNameMap().put(refId, refName);
//				EReference ref = EcoreFactory.eINSTANCE.createEReference();
//				ref.setName(refId);
				EReference ref = UmaUtil.createReference(refId);
				qualifiedReferences.add(ref);
			}
			qualifiedReferencesLoaded = true;
		}
		return qualifiedReferences;
	}
	
	private Map<String, String>  getReferenceQualifiedNameToIdMap() {
		if (referenceQualifiedNameToIdMap == null) {
			referenceQualifiedNameToIdMap = new HashMap<String, String>();
		}
		return referenceQualifiedNameToIdMap;
	}

	private Map<String, String>  getReferenceQualifiedIdToNameMap() {
		if (referenceQualifiedIdToNameMap == null) {
			referenceQualifiedIdToNameMap = new HashMap<String, String>();
		}
		return referenceQualifiedIdToNameMap;
	}
	
	public String getReferenceQualifierName(String referenceQualifierId) {
		getQualifiedReferences();
		return getReferenceQualifiedIdToNameMap().get(referenceQualifierId);		
	}
		
	public String getReferenceQualifierId(String referenceQualifierName) {
		getQualifiedReferences();
		return getReferenceQualifiedNameToIdMap().get(referenceQualifierName);		
	}
	
	public String[] getReferenceQualifierNames() {
		getQualifiedReferences();
		Set<String> nameSet = getReferenceQualifiedNameToIdMap().keySet();
		List<String> nameList = new ArrayList<String>(nameSet);
		Collections.sort(nameList);
		return nameList.toArray(new String[0]);
	}
	
}
