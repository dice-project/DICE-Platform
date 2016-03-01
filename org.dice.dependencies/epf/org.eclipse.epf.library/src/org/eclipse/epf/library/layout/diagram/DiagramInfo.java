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
import java.io.File;

import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;


/**
 * class for diagram layout
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class DiagramInfo extends MethodElementDiagram {

	private int suppressedCount = 0;
	private int nodeCount = 0;
	
	/**
	 * constructor
	 * @param diagramType
	 * @param element
	 */
	public DiagramInfo(String diagramType, MethodElement element) {
		super(element);

		super.setHTMLMap(new HTMLMap(diagramType + "_" + element.getName())); //$NON-NLS-1$
	}

	/**
	 * add a diagram area
	 * @param e
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param altTag
	 * @param suppressed
	 */
	public void addArea(MethodElement e, int x, int y, int width, int height,
			String altTag, boolean suppressed) {
		if ( suppressed )
		{
			suppressedCount++;
			return;
		}
		
		if (altTag == null) {
			if (e instanceof DescribableElement) {
				altTag = ((DescribableElement) e).getPresentationName();
				if (altTag.length() < 1 || altTag == "") { //$NON-NLS-1$
					if (e instanceof Activity) {
						Object base = ((Activity) e).getVariabilityBasedOnElement();
						altTag = ((Activity) base).getPresentationName();
					}
				}
			} else if (e != null) {
				altTag = e.getName();
			} else {
				altTag = ""; //$NON-NLS-1$
			}
		}

		Rectangle coordinates = new Rectangle(x, y, width, height);
		String href = ResourceHelper.getUrl(e, super.getMethodElement(),
				ResourceHelper.FILE_EXT_HTML);
		HTMLMap map = super.getHTMLMap();
		map.addArea(new HTMLArea(e.getGuid(), href, "rect", coordinates, altTag)); //$NON-NLS-1$
	}
	
	/**
	 * Add graphical nodes
	 */
	public void addGraphicalNodes() {
		nodeCount++;
	}

	/**
	 * Sets the image path relative to the publishing dir.
	 * 
	 * @param path
	 */
	public void setImageFilePath(String path) {
		super.setImageFileName(ResourceHelper.getBackPath(super
				.getMethodElement())
				+ path.replace(File.separatorChar, '/'));
	}

	/**
	 * get the number of areas
	 * @return int
	 */
	public int getAreaCount() {
		return super.getHTMLMap().size();
	}

	/**
	 * get the number of suppressed areas
	 * @return int
	 */
	public int getSuppressedCount() {
		return suppressedCount;
	}
	
	/**
	 * get the number of graphical nodes in the diagram
	 * @return int
	 */
	public int getGraphicalNodeCount() {
		return nodeCount;
	}
	
	/**
	 * is empty?
	 * @return boolean
	 */
	public boolean isEmpty() {
		
		return getAreaCount() + getSuppressedCount() + getGraphicalNodeCount() == 0;
					
	}
}

