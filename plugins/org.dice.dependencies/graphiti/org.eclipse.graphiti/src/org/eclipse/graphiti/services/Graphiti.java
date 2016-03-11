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
package org.eclipse.graphiti.services;

import org.eclipse.graphiti.internal.services.impl.CreateServiceImpl;
import org.eclipse.graphiti.internal.services.impl.GaServiceImpl;
import org.eclipse.graphiti.internal.services.impl.LayoutServiceImpl;
import org.eclipse.graphiti.internal.services.impl.LinkServiceImpl;
import org.eclipse.graphiti.internal.services.impl.PeServiceImpl;

/**
 * This class is the main access point to all public Graphiti services. These
 * services can be used to work on your pictogram model. E.g. there are services
 * for creation, layout and query.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class Graphiti {
	private static IGaService gaService;

	private static IPeService peService;

	private static ILayoutService layoutService;

	private static ICreateService createService;

	private static ILinkService linkService;

	/**
	 * 
	 * @return the creation service for GraphicsAlgoritm's
	 */
	public static IGaCreateService getGaCreateService() {
		return getGaService();
	}

	/**
	 * 
	 * @return the layout service for GraphicsAlgoritm's
	 */
	public static IGaLayoutService getGaLayoutService() {
		return getGaService();
	}

	/**
	 * 
	 * @return the full service for GraphicsAlgoritm's
	 */
	public static IGaService getGaService() {
		if (gaService == null) {
			gaService = new GaServiceImpl();
		}
		return gaService;
	}

	/**
	 * 
	 * @return the creation service for PictogramElement's
	 */
	public static IPeCreateService getPeCreateService() {
		return getPeService();
	}

	/**
	 * 
	 * @return the layout services for PictogramElement's
	 */
	public static IPeLayoutService getPeLayoutService() {
		return getPeService();
	}

	/**
	 * 
	 * @return the full service for PictogramElement's
	 */
	public static IPeService getPeService() {
		if (peService == null) {
			peService = new PeServiceImpl();
		}
		return peService;
	}

	/**
	 * 
	 * @return the layout service for PictogramElement's and GraphicsAlgorithm's
	 */
	public static ILayoutService getLayoutService() {
		if (layoutService == null) {
			layoutService = new LayoutServiceImpl();
		}
		return layoutService;
	}

	/**
	 * 
	 * @return the creation service for PictogramElement's and
	 *         GraphicsAlgorithm's
	 */
	public static ICreateService getCreateService() {
		if (createService == null) {
			createService = new CreateServiceImpl();
		}
		return createService;
	}

	/**
	 * 
	 * @return the link service (for links between PictogramElement's and
	 *         BusinessObject's)
	 */
	public static ILinkService getLinkService() {
		if (linkService == null) {
			linkService = new LinkServiceImpl();
		}
		return linkService;
	}
}
