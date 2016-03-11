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
package org.eclipse.epf.library.edit.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * This command is used to remove method element references.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RemoveReferencesCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private MethodPlugin plugin;

	private MethodPlugin base;

	private Map<FeatureValuePair, Object> featureValuePairToObjectMap;

	private Collection<Resource> modifiedResources;

	/**
	 * Constructs a command to remove all references in <code>plugin</code>
	 * that are elements of <code>base</code>
	 * 
	 * @param plugin
	 * @param base
	 */
	public RemoveReferencesCommand(MethodPlugin plugin, MethodPlugin base) {
		super();
		this.plugin = plugin;
		this.base = base;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection<Resource> getModifiedResources() {
		return modifiedResources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		featureValuePairToObjectMap = new HashMap<FeatureValuePair, Object>();
		modifiedResources = new HashSet<Resource>();

		for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) plugin
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject ref = (EObject) featureIterator.next();
			EStructuralFeature f = featureIterator.feature();
			if (f != UmaPackage.eINSTANCE.getMethodPlugin_Bases()
					&& UmaUtil.getMethodPlugin(ref) == base) {
				featureValuePairToObjectMap.put(new FeatureValuePair(f, ref),
						plugin);
			}
		}

		for (Iterator iter = plugin.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();

			// ignore ProcessElement b/c it can references anything
			//
			if (element instanceof ProcessElement) {
				continue;
			}

			for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) element
					.eCrossReferences().iterator(); featureIterator.hasNext();) {
				EObject ref = (EObject) featureIterator.next();

				if (UmaUtil.getMethodPlugin(ref) == base) {
					EStructuralFeature f = featureIterator.feature();
					featureValuePairToObjectMap.put(
							new FeatureValuePair(f, ref), element);
					if(f == UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT) {
						EStructuralFeature feature = UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_TYPE;
						featureValuePairToObjectMap.put(
								new FeatureValuePair(feature, element.eGet(feature)), element);
					}
				}
			}
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		for (Iterator iter = featureValuePairToObjectMap.entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			FeatureValuePair fvPair = (FeatureValuePair) entry.getKey();
			EObject element = (EObject) entry.getValue();
			if (fvPair.feature.isMany()) {
				((Collection) element.eGet(fvPair.feature))
						.remove(fvPair.value);
			} else {
				element.eSet(fvPair.feature, null);
			}
			if (element.eResource() != null) {
				modifiedResources.add(element.eResource());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		for (Iterator iter = featureValuePairToObjectMap.entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			FeatureValuePair fvPair = (FeatureValuePair) entry.getKey();
			EObject element = (EObject) entry.getValue();
			if (fvPair.feature.isMany()) {
				((Collection) element.eGet(fvPair.feature)).add(fvPair.value);
			} else {
				element.eSet(fvPair.feature, fvPair.value);
			}
		}
	}
	
	@Override
	public void dispose() {
		base = plugin = null;
		if(featureValuePairToObjectMap != null) {
			featureValuePairToObjectMap.clear();
		}
		if(modifiedResources != null) {
			modifiedResources.clear();
		}
		super.dispose();
	}

	private static class FeatureValuePair {
		EStructuralFeature feature;

		Object value;

		FeatureValuePair(EStructuralFeature f, Object v) {
			feature = f;
			value = v;
		}
	}
}
