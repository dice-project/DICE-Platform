/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.edit.breakdownelement;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * @author skannoor
 *
 */
public class ActivityItemProvider extends
		org.eclipse.epf.uma.provider.ActivityItemProvider {

	public ActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasChildren(Object object) {
		return false;
	}
	
	@Override
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_Activity_type")); //$NON-NLS-1$
	}
	
	@Override
	public Object getImage(Object object) {
		Object img = TngUtil.getImage(object);
		return img != null ? img : super.getImage(object);
	}
	
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		return;
	}
}
