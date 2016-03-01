package org.eclipse.epf.library.edit.meta.internal;

import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.MetaElement;
import org.w3c.dom.Element;

public class ExtendedOppositeImpl  extends MetaElementImpl implements ExtendedOpposite  {

	private boolean publish;
	private OppositeFeature oFeature;
	
	public ExtendedOppositeImpl(MetaElement parent) {
		super(parent);
	}

	public ExtendedReference getTargetReference() {
		return (ExtendedReference) getParent();
	}

	public void parseElement(Element element) throws TypeDefException {
		super.parseElement(element);	
		if (element == null) {
			return;
		}
		if (super.publish()) {	//Default value for super is true, but we want it to be false here
			String str = element.getAttribute(IMetaDef.publish);
			publish = str == null ? true : Boolean.parseBoolean(str.trim());
		}
	}
		
	public boolean publish() {
		return publish;
	}
	
	public OppositeFeature getOFeature() {
		if (oFeature == null) {
			ExtendedReference eRef = getTargetReference();
			if (eRef != null) {
				oFeature = new OppositeFeature(MethodElement.class, getName(), eRef.getReference(), true);
			}
		}
		return oFeature;
	}
	
}
