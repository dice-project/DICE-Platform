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
package org.eclipse.epf.authoring.ui.editors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.forms.ConfigurationPage;
import org.eclipse.epf.authoring.ui.providers.ConfigurationEditorDefaultPageProvider;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;


/**
 * The Method Configuration editor.
 * 
 * @author Shilpa Toraskar
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationEditor extends MethodElementEditor implements IGotoMarker {

	/**
	 * The editor ID.
	 */
	public static final String EDITOR_ID = ConfigurationEditor.class.getName();
	protected static IMethodElementEditorPageProviderExtension defaultConfigPageProvider;

	ConfigurationPage configPage = null;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationEditor() {
		super();
	}

	/**
	 * Returns the method configuration associated with this editor.
	 */
	public MethodConfiguration getConfiguration() {
		return ((ConfigurationEditorInput) super.getEditorInput())
				.getConfiguration();
	}

	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (input instanceof FileEditorInput) {
			// probably opened from Problems View
			// create a ConfigurationEditorInput
			Resource resource = RefreshJob.getInstance().getResource(((FileEditorInput)input).getFile());
			MethodElement element = PersistenceUtil.getMethodElement(resource);
			if (element instanceof MethodConfiguration) {
				input = new ConfigurationEditorInput((MethodConfiguration)element);
			}
		}

		super.init(site, input);
	}
	
	
	protected IMethodElementEditorPageProviderExtension getDefaultPageProvider() {
		if (defaultConfigPageProvider == null) {
			defaultConfigPageProvider = new ConfigurationEditorDefaultPageProvider();
		}
		return defaultConfigPageProvider;
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	protected void addPages() {
		// first get original list
		Map<Object,String> pageMap = getDefaultPageProvider().getPages(new LinkedHashMap<Object,String>(), this, elementObj);
		
		// let extensions modify
		List<IMethodElementEditorPageProviderExtension> pageProviders = getAllPageProviders();
		if (pageProviders != null && pageProviders.size() > 0) {
			for (IMethodElementEditorPageProviderExtension extension : pageProviders) {
				pageMap = extension.getPages(pageMap, this, elementObj);
			}
		}
		// now add pages
		try {
			for (Map.Entry<Object, String> pageEntry : pageMap.entrySet()) {
				Object page = pageEntry.getKey();
				String name = pageEntry.getValue();
				int index = -1;
				if (page instanceof Control) {
					index = addPage((Control)page);
				} else if (page instanceof IFormPage) {
					index = addPage((IFormPage)page);
				} else if (page instanceof IEditorPart) {
					index = addPage((IEditorPart)page, getEditorInput());
				}
				if (name != null) {
					setPageText(index, name);
				}
				
				if (page instanceof ConfigurationPage) {
					configPage = (ConfigurationPage)page;
				}
			}
		} catch (PartInitException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.editors.MethodElementEditor#createInput(org.eclipse.epf.uma.MethodElement)
	 */
	protected IEditorInput createInput(MethodElement e) {
		if(e instanceof MethodConfiguration) {
			return new ConfigurationEditorInput((MethodConfiguration) e);
		}
		return null;
	}
	
	private List<Listener> setFocusListeners = new ArrayList<Listener>();
	
	
	/**
	 * Add given listener to list of focus listeners
	 * @param lis
	 */
	public void addToSetFocusLiseners(Listener lis) {
		setFocusListeners.add(lis);
	}
	
    /**
     * @see org.eclipse.ui.part.MultiPageEditorPart#setFocus()
     */
    public void setFocus() {    	
    	Event e = new Event();
    	e.data = getActivePageInstance();   	
    	for (int i=0; i<setFocusListeners.size(); i++) {
    		Listener lis = (Listener) setFocusListeners.get(i);
    		lis.handleEvent(e);
    	}
    }

    public void gotoMarker(IMarker marker) {
    	// activate config page
    	setActivePage(1);
    	if (configPage != null) {
    		configPage.gotoMarker(marker);
    	}
    }
    
    public void doQuickFix(IMarker marker) {
    	configPage.doQuickFix(marker);
    }
    
	public void doSave(IProgressMonitor monitor) {
    	super.doSave(monitor);
    	configPage.showErrorsOnSave();
    }
    
}
