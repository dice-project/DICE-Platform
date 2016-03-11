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
package org.eclipse.epf.library.configuration;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.library.edit.IConfigurationApplicator;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * @author Phong Nguyen Le
 * @since 1.1
 */
public class ConfigurationApplicator implements IConfigurationApplicator {
	public static final IConfigurationApplicator INSTANCE = new ConfigurationApplicator();

	public Object getAttribute(VariabilityElement e, EAttribute attribute,
			MethodConfiguration config) {
		// special handling for presentation name of breakdown element
		//
		if(attribute == UmaPackage.eINSTANCE.getMethodElement_PresentationName()) {
			if(e instanceof BreakdownElement) {
				return ProcessUtil.getPresentationName((BreakdownElement) e);
			}
			else {
				return ConfigurationHelper.getPresentationName(e, config);
			}
		}
		return ConfigurationHelper.calcAttributeFeatureValue(e, attribute,
				config);
	}

	public Object getReference(VariabilityElement e, EReference ref,
			MethodConfiguration config) {
		if (ConfigurationHelper.is0nFeature(ref)) {
			return ConfigurationHelper.calc0nFeatureValue(e, ref,
					DefaultElementRealizer.newElementRealizer(config));
		} else if (ConfigurationHelper.is01Feature(ref)) {
			return ConfigurationHelper.calc01FeatureValue(e, ref,
					DefaultElementRealizer.newElementRealizer(config));
		}
		return null;
	}

	public Object getReference(ContentDescription desc,
			DescribableElement owner, EReference ref, MethodConfiguration config) {

		List values = ConfigurationHelper.calc0nFeatureValue(desc, owner, ref,
				DefaultElementRealizer.newElementRealizer(config));
		if (ref.isMany()) {
			return values;
		}
		if (values.isEmpty())
			return null;
		return values;
	}

	public Object getReference(MethodElement element, OppositeFeature feature,
			MethodConfiguration config) {
		List values = ConfigurationHelper.calc0nFeatureValue(element, feature,
				DefaultElementRealizer.newElementRealizer(config));

		return values;
	}

	public Object resolve(Object object, MethodConfiguration config) {
		if (object instanceof MethodElement) {
			Object resolved = ConfigurationHelper.getCalculatedElement(
					(MethodElement) object, config);
			if (resolved == null) {
				return object;
			} else {
				return resolved;
			}
		} else {
			return object;
		}
	}

	public Object getAttribute(ContentDescription desc,
			DescribableElement owner, EAttribute attr,
			MethodConfiguration config) {
		return ConfigurationHelper.calcAttributeFeatureValue(desc, owner, attr,
				config);
	}

}
