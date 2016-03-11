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
package org.eclipse.epf.authoring.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.ValidateAction.EclipseResourcesUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.element.TransientContentPackageItemProvider;
import org.eclipse.epf.library.edit.navigator.ContentItemProvider;
import org.eclipse.epf.library.edit.navigator.MethodPackagesItemProvider;
import org.eclipse.epf.library.edit.navigator.ProcessesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.validation.ValidationManager;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.ui.ide.IDE;

/**
 * @author Phong Nguyen Le
 * @since  1.1
 */
public class LibraryValidationMarkerHelper extends EclipseResourcesUtil {	
	public static final LibraryValidationMarkerHelper INSTANCE = new LibraryValidationMarkerHelper();
	public static final String GUID = "guid"; //$NON-NLS-1$
	
    @Override
    protected String getMarkerID()
    {
      return ValidationManager.MARKER_ID;
    }
	
    @Override
    public void createMarkers(Resource resource, Diagnostic diagnostic)
    {
    	if (diagnostic.getSeverity() == Diagnostic.WARNING) {
    		return;
    	}
    	super.createMarkers(resource, diagnostic);
    }
    
	private static interface IParentProvider {
		final IParentProvider containerProvider = new IParentProvider() {

			public Collection<?> getParents(Object object) {
				if(object instanceof EObject) {
				return Collections.singleton(((EObject)object).eContainer());
				}
				return Collections.emptyList();
			}
			
		};
		
		final IParentProvider parentCustomCategoryProvider = new IParentProvider() {

			public Collection<?> getParents(Object object) {
				if(object instanceof CustomCategory) {
					return AssociationHelper.getCustomCategories((CustomCategory)object);
				}
				return Collections.emptyList();
			}
			
		};
		
		final IParentProvider parentStandardCategoryProvider = new IParentProvider() {

			public Collection<?> getParents(Object object) {
				if(object instanceof Discipline) {
					Discipline discipline = (Discipline) object;
					List parentDisciplines = AssociationHelper.getDiscipline(discipline);
					List disciplineGroupings = AssociationHelper.getDisciplineGroups(discipline);
					if(!parentDisciplines.isEmpty() && !disciplineGroupings.isEmpty()) {
						Collection<Object> parents = new ArrayList<Object>(disciplineGroupings);
						parents.addAll(parentDisciplines);
						return parents;
					}
					else {
						return parentDisciplines.isEmpty() ? disciplineGroupings : parentDisciplines;
					}
				}
				else if(object instanceof RoleSet) {
					return AssociationHelper.getRoleSetGroups((RoleSet) object);
				}
				return Collections.emptyList();
			}
			
		};
		
		Collection<?> getParents(Object object);
	}
	
	private static class ErrorCalculator extends HashMap<MethodPlugin, Set<MethodElement>> {		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ErrorCalculator() {
			super();
			initialize();
			LibraryService.getInstance().addListener(new LibraryServiceListener() {
				public void libraryOpened(MethodLibrary library) {
					initialize();
				}
				
				public void libraryClosed(MethodLibrary library) {
					clear();
				}
				
				public void libraryReopened(MethodLibrary library) {
					initialize();
				}				
			});
		}
		
		private void initialize() {
			clear();
			
			// fill this set with containers of marked objects
			//
			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
			if(lib != null) {
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				try {
					IMarker[] markers = wsRoot.findMarkers(INSTANCE.getMarkerID(), false, IResource.DEPTH_INFINITE);
					for (int i = 0; i < markers.length; i++) {
						IMarker marker = markers[i];
						String guid = (String) marker.getAttribute(GUID);
						MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
						if(e != null) {
							if(e.eIsProxy()) {
								e = (MethodElement) PersistenceUtil.resolve(e,
										LibraryService.getInstance()
												.getCurrentMethodLibrary()
												.eResource().getResourceSet());
							}
							// add the element to this set
							//
							addError(e);
						}
					}
				} catch (CoreException e) {
				}
			}
		}
		
		public void addError(MethodElement e) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
			if(plugin != null) {
				Set<MethodElement> elements = get(plugin);
				if(elements == null) {
					elements = new HashSet<MethodElement>();
					put(plugin, elements);
				}
				elements.add(e);
			}
		}
		
		public void removeError(MethodElement e) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
			if(plugin != null) {
				Set<MethodElement> elements = get(plugin);
				if(elements != null) {
					elements.remove(e);
					if(elements.isEmpty()) {
						remove(plugin);
					}
				}
			}
		}
		
		public boolean hasErrors(MethodElement e) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
			if(plugin != null) {
				Set<MethodElement> elements = get(plugin);
				if(elements != null) {
					if(elements.contains(e)) {
						return true;
					}

					// check if e is container of any error element
					//
					HashSet<Object> processedChildren = null;
					for (MethodElement me : elements) {
						if(UmaUtil.isContainedBy(me, e)) {
							return true;
						}
						if(me instanceof CustomCategory) {
							if(processedChildren == null) {
								processedChildren = new HashSet<Object>();
							}
							else {
								processedChildren.clear();
							}
							if(isParentOf(me, e, IParentProvider.parentCustomCategoryProvider, processedChildren)) {
								return true;
							}
						}
						else if(me instanceof ContentCategory) {
							// me must be a standard category
							// currently all content packages that contain
							// standard categories are not contained by
							// predefined content package "Standard Categories"
							// we need to handle this specially here
							//
							if(e instanceof ContentPackage && e == UmaUtil.findContentPackage(plugin, ModelStructure.DEFAULT.standardCategoryPath)) {
								return true;
							}
							if(processedChildren == null) {
								processedChildren = new HashSet<Object>();
							}
							else {
								processedChildren.clear();
							}
							if(isParentOf(me, e, IParentProvider.parentStandardCategoryProvider, processedChildren)) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}
		
		private static boolean isParentOf(Object child, Object parent, IParentProvider parentProvider, Set<Object> processedChildren) {
			processedChildren.add(child);
			for (Object myParent : parentProvider.getParents(child)) {				
				if(myParent == parent) {
					return true;
				}
				if(!processedChildren.contains(myParent) && isParentOf(myParent, parent, parentProvider, processedChildren)) {
					return true;
				}				
			}
			return false;
		}
	}
	
	private static ErrorCalculator errorCalculator = new ErrorCalculator();	
	
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
			if(o instanceof MethodElement) {
				marker.setAttribute(GUID, ((MethodElement)o).getGuid());
				marker.setAttribute(IMarker.LOCATION, TngUtil.getLabelWithPath(o));
	    		marker.setAttribute(IDE.EDITOR_ID_ATTR, "org.eclipse.epf.authoring.ui.editors.MethodElementEditor"); //$NON-NLS-1$
	    		errorCalculator.addError((MethodElement) o);
			}
		}
		return ret;
	}
	
	public static boolean isInvalid(Object object) {
		if(object instanceof ProcessesItemProvider) {
			// this is an UI object: Processes
			//
			ProcessesItemProvider ip = (ProcessesItemProvider) object;
			MethodElement o;			
			return ((o = ip.getCapabilityPatternPackage()) != null && errorCalculator.hasErrors(o))
				|| ((o = ip.getDeliveryProcessPackage()) != null && errorCalculator.hasErrors(o));
		}
		else if(object instanceof MethodPackagesItemProvider) {
			// this is an UI object
			//
			object = ((ItemProviderAdapter)object).getTarget();			
		}
		else if(object instanceof ContentItemProvider) {
			ContentPackage coreContentPkg = ((ContentItemProvider)object).getCoreContentPackage();
			if(coreContentPkg != null) {
				object = coreContentPkg.eContainer();
			}
		}
		else if(object instanceof TransientContentPackageItemProvider) {
			// this is an UI object: either Tasks, Roles, Work Products, or Guidance
			//
			Collection<?> children = ((TransientContentPackageItemProvider)object).getChildren(object);
			for (Iterator<?> iter = children.iterator(); iter.hasNext();) {
				Object child = TngUtil.unwrap(iter.next());
				if(child instanceof MethodElement && errorCalculator.hasErrors((MethodElement) child)) {
					return true;
				}				
			}
			return false;
		}
		else if(object instanceof TransientGroupItemProvider) {
			object = ((TransientGroupItemProvider)object).getTarget();
		}
		return ((object = TngUtil.unwrap(object)) instanceof MethodElement && errorCalculator
				.hasErrors((MethodElement) object));
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
	
	@Override
	protected IFile getFile(Object datum) {
		try {
			return super.getFile(datum);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public void deleteMarkers(Object object) {
		if(object instanceof MethodElement) {
			IResource resource = getFile(object);
			if (resource != null && resource.exists())
			{
				try
				{
					MethodElement e = (MethodElement) object;
					String guid = e.getGuid();
					IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
					for (int i = 0; i < markers.length; i++) {
						IMarker marker = markers[i];						
						if(guid.equals(marker.getAttribute(GUID))) {
							marker.delete();
							errorCalculator.removeError(e);
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
		if (resource != null && resource.exists()) {
			try {				
				IMarker[] markers = resource.findMarkers(getMarkerID(), false, IResource.DEPTH_ZERO);
				for (int i = 0; i < markers.length; i++) {
					IMarker marker = markers[i];
					String guid = (String) marker.getAttribute(GUID);
					if (guid == null) {
						continue;
					}
					MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
					if(e != null) {
						errorCalculator.removeError(e);
					}
				}

			} catch (CoreException e) {
				
			}			
		}
			
		super.deleteMarkers(resource, includeSubtypes, depth);
	}

	//Override to seperate error message in error dialog and problems view (bug 174351)
	//Note: better fix is to extend BasicDiagnostic to encapsulate both types of msgs -> to do later
	protected String composeMessage(Diagnostic diagnostic, Diagnostic parentDiagnostic) {
		int ix = diagnostic.getMessage().indexOf(LibraryEditResources.duplicateElementNameError_msg2);
		if (ix > 0) {
			return diagnostic.getMessage().substring(0, ix);
		}
		return super.composeMessage(diagnostic, parentDiagnostic);
	}

}