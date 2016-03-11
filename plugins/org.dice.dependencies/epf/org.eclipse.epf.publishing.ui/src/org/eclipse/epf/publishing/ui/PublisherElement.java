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
package org.eclipse.epf.publishing.ui;

import java.net.URL;

import org.eclipse.epf.publishing.services.AbstractPublishManager;
import org.eclipse.epf.publishing.ui.wizards.AbstractPublishWizard;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;

/**
 * This class defines the attributes for a publisher extension point
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class PublisherElement {
	Bundle bundle;
	private String id;
	private String label;
	private String icon;
	private String wizardClass;
	private String managerClass;	
	
	/**
	 * constructor for PublisherElement extension point
	 * @param bundle Bundle the bundle that laod the element definition file
	 * @param id String
	 * @param label String
	 * @param icon String icon path
	 * @param wizardClass String the publishing wizard class. 
	 * This class must extends org.eclipse.epf.publishing.wizards.AbstractPublishWizard class
	 * @param managerClass String the publish mamager class. 
	 * this class must extends the org.eclipse.epf.publishing.services.AbstractPublishManager class.
	 */
	public PublisherElement(Bundle bundle, String id, String label, String icon, String wizardClass, String managerClass) {
		this.bundle = bundle; 
		this.id = id;
		this.label = label;
		this.icon = icon;
		this.wizardClass = wizardClass;
		this.managerClass = managerClass;		
	}
	
	/**
	 * get the publish wizard
	 * 
	 * @return AbstractPublishWizard
	 * @throws Exception
	 */
	public AbstractPublishWizard getPublishWizard() throws Exception {
		Class clazz = bundle.loadClass(wizardClass);
		return (AbstractPublishWizard) clazz.newInstance();
	}
	
	/**
	 * get the publish manager
	 * 
	 * @return AbstractPublishManager
	 * @throws Exception
	 */
	public AbstractPublishManager getPublishManager() throws Exception {
		Class clazz = bundle.loadClass(managerClass);
		return (AbstractPublishManager) clazz.newInstance();
	}
	
	/**
	 * get the id of the publisher
	 * 
	 * @return String
	 */
	public String getId() { 
		return id; 
	}
	
	/**
	 * get the label of the publisher
	 * 
	 * @return String
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * get the image descriptor for the publisher icon.
	 * 
	 * @return ImageDescriptor
	 */
	public ImageDescriptor getImageDescriptor() {
		if ( icon == null || icon.length() == 0 ) {
			return null;
		}
		
		try {
			URL installURL = bundle.getEntry("/"); //$NON-NLS-1$
			URL iconURL = new URL(installURL, icon);
			return ImageDescriptor.createFromURL(iconURL);
		} catch (Exception e) {
		}
		
		return null;
	}
}
