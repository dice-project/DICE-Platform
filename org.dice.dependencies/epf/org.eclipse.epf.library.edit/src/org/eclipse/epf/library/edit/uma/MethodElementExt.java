package org.eclipse.epf.library.edit.uma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.IUserDefinedTypeMeta;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject.ExtendObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.UmaUtil;

public class MethodElementExt extends ExtendObject {

	private MethodElement element;
	private boolean transientElement = false;
	private Object cachedObject;
	private ExtendReferenceMap extendReferenceMap;
	private IUserDefinedTypeMeta userDefinedTypeMeta;
		
	private Map<Object, Object> extendedPropertyMap;
	private IUserDefinedTypeMeta modifiedTypeMeta;
	private boolean excludedFromPublish = false;

	public MethodElementExt(MethodElement element) {
		this.element = element;
		excludedFromPublish = excludedFromPublishDefault(element);
	}	
	
	public static boolean excludedFromPublishDefault(MethodElement element) {
		if (element instanceof WorkProductDescriptor) {
			return true;
		}
		return false;
	}
	
	public ExtendReferenceMap getExtendReferenceMap(boolean create) {
		if (create && extendReferenceMap == null) {
			extendReferenceMap = new ExtendReferenceMap(element);
		}
		return extendReferenceMap;
	}
	
	public Object getCachedObject() {
		return cachedObject;
	}

	public void setCachedObject(Object cachedObject) {
		this.cachedObject = cachedObject;
	}
	
	public MethodElement getElement() {
		return element;
	}
	
	public boolean isTransientElement() {
		return transientElement;
	}

	public void setTransientElement(boolean transientElement) {
		this.transientElement = transientElement;
	}
	
	public static class WorkProductStateExt extends MethodElementExt {
		private Set<WorkProduct> assignedToWps;
		
		public WorkProductStateExt(Constraint element) {
			super(element);
		}
		
		public void addToAssignedToWps(WorkProduct wp) {
			if (assignedToWps == null) {
				assignedToWps = new HashSet<WorkProduct>();
			}
			assignedToWps.add(wp);
		}
		
		public void removeFromAssignedToWps(WorkProduct wp) {
			if (assignedToWps == null) {
				return;
			}
			assignedToWps.remove(wp);
		}
		
		public List<WorkProduct> getAssignedToWorkProducts() {
			List<WorkProduct> list = new ArrayList<WorkProduct>();
			List<WorkProduct> list1 = new ArrayList<WorkProduct>();
			if (assignedToWps == null) {
				return list;
			}
			for (WorkProduct wp : assignedToWps) {
				if (UmaUtil.isInLibrary(wp)) {
					list.add(wp);
				} else {
					list1.add(wp);
				}
			}
			if (! list1.isEmpty()) {
				assignedToWps.removeAll(list1);
			}
			if (list.size() > 1) {
				Collections.sort(list, Comparators.PRESENTATION_NAME_COMPARATOR);
			}
			return list;
		}
		
	}
	
	public static class MethodConfigurationExt extends MethodElementExt {
		boolean loadCheckPackagesCalled = false;

		public MethodConfigurationExt(MethodConfiguration element) {
			super(element);
		}

		public boolean isLoadCheckPackagesCalled() {
			return loadCheckPackagesCalled;
		}

		public void setLoadCheckPackagesCalled(boolean loadCheckPackagesCalled) {
			this.loadCheckPackagesCalled = loadCheckPackagesCalled;
		}
		
	}
	
	
	public IUserDefinedTypeMeta getUserDefinedTypeMeta() {
		return userDefinedTypeMeta;
	}

	public void setUserDefinedTypeMeta(IUserDefinedTypeMeta userDefinedTypeMeta) {
		this.userDefinedTypeMeta = userDefinedTypeMeta;
	}
	
	public IUserDefinedTypeMeta getModifiedTypeMeta() {
		return modifiedTypeMeta;
	}

	public void setModifiedTypeMeta(IUserDefinedTypeMeta modifiedTypeMeta) {
		this.modifiedTypeMeta = modifiedTypeMeta;
	}
	
	public Map<Object, Object> getExtendedPropertyMap(boolean create) {
		if (extendedPropertyMap == null && create) {
			extendedPropertyMap = new HashMap<Object, Object>();
		}
		return extendedPropertyMap;
	}
	
	@Override
	public boolean handleOppostie(OppositeFeature oFeature) {
		return oFeature == UmaUtil.UdtListOpposite || TypeDefUtil.getInstance().getAssociatedExtendedOpposite(oFeature) != null;
	}
	
	@Override
	public Object getOppositeFeatureValue(OppositeFeature feature) {
		if (feature == UmaUtil.UdtListOpposite) {
			return PropUtil.getPropUtil().getReferencingList(
					element,
					TypeDefUtil.getInstance().getAssociatedExtendedReference(
							UmaUtil.MethodElement_UdtList));
		}
		ExtendedOpposite extendedOpposite = TypeDefUtil.getInstance().getAssociatedExtendedOpposite(feature);
		if (extendedOpposite != null) {
			return PropUtil.getPropUtil().getReferencingList(element, extendedOpposite.getTargetReference());
		}
		return super.getOppositeFeatureValue(feature);
	}
	
	public boolean isExcludedFromPublish() {
		return excludedFromPublish;
	}

	public void setExcludedFromPublish(boolean excludedFromPublish) {
		this.excludedFromPublish = excludedFromPublish;
	}
	
}
