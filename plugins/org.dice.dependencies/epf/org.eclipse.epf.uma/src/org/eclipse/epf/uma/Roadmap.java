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
 * A representation of the model object '<em><b>Roadmap</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Roadmap is a special Guidance Type which is only related to Activates and therefore has been added by this package to the list of Guidance Types rather than listed in the Guidance Types package.  A Roadmap represents a linear walkthrough of an Activity, typically a Process.
 * An instance of a Roadmap represents important documentation for the Activity or Process it is related to.  Often a complex Activity such as a Process can be much easier understood by providing a walkthrough with a linear thread of a typical instantiation of this Activity.  In addition to making the process practitioner understand how work in the process is being performed, a Roadmap provides additional information about how Activities and Tasks relate to each other over time.  Roadmaps are also used to show how specific aspects are distributed over a whole process providing a kind of filter on the process for this information.
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.epf.uma.UmaPackage#getRoadmap()
 * @model
 * @generated
 */
public interface Roadmap extends Guidance {
} // Roadmap
