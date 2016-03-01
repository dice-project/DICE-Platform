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
package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.configuration.closure.ElementDependencyError;
import org.eclipse.epf.library.edit.navigator.ConfigContentPackageItemProvider.LeafElementsItemProvider;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ConfigPackageLabelProvider extends LabelProvider {

	private ConfigurationClosure closure = null;
	private ConfigPackageContentProvider contentProvider;
	private AdapterFactoryLabelProvider afProvider;

	/**
	 * Creates an instance
	 * @param contentProvider
	 */
	public ConfigPackageLabelProvider(
			ConfigPackageContentProvider contentProvider) {
		this.contentProvider = contentProvider;
		afProvider = new AdapterFactoryLabelProvider(contentProvider
				.getAdapterFactory());
	}

	private ElementDependencyError getFirstChildError(Object e) {
		ElementDependencyError error = null;
		Object[] children = contentProvider.getChildren(e);
		if (children == null || children.length == 0) {
			return null;
		}

		for (int i = 0; i < children.length; i++) {
			Object object = children[i];
			if (object instanceof ItemProviderAdapter) {
				return getFirstChildError(object);
			} else {
				error = closure.getError(object);
				if (error != null) {
					return error;
				}
			}
		}

		return error;
	}

	/**
	 * This implements {@link ILabelProvider}.getImage by forwarding it to
	 * an object that implements
	 * {@link IItemLabelProvider#getImage IItemLabelProvider.getImage}
	 */
	public Image getImage(Object object) {
		if (closure != null) {

			ElementDependencyError error = null;
			if (object instanceof ItemProviderAdapter) {
				error = getFirstChildError(object);
			} else {
				error = closure.getError(object);
			}

			if (error != null) {
				if (error.isError() || error.isChildError()) {
					return PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJS_ERROR_TSK); 
				} else if (error.isWarning() || error.isChildWarning()) {
					return PlatformUI.getWorkbench().getSharedImages()
							.getImage(ISharedImages.IMG_OBJS_WARN_TSK);
				}
			}
		}

		return afProvider.getImage(object);
	}

	protected Image getImageFromObject(Object object) {
		return ExtendedImageRegistry.getInstance().getImage(object);
	}

	/**
	 * This implements {@link ILabelProvider}.getText by forwarding it to
	 * an object that implements
	 * {@link IItemLabelProvider#getText IItemLabelProvider.getText}
	 */
	public String getText(Object object) {
		if (object instanceof LeafElementsItemProvider) {
			return AuthoringUIResources.LeafElementsNode_text;
		}
		if (object instanceof ProcessComponent) {
			Process proc = (Process) ((ProcessComponent) object)
					.getProcess();
			if (proc != null)
				return proc.getPresentationName();
			else
				// if process is null, return processcomponent name
				return ((ProcessComponent) object).getName();
		} else
			return afProvider.getText(object);
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#dispose()
	 */
	public void dispose() {
		super.dispose();

		contentProvider = null;
		afProvider = null;
	}

	public void setClosure(ConfigurationClosure closure) {
		this.closure = closure;
	}

}
