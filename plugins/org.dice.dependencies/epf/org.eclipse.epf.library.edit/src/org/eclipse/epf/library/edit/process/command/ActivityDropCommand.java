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
package org.eclipse.epf.library.edit.process.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage.Literals;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.IActionTypeProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ActivityHandler;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IResourceScanner;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.ITextReferenceReplacer;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.ResourceFileCopyHandler;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Drop command for activity that supports extend, copy, and deep copy.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityDropCommand extends BSDropCommand {

	private Object viewer;

	private List<CapabilityPattern> oldPatterns;

	private List<CapabilityPattern> patterns;

	protected Process targetProcess;

	private boolean isDeliveryProcess;

	private int type;
	
	private Collection<Activity> appliedActivities;

	protected AdapterFactory adapterFactory;

	protected ActivityHandler activityHandler;

	protected boolean preExecuted;

	protected HashSet<?> addedObjects;
	
	protected IConfigurator activityDeepCopyConfigurator;
	
	private ResourceFileCopyHandler resourceFileCopyHandler;

	public ActivityDropCommand(Activity target, List activities, Object viewer, AdapterFactory adapterFactory) {
		super(target, activities);
		setLabel(LibraryEditResources.ActivityDropCommand_label); 
		this.viewer = viewer;
		this.adapterFactory = adapterFactory;
		targetProcess = TngUtil.getOwningProcess(target);
		
		isDeliveryProcess = targetProcess instanceof DeliveryProcess;
		if (isDeliveryProcess) {
			oldPatterns = new ArrayList(((DeliveryProcess) targetProcess)
					.getIncludesPatterns());
		}
		
		initResouceFileCopyHandler(activities);
		
	}

	private void initResouceFileCopyHandler(List activities) {
		if (activities != null && ! activities.isEmpty() &&
				 activities.get(0) instanceof BreakdownElement) {
			BreakdownElement dropElement0 = (BreakdownElement) activities.get(0);
			
			MethodPlugin srcPlugin = UmaUtil.getMethodPlugin(targetProcess);
			MethodPlugin tgtPlugin = UmaUtil.getMethodPlugin(dropElement0);
			
			boolean toCreateHandler = true;
			if (srcPlugin == tgtPlugin) {
				Process sourceProcess = TngUtil.getOwningProcess(dropElement0);
				boolean sIsDeliveryProcess = sourceProcess instanceof DeliveryProcess;
				if (isDeliveryProcess == sIsDeliveryProcess) {
					toCreateHandler = false;
				}
			}
			
			if (toCreateHandler) {
				ITextReferenceReplacer txtRefReplacer = ExtensionManager.getTextReferenceReplacer();
				if (txtRefReplacer != null) {
					IResourceScanner resourceScanner = txtRefReplacer.getResourceScanner();				
					resourceScanner.init(srcPlugin, tgtPlugin);				
					resourceFileCopyHandler = new ResourceFileCopyHandler(resourceScanner);
				}
			}
		}
	}

		
	/**
	 * If textual descriptions in the copied elements contain references (URLs)
	 * to other elements within the same copied process then replace these
	 * references with references that point to the new elements in the copied
	 * structures.
	 */
	private Collection replaceTextReferences(Map<MethodElement, MethodElement> originalToCopyMap_
			) {
		Collection modifiedResources = new HashSet();
		ITextReferenceReplacer txtRefReplacer = ExtensionManager
				.getTextReferenceReplacer();
		if (txtRefReplacer == null)
			return modifiedResources;
		
		for (Map.Entry<MethodElement, MethodElement> entry: originalToCopyMap_.entrySet()) {		
			if (entry.getValue() instanceof BreakdownElement) {
				EObject element = (EObject) entry.getValue();
				for (Iterator childIter = element.eAllContents(); childIter
						.hasNext();) {
					EObject child = (EObject) childIter.next();
					for (Iterator attributes = child.eClass()
							.getEAllAttributes().iterator(); attributes
							.hasNext();) {
						EAttribute attribute = (EAttribute) attributes.next();
						if (attribute.isChangeable()
								&& !attribute.isDerived()
								&& (attribute.isMany() || child
										.eIsSet(attribute))
								&& attribute.getEAttributeType()
										.getInstanceClass() == Literals.STRING
										.getInstanceClass()) {
							String text = (String) child.eGet(attribute);
							if (text != null) {
								String newtext = txtRefReplacer.replace(text,
										child, originalToCopyMap_);
								if (!newtext.equals(text)) {
									child.eSet(attribute, newtext);
									modifiedResources.add(child.eResource());
								}
							}
						}
					}
				}
			}		
		}
		originalToCopyMap_ = null;
		return modifiedResources;
	}
	
	
	public ActivityDropCommand(Activity target, List activities, Object viewer, 
			AdapterFactory adapterFactory,IConfigurator deepCopyConfigurator){
		this(target, activities, viewer, adapterFactory);
		this.activityDeepCopyConfigurator = deepCopyConfigurator;
	}
	
	@Override
	protected void prepareDropElements() {
		// remove any drop element that is suppress
		//
		for (Iterator<?> iter = dropElements.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			Process proc = TngUtil.getOwningProcess(element);
			if(proc != null) {
				Suppression supp = Suppression.getSuppression(proc);
				if(supp.isSuppressed(element)) {
					iter.remove();
				}
			}
		}

		super.prepareDropElements();		
	}
	
	public void setType(int type) {
		this.type = type;
		if(type == IActionTypeProvider.DEEP_COPY) {
			runAsJob = true;
		}
	}
	
	public IStatus checkCopy() {
		for (Object e : dropElements) {
			if(e instanceof Activity) {
				IStatus status = checkCircular((Activity) e);
				if(!status.isOK()) {
					return status;
				}
			}
		}
		return Status.OK_STATUS;
	}
	
	public IStatus checkExtend() {
		for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
//			BreakdownElement element = (BreakdownElement) iter.next();
//			
//			if ((ProcessUtil.hasContributorOrReplacer((Activity) element))){
//				canExtend = false;
//				break;
//			}
//			
//			Process proc = TngUtil.getOwningProcess(element);
//			if (targetProcess.getVariabilityBasedOnElement() == proc) {
//				canExtend = false;
//				break;
//			}
//			
//			if (proc instanceof CapabilityPattern && proc != targetProcess) {
//				canExtend = true;
//				break;
//			}
			
			Activity act = (Activity) iter.next();
			Process srcProc = TngUtil.getOwningProcess(act);
			if(srcProc instanceof DeliveryProcess && srcProc != targetProcess) {
				// cannot extend a different delivery process or any of its activities
				//
				return new Status(IStatus.ERROR, LibraryEditPlugin.PLUGIN_ID, 0,
						LibraryEditResources.cannot_copy_or_extend_delivery_process,
						null);
			}
			IStatus status = checkCircular(act);
			if(!status.isOK()) {
				return status;
			}
		}
		return Status.OK_STATUS;
	}
	
	private IStatus checkCircular(Activity act) {
		boolean result;
		if (DependencyChecker.newCheckAct) {
			result = DependencyChecker.checkCircularForMovingVariabilityElement
							(activity, Collections.singletonList(act), true);
		} else {
			result = DependencyChecker.checkCircularDependency(act, activity).isOK();
		}
		if(!result) {
			return new Status(IStatus.ERROR, LibraryEditPlugin.PLUGIN_ID, 0,
					LibraryEditResources.circular_dependency_error_msg,
					null);
		}
		return Status.OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#execute()
	 */
	public void execute() {
		IActionTypeProvider actionTypeProvider = (IActionTypeProvider) viewer;
		if ( viewer != null) {
			viewer = null;
			boolean canCopy = checkCopy().isOK();
			boolean canExtend = checkExtend().isOK();
			int[] choices = new int[1 + (canCopy ? 1 : 0) + (canExtend ? 1 : 0)];
			int i = 0;
			if(canCopy) {
				choices[i++] = IActionTypeProvider.COPY;
			}
			if(canExtend) {
				choices[i++] = IActionTypeProvider.EXTEND;
			}
			choices[i] = IActionTypeProvider.DEEP_COPY;
			
			// delegate the execution of this command
			//
			actionTypeProvider.execute(this, choices);

			return;
		}

		activityHandler = new ActivityHandler();
		try {
			if (type == IActionTypeProvider.DEEP_COPY) {
				doDeepCopy();
			} else {
				super.execute();
			}
		} finally {			
			originalToCopyMap = activityHandler.cloneOrignaltoCopyMap();
			if (originalToCopyMap != null && !originalToCopyMap.isEmpty()) {
				Set<Object> excludedKeySet =  new HashSet<Object>();
				
				for (Object key : originalToCopyMap.keySet()) {
					if (! (key instanceof MethodElement)) {
						excludedKeySet.add(key);
					}
				}
				for (Object key : excludedKeySet) {
					originalToCopyMap.remove(key);
				}
			}
			
			activityHandler.dispose();
		}
		
		if (type == IActionTypeProvider.COPY) {
			replaceTextReferences(originalToCopyMap);
		}
		
		
		if(TngUtil.DEBUG) {
			System.out.println("ActivityDropCommand.execute(): done"); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 */
	protected void doDeepCopy() {
		if(!UserInteractionHelper.confirmDeepCopy(dropElements)) {
			return;
		}
		MethodConfiguration deepCopyConfig = null;
		try {
			deepCopyConfig = UserInteractionHelper.chooseDeepCopyConfiguration(targetProcess, adapterFactory);
		}
		catch(OperationCanceledException e) {
			getModifiedResources().clear();
			return;
		}
		
		boolean copyExternalVariations;
		try {
			copyExternalVariations = UserInteractionHelper.copyExternalVariationsAllowed(targetProcess, adapterFactory);
		}
		catch(OperationCanceledException e) {
			getModifiedResources().clear();
			return;			
		}
		activityHandler.setCopyExternalVariations(copyExternalVariations);
		activityHandler.setDeepCopyConfig(deepCopyConfig);
		activityHandler.setTargetProcess(targetProcess);
		
		// Set a configurator to resolve and deep copy contributors and replacers
		activityHandler.setActivityDeepCopyConfigurator(activityDeepCopyConfigurator);
		
		IRunnableWithProgress runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				activityHandler.setMonitor(monitor);
				preExecuted = preExecute();
			}
			
		};
		UserInteractionHelper.runWithProgress(runnable, getLabel());			
		if (preExecuted) {
			redo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#preExecute()
	 */
	protected boolean preExecute() {
		if (!super.preExecute())
			return false;

		// process the selected activities and deep-copy the valid ones
		//
		if (isDeliveryProcess) {
			patterns = new ArrayList<CapabilityPattern>();
		}
		
		for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if ((element instanceof Activity)
			// && element != target
			) {
				Activity act = (Activity) element;
				
//				if(type == IActionTypeProvider.DEEP_COPY) {
//					activityHandler.deepCopy(act);
//				}
//				else {
//					Process proc = TngUtil.getOwningProcess(act);
//					if (proc instanceof CapabilityPattern && proc != targetProcess) {
//						if (type == IActionTypeProvider.EXTEND) {
//							activityHandler.extend(act);
//							if (patterns != null)
//								patterns.add(proc);
//						} else {
//							activityHandler.copy(act);
//						}
//					} else {
//						activityHandler.copy(act);
//					}
//				}
				
				switch(type) {
				case IActionTypeProvider.DEEP_COPY:
					activityHandler.deepCopy(element);
					break;
				case IActionTypeProvider.EXTEND:
					activityHandler.extend(act);
					if (patterns != null) {
						Process proc = TngUtil.getOwningProcess(act);
						if(proc instanceof CapabilityPattern) {
							patterns.add((CapabilityPattern) proc);
						}
					}
					break;
				case IActionTypeProvider.COPY:
					activityHandler.copy(act);
					break;
				}
			}
		}

		appliedActivities = new ArrayList<Activity>(activityHandler.getActivities());

		return !appliedActivities.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doExecute()
	 */
	protected void doExecute() {
		Iterator<Activity> itor = appliedActivities.iterator();

		ProcessPackage pkg = (ProcessPackage) activity.eContainer();
		while (itor.hasNext()) {
			Activity act = (Activity) itor.next();

			if (act.eContainer() != null
					&& act.eContainer().eContainer() != pkg) {
				pkg.getChildPackages().add((MethodPackage) act.eContainer());
			}

			if (patterns != null && !patterns.isEmpty()) {
				DeliveryProcess proc = (DeliveryProcess) targetProcess;
				for (CapabilityPattern pattern : patterns) {
					if (!proc.getIncludesPatterns().contains(pattern)) {
						proc.getIncludesPatterns().add(pattern);
					}
				}
			}
		}
		long time = 0;
		if(TngUtil.DEBUG) {
			time = System.currentTimeMillis();
		}
		activity.getBreakdownElements().addAll(appliedActivities);
		if(TngUtil.DEBUG) {
			System.out.println("ActivityDropCommand.doExecute(): new activities added. " + (System.currentTimeMillis() - time)); //$NON-NLS-1$
		}
		if(!activityHandler.getDeepCopies().isEmpty()) {
			if(TngUtil.DEBUG) {
				time = System.currentTimeMillis();
			}
			postDeepCopy();
			if(TngUtil.DEBUG) {
				System.out.println("ActivityDropCommand.doExecute(): postDeepCopy(). " + (System.currentTimeMillis() - time)); //$NON-NLS-1$
			}
		}

		// Hack to refresh providers forcefully before calling fixDuplicateNames
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(activity, ITreeItemContentProvider.class);
		adapter.getChildren(activity);
		
		// fix duplicate names of newly added elements
		fixDuplicateNames();
		
		getModifiedResources().add(activity.eResource());
	}

	/**
	 * 
	 */
	private void postDeepCopy() {
		Runnable runnable = new Runnable() {

			public void run() {
				// add to default configuratation of the target process any missing content
				//
				if(addedObjects == null) {
					addedObjects = new HashSet();
				}
				else {
					addedObjects.clear();
				}
				boolean defaultConfigChanged = false;
//				// disable notification on change to default configuration of target process to avoid excessive UI refreshes
//				//
//				boolean oldNotify = targetProcess.getDefaultContext().eDeliver();
//				targetProcess.getDefaultContext().eSetDeliver(false);
//				try {
				for (Iterator iter = activityHandler.getDeepCopies().iterator(); iter.hasNext();) {
					Iterator iterator = new AbstractTreeIterator(iter.next()) {

						/**
						 * Comment for <code>serialVersionUID</code>
						 */
						private static final long serialVersionUID = 1L;

						protected Iterator getChildren(Object object) {
							if(object instanceof Activity) {
								return ((Activity)object).getBreakdownElements().iterator();
							}
							return Collections.EMPTY_LIST.iterator();
						}

					};					
					while(iterator.hasNext()) {
						Object e = iterator.next();
						if(e instanceof Descriptor) {
							MethodElement me = ProcessUtil.getAssociatedElement((Descriptor) e);
							int size = addedObjects.size();
							ProcessUtil.addToDefaultConfiguration(targetProcess, me, addedObjects);
							if(!defaultConfigChanged && (size != addedObjects.size())) {
								if (targetProcess.getDefaultContext() != null
										&& targetProcess.getDefaultContext()
												.eResource() != null) {
									getModifiedResources().add(
											targetProcess.getDefaultContext()
													.eResource());
								}
								defaultConfigChanged = true;
							}
						}
					}
				}
//				}
//				finally {
//					targetProcess.getDefaultContext().eSetDeliver(oldNotify);
//				}
			}
			
		};
		
		UserInteractionHelper.runWithProgress(runnable, getLabel());
		
		// synchronize the deep copies with default configuration of the target process				
		//
		final SynchronizeCommand synchCmd = new SynchronizeCommand(activityHandler.getDeepCopies(), targetProcess.getDefaultContext(), null, false);
		try {
			runnable = new Runnable() {

				public void run() {
					synchCmd.initilize();
				}
				
			};
			UserInteractionHelper.runWithProgress(runnable, LibraryEditResources.ProcessAutoSynchronizeAction_prepare);
			if(synchCmd.isIntialized()) {
				synchCmd.execute();
			}
		}
		finally {
			synchCmd.dispose();
		}
	}
	
	protected Suppression getSuppression() {
		return Suppression.getSuppression(targetProcess);
	}

	/**
	 * Fixes duplicate names of newly added elements
	 */
	private void fixDuplicateNames() {
		Suppression suppression = getSuppression();
		for (Iterator iter = appliedActivities.iterator(); iter.hasNext();) {
			Activity act = (Activity) iter.next();
			fixDuplicateNames(act, suppression);
			fixTeamProfileDuplicateNames(act, suppression);
		}			
	}
	
	private void fixDuplicateNames(BreakdownElement e, Suppression suppression) {
		fixDuplicateNames(e, suppression, adapterFactory);
	}
	
	private static void fixDuplicateNames(BreakdownElement e, Suppression suppression, AdapterFactory adapterFactory) {
		String baseName = e.getName();
		if (ProcessUtil.checkBreakdownElementName(adapterFactory, e, baseName, suppression) != null) {
			for (int i = 1; true; i++) {
				String name = baseName + '_' + i;
				if (ProcessUtil.checkBreakdownElementName(adapterFactory, e, name, suppression) == null) {
					e.setName(name);
					break;
				}
			}
		}
		baseName = ProcessUtil.getPresentationName(e);
		if (ProcessUtil.checkBreakdownElementPresentationName(adapterFactory, e, baseName, suppression) != null) {
			for (int i = 1; true; i++) {
				String name = baseName + '_' + i;
				if (ProcessUtil.checkBreakdownElementPresentationName(adapterFactory, e, name, suppression) == null) {
					e.setPresentationName(name);
					break;
				}
			}
		}
	}		
	
	private void fixTeamProfileDuplicateNames(Activity act, Suppression suppression) {
		for (int i = act.getBreakdownElements().size() - 1; i > -1; i--) {
			Object element = act.getBreakdownElements().get(i);
			if(element instanceof TeamProfile) {
				fixDuplicateNames((BreakdownElement) element, suppression, TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());
			}
			else if(element instanceof Activity) {
				fixTeamProfileDuplicateNames((Activity) element, suppression);
			}
		}
	}
		

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doUndo()
	 */
	protected void doUndo() {
		activity.getBreakdownElements().removeAll(appliedActivities);
		if (isDeliveryProcess) {
			DeliveryProcess proc = (DeliveryProcess) targetProcess;
			proc.getIncludesPatterns().clear();
			proc.getIncludesPatterns().addAll(oldPatterns);
		}
	}

	public static void setName(List siblings, Activity e) {
		String baseName = e.getName();
		if (!isNameTaken(siblings, e, baseName)) {
			return;
		}
		for (int i = 1; true; i++) {
			String name = baseName + '_' + i;
			if (!isNameTaken(siblings, e, name)) {
				e.setName(name);
				return;
			}
		}
	}

	public static void setDefaultPresentationName(List siblings, Activity e) {
		// if(e.getPresentationName() != null &&
		// e.getPresentationName().trim().length() > 0) return;

		String basePresentationName = ProcessUtil.getPresentationName(e);
		if (!isPresentationNameTaken(siblings, e, basePresentationName)) {
			// e.setPresentationName(basePresentationName);
			return;
		}
		for (int i = 1; true; i++) {
			String name = basePresentationName + '_' + i;
			if (!isPresentationNameTaken(siblings, e, name)) {
				e.setPresentationName(name);
				return;
			}
		}
	}

	private static boolean isNameTaken(List siblings, DescribableElement e,
			String name) {
		for (int i = siblings.size() - 1; i > -1; i--) {
			BreakdownElement sibling = (BreakdownElement) siblings.get(i);
			// if(sibling != e && name.equals(sibling.getPresentationName())) {
			if (sibling != e && name.equals(sibling.getName())) {
				return true;
			}
		}
		return false;
	}

	private static boolean isPresentationNameTaken(List siblings,
			DescribableElement e, String name) {
		for (int i = siblings.size() - 1; i > -1; i--) {
			BreakdownElement sibling = (BreakdownElement) siblings.get(i);
			// if(sibling != e && name.equals(sibling.getPresentationName())) {
			if (sibling != e
					&& name.equals(ProcessUtil.getPresentationName(sibling))) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
	 */
	public Collection<?> getAffectedObjects() {
		if(executed) {
			return appliedActivities;
		}
		else {
			return Collections.EMPTY_LIST;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
	 */
	public Collection<?> getResult() {
		return getAffectedObjects();
	}

	public ActivityHandler getActivityHandler() {
		return activityHandler;
	}
	public Activity getActivity(){
		return activity;
	}
	public int getType(){
		return type;
	}

	public void setActivityDeepCopyConfigurator(
			IConfigurator activityDeepCopyConfigurator) {
		this.activityDeepCopyConfigurator = activityDeepCopyConfigurator;
	}
	
	private Map originalToCopyMap;
	
	public void scanAndCopyResources() {
		if (resourceFileCopyHandler == null) {
			return;
		}
		try {
			resourceFileCopyHandler.execute(originalToCopyMap);
		} catch (Throwable e){
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		} finally {
			resourceFileCopyHandler.getScanner().init(null, null);
			originalToCopyMap = null;
		}
	}

	public ResourceFileCopyHandler getResourceFileCopyHandler() {
		return resourceFileCopyHandler;
	}
	
}