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
package org.eclipse.epf.library.edit.util;

import java.awt.Dimension;
import java.awt.Point;

import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.GraphNode;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class GraphicalData {
	private Point location = new Point(0, 0);

	private Dimension size = new Dimension(-1, -1);

	private GraphNode graphNode;

	private Diagram diagram;

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public GraphNode getGraphNode() {
		return graphNode;
	}

	public void setGraphNode(GraphNode graphNode) {
		this.graphNode = graphNode;
	}

	/**
	 * @return Returns the location.
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            The location to set.
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return Returns the size.
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * @param size
	 *            The size to set.
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}
}
