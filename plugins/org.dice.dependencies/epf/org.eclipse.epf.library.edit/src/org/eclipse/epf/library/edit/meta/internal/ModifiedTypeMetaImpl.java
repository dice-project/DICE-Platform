package org.eclipse.epf.library.edit.meta.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedFeature;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ExtendedSection;
import org.eclipse.epf.uma.util.ExtendedTable;
import org.eclipse.epf.uma.util.MetaElement;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.w3c.dom.Element;

public class ModifiedTypeMetaImpl extends MetaElementImpl implements ModifiedTypeMeta {
		
	private List<ExtendedReference> references;
	private List<ExtendedAttribute> rtes;
	private List<ExtendedAttribute> attributes;

	private List<ExtendedSection> sections;
	private List<ExtendedSection> referenceSections;
	private List<ExtendedSection> rteSections;
	private List<ExtendedSection> attributeSections;
	private List<ExtendedTable> tables;
	
	private List<String> linkTypes;

	public ModifiedTypeMetaImpl() {
		super(null);
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
	
	@Override
	public boolean isSuppressed() {
		return false;
	}
	
	public List<ExtendedSection> getSections() {
		if (sections == null) {
			sections = new ArrayList<ExtendedSection>();
		}
		return sections;
	}
	
	public List<ExtendedSection> getReferenceSections() {
		if (referenceSections == null) {
			referenceSections = new ArrayList<ExtendedSection>();
		}
		return referenceSections;
	}
	
	@Deprecated
	public List<ExtendedSection> getRteSections() {
		if (rteSections == null) {
			rteSections = new ArrayList<ExtendedSection>();
		}
		return rteSections;
	}
	
	public List<ExtendedSection> getAttributeSections() {
		if (attributeSections == null) {
			attributeSections = new ArrayList<ExtendedSection>();
		}
		return attributeSections;
	}
	
	public List<ExtendedTable> getTables() {
		if (tables == null) {
			tables = new ArrayList<ExtendedTable>();
		}
		return tables;
	}
		
	public List<String> getLinkTypes() {
		if (linkTypes == null) {
			linkTypes = new ArrayList<String>();
		}
		return linkTypes;
	}
	
	public void parseElement(Element element)	throws TypeDefException {		
		super.parseElement(element);
				
		getSections().clear();
		getLinkTypes().clear();
		
		List<Element> linkTypeElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.linkType);
		if (linkTypeElements != null) {
			for (Element linkTypeElement : linkTypeElements) {
				String linkType = linkTypeElement.getAttribute(IMetaDef.ID);
				getLinkTypes().add(linkType);
			}
		}
		
		List<Element> sectionElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.SECTION);
		if (sectionElements != null) {
			for (Element sElement : sectionElements) {
				ExtendedSectionImpl ses = (ExtendedSectionImpl) TypeDefUtil.getInstance().newExtendedSection(this);			
				ses.parseElement(sElement);				
				getSections().add(ses);
			}
		}
		init();
	}
	
	@Override
	public boolean processInheritance() {
		if (! super.processInheritance()) {
			return false;
		}
		
		if (getSuperMeta() == null) {
			int sz = getSections().size();
			sections = (List<ExtendedSection>) processSuppress(this.getSections());
			if (sz != sections.size()) {
				init();
			}
			return true;
		} 
		
		if (getSuperMeta() instanceof ModifiedTypeMetaImpl){
			ModifiedTypeMetaImpl sMeta = (ModifiedTypeMetaImpl) getSuperMeta();
			sMeta.processInheritance();			
			sections = (List<ExtendedSection>) processInherentList(this.getSections(), sMeta.getSections());
			for (ExtendedSection section : getSections()) {
				section.processInheritance();
			}
			init();
			return true;
		}
		
		return true;
	}

	private void init() {
		getReferences().clear();
		getRtes().clear();
		getAttributes().clear();
		getReferenceSections().clear();
		getRteSections().clear();
		getAttributeSections().clear();
		getTables().clear();

		for (ExtendedSection section : getSections()) {
			if (IMetaDef.REFERENCE.equals(section.getType())) {
				getReferenceSections().add(section);
				getReferences().addAll(section.getReferences());
				getTables().addAll(section.getTables());

			} else if (IMetaDef.RTE.equals(section.getType())) {
				getRteSections().add(section);
				getRtes().addAll(section.getRtes());
				
			} else if (IMetaDef.ATTRIBUTE.equals(section.getType())) {
				getAttributeSections().add(section);
				getAttributes().addAll(section.getAttributes());
			}
		}
	}
	
	public boolean processLink(ModifiedTypeMeta linkedMeta) {
		if (linkedMeta == null) {
			return false;
		}
		getSections().addAll(linkedMeta.getSections());
		getReferences().addAll(linkedMeta.getReferences());
		getRtes().addAll(linkedMeta.getRtes());
		getAttributes().addAll(linkedMeta.getAttributes());
		getReferenceSections().addAll(linkedMeta.getReferenceSections());;
		getRteSections().addAll(linkedMeta.getRteSections());
		getAttributeSections().addAll(linkedMeta.getAttributeSections());
		if (linkedMetaElements == null) {
			linkedMetaElements = new HashSet<MetaElement>();
		}
		linkedMetaElements.addAll(linkedMeta.getReferences());
		linkedMetaElements.addAll(linkedMeta.getRtes());
		linkedMetaElements.addAll(linkedMeta.getAttributes());
		linkedMetaElements.addAll(linkedMeta.getSections());
		return true;
	}
	
	private Set<MetaElement> linkedMetaElements;
	public boolean isLinkedFeature(ExtendedFeature feature) {
		return linkedMetaElements == null ? false : linkedMetaElements.contains(feature);
	}
	
	public boolean isLinkedSection(ExtendedSection section) {
		return linkedMetaElements == null ? false : linkedMetaElements.contains(section);
	}
	
	@Override
	protected void getDebugStringImpl(StringBuffer sb, String indent) {
		super.getDebugStringImpl(sb, indent);
		sb.append(indent + "sections: " + sections.size() + "\n");
		for (int i = 0; i < sections.size(); i++) {
			ExtendedSection meta = sections.get(i);
			sb.append(meta.getDebugString(i, indent + indent));
		}
		sb.append(indent + "linkTypes: " + linkTypes.size() + "\n");
		for (int i = 0; i < linkTypes.size(); i++) {
			sb.append(indent + indent + linkTypes.get(i) + "\n");
		}		
		sb.append("\n");		
	}
	
	private static ExtendedAttribute nullExtendedAttributeValue = new ExtendedAttributeImpl(null);
	private Map<String, ExtendedAttribute> extendedAttributeMap = new HashMap<String, ExtendedAttribute>();	
	public ExtendedAttribute getExtendedAttribute(String globalId) {
		if (globalId == null) {
			return null;
		}
		ExtendedAttribute value = extendedAttributeMap.get(globalId);
		if (value == nullExtendedAttributeValue) {
			return null;
		}
		if (value != null) {
			return value;
		}
		for (ExtendedAttribute att : getAttributes()) {
			if (globalId.equals(att.getGlobalId())) {
				value = att;
				break;
			}
		}
		if (value == null) {
			value = nullExtendedAttributeValue;
		} 
		extendedAttributeMap.put(globalId, value);		
		return value == nullExtendedAttributeValue ? null : value;
	}
	
}
