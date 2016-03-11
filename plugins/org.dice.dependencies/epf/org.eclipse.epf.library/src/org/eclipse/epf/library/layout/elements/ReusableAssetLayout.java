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
package org.eclipse.epf.library.layout.elements;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * The element layout for a Reusable Asset.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class ReusableAssetLayout extends AbstractElementLayout {

	public ReusableAssetLayout() {
		super();
	}

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}

	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		XmlElement elementXml = super.getXmlElement(includeReferences);

		if (includeReferences) {
			List contentElements = ConfigurationHelper.calc0nFeatureValue(super.element,
					AssociationHelper.ReusableAsset_ContentElements,
					layoutManager.getElementRealizer());
			
			List activities = ConfigurationHelper.calc0nFeatureValue(super.element,
					AssociationHelper.ReusableAsset_BreakdownElements, layoutManager
							.getElementRealizer());

//			contentElements.addAll(activities);
			contentElements = addBreakdownElementsToContentElements(contentElements, activities);

			addReferences(AssociationHelper.ReusableAsset_ContentElements, elementXml, "contentElements", contentElements); //$NON-NLS-1$

			Guidance guidance = (Guidance) super.element;
			ContentDescription content = (ContentDescription) guidance
					.getPresentation();
			if (content != null) {
				
				// Authoring:  Template attachments of a contributing template guidance element are not appended to the base list of attachments				
				//String attachmentString = ((org.eclipse.epf.uma.GuidanceDescription)content).getAttachments();
				EStructuralFeature feature = UmaPackage.eINSTANCE.getGuidanceDescription_Attachments();
				String attachmentString = (String)ConfigurationHelper.calcAttributeFeatureValue(content, super.element, feature, super.layoutManager.getConfiguration());
				if ( (attachmentString != null) && (attachmentString.indexOf(ConfigurationHelper.ATTRIBUTE_VALUE_SEPERATOR) > 0) )
				{
					attachmentString = attachmentString.replaceAll(ConfigurationHelper.ATTRIBUTE_VALUE_SEPERATOR, TngUtil.GUIDANCE_FILESTRING_SEPARATOR); 
				}
				List attachmentList = TngUtil.convertGuidanceAttachmentsToList(attachmentString);
				for (Iterator iter = attachmentList.iterator();iter.hasNext();) {
					String attachmentFile = (String) iter.next();
					if (attachmentFile != null) {
						Matcher m = ResourceHelper.p_template_attachment_url.matcher(attachmentFile);
						if (!m.find()) {
							String fileName = FileUtil.getFileName(attachmentFile);
							elementXml.newChild("attribute").setAttribute("name", //$NON-NLS-1$ //$NON-NLS-2$
									"attachedFile").setAttribute( //$NON-NLS-1$
									"url", "file://" + attachmentFile) //$NON-NLS-1$ //$NON-NLS-2$ 
									.setAttribute("fileName", fileName); //$NON-NLS-1$
						} else {
							String fileName = m.group(2);
							elementXml.newChild("attribute").setAttribute("name", //$NON-NLS-1$ //$NON-NLS-2$
									"attachedFile").setAttribute( //$NON-NLS-1$
									"url", m.group(1)) //$NON-NLS-1$ 
									.setAttribute("fileName", fileName); //$NON-NLS-1$
						}
					}
				}
			}
}

		return elementXml;
	}


}
