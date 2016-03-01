package org.eclipse.epf.library.edit.meta.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.MetaElement;
import org.eclipse.epf.uma.util.QualifiedReference;
import org.eclipse.epf.uma.util.UmaUtil;
import org.w3c.dom.Element;

public class ExtendedAttributeImpl  extends MetaElementImpl implements ExtendedAttribute {

	private EAttribute att;	
	private String valueType;
	private List<MetaElement> choiceValues;	
	
	public ExtendedAttributeImpl(MetaElement parent) {
		super(parent);
	}

	public EAttribute getAttribute() {
		return att;
	}
	
	public String getValueType() {
		return valueType;
	}
	
	public void parseElement(Element element)	throws TypeDefException {
		super.parseElement(element);			
		att =  UmaUtil.createAttribute(getId());
		TypeDefUtil.getInstance().associate(this, att);			
		valueType = element.getAttribute(IMetaDef.valueType);
		if (valueType == null || valueType.length() == 0) {
			valueType = IMetaDef.richText;
		}
		if (IMetaDef.choice.equalsIgnoreCase(valueType)) {
			choiceValues = new ArrayList<MetaElement>();
			List<Element> valueElements = XMLUtil.getChildElementsByTagName(element, IMetaDef.value);
			if (valueElements == null || valueElements.isEmpty()) {
				return;
			}
			for (Element valueElement : valueElements) {
				MetaElementImpl value = new MetaElementImpl(this);
				value.parseElement(valueElement);
				choiceValues.add(value);
			}
		}
	}
		
	public List<MetaElement> getChoiceValues() {
		return choiceValues;
	}
	
}
