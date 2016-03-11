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
package org.eclipse.epf.authoring.ui.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.IMarkerAttributeContributer;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.configuration.closure.IConfigurationError;
import org.eclipse.epf.library.edit.navigator.ConfigurationsItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.markers.MarkerViewUtil;

/**
 *  Helper class to handle Configuration error markers
 * @author Jeff Hardy
 * @author Jinhua Xi
 *
 */
public class ConfigurationMarkerHelper {	
	public static final ConfigurationMarkerHelper INSTANCE = new ConfigurationMarkerHelper();
	public static final String ATTR_ERROR_ID = "errorID"; //$NON-NLS-1$
	public static final String ATTR_CONFIG_GUID = "configGuid"; //$NON-NLS-1$
	public static final String ATTR_MESSAGE_ID = "messageId"; //$NON-NLS-1$
	public static final String ATTR_ERROR_ELEMENT_GUID = "elementGuid"; //$NON-NLS-1$
	public static final String ATTR_CAUSE_ELEMENT_GUID = "causeGuid"; //$NON-NLS-1$

	// marker ID
	public static final String MARKER_ID = "org.eclipse.epf.authoring.ui.configuration"; //$NON-NLS-1$
	private int maxMarkerCount = 2000;
	
//	private Map<ElementDependencyError, IMarker> errorMarkerMap = new HashMap<ElementDependencyError, IMarker>();
	
	// private constructor
	private ConfigurationMarkerHelper() {
	}
	
	private static class ContainerMap extends HashMap<Object, Integer> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Map<MethodConfiguration, Map<String, IMarker>> configMakerMap;		 

		public ContainerMap() {
			super();
			initMap();
			LibraryService.getInstance().addListener(new LibraryServiceListener() {
				public void libraryOpened(MethodLibrary library) {
					initMap();
				}
				
				public void libraryClosed(MethodLibrary library) {
					clear();
				}
				
				public void libraryReopened(MethodLibrary library) {
					initMap();
				}				
			});
		}
		
		private void initMap() {
			clear();			
			configMakerMap =  new HashMap<MethodConfiguration, Map<String, IMarker>>();
			
			// fill this set with containers of marked objects
			//
			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
			if(lib != null) {
				IProject prj = MethodLibraryProject.findProject(lib);
				if(prj != null) {
					try {
						IMarker[] markers = prj.findMarkers(INSTANCE.getMarkerID(), false, IResource.DEPTH_INFINITE);
						for (int i = 0; i < markers.length; i++) {
							IMarker marker = markers[i];
							String guid = (String) marker.getAttribute(ATTR_CONFIG_GUID);
							MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
							if(e != null) {
								// remove its containers from containersOfMarkedObjects
								//
								markContainers(e);
							}
						}
					} catch (CoreException e) {
					}
				}
			}
		}
		
		private void increment(Object object) {
			Integer count;
			if (containsKey(object)) {
				count = get(object);
			} else {
				count = new Integer(0);
			}
			count++;
			put(object, count);
		}

		private void decrement(Object object) {
			if (containsKey(object)) {
				Integer count = get(object);
				if (count == 1) {
					// remove object
					remove(object);
				} else {
					count--;
					put(object, count);
				}
			}
		}
		
		private void markContainers(MethodElement e) {
			for(EObject container = e.eContainer(); container != null; container = container.eContainer()) {
				increment(container);
			}
		}

		private void unmarkContainers(MethodElement e) {
			for(EObject container = e.eContainer(); container != null; container = container.eContainer()) {
				decrement(container);
			}
		}
		
		public Map<MethodConfiguration, Map<String, IMarker>> getConfigMakerMap() {
			return configMakerMap;
		}
	}
	
	private static ContainerMap containersOfMarkedObjects = new ContainerMap();	
	
	
	protected String getMarkerID() {
		return MARKER_ID;
	}
	                                                                                                                             
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.ui.MarkerHelper#getFile(org.eclipse.emf.common.util.URI)
	 */
	protected IFile getFile(URI uri) {
		if(uri.isFile()) {
			return (IFile) FileManager.getResourceForLocation(uri.toFileString());
		}
	    String scheme = uri.scheme();
	    if ("platform".equals(scheme) && uri.segmentCount() > 1 && "resource".equals(uri.segment(0))) //$NON-NLS-1$ //$NON-NLS-2$
	    {
	      StringBuffer platformResourcePath = new StringBuffer();
	      for (int j = 1, size = uri.segmentCount(); j < size; ++j)
	      {
	        platformResourcePath.append('/');
	        platformResourcePath.append(URI.decode(uri.segment(j)));
	      }
	      return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourcePath.toString()));
	    }
	    return null;
	}
		
	/**
	 * Used by decorators to determine to put an error tick on the Object
	 * @param object
	 * @return
	 */
	public static boolean isInvalid(Object object) {
		if (object instanceof ConfigurationsItemProvider) {
			Collection children = ((ConfigurationsItemProvider)object).getChildren(object);
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object obj = TngUtil.unwrap(iter.next());
				if(containersOfMarkedObjects.containsKey(obj)) {
					return true;
				}				
				if (obj instanceof MethodConfiguration) {
					if (INSTANCE.hasMarkers((MethodConfiguration)obj)) {
						return true;
					}
				}
			}
			return false;
		} else if (object instanceof MethodConfiguration) {
			if (INSTANCE.hasMarkers((MethodConfiguration)object)) {
				return true;
			}
		}
		return containersOfMarkedObjects.containsKey(object);
	}
		    
    public void deleteMarker(MethodConfiguration config, IConfigurationError error) {
    	try {
	    	IMarker marker = getMarker(config, error);
	    	if (marker != null) {
	    		marker.delete();
	    		containersOfMarkedObjects.unmarkContainers(config);
	    		unregisterMarker(config, error.getId());
	    	}
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
    }
	
    public IMarker createMarker(MethodConfiguration config, IConfigurationError error) {
//    	if (error.getErrorMethodElement() == null) return null;
//    	if (error.getCauseMethodElement() == null) return null;
    	
    	IMarker existingMarker = getMarker(config, error);
    	if (existingMarker != null) {
    		adjustMarker(existingMarker, config, error);
    		return existingMarker;
    	}
    	try {
	    	if (getMarkerCount(config) >= maxMarkerCount) {
	    		return null;
	    	}
	    	
	    	IResource resource = getIResource(config);
	    	if (resource != null && resource.exists()) {
	    		IMarker marker = resource.createMarker(getMarkerID());
	    		marker.setAttribute(IDE.EDITOR_ID_ATTR, "org.eclipse.epf.authoring.ui.editors.ConfigurationEditor"); //$NON-NLS-1$
	    		adjustMarker(marker, config, error);
	    		containersOfMarkedObjects.markContainers(config);
	    		registerNewMarker(config, error.getId(), marker);
	    		return marker;
	    	}
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
    }
    
    public void adjustMarker(IMarker marker, MethodConfiguration config, IConfigurationError error) {
    	if (config == null || error == null) return;
       	try {
       		if (marker == null) {
       			// try to find marker
       			marker = getMarker(config, error);
       		}
       		if (marker == null) return;
			marker.setAttribute(IMarker.SEVERITY, error.getSeverity());
	    	String message = getMessage(error);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.LINE_NUMBER, 0);
			marker.setAttribute(ATTR_CONFIG_GUID, config.getGuid());
			marker.setAttribute(ATTR_ERROR_ID, error.getId());
			marker.setAttribute(ATTR_MESSAGE_ID, error.getMessageId());
			
	    	if (error.getErrorMethodElement() != null) {
				marker.setAttribute(IMarker.LOCATION, TngUtil.getLabelWithPath(error.getErrorMethodElement()));
				marker.setAttribute(ATTR_ERROR_ELEMENT_GUID, error.getErrorMethodElement().getGuid());
				marker.setAttribute(MarkerViewUtil.NAME_ATTRIBUTE, error.getErrorMethodElement().getName());
	    	}
	    	if (error.getCauseMethodElement() != null) {
	    		marker.setAttribute(ATTR_CAUSE_ELEMENT_GUID, error.getCauseMethodElement().getGuid());
	    	}
	    	
			IMarkerAttributeContributer attAdder = ExtensionHelper.getMarkerAttributeContributer();
			if (attAdder != null) {
				attAdder.addAddtionalAttributes(marker, error);
			}
			
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
   }
    	
    public boolean hasMarker(MethodConfiguration config, IConfigurationError error) {
		if (getMarker(config, error) != null) {
			return true;
		}
   		return false;
    }
    
    /**                                                                                                
     * Returns whether the a maker with id equals to the return of {@link #getMarkerID()}              
     * is available in the IResource computed from the specified object.                               
     * @param object                                                                                   
     * @return boolean                                                                                 
     */
    public boolean hasMarkers(MethodConfiguration config) {
    	IResource resource = getIResource(config);
        if (resource != null && resource.exists()) {
        	try {
				IMarker[] markers = resource.findMarkers(getMarkerID(), true, IResource.DEPTH_ZERO);
				return markers.length > 0;
	  		} catch (CoreException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
        }
        return false;
    }
    
    public IMarker getMarker(MethodConfiguration config, IConfigurationError error) {
    	if (config == null || error == null) {
    		return null;
    	}
    	
   		return findMarker(config, error.getId());
    	    	
//    	try {
//	    	IResource resource = getIResource(config);                                                     
//	        if (resource != null) {                                                     
//				IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
//				for (int i = 0; i < markers.length; i++) {
//					IMarker marker = markers[i];
//					String markerErrorId = (String) marker.getAttribute(ATTR_ERROR_ID);
//					if (error.getId().equals(markerErrorId)) {
//						return marker;
//					}
//				}
//	        }
//		} catch (CoreException e) {
//			AuthoringUIPlugin.getDefault().getLogger().logError(e);
//		}
//		return null;
    }
    
    private String getMessage(IConfigurationError error) {
    	return error.getErrorMessage();
    }
    
    private IResource getIResource(MethodConfiguration config) {
    	Resource resource = config.eResource();
        URI uri = resource.getURI();
        uri = resource.getResourceSet().getURIConverter().normalize(uri);
        return getFile(uri);

    }
	
	public MethodConfiguration getConfig(IMarker marker) {
		if (marker == null) {
			return null;
		}
		try {
			String configGuid = (String) marker.getAttribute(ATTR_CONFIG_GUID);
			MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(configGuid);
			if (e instanceof MethodConfiguration) {
				return (MethodConfiguration)e;
			}
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}
	
	public MethodElement getErrorMethodElement(IMarker marker) {
		if (marker == null) {
			return null;
		}
		try {
			String errorGuid = (String) marker.getAttribute(ATTR_ERROR_ELEMENT_GUID);
			if (errorGuid != null) {
				MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(errorGuid);
				if (e instanceof MethodElement) {
					return (MethodElement)e;
				}
			}
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	public MethodElement getCauseMethodElement(IMarker marker) {
		if (marker == null) {
			return null;
		}
		try {
			String causeGuid = (String) marker.getAttribute(ATTR_CAUSE_ELEMENT_GUID);
			if (causeGuid != null) {
				MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(causeGuid);
				if (e instanceof MethodElement) {
					return (MethodElement)e;
				}
			}
		} catch (CoreException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	private void registerNewMarker(MethodConfiguration config, String id, IMarker marker) {
		Map<String, IMarker> map = containersOfMarkedObjects.getConfigMakerMap().get(config);
		if (map == null) {
			map = new HashMap<String, IMarker>(); 
			containersOfMarkedObjects.getConfigMakerMap().put(config, map);
		}
		map.put(id, marker);
	}
	
	private void unregisterMarker(MethodConfiguration config, String id) {
		Map<String, IMarker> map = containersOfMarkedObjects.getConfigMakerMap().get(config);
		if (map == null) {
			return;
		}
		map.remove(id);
	}
	
	private IMarker findMarker(MethodConfiguration config, String id) {
		Map<String, IMarker> map = containersOfMarkedObjects.getConfigMakerMap().get(config);
		if (map == null) {
			return null;
		}		
		return map.get(id);
	}
	
	private int getMarkerCount(MethodConfiguration config) {
		Map<String, IMarker> map = containersOfMarkedObjects.getConfigMakerMap().get(config);
		if (map == null) {
			return 0;
		}		
		return map.size();
	}
	
	public void removeAllMarkers(MethodConfiguration config) {
		Map<String, IMarker> map = containersOfMarkedObjects.getConfigMakerMap().remove(config);
		if (map == null) {
			return;
		}
		for (IMarker marker : map.values()) {
			try {
				marker.delete();
			} catch (CoreException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
	}
	
}
