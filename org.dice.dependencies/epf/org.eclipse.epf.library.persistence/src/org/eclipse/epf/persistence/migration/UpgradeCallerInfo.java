//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------

package org.eclipse.epf.persistence.migration;

import java.io.File;
import java.util.List;

import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * Class representing caller info for calling library upgrade code.
 * 
 * @author Weiping Lu
 * 
 * @since 1.0
 */
public class UpgradeCallerInfo {
	
	public static int upgradeLibrary = 0;
	public static int upgradeImportPlugin = 1;
	public static int upgradeImportConfig = 2;
	
	private int callerType = upgradeLibrary;
	private String errorMsg;
	private File libFile;
	private File copiedLibFile;
	private List<File> upgradableFiles;
	private MultiFileResourceSetImpl resourceSet;
	private boolean converToSynFree = false;

	public UpgradeCallerInfo(int callerType, File libFile) {
		this.callerType = callerType;
		this.libFile = libFile;
	}
	
	public UpgradeCallerInfo(MultiFileResourceSetImpl resourceSet, List<File> upgradableFiles) {
		this(upgradeLibrary, null);
		this.upgradableFiles = upgradableFiles;
		this.resourceSet = resourceSet;
	}
	
	public static boolean isUpgradeLibrary(UpgradeCallerInfo info) {
		return info == null || info.isUpgradeLibrary();
	}
	
	public boolean isUpgradeLibrary() {
		return callerType == upgradeLibrary;
	}
	
	public boolean getIsExportedPluginLib() {
		return callerType == upgradeImportPlugin;
	}
	
	public void setErrorMsg(String msg) {
		errorMsg = msg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}			
	
	public void setCopiedLibFile(File file) {
		copiedLibFile = file;
	}
	
	public File getCopiedLibFile() {
		return copiedLibFile;
	}
	
	protected File getLibFile() {
		return libFile;
	}

	public void copyLibrary() {
	}
	
	public void removeCopiedLibrary() {
	}

	public List<File> getUpgradableFiles() {
		return upgradableFiles;
	}

	public MultiFileResourceSetImpl getResourceSet() {
		return resourceSet;
	}
	
	public MethodLibrary loadLibrary(File libFile) throws Exception {
		return null;
	}
	
	public boolean isConverToSynFree() {
		return converToSynFree;
	}

	public void setConverToSynFree(boolean converToSynFree) {
		this.converToSynFree = converToSynFree;
	}
	
}
