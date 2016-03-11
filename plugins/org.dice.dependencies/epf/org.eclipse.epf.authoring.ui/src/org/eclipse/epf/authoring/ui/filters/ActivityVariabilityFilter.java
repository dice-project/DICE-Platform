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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.configuration.CategorizedProcessesItemProvider;
import org.eclipse.epf.library.edit.configuration.ProcessesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.jface.viewers.TableViewer;

/**
 * Activity variability filter
 * 
 * @author Shilpa Toraskar
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ActivityVariabilityFilter extends ExProcessAuthoringConfigurator {

	private Activity activity = null;
	
	private List treeList = new ArrayList();
	private List generalizers = new ArrayList();
	private List baseelements = new ArrayList();
	private List badelements = new ArrayList();

	public ActivityVariabilityFilter(MethodConfiguration config,
			TableViewer viewer, String tabName, Activity activity) {
		super(config, viewer);
		this.activity = activity;
		
		// load the parentlist
		AdapterFactory adapter = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
		FilterUtil.getSubTree(adapter, activity, treeList);
		FilterUtil.getGeneralizers(treeList, generalizers);
		FilterUtil.getVariabilityBase(treeList, baseelements);
		
		FilterUtil.getSubTree(adapter, generalizers, badelements);
		FilterUtil.getSubTree(adapter, baseelements, badelements);
	}
	
	public boolean accept(Object obj) {
		// TODO Auto-generated method stub
		if (!super.accept(obj)) return false;
		return childAccept(obj);
	}

	public boolean childAccept(Object obj) {
		if (obj instanceof ProcessesItemProvider) {
			return true;
		}
		if (obj instanceof CategorizedProcessesItemProvider) {
			return true;

		}
		if ((obj instanceof Activity) || obj instanceof ProcessPackage) {
			if ((obj instanceof Activity)){
				if(this.activity.equals((Activity) obj)) 
					return false;
				VariabilityElement e = ((Activity)obj).getVariabilityBasedOnElement();
				if(e != null && activity.equals(e))
					return false;
			}
			if (obj instanceof BreakdownElement) {
				Process srcProc = TngUtil
						.getOwningProcess((BreakdownElement) obj);
				Process targetProc = TngUtil
						.getOwningProcess((BreakdownElement) this.activity);
				if (srcProc instanceof DeliveryProcess
						&& targetProc instanceof CapabilityPattern) {
					return false;
				}
			}
			if(obj instanceof Activity){
				if(TngUtil.contains(generalizers, obj) 
				//Comment this out due to 182531, other cases missing this check here
				//should be compensated by circular check after filter selection.
				//Better fix is to include the circular check into the filter logic.		
				// || TngUtil.contains(baseelements, obj)						
				//		||TngUtil.contains(badelements, obj)
						){
					return false;
				}
			}
			
//			if (obj instanceof Process) {
//				Process proc = (Process) obj;
//				if (proc.equals(TngUtil.getOwningProcess(this.activity)))
//					return false;
//			}
//			if(obj instanceof Activity){
//				if(TngUtil.contains(contributors, obj)) return false;
//				if(TngUtil.contains(variabilitybases, obj)) return false;
//				if(obj.equals(FilterUtil.getParentActivity(TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory(), 
//						activity))) return false;
//			}
			
			return true;
		} else
			return false;
	}
	
	
}
