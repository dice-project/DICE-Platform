///------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;

/**
 * Manages wrappers of an item provider
 * 
 * @author Phong Nguyen Le - Feb 15, 2006
 * @since  1.0
 */
public class WrapperManager implements IDisposable {

	private HashMap valueToWrapperMap;
	private AdapterFactory adapterFactory;

	/**
	 * 
	 */
	public WrapperManager(AdapterFactory adapterFactory) {
		super();
		this.adapterFactory = adapterFactory;
	}
	
	public Object getWrapper(Object value) {
		if(valueToWrapperMap != null) {
			return valueToWrapperMap.get(value);
		}
		return null;
	}
	
	public Collection getWrappers() {
		if(valueToWrapperMap != null) {
			return valueToWrapperMap.values();
		}
		else {
			return Collections.EMPTY_LIST;
		}
	}
	
	/**
	 * Creates a new instance of this wrapper for the given value, owner, and adapter factory.
	 */
	protected IWrapperItemProvider createWrapper(Object value, Object owner, AdapterFactory adapterFactory) {
		return new DelegatingWrapperItemProvider(value, owner, null, CommandParameter.NO_INDEX, adapterFactory);
	}

	public void update(Collection delegateValues) {
		boolean changed = false;
		Set oldDelegateValues = delegateValues != null ? new HashSet(delegateValues) : Collections.EMPTY_SET;
		
		if (valueToWrapperMap == null && !delegateValues.isEmpty())
		{
			valueToWrapperMap = new HashMap();
		}
		
		// Wrap any new children and add them to the map. Remove each current child from the set of old children.
		//
		for (Iterator i = delegateValues.iterator(); i.hasNext(); )
		{
			Object child = i.next();
			
			if (!valueToWrapperMap.containsKey(child))
			{
				IWrapperItemProvider wrapper = createWrapper(child, this, adapterFactory);
				valueToWrapperMap.put(child, wrapper);
				changed = true;
			}
			oldDelegateValues.remove(child);
		}
		
		// Remove and dispose any wrappers for remaining old children.
		//
		if (!oldDelegateValues.isEmpty())
		{
			changed = true;
			
			for (Iterator i = oldDelegateValues.iterator(); i.hasNext(); )
			{
				Object child = i.next();
				
				IWrapperItemProvider wrapper = (IWrapperItemProvider)valueToWrapperMap.remove(child);
				if (wrapper != null)
				{
					wrapper.dispose();
				}
			}
		}
		
		// If any children were added or removed, reset the indices.
		if (changed)
		{
			int index = 0;
			for (Iterator i = delegateValues.iterator(); i.hasNext(); index++)
			{
				((IWrapperItemProvider)valueToWrapperMap.get(i.next())).setIndex(index);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
	 */
	public void dispose() {
	    if (valueToWrapperMap != null)
	    {
	      for (Iterator i = valueToWrapperMap.values().iterator(); i.hasNext();)
	      {
	        ((IDisposable)i.next()).dispose();
	      }
	      valueToWrapperMap.clear();
	      valueToWrapperMap = null;
	    }
	}

}
