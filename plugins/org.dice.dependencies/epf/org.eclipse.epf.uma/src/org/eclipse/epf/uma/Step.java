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
package org.eclipse.epf.uma;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Step is a Section and Work Definition that is used to organize Tasks into parts or subunits of work.  Steps inherit the subSections decomposition from Section and can therefore describe Sub-Steps nested into Steps.
 * A Step describes a meaningful and consist part of the overall work described for a Task.  The collection of Steps defined for a Task represents all the work that should be done to achieve the overall development goal of the Task.  Not all steps are necessarily performed each time a Task is invoked in a Process (see Task Descriptor), so they can also be expressed in the form of alternate 'flows' of work.  Different ways of achieving the same development goal can then be 'assembled' by selecting different combinations of steps when applying the Task in a Process.  Typical kinds of steps a Task author should consider are: Thinking steps: where the individual roles understand the nature of the task, gathers and examines the input artifacts, and formulates the outcome. Performing steps: where the individual roles create or update some artifacts. Reviewing steps: where the individual roles inspects the results against some criteria.
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.epf.uma.UmaPackage#getStep()
 * @model
 * @generated
 */
public interface Step extends Section, WorkDefinition {
} // Step
