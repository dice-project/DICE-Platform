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

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.configuration.PracticeSubgroupItemProvider;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.LayoutInfo;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.MethodElement;


/**
 * The layout for a summary page of an element.
 * <p>
 * The summarypage layout a list of elements referenced by the owner element
 * 
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public class SummaryPageLayout implements IElementLayout {

	private static final String LAYOUT_TYPE = "Summary"; //$NON-NLS-1$

	private static final String LAYOUT_XSL = "summary.xsl"; //$NON-NLS-1$

	ElementLayoutManager layoutManager;

	MethodElement element;

	List refList;

	String title;
	
	String prefix;
	
	private String typeName;

	private HtmlBuilder htmlBuilder;
	
	// String fileName;
	IElementLayout elementLayout;

//	public SummaryPageLayout(ElementLayoutManager layoutManager,
//			MethodElement element, String title, List refList) {
//		this.layoutManager = layoutManager;
//		this.element = element;
//		this.refList = refList;
//		this.title = title;
//
//		this.elementLayout = layoutManager.getLayout(element, true);
//	}
	public SummaryPageLayout(ElementLayoutManager layoutManager,
			MethodElement element, String prefix, String title, List refList) {
		this(layoutManager, element, prefix, title, refList, null);
	}
	
	public SummaryPageLayout(ElementLayoutManager layoutManager,
			MethodElement element, String prefix, String title, 
			List refList, String typeName) 
	{
		this.typeName = typeName;
		this.layoutManager = layoutManager;
		this.element = element;
		this.refList = refList;
		this.title = title;
		this.prefix = (prefix != null && prefix.length() > 0 && prefix.indexOf("null") < 0) ? prefix : "Summary"; //$NON-NLS-1$ //$NON-NLS-2$

		this.elementLayout = layoutManager.getLayout(element, true);
	}

	public String getId() {
		return element.getGuid();
	}

	public String getType() {
		return LAYOUT_TYPE;
	}

	public String getName() {
		return title;
	}

	public String getDisplayName() {
		return title;
	}

	public String getUrl() {
		return elementLayout.getFilePath()
				+ getFileName(ResourceHelper.FILE_EXT_HTML);
	}

	public String getShapeiconUrl() {
		return null;
	}

	public String getNodeiconUrl() {
		return null;
	}

	public String getDiagramiconUrl() {
		return null;
	}

	public String getXslUrl() {
		return LAYOUT_XSL;
	}

	public String getBackPath() {
		return elementLayout.getBackPath();
	}

	public String getFilePath() {
		return elementLayout.getFilePath();
	}

	public String getNoAdjustedElementPath() {
		return elementLayout.getNoAdjustedElementPath();
	}
	
	public String getFilePath(IElementLayout relativeTo) {
		return elementLayout.getFilePath(relativeTo);
	}

//	public String getFileName(String ext) {
//		return StrUtil.makeValidFileName(title + "_") + elementLayout.getFileName(ext); //$NON-NLS-1$
//
//	}
	
	public String getFileName(String ext) {
		return StrUtil.removeSpecialCharacters(prefix + "_") + elementLayout.getFileName(ext); //$NON-NLS-1$

	}

	public MethodElement getElement() {
		return element;
	}

	public ElementLayoutManager getLayoutMgr() {
		return layoutManager;
	}

	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		
		XmlElement elementXml = new XmlElement("Element") //$NON-NLS-1$
				.setAttribute("Type", getType()) //$NON-NLS-1$
				.setAttribute("Name", getName()) //$NON-NLS-1$
				.setAttribute("Url", getUrl()) //$NON-NLS-1$
				.setAttribute("BackPath", getBackPath()) //$NON-NLS-1$
				.setAttribute("ImageUrl", getShapeiconUrl()) //$NON-NLS-1$
				.setAttribute("DisplayName", getDisplayName()); //$NON-NLS-1$
		
		if (typeName != null) {
			elementXml.setAttribute("TypeName", typeName);//$NON-NLS-1$
		}

		if (includeReferences) {
			
			String copyright = ConfigurationHelper.getCopyrightText(element,
					layoutManager.getConfiguration());
			if (copyright != null && copyright.length() > 0) {
				elementXml.newChild("copyright").setValue(copyright); //$NON-NLS-1$
			}
			
			XmlElement refXml = elementXml
					.newChild("referenceList").setAttribute("name", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			for (Iterator it = refList.iterator(); it.hasNext();) {
				Object e = it.next();
				if (e instanceof MethodElement) {
					MethodElement me = (MethodElement) e;
					me = ConfigurationHelper.getCalculatedElement(me,
							layoutManager.getConfiguration());
					if (me != null) {
						IElementLayout l = layoutManager.getLayout(me, true);
						if (l != null) {
							// don't include the references of the refereced
							// elements, otherwise, may cause deadlock
							refXml.addChild(l.getXmlElement(false));
						}
					}
				} else if (e instanceof PracticeSubgroupItemProvider) {
					PracticeSubgroupItemProvider provider = (PracticeSubgroupItemProvider) e;
					IElementLayout l = new SummaryPageLayout(getLayoutMgr(),
							provider.getPractice(), provider.getPrefix(),
							provider.getText(null), (List) provider
									.getChildren(null), provider.getText(null));
					refXml.addChild(l.getXmlElement(false));
					
					//This is a short cut approach.
					//To do: generalize layoutManager to handle non method element layouts
					if (!this.layoutManager.isPublishingMode() && htmlBuilder != null) {
						htmlBuilder.generateHtml(l);						
					}
				}
			}
		}

		return elementXml;
	}

	public List getLayouts() {
		return null;
	}

	/**
	 * Returns the file name with the given extension for the given layout.
	 */
	public String getFileName(LayoutInfo info, String ext) {
		return ResourceHelper.getFileName(element, null, info.name, ext);
	}

	public void setContentTarget(MethodElement targetElement) {
	}

	public void setElementOwner(MethodElement owner) {
	}

	/**
	 * check if the html content generated from this xsl file needs to be scanned or not
	 * scan the content is for identifying element references in the content and copy over resource files
	 * in some cases we don't need to scan the content, for example, the activity breakdown structure
	 * 
	 * @param xslUrl the xsl that html is generated from, null for the default xsl layout
	 * @return boolean
	 */
	public boolean needContentScan(String xslUrl)
	{
		return false;
	}
	
	public void setShowElementLink(boolean show) {
	}

	public void setHtmlBuilder(HtmlBuilder htmlBuilder) {
		this.htmlBuilder = htmlBuilder;
	}

}
