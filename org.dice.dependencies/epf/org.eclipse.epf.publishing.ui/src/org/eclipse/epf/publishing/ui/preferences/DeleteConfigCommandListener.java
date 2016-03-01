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
package org.eclipse.epf.publishing.ui.preferences;

import java.util.Collection;
import java.util.Iterator;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.command.IDeleteMethodElementCommandListener;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;

/**
 * Command listener for DeleteMethodElementCommand
 * Clean all configuration specific publishing preferences when the configuration is deleted
 * @author Xiang Dong Hu
 * @since 1.2
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=181487
 */
public class DeleteConfigCommandListener implements
		IDeleteMethodElementCommandListener {

	public void collectObjectsToDeleteContent(Collection collectedObjects,
			MethodElement element) {

	}

	public boolean collectObjectsToRemove(Collection objectsToRemove,
			EObject elementToDelete, EObject referencer, Collection references) {
		return false;
	}

	public Class getCommandType() {
		return DeleteMethodElementCommand.class;
	}

	public void notifyExecuted(Command command) {
		Collection result = ((DeleteMethodElementCommand)command).getResult();
		for (Iterator it = result.iterator(); it.hasNext();){
			Object element = it.next();
			if(element instanceof MethodConfiguration){
				String configId = ((MethodConfiguration)element).getGuid();
				removePublishPreferences(configId);
			}
		}
		PublishingUIPreferences.saveAllPreferences();
	}

	public void preExecute(Command command) {

	}

	public void preUndo(Command command) {

	}
	
	protected void removePublishPreferences(String configId){
		PublishingUIPreferences.removeAboutHTML(configId);
		PublishingUIPreferences.removeBannerImage(configId);
		PublishingUIPreferences.removeCheckExternalLinks(configId);
		PublishingUIPreferences.removeConfigPrefInitialized(configId);
		PublishingUIPreferences.removeConvertBrokenLinks(configId);
		PublishingUIPreferences.removeExtraDescriptorInfo(configId);
		PublishingUIPreferences.removeShowLinkedElementForDescriptor(configId);
		PublishingUIPreferences.removeFeedbackURL(configId);
		PublishingUIPreferences.removeIncludeGlossary(configId);
		PublishingUIPreferences.removeIncludeIndex(configId);
		PublishingUIPreferences.removeIncludeSearch(configId);
		PublishingUIPreferences.removeIncludeServletSearch(configId);
		PublishingUIPreferences.removeLightWeightTree(configId);
		PublishingUIPreferences.removePublishEntireConfig(configId);
		PublishingUIPreferences.removePublishADForActivityExtension(configId);
		PublishingUIPreferences.removePublishPath(configId);
		PublishingUIPreferences.removePublishStaticWebSite(configId);
		PublishingUIPreferences.removePublishUnopenActivityDD(configId);
		PublishingUIPreferences.removeTitle(configId);
		PublishingUIPreferences.removeWebAppName(configId);
		PublishingUIPreferences.removeShowRelatedDescriptors(configId);
		PublishingUIPreferences.removeShowDescriptorsInNavigationTree(configId);
	}

	public void postUndo(Command command) {
		// TODO Auto-generated method stub
		
	}


}
