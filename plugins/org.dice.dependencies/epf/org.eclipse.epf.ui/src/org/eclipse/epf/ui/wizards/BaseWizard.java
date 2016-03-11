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
package org.eclipse.epf.ui.wizards;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.ui.EPFUIPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

/**
 * The abstract base class for all extensible EPF wizards.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * 
 * @since 1.2
 */
public abstract class BaseWizard extends Wizard {

	// The current workbench.
	protected IWorkbench workbench;

	// The current object selection.
	protected IStructuredSelection selection;

	// An extender of this wizard.
	protected IWizardExtender wizardExtender;

	/**
	 * Creates a new instance.
	 */
	public BaseWizard() {
		super();
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;

		processWizardExtenderExtensionPoint();

		initializeExtender();
	}

	protected void initializeExtender() {
		if (wizardExtender != null) {
			wizardExtender.init(this);
		}
	}

	/**
	 * Gets the wizard extender specific extension point ID.
	 * 
	 * @return the wizard extender specific extension point ID
	 */
	public abstract String getWizardExtenderExtensionPointId();

	/**
	 * Gets the extender of this wizard.
	 * 
	 * @return a <code>IWizardExtender</code> object or <code>null</code> if
	 *         there is no extender
	 */
	public IWizardExtender getWizardExtender() {
		return wizardExtender;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#getNextPage(IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = null;
		if (wizardExtender != null) {
			nextPage = wizardExtender.getNextPage(page);
		}
		if (nextPage == null) {
			nextPage = super.getNextPage(page);
		}
		return nextPage;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean canFinish() {
		if (wizardExtender != null) {
			return wizardExtender.canFinish();
		}
		return super.canFinish();
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		if (wizardExtender != null) {
			return wizardExtender.doFinish();
		}
		return doFinish();
	}

	/**
	 * Executes the finish operation.
	 * 
	 * @return <code>true</code> if the finish operation is executed
	 *         successfully
	 */
	public boolean doFinish() {
		return true;
	}

	/**
	 * allow override the extender. the default implementation is to return the
	 * defaultExtender
	 * 
	 * @param defaultExtender
	 * @return IWizardExtender
	 */
	protected IWizardExtender createWizardExtender(
			IWizardExtender defaultExtender) {
		return defaultExtender;
	}

	/**
	 * Processes the wizard extender spcific extension point.
	 */
	protected void processWizardExtenderExtensionPoint() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		String extensionPointId = getWizardExtenderExtensionPointId();
		if (extensionPointId == null) {
			return;
		}

		IExtensionPoint extensionPoint = extensionRegistry
				.getExtensionPoint(extensionPointId);
		if (extensionPoint == null) {
			return;
		}

		IExtension[] extensions = extensionPoint.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] configElements = extension
					.getConfigurationElements();
			for (int j = 0; j < configElements.length; j++) {
				IConfigurationElement configElement = configElements[j];
				try {
					IWizardExtender extender = (IWizardExtender) configElement
							.createExecutableExtension("class"); //$NON-NLS-1$
					if (wizardExtender == null) {
						wizardExtender = createWizardExtender(extender);

						IConfigurationElement[] childConfigElements = configElement
								.getChildren("wizardPage"); //$NON-NLS-1$
						for (int k = 0; k < childConfigElements.length; k++) {
							IConfigurationElement childConfigElement = childConfigElements[k];
							IWizardPage wizardPage = (IWizardPage) childConfigElement
									.createExecutableExtension("class"); //$NON-NLS-1$
							String type = childConfigElement
									.getAttribute("type"); //$NON-NLS-1$
							String target = childConfigElement
									.getAttribute("target"); //$NON-NLS-1$
							String insert = childConfigElement
									.getAttribute("insert"); //$NON-NLS-1$
							if (wizardPage != null && type != null
									&& type.length() > 0) {
								if (type.equals("replace")) { //$NON-NLS-1$
									wizardExtender
											.addReplaceWizardPageContribution(new ReplaceWizardPageContribution(
													wizardPage, target));
								} else if (type.equals("new")) { //$NON-NLS-1$
									boolean insertAfter = true;
									if (insert != null
											&& insert.equals("before")) { //$NON-NLS-1$
										insertAfter = false;
									}
									wizardExtender
											.addNewWizardPageContribution(new NewWizardPageContribution(
													wizardPage, target,
													insertAfter));
								}
							}
						}

						return;
					}
				} catch (Exception e) {
					EPFUIPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}

	/**
	 * Gets all the new wizard pages.
	 */
	protected void getNewWizardPages(List<IWizardPage> wizardPages) {
		List<INewWizardPageContribution> contributions = wizardExtender
				.getNewWizardPageContributions();
		if (contributions != null) {
			for (Iterator<INewWizardPageContribution> it = contributions
					.iterator(); it.hasNext();) {
				NewWizardPageContribution contribution = (NewWizardPageContribution) it
						.next();
				IWizardPage newWizardPage = (IWizardPage) contribution
						.getWizardPage();
				String targetWizardPageName = contribution
						.getTargetWizardPage();
				if (targetWizardPageName != null) {
					int totalPages = wizardPages.size();
					int index = 0;
					for (Iterator<IWizardPage> iter = wizardPages.iterator(); wizardPages
							.size() == totalPages
							&& iter.hasNext();) {
						IWizardPage page = iter.next();
						if (page.getName().equals(targetWizardPageName)) {
							if (contribution.getInsertAfter()) {
								wizardPages.add(index + 1, newWizardPage);
							} else {
								wizardPages.add(index, newWizardPage);
							}
						}
						index++;
					}
					if (wizardPages.size() == totalPages) {
						wizardPages.add(newWizardPage);
					}
				}
			}
		}
	}

}
