package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MethodElementSetPropertyCommand;
import org.eclipse.epf.library.edit.meta.ReferenceTable;
import org.eclipse.epf.library.edit.uma.ExtendReferenceMap;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ExtendedTable;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

public class PropUtil extends MethodElementPropUtil {

	public static final String Me_mdtData = "me_mdtData";				//$NON-NLS-1$
	public static final String Me_customize = "me_customize";			//$NON-NLS-1$
	public static final String Me_edited = "me_edited";					//$NON-NLS-1$
	public static final String Me_attribute_ = "me_attribute_";			//$NON-NLS-1$
	public static final String Me_table_ = "me_table_";					//$NON-NLS-1$
	public static final String Me_linkedElement = "me_linkedElement";	//$NON-NLS-1$
	public static final String me_ContibutionOrder = "me_ContributionOrder";			//$NON-NLS-1$
	
	public static final String Pkg_loadCheck = "pkg_loadCheck";	//$NON-NLS-1$
	
	private static PropUtil propUtil = new PropUtil();
	public static PropUtil getPropUtil(IActionManager actionManager) {
		return new PropUtil(actionManager);
	}
	
	public static PropUtil getPropUtil() {
		return propUtil;
	}
		
	protected PropUtil() {		
	}
	
	protected PropUtil(IActionManager actionManager) {
		super(actionManager);
	}
	
	public String getContributionOrder(MethodElement element) {
		String value = getStringValue(element, me_ContibutionOrder);
		return value == null ? null : value.trim();
	}
	
	public void setContributionOrder(MethodElement element, String value) {
		setStringValue(element, me_ContibutionOrder, value);
	}
	
	public MethodElement getLinkedElement(MethodElement element) {
		return getElement(element, Me_linkedElement);
	}
		
	public void setLinkedElement(MethodElement element, MethodElement value) {
		setElement(element, Me_linkedElement, value);
	}
	
	public ReferenceTable retrieveExtendedTable(MethodElement element, ExtendedTable tableMeta) {
		if (tableMeta == null) {
			return null;
		}
		String value = getStringValue(element, Me_table_ + tableMeta.getGlobalId());
		ReferenceTable table = new ReferenceTable(element, tableMeta, value);
		return table;
	}
	
	public ReferenceTable getReferenceTable(MethodElement element, ExtendedTable tableMeta, boolean modified) {
		ExtendReferenceMap map = getExtendReferenceMap(element, modified);
		return map == null ? null : map.getReferenceTable(tableMeta);
	}
	
	public void setReferenceTableProp(ReferenceTable table) {
		setStringValue(table.getElement(), Me_table_ + table.getMeta().getGlobalId(), table.getGuidListString());
	}
	
	public String getExtendedAttribute(ContentDescription content, ExtendedAttribute att) {
		String value = getStringValue(content, Me_attribute_ + att.getGlobalId());
		return value;
	}
	
	public void setExtendedAttribute(ContentDescription content, ExtendedAttribute att, String value) {
		setStringValue(content, Me_attribute_ + att.getGlobalId(), value);
	}
	
	public static Command getSetExtendedAttributeCommand(ContentDescription content, ExtendedAttribute att, String value) {
		return new MethodElementSetPropertyCommand(content,  Me_attribute_ + att.getGlobalId(), value);
	}
		
	public boolean isCustomize(MethodElement element) {
		Boolean value = getBooleanValue(element, Me_customize);
		return value == null ? false : value.booleanValue();
	}
	
	public void setCustomize(MethodElement element, boolean b) {
		setBooleanValue(element, Me_customize, b);
	}
	
	public MethodElement getCustomizeParent(MethodElement element) {
		if (! (element instanceof VariabilityElement)) {
			return null;
		}
		VariabilityElement v = (VariabilityElement) element;
		if (v.getVariabilityType() != VariabilityType.EXTENDS_REPLACES) {
			return null;
		}
		if (! isCustomize(element)) {
			return null;
		}
		return v.getVariabilityBasedOnElement();
	}
	
	public boolean isEdited(MethodElement element) {
		Boolean value = getBooleanValue(element, Me_edited);
		return value == null ? false : value.booleanValue();
	}
	
	public void setEdited(MethodElement element, boolean b) {
		setBooleanValue(element, Me_edited, b);
	}
	
	public List<MethodElement> getExtendedReferenceList(MethodElement element, 	ExtendedReference meta, boolean toModify) {
		List<MethodElement> value = (List<MethodElement>) getReferenceValue(meta.getGlobalId(), element, toModify);
		if (value == null) {
			return new ArrayList<MethodElement>();
		}
		if (! toModify) {
			return UmaUtil.filterOutUnresolved(value);
		}
		return value;
	}
	
	private static String umaTypeScope = "org.eclipse.epf.uma.";		//$NON-NLS-1$	
	public ModifiedTypeMeta getGlobalMdtMeta(MethodElement element) {
		if (element == null) {
			return null;
		}
		String id = umaTypeScope + element.eClass().getName();
		if (element instanceof Practice) {
			PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
			Practice practice = (Practice) element;
			UserDefinedTypeMeta udtMeta = practicePropUtil.getUdtMeta(practice);
			if (udtMeta != null) {
				id = udtMeta.getTypeId();		
			}
		}	
		ModifiedTypeMeta meta = LibraryEditUtil.getInstance().getModifiedType(id);
		return meta;
	}
	
	private static class PropXmlEditUtil extends XmlEditUtil {
		private MethodElement element;
		public static final String _id = "id"; 					//$NON-NLS-1$	
		
		public PropXmlEditUtil(MethodElement element, MethodElementPropUtil propUtil) {
			super(propUtil);
			this.element = element;
		}	
		
//		public void retrieveMdtData(ModifiedTypeMeta meta) throws Exception {
//			if (meta instanceof ModifiedTypeMetaImpl) {
//				return;
//			}
//			Map<String, String> map = null;
//			
//			String xmlString = getPropUtil().getStringValue(element, Me_mdtData);
//			if (xmlString == null || xmlString.trim().length() == 0) {
//				return;
//			}
//			Element firstElement = loadDocumentAndGetFirstElement(xmlString);
//			if (firstElement == null) {
//				return;
//			}				
//			((ModifiedTypeMetaImpl) meta).parseElement(firstElement);
//		}
	}
	
	public void addOpposite(ExtendedReference reference, MethodElement thisElement, MethodElement otherElement) {
		ExtendReferenceMap map = getCachedExtendReferenceMap(thisElement, false);
		if (map == null) {
			return;
		}
		map.addOpposite(reference, otherElement);
	}
	
	public void removeOpposite(ExtendedReference reference, MethodElement thisElement, MethodElement otherElement) {
		ExtendReferenceMap map = getCachedExtendReferenceMap(thisElement, false);
		if (map == null) {
			return;
		}
		map.removeOpposite(reference, otherElement);
	}
	
	public MethodElement getElement(MethodElement ownerElement, String propName) {
		if (ownerElement == null) {
			return null;
		}
		String guid = this.getStringValue(ownerElement, propName);
		MethodElement element = LibraryEditUtil.getInstance().getMethodElement(guid);
		if (! UmaUtil.isInLibrary(element)) {
			return null;
		}
		return element;
	}
	
	public void setElement(MethodElement ownerElement, String propName, MethodElement element) {
		String guid = element == null ? "" : element.getGuid();
		setStringValue(ownerElement, propName, guid);
	}
	
	public List<MethodElement> getReferencingList(MethodElement element, ExtendedReference ref) {
		return getReferencingList(element, ref, false);
	}
	
	public List<MethodElement> getReferencingList(MethodElement element, ExtendedReference ref, boolean modified) {
		String ofeature = ExtendReferenceMap.getOppositeName(ref.getGlobalId());
		List<MethodElement> list = (List<MethodElement>) getReferenceValue(ofeature, element, modified);
		return list == null ? new ArrayList<MethodElement>() : list;
	}

	public Set<MethodElement> getExtendedReferencingSet(MethodElement element) {
		ExtendReferenceMap map = getCachedExtendReferenceMap(element, false);
		return map == null ? Collections.EMPTY_SET : map.getExtendedReferencingSet(element);
	}
	
	public boolean isExcludedFromPublish(MethodElement element) {
		MethodElementExt extObj = getExtendObject(element, false);
		return extObj == null ? MethodElementExt.excludedFromPublishDefault(element) : extObj.isExcludedFromPublish();
	}

	public void setExcludedFromPublish(MethodElement element, boolean excludedFromPublish) {
		if (excludedFromPublish == MethodElementExt.excludedFromPublishDefault(element) 
				&& getExtendObject(element, false) == null) {
			return;
		}
		MethodElementExt extObj = getExtendObject(element, true);
		extObj.setExcludedFromPublish(excludedFromPublish);
	}
	
}
