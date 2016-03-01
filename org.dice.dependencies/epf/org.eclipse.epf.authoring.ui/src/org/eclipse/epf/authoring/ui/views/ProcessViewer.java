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
package org.eclipse.epf.authoring.ui.views;

import org.eclipse.epf.authoring.ui.celleditors.AbstractCheckBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Displays the Process elements in a tree table viewer.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ProcessViewer extends ProcessTreeViewer {

	private String[] columnProperties;

	private AbstractCheckBoxCellEditor[] checkBoxCellEditors = null;

	/**
	 * Creates a new instance.
	 */
	public ProcessViewer(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Return parent - underlying tree
	 * 
	 * @return parent
	 */
	public Composite getCellEditorParent() {
		return getTree();
	}

	/**
	 * Set check box cell editors
	 * 
	 * @param checkBoxCellEditors
	 */
	public void setCheckBoxCellEditors(
			AbstractCheckBoxCellEditor[] checkBoxCellEditors) {
		this.checkBoxCellEditors = checkBoxCellEditors;
		for (int i = 0; i < checkBoxCellEditors.length; i++) {
			AbstractCheckBoxCellEditor editor = checkBoxCellEditors[i];
			if (editor == null)
				continue;
			editor.setColumnIndex(i);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.ProcessTreeViewer#hookControl(org.eclipse.swt.widgets.Control)
	 */
	protected void hookControl(Control control) {
		super.hookControl(control);
		getTree().addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				modifyItem(e);
			}
		});
		// getTree().addMouseMoveListener(new MouseMoveListener(){
		// public void mouseMove(MouseEvent e){
		// setUnsetEditor(e);
		// }
		// });
		Listener paintListener = new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.PaintItem: {
					// System.out.println("event.index = "+ event.index);
					Image image = getCheckBoxCellEditorImage(event);
					if (image == null)
						return;
					TreeColumn col = getTree().getColumn(event.index);
					int x = event.x + col.getWidth() / 2;
					Rectangle rect = image.getBounds();
					int offset = Math.max(0, (event.height - rect.height) / 2);
					event.gc.drawImage(image, x - rect.width / 2, event.y
							+ offset);
					break;
				}
				}
			}
		};
		getTree().addListener(SWT.PaintItem, paintListener);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.views.ProcessTreeViewer#doUpdateItem(org.eclipse.swt.widgets.Item,
	 *      java.lang.Object)
	 */
	protected void doUpdateItem(Item item, Object element) {
		super.doUpdateItem(item, element);

		if (checkBoxCellEditors != null && columnProperties != null
				&& columnProperties.length > 0) {
			for (int i = 0; i < checkBoxCellEditors.length; i++) {
				AbstractCheckBoxCellEditor editor = checkBoxCellEditors[i];
				if (editor == null)
					continue;
				((TreeItem) item).setText(i, ""); //$NON-NLS-1$

			}
		}

	}

	private Image getCheckBoxCellEditorImage(Event event) {
		return getCheckBoxCellEditorImage(event.index, (TreeItem) event.item);
	}

	private Image getCheckBoxCellEditorImage(int columnIndex, TreeItem item) {
		if (checkBoxCellEditors == null
				|| checkBoxCellEditors[columnIndex] == null)
			return null;
		return checkBoxCellEditors[columnIndex].getImage(item,
				columnProperties[columnIndex]);
	}

	/**
	 * @see org.eclipse.jface.viewers.TreeViewer#setCellModifier(org.eclipse.jface.viewers.ICellModifier)
	 */
	public void setCellModifier(ICellModifier modifier) {
		super.setCellModifier(modifier);

		if (checkBoxCellEditors != null) {
			for (int i = 0; i < checkBoxCellEditors.length; i++) {
				AbstractCheckBoxCellEditor editor = checkBoxCellEditors[i];
				if (editor == null)
					continue;
				editor.setCellModifier(modifier);
			}
		}
	}

	/**
	 * This method is used for mouse down listener
	 * 
	 * @param event
	 */
	private void modifyItem(MouseEvent event) {
		Point pt = new Point(event.x, event.y);
		TreeItem item = getTree().getItem(pt);
		if (item == null)
			return;

		for (int i = 0; i < getTree().getColumnCount(); i++) {
			Rectangle rect = item.getBounds(i);
			if (rect.contains(pt)) {
				Image image = getCheckBoxCellEditorImage(i, item);
				if (image == null)
					return;
				Rectangle imgRect = image.getBounds();
				int offset = Math.max(0, (rect.height - imgRect.height) / 2);
				int x = rect.x + rect.width / 2 - imgRect.width / 2;
				int y = rect.y + offset;
				imgRect = new Rectangle(x, y, imgRect.width, imgRect.height);
				if (imgRect.contains(pt)) {
					checkBoxCellEditors[i].modify(item, columnProperties[i]);
					return;
				}
			}
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.TreeViewer#setColumnProperties(java.lang.String[])
	 */
	public void setColumnProperties(String[] columnProperties) {
		super.setColumnProperties(columnProperties);
		this.columnProperties = columnProperties;
	}
}