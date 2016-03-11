package org.eclipse.epf.uma.util;

import java.util.List;

public interface ModifiedTypeMeta extends MetaElement {
	
	public List<ExtendedReference> getReferences();
	@Deprecated
	public List<ExtendedAttribute> getRtes();
	public List<ExtendedAttribute> getAttributes();	
	
	public List<ExtendedSection> getSections();
	public List<ExtendedSection> getReferenceSections();
	@Deprecated
	public List<ExtendedSection> getRteSections();
	public List<ExtendedSection> getAttributeSections();
	public List<ExtendedTable> getTables();
	public List<String> getLinkTypes();
	
	public boolean processLink(ModifiedTypeMeta linkedMeta);
	
	public boolean isLinkedFeature(ExtendedFeature feature);
	
	public boolean isLinkedSection(ExtendedSection section);
	
	public ExtendedAttribute getExtendedAttribute(String globalId);
	
}
