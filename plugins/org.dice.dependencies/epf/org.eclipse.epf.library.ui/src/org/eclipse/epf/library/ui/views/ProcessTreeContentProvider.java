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
package org.eclipse.epf.library.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The content provider for a process tree.
 * <p>
 * Displays the tree in the following hierarchy:
 * <ul>
 * <li>method_plugin_1</li>
 * <ul>
 * <li>"Capability Patterns" UI folder</li>
 * <ul>
 * <li>capability_pattern_1</li>
 * <li>capability_pattern_2</li>
 * </ul>
 * </li>
 * <li>"Delivery Processes" UI folder</li>
 * <ul>
 * <li>delivery_process_1</li>
 * <li>delivery_process_2</li>
 * </ul>
 * </li>
 * </ul>
 * </li>
 * <li>method_plugin_2</li>
 * <ul>
 * <li>"Capability Patterns" UI folder</li>
 * </li>
 * <li>"Delivery Processes" UI folder</li>
 * </li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ProcessTreeContentProvider implements ITreeContentProvider {

	protected static final String CAPABILITY_PATTERNS = LibraryUIResources.capabilityPattern_text_plural;

	protected static final String DELIVERY_PROCESSES = LibraryUIResources.deliveryProcess_text_plural;

	protected static final Object[] EMPTY_LIST = new Object[0];

	protected Map<MethodPlugin, ProcessTreeUIFolder> capabilityPatternUIFolders = new HashMap<MethodPlugin, ProcessTreeUIFolder>();

	protected Map<MethodPlugin, ProcessTreeUIFolder> deliveryProcessUIFolders = new HashMap<MethodPlugin, ProcessTreeUIFolder>();

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MethodLibrary) {
			List<Object> children = new ArrayList<Object>();
			List<MethodPlugin> plugins = ((MethodLibrary) parentElement)
					.getMethodPlugins();
			for (Iterator<MethodPlugin> it = plugins.iterator(); it.hasNext();) {
				MethodPlugin plugin = it.next();
				List<Process> processes = TngUtil.getAllProcesses(plugin);
				if (processes.size() > 0) {
					children.add(plugin);
				}
			}
			return children.toArray();
		} else if (parentElement instanceof MethodPlugin) {
			MethodPlugin plugin = (MethodPlugin) parentElement;

			ProcessTreeUIFolder capabilityPatternUIFolder = capabilityPatternUIFolders
					.get(plugin);
			if (capabilityPatternUIFolder == null) {
				capabilityPatternUIFolder = new ProcessTreeUIFolder(
						CAPABILITY_PATTERNS,
						ExtendedImageRegistry.getInstance().getImage(LibraryEditPlugin.INSTANCE
								.getImage("full/obj16/CapabilityPatterns")), parentElement); //$NON-NLS-1$
				capabilityPatternUIFolders.put(plugin,
						capabilityPatternUIFolder);
			}

			ProcessTreeUIFolder deliveryProcessUIFolder = deliveryProcessUIFolders
					.get(plugin);
			if (deliveryProcessUIFolder == null) {
				deliveryProcessUIFolder = new ProcessTreeUIFolder(
						DELIVERY_PROCESSES,
						ExtendedImageRegistry.getInstance().getImage(LibraryEditPlugin.INSTANCE
								.getImage("full/obj16/DeliveryProcesses")), parentElement); //$NON-NLS-1$
				deliveryProcessUIFolders.put(plugin, deliveryProcessUIFolder);
			}

			return new Object[] { capabilityPatternUIFolder,
					deliveryProcessUIFolder };
		} else if (parentElement instanceof ProcessTreeUIFolder) {
			ProcessTreeUIFolder uiFolder = (ProcessTreeUIFolder) parentElement;
			MethodPlugin plugin = (MethodPlugin) uiFolder.getParent();
			if (uiFolder.getName() == CAPABILITY_PATTERNS) {
				return LibraryServiceUtil.getCapabilityPatterns(plugin)
						.toArray();
			} else {
				return LibraryServiceUtil.getDeliveryProcesses(plugin)
						.toArray();
			}
		}
		return EMPTY_LIST;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof ProcessTreeUIFolder) {
			return ((ProcessTreeUIFolder) element).getParent();
		} else if (element instanceof CapabilityPattern) {
			MethodPlugin plugin = UmaUtil
					.getMethodPlugin((CapabilityPattern) element);
			return capabilityPatternUIFolders.get(plugin);
		} else if (element instanceof DeliveryProcess) {
			MethodPlugin plugin = UmaUtil
					.getMethodPlugin((DeliveryProcess) element);
			return deliveryProcessUIFolders.get(plugin);
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		capabilityPatternUIFolders.clear();
		deliveryProcessUIFolders.clear();
	}

	public void dispose() {
	}

}