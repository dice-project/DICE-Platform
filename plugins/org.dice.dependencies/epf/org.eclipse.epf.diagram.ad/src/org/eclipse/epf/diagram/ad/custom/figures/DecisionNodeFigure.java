//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
/**
 * 
 */
package org.eclipse.epf.diagram.ad.custom.figures;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

/**
 * @author Shashidhar Kannoori
 * Adopted from GMF's GeoShapes
 */
public class DecisionNodeFigure extends DefaultSizeNodeFigure implements
		IPolygonAnchorableFigure {

	private int spacing;

	/**
	 * Constructor create a decision figure
	 * 
	 */
	public DecisionNodeFigure(int width, int height, int spacing) {
		super(width, height);
		this.spacing = spacing;
		createFigure(width, height);
	}
	/**
	 * @param defSize
	 */
	public DecisionNodeFigure(Dimension defSize) {
		super(defSize);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param width
	 * @param height
	 */
	public DecisionNodeFigure(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IPolygonAnchorableFigure#getPolygonPoints()
	 */
	public PointList getPolygonPoints() {
		// TODO Auto-generated method stub
		return getPoints();
	}
	protected void paintFigure(Graphics g) {
		PointList points = getPointList(getBounds().getCopy());
		g.fillPolygon(points);
		g.drawPolygon(points);
	}
	
	/**
	 * This method is used to compute the shapes polygon points.
	 * This is currently based on the shapes bounding box.
	 * 
	 * @param rect the rectangle that the shape will fit in
	 */
	protected PointList getPointList(Rectangle rect) {
		PointList points = new PointList();
		
		int halfX = rect.x + (rect.width / 2);
		int halfY = rect.y + (rect.height / 2);
		
		Point p1 = new Point( halfX, rect.y );
		Point p2 = new Point( rect.x + rect.width - 1, halfY );
		Point p3 = new Point( halfX, rect.y + rect.height - 1 );
		Point p4 = new Point( rect.x, halfY );
		
		points.addPoint( p1 );
		points.addPoint( p2 );
		points.addPoint( p3 );
		points.addPoint( p4 );
		points.addPoint( p1 );	
		
		return points;
	}
	
	public IFigure getContentPane() {
		return (IFigure) getChildren().get(0);
	}
	
	public void createFigure(int width, int height){
		setOpaque(true);
		setLayoutManager(new StackLayout() {
			public void layout(IFigure figure) {
				Rectangle r = figure.getClientArea();
				List children = figure.getChildren();
				IFigure child;
				Dimension d;
				for (int i = 0; i < children.size(); i++) {
					child = (IFigure)children.get(i);
					d = child.getPreferredSize(r.width, r.height);
					d.width = Math.min(d.width, r.width);
					d.height = Math.min(d.height, r.height);
					Rectangle childRect = new Rectangle(
						r.x + (r.width - d.width)/2,
						r.y + (r.height - d.height)/2,
						d.width,
						d.height);
					child.setBounds(childRect);
				}
			}
		});
		
		IFigure f = new Figure();
		ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
		layout.setSpacing(spacing);
		f.setLayoutManager(layout);
		add(f);
	}
	
	public PointList getPoints(){
		return getPointList(getBounds().getCopy());
	}
}
