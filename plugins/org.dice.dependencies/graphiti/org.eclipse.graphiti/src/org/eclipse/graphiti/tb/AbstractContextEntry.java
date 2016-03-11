/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.tb;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.internal.command.CommandExec;
import org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext;

/**
 * The Class AbstractContextEntry.
 */
public class AbstractContextEntry implements IContextEntry {

	private IContext context;

	private IFeature feature;

	private String iconId;

	private String text;

	private String description;

	/**
	 * Creates a new {@link AbstractContextEntry}.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public AbstractContextEntry(IFeature feature, IContext context) {
		setFeature(feature);
		setContext(context);
		if (feature instanceof ICustomFeature) {
			setIconId(((ICustomFeature) feature).getImageId());
		}
	}

	public boolean canExecute() {
		if (getFeature() == null) {
			return false;
		}
		return getFeature().canExecute(getContext());
	}

	public void execute() {
		GenericFeatureCommandWithContext genericFeatureCommandWithContext = new GenericFeatureCommandWithContext(getFeature(), getContext());
		TransactionalEditingDomain editingDomain = getFeature().getFeatureProvider().getDiagramTypeProvider().getDiagramEditor()
				.getEditingDomain();
		CommandExec.getSingleton().executeCommand(genericFeatureCommandWithContext, editingDomain);
	}

	public IContext getContext() {
		return this.context;
	}

	public IFeature getFeature() {
		return this.feature;
	}

	public String getIconId() {
		return this.iconId;
	}

	public String getText() {
		String ret = this.text;
		if (ret == null) {
			IFeature f = getFeature();
			if (f instanceof ICustomFeature) {
				ICustomFeature customFeature = (ICustomFeature) f;
				ret = customFeature.getName();
			}
		}
		return ret;
	}

	public String getDescription() {
		String ret = this.description;
		if (ret == null) {
			IFeature f = getFeature();
			if (f instanceof ICustomFeature) {
				ICustomFeature customFeature = (ICustomFeature) f;
				ret = customFeature.getDescription();
			}
		}
		return ret;
	}

	private void setContext(IContext context) {
		this.context = context;
	}

	private void setFeature(IFeature feature) {
		this.feature = feature;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDescription(String text) {
		this.description = text;
	}

}
