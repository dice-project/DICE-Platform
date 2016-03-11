package org.eclipse.epf.library.edit.meta.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ExtendedSection;
import org.eclipse.epf.uma.util.ExtendedTable;
import org.eclipse.epf.uma.util.MetaElement;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.QualifiedReference;
import org.w3c.dom.Element;

public class MetaElementImpl implements MetaElement, IMetaDef, Adapter {

	private String id;
	private String name;
	private String textContent;

	private String globalId;
	private boolean suppressed = false;;
	private MetaElement parent;	
	private boolean inheritanceProcessed =false;
	private MetaElement superMeta;
	private boolean publish = true;
	private String layout;

	public MetaElementImpl(MetaElement parent) {
		this.parent = parent;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTextContent() {
		return textContent;
	}
	
	public String getGlobalId() {
		if (globalId == null) {
			globalId = getGlobalId_();
		}
		return globalId;		
	}
	
	private String getGlobalId_() {
		if (getParent() != null) {
			return getParent().getGlobalId() + IMetaDef.scopeSeperator + getId();
		}
		return getId();
	}

	public MetaElement getParent() {
		return parent;
	}
	
	public boolean isSuppressed() {
		return suppressed;
	}
	
    public int compareTo(MetaElement o) {
    	return getName().compareTo(o.getName());
    }
	
	private void validateId(String id) throws TypeDefException {
		String context = "";	//$NON-NLS-1$
		if (this instanceof ModifiedTypeMeta) {
			context = "Modified type";//$NON-NLS-1$
			
		} else if (this instanceof ExtendedSection) {
			context = "Section";//$NON-NLS-1$
			
		} else if (this instanceof ExtendedAttribute) {
			context = "Rte";//$NON-NLS-1$
			
		} else if (this instanceof QualifiedReference) {
			context = "Qualified reference";//$NON-NLS-1$
			
		} else if (this instanceof ExtendedReference) {
			context = "Reference";//$NON-NLS-1$
				
		} else if (this instanceof ExtendedTable) {
			context = "Table";//$NON-NLS-1$
			
		} else if (this instanceof ExtendedOpposite) {
			if (id == null || id.length() == 0) {
				return;
			}
			context = "Opposite feature";//$NON-NLS-1$
		}

		TypeDefUtil.getInstance().validateId(context, id);	
	}
    
	public void parseElement(Element element) throws TypeDefException {
		if (element == null) {
			return;
		}
		id = element.getAttribute(IMetaDef.ID);
		name = element.getAttribute(IMetaDef.NAME);
		layout = element.getAttribute(IMetaDef.layout);
		if (id != null) {
			id = id.trim();
		}
		if (name != null) {
			name = name.trim();
		}
		if (layout != null && layout.length() > 0) {
			layout = layout.trim();
		}
		String str = element.getAttribute(IMetaDef.SUPPRESSED);
		suppressed = str == null ? false : Boolean.parseBoolean(str.trim());
		
		str = element.getAttribute(IMetaDef.publish);
		publish = str == null || str.trim().length() == 0 ? true : Boolean.parseBoolean(str.trim());
				
		Element nameElement = XMLUtil.getFirstChildElementByTagName(element, IMetaDef.NAME);
		if (nameElement != null) {
			name = nameElement.getTextContent();
		}
		name = name.trim();		
		textContent = element.getTextContent().trim();
		
		validateId(id);
	}
	
	//Return true if this method is called the first time
	public boolean processInheritance() {
		if (inheritanceProcessed) {
			return false;
		}
		inheritanceProcessed = true;
		return true;
	}
	
	protected static List<? extends MetaElement> processInherentList(List<? extends MetaElement> myList, List<? extends MetaElement> superList) {
		Map<String, MetaElement> map = new HashMap<String, MetaElement>();
		for (MetaElement meta : myList) {
			map.put(meta.getId(), meta);
		}	
		
		List<MetaElement> newList = new ArrayList<MetaElement>();
		for (MetaElement meta : superList) {
			MetaElement childMeta = map.remove(meta.getId());
			if (childMeta == null) {
				childMeta = meta;
			} else {
				childMeta.setSuperMeta(meta);
			}
			if (! childMeta.isSuppressed()) {
				newList.add(childMeta);
			}
		}	
		for (MetaElement meta : myList) {
			if (! meta.isSuppressed() && map.containsKey(meta.getId())) {
				newList.add(meta);
			}
		}
		
		return newList;
	}
	
	protected static List<? extends MetaElement> processSuppress(List<? extends MetaElement> myList) {
		List<MetaElement> newList = new ArrayList<MetaElement>();
		for (MetaElement meta : myList) {
			if (! meta.isSuppressed()) {
				newList.add(meta);
			}
		}		
		return newList;
	}
	
	public boolean publish() {
		return publish;
	}
	
	public String getLayout() {
		return layout;
	}
	
	//Adapter interface methods ->
	public void notifyChanged(Notification notification) {
	}

	public Notifier getTarget() {
		return null;
	}

	public void setTarget(Notifier newTarget) {
	}

	public boolean isAdapterForType(Object type) {
		return false;
	}
	
	public MetaElement getSuperMeta() {
		return superMeta;
	}

	public void setSuperMeta(MetaElement superMeta) {
		this.superMeta = superMeta;
	}
	//Adapter interface methods <-

	public String getDebugString(int ix, String indent) {
		StringBuffer sb = new StringBuffer();
		sb.append(indent + "[" + ix + "] " + getReferenceString(this) + "\n");//$NON-NLS-1$//$NON-NLS-2$
		getDebugStringImpl(sb, indent);
		return sb.toString();
	}
	
	protected String getReferenceString(Object obj) {
		if (obj == null) {
			return null;
		}
		return  getClass().getSimpleName() + ": " + obj.hashCode();//$NON-NLS-1$
	}
	
	protected void getDebugStringImpl(StringBuffer sb, String indent) {
		sb.append(indent + "id:          " + id + "\n");
		sb.append(indent + "name:        " + name + "\n");
		sb.append(indent + "globalId:    " + getGlobalId() + "\n");
		sb.append(indent + "suppressed:  " + suppressed + "\n");
		sb.append(indent + "parent:      " + getReferenceString(parent) + "\n");
		sb.append(indent + "superMeta:   " + getReferenceString(superMeta) + "\n");
		sb.append(indent + "publish:     " + publish + "\n");
		
		sb.append("\n");
	}

	public boolean isAncestorOf(MetaElement element) {
		MetaElement parent = element == null ? null : element.getParent();
		while (parent != null) {
			if (parent == this) {
				return true;
			}
			parent = parent.getParent();
		}
		return false;
	}
	
	public ModifiedTypeMeta getModifiedTypeMeta() {
		MetaElement e = this;
		while (e != null) {
			if (e instanceof ModifiedTypeMeta) {
				return (ModifiedTypeMeta) e;
			}
			e = e.getParent();
		}
		return null;
	}
	
	protected Boolean parseBoolean(Element element, String att) {
		String value = element.getAttribute(att);
		if (value == null) {
			return null;
		}
		if (value.trim().equalsIgnoreCase("true")) {		//$NON-NLS-1$
			return Boolean.TRUE;
		} else if (value.trim().equalsIgnoreCase("false")) {//$NON-NLS-1$
			return Boolean.FALSE;	
		}
		return null;
	}
	
}
