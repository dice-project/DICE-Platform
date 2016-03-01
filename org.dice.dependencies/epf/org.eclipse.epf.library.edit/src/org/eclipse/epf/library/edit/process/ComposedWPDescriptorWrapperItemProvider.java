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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * @author Phong Nguyen Le - Feb 23, 2006
 * @since  7.0.1
 */
public class ComposedWPDescriptorWrapperItemProvider extends
		WorkProductDescriptorWrapperItemProvider {

	private List notifiers;

	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public ComposedWPDescriptorWrapperItemProvider(Object value,
			Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
		if(value instanceof BreakdownElementWrapperItemProvider) {
			BreakdownElementWrapperItemProvider wrapper = ((BreakdownElementWrapperItemProvider)value);
			readOnly = wrapper.isReadOnly();
			isInherited = wrapper.isInherited;
		}
		isRollupChild = true;
	}

	/**
	 * Adds work product descriptor to the notifier list of this wrapper. The given work product descriptor
	 * must be linked to the same work product as the delegate value is 
	 * 
	 * @param object the work product descriptor or its wrapper
	 * 
	 */
	public void addWorkProductDescriptor(Object object) {
		WorkProductDescriptor wpd = (WorkProductDescriptor)TngUtil.unwrap(object);
		WorkProductDescriptor delegateWpd = (WorkProductDescriptor) TngUtil.unwrap(getDelegateValue());
		if(delegateWpd == wpd) {
			return;
		}
		Object wp = ProcessUtil.getAssociatedElement(delegateWpd);
		if(wp == null || wp == wpd.getWorkProduct()) {
			Object itemProvider = getRootAdapterFactory().adapt(wpd, IStructuredItemContentProvider.class);
			if (itemProvider instanceof IChangeNotifier)
			{
				((IChangeNotifier)itemProvider).addListener(this);
				if(notifiers == null) {
					notifiers = new UniqueEList();
				}
				notifiers.add(itemProvider);
			}
		}	
		
		if(isInherited && object instanceof BreakdownElementWrapperItemProvider &&
				!((BreakdownElementWrapperItemProvider)object).isInherited) {
			this.isInherited = false;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider#dispose()
	 */
	public void dispose() {
		if(notifiers != null) {
			for (Iterator iter = notifiers.iterator(); iter.hasNext();) {
				IChangeNotifier notifier = (IChangeNotifier) iter.next();
				notifier.removeListener(this);
			}
			notifiers.clear();
		}
		
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		if(notifiers != null && notifiers.contains(notification.getNotifier())) {
			switch(notification.getEventType()) {
			case Notification.REMOVING_ADAPTER:
				if(notification.getOldValue() == this) {
					fireNotifyChanged(new ViewerNotification(notification, this, false, true));				
					return;
				}
				break;
			case Notification.SET:
				switch(notification.getFeatureID(WorkProductDescriptor.class)) {
				case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_ENTRY_STATE:
				case UmaPackage.WORK_PRODUCT_DESCRIPTOR__ACTIVITY_EXIT_STATE:
					fireNotifyChanged(wrapNotification(notification));
					return;
				}
				break;
			}
		}
		super.notifyChanged(notification);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider#getAttribute(java.lang.Object, java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		if(property == IBSItemProvider.COL_MODEL_INFO && getValue() instanceof ComposedBreakdownElementWrapperItemProvider) {
			String info = ((ComposedBreakdownElementWrapperItemProvider)getValue()).getAttribute(object, property);
			if(!StrUtil.isBlank(info)) {
				StringTokenizer tokens = new StringTokenizer(info, ","); //$NON-NLS-1$
				HashSet infoSet = new HashSet();
				StringBuffer infos = new StringBuffer();
				while(tokens.hasMoreTokens()) {
					String token = tokens.nextToken();
					if(infoSet.add(token)) {
						infos.append(token).append(',');
					}
				}
				infos.deleteCharAt(infos.length() - 1);
				info = infos.toString();
			}
			return info;
		}
		if(notifiers != null && !notifiers.isEmpty()
				&& (property == IBSItemProvider.COL_ENTRY_STATE || property == IBSItemProvider.COL_EXIT_STATE)) {
			StringBuffer strbuf = new StringBuffer();
			String str = super.getAttribute(object, property);
			if(!StrUtil.isBlank(str)) {
				strbuf.append(str);
			}
			for (Iterator iter = notifiers.iterator(); iter.hasNext();) {
				WorkProductDescriptorItemProvider itemProvider = (WorkProductDescriptorItemProvider) iter.next();
				str = itemProvider.getAttribute(itemProvider.getTarget(), property);
				if(!StrUtil.isBlank(str)) {
					if(strbuf.length() > 0) {
						strbuf.append(',');
					}
					strbuf.append(str);
				}
			}
			return strbuf.toString();
		}
		return super.getAttribute(object, property);
	}
	
}
