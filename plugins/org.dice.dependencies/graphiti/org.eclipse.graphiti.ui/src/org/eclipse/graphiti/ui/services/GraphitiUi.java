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
package org.eclipse.graphiti.ui.services;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.ui.internal.platform.ExtensionManager;
import org.eclipse.graphiti.ui.internal.services.impl.ImageService;
import org.eclipse.graphiti.ui.internal.services.impl.UiLayoutService;


/**
 * The Class GraphitiUi.
 * 
 * This class is the main access point to all public Graphiti UI services. These
 * services can be used to work with eclipse platform stuff. E.g. deal with
 * images or platform extensions. Additionally, all services exposed by the graphiti plugin
 * are accessible here.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GraphitiUi {    

	final private static IImageService imageService = new ImageService();
	
	final private static IUiLayoutService uiLayoutService = new UiLayoutService();

	/**
	 * Provides the extension manager.
	 * 
	 * @return the extension manager
	 * 
	 * @see org.eclipse.graphiti.ui.services.IExtensionManager
	 */
	public static IExtensionManager getExtensionManager() {
		return ExtensionManager.getSingleton();
	}

	/**
	 * Provides the image service.
	 * 
	 * @return the image service
	 * 
	 * @see org.eclipse.graphiti.ui.services.IImageService
	 */
	public static IImageService getImageService() {
		return imageService;
	}

	/**
	 * Provides the ui layout service.
	 * 
	 * @return the ui layout service which comprises also the layout service
	 */
	public static IUiLayoutService getUiLayoutService() {
		return uiLayoutService;
	}
	
	/**
	 * 
	 * @return the full service for GraphicsAlgoritm's
	 */
	public static IGaService getGaService() {
		return Graphiti.getGaService();
	}

	/**
	 * 
	 * @return the full service for PictogramElement's
	 */
	public static IPeService getPeService() {
		return Graphiti.getPeService();
	}


	/**
	 * 
	 * @return the link service (for links between PictogramElement's and
	 *         BusinessObject's)
	 */
	public static ILinkService getLinkService() {
		return Graphiti.getLinkService();
	}
}
