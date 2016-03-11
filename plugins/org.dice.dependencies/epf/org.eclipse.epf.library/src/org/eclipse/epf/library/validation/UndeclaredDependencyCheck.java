package org.eclipse.epf.library.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.ValidationStatus;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.validation.LibraryEValidator;

public class UndeclaredDependencyCheck extends ValidationAction {
	
	private Map<MethodPlugin, Set<MethodPlugin>> baseMap;
	private Map<MethodPlugin, Set<MethodPlugin>> problemPluginMap;
	private Map<String, Set<MethodElement>> problemElementMap;
	private boolean skipContent = true;

	public UndeclaredDependencyCheck(ValidationManager mgr) {
		super(mgr);
	}
	
	@Override
	public void run() {
		Set<MethodPlugin> pluginSet = getMgr().getPluginSet();
		if (pluginSet == null || pluginSet.isEmpty()) {
			return;
		}

		//Initialization
		problemPluginMap = new HashMap<MethodPlugin, Set<MethodPlugin>>();
		problemElementMap = new HashMap<String, Set<MethodElement>>();
		baseMap = new HashMap<MethodPlugin, Set<MethodPlugin>>();

		Misc.clearCachedMap();
		for (MethodPlugin plugin : pluginSet) {
			Set baseSet = new HashSet();
			baseSet.addAll(Misc.getAllBase1(plugin));
			baseMap.put(plugin, baseSet);
		}
		Misc.clearCachedMap();

		//Validation check
		Set<MethodElement> processed = new HashSet<MethodElement>();
		for (MethodPlugin plugin : pluginSet) {
			checkReferences(plugin, processed);
		}
		

		MultiStatus multiStatus = new MultiStatus(LibraryPlugin.PLUGIN_ID, 0, "", null); //$NON-NLS-1$
		//Build markers
		for (Map.Entry<MethodPlugin, Set<MethodPlugin>> entry : problemPluginMap
				.entrySet()) {
			MethodPlugin plugin = entry.getKey();
			Set<MethodPlugin> set = entry.getValue();
			
			String msg0 = LibraryResources.bind(LibraryResources.UndeclaredDep_MarkerTxt1, (new String[] {plugin.getName()}));
			for (MethodPlugin p : set) {
				IMarker marker = getMgr().createMarker(plugin);
				String msg = msg0 + " '" + p.getName() + "'";	 //$NON-NLS-1$  //$NON-NLS-2$
				String key = plugin.getGuid() + p.getGuid();
				Set<MethodElement> elements = problemElementMap.get(key);
				if (elements != null && !elements.isEmpty()) {
					String line2msg0 = ". \n" + LibraryResources.UndeclaredDep_MarkerTxt2 + " ";	//$NON-NLS-1$ //$NON-NLS-2$
					String line2msg = line2msg0;
					for (MethodElement e : elements) {
						if (line2msg.length() > 155) {
							line2msg += " ... ";	 //$NON-NLS-1$  
							break;
						}
						if (line2msg != line2msg0) {
							line2msg += "; ";		 //$NON-NLS-1$  
						}
						line2msg += TngUtil.getLabelWithPath(e);
					}
					msg += line2msg;
				}							
				try {
					marker.setAttribute(IMarker.MESSAGE, msg);
					
					marker.setAttribute(IMarker.SEVERITY,
							IMarker.SEVERITY_WARNING);
					
					marker.setAttribute(IMarker.LOCATION, TngUtil.getLabelWithPath(plugin));
					marker.setAttribute(ValidationManager.validationType, ValidationManager.validationType_undeclaredDependancyCheck);
										
					MarkerInfo markerInfo = new MarkerInfo(plugin, p, elements);
					getMgr().addToMarkInfoMap(marker, markerInfo);
					
					multiStatus.add(new ValidationStatus(IStatus.WARNING, 0, LibraryResources.UndeclaredDep_MarkerTxt0, plugin, null));					
					
				} catch (Exception e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
		
		LibraryEValidator.appendDiagnostics(multiStatus, getMgr().getDiagnostics());		
		//Clear
		problemPluginMap = null;
		problemElementMap = null;
		baseMap =null;
	}
	
	private void addToProblemPluginMap(MethodPlugin plugin, MethodPlugin referencedPlugin) {
		Set<MethodPlugin> set = problemPluginMap.get(plugin);
		if (set == null) {
			set = new HashSet<MethodPlugin>();
			problemPluginMap.put(plugin, set);
		}
		set.add(referencedPlugin);
	}
	
	
	private void checkReferences(MethodElement me, Set<MethodElement> processed) {
		if (processed.contains(me)) {
			return;
		}
		processed.add(me);
		MethodPlugin ownerPlugin = UmaUtil.getMethodPlugin(me);
		if ( ! getMgr().getPluginSet().contains(ownerPlugin)) {
			return;
		}
		
		Set<MethodPlugin> baseSet = baseMap.get(ownerPlugin);
				
		EList<EReference> refList = me.eClass().getEAllReferences();
		if (refList == null || refList.isEmpty()) {
			return;
		}
		for (EReference ref : refList) {
			if (skipContent && LibraryUtil.isContentRef(ref)) {
				continue;
			}
			if (ref == UmaPackage.eINSTANCE.getRole_Modifies()) {
				continue;
			}
			if (ref == UmaPackage.eINSTANCE.getRoleDescriptor_Modifies()) {
				continue;
			}
			Object obj = me.eGet(ref);
			if (obj instanceof MethodElement) {
				MethodElement referenced = (MethodElement) obj;
				recordOneHit(me, ownerPlugin, baseSet, referenced);				
				checkReferences(referenced, processed);
				
			} else if (obj instanceof List) {
				List list = (List) obj;
				for (Object itemObj : list) {
					if (itemObj instanceof MethodElement) {
						MethodElement referenced = (MethodElement) itemObj;					
						recordOneHit(me, ownerPlugin, baseSet, referenced);	
						checkReferences(referenced, processed);
					}
				}
			}
		}
	}

	private void recordOneHit(MethodElement me, MethodPlugin ownerPlugin,
			Set<MethodPlugin> baseSet, MethodElement referenced) {
		MethodPlugin referencedPlugin = UmaUtil.getMethodPlugin(referenced);
		if (referencedPlugin != null && referencedPlugin != ownerPlugin && !baseSet.contains(referencedPlugin)) {
			addToProblemPluginMap(ownerPlugin, referencedPlugin);
			String key = ownerPlugin.getGuid() + referencedPlugin.getGuid();
			Set<MethodElement> set = problemElementMap.get(key);
			if (set == null) {
				set = new HashSet<MethodElement>();
				problemElementMap.put(key, set);
			}
			set.add(me);
		}
	}
		
	public void clearResults() {		
	}

	public String addPluginFix(IMarker marker) {
		String emptyStr = "";	//$NON-NLS-1$ 
		
		Object obj = getMgr().getMarkInfo(marker);
		MarkerInfo info = obj instanceof MarkerInfo ? (MarkerInfo) obj : null;
		if (info == null) {
			return emptyStr;
		}
		if (info.referencing == null || info.referenced == null || info.referencing == info.referenced) {
			return emptyStr;
		}
		
		if (Misc.isBaseOf(info.referencing, info.referenced, new HashMap())) {
			return LibraryEditResources.circular_dependency_error_msg;	
		}
		
		info.referencing.getBases().add(info.referenced);
		Resource resource = info.referencing.eResource();
		if (resource == null || !LibraryEditUtil.getInstance().save(Collections.singleton(resource))) {			
			info.referencing.getBases().remove(info.referenced);
			LibraryPlugin.getDefault().getLogger().logError("addPluginFix falied at save");//$NON-NLS-1$ 
			return emptyStr;
		}
		
		getMgr().removeFromMarkInfoMap(marker);
		return emptyStr;
	}
	
	public String removeReferenceFix(IMarker marker) {
		String emptyStr = ""; //$NON-NLS-1$ 
		
		Object infoObj = getMgr().getMarkInfo(marker);
		MarkerInfo info = infoObj instanceof MarkerInfo ? (MarkerInfo) infoObj : null;
		if (info == null || info.referenced == null) {
			return emptyStr;
		}
		Set<MethodElement> offenderElements = info.offenderElements;
		if (offenderElements == null || offenderElements.isEmpty()) {
			return emptyStr;
		}
		Set<Resource> resources = new HashSet<Resource>();
		for (MethodElement e : offenderElements) {
			Resource res = e.eResource();
			if (res != null) {
				resources.add(res);
			}
		}
		List<String> pathList = new ArrayList<String>();
		for (Resource res : resources) {
			pathList.add(res.getURI().toFileString());
		}
		
		IStatus status = FileManager.getInstance().checkModify(pathList.toArray(new String[pathList.size()]), LibraryPlugin.getDefault().getContext());
		if (!status.isOK()) {
			info.referencing.getBases().remove(info.referenced);
			LibraryPlugin.getDefault().getLogger().logError("addPluginFix falied at file check");//$NON-NLS-1$ 
			return emptyStr;
		}

		for (MethodElement me : offenderElements) {				
			EList<EReference> refList = me.eClass().getEAllReferences();
			if (refList == null) {
				continue;
			}
			for (EReference ref : refList) {				
				if (skipContent && LibraryUtil.isContentRef(ref)) {
					continue;
				}
				if (ref == UmaPackage.eINSTANCE.getRole_Modifies()) {
					continue;
				}
				Object obj = me.eGet(ref);
				if (obj instanceof MethodElement) {
					MethodElement referenced = (MethodElement) obj;
					if (info.referenced == UmaUtil.getMethodPlugin(referenced)) {
						me.eSet(ref, null);
					}
					
				} else if (obj instanceof List) {
					List list = (List) obj;
					for (int i = list.size() - 1; i >= 0 ; i--) {
						Object itemObj = list.get(i);
						if (itemObj instanceof MethodElement) {
							MethodElement referenced = (MethodElement) itemObj;	
							if (info.referenced == UmaUtil.getMethodPlugin(referenced)) {
								list.remove(i);
							}
						}
					}
				}
			}

		}
		
		if (!LibraryEditUtil.getInstance().save(resources)) {			
			info.referencing.getBases().remove(info.referenced);
			LibraryPlugin.getDefault().getLogger().logError("addPluginFix falied at save");//$NON-NLS-1$ 
			return emptyStr;
		}
		
		getMgr().removeFromMarkInfoMap(marker);
		return emptyStr;
	}
	
	static class MarkerInfo {
		MethodPlugin referencing;
		MethodPlugin referenced;
		Set<MethodElement> offenderElements;
		public MarkerInfo(MethodPlugin referencing, MethodPlugin referenced, Set<MethodElement> offenderElements) {
			this.referencing = referencing;
			this.referenced = referenced;
			this.offenderElements = offenderElements;
		}
	}
	
}
