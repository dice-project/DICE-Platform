package org.eclipse.epf.uma.util;

public interface ExtendedTable  extends MetaElement {

	ExtendedReference getColumnReference();
	ExtendedReference getRowReference();
	ExtendedReference getCellReference();
	String getColumnSplit();	
}
