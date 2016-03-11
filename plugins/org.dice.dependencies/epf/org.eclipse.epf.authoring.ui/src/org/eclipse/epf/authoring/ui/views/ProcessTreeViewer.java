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
package org.eclipse.epf.authoring.ui.views;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.library.configuration.ActivityDeepCopyConfigurator;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.ActivityDropCommand;
import org.eclipse.epf.library.edit.ui.IActionTypeProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;


/**
 * Displays the Process elements in a tree table viewer.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ProcessTreeViewer extends TreeViewer implements
		IActionTypeProvider {
	private static final String COPY_TXT = AuthoringUIResources.copy_text; 
	private static final String EXTEND_TXT = AuthoringUIResources.extend_text; 
	private static final String DEEP_COPY_TXT = AuthoringUIResources.deepCopy_text; 
	
	private static String getActionText(int actionType) {
		switch (actionType) {
		case COPY:
			return COPY_TXT;
		case EXTEND:
			return EXTEND_TXT;
		case DEEP_COPY:
			return DEEP_COPY_TXT;
		}
		return null;
	}

	private int actionType = IActionTypeProvider.COPY;

	private Menu actionTypePopup;

	private Point actionTypePopupLocation;

	/**
	 * Creates a new instance.
	 */
	public ProcessTreeViewer(Composite parent, int style) {
		super(parent, style);

		final Tree tree = getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		addTreeListener(new ITreeViewerListener() {
			public void treeCollapsed(TreeExpansionEvent event) {
				setItemProviderExpanded(event.getElement(), false);
			}

			public void treeExpanded(TreeExpansionEvent event) {
				setItemProviderExpanded(event.getElement(), true);
			}
		});
		
		Accessible accessible = tree.getAccessible();
		accessible.addAccessibleListener (new AccessibleAdapter () {
			public void getName (AccessibleEvent e) {
				super.getName(e);
				if (e.childID >= 0) {
					TreeItem[] items = tree.getSelection();
					if (items != null && items.length == 1) {
						String stringToRead = ""; //$NON-NLS-1$
						TreeItem item = (TreeItem) items[0];
						if (item != null) {
							TreeColumn[] columns = tree.getColumns();
							for (int i = 0; i < columns.length; i++) {
								TreeColumn column = columns[i];
								stringToRead += column.getText() + " " //$NON-NLS-1$
										+ item.getText(i);
							}
							e.result = stringToRead;
						}
					}
				}
			}
		});
	}

	/**
	 * Set up colunms according to the given array of descriptors
	 * @param columnDescriptors
	 */
	public void setupColumns(ColumnDescriptor[] columnDescriptors) {
		// Remove all old TreeColumns.
		TreeColumn[] cols = getTree().getColumns();
		int size = cols.length;
		for (int i = 0; i < size; i++) {
			cols[i].dispose();
		}

		String[] colProps = new String[columnDescriptors.length];
		for (int i = 0; i < columnDescriptors.length; i++) {
			colProps[i] = columnDescriptors[i].id;
			TreeColumn column = new TreeColumn(getTree(),
					columnDescriptors[i].alignment);
			column.setText(columnDescriptors[i].label);
			column.setResizable(columnDescriptors[i].resizable);
			column.setWidth(columnDescriptors[i].width);
		}

		setColumnProperties(colProps);

	}

	private void setItemProviderExpanded(Object element, boolean expand) {
		Object adapter = getAdapterFactory().adapt(element,
				ITreeItemContentProvider.class);
		if (adapter instanceof IBSItemProvider) {
			((IBSItemProvider) adapter).setExpanded(Boolean.valueOf(expand));
		}
	}

	/**
	 * @return the AdapterFactory
	 */
	public AdapterFactory getAdapterFactory() {
		return getContentProvider() == null ? null
				: ((AdapterFactoryContentProvider) getContentProvider())
						.getAdapterFactory();
	}

	/**
	 * @see org.eclipse.jface.viewers.TableTreeViewer#setExpanded(Item, boolean)
	 */
	protected void setExpanded(Item node, boolean expand) {
		super.setExpanded(node, expand);
		Object adapter = getAdapterFactory().adapt(node.getData(),
				ITreeItemContentProvider.class);
		if (adapter instanceof IBSItemProvider) {
			IBSItemProvider bsItemProvider = ((IBSItemProvider) adapter);
			if (bsItemProvider.isExpanded() == null) {
				bsItemProvider.setExpanded((Boolean.valueOf(expand)));
			}
		}
	}

	private void setExpanded(Item item, Object element) {
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) getAdapterFactory().adapt(element,
				ITreeItemContentProvider.class);
		if(adapter.hasChildren(element)) {
			if (adapter instanceof IBSItemProvider) {
				Boolean expand = ((IBSItemProvider) adapter).isExpanded();
				if (expand != null) {
					setExpandedState(item, expand.booleanValue());
				}
			}
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.AbstractTreeViewer#internalRefresh(Widget,
	 *      Object, boolean, boolean)
	 */
	protected void internalRefresh(Widget widget, Object element,
			boolean doStruct, boolean updateLabels) {
		super.internalRefresh(widget, element, doStruct, updateLabels);

		if (doStruct) {
			preserveExpandedState(widget);
		}
	}
	
	private void preserveExpandedState(Widget widget) {
		if (widget instanceof Item) {
			Item item = (Item) widget;
			setExpanded(item, item.getData());
		} else {
			Item[] children = getChildren(widget);
			for (int i = 0; i < children.length; i++) {
				Item child = children[i];
				if (child instanceof Item) {
					Item item = (Item) child;
					setExpanded(item, item.getData());
				}
			}
		}
	}
	
	@Override
	protected void internalInitializeTree(Control widget) {
		super.internalInitializeTree(widget);
		preserveExpandedState(widget);
	}

	private Command command;

	private IActionManager actionManager;

	/**
	 * @see org.eclipse.epf.library.edit.ui.IActionTypeProvider#getActionType()
	 */
//	public int getActionType() {
//		if (actionTypePopup == null) {
//			createActionTypePopup();
//		}
//
//		Display.getCurrent().syncExec(new Runnable() {
//
//			public void run() {
//				actionType = 0;
//				actionTypePopup.setLocation(actionTypePopupLocation);
//				actionTypePopup.setVisible(true);
//			}
//
//		});
//
//		return actionType;
//	}

	private void createActionTypePopup(int[] choices) {
		actionTypePopup = new Menu(getControl());
		SelectionListener selectionListener = new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				MenuItem item = ((MenuItem) e.getSource());
				String text = item.getText();
				if (text == COPY_TXT) {
					ProcessTreeViewer.this.actionType = IActionTypeProvider.COPY;
				} else if (text == EXTEND_TXT) {
					ProcessTreeViewer.this.actionType = IActionTypeProvider.EXTEND;
				} else if (text == DEEP_COPY_TXT) {
					ProcessTreeViewer.this.actionType = DEEP_COPY;
				}

				if (command instanceof ActivityDropCommand) {
					ActivityDropCommand cmd = ((ActivityDropCommand) command);
					cmd.setType(ProcessTreeViewer.this.actionType);
					cmd.setLabel(text);
					
					if (ProcessTreeViewer.this.actionType == DEEP_COPY ) {
						cmd.setActivityDeepCopyConfigurator(new ActivityDeepCopyConfigurator());
					}
					
				}

				if (actionManager != null
						&& command instanceof IResourceAwareCommand) {
					actionManager.execute((IResourceAwareCommand) command);
				} else {
					command.execute();
				}

				actionTypePopup.setVisible(false);
			}

		};
		
		for (int actionType : choices) {
			MenuItem menuItem = new MenuItem(actionTypePopup, SWT.CASCADE);
			menuItem.setText(getActionText(actionType));
			menuItem.addSelectionListener(selectionListener);
		}
	}

	/**
	 * @see org.eclipse.epf.library.edit.ui.IActionTypeProvider#setInputData(Object)
	 */
	public void setInputData(Object object) {
		actionTypePopupLocation = (Point) object;
	}

	/**
	 * @see org.eclipse.epf.library.edit.ui.IActionTypeProvider#execute(org.eclipse.emf.common.command.Command, boolean)
	 */
	public void execute(Command cmd, int[] choices) {
		createActionTypePopup(choices);

		this.command = cmd;
		actionTypePopup.setLocation(actionTypePopupLocation);
		actionTypePopup.setVisible(true);
	}

	/**
	 * Changes default behavior of activating text cell editor from single mouse click to double mouse click
	 */
	@Override
	protected void hookEditingSupport(Control control) {
		try {
			TreeViewerEditor.create(this, new ColumnViewerEditorActivationStrategy(this) {
				@Override
				protected boolean isEditorActivationEvent(
						ColumnViewerEditorActivationEvent event) {
					return event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
					|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC
					|| event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL;
				}
			},ColumnViewerEditor.DEFAULT);
		}
		catch(NullPointerException e) {
			// work-around for a bug in org.eclipse.jface.viewers.TreeViewerEditor.create(TreeViewer, SWTFocusCellManager, ColumnViewerEditorActivationStrategy, int)
			// that tried to call SWTFocusCellManager.init() even SWTFocusCellManager is null		
		}
		super.hookEditingSupport(control);
	}

	/**
	 * @see org.eclipse.epf.library.edit.ui.IActionTypeProvider#setActionManager(IActionManager)
	 */
	public void setActionManager(IActionManager actionMgr) {
		actionManager = actionMgr;
	}

}