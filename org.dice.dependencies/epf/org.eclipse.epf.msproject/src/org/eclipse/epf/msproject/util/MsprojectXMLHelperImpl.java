/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.msproject.util;

import java.text.FieldPosition;
import java.text.ParseException;
import java.util.Date;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xml.type.internal.XMLCalendar;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * Create a slightly customized XMLHelperImpl class
 * 
 * @author Bingxue Xu
 * @since 1.0
 */
public class MsprojectXMLHelperImpl extends XMLHelperImpl implements XMLHelper {

	 protected static final DateFormat [] EDATE_FORMATS =
	  {
		new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
	    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"),
	    new SafeSimpleDateFormat("yyyy-MM-ddZ")
	  };
	 
	 /**
	  * Constructor
	  * @param resource
	  */
	public MsprojectXMLHelperImpl(XMLResource resource) {
		super(resource);
	}
	
	/**
	 * Convert boolean value to either "0" or "1"
	 * Convert DateTime value to be compatible with MS project
	 */
	 public String convertToString(EFactory factory, EDataType dataType, Object value)
	  {
		 
//		 org.eclipse.emf.ecore.xml.type.impl.XMLTypeFactoryImpl aFactory = new org.eclipse.emf.ecore.xml.type.impl.XMLTypeFactoryImpl();
//		 System.out.println("$$$$$ dataType name = " + dataType.getName());
		 
		 if ("Boolean".equalsIgnoreCase(dataType.getName())) {
//			 System.out.println("$$$ need to override the Boolean: " + value);
			 if (value != null) {
				 String rs = "0";
				 try {
					 Boolean bObject = (Boolean)value;
					 if (bObject.booleanValue())
						 rs = "1";
				 } catch (Exception e) {}
//				 System.out.println("$$$ converted string = " + rs);
				 return rs;
			 }
			 return null; 
		 } else if ("DateTime".equalsIgnoreCase(dataType.getName())) {
//			 System.out.println("$$$ need to override the DateTime: " + value.getClass() + "," + value);
			 if (value != null) {
				   String rs = value.toString();
				   if (value instanceof Date)
				    {
				      rs = EDATE_FORMATS[0].format((Date)value);
				    } else if (value instanceof XMLCalendar){
					  rs = ((XMLCalendar)value).toString();
					  int idx = rs.indexOf('.');
					  if (idx > 0)
						  rs = rs.substring(0, idx);
					}
//				   System.out.println("$$$ converted string = " + rs);
				   return rs;
			 }
			 return null; 
		 }
		 
//		 System.out.println("$$$ EFactory, EDataType, value = " + factory + "," + dataType + "," + value);
		 String rs = super.convertToString( factory,  dataType,  value);
//		 System.out.println("$$$ converted string (out) = " + rs);
		 return rs;
	  }
	 
	 /**
	  * Convert the 1 or 0 boolean value to an Boolean Object
	  * convert the datetime string into XMLCalendar object.
	  */
	 protected Object createFromString(EFactory eFactory, EDataType eDataType, String value)
	  {
		 if ("Boolean".equalsIgnoreCase(eDataType.getName())) {
//			 System.out.println("$$$ need to convert from Boolean: " + value);
			 if (value != null) {
				 if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1"))
					 return Boolean.TRUE;
				 else
					 return Boolean.FALSE;
			 }
			 return null; 
		 } else if ("DateTime".equalsIgnoreCase(eDataType.getName())) {
//			 System.out.println("$$$ need to convert from DateTime: " + value);
			 if (value != null) {
				   return new XMLCalendar(collapseWhiteSpace(value), XMLCalendar.DATETIME);
			 }
			 return null; 
		 }
		 
//		 System.out.println("$$$ EFactory, EDataType, value = " + eFactory + "," + eDataType + "," + value);
		 Object ro = super.createFromString( eFactory,  eDataType,  value);
//		 System.out.println("$$$ converted object (in) = " + ro);
		 return ro;
	  }
	 
	 protected String collapseWhiteSpace(String value)
	 {
		 return XMLTypeUtil.normalize(value, true);
	 }
	  
	 private static class SafeSimpleDateFormat extends SimpleDateFormat
	 {
		private static final long serialVersionUID = 1L;

		public SafeSimpleDateFormat(String pattern)
	    {
	      super(pattern);
	    }

	    public synchronized Date parse(String source) throws ParseException
	    {
	      return super.parse(source);
	    }

	    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos)
	    {
	      StringBuffer result = super.format(date, toAppendTo, pos);
//	      result.insert(result.length() - 2, ":");
	      return result;
	    }
	  }
}
