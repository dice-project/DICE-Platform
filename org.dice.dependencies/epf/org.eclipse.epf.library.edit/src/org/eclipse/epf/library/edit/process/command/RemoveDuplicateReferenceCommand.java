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
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class RemoveDuplicateReferenceCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private Descriptor descriptor;
	private Collection<EReference> sourceFeatures;
	private Map<EStructuralFeature, Object> featureToOldValueMap = new HashMap<EStructuralFeature, Object>();
	private MethodConfiguration config;

	public RemoveDuplicateReferenceCommand(Descriptor descriptor, Collection<EReference> sourceFeatures, MethodConfiguration config) {
		this.descriptor = descriptor;
		this.sourceFeatures = sourceFeatures;
		this.config = config;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection<Resource> getModifiedResources() {
		if(descriptor.eResource() != null) {
			return Collections.singletonList(descriptor.eResource());
		}
		else {
			return Collections.emptyList();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		MethodElement source = ProcessUtil.getAssociatedElement(descriptor);
		if(source instanceof ContentElement) {
			ContentElement contentElement = (ContentElement) source;
			for (EReference sourceFeature : sourceFeatures) {
				Object sourceValue = Providers.getConfigurationApplicator().getReference(contentElement, sourceFeature, config);
				if((sourceFeature.isMany() && ((List) sourceValue).isEmpty()) || (!sourceFeature.isMany() && sourceValue == null)) {
					continue;
				}
				EStructuralFeature feature = BSDropCommand.getDescriptorFeature(sourceFeature);
				if(feature != null) {
					Object value = descriptor.eGet(feature);
					if((feature.isMany() && ((List) value).isEmpty()) || (!feature.isMany() && value == null)) {
						continue;
					}
					if(feature.isMany()) {
						List list = (List) value;
						List<Object> oldList = new ArrayList<Object>(list);
						if(list.removeAll((Collection) sourceValue)) {
							featureToOldValueMap.put(feature, oldList);
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}

	protected boolean prepare() {
		return true;
	}

	public void undo() {
		if(featureToOldValueMap != null) {
			for (Map.Entry<EStructuralFeature, Object> entry : featureToOldValueMap.entrySet()) {
				descriptor.eSet(entry.getKey(), entry.getValue());
			}
			featureToOldValueMap.clear();
		}
	}
	
}
