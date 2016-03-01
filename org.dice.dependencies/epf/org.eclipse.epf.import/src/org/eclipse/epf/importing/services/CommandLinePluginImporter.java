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
package org.eclipse.epf.importing.services;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.common.service.utils.CommandLineRunner;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.importing.services.PluginImportData.PluginInfo;
import org.eclipse.epf.library.xmi.XMILibraryUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implementation for importing plugin driven by command line
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class CommandLinePluginImporter extends CommandLineRunner {

	private static String ImportPluginsTag = "ImportPlugins"; 	//$NON-NLS-1$"	
	private static String SelectedPluginTag = "SelectedPlugin"; //$NON-NLS-1$"
	
	private static String BaseLibraryFolderUriAtt= "baseLibraryFolderUri";		//$NON-NLS-1$"
	private static String ImportLibraryFolderUriAtt= "importLibraryFolderUri";		//$NON-NLS-1$"
	private static String NameAtt= "name";		//$NON-NLS-1$"

	private String baseLibPath;
	private String importLibPath;
	
	private Set<String> selectedPlugins;
	
	public boolean execute(String[] args) {
		if (! super.execute(args)) {
			return false;
		}
		try {
			loadInputFile();
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				public void execute(IProgressMonitor monitor) {
					try {
						monitor.setTaskName("Openning base library and preparing import ...");//$NON-NLS-1$"
						XMILibraryUtil.openMethodLibrary(baseLibPath);

						File importLibFolder = new File(importLibPath);

						PluginImportData data = new PluginImportData();
						data.llData.setLibName(importLibFolder.getName());
						data.llData.setParentFolder(importLibFolder.getAbsolutePath());
						final PluginImportingService service = new PluginImportingService(data);
						service.validate(null);
						
						List importPlugins = data.getPlugins();
						for (int i=0; i < importPlugins.size(); i++) {
							PluginInfo info = (PluginInfo) importPlugins.get(i);
							info.selected = selectedPlugins.isEmpty() || selectedPlugins.contains(info.name);							
						}
						
						service.performImport(monitor);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						monitor.done();
					}
				}
			};
			
			ProgressMonitorDialog pmDialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
			pmDialog.run(true, false, operation);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	private void loadInputFile() throws Exception {
		Document document = XMLUtil.loadXml(getInputFile());	
		Element root = document.getDocumentElement();
		if (! root.getTagName().equals(ImportPluginsTag)) {
			String msg = "The root element must be:  " + ImportPluginsTag;  //$NON-NLS-1$
			throw new RuntimeException(msg);
		}
		baseLibPath = root.getAttribute(BaseLibraryFolderUriAtt);
		importLibPath = root.getAttribute(ImportLibraryFolderUriAtt);
		selectedPlugins = new HashSet<String>();
		
		NodeList nodes = root.getChildNodes();
		int sz = nodes == null ? 0 : nodes.getLength();
		for (int i=0; i<sz; i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				Element element = (Element) node;
				if (element.getTagName().equals(SelectedPluginTag)) {
					String name  = element.getAttribute(NameAtt);					
					selectedPlugins.add(name);
				}
			}
		}		

	}

}
