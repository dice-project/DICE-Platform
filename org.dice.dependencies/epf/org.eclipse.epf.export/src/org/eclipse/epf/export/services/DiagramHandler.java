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
package org.eclipse.epf.export.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Utility class to handle diagram export/import
 * @author Weiping Lu
 * @since 1.2
 *
 */
public class DiagramHandler {
	
	//To do: need to consider element rename issue. 
	//		 -> Resovle the involved file names after all resource names are settled.
	//		 -> Let a sub class to take of it?

	private File sourceLibRoot;
	private File targetLibRoot;
	private Map<String, MapEntryValue> elementMap = new HashMap<String, MapEntryValue>();
	private static boolean debug = false;
		
	/**
	 * Creates a new instance.
	 */	
	public DiagramHandler(File sourceLibRoot, File targetLibRoot) {
		this.sourceLibRoot = sourceLibRoot;
		this.targetLibRoot = targetLibRoot;
	}
	
	protected Map<String, MapEntryValue> getElementMap() {
		return elementMap;
	}
	
	private boolean needToProcess(MethodElement elem) {
		boolean b = elem instanceof CapabilityPattern ||
								elem instanceof DeliveryProcess;
		return b;	
	}
	
	public void registerElement(MethodElement elem) {
		registerElement(elem, false);
	}
	
	public void registerElement(MethodElement elem, boolean processEntry) {
		if (! needToProcess(elem)) {
			return;
		}
		if (elementMap.containsKey(elem.getGuid())) {
			return;
		}
		
		MapEntryValue mapEntryValue = new MapEntryValue();
		mapEntryValue.element = elem;
		elementMap.put(elem.getGuid(), mapEntryValue);		
		
		if (processEntry) {
			processEntry(elem, mapEntryValue);
		}
	}
	
	public void postRegisterElements() {
		for (Iterator<MapEntryValue> it = elementMap.values().iterator(); it.hasNext();) {
			MapEntryValue value = it.next();
			processEntry(value.element, value);
		}	
	}

	private void processEntry(MethodElement elem, MapEntryValue mapEntryValue) {
		File[] files = getFiles(elem);
		File sourceFile = files[0];
		File targetFile = files[1];

		if (sourceFile != null && sourceFile.exists()) {
			mapEntryValue.sourceFile = sourceFile;
		}

		if (targetFile.exists()) {
			if (mapEntryValue.sourceFile == null || needCopy(mapEntryValue.sourceFile, targetFile)) {
				mapEntryValue.targetFile = targetFile;
			}
			mapEntryValue.existing = true;
		} else {
			if (mapEntryValue.sourceFile != null) {
				mapEntryValue.targetFile = targetFile;
			}
		}
		
		if (debug) {
			System.out.println("LD> mapEntryValue: ");					//$NON-NLS-1$
			System.out.println(mapEntryValue.toDebugString("LD> "));	//$NON-NLS-1$
			System.out.println("");										//$NON-NLS-1$
		}
	}
	
	public List getModifiedFiles() {
		List ret = new ArrayList();		
		for (Iterator<MapEntryValue> it = elementMap.values().iterator(); it.hasNext();) {
			MapEntryValue value = it.next();
			if (value.existing && value.targetFile != null) {
				ret.add(value.targetFile.getAbsolutePath());
			}
		}		
		return ret;
	}

	/**
	 * copy all the files to the destination
	 *
	 */
	public void execute() {
		try {
			execute_();
		} catch (Throwable e) {
			if (debug) {
				e.printStackTrace();
			}
		}	
	}
	
	private void execute_() {
		
		for (Iterator<MapEntryValue> it = elementMap.values().iterator(); it.hasNext(); ) {
			MapEntryValue value = it.next();
			if (value.targetFile == null) {
				continue;
			} else if (value.sourceFile != null) {
				File parentFile = value.targetFile.getParentFile();
				FileUtil.copyFile(value.sourceFile, value.targetFile);
				if (debug) {
					System.out.println("LD: File copied: " + value.sourceFile);	//$NON-NLS-1$
				}
			} else {
				value.targetFile.delete();
			}
		}	
	}
		
	/**
	 * Checks to see if the copy action is needed.
	 */
	public static boolean needCopy(File source, File dest) {
		boolean ret = true;
		if (dest.exists()) {
			ret = (dest.lastModified() != source.lastModified())
					|| (dest.length() != source.length());
		}
		return ret;
	}
	
	protected static class MapEntryValue {

		public MethodElement element;
		public File sourceFile;
		public File targetFile;
		public boolean existing = false;		
		
		public String toDebugString(String prompt) {
			String str = prompt + "element: " + element.getName();		//$NON-NLS-1$
			str += "\n" + prompt + "sourceFile: " + sourceFile;		//$NON-NLS-1$ //$NON-NLS-2$
			str += "\n" + prompt + "targetFile: " + targetFile;		//$NON-NLS-1$ 	//$NON-NLS-2$
			str += "\n" + prompt + "existing: " + existing;			//$NON-NLS-1$ 	//$NON-NLS-2$			
			return str;
		}
		
	}

	protected File[] getFiles(MethodElement elem) {
		return getFiles(elem, true);
	}
	
	//[0]: sourceFile
	//[1]: targetFile
	protected File[] getFiles(MethodElement elem, boolean export) {
		File[] files = new File[2];
		
		String elementPath = ResourceHelper.getElementPath(elem);
		if (debug) {
			System.out.println("LD> elementPath: " + elementPath); //$NON-NLS-1$
		}
		if (elementPath == null || elementPath.length() == 0) {
			if (debug) {
				System.out.println("LD> elementPath is null or empty!"); //$NON-NLS-1$
			}
			return files;
		}

		String diagramPath = elementPath + elem.getName() + File.separator + 
								"diagram.xmi";				//$NON-NLS-1$
		
		if (debug) {
			System.out.println("LD> diagramPath 0: " + diagramPath); //$NON-NLS-1$
		}

		File srcRoot = sourceLibRoot;
		File tgtRoot = targetLibRoot;
		
		
		if (export) {
			srcRoot =  getRoot(elem);
		} else {
			tgtRoot =  getRoot(elem);
		}
		
		if (export) {
			Resource res = elem.eResource();
			if (res != null) {
				File modelFile = new File(res.getURI().toFileString());
				File folder = modelFile.getParentFile();
				String folderPath = folder.getAbsolutePath();

				int ix = srcRoot.getAbsolutePath().length();
				if (folderPath.length() > ix
						&& folderPath.substring(0, ix).equalsIgnoreCase(
								srcRoot.getAbsolutePath())) {
					diagramPath = folderPath.substring(ix + 1) + File.separator
							+ "diagram.xmi"; //$NON-NLS-1$
				}
			}
		} else {
			ProcessComponent pc = UmaUtil.getProcessComponent(elem);
			if (pc != null && ! pc.getName().equals(elem.getName())) {
				diagramPath = elementPath + pc.getName() + File.separator
						+ "diagram.xmi"; //$NON-NLS-1$
			}
		}
		
		files[0] = new File(srcRoot, diagramPath);
		files[1] = new File(tgtRoot, diagramPath);		

		return files;
	}
	
	public void visitElementMap(int passId) {
	}

	public File getSourceLibRoot() {
		return sourceLibRoot;
	}
	
	protected File getRoot(MethodElement elem) {
		ILibraryResourceManager libResMgr = ResourceHelper.getResourceMgr(elem);
		String pluginPath = libResMgr.getPhysicalPluginPath(elem);
		File root = (new File(pluginPath)).getParentFile();
		return root;
	}

	public File getTargetLibRoot() {
		return targetLibRoot;
	}
	
}
