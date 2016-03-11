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
package org.eclipse.epf.library.edit.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.utils.StrUtil;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class ComposedBreakdownElementWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {
	private List values = new ArrayList();
	
	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public ComposedBreakdownElementWrapperItemProvider(Object value,
			Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
		addElement(value);
	}
	
	public List getValues() {
		return values;
	}

	public void addElement(Object e) {
		getValues().add(e);
	}
	
	/**
	 * Decides if the values of the given property of all elements in this composed wrapper can be merged.
	 * @param property
	 * @return
	 */
	protected boolean canMerge(String property) {
		return property == IBSItemProvider.COL_MODEL_INFO;
	}
	
	@Override
	public String getAttribute(Object object, String property) {
		if(!values.isEmpty()) {
			if(canMerge(property)) {
				UniqueEList modelInfos = new UniqueEList();				
				for (Iterator iter = values.iterator(); iter.hasNext();) {
					Object o = iter.next();
					String info;
					if(o instanceof IBSItemProvider) {
						info = ((IBSItemProvider)o).getAttribute(o, property);
					}
					else {
						IBSItemProvider ip = (IBSItemProvider) adapterFactory.adapt(o, ITreeItemContentProvider.class);
						info = ip.getAttribute(o, property);
					}
					if(!StrUtil.isBlank(info)) {
						modelInfos.add(info);
					}
				}
				if(!modelInfos.isEmpty()) {
					StringBuffer modelInfo = new StringBuffer((String)modelInfos.get(0));
					int max = modelInfos.size();
					for (int i = 1; i < max; i++) {
						modelInfo.append(',').append(modelInfos.get(i));
					}
					return modelInfo.toString();
				}
				return ""; //$NON-NLS-1$
			}
		}
		return super.getAttribute(object, property);
	}
	
	@Override
	public boolean isInherited() {
		for(int i = values.size() - 1; i > -1; i--) {
			Object o = values.get(i);
			if(!(o instanceof DescribableElementWrapperItemProvider) 
					|| !((DescribableElementWrapperItemProvider)o).isInherited()) {
				return false;
			}
		}
		return true;
	}
}
