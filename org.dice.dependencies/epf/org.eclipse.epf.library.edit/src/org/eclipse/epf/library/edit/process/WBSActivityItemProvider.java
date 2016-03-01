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
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.WBSDragAndDropCommand;
import org.eclipse.epf.library.edit.process.command.WBSDropCommand;
import org.eclipse.epf.library.edit.util.ExposedAdapterFactory;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class WBSActivityItemProvider extends BSActivityItemProvider {

	/**
	 * @param adapterFactory
	 */
	public WBSActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createPhase()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createIteration()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createActivity()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createTaskDescriptor()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createMilestone()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.BSActivityItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object child) {
		child = TngUtil.unwrap(child);
		if(child instanceof Activity || child instanceof TaskDescriptor
				|| child instanceof Milestone) {
			return super.acceptAsChild(child);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#getObject(org.eclipse.epf.uma.Descriptor)
	 */
	protected Object getObject(Descriptor descriptor) {
		return ((TaskDescriptor) descriptor).getTask();
	}

	protected Command createDragAndDropCommand(EditingDomain domain,
			Object owner, float location, int operations, int operation,
			Collection<?> collection) {
		// System.out.println("ENTER:
		// com.ibm.library.edit.process.WBSActivityItemProvider#createDragAndDropCommand(domain,
		// owner, location, operations, operation, collection)");
		Command cmd = new WBSDragAndDropCommand(domain, owner, location,
				operations, operation, collection);

//		 System.out.println(" can execute: " + cmd.canExecute());
//		 System.out.println(" owner: " + owner);
//		 System.out.println(" collection: " + collection);
//		 System.out.println(" location: " + location);
//		 System.out.println(" operations: " + operations);
//		 System.out.println(" operation: " + operation);
		//        
		// System.out
		// .println("EXIT:
		// com.ibm.library.edit.process.WBSActivityItemProvider#createDragAndDropCommand(domain,
		// owner, location, operations, operation, collection)");
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	public IResourceAwareCommand createDropCommand(Object owner,
			List dropElements) {
		return new WBSDropCommand((Activity) owner, dropElements);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#acceptAsRolledUpChild(java.lang.Object)
	 */
	protected boolean acceptAsRolledUpChild(Object child) {
		Object obj = TngUtil.unwrap(child);
		if(obj instanceof TaskDescriptor) {
			return super.acceptAsRolledUpChild(obj);
		}
		return false;
	}

	// /* (non-Javadoc)
	// * @see
	// com.ibm.library.edit.process.BSActivityItemProvider#getColumnImage(java.lang.Object,
	// int)
	// */
	// public Object getColumnImage(Object object, int columnIndex) {
	// BreakdownElement e = (BreakdownElement)object;
	// String property = (String) columnIndexToNameMap.get(new
	// Integer(columnIndex));
	// Boolean b = null;
	// if(property == IBSItemProvider.COL_IS_EVENT_DRIVEN) {
	// b = e.getIsEventDriven();
	// }
	// else if(property == IBSItemProvider.COL_IS_ONGOING) {
	// b = e.getIsOngoing();
	// }
	// else if(property == IBSItemProvider.COL_IS_REPEATABLE) {
	// b = e.getIsRepeatable();
	// }
	// if(b != null) {
	// if(b.booleanValue()) {
	// return TngEditPlugin.INSTANCE.getImage("checked_box");
	// }
	// else {
	// return TngEditPlugin.INSTANCE.getImage("unchecked_box");
	// }
	// }
	// return super.getColumnImage(object, columnIndex);
	// }

	public Collection getEClasses() {
		return ProcessUtil.getWBSEclasses();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#refreshChildrenData(org.eclipse.emf.common.notify.Notification,
	 *      java.util.List)
	 */
	protected boolean refreshChildrenData(Notification notification,
			List affectedChildren) {
		// recalculate the IDs of breakdown elements and refresh them
		//
		Process topAct = (Process) getTopItem();
		AdapterFactory rootAdapterFactory = getRootAdapterFactory();

		ProcessUtil.updateIDs(rootAdapterFactory, topAct);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#doRefreshAffectedViewers()
	 */
	protected void doRefreshAffectedViewers() {
		Object proc = getTopItem();
		Object itemProvider = adapterFactory.adapt(proc,
				ITreeItemContentProvider.class);
		if (itemProvider instanceof BSActivityItemProvider) {
			((BSActivityItemProvider) itemProvider)
					.setRefreshAllIDsRequired(true);
		}

		ProcessUtil
				.refreshIDsInViewers((ExposedAdapterFactory) getRootAdapterFactory());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		updateCachedChildren(children);
		return children;
	}
	
	@Override
	protected boolean isWrappingRollupNeeded(Object object) {
		if(TngUtil.unwrap(object) instanceof TaskDescriptor) {
			return true;
		}
		return super.isWrappingRollupNeeded(object);
	}

	@Override
	protected Object createRollupWrapper(Object object, Object owner, AdapterFactory adapterFactory) {
		Object wrapper = super.createRollupWrapper(object, owner, adapterFactory);
		return new TaskDescriptorWrapperItemProvider(wrapper, owner, adapterFactory);
	}
	
	@Override
	protected ComposedBreakdownElementWrapperItemProvider createComposedWrapper(Object object, Object owner, AdapterFactory adapterFactory) {
		return new ComposedBreakdownElementWrapperItemProvider(object, owner, adapterFactory) {
			@Override
			protected boolean canMerge(String property) {
				return property == IBSItemProvider.COL_ID || property == IBSItemProvider.COL_PREDECESSORS
					|| property == IBSItemProvider.COL_MODEL_INFO;
			}
		};
	}	
	
}
