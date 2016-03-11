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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Process;


/**
 * Wrapper item provider for DescribleElement
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DescribableElementWrapperItemProvider extends
		DelegatingWrapperItemProvider implements IBSItemProvider,
		ITableItemLabelProvider {

	private int id;

	private Object topItem;

	protected boolean readOnly = true;
	
	protected boolean isInherited = false;
	
	protected boolean contributed = false;
	
	protected boolean isRollupChild = false;
	
	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public DescribableElementWrapperItemProvider(DescribableElement value,
			Object owner, AdapterFactory adapterFactory) {
		this(value, owner, null, CommandParameter.NO_INDEX, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param feature
	 * @param index
	 * @param adapterFactory
	 */
	public DescribableElementWrapperItemProvider(DescribableElement value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}
	
	protected DescribableElementWrapperItemProvider(Object value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}	

	/**
	 * Checks if this wrapper is read-only
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	
	/**
	 * @param readOnly
	 *            The readOnly to set.
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}	

	/**
	 * @return the contributed
	 */
	public boolean isContributed() {
		return contributed;
	}

	public boolean isInherited() {
		return isInherited;
	}
	
	public boolean isRollupChild() {
		return isRollupChild;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getId()
	 */
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setParent(java.lang.Object)
	 */
	public void setParent(Object obj) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getTopItem()
	 */
	public Object getTopItem() {
		Object parent = getParent(null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setTopItem(java.lang.Object)
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
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setRolledUp(boolean)
	 */
	public void setRolledUp(boolean b) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isFirstElement(java.lang.Object)
	 */
	public boolean isFirstElement(Object obj) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isLastElement(java.lang.Object)
	 */
	public boolean isLastElement(Object obj) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getFilteredBreakdownElements(java.lang.Object,
	 *      java.lang.Object)
	 */
	public List getFilteredBreakdownElements(Object activityObj, Object obj) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#moveUp(java.lang.Object,
	 *      com.ibm.library.edit.command.IActionManager)
	 */
	public void moveUp(Object obj, IActionManager actionMgr) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#moveDown(java.lang.Object,
	 *      com.ibm.library.edit.command.IActionManager)
	 */
	public void moveDown(Object obj, IActionManager actionMgr) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getEClasses()
	 */
	public Collection getEClasses() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getAttribute(java.lang.Object,
	 *      java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		DescribableElement e = (DescribableElement) TngUtil.unwrap(object);
		if (property == IBSItemProvider.COL_NAME) {
			return e.getName();
		} else if (property == IBSItemProvider.COL_ID) {
			return String.valueOf(id);
		} else if (property == IBSItemProvider.COL_PRESENTATION_NAME) {
			String name = e.getPresentationName();
			if (StrUtil.isBlank(name)) {
				name = e.getName();
			}
			return name;
		}
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setAttribute(java.lang.Object,
	 *      java.lang.String, java.lang.String)
	 */
	public void setAttribute(Object object, String property, String textValue) {

	}

	// /* (non-Javadoc)
	// * @see
	// com.ibm.library.edit.process.IColumnAware#setColumnIndexToNameMap(java.util.Map)
	// */
	// public void setColumnIndexToNameMap(Map map) {
	// if (map != null)
	// {
	// columnIndexToNameMap = map;
	// columnIndexToNameMapChanged = true;
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getPredecessors()
	 */
	public PredecessorList getPredecessors() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isExpanded()
	 */
	public Boolean isExpanded() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setExpanded(java.lang.Boolean)
	 */
	public void setExpanded(Boolean b) {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#createWrapper(java.lang.Object,
	 *      java.lang.Object, org.eclipse.emf.common.notify.AdapterFactory)
	 */
	protected IWrapperItemProvider createWrapper(Object value, Object owner,
			AdapterFactory adapterFactory) {
		IWrapperItemProvider wip = doCreateWrapper(value, owner, adapterFactory);
		if(wip instanceof DescribableElementWrapperItemProvider) {
			DescribableElementWrapperItemProvider wrapper = (DescribableElementWrapperItemProvider) wip;
			if (value instanceof DelegatingWrapperItemProvider) {
				wrapper.feature = ((DelegatingWrapperItemProvider) value)
				.getFeature();
			}
			wrapper.setReadOnly(readOnly);
			wrapper.isRollupChild = isRollupChild;
			wrapper.isInherited = isInherited;
			wrapper.contributed = isContributed();
		}		
		return wip;
	}
	
	protected IWrapperItemProvider doCreateWrapper(Object value, Object owner,
			AdapterFactory adapterFactory) {
		Object e = TngUtil.unwrap(value);
		if (e instanceof DescribableElement) {
			DescribableElementWrapperItemProvider wrapper = new DescribableElementWrapperItemProvider(
					(DescribableElement) e, owner, adapterFactory);
			return wrapper;
		}
		return super.createWrapper(value, owner, adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnImage(java.lang.Object,
	 *      int)
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		if (delegateItemProvider instanceof ITableItemLabelProvider) {
			return ((ITableItemLabelProvider) delegateItemProvider)
					.getColumnImage(object, columnIndex);
		}
		if (columnIndex == 0
				&& delegateItemProvider instanceof IItemLabelProvider) {
			return ((IItemLabelProvider) delegateItemProvider).getImage(object);
		}

		return null;
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
	 * @see com.ibm.library.edit.IMethodElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
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

	@Override
	public String toString() {
		Object o = TngUtil.unwrap(this);
		return super.toString() + " (wrapped object: " + o + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
