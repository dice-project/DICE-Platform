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
package org.eclipse.graphiti.ui.internal.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.internal.figures.GFMultilineText;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.services.ITraceService;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class TraceService implements ITraceService {

	private final boolean FULL_QUALIFIED = false;
	private final boolean ADD_OBJECT_INFO = false;

	@Override
	public String getStacktrace(Throwable t) {
		if (t == null)
			return null;
		StringWriter writer = new StringWriter();
		t.printStackTrace(new PrintWriter(writer));
		return writer.toString();
	}

	@Override
	public void dumpFigureTree(IFigure figure) {
		System.out.println("\nFigure Tree"); //$NON-NLS-1$
		dumpFigureTree(figure, 0);
	}

	@Override
	public void dumpFigureTree(IFigure figure, int indent) {
		String indentString = createIndentString(indent);

		String additional = ""; //$NON-NLS-1$
		if (figure instanceof Label) {
			Label label = (Label) figure;
			additional = " (text: " + label.getText() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (figure instanceof GFMultilineText) {
			GFMultilineText mlt = (GFMultilineText) figure;
			additional = additional + " (text: " + mlt.getText() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (!figure.isVisible()) {
			additional = additional + " (NOT visible)"; //$NON-NLS-1$
		}
		additional = additional + " (" + figure.getBounds() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		if (ADD_OBJECT_INFO) {
			additional = additional + " (" + getObjectInfo(figure) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		System.out.println(indentString + getClassName(figure, FULL_QUALIFIED) + additional);

		@SuppressWarnings("unchecked")
		List<IFigure> children = figure.getChildren();
		for (IFigure childFigure : children) {
			dumpFigureTree(childFigure, indent + 2);
		}
	}

	@Override
	public void dumpEditPartTree(EditPart editPart) {
		System.out.println("\nEdit Part Tree()"); //$NON-NLS-1$
		dumpEditPartTree(editPart, 0);
	}

	@Override
	public void dumpEditPartTree(EditPart editPart, int indent) {
		String indentString = createIndentString(indent);
		Object m = editPart.getModel();
		String additional = ""; //$NON-NLS-1$
		if (m instanceof PictogramElement) {
			PictogramElement pe = (PictogramElement) m;
			GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			if (ga != null) {
				additional = additional + " (" + getClassName(ga, FULL_QUALIFIED) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				if (ADD_OBJECT_INFO) {
					additional = additional + " (" + getObjectInfo(ga) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		System.out.println(indentString + getClassName(editPart, FULL_QUALIFIED) + additional);

		List<EditPart> ch = GraphitiUiInternal.getGefService().getEditPartChildren(editPart);
		for (EditPart epChild : ch) {
			dumpEditPartTree(epChild, indent + 2);
		}
	}

	@Override
	public void dumpPictogramModelTree(PictogramElement pe) {
		System.out.println("\nPictogram Model Tree()"); //$NON-NLS-1$
		dumpPictogramModelTree(pe, 0);
	}

	@Override
	public void dumpPictogramModelTree(PictogramElement pe, int indent) {
		String indentString = createIndentString(indent);

		String additional = ""; //$NON-NLS-1$
		additional = additional + " (" + (pe.isActive() ? "active" : "inactive") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		additional = additional + " (" + (pe.isVisible() ? "visible" : "invisible") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		System.out.println(indentString + GefService.PE + getClassName(pe, FULL_QUALIFIED) + additional);

		dumpGATree(pe.getGraphicsAlgorithm(), indent + 2);

		Collection<PictogramElement> peChildren = Graphiti.getPeService().getPictogramElementChildren(pe);
		for (PictogramElement peChild : peChildren) {
			dumpPictogramModelTree(peChild, indent + 2);
		}
	}

	@Override
	public void dumpGATree(GraphicsAlgorithm ga) {
		dumpGATree(ga, 0);
	}

	@Override
	public void dumpGATree(GraphicsAlgorithm ga, int indent) {
		String indentString = createIndentString(indent);
		if (ga == null) {
			return;
		}

		String additional = ""; //$NON-NLS-1$
		IDimension size = Graphiti.getGaService().calculateSize(ga);
		additional = additional + " (" + ga.getX() + ", " + ga.getY() + ", " + size.getWidth() + ", " + size.getHeight() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		if (ADD_OBJECT_INFO) {
			additional = additional + " (" + getObjectInfo(ga) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		System.out.println(indentString + getClassName(ga, FULL_QUALIFIED) + additional);

		List<GraphicsAlgorithm> gaChildren = ga.getGraphicsAlgorithmChildren();
		for (GraphicsAlgorithm gaChild : gaChildren) {
			dumpGATree(gaChild, indent + 2);
		}
	}

	private String getClassName(Object o, boolean fullQualified) {
		if (fullQualified) {
			return o.getClass().getName();
		} else {
			return o.getClass().getSimpleName();
		}
	}

	private String createIndentString(int indent) {
		int s = 0;
		String indentString = ""; //$NON-NLS-1$
		while (s < indent) {
			indentString = indentString + " "; //$NON-NLS-1$
			s++;
		}
		return indentString;
	}

	private String getObjectInfo(Object o) {
		return o.toString();
	}

}
