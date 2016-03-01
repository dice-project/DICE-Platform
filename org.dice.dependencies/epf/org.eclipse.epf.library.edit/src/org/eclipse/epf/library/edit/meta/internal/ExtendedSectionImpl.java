package org.eclipse.epf.library.edit.meta.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ExtendedSection;
import org.eclipse.epf.uma.util.ExtendedTable;
import org.eclipse.epf.uma.util.MetaElement;
import org.w3c.dom.Element;

public class ExtendedSectionImpl  extends MetaElementImpl implements ExtendedSection {

	private String type;
	private List<ExtendedReference> references;
	private List<ExtendedAttribute> rtes;
	private List<ExtendedAttribute> attributes;
	private List<ExtendedTable> tables;
	
	public ExtendedSectionImpl(MetaElement parent) {
		super(parent);
	}
	
	public String getType() {
		return type;
	}

	public List<ExtendedReference> getReferences() {
		if (references == null) {
			references = new ArrayList<ExtendedReference>();
		}
		return references;
	}
	
	@Deprecated
	public List<ExtendedAttribute> getRtes() {
		if (rtes == null) {
			rtes = new ArrayList<ExtendedAttribute>();
		}
		return rtes;
	}
	
	public List<ExtendedAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new ArrayList<ExtendedAttribute>();
		}
		return attributes;
	}
	
	public List<ExtendedTable> getTables() {
		if (tables == null) {
			tables = new ArrayList<ExtendedTable>();
		}
		return tables;
	}
	
	public void parseElement(Element element)	throws TypeDefException {
		super.parseElement(element);			
		type = element.getAttribute(IMetaDef.type);
		if (IMetaDef.RTE.equals(type)) {
			type = IMetaDef.ATTRIBUTE;
		}
		
		getReferences().clear();
		List<Element> referenceElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.REFERENCE);
		if (referenceElements != null) {
			for (Element rElement : referenceElements) {
//				ExtendedReferenceImpl ref = new ExtendedReferenceImpl(this);
				ExtendedReferenceImpl ref = (ExtendedReferenceImpl) TypeDefUtil.getInstance().newExtendedReference(this);			
				ref.parseElement(rElement);
				getReferences().add(ref);
			}
		}
		
		getAttributes().clear();
//		getRtes().clear();
		List<Element> rteElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.RTE);
		if (rteElements != null) {
			for (Element rElement : rteElements) {
				ExtendedAttributeImpl rte = new ExtendedAttributeImpl(this);
				rte.parseElement(rElement);
				getAttributes().add(rte);
			}
		}
		
//		getAttributes().clear();
		List<Element> attributeElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.ATTRIBUTE);
		if (attributeElements != null) {
			for (Element aElement : attributeElements) {
				ExtendedAttributeImpl att = new ExtendedAttributeImpl(this);
				att.parseElement(aElement);
				getAttributes().add(att);
			}
		}
		
		getTables().clear();
		List<Element> tableElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.TABLE);
		if (tableElements != null) {
			for (Element tElement : tableElements) {
				ExtendedTableImpl table = new ExtendedTableImpl(this);
				table.parseElement(tElement);
				getTables().add(table);
			}
		}
	}

	@Override
	public boolean processInheritance() {
		if (! super.processInheritance()) {
			return false;
		}
		
		if (getSuperMeta() == null) {
			references = (List<ExtendedReference>) processSuppress(this.getReferences());
			rtes = (List<ExtendedAttribute>) processSuppress(this.getRtes());
			return true;

		} 
		
		if (getSuperMeta() instanceof ExtendedSectionImpl){
			ExtendedSectionImpl sMeta = (ExtendedSectionImpl) getSuperMeta();
			sMeta.processInheritance();
			
			references = (List<ExtendedReference>) processInherentList(this.getReferences(), sMeta.getReferences());
			for (ExtendedReference ref : getReferences()) {
				ref.processInheritance();
			}
			
			rtes = (List<ExtendedAttribute>) processInherentList(this.getRtes(), sMeta.getRtes());
			for (ExtendedAttribute att : getRtes()) {
				att.processInheritance();
			}
			return true;
		} 
		
		return true;
	}
	
}
