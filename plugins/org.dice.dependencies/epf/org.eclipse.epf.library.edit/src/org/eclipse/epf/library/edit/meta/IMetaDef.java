package org.eclipse.epf.library.edit.meta;

import org.w3c.dom.Element;

public interface IMetaDef {
	
	public static final String scopeSeperator = ".."; 							//$NON-NLS-1$

	//Elements
	public static final String MODIFIED_TYPE = "modifiedType"; 					//$NON-NLS-1$
	
	public static final String SECTION = "section"; 						//$NON-NLS-1$
	
	public static final String REFERENCE = "reference"; 						//$NON-NLS-1$
	
	public static final String RTE = "rte"; 									//$NON-NLS-1$
	
	public static final String ATTRIBUTE = "attribute"; 						//$NON-NLS-1$
	
	public static final String TABLE = "table";									///$NON-NLS-1$
	
	public static final String REFERENCE_QUALIFIERS = "reference_qualifiers"; 	//$NON-NLS-1$

	public static final String QUALIFIER = "qualifier"; 						//$NON-NLS-1$

	public static final String columnReference = "columnReference";				//$NON-NLS-1$
	
	public static final String rowReference = "rowReference";					//$NON-NLS-1$

	public static final String cellReference = "cellReference";					//$NON-NLS-1$
	
	public static final String valueType = "valueType";							//$NON-NLS-1$
	
	public static final String oppositeReference = "oppositeReference";			//$NON-NLS-1$
	
	public static final String value = "value";							//$NON-NLS-1$
	
	//Attributes
	public static final String NAME = "name"; 									//$NON-NLS-1$

	public static final String ID = "id"; 										//$NON-NLS-1$
	
	public static final String SUPPRESSED = "suppressed"; 						//$NON-NLS-1$
	
	public static final String type = "type"; 									//$NON-NLS-1$
		
	public static final String linkType = "linkType";							//$NON-NLS-1$

	public static final String columnSplit = "columnSplit";						//$NON-NLS-1$
	
	public static final String contributeTo = "contributeTo";					//$NON-NLS-1$
	
	public static final String publish = "publish";								//$NON-NLS-1$
	
	public static final String layout = "layout";								//$NON-NLS-1$
	
	public static final String richText = "richText"; 									//$NON-NLS-1$
	
	public static final String text = "text"; 									//$NON-NLS-1$
	
	public static final String choice = "choice"; 								//$NON-NLS-1$
	
	public static final String attachment = "attachment"; 						//$NON-NLS-1$
	
	//APIs
	public String getName();
	
	public void parseElement(Element element)	throws TypeDefException;
		
}
