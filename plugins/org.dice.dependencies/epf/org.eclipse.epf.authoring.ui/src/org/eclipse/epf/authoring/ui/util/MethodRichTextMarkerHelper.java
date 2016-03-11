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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.action.ValidateAction.EclipseResourcesUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.edit.element.TransientContentPackageItemProvider;
import org.eclipse.epf.library.edit.navigator.ContentItemProvider;
import org.eclipse.epf.library.edit.navigator.MethodPackagesItemProvider;
import org.eclipse.epf.library.edit.navigator.ProcessesItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.ui.ide.IDE;

/**
 * based on org.eclipse.epf.authoring.ui.util.LibraryValidationMarkerHelper 
 * @author Jeff Hardy
 *
 */
public class MethodRichTextMarkerHelper extends EclipseResourcesUtil {	
	public static final MethodRichTextMarkerHelper INSTANCE = new MethodRichTextMarkerHelper();
	public static final String GUID = "guid"; //$NON-NLS-1$

	// marker ID
	public static final String MARKER_ID = "org.eclipse.epf.authoring.ui.methodRichText"; //$NON-NLS-1$
	
	// marker attribute
	private static String METHOD_FIELDNAME = "MethodFieldName"; //$NON-NLS-1$

	// private constructor
	private MethodRichTextMarkerHelper() {
	}
	
	private static class ContainerMap extends HashMap<Object, Integer> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
			
			// fill this set with containers of marked objects
			//
//			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
//			if(lib != null) {
//				IProject prj = MethodLibraryProject.findProject(lib);
//				if(prj != null) {
//					try {
//						IMarker[] markers = prj.findMarkers(INSTANCE.getMarkerID(), false, IResource.DEPTH_INFINITE);
//						for (int i = 0; i < markers.length; i++) {
//							IMarker marker = markers[i];
//							String guid = (String) marker.getAttribute(GUID);
//							MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
//							if(e != null) {
//								// remove its containers from containersOfMarkedObjects
//								//
//								markContainers(e);
//							}
//						}
//					} catch (CoreException e) {
//					}
//				}
//			}
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
		return super.getFile(uri);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.util.EditUIMarkerHelper#adjustMarker(org.eclipse.core.resources.IMarker, org.eclipse.emf.common.util.Diagnostic)
	 */
	protected boolean adjustMarker(IMarker marker, Diagnostic diagnostic) throws CoreException {
		boolean ret = super.adjustMarker(marker, diagnostic);
		if(!diagnostic.getData().isEmpty()) {
			Object o = diagnostic.getData().get(0);
			Object str = diagnostic.getData().get(1);
			if(o instanceof MethodElement) {
				marker.setAttribute(GUID, ((MethodElement)o).getGuid());
				marker.setAttribute(IMarker.LOCATION, TngUtil.getLabel(o) + ", " + str); //$NON-NLS-1$
	    		marker.setAttribute(IDE.EDITOR_ID_ATTR, "org.eclipse.epf.authoring.ui.editors.MethodElementEditor"); //$NON-NLS-1$
			}
			if (str instanceof String) {
				marker.setAttribute(METHOD_FIELDNAME, (String)str);
			}

		}
		return ret;
	}
	
	public static boolean isInvalid(Object object) {
		if(object instanceof ProcessesItemProvider) {
			// this is an UI object: Processes
			//
			ProcessesItemProvider ip = (ProcessesItemProvider) object;
			Object o;
			return ((o = ip.getCapabilityPatternPackage()) != null && containersOfMarkedObjects.containsKey(o))
				|| ((o = ip.getDeliveryProcessPackage()) != null && containersOfMarkedObjects.containsKey(o));
		}
		else if(object instanceof MethodPackagesItemProvider
				|| object instanceof ContentItemProvider) {
			// this is an UI object
			//
			object = ((ItemProviderAdapter)object).getTarget();
		}
		else if(object instanceof TransientContentPackageItemProvider) {
			// this is an UI object: either Tasks, Roles, Work Products, or Guidance
			//
			Collection children = ((TransientContentPackageItemProvider)object).getChildren(object);
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object obj = iter.next();
				if(containersOfMarkedObjects.containsKey(obj) || INSTANCE.hasMarkers(obj)) {
					return true;
				}				
			}
			return false;
		}
		return containersOfMarkedObjects.containsKey(object) || INSTANCE.hasMarkers(object);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.ui.MarkerHelper#hasMarkers(java.lang.Object)
	 */
	public boolean hasMarkers(Object object) {
		if(object instanceof MethodElement) {
			IResource resource = getFile(object);
			if (resource != null && resource.exists())
			{
				try
				{
					String guid = ((MethodElement)object).getGuid();
					IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
					for (int i = 0; i < markers.length; i++) {
						IMarker marker = markers[i];
						if(guid.equals(marker.getAttribute(GUID))) {
							return true;
						}
					}
				}
				catch (CoreException e)
				{
				}
			}
			return false;
		}

		return super.hasMarkers(object);
	}
	
	/**
	 * Overrided to handle exception that is thrown if the object already has been unloaded
	 * 
	 * @see org.eclipse.emf.edit.ui.util.EditUIMarkerHelper#getFile(java.lang.Object)
	 */
	@Override
	protected IFile getFile(Object datum) {
		try {
			return super.getFile(datum);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public void deleteMarkers(Object object, String fieldName) {
		if(object instanceof MethodElement) {
			IResource resource = getFile(object);
			if (resource != null)
			{
				try
				{
					String guid = ((MethodElement)object).getGuid();
					IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
					for (int i = 0; i < markers.length; i++) {
						IMarker marker = markers[i];
						if(guid.equals(marker.getAttribute(GUID)) && (fieldName == null ||
								fieldName.equals(marker.getAttribute(METHOD_FIELDNAME)))) {
							marker.delete();
//							MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
//							if(e != null) {
//								// remove its containers from containersOfMarkedObjects
//								//
//								containersOfMarkedObjects.unmarkContainers(e);
//							}
						}
					}
				}
				catch (CoreException e)
				{
				}
			}
			return;
		}
		
		super.deleteMarkers(object);
	}

	protected void deleteMarkers(IResource resource, boolean includeSubtypes, int depth) {
//		if (resource != null) {
//			try {				
//				IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
//				for (int i = 0; i < markers.length; i++) {
//					IMarker marker = markers[i];
//					String guid = (String) marker.getAttribute(GUID);
//					MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
//					if(e != null) {
//						// remove its containers from containersOfMarkedObjects
//						//
//						containersOfMarkedObjects.unmarkContainers(e);
//					}
//				}
//
//			} catch (CoreException e) {
//				
//			}			
//		}
			
		super.deleteMarkers(resource, includeSubtypes, depth);
	}
	
	protected void createMarkers(IResource resource, Diagnostic diagnostic, Diagnostic parentDiagnostic) throws CoreException {
		super.createMarkers(resource, diagnostic, parentDiagnostic);
//		String fieldName = null;
//		if(!diagnostic.getData().isEmpty() && diagnostic.getData().size() > 1) {
//			fieldName = (String)diagnostic.getData().get(1);
//		}
//		if (resource != null && fieldName != null) {
//			try {				
//				IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
//				for (int i = 0; i < markers.length; i++) {
//					IMarker marker = markers[i];
//					String guid = (String) marker.getAttribute(GUID);
//					if(fieldName.equals(marker.getAttribute(METHOD_FIELDNAME))) {
//						MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
//						if(e != null) {
//							// add its containers to containersOfMarkedObjects
//							//
//							containersOfMarkedObjects.markContainers(e);
//						}
//					}
//				}
//
//			} catch (CoreException e) {
//				
//			}			
//		}

	}
	
    protected String composeMessage(Diagnostic diagnostic, Diagnostic parentDiagnostic)
    {
      String message = diagnostic.getMessage();
      if (parentDiagnostic != null)
      {
        String parentMessage = parentDiagnostic.getMessage();
        if (parentMessage != null)
        {
          message = message != null ? parentMessage + " " + message : parentMessage; //$NON-NLS-1$
        }
      }
      return message;
    }
}