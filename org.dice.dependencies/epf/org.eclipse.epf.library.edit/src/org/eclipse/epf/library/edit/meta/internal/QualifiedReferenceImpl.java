package org.eclipse.epf.library.edit.meta.internal;

import java.util.List;

import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.uma.ExtendReferenceMap;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.MetaElement;
import org.eclipse.epf.uma.util.QualifiedReference;
import org.w3c.dom.Element;

public class QualifiedReferenceImpl extends ExtendedReferenceImpl implements QualifiedReference {
	
	/**
	 * Validation note:
	 * (1) Uniqueness of names and id
	 * (2) Reserved key words
	 * (3) Forbidden chars
	 */
	public QualifiedReferenceImpl(MetaElement parent) {
		super(parent);
	}
	
	public void parseElement(Element element)	throws TypeDefException {
		super.parseElement(element);
		if (element == null) {
			return;
		}
		String name = element.getTextContent();
		setName(name);		
	}
	
	@Override
	public boolean processInheritance() {
		if (! super.processInheritance()) {
			return false;
		}		
		
		return true;
	}
	
}
