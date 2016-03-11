package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.util.model.util.StringResource;
import org.eclipse.epf.library.edit.validation.IValidationManager;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

public class LibraryEditUtil {
	
	private static boolean debug = false;
	private static LibraryEditUtil instance = new LibraryEditUtil();
	private IRealizationManager defaultRealizationManager;
	private ILibraryEditUtilProvider provider;
	private boolean junitTest = false;
	
	private static Map<EReference, EReference> taskGuidanceRefMap;
	private static Map<EReference, EReference> roleGuidanceRefMap;
	private static Map<EReference, EReference> workproductGuidanceRefMap;
	private static UmaPackage up = UmaPackage.eINSTANCE;
	private static Set<EReference> autoSynReferences;

	public static LibraryEditUtil getInstance() {
		return instance;
	}
	
	private LibraryEditUtil() {		
	}
	
	public ILibraryEditUtilProvider getProvider() {
		if (provider == null) {
			provider = ExtensionManager.getLibraryEditUtilProvider();
		}
		return provider;
	}
	
	
	public IValidationManager getValidationManager() {
		ILibraryEditUtilProvider p = getProvider();				
		return p == null ? null : p.getValidationManager();
	}
	
	public IRealizationManager getDefaultRealizationManager() {
		return ProcessUtil.isSynFree() ? defaultRealizationManager : null;
	}

	public void setDefaultRealizationManager(
			IRealizationManager defaultRealizationManager) {
		this.defaultRealizationManager = defaultRealizationManager;
	}
	
	public Set<Descriptor> collectDescriptors(Process process) {			
		Set<Descriptor> descriptors = new HashSet<Descriptor>();
		
		EObject container = process.eContainer();
		if (! (container instanceof ProcessComponent)) {
			return descriptors;
		}
		
		for (Iterator iter = container.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (element instanceof Descriptor) {
				descriptors.add((Descriptor) element);
			}
		}
		return descriptors;
	}
	

	public Set<Process> collectProcesses(MethodElement libOrPlugin) {
		Set<Process> processes = new HashSet<Process>();
		if (! (libOrPlugin instanceof MethodLibrary ||
				libOrPlugin instanceof MethodPlugin)) {
			return processes;
		}
		for (Iterator iter = libOrPlugin.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (element instanceof Process) {				
				collectProcess((Process) element, processes);
			}
		}
		return processes;
	}

	private void collectProcess(Process process, Set<Process> processes) {
		if (process instanceof CapabilityPattern ||
				process instanceof DeliveryProcess) {
			if (process.eContainer() instanceof ProcessComponent) {
				processes.add(process);
			}
		}
	}

	public Set<Process> collectProcessesFromConfig(MethodConfiguration config) {
		Set<Process> result = new HashSet<Process>();
		List<MethodPlugin> plugins = config.getMethodPluginSelection();
		for (int i = 0; i < plugins.size(); i++) {
			Set<Process> set = collectProcesses(plugins.get(i));
			result.addAll(set);
		}
		return result;
	}

	public boolean isSynFree() {
		ILibraryEditUtilProvider p = getProvider();				
		return p == null ? true : p.isSynFree();
	}
	
	public MethodElement getMethodElement(String guid) {
		return getMethodElement(guid, true);
	}
	
	public MethodElement getMethodElement(String guid, boolean skipContent) {
		ILibraryEditUtilProvider p = getProvider();				
		return p == null ? null : p.getMethodElement(guid, skipContent);		
	}
	
	public String getPresentationName(MethodElement element,
			MethodConfiguration config) {
		String pName = element.getPresentationName();
		if (! StrUtil.isBlank(pName)) {
			return pName;
		}		
		ILibraryEditUtilProvider p = getProvider();
		if (p == null) {
			return pName;
		}
		return p.getPresentationName(element, config);
	}
	
	public boolean isDynamicAndExclude(Object obj, Descriptor desc,
			EReference ref, MethodConfiguration config) {
		return getProvider().isDynamicAndExclude(obj, desc, ref, config);
	}

	public boolean isDynamic(Object obj, Descriptor desc, EReference ref) {
		return getProvider().isDynamic(obj, desc, ref);
	}
	
	public MethodLibrary getCurrentMethodLibrary() {
		return getProvider().getCurrentMethodLibrary();
	}
	
	public boolean isGuidanceDynamic(Object obj, Descriptor desc,
			MethodConfiguration config) {
		return getProvider().isGuidanceDynamic(obj, desc, config);
	}
		
	public EReference getExcludeFeature(EReference ref) {
		if (ref == up.getTaskDescriptor_PerformedPrimarilyBy()) {
			return up.getTaskDescriptor_PerformedPrimarilyByExcluded();
		}
		
		if (ref == up.getTaskDescriptor_AdditionallyPerformedBy()) {
			return up.getTaskDescriptor_AdditionallyPerformedByExclude();				
		}
		
		if (ref == up.getTaskDescriptor_MandatoryInput()) {
			return up.getTaskDescriptor_MandatoryInputExclude();
		}
		
		if (ref == up.getTaskDescriptor_OptionalInput()) {
			return up.getTaskDescriptor_OptionalInputExclude();
		}
		
		if (ref == up.getTaskDescriptor_Output()) {
			return up.getTaskDescriptor_OutputExclude();
		}
		
		if (ref == up.getTaskDescriptor_SelectedSteps()) {
			return up.getTaskDescriptor_SelectedStepsExclude();
		}
		
		if (ref == up.getRoleDescriptor_ResponsibleFor()) {
			return up.getRoleDescriptor_ResponsibleForExclude();
		}
		
		if (ref == up.getWorkProductDescriptor_DeliverableParts()) {
			return up.getWorkProductDescriptor_DeliverablePartsExclude();
		}
		
		if (ref == up.getBreakdownElement_Checklists() || ref == up.getBreakdownElement_Concepts()
				|| ref == up.getBreakdownElement_Examples() || ref == up.getBreakdownElement_SupportingMaterials()
				|| ref == up.getBreakdownElement_Guidelines() || ref == up.getBreakdownElement_ReusableAssets()
				|| ref == up.getBreakdownElement_Templates() || ref == up.getBreakdownElement_Reports()
				|| ref == up.getBreakdownElement_Toolmentor() || ref == up.getBreakdownElement_Estimationconsiderations()
				|| ref == UmaUtil.MethodElement_UdtList) {
			return up.getDescriptor_GuidanceExclude();
		}
		
		//...		
		return null;
	}
	
	public EReference getLinkedElementFeature(EReference descriptorRef) {
		if (descriptorRef == up.getTaskDescriptor_PerformedPrimarilyBy()) {
			return up.getTask_PerformedBy();
		}
		
		if (descriptorRef == up.getTaskDescriptor_AdditionallyPerformedBy()) {
			return up.getTask_AdditionallyPerformedBy();				
		}
		
		if (descriptorRef == up.getTaskDescriptor_MandatoryInput()) {
			return up.getTask_MandatoryInput();
		}
		
		if (descriptorRef == up.getTaskDescriptor_OptionalInput()) {
			return up.getTask_OptionalInput();
		}
		
		if (descriptorRef == up.getTaskDescriptor_Output()) {
			return up.getTask_Output();
		}
		
		if (descriptorRef == up.getTaskDescriptor_SelectedSteps()) {
			return up.getTask_Steps();
		}
		
		if (descriptorRef == up.getRoleDescriptor_ResponsibleFor()) {
			return up.getRole_ResponsibleFor();
		}
		
		if (descriptorRef == up.getWorkProductDescriptor_DeliverableParts()) {
			return up.getDeliverable_DeliveredWorkProducts();
		}
		
		//To do: guidances need to be handled differently
//		if (descriptorRef == up.getBreakdownElement_Checklists() || descriptorRef == up.getBreakdownElement_Concepts()
//				|| descriptorRef == up.getBreakdownElement_Examples() || descriptorRef == up.getBreakdownElement_SupportingMaterials()
//				|| descriptorRef == up.getBreakdownElement_Guidelines() || descriptorRef == up.getBreakdownElement_ReusableAssets()
//				|| descriptorRef == up.getBreakdownElement_Templates() || descriptorRef == up.getBreakdownElement_Reports()
//				|| descriptorRef == up.getBreakdownElement_Toolmentor() || descriptorRef == up.getBreakdownElement_Estimationconsiderations()) {
//			return up.getDescriptor_GuidanceExclude();
//		}
		
		//...		
		return null;
	}
	
	
	public boolean isJunitTest() {
		return junitTest;
	}

	public void setJunitTest(boolean junitTest) {
		this.junitTest = junitTest;
	}
	
	public List<EReference> getExcludeRefList(Descriptor des) {
		List<EReference> list = new ArrayList<EReference>();

		if (des instanceof TaskDescriptor) {
			list.add(up.getTaskDescriptor_PerformedPrimarilyByExcluded());
			list.add(up.getTaskDescriptor_AdditionallyPerformedByExclude());
			list.add(up.getTaskDescriptor_MandatoryInputExclude());
			list.add(up.getTaskDescriptor_OptionalInputExclude());
			list.add(up.getTaskDescriptor_OutputExclude());

		} else if (des instanceof RoleDescriptor) {
			list.add(up.getRoleDescriptor_ResponsibleForExclude());

		} else if (des instanceof WorkProductDescriptor) {
			list.add(up.getWorkProductDescriptor_DeliverablePartsExclude());
		}

		return list;
	}
	
	public boolean isGuidanceListReference(EReference ref) {
		if (ref.isContainment() || !ref.isMany()) {
			return false;
		}
		if (ref == UmaPackage.eINSTANCE.getDescriptor_GuidanceAdditional()
				|| ref == UmaPackage.eINSTANCE.getDescriptor_GuidanceExclude()) {
			return false;
		}
		if (ref.getEType() instanceof EClass) {
			Object obj = null;
			try {
				obj = UmaFactory.eINSTANCE.create((EClass) ref.getEType());
			} catch (Exception e) {
				return false;				
			}
			return obj instanceof Guidance;
		}
		return false;
	}
	
	public synchronized Map<EReference, EReference> getGuidanceRefMap(EClass cls) {
		if (cls == up.getTask()) {
			if (taskGuidanceRefMap == null) {
				taskGuidanceRefMap = buildGuidanceMap(cls, up.getTaskDescriptor());
			}
			return taskGuidanceRefMap;
		}
		if (cls == up.getRole()) {
			if (roleGuidanceRefMap == null) {
				roleGuidanceRefMap = buildGuidanceMap(cls, up.getRoleDescriptor());
			}
			return roleGuidanceRefMap;
		}
		if (up.getWorkProduct().isSuperTypeOf(cls)) {
			if (workproductGuidanceRefMap == null) {
				workproductGuidanceRefMap = buildGuidanceMap(up.getWorkProduct(), up.getWorkProductDescriptor());
			}
			return workproductGuidanceRefMap;
		}
		return null;
	}
	
	private  Map<EReference, EReference> buildGuidanceMap(EClass cls, EClass desCls) {
		Map<EReference, EReference> resultMap = new HashMap<EReference, EReference>();
		
		Map<EClassifier, EReference> map = new HashMap<EClassifier, EReference>();
		for (EReference ref : desCls.getEAllReferences()) {
			if (isGuidanceListReference(ref)) {
				map.put(ref.getEType(), ref);
			}
		}
		
		for (EReference ref : cls.getEAllReferences()) {
			if (isGuidanceListReference(ref)) {
				EReference value = map.get(ref.getEType());
				if (value != null) {
					resultMap.put(ref, value);
				}
			}
		}
		
		resultMap.put(UmaUtil.MethodElement_UdtList, UmaUtil.MethodElement_UdtList);
		
		return resultMap;
	}
	
	public void addOppositeFeature(MethodElement referencing,
			MethodElement referenced, EStructuralFeature feature) {
		if (referencing == null || referenced == null) {
			return;
		}
		ExtendedReference eRef = TypeDefUtil.getInstance().getAssociatedExtendedReference(feature);
		if (eRef != null) {
			PropUtil.getPropUtil().addOpposite(eRef.getGlobalId(), referencing, referenced);
			return;
		}
		
		
		OppositeFeature oppositeFeature = OppositeFeature
				.getOppositeFeature(feature);
		if (oppositeFeature == null) {
			return;
		}		
		MultiResourceEObject mreferenced = (MultiResourceEObject) referenced;
		mreferenced.oppositeAdd(oppositeFeature, referencing);
	}
	
	public void removeOppositeFeature(MethodElement referencing,
			MethodElement referenced, EStructuralFeature feature) {
		if (referencing == null || referenced == null) {
			return;
		}
		ExtendedReference eRef = TypeDefUtil.getInstance().getAssociatedExtendedReference(feature);
		if (eRef != null) {
			PropUtil.getPropUtil().removeOpposite(eRef.getGlobalId(), referencing, referenced);
			return;
		}
		
		OppositeFeature oppositeFeature = OppositeFeature
				.getOppositeFeature(feature);
		if (oppositeFeature == null) {
			return;
		}
		MultiResourceEObject mreferenced = (MultiResourceEObject) referenced;
		mreferenced.oppositeRemove(oppositeFeature, referencing);
	}
	
	public Set<? extends VariabilityElement> collectVariabilityRelatives(VariabilityElement element) {
		Set<VariabilityElement> results = new HashSet<VariabilityElement>();
		collectVariabilityRelatives(element, results);
		return results;
	}
	
	private void collectVariabilityRelatives(VariabilityElement element,
			Set<VariabilityElement> results) {
		if (element == null || results.contains(element)) {
			return;
		}
		results.add(element);
		
		VariabilityElement base = element.getVariabilityBasedOnElement();
		if (base != null) {
			collectVariabilityRelatives(base, results);
		}
		for (VariabilityElement v : (List<VariabilityElement>) AssociationHelper
				.getImmediateVarieties(element)) {
			collectVariabilityRelatives(v, results);
		}
	}
	
	public IRealizationManager getRealizationManager(MethodConfiguration config) {
		ILibraryEditUtilProvider p = getProvider();				
		return p == null ? null : p.getRealizationManager(config);
	}
	
	public void removeAutoSynReferences(Process proc) {
		Set<Descriptor> desSet = LibraryEditUtil.getInstance().collectDescriptors(proc);
		for (Descriptor des : desSet) {
			removeAutoSynReferences(des);
		}
	}
		
	private void removeAutoSynReferences(Descriptor des) {
		if (autoSynReferences == null) {
			autoSynReferences = new HashSet<EReference>();
			autoSynReferences.add(up.getTaskDescriptor_PerformedPrimarilyByExcluded());
			autoSynReferences.add(up.getTaskDescriptor_AdditionallyPerformedByExclude());
			autoSynReferences.add(up.getTaskDescriptor_MandatoryInputExclude());
			autoSynReferences.add(up.getTaskDescriptor_OptionalInputExclude());
			autoSynReferences.add(up.getTaskDescriptor_OutputExclude());
			autoSynReferences.add(up.getRoleDescriptor_ResponsibleForExclude());
			autoSynReferences.add(up.getWorkProductDescriptor_DeliverablePartsExclude());
			autoSynReferences.add(up.getDescriptor_GuidanceExclude());
		}
		
		boolean oldDeliver = des.eDeliver();
		try {
			des.eSetDeliver(false);		
			for (EReference ref : des.eClass().getEAllReferences()) {
				if(autoSynReferences.contains(ref)) {
					List list = (List) des.eGet(ref);
					if (list != null && !list.isEmpty()) {
						list.clear();
					}
				}
			}
			DescriptorPropUtil.getDesciptorPropUtil().clearAllAutoSynProps(des);
		} finally {
			des.eSetDeliver(oldDeliver);
		}
		
		
	}
	
	public MethodElement getCalcualtedElement(MethodElement element, MethodConfiguration config) {
		ILibraryEditUtilProvider p = getProvider();				
		return p == null ? element : p.getCalculatedElement(element, config);
	}
	
	public boolean inConfig(MethodElement element, MethodConfiguration config) {
		ILibraryEditUtilProvider p = getProvider();				
		return p == null ? false : p.inConfig(element, config);
	}
	
	public static class CollectElementFilter {
		public boolean accept(MethodElement element) {
			return true;
		}
		
		public boolean skipChildren(MethodElement element) {
			if (element instanceof MethodLibrary) {
				return false;
			}
			if (element instanceof MethodPlugin) {
				return false;
			}
			if (element instanceof MethodPackage) {
				return false;
			}
			
			return true;
		}
		
	}
	
	public static class CollectElementFilterExtend extends CollectElementFilter {
		
		@Override
		public boolean accept(MethodElement element) {
			if (element instanceof MethodPlugin) {
				return false;
			}
			if (element instanceof MethodPackage) {
				return false;
			}
			if (element instanceof MethodConfiguration) {
				return false;
			}
			if (element instanceof MethodLibrary) {
				return false;
			}
			return true;
		}
		
	}
	
	private void collectElements(MethodElement element,
			CollectElementFilter filter, Set<MethodElement> collected,
			Set<MethodElement> processed) {
		if (processed.contains(element)) {
			return;
		}
		processed.add(element);

		if (filter.accept(element)) {
			collected.add(element);
		}
		if (filter.skipChildren(element)) {
			return;
		}

		EList<EReference> refList = element.eClass().getEAllContainments();
		if (refList == null || refList.isEmpty()) {
			return;
		}
		for (EReference ref : refList) {
			if (ref == UmaPackage.eINSTANCE.getDescribableElement_Presentation()) {
				continue;
			}
			Object obj = element.eGet(ref);
			if (obj instanceof MethodElement) {
				collectElements((MethodElement) obj, filter, collected,
						processed);

			} else if (obj instanceof List) {
				List list = (List) obj;
				for (Object itemObj : list) {
					if (itemObj instanceof MethodElement) {
						collectElements((MethodElement) itemObj, filter,
								collected, processed);
					}
				}
			}
		}
	}
		
	public Set<? extends MethodElement> getFilteredElements(
			MethodConfiguration config, CollectElementFilter filter) {
		Set<MethodElement> set = new HashSet<MethodElement>();
		for (MethodPlugin plugin : config.getMethodPluginSelection()) {
			set.addAll(getElementsUnder(plugin, filter));
		}
		return set;
	}
	
	public Set<? extends MethodElement> getElementsUnder(MethodElement topElement, CollectElementFilter filter) {

		Set<MethodElement> set = new HashSet<MethodElement>();
		collectElements(topElement, filter, set, new HashSet<MethodElement>());
		return set;
	}
	
	public Set<WorkProduct> getAllWorkProducts(MethodElement topElement) {
		CollectElementFilter filter = new CollectElementFilter() {
			public boolean accept(MethodElement element) {
				return element instanceof WorkProduct;
			}
			
			public boolean skipChildren(MethodElement element) {
				if (element instanceof ProcessPackage) {
					return true;
				}
				
				return super.skipChildren(element);
			}
		};
		return (Set<WorkProduct> ) getElementsUnder(topElement, filter);
	}
	
	public Set<Practice> getAllPractices(MethodElement topElement) {
		CollectElementFilter filter = new CollectElementFilter() {
			public boolean accept(MethodElement element) {
				return element instanceof Practice;
			}
			
			public boolean skipChildren(MethodElement element) {
				if (element instanceof ProcessPackage) {
					return true;
				}
				
				return super.skipChildren(element);
			}
		};
		return (Set<Practice> ) getElementsUnder(topElement, filter);
	}
	
	public Set<? extends DescribableElement> getTypedElements(MethodElement topElement, final EClass type) {
		final boolean processElementType = UmaPackage.eINSTANCE.getProcessElement().isSuperTypeOf(type);
		
		CollectElementFilter filter = new CollectElementFilter() {
			public boolean accept(MethodElement element) {
				return type.isSuperTypeOf(element.eClass());
			}
			
			public boolean skipChildren(MethodElement element) {
				if (element instanceof ProcessPackage) {
					return processElementType ? false : true;
					
				} else if (element instanceof ContentPackage) {
					if (processElementType) {
						if (element.getName().equals(ModelStructure.CONTENT_PACKAGE_NAME) ||
							element.eContainer() instanceof MethodPlugin) {
							return false;
						}
						return true;
					}
					return false;
				}
				
				return super.skipChildren(element);
			}
		};
		return (Set<? extends DescribableElement>) getElementsUnder(topElement, filter);
	}
	
	//<key: meta, value: set of udt of same meta for update>
	//Also place those invlid id UDTs under UserDefinedTypeMeta.noneValue entry
	public Map<UserDefinedTypeMeta, Set<Practice>> getUdtInstanceMap(MethodLibrary lib, Collection<UserDefinedTypeMeta> metas) {
		Map<UserDefinedTypeMeta, Set<Practice>> map = new HashMap<UserDefinedTypeMeta, Set<Practice>>();
		if (lib == null || metas == null || metas.isEmpty()) {
			return map;
		}
		Map<String, UserDefinedTypeMeta> idMetaMap = new HashMap<String, UserDefinedTypeMeta>();
		for (UserDefinedTypeMeta meta : metas) {
			idMetaMap.put(meta.getId(), meta);
		}
		
		Set<Practice> practices = new HashSet<Practice>();
		for (MethodPlugin plugin : lib.getMethodPlugins()) {
			practices.addAll(getAllPractices(plugin));
		}
		
		PracticePropUtil propUtil = PracticePropUtil.getPracticePropUtil();
		for (Practice practice : practices) {
			UserDefinedTypeMeta oldMeta = propUtil.getUdtMeta(practice);
			if (oldMeta != null) {
				UserDefinedTypeMeta meta = idMetaMap.get(oldMeta.getId());
				if (meta != null && ! meta.same(oldMeta)) {
					addToMap(map, practice, meta);
					
				} else if (meta == null) {
					meta = UserDefinedTypeMeta.noneValue;
					addToMap(map, practice, meta);
				}
			}
		}
		return map;
	}

	private void addToMap(Map<UserDefinedTypeMeta, Set<Practice>> map,
			Practice practice, UserDefinedTypeMeta meta) {
		Set<Practice> set = map.get(meta);
		if (set == null) {
			set = new HashSet<Practice>();
			map.put(meta, set);
		}
		set.add(practice);
	}
	
	public static boolean save(Collection<Resource> resouresToSave) {
		ILibraryPersister.FailSafeMethodLibraryPersister persister = Services.getDefaultLibraryPersister().getFailSafePersister();
		try {
			HashSet<Resource> seens = new HashSet<Resource>();
			for (Iterator<Resource> it = resouresToSave.iterator(); it.hasNext();) {
				Resource res = it.next();
				if (! seens.contains(res)) {
					persister.save(res);
					seens.add(res);
				}
			}
			persister.commit();
		} catch (Exception e) {
			persister.rollback();
			return false;
		} finally {
		}
		
		return true;
	}
	
	public void fixUpDanglingCustomCategories(MethodLibrary library) {
		if (library == null) {
			return;
		}
		for (MethodPlugin plugin : library.getMethodPlugins()) {
			fixUpDanglingCustomCategories(plugin);
		}

	}

	public void fixUpDanglingCustomCategories(MethodPlugin plugin) {
		ContentPackage customCategoryPkg = UmaUtil.findContentPackage(
				plugin, ModelStructure.DEFAULT.customCategoryPath);
		if (customCategoryPkg == null) {
			return;
		}
		CustomCategory rootCC = TngUtil.getRootCustomCategory(plugin);
		if(rootCC == null) {
			return;
		}

		Set<CustomCategory> ccSet = new HashSet<CustomCategory>();
		addToCCSet(rootCC.getCategorizedElements(), ccSet);

		for (ContentElement element : customCategoryPkg
				.getContentElements()) {
			if (element instanceof CustomCategory) {
				addToCCSet(((CustomCategory) element)
						.getCategorizedElements(), ccSet);
			}
		}

		for (ContentElement element : customCategoryPkg
				.getContentElements()) {
			if (element instanceof CustomCategory
					&& !ccSet.contains(element)) {
				rootCC.getCategorizedElements().add(element);
			}
		}
	}
	
	private void addToCCSet(List<DescribableElement> list,
			Set<CustomCategory> ccSet) {
		for (DescribableElement element : list) {
			if (element instanceof CustomCategory) {
				ccSet.add((CustomCategory) element);
			}
		}
	}
	
	public boolean isContainedBy(MethodElement element,
			MethodElement container, MethodConfiguration config) {
		if (element == null || container == null) {
			return false;
		}
		if (UmaUtil.isContainedBy(element, container)) {
			return true;
		}
		if (config == null) {
			return false;
		}
		
		EObject parent = element.eContainer();
		while (parent instanceof VariabilityElement) {
			MethodElement parentElement = getCalcualtedElement(
					(VariabilityElement) parent, config);
			if (parentElement == container) {
				return true;
			}
			parent = parentElement == null ? null : parentElement.eContainer();
		}

		return parent == container;
	}
	
	public List<MethodElement> calc0nFeatureValue(MethodElement element,
			EStructuralFeature feature, MethodConfiguration config) {
		return getProvider() == null ? null : getProvider().calc0nFeatureValue(element, feature, config);
	}
	
	public List<MethodElement> calc0nFeatureValue(MethodElement element,
			OppositeFeature feature, MethodConfiguration config) {
		return getProvider() == null ? null : getProvider().calc0nFeatureValue(element, feature, config);
	}
	
	public void fixWpStates(Collection<? extends MethodElement> elements, Set<Resource> modifeiedResources) {
		try {
			fixWpStates_(elements, modifeiedResources);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	private void fixWpStates_(Collection<? extends Object> elements, Set<Resource> modifeiedResources) {				
		WorkProductPropUtil wpPropUtil =  WorkProductPropUtil.getWorkProductPropUtil();
		for (Object element : elements) {
			if (! (element instanceof WorkProduct)) {
				continue;
			}			
			WorkProduct wp = (WorkProduct) element;
			wpPropUtil.fixWorkProductStates(wp, modifeiedResources);						
		}
		
		for (Object element : elements) {
			if (! (element instanceof TaskDescriptor || element instanceof Milestone)) {
				continue;
			}
			WorkBreakdownElement wbe = (WorkBreakdownElement) element;
			
//			ConstraintManager.fixWpStates(wbe)
		}			
		
	}
	
	public void fixProcssWpStates(Collection<? extends MethodElement> elements, Set<Resource> modifeiedResources) {
		try {
			fixProcessWpStates_(elements, modifeiedResources);
		} catch (Throwable e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	private void fixProcessWpStates_(Collection<? extends Object> elements, Set<Resource> modifeiedResources) {	
		WorkProductPropUtil wpPropUtil =  WorkProductPropUtil.getWorkProductPropUtil();
		for (Object element : elements) {
			wpPropUtil.fixProcessWpStates(element, modifeiedResources);
		}					
	}
	
	public void createUserDefinedTypeContextMenuOnGuidanceNode(Collection<Object> newChildDescriptors) {
		getProvider().createUserDefinedTypeContextMenuOnGuidanceNode(newChildDescriptors);
	}
	
	public Collection<UserDefinedTypeMeta> getUserDefinedTypes() {
		return getProvider() == null ? null : getProvider().getUserDefinedTypes();
	}
	
	public UserDefinedTypeMeta getUserDefineType(String id) {
		return getProvider() == null ? null : getProvider().getUserDefineType(id);
	}
	
	public ModifiedTypeMeta getModifiedType(String id) {
		return getProvider() == null ? null : getProvider().getModifiedType(id);
	}
	
	public Collection<ModifiedTypeMeta> getModifiedTypes() {
		return getProvider() == null ? null : getProvider().getModifiedTypes();
	}
	
	public MethodPackage getCoreContentPackage(MethodPlugin plugin) {
		String[] path = new String[] { ModelStructure.CONTENT_PACKAGE_NAME,
				ModelStructure.CORE_CONTENT_PACAKGE_NAME };
		MethodPackage pkg = UmaUtil.findMethodPackage(plugin, path);
		return pkg;
	}
	
	public List<CustomCategory> getRootLevelCustomCategories(MethodPlugin plugin) {
		List list = new ArrayList<CustomCategory>();
		CustomCategory topRoot = TngUtil.getRootCustomCategory(plugin);
		if (topRoot != null) {
			for (DescribableElement element : topRoot.getCategorizedElements()) {
				if (element instanceof CustomCategory) {
					list.add((CustomCategory) element); 
				}
			}
		}
		return list;
	}	
	
	public String getXmiString(EObject obj) {
		if (obj == null) {
			return "";			//$NON-NLS-1$
		}
		Map options = new HashMap();
		options.put(XMLResource.OPTION_ENCODING, "ASCII"); //$NON-NLS-1$
		
		StringResource res = new StringResource(null);
		res.getContents().add(EcoreUtil.copy(obj));
		try {
			res.save(options);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		return res.getString();
	}
	
	public EObject loadObject(String xmiString) {
		if (xmiString == null || xmiString.length() == 0) {
			return null;
		}
		Map options = new HashMap();
		options.put(XMLResource.OPTION_ENCODING, "ASCII"); //$NON-NLS-1$
				
		StringResource res = new StringResource(xmiString);
		try {
			res.load(options);
			return res.getContents().get(0);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}
	
}
