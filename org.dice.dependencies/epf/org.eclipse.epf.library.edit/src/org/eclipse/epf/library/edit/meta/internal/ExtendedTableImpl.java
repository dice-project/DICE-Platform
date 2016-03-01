package org.eclipse.epf.library.edit.meta.internal;

import java.util.List;

import org.eclipse.epf.library.edit.meta.IMetaDef;
import org.eclipse.epf.library.edit.meta.TypeDefException;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ExtendedSection;
import org.eclipse.epf.uma.util.ExtendedTable;
import org.eclipse.epf.uma.util.MetaElement;
import org.w3c.dom.Element;

public class ExtendedTableImpl extends MetaElementImpl implements ExtendedTable {

	private ExtendedReference columnReference;
	private ExtendedReference rowReference;
	private ExtendedReference cellReference;
	private String columnSplit;
	
	public ExtendedTableImpl(MetaElement parent) {
		super(parent);
	}
	
	public ExtendedReference getColumnReference() {
		return columnReference;
	}

	public ExtendedReference getRowReference() {
		return rowReference;
	}

	public ExtendedReference getCellReference() {
		return cellReference;
	}
	
	public String getColumnSplit() {
		return columnSplit;
	}
	
	public void parseElement(Element element)	throws TypeDefException {
		super.parseElement(element);
		if (! (getParent() instanceof ExtendedSection)) {
			return;
		}
		List<ExtendedReference> references = ((ExtendedSection) getParent()).getReferences();
		if (references.isEmpty()) {
			return;
		}
		String colStr = element.getAttribute(IMetaDef.columnReference);
		String rowStr = element.getAttribute(IMetaDef.rowReference);
		String celStr = element.getAttribute(IMetaDef.cellReference);
		columnSplit = element.getAttribute(IMetaDef.columnSplit);
		if (colStr == null || rowStr == null || celStr == null) {
			return;
		}
		colStr = colStr.trim();
		rowStr = rowStr.trim();
		celStr = celStr.trim();
		
		for (ExtendedReference ref : references) {
			if (colStr.equals(ref.getId())) {
				columnReference = ref;
			}
			if (rowStr.equals(ref.getId())) {
				rowReference = ref;
			}
			if (celStr.equals(ref.getId())) {
				cellReference = ref;
			}
			if (columnReference != null && rowReference != null && cellReference != null) {
				break;
			}
		}

	}	
	
}
