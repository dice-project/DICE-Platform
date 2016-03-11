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
package org.eclipse.epf.authoring.ui.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.epf.authoring.ui.providers.MethodElementLabelDecorator;
import org.eclipse.epf.authoring.ui.util.LibraryValidationMarkerHelper;
import org.eclipse.epf.authoring.ui.views.ViewHelper;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyValidationMgr;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.validation.ValidationManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.epf.validation.constraints.LibraryTraversalStrategy;
import org.eclipse.epf.validation.constraints.LibraryTraversalStrategy.LibraryIterator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class LibraryValidateAction extends ValidateAction {
	private HashSet resources;
	private boolean success;
	private boolean showSuccess = true;
	private Map contextData = new HashMap();
	private MethodLibrary library;

	public LibraryValidateAction() {
		super();
		eclipseResourcesUtil = new LibraryValidationMarkerHelper();
	}
	
	//Called from method editors only
	public LibraryValidateAction(boolean showSuccess) {
		this();
		this.showSuccess  = showSuccess;
	}
	
	public LibraryValidateAction(MethodLibrary library) {
		this();
		this.library = library;
	}
	
	public void putContextData(Object key, Object value) {
		contextData.put(key, value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.action.ValidateAction#validate(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected Diagnostic validate(final IProgressMonitor progressMonitor) {
		ValidationManager validationMgr = (ValidationManager) LibraryEditUtil.getInstance().getValidationManager();
		LibraryUIPreferences.update(validationMgr);
		validationMgr.setEmfValidateAction(this);
		
		if (showSuccess) {	
			SafeUpdateController.syncExec(new Runnable() {	
				public void run() {
					PlatformUI.
					getWorkbench().
					getActiveWorkbenchWindow().
					getActivePage().
					closeAllEditors(true);
					
					ViewHelper.checkLibraryHealth(library == null ? selectedObjects : library);
				}
			});			
		} else {	//called from an method element editing
			validationMgr.setNameCheck(true);
			validationMgr.setCircularDependancyCheck(true);
			validationMgr.setUndeclaredDependancyCheck(false);
		}
		
		BasicDiagnostic ret = validate_old(progressMonitor);
		
//		deleteMarkers();
		Object scope = library == null ? selectedObjects : library;
		validationMgr.validate(ret, scope, progressMonitor);	
				
		return ret;
	}
	
	private BasicDiagnostic validate_old(final IProgressMonitor progressMonitor) {				
	    if(resources == null) {
	    	resources = new HashSet();
	    }
	    else {
	    	resources.clear();
	    }
	    boolean dependencyInfoMgrSet = false;
		Collection eObjects = new ArrayList();
		
		Iterator it = library == null ? selectedObjects.iterator() : library.getMethodPlugins().iterator();
		for ( ; it.hasNext();) {
			Object element = TngUtil.unwrap(it.next());
			if(element instanceof EObject) {
				eObjects.add(element);
				Resource resource = ((EObject)element).eResource();
				if(resource != null) {
					resources.add(resource);
				}
				if (! dependencyInfoMgrSet && element instanceof MethodElement) {
					MethodLibrary lib = UmaUtil.getMethodLibrary((MethodElement) element);	
					putContextData(LibraryEValidator.CTX_DEPENDENCY_VALIDATION_MGR, new DependencyValidationMgr(lib));
					dependencyInfoMgrSet = true;
				}
			}
		}
		
	    int count = eObjects.size();
	    eObjects = LibraryTraversalStrategy.makeTargetsDisjoint(eObjects);
		LibraryIterator libIter = new LibraryIterator(eObjects, true, LibraryTraversalStrategy.DEEP, true);
		while(libIter.hasNext()) {
			Object o = libIter.next();
			count++;
			if(o instanceof EObject) {
				Resource resource = ((EObject)o).eResource();
				if(resource != null) {
					resources.add(resource);
				}
			}
		}
		
	    progressMonitor.beginTask("", count); //$NON-NLS-1$

//	    final AdapterFactory adapterFactory = 
//	      domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)domain).getAdapterFactory() : null;

	    Diagnostician diagnostician = 
	      new Diagnostician()
	      {
	        public String getObjectLabel(EObject eObject)
	        {
//	          if (adapterFactory != null && !eObject.eIsProxy())
//	          {
//	            IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(eObject, IItemLabelProvider.class);
//	            if (itemLabelProvider != null)
//	            {
//	              return itemLabelProvider.getText(eObject);
//	            }
//	          }
//	  
//	          return super.getObjectLabel(eObject);
	        	
	        	return TngUtil.getLabelWithPath(eObject);
	        }

	        public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map context)
	        {
	          progressMonitor.worked(1);
	          if(context == null) {
	        	  context = contextData;	        	  
	          }
	          else {
	        	  context.putAll(contextData);
	          }
	          return super.validate(eClass, eObject, diagnostics, context);
	        }	        
	        
	      };
	      
	      List list = new ArrayList(eObjects);
	      int max = list.size() - 1;
	      StringBuffer strBuf = new StringBuffer();
	      for (int i = 0; i < max; i++) {
	    	  Object e = list.get(i);
	    	  if(e instanceof NamedElement) {
	    		  strBuf.append(((NamedElement)e).getName()).append(", "); //$NON-NLS-1$
	    	  }
	      }
	      Object e = max < 0 ? null : list.get(max);
	      if(e instanceof NamedElement) {
    		  strBuf.append(((NamedElement)e).getName());
    	  }
	      BasicDiagnostic diagnostics = 
	          new BasicDiagnostic
	            (EObjectValidator.DIAGNOSTIC_SOURCE,
	             0,
	             EcorePlugin.INSTANCE.getString
	               ("_UI_DiagnosticRoot_diagnostic", //$NON-NLS-1$ 
	                new Object [] { strBuf.toString() }), 
	             list.toArray()) {
	    	  
	    	  @Override
	    	  public String getMessage() {
	    		  String msg = super.getMessage();
	    		  if (msg != null && msg.length() > 2000) {
	    			  msg = msg.substring(0, 2000) + " ... ";	//$NON-NLS-1$ 
	    		  }
	    		  return msg;
	    	  }
	      };
	      for (Iterator iter = eObjects.iterator(); iter.hasNext();) {
	    	  EObject eObject = (EObject) iter.next();
	    	  progressMonitor.setTaskName
	    	  (EMFEditUIPlugin.INSTANCE.getString("_UI_Validating_message", new Object [] {diagnostician.getObjectLabel(eObject)})); //$NON-NLS-1$
	    	  diagnostician.validate(eObject, diagnostics);
	      }
	      success = diagnostics.getSeverity() == Diagnostic.OK;
	      return diagnostics;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.action.ValidateAction#handleDiagnostic(org.eclipse.emf.common.util.Diagnostic)
	 */
	protected void handleDiagnostic(Diagnostic diagnostic) {
		if(diagnostic != null) {
			if(showSuccess || !isSuccessful()) {
				// save the reference to EclipseResourcesUtil and set eclipseResourcesUtil to null
				// so super method will not try to deal with the markers
				//
				EclipseResourcesUtil savedRef = eclipseResourcesUtil;
				eclipseResourcesUtil = null;
				try {
					super.handleDiagnostic(diagnostic);
				}
				finally {
					eclipseResourcesUtil = savedRef;
				}
			}
			
			// dealing with markers
			//
			if (eclipseResourcesUtil != null)				
			{
				// delete old markers
				//					
//				deleteMakers();
				
				Viewer viewer = null;
				if (!diagnostic.getChildren().isEmpty())
				{
					Map diagnosticToResourceMap = new HashMap();
					for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )
					{
						Diagnostic childDiagnostic = (Diagnostic)i.next();
						List data = childDiagnostic.getData();
						if (!data.isEmpty()) {
							Object o = data.get(0);
							if(o instanceof EObject) {
								Resource resource = ((EObject)o).eResource();
								if(resource != null) {
									diagnosticToResourceMap.put(childDiagnostic, resource);
								}
							}
						}
					}
										
					// create markers
					//
					for (Iterator i = diagnosticToResourceMap.entrySet().iterator(); i.hasNext(); )
					{
						Map.Entry entry = (Entry) i.next();
						Diagnostic childDiagnostic = (Diagnostic)entry.getKey();
						Resource resource = (Resource) entry.getValue();
						eclipseResourcesUtil.createMarkers(resource, childDiagnostic);
					}
					
					// select the first object with problem in the viewer if possible
					//
					List data = ((Diagnostic)diagnostic.getChildren().get(0)).getData();
					if (!data.isEmpty())
					{
						Object part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();						
						Object e = data.get(0);
						if (part instanceof ISetSelectionTarget)
						{
							((ISetSelectionTarget)part).selectReveal(new StructuredSelection(e));
						}
						else if (part instanceof IViewerProvider)
						{
							viewer = ((IViewerProvider)part).getViewer();
							if (viewer != null)
							{
								viewer.setSelection(new StructuredSelection(data.get(0)), true);
							}
						}
					}					
				}
				if(viewer == null) {
					Object part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
					if (part instanceof IViewerProvider)
					{
						viewer = ((IViewerProvider)part).getViewer();
					}
				}
				if(viewer != null) {
					// clear decoration cache
					//
					MethodElementLabelDecorator.clearDecorationCache();
					viewer.refresh();
				}
				
				try {
					refreshViews();
				}
				catch(Exception e) {
					
				}
			}
		}
	}

	private void deleteMarkers() {
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			eclipseResourcesUtil.deleteMarkers((Resource) iter.next());
		}
	}
	
	protected void refreshViews() {
		// no error markers for configuration view
		//
//		ConfigurationView.getView().getViewer().refresh();
	}

	public boolean isSuccessful() {
		return success;
	}
	
	@Override
	public boolean updateSelection(IStructuredSelection selection) {
		if (selection == null) {		//A hack for ValidataionManager to access deleteMarkers through ValidationAction API
			deleteMarkers();
			return false;
		}
		return super.updateSelection(selection);
	}
}
