package org.eclipse.epf.uma.util;

import java.util.List;


public interface ExtendedSection  extends MetaElement {

	public String getType();
	public List<ExtendedReference> getReferences();
	public List<ExtendedAttribute> getAttributes();
	@Deprecated
	public List<ExtendedAttribute> getRtes();
	public List<ExtendedTable> getTables();
	
}
