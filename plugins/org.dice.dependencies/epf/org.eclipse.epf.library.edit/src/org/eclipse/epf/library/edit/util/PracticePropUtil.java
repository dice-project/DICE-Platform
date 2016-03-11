package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.uma.ExtendReferenceMap;
import org.eclipse.epf.library.edit.uma.MethodElementExt;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.w3c.dom.Element;

public class PracticePropUtil extends PropUtil {
	
	public static final String Practice_UtdData = "Practice_utdData";		//$NON-NLS-1$		
	
	private static PracticePropUtil practicePropUtil = new PracticePropUtil();
	public static PracticePropUtil getPracticePropUtil() {
		return practicePropUtil;
	}
	
	public static PracticePropUtil getPracticePropUtil(IActionManager actionManager) {
		return new PracticePropUtil(actionManager);
	}
	
	protected PracticePropUtil() {		
	}
	
	protected PracticePropUtil(IActionManager actionManager) {
		super(actionManager);
	}
	
	public void storeUtdData(Practice practice, UserDefinedTypeMeta meta)  throws Exception {
		PracticeXmlEditUtil xmlEditUtil = new PracticeXmlEditUtil(practice, this);
		xmlEditUtil.storeUtdData(meta);
		MethodElementExt extendObject = getExtendObject(practice, true);
		if (extendObject != null) {
			extendObject.setUserDefinedTypeMeta(meta);
		}
	}
	
	public  UserDefinedTypeMeta getUdtMeta(Practice practice) {
		try {
			return getUtdData(practice);			
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}
	
	public  UserDefinedTypeMeta getUtdData(Practice practice)  throws Exception {
		MethodElementExt extendObject = getExtendObject(practice, true);
		if (extendObject != null && extendObject.getUserDefinedTypeMeta() instanceof UserDefinedTypeMeta) {
			UserDefinedTypeMeta meta = (UserDefinedTypeMeta) extendObject.getUserDefinedTypeMeta();
			return meta == UserDefinedTypeMeta.noneValue ? null : meta;
		}
		UserDefinedTypeMeta meta = new UserDefinedTypeMeta();
		PracticeXmlEditUtil xmlEditUtil = new PracticeXmlEditUtil(practice, this);
		xmlEditUtil.retrieveUtdData(meta);
		if (extendObject == null) {
			return meta.getId() == null ? null : meta;
		}
		if (meta.getId() == null) {
			meta = UserDefinedTypeMeta.noneValue;
		} else {
			UserDefinedTypeMeta globalMeta = LibraryEditUtil.getInstance().getUserDefineType(meta.getId());
			if (meta.same(globalMeta)) {
				meta = globalMeta;
			}
		}
		extendObject.setUserDefinedTypeMeta(meta);
		return meta == UserDefinedTypeMeta.noneValue ? null : meta;
	}
	
	public  boolean isUdtType(MethodElement element) {
		if (element instanceof Practice) {
			return getUdtMeta((Practice) element) != null;			
		}
		return false;
	}
	
	public boolean isUdtType(MethodElement element, String typeId) {
		if (typeId != null && element instanceof Practice) {
			UserDefinedTypeMeta meta = getUdtMeta((Practice) element);
			if (meta != null) {
				return typeId.equals(meta.getRteNameMap().get(
						UserDefinedTypeMeta._typeId));
			}
		}
		return false;
	}
	
	public List<MethodElement> getUdtReferencingList(Practice practice) {
		String ofeature = ExtendReferenceMap.getOppositeName(ExtendReferenceMap.UdtList);
		List<MethodElement> list = (List<MethodElement>) getReferenceValue(ofeature, practice, false);
		return list == null ? new ArrayList<MethodElement>() : list;
	}
	
	private static class PracticeXmlEditUtil extends XmlEditUtil {
		
		private Practice practice;
		
		public static final String _id = "id"; 					//$NON-NLS-1$		
		
		public PracticeXmlEditUtil(Practice practice, MethodElementPropUtil propUtil) {
			super(propUtil);
			this.practice = practice;
		}		
		
		public void storeUtdData(UserDefinedTypeMeta meta) throws Exception {
			if (practice == null) {
				return;
			}
			Map<String, String> map = meta.getRteNameMap();
			Element firstElement = createFirstElement(Practice_UtdData);
			firstElement.setAttribute(_id, meta.getId());
			for (String name : UserDefinedTypeMeta.rteNames) {
				String value = map.get(name);
				if (value != null && value.trim().length() > 0) {
					firstElement.setAttribute(name, value.trim());
				}
			}			
			storeToOwner(practice, Practice_UtdData);	
		}
		
		public void retrieveUtdData(UserDefinedTypeMeta meta) throws Exception {
			Map<String, String> map = meta.getRteNameMap();
			
			String xmlString = getPropUtil().getStringValue(practice, Practice_UtdData);
			if (xmlString == null || xmlString.trim().length() == 0) {
				return;
			}
			Element firstElement = loadDocumentAndGetFirstElement(xmlString);
			if (firstElement == null) {
				return;
			}				
			String value = firstElement.getAttribute(_id);
			if (value != null && value.length() > 0) {
				meta.setId(value);
			}
			for (String name : UserDefinedTypeMeta.rteNames) {
				if (name.equals(UserDefinedTypeMeta._icon) || name.equals(UserDefinedTypeMeta._shapeIcon)) {
					UserDefinedTypeMeta globalMeta = LibraryEditUtil.getInstance().getUserDefineType(meta.getId());
					if (globalMeta != null) {
						value = globalMeta.getRteNameMap().get(name);
					}
				} else {				
					value = firstElement.getAttribute(name);
				}
				if (value != null && value.length() > 0) {
					map.put(name, value);
				}
			}
		}
		
	}

	
	
}
