package org.eclipse.epf.library.edit.meta;

import java.util.List;

import org.w3c.dom.Document;

public interface TypeDefParser {

	public List<IMetaDef> parse(Document doc) throws TypeDefException;
	
}
