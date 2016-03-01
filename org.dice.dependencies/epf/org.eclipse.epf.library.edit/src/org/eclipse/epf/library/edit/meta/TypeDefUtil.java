//------------------------------------------------------------------------------
// Copyright (c) 2005, 2012 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.meta.internal.ExtendedReferenceImpl;
import org.eclipse.epf.library.edit.meta.internal.ExtendedSectionImpl;
import org.eclipse.epf.library.edit.meta.internal.ModifiedTypeMetaImpl;
import org.eclipse.epf.library.edit.meta.internal.TypeDefParserImpl;
import org.eclipse.epf.library.edit.uma.DescriptorExt;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.library.edit.uma.MethodElementExt.MethodConfigurationExt;
import org.eclipse.epf.library.edit.uma.MethodElementExt.WorkProductStateExt;
import org.eclipse.epf.library.edit.uma.MethodPluginExt;
import org.eclipse.epf.library.edit.util.DebugUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ExtendedSection;
import org.eclipse.epf.uma.util.MetaElement;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.QualifiedReference;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

/**
 * @author Weiping Lu
 */
public class TypeDefUtil {

	private static TypeDefUtil instance;
	
	static {
		Object obj = ExtensionHelper.create(TypeDefUtil.class, null);
		if (obj instanceof TypeDefUtil) {
			instance = (TypeDefUtil) obj;
		} else {
			instance = new TypeDefUtil();
		}
	}
	
	public static TypeDefUtil getInstance() {
		return instance;
	}

	protected TypeDefUtil() {		
	}
	
	public TypeDefParser getTypeDefParser() {
		return new TypeDefParserImpl();
	}
	
	public IMetaDef createMetaDef(Class cls) {
		if (cls == ModifiedTypeMeta.class) {
			return (ModifiedTypeMetaImpl) newModifiedTypeMeta();
		}
		return null;
	}
	
	public void associate(ExtendedReference eRef, EReference ref) {
		associate_(eRef, ref);
	}
	
	public void associate(ExtendedAttribute eAtt, EAttribute att) {
		associate_(eAtt, att);
	}
	
	private void associate_(MetaElement metaElement, EStructuralFeature feature) {
		if (! (metaElement instanceof Adapter)) {
			return;
		}
		int sz = feature.eAdapters().size();
		for (int i = sz - 1; i >=0; i--) {
			Object adapter = feature.eAdapters().get(i);
			if (adapter instanceof MetaElement) {
				feature.eAdapters().remove(i);
			}
		}
		feature.eAdapters().add((Adapter) metaElement);
	}
	
	public ExtendedReference getAssociatedExtendedReference(EStructuralFeature feature) {
		MetaElement element = getAssociatedMetaElement(feature);
		if (element instanceof ExtendedReference) {
			return (ExtendedReference) element;
		}
		return null;
	}

	public ExtendedAttribute getAssociatedExtendedAttribute(EStructuralFeature feature) {
		MetaElement element = getAssociatedMetaElement(feature);
		if (element instanceof ExtendedAttribute) {
			return (ExtendedAttribute) element;
		}
		return null;
	}
	
	private MetaElement getAssociatedMetaElement(EStructuralFeature feature) {
		if (feature == null) {
			return null;
		}
		for (Object adapter : feature.eAdapters()) {
			if (adapter instanceof MetaElement) {
				return (MetaElement) adapter;
			}
		}
		return null;
	}
		
	public Object eGet(EObject obj, EStructuralFeature feature) {
		return eGet(obj, feature, false);
	}
	
	public Object eGet(EObject obj, EStructuralFeature feature, boolean toModify) {
		try {
			return eGet_(obj, feature, toModify);			
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);			
			if (DebugUtil.udtDebug || DebugUtil.uiDebug) {
				e.printStackTrace();
			}
		}
		boolean isMany = false;
		if (feature instanceof EReference) {
			isMany = ((EReference) feature).isMany();
		}
		return isMany ? new ArrayList() : null;
	}
	
	private Object eGet_(EObject obj, EStructuralFeature feature, boolean toModify) {
		if (obj == null) {
			return null;
		}		
		if (obj instanceof MethodElement) {
			PropUtil propUtil = PropUtil.getPropUtil();
			MethodElement element = (MethodElement) obj;
			if (feature instanceof EReference) {
				EReference ref = (EReference) feature;
				if (element instanceof Practice) {
					PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
					Practice practice = (Practice) element;
					UserDefinedTypeMeta meta = practicePropUtil.getUdtMeta(practice);
					if (meta != null && meta.isQualifiedRefernce((EReference) feature)) {
						List list = practicePropUtil.getQReferenceListById(element,
								feature.getName(), false);
						return list;
					}
				}								
				if (ref == UmaUtil.MethodElement_UdtList) {
					return propUtil.getUdtList(element, toModify);
				}
				ExtendedReference eRef = getAssociatedExtendedReference(ref);
				if (eRef != null) {
					return propUtil.getExtendedReferenceList(element, eRef, toModify);
				}
			} else if (feature instanceof EAttribute && element instanceof ContentDescription) {
				EAttribute att = (EAttribute) feature;
				ExtendedAttribute eAtt = getAssociatedExtendedAttribute(att);
				if (eAtt != null) {
					return propUtil.getExtendedAttribute((ContentDescription) element, eAtt);
				}
			}
		}
		return obj.eGet(feature);
	}
	
	
	public void eSet(EObject obj, EStructuralFeature feature, Object newValue) {
		try {
			eSet_(obj, feature, newValue);			
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
			if (DebugUtil.udtDebug || DebugUtil.uiDebug) {
				e.printStackTrace();
			}
		}		
	}
	
	private void eSet_(EObject obj, EStructuralFeature feature, Object newValue) {
		if (obj instanceof ContentDescription) {
			ExtendedAttribute eAtt = getAssociatedExtendedAttribute(feature);
			if (eAtt != null && (newValue == null || newValue instanceof String) ) {
				PropUtil.getPropUtil().setExtendedAttribute((ContentDescription) obj, eAtt, (String) newValue);
				return;
			}
		}
		obj.eSet(feature, newValue);
	}
		
	public void eBasicSet(EObject obj, EStructuralFeature feature, Object newValue) {
		boolean oldB = obj.eDeliver();
		try {
			if (oldB) {
				obj.eSetDeliver(false);
			}
			eSet(obj, feature, newValue);
		} finally {
			if (oldB) {
				obj.eSetDeliver(true);
			}
		}
	}
	
	public List<EReference> getEAllReferences(MethodElement element) {
		List<EReference> list = new ArrayList<EReference>(element.eClass().getEAllReferences());
		
		if (element instanceof Practice) {
			PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
			Practice practice = (Practice) element;
			UserDefinedTypeMeta meta = practicePropUtil.getUdtMeta(practice);
			if (meta != null && !meta.getQualifiedReferences().isEmpty()) {
				list.addAll(meta.getQualifiedReferences());
			}
		}
		
		PropUtil propUtil = PropUtil.getPropUtil();	
		
		list.add(UmaUtil.MethodElement_UdtList);
							
		ModifiedTypeMeta meta = propUtil.getGlobalMdtMeta(element);
		if (meta != null) {
			list = new ArrayList<EReference>(list);
			for (ExtendedReference eRef : meta.getReferences()) {
				list.add(eRef.getReference());
				for (QualifiedReference qRef : eRef.getQualifiedReferences()) {
					list.add(qRef.getReference());
				}
			}
		}
		return list;
	}
	
	public List<EAttribute> getEAllAttributes(MethodElement element) {
		List<EAttribute> list =new ArrayList<EAttribute>(element.eClass().getEAllAttributes());
		
		if (! (element instanceof ContentDescription)) {
			return list;
		}
					
		EObject owner = element.eContainer();
		if (! (owner instanceof MethodElement)) {
			return list;
		}
		
		PropUtil propUtil = PropUtil.getPropUtil();	
		ModifiedTypeMeta meta = propUtil.getGlobalMdtMeta((MethodElement) owner);
		if (meta == null) {
			return list;
		}
		for (ExtendedAttribute eAtt : meta.getRtes()) {
			list.add(eAtt.getAttribute());
		}
		for (ExtendedAttribute eAtt : meta.getAttributes()) {
			list.add(eAtt.getAttribute());
		}
		return list;
	}
	
	public MethodElementExt createExtendObject(MethodElement element) {
		PropUtil propUtil = PropUtil.getPropUtil();
		if (propUtil.isWorkProductState(element)) {
			return new WorkProductStateExt((Constraint) element);
		}
		if (element instanceof Descriptor) {
			return new DescriptorExt((Descriptor) element);
		}
		if (element instanceof MethodPlugin) {
			return new MethodPluginExt((MethodPlugin) element);
		}
		if (element instanceof MethodConfiguration) {
			return new MethodConfigurationExt((MethodConfiguration) element);
		}
		return new MethodElementExt(element);
	}
		
	public static Class getSuperClass(Class cls) {
		Class cls1 = ContentElement.class;
		Class cls2 = BreakdownElement.class;
		Class[] ins = cls.getInterfaces();
		if (ins == null || ins.length == 0) {
			return null;
		}
		for (Class in : ins) {
			if (cls1.isAssignableFrom(in) || cls2.isAssignableFrom(in)) {
				return in;
			}
		}
		return null;
	}	
	
	public static ModifiedTypeMeta getMdtMeta(EClass cls) {
		try {
			MethodElement element = (MethodElement) UmaFactory.eINSTANCE
					.create(cls);
			return getMdtMeta(element);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ModifiedTypeMeta getMdtMeta(MethodElement element) {		
		return PropUtil.getPropUtil().getGlobalMdtMeta(element);
	}
	
	public static ModifiedTypeMeta getLinkedMdtMeta(MethodElement element) {		
		ModifiedTypeMeta meta = getMdtMeta(element);
		if (meta == null) {
			return null;
		}
		Collection<ModifiedTypeMeta> allTypes = LibraryEditUtil.getInstance().getModifiedTypes();
		if (allTypes == null) {
			return null;
		}
		for (ModifiedTypeMeta oMeta : allTypes) {
			if (! oMeta.getLinkTypes().isEmpty() && meta.getId().equals(oMeta.getLinkTypes().get(0))) {
				return oMeta;
			}
		}
		return null;
	}
	
	public static boolean hasLinkTypes(MethodElement element) {
		ModifiedTypeMeta meta = getMdtMeta(element);
		if (meta == null) {
			return false;
		}
		return meta.getLinkTypes() != null && ! meta.getLinkTypes().isEmpty();
	}
	
	public static String getTypeId(MethodElement element) {
		if (element instanceof Practice) {
			UserDefinedTypeMeta meta = PracticePropUtil.getPracticePropUtil().getUdtMeta((Practice) element);
			if (meta != null) {
				return meta.getRteNameMap().get(UserDefinedTypeMeta._typeId);
			}
		}
		return element.eClass().getInstanceClass().getName();
	}
	
	public static boolean isTypeOf(MethodElement element, String typeId) {
		String elementTypeId = getTypeId(element);
		if (elementTypeId.equals(typeId)) {
			return true;
		}		
		for (EClass cls : element.eClass().getEAllSuperTypes()) {
			Class c = cls.getInstanceClass();
			if (c.getName().equals(typeId)) {
				return true;
			}
		}		
		return false;
	}
	
	public void validateId(String context, String id) throws TypeDefException {
		String errorMsg = checkIdValue(context, id);
		if (errorMsg != null) {
			throw new TypeDefException(errorMsg);
		}		
	}
	
	public String checkIdValue(String context, String id) {		
		if (id == null || id.trim().length() == 0) {
			return "Invalid id string for " + context + ": id cannot be null or empty!";//$NON-NLS-1$	//$NON-NLS-2$	
		}
		char c = id.charAt(0);
		if (! (c == '_' || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) {
			return "Invalid id string for " + context + ": " + id + "\nid must start with an letter or underscore";//$NON-NLS-1$	//$NON-NLS-2$//$NON-NLS-3$		
		}		
		
		char lastC = c;
		for (int i = 1 ; i < id.length(); i++) {
			c = id.charAt(i);			
			boolean valid = (c == '_' || c == '-' || c == '.' || c >= '0' && c <= '9' || (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'));
			if (c == '.' && lastC == c) {
				valid = false;
			}
			if (! valid) {
				return "Invalid id string for " + context + ": " + id + "\nid can only contain letters, digits, underscores, hyphens, and single periods";//$NON-NLS-1$	//$NON-NLS-2$//$NON-NLS-3$		
			}
			lastC = c;
		}
		return null;
	}
	
	public ModifiedTypeMeta newModifiedTypeMeta() {
		return new ModifiedTypeMetaImpl();
	}
	
	public ExtendedReference newExtendedReference(MetaElement parent) {
		return new ExtendedReferenceImpl(parent);
	}
	
	public ExtendedSection newExtendedSection(MetaElement parent) {
		return new ExtendedSectionImpl(parent);
	}
	
	private static String indent0 = "     ";  //$NON-NLS-1$
	public static String getMTypesDebugString(Collection<ModifiedTypeMeta> mtypes, String label) {
		List<ModifiedTypeMeta> list = new ArrayList<ModifiedTypeMeta>();
		if (mtypes != null) {
			list.addAll(mtypes);
		}
		Collections.sort(list);
				
		StringBuffer sb = new StringBuffer();
		if (label == null) {
			label = "";
		}
		sb.append(label + "\n"); 	//$NON-NLS-1$
		sb.append("size: " + list.size() + "\n"); 	//$NON-NLS-1$
		if (list.isEmpty()) {
			return sb.toString();
		}
		for (int i = 0; i < list.size(); i++) {
			ModifiedTypeMeta meta = list.get(i);
			sb.append(meta.getDebugString(i, indent0));
		}
				
		return sb.toString();
	}
	
	public ExtendedOpposite getAssociatedExtendedOpposite(OppositeFeature oFeature) {
		if (oFeature == null) {
			return null;
		}
		ExtendedReference extendedReference = getAssociatedExtendedReference(oFeature.getTargetFeature());
		return extendedReference == null ? null : extendedReference.getOpposite();
	}
	
	public MethodElement getLinkedElement(MethodElement element) {
		if (element instanceof Descriptor) {
			return ProcessUtil.getAssociatedElement((Descriptor) element);
		}
		return PropUtil.getPropUtil().getLinkedElement(element);
	}
	
}
