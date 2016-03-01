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
package org.eclipse.epf.library.edit.process;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ICachedChildrenItemProvider;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.command.ResourceAwareDragAndDropCommand;
import org.eclipse.epf.library.edit.process.command.GenericDropCommand;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.WorkBreakdownElement;


/**
 * Base item provider for all breakdown elements
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class BreakdownElementItemProvider extends
		org.eclipse.epf.uma.provider.BreakdownElementItemProvider implements
		IProcessItemProvider, IBSItemProvider, ITableItemLabelProvider, ICachedChildrenItemProvider {

	private Object parent;

	private int id;

	private Object topItem;

	private boolean rolledUp;

	private Boolean expanded;

	private PredecessorList predecessors;

	protected ItemProviderAdapter delegateItemProvider;
	
	protected List cachedChildren;

	/**
	 * @param adapterFactory
	 */
	public BreakdownElementItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory);
		this.delegateItemProvider = delegateItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (predecessors != null) {
			predecessors.dispose();
		}
		if(cachedChildren != null) {
			cachedChildren.clear();
			cachedChildren = null;
		}
		super.dispose();
	}

	public List getPropertyDescriptors(Object object) {
		if (delegateItemProvider != null) {
			return delegateItemProvider.getPropertyDescriptors(object);
		}
		return super.getPropertyDescriptors(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.BreakdownElementItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isExpanded()
	 */
	public Boolean isExpanded() {
		return expanded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setExpanded(boolean)
	 */
	public void setExpanded(Boolean b) {
		expanded = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#setParent(java.lang.Object)
	 */
	public void setParent(Object obj) {
		parent = obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null) {
			return parent;
		}
		if(object instanceof BreakdownElement) {
			return ((BreakdownElement)object).getSuperActivities();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#getId()
	 */
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#getTopItem()
	 */
	public Object getTopItem() {
		Object parent = getParent(target);
		if (topItem == null && parent != null) {
			IBSItemProvider adapter = (IBSItemProvider) getRootAdapterFactory()
					.adapt(parent, ITreeItemContentProvider.class);
			if (adapter != null) {
				Object top = adapter.getTopItem();
				if (top == null && parent instanceof Process
						&& ((Process) parent).getSuperActivities() == null) {
					top = parent;
					adapter.setTopItem(top);
				}
				return top;
			}
		}
		return topItem;
	}

	protected String getColumnName(int columnIndex) {
		AdapterFactory rootAdapterFactory = getRootAdapterFactory();
		if (rootAdapterFactory instanceof IColumnAware) {
			Map columnIndexToNameMap = ((IColumnAware) rootAdapterFactory)
					.getColumnIndexToNameMap();
			if (columnIndexToNameMap != null) {
				return (String) columnIndexToNameMap.get(new Integer(
						columnIndex));
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnImage(java.lang.Object,
	 *      int)
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		String colName = getColumnName(columnIndex);
		return TngUtil.getColumnImage(object, colName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnText(java.lang.Object,
	 *      int)
	 */
	public String getColumnText(Object object, int columnIndex) {
		// String colName = (String) columnIndexToNameMap.get(new
		// Integer(columnIndex));
		String colName = getColumnName(columnIndex);
		return getAttribute(object, colName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#setTopItem(java.lang.Object)
	 */
	public void setTopItem(Object top) {
		topItem = top;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isRolledUp()
	 */
	public boolean isRolledUp() {
		return rolledUp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setRolledUp(boolean)
	 */
	public void setRolledUp(boolean b) {
		rolledUp = b;
	}

	// /* (non-Javadoc)
	// * @see
	// com.ibm.library.edit.process.IBSItemProvider#setColumnIndexToNameMap(java.util.Map)
	// */
	// public void setColumnIndexToNameMap(Map map) {
	// if (map != null)
	// {
	// columnIndexToNameMap = map;
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getAttribute(java.lang.Object,
	 *      java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		return ProcessUtil.getAttribute(object, property, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setAttribute(java.lang.Object,
	 *      java.lang.String, java.lang.String)
	 */
	public void setAttribute(Object object, String prop, String txt) {
		WorkBreakdownElement e = (WorkBreakdownElement) object;
		if (prop == IBSItemProvider.COL_NAME) {
			e.setName(txt);
		} else if (prop == IBSItemProvider.COL_PREDECESSORS) {
		} else if (prop == IBSItemProvider.COL_IS_EVENT_DRIVEN) {
			e.setIsEventDriven(Boolean.valueOf(txt));
		} else if (prop == IBSItemProvider.COL_IS_ONGOING) {
			e.setIsOngoing(Boolean.valueOf(txt));
		} else if (prop == IBSItemProvider.COL_IS_REPEATABLE) {
			e.setIsRepeatable(Boolean.valueOf(txt));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getListeners()
	 */
	public List getListeners() {
		if (changeNotifier == null)
			return null;
		return Collections.unmodifiableList((List) changeNotifier);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getPredecessors()
	 */
	public PredecessorList getPredecessors() {
		if (predecessors == null) {
			predecessors = new PredecessorList(TngUtil
					.getBestAdapterFactory(adapterFactory), target);
		}
		return predecessors;
	}

	public boolean isFirstElement(Object obj) {
		return ProcessUtil.isFirstElement(getRootAdapterFactory(), this, obj);

		// Object parent = this.getParent(obj);
		// if ((parent != null) && (parent instanceof Activity))
		// {
		// Activity activity = (Activity) this.getParent(obj);
		// List elements = getFilteredBreakdownElements(activity, obj);
		//			
		// // if it's first element, return true
		// if (elements.get(0).equals(obj))
		// {
		// return true;
		// }
		// else return false;
		// }
		//		
		// return true;
	}

	public boolean isLastElement(Object obj) {
		return ProcessUtil.isLastElement(getRootAdapterFactory(), this, obj);

		// Object parent = this.getParent(obj);
		// if ((parent != null) && (parent instanceof Activity))
		// {
		// Activity activity = (Activity) this.getParent(obj);
		// List elements = getFilteredBreakdownElements(activity, obj);
		//			
		// // if it's last element, return true
		// if (elements.get(elements.size() - 1).equals(obj))
		// {
		// return true;
		// }
		// else return false;
		// }
		//		
		// return true;

	}

	public void moveUp(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveUp((Activity) parent, obj, getEClasses(), actionMgr);

		}
	}

	public void moveDown(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveDown((Activity) parent, obj, getEClasses(), actionMgr);
		}
	}

	public Collection getEClasses() {
		// implemented by subclasses
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.DescriptorItemProvider#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		Object image = TngUtil.getCustomNodeIcon(object);
		if(image != null) {
			return image;
		}
		if (delegateItemProvider != null) {
			return delegateItemProvider.getImage(object);
		}
		return super.getImage(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.DescriptorItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		if (TngUtil.handlePredecessorListChange(this, notification))
			return;

		super.notifyChanged(notification);
	}

	protected void refreshChildren(Notification notification,
			Collection newOrOldChildren) {
		if (!newOrOldChildren.isEmpty()) {
			// // recalculate the IDs of breakdown elements and refresh them
			// //
			// Activity topAct = (Activity) getTopItem();
			// AdapterFactory adapterFactory = getRootAdapterFactory();
			// ProcessUtil.updateIDs(adapterFactory, new
			// AdapterFactoryTreeIterator(adapterFactory, topAct), topAct);

			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		}
	}

	public GenericDropCommand.ElementAdapter createDropAdapter() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	public IResourceAwareCommand createDropCommand(Object owner,
			List dropElements) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.ICachedChildrenItemProvider#getChildrenFromCache()
	 */
	public Collection getChildrenFromCache() {
		if(cachedChildren == null) {
			getChildren(target);
		}
		return cachedChildren;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.ICachedChildrenItemProvider#getRollupChildrenFromCache()
	 */
	public Collection getRollupChildrenFromCache() {
		return Collections.EMPTY_LIST;
	}

	@Override
	protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations, int operation, Collection collection) {
		return new ResourceAwareDragAndDropCommand(domain, owner, location, operations,
				operation, collection);
	}

}
