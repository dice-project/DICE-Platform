package org.eclipse.epf.library.edit.meta.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.meta.TypeDefParser;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TypeDefParserImpl implements TypeDefParser {

	public TypeDefParserImpl() {		
	}
	
	public List<IMetaDef> parse(Document doc) throws TypeDefException {
		List metaList = new ArrayList<IMetaDef>();		
		NodeList list = doc.getElementsByTagName(IMetaDef.MODIFIED_TYPE);
		int size = list.getLength();

		Map<String, ModifiedTypeMetaImpl> map = new HashMap<String, ModifiedTypeMetaImpl>();
		for (int i = 0; i < size; i++) {
			Element element = (Element) list.item(i);
			ModifiedTypeMetaImpl meta = (ModifiedTypeMetaImpl) TypeDefUtil.getInstance().newModifiedTypeMeta();
			meta.parseElement(element);
			metaList.add(meta);			
			if (map.put(meta.getId(), meta) != null) {
				throw new TypeDefException("Duplicate modified type id values are not allowed: " + meta.getId()); //$NON-NLS-1$			
			}
		}
		
		Map<String, ModifiedTypeMetaImpl> linkTypeMap = new HashMap<String, ModifiedTypeMetaImpl>();
		for (ModifiedTypeMetaImpl meta : (List<ModifiedTypeMetaImpl>) metaList) {
			for (String linkType : meta.getLinkTypes()) {
				if (linkTypeMap.put(linkType, meta) != null) {
					//log validation error: linkedType cannot be associated with two modified types
				}
			}			
			try {
				Class cls = Class.forName(meta.getId());
				if (cls == null) {
					continue;
				}
				cls = TypeDefUtil.getSuperClass(cls);
				while (cls != null) {
					ModifiedTypeMetaImpl superMeta = map.get(cls.getName());
					if (superMeta != null) {
						meta.setSuperMeta(superMeta);
						break;
					}
					cls = TypeDefUtil.getSuperClass(cls);
				}				
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		
		for (ModifiedTypeMetaImpl meta : (List<ModifiedTypeMetaImpl>) metaList) {
			meta.processInheritance();
		}
		
		for (ModifiedTypeMetaImpl meta : (List<ModifiedTypeMetaImpl>) metaList) {
			ModifiedTypeMeta linkedMeta = getLinkedMeta(meta, map);
			if (linkedMeta == null && !linkTypeMap.isEmpty()) {
				linkedMeta = linkTypeMap.get(meta.getId());
			}
			meta.processLink(linkedMeta);
		}
		
		return metaList;
	}
	
	private ModifiedTypeMeta getLinkedMeta(ModifiedTypeMeta meta, Map<String, ModifiedTypeMetaImpl> map) {
		if (meta.getId().equals("org.eclipse.epf.uma.TaskDescriptor")) {		//$NON-NLS-1$
			return map.get("org.eclipse.epf.uma.Task");
		}
		if (meta.getId().equals("org.eclipse.epf.uma.RoleDescriptor")) {		//$NON-NLS-1$
			return map.get("org.eclipse.epf.uma.Role");
		}
		if (meta.getId().equals("org.eclipse.epf.uma.WorkProductDescriptor")) {		//$NON-NLS-1$
			return map.get("org.eclipse.epf.uma.WorkProduct");
		}
		return null;
	}
	
}
