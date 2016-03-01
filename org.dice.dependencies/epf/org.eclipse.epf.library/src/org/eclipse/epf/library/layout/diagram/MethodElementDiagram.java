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
package org.eclipse.epf.library.layout.diagram;

import java.awt.Rectangle;
import java.util.Iterator;

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;


/**
 * Encapsulates a published diagram for a <code>MethodElement</code>.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class MethodElementDiagram {

	// The MethodElement.
	private MethodElement element;

	// The generated JPEG file name.
	private String imageFileName;

	// The HTML Area Map.
	private HTMLMap htmlMap;

	// The HTML that represents this diagram.
	// private String diagramHTML;

	/**
	 * Creates a new <code>RoleDiagramInfo</code>.
	 * 
	 * @param element
	 *            The <code>ContentElement</code>.
	 */
	public MethodElementDiagram(MethodElement element) {
		this.element = element;
	}

	/**
	 * Returns the <code>MethodElement</code> associated with the published
	 * diagram.
	 * 
	 * @return The <code>MethodElement</code> associated with the published
	 *         diagram.
	 */
	public MethodElement getMethodElement() {
		return element;
	}

	/**
	 * Returns the image file name.
	 * 
	 * @return The image file name.
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	/**
	 * Sets the image file name.
	 * 
	 * @param imageFileName
	 *            The image file name.
	 */
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * Returns the HTML Area Map.
	 * 
	 * @return The HTML Area Map.
	 */
	public HTMLMap getHTMLMap() {
		return htmlMap;
	}

	/**
	 * Sets the HTML Area Map.
	 * 
	 * @param htmlMap
	 *            The HTML Area Map.
	 */
	public void setHTMLMap(HTMLMap htmlMap) {
		this.htmlMap = htmlMap;
	}

	/**
	 * Returns the HTML representation for the diagram.
	 * 
	 * @return The HTML source that represents the diagram.
	 */
	public String getHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<p>"); //$NON-NLS-1$
		if (imageFileName != null && htmlMap != null) {
			String mapName = htmlMap.getName();
			html.append("<map name=\"").append(htmlMap.getName()).append("\">"); //$NON-NLS-1$ //$NON-NLS-2$
			Iterator areas = htmlMap.getAreas();
			while (areas.hasNext()) {
				HTMLArea htmlArea = (HTMLArea) areas.next();
				Rectangle coords = htmlArea.getCoordinates();
				html
						.append("<area href=\"").append(htmlArea.getHref()).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
						.append(" shape=\"").append(htmlArea.getShape()).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
						.append(" coords=\"") //$NON-NLS-1$
						.append((int) coords.getX())
						.append(", ") //$NON-NLS-1$
						.append((int) coords.getY())
						.append(", ") //$NON-NLS-1$
						.append((int) (coords.getX() + coords.getWidth()))
						.append(", ") //$NON-NLS-1$
						.append((int) (coords.getY() + coords.getHeight()))
						.append("\"") //$NON-NLS-1$
						.append(" alt=\"").append(htmlArea.getAltTag()).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
						.append(" title=\"").append(htmlArea.getAltTag()).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
						.append("/>"); //$NON-NLS-1$
			}
			html.append("</map>"); //$NON-NLS-1$
			html
					.append("<img border=\"0\" src=\"").append(imageFileName).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
					.append(" alt=\"").append(mapName).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
					.append(" title=\"").append(mapName).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
					.append(" usemap=\"#").append(mapName).append("\"/>"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		html.append("</p>"); //$NON-NLS-1$
		return html.toString();
	}

	/**
	 * get the xml element for the diagram
	 * @return XmlElement
	 */
	public XmlElement getXmlElement()
	{
		XmlElement xmlElement = null;

		if (imageFileName != null && htmlMap != null) {
			String mapName = htmlMap.getName();
			String escapedName = StrUtil.toEscapedUnicode(mapName, true).replace("\\u", ""); //$NON-NLS-1$ //$NON-NLS-2$
			xmlElement = new XmlElement("map") //$NON-NLS-1$
				.setAttribute("name", escapedName)  //$NON-NLS-1$ 
				.setAttribute("src", imageFileName) //$NON-NLS-1$ 
				.setAttribute("alt", mapName);  //$NON-NLS-1$ 
			
			Iterator areas = htmlMap.getAreas();
			while (areas.hasNext()) {
				HTMLArea htmlArea = (HTMLArea) areas.next();
				Rectangle coords = htmlArea.getCoordinates();
				StringBuffer buffer = new StringBuffer();
				buffer.append((int) coords.getX())
				.append(", ") //$NON-NLS-1$
				.append((int) coords.getY())
				.append(", ") //$NON-NLS-1$
				.append((int) (coords.getX() + coords.getWidth()))
				.append(", ") //$NON-NLS-1$
				.append((int) (coords.getY() + coords.getHeight()));
				
				// need to escape the url since it will be passed as a literal string to a javascript variable
				xmlElement.newChild("area") //$NON-NLS-1$
					.setAttribute("guid", (htmlArea.getGuid() == null) ? "" : htmlArea.getGuid() )  //$NON-NLS-1$ //$NON-NLS-2$
					.setAttribute("href", StrUtil.escape(htmlArea.getHref()))	//$NON-NLS-1$
					.setAttribute("shape", htmlArea.getShape())	//$NON-NLS-1$
					.setAttribute("coords", buffer.toString())	//$NON-NLS-1$
					.setAttribute("alt", htmlArea.getAltTag());	//$NON-NLS-1$
			}
		}
		
		return xmlElement;
	}
}
