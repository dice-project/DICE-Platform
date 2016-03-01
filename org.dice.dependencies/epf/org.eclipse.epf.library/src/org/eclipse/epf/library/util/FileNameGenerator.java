//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ILibraryServiceListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * helper class to produce a unique name for each guid or element
 * 
 * @author Jinhua Xi
 * @since 1.2
 *
 */
public class FileNameGenerator {
	public static FileNameGenerator INSTANCE = new FileNameGenerator();
	
	private Map<String, String> nameToGuidMap = new HashMap<String, String>();
	private Map<String, String> guidToNameMap = new HashMap<String, String>();
	private Map<String, Integer> baseNameIndexMap = new HashMap<String, Integer>();
	
	private ILibraryServiceListener svcListener;
	
	private FileNameGenerator() {
		
		svcListener = new ILibraryServiceListener(){

			public void configurationSet(MethodConfiguration config) {
				// TODO Auto-generated method stub
				
			}

			public void libraryClosed(MethodLibrary library) {
				FileNameGenerator.this.clear();
				
			}

			public void libraryCreated(MethodLibrary library) {
				FileNameGenerator.this.clear();
				
			}

			public void libraryOpened(MethodLibrary library) {
				FileNameGenerator.this.clear();
				
			}

			public void libraryReopened(MethodLibrary library) {
				FileNameGenerator.this.clear();
				
			}

			public void librarySet(MethodLibrary library) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		LibraryService.getInstance().addListener(svcListener);
	}
	
	/**
	 * get the guid from the file name, assuming the file name is generated from an element, 
	 * return null if not found.
	 * @param fileName String
	 * @return String
	 */
	public synchronized String getGuidFromFileName(String fileName) {
		if ( fileName == null || fileName.equals("") ) { //$NON-NLS-1$
			return null;
		}
		
		return getGuidFromUniqueName(getUniqueName(fileName));
	}

	/**
	 * get the file name with the given prefix and extension
	 * @param element MethodElement
	 * @param namePrefix String prefix for the name
	 * @param nameSuffix String suffix for the name
	 * @param ext String file extension, for example, ".html"
	 * @return String
	 */	 
	public synchronized String getFileName(MethodElement element, String namePrefix,
			String nameSuffix, String ext) {

		String uniqueName = getUniqueNameForGuid(element.getGuid(), element.getName());
		
		StringBuffer buffer = new StringBuffer();
		if (namePrefix != null) {
			buffer.append(namePrefix);
		}

		buffer.append(uniqueName);
		
		if (nameSuffix != null) {
			buffer.append(nameSuffix);
		}

		if (ext != null) {
			if (ext.indexOf(".") < 0) //$NON-NLS-1$
			{
				ext = "." + ext; //$NON-NLS-1$
			}
			buffer.append(ext);
		}
		
		return buffer.toString();
	}
	
	public void clear() {
		nameToGuidMap.clear();
		guidToNameMap.clear();
		baseNameIndexMap.clear();
	}
	
	private static final int MAX_NAME_LENGTH = 64;
	
	private String getUniqueNameForGuid(String guid, String defaultName) {
		if ( guid == null || guid.equals("") ) { //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
		
		// if the name is already genenrated, return it
		String uniqueName = guidToNameMap.get(guid);
		if ( uniqueName != null ) {
			return uniqueName;
		}
		
		// construct the base file name of the element
		// note: to make the name consistent everytime the name is generated, 
		// we still need to make the name guid specific. 
		// appending the guid makes the string to long, so use the hashcode
		// however, hashcode may have collision, so still need to append the suffix index,
		// in such case, the file name may still not uniquely binding to the same element consistently.
		// but the chance is much smaller
		if ( defaultName == null ) {
			defaultName = ""; //$NON-NLS-1$
		} 
		
		// make sure the base name string is valid and not exceed the maximum length
		String baseName = StrUtil.removeSpecialCharacters(defaultName);	
		if ( baseName.length() > MAX_NAME_LENGTH ) {
			baseName = baseName.substring(0, MAX_NAME_LENGTH);
		}
			
		baseName += "_" + Integer.toHexString(guid.hashCode()).toUpperCase(); //$NON-NLS-1$

		uniqueName = baseName;
		Integer index = baseNameIndexMap.get(baseName);
		if ( index == null ) {
			// this is the first with this base name, use it
			index = new Integer(1);
		} else {
			// increase the index by one and append to the end of the base name
			index++;
			uniqueName = baseName + "-" + index.toString(); //$NON-NLS-1$
		}
		
		baseNameIndexMap.put(baseName, index);
		guidToNameMap.put(guid, uniqueName);
		nameToGuidMap.put(uniqueName, guid);
		return uniqueName;
	}
	
	private String getGuidFromUniqueName(String uniqueName) {
		return nameToGuidMap.get(uniqueName);
	}
		
	private String getUniqueName(String fileName) {
		
		try {
		File f = new File(fileName);
		fileName = f.getName();
		int index = fileName.lastIndexOf("."); //$NON-NLS-1$
		if ( index < 0 ) {
			return fileName;
		}
		
		return fileName.substring(0, index);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ""; //$NON-NLS-1$
	}
}
