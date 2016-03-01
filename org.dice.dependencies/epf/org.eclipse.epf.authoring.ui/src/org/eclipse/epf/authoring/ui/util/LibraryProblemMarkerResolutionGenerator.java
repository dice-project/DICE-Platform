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
package org.eclipse.epf.authoring.ui.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.layout.elements.AbstractProcessElementLayout;
import org.eclipse.epf.library.util.LibraryProblemMonitor;
import org.eclipse.epf.services.ILibraryPersister.FailSafeMethodLibraryPersister;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;

/**
 * Generates LibraryProblemMarkerResolution's
 * @author Weiping Lu
 * @since  1.5
 */
public class LibraryProblemMarkerResolutionGenerator implements IMarkerResolutionGenerator {
	
	private static final IMarkerResolution[] EMPTY_RESOLUTIONS = new IMarkerResolution[0];
	
	private static boolean localDebug = true;

	public IMarkerResolution[] getResolutions(IMarker marker) {
		try {
			if (marker.getType() == LibraryProblemMonitor.UnresolvedBasedPluginMARKER_ID) {
				return new IMarkerResolution[] {
						new MissingPluginResolution(marker),
						};
			} else if (marker.getType() == ConfigurationClosure.multipleReplacersMARKER_ID) {
				return new IMarkerResolution[] {
						new MultipleReplacerResolution(marker),
						};
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return EMPTY_RESOLUTIONS;
	}

	public static abstract class LibraryProblemMarkerResolution extends WorkbenchMarkerResolution {
		private IMarker currentMarker;

		public LibraryProblemMarkerResolution(IMarker currentMarker) {
			this.currentMarker = currentMarker;
		}

		@Override
		public IMarker[] findOtherMarkers(IMarker[] markers) {
			ArrayList<IMarker> similarMarkerList = new ArrayList<IMarker>();
			for (int i = 0; i < markers.length; i++) {
				IMarker marker = markers[i];
				if(! currentMarker.equals(marker)) {
					if(acceptedType(marker)) {
						similarMarkerList.add(marker);
					}
				}
			}
			IMarker[] similarMarkers = new IMarker[similarMarkerList.size()];
			similarMarkerList.toArray(similarMarkers);
			return similarMarkers;
		}
		
		protected abstract boolean acceptedType(IMarker marker);

		public String getDescription() {
			// TODO provide detailed description
			return null;
		}

		public Image getImage() {
			// TODO provide image
			return null;
		}
				
		protected void save(Set<Resource> resouresToSave) {
			if (resouresToSave == null) {
				return;
			}
			FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();			
			for (Resource res: resouresToSave) {
				try {
					persister.save(res);
					persister.commit();
				} catch(Exception e) {
					AuthoringUIPlugin.getDefault().getLogger().logError(e);
					persister.rollback();
				}
			}
		}
		
	}
			
	private static class MissingPluginResolution extends LibraryProblemMarkerResolution {
		
		public MissingPluginResolution(IMarker currentMarker) {
			super(currentMarker);
		}
		
		/**
		 * @see org.eclipse.ui.IMarkerResolution#getLabel()
		 */
		public String getLabel() {
			return AuthoringUIResources.LpmResolutionGenerator_removeUnresolvedBasePlugins;
		}

		public void run(IMarker marker) {			
			LibraryService.getInstance().getLibraryProblemMonitor().fixProblem(marker);
		}

		protected boolean acceptedType(IMarker marker) {
			boolean ret = false;
			try {
				ret = LibraryProblemMonitor.UnresolvedBasedPluginMARKER_ID.equals(marker.getType());
			} catch (CoreException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
			
			return ret;
		}
		
	}
	
	
	private static class MultipleReplacerResolution extends LibraryProblemMarkerResolution {
		
		public MultipleReplacerResolution(IMarker currentMarker) {
			super(currentMarker);
		}
		
		/**
		 * @see org.eclipse.ui.IMarkerResolution#getLabel()
		 */
		public String getLabel() {
			return AuthoringUIResources.LpmResolutionGenerator_removeMultipleRepacers;
		}

		public void run(IMarker marker) {
			try {
				ILibraryManager libMgr = LibraryService.getInstance().getCurrentLibraryManager();
				if (libMgr != null) {
					String replacerGuids = (String) marker.getAttribute(ConfigurationClosure.replacerGuids);
					String[] guids = AbstractProcessElementLayout.getPathArray(replacerGuids);
					if (guids == null || guids.length == 0) {
						return;
					}
					Set<Resource> resouresToSave = new HashSet<Resource>();
					for (int i = 0; i < guids.length; i++) {
						MethodElement element = libMgr.getMethodElement(guids[i]);
						if (element instanceof VariabilityElement) {
							EStructuralFeature feature = UmaPackage.eINSTANCE.getVariabilityElement_VariabilityBasedOnElement();
							element.eSet(feature, null);
							feature = UmaPackage.eINSTANCE.getVariabilityElement_VariabilityType();
							element.eSet(feature, VariabilityType.NA);
							marker.delete();
							Resource res = element.eResource();
							if (res != null) {
								resouresToSave.add(res);
							}
						}						
					}
					save(resouresToSave);
				}

			
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}

		protected boolean acceptedType(IMarker marker) {
			boolean ret = false;
			try {
				ret = ConfigurationClosure.multipleReplacersMARKER_ID.equals(marker.getType());
			} catch (CoreException e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
			
			return ret;
		}
		
	}	
	
}
