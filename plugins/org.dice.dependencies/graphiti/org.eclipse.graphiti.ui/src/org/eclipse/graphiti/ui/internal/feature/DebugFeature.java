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
package org.eclipse.graphiti.ui.internal.feature;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditorInternal;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DebugFeature extends AbstractCustomFeature {

	private static final String NAME_DUMP_PICTOGRAM_DATA = "Dump pictogram data"; //$NON-NLS-1$
	private static final String NAME_DUMP_FIGURE_DATA = "Dump figure data"; //$NON-NLS-1$
	private static final String NAME_DUMP_EDIT_PART_DATA = "Dump editpart tree"; //$NON-NLS-1$
	private static final String NAME_DUMP_ALL = "Dump all data"; //$NON-NLS-1$
	private static final String NAME_REFRESH = "Refresh"; //$NON-NLS-1$

	public static final int TYPE_DUMP_PICTOGRAM_DATA = 0;
	public static final int TYPE_DUMP_FIGURE_DATA = 1;
	public static final int TYPE_DUMP_EDIT_PART_DATA = 2;
	public static final int TYPE_DUMP_ALL = 3;
	public static final int TYPE_REFRESH = 4;

	private int type;

	public DebugFeature(IFeatureProvider fp, int type) {
		super(fp);
		setType(type);
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length > 0 && pes[0] != null) {
			PictogramElement pe = pes[0];
			DiagramEditorInternal diagramEditor = (DiagramEditorInternal) getDiagramEditor();
			GraphicalEditPart ep = diagramEditor.getEditPartForPictogramElement(pe);
			IFigure figure = diagramEditor.getFigureForPictogramElement(pe);

			switch (getType()) {
			case TYPE_DUMP_PICTOGRAM_DATA:
				GraphitiUiInternal.getTraceService().dumpPictogramModelTree(pe);
				break;
			case TYPE_DUMP_EDIT_PART_DATA:
				GraphitiUiInternal.getTraceService().dumpEditPartTree(ep);
				break;
			case TYPE_DUMP_FIGURE_DATA:
				GraphitiUiInternal.getTraceService().dumpFigureTree(figure);
				break;
			case TYPE_DUMP_ALL:
				GraphitiUiInternal.getTraceService().dumpPictogramModelTree(pe);
				GraphitiUiInternal.getTraceService().dumpEditPartTree(ep);
				GraphitiUiInternal.getTraceService().dumpFigureTree(figure);
				break;
			case TYPE_REFRESH:
				ep.refresh();
				break;
			}
		}
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return true;
	}

	@Override
	public String getName() {
		String ret = ""; //$NON-NLS-1$
		switch (getType()) {
		case TYPE_DUMP_PICTOGRAM_DATA:
			ret = NAME_DUMP_PICTOGRAM_DATA;
			break;
		case TYPE_DUMP_FIGURE_DATA:
			ret = NAME_DUMP_FIGURE_DATA;
			break;
		case TYPE_DUMP_EDIT_PART_DATA:
			ret = NAME_DUMP_EDIT_PART_DATA;
			break;
		case TYPE_DUMP_ALL:
			ret = NAME_DUMP_ALL;
			break;
		case TYPE_REFRESH:
			ret = NAME_REFRESH;
			break;
		}
		return ret;
	}

	protected int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}

}
