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
package org.eclipse.epf.library.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.utils.RestartableJob;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryService;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceListener;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * The class for monitor library problems.
 * 
 * (1) Wake up regularly (say every 30 seconds) 
 *     to schedule job if any change is registered during sleep
 *     
 * (2) Goes to sleep again immediately after "waking up/do nothing" or
 *     "waking up/scheduling a job"    
 *     
 * (3) Job (finding library problems) runs to end only there is no change during run.
 * 
 * (4) Job run is aborted (by throwing a RestartInterruptException)
 *     whenever a change is detected during run, and a new job scheduled 
 *     with a delay time (say 1 second).
 *      
 * (5) Make sure there is always at most one job running
 * 
 * @author Weiping Lu
 * @since 1.5
 */

public class LibraryProblemMonitor extends RestartableJob implements ILibraryChangeListener {
	
	public static final String UnresolvedBasedPluginMARKER_ID = LibraryPlugin.getDefault().getId() + ".unresolvedBasePlugins"; //$NON-NLS-1$
	
	public static final String Name = "name"; //$NON-NLS-1$
	public static final String Guid = "guid"; //$NON-NLS-1$
	public static final String UnresolvedBaseGuids = "unresolvedBaseGuids"; //$NON-NLS-1$
	
	private long delay = 30000L;
	private long restartDelay = 1000L;
	private boolean hasChange = false;
	private Thread monitorThread;
	private boolean stopMonitor = false;
	private MethodLibrary library;
	private Map<MethodPlugin, IMarker> pluginMarkerMap = new HashMap<MethodPlugin, IMarker>();
	
	public LibraryProblemMonitor() {
		super(LibraryResources.libraryProblemMonitor);
		
		LibraryServiceListener libServiceListener = new LibraryServiceListener() {
			public void librarySet(MethodLibrary lib) {
				ILibraryService libService = LibraryService.getInstance();				
				if (library != null && library != lib) {
					ILibraryManager libMgr = libService.getLibraryManager(library);
					if (libMgr != null) {
						libMgr.removeListener(LibraryProblemMonitor.this);
					}
				}
				
				if (lib != null && lib != library) {
					ILibraryManager libMgr = libService.getLibraryManager(lib);
					if (libMgr != null) {
						libMgr.addListener(LibraryProblemMonitor.this);
					}
					kickToRun();
				}
			
				library = lib;
			}
		};
		LibraryService.getInstance().addListener(libServiceListener);
		startMonitor();
	}
	
	public void startMonitor() {
		Runnable runnable = new Runnable() {
			public void run() {
				while (! stopMonitor) {
					try {
						Thread.sleep(delay);
					} catch (Exception e) {
						LibraryPlugin.getDefault().getLogger().logError(e);
						return;
					}
					if (hasChange) {
						hasChange = false;
						guardedSchedule(0L);
					}
				}
			}
		};
		
		stopMonitor = false;
		if (monitorThread == null) {
			monitorThread = new Thread(runnable);
		}
		monitorThread.start();
	}
	
	public void stopMonitor() {
		stopMonitor = true;
	}
	
	
	public void libraryChanged(int option, Collection<Object> changedItems) {
		if (changedItems != null && changedItems.size() > 0) {
			hasChange = true;
			enableToRestart();
		}
	}
	
	// Update will abort and re-schedule a job if new change detected
	private void update()  throws RestartInterruptException {
		if (localDebug) {
			System.out.println("LD> update"); //$NON-NLS-1$
		}
		MethodLibrary lib = getLibrary();
		if (lib == null) {
			return;
		}
		checkRestartInterruptException(restartDelay);		

		cleanUp();
		
		List<MethodPlugin> plugins = new ArrayList<MethodPlugin>();
		plugins.addAll(lib.getMethodPlugins());
		
		Set<MethodPlugin> pluginSet = new HashSet<MethodPlugin>(plugins);
		for (MethodPlugin plugin: plugins) {
			checkRestartInterruptException(restartDelay);
			List<MethodPlugin> baseList = plugin.getBases();
			boolean missing = false;
			for (MethodPlugin base: baseList) {
				checkRestartInterruptException(restartDelay);
				if (! pluginSet.contains(base)) {					
					if (! missing) {
						if (localDebug) {
							System.out.println("LD> plugin: " + plugin); //$NON-NLS-1$
							System.out.println("LD> " + plugin.getName() + " references unresolved bases:"); //$NON-NLS-1$ //$NON-NLS-2$
						}
						missing = true;
					}
					if (localDebug) {
						System.out.println("LD> base: " + base); //$NON-NLS-1$
					}
					addMissingBasePluginError(plugin, base);
				}
			}
			if (missing) {
				System.out.println(""); //$NON-NLS-1$
			}
		}		
		
	}
	
	private void addMissingBasePluginError(MethodPlugin plugin, MethodPlugin base) {
		IMarker marker = pluginMarkerMap.get(plugin);
		if (marker == null) {
			Resource res = plugin.eResource();
			if (res == null) {
				return;
			}
		
			URI containerURI = res.getURI();
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath path = new Path(containerURI.toFileString());
			IFile file = workspace.getRoot().getFileForLocation(path);
			if (file == null) {
				return;
			}
			String location = containerURI != null ? containerURI
					.toFileString() : ""; //$NON-NLS-1$	
			try {
				marker = file.createMarker(UnresolvedBasedPluginMARKER_ID);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(Name, plugin.getName());
				marker.setAttribute(Guid, plugin.getGuid());
				marker.setAttribute(IMarker.LOCATION, location);
				marker.setAttribute(Guid, plugin.getGuid());	

			} catch (CoreException e) {
				IStatus status = e.getStatus();
				if(status instanceof IResourceStatus && ((IResourceStatus) status).getCode() == IResourceStatus.RESOURCE_NOT_FOUND) {
					// do nothing
				} else {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			} catch(Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
			if (marker == null) {
				return;
			}
			if (localDebug) {
				System.out.println("LD> marker: " + marker); //$NON-NLS-1$
			}
			pluginMarkerMap.put(plugin, marker);				
		
		}
		
		String unresolvedBaseGuidsValue = null;
		try {

			String errMsg = plugin.getName() + " " + LibraryResources.references_unresolved_base_txt; //$NON-NLS-1$
			unresolvedBaseGuidsValue = (String) marker.getAttribute(UnresolvedBaseGuids);
			if (unresolvedBaseGuidsValue == null || unresolvedBaseGuidsValue.length() == 0) {
				unresolvedBaseGuidsValue = base.getGuid();
				errMsg += ": "; //$NON-NLS-1$
			} else if (unresolvedBaseGuidsValue.indexOf(base.getGuid()) < 0) {
				unresolvedBaseGuidsValue += ", " + base.getGuid(); //$NON-NLS-1$
				errMsg += "plugins: "; //$NON-NLS-1$
			}
			errMsg += unresolvedBaseGuidsValue;
			marker.setAttribute(IMarker.MESSAGE, errMsg);
			marker.setAttribute(UnresolvedBaseGuids, unresolvedBaseGuidsValue);
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}

	}
	
	protected IStatus restartableRun(IProgressMonitor monitor) throws RestartInterruptException {
		update();
		return Status.OK_STATUS;
	}
	
	protected void resetToRestart() {
		if (localDebug) {
			System.out.println("LD> resetToRestart"); //$NON-NLS-1$
		}
		cleanUp();
	}
	
	private void cleanUp() {
		if (pluginMarkerMap.isEmpty()) {
			return;
		}
		for (IMarker marker: pluginMarkerMap.values()) {
			try {
				marker.delete();
			} catch (Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
		pluginMarkerMap = new HashMap<MethodPlugin, IMarker>();
	}

	public void kickToRun() {
		if (localDebug) {
			System.out.println("LD> kickToRun"); //$NON-NLS-1$
		}
		enableToRestart();
		guardedSchedule(0L);
	}
	
	//To do: allow a cached data to pased and return -> optimize fix of similar problems
	public void fixProblem(IMarker marker) {
		try {
			if (marker.getType() != UnresolvedBasedPluginMARKER_ID) {
				return;
			}
			MethodLibrary lib = getLibrary();
			if (lib == null) {
				return;
			}

			Map<String, MethodPlugin> guidToPluginMap = new HashMap<String, MethodPlugin>();
			for (MethodPlugin plugin : lib.getMethodPlugins()) {
				guidToPluginMap.put(plugin.getGuid(), plugin);
			}
			String guid = (String) marker.getAttribute(Guid); //$NON-NLS-1$

			MethodPlugin plugin = guidToPluginMap.get(guid);
			if (plugin == null || plugin.eResource() == null) {
				return;
			}
			
			if (localDebug) {
				System.out.println("LD> fixProblem, plugin: " + plugin); //$NON-NLS-1$
			}
			
			List<MethodPlugin> bases = new ArrayList<MethodPlugin>();
			bases.addAll(plugin.getBases());

			boolean modified = false;
			for (MethodPlugin base : bases) {
				if (! guidToPluginMap.containsKey(base.getGuid())) {
					modified = true;
					plugin.getBases().remove(base);
				}
			}
			if (! modified) {
				return;
			}
			
			Resource resource = plugin.eResource();
			ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
					.getPersisterFor(resource).getFailSafePersister();
			try {
				persister.save(resource);
				persister.commit();
			}
			catch(Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
				persister.rollback();
			}

			kickToRun();
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public void dispose() {
		stopMonitor();
		cleanUp();
	}

	public MethodLibrary getLibrary() {
		return library;
	}

	public void setLibrary(MethodLibrary library) {
		this.library = library;
	}

}
