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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.WorkBreakdownElement;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class BreakdownElementWrapperItemProvider extends
		DescribableElementWrapperItemProvider {

	private boolean isRolledUp;

	private PredecessorList predecessors;

	private Boolean expanded;
	
	protected BreakdownElementWrapperItemProvider(Object value,
			Object owner, AdapterFactory adapterFactory) {
		super(value, owner, null, CommandParameter.NO_INDEX, adapterFactory);
		if(value instanceof DescribableElementWrapperItemProvider) {
			DescribableElementWrapperItemProvider wrapper = (DescribableElementWrapperItemProvider) value;
			if (value instanceof DelegatingWrapperItemProvider) {
				feature = ((DelegatingWrapperItemProvider) value).getFeature();
			}
			setReadOnly(wrapper.isReadOnly());
			setRolledUp(wrapper.isRollupChild());
			isInherited = wrapper.isInherited;
			contributed = wrapper.contributed;
		}		
	}

	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public BreakdownElementWrapperItemProvider(BreakdownElement value,
			Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}
	
	/**
	 * @param activity
	 * @param owner
	 * @param feature
	 * @param index
	 * @param adapterFactory
	 */
	public BreakdownElementWrapperItemProvider(BreakdownElement value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
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
	 * @see com.ibm.library.edit.process.IBSItemProvider#isRolledUp()
	 */
	public boolean isRolledUp() {
		return isRolledUp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setRolledUp(boolean)
	 */
	public void setRolledUp(boolean b) {
		isRolledUp = b;
	}

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
	public void setAttribute(Object object, String property, String textValue) {
		ProcessUtil.setAttribute((WorkBreakdownElement) TngUtil.unwrap(object),
				property, textValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.DescribableElementWrapperItemProvider#doCreateWrapper(java.lang.Object, java.lang.Object, org.eclipse.emf.common.notify.AdapterFactory)
	 */
	protected IWrapperItemProvider doCreateWrapper(Object value, Object owner, AdapterFactory adapterFactory) {
		return IBreakdownElementWrapperItemProviderFactory.INSTANCE
				.createWrapper(value, owner, adapterFactory);
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

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getPredecessors()
	 */
	public PredecessorList getPredecessors() {
		if (predecessors == null) {
			Object obj = TngUtil.unwrap(this);
			if (obj instanceof WorkBreakdownElement) {
				predecessors = new PredecessorList(TngUtil
						.getBestAdapterFactory(adapterFactory), this);
			} else {
				predecessors = PredecessorList.EMPTY_LIST;
			}
		}
		return predecessors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#createCommand(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
	 *      org.eclipse.emf.edit.command.CommandParameter)
	 */
	public Command createCommand(Object object, EditingDomain domain,
			Class commandClass, CommandParameter commandParameter) {
		// System.out
		// .println("BreakdownElementWrapperItemProvider.createCommand():");
		// System.out.println(" object: " + object);
		// System.out.println(" commandClass: " + commandClass);
		// System.out.println(" commandParameter: " + commandParameter);
		return super.createCommand(object, domain, commandClass,
				commandParameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#getNewChildDescriptors(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
	 */
	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	public boolean isFirstElement(Object obj) {
		return true;
	}

	public boolean isLastElement(Object obj) {
		return true;
	}

	public List getFilteredBreakdownElements(Object activity, Object obj) {
		return null;
	}

	public void moveUp(Object obj, IActionManager actionMgr) {

	}

	public void moveDown(Object obj, IActionManager actionMgr) {

	}

	public Collection getEClasses() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#dispose()
	 */
	public void dispose() {
		if (predecessors != null) {
			predecessors.dispose();
		}

		super.dispose();
	}

}