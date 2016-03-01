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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * @author Phong Nguyen Le
 * @since 7.0
 */
public class BasicSynchronizeDescriptorCommand extends AbstractCommand
		implements IResourceAwareCommand {
	
	public static final Set NONEMPTY_ATTRIBUTES = new HashSet(Arrays.asList(
			new EStructuralFeature[] {
					UmaPackage.eINSTANCE.getNamedElement_Name(),
					UmaPackage.eINSTANCE.getMethodElement_PresentationName()
			}
	));
	

	protected Descriptor descriptor;
	protected Set synchFeatures;
	protected MethodConfiguration config;
	private HashMap featureMap;

	public BasicSynchronizeDescriptorCommand(Descriptor descriptor, Set synchFeatures, MethodConfiguration config) {
		this.descriptor = descriptor;
		if (synchFeatures == null) {
			this.synchFeatures = BSDropCommand.DEFAULT_SYNCH_FEATURES;
		}
		else {
			this.synchFeatures = synchFeatures;
		}
		this.config = config;
	}
	
	public Collection getModifiedResources() {
		if(descriptor.eResource() != null) {
			return Collections.singletonList(descriptor.eResource());
		}
		else {
			return Collections.EMPTY_LIST;
		}
	}
	
	/**
	 * @param feature
	 * @param value
	 */
	private void saveOldFeatureValue(EStructuralFeature feature) {
		if (featureMap == null) {
			featureMap = new HashMap();
		}
		featureMap.put(feature, descriptor.eGet(feature));
	}


	public void execute() {
		// update the linked element if required
		//
		MethodElement e = ProcessUtil.getAssociatedElement(descriptor);
		Object resolved = Providers.getConfigurationApplicator().resolve(e, config);
		if(resolved != e) {
			EStructuralFeature feature;
			if(descriptor instanceof TaskDescriptor) {
				feature = UmaPackage.eINSTANCE.getTaskDescriptor_Task();					
			}
			else if(descriptor instanceof WorkProductDescriptor) {
				feature = UmaPackage.eINSTANCE.getWorkProductDescriptor_WorkProduct();					
			}
			else if(descriptor instanceof RoleDescriptor) {
				feature = UmaPackage.eINSTANCE.getRoleDescriptor_Role();					
			}
			else {
				feature = null;
			}
			saveOldFeatureValue(feature);
			e = (MethodElement) resolved;
			descriptor.eSet(feature, e);
		}
		
		for (Iterator iter = synchFeatures.iterator(); iter.hasNext();) {
			Object f = iter.next();
			if(f instanceof EAttribute) {
				EAttribute attrib = (EAttribute) f; 
				EAttribute descAttrib = (EAttribute) BSDropCommand.FEATURE_MAP.get(attrib);
				if(descAttrib != null) {
					if(NONEMPTY_ATTRIBUTES.contains(attrib)) {
						// refresh attribute that can not be empty
						//
						Object value =  e instanceof VariabilityElement ? 
								(String) Providers.getConfigurationApplicator().getAttribute((VariabilityElement) e, attrib, config) 
								: e.eGet(attrib);
						// if attriubute is presentation name and new value is blank, copy name over
						if ((descAttrib.equals(UmaPackage.eINSTANCE.getMethodElement_PresentationName())) && (value == null || value.equals(""))) //$NON-NLS-1$
							value = e instanceof VariabilityElement ? 
									(String) Providers.getConfigurationApplicator().getAttribute((VariabilityElement) e, UmaPackage.eINSTANCE.getNamedElement_Name(), config) 
									: e.eGet(UmaPackage.eINSTANCE.getNamedElement_Name());
						Object descValue = descriptor.eGet(descAttrib);
					
						if((value == null && descValue != null) || (value != null && !value.equals(descValue))) {
							saveOldFeatureValue(descAttrib);
							descriptor.eSet(descAttrib, value);
						}
					}
					else {
						// reset attribute to its default value
						//
						saveOldFeatureValue(descAttrib);
						descriptor.eSet(descAttrib, descAttrib.getDefaultValue());
					}
				}
			}
		}
	}

	public void redo() {
		execute();
	}
	
	protected boolean prepare() {
		return true;
	}
	
	public void undo() {
		if(featureMap != null) {
			for (Iterator iterator = featureMap.entrySet().iterator(); iterator
			.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				EStructuralFeature feature = (EStructuralFeature) entry
				.getKey();
				try {
					descriptor.eSet(feature, entry.getValue());
				}
				catch(RuntimeException e) {
					throw e;
				}
			}	
			featureMap.clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose() {
		if(featureMap != null) {
			featureMap.clear();
			featureMap = null;
		}		
		super.dispose();
	}
}
