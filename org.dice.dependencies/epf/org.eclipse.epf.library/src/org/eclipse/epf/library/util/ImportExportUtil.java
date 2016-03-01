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
package org.eclipse.epf.library.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.xml.sax.SAXException;

/**
 * utility class to handle element replace or renaming for import/export
 * 
 * @author ???
 * @since 1.0
 *
 */
public class ImportExportUtil {
	
	/**
	 * 
	 * @param procComp
	 * @param dir
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws FactoryConfigurationError
	 * @throws SAXException
	 */
	public static void replace(ProcessComponent procComp, String dir)
			throws IOException, ParserConfigurationException,
			FactoryConfigurationError, SAXException {
		File newProcCompFile = new File(dir,
				MultiFileSaveUtil.DEFAULT_MODEL_FILENAME);
		if (!newProcCompFile.exists()) {
			throw new FileNotFoundException(
					MessageFormat
							.format(
									LibraryResources.ImportExportUtil_MSG8, new Object[] { newProcCompFile }));
		}

		String oldModelFileStr = procComp.eResource().getURI().toFileString();
		String oldProcCompDir = new File(oldModelFileStr).getParent();

		String suffix = MultiFileSaveUtil.getBackupFileSuffix();

		File tempDir = null;

		try {
			// copy dir a temp dir inside the target dir
			//
			tempDir = new File(oldProcCompDir, suffix);
			if (!tempDir.mkdir()) {
				throw new IOException(
						MessageFormat
								.format(
										LibraryResources.ImportExportUtil_MSG9, new Object[] { tempDir }));
			}
			FileUtil.copyDir(new File(dir), tempDir);
			newProcCompFile = new File(tempDir,
					MultiFileSaveUtil.DEFAULT_MODEL_FILENAME);

			// load the new model.xmi
			//
			ResourceSet resourceSet = procComp
					.eResource().getResourceSet();
			Resource newResource = resourceSet.getResource(URI
					.createFileURI(newProcCompFile.getAbsolutePath()), true);

			// fix configuration references
			//
			ProcessComponent newProcComp = (ProcessComponent) PersistenceUtil
					.getMethodElement(newResource);
			Process newProc = newProcComp.getProcess();
			newProc
					.setDefaultContext(procComp.getProcess()
							.getDefaultContext());
			newProc.getValidContext().clear();
			newProc.getValidContext().addAll(
					procComp.getProcess().getValidContext());

			// fix all steps
			//		
			for (Iterator iter = newProcComp.eAllContents(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof TaskDescriptor) {
					TaskDescriptor taskDesc = (TaskDescriptor) element;
					Task task = taskDesc.getTask();
					String taskName = task == null ? "NONE" : "'" + task.getName() + "'"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					System.out.println("Task: " + taskName); //$NON-NLS-1$
					if (task != null) {
						taskDesc.getSelectedSteps().clear();
						taskDesc.getSelectedSteps().addAll(task.getSteps());
					}
				}
			}

			// replace GUID of new ProcessComponent and its ResourceManager with
			// old ones
			//

			// // read the model.xmi file
			// DocumentBuilder builder =
			// DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Document doc = builder.parse(newProcCompFile);

			// set GUID of new ResourceManager to old GUID
			ResourceManager resMgr = MultiFileSaveUtil
					.getResourceManager(procComp.eResource());
			if (resMgr != null) {
				MultiFileSaveUtil.getResourceManager(newResource).setGuid(
						resMgr.getGuid());
			}

			// NodeList list =
			// doc.getElementsByTagName("org.eclipse.epf.uma.resourcemanager:ResourceManager");
			// Element e = (Element) list.item(0);
			// e.setAttribute("xmi:id", guid);
			// e.setAttribute("guid", guid);

			// set GUID of new ProcessComponent to old GUID
			newProcComp.setGuid(procComp.getGuid());

			// guid = procComp.getGuid();
			// list =
			// doc.getElementsByTagName("org.eclipse.epf.uma:ProcessComponent");
			// e = (Element) list.item(0);
			// e.setAttribute("xmi:id", guid);
			// e.setAttribute("guid", guid);

			// save the new model.xmi
			ILibraryManager manager = LibraryService.getInstance()
					.getCurrentLibraryManager();
			Map saveOptions = manager != null ? manager.getSaveOptions() : new HashMap();			
			newResource.save(saveOptions);

			// back up old model.xmi
			File file = new File(oldModelFileStr);
			FileUtil.moveFile(file, new File(oldModelFileStr + ".bak" + suffix)); //$NON-NLS-1$

			FileUtil.moveFile(newProcCompFile, new File(oldModelFileStr));

			// XMLUtil.saveDocument(doc, oldModelFileStr);

			File newContentFile = new File(tempDir,
					MultiFileSaveUtil.DEFAULT_CONTENT_FILENAME);

			// back up old content.xmi
			String contentFileStr = oldProcCompDir + File.separator
					+ MultiFileSaveUtil.DEFAULT_CONTENT_FILENAME;
			File oldContentFile = new File(contentFileStr);
			FileUtil.moveFile(oldContentFile, new File(contentFileStr + ".bak" + suffix)); //$NON-NLS-1$

			// copy new content.xmi
			if (newContentFile.exists()) {
				FileUtil.moveFile(newContentFile, oldContentFile);
			}
		} finally {
			if (tempDir != null) {
				FileUtil.deleteAllFiles(tempDir.getAbsolutePath());
				tempDir.delete();
			}
		}
	}

}