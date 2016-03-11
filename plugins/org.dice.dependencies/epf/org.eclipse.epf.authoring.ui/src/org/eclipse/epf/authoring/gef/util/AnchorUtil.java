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
package org.eclipse.epf.authoring.gef.util;

import org.eclipse.draw2d.geometry.Point;

/**
 * Utility class for anchors.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class AnchorUtil {

	public static Point getClosestPoint(Point[] points, Point point) {
		int size = points.length;
		double[] distances = new double[size];
		for (int i = 0; i < size; i++) {
			distances[i] = points[i].getDistance(point);
		}
		return points[min(distances)];
	}

	public static int min(double[] numbers) {
		if (numbers.length == 0)
			return -1;
		double min = numbers[0];
		int id = 0;
		for (int i = 1; i < numbers.length; i++) {
			double n = numbers[i];
			if (n < min) {
				min = n;
				id = i;
			}
		}
		return id;
	}

	/**
	 * @param numbers
	 * @return
	 * 		index of the smallest number in the given array
	 */
	public static int min(int[] numbers) {
		if (numbers.length == 0)
			return -1;
		int min = numbers[0];
		int id = 0;
		for (int i = 1; i < numbers.length; i++) {
			int n = numbers[i];
			if (n < min) {
				min = n;
				id = i;
			}
		}
		return id;
	}

}
