/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.config;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.IResourceRegistryHolder;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditorInternal;
import org.eclipse.graphiti.ui.internal.policy.IEditPolicyFactory;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This interface is the wrapping container around all providers and factories,
 * which determine this framework. So by having access to this interface, it is
 * possible to retrieve every information which might be necessary to build on
 * this framework.
 * <p>
 * All providers and factories have access to the IConfigurationProvider, to
 * which they belong (backward-pointer). This is necessary, because the
 * providers/factories are sometimes dependent on each other. However, this
 * prohibits the usage of the same instance of a provider/factory in different
 * IConfigurationProviders.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IConfigurationProvider extends IFeatureProviderHolder, IResourceRegistryHolder {

	/**
	 * Disposes this object and frees all resources. This object will be
	 * unusable afterwards.
	 */
	public void dispose();

	/**
	 * Returns true, if this object is already disposed.
	 * 
	 * @return true, if this object is already disposed.
	 */
	public boolean isDisposed();

	/**
	 * Returns the IEditPartFactory of this Model.
	 * 
	 * @return The IEditPartFactory of this Model.
	 */
	public IEditPartFactory getEditPartFactory();

	/**
	 * Returns the IEditPolicyFactory of this Model.
	 * 
	 * @return The IEditPolicyFactory of this Model.
	 */
	public IEditPolicyFactory getEditPolicyFactory();

	/**
	 * Returns the Diagram-Model to display.
	 * 
	 * @return The Diagram-Model to display.
	 */
	public Diagram getDiagram();

	// ================ ConnectionDecoratorTreeEditPart to (graphical)
	// environment ====================

	/**
	 * Sets the workbench-part of this configuration-provider. This method
	 * should be called, when a workbench-part is available for the
	 * configuration-provider. After setting the workbench-part it must not be
	 * changed again.
	 * <p>
	 * However, it must not be assumed, that this method is ever called. So the
	 * workbench-part might always be null. It is just an offer, to support
	 * possible further functionality.
	 * 
	 * @param workbenchPart
	 *            The workbench-part to set.
	 */
	public void setWorkbenchPart(IWorkbenchPart workbenchPart);

	/**
	 * Returns the workbench-part of this configuration-provider. Can return
	 * null.
	 * 
	 * @return The workbench-part of this configuration-provider. Can return
	 *         null.
	 */
	public IWorkbenchPart getWorkbenchPart();

	/**
	 * Returns the diagram type provider.
	 * 
	 * @return diagram type provider
	 */
	IDiagramTypeProvider getDiagramTypeProvider();

	/**
	 * Gets the diagram editor.
	 * 
	 * @return the editor which is connected with this configuration-provider
	 */
	DiagramEditorInternal getDiagramEditor();
}