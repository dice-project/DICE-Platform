/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface ITraceService {

	/**
	 * Returns the stacktrace of the given Throwable.
	 * 
	 * @param The
	 *            Throwable, for which to get the stacktrace.
	 * @return The stacktrace of the given Throwable.
	 */
	public abstract String getStacktrace(Throwable t);

	public abstract void dumpFigureTree(IFigure figure);

	public abstract void dumpFigureTree(IFigure figure, int indent);

	public abstract void dumpEditPartTree(EditPart editPart);

	public abstract void dumpEditPartTree(EditPart editPart, int indent);

	public abstract void dumpPictogramModelTree(PictogramElement pe);

	public abstract void dumpPictogramModelTree(PictogramElement pe, int indent);

	public abstract void dumpGATree(GraphicsAlgorithm ga);

	public abstract void dumpGATree(GraphicsAlgorithm ga, int indent);

}