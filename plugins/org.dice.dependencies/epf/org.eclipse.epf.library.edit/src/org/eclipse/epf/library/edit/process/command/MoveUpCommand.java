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
package org.eclipse.epf.library.edit.process.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.util.WbePropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkBreakdownElement;


/**
 * Command to move up the element in breakdown structure
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class MoveUpCommand extends AbstractCommand implements
		IResourceAwareCommand {
	private Activity activity;

	private Collection modifiedResources;

	private int elementLocation = 0;

	private int transferLocation = 0;

	private Object elementObj;

	private Collection eClasses;
	
	private boolean adjacent = false;
	
	private Map<WorkBreakdownElement, WorkBreakdownElement> globalPresentedAfterMap = new HashMap<WorkBreakdownElement, WorkBreakdownElement>();

	/**
	 * 
	 */
	public MoveUpCommand(Activity activity, Object elementObj,
			Collection eClasses) {
		super();
		this.activity = activity;
		this.elementObj = elementObj;
		this.eClasses = eClasses;

		this.modifiedResources = new HashSet();
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if (elementObj instanceof WorkBreakdownElement) {
			boolean completelyDone = handleWbeGlobalMove(activity,
					(WorkBreakdownElement) elementObj, true, globalPresentedAfterMap);
			if (completelyDone) {
				return;
			}
		}	
		
		List allElements = activity.getBreakdownElements();

		for (int i = 0; i < allElements.size(); i++) {
			Object obj = allElements.get(i);
			if (obj.equals(elementObj)) {
				elementLocation = i;
				break;
			}
		}
		for (int i = elementLocation - 1; i >= 0; i--) {
			Object obj = allElements.get(i);
			if (TngUtil.isEClassInstanceOf(eClasses, obj)) {
				transferLocation = i;
				break;
			}
		}
		
		BreakdownElement prev =  (BreakdownElement) allElements.get(transferLocation);		
		if(prev.getPresentedAfter() == elementObj) {
			adjacent = true;
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		if(adjacent) {
			// swap "presented-after" element
			//
			BreakdownElement prev =  (BreakdownElement) activity.getBreakdownElements().get(transferLocation);		
			BreakdownElement e = (BreakdownElement) elementObj;
			prev.setPresentedAfter(e.getPresentedAfter());
			e.setPresentedAfter(prev);
		}
		((EList) activity.getBreakdownElements()).move(transferLocation,
				elementLocation);
			
	}

	//return true if move is completely done - no need to do local move
	//return false if move is partially done - still need to do local move
	public static boolean handleWbeGlobalMove(Activity act, WorkBreakdownElement wbe, boolean up,
			Map<WorkBreakdownElement, WorkBreakdownElement> globalPresentedAfterMap ) {
		if (wbe instanceof TaskDescriptor
				&& DescriptorPropUtil.getDesciptorPropUtil().getGreenParent(
						(TaskDescriptor) wbe) != null) {
			return true;	//don't move for green customizing child
		}	
		
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				act, ITreeItemContentProvider.class);
		
		Collection<?> children = adapter.getChildren(act);
		if (! (children instanceof List)) {
			return false;
		}
		
		WbePropUtil propUtil = WbePropUtil.getWbePropUtil();
		
		boolean completelyDone = false;
		List<?> childList = (List<?>) children;
		
		Set<BreakdownElement> localSet = new HashSet<BreakdownElement>();		
		for (BreakdownElement be : act.getBreakdownElements()) {
			if (be instanceof WorkBreakdownElement) {
				if (be instanceof TaskDescriptor) {
					if (DescriptorPropUtil.getDesciptorPropUtil()
							.getGreenParent((TaskDescriptor) be) != null) {
						continue;
					}
				}
				WorkBreakdownElement w = (WorkBreakdownElement) be;
				
				localSet.add(w);
				globalPresentedAfterMap.put(w, propUtil.getGlobalPresentedAfter(w));
			}
		}
		
		if (localSet.isEmpty()) {
			return false;
		}
			
		WorkBreakdownElement globalPresentedAfter = act;
		for (int i = 0; i < childList.size(); i++) {
			Object child = childList.get(i);
			if (child == wbe) {
				Object newPrev = null;
				if (up) {
					if (i == 0) {
						break;
					}

					newPrev = i == 1 ? act : TngUtil.unwrap(childList.get(i - 2));
					Object prevItem = childList.get(i - 1);		
					Object nextItem = i + 1 < childList.size() ? childList
							.get(i + 1) : null;
					if (localSet.contains(prevItem)) {
						propUtil.setGlobalPresentedAfter(
								(WorkBreakdownElement) prevItem, wbe);
					} else {
						completelyDone = true;
					}
					if (nextItem != null && localSet.contains(nextItem)) {
						WorkBreakdownElement nextWbe = (WorkBreakdownElement) nextItem;
						prevItem = TngUtil.unwrap(prevItem);
						if (prevItem instanceof WorkBreakdownElement) {
							propUtil.setGlobalPresentedAfter(nextWbe,
									(WorkBreakdownElement) prevItem);
						} else {
							propUtil.setGlobalPresentedAfter(nextWbe, null);
						}
					}
				
				} else {
					if (i == childList.size() - 1) {
						break;
					}
					
					Object prevItem = i - 1 >= 0 ? childList.get(i - 1) : act;
					newPrev = childList.get(i + 1);
					if (localSet.contains(newPrev)) {
						prevItem = TngUtil.unwrap(prevItem);
						if (prevItem instanceof WorkBreakdownElement) {
							propUtil.setGlobalPresentedAfter(
									(WorkBreakdownElement) newPrev,
									(WorkBreakdownElement) prevItem);
						} else {
							propUtil.setGlobalPresentedAfter(
									(WorkBreakdownElement) newPrev, null);
						}
					} else {
						completelyDone = true;
						newPrev = TngUtil.unwrap(newPrev);
					}
					
					Object newNextItem = i + 2 < childList.size() ? childList
							.get(i + 2) : null;
					if (localSet.contains(newNextItem)) {
						propUtil.setGlobalPresentedAfter((WorkBreakdownElement) newNextItem, wbe);
					}

				}
				
				if (newPrev instanceof WorkBreakdownElement) {
					globalPresentedAfter = (WorkBreakdownElement) newPrev;
				}
				
				break;
			}
		}	
		propUtil.setGlobalPresentedAfter(wbe, globalPresentedAfter);

		if (! act.getBreakdownElements().isEmpty()) {
			((EList) act.getBreakdownElements()).move(0, 0);	//hmm ... cause notifying UI refresh
		}
		
		return completelyDone;
	}

	public void undo() {
		((EList) activity.getBreakdownElements()).move(elementLocation,
				transferLocation);
		if(adjacent) {
			// restore "presented-after" element
			//
			BreakdownElement prev =  (BreakdownElement) activity.getBreakdownElements().get(transferLocation);		
			BreakdownElement e = (BreakdownElement) elementObj;
			e.setPresentedAfter(prev.getPresentedAfter());
			prev.setPresentedAfter(e);
		}
		
		WbePropUtil propUtil = WbePropUtil.getWbePropUtil();
		if (!globalPresentedAfterMap.isEmpty()) {
			for (Map.Entry<WorkBreakdownElement, WorkBreakdownElement> entry : globalPresentedAfterMap
					.entrySet()) {
				propUtil.setGlobalPresentedAfter(entry.getKey(), entry.getValue());
			}
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {
		return modifiedResources;
	}
}
