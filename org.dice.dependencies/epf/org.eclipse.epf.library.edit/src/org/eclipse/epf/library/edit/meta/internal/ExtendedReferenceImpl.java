package org.eclipse.epf.library.edit.meta.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.MetaElement;
import org.eclipse.epf.uma.util.QualifiedReference;
import org.eclipse.epf.uma.util.UmaUtil;
import org.w3c.dom.Element;

public class ExtendedReferenceImpl extends MetaElementImpl implements ExtendedReference {
	private EReference ref;	

	private List<QualifiedReference> qualifiedReferences;
	
	private List<String> valueTypes;
	
	private String contributeTo;
	
	private ExtendedOppositeImpl opposite;

	public ExtendedReferenceImpl(MetaElement parent) {
		super(parent);
	}
	
	public EReference getReference() {
		return ref;
	}
	
	protected void setReference(EReference ref) {
		this.ref = ref;
	}
	
	public List<QualifiedReference> getQualifiedReferences() {
		return qualifiedReferences;
	}
	
	public void parseElement(Element element)	throws TypeDefException {
		super.parseElement(element);
		if (element == null) {
			return;
		}
		ref =  UmaUtil.createReference(getId());
		TypeDefUtil.getInstance().associate(this, ref);
		
		valueTypes = XMLUtil.getChildTextsByTagName(element, IMetaDef.valueType);
		contributeTo = element.getAttribute(IMetaDef.contributeTo);
		if (contributeTo != null) {
			contributeTo = contributeTo.toLowerCase();
		}
		if (Roles.equals(contributeTo)) {
			valueTypes = Collections.singletonList(UmaPackage.eINSTANCE.getRole().getInstanceClass().getName());
		} else if (WorkProducts.equals(contributeTo)) {
			valueTypes = Collections.singletonList(UmaPackage.eINSTANCE.getWorkProduct().getInstanceClass().getName());			
		} else {
			contributeTo = null;
		}
		
		Element childElement = XMLUtil.getFirstChild(element, IMetaDef.oppositeReference);
		if (childElement != null) {
			opposite = new ExtendedOppositeImpl(this);
			opposite.parseElement(childElement);
		}
		
		qualifiedReferences = new ArrayList<QualifiedReference>();
		List<Element> rqElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.REFERENCE_QUALIFIERS);
		if (rqElements == null || rqElements.isEmpty()) {
			return;
		}
		for (Element rqElement : rqElements) {
			List<Element> qElements = XMLUtil.getChildElementsByTagName(rqElement, IMetaDef.QUALIFIER);
			for (Element qElement : qElements) {
				QualifiedReferenceImpl q = new QualifiedReferenceImpl(this);
				q.parseElement(qElement);
				qualifiedReferences.add(q);
			}
		}
	}
	
	public List<String> getValueTypes() {		
		return valueTypes;
	}
	
	public String getContributeTo() {
		return contributeTo;
	}
	
	@Override
	public boolean processInheritance() {
		if (! super.processInheritance()) {
			return false;
		}		
		
		if (getSuperMeta() == null) {
			qualifiedReferences = (List<QualifiedReference>) processSuppress(this.getQualifiedReferences());
			return true;
			
		} 
		
		if (getSuperMeta() instanceof ExtendedReferenceImpl) {			
			ExtendedReferenceImpl sMeta = (ExtendedReferenceImpl) getSuperMeta();
			sMeta.processInheritance();			
			qualifiedReferences = (List<QualifiedReference>) processInherentList(this.getQualifiedReferences(), sMeta.getQualifiedReferences());
			for (QualifiedReference qref : getQualifiedReferences()) {
				qref.processInheritance();
			}
			return true;
			
		}
		return true;
	}
	
	public ExtendedOpposite getOpposite() {
		return opposite;
	}
	
}
