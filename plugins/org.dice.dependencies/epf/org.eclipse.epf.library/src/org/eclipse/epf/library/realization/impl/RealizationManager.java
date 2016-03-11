package org.eclipse.epf.library.realization.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.command.CustomizeDescriptorCommand;
import org.eclipse.epf.library.edit.process.command.ProcessCommandUtil;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.realization.IRealizedDescriptor;
import org.eclipse.epf.library.edit.realization.IRealizedElement;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class RealizationManager implements IRealizationManager {

	private Map<MethodElement, IRealizedElement> elementMap;
	private MethodConfiguration config;
	private MethodConfiguration dynamicConfig;

	private boolean caching = false;
	private IPerspectiveListener perspectiveListener;
	private boolean localTiming = true;	
	
	public boolean isCaching() {
		return caching;
	}

	public void setCaching(boolean caching) {
		this.caching = caching;
	}

	private Map<MethodElement, IRealizedElement> getElementMap() {
		if (elementMap == null) {
			elementMap = new HashMap<MethodElement, IRealizedElement>();
		}
		return elementMap;
	}
	
	public RealizationManager(MethodConfiguration config) {
		this.config = config;
		init();
	}
	
	public void clearCacheData() {
		for (IRealizedElement element : getElementMap().values()) {
			((RealizedElement) element).dispose();
		}		
		elementMap = null;
	}
	
	public void dispose() {
		clearCacheData();
		
		IWorkbenchWindow window = LibraryUtil.getActiveWorkbenchWindow();
		if (window != null && perspectiveListener != null) {
			window.removePerspectiveListener(perspectiveListener);
		}
	}
	
	public MethodConfiguration getConfig() {
		if (config == null) {	//Default RealizationManager instance would not have a null config
			return dynamicConfig;
		}
		return config;
	}	

	public IRealizedElement getRealizedElement(MethodElement element) {
		IRealizedElement rElement = getElementMap().get(element);
		if (rElement == null) {
			rElement = newRealizedElement(element);
			getElementMap().put(element, rElement);
		}
		return rElement;
	}
	
	public IRealizedElement removeRealizedElement(MethodElement element) {
		return getElementMap().remove(element);
	}
	
	private void init() {
		IWorkbenchWindow window = LibraryUtil.getActiveWorkbenchWindow();
		if (window != null) {
			perspectiveListener = new IPerspectiveListener() {
				public void perspectiveActivated(IWorkbenchPage page,
						IPerspectiveDescriptor desc) {
					clearCacheData();
				}

				public void perspectiveChanged(IWorkbenchPage page,
						IPerspectiveDescriptor desc, String id) {
					clearCacheData();
				}
			};
			window.addPerspectiveListener(perspectiveListener);
		}
	}
	
	private IRealizedElement newRealizedElement(MethodElement element) {
		RealizedElement rElement = null;
		if (element instanceof TaskDescriptor) {
			rElement = new RealizedTaskDescriptor((TaskDescriptor) element);
			
		} else if (element instanceof RoleDescriptor) {
			rElement = new RealizedRoleDescriptor((RoleDescriptor) element);
			
		} else if (element instanceof WorkProductDescriptor) {
			rElement = new RealizedWorkProductDescriptor((WorkProductDescriptor) element);
			
		} else if (element instanceof BreakdownElement) {
			rElement = new RealizedBreakdownElement((BreakdownElement) element);
			
		}
					
		rElement.setMgr(this);
		
		return rElement;
	}
	
	private MethodElement getLinkedElement(MethodElement element) {
		if (element instanceof RoleDescriptor) {
			return ((RoleDescriptor) element).getRole();
		}
		
		if (element instanceof WorkProductDescriptor) {
			return ((WorkProductDescriptor) element).getWorkProduct();
		}
		
		if (element instanceof TaskDescriptor) {
			return ((TaskDescriptor) element).getTask();
		}
		
		return PropUtil.getPropUtil().getLinkedElement(element);
	}

	public Descriptor getDescriptor(Descriptor referencingDes, Activity parentAct, MethodElement element, EReference feature) {
		Descriptor descriptor = getDescriptor_(referencingDes, parentAct, element, feature);
		if (feature == IRealizedDescriptor.ArtifactDescriptor_ContainedArtifacts) {
			return descriptor;
		}		
		
		boolean oldDeliver = referencingDes.eDeliver();
		referencingDes.eSetDeliver(false);
		LibraryEditUtil libEditUtil = LibraryEditUtil.getInstance();
		try {
			if (feature.isMany()) {
//				List listValue = (List) referencingDes.eGet(feature);
				List listValue = (List) TypeDefUtil.getInstance().eGet(referencingDes, feature, true);
				if (listValue != null) {
					listValue.add(descriptor);
					libEditUtil.addOppositeFeature(referencingDes, descriptor, feature);
				}
			} else {
				referencingDes.eSet(feature, descriptor);
				libEditUtil.addOppositeFeature(referencingDes, descriptor, feature);
			}
		} finally {
			referencingDes.eSetDeliver(oldDeliver);
		}

		return descriptor;
	}
	
	private Descriptor getDescriptor_(Descriptor referencingDes, Activity parentAct, MethodElement element, EReference feature) {
		if (parentAct == null) {
			return null;
		}
		
		Object foundDes = ProcessCommandUtil.getBestDescriptor(element, parentAct, getConfig());
		if (foundDes instanceof Descriptor) {
			return (Descriptor) foundDes;
		}
		
		foundDes = ProcessCommandUtil.getInheritedDescriptor(element, parentAct, getConfig());
		if (foundDes instanceof Descriptor) {
			return (Descriptor) foundDes;
		}
		
		Descriptor descriptor = null;
		if (element instanceof Role) {
			RoleDescriptor rd = UmaFactory.eINSTANCE.createRoleDescriptor();
			rd.setRole((Role) element);
			descriptor = rd;
			
		} else if (element instanceof Task) {
			TaskDescriptor td = UmaFactory.eINSTANCE.createTaskDescriptor();
			td.setTask((Task) element);
			descriptor = td;
			
		} else if (element instanceof WorkProduct) {
			WorkProductDescriptor wpd = UmaFactory.eINSTANCE.createWorkProductDescriptor();
			wpd.setWorkProduct((WorkProduct) element);
			descriptor = wpd;
		}
		
		if (debug) {
			System.out.println("LD> Creating descriptor: " + descriptor); //$NON-NLS-1$
		}
		
		if (descriptor == null) {
			return null;
		}
		
		DescriptorPropUtil.getDesciptorPropUtil().setCreatedByReference(descriptor, true);
			
		String presentationName = element.getPresentationName();
		descriptor.setName(element.getName());
		descriptor.setPresentationName(StrUtil.isBlank(presentationName) ? element
				.getName() : presentationName);
		String guid = UmaUtil.generateGUID();
		descriptor.setBriefDescription(element.getBriefDescription());

		addToProcess(parentAct, descriptor, feature);	
		
		return descriptor;
	}
	
	private void addToProcess(Activity parent, Descriptor referencedDes,
			EReference feature) {
		UmaPackage up = UmaPackage.eINSTANCE;
		ProcessPackage pkg = (ProcessPackage) parent.eContainer();

		boolean oldParentDeliver = parent.eDeliver();
		boolean oldPkgDeliver = pkg.eDeliver();
		boolean oldReferencedDesDeliver = referencedDes.eDeliver();
		parent.eSetDeliver(false);
		pkg.eSetDeliver(false);

		try {
			parent.getBreakdownElements().add(referencedDes);
			pkg.getProcessElements().add(referencedDes);

			if (feature == up.getWorkProductDescriptor_DeliverableParts()) {
				referencedDes.eSetDeliver(false);
				referencedDes.setSuperActivities(null);
			}
		} finally {
			parent.eSetDeliver(oldParentDeliver);
			pkg.eSetDeliver(oldPkgDeliver);
			if (referencedDes.eDeliver() != oldReferencedDesDeliver) {
				referencedDes.eSetDeliver(oldReferencedDesDeliver);
			}
		}

	}
	
	public void updateProcessModel(Process proc) {
		clearCacheData();
		setCaching(true);
		updateProcessModel(proc, new HashSet<Activity>());
		clearCacheData();
		setCaching(false);
	}
	
	public void elementUpdateProcessModel(Process proc,
			Set<MethodElement> changedElementSet) {

		Set<Activity> actCollectedSet = new HashSet<Activity>();
		collectActivitiesToUpdate(proc, actCollectedSet,
				new HashSet<Activity>(), changedElementSet);
		Set<Activity> updatedActSet = new HashSet<Activity>();
		clearCacheData();
		setCaching(true);
		for (Activity act : actCollectedSet) {
			this.updateModelImpl(act, updatedActSet, false);
		}
		clearCacheData();
		setCaching(false);		
	}
	
	private void collectActivitiesToUpdate(Activity act,
			Set<Activity> actCollectedSet, Set<Activity> actProcessedSet,
			Set<MethodElement> changedElementSet) {
		if (actProcessedSet.contains(act)) { // extra precaution to prevent infinite loop
			return;
		}
		actProcessedSet.add(act);
		
		Activity baseAct = (Activity) act.getVariabilityBasedOnElement();
		if (baseAct != null) {
			collectActivitiesToUpdate(baseAct, actCollectedSet, actProcessedSet, changedElementSet);
		}

		List<BreakdownElement> beList = act.getBreakdownElements();
		for (int i = 0; i < beList.size(); i++) {
			BreakdownElement be = beList.get(i);
			if (!actCollectedSet.contains(act)) {
				MethodElement element = getLinkedElement(be);
				if (element != null) {
					if (changedElementSet.contains(element)) {
						actCollectedSet.add(act);
	
					} else if (be instanceof Descriptor) {
						if (changedElementSet.contains(be)) {
							actCollectedSet.add(act);
						}
					}
				}
			}
			if (be instanceof Activity) {
				collectActivitiesToUpdate((Activity) be, actCollectedSet,
						actProcessedSet, changedElementSet);
			}
		}
	}

	private void updateProcessModel(Process proc, Set<Activity> updatedActSet) {
		if (! ProcessUtil.isSynFree()) {
			if (! ProcessScopeUtil.getInstance().isConfigFree(proc)) {
				return;
			}
		}
		if (! canBeAutoSyned(proc)) {
			return;
		}
		long time = 0;
		if (timing && localTiming) {
			time = System.currentTimeMillis();
		}
		updateModelImpl(proc, updatedActSet, true);
		if (timing && localTiming) {
			time = System.currentTimeMillis() - time;
			System.out.println("LD> updateModel: " + time); //$NON-NLS-1$
		}
	}
	
	public void updateActivityModel(Activity act) {
		if (act instanceof Process) {
			if (! canBeAutoSyned((Process) act)) {
				return;
			}			
		}

		clearCacheData();
		setCaching(true);
		updateModelImpl(act, new HashSet<Activity>(), false);
		clearCacheData();
		setCaching(false);
	}
	
	private void updateModelImpl(Activity act, Set<Activity> updatedActSet, boolean recursive) {
		if (updatedActSet.contains(act)) {
			return;
		}
		//System.out.println("LD> updateModelImpl, act: " + act);
		
		updatedActSet.add(act);
		Activity baseAct = (Activity) act.getVariabilityBasedOnElement();
		if (baseAct != null) {
			updateModelImpl(baseAct, updatedActSet, recursive);
		}
		if (! ProcessUtil.isSynFree()) {
			Process proc = ProcessUtil.getProcess(act);
			if (proc == null || ! ProcessScopeUtil.getInstance().isConfigFree(proc)) {
				return;
			}
		}
				
		if (config == null) {
			Process proc = ProcessUtil.getProcess(act);
			Scope scope = ProcessScopeUtil.getInstance().getScope(proc);
			if (scope != null) {
				dynamicConfig = scope;
			} else {
				dynamicConfig = LibraryService.getInstance()
						.getCurrentMethodConfiguration();
			}
		}
		
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		
		Set<Descriptor> tdReferencedSet = new HashSet<Descriptor>();
		Set<Descriptor> seenSet = new HashSet<Descriptor>();
		List<Descriptor> rdwpdList = new ArrayList<Descriptor>();
		List<BreakdownElement> beList =  act.getBreakdownElements();
		Set<Descriptor> localUseSet = new HashSet<Descriptor>(); 
		for (int i = 0; i < beList.size(); i++) {
			BreakdownElement be = beList.get(i);
			if (be instanceof Activity) {
				if (recursive) {
					updateModelImpl((Activity) be, updatedActSet, recursive);
				}
				
			} else if (be instanceof TaskDescriptor) {
				TaskDescriptor td = (TaskDescriptor) be;
				Descriptor greenParent = propUtil.getGreenParentDescriptor(td);
				if (greenParent != null) {
					CustomizeDescriptorCommand.updateFromGreenParent(greenParent, td, false);
				}
				
				collectAllReferences(td, tdReferencedSet, seenSet);

			} else if (be instanceof RoleDescriptor) {
				RoleDescriptor rd = (RoleDescriptor) be;
				rdwpdList.add(rd);
				
			} else if (be instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpd = (WorkProductDescriptor) be;
				rdwpdList.add(wpd);
			}
			
			if (be instanceof Descriptor) {
				localUseSet.addAll(propUtil.getLocalUsedDescriptors((Descriptor) be));
			}
		}
		
		Set<Descriptor> toRemovedSet = new HashSet<Descriptor>();
		for (Descriptor des : rdwpdList) {
			collectAllReferences(des, null, seenSet);
			
			if (des instanceof TaskDescriptor || localUseSet.contains(des) ||
					! propUtil.isCreatedByReference(des)) {
				continue;
			}
			if (!tdReferencedSet.contains(des)) {
//				act.getBreakdownElements().remove(des);
				toRemovedSet.add(des);
			}
		}
		for (Descriptor des : toRemovedSet) {
			if (! ProcessUtil.checkDescriptorReferences(toRemovedSet, des)) {
				act.getBreakdownElements().remove(des);
			}
		}
		
		beList =  act.getBreakdownElements();
		for (int i = 0; i < beList.size(); i++) {
			BreakdownElement be = beList.get(i);
			if (be instanceof Descriptor) {
				RealizedDescriptor rdes = (RealizedDescriptor) getRealizedElement(be);	
				rdes.updateStringValues();
			} else {
				RealizedBreakdownElement rBe = (RealizedBreakdownElement) getRealizedElement(be);
				rBe.updateAndGetAllReferenced();
				rBe.updateStringValues();
			}
		}
				
	}
	
	private void collectAllReferences(Descriptor des, Set<Descriptor> collectingSet, Set<Descriptor> seenSet) {
		if (seenSet.contains(des)) {
			return;
		}
		seenSet.add(des);
		
		RealizedDescriptor rdes = (RealizedDescriptor) getRealizedElement(des);
		Set<Descriptor> references = rdes.updateAndGetAllReferenced();
		if (collectingSet != null) {
			collectingSet.addAll(references);
		}
		for (Descriptor ref : references) {
			collectAllReferences(ref, collectingSet, seenSet);
		}
	}
	
	public void updateAllProcesseModels() {		
		boolean oldLocalTiming = localTiming;
		long time = 0;
		if (timing) {
			time = System.currentTimeMillis();
			localTiming = false;
		}

		Set<Activity> updatedActSet = new HashSet<Activity>();
		clearCacheData();
		setCaching(true);
		for (Process proc : LibraryEditUtil.getInstance().collectProcessesFromConfig(
				getConfig())) {
			updateProcessModel(proc, updatedActSet);
		}
		clearCacheData();
		setCaching(false);
		if (timing) {
			time = System.currentTimeMillis() - time;
			System.out.println("LD> beginPublish: " + time); //$NON-NLS-1$
			localTiming = oldLocalTiming;
		}
	}

	private boolean canBeAutoSyned(Process proc) {
		boolean ret = ConfigurationHelper.getDelegate().canBeConfigFree(proc);
		return ret;
	}
	
}
