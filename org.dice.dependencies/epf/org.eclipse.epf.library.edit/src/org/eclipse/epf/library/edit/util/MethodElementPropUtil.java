package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MethodElementSetPropertyCommand;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.uma.ExtendReferenceMap;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.library.edit.uma.MethodElementExt.WorkProductStateExt;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject.ExtendObject;
import org.eclipse.epf.uma.util.UmaUtil;
import org.w3c.dom.Element;

public class MethodElementPropUtil {
	
	public static final String infoSeperator = "/"; 							//$NON-NLS-1$
	
	public static final String CONSTRAINT_WPStates = "constraint_wpStates";		//$NON-NLS-1$
	public static final String Package_supporting = "pakage_supporting";		//$NON-NLS-1$
	public static final String Me_references = "me_references";					//$NON-NLS-1$

	private static MethodElementPropUtil methodElementPropUtil = new MethodElementPropUtil();
	public static MethodElementPropUtil getMethodElementPropUtil(IActionManager actionManager) {
		return new MethodElementPropUtil(actionManager);
	}
	
	public static MethodElementPropUtil getMethodElementPropUtil() {
		return methodElementPropUtil;
	}
	
	private IActionManager actionManager;
	
	protected MethodElementPropUtil() {		
	}
	
	protected MethodElementPropUtil(IActionManager actionManager) {
		this.actionManager = actionManager;
	}	
	
	public IActionManager getActionManager() {
		return actionManager;
	}
	
	public String getStringValue(MethodElement element, String propName) {		
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(element, propName);
		if (prop == null) {
			return null;
		}
		return prop.getValue();
	}
	
	public void setStringValue(MethodElement element, String propName, String value) {	
		setProperty(element, propName, value);
	}
	
	public boolean isSupporting(ContentPackage pkg) {
		 Boolean value = getBooleanValue(pkg, Package_supporting);
		 return value == null ? false : value.booleanValue();
	}
	
	public boolean ancestorIsSupporting(ContentPackage pkg) {
		EObject parent = pkg.eContainer();
		while(parent != null) {
			if (parent instanceof MethodPlugin) { 
				return ((MethodPlugin) parent).isSupporting();
			}
			if (parent instanceof ContentPackage && isSupporting((ContentPackage) parent)) {
				return true;
			}
			parent = parent.eContainer();
		}
		return false;
	}
		
	public void updatePackageSupportingBits(Collection<? extends MethodPackage> pkgs, boolean supporting) {
		for (MethodPackage mpkg : pkgs) {
			if (! (mpkg instanceof ContentPackage)) {
				continue;
			}
			ContentPackage pkg = (ContentPackage) mpkg;
			boolean pkgIsSupporting = isSupporting(pkg);
			if (pkgIsSupporting && supporting) {
				continue;	
			}
			if (pkgIsSupporting != supporting) {
				setBooleanValue(pkg, Package_supporting, supporting);
			}
			updatePackageSupportingBits(pkg.getChildPackages(), supporting);
		}
	}
	
	public void setSupporting(ContentPackage pkg, boolean value) {
		 setBooleanValue(pkg, Package_supporting, value);
	}
	
	public Boolean getBooleanValue(MethodElement element, String propName) {		
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(element, propName);
		if (prop == null) {
			return null;
		}
		String value = prop.getValue();
		return Boolean.parseBoolean(value);
	}
	
	public void setBooleanValue(MethodElement element, String propName, boolean value) {	
		String strValue = value ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
		setProperty(element, propName, strValue);
	}
	
	protected Integer getIntValue(MethodElement element, String propName) {		
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(element, propName);
		if (prop == null) {
			return null;			
		}
		String value = prop.getValue();
		return Integer.parseInt(value);
	}
	
	protected void setIntValue(MethodElement element, String propName, int value) {	
		String strValue = Integer.toString(value);
		setProperty(element, propName, strValue);
	}
	
	private void setProperty(MethodElement e, String propName, String propValue) {
		if (getActionManager() == null) {
			MethodElementPropertyHelper.setProperty(e, propName, propValue);
		} else {
			MethodElementSetPropertyCommand cmd = new MethodElementSetPropertyCommand(
					e, propName, propValue);
			getActionManager().execute(cmd);
		}
	}
	
	protected void removeProperty(MethodElement e, String propName) {
		MethodElementPropertyHelper.removeProperty(e, propName);
	}
	
	public MethodElementExt getExtendObject(MethodElement element, boolean create) {
		if (element == null) {
			return null;
		}
		MultiResourceEObject mobj = (MultiResourceEObject) element;		
		ExtendObject obj = mobj.getExtendObject();
		if (create && !(obj instanceof MethodElementExt)) {
			obj = TypeDefUtil.getInstance().createExtendObject(element);
			mobj.setExtendObject(obj);
		}
		return (MethodElementExt) obj;
	}
	
	public Map<Object, Object> getExtendedPropertyMap(MethodElement element, boolean create) {
		MethodElementExt ext = getExtendObject(element, create);
		return ext == null ? null : ext.getExtendedPropertyMap(create);
	}
	
	public boolean isWorkProductState(MethodElement element) {
		if (!(element instanceof Constraint)) {
			return false;
		}
		Constraint c = (Constraint) element;
		return ConstraintManager.Plugin_wpState.equals(c.getName());
	}
	
	public void addReferenceInfo(MethodElement owner, MethodElement reference, String propName, String refName) {
		String oldValue = getStringValue(owner, propName);
		String newValue = reference.getGuid().concat(infoSeperator).concat(refName);

		if (oldValue != null && oldValue.length() > 0) {			
			String[] infos = oldValue.split(infoSeperator); 
			
			int sz = infos.length / 2; 		
			for (int i = 0; i < sz; i++) {
				int i1 = i*2;
				int i2 = i1 + 1;
				String iGuid = infos[i1];
				String iFeature = infos[i2];
				if (iGuid.equals(reference.getGuid()) && iFeature.equals(refName)) {
					return;
				} 
			}
			
			newValue = oldValue.concat(infoSeperator).concat(newValue);
		}				
		setStringValue(owner, propName, newValue);
	}
	
	public void removeReferenceInfo(MethodElement owner, MethodElement reference, String propName, String refName) {
		String oldValue = getStringValue(owner, propName);
		if (oldValue == null || oldValue.length() == 0) {
			return;
		}
		boolean removed = false;
		String newValue = ""; //$NON-NLS-1$

		if (oldValue != null && oldValue.length() > 0) {			
			String[] infos = oldValue.split(infoSeperator); 
			
			int sz = infos.length / 2; 		
			for (int i = 0; i < sz; i++) {
				int i1 = i*2;
				int i2 = i1 + 1;
				String iGuid = infos[i1];
				String iFeature = infos[i2];
				if (iGuid.equals(reference.getGuid()) && iFeature.equals(refName)) {
					removed = true;		
				} else {
					if (newValue.length() > 0) {
						newValue = newValue.concat(infoSeperator);
					}
					newValue = newValue.concat(iGuid.concat(infoSeperator).concat(iFeature));
				}
			}

		}
		
		if (removed) {
			setStringValue(owner, propName, newValue);
		}

	}
	
	public List<? extends MethodElement> extractElements(MethodElement propertyOwner, String propName, String refName,
			Set<? extends MethodElement> validSet) {
		List<MethodElement> elements = new ArrayList<MethodElement>();
		
		String value = getStringValue(propertyOwner, propName);
		if (value == null || value.length() == 0) {
			return elements;
		}
		
		String[] infos = value.split(infoSeperator);
		if (infos == null || infos.length == 0) {
			return elements;
		}
		
		boolean modified = false;
		String newValue = ""; //$NON-NLS-1$
		int sz = infos.length / 2; 		
		for (int i = 0; i < sz; i++) {
			int i1 = i*2;
			int i2 = i1 + 1;
			String iGuid = infos[i1];
			String iRefName = infos[i2];			
			if (refName.equals(iRefName)) {
				MethodElement element = LibraryEditUtil.getInstance().getMethodElement(iGuid);
				if (element != null && validSet.contains(element)) {
					elements.add(element);
					if (newValue.length() > 0) {
						newValue = newValue.concat(infoSeperator);
					}
					newValue = newValue.concat(iGuid.concat(infoSeperator).concat(iRefName));
				} else {
					modified = true;
				}
			} else {
				if (newValue.length() > 0) {
					newValue = newValue.concat(infoSeperator);
				}
				newValue = newValue.concat(iGuid.concat(infoSeperator).concat(iRefName));
			}
		}		
		if (modified) {
			setStringValue(propertyOwner, propName, newValue);
		}
		
		return elements;
	}

	public boolean isTransientElement(MethodElement element) {
		MethodElementExt extObj = getExtendObject(element, false);
		return extObj == null ? false : extObj.isTransientElement();
	}

	public void setTransientElement(MethodElement element,
			boolean transientElement) {
		if (!transientElement && getExtendObject(element, false) == null) {
			return;
		}
		MethodElementExt extObj = getExtendObject(element, true);
		extObj.setTransientElement(transientElement);

	}
	
	//-> For work product state objects
	public void addToAssignedToWps(WorkProduct wp, Constraint state) {
		if (! isWorkProductState(state)) {
			return;
		}
		MethodElementExt extObj = getExtendObject(state, true);
		if (! (extObj instanceof WorkProductStateExt)) {
			return;
		}
		((WorkProductStateExt) extObj).addToAssignedToWps(wp);
	}
	
	public void removeFromAssignedToWps(WorkProduct wp, Constraint state) {
		MethodElementExt extObj = getExtendObject(state, false);
		if (! (extObj instanceof WorkProductStateExt)) {
			return;
		}
		((WorkProductStateExt) extObj).removeFromAssignedToWps(wp);
	}
	
	public List<WorkProduct> getAssignedToWorkProducts(Constraint state) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(state);
		if (plugin != null) {
			MethodPluginPropUtil.getMethodPluginPropUtil().loadWpStates(plugin);
		}	
		
		MethodElementExt extObj = getExtendObject(state, false);
		if (! (extObj instanceof WorkProductStateExt)) {
			return Collections.EMPTY_LIST;
		}
		return ((WorkProductStateExt) extObj).getAssignedToWorkProducts();
	}
	//<- For work product state objects
	
	//Get elements stored as guids in the property specified by propName
	protected Set<? extends MethodElement> getElements(MethodElement owner, String propName, EClass type) {
		Set<MethodElement> elements = new HashSet<MethodElement>();
		if (owner == null) {
			return elements;
		}
		
		String value = getStringValue(owner, propName);
		String[] guids = value == null || value.trim().length() == 0 ? null : value.split(infoSeperator);

		if (guids == null || guids.length == 0) {
			return elements;
		}

		for (int i = 0; i < guids.length; i++) {
			MethodElement element = LibraryEditUtil.getInstance()
					.getMethodElement(guids[i]);
			if (element != null && (type == null || type.isSuperTypeOf(element.eClass()))) {
				elements.add(element);
			}
		}

		return elements;

	}
	
	//Store elements as guids in the property specified by propName
	protected void setElements(MethodElement owner, String propName, Set<? extends MethodElement> elements, EClass type) {
		if (owner == null) {
			return;
		}
		
		String value = "";	//$NON-NLS-1$
		List<String> guidList = new ArrayList<String>();
		if (elements != null) {
			for (MethodElement element : elements) {
				if (element != null && (type == null || type.isSuperTypeOf(element.eClass()))) {
					guidList.add(element.getGuid());
				}
			}
			Collections.sort(guidList);
			for (String guid : guidList) {
				if (value.length() != 0) {
					value += infoSeperator;
				}
				value += guid;
			}
		}
		setStringValue(owner, propName, value);
	}
	
	public XmlEditUtil newXmlEditUtil() {
		return new XmlEditUtil(this);
	}
	
	public void storeReferences(MethodElement element, boolean rollback)  throws Exception  {
		MeXmlEditUtil meXmlEditUtil = new MeXmlEditUtil(element, this);
		meXmlEditUtil.storeReferences(rollback);
	}
	
	public String getReferencesXml(MethodElement element, boolean rollback) throws Exception  {
		MeXmlEditUtil meXmlEditUtil = new MeXmlEditUtil(element, this);
		return meXmlEditUtil.getReferencesXml(rollback);
	}
	
	public boolean hasUdtList(MethodElement element) {
		return hasReferences(element);
	}
	
	public boolean hasReferences(MethodElement element) {
		String value = getStringValue(element, Me_references);
		return value != null && value.length() > 0;
	}
	
	public List<Practice> getUdtList(MethodElement element, boolean toModify) {
		List<Practice> value = (List<Practice>) getReferenceValue(UmaUtil.MethodElement_UdtList.getName(), element, toModify);
		if (value == null) {
			return new ArrayList<Practice>();
		}
		return value;
	}
	
	public List<MethodElement> getQReferenceListById(MethodElement element, String qualifierId, boolean toModify) {
		String referenceName = qualifierId;
		List<MethodElement> value = (List<MethodElement>) getReferenceValue(
				referenceName, element, toModify);
		if (value == null) {
			return new ArrayList<MethodElement>();
		}
		return value;
	}	
	
	public void notifyElemetSaved(MethodElement element) {
		ExtendReferenceMap map = getCachedExtendReferenceMap(element, false);
		if (map == null) {
			return;
		}
		map.notifyOwnerElementSaved();
	}
	
	public void addOpposite(String referenceName, MethodElement thisElement, MethodElement otherElement) {
		ExtendReferenceMap map = getCachedExtendReferenceMap(thisElement, false);
		if (map == null) {
			return;
		}
		map.addOpposite(referenceName, otherElement);
	}
	
	public void removeOpposite(String referenceName, MethodElement thisElement, MethodElement otherElement) {
		ExtendReferenceMap map = getCachedExtendReferenceMap(thisElement, false);
		if (map == null) {
			return;
		}
		map.removeOpposite(referenceName, otherElement);
	}
	
	protected Object getReferenceValue(String referenceName, MethodElement element, boolean toModify) {
		MeXmlEditUtil meXmlEditUtil = new MeXmlEditUtil(element, this);
		return meXmlEditUtil.getReferenceValue(referenceName, element, toModify);
	}
	
	protected ExtendReferenceMap getCachedExtendReferenceMap(MethodElement element, boolean toModify) {
		MethodElementExt extendObject = getExtendObject(element, toModify);
		if (extendObject == null) {
			return null;
		}
		return extendObject.getExtendReferenceMap(toModify);
	}
	
	public ExtendReferenceMap getExtendReferenceMap(MethodElement element, boolean toModify) {
		MeXmlEditUtil meXmlEditUtil = new MeXmlEditUtil(element, this);
		return meXmlEditUtil.getExtendReferenceMap(element, toModify);
	}
	
	private static class MeXmlEditUtil extends XmlEditUtil {
		
		private MethodElement element;
		
		public MeXmlEditUtil(MethodElement element, MethodElementPropUtil propUtil) {
			super(propUtil);
			this.element = element;
		}
		
		public void storeReferences(boolean rollback) throws Exception  {
			if (! buildReferencesElement(rollback)) {
				return;
			}			
			storeToOwner(element, Me_references);
		}
		
		public Object getReferenceValue(String referenceName, MethodElement element, boolean toModify) {
			ExtendReferenceMap map = getExtendReferenceMap(element, toModify);
			return map == null ? null : map.get(referenceName, toModify);
		}
		
		public ExtendReferenceMap getExtendReferenceMap(MethodElement element, boolean toModify) {
			ExtendReferenceMap map = getPropUtil().getCachedExtendReferenceMap(element, toModify);
			if (map != null && map.isRetrieved() || ! getPropUtil().hasUdtList(element)) {
				return map;
			}

			String xmlString = getPropUtil().getStringValue(element, Me_references);
			Element firstElement = null;
			try {
				firstElement = loadDocumentAndGetFirstElement(xmlString);
			} catch (Exception e) {
				LibraryEditPlugin.getDefault().getLogger().logError(e);
				firstElement = null;
			}
			if (firstElement != null) {
//				map = getExtendReferenceMap(element, true);	
				map = getPropUtil().getCachedExtendReferenceMap(element, true);
				map.retrieveReferencesFromElement(firstElement);
			}

			return map;
		}
		
		private boolean buildReferencesElement(boolean rollback) throws Exception {
			ExtendReferenceMap map = getExtendReferenceMap(element, false);
			if (map == null) {
				return false;
			}
			Element firstElement = createFirstElement(Me_references);
			map.storeReferencesToElement(firstElement, rollback);
			return true;
		}		
		
		public String getReferencesXml(boolean rollback) throws Exception  {
			if (! buildReferencesElement(rollback)) {
				return null;
			}			
			return XMLUtil.toXmlString(getDocument());
		}
		
	}
	
	public Object eGet(EObject eobj, EStructuralFeature feature, boolean toModify) {
		return TypeDefUtil.getInstance().eGet(eobj, feature, toModify);
	}
	
}
