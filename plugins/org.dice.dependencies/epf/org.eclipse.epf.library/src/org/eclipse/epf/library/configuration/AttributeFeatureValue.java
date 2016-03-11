//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.configuration;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.ExtendedAttribute;

/**
 * for a given method element and attribute feature, 
 * realized the feature value based on the given realizer.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class AttributeFeatureValue extends FeatureValue {

	// map of element and the value
	LinkedHashMap valueMap = new LinkedHashMap();

	/**
	 * constrctor
	 * 
	 * @param element MethodElement the element whose feature value is to be realized
	 * @param ownerElement MethodElement the owner element of this element, this is needed for handle the spacial cases 
	 * where the element's eContainer is not properly set yet. For example, when a new ContentDescrition object is created, 
	 * it's owner is not set until the content is saved.
	 * @param feature Object The featue or opposite feature whose value is to be realized
	 * @param realizer ElementRealizer the realizer used to realize the feature value.
	 */
	public AttributeFeatureValue(MethodElement element, MethodElement ownerElement, Object feature, ElementRealizer realizer) {
		super(element, ownerElement, feature, realizer);
	}
	
	/**
	 * method to add a feature value to the value list. This value could be the element's own feature value, 
	 * or the feature value of another variability element, such as a contributor. 
	 * So the owner is passed in to indicate the origination of the value.
	 * 
	 * @param owner VariabilityElement the element that provide the value due to variability realization
	 * @param value Object the feature value from the owner element
	 */
	public void add(VariabilityElement owner, Object value) {
		if ( (value == null) || (value instanceof String)
				&& value.toString().trim().length() == 0) {
			return;
		}
		
		// note: owner can be null, this is fine
		valueMap.put(owner, value);
	}

	/**
	 * return the size of the feature value list.
	 * @return int
	 */
	public int size() {
		return valueMap.size();
	}
	
	/**
	 * get the realized feature value.
	 * @return Object the realized feature value.
	 */
	public Object getValue() {
		StringBuffer buffer = new StringBuffer();
		for (Iterator it = valueMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			MethodElement e = (MethodElement)entry.getKey();
			Object v = entry.getValue();
			
			if (v == null || v.toString().length() == 0) {
				continue;
			}

			if ( isExtendReplaceEnabled() ) {
				String str = StrUtil.getPlainText(v.toString()).trim();
				if ( isBlankIndicator(str) ) {
					continue;
				}
			}
			
			if (feature == UmaPackage.eINSTANCE
					.getMethodElement_PresentationName()) {
				if (size() > 1) {
					// something wrong here, will not happen but put test
					// message here just in case
					if (debug) {
						System.out
								.println("AttributeFeatureValue: Presentation Name get more then one entry: " + LibraryUtil.getTypeName(element)); //$NON-NLS-1$
					}
				}
				return v;
			}
			ExtendedAttribute eAtt = null;
			if (feature instanceof EAttribute) {
				eAtt = TypeDefUtil.getInstance().getAssociatedExtendedAttribute((EAttribute) feature);
			}
			if (eAtt != null && eAtt.getValueType().equalsIgnoreCase(IMetaDef.attachment) || feature == UmaPackage.eINSTANCE.getGuidanceDescription_Attachments() 
					&& v instanceof String) {
				List<String> vList = TngUtil.convertGuidanceAttachmentsToList((String) v);
				for (String str: vList) {
					modifyBuffer(buffer, e, str);
				}
			} else {
				modifyBuffer(buffer, e, v);
			}
		}

		return buffer.toString();
	}

	private void modifyBuffer(StringBuffer buffer, MethodElement e, Object v) {
		if (buffer.length() > 0) {
			buffer.append(ConfigurationHelper.ATTRIBUTE_VALUE_SEPERATOR); 
		}

		if ((e == element) && !(e instanceof Section) || e == null ) {
			buffer.append(v);
		} else {
			String contentPath = ResourceHelper
					.getElementPath((e instanceof ContentDescription) 
							? (MethodElement) e.eContainer()
							: e);

			String backPath = ResourceHelper
					.getBackPath((element instanceof ContentDescription) 
							? ((ownerElement != null) ? ownerElement
							: (MethodElement) element.eContainer())
							: element);

			ExtendedAttribute eAtt = null;
			if (feature instanceof EAttribute) {
				eAtt = TypeDefUtil.getInstance().getAssociatedExtendedAttribute((EAttribute) feature);
			}
			if (eAtt != null && eAtt.getValueType().equalsIgnoreCase(IMetaDef.attachment) || feature == UmaPackage.eINSTANCE.getGuidanceDescription_Attachments()) {
				buffer.append(ResourceHelper.resolveUrl(v.toString(), contentPath, backPath));
			} else {
				buffer.append(ResourceHelper.fixContentUrlPath(v.toString(),
					contentPath, backPath));
			}
		}
	}


}
