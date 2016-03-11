//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.configuration;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 *  Base class managing configuration data
 *  Current sub class: SuppportingElementData
 *  To do: refactor ConfigurationData to be a sub class of it too.
 * 
 * @author Weiping Lu - Mar 24, 2008
 * @since 1.5
 */
public abstract class ConfigDataBase {

	public static boolean localDebug = false;
	private static boolean profiling = false;
	
	private MethodConfiguration config;
	
	private boolean needUpdateChanges = true;
	private ILibraryManager libraryManager; 
	private Adapter configListener;
	private ILibraryChangeListener libListener;
	private boolean enableUpdate = true;
	private boolean updatingChanges = false;		
	
	public ConfigDataBase(MethodConfiguration config) {
		this.config = config;
		
		configListener = new AdapterImpl() {
			public void notifyChanged(Notification msg) {
				if (isNeedUpdateChanges()) {
					return;
				}
				int type = msg.getEventType();
				if (		type == Notification.ADD
						|| 	type == Notification.ADD_MANY
						|| 	type == Notification.REMOVE
						||  type == Notification.REMOVE_MANY) {					
					setNeedUpdateChanges(true);
				}
			}
		};
		config.eAdapters().add(configListener);
				
		libListener = new ILibraryChangeListener() {
			public void libraryChanged(int option, Collection changedItems) {
				if (localDebug) {
					//System.out.println("LD> libraryChanged, option: " + option + ", " + changedItems);//$NON-NLS-1$//$NON-NLS-2$
				}				
				handleLibraryChange(option, changedItems);
			}
		};
		
		MethodLibrary library = LibraryServiceUtil.getMethodLibrary(config);
		libraryManager = LibraryService.getInstance().getLibraryManager(library);
		if(libraryManager != null) {
			libraryManager.addListener(libListener);
		}
	}
	
	protected void handleLibraryChange(int option, Collection changedItems) {
		setNeedUpdateChanges(true);
		
/*		if (isNeedUpdateChanges()) {
			return;
		}
		for (Iterator it = changedItems.iterator(); it.hasNext();) {
			Object item = it.next();
			if (item instanceof ContentCategory) {
				setNeedUpdateChanges(true);
				return;
			}
		}*/
	}
	
	public void dispose() {
		config.eAdapters().remove(configListener);
		libraryManager.removeListener(libListener);		
	}

	protected boolean isNeedUpdateChanges() {
		return needUpdateChanges;
	}

	public void setNeedUpdateChanges(boolean needUpdateChanges) {
		this.needUpdateChanges = needUpdateChanges;
		if (localDebug) {
			System.out.println("LD> setNeedUpdateChanges: " + needUpdateChanges);	//$NON-NLS-1$
		}
	}

	protected boolean isEnableUpdate() {
		return enableUpdate;
	}

	public void setEnableUpdate(boolean enableUpdate) {
		this.enableUpdate = enableUpdate;
		if (localDebug) {
			System.out.println("LD> setEnableUpdate: " + enableUpdate);	//$NON-NLS-1$
		}
	}

	public MethodConfiguration getConfig() {
		return config;
	}

	public void setConfig(MethodConfiguration config) {
		this.config = config;
	}
	
	protected void updateChanges() {
		if (! isEnableUpdate()) {
			return;
		}		
		if (! isNeedUpdateChanges()) {
			return;
		}
		if (isUpdatingChanges()) {
			return;			
		}
		
		long t = 0;
		if (profiling) {
			System.out.println("LD> updateChanges_() -> " + getType());	//$NON-NLS-1$
			t = System.currentTimeMillis();
		}
		if (localDebug) {
			System.out.println("LD> updateChanges(): " + getType());	//$NON-NLS-1$
		}
		updateChangeImpl();
		if (profiling) {
			t =  System.currentTimeMillis() - t;
			System.out.println("LD> updateChanges_() <- time: " + t + ", " + getType());	//$NON-NLS-1$//$NON-NLS-2$
			System.out.println("");										//$NON-NLS-1$
		}
		
	}
	
	protected abstract void updateChangeImpl();

	public boolean isUpdatingChanges() {
		return updatingChanges;
	}

	protected void setUpdatingChanges(boolean updatingChanges) {
		this.updatingChanges = updatingChanges;
		if (localDebug) {
			System.out.println("LD> setUpdatingChanges: " + updatingChanges + ", " + getType());//$NON-NLS-1$	//$NON-NLS-2$									
		}
	}
	
	private String getType() {
		if (this instanceof SupportingElementData) {
			return "SupportingElementData"; //$NON-NLS-1$
		} 

		return "";	//$NON-NLS-1$
	}

}
