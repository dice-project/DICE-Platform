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
package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.library.edit.configuration.CategorizedProcessesItemProvider;
import org.eclipse.epf.library.edit.configuration.ProcessesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.edit.ui.IActionTypeProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Activity Filter for doing Process copy/extend
 * 
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ProcessActivityFilter extends DescriptorConfigurationFilter {

	private Activity activity = null;

	private int command = 0;

	private ActivityVariabilityFilter delegateFilter;

	public ProcessActivityFilter(MethodConfiguration config,
			TableViewer viewer, String tabName, Activity activity, int command) {
		super(config, viewer, tabName);
		this.activity = activity;
		this.command = command;
		delegateFilter = new ActivityVariabilityFilter(config, viewer, tabName, activity);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter#setHelper(org.eclipse.epf.library.edit.itemsfilter.FilterHelper)
	 */
	public void setHelper(FilterHelper helper) {
		super.setHelper(helper);
		delegateFilter.setHelper(helper);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter#accept(java.lang.Object)
	 */
	public boolean accept(Object obj) {
		// any activity can be deep-copied
		//
		if(command == IActionTypeProvider.DEEP_COPY && 
				(obj instanceof Activity || obj instanceof ProcessComponent)) {
			return true;
		}
		return super.accept(obj);
	}

	public boolean childAccept(Object obj) {
		if (obj instanceof ProcessesItemProvider) {
			return true;
		}
		if (obj instanceof CategorizedProcessesItemProvider) {
			return true;

		}
		
//		if ((obj instanceof Activity) || obj instanceof ProcessPackage) {
//			if (obj instanceof ProcessComponent) {
//				// if source and taget is same process, block it
//				Process proc = ((ProcessComponent) obj).getProcess();
//				if (!(this.command == IActionTypeProvider.COPY)) {
//					if (proc.equals(this.activity))
//						return false;
//				}
//			}
//			if (obj instanceof Process) {
//				// if source and taget is same process, block it
//				Process proc = (Process) obj;
//				if (!(this.command == IActionTypeProvider.COPY)) {
//					if (proc.equals(this.activity))
//						return false;
//				}
//			}
//
//			if (obj instanceof BreakdownElement) {
//				Process srcProc = TngUtil
//						.getOwningProcess((BreakdownElement) obj);
//				Process targetProc = TngUtil
//						.getOwningProcess((BreakdownElement) this.activity);
//				if (srcProc instanceof DeliveryProcess
//						&& targetProc instanceof CapabilityPattern) {
//					return false;
//				}
//			}
//			if (obj instanceof Process) {
//				Process proc = (Process) obj;
//				if (proc.equals(this.activity))
//					return false;
//			}
//
//			return true;
//		} else
//			return false;
		
		if(obj instanceof Activity || obj instanceof ProcessPackage) {
			switch(command) {
				case IActionTypeProvider.EXTEND:
					return delegateFilter.accept(obj);
				case IActionTypeProvider.COPY:
					// disallow copying from delivery process to capability pattern
					//
					Process srcProc = null;
					if (obj instanceof ProcessComponent) {
						// if source and taget is same process, block it
						srcProc = ((ProcessComponent) obj).getProcess();
					}
					else if (obj instanceof BreakdownElement) {
						BreakdownElement be = (BreakdownElement) obj;
						srcProc = TngUtil.getOwningProcess(be);
					}
					if(srcProc != null) {
						Process targetProc = TngUtil.getOwningProcess(this.activity);
						if (srcProc instanceof DeliveryProcess
								&& targetProc instanceof CapabilityPattern) {
							return false;
						}
						else {
							return true;
						}
					}
				default:
					return true;
			}
		}
		
		return false;
	}
}
