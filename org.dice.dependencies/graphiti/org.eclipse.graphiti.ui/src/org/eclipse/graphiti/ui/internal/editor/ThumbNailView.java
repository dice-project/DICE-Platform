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
package org.eclipse.graphiti.ui.internal.editor;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.SimpleRootEditPart;
import org.eclipse.graphiti.ui.internal.fixed.FixedScrollableThumbnail;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

/**
 * A Thumbnail view for <code>GraphicalViewers</code>. The class scans all
 * editparts and viewparts of the active WorkbenchPage for GraphicalViewer
 * Adapters and shows a thumbnail view for the first one found. Views are
 * scanned first.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ThumbNailView extends ViewPart implements IPartListener {

	private FixedScrollableThumbnail _thumbnail;

	private LightweightSystem _lws;

	private IWorkbenchPart _workbenchPart;

	private GraphicalViewer _graphicalViewer;

	/**
	 * Creates a new ThumbNailView, which registers as PartListener at the
	 * active WorkbenchWindow.
	 */
	public ThumbNailView() {
		IWorkbenchWindow workbenchWindow = GraphitiUiInternal.getWorkbenchService().getActiveOrFirstWorkbenchWindow();
		workbenchWindow.getPartService().addPartListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		Canvas overview = new Canvas(parent, SWT.NONE);
		_lws = new LightweightSystem(overview);
		refreshThumbnailViewer();
	}

	@Override
	public void setFocus() {
		// nothing to do
	}

	@Override
	public void dispose() {
		IWorkbenchWindow workbenchWindow = GraphitiUiInternal.getWorkbenchService().getActiveOrFirstWorkbenchWindow();
		workbenchWindow.getPartService().removePartListener(this);
		super.dispose();
		clearThumbnail();
	}

	public void partActivated(IWorkbenchPart part) {
		if (!part.equals(_workbenchPart)) {
			GraphicalViewer viewer = (GraphicalViewer) part.getAdapter(GraphicalViewer.class);
			if (viewer != null) {
				createThumbNailViewer(part);
			}
		}
	}

	public void partBroughtToTop(IWorkbenchPart part) {
		// nothing to do
	}

	public void partClosed(IWorkbenchPart part) {
		if (part.equals(_workbenchPart)) {
			refreshThumbnailViewer();
		}
	}

	public void partDeactivated(IWorkbenchPart part) {
		// clearThumbnail();
	}

	private void clearThumbnail() {
		if (_thumbnail != null) {
			_thumbnail.deactivate();
			_thumbnail = null;
		}
	}

	public void partOpened(IWorkbenchPart part) {
		GraphicalViewer viewer = (GraphicalViewer) part.getAdapter(GraphicalViewer.class);
		if (viewer != null) {
			createThumbNailViewer(part);
		}
	}

	private void refreshThumbnailViewer() {
		IWorkbenchPart part = findGraphicalViewerAdaptable();
		createThumbNailViewer(part);
	}

	private IWorkbenchPart findGraphicalViewerAdaptable() {
		IWorkbenchWindow window = GraphitiUiInternal.getWorkbenchService().getActiveOrFirstWorkbenchWindow();
		if (window == null)
			return null;
		IWorkbenchPage page = window.getActivePage();
		if (page == null)
			return null;

		IWorkbenchPart activePart = page.getActivePart();
		if (activePart == null) {
			return null;
		}

		GraphicalViewer currentViewer = (GraphicalViewer) activePart.getAdapter(GraphicalViewer.class);
		if (currentViewer != null) {
			return activePart;
		}

		return null;
	}

	private void createThumbNailViewer(IWorkbenchPart part) {
		if (part != null) {
			_graphicalViewer = (GraphicalViewer) part.getAdapter(GraphicalViewer.class);
			_workbenchPart = part;
			if (_graphicalViewer != null) {
				SimpleRootEditPart rootEditPart = (SimpleRootEditPart) _graphicalViewer.getRootEditPart();
				_thumbnail = new FixedScrollableThumbnail((Viewport) rootEditPart.getFigure());
				_thumbnail.setBorder(new MarginBorder(3));
				if (rootEditPart instanceof LayerManager)
					_thumbnail.setSource(((LayerManager) rootEditPart).getLayer(LayerConstants.PRINTABLE_LAYERS));
				_lws.setContents(_thumbnail);
			}
		} else {
			_graphicalViewer = null;
			_workbenchPart = null;
			_lws.setContents(new Figure());
		}
	}

}