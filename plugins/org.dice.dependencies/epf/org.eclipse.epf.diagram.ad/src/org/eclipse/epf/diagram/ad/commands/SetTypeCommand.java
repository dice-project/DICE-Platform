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
package org.eclipse.epf.diagram.ad.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNode3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeNameEditPart;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class SetTypeCommand extends org.eclipse.emf.common.command.AbstractCommand implements
		IResourceAwareCommand {
	private Map<EModelElement, String> elementToNewTypeMap = new HashMap<EModelElement, String>();
	private Map<EModelElement, String> elementToOldTypeMap;
	private Map<View, String> viewToNewTypeMap = new HashMap<View, String>();
	private Map<View, String> viewToOldTypeMap;

	public Collection<Resource> getModifiedResources() {
		HashSet<Resource> modifiedResources = new HashSet<Resource>();
		for (EModelElement e : elementToNewTypeMap.keySet()) {
			Resource resource = e.eResource();
			if(resource != null) {
				modifiedResources.add(resource);
			}
		}
		return modifiedResources;
	}

	public void execute() {
		if(elementToOldTypeMap == null) {
			elementToOldTypeMap = new HashMap<EModelElement, String>();
		}
		else {
			elementToOldTypeMap.clear();
		}
		for (Map.Entry<EModelElement, String> entry : elementToNewTypeMap.entrySet()) {
			final EModelElement e = entry.getKey();
			final String type = entry.getValue();
			try {
				String oldType = BridgeHelper.getEAnnotationDetail(e, BridgeHelper.UMA_TYPE);
				TxUtil.runInTransaction(e, new Runnable() {

					public void run() {
						BridgeHelper.addEAnnotationType(e, type);
					}
					
				});
				elementToOldTypeMap.put(e, oldType);
			} catch (Exception ex) {
				DiagramCorePlugin.getDefault().getLogger().logError(ex);
				throw new WrappedException(ex);
			}
		}
		if(!viewToNewTypeMap.isEmpty()) {
			if(viewToOldTypeMap == null) {
				viewToOldTypeMap = new HashMap<View, String>();
			}
			else {
				viewToOldTypeMap.clear();
			}
			for (Map.Entry<View, String> entry : viewToNewTypeMap.entrySet()) {
				final View view = entry.getKey();
				final String newType = entry.getValue();
				final String oldType = view.getType();
				try {
					TxUtil.runInTransaction(view, new Runnable() {

						public void run() {
							String newNameType = String.valueOf(getViewNameType(Integer.parseInt(newType)));
							String oldNameType = String.valueOf(getViewNameType(Integer.parseInt(oldType)));
							view.setType(newType);
							// set new type for node names
							//
							for (Iterator iterator = view.getChildren().iterator(); iterator
									.hasNext();) {
								View child = (View) iterator.next();
								if(child.getType().equals(oldNameType)) {
									child.setType(newNameType);
								}
							}
						}
						
					});
					viewToOldTypeMap.put(view, oldType);
				} catch (Exception e) {
					DiagramCorePlugin.getDefault().getLogger().logError(e);
					throw new WrappedException(e);
				}
			}
		}
	}
	
	@Override
	public void undo() {
		if (elementToOldTypeMap != null && !elementToOldTypeMap.isEmpty()) {
			for (Map.Entry<EModelElement, String> entry : elementToOldTypeMap
					.entrySet()) {
				final EModelElement e = entry.getKey();
				final String type = entry.getValue();
				try {
					TxUtil.runInTransaction(e, new Runnable() {

						public void run() {
							BridgeHelper.addEAnnotationType(e, type);
						}
						
					});
				} catch (Exception ex) {
					DiagramCorePlugin.getDefault().getLogger().logError(ex);
					throw new WrappedException(ex);
				}
			}
			elementToOldTypeMap.clear();
		}
		if(viewToOldTypeMap != null && !viewToOldTypeMap.isEmpty()) {
			for (Map.Entry<View, String> entry : viewToOldTypeMap.entrySet()) {
				final View view = entry.getKey();
				final String oldType = entry.getValue();
				try {
					TxUtil.runInTransaction(view, new Runnable() {

						public void run() {
							String newNameType = String.valueOf(getViewNameType(Integer.parseInt(view.getType())));
							String oldNameType = String.valueOf(getViewNameType(Integer.parseInt(oldType)));
							view.setType(oldType);
							// restore old type for node names
							//
							for (Iterator iterator = view.getChildren().iterator(); iterator
									.hasNext();) {
								View child = (View) iterator.next();
								if(child.getType().equals(newNameType)) {
									child.setType(oldNameType);
								}
							}
						}
						
					});
				} catch (Exception e) {
					DiagramCorePlugin.getDefault().getLogger().logError(e);
					throw new WrappedException(e);
				}
			}
			viewToOldTypeMap.clear();
		}
	}

	public void redo() {
		execute();
	}
	
	@Override
	protected boolean prepare() {
		return !elementToNewTypeMap.isEmpty();
	}

	public void prepare(View view, EModelElement e, String type) {
		elementToNewTypeMap.put(e, type);
		int viewType = getViewType(type);
		if(viewType != -1) {
			viewToNewTypeMap .put(view, String.valueOf(viewType));
		}
	}
	
	@Override
	public void dispose() {
		if(elementToNewTypeMap != null) {
			elementToNewTypeMap.clear();
		}
		if(elementToOldTypeMap != null) {
			elementToOldTypeMap.clear();
		}
		if(viewToNewTypeMap != null) {
			viewToNewTypeMap.clear();
		}
		if(viewToOldTypeMap != null) {
			viewToOldTypeMap.clear();
		}
	}
	
	public static int getViewType(String elementType) {
		if(BridgeHelper.UMA_ACTIVITY.equals(elementType)) {
			return StructuredActivityNodeEditPart.VISUAL_ID;
		}
		else if(BridgeHelper.UMA_PHASE.equals(elementType)) {
			return StructuredActivityNode2EditPart.VISUAL_ID;
		}
		else if(BridgeHelper.UMA_ITERATION.equals(elementType)) {
			return StructuredActivityNode3EditPart.VISUAL_ID;
		}
		else if(BridgeHelper.UMA_MILESTONE.equals(elementType)) {
			return ActivityParameterNode2EditPart.VISUAL_ID;			
		}
		else if(BridgeHelper.UMA_TASK_DESCRIPTOR.equals(elementType)) {
			return ActivityParameterNodeEditPart.VISUAL_ID;
		}
		return -1;
	}	
	
	public static int getViewNameType(int viewType) {
		switch(viewType) {
		case StructuredActivityNodeEditPart.VISUAL_ID:
			return StructuredActivityNodeNameEditPart.VISUAL_ID;
		case StructuredActivityNode2EditPart.VISUAL_ID:
			return StructuredActivityNodeName2EditPart.VISUAL_ID;
		case StructuredActivityNode3EditPart.VISUAL_ID:
			return StructuredActivityNodeName3EditPart.VISUAL_ID;
		case ActivityParameterNodeEditPart.VISUAL_ID:
			return ActivityParameterNodeNameEditPart.VISUAL_ID;			
		case ActivityParameterNode2EditPart.VISUAL_ID:
			return ActivityParameterNodeName2EditPart.VISUAL_ID;
		}
		return -1;
	}
}
