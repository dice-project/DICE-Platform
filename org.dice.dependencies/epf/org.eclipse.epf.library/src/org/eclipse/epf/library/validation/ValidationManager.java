package org.eclipse.epf.library.validation;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.validation.IValidationManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.validation.LibraryEValidator;

public class ValidationManager implements IValidationManager {

	private boolean nameCheck = false;

	private boolean circularDependancyCheck = false;

	private boolean undeclaredDependancyCheck = false;

	private IProgressMonitor progressMonitor;
	
	public static final String MARKER_ID = "org.eclipse.epf.library.validation"; //$NON-NLS-1$
	
	private UndeclaredDependencyCheck undeclaredDependencyCheck;
	
	private ValidateAction emfValidateAction;
	
	private Map<IMarker, Object> markerInfoMap;
	
	private DiagnosticChain diagnostics;

	public static final String validationType = "validationType"; //$NON-NLS-1$
	public static final String validationType_undeclaredDependancyCheck = "undeclaredDependancyCheck"; //$NON-NLS-1$
	
	public DiagnosticChain getDiagnostics() {
		return diagnostics;
	}

	protected ValidationManager() {	
		undeclaredDependencyCheck = newUndeclaredDependencyCheck();
		markerInfoMap = new HashMap<IMarker, Object>();
	}
	
	private static ValidationManager instance;
	public static IValidationManager getInstance() {
		if (instance == null) {
			Object obj = ExtensionHelper.create(ValidationManager.class, null);
			if (obj instanceof ValidationManager) {
				instance = (ValidationManager) obj;
			} else {
				instance = new ValidationManager();
			}
		}
		return instance;
	}
	
	public IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}
	
	public void setNameCheck(boolean b) {
		nameCheck = b;
	}

	public void setCircularDependancyCheck(boolean b) {
		circularDependancyCheck = b;
	}

	public void setUndeclaredDependancyCheck(boolean b) {
		undeclaredDependancyCheck = b;
	}
	
	public boolean isNameCheck() {
		return nameCheck;
	}
	
	public boolean isCircularDependancyCheck() {
		return circularDependancyCheck;
	}
		
	public boolean isUndeclaredDepenancyCheck() {
		return undeclaredDependancyCheck;
	}
	
	public void clearAllMarkers() {
		for (IMarker marker : markerInfoMap.keySet()) {			
	    	try {
	    		if (marker.exists()) {
	    			marker.delete();	  
	    		}
			} catch (CoreException e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
		markerInfoMap.clear();
		if (emfValidateAction != null) {
			emfValidateAction.updateSelection(null);
		}
	}
	
	public void validate(DiagnosticChain diagnostics, Object scope, IProgressMonitor progressMonitor)  {
		this.diagnostics = diagnostics;
		this.progressMonitor = progressMonitor;
		initValidationScope(scope);		
		try {
			clearAllMarkers();
			validate();
		} finally {
			this.progressMonitor = null;
			
			pluginSet = null;
		}
	}
	
	
	private Set<MethodPlugin> pluginSet;
	public Set<MethodPlugin> getPluginSet() {
		return pluginSet;
	}

	protected void initValidationScope(Object scope) {
		pluginSet = new LinkedHashSet<MethodPlugin>();
		if (scope instanceof MethodLibrary) {
			pluginSet.addAll(((MethodLibrary) scope).getMethodPlugins());
		} else if (scope instanceof List) {
			for (Object obj : (List) scope) {
				if (obj instanceof MethodPlugin) {
					pluginSet.add((MethodPlugin) obj);
				}
			}
		}
	}
	
	protected void validate()  {
		clearResults();
		if (isUndeclaredDepenancyCheck()) {
			 undeclaredDependencyCheck.run();
		}
	}
	
	private void clearResults() {
		undeclaredDependencyCheck.clearResults();
	}
	
	private void appendDiagnostics(IStatus status, DiagnosticChain diagnostics) {
		LibraryEValidator.appendDiagnostics(status, diagnostics);
	}
	
	protected UndeclaredDependencyCheck newUndeclaredDependencyCheck() {
		return new UndeclaredDependencyCheck(this);
	}
	
	public IMarker createMarker(MethodElement element) {   
		return createMarker(element, MARKER_ID);
	}
	
	private static final Object dummyMarkInfo = new Object();
    public IMarker createMarker(MethodElement element, String markerId) {    	
    	try {
    		IFile file = getFile(element);
    		IMarker marker = file.createMarker(markerId);
    		addToMarkInfoMap(marker, dummyMarkInfo);
    		return marker;
	    	
		} catch (CoreException e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
		return null;
    }

    private static IFile getFile(MethodElement element) {
    	URI containerURI = element.eResource().getURI();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath path = new Path(containerURI.toFileString());
		IFile file = workspace.getRoot().getFileForLocation(path);
		return file;
    }
    
	public String UndeclaredDependencyCheckAddPluginFix(IMarker marker) {
		return undeclaredDependencyCheck.addPluginFix(marker);
	}
	
	public String UndeclaredDependencyCheckRemoveReferenceFix(IMarker marker) {
		return undeclaredDependencyCheck.removeReferenceFix(marker);
	}
	
	public void setEmfValidateAction(ValidateAction emfValidateAction) {
		this.emfValidateAction = emfValidateAction;
	}
	
	public void addToMarkInfoMap(IMarker marker, Object obj) {
		markerInfoMap.put(marker, obj);
	}
	
	public Object getMarkInfo(IMarker marker) {
		return markerInfoMap.get(marker);
	}
	
	public void removeFromMarkInfoMap(IMarker marker) {
		markerInfoMap.remove(marker);
    	try {
    		if (marker.exists()) {
    			marker.delete();	  
    		}
		} catch (CoreException e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
	}
	
}
